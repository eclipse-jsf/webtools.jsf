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

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * @author mengbo
 * @version 1.5
 * @deprecated
 */
public interface IBindingHandler {
	/**
	 * This handler should open a dialog to accept user input.
	 * 
	 * @param shell
	 * @param ancester
	 * @param element
	 * @param currentValue
	 * @return null means user canceled the operation.
	 */
	public String handleBinding(Shell shell, IDOMNode ancester,
			IDOMElement element, String currentValue);

	/**
	 * Whether should the binding be enabled for the specified element context.
	 * element could be null.
	 * 
	 * @param ancester
	 * @param element
	 * @param uri
	 * @param tagName
	 * @param attr
	 *            could be null.
	 * @return true if is enabled
	 */
	public boolean isEnabled(IDOMNode ancester, IDOMElement element,
			String uri, String tagName, IAttributeDescriptor attr);

	
//	public boolean isEnabled(IDOMNode ancester, IDOMElement element,
//			IPropertyPageDescriptor attr);
	
	/**
	 * given the meta data of an attribute, to see whether should enable binding
	 * handler for it.
	 * 
	 * @param uri
	 * @param tagName
	 * @param attr
	 * @return
	 */
	// public boolean isEnabled(String uri, String tagName, IAttributeDescriptor
	// attr);
	/**
	 * Image used for the small button.
	 * 
	 * @return the image
	 */
	public Image getImage();

	/**
	 * @return the image indicating disablement
	 */
	public Image getDisabledImage();
}
