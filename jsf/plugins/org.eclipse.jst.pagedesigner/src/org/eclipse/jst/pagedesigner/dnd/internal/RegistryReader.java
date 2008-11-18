/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.dnd.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dnd.ILocalDropHandler;

/**
 * Read the registry to find out all the LocalDropHandlers.
 * 
 * @author mengbo
 */
public class RegistryReader {
	final private static Logger _log = PDPlugin.getLogger(RegistryReader.class);

	private static ILocalDropHandler[] _handlers = null;

	/**
	 * @return all registered drop handlers
	 */
	public static synchronized ILocalDropHandler[] getAllHandlers() {
		if (_handlers == null) {
			_handlers = readAllHandlers();
		}
		return _handlers;

	}

	private static ILocalDropHandler[] readAllHandlers() {
		List result = new ArrayList();
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(PDPlugin.getPluginId(),
						IJMTConstants.EXTENSION_POINT_PAGEDESIGNER);
		IExtension[] extensions = extensionPoint.getExtensions();

		for (int i = 0; i < extensions.length; i++) {
			IExtension ext = extensions[i];
			IConfigurationElement[] dropHandlers = ext
					.getConfigurationElements();

			for (int j = 0; j < dropHandlers.length; j++) {
				if (dropHandlers[j].getName().equals(
						IJMTConstants.LOCAL_DROP_HANDLER)) {
					dropHandlers[j].getAttribute("class"); //$NON-NLS-1$
					Object obj;
					try {
						obj = dropHandlers[j]
								.createExecutableExtension("class"); //$NON-NLS-1$

						if (obj instanceof ILocalDropHandler) {
							result.add(obj);
						}
					} catch (CoreException e) {
						_log.info("CoreException", e); //$NON-NLS-1$
					}
				}
			}
		}
		ILocalDropHandler[] ret = new ILocalDropHandler[result.size()];
		result.toArray(ret);
		return ret;
	}
}
