/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.List;


/**
 * Strategy for loading tag metadata.   Extends and uses the default {@link DomainLoadingStrategy}.  
 * Mainly exists for illustrative purposes.  Could be removed..
 */
public class JSPTagLibDomainLoadingStrategy extends DomainLoadingStrategy {

	/**
	 * Constructor
	 * @param domain
	 */
	public JSPTagLibDomainLoadingStrategy(String domain) {
		super(domain);
	}

	/*@Override*/
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.DomainLoadingStrategy#sortSourceTypes(java.util.List)
	 */
	protected void sortSourceTypes(List<IDomainSourceModelType> sourceTypes) {
		//an opportunity to override the priority of the source types...
	}

	
	
}
