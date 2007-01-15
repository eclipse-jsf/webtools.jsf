package org.eclipse.jst.jsf.common.metadata.internal.provisional.query;

import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;

public interface ITraitQueryVisitor extends ITraitVisitor{
	/**
	 * @param entity
	 * @param traitKey
	 * @return IResultSet of Traits
	 */
	public IResultSet/*<Trait>*/ findTraits(final Entity entity,
			final String traitKey);

}
