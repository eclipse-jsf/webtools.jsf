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
package org.eclipse.jst.pagedesigner.editors.palette.impl;

import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;

/**
 * @author mengbo
 */
public class DocumentFactoryTLD extends CMDocumentFactoryTLD {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD#buildCMDocumentFromJar(java.lang.String,
	 *      java.lang.String)
	 */
	public CMDocument buildCMDocumentFromJar(String jarFileName,
			String contentFileName) {
		// TODO Auto-generated method stub
		return super.buildCMDocumentFromJar(jarFileName, contentFileName);
	}
}
