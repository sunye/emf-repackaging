/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: JETNature.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.jet;


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

import org.eclipse.emf.codegen.CodeGenPlugin;


public class JETNature implements IJETNature 
{
  /**
   * Get a JETNature that corresponds to the supplied project.
   * @return JETNature
   * @param project IProject
   */
  public static JETNature getRuntime(IProject project) 
  {
    try 
    {
      JETNature a = (JETNature) project.getNature(IJETNature.NATURE_ID);
      return a;
    } 
    catch (CoreException e) 
    {
      return null;
    }
  }

  protected static final String BUILDER = "JETBuilder";
  protected static final String DEFAULT_TEMPLATE_CONTAINER_NAME = "templates";
  protected static final String JET_NATURE_PROPERTIES_FILE = ".jetproperties";
  protected static final String TEMPLATE_CONTAINER_NODE = "template-container";
  protected static final String SOURCE_CONTAINER_NODE = "source-container";
  protected static final String JET_SETTINGS_NODE = "jet-settings";

  protected IProject jetProject;
  protected List jetTemplateContainers;
  protected IContainer jetJavaSourceContainer;

  /**
   * Constructor for JETNature.
   */
  public JETNature() 
  {
    super();
  }

  public List getTemplateContainers() 
  {
    if (jetTemplateContainers == null) 
    {
      jetTemplateContainers = getTemplateContainersFromFile();
    }
    return jetTemplateContainers;
  }

  public IContainer getJavaSourceContainer() 
  {
    if (jetJavaSourceContainer == null) 
    {
      jetJavaSourceContainer = getJavaSourceContainerFromFile();
    }
    return jetJavaSourceContainer;
  }

  public void setTemplateContainers(List templateContainers) 
  {
    jetTemplateContainers = templateContainers;
    try 
    {
      setTemplateContainersToFile(templateContainers);
    } 
    catch (CoreException e) 
    {
      CodeGenPlugin.write(e);
    }
  }

  public void setJavaSourceContainer(IContainer javaSourceContainer) 
  {
    jetJavaSourceContainer = javaSourceContainer;
    try 
    {
      setJavaSourceContainerToFile(javaSourceContainer);
    } 
    catch (CoreException e) 
    {
      CodeGenPlugin.write(e);
    }
  }

  public void configure() throws CoreException 
  {
    configure(new NullProgressMonitor());
  }

  public void configure(IProgressMonitor monitor) throws CoreException 
  {
    setDefaults(monitor);

    // Add JETBuilder
    //
    addToFrontOfBuildSpec(CodeGenPlugin.getPlugin().getDescriptor().getUniqueIdentifier() + "." + BUILDER);
  }
  
  /**
   * Sets the containers to their defaults.
   */
  public void setDefaults(IProgressMonitor monitor) throws CoreException
  {
    initTemplateContainer(monitor);
    initJavaSourceContainer(monitor);

    // Create .jetproperties file
    //
    try 
    {
      createDefaultJETSettingsFile(getTemplateContainers(), getJavaSourceContainer());
    } 
    catch (IOException e) 
    {
      CodeGenPlugin.write(e);
    }  
  }

  public void deconfigure() throws CoreException 
  {
  }

  public IProject getProject() 
  {
    return jetProject;
  }

  public void setProject(IProject project) 
  {
    jetProject = project;
  }

  /**
   * Initializes the template container.
   */
  protected void initTemplateContainer(IProgressMonitor monitor) throws CoreException 
  {
    IContainer templateFolder = getContainer(getProject(), DEFAULT_TEMPLATE_CONTAINER_NAME);

    if (templateFolder instanceof IFolder && !templateFolder.exists()) 
    {
      ((IFolder) templateFolder).create(true, true, monitor);
    }

    jetTemplateContainers = new ArrayList();
    jetTemplateContainers.add(templateFolder);
  }

  /**
   * Initializes the Java Source Container
   */
  protected void initJavaSourceContainer(IProgressMonitor monitor) throws CoreException 
  {
    IContainer sourceFolder = getContainer(getProject(), getDefaultSourcePath());
    if (sourceFolder instanceof IFolder && !sourceFolder.exists()) 
    {
      ((IFolder)sourceFolder).create(true, true, monitor);
    }

    jetJavaSourceContainer = sourceFolder;
  }

  /**
   * Returns the project's root directory
   */
  protected IPath getDefaultSourcePath() 
  {
    IPath path = new Path("");
    return path;
  }

