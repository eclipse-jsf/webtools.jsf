package org.eclipse.jst.jsf.common.metadata.internal.provisional.query;

import org.eclipse.core.resources.IProject;

public interface IMetaDataModelContext {
	public IProject getProject();
	public String getDomain();
	public String getURI();
}
