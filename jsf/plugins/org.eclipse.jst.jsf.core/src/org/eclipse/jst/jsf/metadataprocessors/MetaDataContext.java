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

package org.eclipse.jst.jsf.metadataprocessors;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;


/**
 * The current meta data model context being used for processing. 
 * <p><b>Provisional API - subject to change</b></p>
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
	 * @return Entity
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
