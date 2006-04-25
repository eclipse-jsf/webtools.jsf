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

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PFPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory;

/**
 * This class implements the CreationFactory used by the CreationTool. It in
 * turn uses the EMF-generated factories to create the model instances
 * 
 * @author Xiao-guang Zhang
 */
public class ModelCreationFactory implements CreationFactory {
	private Class targetClass;

	public ModelCreationFactory(Class targetClass) {
		this.targetClass = targetClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
	 */
	public Object getNewObject() {
		PageflowFactory factory = PageflowModelManager.getFactory();

		Object result = null;

		if (targetClass.equals(PFLink.class)) {
			result = factory.createPFLink();

		} else if (targetClass.equals(PFPage.class)) {
			result = factory.createPFPage();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
	 */
	public Object getObjectType() {
		return targetClass;
	}

}
