/**
 * Confidential Property of Sybase, Inc. 
 * (c) Copyright Sybase, Inc. 2004-2006. 
 * All rights reserved.
 */
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.wtp.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.wtp.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.wtp.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.wtp.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.wtp.jsf.facesconfig.emf.IconType;
import org.eclipse.wtp.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.wtp.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.wtp.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.wtp.jsf.facesconfig.emf.RedirectType;
import org.eclipse.wtp.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.wtp.jsf.facesconfig.emf.ToViewIdType;

/**
 * The class to synchronize modification in pageflow for faces config.
 * 
 * @author hmeng
 * 
 */
public class PF2FCSynchronizer implements Adapter {

	Pageflow pageflow;

	FacesConfigType facesConfig;

	public PF2FCSynchronizer(Pageflow pf, FacesConfigType fc) {
		this.pageflow = pf;
		this.facesConfig = fc;
	}

	public Notifier getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAdapterForType(Object type) {
		// TODO Auto-generated method stub
		return false;
	}

	private void addPage(Notification notification, PageflowElement element) {
		// ((EObject) notification.getNewValue()).eAdapters().add(this);
	}

	private String getFromViewID(NavigationRuleType rule) {
		String result = "*";
		if (rule.getFromViewId() != null) {
			result = rule.getFromViewId().getTextContent();
		}
		return result;
	}

	private String getToViewID(NavigationCaseType navCase) {
		String result = "*";
		if (navCase.getToViewId() != null) {
			result = navCase.getToViewId().getTextContent();
		}
		return result;
	}

	/**
	 * All the fromViewID or toViewID
	 * 
	 * @param path
	 * @return
	 */
	public List findFacesPages(String path) {
		List rules = facesConfig.getNavigationRule();
		List result = new ArrayList();
		for (int i = 0; i < rules.size(); i++) {
			NavigationRuleType rule = (NavigationRuleType) rules.get(i);
			String fromViewID = getFromViewID(rule);
			if (fromViewID.equals(path)) {
				result.add(rule.getFromViewId());
			}
			List cases = rule.getNavigationCase();
			for (int j = 0; j < cases.size(); j++) {
				NavigationCaseType navCase = (NavigationCaseType) cases.get(j);
				String toViewID = getToViewID(navCase);
				if (toViewID.equals(path)) {
					result.add(navCase.getToViewId());
				}
			}
		}
		return result;
	}

