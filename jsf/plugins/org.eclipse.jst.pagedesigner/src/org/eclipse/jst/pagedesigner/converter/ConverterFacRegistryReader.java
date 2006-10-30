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
package org.eclipse.jst.pagedesigner.converter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * @author mengbo
 * @version 1.5
 */
public class ConverterFacRegistryReader {
	static IConverterFactory[] _handlers = null;

	public static synchronized IConverterFactory[] getAllHandlers() {
		if (_handlers == null) {
			_handlers = readAllHandlers();
		}
		return _handlers;

	}

	private static IConverterFactory[] readAllHandlers() {
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
						IJMTConstants.TAG_CONVERTER_FACTORY)) {
					dropHandlers[j].getAttribute("class");
					Object obj;
					try {
						obj = dropHandlers[j]
								.createExecutableExtension("class");

						if (obj instanceof IConverterFactory) {
							result.add(obj);
						}
					} catch (CoreException e) {
						// ignore the exception
						e.printStackTrace();
					}
				}
			}
		}
		IConverterFactory[] ret = new IConverterFactory[result.size()];
		result.toArray(ret);
		return ret;
	}

}
