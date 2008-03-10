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
package org.eclipse.jst.pagedesigner.meta.internal;

import java.util.Comparator;
import java.util.HashSet;

/**
 * Sorts categories and ensures that @see{ITabbedPropertiesConstants.OTHER_CATEGORY} comes last
 * @author mengbo
 */
public class CategoryNameComparator implements Comparator {
	static HashSet _pairs = new HashSet();

	private final static CategoryNameComparator _instance = new CategoryNameComparator();

	static class Pair {
		String s1;

		String s2;

		Pair(String a, String b) {
			s1 = a;
			s2 = b;
		}

		public int hashCode() {
			return s1.hashCode() + s2.hashCode();
		}

		public boolean equals(Object o) {
			if (o instanceof Pair) {
				Pair p = (Pair) o;
				return s1.equals(p.s1) && s2.equals(p.s2);
			}
			return false;
		}
	}

	/**
	 * @param s1
	 * @param s2
	 */
	public static void addPair(String s1, String s2) {
		_pairs.add(new Pair(s1, s2));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		if (o1.equals(o2))
			return 0;

		//Below code sorted OTHER category ("Attributes") to the bottom
		//Commenting out as it just looks strange
//		if (ITabbedPropertiesConstants.OTHER_CATEGORY.equals(o1))
//			return 1;
//		if (ITabbedPropertiesConstants.OTHER_CATEGORY.equals(o2))
//			return -1;

//		Pair p = new Pair((String) o1, (String) o2);
//		if (_pairs.contains(p))
//			return -1;
		return ((String)o1).compareTo(((String)o2));
	}

	/**
	 * @return the instance
	 */
	public static CategoryNameComparator getInstance() {
		return _instance;
	}
}