	/**
	 * Find navigation rule with fromViewID.
	 * 
	 * @param fromID
	 * @return
	 */
	// private List findFacesRules(String fromID) {
	// List rules = facesConfig.getNavigationRule();
	// List result = new ArrayList();
	// for (int i = 0; i < rules.size(); i++) {
	// NavigationRuleType rule = (NavigationRuleType) rules.get(i);
	// String fromViewID = "*";
	// if (rule.getFromViewId() != null) {
	// fromViewID = rule.getFromViewId().getTextContent();
	// }
	// // FIXME: case sensitive?
	// if (fromViewID.equalsIgnoreCase(fromID)) {
	// result.add(rule);
	// }
	// }
	// return result;
	// }
	// /**
	// * Page to page case.
	// *
	// * @param rule
	// * @param outCome
	// * @param toViewID
	// * @return
	// */
	// private List findFacesCase(NavigationRuleType rule, String outCome,
	// String toViewID) {
	// List cases = rule.getNavigationCase();
	// List result = new ArrayList();
	// for (int i = 0; i < cases.size(); i++) {
	// NavigationCaseType navCase = (NavigationCaseType) cases.get(i);
	// String fromOutcome = null;
	// if (navCase.getFromOutcome() != null) {
	// fromOutcome = navCase.getFromOutcome().getTextContent();
	// }
	// // FIXME: case sensitive? Don't consider other properties like icon,
	// // description. etc.
	// if (outCome == null && fromOutcome == null || outCome != null
	// && fromOutcome != null
	// && fromOutcome.equalsIgnoreCase(outCome)) {
	// if (toViewID != null
	// && navCase.getToViewId() != null
	// && toViewID.equals(navCase.getToViewId()
	// .getTextContent()))
	// result.add(navCase);
	// }
	// }
	// return result;
	// }
	/**
	 * Page->action->page
	 * 
	 * @param rule
	 * @param action
	 * @param outCome
	 * @param toViewID
	 * @return
	 */
	// private List findFacesCase(NavigationRuleType rule, String action,
	// String outCome, String toViewID) {
	// List cases = rule.getNavigationCase();
	// List result = new ArrayList();
	// for (int i = 0; i < cases.size(); i++) {
	// NavigationCaseType navCase = (NavigationCaseType) cases.get(i);
	// String fromAction = null;
	// if (navCase.getFromAction() != null) {
	// fromAction = navCase.getFromAction().getTextContent();
	// }
	// String fromOutcome = null;
	// if (navCase.getFromOutcome() != null) {
	// fromOutcome = navCase.getFromOutcome().getTextContent();
	// }
	// // FIXME: case sensitive? Don't consider other properties like icon,
	// // description. etc.
	// if (outCome == null && fromOutcome == null || outCome != null
	// && fromOutcome != null && fromOutcome.equals(outCome)) {
	// if (toViewID != null
	// && navCase.getToViewId() != null
	// && toViewID.equals(navCase.getToViewId()
	// .getTextContent()))
	// if (fromAction != null && action != null
	// && fromAction.equals(action)) {
	// result.add(navCase);
	// }
	// }
	// }
	// return result;
	// }
	//
	// private void createFacesCase(PFLink link, PFPage source, String
	// actionStr,
	// PFPage target) {
	// NavigationRuleType rule = FacesConfigFactory.eINSTANCE
	// .createNavigationRuleType();
	// NavigationCaseType navCase = FacesConfigFactory.eINSTANCE
	// .createNavigationCaseType();
	// FromViewIdType from = FacesConfigFactory.eINSTANCE
	// .createFromViewIdType();
	// from.setTextContent(source.getPath());
	// ToViewIdType toView = FacesConfigFactory.eINSTANCE.createToViewIdType();
	// toView.setTextContent(target.getPath());
	// FromActionType action = FacesConfigFactory.eINSTANCE
	// .createFromActionType();
	// action.setTextContent(actionStr);
	// updateFacesCase(link, navCase);
	// navCase.setFromAction(action);
	// navCase.setToViewId(toView);
	// rule.getNavigationCase().add(navCase);
	// rule.setFromViewId(from);
	// facesConfig.getNavigationRule().add(rule);
	// updateFacesRule(rule, source);
	// FC2PFTransformer.getInstance().getMapCases2Links().put(navCase, link);
	// FC2PFTransformer.getInstance().getMapLinks2Cases().put(link, navCase);
	// }
	// private void updateFacesCase(PFLink ref, NavigationCaseType navCase) {
	// if (ref != null) {
	// if (ref.getOutcome() != null) {
	// FromOutcomeType outcome = FacesConfigFactory.eINSTANCE
	// .createFromOutcomeType();
	// outcome.setTextContent(ref.getOutcome());
	// navCase.setFromOutcome(outcome);
	// }
	// if (ref.getName() != null) {
	// DisplayNameType display = FacesConfigFactory.eINSTANCE
	// .createDisplayNameType();
	// display.setTextContent(ref.getName());
	// navCase.getDisplayName().add(display);
	// }
	// if (ref.getComment() != null) {
	// DescriptionType description = FacesConfigFactory.eINSTANCE
	// .createDescriptionType();
	// description.setTextContent(ref.getComment());
	// navCase.getDescription().add(description);
	// }
	// }
	// }
	private void updateFacesRule(NavigationRuleType navigationRule,
			PFPage pageSource) {
		FacesConfigFactory factory = FacesConfigFactory.eINSTANCE;
		if (pageSource.getComment() != null) {
			DescriptionType description = factory.createDescriptionType();
			description.setTextContent(pageSource.getComment());
			navigationRule.getDescription().clear();
			navigationRule.getDescription().add(description);
		}
		// set the navigation rule's icon
		if (pageSource.getLargeicon() != null
				|| pageSource.getSmallicon() != null) {
			IconType icon = factory.createIconType();
			if (pageSource.getLargeicon() != null) {
				LargeIconType largeicon = factory.createLargeIconType();
				largeicon.setTextContent(pageSource.getLargeicon());
				icon.setLargeIcon(largeicon);
			}

			if (pageSource.getSmallicon() != null) {
				SmallIconType smallicon = factory.createSmallIconType();
				smallicon.setTextContent(pageSource.getSmallicon());
				icon.setSmallIcon(smallicon);
			}
			navigationRule.getIcon().clear();
			navigationRule.getIcon().add(icon);
		}
	}

