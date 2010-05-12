/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.pde.AbstractSimpleClassExtensionRegistryReader;
import org.eclipse.jst.jsf.common.internal.resource.ResourceSingletonObjectManager;
import org.eclipse.jst.jsf.common.internal.strategy.AbstractTestableExtensibleDefaultProviderSelectionStrategy;
import org.eclipse.jst.jsf.common.internal.strategy.ISimpleStrategy;
import org.eclipse.jst.jsf.common.internal.strategy.TestableProjectFactoryStrategy;

/**
 * Produces {@link IMetaDataModelManager}s
 *
 */
public final class MetaDataModelManagerFactory extends ResourceSingletonObjectManager<IMetaDataModelManager, IResource> {

	private static MetaDataModelManagerFactory 	INSTANCE;
	
	private IMetaDataModelManagerFactory 		EXT_PT_BASED_FACTORY;
	
	//private constructor
	private MetaDataModelManagerFactory(final IWorkspace workspace) {
		super(workspace);
	}
	
	/**
	 * Project property session key for testing.
	 * Project value should hold the testable {@link IMetaDataModelManagerFactory} instance or null
	 * NOT API - for testing purposes
	 */
	public static final QualifiedName TESTABLE_FACTORY_SESSION_KEY = new QualifiedName(JSFCommonPlugin.PLUGIN_ID, "MDModelManagerFactoryInstance"); //$NON-NLS-1$
	
	/**
	 * @param project 
	 * @return IMetaDataModelManager
	 */
	public static IMetaDataModelManager getMetaDataModelManagerInstance(final IProject project) {
		try {
			return getMetaDataModelManagerFactoryInstance(project).getInstance(project != null ? project : ResourcesPlugin.getWorkspace().getRoot());			
		} catch (ManagedObjectException e) {
			if (project != null)
				JSFCommonPlugin.log(e, "Cannot create IMetaDataModelManager for "+project.getName()+ " (1)"); //$NON-NLS-1$ //$NON-NLS-2$
			else
				JSFCommonPlugin.log(e, "Cannot create workspace shared IMetaDataModelManager (1)"); //$NON-NLS-1$ 
		}
		return null;
	}	
	
	private synchronized static MetaDataModelManagerFactory getMetaDataModelManagerFactoryInstance(final IProject project) {
		if (INSTANCE == null) {
			final IWorkspace workspace = getWorkspace(project);
			INSTANCE = new MetaDataModelManagerFactory(workspace);
			INSTANCE.init();
		}
		return INSTANCE;
	}

	private static IWorkspace getWorkspace(final IProject project) {
		if (project != null)
			return project.getWorkspace();
		
		return ResourcesPlugin.getWorkspace();
	}


	private void init() {
		final MetaDataModelManagerFactoryExtensionPointReader reader = new MetaDataModelManagerFactoryExtensionPointReader();
		final List<IMetaDataModelManagerFactory> res = reader.getExtensions();
		if (res != null && res.size() > 0) {//return first
			EXT_PT_BASED_FACTORY = res.get(0);
		}
	}
	
	@Override
	protected IMetaDataModelManager createNewInstance(final IResource resource) {
		try {
			final IMetaDataModelManagerFactory factory = getMetaDataModelManagerFactoryProviderInstances(resource);
			if (factory != null)
				return factory.getInstance(resource);
		} catch (Exception e) {
			if (resource != null)
				JSFCommonPlugin.log(e, "Cannot create IMetaDataModelManager for "+resource.getName()+ " (2)"); //$NON-NLS-1$ //$NON-NLS-2$
			else
				JSFCommonPlugin.log(e, "Cannot create workspace shared IMetaDataModelManager (2)"); //$NON-NLS-1$ 
		}			
		return null;
	}

	private IMetaDataModelManagerFactory getMetaDataModelManagerFactoryProviderInstances(final IResource resource) {
		final CompositeFactorySelectionStrategyProvider factoryProvider = new CompositeFactorySelectionStrategyProvider();		
		return factoryProvider != null ? factoryProvider.getFactoryToUse(resource) : null;
	}
	
