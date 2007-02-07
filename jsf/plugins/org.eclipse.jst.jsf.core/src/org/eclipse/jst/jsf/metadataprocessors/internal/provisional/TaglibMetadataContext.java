package org.eclipse.jst.jsf.metadataprocessors.internal.provisional;

import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;

public class TaglibMetadataContext extends MetaDataContext {

	private String uri;
	private String tagName;
	private String attributeName;

	public TaglibMetadataContext(String uri, String tagName, String attributeName, Entity entity, Trait trait){
		super(entity, trait);
		this.uri = uri;
		this.tagName = tagName;
		this.attributeName = attributeName;
	}

	public String getUri() {
		return uri;
	}

	public String getTagName() {
		return tagName;
	}

	public String getAttributeName() {
		return attributeName;
	}
	
	
}
