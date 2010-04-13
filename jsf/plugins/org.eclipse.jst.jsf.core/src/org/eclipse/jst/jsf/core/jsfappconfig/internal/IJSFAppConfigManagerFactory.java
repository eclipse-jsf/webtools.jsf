package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.managedobject.ObjectManager.ManagedObjectException;

/**
 * Factory for creating {@link IJSFAppConfigManager} instances.
 * <p>
 * NOT API
 */
public interface IJSFAppConfigManagerFactory {
	/**
	 * @param project - may be null
	 * @return instance of an {@link IJSFAppConfigManager} for the project.
	 * @throws ManagedObjectException 
	 */
	IJSFAppConfigManager getInstance(IProject project) throws ManagedObjectException;
}
