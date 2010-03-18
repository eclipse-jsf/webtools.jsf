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
