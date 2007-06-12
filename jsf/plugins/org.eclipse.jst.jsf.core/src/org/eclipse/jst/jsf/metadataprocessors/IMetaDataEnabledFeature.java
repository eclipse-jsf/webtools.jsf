/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadataprocessors;

import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;

/**
 * Required interface used by the MetaData feature processing framework.
 * All features must extend this interface.
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 *
 */
public abstract interface IMetaDataEnabledFeature {
	
	/**
	 * Sets structured document context that this feature will operating on
	 * @param context 
	 */
	public void setStructuredDocumentContext(IStructuredDocumentContext context);
	
	/**
	 * Gets structured document context that this feature will operating on
	 * @return IStructuredDocumentContext
	 */
	public IStructuredDocumentContext getStructuredDocumentContext();
	
	/**
	 * Sets metadata model context that this feature will operating on
	 * @param context 
	 */
	public void setMetaDataContext(MetaDataContext context);
	
	/**
	 * Gets metadata model context that this feature will be operating on. 
	 * @return MetaDataContext
	 */
	public MetaDataContext getMetaDataContext();
	
}
