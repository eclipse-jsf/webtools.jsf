/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery.DefaultVirtualComponentQuery;
import org.eclipse.jst.jsf.common.internal.finder.AbstractMatcher.AlwaysMatcher;
import org.eclipse.jst.jsf.common.internal.locator.AbstractLocatorProvider;
import org.eclipse.jst.jsf.common.internal.locator.AbstractLocatorProvider.DefaultLocatorProvider;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.common.internal.resource.DefaultJarProvider;
import org.eclipse.jst.jsf.common.internal.resource.ResourceSingletonObjectManager;
import org.eclipse.jst.jsf.common.internal.resource.WorkspaceMediator;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResourceLocator;
import org.eclipse.jst.jsf.designtime.internal.resources.JarBasedJSFResourceLocator;
import org.eclipse.jst.jsf.designtime.internal.resources.WorkspaceJSFResourceLocator;

/**
 * @author cbateman
 * 
 */
/**
 * @author cbateman
 * 
 */
public class FaceletTagIndex extends
        ResourceSingletonObjectManager<IProjectTaglibDescriptor, IProject>
{
    private IProjectTaglibDescriptorFactory _factory;

    /**
     * @param ws
     * @param factory
     */
    public FaceletTagIndex(final IWorkspace ws,
            final IProjectTaglibDescriptorFactory factory)
    {
        super(ws);
        _factory = factory;
    }

    private static FaceletTagIndex INSTANCE;

    /**
     * @param ws
     * @return the singleton instance
     */
    public static synchronized FaceletTagIndex getInstance(final IWorkspace ws)
    {
        if (INSTANCE == null)
        {
            INSTANCE = new FaceletTagIndex(ws,
                    new DefaultProjectTaglibDescriptorFactory());

        }
        return INSTANCE;
    }

    @Override
    protected IProjectTaglibDescriptor createNewInstance(final IProject project)
    {
        final TagRecordFactory factory = new TagRecordFactory(project);

        return _factory.create(project, factory);
    }

    /**
     * Force a project to refresh its tag index info
     * 
     * @param project
     */
    public void flush(final IProject project)
    {
        unmanageResource(project);
    }

    /**
     * The default factory for creating per-project tag descriptors.
     * 
     * @author cbateman
     * 
     */
    public static class DefaultProjectTaglibDescriptorFactory extends
            AbstractProjectTaglibDescriptorFactory
    {
        @Override
        public IProjectTaglibDescriptor create(IProject project,
                TagRecordFactory factory)
        {
            final List<AbstractFaceletTaglibLocator> locators = new ArrayList<AbstractFaceletTaglibLocator>();
            locators.add(new JarFileFaceletTaglibLocator(factory));
            locators.add(new ContextParamSpecifiedFaceletTaglibLocator(project,
                    factory, ModelProviderManager.getModelProvider(project),
                    new DefaultVirtualComponentQuery(), new WorkspaceMediator()));
            List<IJSFResourceLocator> resourceLocators = new ArrayList<IJSFResourceLocator>();
            resourceLocators.add(new JarBasedJSFResourceLocator(
                    Collections.EMPTY_LIST,
                    new CopyOnWriteArrayList<ILocatorChangeListener>(),
                    new DefaultJarProvider(Collections
                            .singletonList(new AlwaysMatcher())),
                    new ContentTypeResolver()));
            resourceLocators.add(new WorkspaceJSFResourceLocator(
                    Collections.EMPTY_LIST,
                    new CopyOnWriteArrayList<ILocatorChangeListener>(),
                    new DefaultVirtualComponentQuery(),
                    new ContentTypeResolver()));
            final DefaultLocatorProvider<IJSFResourceLocator> resourceLocatorProvider = new DefaultLocatorProvider<IJSFResourceLocator>(
                    resourceLocators);
            locators.add(new CompositeComponentTaglibLocator(
                    resourceLocatorProvider));
            LocatorProvider provider = new LocatorProvider(locators);
            return new ProjectTaglibDescriptor(project, factory, provider);
        }
    }

    /**
     * The locator provider used by the tag index.
     * 
     * @author cbateman
     * 
     */
    public static class LocatorProvider extends
            AbstractLocatorProvider<AbstractFaceletTaglibLocator>
    {
        private final List<AbstractFaceletTaglibLocator> _locators;

        /**
         * @param locators
         */
        public LocatorProvider(final List<AbstractFaceletTaglibLocator> locators)
        {
            _locators = locators;
        }

        @Override
        protected void doInitialize()
        {
            // nothing to do
        }

        @Override
        protected List<? extends AbstractFaceletTaglibLocator> doGetLocators()
        {
            return Collections.unmodifiableList(_locators);
        }
    }
}