	private class CompositeFactorySelectionStrategyProvider
	{
		public IMetaDataModelManagerFactory getFactoryToUse(final IResource resource) {
			final MetaDataModelManagerProviderSelectionStrategy providerSelector = new MetaDataModelManagerProviderSelectionStrategy();		
	        addStrategies(providerSelector);
	        
			final IMetaDataModelManagerFactory provider = providerSelector.perform(resource);
	        if (provider != providerSelector.getNoResult())
	        {
	            return provider;
	        }
	        return null;
		}
		
		private void addStrategies(final MetaDataModelManagerProviderSelectionStrategy providerSelector) {
			providerSelector.addDefaultStrategy(new DefaultManagerProviderStrategy());
			providerSelector.addExtensionStrategy(new ExtensionBasedManagerProviderStrategy());
			providerSelector.addTestableStrategy(new TestableManagerProviderStrategy(TESTABLE_FACTORY_SESSION_KEY));				
		}
		
	}
	
	private static class MetaDataModelManagerProviderSelectionStrategy
		extends
			AbstractTestableExtensibleDefaultProviderSelectionStrategy<IResource, IMetaDataModelManagerFactory> {
		
		private static final IMetaDataModelManagerFactory NO_RESULT = null;
		
		@Override
		public IMetaDataModelManagerFactory getNoResult() {
			return NO_RESULT;
		}
	}
	
	private abstract class AbstractManagerProviderStrategy 
		implements ISimpleStrategy<IResource, IMetaDataModelManagerFactory> {
		
		private final IMetaDataModelManagerFactory NO_RESULT = null;
		public IMetaDataModelManagerFactory getNoResult() {
			return NO_RESULT;
		}
	}
	
	private class DefaultManagerProviderStrategy extends
			AbstractManagerProviderStrategy {

		public IMetaDataModelManagerFactory perform(final IResource input) throws Exception {
			return new DefaultManagerProvider();
		}

	}

	private static class DefaultManagerProvider 
		implements IMetaDataModelManagerFactory {

		public IMetaDataModelManager getInstance(final IResource project) {
			if (project != null && project instanceof IProject)
				return new MetaDataModelManager((IProject)project);
			return MetaDataModelManager.getSharedInstance();
		}
		
	}
	
	private class ExtensionBasedManagerProviderStrategy 
		extends AbstractManagerProviderStrategy {
	
		public IMetaDataModelManagerFactory perform(final IResource input) throws Exception {
			return EXT_PT_BASED_FACTORY != null ? EXT_PT_BASED_FACTORY : getNoResult();
		}
	}
	
	private static class TestableManagerProviderStrategy<IMetaDataModelManagerFactory>
		extends TestableProjectFactoryStrategy<IMetaDataModelManagerFactory> {
	
		/**
		 * @param testableFactorySessionKey - project property session key for property value holding testable instance 
		 */
		public TestableManagerProviderStrategy(final QualifiedName testableFactorySessionKey) {
			super(testableFactorySessionKey);
		}	
	
	}
	
	private static class MetaDataModelManagerFactoryExtensionPointReader extends
			AbstractSimpleClassExtensionRegistryReader<IMetaDataModelManagerFactory> {
	
		private static final String EXT_PT_ID 		= "mdModelManagerFactory"; //$NON-NLS-1$
		private static final String EXT_PT_ELEMENT 	= "factory"; //$NON-NLS-1$
		private static final String EXT_PT_ATTR 	= "class"; //$NON-NLS-1$
		
		protected MetaDataModelManagerFactoryExtensionPointReader() {
			super(
					JSFCommonPlugin.PLUGIN_ID,
					EXT_PT_ID, EXT_PT_ELEMENT, EXT_PT_ATTR, 
					new CompareOrgEclipseJstContributorsLastComparator<IMetaDataModelManagerFactory>()
			); 		
		}
		
		@Override
		protected void handleLoadFailure(final CoreException ce) {
			JSFCommonPlugin.log(ce,
					"Error loading IMetaDataModelManagerFactory from extension"); //$NON-NLS-1$
		
		}
	
	}
	
}
