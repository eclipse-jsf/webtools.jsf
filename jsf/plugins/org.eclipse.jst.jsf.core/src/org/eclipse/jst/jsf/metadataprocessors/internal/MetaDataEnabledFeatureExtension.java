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

package org.eclipse.jst.jsf.metadataprocessors.internal;


/**
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class MetaDataEnabledFeatureExtension implements IMetaDataEnabledFeatureExtension{

	private String klass;
	private String typeId;
	private String bundleId;

	MetaDataEnabledFeatureExtension(String bundleID, String typeId, String klass) {		
		this.bundleId = bundleID;
		this.typeId = typeId;
		this.klass = klass;
	}

	public String getBundleID() {
		return bundleId;
	}

	public String getClassName() {
		return klass;
	}

	public String getTypeID() {
		return typeId;
	}

}
