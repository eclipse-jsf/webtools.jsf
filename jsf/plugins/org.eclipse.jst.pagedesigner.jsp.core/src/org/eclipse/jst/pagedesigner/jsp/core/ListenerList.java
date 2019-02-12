/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsp.core;

/**
 * Local version of org.eclipse.jface.util.ListenerList (modified)
 * @author mengbo
 * @version 1.5
 */
public class ListenerList {
	/**
	 * The current number of listeners. Maintains invariant: 0 <= fSize <=
	 * listeners.length.
	 */
	private int _size;

	/**
	 * The list of listeners. Initially <code>null</code> but initialized to
	 * an array of size capacity the first time a listener is added. Maintains
	 * invariant: listeners != null if and only if fSize != 0
	 */
	private Object[] _listeners = null;

	/**
	 * The empty array singleton instance, returned by getListeners() when size ==
	 * 0.
	 */
	private static final Object[] EmptyArray = new Object[0];

	/**
	 * Creates a listener list with the given initial capacity.
	 * 
	 * @param capacity
	 *            the number of listeners which this list can initially accept
	 *            without growing its internal representation; must be at least
	 *            1
	 */
	public ListenerList(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException();
		}
		_listeners = new Object[capacity];
		_size = 0;
	}

	/**
	 * Adds a listener to the list. Has no effect if an identical listener is
	 * already registered.
	 * 
	 * @param listener
	 *            a listener
	 */
	public synchronized void add(Object listener) {
		if (listener == null) {
			throw new IllegalArgumentException();
		}
		// check for duplicates using identity
		for (int i = 0; i < _size; ++i) {
			if (_listeners[i] == listener) {
				return;
			}
		}
		// grow array if necessary
		if (_size == _listeners.length) {
			Object[] temp = new Object[(_size * 2) + 1];
			System.arraycopy(_listeners, 0, temp, 0, _size);
			_listeners = temp;
		}
		_listeners[_size++] = listener;
	}

	/**
	 * Returns an array containing all the registered listeners. The resulting
	 * array is unaffected by subsequent adds or removes. If there are no
	 * listeners registered, the result is an empty array singleton instance (no
	 * garbage is created). Use this method when notifying listeners, so that
	 * any modifications to the listener list during the notification will have
	 * no effect on the notification itself.
	 * @return the array of registered listeners
	 */
	public synchronized Object[] getListeners() {
		if (_size == 0) {
			return EmptyArray;
		}
		Object[] result = new Object[_size];
		System.arraycopy(_listeners, 0, result, 0, _size);
		return result;
	}

	/**
	 * Removes a listener from the list. Has no effect if an identical listener
	 * was not already registered.
	 * 
	 * @param listener
	 *            a listener
	 */
	public synchronized void remove(Object listener) {
		if (listener == null) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < _size; ++i) {
			if (_listeners[i] == listener) {
				if (--_size == 0) {
					_listeners = new Object[1];
				} else {
					if (i < _size) {
						_listeners[i] = _listeners[_size];
					}
					_listeners[_size] = null;
				}
				return;
			}
		}
	}

	/**
	 * Removes all the listeners from the list.
	 */
	public void removeAll() {
		_listeners = new Object[0];
		_size = 0;
	}

	/**
	 * Returns the number of registered listeners
	 * 
	 * @return the number of registered listeners
	 */
	public int size() {
		return _size;
	}
}
