/*******************************************************************************
 * Copyright (c) 2007, 2010 Oracle Corporation.
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
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.HashSet;
import java.util.Set;

/**
 * Convenient abstract class that a source model locator should consider extending.
 */
public abstract class AbstractMetaDataLocator implements IMetaDataLocator, IMetaDataObservable{
	
	private IDomainSourceModelType domainSourceModelType;
	private Set <IMetaDataObserver> observers;

	/**
	 * Constructor
	 */
	public AbstractMetaDataLocator(){
		observers = new HashSet(1);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataLocator#getDomainSourceModelType()
	 */
	public IDomainSourceModelType getDomainSourceModelType() {
		return domainSourceModelType;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataLocator#setDomainSourceModelType(org.eclipse.jst.jsf.common.metadata.internal.IDomainSourceModelType)
	 */
	public void setDomainSourceModelType(
			final IDomainSourceModelType domainSourceModelType) {
		this.domainSourceModelType = domainSourceModelType;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataLocator#addObserver(org.eclipse.jst.jsf.common.metadata.internal.IMetaDataObserver)
	 */
	public void addObserver(final IMetaDataObserver observer) {
		observers.add(observer);		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataLocator#removeObserver(org.eclipse.jst.jsf.common.metadata.internal.IMetaDataObserver)
	 */
	public void removeObserver(final IMetaDataObserver observer) {	
		observers.remove(observer);
	}
	
	/**
	 * @return the set of {@link IMetaDataObserver}s for this instance
	 */
	public Set <IMetaDataObserver> getObservers(){
		return observers;
	}

}
