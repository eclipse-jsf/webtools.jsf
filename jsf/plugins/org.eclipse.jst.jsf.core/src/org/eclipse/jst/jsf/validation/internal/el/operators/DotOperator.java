package org.eclipse.jst.jsf.validation.internal.el.operators;

import org.eclipse.core.resources.IFile;

/**
 * Handles the operator 'dot' where dot(expr-a, id-b) == 'expr-a.id-b' in EL syntax
 * 
 * @author cbateman
 *
 */
public class DotOperator extends MemberAccessorOperator
{
    /**
     * @param file
     */
    public DotOperator(IFile file) 
    {
        super(file);
    }
}
