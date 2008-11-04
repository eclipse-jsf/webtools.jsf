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

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.designtime.internal.Messages;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.analyzer.TagAnalyzer;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * A resolving strategy that uses tag introspection.
 * 
 * @author cbateman
 * 
 */
public final class TagIntrospectingStrategy extends JSPTagResolvingStrategy
{
    /**
     * the identifier of this strategy
     */
    public final static String ID = "org.eclipse.jst.jsf.designtime.TagIntrospectingStrategy"; //$NON-NLS-1$
    /**
     * the display name
     */
    public final static String DISPLAY_NAME = Messages.TagIntrospectingStrategy_DisplayName;

    private final IProject _project;

    /**
     * @return the descriptor for this resolver strategy
     */
    public static StrategyDescriptor createDescriptor()
    {
        return new StrategyDescriptor(ID, DISPLAY_NAME);
    }

    /**
     * @param project
     */
    public TagIntrospectingStrategy(final IProject project)
    {
        // TODO: would it be better to have a model context on the resolve?
        _project = project;
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
        return TagAnalyzer.createTLDTagElement(element, _project);
    }

}