	private void createFacesCase(PFLink link, PFPage source, PFPage target) {
		NavigationRuleType rule = FacesConfigFactory.eINSTANCE
				.createNavigationRuleType();
		FromViewIdType from = FacesConfigFactory.eINSTANCE
				.createFromViewIdType();
		from.setTextContent((source).getPath());
		rule.setFromViewId(from);
		NavigationCaseType navCase = FacesConfigFactory.eINSTANCE
				.createNavigationCaseType();
		ToViewIdType toView = FacesConfigFactory.eINSTANCE.createToViewIdType();
		toView.setTextContent((target).getPath());
		navCase.setToViewId(toView);
		// updateFacesCase(link, navCase);
		link.getFCElements().add(navCase);
		rule.getNavigationCase().add(navCase);
		facesConfig.getNavigationRule().add(rule);
		updateFacesRule(rule, source);
		FC2PFTransformer.getInstance().getMapCases2Links().put(navCase, link);
		FC2PFTransformer.getInstance().getMapLinks2Cases().put(link, navCase);
	}

	private void removePageToPageLink(PFPage start, PFLink link, PFPage target) {
		if (FC2PFTransformer.getInstance().getMapLinks2Cases()
				.containsKey(link)) {
			link.eAdapters().remove(FC2PFTransformer.getInstance());
			NavigationCaseType caseFC = (NavigationCaseType) FC2PFTransformer
					.getInstance().getMapLinks2Cases().remove(link);
			FC2PFTransformer.getInstance().getMapCases2Links().remove(caseFC);
			removeCase(caseFC);
		}
	}

	private void removeCase(NavigationCaseType navCase) {
		NavigationRuleType rule = (NavigationRuleType) navCase.eContainer();
		(rule).getNavigationCase().remove(navCase);
		if (rule.getNavigationCase().size() == 0) {
			facesConfig.getNavigationRule().remove(rule);
		}
	}

	// private void removePageToActionLink(PFPage page, PFAction action) {
	// if (action.getOutlinks().size() > 0 && page != null) {
	// String pagePath = page.getPath();
	// List list = findFacesRules(pagePath);
	// for (int i = 0; i < list.size(); i++) {
	// NavigationRuleType rule = (NavigationRuleType) list.get(i);
	// List actionOutlinks = action.getOutlinks();
	// for (int j = 0; j < actionOutlinks.size(); j++) {
	// PFLink actionOutlink = (PFLink) actionOutlinks.get(j);
	// String outcome = actionOutlink.getOutcome();
	// List cases = findFacesCase(rule, action.getName(), outcome,
	// ((PFPage) actionOutlink.getTarget()).getPath());
	// removeCases(rule, cases);
	// if (rule.getNavigationCase().size() == 0) {
	// FacesConfigType facesConfig = (FacesConfigType) rule
	// .eContainer();
	// facesConfig.getNavigationRule().remove(rule);
	// break;
	// }
	// }
	// }
	// }
	// }

