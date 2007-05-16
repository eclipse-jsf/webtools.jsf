/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query;

/**
 * Abstract class implementing {@link IMetaDataVisitor}
 */
public abstract class AbstractMetaDataVisitor implements IMetaDataVisitor {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.query.IMetaDataVisitor#stopVisiting()
	 */
	public boolean stopVisiting() {		
		return false;
	}

}
