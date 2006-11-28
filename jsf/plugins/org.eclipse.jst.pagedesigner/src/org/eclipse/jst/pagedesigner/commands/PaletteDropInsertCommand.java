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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.Assert;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dnd.internal.SourceViewerDragDropHelper;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.dom.JSFValidatorSupport;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemDescriptor;
import org.eclipse.jst.pagedesigner.utils.CommandUtil;
import org.eclipse.jst.pagedesigner.utils.JSPUtil;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class PaletteDropInsertCommand extends SourceViewerCommand {
	private final Logger _log = PDPlugin
			.getLogger(PaletteDropInsertCommand.class);

	private PaletteItemDescriptor _descriptor;

	private int _location;

	private List _nodesToFormat = new ArrayList();

	private Element _element;

	public PaletteDropInsertCommand(String label, StructuredTextEditor editor,
			PaletteItemDescriptor descriptor, int location) {
		super(label, editor);
		_descriptor = descriptor;
		_location = location;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void doExecute() {
		IDOMModel model = getModel();
		try {
			Node node = getSourceEditingTextTools().getNode(_location);
			IDOMPosition position = null;
			if (node != null) {
				position = SourceViewerDragDropHelper.getInstance()
						.findPosition(_location, node);
			} else {
				// empty file
				position = new DOMPosition(getModel().getDocument(), 0);
			}
			Assert.isTrue(position != null);
			if (!_descriptor.getURI().equalsIgnoreCase(IJMTConstants.URI_HTML)
					&& //
					!_descriptor.getURI().equalsIgnoreCase(
							IJMTConstants.URI_JSP)) {
				position = JSFValidatorSupport.prepareView(position);
			}
			_element = CommandUtil
					.excuteInsertion(_descriptor, model, position);
			if (_element != null) {
				_nodesToFormat.add(_element);
				SourceViewerDragDropHelper.getInstance().changeCaret(_editor,
						true);
				formatNodes();
			}
		} catch (Exception e) {
			_log.error("Bad text insertion location", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.requests.NodeCreationFactory#getPrefix(int)
	 */
	public String getPrefix(String uri, IDOMModel model, String suggested,
			Node nodes[]) {
		if (IJMTConstants.URI_HTML.equals(uri)
				|| IJMTConstants.URI_JSP.equals(uri)) {
			return null;
		}

		// now handles custom tag lib

		return JSPUtil.getOrCreatePrefix(model, uri, suggested, nodes);
	}

	public void setSelection() {
		if (_element != null) {
			int offset = EditModelQuery.getNodeStartIndex(_element);
			int length = EditModelQuery.getNodeEndIndex(_element) - offset;
			_editor.getTextViewer().setSelectedRange(offset, length);
		}
	}

	private void formatNodes() {
		for (int i = 0, n = _nodesToFormat.size(); i < n; i++) {
			formatNode((Node) _nodesToFormat.get(i));
		}
	}
}
