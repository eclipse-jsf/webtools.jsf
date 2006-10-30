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
package org.eclipse.jst.pagedesigner.editors.palette;

import java.util.Map;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public interface IPaletteItemDescriptor extends IPaletteItemEntry {

	public String getURI();

	public String getTagName();

	public Map getInitialAttributes();

	/**
	 * We use the model to get runtime prefix and apply to the node.
	 * 
	 * @param model
	 * @return
	 */
	public Node[] getTemplateSubNodes(IDOMModel model);

	/**
	 * @param tagName
	 */
	public void setTagName(String tagName);

	/**
	 * @param uri
	 */
	public void setURI(String uri);

	/**
	 * @param defaultPrefix
	 */
	public void setDefaultPrefix(String defaultPrefix);

	/**
	 * get the default prefix.
	 * 
	 * @return
	 */
	public String getDefaultPrefix();

	/**
	 * set whether this item require <h:form> to be automatically inserted.
	 * 
	 */
	public void setRequireHForm(boolean r);

	/**
	 * get whether this item require <h:form> to be automatically inserted.
	 * 
	 * @return
	 */
	public boolean isRequireHForm();

	/**
	 * Whether this item is JSF component (or other JSF core tags.)
	 * 
	 * @return
	 */
	public boolean isJSFComponent();

	/**
	 * @param b
	 */
	public void setJSFComponent(boolean b);
}
