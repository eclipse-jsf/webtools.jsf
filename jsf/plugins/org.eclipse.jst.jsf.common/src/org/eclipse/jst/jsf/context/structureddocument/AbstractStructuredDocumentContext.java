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
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.structureddocument;

import org.eclipse.jst.jsf.context.AbstractDocumentContext;

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
	 * @see org.eclipse.jst.jsf.context.AbstractDocumentContext#getAdapter(java.lang.Class)
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
