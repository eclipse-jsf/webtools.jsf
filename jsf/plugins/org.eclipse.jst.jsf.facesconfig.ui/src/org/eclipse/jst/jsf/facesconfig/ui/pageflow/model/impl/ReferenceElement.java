/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

	protected PageflowElement pageflowElement;

	public ReferenceElement(PageflowElement pageflowElement) {
		this.pageflowElement = pageflowElement;
	}

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

	public boolean contains(EObject object) {
		return data.contains(object);
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
        return "";
	}

	public boolean remove(EObject object) {
		boolean result = data.remove(object);
		return result;
	}

	/**
	 * Resolve each referenced data's path and combine the result into one
	 * string, the path will be seperated with '|'.
	 * 
	 * @return the resolved string 
	 */
	public String resolveReferenceString() {
		String result = "";
		for (int i = 0, n = data.size(); i < n; i++) {
			result += resolvePath((EObject) data.get(i)) + "|";
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

	public void clear() {
		if (!data.isEmpty()) {
			data.clear();
		}
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

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
