package org.eclipse.jst.jsf.common.runtime.internal.model.component;

/**
 * An abstract class sub-classed by objects that wish to apply a Visitor pattern
 * type visitation to a component tree.
 * 
 * @author cbateman
 * 
 */
public abstract class ComponentTreeVisitor extends AbstractVisitor
{
    /**
     * @param policy
     */
    public ComponentTreeVisitor(final VisitationPolicy policy)
    {
        super(policy);
    }

    /**
     * @param component
     */
    public abstract void visit(final ComponentInfo component);

    public final void visit(final Object object)
    {
        visit((ComponentInfo) object);
    }
}