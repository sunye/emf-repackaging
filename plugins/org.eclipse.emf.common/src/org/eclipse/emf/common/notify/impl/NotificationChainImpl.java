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
 * $Id: NotificationChainImpl.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.common.notify.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;


/**
 * A list that acts as a notification chain.
 */
public class NotificationChainImpl extends BasicEList implements NotificationChain
{
  /**
   * Creates an empty instance.
   */
  public NotificationChainImpl()
  {
    super();
  }

  /**
   * Creates an empty instance with a given capacity.
   * param initialCapacity the initial capacity of the list before it must grow.
   */
  public NotificationChainImpl(int initialCapacity)
  {
    super(initialCapacity);
  }

  /**
   * Returns new data storage of type {@link Notification}[].
   * @return new data storage.
   */
  protected Object [] newData(int capacity)
  {
    return new Notification [capacity];
  }

  /**
   * Adds or merges a new notification.
   * @param newNotification a notification.
   * @return <code>true</code> when the notification is added and <code>false</code> when it is merged.
   */
  public boolean add(Notification newNotification)
  {
    if (newNotification == null)
    {
      return false;
    }
    else 
    {
      for (int i = 0; i < size; ++i)
      {
        Notification notification = (Notification)data[i];
        if (notification.merge(newNotification))
        {
          return false;
        }
      }

      return super.add(newNotification);
    }
  }

  /**
   * Returns the result of calling {@link #add(Notification)}.
   * @param object the notification to add.
   * @return the result of calling <code>add(Notification)</code>.
   */
  public boolean add(Object object)
  {
    return add((Notification)object);
  }

  /*
   * Javadoc copied from interface.
   */
  public void dispatch()
  {
    for (int i = 0; i < size; ++i)
    {
      Notification notification = (Notification)data[i];
      dispatch(notification);
    }
  }

  /**
   * Dispatches the notification to it's notifier.
   */
  protected void dispatch(Notification notification)
  {
    Object notifier = notification.getNotifier();
    if (notifier != null && notification.getEventType() != -1)
    {
      ((Notifier)notifier).eNotify(notification);
    }
  }
}
