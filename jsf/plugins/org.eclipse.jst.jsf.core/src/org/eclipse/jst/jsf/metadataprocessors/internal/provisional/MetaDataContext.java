/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadataprocessors.internal.provisional;

import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;


/**
 * The current metadata model context being used for processing. 
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class MetaDataContext {
	private Trait trait;
	private Entity entity;
	
	/**
	 * Constructor
	 * @param entity
	 * @param trait
	 */
	public MetaDataContext(Entity entity, Trait trait) {
		this.entity = entity;
		this.trait = trait;
	}
	
	/**
	 * @return Entiry
	 */
	public Entity getEntity(){
		return entity;
	}
	/**
	 * @return Trait
	 */
	public Trait getTrait(){
		return trait;
	}

}
