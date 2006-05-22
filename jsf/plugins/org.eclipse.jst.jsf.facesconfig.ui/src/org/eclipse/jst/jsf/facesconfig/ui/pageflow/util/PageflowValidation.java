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

import java.util.Iterator;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;
import org.eclipse.osgi.util.NLS;

/**
 * Pageflow's validation functions
 * 
 * @author Xiao-guang Zhang
 */
public class PageflowValidation {
	/** PageflowValidation singleton instance */
	private static PageflowValidation instance;

	/** constructor */
	private PageflowValidation() {

	}

	/**
	 * get the singleton instance of pageflow validation
	 * 
	 * @return
	 */
	public static PageflowValidation getInstance() {
		if (instance == null) {
			instance = new PageflowValidation();
		}
		return instance;
	}

	/**
	 * check whether the source and target pageflow node are allowed to create a
	 * new link NOT allowed source and target: 1. target node is Begin or source
	 * node is End 2. both of source and target node are Action. 3. double
	 * connection from the same source to target
	 * 
	 * @param source -
	 *            source pageflow node
	 * @param target -
	 *            target pageflow node
	 * @return - is valid link or not.
	 * 
	 */
	public boolean isValidLinkForCreation(PageflowNode source,
			PageflowNode target) {
		// Source and target must be pointing to some real pageflow node
		if (source == null || target == null) {
			return false;
		}
		return true;
	}

	/**
	 * determine the pflink is valid or not for property souce. in our model,
	 * there are five types of invalid pflinke for property source. 1. link
	 * between page and action 2. the source of link is Begin or End node 3. the
	 * target of link is Begin or End node.
	 * 
	 * @return - valid link or not.
	 * 
	 */
	public boolean isValidLinkForProperty(PageflowLink link) {
		return true;
	}

	/**
	 * determine the pflink is valid or not for face-config rule in our model,
	 * there are four types of invalid pflinke. 1. the source of link is Begin
	 * or End node 2. the target of link is Begin or End node.
	 * 
	 * @return - valid link or not.
	 * 
	 */
	public boolean isValidLinkForNavigationRule(PageflowLink link) {
		return true;
	}

	/**
	 * determine the pflink is valid or not for face-config rule in our model,
	 * there are four types of invalid pflinke.
	 * 
	 * @return - valid link or not.
	 * 
	 */
	public String isValidLink(PageflowLink link) {
		String errorMessage = null;
		// action-page style
		// TODO: it should guarantee that outcome should be defined in the
		// managed bean's method.

		if (link.getSource() instanceof PageflowPage
				&& link.getTarget() instanceof PageflowPage) {
			String outcome = link.getOutcome();
			if (outcome != null && outcome.length() >= 0) {
				// if (isValidServiceOutcome(outcome))
				// {
				// return errorMessage;
				// }
				String webPath = ((PageflowPage) link.getSource()).getPath();
				if (webPath != null) {
					String projectPath = WebrootUtil.getProjectPath(link,
							webPath);

					Path resourcePath = new Path(projectPath);
					IWorkspaceRoot workspaceRoot = ResourcesPlugin
							.getWorkspace().getRoot();
					final IResource resource = workspaceRoot
							.findMember(resourcePath);

					if (resource == null || !resource.exists()) {
						// Pageflow.Validation.NotExistedSourcePage = The source
						// page {0} is not existed.
						errorMessage = NLS
								.bind(
										PageflowMessages.Pageflow_Validation_UndefinedOutcome,
										outcome, webPath);
					}
				} else {
					// Pageflow.Validation.MissingSourcePage = The source page
					// should be specified.
					errorMessage = PageflowMessages.Pageflow_Validation_MissingSourcePage;
				}
			}
		}
		return errorMessage;
	}

	/**
	 * Check whether the page is already existed in the pageflow.
	 * 
	 * @param pageflow
	 * @param page
	 * @return
	 */
	public boolean isExistedPage(Pageflow pageflow, PageflowPage page) {
		if (pageflow == null || page == null || page.getPath() == null) {
			return false;
		}

		return isExistedPage(pageflow, page.getPath());
	}

	/**
	 * Check whether the page is already existed in the pageflow.
	 * 
	 * @param pageflow
	 * @param pagePath
	 * @return
	 */
	public boolean isExistedPage(Pageflow pageflow, String pagePath) {
		if (pageflow == null || pagePath == null) {
			return false;
		}

		Iterator iter = pageflow.getNodes().iterator();

		while (iter.hasNext()) {
			PageflowNode existedNode = (PageflowNode) iter.next();

			if (existedNode instanceof PageflowPage) {
				if (((PageflowPage) existedNode).getPath() != null
						&& ((PageflowPage) existedNode).getPath()
								.equalsIgnoreCase(pagePath)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Check whether the page is valid or not validate page is : 1. if the page
	 * is general jsp page, it should physically existed. 2. if the page is in
	 * the form of /foldername/* (it contains the folder), the folder should be
	 * existed.
	 * 
	 * @param element
	 * @return
	 */
	public String getNotifyMessage(PageflowPage page) {
		String errorMessage = null;
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		String pagePath = page.getPath();

		// Check the path is empty or not
		if (pagePath != null && pagePath.length() > 0) {
			String resourceName = WebrootUtil.getProjectPath(page, pagePath);
			Path resourcePath = new Path(resourceName);

			final IResource resource = workspaceRoot.findMember(resourcePath);

			if (resource != null && resource.exists()) {
				// This page is related with jsp file
				if (resourcePath.getFileExtension() != null) {
					if (!WebrootUtil.isValidWebFile(resourcePath)) {
						// Pageflow.Validation.InvalidWebPage = the file {0} is
						// not valid web file.
						errorMessage = NLS
								.bind(
										PageflowMessages.Pageflow_Validation_InvalidWebPage,
										pagePath);
					}
				}
			} else {
				// Pageflow.Validation.NotExitedWebPage = the web page {0} is
				// not existed.
				errorMessage = NLS.bind(
						PageflowMessages.Pageflow_Validation_NotExitedWebPage,
						pagePath);
			}
		} else {
			// Pageflow.Validation.MissingWebPage = the web page's path should
			// be specified.
			errorMessage = PageflowMessages.Pageflow_Validation_MissingWebPage;
		}
		return errorMessage;
	}

	/**
	 * check that the action is valid EL or not,
	 * 
	 * @param actionEL -
	 *            action string, it should be in the form of #{...}
	 * @return - True or False
	 */
	public static boolean isValidActionEL(String actionEL) {
		if (actionEL != null && actionEL.length() > 3) {
			// action is defined in JSF expression language, e.g,
			// #{beanname.methodname}
			if (actionEL.substring(0, 2).equalsIgnoreCase("#{")//$NON-NLS-1$
					&& actionEL.substring(actionEL.length() - 1,
							actionEL.length()).equalsIgnoreCase("}"))//$NON-NLS-1$
			{
				// Check the bean is existed or not.
				return true;
			}

		}
		return false;
	}

	/**
	 * get the managed bean from action's expression, e.g., beanName.methodName.
	 * The return will be beanName.
	 * 
	 * @param actionEL
	 * @return
	 */
	public static String getManagedBeanforAction(String actionEL) {
		String managedBeanName = null;
		if (isValidActionEL(actionEL)) {
			actionEL = actionEL.substring(2, actionEL.length() - 1);
			String[] actionParts = actionEL.split("[.]");
			managedBeanName = actionParts[0];//$NON-NLS-1$
		}

		return managedBeanName;
	}

}
