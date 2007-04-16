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

import org.eclipse.jst.jsf.metadataprocessors.ITypeDescriptor;


/**
 * Factory producing <code>IMetaDataEnabledFeature</code>s from 
 * <code>AttributeValueRuntimeType</code> objects
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class AttributeValueRuntimeTypeFactory extends AbstractMetaDataEnabledTypeFactory{
	
	/**
	 * @return the single instance... TODO: why is this allocated here if it is
     * declared in super?
	 */
	public static AttributeValueRuntimeTypeFactory getInstance(){
		if (INSTANCE == null){
			INSTANCE = new AttributeValueRuntimeTypeFactory();	
		}
		return (AttributeValueRuntimeTypeFactory)INSTANCE;
	}
	
	private AttributeValueRuntimeTypeFactory(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.internal.AbstractMetaDataEnabledTypeFactory#getType(java.lang.String)
	 */
	public ITypeDescriptor getType(String typeId){ 
		
		return createType(AttributeValueRuntimeTypeRegistry.getInstance().getType(typeId));

	}
}
