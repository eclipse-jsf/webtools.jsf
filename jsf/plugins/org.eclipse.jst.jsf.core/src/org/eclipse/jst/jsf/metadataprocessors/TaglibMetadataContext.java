/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.metadataprocessors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;

/**
 * Binds tag library context to the metadata context 
 * <p><b>Provisional API - subject to change</b></p>
 * @deprecated - should not have been public, and no reason anyone should be using this class
 */
public class TaglibMetadataContext extends MetaDataContext {

	private String uri;
	private String tagName;
	private String attributeName;

	/**
	 * Constructor 
	 * 
	 * @param uri - must not be null
	 * @param tagName - must not be null
	 * @param attributeName - may be null
	 * @param entity - the metadata entity represented by the tag or attribute
	 * @param trait - the metadata trait of interest
	 */
	public TaglibMetadataContext(String uri, String tagName, String attributeName, Entity entity, Trait trait){
		super(entity, trait);
		this.uri = uri;
		this.tagName = tagName;
		this.attributeName = attributeName;
	}
	
	/**
	 * Package-private Constructor
	 * 
	 * @param tagAttrEntity - must be tag attribute entity and not null
	 * @param trait
	 */
	/*package*/ TaglibMetadataContext(Entity tagAttrEntity, Trait trait){
		super(tagAttrEntity, trait);
		Assert.isNotNull(tagAttrEntity);
		this.uri = tagAttrEntity.getModel().getCurrentModelContext().getUri().toString();
		if (!(tagAttrEntity.eContainer() instanceof Entity))
		{
		    throw new IllegalArgumentException("tagAttrEntity must be contained in a tag Entity to use this constructor"); //$NON-NLS-1$
		}
		this.tagName = ((Entity)tagAttrEntity.eContainer()).getId();
		this.attributeName = tagAttrEntity.getId();
	}

	/**
	 * @return URI
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @return Tag name - should not be null
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * @return Attribute name  - may be null
	 */
	public String getAttributeName() {
		return attributeName;
	}
	
	
}
