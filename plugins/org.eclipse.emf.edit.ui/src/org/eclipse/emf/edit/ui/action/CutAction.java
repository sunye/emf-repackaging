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
 * $Id: CutAction.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.ui.action;


import java.util.Collection;

import org.eclipse.ui.IEditorPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.CutToClipboardCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;


/**
 * A cut action is implemented by creating a {@link CutToClipboardCommand}.
 */
public class CutAction extends CommandActionHandler
{
  public CutAction(EditingDomain domain)
  {
    super(domain, EMFEditUIPlugin.INSTANCE.getString("_UI_Cut_menu_item"));
  }

  public CutAction()
  {
    super(null, EMFEditUIPlugin.INSTANCE.getString("_UI_Cut_menu_item"));
  }

  public Command createCommand(Collection selection)
  {
    return CutToClipboardCommand.create(domain, selection);
  }

  public void setActiveEditor(IEditorPart editorPart)
  {
    if (editorPart instanceof IEditingDomainProvider)
    {
      domain = ((IEditingDomainProvider)editorPart).getEditingDomain();
    }
  }
}
