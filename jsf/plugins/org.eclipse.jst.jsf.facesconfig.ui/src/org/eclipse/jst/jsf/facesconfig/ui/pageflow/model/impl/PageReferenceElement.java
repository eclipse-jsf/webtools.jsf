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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.util.Assert;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.FC2PFTransformer;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.TransformUtil;

/**
 * The reference element represents a mapping from pageflow page to one or more
 * navigation rules/cases's view IDs (fromViewID or toViewID).
 * 
 * @author hmeng
 */

public class PageReferenceElement extends ReferenceElement {

	public PageReferenceElement(PageflowElement pageflowElement) {
		super(pageflowElement);
		// TODO Auto-generated constructor stub
	}

	public PageReferenceElement(PageflowElement pageflowElement,
			EObject facesConfigObject) {
		super(pageflowElement, facesConfigObject);
		// TODO Auto-generated constructor stub
	}

	public void add(EObject object) {
		Assert.isTrue(object instanceof FromViewIdType
				|| object instanceof ToViewIdType);
		super.add(object);
	}

	public void update() {
		outer: for (Iterator nodes = getData().iterator(); nodes.hasNext();) {
			Object next = nodes.next();
			if (next instanceof FromViewIdType) {
				if (!TransformUtil.isValidFacesConfigElement((EObject) next)) {
					nodes.remove();
					continue;
				}
				List links = ((PageflowPage) pageflowElement).getOutlinks();
				for (int i = 0; i < links.size(); i++) {
					PageflowLink link = (PageflowLink) links.get(i);
					if (!link.getFCElements().isEmpty()
							&& TransformUtil
									.isValidFacesConfigElement((NavigationCaseType) ((PageflowLink) links
											.get(i)).getFCElements().getData()
											.get(0))) {
						NavigationRuleType rule = (NavigationRuleType) ((NavigationCaseType) ((PageflowLink) links
								.get(i)).getFCElements().getData().get(0))
								.eContainer();
						if (rule == null || rule.getFromViewId() == next) {
							// There navigation case
							// matching this end.
							continue outer;
						}
					}
				}
				// No case contains this end.
				nodes.remove();
			} else if (next instanceof ToViewIdType) {
				if (!TransformUtil.isValidFacesConfigElement((EObject) next)) {
					nodes.remove();
					continue;
				}
				// Verify this is case end.
				List links = ((PageflowPage) pageflowElement).getInlinks();
				for (int i = 0; i < links.size(); i++) {
					PageflowLink link = (PageflowLink) links.get(i);
					if (!link.getFCElements().isEmpty()
							&& TransformUtil
									.isValidFacesConfigElement((NavigationCaseType) link
											.getFCElements().getData().get(0))) {
						if (((NavigationCaseType) ((PageflowLink) links.get(i))
								.getFCElements().getData().get(0))
								.getToViewId() == next) {
							// There navigation case
							// matching this end.
							continue outer;
						}
					}
				}
				// No case contains this end.
				nodes.remove();
			}
		}
	}

