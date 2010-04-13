package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.internal.pde.AbstractSimpleClassExtensionRegistryReader;

/**
 * Extension-based strategy for returning {@link IJSFAppConfigManager}s
 *
 */
public class ExtensionBasedJSFAppConfigManagerProviderStrategy extends
		AbstractJSFAppConfigManagerProviderStrategy {

	private static IJSFAppConfigManagerFactory EXT_PT_BASED_FACTORY;
	static {
		final JSFAppConfigManagerFactoryExtensionPointReader reader = new JSFAppConfigManagerFactoryExtensionPointReader();
		final List<IJSFAppConfigManagerFactory> res = reader.getExtensions();
		if (res != null && res.size() > 0) {//return first
			EXT_PT_BASED_FACTORY = res.get(0);
		}
	}
	
	public IJSFAppConfigManagerFactory perform(final IProject input) throws Exception {
		return EXT_PT_BASED_FACTORY != null ? EXT_PT_BASED_FACTORY : getNoResult();
	}
	
	private static class JSFAppConfigManagerFactoryExtensionPointReader extends
			AbstractSimpleClassExtensionRegistryReader<IJSFAppConfigManagerFactory> {
	
		private static final String EXT_PT_ID 		= "jsfAppConfigManagerFactory"; //$NON-NLS-1$
		private static final String EXT_PT_ELEMENT 	= "factory"; //$NON-NLS-1$
		private static final String EXT_PT_ATTR 	= "class"; //$NON-NLS-1$
		
		protected JSFAppConfigManagerFactoryExtensionPointReader() {
			super(
					org.eclipse.jst.jsf.core.internal.JSFCorePlugin.PLUGIN_ID,
					EXT_PT_ID, EXT_PT_ELEMENT, EXT_PT_ATTR, 
					new CompareOrgEclipseJstContributorsLastComparator<IJSFAppConfigManagerFactory>()
			); 		
		}
		
		@Override
		protected void handleLoadFailure(final CoreException ce) {
			org.eclipse.jst.jsf.core.internal.JSFCorePlugin.log(ce,
					"Error loading JSFAppConfigLocatorProvider from extension"); //$NON-NLS-1$
		
		}

	}
	
}
