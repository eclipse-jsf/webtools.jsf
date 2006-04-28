/**
 * Confidential Property of Sybase, Inc. 
 * (c) Copyright Sybase, Inc. 2004-2006. 
 * All rights reserved.
 */
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowTransform;

/**
 * Update and synchronize pageflow from faces config or vice-versa.
 * 
 * @author hmeng
 * 
 */

public class FC2PFTransformer implements Adapter {
	private boolean listenToNotify;

	PF2FCSynchronizer pf2FCSynchronizer;

	FC2PFSynchronizer fc2PFSynchronizer;

	private boolean isInEvent;

	private Map mapCases2Links = new HashMap();

	private Map mapLinks2Cases = new HashMap();

	public Map getMapCases2Links() {
		return mapCases2Links;
	}

	public Map getMapLinks2Cases() {
		return mapLinks2Cases;
	}

	public void facesConfigNotifyChanged(Notification notification) {
		getFc2PFSynchronizer().notifyChanged(notification);
	}

	private static FC2PFTransformer _instance;

	private Map mapPaths2FC = new HashMap();

	private Map mapPaths2PF = new HashMap();

	boolean newSource = false;

	boolean newTarget = false;

	boolean newAction = false;

	boolean dirty = false;

	private Pageflow pageflow;

	FacesConfigType facesConfig;

	public void setFacesConfig(FacesConfigType facesConfig) {
		this.facesConfig = facesConfig;
	}

	public void setPageflow(Pageflow pageflow) {
		this.pageflow = pageflow;
	}

	public void clearCaches() {
		mapPaths2FC.clear();
		mapPaths2PF.clear();
		mapCases2Links.clear();
		mapLinks2Cases.clear();
	}

	/**
	 * The map from PFLink - Cases Use case.getContainer to get rule.
	 */
	private Map cases2LinksMap = new HashMap();

	public static FC2PFTransformer getInstance() {
		if (_instance == null) {
			_instance = new FC2PFTransformer();
		}
		return _instance;
	}

