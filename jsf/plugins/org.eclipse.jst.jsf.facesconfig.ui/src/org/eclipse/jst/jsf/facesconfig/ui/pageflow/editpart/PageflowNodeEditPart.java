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

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jdt.ui.JavaElementImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.OpenEditorCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy.PageflowElementEditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy.PageflowNodeDirectEditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy.PageflowNodeEditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.figure.ILabelDecorator;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.figure.PageflowNodeFigure;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.PFBatchAdapter;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowAnnotationUtil;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * The base class for the applications EditParts that represent
 * PageflowNode-derived objects in the model. This class implements the
 * NodeEditPart interface which supports:
 * <ul>
 * <li>feedback for Connections when they are being
 * <li>
 * <li>initially connected and when they are disonnected/reconnected</li>
 * </ul>
 * 
 * 
 */
public class PageflowNodeEditPart extends PageflowElementEditPart implements
		NodeEditPart, INodePreference, PFValidator {

	/** property source of pageflow node */
	// private IPropertySource propertySource = null;
	protected DirectEditManager editManager;

	private class ImageDecorator implements ILabelDecorator {
		private Image decrateImage = null;

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sybase.stf.jmt.editors.pageflow.figures.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image,
		 *      java.lang.Object)
		 */
		public Image decorateImage(Image image, Object element) {
			dispose();
			int adornmentFlags = computeAdornmentFlags(element);
			if (adornmentFlags != 0) {
				ImageDescriptor baseImage = ImageDescriptor.createFromImage(image);
				org.eclipse.swt.graphics.Rectangle bounds = image.getBounds();
				decrateImage = (new JavaElementImageDescriptor(baseImage,
						adornmentFlags, new org.eclipse.swt.graphics.Point(
								bounds.width, bounds.height))).createImage();
				return decrateImage;
			}
			return image;
		}

		/**
		 * Computes adornment flags for specified object.
		 * 
		 * Note: This method is for internal use only. Clients should not call
		 * this method.
		 * 
		 * @param obj Object to complute flags for.
		 * @return Adornment flags.
		 */
		protected int computeAdornmentFlags(Object obj) {
			return JavaElementImageDescriptor.WARNING;
		}

		/**
		 * Disposes this instance.
		 */
		public void dispose() {
			if (decrateImage != null) {
				decrateImage.dispose();
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.sybase.stf.jmt.editors.pageflow.figures.ILabelDecorator#decorateText(java.lang.String,
		 *      java.lang.Object)
		 */
		public String decorateText(String text, Object element) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/**
	 * Disposes this instance.
	 */
	public void dispose() {
		if (imageDecorator != null) {
			imageDecorator.dispose();
		}
	}

	private ImageDecorator imageDecorator = null;

	/**
	 * Creates a new PageflowNodeEditPart instance.
	 * 
	 * @param element -
	 *            pageflow node
	 */
	public PageflowNodeEditPart(PageflowNode element) {
		super(element);
	}

	/**
	 * get the pageflow node
	 * 
	 * @return - pageflow node
	 */
	public PageflowNode getPageflowNode() {
		return (PageflowNode) getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractGraphicalEditPart#getModelSourceConnections()
	 */
	protected List getModelSourceConnections() {
		return getPageflowNode().getOutlinks();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractGraphicalEditPart#getModelTargetConnections()
	 */
	protected List getModelTargetConnections() {
		return getPageflowNode().getInlinks();
	}

	/**
	 * Returns the Figure of this, as a node type figure.
	 * 
	 * @return - Figure as a NodeFigure.
	 */
	protected PageflowNodeFigure getPageflowNodeFigure() {
		return (PageflowNodeFigure) getFigure();
	}

	public Adapter createEMFAdapter() {
		return new PFBatchAdapter() {
			/**
			 * when ports are added to a PageflowNode, add this EditPart as a
			 * listener on the port so that it gets notified of PFLinks being
			 * added or removed. When links are added or removed from a port
			 * owned by the PageflowNode of this EditPart, refresh the
			 * connections.
			 */
			public void doNotifyChanged(Notification notification) {
				int type = notification.getEventType();
				// FC2PFTransformer.getInstance().NotifyChanged(notification,
				// (PageflowElement) getModel());
				switch (type) {
				case Notification.ADD:
				case Notification.ADD_MANY:
				case Notification.REMOVE:
				case Notification.REMOVE_MANY:
					if (notification.getNewValue() instanceof PageflowLink) {
						refreshTargetConnections();
						refreshSourceConnections();
						validate();
					}
					break;

				case Notification.SET:
					int featureId = notification
							.getFeatureID(PageflowPackage.class);
					if (needValidation(featureId)) {
						validate();
					}
					refreshVisuals();
					break;
				}
			}
		};
	}

	private boolean needValidation(int featureId) {
		if (getModel() instanceof PageflowPage) {
			if (featureId == PageflowPackage.PF_PAGE__PATH) {
				return true;
			}
		}
		return false;
	}

	public void validate() {
		if (getModel() instanceof PageflowPage) {
			PageflowAnnotationUtil.validatePage(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// install the edit policy to handle connection creation
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new PageflowNodeEditPolicy());

		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new PageflowElementEditPolicy());

		// install the direct policy
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new PageflowNodeDirectEditPolicy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see NodeEditPart#getSourceConnectionAnchor(ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		PageflowLink link = (PageflowLink) connection.getModel();
		return getPageflowNodeFigure().getSourceConnectionAnchorAt(
				new Point(link.getSource().getX(), link.getSource().getY()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see NodeEditPart#getSourceConnectionAnchor(Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		Point pt = new Point(((DropRequest) request).getLocation());
		return getPageflowNodeFigure().getSourceConnectionAnchorAt(pt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see NodeEditPart#getTargetConnectionAnchor(ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		PageflowLink link = (PageflowLink) connection.getModel();
		return getPageflowNodeFigure().getTargetConnectionAnchorAt(
				new Point(link.getTarget().getX(), link.getTarget().getY()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see NodeEditPart#getTargetConnectionAnchor(Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		Point pt = new Point(((DropRequest) request).getLocation());
		return getPageflowNodeFigure().getTargetConnectionAnchorAt(pt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		super.refreshVisuals();

		getPageflowNodeFigure().setText(getPageflowNode().getName());
	}

	/**
	 * make this a listener on its ports
	 */
	public void activate() {
		super.activate();

		Iterator it;

		it = getPageflowNode().getInlinks().iterator();
		while (it.hasNext()) {
			PageflowLink link = (PageflowLink) it.next();

			hookIntoPageflowElement(link);
		}

		it = getPageflowNode().getOutlinks().iterator();
		while (it.hasNext()) {
			PageflowLink link = (PageflowLink) it.next();

			hookIntoPageflowElement(link);
		}
	}

	/**
	 * remove this as a listener on its ports
	 */
	public void deactivate() {
		super.deactivate();
		dispose();
		Iterator it;

		it = getPageflowNode().getInlinks().iterator();
		while (it.hasNext()) {
			PageflowLink link = (PageflowLink) it.next();

			unhookFromPageflowElement(link);
		}

		it = getPageflowNode().getOutlinks().iterator();
		while (it.hasNext()) {
			PageflowLink link = (PageflowLink) it.next();

			unhookFromPageflowElement(link);
		}
	}

	/**
	 * perfrom direct edit request
	 * 
	 */
	protected void performDirectEdit() {
		if (editManager == null) {
			Label l = ((PageflowNodeFigure) getFigure()).getLabel();
			editManager = new PageflowDirectEditManager(this,
					TextCellEditor.class, new DirectEditCellEditorLocator(l), l);
		}
		editManager.show();
	}

	/**
	 * perform double click request
	 * 
	 */
	protected void performOpen() {
		// only Page and Action support the double-click commands
		if (getModel() instanceof PageflowPage) {
			// CommandStack stack =
			// getViewer().getEditDomain().getCommandStack();
			Command command = new OpenEditorCommand(this);
			if (command != null && command.canExecute()) {
				// stack.execute(command);
				command.execute();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IFigurePreference#setForegroundColor(org.eclipse.swt.graphics.Color)
	 */
	public void setForegroundColor(Color c) {
		getPageflowNodeFigure().setForegroundColor(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IFigurePreference#setBackgroundColor(org.eclipse.swt.graphics.Color)
	 */
	public void setBackgroundColor(Color c) {
		getPageflowNodeFigure().setBackgroundColor(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IFigurePreference#setFont(org.eclipse.swt.graphics.Font)
	 */
	public void setFont(Font f) {
		getPageflowNodeFigure().setFont(f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.INodePreference#setTextPlacement(int)
	 */
	public void setTextPlacement(int where) {
		getPageflowNodeFigure().setTextPlacement(where);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IEditPartDecorator#decorateEditPart()
	 */
	public void addAnnotation(final Annotation annotation) {
		getViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				getPageflowNodeFigure().setImage(
						getDecoratedImage((PageflowElement) getModel()));
				getPageflowNodeFigure().setToolTipText(annotation.getText());
			}

		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IEditPartDecorator#undecorateEditPart()
	 */
	public void removeAnnotation() {
		getViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				getPageflowNodeFigure().setImage(
						getImage((PageflowElement) getModel()));
				getPageflowNodeFigure().setToolTipText(null);
			}

		});
	}

	/**
	 * Returns the image for the pageflow element.
	 * 
	 * @param element -
	 *            pageflow element
	 * @return - the image for the pageflow element.
	 */
	private Image getDecoratedImage(PageflowElement element) {
		Image decoratedImage = getImage(element);

		decoratedImage = getImageDecorator().decorateImage(getImage(element),
				null);

		return decoratedImage;
	}

	private ImageDecorator getImageDecorator() {
		if (imageDecorator == null) {
			imageDecorator = new ImageDecorator();
		}
		return imageDecorator;
	}

}
