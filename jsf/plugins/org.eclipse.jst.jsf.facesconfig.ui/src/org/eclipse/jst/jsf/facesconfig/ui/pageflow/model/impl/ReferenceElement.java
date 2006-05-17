/**
 * Confidential Property of Sybase, Inc. 
 * (c) Copyright Sybase, Inc. 2004-2006. 
 * All rights reserved.
 */
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.wst.common.internal.emf.resource.CompatibilityXMIResource;

/**
 * The base class for mapping facesconfig node from pageflow node.
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

	public ReferenceElement(PageflowElement pageflowElement, EObject facesConfigObject) {
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
	 * @return
	 */
	public static String resolvePath(EObject object) {
		if (object != null) {
			((CompatibilityXMIResource) object.eResource())
					.setFormat(CompatibilityXMIResource.FORMAT_EMF1);
			String uriTarget = object.eResource().getURIFragment(object);
			return uriTarget;
		} else {
			return "";
		}
	}

	public boolean remove(EObject object) {
		boolean result = data.remove(object);
		return result;
	}

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
	 * The EMF path of an element.
	 * 
	 * @return
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
	 * @return
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

	/**
	 * Update when faces-config is modified.
	 * 
	 */
	abstract public void update();

	abstract public Object get(int eFeature);

	abstract public void set(EStructuralFeature eFeature, Object newValue);
}
