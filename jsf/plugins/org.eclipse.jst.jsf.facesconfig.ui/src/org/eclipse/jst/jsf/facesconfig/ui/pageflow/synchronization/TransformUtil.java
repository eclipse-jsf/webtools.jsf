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
	 * @param object 
	 * @return true if object's root ancestor is a facesConfig model
	 */
	public static boolean isValidFacesConfigElement(EObject object) {
		EObject parent = EcoreUtil.getRootContainer(object);
		boolean result = parent instanceof FacesConfigType;
		return result;
	}

	/**
	 * To see if the pageflow element is on a valid hirachy.
	 * @param element 
	 * @return true if element's root ancestor is a page flow element
	 */
	public static boolean isValidPageflowElement(EObject element) {
		EObject root = EcoreUtil.getRootContainer(element);
		boolean result = root instanceof Pageflow;
		return result;
	}

	/**
	 * @param link
	 * @return true the link is valid
	 */
	public static boolean isValidLink(PageflowLink link) {
		return isValidPageflowElement(link) && link.getSource() != null
				&& link.getTarget() != null;
	}

	/**
	 * @param rule
	 * @return gets the from-view-id from rule
	 */
	public static String getFromViewID(NavigationRuleType rule) {
		String result = "*"; //$NON-NLS-1$
		if (rule.getFromViewId() != null) {
			result = rule.getFromViewId().getTextContent();
		}
		return result;
	}

	/**
	 * @param navCase
	 * @return gets the to-view-id from navCase
	 */
	public static String getToViewID(NavigationCaseType navCase) {
		String result = "*"; //$NON-NLS-1$
		if (navCase.getToViewId() != null) {
			result = navCase.getToViewId().getTextContent();
		}
		return result;
	}

	/**
	 * @param path
	 * @param pageflow
	 * @return get pageflowpage in pageflow corresponding to path
	 */
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

	/**
	 * @param action
	 * @param outcome
	 * @param pageflow
	 * @return find the end case
	 */
	public static PageflowNode findCaseEnd(PageflowPage action,
			FromOutcomeType outcome, Pageflow pageflow) {
		// TODO: find a case end in pageflow model
		List links = action.getOutlinks();
		for (int i = 0; i < links.size(); i++) {
			PageflowLink link = (PageflowLink) links.get(i);
			String outcomeStr = ""; //$NON-NLS-1$
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
