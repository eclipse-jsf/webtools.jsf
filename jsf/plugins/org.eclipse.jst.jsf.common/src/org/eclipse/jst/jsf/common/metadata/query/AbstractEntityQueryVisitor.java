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

	/** 
	 * @return EmptyResultSet.   Subclasses should override.
	 */
	public IResultSet findEntities(Entity initialEntityContext, String entityKey) {
		return new EmptyResultSet();
	}

}
