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
package org.eclipse.jst.pagedesigner.actions.link;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * @author mengbo
 * @version 1.5
 */
public class ExtensionReader {
	private static Logger _log = PDPlugin.getLogger(ExtensionReader.class);

	private static List<ILinkCreator> _handlers = null;

	private static final String ATTR_CLASS = "class"; //$NON-NLS-1$

	/** 
	 * @return the ext-pts for the link handler
	 * List is not modifiable
	 */
	public static synchronized List<ILinkCreator> getAllLinkHandlers() {
		if (_handlers == null) {
			_handlers = readAllLinkHandlers();
		}
		return Collections.unmodifiableList(_handlers);

	}

	private static List<ILinkCreator> readAllLinkHandlers() {
	    List<ILinkCreator> result = new ArrayList<ILinkCreator>();
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
				.getExtensionPoint(PDPlugin.getPluginId(),
						IJMTConstants.EXTENSION_POINT_PAGEDESIGNER);
		IExtension[] extensions = extensionPoint.getExtensions();

		for (int i = 0; i < extensions.length; i++) {
			IExtension ext = extensions[i];
			IConfigurationElement[] linkHandlers = ext
					.getConfigurationElements();

			for (int j = 0; j < linkHandlers.length; j++) {
				if (linkHandlers[j].getName()
						.equals(IJMTConstants.LINK_CREATOR)) {
					linkHandlers[j].getAttribute(ATTR_CLASS);
					Object obj;
					try {
						obj = linkHandlers[j]
								.createExecutableExtension(ATTR_CLASS);

						if (obj instanceof ILinkCreator) {
							result.add((ILinkCreator)obj);
						}
					} catch (CoreException e) {
						_log
								.error("Log.Error.ExtensionReader.ReadLinkExtension"); //$NON-NLS-1$
					}
				}
			}
		}
		return result;
	}
}
