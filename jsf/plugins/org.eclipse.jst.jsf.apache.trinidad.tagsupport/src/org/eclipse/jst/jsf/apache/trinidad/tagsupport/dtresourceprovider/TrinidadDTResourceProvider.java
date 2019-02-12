/**
 * Copyright (c) 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.dtresourceprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

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
		addSkin(createSkin(SKIN_MINIMAL, STYLESHEET_MINIMAL_URL, true));
		addSkin(createSkin(SKIN_SIMPLE, STYLESHEET_SIMPLE_URL, false));
	}

	private DefaultDTSkin createSkin(String name, URL stylesheetURL, boolean isDefault) {
		DefaultDTSkin skin = null;
		if (stylesheetURL != null) {
			skin = new DefaultDTSkin(name, Collections.singletonList(stylesheetURL));
			skin.setDefault(isDefault);
		} else {
			TrinidadTagSupportActivator.logError(
					Messages.TrinidadDTResourceProvider_StyleSheetNotLocated,
					null);
		}
		return skin;
	}

	//here we try to ensure we can access stylesheets and referenced resources even when JARed
	private static URL STYLESHEET_MINIMAL_URL;
	private static URL STYLESHEET_SIMPLE_URL;
	private static final String DTREZPROV_CACHE_DIRNAME = "dtRezProvCache"; //$NON-NLS-1$
	private static final String URI_DIRNAME = "myfaces.apache.org_trinidad"; //$NON-NLS-1$
	private static final String DIR_TO_COPY = "skinning/"; //$NON-NLS-1$
	/**
	 * Initializes the class.
	 */
	public static void init() {
		//worst case - we get the stylesheets but no referenced resources (if plug-in is JARed)
		try {
			STYLESHEET_MINIMAL_URL = FileLocator.toFileURL(
					TrinidadDTResourceProvider.class.getResource(STYLESHEET_MINIMAL));
			STYLESHEET_SIMPLE_URL = FileLocator.toFileURL(
					TrinidadDTResourceProvider.class.getResource(STYLESHEET_SIMPLE));
		} catch(IOException ioe) {
			//nothing to be done
		}
		try {
			File bundleFile = FileLocator.getBundleFile(TrinidadTagSupportActivator.getDefault().getBundle());
			if (bundleFile.isFile()) {
				//plug-in is JARed
				try {
					File stateLocation = TrinidadTagSupportActivator.getDefault().getStateLocation().toFile();
					File dtRezProvCacheDir = new File(stateLocation, DTREZPROV_CACHE_DIRNAME);
					File targetDir = new File(dtRezProvCacheDir, URI_DIRNAME);
					wipeDir(targetDir);
					if (targetDir.mkdirs()) {
						FileInputStream fis = null;
						JarInputStream jis = null;
						FileOutputStream fos = null;
						try {
							fis = new FileInputStream(bundleFile);
							jis = new JarInputStream(fis, false);
							JarEntry jarEntry = jis.getNextJarEntry();
							while (jarEntry != null) {
								String name = jarEntry.getName();
								if (name != null && name.startsWith(DIR_TO_COPY)) {
									File targetFile = new File(targetDir, name);
									if (!jarEntry.isDirectory()) {
										File parentDir = targetFile.getParentFile();
										if (parentDir != null && !parentDir.exists()) {
											parentDir.mkdirs();
										}
										if (targetFile.createNewFile()) {
											fos = new FileOutputStream(targetFile);
											byte[] buf = new byte[1024];
											int bytesRead = jis.read(buf, 0, 1024);
											while (bytesRead > 0) {
												fos.write(buf, 0, bytesRead);
												bytesRead = jis.read(buf, 0, 1024);
											}
										}
									}
								}
								jarEntry = jis.getNextJarEntry();
							}
						} catch(IOException ioe) {
							//do nothing, we fall back on stylesheet without referenced resources
						} finally {
							if (fos != null) {
								fos.close();
							}
							if (jis != null) {
								jis.close();
							}
							if (fis != null) {
								fis.close();
							}
						}
					}
					File minimalStylesheet = new File(targetDir, STYLESHEET_MINIMAL);
					if (minimalStylesheet.exists()) {
						STYLESHEET_MINIMAL_URL = minimalStylesheet.toURL();
					}
					File simpleStylesheet = new File(targetDir, STYLESHEET_SIMPLE);
					if (simpleStylesheet.exists()) {
						STYLESHEET_SIMPLE_URL = simpleStylesheet.toURL();
					}
				} catch(IllegalStateException ise) {
					//do nothing, we fall back on stylesheet without referenced resources
				}
			}
		} catch(IOException ioe) {
			//do nothing, we fall back on stylesheet without referenced resources
		}
	}

	private static void wipeDir(File targetDir) {
		if (targetDir != null && targetDir.exists()) {
			File[] files = targetDir.listFiles();
			for (File file: files) {
				if (file.isFile()) {
					file.delete();
				} else {
					wipeDir(file);
				}
			}
			targetDir.delete();
		}
	}

}
