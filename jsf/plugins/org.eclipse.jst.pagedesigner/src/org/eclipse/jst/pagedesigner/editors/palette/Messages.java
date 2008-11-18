/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.palette;

import org.eclipse.osgi.util.NLS;

/**
 * String resource handler	
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.pagedesigner.editors.palette.messages"; //$NON-NLS-1$
	/**
	 * see messages.properties
	 */
	public static String DesignerPaletteCustomizerDialog_ExportCustomizations;
	/**
	 * see messages.properties
	 */
	public static String DesignerPaletteCustomizerDialog_ImportCustomizations;
	/**
	 * see messages.properties
	 */
	public static String DesignerPaletteViewerProvider_Hide;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
