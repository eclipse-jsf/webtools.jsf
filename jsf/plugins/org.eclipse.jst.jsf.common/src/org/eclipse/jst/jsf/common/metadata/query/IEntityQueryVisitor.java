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
package org.eclipse.jst.jsf.common.metadata.query;

import org.eclipse.jst.jsf.common.metadata.Entity;

/**
 * Entity querying interface
 * <p>NOT to implemented by clients directly.   Clients should subclass AbstractEntityQueryVisitor instead.
 * <p><b>Provisional API - subject to change</b></p>
 */
public interface IEntityQueryVisitor extends IEntityVisitor{
	/**
	 * @param initialEntityContext
	 * @param entityKey to find relative to the passed intialEntityContext
	 * @return IResultSet of Entities matching the key.  IResultSet must NOT be null.  Implementers may return {@link EmptyResultSet}.
	 */
	public IResultSet<Entity> findEntities(final Entity initialEntityContext,
			final String entityKey);
	
}
