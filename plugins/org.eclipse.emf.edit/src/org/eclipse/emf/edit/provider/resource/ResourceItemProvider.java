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
 * $Id: ResourceItemProvider.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.provider.resource;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.ecore.resource.Resource} object.
 * @generated
 */
public class ResourceItemProvider
  extends ItemProviderAdapter
  implements 
    IEditingDomainItemProvider,
    IStructuredItemContentProvider, 
    ITreeItemContentProvider, 
    IItemLabelProvider, 
    IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * @generated
   */
  public ResourceItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * @generated
   */
  public List getPropertyDescriptors(Object object)
  {
    return super.getPropertyDescriptors(object);
  }

  public Collection getChildren(Object object)
  {
    Resource resource= (Resource)object;
    return resource.getContents();
  }

  /**
   * This specifies how to implement {@link #getChildren} and 
   * {@link org.eclipse.emf.edit.command.AddCommand} and 
   * {@link org.eclipse.emf.edit.command.RemoveCommand} support in 
   * {@link #createCommand}.
   * @generated
   */
  public Collection getChildrenReferences(Object object)
  {
    if (childrenReferences == null)
    {
      super.getChildrenReferences(object);
      // Resource resource = (Resource)object;
      // childrenReferences.add(ResourcePackage.eINSTANCE.getResource_Contents());
    }
    return childrenReferences;
  }

  /**
   * This returns the parent of the Resource.
   * @generated
   */
  public Object getParent(Object object)
  {
    return ((Resource)object).getResourceSet();
  }

  /**
   * This returns Resource.gif.
   * @generated
   */
  public Object getImage(Object object)
  {
    Resource resource = (Resource)object;
    // return PlatformUI.getWorkbench().getEditorRegistry().getImageDescriptor(IWorkbenchPlresource.getURI().toString());
    try
    {
      return new URL(getResourceLocator().getImage("full/obj16/Resource").toString() + "#" + resource.getURI().fileExtension());
    }
    catch (MalformedURLException exception)
    {
      return getResourceLocator().getImage("full/obj16/Resource");
    }
  }

  /**
   * This returns the label text for the adapted class.
   * @generated
   */
  public String getText(Object object)
  {
    Resource resource = (Resource)object;
    return resource.getURI() == null ? "" : resource.getURI().toString();
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   * @generated
   */
  public void notifyChanged(Notification notification) 
  {
    Resource resource = (Resource)notification.getNotifier();
    switch (notification.getFeatureID(Resource.class))
    {
      case Resource.RESOURCE__URI:
      // case Resource.RESOURCE__IS_MODIFIED:
      // case Resource.RESOURCE__IS_LOADED:
      // case Resource.RESOURCE__IS_TRACKING_MODIFICATION:
      case Resource.RESOURCE__CONTENTS:
      // case Resource.RESOURCE__RESOURCE_SET:
      {
        fireNotifyChanged(notification);
        return;
      }
    }
    super.notifyChanged(notification);
  }

  public Collection getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
  {
    return Collections.EMPTY_LIST;
  }

  /**
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s 
   * describing all of the children that can be created under this object.
   * @generated
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);
/*
    Resource resource = (Resource)object;

    newChildDescriptors.add
      (createChildParameter
        (ResourcePackage.eINSTANCE.getResource_Contents(),
         ResourcePackage.eINSTANCE.getResourceFactory().createEObject()));
*/
  }

  /**
   * Return the resource locator for this item provider's resources.
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return EMFEditPlugin.INSTANCE;
  }
}
