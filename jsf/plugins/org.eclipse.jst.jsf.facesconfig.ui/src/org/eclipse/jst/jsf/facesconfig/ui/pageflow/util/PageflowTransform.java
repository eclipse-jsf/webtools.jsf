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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.FC2PFTransformer;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wtp.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.wtp.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.wtp.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.wtp.jsf.facesconfig.emf.IconType;
import org.eclipse.wtp.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.wtp.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.wtp.jsf.facesconfig.emf.ToViewIdType;

/**
 * The tranform between pageflow and navigation rules in faces-config file.
 * 
 * @author Xiao-guang Zhang
 */
public class PageflowTransform {
	private static final String CONDITION_SEPERATOR = ";";

	/** The shared singleton instance. */
	private static PageflowTransform transform;

	/** valid links map */
	private Map mapLinkHelper = null;

	private Map mapUpdatedNavigationCase = null;

	/**
	 * navigation case map to be used to get the PFLinks according to navigation
	 * rule
	 */
	private Map mapLinkNavigationRule = null;

	/** creation factory of faces-config model */
	private FacesConfigFactory facesConfigFactory = null;

	/** pageflow elements creation factory */
	private PageflowFactory pageflowFactory;

	private Pageflow pageflow;

	private PageflowTransform() {
		// private contructor
	}

	/**
	 * Returns the shared singleton instance.
	 */
	public static PageflowTransform getInstance() {
		if (transform == null) {
			transform = new PageflowTransform();
		}
		return transform;
	}

	/**
	 * update the navigation rules in sse model according to pageflow.
	 * 
	 * @param pageflow
	 *            the source;
	 * @param sseModel
	 *            the target.
	 * @author sfshi
	 */
	public void updateFacesConfigModel(Pageflow pageflow,
			IStructuredModel sseModel) {
		// System.out.println("Update faces-config model from page flow");
		// if (sseModel == null || pageflow == null) {
		// return;
		// }
		//
		// FacesConfigDocumentWrap documentWrap = new FacesConfigDocumentWrap(
		// ((IDOMModel) sseModel).getDocument());
		// // Recreate the navigation_rule node according to page flow editor.
		// documentWrap.getFacesConfig().getElement().removeChildNodes(
		// IFacesConfigConstants.NAVIGATION_RULE);
		//
		// List navigationRulesInPageflow =
		// getNavigationRulesInPageflow(pageflow);
		// if (navigationRulesInPageflow != null) {
		// Iterator iter = navigationRulesInPageflow.iterator();
		// while (iter.hasNext()) {
		// NavigationRuleType navigationRule = (NavigationRuleType) iter
		// .next();
		//
		// FacesConfigUtil.addXMLNavigationRuleFromEMFModel(sseModel,
		// navigationRule);
		// }
		// }
	}

	/**
	 * update pageflow model according to the face-config model
	 * 
	 * @param pageflow -
	 *            pageflow model
	 * @param facesConfig -
	 *            face-config model
	 * @author sfshi
	 */
	public boolean updatePageflowModelFromEMF(Pageflow pageflow,
			FacesConfigType emfModel) {
		if (pageflow != null && emfModel != null) {
			this.pageflow = pageflow;

			// List navigationRulesInPageflow =
			// getNavigationRulesInPageflow(pageflow);

			List navigationRulesInFacesconfig = emfModel.getNavigationRule();
			FC2PFTransformer.getInstance().clearCaches();
			// if add new one, we can relayout the current pageflow.
			return updateNavigationRulesInPageflowFromFacesConfig(
					navigationRulesInFacesconfig, pageflow);// navigationRulesInPageflow);
		}
		return false;
	}

	// private List getNavigationRulesInSSEModel_back(IStructuredModel sseModel)
	// {
	// if (sseModel == null) {
	// return new ArrayList();
	// }
	// // FIXME: remove this?
	// return null;
	// // FacesConfigDocumentWrap documentWrap = new FacesConfigDocumentWrap(
	// // ((IDOMModel) sseModel).getDocument());
	// // return documentWrap.getFacesConfig().getNavigationList();
	// }

	/**
	 * Get the navigation rules in pageflow model. Following is the algorithms:
	 * 1. first retrieve and store the link's relationship in a hashmap. The
	 * link's relationship is defined in LinkHelper including previous and
	 * subsequent links 2. tranverse all link to get all valid navigation rules
	 * defined in pageflow model and build a map between PFLinks and navigation
	 * rule, and stored in the mapLinkNavigationRule.
	 */
	// private List getNavigationRulesInPageflow(Pageflow pageflow) {
	// List navigationRulesInPageflow = null;
	// // get valid links, some
	// List lstValidLink = new ArrayList();
	// EList lstLinks = pageflow.getLinks();
	// for (Iterator i = lstLinks.iterator(); i.hasNext();) {
	// PFLink link = (PFLink) i.next();
	// if (PageflowValidation.getInstance().isValidLinkForNavigationRule(
	// link)) {
	// lstValidLink.add(link);
	// }
	// }
	//
	// // create the relationships of valid links, including subsequent and
	// // previous links
	// if (!lstValidLink.isEmpty()) {
	// mapLinkHelper = new HashMap();
	// for (Iterator i = lstValidLink.iterator(); i.hasNext();) {
	// Object o = i.next();
	//
	// LinkHelper linkHelper = null;
	//
	// if (!mapLinkHelper.containsKey(((PFLink) o).getId())) {
	// linkHelper = new LinkHelper((PFLink) o);
	// mapLinkHelper.put(((PFLink) o).getId(), linkHelper);
	// } else {
	// linkHelper = (LinkHelper) mapLinkHelper.get(((PFLink) o)
	// .getId());
	// }
	// setAdjacentLinks(lstValidLink, linkHelper);
	// }
	// }
	// // get the navigation rules based on the sequenced links.
	// if (mapLinkHelper != null && !mapLinkHelper.isEmpty()) {
	// mapLinkNavigationRule = new HashMap();
	// // initialize the navigation rules' list
	// navigationRulesInPageflow = new ArrayList();
	// for (Iterator i = mapLinkHelper.values().iterator(); i.hasNext();) {
	// LinkHelper linkHelper = (LinkHelper) i.next();
	// if (!linkHelper.isVisited()) {
	// PFLink pfLink = (PFLink) (linkHelper.getModel());
	// if (pfLink.getSource() instanceof PFPage
	// && pfLink.getTarget() instanceof PFPage) {
	// addPageToPageNavigationRule(linkHelper,
	// navigationRulesInPageflow);
	// } else if (pfLink.getSource() instanceof PFPage
	// && pfLink.getTarget() instanceof PFAction) {
	// addPageToActionNavigationRule(linkHelper,
	// navigationRulesInPageflow);
	// } else if (pfLink.getSource() instanceof PFAction
	// && pfLink.getTarget() instanceof PFPage) {
	// addActionToPageNavigationRule(linkHelper,
	// navigationRulesInPageflow);
	// }
	// }
	// }
	// }
	// return navigationRulesInPageflow;
	// }
	//
	// /**
	// * check whether these two fromviewid is same or not.
	// *
	// * @param fromViewID1
	// * @param fromViewID2
	// * @return
	// */
	// private boolean hasSameFromViewID(String fromViewID1,
	// FromViewIdType fromViewID2) {
	// String viewID1 = null;
	// if (fromViewID1 == null) {
	// viewID1 = "*";
	// } else {
	// viewID1 = fromViewID1.trim();
	// }
	//
	// String viewID2 = null;
	// if (fromViewID2 == null) {
	// viewID2 = "*";
	// } else {
	// viewID2 = fromViewID2.getTextContent().trim();
	// }
	//
	// if (viewID1.equalsIgnoreCase(viewID2)) {
	// return true;
	// }
	// return false;
	// }
	/**
	 * check whether these two fromviewid is same or not.
	 * 
	 * @param fromViewID1
	 * @param fromViewID2
	 * @return
	 */
	private boolean hasSameFromViewID(FromViewIdType fromViewID1,
			FromViewIdType fromViewID2) {
		String viewID1 = null;
		if (fromViewID1 == null) {
			viewID1 = "*";
		} else {
			viewID1 = fromViewID1.getTextContent().trim();
		}

		String viewID2 = null;
		if (fromViewID2 == null) {
			viewID2 = "*";
		} else {
			viewID2 = fromViewID2.getTextContent().trim();
		}

		if (viewID1.equalsIgnoreCase(viewID2)) {
			return true;
		}
		return false;
	}

