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

package org.eclipse.jst.jsf.metadataprocessors.internal.provisional;

import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;

/**
 * Required interface used by the MetaData feature processing framework.
 * All features must extend this interface.
 * 
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
	 * Sets content model context that this feature will operating on
	 * @param context 
	 */
	public void setContentModelContext(CMAnnotationContext context);
	
	/**
	 * Gets content model context that this feature will operating on
	 * @return CMAnnotationContext
	 */
	public CMAnnotationContext getCMAnnotationContext();
	
	/**
	 * Sets the bundle id used to define this feature
	 * @param bundleId 
	 */
	public void setBundleID(String bundleId);
	/**
	 * Gets the bundleId used to define the extension
	 * @return bundleID
	 */
	public String getBundleID();
}
