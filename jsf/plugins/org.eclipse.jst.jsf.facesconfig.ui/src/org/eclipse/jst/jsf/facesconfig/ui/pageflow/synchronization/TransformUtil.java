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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;

/**
 * The util for pageflow and faces-config transforming
 * 
 * @author hmeng
 */

public class TransformUtil {

	/**
	 * To see if the faces-config element is on a valid hirachy.
	 */
	public static boolean isValidFacesConfigElement(EObject object) {
		EObject parent = EcoreUtil.getRootContainer(object);
		boolean result = parent instanceof FacesConfigType;
		return result;
	}

	/**
	 * To see if the pageflow element is on a valid hirachy.
	 */
	public static boolean isValidPageflowElement(EObject element) {
		EObject root = EcoreUtil.getRootContainer(element);
		boolean result = root instanceof Pageflow;
		return result;
	}

	public static boolean isValidLink(PageflowLink link) {
		return isValidPageflowElement(link) && link.getSource() != null
				&& link.getTarget() != null;
	}

	public static String getFromViewID(NavigationRuleType rule) {
		String result = "*";
		if (rule.getFromViewId() != null) {
			result = rule.getFromViewId().getTextContent();
		}
		return result;
	}

	public static String getToViewID(NavigationCaseType navCase) {
		String result = "*";
		if (navCase.getToViewId() != null) {
			result = navCase.getToViewId().getTextContent();
		}
		return result;
	}

	public static PageflowPage findPage(String path, Pageflow pageflow) {
		List nodes = pageflow.getNodes();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i) instanceof PageflowPage) {
				if (path != null) {
					if (path.equals(((PageflowPage) nodes.get(i)).getPath())) {
						return (PageflowPage) nodes.get(i);
					}
				}
			}
		}
		return null;
	}

	public static PageflowNode findCaseEnd(PageflowPage action,
			FromOutcomeType outcome, Pageflow pageflow) {
		// TODO: find a case end in pageflow model
		List links = action.getOutlinks();
		for (int i = 0; i < links.size(); i++) {
			PageflowLink link = (PageflowLink) links.get(i);
			String outcomeStr = "";
			if (outcome != null) {
				outcomeStr = outcome.getTextContent();
			}
			if (link.getOutcome().equals(outcomeStr)) {
				return link.getTarget();
			}
		}
		return null;
	}
}
