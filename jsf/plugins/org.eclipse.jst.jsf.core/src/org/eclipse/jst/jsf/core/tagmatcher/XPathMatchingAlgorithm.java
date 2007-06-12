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

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.eclipse.jst.jsf.common.sets.ConcreteAxiomaticSet;
import org.eclipse.jst.jsf.common.sets.NodeSet;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A algorithm that uses an XPath expression to evaluate a result.  The
 * algorithm assumes (although cannot verify beforehand) that the expression
 * provided can be evaluated with a node set return type (see JAXP).  If the
 * algorithm's underlying XPath evaluator throws an exception indicating that
 * the return type cannot be coerced to a node set, the algorithm will return
 * an empty set (no match) unless the strictMatching flag is set to true, in which
 * case it will re-throw the exception wrapped as an unchecked EvaluationException.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public class XPathMatchingAlgorithm extends TagMatchingAlgorithm 
{
    private final boolean  _isStrictMatching;
    private final XPath    _xpath;
    private XPathExpression _compiledExpr;
    
    /**
     * @param expression
     */
    public XPathMatchingAlgorithm(String expression)
    {
        this(expression, false);
    }

    /**
     * @param expression
     * @param isStrictMatching 
     */
    public XPathMatchingAlgorithm(String expression, boolean isStrictMatching) 
    {
        super(expression);
        _isStrictMatching = isStrictMatching;
        _xpath = XPathFactory.newInstance().newXPath();
    }

    protected AxiomaticSet doEvaluate(Node target) 
    {
        try
        {
            NodeList nodeList = 
                (NodeList) _compiledExpr.evaluate(target, XPathConstants.NODESET);
            return new NodeSet(nodeList);
        }
        catch (XPathExpressionException  xpam)
        {
            if (_isStrictMatching)
            {
                throw new EvaluationException(xpam);
            }
            // if not strict checking, return empty set
            return new ConcreteAxiomaticSet();
        }
    }

    protected void doInitialize() throws XPathExpressionException {
        // compile the xpath expr
        _compiledExpr = _xpath.compile(_expression);
    }
}
