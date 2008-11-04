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
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.IAttributeAdvisor.NullAttributeAdvisor;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * This a fallback strategy that always creates an element for a tld decl.  This
 * can be tacked to the end of a composite strategy (or used alone) to ensure
 * that a basic ITagElement is always created for a TLDElementDeclaration.
 * 
 * @author cbateman
 *
 */
public class UnresolvedJSPTagResolvingStrategy extends JSPTagResolvingStrategy
{
    /**
     * the identifier of this strategy
     */
    public final static String ID = "org.eclipse.jst.jsf.designtime.UnresolvedJSPTagResolvingStrategy"; //$NON-NLS-1$
    /**
     * the displayable name
     */
    public final static String DISPLAY_NAME = Messages.UnresolvedJSPTagResolvingStrategy_1;

    /**
     * @return the descriptor for this resolver strategy
     */
    public static StrategyDescriptor createDescriptor()
    {
        return new StrategyDescriptor(ID, DISPLAY_NAME);
    }

    @Override
    public String getId()
    {
        return ID;
    }

    public String getDisplayName()
    {
        return DISPLAY_NAME;
    }

    @Override
    public ITagElement resolve(TLDElementDeclaration element)
    {
        // just create a tag element
        return new TLDTagElement(element, new NullAttributeAdvisor());
    }

}
