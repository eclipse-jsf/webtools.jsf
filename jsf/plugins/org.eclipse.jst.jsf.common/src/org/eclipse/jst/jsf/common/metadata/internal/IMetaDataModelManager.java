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

package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.jst.jsf.common.internal.managedobject.IManagedObject;
import org.eclipse.jst.jsf.common.metadata.Model;

/**
 * Returns {@link Model}s for a given context which can then be queried.
 * <p>
 * Responsible for managing all domains of metadata
 * <p>
 * @noimplement - users must extend AbstractMetaDataModelManager
 * @noextend
 */
public interface IMetaDataModelManager extends IManagedObject {

	/**
	 * Will locate a MetaData Model.  Sets the model context in the
	 * model.
	 * 
	 * @param modelContext
	 * @return the {@link Model} for the given model identifier
	 */
	public Model getModel(
			final IMetaDataModelContext modelContext);
	

}