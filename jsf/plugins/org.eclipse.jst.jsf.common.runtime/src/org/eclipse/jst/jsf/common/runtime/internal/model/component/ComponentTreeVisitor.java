/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
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