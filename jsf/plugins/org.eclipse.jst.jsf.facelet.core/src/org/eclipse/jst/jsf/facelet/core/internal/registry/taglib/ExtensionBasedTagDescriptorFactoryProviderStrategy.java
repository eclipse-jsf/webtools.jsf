package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.internal.pde.AbstractSimpleClassExtensionRegistryReader;
import org.eclipse.jst.jsf.common.internal.strategy.ISimpleStrategy;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;

/**
 * Extension-based strategy for returning {@link IJSFAppConfigManager}s
 * 
 */
public class ExtensionBasedTagDescriptorFactoryProviderStrategy implements
        ISimpleStrategy<IProject, IProjectTaglibDescriptorFactory>
{
    private static IProjectTaglibDescriptorFactory EXT_PT_BASED_FACTORY;
    static
    {
        final ProjectTaglibDescriptorFactoryExtensionPointReader reader = new ProjectTaglibDescriptorFactoryExtensionPointReader();
        final List<IProjectTaglibDescriptorFactory> res = reader
                .getExtensions();
        if (res != null && res.size() > 0)
        {// return first
            EXT_PT_BASED_FACTORY = res.get(0);
        }
    }

    public IProjectTaglibDescriptorFactory perform(final IProject input)
            throws Exception
    {
        return EXT_PT_BASED_FACTORY != null ? EXT_PT_BASED_FACTORY
                : getNoResult();
    }

    private static class ProjectTaglibDescriptorFactoryExtensionPointReader
            extends
            AbstractSimpleClassExtensionRegistryReader<IProjectTaglibDescriptorFactory>
    {
        private static final String EXT_PT_ID = "projectTaglibDescriptorFactory"; //$NON-NLS-1$
        private static final String EXT_PT_ELEMENT = "factory"; //$NON-NLS-1$
        private static final String EXT_PT_ATTR = "class"; //$NON-NLS-1$

        protected ProjectTaglibDescriptorFactoryExtensionPointReader()
        {
            super(
                    FaceletCorePlugin.PLUGIN_ID,
                    EXT_PT_ID,
                    EXT_PT_ELEMENT,
                    EXT_PT_ATTR,
                    new CompareOrgEclipseJstContributorsLastComparator<IProjectTaglibDescriptorFactory>());
        }

        @Override
        protected void handleLoadFailure(final CoreException ce)
        {
            org.eclipse.jst.jsf.core.internal.JSFCorePlugin
                    .log(ce,
                            "Error loading ProjectTaglibDescriptorFactory from extension"); //$NON-NLS-1$
        }
    }

    public IProjectTaglibDescriptorFactory getNoResult()
    {
        return null;
    }
}
