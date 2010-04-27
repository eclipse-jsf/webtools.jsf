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

package org.eclipse.jst.jsf.common.metadata.query;

import org.eclipse.jst.jsf.common.metadata.Entity;

/**
 * Abstract class implementing {@link IEntityQueryVisitor} that concrete subclasses should provide implementations
 * <p><b>Provisional API - subject to change</b></p>
 */
public abstract class AbstractEntityQueryVisitor extends AbstractEntityVisitor
		implements IEntityQueryVisitor {

	private String _delimiter = "/"; //$NON-NLS-1$

	/** 
	 * @return EmptyResultSet.   Subclasses should override.
	 */
	public IResultSet<Entity> findEntities(final Entity initialEntityContext, final String entityKey) {
		return new EmptyResultSet();
	}

	/**
	 * @return String delimiting levels in the hierarchy.  Defaults to "/"
	 */
	protected String  getLevelDelimiter() {		
		return _delimiter;
	}
	
	/**
	 * Sets the string used to delimit the levels in an entityKey
	 * @param delimiter
	 */
	protected void setLevelDelimiter(final String delimiter) {
		_delimiter = delimiter;
	}
}
