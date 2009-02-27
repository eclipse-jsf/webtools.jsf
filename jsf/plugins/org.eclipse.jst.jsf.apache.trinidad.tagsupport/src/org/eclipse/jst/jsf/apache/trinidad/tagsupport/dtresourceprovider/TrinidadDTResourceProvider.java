/**
 * Copyright (c) 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.dtresourceprovider;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Collections;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.Messages;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.TrinidadTagSupportActivator;
import org.eclipse.jst.pagedesigner.dtresourceprovider.DefaultDTResourceProvider;
import org.eclipse.jst.pagedesigner.dtresourceprovider.DefaultDTSkin;

public class TrinidadDTResourceProvider extends DefaultDTResourceProvider {

	private static final String ID = "http://myfaces.apache.org/trinidad"; //$NON-NLS-1$

	private static final String SKIN_MINIMAL = "minimal"; //$NON-NLS-1$
	private static final String SKIN_SIMPLE = "simple"; //$NON-NLS-1$

	private static final String STYLESHEET_MINIMAL = "/skinning/minimal.css"; //$NON-NLS-1$
	private static final String STYLESHEET_SIMPLE = "/skinning/simple.css"; //$NON-NLS-1$

	/**
	 * Constructs an instance.
	 */
	public TrinidadDTResourceProvider() {
		super(ID);
		addSkin(createSkin(SKIN_MINIMAL, STYLESHEET_MINIMAL, true));
		addSkin(createSkin(SKIN_SIMPLE, STYLESHEET_SIMPLE, false));
	}

	private DefaultDTSkin createSkin(String name, String relativePath, boolean isDefault) {
		DefaultDTSkin skin = null;
		URL url = this.getClass().getResource(relativePath);
		try {
			url = FileLocator.toFileURL(url);
			skin = new DefaultDTSkin(name, Collections.singletonList(url));
			skin.setDefault(isDefault);
		} catch(IOException ioe) {
			TrinidadTagSupportActivator.logError(
					MessageFormat.format(
							Messages.TrinidadDTResourceProvider_StyleSheetNotLocated,
							relativePath),
					ioe);
		}
		return skin;
	}

}
