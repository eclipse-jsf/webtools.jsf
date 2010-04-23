/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.elementedit.html;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.single.AddSubNodeCommand;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 */
public class InsertStylesheetLinkAction extends Action
{
	private IDOMElement _parentElement;

	/**
	 * @param parentElement 
	 */
	public InsertStylesheetLinkAction(IDOMElement parentElement) {
		setText(PDPlugin.getResourceString("HeadElementEdit.Submenu.InsertStylesheetLink"));//$NON-NLS-1$
		_parentElement = parentElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		// TODO: figure out how to get the default attributes from
		// the tag create metadata. Get a tag creation provider?
		Map attributes = new LinkedHashMap();
		attributes.put("href", "");  //$NON-NLS-1$//$NON-NLS-2$
		attributes.put("rel", "Stylesheet");  //$NON-NLS-1$//$NON-NLS-2$
		attributes.put("type", "text/css");  //$NON-NLS-1$//$NON-NLS-2$
		Command command = new AddSubNodeCommand(
				PDPlugin.getResourceString("ItemCreationEditPolicy.CommandLabel.CreateItem"),//$NON-NLS-1$
				_parentElement, IHTMLConstants.TAG_LINK, ITLDConstants.URI_HTML,
				attributes);
		command.execute();
	}
}
