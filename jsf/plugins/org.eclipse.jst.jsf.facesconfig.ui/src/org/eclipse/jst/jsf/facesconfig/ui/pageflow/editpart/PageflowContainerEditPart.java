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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy.PageflowContainerEditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy.PageflowXYLayoutEditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.PFBatchAdapter;
import org.eclipse.ui.PlatformUI;

/**
 * abstract class for pageflow container.
 */
abstract class PageflowContainerEditPart extends PageflowElementEditPart {

	/**
	 * @param element -
	 *            pageflow model.
	 */
	public PageflowContainerEditPart(PageflowElement element) {
		super(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new PageflowContainerEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new PageflowXYLayoutEditPolicy());
	}

	public Adapter createEMFAdapter() {
		return new PFBatchAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see PageflowElementEditPart#notifyChanged(Notification)
			 */
			public void doNotifyChanged(Notification notification) {
				int type = notification.getEventType();

				switch (type) {
				case Notification.ADD:
				case Notification.ADD_MANY:
				case Notification.REMOVE:
				case Notification.REMOVE_MANY:
					refreshChildrenOnUIThread();
					break;
				case Notification.SET:
					refreshVisualsOnUIThread();
					break;
				}
				super.notifyChanged(notification);
			}
		};
	}

	private void refreshChildrenOnUIThread() {
		if (Thread.currentThread() == PlatformUI.getWorkbench().getDisplay().getThread()) {
			refreshChildren();
		} else {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable(){
				public void run() {
					refreshChildren();
				}			
			});
		}
	}
	
	private void refreshVisualsOnUIThread() {
		if (Thread.currentThread() == PlatformUI.getWorkbench().getDisplay().getThread()) {
			refreshVisuals();
		} else {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable(){
				public void run() {
					refreshVisuals();
				}			
			});
		}
	}
}
