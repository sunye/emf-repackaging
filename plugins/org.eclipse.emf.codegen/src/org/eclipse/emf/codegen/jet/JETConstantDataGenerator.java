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
 * $Id: JETConstantDataGenerator.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.jet;



/**
 * The JETConstantDataGenerator generates constant declaration of the strings.
 */
public class JETConstantDataGenerator
  extends JETCharDataGenerator
{
  protected static final String CONSTANT_DECLARATION_BEGIN = "protected final String ";
  protected static final String CONSTANT_DECLARATION_MIDDLE = " = ";
  protected static final String CONSTANT_DECLARATION_END = ";";

  protected String label;

  public JETConstantDataGenerator(char[] characters, String label) 
  {
    super(characters);
    this.label = label;
  }

  public String generate() 
  {
    StringBuffer stringBuffer = new StringBuffer(FUNCTION_CALL_BEGIN);
    stringBuffer.append(label);
    stringBuffer.append(FUNCTION_CALL_END);
    return stringBuffer.toString();
  }

  public String generateConstant() 
  {
    StringBuffer stringBuffer = new StringBuffer(CONSTANT_DECLARATION_BEGIN);
    stringBuffer.append(label);
    stringBuffer.append(CONSTANT_DECLARATION_MIDDLE);
    stringBuffer.append(generateCharData());
    stringBuffer.append(CONSTANT_DECLARATION_END);
    return stringBuffer.toString();
  }
}
