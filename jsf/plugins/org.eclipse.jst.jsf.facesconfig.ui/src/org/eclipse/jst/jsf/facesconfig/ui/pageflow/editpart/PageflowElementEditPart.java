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

import org.eclipse.core.resources.IResource;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.IconResources;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.figure.PageflowNodeFigure;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PageflowElementPropertySource;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.FC2PFTransformer;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * 
 * 
 * <code>PageflowElementEditPart</code> is the base class for all the
 * EditParts in the application, whose model is a subclass of PageflowElement.
 * This class maintains the figure's size and location inresponse to changes in
 * the model, and registers as a listener on its model using the
 * <code>org.eclipse.emf.common.notify.Adapter</code> interface
 */
public abstract class PageflowElementEditPart extends AbstractGraphicalEditPart
		implements Adapter, IAnnotationEditPart {
	/** image description for different edit part */
	public static final ImageDescriptor IMG_ACTION = EditorPlugin.getDefault()
			.getImageDescriptor("facesconfig/Pageflow_Action16.gif"); //$NON-NLS-1$

//	public static final ImageDescriptor IMG_BEGIN = EditorPlugin
//			.getDefault()
//			.getImageDescriptor(IconResources.getString("Pageflow.begin.large")); //$NON-NLS-1$
//
//	public static final ImageDescriptor IMG_END = EditorPlugin.getDefault()
//			.getImageDescriptor(IconResources.getString("Pageflow.end.large")); //$NON-NLS-1$

	public static final ImageDescriptor IMG_PAGE = EditorPlugin.getDefault()
			.getImageDescriptor(IconResources.getString("Pageflow.page.large")); //$NON-NLS-1$

	public static final ImageDescriptor IMG_NODE = EditorPlugin.getDefault()
			.getImageDescriptor(IconResources.getString("Pageflow.node.large")); //$NON-NLS-1$

	/** property source of the pageflow element */
	private IPropertySource propertySource = null;

	/** notifer of the model modification */
	private Notifier target = null;

	private Image image = null;

	protected PageflowElementEditPart(PageflowElement element) {
		setModel(element);
	}

	/**
	 * Returns the image for the pageflow element.
	 * 
	 * @param element -
	 *            pageflow element
	 * @return - the image for the pageflow element.
	 */
	public Image getImage(PageflowElement element) {
		if (image == null) {
			ImageDescriptor imageDescriptor = null;

			if (element instanceof PFPage) {
				imageDescriptor = IMG_PAGE;
			} else {
				imageDescriptor = IMG_NODE;
			}

			image = EditorPlugin.getDefault().getImageRegistry().get(
					imageDescriptor.toString());
			if (null == image) {
				EditorPlugin.getDefault().getImageRegistry().put(
						imageDescriptor.toString(), imageDescriptor);
				image = EditorPlugin.getDefault().getImageRegistry().get(
						imageDescriptor.toString());
			}
		}

		return image;
	}

	/**
	 * get the type name of the pageflow element
	 * 
	 * @param element -
	 *            pageflow element
	 * @return - the type name of the pageflow element
	 */
	public static String getTypeName(PageflowElement element) {
		if (element instanceof Pageflow) {
			return "Pageflow"; //$NON-NLS-1$
		} else if (element instanceof PFPage) {
			return "Page"; //$NON-NLS-1$
		}

		return "[unknown]"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractGraphicalEditPart#activate()
	 */
	public void activate() {
		if (isActive()) {
			return;
		}

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
		if (!isActive()) {
			return;
		}

		// stop listening for changes in the model
		unhookFromPageflowElement(getPageflowElement());

		super.deactivate();
	}

	/**
	 * Returns the model as pageflow element.
	 * 
	 * @return - pageflow element
	 */
	public PageflowElement getPageflowElement() {
		return (PageflowElement) getModel();
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
	 * @see Adapter#isAdapterForType(Object)
	 */
	public boolean isAdapterForType(Object type) {
		return type.equals(getModel().getClass());
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
	 * @see AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		if (getParent() != null) {
			Point loc = new Point(getPageflowElement().getX(),
					getPageflowElement().getY());
			Dimension size = new Dimension(getPageflowElement().getWidth(),
					getPageflowElement().getHeight());
			Rectangle r = new Rectangle(loc, size);

			((GraphicalEditPart) getParent()).setLayoutConstraint(this,
					getFigure(), r);
		}
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
	 * @see AbstractGraphicalEditPart#notifyChanged()
	 */
	public void notifyChanged(Notification notification) {
		int type = notification.getEventType();
		int featureId = notification.getFeatureID(PageflowPackage.class);
		// if (featureId == PageflowPackage.PAGEFLOW__LINKS) {
		// FC2PFTransformer.getInstance().NotifyChanged(notification,
		// (PageflowElement) getModel());
		// }
		if (type == Notification.SET) {
			switch (featureId) {
			case PageflowPackage.PAGEFLOW_ELEMENT__X:
			case PageflowPackage.PAGEFLOW_ELEMENT__Y:
			case PageflowPackage.PAGEFLOW_ELEMENT__WIDTH:
			case PageflowPackage.PAGEFLOW_ELEMENT__HEIGHT:
				refreshVisuals();
				break;
			}
		}
	}

	/**
	 * Registers this edit part as a listener for change notifications to the
	 * specified pageflow element.
	 * 
	 * @param element
	 *            the pagelfow element that should be observed for change
	 *            notifications
	 */
	protected void hookIntoPageflowElement(PageflowElement element) {
		if (null != element) {
			if (!element.eAdapters().contains(FC2PFTransformer.getInstance())) {
				element.eAdapters().add(FC2PFTransformer.getInstance());
			}
			if (!element.eAdapters().contains(this)) {
				element.eAdapters().add(this);
			}
		}
	}

	/**
	 * Removes this edit part from the specified pageflow element. Thus, it will
	 * no longe receive change notifications.
	 * 
	 * @param element
	 *            the pagelfow element that should not be observed any more
	 */
	protected void unhookFromPageflowElement(PageflowElement element) {
		if (null != element) {
			element.eAdapters().remove(this);
			element.eAdapters().remove(FC2PFTransformer.getInstance());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		IFigure figure = null;
		figure = new PageflowNodeFigure();
		PageflowElement elm = getPageflowElement();
		if (elm.getName() != null) {
			((PageflowNodeFigure) figure).setImageText(getImage(elm), elm
					.getName());
		} else {
			((PageflowNodeFigure) figure).setImageText(getImage(elm),
					getTypeName(elm));
		}
		((PageflowNodeFigure) figure).update();
		return figure;
	}

	/**
	 * perform corresponding request related with double click or direct edit
	 * click.
	 * 
	 * @param request :
	 *            request composed by mouse click.
	 */
	public void performRequest(Request request) {
		// the request is triggered by double clicked on a edit part
		if (request.getType() == RequestConstants.REQ_OPEN) {
			performOpen();
		} else if (request.getType() == RequestConstants.REQ_DIRECT_EDIT) {
			performDirectEdit();
		}
	}

	/**
	 * perfrom direct edit request
	 * 
	 */
	protected void performDirectEdit() {
	}

	/**
	 * perform double click request
	 * 
	 */
	protected void performOpen() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IEditPartDecorator#decorateEditPart()
	 */
	public void addAnnotation(Annotation annotation) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IEditPartDecorator#undecorateEditPart()
	 */
	public void removeAnnotation() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.marker.IEditPartMarker#getMarkerResource()
	 */
	public IResource getMarkerResource() {
		IResource resource = WebrootUtil.getResource((EObject) getModel());
		;
		return resource;
	}
}
