package org.eclipse.jst.jsf.common.metadata.internal.provisional.query;

import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;

public abstract interface IEntityVisitor extends IMetaDataVisitor {
	public void visit(final Entity entity);
	public void visitCompleted();
}
