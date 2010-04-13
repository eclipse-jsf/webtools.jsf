package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.jsf.common.internal.pde.AbstractSimpleClassExtensionRegistryReader;
import org.eclipse.jst.jsf.common.internal.strategy.AbstractTestableExtensibleDefaultProviderSelectionStrategy;
import org.eclipse.jst.jsf.common.internal.strategy.TestableProjectFactoryStrategy;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigLocater;

/**
 * Composite strategy for providing {@link IJSFAppConfigLocatorProvider}s
 *
 */
public class CompositeJSFAppConfigLocatorProviderStrategy
		implements IJSFAppConfigLocatorProvider {

	/**
	 * Project property session key for testing.
	 * Project value should hold the testable IJSFAppConfigLocatorProvider instance or null
	 * NOT API - for testing purposes
	 */
	public static final QualifiedName TESTABLE_FACTORY_SESSION_KEY = new QualifiedName(JSFCorePlugin.PLUGIN_ID, "LocatorProviderStrategyFactory"); //$NON-NLS-1$
	
	private IProject _project;

	/**
	 * @param project - may be null
	 */
	public CompositeJSFAppConfigLocatorProviderStrategy(final IProject project) {
		_project = project;
	}

	public List<IJSFAppConfigLocater> getLocators() {
		final LocatorProviderSelectionStrategy providerSelector = new LocatorProviderSelectionStrategy();		
        addLocatorProviderStrategies(providerSelector);
        
		final IJSFAppConfigLocatorProvider provider = providerSelector.perform(_project);
        if (provider != providerSelector.getNoResult())
        {
            return Collections.unmodifiableList(provider.getLocators());
        }
        return Collections.emptyList();
	}

	private void addLocatorProviderStrategies(final LocatorProviderSelectionStrategy providerSelector) {
		providerSelector.addDefaultStrategy(new DefaultJSFAppConfigLocatorProviderStrategy());
		providerSelector.addExtensionStrategy(new ExtensionPointLocatorProviderStrategy());
		providerSelector.addTestableStrategy(new TestableLocatorProviderStrategy());
	}
	
	private static class TestableLocatorProviderStrategy extends
			TestableProjectFactoryStrategy<IJSFAppConfigLocatorProvider> {

		public TestableLocatorProviderStrategy() {
			super(TESTABLE_FACTORY_SESSION_KEY);
		}		

	};

	private static class ExtensionPointLocatorProviderStrategy extends
			JSFAppConfigLocatorProviderStrategy {

		public IJSFAppConfigLocatorProvider perform(final IProject input) throws Exception {
			final JSFAppConfigLocatorProviderExtensionPointReader reader = new JSFAppConfigLocatorProviderExtensionPointReader();
			final List<IJSFAppConfigLocatorProvider> res = reader.getExtensions();
			if (res != null && res.size() > 0) {
				return res.get(0);
			}
			return getNoResult();
		}

	}
	
	private static class LocatorProviderSelectionStrategy
			extends
				AbstractTestableExtensibleDefaultProviderSelectionStrategy<IProject, IJSFAppConfigLocatorProvider> {
		
		private static final IJSFAppConfigLocatorProvider NO_RESULT = null;

		@Override
		public IJSFAppConfigLocatorProvider getNoResult() {
			return NO_RESULT;
		}
		
	}
	
	private static class JSFAppConfigLocatorProviderExtensionPointReader extends
		AbstractSimpleClassExtensionRegistryReader<IJSFAppConfigLocatorProvider> {
	
		private static final String EXT_PT_ID 		= "jsfAppConfigLocatorProviderFactory"; //$NON-NLS-1$
		private static final String EXT_PT_ELEMENT 	= "locatorProvider"; //$NON-NLS-1$
		private static final String EXT_PT_ATTR 	= "class"; //$NON-NLS-1$
		
		protected JSFAppConfigLocatorProviderExtensionPointReader() {
			super(
					org.eclipse.jst.jsf.core.internal.JSFCorePlugin.PLUGIN_ID,
					EXT_PT_ID, EXT_PT_ELEMENT, EXT_PT_ATTR,
					new CompareOrgEclipseJstContributorsLastComparator<IJSFAppConfigLocatorProvider>()
			); 		
		}
		
		@Override
		protected void handleLoadFailure(final CoreException ce) {
			org.eclipse.jst.jsf.core.internal.JSFCorePlugin.log(ce,
					"Error loading JSFAppConfigLocatorProvider from extension"); //$NON-NLS-1$
		
		}

	}
}