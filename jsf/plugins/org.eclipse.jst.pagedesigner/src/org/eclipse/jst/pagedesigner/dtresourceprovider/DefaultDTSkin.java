/*******************************************************************************
 * Copyright (c) 2009 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.dtresourceprovider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSModel;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.w3c.dom.stylesheets.StyleSheet;

/**
 * Concrete implementation of AbstractDTSkin.
 * 
 * @author Ian Trimble - Oracle
 */
public class DefaultDTSkin extends AbstractDTSkin {

	private List<URL> styleSheetURLs;
	private List<StyleSheet> styleSheets;
	private List<IStructuredModel> models;
	private Logger log = PDPlugin.getLogger(DefaultDTSkin.class);

	/**
	 * Constructs an instance.
	 * 
	 * @param name The human-readable name of the skin.
	 */
	public DefaultDTSkin(String name) {
		super(name);
	}

	/**
	 * Constructs an instance.
	 * 
	 * @param name The human-readable name of the skin.
	 * @param isDefault true if this instance is considered the default for its
	 * provider.
	 */
	public DefaultDTSkin(String name, boolean isDefault) {
		super(name, isDefault);
	}

	/**
	 * Constructs an instance.
	 * 
	 * @param name The human-readable name of the skin.
	 * @param styleSheetURLs The List of URL instances that provide an absolute
	 * reference to the stylesheets.
	 */
	public DefaultDTSkin(String name, List<URL> styleSheetURLs) {
		this(name);
		this.styleSheetURLs = styleSheetURLs;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtresourceprovider.IDTSkin#getStyleSheets()
	 */
	public List<StyleSheet> getStyleSheets() {
		if (styleSheets == null) {
			styleSheets = new ArrayList<StyleSheet>();
			if (styleSheetURLs != null) {
				for (URL currentURL: styleSheetURLs) {
					InputStream in = null;
					try {
						in = currentURL.openStream();
						if (in != null) {
							IStructuredModel model = StructuredModelManager.getModelManager().getModelForRead(currentURL.toExternalForm(), in, null);
							if (model instanceof ICSSModel) {
								styleSheets.add((StyleSheet)((ICSSModel)model).getDocument());
								if (models == null) {
									models = new ArrayList<IStructuredModel>();
								}
								models.add(model);
							} else {
								model.releaseFromRead();
							}
						}
					} catch(IOException ioe) {
						log.error("Warning.DefaultDTSkin.FailureLoadingStyleSheet", name, currentURL.toExternalForm(), ioe); //$NON-NLS-1$
					} finally {
						ResourceUtils.ensureClosed(in);
					}
				}
			}
		}
		return Collections.unmodifiableList(styleSheets);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtresourceprovider.IDTSkin#getStyleSheetLocations()
	 */
	public List<String> getStyleSheetLocations() {
		List<String> locations = new ArrayList<String>();
		if (styleSheetURLs != null) {
			for (URL currentURL: styleSheetURLs) {
				URL fileURL;
				try {
					fileURL = FileLocator.toFileURL(currentURL);
				} catch(IOException ioe) {
					//attempt to convert to a file protocol URI failed, revert to original form
					fileURL = currentURL;
				}
				locations.add(fileURL.toExternalForm());
			}
		}
		return Collections.unmodifiableList(locations);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtresourceprovider.IDTSkin#releaseResources()
	 */
	public void releaseResources() {
		if (models != null) {
			for (IStructuredModel model: models) {
				model.releaseFromRead();
			}
			models = null;
		}
		styleSheets = null;
	}

}
