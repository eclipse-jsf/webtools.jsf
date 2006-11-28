package org.eclipse.jst.jsf.validation.internal.el;

import java.util.List;

import org.eclipse.jst.jsf.common.internal.types.SignatureBasedType;

/**
 * Exposes certain parts of the ASTSemanticValidator without exposing the whole class
 * Primarily this was done for JUnit testing
 * 
 * @author cbateman
 *
 */
public interface IExpressionSemanticValidator {

    /**
     * Performs the semantic validatino
     */
    public abstract void validate();

    /**
     * @return the list of generated message (may be empty if validate has not been called).
     */
    public abstract List getMessages();

    /**
     * @return the value type of fully resolved expression
     * or null if not resolved (or could not be resolved)
     */
    public abstract SignatureBasedType getExpressionType();

}