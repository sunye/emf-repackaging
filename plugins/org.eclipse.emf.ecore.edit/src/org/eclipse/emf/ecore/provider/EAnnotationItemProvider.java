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
 * $Id: EAnnotationItemProvider.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

/**
 * This is the item provider adpater for a {@link org.eclipse.emf.ecore.EAnnotation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EAnnotationItemProvider
  extends EModelElementItemProvider
  implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAnnotationItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addSourcePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Source feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSourcePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getString("_UI_EAnnotation_source_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_EAnnotation_source_feature", "_UI_EAnnotation_type"),
         EcorePackage.eINSTANCE.getEAnnotation_Source(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
  }


  /**
   * This specifies how to implement {@link #getChildren} 
   * and {@link org.eclipse.emf.edit.command.AddCommand} and {@link org.eclipse.emf.edit.command.RemoveCommand} 
   * support in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Collection getChildrenReferences(Object object)
  {
    if (childrenReferences == null)
    {
      super.getChildrenReferences(object);
      childrenReferences.add(EcorePackage.eINSTANCE.getEAnnotation_Details());
      childrenReferences.add(EcorePackage.eINSTANCE.getEAnnotation_Contents());
    }
    return childrenReferences;
  }

  /**
   * This returns EAnnotation.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getImage(Object object)
  {
    return getResourceLocator().getImage("full/obj16/EAnnotation");
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getText(Object object)
  {
    EAnnotation eAnnotation = (EAnnotation)object;
    StringBuffer result = new StringBuffer();
    if (eAnnotation.getSource() != null)
    {
      int index = eAnnotation.getSource().lastIndexOf("/");
      if (index == -1)
      {
        result.append(eAnnotation.getSource());
      }
      else
      {
        result.append(eAnnotation.getSource().substring(index + 1));
      }
    }
    return result.toString();
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void notifyChanged(Notification notification)
  {
    switch (notification.getFeatureID(EAnnotation.class))
    {
      case EcorePackage.EANNOTATION__SOURCE:
      case EcorePackage.EANNOTATION__DETAILS:
      {
        fireNotifyChanged(notification);
        return;
      }
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
   * describing all of the children that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)
  {
    // super.collectNewChildDescriptors(newChildDescriptors, object);
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return EcoreEditPlugin.INSTANCE;
  }
}
