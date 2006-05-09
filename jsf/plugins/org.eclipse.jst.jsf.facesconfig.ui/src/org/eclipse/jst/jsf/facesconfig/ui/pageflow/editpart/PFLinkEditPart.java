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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RelativeBendpoint;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.IconResources;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy.PFLinkBendpointEditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy.PFLinkEditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy.PFLinkEndpointEditPolicy;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.figure.PFLinkFigure;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLinkBendpoint;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.PageflowElementPropertySource;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowAnnotationUtil;
import org.eclipse.jst.jsf.facesconfig.ui.preference.GEMPreferences;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * <code>PFLinkEditPart</code> is the EditPart for PFLink model elements. The
 * figure for this EditPart is simply a PolylineConnection. Because both
 * AbstractConnectionEditPart and the Adapter interface have a getTarget method,
 * we use an inner class to implement the Adapter interface in order to work
 * around the name collision.
 * 
 * 
 */
public class PFLinkEditPart extends AbstractConnectionEditPart implements
		IConnectionPreference, IAnnotationEditPart {
	/** adapter for notification */
	private PFLinkAdapter adapter = new PFLinkAdapter();

	private FCAdapter fcAdapter = new FCAdapter();

	/** property source of the pflink */
	private IPropertySource propertySource = null;

	/** image description for different edit part */
	public static final ImageDescriptor IMG_WARNING = EditorPlugin.getDefault()
			.getImageDescriptor(
					IconResources.getString("Pageflow.quickfixerrorobject"));

	private int connectionStyle = -1;

	private PFLinkBendpointEditPolicy bendpointEditPolicy;

	/**
	 * @param element
	 */
	public PFLinkEditPart(PFLink element) {
		super();
		// element.getPageflow()
		setModel(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		IFigure figure = new PFLinkFigure();
		return figure;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new PFLinkEndpointEditPolicy());

		if (getConnectionRouterStyle() == ILayerPanePreference.LINE_ROUTING_MANUAL) {
			installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
					getBendpointEditPolicy());
		}
		PFLinkEditPolicy policy = new PFLinkEditPolicy();
		// policy.setSseModel(model);
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new PFLinkEditPolicy());

	}

	private PFLinkBendpointEditPolicy getBendpointEditPolicy() {
		if (bendpointEditPolicy == null) {
			bendpointEditPolicy = new PFLinkBendpointEditPolicy();
		}
		return bendpointEditPolicy;
	}

	public PFLink getPFLink() {
		return (PFLink) getModel();
	}

	private class FCAdapter implements Adapter {

		public Notifier getTarget() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean isAdapterForType(Object type) {
			return getModel().getClass() == type;
		}

		public void notifyChanged(Notification notification) {
			int type = notification.getEventType();
			int featureId = notification.getFeatureID(PageflowPackage.class);
			// FC2PFTransformer.getInstance().NotifyChanged(notification,
			// getPFLink());
			switch (type) {
			case Notification.ADD:
			case Notification.ADD_MANY:
			case Notification.REMOVE:
			case Notification.SET:
				// FIXME: need the more detailed updation.
				refreshVisuals();
				break;
			}
		}

		public void setTarget(Notifier newTarget) {
			// TODO Auto-generated method stub

		}

	}

	private class PFLinkAdapter implements Adapter {
		private Notifier _newTarget = null;

		// private IPropertySource _propertySource = null;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
		 */
		public Notifier getTarget() {
			return _newTarget;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
		 */
		public boolean isAdapterForType(Object type) {
			return getModel().getClass() == type;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification notification) {
			int type = notification.getEventType();
			int featureId = notification.getFeatureID(PageflowPackage.class);
			// FC2PFTransformer.getInstance().NotifyChanged(notification,
			// getPFLink());
			switch (type) {
			case Notification.ADD:
			case Notification.ADD_MANY:
			case Notification.REMOVE:
			case Notification.SET:
				if (featureId == PageflowPackage.PF_LINK__OUTCOME
						|| featureId == PageflowPackage.PF_LINK__SOURCE
						|| featureId == PageflowPackage.PF_LINK__TARGET) {
					PageflowAnnotationUtil.validateLink(PFLinkEditPart.this);
				}

				refreshVisuals();
				break;
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
		 */
		public void setTarget(Notifier newTarget) {
			this._newTarget = newTarget;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see EditPart#activate()
	 */
	public void activate() {
		getPFLink().eAdapters().add(adapter);
		PFLink element = (PFLink) getModel();
		if (element.getFCElements().size() > 0) {
			NavigationCaseType navCase = ((NavigationCaseType) element
					.getFCElements().get(0));
			if (!navCase.eAdapters().contains(fcAdapter)) {
				navCase.eAdapters().add(fcAdapter);
				TreeIterator iterator = navCase.eAllContents();
				while (iterator.hasNext()) {
					EObject next = (EObject) iterator.next();
					if (!next.eAdapters().contains(fcAdapter)) {
						next.eAdapters().add(fcAdapter);
					}
				}
			}
		}
		super.activate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see EditPart#deactivate()
	 */
	public void deactivate() {
		getPFLink().eAdapters().remove(adapter);
		PFLink element = (PFLink) getModel();
		NavigationCaseType navCase = ((NavigationCaseType) element
				.getFCElements().get(0));
		navCase.eAdapters().remove(fcAdapter);
		TreeIterator iterator = navCase.eAllContents();
		while (iterator.hasNext()) {
			((EObject) iterator.next()).eAdapters().remove(fcAdapter);
		}

		super.deactivate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IAdaptable#getAdapter(java.lang.Class)
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
			propertySource = new PageflowElementPropertySource(getPFLink());
		}
		return propertySource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see AbstractEditPart#refreshVisuals()
	 */
	protected void refreshVisuals() {
		super.refreshVisuals();
		resetLabel();
		if (getConnectionRouterStyle() == ILayerPanePreference.LINE_ROUTING_MANUAL) {
			refreshBendpoints();
		}
	}

	/**
	 * set the bendpoint constraints of the pflink connection
	 * 
	 */
	protected void refreshBendpoints() {
		// bendpoints stored in pflink
		List modelConstraint = getPFLink().getBendPoints();
		// bendpoint constraint list
		List figureConstraint = new ArrayList();
		for (int i = 0; i < modelConstraint.size(); i++) {
			PFLinkBendpoint wbp = (PFLinkBendpoint) modelConstraint.get(i);
			RelativeBendpoint rbp = new RelativeBendpoint(getConnectionFigure());
			rbp.setRelativeDimensions(wbp.getFirstRelativeDimension(), wbp
					.getSecondRelativeDimension());
			rbp.setWeight((i + 1) / ((float) modelConstraint.size() + 1));
			figureConstraint.add(rbp);
		}
		// set the router constaints.
		getConnectionFigure().setRoutingConstraint(figureConstraint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IConnectionPreference#setLineWidth(int)
	 */
	public void setLineWidth(int w) {
		((PFLinkFigure) getFigure()).setLineWidth(w);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IConnectionPreference#setLabelVisible(boolean)
	 */
	public void setLabelVisible(boolean b) {
		((PFLinkFigure) getFigure()).setLabelVisible(b);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IFigurePreference#setForegroundColor(org.eclipse.swt.graphics.Color)
	 */
	public void setForegroundColor(Color c) {
		((PFLinkFigure) getFigure()).setForegroundColor(c);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IFigurePreference#setBackgroundColor(org.eclipse.swt.graphics.Color)
	 */
	public void setBackgroundColor(Color c) {
		((PFLinkFigure) getFigure()).setBackgroundColor(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IFigurePreference#setFont(org.eclipse.swt.graphics.Font)
	 */
	public void setFont(Font f) {
		((PFLinkFigure) getFigure()).setFont(f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IConnectionPreference#setLabelForeground(org.eclipse.swt.graphics.Color)
	 */
	public void setLabelForegroundColor(Color c) {
		((PFLinkFigure) getFigure()).setLabelForegroundColor(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IConnectionPreference#setLabelBackground(org.eclipse.swt.graphics.Color)
	 */
	public void setLabelBackgroundColor(Color c) {
		((PFLinkFigure) getFigure()).setLabelBackgroundColor(c);
	}

	private boolean needDrawingLabel() {
		return (((PFLink) getModel()).getOutcome() != null && ((PFLink) getModel())
				.getOutcome().trim().length() > 0);
	}

	private boolean needDrawingAction() {
		return (((PFLink) getModel()).getFromaction() != null && ((PFLink) getModel())
				.getFromaction().trim().length() > 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IEditPartDecorator#decorateEditPart()
	 */
	public void addAnnotation(final Annotation annotation) {
		getViewer().getControl().getDisplay().asyncExec(new Runnable() {
			public void run() {
				((PFLinkFigure) getFigure()).setImage(getImage(IMG_WARNING));
				((PFLinkFigure) getFigure()).setToolTipText(annotation
						.getText());
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
				((PFLinkFigure) getFigure()).clearIcon();
				resetLabel();
			}
		});
	}

	public void resetLabel() {
		String tip = "";
		if (needDrawingAction()) {
			((PFLinkFigure) getFigure()).setActionImage();
			tip = "fromAction:(" + ((PFLink) getModel()).getFromaction() + ") ";
		} else if (((PFLinkFigure) getFigure()).getImage() != getImage(IMG_WARNING)) {
			((PFLinkFigure) getFigure()).clearIcon();
			tip = "";
		}
		if (needDrawingLabel()) {
			((PFLinkFigure) getFigure()).setLabel(((PFLink) getModel())
					.getOutcome());
			tip += "fromOutcome:(";
			tip += ((PFLink) getModel()).getOutcome() + ")";
		} else {
			((PFLinkFigure) getFigure()).clearOutcome();
		}
		((PFLinkFigure) getFigure()).setToolTipText(tip);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.marker.IEditPartMarker#getMarkerResource()
	 * 
	 */
	public IResource getMarkerResource() {
		// IResource resource = null;
		// if (getModel() instanceof PFLink)
		// {
		// resource =
		// FacesConfigUtil.getFacesConfigResource(((PFLink)getModel()).getPageflow());
		// }
		// FIXME: it should be changed to link to faces-config file.
		IResource resource = WebrootUtil.getResource((EObject) getModel());
		return resource;
	}

	public static Image getImage(ImageDescriptor imageDescriptor) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IConnectionPreference#setConnectionRouterStyle(int)
	 */
	public void setConnectionRouterStyle(int style) {
		connectionStyle = style;
		if (getConnectionRouterStyle() == ILayerPanePreference.LINE_ROUTING_MANUAL) {
			installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
					getBendpointEditPolicy());
			refreshVisuals();
		} else {
			removeEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);
			refreshVisuals();
		}

	}

	/**
	 * get the foreground color from preference
	 * 
	 */
	public int getConnectionRouterStyle() {
		if (this.connectionStyle == -1) {
			IPreferenceStore store = EditorPlugin.getDefault()
					.getPreferenceStore();
			String connectionStyle = store
					.getString(GEMPreferences.LINE_ROUTING);

			if (GEMPreferences.LINE_ROUTING_MANHATTAN
					.equals(connectionStyle)) {
				this.connectionStyle = ILayerPanePreference.LINE_ROUTING_MANHATTAN;
			} else {
				this.connectionStyle = ILayerPanePreference.LINE_ROUTING_MANUAL;
			}
		}
		return this.connectionStyle;
	}

}
