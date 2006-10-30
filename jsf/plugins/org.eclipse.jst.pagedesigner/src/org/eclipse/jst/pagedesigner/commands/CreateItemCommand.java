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
package org.eclipse.jst.pagedesigner.commands;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemDescriptor;
import org.eclipse.jst.pagedesigner.utils.CommandUtil;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
public class CreateItemCommand extends DesignerCommand {
	IDOMPosition _position;

	IPaletteItemDescriptor _itemDescriptor;

	Element _ele;

	/**
	 * @param label
	 * @param viewer
	 */
	public CreateItemCommand(String label, IHTMLGraphicalViewer viewer,
			IDOMPosition position, IPaletteItemDescriptor itemDesc) {
		super(label, viewer);
		this._position = position;
		this._itemDescriptor = itemDesc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		Element element = CommandUtil.excuteInsertion(this._itemDescriptor,
				getViewer(), this._position);
		if (element != null) {
			formatNode(element);
		}
		this._ele = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#getAfterCommandDesignerSelection()
	 */
	protected ISelection getAfterCommandDesignerSelection() {
		return toDesignSelection(_ele);
	}

}