	// private void removeActionToPageLink(PFAction element, PFPage page,
	// PFLink value) {
	// if (element != null && value != null && page != null) {
	// if (hasDuplicatedLinks(element, page, value)) {
	// return;
	// }
	// PFLink link = (PFLink) value;
	// PageflowNode source = link.getSource();
	// PageflowNode target = link.getTarget();
	// link.setTarget(page);
	// link.setSource(element);
	// List cases = getCasesInCache(link);
	// link.setTarget(source);
	// link.setSource(target);
	// for (int i = 0, n = cases.size(); i < n; i++) {
	// removeCase((NavigationCaseType) cases.get(i));
	// }
	// // List inLinks = ((PFAction) element).getInlinks();
	// // for (int i = 0; i < inLinks.size(); i++) {
	// // PFLink inLink = (PFLink) inLinks.get(i);
	// // PFPage start = (PFPage) inLink.getSource();
	// // if (start instanceof PFPage) {
	// // String path = ((PFPage) start).getPath();
	// // List list = findFacesRules(path);
	// // for (int j = 0; j < list.size(); j++) {
	// // NavigationRuleType rule = (NavigationRuleType) list
	// // .get(j);
	// // String outcome = link.getOutcome();
	// // String fromAction = ((PFAction) element).getAction();
	// // List cases = findFacesCase(rule, fromAction, outcome,
	// // page.getPath());
	// // removeCases(rule, cases);
	// // if (rule.getNavigationCase().size() == 0) {
	// // FacesConfigType facesConfig = (FacesConfigType) rule
	// // .eContainer();
	// // facesConfig.getNavigationRule().remove(rule);
	// // }
	// // }
	// // }
	// // }
	// }
	// }

	// private boolean hasDuplicatedLinks(PFPage start, PFPage end,
	// PFLink link) {
	// List links = start.getOutlinks();
	// int c = 0;
	// for (int i = 0, n = links.size(); i < n; i++) {
	// PFLink theLink = ((PFLink) links.get(i));
	// if (theLink.getTarget() == end) {
	// if (theLink.getOutcome() == null && link.getOutcome() == null
	// || theLink.getOutcome() != null
	// && link.getOutcome() != null
	// && theLink.getOutcome().equals(link.getOutcome())) {
	// c++;
	// break;
	// }
	// }
	// }
	// return c > 0;
	// }

	// private boolean hasDuplicatedLinks(PFLink link) {
	// PageflowNode source = link.getSource();
	// List links = source.getOutlinks();
	// int c = 0;
	// for (int i = 0, n = links.size(); i < n; i++) {
	// PFLink theLink = ((PFLink) links.get(i));
	// if (theLink.getOutcome() == null && link.getOutcome() == null
	// || theLink.getOutcome() != null
	// && link.getOutcome() != null
	// && theLink.getOutcome().equals(link.getOutcome())) {
	// c++;
	// break;
	// }
	// }
	// return c > 0;
	// }

	private void propertyChanged(Notification notification,
			PageflowElement element) {
		if (notification.getNewValue() != null) {
			int value = notification.getFeatureID(PageflowPackage.class);
			if (element instanceof PFPage) {
				setPageProperties(notification, element, value);
			} else if (element instanceof PFLink) {
				// System.out.println("The link property is changed");
				// setLinkProperties(notification, element, value);
			}
		}
	}

	// private void setActionProperties(Notification notification,
	// PageflowElement element, int value) {
	// String actionID = ((PFAction) element).getAction();
	// if (value == PageflowPackage.PF_ACTION__ACTION) {
	// actionID = notification.getOldStringValue();
	// List cases = getCases((PFAction) element, actionID);
	// for (int i = 0; i < cases.size(); i++) {
	// // switch (value) {
	// // case PageflowPackage.PF_ACTION__ACTION:
	// FromActionType action = ((NavigationCaseType) cases.get(i))
	// .getFromAction();
	// if (action == null) {
	// action = FacesConfigFactory.eINSTANCE
	// .createFromActionType();
	// action.setTextContent(notification.getNewStringValue());
	// ((NavigationCaseType) cases.get(i)).setFromAction(action);
	// } else {
	// action.setTextContent(notification.getNewStringValue());
	// }
	// }
	// }
	// }

	private List getCasesInCache(PFLink link) {
		List result = new ArrayList();
		Iterator iterator = FC2PFTransformer.getInstance().getMapLinks2Cases()
				.keySet().iterator();
		while (iterator.hasNext()) {
			PFLink theLink = (PFLink) iterator.next();
			if (theLink.equals(link)) {
				result.add(FC2PFTransformer.getInstance().getMapLinks2Cases()
						.get(theLink));
			}
		}
		return result;
	}

