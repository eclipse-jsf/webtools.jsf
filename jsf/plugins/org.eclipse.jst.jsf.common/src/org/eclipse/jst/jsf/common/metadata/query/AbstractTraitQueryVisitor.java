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
 * Abstract implmentation of {@link ITraitQueryVisitor} that subclasses should use to provide implementation
 * <p><b>Provisional API - subject to change</b></p> 
 */
public abstract class AbstractTraitQueryVisitor extends AbstractTraitVisitor implements
		ITraitQueryVisitor {

	/** 
	 * @return EmptyResultSet.   Subclasses should override.
	 */
	public IResultSet findTraits(Entity entity, String traitKey) {
		return new EmptyResultSet();
	}

}
