/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tagmatcher;

import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.w3c.dom.Node;

/**
 * An algorithm that can be applied at a particular DOM node.  The result
 * may be expressed as a boolean or a set of matching nodes.  The following
 * are equivalent:
 * 
 *    false and empty set
 *    true and non-empty set
 * 
 * Concrete implementations must be idempotent on evaluate():
 * 
 * 1) evaluate(node) must always return the same set if called repeatedly on
 *    the same node in the same DOM tree.
 * 2) evaluate must be able to be called on any number of nodes in order and always
 * produce the same result independent of what was called before and in what order.
 *  
 * <p><b>Provisional API - subject to change</b></p>
 *
 * @author cbateman
 *
 */
public abstract class TagMatchingAlgorithm 
{
    private boolean             _isInvalid; // = false;
    
    private boolean             _isInitialized;
    /**
     * The expression being passed to the algorithm
     */
    protected final String      _expression;
    
    /**
     * Constructor.
     * 
     * @param expression
     */
    protected TagMatchingAlgorithm(String expression)
    {
        _expression = expression;
    }
    
    /**
     * Called exactly once to initialize any pre-evaluation setup for
     * the expression set for the algorithm.  This is public to allow
     * the client control when this initialization occurs in case it is expensive.
     * evaluate() will call this method automatically if it has never been called
     * @throws InvalidExpressionException if the underlying algorithm throws an 
     * exception during init
     * @throws IllegalStateException if it has already been determined that the
     * expression is invalid.
     */
    public final void initialize() 
    {
        if (_isInvalid)
        {
            throw new IllegalStateException("Expression: "+_expression+" has already been determined to be invalid"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        
        if (!_isInitialized)
        {
            try
            {
                doInitialize();
            }
            catch (Exception e)
            {
                _isInvalid = true;
                throw new InvalidExpressionException(e);
            }
            _isInitialized = true;
        }
    }
    
    /**
     * @param applyTo
     * @return the set matching the configured expression applied to applyTo
     * using the algorithm represented by this instance.
     * @throws InvalidExpressionException of a problem occurs initializing the expression
     * @throws EvaluationException if the internal algorithm throws an exception while
     * evaluating.
     * @throws IllegalStateException if evaluate is called again once InvalidExpressionException
     * has already been thrown.
     */
    public final AxiomaticSet evaluate(Node applyTo)
    {
        initialize();
        return doEvaluate(applyTo);
    }
    
    /**
     * Implementers must override to the evaluation of the target,expression pair
     * @param target
     * @return the algorithm evaluated with
     */
    protected abstract AxiomaticSet doEvaluate(Node target);
    
    /**
     * Do any initialization that is required before the algorithm is used
     * to evaluate an expression on any node.  This will get called exactly once and
     * is guaranteed to be called, at latest, immediately before doEvaluate
     * 
     * Method may throw runtime exceptions.  These will be repropagated as
     * InvalidExpressionException's with the original exception wrapped.
     * @throws Exception 
     */
    protected abstract void doInitialize() throws Exception;
}
