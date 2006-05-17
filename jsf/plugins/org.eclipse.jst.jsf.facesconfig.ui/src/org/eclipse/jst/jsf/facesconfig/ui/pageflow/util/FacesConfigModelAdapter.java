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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.util;

import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;

/**
 * 
 * This class includes transform from the faces-config xml model to databinding
 * EMF model, and pageflow EMF model. and the reverse direction.
 * 
 * @author Xiao-guang Zhang
 */
public class FacesConfigModelAdapter {
	/** the dirty state of source page */
	private boolean isDirtyFacesConfig = false;

	/** the dirty state of pageflow page */
	private boolean isDirtyPageflow = false;

	/** the pageflow model manager */
	private PageflowModelManager pageflowManager;

	private FacesConfigType facesConfigType;

	public FacesConfigType getFacesConfigType() {
		return facesConfigType;
	}

	public void setFacesConfigEMFModel(FacesConfigType facesConfigType) {
		this.facesConfigType = facesConfigType;
	}

	public FacesConfigModelAdapter() {
		super();
	}

	public void dispose() {
	}

	/**
	 * get the pageflow model manager
	 * 
	 * @return
	 */
	public PageflowModelManager getPageflowManager() {
		return pageflowManager;
	}

	/**
	 * set the pageflow model manager
	 * 
	 * @param pageflowManager
	 */
	public void setPageflowManager(PageflowModelManager pageflowManager) {
		this.pageflowManager = pageflowManager;
	}

	/**
	 * faces-config source editor's dirty status
	 * 
	 * @return
	 */
	public boolean isFacesConfigDirty() {
		return isDirtyFacesConfig;
	}

	/**
	 * pageflow editor is dirty or not.
	 * 
	 * @return
	 */
	public boolean isPageflowDirty() {
		return isDirtyPageflow;
	}

	/**
	 * set the dirty status of faces-config source editor.
	 * 
	 * @param isDirtyFacesConfig
	 */
	public void setFacesConfigDirty(boolean isDirtyFacesConfig) {
		if (this.isDirtyFacesConfig != isDirtyFacesConfig) {
			this.isDirtyFacesConfig = isDirtyFacesConfig;
		}
	}

	/**
	 * set the dirty status of pageflow editor.
	 * 
	 */
	public void setPageflowDirty(boolean dirty) {
		if (this.isDirtyPageflow != dirty) {
			this.isDirtyPageflow = dirty;
		}
	}

	/**
	 * Sychronize the dirty statuses of all editors.
	 * 
	 * @param isSynchronized
	 */
	public void setPageflowSynchronizeState(boolean isSynchronized) {
		if (isSynchronized) {
			isDirtyFacesConfig = false;
			isDirtyPageflow = false;
		}
	}

	/**
	 * get the content of the model with the faces-config file.
	 * 
	 * @param path
	 */
	// public Pageflow getPageflowFromFacesConfig() throws IOException {
	// Pageflow pageflow = pageflowManager.getModel();
	// getModelsTransformer().updatePageflowModelFromEMF();
	// return pageflow;
	// }
}
