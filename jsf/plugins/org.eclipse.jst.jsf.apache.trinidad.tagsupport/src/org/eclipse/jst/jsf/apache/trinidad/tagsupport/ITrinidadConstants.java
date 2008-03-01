/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.jsf.apache.trinidad.tagsupport;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;

/**
 * Trinidad-related constants.
 *
 * @author Ian Trimble - Oracle
 */
public interface ITrinidadConstants {

	/**
	 * Trinidad "core" URI.
	 */
	public static final String URI_CORE =
		"http://myfaces.apache.org/trinidad"; //$NON-NLS-1$

	/**
	 * Trinidad "html" URI.
	 */
	public static final String URI_HTML =
		"http://myfaces.apache.org/trinidad/html"; //$NON-NLS-1$

	/**
	 * Tag name for "panelTabbed".
	 */
	public static final String TAG_PANELTABBED = "panelTabbed"; //$NON-NLS-1$

	/**
	 * Tag name for "showDetailItem".
	 */
	public static final String TAG_SHOWDETAILITEM = "showDetailItem"; //$NON-NLS-1$

	/**
	 * TagIdentifier for "tr:panelTabbed".
	 */
	public static final TagIdentifier TAG_IDENTIFIER_PANELTABBED =
		TagIdentifierFactory.createJSPTagWrapper(URI_CORE, TAG_PANELTABBED);

	/**
	 * TagIdentifier for "tr:showDetailItem".
	 */
	public static final TagIdentifier TAG_IDENTIFIER_SHOWDETAILITEM =
		TagIdentifierFactory.createJSPTagWrapper(URI_CORE, TAG_SHOWDETAILITEM);

}
