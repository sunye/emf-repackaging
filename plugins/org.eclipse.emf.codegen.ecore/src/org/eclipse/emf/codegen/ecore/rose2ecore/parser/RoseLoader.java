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
 * $Id: RoseLoader.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.rose2ecore.parser;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * A loader that creates Buffered Reader.
 *
 */
public class RoseLoader extends RoseComponent 
{
  public static final String PROGRESS = "ROSE_LOADER_PROGRESS";  

  protected boolean valid;
  protected BufferedReader bufferedReader;
  protected long length = 0;
  protected long currentLength = 0;
  protected int  progressIncrement = 10;
  protected int  oldValue;
  protected int  lower = 0;
  protected int  upper = 100;

  public RoseLoader(String fileName) throws Exception
  {
    try 
    {
      if (!fileName.startsWith("\\\\"))      
      {
        fileName = Util.updateFileName(fileName, "\\\\");
      }
      fileName = Util.updateFileName(fileName, "\\");
      fileName = Util.updateFileName(fileName, "/");
      
      bufferedReader = new BufferedReader(new FileReader(fileName));
      valid = true;
      length = (new File(fileName)).length();
      currentLength = 0;
      oldValue = lower;
    } 
    catch (Exception exception) 
    {
      System.out.println(exception.getLocalizedMessage());
      // throw e;
    }
  }

  public boolean isValid()
  {
    return valid;
  }

  public void setProgressIncrement(int progressIncrement)
  {
    this.progressIncrement = progressIncrement;
  }

  public void setLower(int lower)
  {
    this.lower = lower;
    oldValue = lower;
  }

  public int getLower()
  {
    return lower;
  }

  public void setUpper(int upper)
  {
    this.upper = upper;
  }

  public int getUpper()
  {
    return upper;
  }

  public String readLine()
  {
    try 
    {
      String line = bufferedReader.readLine();
      currentLength += line.length();
      if (length > 0) 
      {
        int newValue = lower + (int) (currentLength * (upper - lower) / length);
        if (newValue >= oldValue + progressIncrement && newValue < upper) 
        {
          firePropertyChange(PROGRESS, oldValue, newValue);
          oldValue = newValue;
        }
      }
      return line;
    } 
    catch (Exception e) 
    {
      return null;
    }
  }

  public void close() throws IOException
  {
    if (bufferedReader != null)
    {
      bufferedReader.close();
    }
  }
}
