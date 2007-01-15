package org.eclipse.jst.jsf.common.metadata.internal.provisional.query;

import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;

public interface IEntityQueryVisitor extends IEntityVisitor{
	/**
	 * @param initialEntityContext
	 * @param entityKey to find relative to the passed intialEntityContext
	 * @return IResultSet of Entities matching the key
	 */
	public IResultSet/*<Entity>*/ findEntities(final Entity initialEntityContext,
			final String entityKey);
	
}
