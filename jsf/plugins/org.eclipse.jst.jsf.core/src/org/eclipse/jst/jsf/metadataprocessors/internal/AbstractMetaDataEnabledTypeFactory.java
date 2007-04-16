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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.metadataprocessors.IType;
import org.eclipse.jst.jsf.metadataprocessors.ITypeDescriptor;
import org.osgi.framework.Bundle;

/**
 * Abstract factory producing <code>ITypeDescriptor</code>s 
 * from instances of <code>AbstractMetaDataEnabledType</code>s
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public abstract class AbstractMetaDataEnabledTypeFactory {
	/**
	 * The factory instance
     * TODO: why is this defined here it is only used in sub-classes??
	 */
	protected static AbstractMetaDataEnabledTypeFactory INSTANCE;
	
	/**
	 * No arg constructor
	 */
	protected AbstractMetaDataEnabledTypeFactory(){
		super();
	}
	
	/**
	 * Concrete factory must pass instances of the it's types.
	 * 
	 * @param typeId
	 * @return list of instances identified by the type id
	 */
	public abstract ITypeDescriptor getType(String typeId);
	
	/**
	 * @param type 
	 * @return list of instances identified by the type id
	 */
	public ITypeDescriptor getType(AbstractMetaDataEnabledType type){ 
		
		return createType(type);

	}

	/**
	 * Creates instances of <code>ITypeDescriptor</code>s from 
	 * <code>AbstractMetaDataEnabledType</code>s
	 * 
	 * @param atype
	 * @return the meta-data type descriptor
	 */
	protected ITypeDescriptor createType(IType atype){
		if (atype != null){
			ITypeDescriptor desc = createDescriptor(atype);
			if (desc != null){
				desc.setTypeExtension(atype);
				return desc;
			}
		}
		return null;
	}

	
	private ITypeDescriptor createDescriptor(IType type){
		String className = type.getClassName();
		try {
			Bundle bundle =Platform.getBundle(type.getBundleID());
			if (bundle == null){
				JSFCorePlugin.log(IStatus.ERROR, type.getBundleID() + " could not be created to load " + className);
				return null;
			}
			Class klass = bundle.loadClass(className);
			if (klass != null){
				//make sure the class can support the feature/extended interface
				if (ITypeDescriptor.class.isAssignableFrom(klass))
                {
					return (ITypeDescriptor)klass.newInstance();
                }
                JSFCorePlugin.log(IStatus.INFO, className + " was not found in " + type.getBundleID() +" for " + type.getTypeID());
			}
		} catch (ClassNotFoundException e) {
			JSFCorePlugin.log(IStatus.ERROR, className + " was not found in " + type.getBundleID() +" for " + type.getTypeID());
		} catch (InstantiationException e) {
			JSFCorePlugin.log(IStatus.ERROR, "InstantiationException: " + className + " in " + type.getBundleID() +" for " + type.getTypeID());
		} catch (IllegalAccessException e) {
			JSFCorePlugin.log(IStatus.ERROR,  "IllegalAccessException: " + className + " in " + type.getBundleID() +" for " + type.getTypeID());
		}
		return null;
	}
}
