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
import org.eclipse.jst.jsf.facelet.core.internal.cm.strategy.IExternalMetadataStrategy;
import org.eclipse.jst.jsf.facelet.core.internal.cm.strategy.JSPExternalMetadataStrategy;
import org.eclipse.jst.jsf.facelet.core.internal.cm.strategy.MDExternalMetadataStrategy;
import org.eclipse.jst.jsf.facelet.core.internal.cm.strategy.TagInfoStrategyComposite;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;

/**
 * An external tag info that checks first the meta-data repository and second in
 * the provided TLDDocument for data.
 * 
 * @author cbateman
 * 
 */
/* package */class MetadataTagInfo extends CompositeTagInfo
{
    private MetadataTagInfo(final IProject project, final TLDDocument doc,
            final String uri)
    {
        super(uri, createStrategy(project,doc));
    }

    public MetadataTagInfo(final IProject project, final String uri)
    {
        this(project, null, uri);
    }

    /**
     * @param project
     * @param doc
     */
    public MetadataTagInfo(final IProject project, final TLDDocument doc)
    {
        this(project, doc, doc.getUri());
    }

    private static TagInfoStrategyComposite createStrategy(final IProject project, final TLDDocument doc)
    {
        IExternalMetadataStrategy mdStrategy = MDExternalMetadataStrategy.create(project);
        JSPExternalMetadataStrategy jspStrategy = new JSPExternalMetadataStrategy(doc);

        final List<String> ids = new ArrayList<String>();
        ids.add(MDExternalMetadataStrategy.STRATEGY_ID);
        ids.add(JSPExternalMetadataStrategy.STRATEGY_ID);

        TagInfoStrategyComposite strategyComposite = new TagInfoStrategyComposite(ids);
        strategyComposite.addStrategy(mdStrategy);
        strategyComposite.addStrategy(jspStrategy);
        return strategyComposite;
    }
}
