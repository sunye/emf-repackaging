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
 * $Id: JETMark.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.jet;


import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import org.eclipse.emf.codegen.CodeGenPlugin;


/**
 * A mark represents a point in the JET input.
 */
public final class JETMark 
{
  /**
   * This is the character offset.
   */
  protected int cursor;

  /**
   * This is the line index.
   */
  protected int line;

  /**
   * This is the column index.
   */
  protected int col;      

  /**
   * This is the id of the file.
   */
  protected int fileid;                 

  /**
   * This is the base URI for relative paths.
   */
  protected String baseDir;             

  /**
   * This is the stream of characters.
   */
  protected char[] stream = null;

  /**
   * This is the stack of inclusions.
   */
  protected Stack includeStack = null;  

  /**
   * This is the encoding of the stream.
   */
  protected String encoding = null;

  /**
   * This is the reader that owns this mark.
   */
  protected JETReader reader;   
                             
  /**
   * Keep track of parser before parsing an included file.
   * This class keeps track of the parser before we switch to parsing an
   * included file. In other words, it's the parser's continuation to be
   * reinstalled after the included file parsing is done.
   */
  class IncludeState 
  {
    int cursor;
    int line;
    int col;
    int fileid;
    String baseDir;
    String encoding;
    char[] stream = null;

    IncludeState(int inCursor, int inLine, int inCol, int inFileid, String inBaseDir, String inEncoding, char[] inStream)
    {
      cursor = inCursor;
      line = inLine;
      col = inCol;
      fileid = inFileid;
      baseDir = inBaseDir;
      encoding = inEncoding;
      stream = inStream;
    }
  }

  /**
   * Creates a new mark
   * @param inReader JETReader this mark belongs to
   * @param inStream current stream for this mark
   * @param inFileid id of requested jet file
   * @param inEncoding encoding of current file
   * @param inBaseDir base directory of requested jet file
   */
  JETMark(JETReader reader, char[] inStream, int fileid, String inBaseDir, String inEncoding)
  {
    this.reader = reader;
    this.stream = inStream;
    this.cursor = this.line = this.col = 0;
    this.fileid = fileid;
    this.baseDir = inBaseDir;
    this.encoding = inEncoding;
    this.includeStack = new Stack();
  }

  JETMark(JETMark other) 
  {
    this.reader = other.reader;
    this.stream = other.stream;
    this.fileid = other.fileid;
    this.cursor = other.cursor;
    this.line = other.line;
    this.col = other.col;
    this.baseDir = other.baseDir;
    this.encoding = other.encoding;

    // clone includeStack without cloning contents
    //
    includeStack = new Stack();
    for (int i = 0; i < other.includeStack.size(); ++i) 
    {
      includeStack.addElement(other.includeStack.elementAt(i));
    }
  }

  /** 
   * Sets this mark's state to a new stream.
   * It will store the current stream in it's includeStack.
   * @param inStream new stream for mark
   * @param inFileid id of new file from which stream comes from
   * @param inBaseDir directory of file
   * @param inEncoding encoding of new file
   */
  public void pushStream(char[] inStream, int inFileid, String inBaseDir, String inEncoding)
  {
    // Store the current state in stack.
    //
    includeStack.push(new IncludeState(cursor, line, col, fileid, baseDir, encoding, stream) );

    // Set the new variables.
    //
    cursor = 0;
    line = 0;
    col = 0;
    fileid = inFileid;
    baseDir = inBaseDir;
    encoding = inEncoding;
    stream = inStream;
  }

  /** 
   * Restores this mark's state to a previously stored stream.
   */
  public boolean popStream() 
  {
    // Make sure we have something to pop.
    //
    if (includeStack.size() <= 0) 
    {
      return false;
    }

    // Get previous state in stack.
    //
    IncludeState state = (IncludeState) includeStack.pop( );

    // Set the new variables.
    //
    cursor = state.cursor;
    line = state.line;
    col = state.col;
    fileid = state.fileid;
    baseDir = state.baseDir;
    stream = state.stream;
    return true;
  }

  public String getFile() 
  {
    return reader.getFile(fileid);
  }

  public String getLocalFile() 
  {
    String file = reader.getFile(fileid);
    if (file.startsWith("file:/"))
    {
      IWorkspaceRoot workspaceRoot =  ResourcesPlugin.getWorkspace().getRoot();
      IFile iFile = workspaceRoot.getFileForLocation(new Path(file.substring(6)));
      file = iFile.getFullPath().toString();
    }

    return file;
  }

  public int getFileId() 
  { 
    return fileid; 
  }

  public int getCursor() 
  {
    return cursor;
  }

  public String toShortString() 
  {
    return "(" + line + "," + col + ")";
  }

  public String toString() 
  {
    return getLocalFile() + "(" + line + "," + col + ")";
  }

  public String format(String key)
  {
    return 
      CodeGenPlugin.getPlugin().getString
        (key, 
         new Object [] 
         { 
           getLocalFile(),
           new Integer(line + 1),
           new Integer(col + 1),
           new Integer(cursor)
         });
  }

  public boolean equals(Object other) 
  {
    if (other instanceof JETMark) 
    {
      JETMark m = (JETMark) other;
      return 
        this.reader == m.reader && 
          this.fileid == m.fileid && 
          this.cursor == m.cursor && 
          this.line == m.line && 
          this.col == m.col;
    }
    return false;
  }
}
