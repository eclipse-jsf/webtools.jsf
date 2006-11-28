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
package org.eclipse.jst.jsf.common.ui.internal.utils;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/**
 * The Entity Resolver for loading local dtd copies. Currently setup for:
 * <ul>
 * <li>web.xml (V2.3)</li>
 * <li>faces-config.xml (V1.0)</li>
 * <li>faces-config.xml (V1.1)</li>
 * </ul>
 * 
 * <pre>
 *  For Example:
 * </pre>
 *	<pre>
 * SAXReader reader = new SAXReader();
 * reader.setIgnoreComments(false);
 * JMTEntityResolver resolver = new JMTEntityResolver();
 * reader.setEntityResolver(resolver);
 * </pre>
 * 
 * @author mengbo
 * @version 1.5
 */
public class JMTEntityResolver implements EntityResolver {
	public InputSource resolveEntity(String publicId, String systemId) {
		String localURL;
		if (systemId
				.equalsIgnoreCase("http://java.sun.com/j2ee/dtds/web-app_2_2.dtd")) {
			localURL = "dtd/web-app_2_2.dtd";
		} else if (systemId
				.equalsIgnoreCase("http://java.sun.com/dtd/web-app_2_3.dtd")) //$NON-NLS-1$
		{
			localURL = "dtd/web-app_2_3.dtd"; //$NON-NLS-1$
		} else if (systemId
				.equalsIgnoreCase("http://java.sun.com/dtd/web-facesconfig_1_0.dtd")) //$NON-NLS-1$
		{
			localURL = "dtd/web-facesconfig_1_0.dtd"; //$NON-NLS-1$
		} else if (systemId
				.equalsIgnoreCase("http://java.sun.com/dtd/web-facesconfig_1_1.dtd")) //$NON-NLS-1$
		{
			localURL = "dtd/web-facesconfig_1_1.dtd"; //$NON-NLS-1$
		} else {
			// use the default behaviour
			return null;
		}

		JSFUICommonPlugin plugin = JSFUICommonPlugin.getDefault();
		URL url = FileLocator.find(Platform.getBundle(JSFUICommonPlugin.getPluginId()),
				new Path(localURL), null);
		InputSource source = null;
		try {
			source = new InputSource(url.openStream());
			source.setPublicId(publicId);
		} catch (IOException e) {
			Logger log = plugin.getRootLogger();
			log.error("Web.XML.DTDError", e); //$NON-NLS-1$
		}
		return source;
	}
}
