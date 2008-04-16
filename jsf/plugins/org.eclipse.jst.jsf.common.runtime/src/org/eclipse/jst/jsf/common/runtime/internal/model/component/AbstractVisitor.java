package org.eclipse.jst.jsf.common.runtime.internal.model.component;

/**
 * Implemented by visitors
 * 
 * @author cbateman
 * 
 */
public abstract class AbstractVisitor
{
    /**
     * A policy to control visitation
     */
    protected final VisitationPolicy _policy;

    /**
     * @param policy
     */
    protected AbstractVisitor(final VisitationPolicy policy)
    {
        super();
        _policy = policy;
    }

    /**
     * @param object
     */
    public abstract void visit(Object object);

    /**
     * @return the visitation policy
     */
    public VisitationPolicy getPolicy()
    {
        return _policy;
    }

    /**
     * A policy that allows a visitor to configure how it will visit a tree.
     * 
     */
    public static final class VisitationPolicy
    {
        /**
         * indicates pre-order, parent first traversal (root visited first)
         */
        public static final int              VISIT_PARENT_FIRST   = 0;                          // pre-order
        // tree
        // visit
        /**
         * indicates post-order, children first traveral (root visited last)
         */
        public static final int              VISIT_CHILDREN_FIRST = 1;                          // post-order
        // tree
        // visit
        /**
         * A default parent first policy
         */
        public final static VisitationPolicy ParentFirstPolicy    = new VisitationPolicy(
                VISIT_PARENT_FIRST);
        /**
         * A default children first policy
         */
        public final static VisitationPolicy ChildrenFirstPolicy  = new VisitationPolicy(
                VISIT_CHILDREN_FIRST);

        private final int                    _ordering;

        /**
         * @param ordering
         */
        public VisitationPolicy(final int ordering)
        {
            _ordering = ordering;
        }

        /**
         * @return the ordering
         */
        public final int getOrdering()
        {
            return _ordering;
        }
    }

}