	public Object get(int eFeature) {
		Object result = null;
		switch (eFeature) {
		case PageflowPackage.PF_PAGE__COMMENT:
			if (!isEmpty()) {
				for (int i = 0, n = getData().size(); i < n; i++) {
					NavigationRuleType rule = null;
					if ((rule = resolveRuleFromFCElement(getData().get(i))) != null) {
						List descriptions = rule.getDescription();
						if (descriptions.size() > 0) {
							result = ((DescriptionType) descriptions.get(0))
									.getTextContent();
							break;
						}
					}
				}
			}
			break;
		case PageflowPackage.PF_PAGE__LARGEICON:
			if (!isEmpty()) {
				for (int i = 0, n = getData().size(); i < n; i++) {
					NavigationRuleType rule = null;
					if ((rule = resolveRuleFromFCElement(getData().get(i))) != null) {
						List icons = rule.getIcon();
						if (icons.size() > 0) {
							result = ((IconType) icons.get(0)).getLargeIcon() != null ? ((IconType) icons
									.get(0)).getLargeIcon().getTextContent()
									: null;
							break;
						}
					}
				}
			}
			break;
		case PageflowPackage.PF_PAGE__NAME:
			// To see if it is in navigation rule.
			if (!isEmpty() && !isEndOnly()) {
				for (int i = 0, n = getData().size(); i < n; i++) {
					NavigationRuleType rule = null;
					if ((rule = resolveRuleFromFCElement(getData().get(i))) != null) {
						List displaynames = rule.getDisplayName();
						if (displaynames.size() > 0) {
							result = ((DisplayNameType) displaynames.get(0))
									.getTextContent();
							break;
						}
					}
				}
			}
			break;
		case PageflowPackage.PF_PAGE__PATH:
			if (!isEmpty()) {
				for (int i = 0, n = getData().size(); i < n; i++) {
					if (getData().get(i) instanceof FromViewIdType) {
						result = ((FromViewIdType) getData().get(i))
								.getTextContent();
						break;
					}
				}
				if (result == null && getData().get(0) instanceof ToViewIdType) {
					result = ((ToViewIdType) getData().get(0)).getTextContent();
				}
			}
			break;
		case PageflowPackage.PF_PAGE__SMALLICON:
			if (!isEmpty()) {
				for (int i = 0, n = getData().size(); i < n; i++) {
					NavigationRuleType rule = null;
					if ((rule = resolveRuleFromFCElement(getData().get(i))) != null) {
						List icons = rule.getIcon();
						if (icons.size() > 0) {
							result = ((IconType) icons.get(0)).getSmallIcon() != null ? ((IconType) icons
									.get(0)).getSmallIcon().getTextContent()
									: null;
							break;
						}
					}
				}
			}
			break;
		}
		return result;
	}

	public NavigationRuleType resolveRuleFromFCElement(Object object) {
		if (object instanceof FromViewIdType) {
			return (NavigationRuleType) ((FromViewIdType) object).eContainer();
		}
		return null;
	}

	public NavigationRuleType resolveRuleFromPFElement(Object object) {
		if (object instanceof PageflowLink) {
			NavigationCaseType caseType = (NavigationCaseType) ((PageflowLink) object)
					.getFCElements().getData().get(0);
			return (NavigationRuleType) caseType.eContainer();
		}
		return null;
	}

