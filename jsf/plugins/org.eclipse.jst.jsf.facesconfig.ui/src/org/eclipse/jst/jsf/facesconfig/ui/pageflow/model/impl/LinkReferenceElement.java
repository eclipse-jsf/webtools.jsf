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
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FromActionType;
import org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.RedirectType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.TransformUtil;

/**
 * The reference element represents a mapping from pageflow link to navigation
 * rule
 * 
 * @author hmeng
 */

public class LinkReferenceElement extends ReferenceElement {

	public void add(EObject object) {
		Assert.isTrue(object instanceof NavigationCaseType);
		clear();
		super.add(object);
	}

	public LinkReferenceElement(PageflowElement pageflowElement) {
		super(pageflowElement);
		// TODO Auto-generated constructor stub
	}

	public LinkReferenceElement(PageflowElement pageflowElement,
			EObject facesConfigObject) {
		super(pageflowElement, facesConfigObject);
		// TODO Auto-generated constructor stub
	}

	public void update() {
		for (Iterator nodes = getData().iterator(); nodes.hasNext();) {
			Object next = nodes.next();
			if (!TransformUtil.isValidFacesConfigElement((EObject) next)) {
				nodes.remove();
			}
		}
	}

	public Object get(int eFeature) {
		Object result = null;
		switch (eFeature) {
		case PageflowPackage.PF_LINK__COMMENT:
			break;
		case PageflowPackage.PF_LINK__OUTCOME:
			if (!isEmpty()) {
				FromOutcomeType outcome = ((NavigationCaseType) getData()
						.get(0)).getFromOutcome();
				if (outcome != null) {
					result = outcome.getTextContent();
				}
			}
			break;
		case PageflowPackage.PF_LINK__NAME:
			break;
		case PageflowPackage.PF_LINK__REDIRECT:
			result = new Boolean(false);
			if (!isEmpty()) {
				RedirectType outcome = ((NavigationCaseType) getData().get(0))
						.getRedirect();
				if (outcome != null) {
					result = new Boolean(true);
				}
			}
			break;

		case PageflowPackage.PF_LINK__FROMACTION:
			if (!isEmpty()) {
				FromActionType fromActionType = ((NavigationCaseType) getData()
						.get(0)).getFromAction();
				if (fromActionType != null) {
					result = fromActionType.getTextContent();
				}
			}
			break;
		case PageflowPackage.PF_LINK__SMALLICON:
			if (!isEmpty()) {
				List icons = ((NavigationCaseType) getData().get(0)).getIcon();
				if (icons.size() > 0) {
					result = ((IconType) icons.get(0)).getSmallIcon() != null ? ((IconType) icons
							.get(0)).getSmallIcon().getTextContent()
							: null;
				}
			}
			break;
		case PageflowPackage.PF_LINK__LARGEICON:
			if (!isEmpty()) {
				List icons = ((NavigationCaseType) getData().get(0)).getIcon();
				if (icons.size() > 0) {
					result = ((IconType) icons.get(0)).getLargeIcon() != null ? ((IconType) icons
							.get(0)).getLargeIcon().getTextContent()
							: null;
				}
			}
			break;
		}
		return result;
	}

	public void set(EStructuralFeature eFeature, Object newVal) {
		switch (eFeature.getFeatureID()) {
		case PageflowPackage.PF_LINK__COMMENT:
			break;
		case PageflowPackage.PF_LINK__OUTCOME: {
			FromOutcomeType oldOutcome = null;
			FromOutcomeType newOutcome = null;
			String newValue = (String) newVal;
			if (!isEmpty()) {
				oldOutcome = ((NavigationCaseType) getData().get(0))
						.getFromOutcome();
				if (newValue == null || newValue.length() == 0) {
					((NavigationCaseType) getData().get(0))
							.setFromOutcome(null);
				} else {
					if (oldOutcome == null) {
						newOutcome = FacesConfigFactory.eINSTANCE
								.createFromOutcomeType();
						newOutcome.setTextContent(newValue);
						((NavigationCaseType) getData().get(0))
								.setFromOutcome(newOutcome);
					} else {
						oldOutcome.setTextContent(newValue);
					}
				}
			}
			break;
		}
		case PageflowPackage.PF_LINK__NAME:
			break;
		case PageflowPackage.PF_LINK__REDIRECT:
			RedirectType redirect = null;
			RedirectType oldRedirect = null;
			boolean newRedirect = ((Boolean) newVal).booleanValue();
			if (!isEmpty()) {
				oldRedirect = ((NavigationCaseType) getData().get(0))
						.getRedirect();
				if (!newRedirect) {
					redirect = null;
				} else {
					if (oldRedirect == null) {
						redirect = FacesConfigFactory.eINSTANCE
								.createRedirectType();
					}
				}
				((NavigationCaseType) getData().get(0)).setRedirect(redirect);
			}
			break;
		case PageflowPackage.PF_LINK__FROMACTION:
			FromActionType oldFromActionType,
			newFromActionType;
			String newFromaction = (String) newVal;
			if (!isEmpty()) {
				oldFromActionType = ((NavigationCaseType) getData().get(0))
						.getFromAction();
				if (newFromaction == null || newFromaction.length() == 0) {
					((NavigationCaseType) getData().get(0)).setFromAction(null);
				} else {
					if (oldFromActionType == null) {
						newFromActionType = FacesConfigFactory.eINSTANCE
								.createFromActionType();
						newFromActionType.setTextContent(newFromaction);
						((NavigationCaseType) getData().get(0))
								.setFromAction(newFromActionType);
					} else {
						oldFromActionType.setTextContent(newFromaction);
					}
				}
			}
			break;
		case PageflowPackage.PF_LINK__SMALLICON: {
			SmallIconType oldSmallicon = null;
			SmallIconType newSmallIconType = null;
			IconType icon = null;
			String newValue = (String) newVal;
			if (!isEmpty()) {
				List icons = ((NavigationCaseType) getData().get(0)).getIcon();
				if (newValue == null || newValue.length() == 0) {
					if (icons.size() > 0) {
						if (((IconType) icons.get(0)).getSmallIcon() != null) {
							((IconType) icons.get(0)).setSmallIcon(null);
						}
					}
				} else {
					if (icons.size() == 0) {
						icon = FacesConfigFactory.eINSTANCE.createIconType();
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
						oldSmallicon = ((IconType) icons.get(0)).getSmallIcon();
						oldSmallicon.setTextContent(newValue);
					}
				}
			}
			break;
		}
		case PageflowPackage.PF_LINK__LARGEICON: {
			String newValue = (String) newVal;
			LargeIconType oldLargeIcon = null;
			LargeIconType newLargeIconType = null;
			IconType icon = null;
			if (!isEmpty()) {
				List icons = ((NavigationCaseType) getData().get(0)).getIcon();
				if (newValue == null || newValue.length() == 0) {
					if (icons.size() > 0) {
						if (((IconType) icons.get(0)).getLargeIcon() != null) {
							((IconType) icons.get(0)).setLargeIcon(null);
						}
					}
				} else {
					if (icons.size() == 0) {
						icon = FacesConfigFactory.eINSTANCE.createIconType();
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
						oldLargeIcon = ((IconType) icons.get(0)).getLargeIcon();
						oldLargeIcon.setTextContent(newValue);
					}
				}
			}
			break;
		}
		}
	}
}
