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
 * $Id: EditingDomainActionBarContributor.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.ui.action;


import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;

import org.eclipse.emf.edit.domain.IEditingDomainProvider;


/**
 * This is a contributor for an editor, multipage or otherwise,
 * that implements {@link IEditingDomainProvider}.
 * It automatically hooks up the Undo, Redo, Cut, Copy, Paste, and Delete actions on the Edit menu
 * to the corresponding commands supported by the {@link org.eclipse.emf.edit.domain.EditingDomain}.
 * The editor site'selection provider is used to keep the Cut, Copy, Paste, and Delete actions up-to-date.
 * The actions are also refreshed everytime the editor fires to its {@link IPropertyListener}s.
 * <p>
 * Another very useful feature of this contributor is that it can be used as follows:
 * <pre>
 *   ((IMenuListener)((IEditorSite)getSite()).getActionBarContributor()).menuAboutToShow(menuManager);
 * </pre>
 * to contribute the Edit menu actions to a popup menu.
 */
public class EditingDomainActionBarContributor 
  extends 
    MultiPageEditorActionBarContributor 
  implements 
    IMenuListener,
    IPropertyListener
{
  /**
   * This keeps track of the current editor part.
   */
  protected IEditorPart activeEditor;

  /**
   * This is the action used to implement delete.
   */
  protected DeleteAction deleteAction;

  /**
   * This is the action used to implement cut.
   */
  protected CutAction cutAction;

  /**
   * This is the action used to implement copy.
   */
  protected CopyAction copyAction;

  /**
   * This is the action used to implement paste.
   */
  protected PasteAction pasteAction;

  /**
   * This is the action used to implement undo.
   */
  protected UndoAction undoAction;

  /**
   * This is the action used to implement redo.
   */
  protected RedoAction redoAction;


  /**
   * This creates an instance the contributor.
   */
  public EditingDomainActionBarContributor()
  {
    super();
  }

  public void init(IActionBars actionBars)
  {
    super.init(actionBars);

    deleteAction = new DeleteAction();
    actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteAction);

    cutAction = new CutAction();
    actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);

    copyAction = new CopyAction();
    actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);

    pasteAction = new PasteAction();
    actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);

    undoAction = new UndoAction();
    actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);

    redoAction = new RedoAction();
    actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);
  }

  public void contributeToMenu(IMenuManager menuManager) 
  {
    super.contributeToMenu(menuManager);
  }

  public void contributeToStatusLine(IStatusLineManager statusLineManager) 
  {
    super.contributeToStatusLine(statusLineManager);
  }

  public void contributeToToolBar(IToolBarManager toolBarManager)
  {
    super.contributeToToolBar(toolBarManager);
  }

  public void shareGlobalActions(IPage page, IActionBars actionBars)
  {
    if (!(page instanceof IPropertySheetPage))
    {
      actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteAction);
      actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);
      actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);
      actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);
    }
    actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
    actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);
  }

  /**
   * @deprecated
   */
  public void setActiveView(IViewPart part)
  {
    IActionBars actionBars = part.getViewSite().getActionBars();
    if (!(part instanceof PropertySheet))
    {
      actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteAction);
      actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cutAction);
      actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copyAction);
      actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteAction);
    }
    actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
    actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);

    actionBars.updateActionBars();
  }

  public void setActiveEditor(IEditorPart part) 
  {
    super.setActiveEditor(part);

    if (part != activeEditor)
    {
      if (activeEditor != null)
      {
        deactivate();
      }

      if (part instanceof IEditingDomainProvider)
      {
        activeEditor = part;
        activate();

      }
    }
  }

  public void setActivePage(IEditorPart part) 
  {
  }

  public void deactivate()
  {
    activeEditor.removePropertyListener(this);

    deleteAction.setActiveEditor(null);
    cutAction.setActiveEditor(null);
    copyAction.setActiveEditor(null);
    pasteAction.setActiveEditor(null);
    undoAction.setActiveEditor(null);
    redoAction.setActiveEditor(null);

    ISelectionProvider selectionProvider = 
      activeEditor instanceof ISelectionProvider ?
        (ISelectionProvider)activeEditor :
        activeEditor.getEditorSite().getSelectionProvider();
    selectionProvider.removeSelectionChangedListener(deleteAction);
    selectionProvider.removeSelectionChangedListener(cutAction);
    selectionProvider.removeSelectionChangedListener(copyAction);
    selectionProvider.removeSelectionChangedListener(pasteAction);
  }

  public void activate()
  {
    activeEditor.addPropertyListener(this);

    deleteAction.setActiveEditor(activeEditor);
    cutAction.setActiveEditor(activeEditor);
    copyAction.setActiveEditor(activeEditor);
    pasteAction.setActiveEditor(activeEditor);
    undoAction.setActiveEditor(activeEditor);
    redoAction.setActiveEditor(activeEditor);

    ISelectionProvider selectionProvider = 
      activeEditor instanceof ISelectionProvider ?
        (ISelectionProvider)activeEditor :
        activeEditor.getEditorSite().getSelectionProvider();
    selectionProvider.addSelectionChangedListener(deleteAction);
    selectionProvider.addSelectionChangedListener(cutAction);
    selectionProvider.addSelectionChangedListener(copyAction);
    selectionProvider.addSelectionChangedListener(pasteAction);

    update();
  }

  public void update()
  {
    ISelectionProvider selectionProvider = 
      activeEditor instanceof ISelectionProvider ?
        (ISelectionProvider)activeEditor :
        activeEditor.getEditorSite().getSelectionProvider();
    ISelection selection = selectionProvider.getSelection();
    IStructuredSelection structuredSelection =
      selection instanceof IStructuredSelection ?  (IStructuredSelection)selection : StructuredSelection.EMPTY;

    deleteAction.updateSelection(structuredSelection);
    cutAction.updateSelection(structuredSelection);
    copyAction.updateSelection(structuredSelection);
    pasteAction.updateSelection(structuredSelection);
    undoAction.update();
    redoAction.update();
  }

  /**
   * This implements {@link org.eclipse.jface.action.IMenuListener} to help fill the context menus with contributions from the Edit menu.
   */
  public void menuAboutToShow(IMenuManager menuManager)
  {
    // Add our standard marker.
    //
    menuManager.add(new Separator("additions"));
    menuManager.add(new Separator("edit"));

    // Add the edit menu actions.
    //
    menuManager.add(new ActionContributionItem(undoAction));
    menuManager.add(new ActionContributionItem(redoAction));
    menuManager.add(new Separator());
    menuManager.add(new ActionContributionItem(cutAction));
    menuManager.add(new ActionContributionItem(copyAction));
    menuManager.add(new ActionContributionItem(pasteAction));
    menuManager.add(new Separator());
    menuManager.add(new ActionContributionItem(deleteAction));

    // Add our other standard marker.
    //
    menuManager.add(new Separator("additions-end"));
  }

  public void propertyChanged(Object source, int id)
  {
    update();
  }
}
