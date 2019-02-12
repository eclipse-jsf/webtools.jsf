/*******************************************************************************
 * Copyright (c) 2007, 2011 Oracle Corporation.
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
