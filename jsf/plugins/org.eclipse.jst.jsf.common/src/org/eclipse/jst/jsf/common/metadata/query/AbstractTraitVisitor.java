/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
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

import org.eclipse.jst.jsf.common.metadata.Trait;

/**
 * Abstract Trait visitor
 * <p><b>Provisional API - subject to change</b></p>
 */
public abstract class AbstractTraitVisitor extends AbstractMetaDataVisitor implements ITraitVisitor{

	public abstract void visit(final Trait trait); 
	
	public void visitCompleted(final Trait trait) {
		//subclasses should override if needed
	}

}