	/**
	 * Update the navigation case in second model with the same from-view-id,
	 * from-action and from-outcome of navigation case in first model.
	 * 
	 * @param navigationRuleFC -
	 *            navigation rule in first model
	 * @param navigationCaseFC -
	 *            navigation case of the rule in first model
	 * @param navigationRulePF -
	 *            navigation rule in second model, which may have multiple
	 *            navigation cases.
	 * @return - if updated the navigation case with same condition, return
	 *         true;
	 * @author sfshi
	 */
	// private boolean updatePageflowWithSameCondition(
	// NavigationRuleType navigationRuleFC,
	// NavigationCaseType navigationCaseFC,
	// NavigationRuleType navigationRulePF) {
	// if (hasSameFromViewID(navigationRuleFC.getFromViewId(),
	// navigationRulePF.getFromViewId())) {
	// for (Iterator iterRulePF = navigationRulePF.getNavigationCase()
	// .iterator(); iterRulePF.hasNext();) {
	// NavigationCaseType navigationCasePF = (NavigationCaseType) iterRulePF
	// .next();
	// // FIXME: if in one navigation rule has more than one navigation
	// // cases with same conditions.
	// // if has the same condition, update it.
	// if (hasSameCondition(navigationCasePF, navigationCaseFC)) {
	// LinkNavigationRule linkRule = (LinkNavigationRule) mapLinkNavigationRule
	// .get(navigationCasePF);
	//
	// PFLink firstLink = linkRule.getFirstLink();
	// PFLink secondLink = linkRule.getSecondLink();
	//
	// // Page-action-Page form
	// if (firstLink != null && secondLink != null) {
	// String toViewID = navigationCaseFC.getToViewId()
	// .getTextContent();
	// if (!((PFPage) secondLink.getTarget()).getPath()
	// .equalsIgnoreCase(toViewID)) {
	// String secondFromOutcome = null;
	// if (navigationCaseFC.getFromOutcome() != null
	// && navigationCaseFC.getFromOutcome()
	// .getTextContent().length() > 0) {
	// secondFromOutcome = navigationCaseFC
	// .getFromOutcome().getTextContent();
	// }
	// PFLink newLink = createPFLink(secondFromOutcome);
	//
	// PageflowNode target = findPFPage(toViewID);
	// if (target == null) {
	// target = createPFPage(toViewID);
	// }
	// updatePageflowTargetPage((PFPage) target,
	// navigationCaseFC);
	//
	// PageflowNode source = secondLink.getSource();
	// // remove the old link
	// source.getOutlinks().remove(secondLink);
	// secondLink.getTarget().getInlinks().remove(
	// secondLink);
	// source.getPageflow().getLinks().remove(secondLink);
	// secondLink = null;
	//
	// // create a new link
	// newLink.setSource(source);
	// newLink.setTarget(target);
	//
	// // updatePageflowLink(newLink, navigationRuleFC,
	// // navigationCaseFC);
	// }
	// }
	// // Page-Page form
	// else if (firstLink != null && secondLink == null) {
	// String toViewID = navigationCaseFC.getToViewId()
	// .getTextContent();
	// if (!((PFPage) firstLink.getTarget()).getPath()
	// .equalsIgnoreCase(toViewID)) {
	// String fromOutcome = null;
	// if (navigationCaseFC.getFromOutcome() != null) {
	// fromOutcome = navigationCaseFC.getFromOutcome()
	// .getTextContent();
	// }
	// PFLink newLink = createPFLink(fromOutcome);
	// PageflowNode target = findPFPage(toViewID);
	// if (target == null) {
	// target = createPFPage(toViewID);
	// }
	// updatePageflowTargetPage((PFPage) target,
	// navigationCaseFC);
	//
	// PageflowNode source = firstLink.getSource();
	// // remove the old link
	// source.getOutlinks().remove(firstLink);
	// firstLink.getTarget().getInlinks()
	// .remove(firstLink);
	// source.getPageflow().getLinks().remove(firstLink);
	// firstLink = null;
	//
	// // create a new link
	// newLink.setSource(source);
	// newLink.setTarget(target);
	//
	// // updatePageflowLink(newLink, navigationRuleFC,
	// // navigationCaseFC);
	// }
	// }
	// mapUpdatedNavigationCase.put(navigationCasePF,
	// navigationCasePF);
	// return true;
	// }
	// }
	// }
	//
	// return false;
	// }
	/**
	 * Update source PFPage with Faces-config model.
	 * 
	 * @param sourcePage
	 * @param navigationRuleFC
	 * @author sfshi
	 */
	public void updatePageflowSourcePage(PFPage sourcePage,
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
	public void updatePageflowTargetPage(PFPage targetPage,
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
	 * check whether these two toviewid are same or not.
	 * 
	 * @param toViewID1
	 * @param toViewID2
	 * @return
	 * @author sfshi
	 */
	private boolean hasSameToViewID(ToViewIdType toViewID1, String toViewID2) {
		String viewID1 = null;
		if (toViewID1 == null) {
			viewID1 = "";
		} else {
			viewID1 = toViewID1.getTextContent().trim();
		}

		String viewID2 = null;
		if (toViewID2 == null) {
			viewID2 = "";
		} else {
			viewID2 = toViewID2.trim();
		}

		if (viewID1.equalsIgnoreCase(viewID2)) {
			return true;
		}
		return false;
	}

	/**
	 * check whether these two toviewid are same or not.
	 * 
	 * @param toViewID1
	 * @param toViewID2
	 * @return
	 */
	private boolean hasSameToViewID(ToViewIdType toViewID1,
			ToViewIdType toViewID2) {
		String viewID1 = null;
		if (toViewID1 == null) {
			viewID1 = "";
		} else {
			viewID1 = toViewID1.getTextContent().trim();
		}

		String viewID2 = null;
		if (toViewID2 == null) {
			viewID2 = "";
		} else {
			viewID2 = toViewID2.getTextContent().trim();
		}

		if (viewID1.equalsIgnoreCase(viewID2)) {
			return true;
		}
		return false;
	}

	/**
	 * Check the same navigation case based on from-view-id, from-action and
	 * from-outcome comparasion
	 * 
	 * @param navigationCaseFirst -
	 *            navigation case in first model
	 * @param navigationCaseSecond -
	 *            navigation case in second model.
	 * @return - true if has same condition.
	 * @author sfshi
	 */
	// private boolean hasSameCondition(NavigationCaseType navigationCaseFirst,
	// NavigationCaseType navigationCaseSecond) {
	// FromActionType actionFirst = navigationCaseFirst.getFromAction();
	// FromOutcomeType outcomeFirst = navigationCaseFirst.getFromOutcome();
	// String actionSecond = navigationCaseSecond.getFromAction()
	// .getTextContent();
	// String outcomeSecond = navigationCaseSecond.getFromOutcome()
	// .getTextContent();
	//
	// // that is a page-page rule, have no condition.
	// if (actionFirst == null && actionSecond == null
	// && actionSecond.length() > 0 && outcomeFirst == null
	// && outcomeSecond == null && outcomeSecond.length() > 0) {
	// if (hasSameToViewID(navigationCaseFirst.getToViewId(),
	// navigationCaseSecond.getToViewId())) {
	// return true;
	// }
	// return false;
	// }
	//
	// String firstCondition = "";
	// String secondCondition = "";
	// // get the condition string for the first navigation case
	// if (actionFirst != null) {
	// firstCondition = firstCondition
	// + actionFirst.getTextContent().trim() + CONDITION_SEPERATOR;
	// } else {
	// firstCondition = firstCondition + CONDITION_SEPERATOR;
	// }
	//
	// if (outcomeFirst != null) {
	// firstCondition = firstCondition
	// + outcomeFirst.getTextContent().trim()
	// + CONDITION_SEPERATOR;
	// } else {
	// firstCondition = firstCondition + CONDITION_SEPERATOR;
	// }
	//
	// // get the condition string for the second navigation case
	// if (actionSecond != null && actionSecond.length() > 0) {
	// secondCondition = secondCondition + actionSecond.trim()
	// + CONDITION_SEPERATOR;
	// } else {
	// secondCondition = secondCondition + CONDITION_SEPERATOR;
	// }
	//
	// if (outcomeSecond != null && outcomeSecond.length() > 0) {
	// secondCondition = secondCondition + outcomeSecond.trim()
	// + CONDITION_SEPERATOR;
	// } else {
	// secondCondition = secondCondition + CONDITION_SEPERATOR;
	// }
	//
	// if (firstCondition.equalsIgnoreCase(secondCondition)) {
	// return true;
	// }
	// return false;
	// }
	//
	// /**
	// * set the link's previous and subsequent links,i.e., Tranverse all
	// * validLinks to get the previous and subsequent links for the current
	// link.
	// *
	// * @param lstValidLink -
	// * all valid link.
	// * @param linkHelper -
	// * current link helper
	// */
	// private void setAdjacentLinks(List lstValidLink, LinkHelper linkHelper) {
	// /**
	// * FIXME:can improve the effeciency of algorithms by marking the linked
	// * links.
	// */
	// Iterator i = lstValidLink.iterator();
	// while (i.hasNext()) {
	// Object o = i.next();
	// if (o instanceof PFLink && o != linkHelper.getModel()) {
	// // get the link's subsequent links
	// if (((PFLink) (linkHelper.getModel())).getTarget() == ((PFLink) o)
	// .getSource()) {
	// LinkHelper validSubLink = null;
	// if (!mapLinkHelper.containsKey(((PFLink) o).getId())) {
	// validSubLink = new LinkHelper((PFLink) o);
	// mapLinkHelper.put(((PFLink) o).getId(), validSubLink);
	// } else {
	// validSubLink = (LinkHelper) mapLinkHelper
	// .get(((PFLink) o).getId());
	// }
	// linkHelper.addSubsequentLink(validSubLink);
	// }
	// // get the link's previous links
	// else if (((PFLink) (linkHelper.getModel())).getSource() == ((PFLink) o)
	// .getTarget()) {
	// LinkHelper validPrevLink = null;
	// if (!mapLinkHelper.containsKey(((PFLink) o).getId())) {
	// validPrevLink = new LinkHelper((PFLink) o);
	// mapLinkHelper.put(((PFLink) o).getId(), validPrevLink);
	// } else {
	// validPrevLink = (LinkHelper) mapLinkHelper
	// .get(((PFLink) o).getId());
	// }
	// linkHelper.addPreviousLink(validPrevLink);
	// }
	// }
	// }
	// }
	/**
	 * get the model creation factory of faces-config 1.0
	 * 
	 * @return - the model creation factory of faces-config 1.0
	 */
	private FacesConfigFactory getFacesConfigFactory() {
		if (facesConfigFactory == null) {
			facesConfigFactory = FacesConfigFactory.eINSTANCE;
		}
		return facesConfigFactory;
	}

	/**
	 * get or create a navigation rule
	 * 
	 * @param fromViewIdPath
	 * @param navigationRulesInPageflow
	 * @return
	 */
	private NavigationRuleType getNavigationRule(String fromViewIdPath,
			List navigationRulesInPageflow) {
		// create or get a new navigation rule
		NavigationRuleType navigationRule = null;

		FacesConfigFactory factory = getFacesConfigFactory();
		// set the from-view-id
		FromViewIdType fromViewId = factory.createFromViewIdType();
		fromViewId.setTextContent(fromViewIdPath);
		// found the navigation rule with the same from-view-id
		boolean bFound = false;
		for (Iterator iter = navigationRulesInPageflow.iterator(); iter
				.hasNext()
				&& !bFound;) {
			NavigationRuleType rule = (NavigationRuleType) iter.next();
			if (hasSameFromViewID(fromViewId, rule.getFromViewId())) {
				navigationRule = rule;
				bFound = true;
			}
		}
		// if not found, create a new one.
		if (!bFound) {
			// create a new navigation rule
			navigationRule = factory.createNavigationRuleType();

			// set the from-view-id
			FromViewIdType fromViewid = factory.createFromViewIdType();
			fromViewid.setTextContent(fromViewIdPath);
			navigationRule.setFromViewId(fromViewid);

			navigationRulesInPageflow.add(navigationRule);
		}
		return navigationRule;
	}

	/**
	 * change the page-to-page link to a navigation rule.
	 * 
	 * @param linkHelper -
	 *            page-to-page link
	 */
	// private void addPageToPageNavigationRule(LinkHelper linkHelper,
	// List navigationRulesInPageflow) {
	// FacesConfigFactory factory = getFacesConfigFactory();
	//
	// PFLink pfLink = (PFLink) linkHelper.getModel();
	// PFPage pageSource = (PFPage) pfLink.getSource();
	// PFPage pageTarget = (PFPage) pfLink.getTarget();
	//
	// // create or get a navigation rule
	// NavigationRuleType navigationRule = getNavigationRule(pageSource
	// .getPath(), navigationRulesInPageflow);
	//
	// // Set the navigation rule's discriptin
	// if (pageSource.getComment() != null) {
	// DescriptionType description = factory.createDescriptionType();
	// description.setTextContent(pageSource.getComment());
	// navigationRule.getDescription().clear();
	// navigationRule.getDescription().add(description);
	// }
	// // set the navigation rule's icon
	// if (pageSource.getLargeicon() != null
	// || pageSource.getSmallicon() != null) {
	// IconType icon = factory.createIconType();
	// if (pageSource.getLargeicon() != null) {
	// LargeIconType largeicon = factory.createLargeIconType();
	// largeicon.setTextContent(pageSource.getLargeicon());
	// icon.setLargeIcon(largeicon);
	// }
	//
	// if (pageSource.getSmallicon() != null) {
	// SmallIconType smallicon = factory.createSmallIconType();
	// smallicon.setTextContent(pageSource.getSmallicon());
	// icon.setSmallIcon(smallicon);
	// }
	// navigationRule.getIcon().clear();
	// navigationRule.getIcon().add(icon);
	// }
	//
	// // set the navigation display-name
	// if (pageSource.getName() != null) {
	// DisplayNameType displayName = factory.createDisplayNameType();
	// displayName.setTextContent(pageSource.getName());
	// navigationRule.getDisplayName().clear();
	// navigationRule.getDisplayName().add(displayName);
	// }
	//
	// // create a new navigation case
	// NavigationCaseType navigationCase = factory.createNavigationCaseType();
	//
	// // create a new from-outcome for navigation case
	// if (pfLink.getOutcome() != null) {
	// FromOutcomeType fromOutcome = factory.createFromOutcomeType();
	// fromOutcome.setTextContent(pfLink.getOutcome());
	// navigationCase.setFromOutcome(fromOutcome);
	// }
	//
	// // Set the to-view-id of navigation case
	// ToViewIdType toViewid = factory.createToViewIdType();
	// toViewid.setTextContent(pageTarget.getPath());
	// navigationCase.setToViewId(toViewid);
	//
	// // Set the icon of navigation case
	// if (pageTarget.getLargeicon() != null
	// || pageTarget.getSmallicon() != null) {
	// IconType icon = factory.createIconType();
	// if (pageTarget.getLargeicon() != null) {
	// LargeIconType largeicon = factory.createLargeIconType();
	// largeicon.setTextContent(pageTarget.getLargeicon());
	// icon.setLargeIcon(largeicon);
	// }
	//
	// if (pageTarget.getSmallicon() != null) {
	// SmallIconType smallicon = factory.createSmallIconType();
	// smallicon.setTextContent(pageTarget.getSmallicon());
	// icon.setSmallIcon(smallicon);
	// }
	// navigationCase.getIcon().add(icon);
	// }
	//
	// // Set the navigation case's description
	// if (pageTarget.getComment() != null) {
	// DescriptionType description = factory.createDescriptionType();
	// description.setTextContent(pageTarget.getComment());
	// navigationCase.getDescription().add(description);
	// }
	//
	// // set the navigation display-name
	// if (pageTarget.getName() != null) {
	// DisplayNameType displayName = factory.createDisplayNameType();
	// displayName.setTextContent(pageTarget.getName());
	// navigationCase.getDisplayName().add(displayName);
	// }
	//
	// // Set the navigation case's redirect
	// if (pfLink.isRedirect()) {
	// RedirectType redirect = factory.createRedirectType();
	// navigationCase.setRedirect(redirect);
	// }
	//
	// // add the navigation case to the navigation rule
	// navigationRule.getNavigationCase().add(navigationCase);
	//
	// linkHelper.setVisit(true);
	//
	// LinkNavigationRule linkRule = new LinkNavigationRule();
	// linkRule.setNavigationRule(navigationRule, navigationCase);
	// linkRule.setLinks(pfLink, null);
	// mapLinkNavigationRule.put(navigationCase, linkRule);
	//
	// }
	/**
	 * change the page-to-action link to a navigation rule.
	 * 
	 * @param linkHelper -
	 *            page-to-action link
	 */
	// private void addPageToActionNavigationRule(LinkHelper linkHelper,
	// List navigationRulesInPageflow) {
	// if (!linkHelper.isVisited()) {
	// if (!linkHelper.isVisited()) {
	// List subsequentLinks = linkHelper.getSubsequentLinks();
	// if (subsequentLinks != null) {
	// for (Iterator iterSub = subsequentLinks.iterator(); iterSub
	// .hasNext();) {
	// LinkHelper subLink = (LinkHelper) iterSub.next();
	// if (!subLink.isVisited()) {
	// addNewPageActionPageNavigationRule(
	// (PFLink) linkHelper.getModel(),
	// (PFLink) subLink.getModel(),
	// navigationRulesInPageflow);
	// subLink.setVisit(true);
	// }
	// }
	// }
	// linkHelper.setVisit(true);
	// }
	// }
	// }
	/**
	 * change the page-to-action link to a navigation rule.
	 * 
	 * @param linkHelper -
	 *            action-to-page link
	 */
	// private void addActionToPageNavigationRule(LinkHelper linkHelper,
	// List navigationRulesInPageflow) {
	// if (!linkHelper.isVisited()) {
	// List prevLinks = linkHelper.getPreviousLink();
	// if (prevLinks != null) {
	// for (Iterator iterSub = prevLinks.iterator(); iterSub.hasNext();) {
	// LinkHelper prevLink = (LinkHelper) iterSub.next();
	// addNewPageActionPageNavigationRule((PFLink) prevLink
	// .getModel(), (PFLink) linkHelper.getModel(),
	// navigationRulesInPageflow);
	// }
	// } else // only action-page's rule.
	// {
	// addNewActionPageNavigationRule((PFLink) linkHelper.getModel(),
	// navigationRulesInPageflow);
	//
	// }
	// linkHelper.setVisit(true);
	// }
	// }
	/**
	 * convert two connected links in the form of page-action, action-page into
	 * a new navigation rule
	 * 
	 * @param pfLinkPrev -
	 *            page-action link
	 * @param pfLink -
	 *            action-page link
	 */
	// private void addNewActionPageNavigationRule(PFLink pfLink,
	// List navigationRulesInPageflow) {
	// FacesConfigFactory factory = getFacesConfigFactory();
	//
	// PFPage pageTarget = (PFPage) pfLink.getTarget();
	//
	// // create a new navigation rule
	// NavigationRuleType navigationRule = getNavigationRule("*",
	// navigationRulesInPageflow);
	//
	// // create a new navigation case
	// NavigationCaseType navigationCase = factory.createNavigationCaseType();
	//
	// if (action.getAction() != null) {
	// FromActionType fromAction = factory.createFromActionType();
	// fromAction.setTextContent(action.getAction());
	// navigationCase.setFromAction(fromAction);
	// }
	//
	// // create a new from-outcome for navigation case
	// if (pfLink.getOutcome() != null) {
	// FromOutcomeType fromOutcome = factory.createFromOutcomeType();
	// fromOutcome.setTextContent(pfLink.getOutcome());
	// navigationCase.setFromOutcome(fromOutcome);
	// }
	//
	// // set the to-view-id of navigation case
	// ToViewIdType toViewid = factory.createToViewIdType();
	// toViewid.setTextContent(pageTarget.getPath());
	// navigationCase.setToViewId(toViewid);
	//
	// // Set the icon of navigation case
	// if (pageTarget.getLargeicon() != null
	// || pageTarget.getSmallicon() != null) {
	// IconType icon = factory.createIconType();
	// if (pageTarget.getLargeicon() != null) {
	// LargeIconType largeicon = factory.createLargeIconType();
	// largeicon.setTextContent(pageTarget.getLargeicon());
	// icon.setLargeIcon(largeicon);
	// }
	//
	// if (pageTarget.getSmallicon() != null) {
	// SmallIconType smallicon = factory.createSmallIconType();
	// smallicon.setTextContent(pageTarget.getSmallicon());
	// icon.setSmallIcon(smallicon);
	// }
	// navigationCase.getIcon().add(icon);
	// }
	//
	// // Set the navigation case's description
	// if (pageTarget.getComment() != null) {
	// DescriptionType description = factory.createDescriptionType();
	// description.setTextContent(pageTarget.getComment());
	// navigationCase.getDescription().add(description);
	// }
	//
	// // set the navigation case display-name
	// if (pageTarget.getName() != null) {
	// DisplayNameType displayName = factory.createDisplayNameType();
	// displayName.setTextContent(pageTarget.getName());
	// navigationCase.getDisplayName().add(displayName);
	// }
	//
	// // Set the navigation case's redirect
	// if (pfLink.isRedirect()) {
	// RedirectType redirect = factory.createRedirectType();
	// navigationCase.setRedirect(redirect);
	// }
	//
	// // add the navigation case to the navigation rule
	// navigationRule.getNavigationCase().add(navigationCase);
	//
	// LinkNavigationRule linkRule = new LinkNavigationRule();
	// linkRule.setNavigationRule(navigationRule, navigationCase);
	// linkRule.setLinks(pfLink, null);
	// mapLinkNavigationRule.put(navigationCase, linkRule);
	// }
	/**
	 * convert two connected links in the form of page-action, action-page into
	 * a new navigation rule
	 * 
	 * @param pfLinkPrev -
	 *            page-action link
	 * @param pfLinkNext -
	 *            action-page link
	 */
	// private void addNewPageActionPageNavigationRule(PFLink pfLinkPrev,
	// PFLink pfLinkNext, List navigationRulesInPageflow) {
	// FacesConfigFactory factory = getFacesConfigFactory();
	//
	// PFPage pageSource = (PFPage) pfLinkPrev.getSource();
	// PFAction action = (PFAction) pfLinkPrev.getTarget();
	// PFPage pageTarget = (PFPage) pfLinkNext.getTarget();
	//
	// // create a new navigation rule
	// NavigationRuleType navigationRule = getNavigationRule(pageSource
	// .getPath(), navigationRulesInPageflow);
	//
	// // set the navigation rule's discriptin
	// if (pageSource.getComment() != null) {
	// DescriptionType description = factory.createDescriptionType();
	// description.setTextContent(pageSource.getComment());
	// navigationRule.getDescription().clear();
	// navigationRule.getDescription().add(description);
	// }
	// // set the navigation rule's icon
	// if (pageSource.getLargeicon() != null
	// || pageSource.getSmallicon() != null) {
	// IconType icon = factory.createIconType();
	// if (pageSource.getLargeicon() != null) {
	// LargeIconType largeicon = factory.createLargeIconType();
	// largeicon.setTextContent(pageSource.getLargeicon());
	// icon.setLargeIcon(largeicon);
	// }
	//
	// if (pageSource.getSmallicon() != null) {
	// SmallIconType smallicon = factory.createSmallIconType();
	// smallicon.setTextContent(pageSource.getSmallicon());
	// icon.setSmallIcon(smallicon);
	// }
	// navigationRule.getIcon().clear();
	// navigationRule.getIcon().add(icon);
	// }
	//
	// // set the navigation display-name
	// if (pageSource.getName() != null) {
	// DisplayNameType displayName = factory.createDisplayNameType();
	// displayName.setTextContent(pageSource.getName());
	// navigationRule.getDisplayName().clear();
	// navigationRule.getDisplayName().add(displayName);
	// }
	//
	// // create a new navigation case
	// NavigationCaseType navigationCase = factory.createNavigationCaseType();
	//
	// if (action.getAction() != null) {
	// FromActionType fromAction = factory.createFromActionType();
	// fromAction.setTextContent(action.getAction());
	// navigationCase.setFromAction(fromAction);
	// }
	//
	// // create a new from-outcome for navigation case
	// if (pfLinkNext.getOutcome() != null) {
	// FromOutcomeType fromOutcome = factory.createFromOutcomeType();
	// fromOutcome.setTextContent(pfLinkNext.getOutcome());
	// navigationCase.setFromOutcome(fromOutcome);
	// }
	//
	// // set the to-view-id of navigation case
	// ToViewIdType toViewid = factory.createToViewIdType();
	// toViewid.setTextContent(pageTarget.getPath());
	// navigationCase.setToViewId(toViewid);
	//
	// // set the icon of navigation case
	// if (pageTarget.getLargeicon() != null
	// || pageTarget.getSmallicon() != null) {
	// IconType icon = factory.createIconType();
	// if (pageTarget.getLargeicon() != null) {
	// LargeIconType largeicon = factory.createLargeIconType();
	// largeicon.setTextContent(pageTarget.getLargeicon());
	// icon.setLargeIcon(largeicon);
	// }
	//
	// if (pageTarget.getSmallicon() != null) {
	// SmallIconType smallicon = factory.createSmallIconType();
	// smallicon.setTextContent(pageTarget.getSmallicon());
	// icon.setSmallIcon(smallicon);
	// }
	// navigationCase.getIcon().add(icon);
	// }
	//
	// // set the navigation case display-name
	// if (pageTarget.getName() != null) {
	// DisplayNameType displayName = factory.createDisplayNameType();
	// displayName.setTextContent(pageTarget.getName());
	// navigationCase.getDisplayName().add(displayName);
	// }
	//
	// // Set the navigation case's description
	// if (pageTarget.getComment() != null) {
	// DescriptionType description = factory.createDescriptionType();
	// description.setTextContent(pageTarget.getComment());
	// navigationCase.getDescription().add(description);
	// }
	//
	// // Set the navigation case's redirect
	// if (pfLinkNext.isRedirect()) {
	// RedirectType redirect = factory.createRedirectType();
	// navigationCase.setRedirect(redirect);
	// }
	//
	// // add the navigation case to the navigation rule
	// navigationRule.getNavigationCase().add(navigationCase);
	//
	// LinkNavigationRule linkRule = new LinkNavigationRule();
	// linkRule.setNavigationRule(navigationRule, navigationCase);
	// linkRule.setLinks(pfLinkPrev, pfLinkNext);
	// mapLinkNavigationRule.put(navigationCase, linkRule);
	// }
	/**
	 * merge the two navigation rules list between pageflow and face-config
	 * file. merge rule: 1. if the from-view-id, from-action, and from-outcome
	 * are same, they are the same navigation case, then the navigation case in
	 * face-config file should be updated by one in the pageflow. 2. otherwise
	 * the new navigation case should be created.
	 * 
	 * FIXME: Not merge, only update page flow from faces-config.
	 * 
	 * @param rulesFC -
	 *            navigation rules in faces-config.
	 * @param navigationRulesInPageflow -
	 *            navigation rules in pageflow model.
	 * 
	 * @return boolean - whether add a new rule or not.
	 */
	private boolean updateNavigationRulesInPageflowFromFacesConfig(
			List rulesFC, Pageflow pageflow) {
		boolean isNew = false;
		// if the faces-config file is empty, the links should be removed.
		if (rulesFC == null || rulesFC.isEmpty()) {
			for (Iterator iterLink = pageflow.getLinks().iterator(); iterLink
					.hasNext();) {
				PFLink link = (PFLink) iterLink.next();
				link.getSource().getOutlinks().remove(link);
				link.getTarget().getInlinks().remove(link);
			}
			pageflow.getLinks().clear();
		} else {
			mapUpdatedNavigationCase = new HashMap();
			// Rule - Cases
			for (Iterator iterRulesFC = rulesFC.iterator(); iterRulesFC
					.hasNext();) {
				NavigationRuleType ruleFC = (NavigationRuleType) iterRulesFC
						.next();

				List casesFC = ruleFC.getNavigationCase();
				for (Iterator iterCasesFC = casesFC.iterator(); iterCasesFC
						.hasNext();) {
					NavigationCaseType navigationCaseFC = (NavigationCaseType) iterCasesFC
							.next();
					isNew |= FC2PFTransformer.getInstance().createPFElements(
							pageflow, navigationCaseFC);
				}
			}
		}
		isNew |= cleanPageflowNavigationRule(pageflow);
		return isNew;
	}

	private boolean cleanPageflowNavigationRule(Pageflow pageflow) {
		boolean dirty = false;
		List links = pageflow.getLinks();
		Iterator linksIterator = links.iterator();
		while (linksIterator.hasNext()) {
			PFLink link = (PFLink) linksIterator.next();
			if (link.getTarget() instanceof PFPage) {
				if (!FC2PFTransformer.getInstance().getMapLinks2Cases()
						.containsKey(link)) {
					linksIterator.remove();
					if (link.getSource() != null) {
						link.getSource().getOutlinks().remove(link);
					}
					if (link.getTarget() != null) {
						link.getTarget().getInlinks().remove(link);
					}
					dirty = true;
				}
			}
		}
		return dirty;
	}

	/**
	 * merge the two navigation rules list between pageflow and face-config
	 * file. merge rule: 1. if the from-view-id, from-action, and from-outcome
	 * are same, they are the same navigation case, then the navigation case in
	 * face-config file should be updated by one in the pageflow. 2. otherwise
	 * the new navigation case should be created.
	 * 
	 * @param navigationRulesInFacesconfig -
	 *            navigation rules in faces-config.
	 * @param navigationRulesInPageflow -
	 *            navigation rules in pageflow model.
	 * 
	 * @return boolean - whether add a new rule or not.
	 */
	// private boolean updateNavigationRulesInPageflow(
	// List navigationRulesInFacesconfig, List navigationRulesInPageflow) {
	// // if the faces-config file is empty, the links should be removed.
	// if (navigationRulesInFacesconfig == null
	// || navigationRulesInFacesconfig.isEmpty()) {
	// for (Iterator iterLink = pageflow.getLinks().iterator(); iterLink
	// .hasNext();) {
	// PFLink link = (PFLink) iterLink.next();
	// link.getSource().getOutlinks().remove(link);
	// link.getTarget().getInlinks().remove(link);
	// }
	// pageflow.getLinks().clear();
	// return false;
	// }
	//
	// // whether add a new rule or not
	// boolean bAddNewRule = false;
	//
	// // The new navigation rules defined in faces-config model, but not in
	// // pageflow model.
	// List newNavigationRules = null;
	//
	// // if the navigation rules in pageflow is empty, append all navigation
	// // rules defined in faces-config file.
	// if (navigationRulesInPageflow == null
	// || navigationRulesInPageflow.isEmpty()) {
	// newNavigationRules = new ArrayList();
	// newNavigationRules.addAll(navigationRulesInFacesconfig);
	// } else if (navigationRulesInFacesconfig != null
	// && !navigationRulesInFacesconfig.isEmpty()) {
	// mapUpdatedNavigationCase = new HashMap();
	// // otherwise, merge these two lists
	// for (Iterator iterRulesFC = navigationRulesInFacesconfig.iterator();
	// iterRulesFC
	// .hasNext();) {
	// NavigationRuleType navigationRuleFC = (NavigationRuleType) iterRulesFC
	// .next();
	//
	// List casesFCList = navigationRuleFC.getNavigationCase();
	// for (Iterator iterCasesFC = casesFCList.iterator(); iterCasesFC
	// .hasNext();) {
	// NavigationCaseType caseFC = (NavigationCaseType) iterCasesFC
	// .next();
	// FC2PFTransformer.getInstance().createPFElements(pageflow,
	// caseFC);
	// // for (Iterator iterRulesPF = navigationRulesInPageflow
	// // .iterator(); iterRulesPF.hasNext();) {
	// // NavigationRuleType navigationRulePF =
	// // (NavigationRuleType) iterRulesPF
	// // .next();
	// // // update the navigation case with same condition,
	// // // if have same navigation case, remove this
	// // // navigationCase in faces-config model.
	// // if (updatePageflowWithSameCondition(navigationRuleFC,
	// // navigationCaseFC, navigationRulePF)) {
	// // iterCasesFC.remove();
	// // break;
	// // }
	// // }
	// }
	// // if the navigation rule in faces-config is not empty, should
	// // add a new navigation rule in pageflow model
	// if (!casesFCList.isEmpty()) {
	// if (newNavigationRules == null) {
	// newNavigationRules = new ArrayList();
	// }
	// newNavigationRules.add(navigationRuleFC);
	// }
	// }
	// // update the pageflow according to merge status,
	// // i.e., if the navigation case in page flow is not updated by the
	// // faces-config file,
	// // the navigation case should be removed from pageflow.
	// for (Iterator iterRulesPF = navigationRulesInPageflow.iterator();
	// iterRulesPF
	// .hasNext();) {
	// NavigationRuleType navigationRulePF = (NavigationRuleType) iterRulesPF
	// .next();
	//
	// for (Iterator iterCasePF = navigationRulePF.getNavigationCase()
	// .iterator(); iterCasePF.hasNext();) {
	// NavigationCaseType navigationCasePF = (NavigationCaseType) iterCasePF
	// .next();
	// if (!mapUpdatedNavigationCase.containsKey(navigationCasePF)) {
	// removeNavigationCaseFromPageflow(navigationCasePF);
	// }
	// }
	// }
	// }
	// // append the new navigation rules to the face-config.xml
	// if (newNavigationRules != null && !newNavigationRules.isEmpty()) {
	// bAddNewRule = true;
	// addNewNavigationRulesToPageflow(newNavigationRules);
	// }
	// return bAddNewRule;
	// }
	/**
	 * add the new navigation rules to pageflow model there are two forms of
	 * navigation rules: Page-action-page, Page-page.
	 * 
	 * @param newNavigationRules -
	 *            list of new navigation rules
	 */
	// private void addNewNavigationRulesToPageflow(List newNavigationRules) {
	// if (newNavigationRules != null && !newNavigationRules.isEmpty()) {
	// for (Iterator iterRule = newNavigationRules.iterator(); iterRule
	// .hasNext();) {
	// NavigationRuleType navigationRule = (NavigationRuleType) iterRule
	// .next();
	// String fromViewID = null;
	// if (navigationRule.getFromViewId() == null) {
	// fromViewID = "*";
	// } else {
	// fromViewID = navigationRule.getFromViewId()
	// .getTextContent();
	// }
	// PFPage sourcePage;
	// sourcePage = (PFPage) findPFPage(fromViewID);
	//
	// if (sourcePage == null) {
	// sourcePage = createPFPage(fromViewID);
	// }
	//
	// updatePageflowSourcePage(sourcePage, navigationRule);
	//
	// for (Iterator iterCase = navigationRule.getNavigationCase()
	// .iterator(); iterCase.hasNext();) {
	// NavigationCaseType navigationCase = (NavigationCaseType) iterCase
	// .next();
	// String fromAction = navigationCase.getFromAction()
	// .getTextContent();
	// // if the fromAction is set, i.e, in the form of
	// // Page-action-Page.
	// PFAction action = null;
	// if (fromAction != null && fromAction.length() > 0) {
	// action = (PFAction) findPFAction(fromAction);
	// if (action == null) {
	// action = createPFAction(fromAction);
	// }
	// }
	// // create the second page
	// PFPage targetPage = null;
	//
	// if (navigationCase.getToViewId() != null) {
	// String toViewID = navigationCase.getToViewId()
	// .getTextContent();
	//
	// targetPage = (PFPage) findPFPage(toViewID);
	// if (targetPage == null) {
	// targetPage = createPFPage(toViewID);
	// }
	// updatePageflowTargetPage(targetPage, navigationCase);
	// }
	//
	// if (targetPage == null) {
	// return;
	// }
	//
	// // if action object is existed, it is in the form of
	// // Page-action-Page
	// if (action != null) {
	// PFLink firstLink;
	// firstLink = findPFLink(sourcePage, action);
	//
	// if (firstLink == null) {
	// firstLink = createPFLink(null);
	// firstLink.setSource(sourcePage);
	// firstLink.setTarget(action);
	// }
	//
	// PFLink secondLink;
	// // secondLink = findPFLink(action, targetPage);
	// //
	// // if (secondLink==null)
	// {
	// String secondFromOutcome = null;
	// if (navigationCase.getFromOutcome() != null) {
	// secondFromOutcome = navigationCase
	// .getFromOutcome().getTextContent();
	// }
	// secondLink = createPFLink(secondFromOutcome);
	// secondLink.setSource(action);
	// secondLink.setTarget(targetPage);
	//
	// // updatePageflowLink(secondLink, navigationRule,
	// // navigationCase);
	// }
	// } else // else, it is in the form of page-page.
	// {
	// PFLink link = null;
	// // link = findPFLink(sourcePage, targetPage);
	// // if (link==null)
	// {
	// String fromOutcome = null;
	// if (navigationCase.getFromOutcome() != null) {
	// fromOutcome = navigationCase.getFromOutcome()
	// .getTextContent();
	// }
	// link = createPFLink(fromOutcome);
	//
	// link.setSource(sourcePage);
	// link.setTarget(targetPage);
	//
	// // updatePageflowLink(link, navigationRule,
	// // navigationCase);
	// }
	// }
	// }
	// }
	// }
	// }
	/**
	 * get the pageflow creation factory
	 * 
	 * @return - pageflow creation factory
	 */
	private PageflowFactory getPageflowFactory() {
		if (pageflowFactory == null) {
			pageflowFactory = PageflowModelManager.getFactory();
		}
		return pageflowFactory;
	}

	/**
	 * Create a new PFPage according to from-view-id
	 * 
	 * @param fromViewID -
	 *            page's from-view-id
	 * @return
	 */
	public PFPage createPFPage(String fromViewID) {
		PFPage page = getPageflowFactory().createPFPage();
		if (fromViewID != null && fromViewID.length() > 0) {
			page.setPath(fromViewID.trim());
			page.setName(WebrootUtil.getPageNameFromWebPath(fromViewID.trim()));
		}
		this.pageflow.getNodes().add(page);
		return page;
	}

	/**
	 * create a new PFAction according to fromAction attribute
	 * 
	 * @param fromAction -
	 *            action's fromAction attribute
	 * @return - a new PFAction object
	 */
	// public PFAction createPFAction(String fromAction) {
	// PFAction action = null;
	// action = getPageflowFactory().createPFAction();
	// if (fromAction != null && fromAction.length() > 0) {
	// action.setAction(fromAction.trim());
	// action.setName(fromAction.trim());
	// }
	// pageflow.getNodes().add(action);
	// return action;
	// }
	/**
	 * create a new PFLink object according to fromOutcome attribute
	 * 
	 * @param fromOutcome -
	 *            PFLink's fromOutcome attribute
	 * @return - new PFLink object
	 */
	public PFLink createPFLink(String fromOutcome) {
		PFLink link = null;
		link = getPageflowFactory().createPFLink();
		if (fromOutcome != null && fromOutcome.length() > 0) {
			link.setOutcome(fromOutcome.trim());
		}
		pageflow.getLinks().add(link);
		return link;
	}

	/**
	 * create a new PFLink object according to fromOutcome attribute
	 * 
	 * @param fromOutcome -
	 *            PFLink's fromOutcome attribute
	 * @return - new PFLink object
	 */
	public PFLink createPFLink(PageflowNode start, PageflowNode target,
			String action, String fromOutcome) {
		PFLink link = null;
		link = createPFLink(fromOutcome);
		link.setFromaction(action);
		link.setSource(start);
		link.setTarget(target);
		if (start != null) {
			start.getOutlinks().add(link);
		}
		if (target != null) {
			target.getInlinks().add(link);
		}
		return link;
	}

	/**
	 * find an existing PFLink object according to source and target
	 * 
	 * @param source -
	 *            source pageflow node
	 * @param target -
	 *            target pageflow node
	 * @return - existing PFLink or null
	 */
	// private PFLink findPFLink(PageflowNode source, PageflowNode target) {
	// PFLink linkFind = null;
	// for (Iterator iter = pageflow.getLinks().iterator(); iter.hasNext();) {
	// PFLink link = (PFLink) iter.next();
	// if (link.getSource() == source && link.getTarget() == target) {
	// linkFind = link;
	// break;
	// }
	// }
	// return linkFind;
	// }
	/**
	 * Find an exisiting PFPage node, if not exising, return null
	 * 
	 * @param fromViewID -
	 *            page's from-view-id
	 * @return - existing PFPage, or null
	 */
	// private PageflowNode findPFPage(String fromViewID) {
	// PageflowNode node = null;
	//
	// for (Iterator iter = pageflow.getNodes().iterator(); iter.hasNext();) {
	// node = (PageflowNode) iter.next();
	// if (node instanceof PFPage) {
	// if (null != ((PFPage) node).getPath()
	// && ((PFPage) node).getPath().trim().equalsIgnoreCase(
	// fromViewID.trim())) {
	// return node;
	// }
	// }
	// }
	// return null;
	// }
	/**
	 * find and get the existing action according to fromAction
	 * 
	 * @param fromAction -
	 *            PFAction's fromAction attribute
	 * @return - an existing action or null
	 */
	// private PageflowNode findPFAction(String fromAction) {
	// PageflowNode node = null;
	//
	// for (Iterator iter = pageflow.getNodes().iterator(); iter.hasNext();) {
	// node = (PageflowNode) iter.next();
	// if (node instanceof PFAction) {
	// if (null != ((PFAction) node).getAction()
	// && ((PFAction) node).getAction().trim()
	// .equalsIgnoreCase(fromAction.trim())) {
	// return node;
	// }
	// }
	// }
	//
	// return null;
	// }
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

		updatePageflowNode(newPageflow, oldPageflow);
	}

	/**
	 * update new pageflow's layout using the existing one.
	 * 
	 * @param newPageflow
	 * @param oldPageflow
	 */
	private static void updatePageflowNode(Pageflow newPageflow,
			Pageflow oldPageflow) {
		if (oldPageflow.getNodes().size() > 0) {
			for (Iterator iter = newPageflow.getNodes().iterator(); iter
					.hasNext();) {
				PageflowNode newNode = (PageflowNode) iter.next();

				updatePageflowNode(newNode, oldPageflow);
			}
		}
	}

	/**
	 * Update pageflow node using the same node
	 * 
	 * @param newNode
	 * @param oldPageflow
	 * @return - the old pageflow node.
	 */
	private static void updatePageflowNode(PageflowNode newNode,
			Pageflow oldPageflow) {
		for (Iterator iter = oldPageflow.getNodes().iterator(); iter.hasNext();) {
			PageflowNode oldNode = (PageflowNode) iter.next();

			// if (oldNode instanceof PFAction && newNode instanceof PFAction) {
			// if (((PFAction) oldNode).getAction() != null
			// && ((PFAction) newNode).getAction() != null
			// && ((PFAction) oldNode).getAction().trim().equals(
			// ((PFAction) newNode).getAction().trim())) {
			// ((PFAction) newNode)
			// .setName(((PFAction) oldNode).getName());
			// ((PFAction) newNode).setComment(((PFAction) oldNode)
			// .getComment());
			// }
			// }
		}
	}

	/**
	 * remove the non-updated navigation case from pageflow model.
	 * 
	 * @param navigationCasePF
	 */
	// private void removeNavigationCaseFromPageflow(
	// NavigationCaseType navigationCasePF) {
	// // get the PFlinks according to navigation case.
	// LinkNavigationRule linkRule = (LinkNavigationRule) mapLinkNavigationRule
	// .get(navigationCasePF);
	//
	// PFLink firstLink = linkRule.getFirstLink();
	// PFLink secondLink = linkRule.getSecondLink();
	//
	// // Page-action-Page form
	// if (firstLink != null && secondLink != null) {
	// firstLink.getSource().getOutlinks().remove(firstLink);
	// firstLink.getTarget().getInlinks().remove(firstLink);
	//
	// secondLink.getSource().getOutlinks().remove(secondLink);
	// secondLink.getTarget().getInlinks().remove(secondLink);
	// pageflow.getLinks().remove(firstLink);
	// pageflow.getLinks().remove(secondLink);
	// }
	// // Page-Page form
	// else if (firstLink != null && secondLink == null) {
	// firstLink.getSource().getOutlinks().remove(firstLink);
	// firstLink.getTarget().getInlinks().remove(firstLink);
	// pageflow.getLinks().remove(firstLink);
	// }
	//
	// }
}
// PageflowTransform
