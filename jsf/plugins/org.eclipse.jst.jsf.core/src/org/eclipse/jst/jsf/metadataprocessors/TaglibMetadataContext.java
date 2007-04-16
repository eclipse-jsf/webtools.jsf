package org.eclipse.jst.jsf.metadataprocessors;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;

/**
 * Binds tag library context to the metadata context 
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
