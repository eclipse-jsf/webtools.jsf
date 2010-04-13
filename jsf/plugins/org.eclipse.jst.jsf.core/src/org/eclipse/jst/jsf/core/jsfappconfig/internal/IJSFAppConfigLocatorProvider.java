package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import java.util.List;

import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigLocater;

/**
 * Provides list of {@link IJSFAppConfigLocater}s
 *
 */
public interface IJSFAppConfigLocatorProvider {
	/**
	 * @return list of ILocators
	 */
	List<IJSFAppConfigLocater> getLocators();
}
