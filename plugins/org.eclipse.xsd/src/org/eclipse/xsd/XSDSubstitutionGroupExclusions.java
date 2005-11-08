/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDSubstitutionGroupExclusions.java,v 1.3 2005/11/08 13:52:50 emerks Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Substitution Group Exclusions</b></em>'.
 * @see org.eclipse.xsd.XSDElementDeclaration#getSubstitutionGroupExclusions()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDSubstitutionGroupExclusions()
 * @model
 * @generated
 */
public final class XSDSubstitutionGroupExclusions extends AbstractEnumerator
{
  /**
   * The '<em><b>Extension</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an element with a type derived via extension 
   * may not be nominated to an element declaration's substitution group.
   * </p>
   * <!-- end-user-doc --> 
   * @see #EXTENSION_LITERAL
   * @model name="extension"
   * @generated
   * @ordered
   */
  public static final int EXTENSION = 0;

  /**
   * The '<em><b>Restriction</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an element with a type derived via restriction 
   * may not be nominated to an element declaration's substitution group.
   * </p>
   * <!-- end-user-doc --> 
   * @see #RESTRICTION_LITERAL
   * @model name="restriction"
   * @generated
   * @ordered
   */
  public static final int RESTRICTION = 1;
  /**
   * The '<em><b>Extension</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #EXTENSION
   * @generated
   * @ordered
   */
  public static final XSDSubstitutionGroupExclusions EXTENSION_LITERAL = new XSDSubstitutionGroupExclusions(EXTENSION, "extension", "extension");

  /**
   * The '<em><b>Restriction</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #RESTRICTION
   * @generated
   * @ordered
   */
  public static final XSDSubstitutionGroupExclusions RESTRICTION_LITERAL = new XSDSubstitutionGroupExclusions(RESTRICTION, "restriction", "restriction");

  /**
   * An array of all the '<em><b>Substitution Group Exclusions</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDSubstitutionGroupExclusions[] VALUES_ARRAY =
    new XSDSubstitutionGroupExclusions[]
    {
      EXTENSION_LITERAL,
      RESTRICTION_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Substitution Group Exclusions</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Substitution Group Exclusions</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDSubstitutionGroupExclusions get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDSubstitutionGroupExclusions result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Substitution Group Exclusions</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDSubstitutionGroupExclusions getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDSubstitutionGroupExclusions result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Substitution Group Exclusions</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDSubstitutionGroupExclusions get(int value)
  {
    switch (value)
    {
      case EXTENSION: return EXTENSION_LITERAL;
      case RESTRICTION: return RESTRICTION_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private XSDSubstitutionGroupExclusions(int value, String name, String literal)
  {
    super(value, name, literal);
  }

}
