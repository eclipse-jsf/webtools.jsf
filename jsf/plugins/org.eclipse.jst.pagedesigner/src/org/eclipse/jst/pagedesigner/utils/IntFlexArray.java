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

	public IntFlexArray() {
		this(10);
	}

	public IntFlexArray(int initCapacity) {
		if (initCapacity <= 0)
			initCapacity = 10;
		array = new int[initCapacity];
	}

	public void setAt(int idx, int obj) {
		ensureCapacity(idx + 1);
		array[idx] = obj;
		if (idx + 1 > size)
			size = idx + 1;
	}

	public int getAt(int idx) {
		if (idx < array.length)
			return array[idx];
        return 0;
	}

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