  /**
   * Adds a builder to the build spec for the given project.
   */
  protected void addToFrontOfBuildSpec(String builderID) throws CoreException 
  {
    IProjectDescription description = getProject().getDescription();
    ICommand[] commands = description.getBuildSpec();
    boolean found = false;
    for (int i = 0; i < commands.length; ++i) 
    {
      if (commands[i].getBuilderName().equals(builderID)) 
      {
        found = true;
        break;
      }
    }
    if (!found) 
    {
      ICommand command = description.newCommand();
      command.setBuilderName(builderID);
      ICommand [] newCommands = new ICommand [commands.length + 1];
      System.arraycopy(commands, 0, newCommands, 1, commands.length);
      newCommands[0] = command;
      description.setBuildSpec(newCommands);
      getProject().setDescription(description, null);
    }
  }

  /**
   * Returns the template path from the .jetproperties file.
   */
  public List getTemplateContainersFromFile() 
  {
    List result = Collections.EMPTY_LIST;

    try 
    {
      Document document = parseJETSettings();
      if (document != null) 
      {
        result = getContainerValues(document.getDocumentElement(), TEMPLATE_CONTAINER_NODE);
      } 
      else 
      {
        setDefaults(new NullProgressMonitor());
        result = getTemplateContainers();
      }
    } 
    catch (Exception e) 
    {
      try 
      {
        setDefaults(new NullProgressMonitor());
        result = getTemplateContainers();
      } 
      catch (Exception ex) 
      {
        CodeGenPlugin.write(ex);
      }
    }

    return result;
  }

  /**
   * Returns the template file from the .jetproperties file
   */
  public IContainer getJavaSourceContainerFromFile() 
  {
    IContainer result = null;

    try 
    {
      Document document = parseJETSettings();
      if (document != null) 
      {
        result = getContainerValue(document.getDocumentElement(), SOURCE_CONTAINER_NODE);

      } 
      else 
      {
        setDefaults(new NullProgressMonitor());
        result = getJavaSourceContainer();
      }
    } 
    catch (Exception e) 
    {
      try 
      {
        setDefaults(new NullProgressMonitor());
        result = getJavaSourceContainer();
      } 
      catch (Exception ex) 
      {
        CodeGenPlugin.write(ex);
      }
    }

    return result;
  }

  /**
   * Parse the JET settings file for the  root element.
   */
  protected Document parseJETSettings() throws ParserConfigurationException, SAXException, IOException, CoreException 
  {
    Document document = null;
    StringReader reader = readJETSettingFile();
    if (reader != null) 
    {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      documentBuilderFactory.setNamespaceAware(true);
      documentBuilderFactory.setValidating(false);
      DocumentBuilder parser = documentBuilderFactory.newDocumentBuilder();
      document = parser.parse(new InputSource(reader));
      if (!document.getDocumentElement().getNodeName().equalsIgnoreCase(JET_SETTINGS_NODE)) 
      {
        CodeGenPlugin.write(new IOException(CodeGenPlugin.getPlugin().getString("_UI_MalformedJETPropertiesFile_exception")));
      }
      reader.close();
    }
    return document;
  }

  /**
   * Open the JET Settings file and return a StringReader on the contents
   */
  protected StringReader readJETSettingFile() throws CoreException, IOException 
  {
    StringReader reader = null;

    IFile jetSettingsFile = getProject().getFile(JET_NATURE_PROPERTIES_FILE);
    if (jetSettingsFile.exists()) 
    {
      InputStream input = jetSettingsFile.getContents(true);

      String jetSettings = new String(readContentsAsBytes(input));
      reader = new StringReader(jetSettings);
    }
    return reader;
  }

  /**
   * Returns the containers defined from the base Element passed in.
   */
  protected List getContainerValues(Element element, String localName) 
  {
    List result = new ArrayList();
    Element childElement = getChildWithLocalName(element, localName);
    for (Node node = childElement.getFirstChild(); node != null; node = node.getNextSibling())
    {
      if (node.getNodeType() == Node.TEXT_NODE)
      {
        result = getContainers(getProject(), node.getNodeValue());
        break;
      }
    }

    if (result.isEmpty())
    {
      result.add(getProject());
    }

    return result;
  }

