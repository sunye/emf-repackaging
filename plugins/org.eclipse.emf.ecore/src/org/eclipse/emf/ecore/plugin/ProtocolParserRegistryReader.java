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
 * $Id: ProtocolParserRegistryReader.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.ecore.plugin;


import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.ecore.resource.Resource;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#INSTANCE global} resource factory's
 * {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getProtocolToFactoryMap() protocol} map.
 * Clients are not expected to use this class directly.
 */
class ProtocolParserRegistryReader extends RegistryReader
{
  static final String TAG_PARSER = "parser";
  static final String ATT_PROTOCOLNAME = "protocolName";
  static final String ATT_CLASS = "class";

  public ProtocolParserRegistryReader()
  {
    super
      (Platform.getPluginRegistry(),
       EcorePlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 
       EcorePlugin.PROTOCOL_PARSER_PPID);
  }

  protected boolean readElement(IConfigurationElement element)
  {
    if (element.getName().equals(TAG_PARSER))
    {
      String protocolName = element.getAttribute(ATT_PROTOCOLNAME);
      if (protocolName == null)
      {
        logMissingAttribute(element, ATT_PROTOCOLNAME);
      }
      else if (element.getAttribute(ATT_CLASS) == null)
      {
        logMissingAttribute(element, ATT_CLASS);
      }
      else
      {
        Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().put
          (protocolName, new ResourceFactoryDescriptor(element, ATT_CLASS));
        return true;
      }
    }

    return false;
  }
}
