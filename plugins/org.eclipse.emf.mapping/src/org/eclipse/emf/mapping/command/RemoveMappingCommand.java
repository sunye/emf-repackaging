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
 * $Id: RemoveMappingCommand.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.mapping.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * The create mapping command creates a new mapping in a {@link MappingDomain} 
 * from a set of the domain's input and output objects.
 */
public class RemoveMappingCommand extends AbstractCommand 
{
  /**
   * This creates a command that removes the mapping from the mapping root.
   */
  public static Command create(MappingDomain domain, Mapping mapping)
  {
    return create(domain, Collections.singleton(mapping));
  }

  /**
   * This creates a command that removes the mappings in the collection from the mapping root.
   */
  public static Command create(MappingDomain domain, Collection collection)
  {
    return 
      domain.createCommand
        (RemoveMappingCommand.class, 
         new CommandParameter(domain.getMappingRoot(), null, collection));
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = MappingPlugin.getPlugin().getString("_UI_RemoveMappingCommand_label");

  /**
   * This cachaes the description.
   */
  protected static final String DESCRIPTION = MappingPlugin.getPlugin().getString("_UI_RemoveMappingCommand_description");

  /**
   * This keeps track of the mapping domain in which the command operates.
   */
  protected MappingDomain domain;

  /**
   * This keeps track of the input and output objects that are to be mapped.
   */
  protected Collection collection;

  /**
   * This keeps track of all the subcommand(s) use to implement this command.
   */
  Command subcommand;


  /**
   * This creates a command instance that removes the mappings in the collection from the mapping root.
   */
  public RemoveMappingCommand(MappingDomain domain, Collection collection)
  {
    super(LABEL, DESCRIPTION);

    this.domain = domain;
    this.collection = collection;
  }

  protected boolean prepare() 
  {
    boolean result = true;

    if (domain == null || collection == null || collection.isEmpty())
    {
      result = false;
    }
    else
    {
      for (Iterator objects = collection.iterator(); objects.hasNext(); )
      {
        Object object = objects.next();
        if (!(object instanceof Mapping))
        {
          result = false;
          break;
        }
        else
        {
          Mapping mapping = (Mapping)object;
          result = domain.getMappingRoot().canRemoveMapping(mapping);
        }
      }
    }

    return result;
  }

  public void execute()
  {
    // This will deal with all the subcommands to modifying the root mapping tree.
    //
    CompoundCommand subcommands = new CompoundCommand();

    // For each mapping being removed...
    //
    for (Iterator mappings = collection.iterator(); mappings.hasNext(); )
    {
      Mapping mapping = (Mapping)mappings.next();
      Mapping parentMapping = mapping.getNestedIn();

      // Make sure the back pointers to this mapping from the mapped objects is set.
      //
      domain.getMappingRoot().deregister(mapping);

      // Create a command to do parentMapping.getNested().remove(mapping).
      //
      //subcommands.appendAndExecute(new RemoveCommand(domain, parentMapping, parentMapping.ePackageMapping().getMapping_Nested(), mapping));
      subcommands.appendAndExecute(new RemoveCommand(domain, parentMapping, MappingPackage.eINSTANCE.getMapping_Nested(), mapping));
   
      Collection nestedMappings = new ArrayList(mapping.getNested());
      if (!nestedMappings.isEmpty())
      {
        //subcommands.appendAndExecute(new RemoveCommand(domain, mapping, mapping.ePackageMapping().getMapping_Nested(), nestedMappings));
        //subcommands.appendAndExecute(new AddCommand(domain, parentMapping, parentMapping.ePackageMapping().getMapping_Nested(), nestedMappings));
        subcommands.appendAndExecute(new RemoveCommand(domain, mapping, MappingPackage.eINSTANCE.getMapping_Nested(), nestedMappings));
        subcommands.appendAndExecute(new AddCommand(domain, parentMapping, MappingPackage.eINSTANCE.getMapping_Nested(), nestedMappings));
      }
    }

    subcommand = subcommands.unwrap();
  }

  public void undo() 
  {
    for (Iterator objects = collection.iterator(); objects.hasNext(); )
    {
      Mapping mapping = (Mapping)objects.next();
      domain.getMappingRoot().register(mapping);
    }

    subcommand.undo();
  }

  public void redo()
  {
    for (Iterator objects = collection.iterator(); objects.hasNext(); )
    {
      Mapping mapping = (Mapping)objects.next();
      domain.getMappingRoot().deregister(mapping);
    }

    subcommand.redo();
  }

  public Collection getResult() 
  {
    return collection;
  }

  public void dispose()
  {
    if (subcommand != null)
    {
      subcommand.dispose();
    }
    super.dispose();
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (domain: " + domain + ")");
    result.append(" (collection: " + collection + ")");
    result.append(" (subcommand: " + subcommand + ")");

    return result.toString();
  }
}
