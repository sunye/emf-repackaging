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
 * $Id: TableObject.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.rose2ecore;



public class TableObject 
{
  private String name;
  private String quid;
  private UnitTreeNode container;
  private Object object;

  public TableObject(String name, String quid, UnitTreeNode unitTree)
  {
    this.name = name;
    this.quid = quid;
    container = unitTree;
  }

  public void setName(String name) 
  {
    this.name = name;
  }

  public void setQUID(String quid) 
  {
    this.quid = quid;
  }

  public void setContainer(UnitTreeNode container) 
  {
    this.container = container;
  }

  public String getName() 
  {
     return name;
  }

  public String getQUID() 
  {
    return quid;
  }

  public UnitTreeNode getContainer() 
  {
    return container;
  }

  public void setObject(Object object) 
  {
    this.object = object;
  }

  public Object getObject() 
  {
    return object;
  }
}
