/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dnd.internal.SourceViewerDragDropHelper;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.DropCustomizationController;
import org.eclipse.jst.pagedesigner.utils.CommandUtil;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Handles tag creation when dropped onto the WPE source view
 * 
 * @author mengbo
 */
public class PaletteDropInsertCommand extends SourceViewerCommand implements ICustomizableCommand
{

	private final Logger _log = PDPlugin
			.getLogger(PaletteDropInsertCommand.class);

	private TagToolPaletteEntry _tagItem;

	private int _location;

	private Element _element;

	private IAdaptable _customizationData;

	/**
	 * @param label
	 * @param editor
	 * @param tagItem
	 * @param location
	 */
	public PaletteDropInsertCommand(String label, StructuredTextEditor editor,
			TagToolPaletteEntry tagItem, int location) {
		super(label, editor);
		_tagItem = tagItem;
		_location = location;
	}

	public void doExecute() {
		Node node;
		try {
			node = getSourceEditingTextTools().getNode(_location);
		} catch (Exception e) {
			_log.error("Bad text insertion location", e);		 //$NON-NLS-1$
			return;
		}
		IDOMPosition position = null;
		if (node != null) {
			position = SourceViewerDragDropHelper.getInstance()
					.findPosition(_location, node);
		} else {
			if (getModel().getDocument().getFirstChild() != null) {
				//Fix for 224541 - When palette item is dropped to end of file in source view of Web Page Editor, the item is inserted at the top of file
				//add inside at end of necessary container
				position = SourceViewerDragDropHelper.getInstance()
					.findPosition(getModel().getDocument().getEndOffset(), getModel().getDocument().getFirstChild());
			} 
			else {
				// empty file
				position = new DOMPosition(getModel().getDocument(), 0);
			}
		}

		//essentially copied from ItemCreationTool so that DesignView drop and SourceViewDrop are same.
		// Note that SourceView does NO drop validation checking.   This is handled by ItemCreationPolicy in DesignView
		final IStatus status = 
		    performCustomization(getModel().getDocument(), position);

		if (status.getSeverity() == IStatus.OK) {
			Element element = CommandUtil.excuteInsertion(this._tagItem,
					getModel(), position, getCustomizationData());
			if (element != null) {				
				formatNode(element);
			}
			this._element = element;
		}
	}

	/**
     * @param domDoc 
	 * @param position 
     * @return status
	 */
	protected IStatus performCustomization(final IDOMDocument domDoc, final IDOMPosition position) {
		final String  uri = _tagItem.getURI();
		final String name = _tagItem.getTagName();
		return new DropCustomizationController(this, uri, name, domDoc, position).performCustomization();
	}

    /**
	 * @param customizationData
	 */
	public void setCustomizationData(IAdaptable customizationData) {
		_customizationData = customizationData;
	}

	/**
	 * This method is for test purposes and should generally not be 
	 * used by clients.
	 * 
	 * @return the customization data
	 */
	protected final IAdaptable getCustomizationData() {
		return _customizationData;
	}
	
	public void setSelection() {
		if (_element != null) {
			int offset = EditModelQuery.getNodeStartIndex(_element);
			int length = EditModelQuery.getNodeEndIndex(_element) - offset;
			_editor.getTextViewer().setSelectedRange(offset, length);
		}
	}
	
}