	private void setLinkProperties(Notification notification,
			PageflowElement element, int value) {
		// String outcome = ((PFLink) element).getOutcome();
		// if (value == PageflowPackage.PF_LINK__OUTCOME) {
		// outcome = notification.getOldStringValue();
		// }
		List cases = getCasesInCache((PFLink) element);
		// NavigationCaseType caseFC = null;
		// if (FC2PFTransformer.getInstance().getMapLinks2Cases().get(element)
		// != null) {
		// caseFC = (NavigationCaseType) FC2PFTransformer.getInstance()
		// .getMapLinks2Cases().get(element);
		// }
		// if (cases.size()>0 != null) {
		// List cases = getCases((PFLink) element, outcome);
		for (int i = 0; i < cases.size(); i++) {
			NavigationCaseType caseFC = (NavigationCaseType) cases.get(i);
			switch (value) {
			case PageflowPackage.PF_LINK__REDIRECT:
				if (notification.getNewBooleanValue()) {
					RedirectType redirect = FacesConfigFactory.eINSTANCE
							.createRedirectType();
					caseFC.setRedirect(redirect);
				} else {
					if (caseFC != null) {
						caseFC.setRedirect(null);
					}
				}
				break;
			case PageflowPackage.PF_LINK__COMMENT:
				caseFC.getDescription().clear();
				caseFC.getDescription().add(notification.getNewStringValue());
				break;
			case PageflowPackage.PF_LINK__NAME:
				caseFC.getDisplayName().clear();
				caseFC.getDisplayName().add(notification.getNewStringValue());
				break;
			case PageflowPackage.PF_LINK__OUTCOME:
				FromOutcomeType caseOutcome = caseFC.getFromOutcome();
				if (caseOutcome != null) {
					caseOutcome
							.setTextContent(notification.getNewStringValue());
				} else {
					FromOutcomeType newoutcome = FacesConfigFactory.eINSTANCE
							.createFromOutcomeType();
					newoutcome.setTextContent(notification.getNewStringValue());
					caseFC.setFromOutcome(newoutcome);
				}
				break;

			case PageflowPackage.PF_LINK__FROMACTION:
				// FromActionType fromAction = caseFC.getFromAction();
				// if (fromAction != null) {
				// if (notification.getNewStringValue() != null
				// && notification.getNewStringValue().trim().length() != 0) {
				// fromAction.setTextContent(notification
				// .getNewStringValue());
				// } else {
				// caseFC.setFromAction(null);
				// }
				// } else if (notification.getNewStringValue() != null
				// && notification.getNewStringValue().trim().length() != 0) {
				// FromActionType fromaction = FacesConfigFactory.eINSTANCE
				// .createFromActionType();
				// fromaction.setTextContent(notification.getNewStringValue());
				// caseFC.setFromAction(fromaction);
				// }
				break;
			}
		}
	}

	// private List getCases(PFAction action, String actionStr) {
	// List result = new ArrayList();
	// List InLinks = action.getInlinks();
	// List outLinks = action.getOutlinks();
	// for (int i = 0, n = InLinks.size(); i < n; i++) {
	// PageflowNode start = ((PFLink) InLinks.get(i)).getSource();
	// List rules = findFacesRules(((PFPage) start).getPath());
	// for (int j = 0, nn = rules.size(); j < nn; j++) {
	// for (int jj = 0, nnn = outLinks.size(); jj < nnn; jj++) {
	// PFLink link = (PFLink) outLinks.get(jj);
	// String outcome = link.getOutcome();
	// String toID = ((PFPage) (link.getTarget())).getPath();
	// List cases = findFacesCase((NavigationRuleType) rules
	// .get(j), actionStr, outcome, toID);
	// result.addAll(cases);
	// }
	// }
	// }
	// return result;
	// }

