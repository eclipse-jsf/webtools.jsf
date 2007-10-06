/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.util;

import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;

/**
 * The tranform between pageflow and navigation rules in faces-config file.
 * 
 * @author Xiao-guang Zhang
 */
public class PageflowTransform {

	/** The shared singleton instance. */
	private static PageflowTransform transform;

	private PageflowTransform() {
		// private contructor
	}

	/**
	 * Returns the shared singleton instance.
	 * @return  the singleton transform instance
	 */
	public static PageflowTransform getInstance() {
		if (transform == null) {
			transform = new PageflowTransform();
		}
		return transform;
	}

	/**
	 * Update source PFPage with Faces-config model.
	 * 
	 * @param sourcePage
	 * @param navigationRuleFC
	 * @author sfshi
	 */
	public void updatePageflowSourcePage(PageflowPage sourcePage,
			NavigationRuleType navigationRuleFC) {
		// set the descriiption of link
		if (navigationRuleFC.getDescription() != null
				&& navigationRuleFC.getDescription().size() > 0) {
			String description = ((DescriptionType) navigationRuleFC
					.getDescription().get(0)).getTextContent();
			sourcePage.setComment(description);
		}
		// set the display-name of link
		if (navigationRuleFC.getDisplayName() != null
				&& navigationRuleFC.getDisplayName().size() > 0) {
			String displayName = ((DisplayNameType) navigationRuleFC
					.getDisplayName().get(0)).getTextContent();
			sourcePage.setName(displayName);
		}

		// Set the icon of link
		if (navigationRuleFC.getIcon() != null
				&& navigationRuleFC.getIcon().size() > 0) {
			boolean largeUpdated = false;
			boolean smallUpdated = false;
			for (int i = 0; i < navigationRuleFC.getIcon().size(); i++) {
				IconType icon = (IconType) navigationRuleFC.getIcon().get(i);
				if (!largeUpdated && icon.getLargeIcon() != null) {
					sourcePage.setLargeicon(icon.getLargeIcon()
							.getTextContent());
					largeUpdated = true;
				}

				if (!smallUpdated && icon.getSmallIcon() != null) {
					sourcePage.setSmallicon(icon.getSmallIcon()
							.getTextContent());
					smallUpdated = true;
				}
				if (largeUpdated && smallUpdated) {
					return;
				}
			}
		}
	}

	/**
	 * Update target PFPage with Faces-config model.
	 * 
	 * @param targetPage
	 * @param navigationCaseFC
	 * @author sfshi
	 */
	public void updatePageflowTargetPage(PageflowPage targetPage,
			NavigationCaseType navigationCaseFC) {
		// set the descriiption of link
		if (navigationCaseFC.getDescription() != null
				&& navigationCaseFC.getDescription().size() > 0) {
			String description = ((DescriptionType) navigationCaseFC
					.getDescription().get(0)).getTextContent();
			targetPage.setComment(description);
		}
		// set the display-name of link
		if (navigationCaseFC.getDisplayName() != null
				&& navigationCaseFC.getDisplayName().size() > 0) {
			String displayName = ((DisplayNameType) navigationCaseFC
					.getDisplayName().get(0)).getTextContent();
			targetPage.setName(displayName);
		}

		// Set the icon of link
		if (navigationCaseFC.getIcon() != null
				&& navigationCaseFC.getIcon().size() > 0) {
			boolean largeUpdated = false;
			boolean smallUpdated = false;
			for (int i = 0; i < navigationCaseFC.getIcon().size(); i++) {
				IconType icon = (IconType) navigationCaseFC.getIcon().get(0);
				if (!largeUpdated && icon.getLargeIcon() != null) {
					targetPage.setLargeicon(icon.getLargeIcon()
							.getTextContent());
					largeUpdated = true;
				}

				if (!smallUpdated && icon.getSmallIcon() != null) {
					targetPage.setSmallicon(icon.getSmallIcon()
							.getTextContent());
					smallUpdated = true;
				}
				if (largeUpdated && smallUpdated) {
					return;
				}
			}
		}
	}

	/**
	 * update the pageflow model 's information, including pageflow's name,
	 * description and related config file
	 * 
	 * @param newPageflow
	 * @param oldPageflow
	 */
	public static void updatePageflowModelInfo(Pageflow newPageflow,
			Pageflow oldPageflow) {
		if (newPageflow == null || oldPageflow == null) {
			return;
		}
		newPageflow.setName(oldPageflow.getName());
		newPageflow.setComment(oldPageflow.getComment());
		newPageflow.setConfigfile(oldPageflow.getConfigfile());

	}
}
// PageflowTransform
