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
 * $Id: GenClassImpl.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenProviderKind;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#getProvider <em>Provider</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#isImage <em>Image</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#getEcoreClass <em>Ecore Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#getGenFeatures <em>Gen Features</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#getGenOperations <em>Gen Operations</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl#getLabelFeature <em>Label Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenClassImpl extends GenClassifierImpl implements GenClass
{
  /**
   * The default value of the '{@link #getProvider() <em>Provider</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvider()
   * @generated
   * @ordered
   */
  protected static final GenProviderKind PROVIDER_EDEFAULT = GenProviderKind.SINGLETON_LITERAL;

  /**
   * The cached value of the '{@link #getProvider() <em>Provider</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProvider()
   * @generated
   * @ordered
   */
  protected GenProviderKind provider = PROVIDER_EDEFAULT;

  /**
   * The default value of the '{@link #isImage() <em>Image</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isImage()
   * @generated
   * @ordered
   */
  protected static final boolean IMAGE_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isImage() <em>Image</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isImage()
   * @generated
   * @ordered
   */
  protected boolean image = IMAGE_EDEFAULT;

  /**
   * The cached value of the '{@link #getEcoreClass() <em>Ecore Class</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreClass()
   * @generated
   * @ordered
   */
  protected EClass ecoreClass = null;

  /**
   * The cached value of the '{@link #getGenFeatures() <em>Gen Features</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenFeatures()
   * @generated
   * @ordered
   */
  protected EList genFeatures = null;

  /**
   * The cached value of the '{@link #getGenOperations() <em>Gen Operations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenOperations()
   * @generated
   * @ordered
   */
  protected EList genOperations = null;

  /**
   * The cached value of the '{@link #getLabelFeature() <em>Label Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabelFeature()
   * @generated
   * @ordered
   */
  protected GenFeature labelFeature = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenClassImpl() 
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return GenModelPackage.eINSTANCE.getGenClass();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenProviderKind getProvider()
  {
    return provider;
  }

  public boolean isProviderSingleton()
  {
    return provider == GenProviderKind.SINGLETON_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProvider(GenProviderKind newProvider)
  {
    GenProviderKind oldProvider = provider;
    provider = newProvider == null ? PROVIDER_EDEFAULT : newProvider;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_CLASS__PROVIDER, oldProvider, provider));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isImage()
  {
    return image;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImage(boolean newImage)
  {
    boolean oldImage = image;
    image = newImage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_CLASS__IMAGE, oldImage, image));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEcoreClass()
  {
    if (ecoreClass != null && ecoreClass.eIsProxy())
    {
      EClass oldEcoreClass = ecoreClass;
      ecoreClass = (EClass)eResolveProxy((InternalEObject)ecoreClass);
      if (ecoreClass != oldEcoreClass)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_CLASS__ECORE_CLASS, oldEcoreClass, ecoreClass));
      }
    }
    return ecoreClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass basicGetEcoreClass()
  {
    return ecoreClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreClass(EClass newEcoreClass)
  {
    EClass oldEcoreClass = ecoreClass;
    ecoreClass = newEcoreClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_CLASS__ECORE_CLASS, oldEcoreClass, ecoreClass));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getGenFeatures()
  {
    if (genFeatures == null)
    {
      genFeatures = new EObjectContainmentWithInverseEList(GenFeature.class, this, GenModelPackage.GEN_CLASS__GEN_FEATURES, GenModelPackage.GEN_FEATURE__GEN_CLASS);
    }
    return genFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getGenOperations()
  {
    if (genOperations == null)
    {
      genOperations = new EObjectContainmentWithInverseEList(GenOperation.class, this, GenModelPackage.GEN_CLASS__GEN_OPERATIONS, GenModelPackage.GEN_OPERATION__GEN_CLASS);
    }
    return genOperations;
  }

  public EClassifier getEcoreClassifier()
  {
    return getEcoreClass();
  }

  public String getImportedMetaType()
  {
    return getGenModel().getImportedName("org.eclipse.emf.ecore.EClass");
  }

  public String getInterfaceName()
  {
    return getName();
  }

  public String getQualifiedInterfaceName()
  {
    return getInternalQualifiedInterfaceName().replace('$', '.');
  }

  protected String getInternalQualifiedInterfaceName()
  {
    return isExternalInterface() ?
      getEcoreClass().getInstanceClassName() :
      getGenPackage().getInterfacePackageName() + "." + getInterfaceName();
  }
  
  public String getImportedInstanceClassName()
  {
    return getImportedInterfaceName();
  }

  public String getImportedInterfaceName()
  {
    return getGenModel().getImportedName(getInternalQualifiedInterfaceName());
  }

  public String getClassName()
  {
    return getImplClassName(getInterfaceName());
  }

  public String getQualifiedClassName()
  {
    return getGenPackage().getClassPackageName() + "." + getClassName();
  }

  public String getImportedClassName()
  {
    return getGenModel().getImportedName(getQualifiedClassName());
  }

  public String getUncapName()
  {
    return uncapPrefixedName(getName());
  }

  public String getSafeUncapName()
  {
    return safeName(getUncapName());
  }

  public List getBaseGenClasses()
  {
    return collectGenClasses(getEcoreClass().getESuperTypes(), null);
  }

  public List getAllBaseGenClasses()
  {
    return collectGenClasses(getEcoreClass().getEAllSuperTypes(), null);
  }

  public List getSwitchGenClasses()
  {
    // for Ecore or something that explicitly extends it, we need to exclude
    // EObject, which is already handled by the default case
    List result = 
      collectGenClasses
        (getEcoreClass().getESuperTypes(), 
         new GenClassFilter()
         {
           public boolean accept(GenClass genClass) 
           {
             return !genClass.isEObject();
           }
         });
    Set resultSet = new HashSet(result);

    for (int i = 0; i < result.size(); i++)
    {
      GenClass base = (GenClass)result.get(i);
      for (Iterator iter = base.getBaseGenClasses().iterator(); iter.hasNext(); )
      {
        GenClass baseOfBase = (GenClass)iter.next();
        if (!baseOfBase.isEObject() && resultSet.add(baseOfBase))
        {
          result.add(baseOfBase);
        }
      }
    }
    return result;
  }

  public GenClass getBaseGenClass()
  {
    List s = getEcoreClass().getESuperTypes();
    return s.isEmpty() ? null : findGenClass((EClass)s.iterator().next());
  }

  public GenClass getClassExtendsGenClass()
  {
    GenClass base = getBaseGenClass();
    while (base != this)
    {
      if (base == null || !base.isInterface()) return base; 
      base = base.getBaseGenClass();
    }
    throw new RuntimeException("inheritance loop at " + getName());
  }

  public String getClassExtends()
  {
    GenClass extendsClass = getClassExtendsGenClass();
    if (extendsClass != null)
    {   
      return " extends " + extendsClass.getImportedClassName();
    }
    else if (!isEObject())
    {
      String rootExtendsClass = getGenModel().getRootExtendsClass();
      if (!isBlank(rootExtendsClass))
      {
        return " extends " + getGenModel().getImportedName(rootExtendsClass);
      }
    }
    return "";
  }

  public boolean needsRootImplementsInterfaceOperations()
  {
    if (!isMapEntry())
    {
      String rootImplementsInterface = getGenModel().getRootImplementsInterface();
      if (!isBlank(rootImplementsInterface))
      {
        GenClass extendsClass = getClassExtendsGenClass();

        // We assume that the rootExtendsClass already implements it.
        //
        if (extendsClass != null && !rootImplementsInterface.equals(extendsClass.getGenModel().getRootImplementsInterface()))
        {
          return true;
        }
      }
    }
    return false;
  }

  public String getClassImplements()
  {
    if (isMapEntry())
    {
      return " implements " + getGenModel().getImportedName("org.eclipse.emf.common.util.BasicEMap$Entry");
    }
    else
    {
      String result = " implements " + getImportedInterfaceName();
      String rootImplementsInterface = getGenModel().getRootImplementsInterface();
      if (!isBlank(rootImplementsInterface))
      {
        GenClass extendsClass = getClassExtendsGenClass();

        // We assume that the rootExtendsClass already implements it.
        //
        if (extendsClass != null && !rootImplementsInterface.equals(extendsClass.getGenModel().getRootImplementsInterface()))
        {
          result += ", " + getGenModel().getImportedName(rootImplementsInterface);
        }
      }

      return result;
    }
  }

  public String getInterfaceExtends()
  {
    if (getBaseGenClasses().isEmpty())
    {
      if (getGenPackage().isEcorePackage()) 
      {
        return "";
      }
      else 
      {
        String rootExtendsInterface = getGenModel().getRootExtendsInterface();
        if (!isBlank(rootExtendsInterface))
        {
          return " extends " + getGenModel().getImportedName(rootExtendsInterface);
        }
        else
        {
          return "";
        }
      }
    }

    boolean needsEObject = true;
    for (Iterator iter = getAllBaseGenClasses().iterator(); iter.hasNext(); )
    {
      GenClass genClass = (GenClass)iter.next();
      if (genClass.getEcoreClass().getInstanceClassName() == null)
      {
        needsEObject = false;
        break;
      }
    }

    StringBuffer result = new StringBuffer(" extends ");
    if (needsEObject)
    {
      String rootExtendsInterface = getGenModel().getRootExtendsInterface();
      if (!isBlank(rootExtendsInterface))
      {
        result.append(getGenModel().getImportedName(rootExtendsInterface));
        result.append(", ");
      }
    }

    for (Iterator iter = getBaseGenClasses().iterator(); iter.hasNext(); )
    {
      result.append(((GenClass)iter.next()).getImportedInterfaceName());
      if (iter.hasNext()) result.append(", ");
    } 
    return result.toString();
  }

  public List getAllGenFeatures()
  {
    return collectGenFeatures(getAllBaseGenClasses(), getGenFeatures(), null);
  }

  public List getInheritedGenFeatures()
  {
    return collectGenFeatures(getAllBaseGenClasses(), null, null);
  }

  public List getAllGenOperations()
  {
    return collectGenOperations(getAllBaseGenClasses(), getGenOperations(), null);
  }

  public String getFeatureID(GenFeature genFeature)
  {
    return getClassifierID() + "__" + format(genFeature.getName(), '_', null, false).toUpperCase();
  }

  public String getQualifiedFeatureID(GenFeature genFeature)
  {
    return getGenPackage().getImportedPackageInterfaceName() + "." + getFeatureID(genFeature);
  }

  public String getFeatureValue(GenFeature genFeature)
  {
    List allFeatures = getAllGenFeatures();
    int i = allFeatures.indexOf(genFeature);
    GenClass base = getBaseGenClass();

    if (base == null)
    {
      return Integer.toString(i);
    }

    int baseCount = base.getFeatureCount();    
    if (i < baseCount)
    {
      return getGenPackage() == base.getGenPackage() ?
        base.getFeatureID(genFeature) : base.getQualifiedFeatureID(genFeature);
    }

    String baseCountID = getGenPackage() == base.getGenPackage() ?
      base.getFeatureCountID() : base.getQualifiedFeatureCountID();
    return baseCountID + " + " + Integer.toString(i - baseCount);
  }

  public String getLocalFeatureIndex(GenFeature genFeature)
  {
    return Integer.toString(getEcoreClass().getEStructuralFeatures().indexOf(genFeature.getEcoreFeature()));
  }

  public String getFeatureCountID()
  {
    return getClassifierID() + "_FEATURE_COUNT";
  }

  public String getQualifiedFeatureCountID()
  {
    return getGenPackage().getImportedPackageInterfaceName() + "." + getFeatureCountID();
  }

  public String getFeatureCountValue()
  {
    GenClass base = getBaseGenClass();
    if (base == null)
    {
      return Integer.toString(getFeatureCount());
    }

    String baseCountID = getGenPackage() == base.getGenPackage() ?
      base.getFeatureCountID() : base.getQualifiedFeatureCountID();
    return baseCountID + " + " + Integer.toString(getFeatureCount() - base.getFeatureCount());
  }

  public int getFeatureCount()
  {
    return getAllGenFeatures().size();
  }

  public boolean isEObject()
  {
    return getName().equals("EObject") && getGenPackage().isEcorePackage();
  }

  public boolean isEObjectExtension()
  {
    if (isMapEntry())
    {
      return false;
    }
    else
    {
      for (Iterator iter = getAllBaseGenClasses().iterator(); iter.hasNext(); )
      {
        GenClass genClass = (GenClass)iter.next();
        if (genClass.isEObjectExtension())
        {
          return true;
        }
      }
  
      return getGenPackage().isEcorePackage() || "org.eclipse.emf.ecore.EObject".equals(getGenModel().getRootExtendsInterface());
    }
  }

  public boolean isAbstract()
  {
    // An interface should be abstract, but this makes sure of that fact.
    //
    return getEcoreClass().isAbstract() || getEcoreClass().isInterface();
  }

  public String getAbstractFlag()
  {
    String result = !isAbstract() ? "!" : "";
    return result + "IS_ABSTRACT";
  }

  public boolean isInterface()
  {
    return getEcoreClass().isInterface();
  }

  public String getInterfaceFlag()
  {
    String result = !getEcoreClass().isInterface() ? "!" : "";
    return result + "IS_INTERFACE";
  }

  public boolean isExternalInterface()
  {
    return getEcoreClass().getInstanceClassName() != null;
  }

  public boolean isMapEntry()
  {
    return 
      isJavaUtilMapEntry(getEcoreClass().getInstanceClassName()) &&
      getEcoreClass().getEStructuralFeature("key") != null &&
      getEcoreClass().getEStructuralFeature("value") != null;
  }

  public GenFeature getMapEntryKeyFeature()
  {
    return findGenFeature(getEcoreClass().getEStructuralFeature("key"));
  }

  public GenFeature getMapEntryValueFeature()
  {
    return findGenFeature(getEcoreClass().getEStructuralFeature("value"));
  }

  public List getImplementedGenClasses()
  {
    List allBases = getAllBaseGenClasses();
    GenClass extendedBase = getClassExtendsGenClass();
    int i = extendedBase == null ? 0 : allBases.indexOf(extendedBase) + 1;
    List result = new ArrayList(allBases.subList(i, allBases.size()));
    result.add(this);
    return result;
  }

  public List getImplementedGenFeatures()
  {
    return collectGenFeatures(getImplementedGenClasses(), null, null);
  }

  public List getImplementedGenOperations()
  {
    final List allGenFeatures = getAllGenFeatures();
    List implementedGenClasses = new UniqueEList(getImplementedGenClasses());
    if (needsRootImplementsInterfaceOperations())
    {
      String rootImplementsInterface = getGenModel().getRootImplementsInterface();
      GenClass match = null;
      LOOP:
      for (Iterator i = getGenModel().getAllGenUsedAndStaticGenPackagesWithClassifiers().iterator(); i.hasNext(); )
      {
        GenPackage genPackage = (GenPackage)i.next();
        for (Iterator j = genPackage.getGenClasses().iterator(); j.hasNext(); )
        {
          GenClass genClass = (GenClass)j.next();
          if (genClass.getQualifiedInterfaceName().equals(rootImplementsInterface))
          {
            match = genClass;
            break LOOP;
          }
        }
      }
      if (match != null)
      {
        List allBaseClasses = new UniqueEList(match.getAllBaseGenClasses());
        for (Iterator i = allBaseClasses.iterator(); i.hasNext(); )
        {
          GenClass genClass = (GenClass)i.next();
          if (genClass.isEObject())
          {
            i.remove();
          }
        }
        allBaseClasses.add(match);
        implementedGenClasses.addAll(allBaseClasses);
      }
    }
    return
      collectGenOperations
        (implementedGenClasses,
         null, 
         new GenOperationFilter()
         {
           public boolean accept(GenOperation genOperation) 
           {
             if (genOperation.getName().startsWith("get") && genOperation.getGenParameters().isEmpty())
             {
               for (Iterator i = allGenFeatures.iterator(); i.hasNext(); )
               {
                 GenFeature genFeature = (GenFeature)i.next();
                 if (genFeature.getGetAccessor().equals(genOperation.getName()))
                 {
                   return false;
                 }
               }
             }
             else if (genOperation.getName().startsWith("set") && genOperation.getGenParameters().size() == 1)
             {
               GenParameter genParameter =  (GenParameter)genOperation.getGenParameters().get(0);
               for (Iterator i = allGenFeatures.iterator(); i.hasNext(); )
               {
                 GenFeature genFeature = (GenFeature)i.next();
                 if (genFeature.isChangeable() && 
                       !genFeature.isListType() &&
                       genOperation.getName().equals("set" + genFeature.getAccessorName()) &&
                       genParameter.getType().equals(genFeature.getType()))
                 {
                   return false;
                 }
               }
             }
             else if (genOperation.getName().startsWith("unset") && genOperation.getGenParameters().isEmpty())
             {
               for (Iterator i = allGenFeatures.iterator(); i.hasNext(); )
               {
                 GenFeature genFeature = (GenFeature)i.next();
                 if (genFeature.isChangeable() && 
                       genFeature.isUnsettable() &&
                       genOperation.getName().equals("unset" + genFeature.getAccessorName()))
                 {
                   return false;
                 }
               }
             }
             else if (genOperation.getName().startsWith("isSet") && genOperation.getGenParameters().isEmpty())
             {
               for (Iterator i = allGenFeatures.iterator(); i.hasNext(); )
               {
                 GenFeature genFeature = (GenFeature)i.next();
                 if (genFeature.isChangeable() && 
                       genFeature.isUnsettable() &&
                       genOperation.getName().equals("isSet" + genFeature.getAccessorName()))
                 {
                   return false;
                 }
               }
             }
             return !genOperation.getGenClass().isEObject();
           }
         });
  }

  public List getESetGenFeatures()
  {
    return 
      collectGenFeatures
        (getAllBaseGenClasses(), 
         getGenFeatures(), 
         new GenFeatureFilter() 
         {
           public boolean accept(GenFeature genFeature) 
           {
             return genFeature.isChangeable();
           }
         });
  }

  public List getEInverseAddGenFeatures()
  {
    return 
     collectGenFeatures
       (getAllBaseGenClasses(), 
        getGenFeatures(), 
        new GenFeatureFilter() 
        {
          public boolean accept(GenFeature genFeature) 
          {
            return genFeature.isBidirectional() && !genFeature.isVolatile();
          }
        });
  }

  public List getEInverseRemoveGenFeatures()
  {
    return 
      collectGenFeatures
        (getAllBaseGenClasses(), 
         getGenFeatures(), 
         new GenFeatureFilter() 
         {
           public boolean accept(GenFeature genFeature) 
           {
             return genFeature.isContains() ||
               (genFeature.isBidirectional() &&
                !genFeature.getReverse().isVolatile()) ||
               genFeature.isFeatureMapType();
           }
         });
  }

  public List getEBasicRemoveFromContainerGenFeatures()
  {
    return 
      collectGenFeatures
        (getAllBaseGenClasses(), 
         getGenFeatures(), 
         new GenFeatureFilter() 
         {
           public boolean accept(GenFeature genFeature) 
           {
             return genFeature.isContainer(); 
           }
         });
  }

  public List getToStringGenFeatures()
  {
    return 
      collectGenFeatures
        (getImplementedGenClasses(), 
         null, 
         new GenFeatureFilter()
         {
           public boolean accept(GenFeature genFeature)
           {
             return !genFeature.isVolatile() && !genFeature.isReferenceType();
           }
         });
  }

  public List getMixinGenClasses()
  {
    // simple cases: no mixins for no inheritance or for a single base class
    if (getEcoreClass().getESuperTypes().size() <= 1)
    {
      return Collections.EMPTY_LIST;
    }

    // otherwise, mixins are everything after base class, even if interface
    List allBases = getAllBaseGenClasses();
    int i = allBases.indexOf(getBaseGenClass()) + 1;
    return new ArrayList(allBases.subList(i, allBases.size()));
  }

  public List getMixinGenFeatures()
  {
      return collectGenFeatures(getMixinGenClasses(), null, null);  
  }

  public void initialize(EClass eClass)
  {
    if (eClass != getEcoreClass())
    {
      setEcoreClass(eClass);

      if (getLabelFeatureGen() != null && getLabelFeatureGen().eIsProxy())
      {
        setLabelFeature(null);
      }
    }

    int localFeatureIndex = 0;
    LOOP:
    for (Iterator iter = eClass.getEStructuralFeatures().iterator(); iter.hasNext(); )
    {
      EStructuralFeature eStructuralFeature = (EStructuralFeature)iter.next();
      if (eStructuralFeature instanceof EAttribute)
      {
        EAttribute attribute = (EAttribute)eStructuralFeature;

        for (Iterator j = getGenFeatures().iterator(); j.hasNext(); )
        {
          GenFeature genFeature = (GenFeature)j.next();
          if (genFeature.getEcoreFeature() == attribute)
          {
            genFeature.initialize(attribute);
            getGenFeatures().move(localFeatureIndex++, genFeature);
            continue LOOP;
          }
        }

        GenFeature genFeature = ePackageGenModel().getGenModelFactory().createGenFeature();
        genFeature.initialize(attribute);
        getGenFeatures().add(localFeatureIndex++, genFeature);
      }
      else
      {
        EReference reference = (EReference)eStructuralFeature;

        for (Iterator j = getGenFeatures().iterator(); j.hasNext(); )
        {
          GenFeature genFeature = (GenFeature)j.next();
          if (genFeature.getEcoreFeature() == reference)
          {
            genFeature.initialize(reference);
            getGenFeatures().move(localFeatureIndex++, genFeature);
            continue LOOP;
          }
        }

        GenFeature genFeature = ePackageGenModel().getGenModelFactory().createGenFeature();
        genFeature.initialize(reference);
        getGenFeatures().add(localFeatureIndex++, genFeature);
      }
    }
    
    OPERATION_LOOP:
    for (Iterator iter = eClass.getEOperations().iterator(); iter.hasNext(); )
    {
      EOperation operation = (EOperation)iter.next();

      for (Iterator j = getGenOperations().iterator(); j.hasNext(); )
      {
        GenOperation genOperation = (GenOperation)j.next();
        if (genOperation.getEcoreOperation() == operation)
        {
          genOperation.initialize(operation);
          continue OPERATION_LOOP;
        }
      }

      GenOperation genOperation = ePackageGenModel().getGenModelFactory().createGenOperation();
      genOperation.initialize(operation);
      getGenOperations().add(genOperation);
    }
  }

  protected boolean hasModelContribution()
  {
    return true;
  }

  public void generate(IProgressMonitor progressMonitor)
  {
    try
    {
      if (!canGenerate()) return;

      int fileCount = isInterface() ? 1 : 2;
      if (isExternalInterface()) fileCount--;

      progressMonitor.beginTask("", fileCount);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_Generating_message", new Object [] { getFormattedName() }));

      if (!isExternalInterface())
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaInterface_message", 
              new Object [] { getGenPackage().getInterfacePackageName() + "." + getInterfaceName() }));
        generate
          (new SubProgressMonitor(progressMonitor, 1), 
           Generator.EMF_MODEL_PROJECT_STYLE, 
           getGenModel().getEffectiveModelPluginVariables(), 
           getGenModel().getModelDirectory(), 
           getGenPackage().getInterfacePackageName(), 
           getInterfaceName(), getGenModel().getInterfaceEmitter());
      }

      if (!isInterface())
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingJavaClass_message", new Object [] { getGenPackage().getClassPackageName() + "." + getClassName() }));
        generate
          (new SubProgressMonitor(progressMonitor, 1), 
           Generator.EMF_MODEL_PROJECT_STYLE, 
           getGenModel().getEffectiveModelPluginVariables(), 
           getGenModel().getModelDirectory(), 
           getGenPackage().getClassPackageName(), 
           getClassName(), 
           getGenModel().getClassEmitter());
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }

  public String getModelInfo()
  {
    StringBuffer result = new StringBuffer();
    if (isMapEntry())
    {
      StringBuffer names = new StringBuffer();
      StringBuffer body = new StringBuffer();
      for (Iterator i = getGenFeatures().iterator(); i.hasNext(); )
      {
        GenFeature genFeature = (GenFeature)i.next();
        body.append(genFeature.getQualifiedModelInfo());
        body.append(' ');
        names.append(genFeature.getEcoreFeature().getName());
        names.append(' ');
      }

      String features = names.toString().trim();
      if (!features.equals("key value"))
      {
        appendModelSetting(result, "features", features);
      }
      result.append(body);
    }
    else if (isExternalInterface())
    {
      appendModelSetting(result, "instanceClass", getEcoreClass().getInstanceClassName());
    }
    else
    {
      if (isInterface())
      {
        result.append("interface=\"true\"");
      }
      if (isAbstract())
      {
        if (result.length() > 0)
        {
          result.append(' ');
        }
        result.append("abstract=\"true\"");
      }
    }
    return result.toString();
  }

  //
  // EMFEdit generation
  //

  public String getProviderClassName()
  {
    return getName() + "ItemProvider";
  }

  public String getQualifiedProviderClassName()
  {
    return getGenPackage().getProviderPackageName() + "." + getProviderClassName();
  }

  public String getImportedProviderClassName()
  {
    return getGenModel().getImportedName(getQualifiedProviderClassName());
  }

  public String getItemIconFileName()
  {
    return getGenModel().getEditIconsDirectory() + "/full/obj16/" + getName() + ".gif";
  }

  public String getCreateChildIconFileName(GenFeature feature, GenClass childClass)
  {
    GenClass parentClass = feature.getGenClass();
    return getGenModel().getEditIconsDirectory() + "/full/ctool16/" + 
      "Create" + parentClass.getName() + "_" + feature.getName() + "_" + childClass.getName() + ".gif";
  }

  protected GenClass getProviderExtendsGenClass()
  {
    GenClass baseClass = getClassExtendsGenClass();
    while (baseClass != null && baseClass.getProvider() == GenProviderKind.NONE_LITERAL)
    {
      baseClass = baseClass.getClassExtendsGenClass();
    }
    return baseClass;
  }

  public String getProviderBaseClassName()
  {
    GenClass baseClass = getProviderExtendsGenClass();
    return baseClass != null ?  baseClass.getImportedProviderClassName() : null;
  }

  protected List getProviderImplementedGenClasses()
  {
    List allBases = getAllBaseGenClasses();
    GenClass extendedBase = getProviderExtendsGenClass();
    int i = extendedBase == null ? 0 : allBases.indexOf(extendedBase) + 1;
    List result = new ArrayList(allBases.subList(i, allBases.size()));
    result.add(this);
    return result;
  }

  protected List getProviderImplementedGenFeatures()
  {
    return collectGenFeatures(getProviderImplementedGenClasses(), null, null);
  }

  public List/*of GenFeature*/ getLabelFeatureCandidates()
  {
    return 
      collectGenFeatures
        (getAllBaseGenClasses(), 
         getGenFeatures(), 
         new GenFeatureFilter() 
         {
           public boolean accept(GenFeature genFeature) 
           {
             return !genFeature.isReferenceType() && !genFeature.isListType() && !genFeature.isMapType();
           }
         });
  }

  public List/*of GenFeature*/ getPropertyFeatures()
  {
    return 
      collectGenFeatures
        (getProviderImplementedGenClasses(), 
         null, 
         new GenFeatureFilter() 
         {
           public boolean accept(GenFeature genFeature) 
           {
             //FB TBD filter out volatile and other inappropriate links?
             return genFeature.isProperty();
           }
         });
  }

  public List/*of GenFeature*/ getNotifyFeatures()
  {
    return 
     collectGenFeatures
       (getProviderImplementedGenClasses(), 
        null, 
        new GenFeatureFilter()
        {
          public boolean accept(GenFeature genFeature)
          {
            return genFeature.isNotify();
          }
        });
  }

  public List/*of GenFeature*/ getChildrenFeatures()
  {
    return 
      collectGenFeatures
       (getProviderImplementedGenClasses(), 
        null, 
        new GenFeatureFilter()
        {
          public boolean accept(GenFeature genFeature) 
          {
            return (genFeature.isFeatureMapType() || genFeature.isContains()) && genFeature.isChildren();
          }
        });
  }

  public List/*of GenFeature*/ getAllChildrenFeatures()
  {
    return 
      collectGenFeatures
        (getAllBaseGenClasses(), 
         getGenFeatures(), 
         new GenFeatureFilter()
         {
           public boolean accept(GenFeature genFeature) 
           {
             return (genFeature.isFeatureMapType() || genFeature.isContains()) && genFeature.isChildren();
           }
         });
  }

  public List getCrossPackageChildrenFeatures()
  {
    GenClass base = getProviderExtendsGenClass();

    // children features handled by a provider base class from outside of
    // this package, that has already been generated
    return base == null || base.getGenPackage() == getGenPackage() || getGenModel().getAllGenPackagesWithClassifiers().contains(base.getGenPackage()) ?
      Collections.EMPTY_LIST : base.getAllChildrenFeatures();
  }

  public List getSharedClassChildrenFeatures()
  {
    List childrenFeatures = getAllChildrenFeatures();
    
    // build mapping from classes to list of features that use them
    Map classToFeatureMap = new HashMap();
    List packages = getGenModel().getAllGenAndUsedGenPackagesWithClassifiers();
    for (Iterator iter = childrenFeatures.iterator(); iter.hasNext(); )
    {
      GenFeature genFeature = (GenFeature)iter.next();
      List genClasses = getTypeGenClasses(genFeature.getEcoreFeature().getEType(), null, packages, -1);

      for (Iterator cIter = genClasses.iterator(); cIter.hasNext(); )
      {
        GenClass genClass = (GenClass)cIter.next();
        List genFeatures = (List)classToFeatureMap.get(genClass);
        if (genFeatures == null)
        {
          genFeatures = new ArrayList(5);
          classToFeatureMap.put(genClass, genFeatures);
        }
        genFeatures.add(genFeature);
      }
    }

    // scan feature lists for those with multiple elements and return them
    List result = new UniqueEList(childrenFeatures.size());
    for (Iterator iter = classToFeatureMap.values().iterator(); iter.hasNext();)
    {
      List genFeatures = (List)iter.next();
      if (genFeatures.size() > 1) result.addAll(genFeatures);
    }
    return result;
  }

  public List getChildrenClasses(GenFeature genFeature)
  {
    return getTypeGenClasses(genFeature.getEcoreFeature().getEType(), getGenPackage(), getGenModel().getAllGenAndUsedGenPackagesWithClassifiers(), -1);
  }

  public List getCrossPackageChildrenClasses(GenFeature genFeature)
  {
    return getTypeGenClasses(genFeature.getEcoreFeature().getEType(), getGenPackage(), getGenModel().getAllGenPackagesWithClassifiers(), -1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenFeature getLabelFeatureGen()
  {
    if (labelFeature != null && labelFeature.eIsProxy())
    {
      GenFeature oldLabelFeature = labelFeature;
      labelFeature = (GenFeature)eResolveProxy((InternalEObject)labelFeature);
      if (labelFeature != oldLabelFeature)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_CLASS__LABEL_FEATURE, oldLabelFeature, labelFeature));
      }
    }
    return labelFeature;
  }

  public GenFeature getLabelFeature()
  {
    GenFeature labelFeature = getLabelFeatureGen();
    if (labelFeature != null)
      return labelFeature;
    
    //FB TBD can we come up with a better algorithm for choosing the default label feature?
    for (Iterator iter = getLabelFeatureCandidates().iterator(); iter.hasNext(); )
    {
      GenFeature feature = (GenFeature) iter.next();
      if (!feature.isListType())
      {
        String featureName = feature.getName();
        if (featureName != null)
        {
          if (featureName.equalsIgnoreCase("name")) 
          {
            labelFeature = feature;
          }
          else if (featureName.toLowerCase().endsWith("name")) 
          {
            if (labelFeature == null || !labelFeature.getName().toLowerCase().endsWith("name"))
              labelFeature = feature;
          }
          else if (labelFeature == null) 
          {
            labelFeature = feature;
          }
        }
      }
    }

    return labelFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenFeature basicGetLabelFeature()
  {
    return labelFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLabelFeature(GenFeature newLabelFeature)
  {
    GenFeature oldLabelFeature = labelFeature;
    labelFeature = newLabelFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_CLASS__LABEL_FEATURE, oldLabelFeature, labelFeature));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case GenModelPackage.GEN_CLASS__GEN_FEATURES:
          return ((InternalEList)getGenFeatures()).basicAdd(otherEnd, msgs);
        case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
          return ((InternalEList)getGenOperations()).basicAdd(otherEnd, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case GenModelPackage.GEN_CLASS__GEN_FEATURES:
          return ((InternalEList)getGenFeatures()).basicRemove(otherEnd, msgs);
        case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
          return ((InternalEList)getGenOperations()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case GenModelPackage.GEN_CLASS__GEN_PACKAGE:
        return getGenPackage();
      case GenModelPackage.GEN_CLASS__PROVIDER:
        return getProvider();
      case GenModelPackage.GEN_CLASS__IMAGE:
        return isImage() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_CLASS__ECORE_CLASS:
        if (resolve) return getEcoreClass();
        return basicGetEcoreClass();
      case GenModelPackage.GEN_CLASS__GEN_FEATURES:
        return getGenFeatures();
      case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
        return getGenOperations();
      case GenModelPackage.GEN_CLASS__LABEL_FEATURE:
        if (resolve) return getLabelFeature();
        return basicGetLabelFeature();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case GenModelPackage.GEN_CLASS__GEN_PACKAGE:
        return getGenPackage() != null;
      case GenModelPackage.GEN_CLASS__PROVIDER:
        return provider != PROVIDER_EDEFAULT;
      case GenModelPackage.GEN_CLASS__IMAGE:
        return image != IMAGE_EDEFAULT;
      case GenModelPackage.GEN_CLASS__ECORE_CLASS:
        return ecoreClass != null;
      case GenModelPackage.GEN_CLASS__GEN_FEATURES:
        return genFeatures != null && !genFeatures.isEmpty();
      case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
        return genOperations != null && !genOperations.isEmpty();
      case GenModelPackage.GEN_CLASS__LABEL_FEATURE:
        return labelFeature != null;
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case GenModelPackage.GEN_CLASS__PROVIDER:
        setProvider((GenProviderKind)newValue);
        return;
      case GenModelPackage.GEN_CLASS__IMAGE:
        setImage(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_CLASS__ECORE_CLASS:
        setEcoreClass((EClass)newValue);
        return;
      case GenModelPackage.GEN_CLASS__GEN_FEATURES:
        getGenFeatures().clear();
        getGenFeatures().addAll((Collection)newValue);
        return;
      case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
        getGenOperations().clear();
        getGenOperations().addAll((Collection)newValue);
        return;
      case GenModelPackage.GEN_CLASS__LABEL_FEATURE:
        setLabelFeature((GenFeature)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case GenModelPackage.GEN_CLASS__PROVIDER:
        setProvider(PROVIDER_EDEFAULT);
        return;
      case GenModelPackage.GEN_CLASS__IMAGE:
        setImage(IMAGE_EDEFAULT);
        return;
      case GenModelPackage.GEN_CLASS__ECORE_CLASS:
        setEcoreClass((EClass)null);
        return;
      case GenModelPackage.GEN_CLASS__GEN_FEATURES:
        getGenFeatures().clear();
        return;
      case GenModelPackage.GEN_CLASS__GEN_OPERATIONS:
        getGenOperations().clear();
        return;
      case GenModelPackage.GEN_CLASS__LABEL_FEATURE:
        setLabelFeature((GenFeature)null);
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (provider: ");
    result.append(provider);
    result.append(", image: ");
    result.append(image);
    result.append(')');
    return result.toString();
  }

  public String getItemProviderAdapterFactoryClassName()
  {
    return getGenPackage().getItemProviderAdapterFactoryClassName();
  }

  public boolean canGenerateEdit()
  {
    return super.canGenerateEdit() && !isInterface();
  }

  public boolean canGenerateEditor()
  {
    return false;
  }

  public void generateEdit(IProgressMonitor progressMonitor)
  {
    try
    {
      if (!canGenerateEdit()) return;

      progressMonitor.beginTask("", 2 + getAllChildrenFeatures().size());
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingProvider_message", new Object [] { getFormattedName() }));

      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingJavaClass_message", 
            new Object [] { getGenPackage().getProviderPackageName() + "." + getProviderClassName() }));
      generate
        (new SubProgressMonitor(progressMonitor, 1), 
         Generator.EMF_EDIT_PROJECT_STYLE, 
         getGenModel().getEffectiveModelPluginVariables(), 
         getGenModel().getEditDirectory(), 
         getGenPackage().getProviderPackageName(),
         getProviderClassName(), 
         getGenModel().getItemProviderEmitter());

      if (isImage())
      {
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_GeneratingItemIcon_message", new Object [] { getItemIconFileName() }));
        generate
          (new SubProgressMonitor(progressMonitor, 1), 
           Generator.EMF_EDIT_PROJECT_STYLE, 
           getGenModel().getEffectiveModelPluginVariables(), 
           getItemIconFileName(), 
           ((GenModelImpl)getGenModel()).getItemGIFEmitter(),
           getName());
      }

      if (getGenModel().isCreationCommands())
      {
        for (Iterator iter = getAllChildrenFeatures().iterator(); iter.hasNext(); )
        {
          GenFeature feature = (GenFeature)iter.next();
          for (Iterator cIter = getChildrenClasses(feature).iterator(); cIter.hasNext(); )
          {
            GenClass childClass = (GenClass)cIter.next();
            progressMonitor.subTask
              (CodeGenEcorePlugin.INSTANCE.getString
                 ("_UI_GeneratingCreateChildIcon_message", new Object [] { getCreateChildIconFileName(feature, childClass) }));
            generate
              (new SubProgressMonitor(progressMonitor, 1), 
               Generator.EMF_EDIT_PROJECT_STYLE, 
               getGenModel().getEffectiveModelPluginVariables(), 
               getCreateChildIconFileName(feature, childClass), 
               ((GenModelImpl)getGenModel()).getCreateChildGIFEmitter(),
               getName(),
               childClass.getName());
          }
        }
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }

  public boolean reconcile(GenClass oldGenClassVersion)
  {
    if (getEcoreClass().getName().equals(oldGenClassVersion.getEcoreClass().getName()))
    {
      for (Iterator i = getGenFeatures().iterator(); i.hasNext(); )
      {
        GenFeature genFeature = (GenFeature)i.next();
        for (Iterator j = oldGenClassVersion.getGenFeatures().iterator(); j.hasNext(); )
        {
          GenFeature oldGenFeatureVersion = (GenFeature)j.next();
          if (genFeature.reconcile(oldGenFeatureVersion))
          {
            break;
          }
        }
      }

      for (Iterator i = getGenOperations().iterator(); i.hasNext(); )
      {
        GenOperation genOperation = (GenOperation)i.next();
        for (Iterator j = oldGenClassVersion.getGenOperations().iterator(); j.hasNext(); )
        {
          GenOperation oldGenOperation = (GenOperation)j.next();
          if (genOperation.reconcile(oldGenOperation))
          {
            break;
          }
        }
      }
      reconcileSettings(oldGenClassVersion);
      return true;
    }
    else
    {
      return false;
    }
  }

  protected void reconcileSettings(GenClass oldGenClassVersion)
  {
    setProvider(oldGenClassVersion.getProvider());
    setImage(oldGenClassVersion.isImage());
  }

  public boolean reconcile()
  {
    EClass eClass = getEcoreClass();
    if (eClass == null || eClass.eIsProxy() || eClass.eResource() == null)
    {
      return false;
    }
    else
    {
      for (Iterator i = getGenFeatures().iterator(); i.hasNext(); )
      {
        GenFeature genFeature = (GenFeature)i.next();
        if (!genFeature.reconcile())
        {
          i.remove();
        }
      }

      for (Iterator i = getGenOperations().iterator(); i.hasNext(); )
      {
        GenOperation genOperation = (GenOperation)i.next();
        if (!genOperation.reconcile())
        {
          i.remove();
        }
      }

      return true;
    }
  }
}
