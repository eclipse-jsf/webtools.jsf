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
package org.eclipse.jst.pagedesigner.actions.link;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.commands.DesignerCommand;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 * @version 1.5
 */
public class MakeLinkCommand extends DesignerCommand {
	private String _identifier = null;

	private EditPart _part = null;

	private DesignRange _range = null;

	private ILinkCreator _linkcreator = null;

	private Element _ele = null;

	/**
	 * @param identifier
	 * @param viewer
	 * @param part
	 * @param range
	 */
	public MakeLinkCommand(String identifier, IHTMLGraphicalViewer viewer,
			EditPart part, DesignRange range) {
		super(identifier, viewer);
		setLabel(PDPlugin.getResourceString("MakeLinkCommand.Label.MakeLink"));//$NON-NLS-1$
		this._identifier = identifier;
		this._part = part;
		this._range = range;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		List<ILinkCreator> creators = ExtensionReader.getAllLinkHandlers();
		if (creators != null) {
			for (ILinkCreator linkCreator : creators) {
				String identifier = linkCreator.getLinkIdentifier();
				if (this._identifier.equalsIgnoreCase(identifier)) {
					this._linkcreator = linkCreator;
					break;
				}
			}
		}
		if (this._linkcreator != null) {
			return this._linkcreator.canExecute(_range);
		}

		return super.canExecute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.DesignerCommand#doExecute()
	 */
	protected void doExecute() {
		if (this._linkcreator != null) {
			Element ele = this._linkcreator.makeLinkElement(this._part,
					this._range);
			Node node = (Node) this._part.getModel();
			Node parent = node.getParentNode();
			formatNode(parent);

			this._ele = ele;
		}
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
