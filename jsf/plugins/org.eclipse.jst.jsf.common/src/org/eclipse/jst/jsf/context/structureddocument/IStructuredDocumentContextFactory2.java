/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.context.structureddocument;

import org.eclipse.jst.jsf.context.structureddocument.internal.impl.StructuredDocumentContextFactory;
import org.w3c.dom.Element;

/**
 * A factory method for constructing context objects related to
 * SSE structured documents.  
 *
 * May be sub-classed or implemented by clients
 * 
 */
public interface IStructuredDocumentContextFactory2 extends
		IStructuredDocumentContextFactory {
	
	/**
	 * The default singleton instance of the factory
	 */
	public static final IStructuredDocumentContextFactory2  INSTANCE = 
        StructuredDocumentContextFactory.getInstance();
	
	/**
	 * @param element -- org.w3c.Element 
	 * @return a context for the element or if null if one cannot be determined.
	 */
	IStructuredDocumentContext getContext(Element element);
}