	public void dispose() {
		clearCaches();
		getFc2PFSynchronizer().dispose();
		getPf2FCSynchronizer().dispose();
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

	public void notifyChanged(Notification notification) {
		if (listenToNotify && !isInEvent) {
			isInEvent = true;
			if (notification.getNotifier() instanceof PageflowElement) {
				pageflowNotifyChanged(notification);
			} else {
				facesConfigNotifyChanged(notification);
				// PageflowLayoutManager.getInstance().layoutPageflow(pageflow);
			}
			isInEvent = false;
		}
	}

	public void setTarget(Notifier newTarget) {

	}

	public void pageflowNotifyChanged(Notification notification) {
		getPf2FCSynchronizer().notifyChanged(notification);
	}

	public PFPage findPage(String path, Pageflow pageflow) {
		List nodes = pageflow.getNodes();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i) instanceof PFPage) {
				if (path != null) {
					RefElement element = new RefElement(((PFPage) nodes.get(i))
							.getReferenceLink());
					if (path.equals(((PFPage) nodes.get(i)).getPath())) {
						return (PFPage) nodes.get(i);
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

	public PageflowNode findCaseEnd(PFPage action, FromOutcomeType outcome,
			Pageflow pageflow) {
		// TODO: find a case end in pageflow model
		List links = action.getOutlinks();
		for (int i = 0; i < links.size(); i++) {
			PFLink link = (PFLink) links.get(i);
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

	// public PFAction findCaseAction(PFPage page, String action, Pageflow
	// pageflow) {
	// // TODO: find an action in pageflow model
	// // FromActionType action = caseFC.getFromAction();
	// List links = page.getOutlinks();
	// for (int i = 0; i < links.size(); i++) {
	// PFLink link = (PFLink) links.get(i);
	// if (link.getTarget() instanceof PFAction) {
	// if (((PFAction) link.getTarget()).getName().equals(action)) {
	// return (PFAction) link.getTarget();
	// }
	// }
	// }
	// return null;
	// }

	/**
	 * Delete a pageflow rule.
	 * 
	 * @param rulePF
	 */
	public void deletePageflowRule(NavigationRuleType rulePF) {
		NavigationCaseType casePF;
		for (Iterator iterCasesPF = rulePF.getNavigationCase().iterator(); iterCasesPF
				.hasNext();) {
			casePF = (NavigationCaseType) iterCasesPF.next();
			deletePageflowCase(casePF);
		}
	}

	/**
	 * Delete a pageflow case, the float node should be removed also.
	 * 
	 * @param rulePF
	 */

	public void deletePageflowCase(NavigationCaseType casePF) {
		Object link = cases2LinksMap.get(casePF);
		// if (link instanceof LinksBundle) {
		// LinksBundle linkBundle = ((LinksBundle) link);
		// if (!linkBundle.isPage2Page()) {
		// removeLink(linkBundle.getFirst());
		// PageflowNode sourcePage = linkBundle.getFirst().getSource();
		// PageflowNode targetPage = linkBundle.getFirst().getTarget();
		// // if it is float, remove it.
		// removePageflowNode(sourcePage);
		// removePageflowNode(targetPage);
		// sourcePage.getPageflow().getLinks().remove(link);
		// } else {
		// removeLink(linkBundle.getFirst());
		// removeLink(linkBundle.getSecond());
		// PageflowNode action = linkBundle.getFirst().getTarget();
		// if (action.getInlinks().size() == 0) {
		// action.getPageflow().getLinks().remove(
		// linkBundle.getSecond());
		// }
		// if (action.getOutlinks().size() == 0) {
		// action.getPageflow().getLinks().remove(
		// linkBundle.getFirst());
		// }
		// PageflowNode sourcePage = linkBundle.getFirst().getSource();
		// PageflowNode targetPage = linkBundle.getSecond().getTarget();
		// // if it is float, remove it.
		// removePageflowNode(sourcePage);
		// removePageflowNode(targetPage);
		// removePageflowNode(action);
		// }
		// links2CasesMap.remove(linkBundle);
		// }

	}

	// private boolean removePageflowNode(PageflowNode node) {
	// if (node.getOutlinks().size() == 0 && node.getInlinks().size() == 0) {
	// node.getPageflow().getNodes().remove(node);
	// return true;
	// }
	// return false;
	// }
	//
	// private void removeLink(PFLink link) {
	// PageflowNode source = link.getSource();
	// PageflowNode target = link.getTarget();
	// source.getOutlinks().remove(link);
	// target.getInlinks().remove(link);
	// }
	//
	// /**
	// * get the model creation factory of faces-config 1.0
	// *
	// * @return - the model creation factory of faces-config 1.0
	// */
	// private FacesConfigFactory getFacesConfigFactory() {
	// return FacesConfigFactory.eINSTANCE;
	// }

	/**
	 * check whether these two fromviewid is same or not.
	 * 
	 * @param fromViewID1
	 * @param fromViewID2
	 * @return
	 */
	// private boolean hasSameFromViewID(FromViewIdType fromViewID1,
	// FromViewIdType fromViewID2) {
	// String viewID1 = null;
	// if (fromViewID1 == null) {
	// viewID1 = "*";
	// } else {
	// viewID1 = fromViewID1.getTextContent().trim();
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
	 * get or create a navigation rule
	 * 
	 * @param fromViewIdPath
	 * @param navigationRulesInPageflow
	 * @return
	 */
	// private NavigationRuleType getNavigationRule(String fromViewIdPath,
	// List navigationRulesInPageflow) {
	// // create or get a new navigation rule
	// NavigationRuleType navigationRule = null;
	//
	// FacesConfigFactory factory = getFacesConfigFactory();
	// // set the from-view-id
	// FromViewIdType fromViewId = factory.createFromViewIdType();
	// fromViewId.setTextContent(fromViewIdPath);
	// // found the navigation rule with the same from-view-id
	// boolean bFound = false;
	// for (Iterator iter = navigationRulesInPageflow.iterator(); iter
	// .hasNext()
	// && !bFound;) {
	// NavigationRuleType rule = (NavigationRuleType) iter.next();
	// if (hasSameFromViewID(fromViewId, rule.getFromViewId())) {
	// navigationRule = rule;
	// bFound = true;
	// }
	// }
	// // if not found, create a new one.
	// if (!bFound) {
	// // create a new navigation rule
	// navigationRule = factory.createNavigationRuleType();
	//
	// // set the from-view-id
	// FromViewIdType fromViewid = factory.createFromViewIdType();
	// fromViewid.setTextContent(fromViewIdPath);
	// navigationRule.setFromViewId(fromViewid);
	//
	// navigationRulesInPageflow.add(navigationRule);
	// }
	// return navigationRule;
	// }
	// private PFAction getOrCreateAction(NavigationCaseType caseFC, PFPage
	// start,
	// boolean oldRule) {
	// PFAction pfAction = null;
	// FromActionType action = caseFC.getFromAction();
	// String actionPath = action.getTextContent();
	// if (oldRule) {
	// if (caseFC.getFromAction() != null) {
	// // pfAction = (PFAction) mapPaths2PF.get(path);
	// // try pageflow for valid action.
	// if ((pfAction = findCaseAction(start, actionPath, pageflow)) == null) {
	// System.out.println("Create a new action");
	// pfAction = PageflowTransform.getInstance().createPFAction(
	// action.getTextContent());
	// newAction = true;
	// Vector list = new Vector();
	// list.add(action);
	// mapPaths2PF.put(actionPath, pfAction);
	// mapPaths2FC.put(actionPath, list);
	// }
	// }
	// } else {
	// System.out.println("new page, Create a new action");
	// pfAction = PageflowTransform.getInstance().createPFAction(
	// action.getTextContent());
	// newAction = true;
	// Vector list = new Vector();
	// list.add(action);
	// mapPaths2PF.put(actionPath, pfAction);
	// mapPaths2FC.put(actionPath, list);
	// }
	// return pfAction;
	// }
	private PFPage getOrCreateStartPage(NavigationCaseType navCase) {// FromViewIdType
		PFPage startPFNode = null;
		String path = "*";
		NavigationRuleType rule = (NavigationRuleType) navCase.eContainer();
		FromViewIdType source = getSource(navCase);
		if (source != null) {
			// ((CompatibilityXMIResource) source.eContainer().eResource())
			// .setFormat(CompatibilityXMIResource.FORMAT_EMF1);
			// uriSource = source.eContainer().eResource().getURIFragment(
			// source.eContainer());
			path = source.getTextContent();
		}
		if (!mapPaths2PF.containsKey(path)) {
			startPFNode = findPage(path, pageflow);

			if (startPFNode == null) {
				// not exist or is "*"
				// System.out.println("Create new start page");
				startPFNode = PageflowTransform.getInstance()
						.createPFPage(path);
				// PageflowTransform.getInstance().updatePageflowSourcePage(start,
				// rule);
				newSource = true;
			}
			// FIXME: not only one!
			// start.setReferenceLink(uriSource);
			// mapLinks2PF.put(uriSource, start);
			// start = PageflowTransform.getInstance().createPFPage(path);
			// start.setReferenceLink("");
			// mapLinks2URI.put(uriSource, path);
			Vector list = new Vector();
			list.add(source);
			mapPaths2PF.put(path, startPFNode);
			mapPaths2FC.put(path, list);
		} else {
			startPFNode = (PFPage) mapPaths2PF.get(path);
		}
		RefElement element = new RefElement(startPFNode.getReferenceLink());
		element.add(source);
		startPFNode.setReferenceLink(element.resolveReferenceString());
		startPFNode.getFCElements().add(source);
		startPFNode.setInitPath(path);
		PageflowTransform.getInstance().updatePageflowSourcePage(startPFNode,
				rule);
		return startPFNode;
	}

	private PFPage getOrCreateEndPage(NavigationCaseType navCase) {// ToViewIdType
		// target) {
		ToViewIdType target = navCase.getToViewId();
		if (target != null) {
			String path = target.getTextContent();
			PFPage endFCNode;
			if (!mapPaths2PF.containsKey(path)) {
				endFCNode = findPage(path, pageflow);
				if (endFCNode == null) {
					endFCNode = PageflowTransform.getInstance().createPFPage(
							path);
					// PageflowTransform.getInstance().updatePageflowTargetPage(end,
					// navCase);
					// System.out.println("Create new end page");
					newTarget = true;
				}
				// FIXME: not only one!
				mapPaths2PF.put(path, endFCNode);
				// mapLinks2URI.put(uriTarget, path);
				Vector list = new Vector();
				list.add(target);
				mapPaths2FC.put(path, list);
			} else {
				endFCNode = (PFPage) mapPaths2PF.get(path);
			}
			RefElement element = new RefElement(endFCNode.getReferenceLink());
			element.add(target);
			endFCNode.setReferenceLink(element.resolveReferenceString());
			endFCNode.getFCElements().add(target);
			endFCNode.setInitPath(path);
			PageflowTransform.getInstance().updatePageflowTargetPage(endFCNode,
					navCase);
			return endFCNode;
		}
		return null;
	}

	/**
	 * Create pageflow model element from faces-config navigation case. A case
	 * might be corresponding to: 1. Page-action-page. 2. Page-page. 3. *-page.
	 * 4. ? page-action-action-page?
	 */
	public boolean createPFElements(Pageflow pageflow, NavigationCaseType caseFC) {
		this.newSource = false;
		this.newAction = false;
		this.newTarget = false;
		this.pageflow = pageflow;
		ToViewIdType target = caseFC.getToViewId();
		if (target == null) {
			return false;
		}

		PFPage start = null, end = null;
		// ------------------------------------start page ------------------
		start = getOrCreateStartPage(caseFC);
		String action = null;
		// -------------------------------action--------------------------
		if (caseFC.getFromAction() != null) {
			action = caseFC.getFromAction().getTextContent();
		}
		// -------------------------------------from outcome ---------
		String fromOutCome = null;
		if (caseFC.getFromOutcome() != null) {
			fromOutCome = caseFC.getFromOutcome().getTextContent();
		}
		// ---------------------------------------end page------------------
		end = getOrCreateEndPage(caseFC);
		// ----------------------link------------------------
		// Is there a link in pageflow model?
		PFLink link = null;
		if ((link = findLink(start, end, action, fromOutCome, caseFC)) == null) {
			link = PageflowTransform.getInstance().createPFLink(start, end,
					action, fromOutCome);
			dirty = true;
		}
		RefElement element = new RefElement(link.getReferenceLink());
		element.add(caseFC);
		link.setReferenceLink(element.resolveReferenceString());
		link.getFCElements().add(caseFC);
		mapCases2Links.put(caseFC, link);
		mapLinks2Cases.put(link, caseFC);
		dirty |= newTarget | newAction | newSource;
		return dirty;
	}

	// private PFLink findUnusedLink(List secondLinks) {
	// PFLink link = null;
	// for (int i = 0; i < secondLinks.size(); i++) {
	// CaseLink cLink = new CaseLink((PFLink) secondLinks.get(i));
	// if (!mapLinks2Cases.containsKey(cLink)) {
	// link = (PFLink) secondLinks.get(i);
	// break;
	// }
	// }
	// return link;
	// }

	// private PFLink createLink(PageflowNode start, PageflowNode target,
	// String fromAction, String fromOutcome) {
	// PFLink result = null;
	// if (target instanceof PFPage) {
	// if (start instanceof PFAction) {
	// result = PageflowTransform.getInstance().createPFLink(start,
	// target, fromOutcome);
	// result.set
	// } else if (start instanceof PFPage) {
	// result = PageflowTransform.getInstance().createPFLink(start,
	// target, fromOutcome);
	// }
	// }
	// // Page -> action
	// else {
	// result = PageflowTransform.getInstance().createPFLink(
	// start, target, fromOutcome);
	// }
	// }

	// private PFLink findPage2PageLink(PFPage start, PFPage target,
	// String fromOutCome) {
	// List links = start.getOutlinks();
	// for (int i = 0, n = links.size(); i < n; i++) {
	// if ((((PFLink) links.get(i)).getOutcome() == null && fromOutCome == null)
	// || (((PFLink) links.get(i)).getOutcome() != null
	// && fromOutCome != null && ((PFLink) links.get(i))
	// .getOutcome().equals(fromOutCome))) {
	// if (target.getPath().equals(
	// ((PFPage) ((PFLink) links.get(i)).getTarget())
	// .getPath())) {
	// return (PFLink) links.get(i);
	// }
	// }
	// }
	// return null;
	// }

	private PFLink findLink(PFPage start, PFPage end, String action,
			String fromOutCome, NavigationCaseType caseFC) {
		List links = start.getOutlinks();
		for (int i = 0, n = links.size(); i < n; i++) {
			PFLink link = (PFLink) links.get(i);
			if (link.getReferenceLink() != null
					&& link.getReferenceLink().length() > 0) {
				String ref = link.getReferenceLink();
				RefElement element = new RefElement(ref);
				if (element.getRefLinks().get(0).equals(
						RefElement.resolveURI(caseFC)))
					// if (isSame(link.getOutcome(), fromOutCome)
					// && isSame(action, link.getFromaction())) {
					if (end.getPath() != null
							&& end.getPath().equals(
									((PFPage) link.getTarget()).getPath())
							&& !mapLinks2Cases.containsKey(link)) {
						return link;
					}
			}
		}
		return null;
	}

	private boolean isSame(String first, String second) {
		return (first == null && second == null || first != null
				&& second != null && first.equals(second));
	}

	// private PFLink findFirstLink(PFPage start, PFAction end) {
	// List links = start.getOutlinks();
	// for (int i = 0, n = links.size(); i < n; i++) {
	// PFLink link = (PFLink) links.get(i);
	// if (link.getSource() == start && link.getTarget() == end) {
	// return link;
	// }
	// }
	// return null;
	// }

	public FC2PFSynchronizer getFc2PFSynchronizer() {
		if (fc2PFSynchronizer == null) {
			fc2PFSynchronizer = new FC2PFSynchronizer(pageflow, facesConfig);
		}
		return fc2PFSynchronizer;
	}

	public PF2FCSynchronizer getPf2FCSynchronizer() {
		if (pf2FCSynchronizer == null) {
			pf2FCSynchronizer = new PF2FCSynchronizer(pageflow, facesConfig);
		}
		return pf2FCSynchronizer;
	}
}
