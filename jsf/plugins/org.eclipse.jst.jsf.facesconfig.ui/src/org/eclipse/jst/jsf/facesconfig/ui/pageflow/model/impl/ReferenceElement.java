/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.wst.common.internal.emf.resource.CompatibilityXMIResource;

/**
 * The base class for mapping facesconfig node from pageflow node. The
 * ReferenceElement could be extended later to enable other model to be
 * referenced by pageflow.
 * 
 * @author hmeng
 * 
 */
public abstract class ReferenceElement {

	private List data = new ArrayList();

	/**
	 * the pageflow element
	 */
	protected PageflowElement pageflowElement;

	/**
	 * @param pageflowElement
	 */
	public ReferenceElement(PageflowElement pageflowElement) {
		this.pageflowElement = pageflowElement;
	}

	/**
	 * @param pageflowElement
	 * @param facesConfigObject
	 */
	public ReferenceElement(PageflowElement pageflowElement,
			EObject facesConfigObject) {
		this(pageflowElement);
		this.add(facesConfigObject);
	}

	/**
	 * Add object to the list.
	 * 
	 * @param object
	 */
	public void add(EObject object) {
		if (!data.contains(object)) {
			data.add(object);
		}
	}


	/**
	 * The fragment path of a node.
	 * 
	 * @param object
	 * @return the path
	 */
	public static String resolvePath(EObject object) {
		if (object != null) {
			((CompatibilityXMIResource) object.eResource())
					.setFormat(CompatibilityXMIResource.FORMAT_EMF1);
			String uriTarget = object.eResource().getURIFragment(object);
			return uriTarget;
		}
        return ""; //$NON-NLS-1$
	}

	/**
	 * Resolve each referenced data's path and combine the result into one
	 * string, the path will be seperated with '|'.
	 * 
	 * @return the resolved string 
	 */
	public String resolveReferenceString() {
		String result = ""; //$NON-NLS-1$
		for (int i = 0, n = data.size(); i < n; i++) {
			result += resolvePath((EObject) data.get(i)) + "|"; //$NON-NLS-1$
		}
		if (result.length() > 0) {
			result = result.substring(0, result.length());
		}
		return result;
	}

	/**
	 * The EMF paths of all referenced elements.
	 * 
	 * @return the list of resolved paths for the data elements
	 */
	public List getPaths() {
		List paths = new ArrayList();
		for (int i = 0, n = data.size(); i < n; i++) {
			paths.add(resolvePath((EObject) data.get(i)));
		}
		return paths;
	}

	/**
	 * The facesconfig elements that are referenced.
	 * 
	 * @return the list of elements
	 */
	public List getData() {
		return data;
	}

	/**
	 * Clear the data
	 */
	public void clear() {
		if (!data.isEmpty()) {
			data.clear();
		}
	}

	/**
	 * @return true if the data is empty
	 */
	public boolean isEmpty() {
		return data.isEmpty();
	}

	/**
	 * Dispose the element
	 */
	public void dispose() {
		clear();
	}

	/**
	 * Update the referenced faces-config elements.
	 * 
	 */
	abstract public void update();

	/**
	 * Return a pageflow property's value with referenced faces-config element.
	 * 
	 * @param eFeature
	 * @return the value for eFeature
	 */
	abstract public Object get(int eFeature);

	/**
	 * To set a pageflow property's value will result in seting a faces-config
	 * element.
	 * 
	 * @param eFeature
	 * @param newValue
	 */
	abstract public void set(EStructuralFeature eFeature, Object newValue);
}
