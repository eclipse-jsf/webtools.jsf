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

import org.eclipse.jst.jsf.common.internal.managedobject.AbstractManagedObject;


/**
 * Abstract implementation of an {@link IMetaDataModelManager} that all implementers must subclass
 */
public abstract class AbstractMetaDataModelManager extends AbstractManagedObject implements IMetaDataModelManager {

	@Override
	public void dispose() {
		//do nothing by default		
	}

	@Override
	public void checkpoint() {
		//do nothing by default
	}

	@Override
	public void destroy() {
		// do nothing by default		
	}

}