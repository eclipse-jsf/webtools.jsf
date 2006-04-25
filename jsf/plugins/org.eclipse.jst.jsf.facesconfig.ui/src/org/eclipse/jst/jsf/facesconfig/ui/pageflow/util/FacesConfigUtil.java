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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * This utility is used to get information from faces-config.
 * 
 * @author Xiao-guang Zhang
 * 
 * 
 */
public class FacesConfigUtil {
	/** the singleton instance of face-config files manager */
	private static ConfigurationManager facesConfigManager;

	// private static final Logger log = EditorPlugin
	// .getLogger(FacesConfigUtil.class);

	/**
	 * 
	 */
	private FacesConfigUtil() {

	}

	/**
	 * get the default faces-config file name according to project name of the
	 * pageflow
	 * 
	 * @param pathPageflow -
	 *            the pageflow's path
	 * @return - the default faces-config file name
	 */
	// public static String getDefaultFacesConfigFileName(final IPath
	// pathPageflow) {
	// IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	// IFile filePageflow = root.getFile(pathPageflow);
	//
	// String strFacesConfig = IFileFolderConstants.PATH_SEPARATOR
	// + filePageflow.getProject().getName()
	// + IFileFolderConstants.DEFAULT_FACES_CONFIG_FILE_PATH;
	// return strFacesConfig;
	// }
	// public static String getFacesConfigVersion(IStructuredModel sseModel) {
	// System.out.println("get faces config version in facesConfigUtil");
	// return "";
	// FacesConfigType facesConfig = getFacesConfig(sseModel);
	// return ((IDOMElement) facesConfig.getElement().getNode())
	// .getAttribute(IFacesConfigConstants.VERSION);
	// }
	// public static FacesConfigType getFacesConfig(IStructuredModel sseModel) {
	// if (sseModel == null) {
	// return null;
	// }
	// FacesConfigDocumentWrap documentWrap = new FacesConfigDocumentWrap(
	// ((IDOMModel) sseModel).getDocument());
	// return documentWrap.getFacesConfig();
	// }
	/**
	 * get the action list in the jsp file
	 * 
	 * @return - action list
	 */
	public static List getActionListInJSPFile(String jspFileName) {
		/** jsp dom adapter */
		JSPDomAdapter jspAdapter;

		List actions = new ArrayList();
		jspAdapter = new JSPDomAdapter();
		// convert the relative directory to project directory, e.g., /a.jsp to
		// /testproject/webroot/a.sjp
		String physicalJspPath = jspFileName;
		if (physicalJspPath != null && physicalJspPath.length() > 0) {
			IPath jspPath = new Path(physicalJspPath);
			IFile jspFile = ResourcesPlugin.getWorkspace().getRoot().getFile(
					jspPath);

			if (jspFile != null && jspFile.exists()) {
				// initialize the adapter to initialize the model of jsp
				if (jspAdapter.initialize(jspFile)) {
					// the prefix of JSF HTML TagLib
					String prefix = jspAdapter
							.getTagLibPrefix(JSPDomAdapter.JSF_HTML_TAGLIB);

					// get the command butonns
					List buttonActions = jspAdapter.getElementsByTagNameNS(
							prefix, "commandButton");//$NON-NLS-1$
					if (buttonActions != null) {
						actions.addAll(buttonActions);
					}

					// get the command links
					List linkActions = jspAdapter.getElementsByTagNameNS(
							prefix, "commandLink");//$NON-NLS-1$
					if (linkActions != null) {
						actions.addAll(linkActions);
					}
				}
			}
		}
		jspAdapter.releaseModel();
		return actions;
	}

