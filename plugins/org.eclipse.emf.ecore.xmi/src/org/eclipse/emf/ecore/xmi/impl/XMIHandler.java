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
 * $Id: XMIHandler.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.Map;

import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;


/**
 * This class is a SAX handler for creating MOF2 objects from an XMI 2.0 file.
 */
public abstract class XMIHandler extends XMLHandler
{
  protected static final String XMI_ELEMENT_TYPE = "xmi";
  protected static final String XMI_UUID = "uuid";

  protected final static String XMI_TYPE_ATTRIB = XMIResource.XMI_NS + ":" + XMIResource.TYPE;
  protected final static String ID_ATTRIB = XMIResource.XMI_NS + ":" + XMIResource.XMI_ID;
  protected final static String VERSION_ATTRIB = XMIResource.XMI_NS + ":" + XMIResource.VERSION_NAME;
  protected final static String UUID_ATTRIB = XMIResource.XMI_NS + ":" + XMI_UUID;
  protected final static String XMI_ELEMENT_NAME = XMIResource.XMI_NS + ":" + XMIResource.XMI_TAG_NAME;

  /**
   * Constructor.
   */
  public XMIHandler(XMLResource xmiResource, XMLHelper helper, Map options)
  {
    super(xmiResource, helper, options);

    notFeatures.add(VERSION_ATTRIB);
    notFeatures.add(XMI_TYPE_ATTRIB);
    notFeatures.add(UUID_ATTRIB);
  }

  protected void processElement(String name, String prefix, String localName)
  {
    if (name.equals(XMI_ELEMENT_NAME))
    {
      types.push(XMI_ELEMENT_TYPE);
    }
    else
    {
      super.processElement(name, prefix, localName);
    }
  }

  protected boolean isTextFeatureValue(Object type)
  {
    return super.isTextFeatureValue(type) && type != XMI_ELEMENT_TYPE;
  }
} // XMIHandler
