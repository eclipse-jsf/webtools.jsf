package org.eclipse.jst.jsf.common.metadata.internal.provisional.query;

import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;

public abstract interface ITraitVisitor extends IMetaDataVisitor {
	abstract void visit(final Trait trait);
}