  /**
   * Returns the container defined from the base Element passed in.
   */
  protected IContainer getContainerValue(Element element, String localName) 
  {
    IContainer result = null;
    Element childElement = getChildWithLocalName(element, localName);
    for (Node node = childElement.getFirstChild(); node != null; node = node.getNextSibling())
    {
      if (node.getNodeType() == Node.TEXT_NODE)
      {
        result = getContainer(getProject(), node.getNodeValue());
        break;
      }
    }

    if (result == null)
    {
      result = getProject();
    }

    return result;
  }

  /**
   * Sets the template container locations in the settings file
   */
  protected void setContainerValues(List containers, Element element, String localName) 
  {
    Element childElement = getChildWithLocalName(element, localName);
    Text text = null;
    for (Node node = childElement.getFirstChild(); node != null; node = node.getNextSibling())
    {
      if (node.getNodeType() == Node.TEXT_NODE)
      {
        text = (Text)node;
        break;
      }
    }

    if (text == null)
    {
      text = element.getOwnerDocument().createTextNode(getContainers(jetProject, containers));
      childElement.appendChild(text);
    }
    else
    {
      text.setNodeValue(getContainers(jetProject, containers));
    }
  }

  /**
   * Sets the template container location in the settings file
   */
  protected void setContainerValue(IContainer container, Element element, String localName) 
  {
    Element childElement = getChildWithLocalName(element, localName);
    Text text = null;
    for (Node node = childElement.getFirstChild(); node != null; node = node.getNextSibling())
    {
      if (node.getNodeType() == Node.TEXT_NODE)
      {
        text = (Text)node;
        break;
      }
    }

    if (text == null)
    {
      text = element.getOwnerDocument().createTextNode(container.getProjectRelativePath().toString());
      childElement.appendChild(text);
    }
    else
    {
      text.setNodeValue(container.getProjectRelativePath().toString());
    }
  }

  /**
   * Returns the child with the given local name.
   */
  protected Element getChildWithLocalName(Element element, String localName) 
  {
    for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
    {
      if (child.getNodeType() == Node.ELEMENT_NODE)
      {
        Element childElement = (Element)child;
        if (childElement.getLocalName().equals(localName))
        {
          return childElement;
        }
      }
    }

    return null;
  }

  /**
   * Reads an input stream and converts it to bytes
   */
  public static byte[] readContentsAsBytes(InputStream input) throws IOException 
  {
    BufferedInputStream bufferedInputStream = null;
    try 
    {
      final int BUF_SIZE = 8192;
      byte[] buf = new byte[BUF_SIZE];
      int read;
      int totalRead = 0;
      bufferedInputStream = new BufferedInputStream(input);
      while (totalRead < BUF_SIZE && (read = bufferedInputStream.read(buf, totalRead, BUF_SIZE - totalRead)) != -1) 
      {
        totalRead += read;
      }
      if (totalRead < BUF_SIZE) 
      {
        byte[] result = new byte[totalRead];
        System.arraycopy(buf, 0, result, 0, totalRead);
        return result;
      }
      ByteArrayOutputStream out = new ByteArrayOutputStream(BUF_SIZE * 2);
      out.write(buf);
      while ((read = bufferedInputStream.read(buf, 0, BUF_SIZE)) != -1) 
      {
        out.write(buf, 0, read);
      }
      return out.toByteArray();
    } 
    finally 
    {
      try 
      {
        if (bufferedInputStream != null) 
        {
          bufferedInputStream.close();
        }
      } 
      catch (IOException e) 
      {
        CodeGenPlugin.write(e);
      }
    }
  }

  /**
   * Writes the Template Container Location to a file
   */
  public void setTemplateContainersToFile(List templateContainers) throws CoreException 
  {
    Document document;
    try 
    {
      try 
      {
        document = parseJETSettings();
        if (document != null) 
        {
          setContainerValues(templateContainers, document.getDocumentElement(), TEMPLATE_CONTAINER_NODE);
          commitXML(document);
        } 
        else 
        {
          initJavaSourceContainer(new NullProgressMonitor());
          createDefaultJETSettingsFile(templateContainers, getJavaSourceContainer());
        }
      } 
      catch (Exception e) 
      {
        initJavaSourceContainer(new NullProgressMonitor());
        createDefaultJETSettingsFile(templateContainers, getJavaSourceContainer());
      }
    } 
    catch (Exception e) 
    {
      CodeGenPlugin.write(e);
    }
  }