	/**
	 * // * Determines if the bean is already a member of the configuration set. // * // *
	 * 
	 * @param beanName - // *
	 *            The name of the bean // *
	 * @return boolean - false if bean doesn't exist //
	 */
	// public static boolean isExsitedBean(IProject project, String beanName) {
	// List[] beanlist = getConfigurationManager(project).getManagedBeans();
	//
	// if (beanlist != null) {
	// for (int x = 0; x < beanlist.length; x++) {
	// List beans = beanlist[x];
	//
	// // Iterate through the bean list
	// for (Iterator i = beans.iterator(); i.hasNext();) {
	// Object o = i.next();
	// if (o instanceof ManagedBean) {
	// ManagedBean mbti = (ManagedBean) o;
	// String name = mbti.getManagedBeanName().trim();
	// // String name = mbnt.getValue().trim();
	// if (name.equals(beanName)) {
	// return true;
	// }
	// }
	// }
	// }
	// }
	// return false;
	// }
	// /**
	// * Constructs a unique reference name when we want to silently avoid
	// * duplicates
	// *
	// * @param xName -
	// * the integer value to increment as we look for names
	// * @param beanName -
	// * the basic reference name
	// * @return String - a unique reference name
	// */
	// public static String getUniqueName(ConfigurationManager manager, int
	// xName,
	// String beanName, String newName) {
	// if (manager == null) {
	// return null;
	// }
	// List[] beanlist = manager.getManagedBeans();
	// for (int x = 0; x < beanlist.length; x++) {
	// List beans = beanlist[x];
	// // Iterate through the bean list
	// for (Iterator i = beans.iterator(); i.hasNext();) {
	// Object o = i.next();
	// if (o instanceof ManagedBean) {
	// ManagedBean mbti = (ManagedBean) o;
	// // ManagedBeanNameType mbnt = mbti.getManagedBeanName();
	// String name = mbti.getManagedBeanName().trim();
	// if (name.equals(newName)) {
	// // Increment the name by 1
	// xName++;
	// newName = beanName + xName;
	// // Now check this name
	// return getUniqueName(manager, xName, beanName, newName);
	// }
	// }
	// }
	// }
	// return newName;
	// }
	/**
	 * get the configuration manager for faces-config files in the project
	 * 
	 * @return - configuration manager.
	 */
	public static ConfigurationManager getConfigurationManager(IProject project) {
		if (facesConfigManager == null) {
			facesConfigManager = new ConfigurationManager(project);
		}

		facesConfigManager.loadConfiguration(project);
		// facesConfigManager.deriveManagedBeans();

		return facesConfigManager;
	}

	// public static void addXMLNavigationRuleFromEMFModel(IStructuredModel
	// model,
	// NavigationRuleType emfNavigationRule) {
	// if (model == null || emfNavigationRule == null) {
	// return;
	// }
	//
	// FacesConfigDocumentWrap documentWrap = new FacesConfigDocumentWrap(
	// ((IDOMModel) model).getDocument());
	//
	// NavigationRule xmlNavigationRule = documentWrap.getFacesConfig()
	// .createNavigationRule();
	//
	// Iterator iter = emfNavigationRule.getDisplayName().iterator();
	//
	// while (iter.hasNext()) {
	// DisplayNameType displayName = (DisplayNameType) iter.next();
	// xmlNavigationRule.getElement().createChildNode(
	// IFacesConfigConstants.DISPLAY_NAME, false,
	// displayName.getTextContent(),
	// FacesConfigOrderHelper.getInstance());
	//
	// }
	//
	// iter = emfNavigationRule.getDescription().iterator();
	//
	// while (iter.hasNext()) {
	// DescriptionType description = (DescriptionType) iter.next();
	// xmlNavigationRule.getElement().createChildNode(
	// IFacesConfigConstants.DESCRIPTION, false,
	// description.getValue(),
	// FacesConfigOrderHelper.getInstance());
	// }
	//
	// iter = emfNavigationRule.getIcon().iterator();
	// while (iter.hasNext()) {
	// IconType icon = (IconType) iter.next();
	//
	// NodeWrapUtil iconWrap = new NodeWrapUtil(xmlNavigationRule
	// .getElement().createChildNode(IFacesConfigConstants.ICON,
	// true, null, FacesConfigOrderHelper.getInstance()));
	// if (icon.getLargeIcon() != null) {
	// iconWrap.createChildNode(IFacesConfigConstants.LARGE_ICON,
	// false, icon.getLargeIcon().getTextContent(),
	// FacesConfigOrderHelper.getInstance());
	// }
	//
	// if (icon.getSmallIcon() != null) {
	// iconWrap.createChildNode(IFacesConfigConstants.SMALL_ICON,
	// false, icon.getSmallIcon().getTextContent(),
	// FacesConfigOrderHelper.getInstance());
	// }
	// }
	// FromViewIdType fromViewId = emfNavigationRule.getFromViewId();
	// if (fromViewId != null) {
	// xmlNavigationRule.getElement().createChildNode(
	// IFacesConfigConstants.FROM_VIEW_ID, false,
	// fromViewId.getTextContent(),
	// FacesConfigOrderHelper.getInstance());
	// }
	//
	// iter = emfNavigationRule.getNavigationCase().iterator();
	//
	// while (iter.hasNext()) {
	// NavigationCaseType navigationCase = (NavigationCaseType) iter
	// .next();
	// IDOMNode navigationCaseNode = xmlNavigationRule.getElement()
	// .createChildNode(IFacesConfigConstants.NAVIGATION_CASE,
	// true, null, FacesConfigOrderHelper.getInstance());
	//
	// addNavigationCaseFromEMFModel(navigationCaseNode, navigationCase);
	// }
	// }