	public void set(EStructuralFeature eFeature, Object newVal) {
		switch (eFeature.getFeatureID()) {
		case PageflowPackage.PF_PAGE__COMMENT: {
			DescriptionType description = null;
			String newValue = (String) newVal;
			if (!isEmpty()) {
				for (int i = 0, n = getData().size(); i < n; i++) {
					NavigationRuleType rule = null;
					if ((rule = resolveRuleFromFCElement(getData().get(i))) != null) {
						rule.getDescription().clear();
						description = FacesConfigFactory.eINSTANCE
								.createDescriptionType();
						description.setTextContent(newValue);
						rule.getDescription().add(description);
					}
				}
			}
			break;
		}
		case PageflowPackage.PF_PAGE__LARGEICON: {
			LargeIconType oldLargeIcon = null;
			LargeIconType newLargeIconType = null;
			IconType icon = null;
			String newValue = (String) newVal;
			if (!isEmpty()) {
				for (int i = 0, n = getData().size(); i < n; i++) {
					NavigationRuleType rule = null;
					if ((rule = resolveRuleFromFCElement(getData().get(i))) != null) {
						List icons = rule.getIcon();
						if (newValue == null || newValue.length() == 0) {
							if (icons.size() > 0) {
								if (((IconType) icons.get(0)).getLargeIcon() != null) {
									((IconType) icons.get(0))
											.setLargeIcon(null);
								}
							}
						} else {
							if (icons.size() == 0) {
								icon = FacesConfigFactory.eINSTANCE
										.createIconType();
								newLargeIconType = FacesConfigFactory.eINSTANCE
										.createLargeIconType();
								newLargeIconType.setTextContent(newValue);
								icon.setLargeIcon(newLargeIconType);
								icons.add(icon);
							} else if (((IconType) icons.get(0)).getLargeIcon() == null) {
								newLargeIconType = FacesConfigFactory.eINSTANCE
										.createLargeIconType();
								newLargeIconType.setTextContent(newValue);
								icon = ((IconType) icons.get(0));
								icon.setLargeIcon(newLargeIconType);
							} else {
								oldLargeIcon = ((IconType) icons.get(0))
										.getLargeIcon();
								oldLargeIcon.setTextContent(newValue);
							}
						}
					}
				}
			}
			break;
		}
		case PageflowPackage.PF_PAGE__NAME:
			DisplayNameType newDisplayNameType = null;
			String newName = (String) newVal;
			if (!isEmpty()) {
				for (int i = 0, n = getData().size(); i < n; i++) {
					NavigationRuleType rule = null;
					if ((rule = resolveRuleFromFCElement(getData().get(i))) != null) {
						rule.getDisplayName().clear();
						if (newName != null && newName.length() > 0) {
							newDisplayNameType = FacesConfigFactory.eINSTANCE
									.createDisplayNameType();
							newDisplayNameType.setTextContent(newName);
							rule.getDisplayName().add(newDisplayNameType);
						}
					}
				}
			}
			break;
		case PageflowPackage.PF_PAGE__PATH: {
			Object oldPath = null;
			Object newPath = null;
			String newValue = (String) newVal;
			if (!isEmpty()) {
				for (int i = 0, n = getData().size(); i < n; i++) {
					if (getData().get(i) instanceof FromViewIdType) {
						oldPath = getData().get(i);
						((FromViewIdType) oldPath).setTextContent(newValue);
					} else if (getData().get(i) instanceof ToViewIdType) {
						oldPath = getData().get(i);
						((ToViewIdType) oldPath).setTextContent(newValue);
					}
				}
			}
			// Create new fromViewID or toViewID node as needed.
			else if (newValue != null && !"*".equals(newValue)) {
				if (((PageflowPage) pageflowElement).getOutlinks().size() > 0) {
					List links = ((PageflowPage) pageflowElement).getOutlinks();
					for (int i = 0, n = links.size(); i < n; i++) {
						PageflowLink link = (PageflowLink) links.get(i);
						NavigationRuleType rule = resolveRuleFromPFElement(link);
						newPath = FC2PFTransformer.createRLFromViewID(newValue);
						rule.setFromViewId((FromViewIdType) newPath);
						add((EObject) newPath);
					}
					links = ((PageflowPage) pageflowElement).getInlinks();
					for (int i = 0, n = links.size(); i < n; i++) {
						PageflowLink link = (PageflowLink) links.get(i);
						NavigationCaseType caseType = (NavigationCaseType) link
								.getFCElements().getData().get(0);
						newPath = FC2PFTransformer.createFCToViewID(newValue);
						caseType.setToViewId((ToViewIdType) newPath);
						add((EObject) newPath);
					}
				}
			}
			break;
		}
		case PageflowPackage.PF_PAGE__SMALLICON: {
			SmallIconType oldSmallicon = null;
			SmallIconType newSmallIconType = null;
			IconType icon = null;
			String newValue = (String) newVal;
			if (!isEmpty()) {
				for (int i = 0, n = getData().size(); i < n; i++) {
					NavigationRuleType rule = null;
					if ((rule = resolveRuleFromFCElement(getData().get(i))) != null) {
						List icons = rule.getIcon();
						if (newValue == null || newValue.length() == 0) {
							if (icons.size() > 0) {
								if (((IconType) icons.get(0)).getSmallIcon() != null) {
									((IconType) icons.get(0))
											.setSmallIcon(null);
								}
							}
						} else {
							if (icons.size() == 0) {
								icon = FacesConfigFactory.eINSTANCE
										.createIconType();
								newSmallIconType = FacesConfigFactory.eINSTANCE
										.createSmallIconType();
								newSmallIconType.setTextContent(newValue);
								icon.setSmallIcon(newSmallIconType);
								icons.add(icon);
							} else if (((IconType) icons.get(0)).getSmallIcon() == null) {
								newSmallIconType = FacesConfigFactory.eINSTANCE
										.createSmallIconType();
								newSmallIconType.setTextContent(newValue);
								icon = ((IconType) icons.get(0));
								icon.setSmallIcon(newSmallIconType);
							} else {
								oldSmallicon = ((IconType) icons.get(0))
										.getSmallIcon();
								oldSmallicon.setTextContent(newValue);
							}
						}
					}
				}
			}
			break;
		}
		}
	}

	public boolean isEndOnly() {
		for (int i = 0, n = getData().size(); i < n; i++) {
			if (getData().get(i) instanceof FromViewIdType) {
				return false;
			}
		}
		return true;
	}
}
