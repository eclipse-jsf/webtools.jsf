package org.eclipse.jst.jsf.common.metadata.internal.provisional.query;

import org.eclipse.core.resources.IProject;

public class MetaDataModelContextImpl implements IMetaDataModelContext {
	private IProject project;
	private String domain;
	private String uri;
	
	public MetaDataModelContextImpl(IProject project, String domain, String uri){
		this.project = project;
		this.domain = domain;
		this.uri = uri;
	}
	public String getDomain() {
		return domain;
	}

	public IProject getProject() {		
		return project;
	}

	public String getURI() {
		return uri;
	}

}
