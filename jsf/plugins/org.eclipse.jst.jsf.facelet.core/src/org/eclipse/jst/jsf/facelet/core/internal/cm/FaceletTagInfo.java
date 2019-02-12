/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.cm;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facelet.core.internal.cm.strategy.FaceletExternalMetadataStrategy;
import org.eclipse.jst.jsf.facelet.core.internal.cm.strategy.IExternalMetadataStrategy;
import org.eclipse.jst.jsf.facelet.core.internal.cm.strategy.MDExternalMetadataStrategy;
import org.eclipse.jst.jsf.facelet.core.internal.cm.strategy.TagInfoStrategyComposite;

/*package*/class FaceletTagInfo extends CompositeTagInfo
{
    public FaceletTagInfo(final IProject project, final String uri)
    {
        super(uri, createStrategy(project));
    }

    private static TagInfoStrategyComposite createStrategy(
            final IProject project)
    {
        final IExternalMetadataStrategy mdStrategy = MDExternalMetadataStrategy
        .create(project);
        final IExternalMetadataStrategy faceletStrategy = new FaceletExternalMetadataStrategy(
                project);
        final List<String> ids = new ArrayList<String>();
        ids.add(FaceletExternalMetadataStrategy.STRATEGY_ID);
        ids.add(MDExternalMetadataStrategy.STRATEGY_ID);

        final TagInfoStrategyComposite strategyComposite = new TagInfoStrategyComposite(
                ids);
        strategyComposite.addStrategy(faceletStrategy);
        strategyComposite.addStrategy(mdStrategy);
        return strategyComposite;
    }
}
