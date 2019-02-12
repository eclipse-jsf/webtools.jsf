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

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;

/**
 * The adapter that listens to modification of faces-config and updates pageflow
 * as needed.
 * 
 * @author hmeng
 * 
 */

public class FC2PFSynchronizer extends AdapterImpl {
	private final boolean DEBUG = false;

	FC2PFTransformer transformer;

	private static final Logger logger = EditorPlugin
			.getLogger(FC2PFSynchronizer.class);

	/**
	 * @param transformer
	 */
	public FC2PFSynchronizer(FC2PFTransformer transformer) {
		this.transformer = transformer;
	}

	public Notifier getTarget() {
		// can't set target so return null
		return null;
	}

	public void setTarget(Notifier newTarget) {
        // do nothing
	}

	public boolean isAdapterForType(Object type) {
		return type == FC2PFSynchronizer.class;
	}

	public void notifyChanged(Notification notification) {
		if (!isProcessorFor(notification)) {
			return;
		}
		transformer.setInEvent(true);
		try {
			int type = notification.getEventType();
			switch (type) {
			case Notification.ADD: {
				processAdd(notification);
				notifyPageflow(notification);
				break;
			}
			case Notification.SET: {
				processSet(notification);
				notifyPageflow(notification);
				break;
			}
			case Notification.REMOVE:
				processRemove(notification);
				notifyPageflow(notification);
				break;
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (notification.getEventType() != Notification.REMOVING_ADAPTER) {
				transformer.refreshFCAdapter((EObject) notification
						.getNotifier());
				transformer.refreshPFAdapter(transformer.getPageflow());
			}
			transformer.setInEvent(false);
		}
	}

	private boolean isProcessorFor(Notification notification) {
		boolean result = false;
		if (transformer.isListenToNotify()
				&& (notification.getNotifier() instanceof EObject)) {
			if (notification.getNotifier() == transformer.getFacesConfig()) {
				// For faces-config, only navigation rule's change is awared.
				if (notification.getFeature() == FacesConfigPackage.eINSTANCE
						.getFacesConfigType_NavigationRule()) {
					result = true;
				}
			} else {
				result = true;
			}
		}
		return result;
	}

	private void processAdd(Notification notification) {
		Object feature = notification.getFeature();
		if (feature == FacesConfigPackage.eINSTANCE
				.getNavigationRuleType_NavigationCase()) {
			if (DEBUG)
				System.out.println("New navigation case"); //$NON-NLS-1$
			NavigationCaseType newCase = (NavigationCaseType) notification
					.getNewValue();
			transformer.updatePageflowElements(transformer.getPageflow(),
					newCase);
		} else if (feature == FacesConfigPackage.eINSTANCE
				.getFacesConfigType_NavigationRule()) {
			NavigationRuleType newRule = (NavigationRuleType) notification
					.getNewValue();
			if (newRule.getNavigationCase().size() > 0) {
				for (int i = 0; i < newRule.getNavigationCase().size(); i++) {
					transformer.updatePageflowElements(transformer
							.getPageflow(), (NavigationCaseType) newRule
							.getNavigationCase().get(i));
				}
			}
			if (DEBUG)
				System.out.println("New navigation rule"); //$NON-NLS-1$
		} else if (DEBUG)
			System.out.println("Something is added"); //$NON-NLS-1$
	}

	/**
	 * Notify pageflow the changes.
	 * 
	 * @param notification
	 */
	private void notifyPageflow(Notification notification) {
		Assert.isTrue(notification.getNotifier() instanceof InternalEObject);
		transformer.getPageflow().notifyModelChanged(
				new ENotificationImpl((InternalEObject) notification
						.getNotifier(), Notification.SET,
						PageflowPackage.PAGEFLOW, null, null));
	}

	/**
	 * Deal with the case when some element is removed.
	 * 
	 * @param notification
	 */
	private void processRemove(Notification notification) {
		if (notification.getFeature() == FacesConfigPackage.eINSTANCE
				.getNavigationRuleType_NavigationCase()) {
			if (notification.getOldValue() instanceof NavigationCaseType) {
				NavigationCaseType caseType = (NavigationCaseType) notification
						.getOldValue();
				transformer.removeLink(caseType);
			}
			if (DEBUG)
				System.out.println("Navigation case"); //$NON-NLS-1$
		} else if (notification.getFeature() == FacesConfigPackage.eINSTANCE
				.getNavigationCaseType()) {
			if (notification.getOldValue() instanceof NavigationRuleType) {
				NavigationRuleType rule = (NavigationRuleType) notification
						.getOldValue();
				for (int i = 0; i < rule.getNavigationCase().size(); i++) {
					NavigationCaseType caseType = (NavigationCaseType) rule
							.getNavigationCase().get(i);
					transformer.refreshLink(caseType);
				}
			}
			if (DEBUG)
				System.out.println("navigation rule"); //$NON-NLS-1$
		} else if (notification.getFeature() == FacesConfigPackage.eINSTANCE
				.getFacesConfigType_NavigationRule()) {
			if (notification.getOldValue() instanceof NavigationRuleType) {
				NavigationRuleType rule = (NavigationRuleType) notification
						.getOldValue();
				for (int i = 0; i < rule.getNavigationCase().size(); i++) {
					NavigationCaseType caseType = (NavigationCaseType) rule
							.getNavigationCase().get(i);
					transformer.refreshLink(caseType);
				}
			}
		}
		if (DEBUG)
			System.out.println("Something is removed"); //$NON-NLS-1$
	}

	/**
	 * Deal with property change.
	 * 
	 * @param notification
	 */
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
			System.out.println("Something is set"); //$NON-NLS-1$
	}

	private void fromViewIdChanged(Notification notification) {
		// remove
		Object feature = notification.getFeature();
		if (feature == FacesConfigPackage.eINSTANCE
				.getNavigationRuleType_FromViewId()
				&& notification.getNewValue() == null
				&& notification.getOldValue() instanceof FromViewIdType) {
			NavigationRuleType rule = (NavigationRuleType) notification
					.getNotifier();
			for (int i = 0; i < rule.getNavigationCase().size(); i++) {
				NavigationCaseType caseType = (NavigationCaseType) rule
						.getNavigationCase().get(i);
				transformer.refreshLink(caseType);
			}
		}
		// add or change
		else if (feature == FacesConfigPackage.eINSTANCE
				.getFromViewIdType_TextContent()) {
			NavigationRuleType rule = (NavigationRuleType) ((EObject) notification
					.getNotifier()).eContainer();
			for (int i = 0; i < rule.getNavigationCase().size(); i++) {
				NavigationCaseType caseType = (NavigationCaseType) rule
						.getNavigationCase().get(i);
				transformer.refreshLink(caseType);
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
			NavigationCaseType caseType = (NavigationCaseType) notification
					.getNotifier();
			transformer.refreshLink(caseType);
		}
		// add or change
		else if (feature == FacesConfigPackage.eINSTANCE
				.getToViewIdType_TextContent()) {
			NavigationCaseType caseType = (NavigationCaseType) ((EObject) notification
					.getNotifier()).eContainer();
			transformer.refreshLink(caseType);
		}
	}
}
