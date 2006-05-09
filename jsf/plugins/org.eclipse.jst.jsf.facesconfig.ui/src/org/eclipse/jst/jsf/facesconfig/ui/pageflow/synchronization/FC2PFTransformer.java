/**
 * Confidential Property of Sybase, Inc. 
 * (c) Copyright Sybase, Inc. 2004-2006. 
 * All rights reserved.
 */
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jface.util.Assert;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowModelManager;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowTransform;

/**
 * Update and synchronize pageflow from faces config or vice-versa.
 * 
 * @author hmeng
 * 
 */

public class FC2PFTransformer {
	// For code debug.
	private static final boolean DEBUG = false;

	class PF2FCSynchronizer implements Adapter {

		public PF2FCSynchronizer() {
		}

		public Notifier getTarget() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean isAdapterForType(Object type) {
			return type == PF2FCSynchronizer.class;
		}

		private void addPage(Notification notification, PageflowElement element) {
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
					NavigationCaseType navCase = (NavigationCaseType) cases
							.get(j);
					String toViewID = getToViewID(navCase);
					if (toViewID.equals(path)) {
						result.add(navCase.getToViewId());
					}
				}
			}
			return result;
		}

		private void updateFacesRule(NavigationRuleType navigationRule,
				PageflowPage pageSource) {
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
			if (pageSource.getName() != null) {
				DisplayNameType displayName = factory.createDisplayNameType();
				displayName.setTextContent(pageSource.getName());
				navigationRule.getDisplayName().clear();
				navigationRule.getDisplayName().add(displayName);
			}
		}

		private void createFacesCase(PageflowLink link, PageflowPage source,
				PageflowPage target) {
			NavigationRuleType rule = FacesConfigFactory.eINSTANCE
					.createNavigationRuleType();
			FromViewIdType from = FacesConfigFactory.eINSTANCE
					.createFromViewIdType();
			from.setTextContent((source).getPath());
			rule.setFromViewId(from);
			NavigationCaseType navCase = FacesConfigFactory.eINSTANCE
					.createNavigationCaseType();
			ToViewIdType toView = FacesConfigFactory.eINSTANCE
					.createToViewIdType();
			toView.setTextContent((target).getPath());
			navCase.setToViewId(toView);
			rule.getNavigationCase().add(navCase);
			facesConfig.getNavigationRule().add(rule);
			updateFacesRule(rule, source);
			source.getFCElements().add(from);
			target.getFCElements().add(toView);
			link.getFCElements().add(navCase);
			mapCases2Links.put(navCase, link);
		}

		private void removePageToPageLink(PageflowPage start,
				PageflowLink link, PageflowPage target) {
			if (!link.getFCElements().isEmpty()) {
				FC2PFTransformer.getInstance().unAdapt(link);
				NavigationCaseType caseFC = (NavigationCaseType) link
						.getFCElements().getData().get(0);// (NavigationCaseType)
				link.getFCElements().remove(caseFC);
				mapCases2Links.remove(caseFC);
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

		private void propertyChanged(Notification notification,
				PageflowElement element) {
			if (notification.getNewValue() != null) {
				int value = notification.getFeatureID(PageflowPackage.class);
				if (element instanceof PageflowPage) {
					// setPageProperties(notification, element, value);
				} else if (element instanceof PageflowLink) {
					if (DEBUG)
						System.out.println("The link property is changed");
					// setLinkProperties(notification, element, value);
				}
			}
		}

		// private void setPageProperties(Notification notification,
		// PageflowElement element, int value) {
		// String path = ((PageflowPage) element).getPath();
		// List list = findFacesPages(path);
		// for (int i = 0; i < list.size(); i++) {
		// if (list.get(i) instanceof FromViewIdType) {
		// FromViewIdType fromViewID = ((FromViewIdType) list.get(i));
		// NavigationRuleType rule = (NavigationRuleType) fromViewID
		// .eContainer();
		// switch (value) {
		// case PageflowPackage.PF_PAGE__NAME:
		// rule.getDisplayName().clear();
		// DisplayNameType displayname = FacesConfigFactory.eINSTANCE
		// .createDisplayNameType();
		// displayname.setTextContent((String) notification
		// .getNewValue());
		// rule.getDisplayName().add(displayname);
		// break;
		// case PageflowPackage.PF_PAGE__COMMENT:
		// rule.getDescription().clear();
		// DescriptionType description = FacesConfigFactory.eINSTANCE
		// .createDescriptionType();
		// description.setTextContent((String) notification
		// .getNewValue());
		// rule.getDescription().add(description);
		// break;
		// case PageflowPackage.PF_PAGE__LARGEICON: {
		// List icons = rule.getIcon();
		// boolean did = false;
		// for (int index = 0; index < icons.size(); index++) {
		// IconType icon = (IconType) icons.get(index);
		// if (icon.getLargeIcon() != null) {
		// icon.getLargeIcon().setTextContent(
		// (String) notification.getNewValue());
		// did = true;
		// }
		// }
		// if (!did) {
		// IconType icon = FacesConfigFactory.eINSTANCE
		// .createIconType();
		// LargeIconType largeIconType = FacesConfigFactory.eINSTANCE
		// .createLargeIconType();
		// largeIconType.setTextContent((String) notification
		// .getNewValue());
		// icon.setLargeIcon(largeIconType);
		// icons.add(icon);
		// }
		// break;
		// }
		// case PageflowPackage.PF_PAGE__SMALLICON: {
		// List icons = rule.getIcon();
		// boolean did = false;
		// for (int index = 0; index < icons.size(); index++) {
		// IconType icon = (IconType) icons.get(index);
		// if (icon.getSmallIcon() != null) {
		// icon.getSmallIcon().setTextContent(
		// (String) notification.getNewValue());
		// did = true;
		// }
		// }
		// if (!did) {
		// IconType icon = FacesConfigFactory.eINSTANCE
		// .createIconType();
		// SmallIconType smallIconType = FacesConfigFactory.eINSTANCE
		// .createSmallIconType();
		// smallIconType.setTextContent((String) notification
		// .getNewValue());
		// icon.setSmallIcon(smallIconType);
		// icons.add(icon);
		// }
		// break;
		// }
		// }
		// }
		// }
		// }

		private void addPageToPageLink(PageflowPage source,
				PageflowPage target, PageflowLink value) {
			if (source != null && target != null && value != null) {
				createFacesCase(value, (PageflowPage) source,
						(PageflowPage) target);
			}
		}

		private void addInLink(Notification notification,
				PageflowElement element) {
			Object value = notification.getNewValue();
			PageflowLink link = (PageflowLink) value;
			if (element instanceof PageflowPage) {
				PageflowNode source = link.getSource();
				if (source instanceof PageflowPage) {
					addPageToPageLink((PageflowPage) source,
							(PageflowPage) element, link);
				}
			}

		}

		private void addOutLink(Notification notification,
				PageflowElement element) {
			Object value = notification.getNewValue();
			// for outLink remove, only target is referenced.
			PageflowLink link = (PageflowLink) value;
			if (element instanceof PageflowPage) {
				PageflowNode target = link.getTarget();
				// page->page
				if (target instanceof PageflowPage) {
					addPageToPageLink((PageflowPage) element,
							(PageflowPage) target, link);
				}
			}
		}

		private void removePage(Notification notification,
				PageflowElement element) {

		}

		private void removeInLink(Notification notification,
				PageflowElement element) {
			Object value = notification.getOldValue();
			PageflowLink link = (PageflowLink) value;

			if (element instanceof PageflowPage) {
				PageflowNode source = link.getSource();
				if (source instanceof PageflowPage) {
					removePageToPageLink((PageflowPage) source, link,
							(PageflowPage) element);
				}
			}
		}

		private void removeOutLink(Notification notification,
				PageflowElement element) {
			Object value = notification.getOldValue();
			PageflowLink link = (PageflowLink) value;
			if (element instanceof PageflowPage) {
				PageflowNode target = link.getTarget();
				// page->page
				if (target instanceof PageflowPage) {
					removePageToPageLink((PageflowPage) element, link,
							(PageflowPage) target);
				}
			}
		}

		public void notifyChanged(Notification notification) {
			if (!listenToNotify || isInEvent) {
				return;
			}
			isInEvent = true;
			PageflowElement element;
			if (!(notification.getNotifier() instanceof PageflowElement)) {
				return;
			}
			element = (PageflowElement) notification.getNotifier();
			int type = notification.getEventType();
			switch (type) {
			case Notification.ADD: {
				// Object feature = notification.getFeature();
				// if (feature == PageflowPackage.eINSTANCE
				// .getPageflowNode_Inlinks()) {
				//
				// }
				int featureId = notification
						.getFeatureID(PageflowPackage.class);
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
				int featureId = notification
						.getFeatureID(PageflowPackage.class);
				switch (featureId) {
				case PageflowPackage.PF_PAGE:
					removePage(notification, element);
					updateAndNotify(notification);
					break;
				case PageflowPackage.PAGEFLOW_NODE__OUTLINKS:
					removeOutLink(notification, element);
					updateAndNotify(notification);
					break;
				case PageflowPackage.PAGEFLOW_NODE__INLINKS:
					removeInLink(notification, element);
					updateAndNotify(notification);
					break;
				// case PageflowPackage.PAGEFLOW:
				// updateAndNotify(notification);
				// break;
				}
				break;
			}
			}
			refreshPFAdapter(pageflow);
			refreshFCAdapter(facesConfig);
			isInEvent = false;
		}

		private void updateAndNotify(Notification notification) {
			pageflow.notifyModelChanged(new ENotificationImpl(
					(InternalEObject) notification.getNotifier(),
					Notification.SET, PageflowPackage.PAGEFLOW, null, null));
		}

		public void dispose() {

		}

		public void setTarget(Notifier newTarget) {
			// TODO Auto-generated method stub

		}

	}

	class FC2PFSynchronizer implements Adapter {

		public FC2PFSynchronizer() {
		}

		public Notifier getTarget() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean isAdapterForType(Object type) {
			return type == FC2PFSynchronizer.class;
		}

		private void processAdd(Notification notification) {
			Object feature = notification.getFeature();
			if (feature == FacesConfigPackage.eINSTANCE
					.getNavigationRuleType_NavigationCase()) {
				if (DEBUG)
					System.out.println("New navigation case");
				NavigationCaseType newCase = (NavigationCaseType) notification
						.getNewValue();
				updatePageflowElements(pageflow, newCase);
			} else if (feature == FacesConfigPackage.eINSTANCE
					.getFacesConfigType_NavigationRule()) {
				NavigationRuleType newRule = (NavigationRuleType) notification
						.getNewValue();
				if (newRule.getNavigationCase().size() > 0) {
					for (int i = 0; i < newRule.getNavigationCase().size(); i++) {
						updatePageflowElements(pageflow,
								(NavigationCaseType) newRule
										.getNavigationCase().get(i));
					}
				}
				if (DEBUG)
					System.out.println("New navigation rule");
			} else if (DEBUG)
				System.out.println("Something is added");
		}

		public void notifyChanged(Notification notification) {
			if (!listenToNotify || isInEvent
					|| !(notification.getNotifier() instanceof EObject)) {
				return;
			}
			isInEvent = true;
			if (checkSanity(notification)) {
				int type = notification.getEventType();
				switch (type) {
				case Notification.ADD: {
					processAdd(notification);
					updateAndNotify(notification);
					break;
				}
				case Notification.SET: {
					processSet(notification);
					updateAndNotify(notification);
					break;
				}
				case Notification.REMOVE:
					processRemove(notification);
					updateAndNotify(notification);
					break;
				}
			}
			refreshFCAdapter((EObject) notification.getNotifier());
			refreshPFAdapter(pageflow);

			isInEvent = false;
		}

		private void updateAndNotify(Notification notification) {
			Assert
					.isTrue(notification.getNotifier() instanceof InternalEObject);
			pageflow.notifyModelChanged(new ENotificationImpl(
					(InternalEObject) notification.getNotifier(),
					Notification.SET, PageflowPackage.PAGEFLOW, null, null));
		}

		/**
		 * Avoid to process partial navigation rule or navigation case.
		 * 
		 * @param notification
		 * @return
		 */
		private boolean checkSanity(Notification notification) {
			// boolean result = false;
			// int type = notification.getEventType();
			// switch (type) {
			// case Notification.ADD:
			// Object newObject = notification.getNewValue();
			// if (newObject instanceof ToViewIdType
			// || newObject instanceof FromViewIdType) {
			// result = isValidViewID(newObject);
			// }
			// break;
			// case Notification.SET:
			// result = true;
			// newObject = notification.getNewValue();
			// if (newObject instanceof ToViewIdType
			// || newObject instanceof FromViewIdType) {
			// result = isValidViewID(notification.getNewValue());
			// }
			// break;
			// case Notification.REMOVE:
			// result = true;
			// }
			// return result;
			return true;
		}

		private boolean isValidViewID(Object object) {
			boolean result = false;
			if (object instanceof ToViewIdType) {
				result = ((ToViewIdType) object).getTextContent() != null
						&& ((ToViewIdType) object).getTextContent().trim()
								.length() > 0;
			} else if (object instanceof FromViewIdType) {
				result = ((FromViewIdType) object).getTextContent() != null
						&& ((FromViewIdType) object).getTextContent().trim()
								.length() > 0;
			}
			return result;
		}

		private void processRemove(Notification notification) {
			if (notification.getFeature() == FacesConfigPackage.eINSTANCE
					.getNavigationRuleType_NavigationCase()) {
				if (notification.getOldValue() instanceof NavigationCaseType) {
					NavigationCaseType caseType = (NavigationCaseType) notification
							.getOldValue();
					PageflowLink link = (PageflowLink) mapCases2Links
							.get(caseType);
					removeLink(link);
				}
				if (DEBUG)
					System.out.println("Navigation case");
			} else if (notification.getFeature() == FacesConfigPackage.eINSTANCE
					.getNavigationCaseType()) {
				if (notification.getOldValue() instanceof NavigationRuleType) {
					NavigationRuleType rule = (NavigationRuleType) notification
							.getOldValue();
					moveStartTo(rule, null, rule.getFromViewId());
				}
				if (DEBUG)
					System.out.println("navigation rule");
			} else if (notification.getFeature() == FacesConfigPackage.eINSTANCE
					.getFacesConfigType_NavigationRule()) {
				if (notification.getOldValue() instanceof NavigationRuleType) {
					NavigationRuleType rule = (NavigationRuleType) notification
							.getOldValue();
					moveStartTo(rule, null, rule.getFromViewId());
				}
			}
			if (DEBUG)
				System.out.println("Something is removed");
		}

		private void processSet(Notification notification) {
			Object feature = notification.getFeature();
			if (feature == FacesConfigPackage.eINSTANCE
					.getFromViewIdType_TextContent()
					|| feature == FacesConfigPackage.eINSTANCE
							.getNavigationRuleType_FromViewId()) {
				fromViewIdChanged(notification);
			} else if (feature == FacesConfigPackage.eINSTANCE
					.getToViewIdType_TextContent()
					|| feature == FacesConfigPackage.eINSTANCE
							.getNavigationCaseType_ToViewId()) {
				toViewIdChanged(notification);
			}
			if (DEBUG)
				System.out.println("Something is set");
		}

		private void fromViewIdChanged(Notification notification) {
			// remove
			Object feature = notification.getFeature();
			if (feature == FacesConfigPackage.eINSTANCE
					.getNavigationRuleType_FromViewId()
					&& notification.getNewValue() == null
					&& notification.getOldValue() instanceof FromViewIdType) {
				PageflowPage newStart = PageflowTransform.getInstance()
						.createPFPage("*");
				NavigationRuleType rule = (NavigationRuleType) notification
						.getNotifier();
				moveStartTo(rule, newStart, (FromViewIdType) notification
						.getOldValue());
			}
			// add
			else if (notification.getOldValue() == null
					&& notification.getNewValue() instanceof String) {
				String fromID = (String) notification.getNewValue();
				PageflowPage newStart = findPage(fromID, pageflow);
				newStart = newStart == null ? PageflowTransform.getInstance()
						.createPFPage(fromID) : newStart;
				NavigationRuleType rule = (NavigationRuleType) ((EObject) notification
						.getNotifier()).eContainer();
				moveStartTo(rule, newStart, (FromViewIdType) notification
						.getNotifier());
			}
			// // change
			// else if (notification.getOldValue() instanceof String &&
			// notification.getNewValue() instanceof String)
			// {
			// mapPaths2PF.put(key, value)
			// }
		}

		private void moveStartTo(NavigationRuleType rule,
				PageflowPage newStart, FromViewIdType fromViewId) {
			List cases = rule.getNavigationCase();
			for (int i = 0, n = cases.size(); i < n; i++) {
				NavigationCaseType caseFC = (NavigationCaseType) cases.get(i);
				PageflowLink link = (PageflowLink) mapCases2Links.get(caseFC);
				if (link.getSource() == newStart) {
					continue;
				}
				PageflowPage start = (PageflowPage) link.getSource();
				PageflowPage end = (PageflowPage) link.getTarget();
				PageflowLink newLink = null;
				if (newStart != null) {
					newLink = createPFLink(newStart, end, caseFC);
					mapCases2Links.put(caseFC, newLink);
				}
				// Remove old link
				// link.getSource().getFCElements().remove(fromViewId);
				// link.getTarget().getFCElements().remove(caseFC.getToViewId());
				removeLink(link);
				mapCases2Links.remove(caseFC);
				if (newLink != null) {
					mapCases2Links.put(caseFC, newLink);
				}

				if (start.getOutlinks().size() == 0
						&& start.getInlinks().size() == 0) {
					removePageflowPage(start);
				}
			}
		}

		private void toViewIdChanged(Notification notification) {
			// remove
			Object feature = notification.getFeature();
			if (feature == FacesConfigPackage.eINSTANCE
					.getNavigationCaseType_ToViewId()
					&& notification.getNewValue() == null
					&& notification.getOldValue() instanceof ToViewIdType) {
				NavigationCaseType rule = (NavigationCaseType) ((EObject) notification
						.getNotifier()).eContainer();
				moveEndTo(rule, null, (ToViewIdType) notification.getOldValue());
			}
			// add
			else if (feature == FacesConfigPackage.eINSTANCE
					.getToViewIdType_TextContent()) {
				String toViewId = (String) notification.getNewValue();
				PageflowPage newEnd = findPage(toViewId, pageflow);
				newEnd = newEnd == null ? PageflowTransform.getInstance()
						.createPFPage(toViewId) : newEnd;
				if (mapCases2Links.get(notification.getNotifier()) == null) {
					// need to create new link:
					updatePageflowElements(pageflow,
							(NavigationCaseType) ((EObject) notification
									.getNotifier()).eContainer());
				} else {
					NavigationCaseType rule = (NavigationCaseType) ((EObject) notification
							.getNotifier()).eContainer();
					moveEndTo(rule, newEnd, (ToViewIdType) notification
							.getNotifier());
				}
			}
		}

		private void moveEndTo(NavigationCaseType caseFC, PageflowPage newEnd,
				ToViewIdType toViewId) {
			PageflowLink link = (PageflowLink) mapCases2Links.get(caseFC);
			PageflowPage start = (PageflowPage) link.getSource();
			PageflowPage end = (PageflowPage) link.getTarget();
			PageflowLink newLink = null;
			if (newEnd != null) {
				newLink = createPFLink(start, newEnd, caseFC);
			}
			// Remove old link
			link.getTarget().getFCElements().remove(toViewId);
			removeLink(link);
			mapCases2Links.remove(caseFC);
			if (newLink != null) {
				mapCases2Links.put(caseFC, newLink);
			}

			if (end.getOutlinks().size() == 0 && end.getInlinks().size() == 0) {
				removePageflowPage(end);
			}
		}

		private void removePageflowPage(PageflowPage source) {
			Assert.isTrue(source.getOutlinks().size() == 0
					&& source.getInlinks().size() == 0);
			pageflow.getNodes().remove(source);
		}

		private void removeLink(PageflowLink pLink) {
			// link
			pLink.getPageflow().getLinks().remove(pLink);
			pLink.getSource().getOutlinks().remove(pLink);
			pLink.getTarget().getInlinks().remove(pLink);
			pLink.getFCElements().clear();
		}

		public void dispose() {

		}

		public void setTarget(Notifier newTarget) {
			// TODO Auto-generated method stub

		}

	}

	private void refreshPFAdapter(EObject object) {
		TreeIterator rules = object.eAllContents();
		while (rules.hasNext()) {
			Object next = rules.next();
			adapt((EObject) next);
		}

	}

	private void refreshFCAdapter(EObject object) {
		List rules = facesConfig.getNavigationRule();
		for (int i = 0; i < rules.size(); i++) {
			NavigationRuleType rule = (NavigationRuleType) rules.get(i);
			adapt(rule);
			TreeIterator children = rule.eAllContents();
			while (children.hasNext()) {
				adapt((EObject) children.next());
			}
		}
	}

	private static FC2PFTransformer _instance;

	/**
	 * The listener is active.
	 */
	private boolean listenToNotify;

	/**
	 * Avoid redundent event.
	 */
	private boolean isInEvent;

	boolean pageflowChanged = false;

	/**
	 * Catches
	 */
	private Map mapPaths2PF = new HashMap();

	private Map mapCases2Links = new HashMap();

	/**
	 * The pageflow model.
	 */
	private Pageflow pageflow;

	/**
	 * The faces-config model.
	 */
	FacesConfigType facesConfig;

	public void setFacesConfig(FacesConfigType facesConfig) {
		this.facesConfig = facesConfig;
	}

	public void setPageflow(Pageflow pageflow) {
		this.pageflow = pageflow;
	}

	/**
	 * Install compatible synchronization adapter for particular object.
	 * 
	 * @param object
	 */
	public void adapt(EObject object) {
		if (object != null && !isAdapted(object)) {
			if (object instanceof PageflowElement) {
				object.eAdapters().add(new PF2FCSynchronizer());
			} else {
				object.eAdapters().add(new FC2PFSynchronizer());
			}
		}
	}

	/**
	 * Remove the synchronization adapter.
	 * 
	 * @param object
	 */
	public void unAdapt(EObject object) {
		Iterator adapters = object.eAdapters().iterator();
		while (adapters.hasNext()) {
			Object adapter = adapters.next();
			if (adapter instanceof FC2PFSynchronizer
					|| adapter instanceof PF2FCSynchronizer) {
				adapters.remove();
			}
		}
	}

	public boolean isAdapted(EObject object) {
		Iterator adapters = object.eAdapters().iterator();
		while (adapters.hasNext()) {
			Object adapter = adapters.next();
			if (adapter instanceof FC2PFSynchronizer
					|| adapter instanceof PF2FCSynchronizer) {
				return true;
			}
		}
		return false;
	}

	public void clearCaches() {
		mapPaths2PF.clear();
		mapCases2Links.clear();
	}

	public static FC2PFTransformer getInstance() {
		if (_instance == null) {
			_instance = new FC2PFTransformer();
		}
		return _instance;
	}

	public void dispose() {
		clearCaches();
		_instance = null;
	}

	public void setListenToNotify(boolean listenToNotify) {
		this.listenToNotify = listenToNotify;
	}

	public Notifier getTarget() {
		return null;
	}

	public boolean isAdapterForType(Object type) {
		return false;
	}

	public void setTarget(Notifier newTarget) {

	}

	public PageflowPage findPage(String path, Pageflow pageflow) {
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

	public FromViewIdType getSource(NavigationCaseType caseType) {
		if (caseType.eContainer() instanceof NavigationRuleType) {
			NavigationRuleType rule = (NavigationRuleType) caseType
					.eContainer();
			return rule.getFromViewId();
		}
		return null;
	}

	public PageflowNode findCaseEnd(PageflowPage action,
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

	private PageflowPage getOrCreateStartPage(NavigationCaseType navCase) {// FromViewIdType
		PageflowPage startPageflowNode = null;
		String path = "*";
		NavigationRuleType rule = (NavigationRuleType) navCase.eContainer();
		FromViewIdType source = getSource(navCase);
		if (source != null) {
			path = source.getTextContent();
		}
		if (!hasValidCache(path)) {
			startPageflowNode = findPage(path, pageflow);

			if (startPageflowNode == null) {
				// not exist or is "*"
				if (DEBUG)
					System.out.println("Create new start page");
				startPageflowNode = PageflowTransform.getInstance()
						.createPFPage(path);
				pageflowChanged = true;
			}
			mapPaths2PF.put(path, startPageflowNode);
			PageflowTransform.getInstance().updatePageflowSourcePage(
					startPageflowNode, rule);
		} else {
			startPageflowNode = (PageflowPage) mapPaths2PF.get(path);
		}
		if (source != null) {
			RefElement element = new RefElement(startPageflowNode);
			element.add(source);
			startPageflowNode
					.setReferenceLink(element.resolveReferenceString());
			startPageflowNode.getFCElements().add(source);
		}
		return startPageflowNode;
	}

	private boolean hasValidCache(Object key) {
		EObject element = (EObject) mapPaths2PF.get(key);
		boolean result = false;
		while (element != null) {
			if (element == pageflow) {
				result = true;
			}
			element = element.eContainer();
		}
		return result;
	}

	private PageflowPage getOrCreateEndPage(NavigationCaseType navCase) {// ToViewIdType
		// target) {
		PageflowPage endPageflowNode = null;
		ToViewIdType target = navCase.getToViewId();
		if (target != null) {
			String path = target.getTextContent();
			if (!hasValidCache(path)) {
				endPageflowNode = findPage(path, pageflow);
				if (endPageflowNode == null) {
					endPageflowNode = PageflowTransform.getInstance()
							.createPFPage(path);
					if (DEBUG)
						System.out.println("Create new end page");
					pageflowChanged = true;
				}
				mapPaths2PF.put(path, endPageflowNode);
				PageflowTransform.getInstance().updatePageflowTargetPage(
						endPageflowNode, navCase);
			} else {
				endPageflowNode = (PageflowPage) mapPaths2PF.get(path);
			}
			if (target != null) {
				RefElement element = new RefElement(endPageflowNode);
				element.add(target);
				endPageflowNode.setReferenceLink(element
						.resolveReferenceString());
				endPageflowNode.getFCElements().add(target);
			}
		}
		return endPageflowNode;
	}

	/**
	 * Create pageflow model element from faces-config navigation case. A case
	 * might be corresponding to: 1. Page-action-page. 2. Page-page. 3. *-page.
	 * 4. ? page-action-action-page?
	 */
	public boolean updatePageflowElements(Pageflow pageflow,
			NavigationCaseType caseFC) {
		this.pageflowChanged = false;
		this.pageflow = pageflow;
		ToViewIdType target = caseFC.getToViewId();
		if (target == null) {
			return false;
		}

		PageflowPage start = null, end = null;
		// start page
		start = getOrCreateStartPage(caseFC);
		String action = null;
		// action
		if (caseFC.getFromAction() != null) {
			action = caseFC.getFromAction().getTextContent();
		}
		// from outcome
		String fromOutCome = null;
		if (caseFC.getFromOutcome() != null) {
			fromOutCome = caseFC.getFromOutcome().getTextContent();
		}
		// end page
		end = getOrCreateEndPage(caseFC);
		// link
		// Is there a link in pageflow model?
		PageflowLink link = null;
		if ((link = findUnmappedLink(start, end, caseFC)) == null) {
			if (end != null) {
				if (DEBUG)
					System.out.println("Create new link");
				link = createPFLink(start, end, action, fromOutCome);
				pageflowChanged = true;
			}
		}
		if (link != null) {
			link.getFCElements().clear();
			link.getFCElements().add(caseFC);
			mapCases2Links.put(caseFC, link);
		}
		adapt(start);
		adapt(end);
		adapt(caseFC);
		adapt(link);
		return pageflowChanged;
	}

	/**
	 * Find a pageflow link that is not linked to a faces-config element. Since
	 * user might edit faces-config file with other editor, so the path is not
	 * always valid, here we still try to match link and navigation case. FIXME:
	 * should we always resolve mapping from referenceLink on PageflowElement?
	 */
	private PageflowLink findUnmappedLink(PageflowPage start, PageflowPage end,
			NavigationCaseType caseFC) {
		// Assert.isTrue(!mapCases2Links.containsKey(caseFC));
		List links = start.getOutlinks();
		for (int i = 0, n = links.size(); i < n; i++) {
			PageflowLink link = (PageflowLink) links.get(i);
			if (link.getSource() == start && link.getTarget() == end) {
				if (link.getFCElements().isEmpty()) {
					// if (isSame(link.getFromaction(),
					// (caseFC.getFromAction() != null ? caseFC
					// .getFromAction().getTextContent() : null))
					// && isSame(link.getOutcome(), (caseFC
					// .getFromOutcome() != null ? caseFC
					// .getFromOutcome().getTextContent() : null)))
					// return link;
					// } else {
					return link;
				}
			}
		}
		return null;
	}

	private boolean isSame(Object first, Object second) {
		return (first == null && second == null || first != null
				&& second != null && first.equals(second));
	}

	public FromViewIdType createRLFromViewID(String value) {
		FromViewIdType fromView = FacesConfigFactory.eINSTANCE
				.createFromViewIdType();
		((FromViewIdType) fromView).setTextContent(value);
		return fromView;
	}

	public PageflowLink createPFLink(PageflowNode start, PageflowNode target,
			NavigationCaseType caseFC) {
		PageflowLink link = null;
		String fromOutcome = caseFC.getFromOutcome() != null ? caseFC
				.getFromOutcome().getTextContent() : null;
		String action = caseFC.getFromAction() != null ? caseFC.getFromAction()
				.getTextContent() : null;
		NavigationRuleType rule = (NavigationRuleType) caseFC.eContainer();
		link = createPFLink(fromOutcome);
		link.setFromaction(action);
		link.setSource(start);
		link.setTarget(target);
		start.getOutlinks().add(link);
		target.getInlinks().add(link);
		// The reference.
		link.getFCElements().add(caseFC);
		start.getFCElements().add(rule.getFromViewId());
		target.getFCElements().add(caseFC.getToViewId());
		return link;
	}

	/**
	 * create a new PFLink object according to fromOutcome attribute
	 * 
	 * @param fromOutcome -
	 *            PFLink's fromOutcome attribute
	 * @return - new PFLink object
	 */
	public PageflowLink createPFLink(PageflowNode start, PageflowNode target,
			String action, String fromOutcome) {
		Assert.isTrue(start != null && target != null);
		PageflowLink link = null;
		link = createPFLink(fromOutcome);
		link.setFromaction(action);
		link.setSource(start);
		link.setTarget(target);
		start.getOutlinks().add(link);
		target.getInlinks().add(link);
		return link;
	}

	/**
	 * create a new PFLink object according to fromOutcome attribute
	 * 
	 * @param fromOutcome -
	 *            PFLink's fromOutcome attribute
	 * @return - new PFLink object
	 */
	public PageflowLink createPFLink(String fromOutcome) {
		PageflowLink link = null;
		link = PageflowModelManager.getFactory().createPFLink();
		if (fromOutcome != null && fromOutcome.length() > 0) {
			link.setOutcome(fromOutcome.trim());
		}
		pageflow.getLinks().add(link);
		return link;
	}

	public ToViewIdType createFCToViewID(String value) {
		ToViewIdType toView = FacesConfigFactory.eINSTANCE.createToViewIdType();
		toView.setTextContent(value);
		return toView;
	}
}
