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
 * $Id: JETParseEventListener.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.jet;


import java.util.Map;


/**
 * The interface for the JET code generation backend. 
 */
public interface JETParseEventListener 
{
  void beginPageProcessing() throws JETException;

  void handleDirective(String directive, JETMark start, JETMark stop, Map attributes) throws JETException;

  void handleExpression(JETMark start, JETMark stop, Map attributes) throws JETException;

  void handleCharData(char[] chars) throws JETException;

  void endPageProcessing() throws JETException;

  void handleScriptlet(JETMark start, JETMark stop, Map attributes) throws JETException;
}
