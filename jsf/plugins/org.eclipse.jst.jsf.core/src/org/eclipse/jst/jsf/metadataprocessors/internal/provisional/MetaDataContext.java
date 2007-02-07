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
 * The current metadata model context file being used for processing. 
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class MetaDataContext {
//	private String bundleId;
	private Trait trait;
	private Entity entity;
	
	
//	public MetaDataContext(String bundleId, String uri, String elementName){
//		this.bundleId = bundleId;
//		this.uri = uri;
//		this.elementName = elementName;
//	}
//	
//	public MetaDataContext(String bundleId, String uri, String elementName, String attributeName){
//		this.bundleId = bundleId;
//		this.uri = uri;
//		this.elementName = elementName;
//		this.attributeName = attributeName;
//	}
//	
//	public MetaDataContext(Trait trait, String uri, String elementName, String attributeName){
//		this.trait = trait;
//		this.uri = uri;
//		this.elementName = elementName;
//		this.attributeName = attributeName;
//	}
	
	public MetaDataContext(Entity entity, Trait trait) {
		this.entity = entity;
		this.trait = trait;
	}

//	public boolean isAttributeContext(){
//		return attributeName != null;
//	}
//	
//	public boolean isElementContext(){
//		return attributeName == null;
//	}
//
//	public String getAttributeName() {
//		return attributeName;
//	}
//
//	public String getElementName() {
//		return elementName;
//	}
//
//	public String getUri() {
//		return uri;
//	}
//	
//	public String getBundleId() {
//		return bundleId;
//	}
	
	public Entity getEntity(){
		return entity;
	}
	public Trait getTrait(){
		return trait;
	}

}
