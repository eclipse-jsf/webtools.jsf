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
