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

package org.eclipse.jst.jsf.context.structureddocument.internal.provisional;

import org.eclipse.jst.jsf.context.internal.provisional.AbstractDocumentContext;

/**
 * A convenience abstract super-class for context implementing
 * the IStructuredDocumentContext interface
 * 
 * Class may be sub-classed by clients
 * 
 * @author cbateman
 *
 */
public abstract class AbstractStructuredDocumentContext extends AbstractDocumentContext
				implements IStructuredDocumentContext 
{
	/**
	 * @see org.eclipse.jst.jsf.context.internal.provisional.AbstractDocumentContext#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapterClass)
	{
		if (adapterClass.equals(IStructuredDocumentContext.class))
		{
			return this;
		}

		return super.getAdapter(adapterClass);
	}
}
