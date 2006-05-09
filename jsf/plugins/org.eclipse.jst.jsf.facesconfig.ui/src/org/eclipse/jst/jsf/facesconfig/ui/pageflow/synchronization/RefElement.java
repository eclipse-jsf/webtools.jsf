/**
 * Confidential Property of Sybase, Inc. 
 * (c) Copyright Sybase, Inc. 2004-2006. 
 * All rights reserved.
 */
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.wst.common.internal.emf.resource.CompatibilityXMIResource;

/**
 * The element holds reference information in pageflow node to faces config
 * node.
 * 
 * @author hmeng
 * 
 */
public class RefElement {

	private List data = new ArrayList();

	private PageflowElement pageflowElement;

	public RefElement(PageflowElement pageflowElement) {
		this.pageflowElement = pageflowElement;
	}

	public RefElement(PageflowElement pageflowElement, EObject facesConfigObject) {
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
			// pageflowElement.notifyModelChanged(new ENotificationImpl(
			// (PageflowElementImpl) pageflowElement,
			// NotificationImpl.SET,
			// PageflowPackage.PAGEFLOW__REFERENCE_LINK, null, null));
		}
	}

	public void clear() {
		if (!data.isEmpty()) {
			data.clear();
			// pageflowElement.notifyModelChanged(new ENotificationImpl(
			// (PageflowElementImpl) pageflowElement,
			// NotificationImpl.SET,
			// PageflowPackage.PAGEFLOW__REFERENCE_LINK, null, null));
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
		if (result) {
			// pageflowElement.notifyModelChanged(new ENotificationImpl(
			// (PageflowElementImpl) pageflowElement,
			// NotificationImpl.SET,
			// PageflowPackage.PAGEFLOW__REFERENCE_LINK, null, null));

		}
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

	public List getPaths() {
		List paths = new ArrayList();
		for (int i = 0, n = data.size(); i < n; i++) {
			paths.add(resolvePath((EObject) data.get(i)));
		}
		return paths;
	}

	public List getData() {
		return data;
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	/**
	 * Update when faces-config is modified.
	 * 
	 */
	public void update() {
		for (Iterator nodes = data.iterator(); nodes.hasNext();) {
			Object next = nodes.next();
			if (next instanceof FromViewIdType
					|| next instanceof NavigationCaseType) {
				if (!(((EObject) next).eContainer() instanceof NavigationRuleType)) {
					nodes.remove();
				}
			} else if (next instanceof ToViewIdType) {
				if (!(((EObject) next).eContainer() instanceof NavigationCaseType)) {
					nodes.remove();
				}
			}
		}
	}
}
