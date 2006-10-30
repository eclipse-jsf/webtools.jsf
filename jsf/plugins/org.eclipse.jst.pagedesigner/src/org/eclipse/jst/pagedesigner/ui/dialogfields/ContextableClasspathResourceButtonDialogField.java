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
package org.eclipse.jst.pagedesigner.ui.dialogfields;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.pagedesigner.common.dialogfield.ClasspathResourceButtonDialogField;
import org.eclipse.jst.pagedesigner.properties.attrgroup.IElementContextable;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * @author mengbo
 * @version 1.5
 */
public class ContextableClasspathResourceButtonDialogField extends
		ClasspathResourceButtonDialogField implements IElementContextable {
	/**
	 * @param project
	 */
	public ContextableClasspathResourceButtonDialogField() {
		super(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.attributegroup.IElementContextable#setElementContext(org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode,
	 *      org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement)
	 */
	public void setElementContext(IDOMNode ancester, IDOMElement element) {
		IProject prj = StructuredModelUtil.getProjectFor(ancester.getModel());
		this.setProject(prj);
	}
}
