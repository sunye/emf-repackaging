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
 * $Id: CreateChildAction.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.ui.action;


import java.util.Collection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A child creation action is implemented by creating a {@link
 * CreateChildCommand}.
 */
public class CreateChildAction extends StaticSelectionCommandAction
{
  /**
   * This describes the child to be created.
   */
  protected Object descriptor;

  /**
   * This constructs an instance of an action that creates a child
   * specified by <code>descriptor</code> for the single object in the
   * <code>selection</code>.
   */
  public CreateChildAction(IEditorPart editorPart, ISelection selection,
                           Object descriptor)
  {
    super(editorPart);
    this.descriptor = descriptor;
    configureAction(selection);
  }

  /**
   * This creates the command for {@link
   * StaticSelectionCommandAction#createActionCommand}.
   */
  protected Command createActionCommand(EditingDomain editingDomain,
                                        Collection collection)
  {
    if (collection.size() == 1)
    {
      Object owner = collection.iterator().next();
      return CreateChildCommand.create(editingDomain, owner,
                                       descriptor, collection);
    }
    return UnexecutableCommand.INSTANCE;
  }
}
