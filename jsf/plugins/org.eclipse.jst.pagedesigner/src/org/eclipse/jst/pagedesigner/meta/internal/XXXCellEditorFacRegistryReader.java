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
package org.eclipse.jst.pagedesigner.meta.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.meta.IAttributeCellEditorFactory;

/**
 * Read the registry to find out all the CellEditorFactory.
 * 
 * @author mengbo
 * will be deleted
 */
public class XXXCellEditorFacRegistryReader {
	static IAttributeCellEditorFactory[] _factories = null;

	/**
	 * @return the factories
	 */
	public static synchronized IAttributeCellEditorFactory[] getAllFactories() {
		if (_factories == null) {
			_factories = readAllFactories();
		}
		return _factories;

	}

	private static IAttributeCellEditorFactory[] readAllFactories() {
		List result = new ArrayList();
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(PDPlugin.getPluginId(),
						IJMTConstants.EXTENSION_POINT_PAGEDESIGNER);
		IExtension[] extensions = extensionPoint.getExtensions();

		for (int i = 0; i < extensions.length; i++) {
			IExtension ext = extensions[i];
			IConfigurationElement[] facs = ext.getConfigurationElements();

			for (int j = 0; j < facs.length; j++) {
				if (facs[j].getName().equals(
						IJMTConstants.ATTRIBUTE_CELLEDITOR_FACTORY)) {
//					facs[j].getAttribute("class");
					Object obj;
					try {
						obj = facs[j].createExecutableExtension("class");

						if (obj instanceof IAttributeCellEditorFactory) {
							result.add(obj);
						}
					} catch (CoreException e) {
						// ignore the exception
						e.printStackTrace();
					}
				}
			}
		}
		IAttributeCellEditorFactory[] ret = new IAttributeCellEditorFactory[result
				.size()];
		result.toArray(ret);
		return ret;
	}

}
