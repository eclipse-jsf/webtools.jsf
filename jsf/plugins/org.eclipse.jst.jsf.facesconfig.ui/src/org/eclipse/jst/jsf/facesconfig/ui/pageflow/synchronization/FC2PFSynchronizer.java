/**
 * Confidential Property of Sybase, Inc. 
 * (c) Copyright Sybase, Inc. 2004-2006. 
 * All rights reserved.
 */
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.wtp.jsf.facesconfig.emf.FacesConfigType;

/**
 * The synchronizer which is used to synchronize modification in source view
 * with pageflow view.
 * 
 * @author hmeng
 * 
 */
public class FC2PFSynchronizer implements Adapter {

	Pageflow pageflow;

	FacesConfigType facesConfig;

	public FC2PFSynchronizer(Pageflow pf, FacesConfigType fc) {
		this.pageflow = pf;
		this.facesConfig = fc;
	}

	public Notifier getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAdapterForType(Object type) {
		// TODO Auto-generated method stub
		return false;
	}

	public void notifyChanged(Notification notification) {
		if (!(notification.getNotifier() instanceof EObject)) {
			return;
		}
		int type = notification.getEventType();
		switch (type) {
		case Notification.ADD: {
			// PageflowTransform.getInstance().updatePageflowModelFromEMF(
			// pageflow, facesConfig);
			break;
		}
			// int featureId = notification.getFeatureID(PageflowPackage.class);
			//
			// EObject target = (EObject) this.getTarget();
			// if (target instanceof NavigationCaseType) {
			// PFLink link = (PFLink) FC2PFTransformer.getInstance()
			// .getCases2LinksMap().get(target);
			// } else if (target.eContainer() instanceof NavigationCaseType) {
			// PFLink link = (PFLink) FC2PFTransformer.getInstance()
			// .getCases2LinksMap().get(target.eContainer());
			// if (link != null) {
			// if (target instanceof DescriptionType) {
			// link.setComment(((DescriptionType) target)
			// .getTextContent());
			// } else if (target instanceof DisplayNameType) {
			// link.setName(((DisplayNameType) target)
			// .getTextContent());
			// PageflowLayoutManager.getInstance().layoutPageflow(
			// link.getPageflow());
			// }
			// }
			// }
			// }
		case Notification.SET:
			if (notification.getNewValue() != null) {
			}

			break;
		case Notification.REMOVE:
			break;
		}
	}

	public void dispose() {

	}

	public void setTarget(Notifier newTarget) {
		// TODO Auto-generated method stub

	}

}
