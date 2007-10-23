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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ResourceButtonDialogField;
import org.eclipse.jst.pagedesigner.properties.attrgroup.IElementContextable;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.jst.pagedesigner.utils.WebAppUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * @author mengbo
 * @version 1.5
 */
public class ContextableResourceButtonDialogField extends
		ResourceButtonDialogField implements IElementContextable {

	/**
	 * Default constructor
	 */
	public ContextableResourceButtonDialogField() {
		super(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.properties.attrgroup.IElementContextable#setElementContext(org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode,
	 *      org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement)
	 */
	public void setElementContext(IDOMNode ancester, IDOMElement element) {
		if (ancester == null) {
			setProject(null);
			setReferredFile(null);
		} else {
			IDOMModel model = ancester.getModel();
			IFile file = StructuredModelUtil.getFileFor(model);
			IProject prj = (file == null ? null : file.getProject());
			setProject(prj);
			setReferredFile(file);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.ResourceButtonDialogField#browseButtonPressed()
	 */
	protected String browseButtonPressed() {
		String url = super.browseButtonPressed();
		url = WebAppUtil.transformJSPURL(url, this.getReferredFile());
		return url;
	}
}
