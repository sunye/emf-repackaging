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
 * $Id: AbstractTreeIterator.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.common.util;


import java.util.Iterator;


/**
 * An extensible tree iterator implementation 
 * that iterates over an object, it's children, their children, and so on.
 * Clients need only implement {@link #getChildren getChildren} 
 * in order to implement a fully functional tree iterator.
 */
public abstract class AbstractTreeIterator extends BasicEList implements TreeIterator
{
  /**
   * Whether the first call to next returns the initial root object 
   * or begins with the first child of the root object.
   */
  protected boolean includeRoot;

  /**
   * The root object for which the iteration is initiated.
   */
  protected Object object;

  /**
   * The iterator that would be cut short by a call to {@link #prune}.
   */
  protected Iterator nextPruneIterator;

  /**
   * The iterator to which a {@link #remove} call will delegated.
   */
  protected Iterator nextRemoveIterator;

  /**
   * Creates an instance that iterates over an object, it's children, their children, and so on.
   * @param object the root object of the tree.
   */
  public AbstractTreeIterator(Object object)
  {
    this.object = object;
    this.includeRoot = true;
  }

  /**
   * Creates and instance that iterates over an object (but only if <code>includeRoot</code> is <code>true</code>), 
   * it's children, their children, and so on.
   */
  public AbstractTreeIterator(Object object, boolean includeRoot)
  {
    this.object = object;
    this.includeRoot = includeRoot;
  }

  /**
   * Returns the iterator that yields the children of the object.
   * @param object the object for which children are required.
   * @return the iterator that yields the children.
   */
  protected abstract Iterator getChildren(Object object);

  /**
   * Returns whether there are more elements.
   * @return whether there are more elements.
   */
  public boolean hasNext()
  {
    if (!includeRoot && data == null)
    {
      nextPruneIterator = getChildren(object);
      add(nextPruneIterator);
      return nextPruneIterator.hasNext();
    }
    else
    {
      // We don't create an iterator stack until the root mapping itself has been returned by next once.
      // After that the stack should be non-empty and the top iterator should yield true for hasNext.
      //
      return data == null || !isEmpty() && ((Iterator)data[size - 1]).hasNext();
    }
  }

  /**
   * Returns the next object and advances the iterator.
   * @return the next object.
   */
  public Object next()
  {
    Object result;

    // If we are still on the root mapping itself...
    //
    if (data == null)
    {
      // Yield that mapping, create a stack, record it as the next one to prune, and add it to the stack.
      //
      result = object;
      nextPruneIterator = getChildren(object);
      add(nextPruneIterator);
      if (includeRoot)
      {
        return result;
      }
    }

    // Get the top iterator, retrieve it's result, and record it as the one to which remove will be delegated.
    //
    Iterator currentIterator = (Iterator)data[size - 1];
    result = currentIterator.next();
    nextRemoveIterator = currentIterator;

    // If the result about to be returned has children...
    //
    Iterator iterator = getChildren(result);
    if (iterator.hasNext())
    {
      // Record the iterator as the next one to prune, and add it to the stack.
      //
      nextPruneIterator = iterator;
      add(iterator);
    }
    else
    {
      // There will be no iterator to prune.
      //
      nextPruneIterator = null;

      // While the current iterator has no next...
      //
      while (!currentIterator.hasNext())
      {
        // Pop it from the stack.
        //
        data[--size] = null;

        // If the stack is empty, we're done.
        //
        if (isEmpty())
        {
          break;
        }

        // Get the next one down and then test it for has next.
        //
        currentIterator = (Iterator)data[size - 1];
      }
    }

    return result;
  }

  /**
   * Removes the last object returned by {@link #next()} from the underlying tree;
   * it's an optional operation.
   * @exception IllegalStateException 
   * if <code>next</code> has not yet been called or has been called only the yield the root object, 
   * or <code>remove</code> has already been called after the last call to the <code>next</code> method.
   * 
   */
  public void remove()
  {
    if (nextRemoveIterator == null)
    {
      throw new IllegalStateException("There is no valid object to remove.");
    }
    nextRemoveIterator.remove();
  }

  /**
   * Prunes the iterator so that it skips over all the nodes below the most recent result of calling {@link #next next()}.
   */
  public void prune()
  {
    // If there is an iterator to prune.
    //
    if (nextPruneIterator != null)
    {
      // If that iterator is still at the top of the stack...
      //
      if (!isEmpty() && data[size - 1] == nextPruneIterator)
      {
        // Pop it off the stack.
        //
        data[--size] = null;

        // Keep poping the stack until an iterator that has a next is at the top.
        //
        while (!isEmpty() && !((Iterator)data[size - 1]).hasNext())
        {
          data[--size] = null;
        }
      }

      // You can only prune once.
      //
      nextPruneIterator = null;
    }
  }
}
