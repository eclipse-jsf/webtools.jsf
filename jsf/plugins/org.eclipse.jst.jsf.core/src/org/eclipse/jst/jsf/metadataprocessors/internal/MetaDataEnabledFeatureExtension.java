/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
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
