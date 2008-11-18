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
package org.eclipse.jst.jsf.facesconfig.ui.provider;

import java.util.Hashtable;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;
import org.eclipse.jst.jsf.facesconfig.ui.section.ManagedBeanScopeTreeItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * The Managed Bean Page's TreeViewer Label Provider
 * 
 * @author Xiao-guang Zhang, sfshi
 * @version 1.5
 */
public class ManagedBeanLabelProvider extends LabelProvider {
	
	private static final String SCOPE_IMAGE_FILE = "Scope.gif"; //$NON-NLS-1$
	private static final String SCOPE_NONE_IMAGE_FILE = "Scope_None.gif"; //$NON-NLS-1$
	private static final String SCOPE_APPLICATION_IMAGE_FILE = "Scope_Application.gif"; //$NON-NLS-1$
	private static final String SCOPE_REQUEST_IMAGE_FILE = "Scope_Request.gif"; //$NON-NLS-1$
	private static final String SCOPE_SESSION_IMAGE_FILE = "Scope_Session.gif"; //$NON-NLS-1$
	private static final String MANAGED_BEAN_IMAGE_FILE = "facesconfig/FacesConfig_ManagedBean.gif"; //$NON-NLS-1$
	
	/** Cache of images that have been dispensed by this provider */
	private Map imageTable;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(Object obj)
	 */
	public String getText(Object obj) {
		String text = ""; //$NON-NLS-1$

		if (obj instanceof ManagedBeanType) {
			ManagedBeanType bean = (ManagedBeanType) obj;
			if (bean.getManagedBeanName() != null) {
				text = bean.getManagedBeanName().getTextContent();

			}
			if (text == null || text.length() < 1) {
				if (bean.getManagedBeanClass() != null)
					text = bean.getManagedBeanClass().getTextContent();
			}
		} else if (obj instanceof ManagedBeanScopeTreeItem) {
			text = ((ManagedBeanScopeTreeItem) obj).getScope();
		}

		return text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(Object obj)
	 */
	public Image getImage(Object obj) {
		ImageDescriptor imageDesc = null;
		if (obj instanceof ManagedBeanType) {

			imageDesc = EditorPlugin.getDefault().getImageDescriptor(
					MANAGED_BEAN_IMAGE_FILE);

		} else if (obj instanceof ManagedBeanScopeTreeItem) {
			ManagedBeanScopeTreeItem scopeTreeItem = (ManagedBeanScopeTreeItem) obj;
			if (IFacesConfigConstants.MANAGED_BEAN_SCOPE_SESSION
					.equals(scopeTreeItem.getScope())) {
				imageDesc = EditorPlugin.getDefault().getImageDescriptor(
						SCOPE_SESSION_IMAGE_FILE);
			} else if (IFacesConfigConstants.MANAGED_BEAN_SCOPE_REQUEST
					.equals(scopeTreeItem.getScope())) {
				imageDesc = EditorPlugin.getDefault().getImageDescriptor(
						SCOPE_REQUEST_IMAGE_FILE);
			} else if (IFacesConfigConstants.MANAGED_BEAN_SCOPE_APPLICATION
					.equals(scopeTreeItem.getScope())) {
				imageDesc = EditorPlugin.getDefault().getImageDescriptor(
						SCOPE_APPLICATION_IMAGE_FILE);
			} else if (IFacesConfigConstants.MANAGED_BEAN_SCOPE_NONE
					.equals(scopeTreeItem.getScope())) {
				imageDesc = EditorPlugin.getDefault().getImageDescriptor(
						SCOPE_NONE_IMAGE_FILE);
			} else {
				imageDesc = EditorPlugin.getDefault().getImageDescriptor(
						SCOPE_IMAGE_FILE);
			}
		}

		if (imageDesc != null) {
			// Obtain the cached image corresponding to the descriptor
			if (imageTable == null) {
				imageTable = new Hashtable(40);
			}
			Image image = (Image) imageTable.get(imageDesc);
			if (image == null) {
				image = imageDesc.createImage();
				imageTable.put(imageDesc, image);
			}
			return image;
		}
		return PlatformUI.getWorkbench().getSharedImages().getImage(
				ISharedImages.IMG_OBJ_ELEMENT);
	}
}
