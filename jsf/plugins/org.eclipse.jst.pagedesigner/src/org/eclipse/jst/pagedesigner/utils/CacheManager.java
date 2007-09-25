/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * This is a cache manager. It will use the ICacheEntryCreator interface to
 * manage creating new item and dispose old item.
 * 
 * @author mengbo
 * @version 1.5
 */
public class CacheManager {
	ICacheEntryCreator _creator;

	int _maxSize;

	// key --> value
	Map _map = new HashMap();

	// keep track of LRU
	LinkedList _keys = new LinkedList();

	/**
	 * @param creator
	 * @param maxSize
	 */
	public CacheManager(ICacheEntryCreator creator, int maxSize) {
		_creator = creator;
		_maxSize = maxSize;
		if (_maxSize <= 0) {
			_maxSize = 10;
		}
	}

	/**
	 * @param key
	 * @return the cache entry for key
	 */
	public Object getEntry(Object key) {
		Object result = _map.get(key);
		if (result == null) {
			// not existed yet.
			if (_map.size() >= _maxSize) {
				// we need to remove the oldest one.
				Object keyRemove = _keys.removeFirst();
				Object objToRemove = _map.remove(keyRemove);
				_creator.dispose(keyRemove, objToRemove);
			}
			result = _creator.createEntry(key);
			_keys.addLast(key);
			_map.put(key, result);
			return result;
		}
        _keys.remove(key);
        _keys.addLast(key);
        return result;
	}

	/**
	 * Dispose the cache
	 */
	public void disposeAll() {
		_keys.clear();
		for (Iterator iter = _map.keySet().iterator(); iter.hasNext();) {
			Object key = iter.next();
			Object entry = _map.get(key);
			_creator.dispose(key, entry);
		}
		_map.clear();
	}
}
