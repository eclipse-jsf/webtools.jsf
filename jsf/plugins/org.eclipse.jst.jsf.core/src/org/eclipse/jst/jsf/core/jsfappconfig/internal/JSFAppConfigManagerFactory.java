package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
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
	private static final Object LOCK = new Object();

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
	 * Note: to avoid possible deadlocks during the construction and 
	 * initialization of an instance, this method runs with a project
	 * scheduling rule before trying to synchronize.
	 *
	 * @param project
	 * @return IJSFAppConfigManager
	 */
	public static IJSFAppConfigManager getJSFAppConfigManagerInstance(final IProject project) {
        final IJSFAppConfigManager jsfAppConfigMgr[] = {null};
        IProgressMonitor monitor = new NullProgressMonitor();

        try {
        	// to avoid a possible deadlock issue, ensure there's a
        	// scheduling rule (at least for the project) applied
        	// before synchronizing to create and initialize the
        	// actual IJSFAppConfigManager instance.
        	IWorkspaceRunnable runnable= new IWorkspaceRunnable() {
                public void run(IProgressMonitor pm) throws CoreException {
                    synchronized (LOCK) {
                		try {
                			jsfAppConfigMgr[0] = getJSFAppConfigManagerFactoryInstance(project).getInstance(project);			
                		} catch (ManagedObjectException e) {
                			JSFCorePlugin.log(e, "Cannot create IJSFAppConfigManager for " + project.getName() + " (1)"); //$NON-NLS-1$ //$NON-NLS-2$
                		}
                    }
                }
            };

            ISchedulingRule currentRule = Job.getJobManager().currentRule();
            if (currentRule != null) {
                if (currentRule.contains(project)) {
                    runnable.run(monitor);
                    return jsfAppConfigMgr[0];
                } else if (currentRule.isConflicting(project)) {
                    // we can't expand an existing rule, return null
                    return null;
                }
            }

            IWorkspace workspace = ResourcesPlugin.getWorkspace();
            workspace.run(runnable, project, IWorkspace.AVOID_UPDATE, monitor);
        } catch(CoreException ce) {
            //log error
			JSFCorePlugin.log(ce, "Cannot create IJSFAppConfigManager for " + project.getName() + " (1)"); //$NON-NLS-1$ //$NON-NLS-2$
        }

        return jsfAppConfigMgr[0];
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
			if (factory != null) {
				IJSFAppConfigManager jsfAppConfigMgr = factory.getInstance(project);

                // Make a call to ensure the underlying EMF models in
				// the providers (from the locators) are initialized
                // in this instance begin created. Helps avoid some
                // potential concurrency issues as models are created.
				jsfAppConfigMgr.getApplications();

				return jsfAppConfigMgr;
			}
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
