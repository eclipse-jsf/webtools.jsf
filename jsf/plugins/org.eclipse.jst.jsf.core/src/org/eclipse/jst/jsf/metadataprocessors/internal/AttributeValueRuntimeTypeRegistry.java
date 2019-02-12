/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
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
 * Registry of <code>AbstractMetaDataEnabledType</code>s that are loaded from 
 * the <code>AttributeValueRuntimeValueType</code> extension point
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class AttributeValueRuntimeTypeRegistry extends AbstractMetaDataEnabledTypeRegistry {

	private static final String EXTPTID = "AttributeValueRuntimeTypes"; //$NON-NLS-1$
	private static final String DEFAULT_CLASS = "org.eclipse.jst.jsf.metadataprocessors.DefaultTypeDescriptor"; //$NON-NLS-1$
	private static AttributeValueRuntimeTypeRegistry INSTANCE;
	
	/**
	 * @return singleton instance
	 */
	public static AttributeValueRuntimeTypeRegistry getInstance(){
		if (INSTANCE == null){
			INSTANCE = new AttributeValueRuntimeTypeRegistry();	
		}
		return INSTANCE;
	}
	
	private AttributeValueRuntimeTypeRegistry(){
		super(EXTPTID);
	}

	protected String getDefaultClassName() {
		return DEFAULT_CLASS;
	}

}
