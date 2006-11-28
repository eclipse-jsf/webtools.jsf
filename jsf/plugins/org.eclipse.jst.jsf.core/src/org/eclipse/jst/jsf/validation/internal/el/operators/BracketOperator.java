package org.eclipse.jst.jsf.validation.internal.el.operators;

import org.eclipse.core.resources.IFile;

/**
 * Handles the operator 'bracket' where bracket(expr-a, id-b) == 'expr-a[id-b]' in EL syntax
 * 
 * @author cbateman
 *
 */
public class BracketOperator extends MemberAccessorOperator
{
    /**
     * @param file 
     */
    public BracketOperator(final IFile file)
    {
        super(file);
    }
}
