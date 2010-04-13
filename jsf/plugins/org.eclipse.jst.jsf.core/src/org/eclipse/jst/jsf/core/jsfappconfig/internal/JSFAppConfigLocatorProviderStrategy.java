package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.strategy.ISimpleStrategy;

/**
 * Abstract implementation of a JSFAppConfigLocatorProviderStrategy 
 * implementing {@link ISimpleStrategy}
 */
public abstract class JSFAppConfigLocatorProviderStrategy 
	implements ISimpleStrategy<IProject, IJSFAppConfigLocatorProvider>  {

	public IJSFAppConfigLocatorProvider getNoResult() {
		return null;
	}
}
