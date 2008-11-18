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

import org.eclipse.jst.jsf.metadataprocessors.IType;


/**
 * Class that encapuslates the information from the 
 * <code>AttributeValueRuntimeTypes</code> and 
 * <code>MetaDataEnabledFeatures</code>
 * extension points.
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class AbstractMetaDataEnabledType implements IType{
	String typeId;
	String bundleId;
	String klass;
	
	AbstractMetaDataEnabledType(String bundleID, String id, String klass){
		this.bundleId = bundleID;
		this.typeId = bundleId + "." + id; //$NON-NLS-1$
		this.klass = klass;
	}
	
	public String getTypeID(){		
		return typeId;
	}
	
	public String getBundleID(){
		return bundleId;
	}
	
	public String getClassName(){
		return klass;
	}

}
