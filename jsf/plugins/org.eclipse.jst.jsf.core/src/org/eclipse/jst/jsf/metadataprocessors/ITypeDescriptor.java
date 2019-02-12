/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadataprocessors;

import java.util.List;

/**
 * Interface describing the runtime type of a metadata enabled annotation. 
 * The type descriptor will load the feature adapters for a certain kind of 
 * feature that it supports.  The type descriptor is registered by an extension point.
 * <p><b>Provisional API - subject to change</b></p>
 * @see IMetaDataEnabledFeature
 * @see AbstractRootTypeDescriptor
 * @see org.eclipse.jst.jsf.metadataprocessors.internal.AbstractMetaDataEnabledTypeFactory
 * @see IType
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public interface ITypeDescriptor {
	/**
	 * @param type 
	 * The IType must be set after the ITypeDescriptor is constructed
	 */
	public void setTypeExtension(IType type);
	/**
	 * @return IType
	 * Must not be null
	 */
	public IType getTypeExtension();

	/**
	 * @param processingFeature interface class
	 * @return List of <code>IMetaDataEnabledFeature</code>s that support the
	 * specified processingFeature interface.  
	 */
	public List<IMetaDataEnabledFeature> getFeatureAdapters(Class processingFeature);

}
