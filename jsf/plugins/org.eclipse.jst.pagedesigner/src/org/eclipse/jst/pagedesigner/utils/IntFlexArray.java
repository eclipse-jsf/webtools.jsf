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

/**
 * Flexible int array. You can set/get of any index value. The array size will
 * be automatically adjusted.
 * 
 * @author mengbo
 */
public class IntFlexArray {
	int[] array;

	int size = 0;

	/**
	 * Create a new flex array with default capacity
	 */
	public IntFlexArray() {
		this(10);
	}

	/**
	 * @param initCapacity
	 */
	public IntFlexArray(int initCapacity) {
		if (initCapacity <= 0)
			initCapacity = 10;
		array = new int[initCapacity];
	}

	/**
	 * @param idx
	 * @param obj
	 */
	public void setAt(int idx, int obj) {
		ensureCapacity(idx + 1);
		array[idx] = obj;
		if (idx + 1 > size)
			size = idx + 1;
	}

	/**
	 * @param idx
	 * @return the value at idx or 0 if idx is out of bounds
	 */
	public int getAt(int idx) {
		if (idx < array.length)
			return array[idx];
        return 0;
	}

	/**
	 * @return the current size of the array
	 */
	public int getSize() {
		return size;
	}

	private void ensureCapacity(int size1) {
		if (size1 <= array.length)
			return;
		int[] temp = array;
		array = new int[2 * temp.length];
		System.arraycopy(temp, 0, array, 0, temp.length);
	}
}
