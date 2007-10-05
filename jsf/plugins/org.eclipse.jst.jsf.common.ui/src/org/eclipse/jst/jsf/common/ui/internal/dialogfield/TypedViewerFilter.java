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
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;


import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Viewer filter used in selection dialogs.
 * 
 * @author mengbo
 * @version 1.5
 */
/*package*/ class TypedViewerFilter extends ViewerFilter {

	private Class[] _fAcceptedTypes;

	private Object[] _fRejectedElements;

	/**
	 * Creates a filter that only allows elements of gives types.
	 * 
	 * @param acceptedTypes
	 *            The types of accepted elements
	 */
	public TypedViewerFilter(Class[] acceptedTypes) {
		this(acceptedTypes, null);
	}

	/**
	 * Creates a filter that only allows elements of gives types, but not from a
	 * list of rejected elements.
	 * 
	 * @param acceptedTypes
	 *            Accepted elements must be of this types
	 * @param rejectedElements
	 *            Element equals to the rejected elements are filtered out
	 */
	public TypedViewerFilter(Class[] acceptedTypes, Object[] rejectedElements) {
		Assert.isNotNull(acceptedTypes);
		_fAcceptedTypes = acceptedTypes;
		_fRejectedElements = rejectedElements;
	}

	/**
	 * @see ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (_fRejectedElements != null) {
			for (int i = 0; i < _fRejectedElements.length; i++) {
				if (element.equals(_fRejectedElements[i])) {
					return false;
				}
			}
		}
		for (int i = 0; i < _fAcceptedTypes.length; i++) {
			if (_fAcceptedTypes[i].isInstance(element)) {
				return true;
			}
		}
		return false;
	}

}