	// private List getCases(PFLink link, String outcome) {
	// List result = new ArrayList();
	// if (link != null && link.getTarget() instanceof PFPage) {
	// if (link.getSource() instanceof PFAction) {
	// // Page source:
	// List links = ((PFAction) link.getSource()).getInlinks();
	// for (int i = 0; i < links.size(); i++) {
	// PageflowNode source = ((PFLink) links.get(i)).getSource();
	// List rules = findFacesRules(((PFPage) source).getPath());
	// for (int j = 0; j < rules.size(); j++) {
	// NavigationRuleType rule = (NavigationRuleType) rules
	// .get(j);
	// // Compare cases:
	// List cases = rule.getNavigationCase();
	// for (int k = 0; k < cases.size(); k++) {
	// NavigationCaseType navCase = (NavigationCaseType) cases
	// .get(k);
	// String action = ((PFAction) link.getSource())
	// .getAction();
	// String targetPath = ((PFPage) link.getTarget())
	// .getPath();
	// if (navCase.getFromAction() != null
	// && action.equals(navCase.getFromAction()
	// .getTextContent())
	// && navCase.getToViewId() != null
	// && navCase.getToViewId().getTextContent()
	// .equals(targetPath)) {
	// FromOutcomeType fromOutCome = navCase
	// .getFromOutcome();
	// if ((fromOutCome == null && outcome == null)
	// || (fromOutCome != null
	// && outcome != null && fromOutCome
	// .getTextContent().equals(
	// outcome)))
	// result.add(navCase);
	// }
	// }
	// }
	// }
	// } else if (link.getSource() instanceof PFPage) {
	// List rules = findFacesRules(((PFPage) link.getSource())
	// .getPath());
	// for (int j = 0; j < rules.size(); j++) {
	// NavigationRuleType rule = (NavigationRuleType) rules.get(j);
	// List cases = rule.getNavigationCase();
	// for (int k = 0; k < cases.size(); k++) {
	// NavigationCaseType navCase = (NavigationCaseType) cases
	// .get(k);
	// PFPage target = (PFPage) link.getTarget();
	// // FIXME: Can getToViewId() return null?
	// if (target.getPath().equals(
	// navCase.getToViewId().getTextContent())) {
	// FromOutcomeType fromOutCome = navCase
	// .getFromOutcome();
	// if ((fromOutCome == null && outcome == null)
	// || (fromOutCome != null && outcome != null && fromOutCome
	// .getTextContent().equals(outcome)))
	// result.add(navCase);
	// }
	// }
	// }
	// }
	// }
	// return result;
	// }

