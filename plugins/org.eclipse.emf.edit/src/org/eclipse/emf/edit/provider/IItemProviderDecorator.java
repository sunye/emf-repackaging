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
 * $Id: IItemProviderDecorator.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.provider;



/**
 * This is implemented by an item provider that decorates another item provider.
 */
public interface IItemProviderDecorator 
{
  /**
   * This returns the decorated item provider, which must implement the {@link org.eclipse.emf.edit.provider.IChangeNotifier} interface.
   */
  IChangeNotifier getDecoratedItemProvider();

  /**
   * This set the decorated item provider, which must implement the {@link org.eclipse.emf.edit.provider.IChangeNotifier} interface.
   */
  void setDecoratedItemProvider(IChangeNotifier decoratedItemProvider);
}
