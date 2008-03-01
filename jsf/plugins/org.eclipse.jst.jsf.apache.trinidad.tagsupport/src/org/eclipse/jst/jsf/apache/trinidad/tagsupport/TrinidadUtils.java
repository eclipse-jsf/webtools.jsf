/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport;

import org.w3c.dom.Node;

/**
 * Utility class for the Trinidad Tag Support plug-in.
 *
 * @author Ian Trimble - Oracle
 */
public class TrinidadUtils {

	private static final String KEY_DISCLOSED_CHILD_INDEX =
		"KEY_DISCLOSED_CHILD_INDEX";

	/**
	 * Sets the index of the Node instance's "disclosed" child as user data on
	 * the Node instance.
	 * 
	 * @param node Node instance on which to set index.
	 * @param index Index of node's "disclosed" child.
	 * @return true if index has changed, else false.
	 */
	public static boolean setDisclosedChildIndex(Node node, int index) {
		boolean indexChanged = false;
		if (node != null) {
			int currentIndex = getDisclosedChildIndex(node);
			if (currentIndex != index) {
				node.setUserData(
						KEY_DISCLOSED_CHILD_INDEX,
						new Integer(index),
						null);
				indexChanged = true;
			}
		}
		return indexChanged;
	}

	/**
	 * Gets the index of the Node instance's "disclosed child from user data on
	 * the Node instance.
	 * 
	 * @param node Node instance from which to get index.
	 * @return Index of node's "disclosed" child. A value of -1 indicates
	 * inability to get index from node.
	 */
	public static int getDisclosedChildIndex(Node node) {
		int index = -1;
		if (node != null) {
			Object obj = node.getUserData(KEY_DISCLOSED_CHILD_INDEX);
			if (obj instanceof Integer) {
				index = ((Integer)obj).intValue();
			}
		}
		return index;
	}

}
