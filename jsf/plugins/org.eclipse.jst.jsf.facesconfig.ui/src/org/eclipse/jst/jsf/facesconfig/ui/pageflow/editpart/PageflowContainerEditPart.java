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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy.PageflowContainerEditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy.PageflowXYLayoutEditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;

/**
 * abstract class for pageflow container.
 */
abstract public class PageflowContainerEditPart extends PageflowElementEditPart {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see PageflowElementEditPart#notifyChanged(Notification)
	 */
	public void notifyChanged(Notification notification) {
		int type = notification.getEventType();

		switch (type) {
		case Notification.ADD:
		case Notification.ADD_MANY:
		case Notification.REMOVE:
		case Notification.REMOVE_MANY:
			refreshChildren();
			break;
		case Notification.SET:
			refreshVisuals();
			break;
		}
		super.notifyChanged(notification);
	}

}
