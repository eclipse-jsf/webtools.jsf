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

public interface IEntityQueryVisitor extends IEntityVisitor{
	/**
	 * @param initialEntityContext
	 * @param entityKey to find relative to the passed intialEntityContext
	 * @return IResultSet of Entities matching the key.  IResultSet should NOT be null.
	 */
	public IResultSet/*<Entity>*/ findEntities(final Entity initialEntityContext,
			final String entityKey);
	
}
