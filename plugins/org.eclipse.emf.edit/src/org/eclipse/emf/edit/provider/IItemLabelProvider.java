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
 * $Id: IItemLabelProvider.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.provider;



/**
 * This is the interface implemented to provide a label text and even a label icon for an item;
 * it receives delegated calls from ILabelProvider.
 */
public interface IItemLabelProvider
{
  /**
   * This does the same thing as ILabelProvider.getlText, 
   * it fetches the label text specific to this object instance.
   */
  public String getText(Object object);

  /**
   * This does the same thing as ILabelProvider.getImage, 
   * it fetches the label image specific to this object instance.
   */
  public Object getImage(Object object);
}
