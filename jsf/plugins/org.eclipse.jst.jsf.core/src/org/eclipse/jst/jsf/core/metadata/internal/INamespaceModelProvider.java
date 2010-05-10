package org.eclipse.jst.jsf.core.metadata.internal;

import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;

/**
 * Provider of {@link Namespace} metadata
 *
 */
public interface INamespaceModelProvider extends IMetaDataSourceModelProvider {
	/**
	 * @return the namespace provided.   May be null.
	 */
	public Namespace getNamespace();
}
