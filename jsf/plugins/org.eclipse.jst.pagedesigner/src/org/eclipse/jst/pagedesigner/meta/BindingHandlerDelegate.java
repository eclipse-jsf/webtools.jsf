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
package org.eclipse.jst.pagedesigner.meta;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * @author mengbo
 * @version 1.5
 */
public class BindingHandlerDelegate implements IBindingHandler {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.IBindingHandler#handleBinding(org.eclipse.swt.widgets.Shell,
	 *      org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode,
	 *      org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement,
	 *      java.lang.String)
	 */
	public String handleBinding(Shell shell, IDOMNode ancester,
			IDOMElement element, String currentValue) {
		IBindingHandler handler = getDelegatedHandler(ancester, element);
		if (handler != null) {
			return handler
					.handleBinding(shell, ancester, element, currentValue);
		}
        return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.IBindingHandler#isEnabled(org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode,
	 *      org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement)
	 */
	// public boolean isEnabled(IDOMNode ancester, IDOMElement element)
	// {
	// IBindingHandler handler = getDelegatedHandler(ancester, element);
	// return (handler == null) ? false : handler.isEnabled(ancester, element);
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.IBindingHandler#isEnabled(org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode,
	 *      org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement,
	 *      java.lang.String, java.lang.String,
	 *      org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor)
	 */
	public boolean isEnabled(IDOMNode ancester, IDOMElement element,
			String uri, String tagName, IAttributeDescriptor attr) {
		IBindingHandler handler = getDelegatedHandler(ancester, element);
		return (handler == null) ? false : handler.isEnabled(ancester, element,
				uri, tagName, attr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.IBindingHandler#getImage()
	 */
	public Image getImage() {
		return PDPlugin.getDefault().getImage("PD_Binding.gif");
	}

	public Image getDisabledImage() {
		return PDPlugin.getDefault().getImage("PD_Binding_disabled.gif");
	}

	private IBindingHandler getDelegatedHandler(IDOMNode ancester,
			IDOMElement element) {
		IProject project = getProject(ancester, element);
		if (project != null) {
			Object obj = project.getAdapter(IBindingHandler.class);
			if (obj instanceof IBindingHandler) {
				return (IBindingHandler) obj;
			}
		}

		return null;
	}

	private IProject getProject(IDOMNode ancester, IDOMElement element) {
		IDOMModel model = getModel(ancester, element);
		if (model != null) {
			return StructuredModelUtil.getProjectFor(model);
		}
        return null;
	}

	private IDOMModel getModel(IDOMNode ancester, IDOMElement element) {
		if (ancester != null) {
			return ancester.getModel();
		} else if (element != null) {
			return element.getModel();
		} else {
			return null;
		}
	}

}