	private void setPageProperties(Notification notification,
			PageflowElement element, int value) {
		String path = ((PFPage) element).getPath();
		List list = findFacesPages(path);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof FromViewIdType) {
				FromViewIdType fromViewID = ((FromViewIdType) list.get(i));
				NavigationRuleType rule = (NavigationRuleType) fromViewID
						.eContainer();
				switch (value) {
				case PageflowPackage.PF_PAGE__NAME:
					rule.getDisplayName().clear();
					DisplayNameType displayname = FacesConfigFactory.eINSTANCE
							.createDisplayNameType();
					displayname.setTextContent((String) notification
							.getNewValue());
					rule.getDisplayName().add(displayname);
					break;
				case PageflowPackage.PF_PAGE__COMMENT:
					rule.getDescription().clear();
					DescriptionType description = FacesConfigFactory.eINSTANCE
							.createDescriptionType();
					description.setTextContent((String) notification
							.getNewValue());
					rule.getDescription().add(description);
					break;
				case PageflowPackage.PF_PAGE__LARGEICON: {
					List icons = rule.getIcon();
					boolean did = false;
					for (int index = 0; index < icons.size(); index++) {
						IconType icon = (IconType) icons.get(index);
						if (icon.getLargeIcon() != null) {
							icon.getLargeIcon().setTextContent(
									(String) notification.getNewValue());
							did = true;
						}
					}
					if (!did) {
						IconType icon = FacesConfigFactory.eINSTANCE
								.createIconType();
						LargeIconType largeIconType = FacesConfigFactory.eINSTANCE
								.createLargeIconType();
						largeIconType.setTextContent((String) notification
								.getNewValue());
						icon.setLargeIcon(largeIconType);
						icons.add(icon);
					}
					break;
				}
				case PageflowPackage.PF_PAGE__SMALLICON: {
					List icons = rule.getIcon();
					boolean did = false;
					for (int index = 0; index < icons.size(); index++) {
						IconType icon = (IconType) icons.get(index);
						if (icon.getSmallIcon() != null) {
							icon.getSmallIcon().setTextContent(
									(String) notification.getNewValue());
							did = true;
						}
					}
					if (!did) {
						IconType icon = FacesConfigFactory.eINSTANCE
								.createIconType();
						SmallIconType smallIconType = FacesConfigFactory.eINSTANCE
								.createSmallIconType();
						smallIconType.setTextContent((String) notification
								.getNewValue());
						icon.setSmallIcon(smallIconType);
						icons.add(icon);
					}
					break;
				}
				}
			}
		}
	}

	private void addPageToPageLink(PFPage source, PFPage target, PFLink value) {
		if (source != null && target != null && value != null) {
			if (!((PFLink) value).eAdapters().contains(
					FC2PFTransformer.getInstance())) {
				((PFLink) value).eAdapters()
						.add(FC2PFTransformer.getInstance());
			}
			// List list = ((PageflowNode) source).getOutlinks();
			// for (int i = 0; i < list.size(); i++) {
			// PFLink link = (PFLink) list.get(i);
			// if (link == value) {
			// continue;
			// }
			// if (link.getTarget() instanceof PFPage) {
			// PFPage page = (PFPage) link.getTarget();
			// // there is an existing link.
			// if (page.getPath().equals(((PFPage) target).getPath())) {
			// return;
			// }
			// }
			// }
			createFacesCase(value, (PFPage) source, (PFPage) target);
		}
	}

	// private void addPageToActionLink(PFPage source, PFAction target,
	// Object value) {
	// if (source != null && target != null && value != null) {
	// List list = ((PageflowNode) source).getOutlinks();
	// for (int i = 0; i < list.size(); i++) {
	// // there is an existing link.
	// PFLink link = (PFLink) list.get(i);
	// if (link == value) {
	// continue;
	// }
	// if (link.getTarget() instanceof PFPage) {
	// PFPage page = (PFPage) link.getTarget();
	// if (page.getPath().equals(((PFPage) target).getPath())) {
	// return;
	// }
	// }
	// }
	// // find next link.
	// if (target.getOutlinks().size() > 0) {
	// list = ((PageflowNode) target).getOutlinks();
	// for (int i = 0; i < list.size(); i++) {
	// PFLink secondLink = (PFLink) list.get(i);
	// if (secondLink.getTarget() instanceof PFPage) {
	// PFPage page = (PFPage) secondLink.getTarget();
	// PFPage target1 = page;
	// createFacesCase((PFLink) value, secondLink, source,
	// target, target1);
	// }
	// }
	// }
	// }
	// }
	//
	// private void addActionToPageLink(PFAction action, PFPage target,
	// PFLink value) {
	// if (action != null && target != null && value != null) {
	// // List list = ((PageflowNode) action).getOutlinks();
	// // for (int i = 0; i < list.size(); i++) {
	// // // there is an existing link.
	// // PFLink link = (PFLink) list.get(i);
	// // if (link == value) {
	// // continue;
	// // }
	// // if (link.getTarget() instanceof PFPage) {
	// // PFPage page = (PFPage) link.getTarget();
	// // if (page.getPath().equals(((PFPage) target).getPath())) {
	// // return;
	// // }
	// // }
	// // }
	// List list;
	// // find previous link.
	// if (action.getInlinks().size() > 0) {
	// list = ((PageflowNode) action).getInlinks();
	// for (int i = 0; i < list.size(); i++) {
	// // there is an existing link.
	// PFLink link = (PFLink) list.get(i);
	// if (link.getSource() instanceof PFPage) {
	// createFacesCase(link, value, (PFPage) link.getSource(),
	// action, target);
	// }
	// }
	// }
	// }
	// }

	// private void addedNewElement(Object value) {
	// // Object value = notification.getNewValue();
	// if (value instanceof PFLink) {
	// PageflowNode source = ((PFLink) value).getSource();
	// PageflowNode target = ((PFLink) value).getTarget();
	// // page -> page
	// if (source instanceof PFPage) {
	// if (target instanceof PFPage) {
	// }
	// }
	// // // page->action->page.
	// // else if (source instanceof PFAction) {
	// // if (target instanceof PFPage) {
	// // }
	// // }
	// }
	// }

	private void addInLink(Notification notification, PageflowElement element) {
		Object value = notification.getNewValue();
		PFLink link = (PFLink) value;
		// for outLink remove, only source is referenced.
		// if (element instanceof PFAction) {
		// addPageToActionLink((PFPage) link.getSource(), (PFAction) element,
		// link);
		// } else
		if (element instanceof PFPage) {
			PageflowNode source = link.getSource();
			if (source instanceof PFPage) {
				addPageToPageLink((PFPage) source, (PFPage) element, link);
			}

			// else if (source instanceof PFAction) {
			// addActionToPageLink((PFAction) source, (PFPage) element, link);
			// }
		}

	}

	private void addOutLink(Notification notification, PageflowElement element) {
		Object value = notification.getNewValue();
		// for outLink remove, only target is referenced.
		PFLink link = (PFLink) value;
		if (element instanceof PFPage) {
			PageflowNode target = link.getTarget();
			// page->page
			if (target instanceof PFPage) {
				addPageToPageLink((PFPage) element, (PFPage) target, link);
			}
			// else if (target instanceof PFAction)
			// addPageToActionLink((PFPage) element, (PFAction) target, link);
			// } else if (element instanceof PFAction)
			// addActionToPageLink((PFAction) element, (PFPage)
			// link.getTarget(),
			// link);
		}
	}

	private void removePage(Notification notification, PageflowElement element) {

	}

	private void removeInLink(Notification notification, PageflowElement element) {
		Object value = notification.getOldValue();
		PFLink link = (PFLink) value;
		// for outLink remove, only source is referenced.
		// if (element instanceof PFAction) {
		// removePageToActionLink((PFPage) link.getSource(),
		// (PFAction) element);
		// } else
		if (element instanceof PFPage) {
			PageflowNode source = link.getSource();
			if (source instanceof PFPage) {
				removePageToPageLink((PFPage) source, link, (PFPage) element);
			}
			// else if (source instanceof PFAction) {
			// removeActionToPageLink((PFAction) source, (PFPage) element,
			// link);
			// }
		}
	}

	private void removeOutLink(Notification notification,
			PageflowElement element) {
		// the link that is removed.
		Object value = notification.getOldValue();
		PFLink link = (PFLink) value;
		// for outLink remove, only target is referenced.
		if (element instanceof PFPage) {
			PageflowNode target = link.getTarget();
			// page->page
			if (target instanceof PFPage) {
				removePageToPageLink((PFPage) element, link, (PFPage) target);
			}
			// else if (target instanceof PFAction)
			// removePageToActionLink((PFPage) element, (PFAction) target);
			// } else if (element instanceof PFAction)
			// removeActionToPageLink((PFAction) element, (PFPage) link
			// .getTarget(), link);
		}
	}

	public void notifyChanged(Notification notification) {
		PageflowElement element;
		if (!(notification.getNotifier() instanceof PageflowElement)) {
			return;
		}
		element = (PageflowElement) notification.getNotifier();
		int type = notification.getEventType();
		switch (type) {
		case Notification.ADD: {
			int featureId = notification.getFeatureID(PageflowPackage.class);
			switch (featureId) {
			case PageflowPackage.PAGEFLOW__NODES:
				addPage(notification, element);
				break;
			case PageflowPackage.PAGEFLOW_NODE__OUTLINKS:
				addOutLink(notification, element);
				break;
			case PageflowPackage.PAGEFLOW_NODE__INLINKS:
				addInLink(notification, element);
				break;
			case PageflowPackage.PAGEFLOW:
				break;
			}
			break;
		}
		case Notification.SET:
			if (notification.getNewValue() != null) {
				propertyChanged(notification, element);
			}
			break;
		case Notification.REMOVE: {
			int featureId = notification.getFeatureID(PageflowPackage.class);
			switch (featureId) {
			case PageflowPackage.PF_PAGE:
				removePage(notification, element);
				break;
			case PageflowPackage.PAGEFLOW_NODE__OUTLINKS:
				removeOutLink(notification, element);
				break;
			case PageflowPackage.PAGEFLOW_NODE__INLINKS:
				removeInLink(notification, element);
				break;
			case PageflowPackage.PAGEFLOW:
				break;
			}
			break;
		}
		}
	}

	public void dispose() {

	}

	public void setTarget(Notifier newTarget) {
		// TODO Auto-generated method stub

	}

}
