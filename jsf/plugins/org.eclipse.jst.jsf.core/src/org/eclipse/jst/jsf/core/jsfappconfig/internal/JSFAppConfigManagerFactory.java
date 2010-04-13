package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.jsf.common.internal.resource.ResourceSingletonObjectManager;
import org.eclipse.jst.jsf.common.internal.strategy.AbstractTestableExtensibleDefaultProviderSelectionStrategy;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Responsible for producing and caching {@link IJSFAppConfigManager} instances by project.
 * <p>
 * This class will guarantee that he same instance of the {@link IJSFAppConfigManager} is returned for a given project
 *
 */
public class JSFAppConfigManagerFactory 
		extends 
			ResourceSingletonObjectManager<IJSFAppConfigManager, IProject>{
	
	private static JSFAppConfigManagerFactory INSTANCE;
	
	//private constructor
	private JSFAppConfigManagerFactory(final IProject project) {
		super(project.getWorkspace());
	}
	
	/**
	 * Project property session key for testing.
	 * Project value should hold the testable IJSFAppConfigManagerFactory instance or null
	 * NOT API - for testing purposes
	 */
	public static final QualifiedName TESTABLE_FACTORY_SESSION_KEY = new QualifiedName(JSFCorePlugin.PLUGIN_ID, "JSFAppConfigManagerFactoryInstance"); //$NON-NLS-1$
	
	/**
	 * @param project
	 * @return IJSFAppConfigManager
	 */
	public synchronized static IJSFAppConfigManager getJSFAppConfigManagerInstance(final IProject project) {
		try {
			return getJSFAppConfigManagerFactoryInstance(project).getInstance(project);			
		} catch (ManagedObjectException e) {
			JSFCorePlugin.log(e, "Cannot create IJSFAppConfigManager for "+project.getName()+ " (1)"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return null;
	}
	
	private static JSFAppConfigManagerFactory getJSFAppConfigManagerFactoryInstance(final IProject project) {
		if (INSTANCE == null) {
			INSTANCE = new JSFAppConfigManagerFactory(project);
		}
		return INSTANCE;
	}

	@Override
	protected IJSFAppConfigManager createNewInstance(final IProject project) {
		try {
			final IJSFAppConfigManagerFactory factory = getJSFAppConfigManagerFactoryProviderInstance(project);
			if (factory != null)
				return factory.getInstance(project);
		} catch (ManagedObjectException e) {
			JSFCorePlugin.log(e, "Cannot create IJSFAppConfigManager for "+project.getName()+ " (2)"); //$NON-NLS-1$ //$NON-NLS-2$
		}			
		return null;
	}

	private IJSFAppConfigManagerFactory getJSFAppConfigManagerFactoryProviderInstance(final IProject project) {
		final CompositeFactorySelectionStrategyProvider factoryProvider = new CompositeFactorySelectionStrategyProvider();		
		return factoryProvider != null ? factoryProvider.getFactoryToUse(project) : null;
	}
	
	private static class CompositeFactorySelectionStrategyProvider
	{
		public IJSFAppConfigManagerFactory getFactoryToUse(final IProject project) {
			final JSFAppConfigManagerProviderSelectionStrategy providerSelector = new JSFAppConfigManagerProviderSelectionStrategy();		
	        addStrategies(providerSelector);
	        
			final IJSFAppConfigManagerFactory provider = providerSelector.perform(project);
	        if (provider != providerSelector.getNoResult())
	        {
	            return provider;
	        }
	        return null;
		}
		
		private void addStrategies(final JSFAppConfigManagerProviderSelectionStrategy providerSelector) {
			providerSelector.addDefaultStrategy(new DefaultJSFAppConfigManagerProviderStrategy());
			providerSelector.addExtensionStrategy(new ExtensionBasedJSFAppConfigManagerProviderStrategy());
			providerSelector.addTestableStrategy(new TestableJSFAppConfigManagerProviderStrategy(TESTABLE_FACTORY_SESSION_KEY));				
		}
		
	}
	
	private static class JSFAppConfigManagerProviderSelectionStrategy
		extends
			AbstractTestableExtensibleDefaultProviderSelectionStrategy<IProject, IJSFAppConfigManagerFactory> {
		
		private static final IJSFAppConfigManagerFactory NO_RESULT = null;
		
		@Override
		public IJSFAppConfigManagerFactory getNoResult() {
			return NO_RESULT;
		}
	}
	
}
