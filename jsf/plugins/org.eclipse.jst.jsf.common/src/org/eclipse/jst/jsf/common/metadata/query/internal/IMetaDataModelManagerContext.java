package org.eclipse.jst.jsf.common.metadata.query.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;

/**
 * Context for the meta data model manager
 *
 */
public interface IMetaDataModelManagerContext extends IMetaDataDomainContext {
	/**
	 * @return project - may be null
	 */
	public IProject getProject();
}
