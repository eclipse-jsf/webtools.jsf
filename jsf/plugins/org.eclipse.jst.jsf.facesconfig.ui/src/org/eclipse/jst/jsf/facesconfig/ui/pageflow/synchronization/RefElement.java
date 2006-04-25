/**
 * Confidential Property of Sybase, Inc. 
 * (c) Copyright Sybase, Inc. 2004-2006. 
 * All rights reserved.
 */
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.common.internal.emf.resource.CompatibilityXMIResource;

/**
 * The element holds reference information in pageflow node to faces config
 * node.
 * 
 * @author hmeng
 * 
 */
public class RefElement {
	private List elements = new ArrayList();

	public RefElement(String reference) {
		reset(reference);
	}

	public void add(EObject object) {
		String resolveString = resolveURI(object);
		if (!elements.contains(resolveString)) {
			elements.add(resolveString);
		}
	}

	public void clear() {
		elements.clear();
	}

	public boolean contains(EObject object) {
		String resolveString = resolveURI(object);
		return elements.contains(resolveString);
	}

	public static String resolveURI(EObject object) {
		if (object != null) {
			((CompatibilityXMIResource) object.eResource())
					.setFormat(CompatibilityXMIResource.FORMAT_EMF1);
			String uriTarget = object.eResource().getURIFragment(object);
			return uriTarget;
		} else {
			return "";
		}
	}

	public void remove(EObject object) {
		String resolveString = resolveURI(object);
		elements.remove(resolveString);
	}

	public String resolveReferenceString() {
		String result = "";
		for (int i = 0, n = elements.size(); i < n; i++) {
			result += (String) elements.get(i) + "|";
		}
		if (result.length() > 0) {
			result = result.substring(0, result.length());
		}
		return result;
	}

	public void reset(String referenceString) {
		StringTokenizer token = new StringTokenizer(referenceString, "|");
		while (token.hasMoreTokens()) {
			elements.add(token.nextElement());
		}
	}

	public List getRefLinks() {
		return elements;
	}
}
