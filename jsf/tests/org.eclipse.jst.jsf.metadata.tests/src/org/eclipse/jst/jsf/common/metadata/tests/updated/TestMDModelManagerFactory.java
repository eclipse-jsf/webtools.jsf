/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.tests.updated;

import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.AbstractMetaDataModelManager;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelManager;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelManagerFactory;


public class TestMDModelManagerFactory implements IMetaDataModelManagerFactory {

	public IMetaDataModelManager getInstance(IResource projectOrWorkspaceRoot) {
		return new TestMDModelManager();
	}

	private static class TestMDModelManager extends AbstractMetaDataModelManager {

		public Model getModel(IMetaDataModelContext modelContext) {
			return null;
		}

		@SuppressWarnings("unused")
		public Object getAdapter(Class adapter) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