	/**
	 * @param xmlNavigationCase
	 * @param emfNavigationCase
	 */
	// private static void addNavigationCaseFromEMFModel(
	// IDOMNode xmlNavigationCase, NavigationCaseType emfNavigationCase) {
	// if (xmlNavigationCase == null || emfNavigationCase == null) {
	// return;
	// }
	//
	// NodeWrapUtil xmlNavigationCaseWrap = new NodeWrapUtil(xmlNavigationCase);
	//
	// Iterator iter = emfNavigationCase.getDisplayName().iterator();
	//
	// while (iter.hasNext()) {
	// DisplayNameType displayName = (DisplayNameType) iter.next();
	// xmlNavigationCaseWrap.createChildNode(
	// IFacesConfigConstants.DISPLAY_NAME, false, displayName
	// .getTextContent(), FacesConfigOrderHelper
	// .getInstance());
	//
	// }
	//
	// iter = emfNavigationCase.getDescription().iterator();
	//
	// while (iter.hasNext()) {
	// DescriptionType description = (DescriptionType) iter.next();
	// xmlNavigationCaseWrap.createChildNode(
	// IFacesConfigConstants.DESCRIPTION, false, description
	// .getValue(), FacesConfigOrderHelper.getInstance());
	// }
	//
	// iter = emfNavigationCase.getIcon().iterator();
	// while (iter.hasNext()) {
	// IconType icon = (IconType) iter.next();
	//
	// NodeWrapUtil iconWrap = new NodeWrapUtil(xmlNavigationCaseWrap
	// .createChildNode(IFacesConfigConstants.ICON, true, null,
	// FacesConfigOrderHelper.getInstance()));
	// if (icon.getLargeIcon() != null) {
	// iconWrap.createChildNode(IFacesConfigConstants.LARGE_ICON,
	// false, icon.getLargeIcon().getTextContent(),
	// FacesConfigOrderHelper.getInstance());
	// }
	//
	// if (icon.getSmallIcon() != null) {
	// iconWrap.createChildNode(IFacesConfigConstants.SMALL_ICON,
	// false, icon.getSmallIcon().getTextContent(),
	// FacesConfigOrderHelper.getInstance());
	// }
	// }
	// FromActionType fromAction = emfNavigationCase.getFromAction();
	// if (fromAction != null) {
	// xmlNavigationCaseWrap.createChildNode(
	// IFacesConfigConstants.FROM_ACTION, false, fromAction
	// .getTextContent(), FacesConfigOrderHelper
	// .getInstance());
	// }
	//
	// FromOutcomeType fromOutcome = emfNavigationCase.getFromOutcome();
	// if (fromOutcome != null) {
	// xmlNavigationCaseWrap.createChildNode(
	// IFacesConfigConstants.FROM_OUTCOME, false, fromOutcome
	// .getTextContent(), FacesConfigOrderHelper
	// .getInstance());
	// }
	//
	// ToViewIdType toViewId = emfNavigationCase.getToViewId();
	// if (toViewId != null) {
	// xmlNavigationCaseWrap.createChildNode(
	// IFacesConfigConstants.TO_VIEW_ID, false, toViewId
	// .getTextContent(), FacesConfigOrderHelper
	// .getInstance());
	// }
	//
	// RedirectType redirect = emfNavigationCase.getRedirect();
	// if (redirect != null) {
	// xmlNavigationCaseWrap.createChildNode(
	// IFacesConfigConstants.REDIRECT, true, null,
	// FacesConfigOrderHelper.getInstance());
	// }
	// }
	/**
	 * To find if a faces config file defined any navigation rule about this jsp
	 * file.
	 * 
	 * @param facesConfigFile
	 * @param jspFileName
	 * @return boolean
	 */
	// private static boolean facesConfigContains(IFile facesConfigFile,
	// String jspFileName) {
	// if (!(facesConfigFile.exists())) {
	// log.info("FacesConfigUtil.FacesConfigFileNotExist", facesConfigFile
	// .getName());
	// return false;
	// }
	//
	// IModelManager modelManager = StructuredModelManager.getModelManager();
	// IStructuredModel model = null;
	//
	// try {
	// model = modelManager.getModelForRead(facesConfigFile);
	// } catch (IOException e) {
	// log.error("FacesConfigUtil.GetFacesConfigModelError", e);
	// return false;
	// } catch (CoreException e) {
	// log.error("FacesConfigUtil.GetFacesConfigModelError", e);
	// return false;
	// }
	//
	// if (model == null) {
	// return false;
	// }
	//
	// Document documentNode = ((IDOMModel) model).getDocument();
	// FacesConfigDocumentWrap documentWrap = new FacesConfigDocumentWrap(
	// documentNode);
	//
	// FacesConfig facesConfig = documentWrap.getFacesConfig();
	// List navigationList = facesConfig.getNavigationList();
	// for (int i = 0, n = navigationList.size(); i < n; i++) {
	// NavigationRule navigationRole = (NavigationRule) navigationList
	// .get(i);
	// if (navigationRuleContains(navigationRole, jspFileName)) {
	// model.releaseFromRead();
	// return true;
	// }
	// }
	//
	// model.releaseFromRead();
	// return false;
	// }
	/**
	 * @param navigationNode
	 * @param jspFileName
	 * @return
	 */
	// private static boolean navigationRuleContains(
	// NavigationRule navigationRule, String jspFileName) {
	// if (null == navigationRule) {
	// return false;
	// }
	// String fromViewIDString = navigationRule.getFromViewId();
	// if (fromViewIDString != null
	// && fromViewIDString.indexOf(jspFileName) >= 0) {
	// return true;
	// }
	//
	// List navigationCaseList = navigationRule.getNavigationCaseList();
	// for (int i = 0, n = navigationCaseList.size(); i < n; i++) {
	// NavigationCase navigationCase = (NavigationCase) navigationCaseList
	// .get(i);
	// if (navigationCaseContains(navigationCase, jspFileName)) {
	// return true;
	// }
	// }
	//
	// return false;
	// }
	/**
	 * @param navigationCaseNode
	 * @param jspFileName
	 * @return
	 */
	// private static boolean navigationCaseContains(
	// NavigationCase navigationCase, String jspFileName) {
	// if (null == navigationCase) {
	// return false;
	// }
	// String toViewIDString = navigationCase.getToViewId();
	// if (toViewIDString != null && toViewIDString.indexOf(jspFileName) >= 0) {
	// return true;
	// }
	//
	// return false;
	// }
	// /**
	// * Use the faces config editor to open a faces config file.
	// *
	// * @param facesConfigFile
	// */
	// private static void openFacesConfigFile(IFile facesConfigFile) {
	// IWorkbenchPage page = PlatformUI.getWorkbench()
	// .getActiveWorkbenchWindow().getActivePage();
	// try {
	// FacesConfigEditor editor = (FacesConfigEditor) IDE
	// .openEditor(page, facesConfigFile,
	// FacesConfigEditor.EDITOR_ID, false);
	// editor.setActiveEditorPage(PageflowEditor.class.getName());
	// } catch (PartInitException e) {
	// log.error("FacesConfigUtil.OpenFacesEditorError", e);
	// }
	//
	// }
}
