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
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractCompCoreQueryFactory;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractJEEModelProviderQuery;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery;
import org.eclipse.jst.jsf.common.internal.finder.AbstractMatcher.AlwaysMatcher;
import org.eclipse.jst.jsf.common.internal.locator.AbstractLocatorProvider;
import org.eclipse.jst.jsf.common.internal.locator.AbstractLocatorProvider.DefaultLocatorProvider;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;
import org.eclipse.jst.jsf.common.internal.resource.ContentTypeResolver;
import org.eclipse.jst.jsf.common.internal.resource.DefaultJarLocator;
import org.eclipse.jst.jsf.common.internal.resource.JavaCoreMediator;
import org.eclipse.jst.jsf.common.internal.resource.ResourceSingletonObjectManager;
import org.eclipse.jst.jsf.common.internal.resource.WorkspaceMediator;
import org.eclipse.jst.jsf.common.internal.strategy.AbstractTestableExtensibleDefaultProviderSelectionStrategy;
import org.eclipse.jst.jsf.common.internal.strategy.ISimpleStrategy;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResourceLocator;
import org.eclipse.jst.jsf.designtime.internal.resources.JarBasedJSFResourceLocator;
import org.eclipse.jst.jsf.designtime.internal.resources.WorkspaceJSFResourceLocator;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;

/**
 * @author cbateman
 * 
 */
public class FaceletTagIndex extends
        ResourceSingletonObjectManager<IProjectTaglibDescriptor, IProject>
{
    private ISimpleStrategy<IProject, IProjectTaglibDescriptorFactory> _tagDescriptorFactoryProvider;

    /**
     * @param ws
     */
    public FaceletTagIndex(final IWorkspace ws)
    {
        super(ws);
        _tagDescriptorFactoryProvider = new ProjectTaglibDescriptorFactoryProviderSelectionStrategy();
    }

    /**
     * @param ws
     * @param tagDescriptorFactoryProvider
     */
    public FaceletTagIndex(final IWorkspace ws, final ISimpleStrategy<IProject, IProjectTaglibDescriptorFactory> tagDescriptorFactoryProvider)
    {
        this(ws);
        _tagDescriptorFactoryProvider = tagDescriptorFactoryProvider;
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
            INSTANCE = new FaceletTagIndex(ws);
        }
        return INSTANCE;
    }

    @Override
    protected IProjectTaglibDescriptor createNewInstance(final IProject project)
    {
        final TagRecordFactory factory = new TagRecordFactory(project, true);
        IProjectTaglibDescriptorFactory descFactory;
        try
        {
            descFactory = _tagDescriptorFactoryProvider
                    .perform(project);
            return descFactory.create(project, factory);
        } catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Force a project to refresh its tag index info
     * 
     * @param project
     */
    public void flush(final IProject project)
    {
        final IProjectTaglibDescriptor flushedDescriptor = unmanageResource(project);
        flushedDescriptor.destroy();
    }

    /**
     * Used to decide what provider gets used to get the descriptor factory.
     * This allows us to inject a different descriptor factory than the default
     * through either a test setter (test-only) or production (ext point).
     * 
     * @author cbateman
     * 
     */
    private static class ProjectTaglibDescriptorFactoryProviderSelectionStrategy
            extends
            AbstractTestableExtensibleDefaultProviderSelectionStrategy<IProject, IProjectTaglibDescriptorFactory>
    {
        private static final IProjectTaglibDescriptorFactory NO_RESULT = null;

        public ProjectTaglibDescriptorFactoryProviderSelectionStrategy()
        {
            super();
            addDefaultStrategy(new DefaultProjectTaglibDescriptorFactoryProvider(
                    new DefaultProjectTaglibDescriptorFactory()));
            addExtensionStrategy(new ExtensionBasedTagDescriptorFactoryProviderStrategy());
        }

        @Override
        public IProjectTaglibDescriptorFactory getNoResult()
        {
            return NO_RESULT;
        }
    }

    private static class DefaultProjectTaglibDescriptorFactoryProvider
            implements
            ISimpleStrategy<IProject, IProjectTaglibDescriptorFactory>
    {
        private final DefaultProjectTaglibDescriptorFactory _factory;

        public DefaultProjectTaglibDescriptorFactoryProvider(
                final DefaultProjectTaglibDescriptorFactory factory)
        {
            _factory = factory;
        }

        public IProjectTaglibDescriptorFactory perform(final IProject input)
                throws Exception
        {
            return _factory;
        }

        public IProjectTaglibDescriptorFactory getNoResult()
        {
            return null;
        }
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
        public IProjectTaglibDescriptor create(final IProject project,
                final TagRecordFactory factory)
        {
            final List<AbstractFaceletTaglibLocator> locators = new ArrayList<AbstractFaceletTaglibLocator>();
            locators.add(new JarFileFaceletTaglibLocator(factory));
            ContextParamSpecifiedFaceletTaglibLocator createContextParamSpecific = createContextParamSpecific(project, factory);
            if (createContextParamSpecific != null)
            {
                locators.add(createContextParamSpecific);
            }
            final List<IJSFResourceLocator> resourceLocators = new ArrayList<IJSFResourceLocator>();
            resourceLocators
                    .add(new JarBasedJSFResourceLocator(Collections.EMPTY_LIST,
                            new CopyOnWriteArrayList<ILocatorChangeListener>(),
                            new DefaultJarLocator(Collections
                                    .singletonList(new AlwaysMatcher()),
                                    new JavaCoreMediator()),
                            new ContentTypeResolver()));
            resourceLocators.add(createJsfResourceLocator(project));
            final DefaultLocatorProvider<IJSFResourceLocator> resourceLocatorProvider = new DefaultLocatorProvider<IJSFResourceLocator>(
                    resourceLocators);
            locators.add(new CompositeComponentTaglibLocator(
                    resourceLocatorProvider));
            final LocatorProvider provider = new LocatorProvider(locators);
            return new ProjectTaglibDescriptor(project, factory, provider);
        }

        private WorkspaceJSFResourceLocator createJsfResourceLocator(final IProject project) {
            return new WorkspaceJSFResourceLocator(
                    Collections.EMPTY_LIST,
                    new CopyOnWriteArrayList<ILocatorChangeListener>(),
                    FaceletCorePlugin.getDefault().getCompCoreQueryFactory().createVirtualComponentQuery(project),
                    new ContentTypeResolver(), project.getWorkspace());
        }

        private ContextParamSpecifiedFaceletTaglibLocator createContextParamSpecific(final IProject project,
                final TagRecordFactory factory) {
            AbstractCompCoreQueryFactory compCoreQueryFactory = FaceletCorePlugin.getDefault()
                    .getCompCoreQueryFactory();
            AbstractJEEModelProviderQuery modelProviderQuery = compCoreQueryFactory
                    .createJEEModelProviderQuery(project);
            AbstractVirtualComponentQuery virtualComponentQuery = compCoreQueryFactory
                    .createVirtualComponentQuery(project);
            if (modelProviderQuery != null && virtualComponentQuery != null) {
                return new ContextParamSpecifiedFaceletTaglibLocator(project, factory, modelProviderQuery,
                        virtualComponentQuery, new WorkspaceMediator());
            }
            return null;
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
