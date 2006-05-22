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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PageflowElementPropertySource;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * This is the abstract base <code>TreeEditPart</code> for pageflow elements.
 */
public class PageflowElementTreeEditPart extends AbstractTreeEditPart implements
		Adapter {
	/** Image descriptor of tree item for the different edit parts */
	public static final ImageDescriptor IMG_PAGE = EditorPlugin.getDefault()
			.getImageDescriptor("facesconfig/Pageflow_Page16.gif"); //$NON-NLS-1$

	public static final ImageDescriptor IMG_NODE = EditorPlugin.getDefault()
			.getImageDescriptor("facesconfig/FacesConfig_Pageflow16.gif"); //$NON-NLS-1$

	/** Property resource for shared property view */
	private IPropertySource propertySource = null;

	/** notifer to pageflow element */
	private Notifier target = null;

	/**
	 * Returns the image for the pageflow element.
	 * 
	 * @param element -
	 *            pageflow element, such as Begin, End, Page, and Action.
	 * @return - the image for the pageflow element
	 */
	public static Image getImage(PageflowElement element) {
		ImageDescriptor imageDescriptor = null;

		if (element instanceof PageflowPage) {
			imageDescriptor = IMG_PAGE;
		} else {
			imageDescriptor = IMG_NODE;
		}

		if (null == imageDescriptor) {
			return null;
		}

		Image image = EditorPlugin.getDefault().getImageRegistry().get(
				imageDescriptor.toString());
		if (null == image) {
			EditorPlugin.getDefault().getImageRegistry().put(
					imageDescriptor.toString(), imageDescriptor);
			image = EditorPlugin.getDefault().getImageRegistry().get(
					imageDescriptor.toString());
		}

		return image;
	}

	/**
	 * Creates a new PageflowElementTreeEditPart instance.
	 * 
	 * @param pageflowElement -
	 *            create a new edit part according to the pageflow model
	 */
	public PageflowElementTreeEditPart(PageflowElement pageflowElement) {
		super(pageflowElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IAdaptable#getAdapter(Class)
	 */
	public Object getAdapter(Class key) {
		/*
		 * override the default behavior defined in AbstractEditPart which would
		 * expect the model to be a property sourced. instead the editpart can
		 * provide a property source
		 */
		if (key == IPropertySource.class) {
			return getPropertySource();
		}
		return super.getAdapter(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractTreeEditPart#getImage()
	 */
	protected Image getImage() {
		return getImage(getPageflowElement());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see PageflowElementEditPart#getPropertySource()
	 */
	protected IPropertySource getPropertySource() {
		if (propertySource == null) {
			propertySource = new PageflowElementPropertySource(
					getPageflowElement());
		}
		return propertySource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Adapter#getTarget()
	 */
	public Notifier getTarget() {
		return target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractTreeEditPart#getText()
	 */
	protected String getText() {
		return (null != getPageflowElement().getName() ? getPageflowElement()
				.getName() : "[unnamed]"); //$NON-NLS-1$
	}

	/**
	 * Returns the model as <code>PageflowElement</code>.
	 * 
	 * @return - the model as <code>PageflowElement</code>
	 */
	public PageflowElement getPageflowElement() {
		return (PageflowElement) getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Adapter#isAdapterForType(Object)
	 */
	public boolean isAdapterForType(Object type) {
		return type.equals(getModel().getClass());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Adapter#notifyChanged(Notification)
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Adapter#setTarget(Notifier)
	 */
	public void setTarget(Notifier newTarget) {
		target = newTarget;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractGraphicalEditPart#activate()
	 */
	public void activate() {
		if (isActive())
			return;

		// start listening for changes in the model
		hookIntoPageflowElement(getPageflowElement());

		super.activate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractGraphicalEditPart#deactivate()
	 */
	public void deactivate() {
		if (!isActive())
			return;

		// stop listening for changes in the model
		unhookFromPageflowElement(getPageflowElement());

		super.deactivate();
	}

	/**
	 * Registers this edit part as a listener for change notifications to the
	 * specified pageflow element.
	 * 
	 * @param element -
	 *            the pagelfow element that should be observed for change
	 *            notifications
	 */
	protected void hookIntoPageflowElement(PageflowElement element) {
		if (null != element) {
			element.eAdapters().add(this);
		}
	}

	/**
	 * Removes this edit part from the specified pageflow element. Thus, it will
	 * no longe receive change notifications.
	 * 
	 * @param element
	 *            -the pagelfow element that should not be observed
	 */
	protected void unhookFromPageflowElement(PageflowElement element) {
		if (null != element) {
			element.eAdapters().remove(this);
		}
	}

}
