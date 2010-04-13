package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigManager;

/**
 * Default strategy for returning {@link IJSFAppConfigManager}s
 *
 */
@SuppressWarnings("deprecation")
public class DefaultJSFAppConfigManagerProviderStrategy extends
		AbstractJSFAppConfigManagerProviderStrategy {

	public IJSFAppConfigManagerFactory perform(final IProject input) throws Exception {
		return new DefaultProvider();
	}

	private static class DefaultProvider 
		implements IJSFAppConfigManagerFactory {
		
		public IJSFAppConfigManager getInstance(final IProject project) {
			//deprecated warning is to be expected here
			return JSFAppConfigManager.getInstance(project);
		}
				
	}
	
}
