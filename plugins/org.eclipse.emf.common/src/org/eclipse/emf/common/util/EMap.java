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
 * $Id: EMap.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.common.util;


import java.util.Collection;
import java.util.Map;
import java.util.Set;


/**
 * A list of {@link java.util.Map.Entry java.util.Map.Entry} instances, i.e., entries, that 
 * supports a {@link #map} view
 * as well as the full {@link java.util.Map} API,
 * with the noteable exception of {@link java.util.Map#remove(Object)}.
 * It's return type conflicts with that of {@link java.util.Collection#remove(Object)}.
 * The {@link #removeKey removeKey(Object)} method may be used instead.
 * The implementation of remove may delegate to <code>removeKey</code> 
 * for an object that is not an instance of <code>Map.Entry</code>.
 */
public interface EMap extends EList
{
  /**
   * Returns the value associated with the key.
   * The key, the value, or both may be <code>null</code>.
   * @param key the key of the value.
   * @return the value associated with the key.
   */
  Object get(Object key);

  /**
   * Associates the key with the value
   * and returns the value previously associated with the key, or <code>null</code>.
   * The key, the value, or both may be <code>null</code>.
   * Either the existing entry is updated, 
   * or a new entry is added to the end of the list.
   * @param key the key of the value.
   * @param value the value associated with the key.
   * @return the value formerly associated with the key, or <code>null</code>.
   */
  Object put(Object key, Object value);

  /**
   * Puts each {@link java.util.Map.Entry Map.Entry} of the given map into this one.
   * @param map the map of entries.
   * @see #put
   */
  void putAll(Map map);

  /**
   * Puts each {@link java.util.Map.Entry Map.Entry} of the given map into this one.
   * @param map the map of entries.
   * @see #put
   */
  void putAll(EMap map);

  /**
   * Returns the index in the list of the entry with the given key, 
   * or <code>-1</code>, if there is no such entry.
   * @param key a key.
   * @return the index of the entry with the given key.
   */
  int indexOfKey(Object key);

  /**
   * Returns whether the key is associated with a value.
   * @param key a key associated with a value.
   * @return whether the key is associated with a value.
   */
  boolean containsKey(Object key);

  /**
   * Returns whether the value is associated with a key.
   * @param value a value associated with a key.
   * @return whether the value is associated with a key.
   */
  boolean containsValue(Object value);

  /**
   * Disassociates the key from its value,
   * and returns the value formerly associated with the key.
   * An entry is removed from the list, if the key is found.
   * @param key the key of a value.
   * @return the value formerly associated with the key.
   * 
   */
  Object removeKey(Object key);

  /**
   * Returns a map view.
   * @return a map view.
   */
  Map map();

  /**
   * Returns a set view of the entries.
   * @return a set view of the entries.
   */
  Set entrySet();

  /**
   * Returns a set view of the keys of the entries.
   * @return a set view of the keys of the entries.
   */
  Set keySet();

  /**
   * Returns a collection view the values of the entries.
   * @return a collection view the values of the entries.
   */
  Collection values();

  /**
   * An internal interface implemented by the {@link #map() map view}.
   * It provides access to the EMap view.
   */
  interface InternalMapView extends Map
  {
    /**
     * Returns the EMap view of the map.
     * @return the EMap view of the map.
     */
    EMap eMap();
  }
}
