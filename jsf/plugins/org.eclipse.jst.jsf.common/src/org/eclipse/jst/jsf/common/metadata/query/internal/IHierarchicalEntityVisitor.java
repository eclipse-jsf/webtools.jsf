package org.eclipse.jst.jsf.common.metadata.query.internal;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor;

/**
 * Provides the necessary hierarchical visitor interface methods to 
 * provide conditional navigation of the entity hierarchy 
 *
 */
public interface IHierarchicalEntityVisitor extends IEntityVisitor {
	/**
	 * @param entity
	 * @return true if children should be traversed 
	 */
	boolean visitEnter(Entity entity); 
	/**
	 * @param entity
	 * @return true when coming out of a branch
	 */
	boolean visitLeave(Entity entity);
	

}
