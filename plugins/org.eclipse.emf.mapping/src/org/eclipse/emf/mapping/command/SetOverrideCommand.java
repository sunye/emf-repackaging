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
 * $Id: SetOverrideCommand.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.mapping.command;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.mapping.MappedObjectState;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * This command overrides the SetCommand to additionally unmap the removed object and map the new object.
 */
public class SetOverrideCommand extends AbstractCommand
{
  /**
   * This keeps track of the mapping domain in which the command operates.
   */
  protected MappingDomain mappingDomain;

  /**
   * This keeps track of the SetCommand we're overriding
   */
  protected SetCommand setCommand;

  /**
   * This keeps track of the set mapping command(s) used to implement this command.
   */
  protected Command mapCommand;

  /**
   * This creates a command instance that removes, unmaps the removed object and then adds and maps the new object.
   */
  public SetOverrideCommand(MappingDomain domain, SetCommand setCommand)
  {
    super(setCommand.doGetLabel(), setCommand.doGetDescription());

    this.mappingDomain = domain;
    this.setCommand = setCommand;
  }

  protected boolean prepare()
  {
    return setCommand.doCanExecute();
  }

  public void execute()
  {
    setCommand.doExecute();

    MappingRoot mappingRoot = mappingDomain.getMappingRoot();
    CompoundCommand subcommands = new CompoundCommand();

    if (setCommand.getOldValue() != null)
    {
      Object oldValue = setCommand.getOldValue();
      for (Iterator objects = mappingDomain.treeIterator(oldValue); objects.hasNext(); )
      {
        Object object = objects.next();
        for (Iterator mappings = mappingRoot.getMappings(object).iterator(); mappings.hasNext(); )
        {
          Mapping mapping = (Mapping)mappings.next();
          Collection outputs = mapping.getOutputs();
          if (outputs.size() == 1 && outputs.iterator().next() == object)
          {
            subcommands.append(RemoveMappingCommand.create(mappingDomain, mapping));
          }
          else
          {
            subcommands.append(
              //(RemoveCommand.create(mappingDomain, mapping, mapping.ePackageMapping().getMapping_Outputs(), object)));
              (RemoveCommand.create(mappingDomain, mapping, MappingPackage.eINSTANCE.getMapping_Outputs(), object)));
          }
        }
      }
    }

    if (setCommand.getValue() != null)
    {
      Object value = setCommand.getValue();
      for (Iterator objects = mappingDomain.treeIterator(value); objects.hasNext(); )
      {
        Object object = objects.next();
        MappedObjectState mappedObjectState = mappingRoot.getMappedObjectState(object);
        Object originatingInput = mappedObjectState.getOriginatingInput();
 
        // This is tricky and is done for each object here rather than once for the owner of the addCommnd.
        // We want to make sure the object is really part of the conceptual tree induced by the domain.
        //
        if (originatingInput == null)
        {
          mappedObjectState.setOutput();
        }
        else if (mappingRoot.isAttachedObject(object))
        {
          subcommands.append(CreateMappingCommand.create(mappingDomain,  originatingInput, object));
        }
      }
    }

    mapCommand = subcommands.unwrap();
    if (mapCommand.canExecute())
    {
      mapCommand.execute();
    }
    else
    {
      mapCommand.dispose();
      mapCommand = null;
    }
  }

  public void undo()
  {
    if (mapCommand != null)
    {
      mapCommand.undo();
    }
    setCommand.doUndo();          
  }

  public void redo()
  {
    setCommand.doRedo();
    if (mapCommand != null)
    {
      mapCommand.redo();
    }
  }

  public void dispose()
  {
    if (mapCommand != null) 
    {
      mapCommand.dispose();
    }
    setCommand.doDispose();
  }

  public Collection getResult()
  {
    return setCommand.doGetResult();
  }

  public Collection getAffectedObjects()
  {
    return setCommand.doGetAffectedObjects();
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (mappingDomain: " + mappingDomain + ")");
    result.append(" (mapCommand: " + mapCommand + ")");

    return result.toString();
  }
}