  /**
   * Writes the Java Source Container Location to a file
   */
  public void setJavaSourceContainerToFile(IContainer sourceContainer) throws CoreException 
  {
    Document document;
    try 
    {
      try 
      {
        document = parseJETSettings();
        if (document != null) 
        {
          setContainerValue(sourceContainer, document.getDocumentElement(), SOURCE_CONTAINER_NODE);
          commitXML(document);
        } 
        else 
        {
          initTemplateContainer(new NullProgressMonitor());
          createDefaultJETSettingsFile(getTemplateContainers(), sourceContainer);
        }
      } 
      catch (Exception e) 
      {
        initTemplateContainer(new NullProgressMonitor());
        createDefaultJETSettingsFile(getTemplateContainers(), sourceContainer);
      }
    } 
    catch (Exception e) 
    {
      CodeGenPlugin.write(e);
    }
  }

  /**
   * Writes the default file 
   */
  protected void createDefaultJETSettingsFile(List templateContainers, IContainer sourceContainer) 
    throws CoreException, IOException 
  {
    StringWriter writer = new StringWriter();
    // Create a default .jetsettings file file

    writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    writer.write("\n");
    writer.write("<" + JET_SETTINGS_NODE + ">");
    writer.write("\n");
    writer.write(
      "\t<"
        + TEMPLATE_CONTAINER_NODE
        + ">"
        + getContainers(jetProject, templateContainers)
        + "</"
        + TEMPLATE_CONTAINER_NODE
        + ">");
    writer.write(
      "\t<"
        + SOURCE_CONTAINER_NODE
        + ">"
        + sourceContainer.getProjectRelativePath().toString()
        + "</"
        + SOURCE_CONTAINER_NODE
        + ">");
    writer.write("\n");
    writer.write("</" + JET_SETTINGS_NODE + ">");
    writer.write("\n");

    IFile jetSettingsFile = getProject().getFile(JET_NATURE_PROPERTIES_FILE);
    InputStream sourceStream = new ByteArrayInputStream(writer.toString().getBytes());
    if (jetSettingsFile.exists())
    {
      jetSettingsFile.setContents(sourceStream, true, true, null);
    }
    else
    {
      jetSettingsFile.create(sourceStream, true, null);
    }

    sourceStream.close();
  }


  protected void commitXML(Document document) throws CoreException, ClassNotFoundException, IOException 
  {
    IFile jetSettingsFile = getProject().getFile(JET_NATURE_PROPERTIES_FILE);

    StringWriter writer = new StringWriter();
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    try
    {
      Transformer transformer = transformerFactory.newTransformer();

      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");

      transformer.transform(new DOMSource(document), new StreamResult(writer));
    }
    catch (TransformerException exception)
    {
      CodeGenPlugin.write(exception);
    }

    InputStream sourceStream = new ByteArrayInputStream(writer.toString().getBytes());
    if (jetSettingsFile.exists())
    {
      jetSettingsFile.setContents(sourceStream, true, true, null);
    }
    else
    {
      jetSettingsFile.create(sourceStream, true, null);
    }

    sourceStream.close();
  }

  public static IContainer getContainer(IProject project, IPath path) 
  {
    IContainer result = project;
    if (!path.isEmpty())  
    {
      if (path.isAbsolute())
      {
        IResource resource = project.getWorkspace().getRoot().findMember(path);
        if (resource instanceof IContainer)
        {
          result = (IContainer)resource;
        }
      }
      else
      {
        result = project.getFolder(path);
      }
    }
    return result;
  }

  public static IContainer getContainer(IProject project, String path) 
  {
    return getContainer(project, new Path(path));
  }

  public static List getContainers(IProject project, String paths) 
  {
    List result = new ArrayList();
    for (StringTokenizer stringTokenizer = new StringTokenizer(paths, " ;"); stringTokenizer.hasMoreTokens(); )
    {
      String path = stringTokenizer.nextToken();
      result.add(getContainer(project, new Path(path)));
    }
    return result;
  }

  public static String getContainers(IProject project, List containers)
  {
    StringBuffer result = new StringBuffer();
    for (Iterator i = containers.iterator(); i.hasNext(); )
    {
      IContainer container = (IContainer)i.next();
      if (result.length() != 0)
      {
        result.append(";");
      }
      result.append(getContainer(project, container));
    }
    return result.toString();
  }

  public static String getContainer(IProject project, IContainer container)
  {
    return 
      (project == container.getProject() ?
        container.getProjectRelativePath() :
        container.getFullPath()).toString();
  }
}
