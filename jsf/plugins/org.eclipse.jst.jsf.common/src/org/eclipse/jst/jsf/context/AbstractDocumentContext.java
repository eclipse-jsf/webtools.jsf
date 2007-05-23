/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context;


/**
 * A convenience super-type for all context instances that implement
 * IDocumentContext or one of its sub-types. 
 * 
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDocumentContext implements IDocumentContext {

	/**
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapterClass) 
	{
		if (adapterClass.equals(IDocumentContext.class))
		{
			return this;
		}
		else if (adapterClass.equals(IModelContext.class))
		{
			return this;
		}
		
		return null;
	}
}
