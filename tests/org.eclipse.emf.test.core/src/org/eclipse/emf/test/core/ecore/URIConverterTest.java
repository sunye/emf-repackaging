/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: URIConverterTest.java,v 1.1 2006/12/31 19:29:58 marcelop Exp $
 */
package org.eclipse.emf.test.core.ecore;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.URIConverterImpl;
import org.eclipse.emf.test.core.TestUtil;

public class URIConverterTest extends TestCase
{
  /**
   * @param name
   */
  public URIConverterTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("URIConverterTest");
    ts.addTest(new URIConverterTest("testCompressedURI"));
    return ts;
  }
  
  public void testCompressedURI() throws Exception
  {
    String pluginDirectory = TestUtil.getPluginDirectory();
    URIConverter uriConverter = new URIConverterImpl();

    URI uri = URI.createFileURI(pluginDirectory + "/data/file.txt");
    String contentsFromUncompressedFile = readFile(uriConverter.createInputStream(uri));
    
    uri = URI.createURI("jar:file:/" + pluginDirectory + "/data/data.jar!/file.txt");    
    String contents = readFile(uriConverter.createInputStream(uri));    
    assertEquals(contentsFromUncompressedFile, contents);

    uri = URI.createURI("archive:file:/" + pluginDirectory + "/data/data.jar!/file.txt");    
    contents = readFile(uriConverter.createInputStream(uri)); 
    assertEquals(contentsFromUncompressedFile, contents);    
    
    uri = URI.createURI("archive:file:/" + pluginDirectory + "/data/data.zip!/file.txt");    
    contents = readFile(uriConverter.createInputStream(uri)); 
    assertEquals(contentsFromUncompressedFile, contents);
    
    //Access the data.zip in our CVS repository.
    uri = URI.createURI("archive:http://dev.eclipse.org/viewcvs/indextools.cgi/org.eclipse.emf/tests/org.eclipse.emf.test.core/data/data.zip!/file.txt");    
    contents = readFile(uriConverter.createInputStream(uri)); 
    assertEquals(contentsFromUncompressedFile, contents);
  }
  
  protected String readFile(InputStream inputStream) throws IOException
  {
    if (!(inputStream instanceof BufferedInputStream))
    {
      inputStream = new BufferedInputStream(inputStream);
    }
    
    StringBuilder sb = new StringBuilder();
    byte[] buffer = new byte[1024];
    int size = 0;
    while ((size = inputStream.read(buffer)) > -1)
    {
      sb.append(new String(buffer, 0, size));
    }
    return sb.toString();
  }
}
