package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.strategy.ISimpleStrategy;

/**
 * Abstract provider strategy for {@link IJSFAppConfigManager}s
 *
 */
public abstract class AbstractJSFAppConfigManagerProviderStrategy 
	implements ISimpleStrategy<IProject, IJSFAppConfigManagerFactory> {
	
	public IJSFAppConfigManagerFactory getNoResult() {
		return null;
	}
}
