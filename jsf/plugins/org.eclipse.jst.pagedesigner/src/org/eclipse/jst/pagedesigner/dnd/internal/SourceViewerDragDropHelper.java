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
package org.eclipse.jst.pagedesigner.dnd.internal;

import org.eclipse.gef.SharedCursors;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.EditValidateUtil;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Caret;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.ModelQuery;
import org.eclipse.wst.xml.core.internal.modelquery.ModelQueryUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * This class will 1. determine it's insertion or update 2. call validator
 * corresponding helper to resolve it.
 * 
 * @author mengbo
 */
public final class SourceViewerDragDropHelper {
	private static SourceViewerDragDropHelper _instance;

	/**
	 * @return the singleton instance
	 */
	public static SourceViewerDragDropHelper getInstance() {
		if (_instance == null) {
			_instance = new SourceViewerDragDropHelper();
		}
		return _instance;
	}

	private SourceViewerDragDropHelper()
	{
	    // singleton, no external instantiation
	}
	
	private Point toControl(TextViewer textViewer, Point point) {
		return (textViewer != null ? textViewer.getTextWidget()
				.toControl(point) : point);
	}

	private int getDropOffset(StructuredTextEditor ste, Point pt) {
		StyledText st = ste.getTextViewer().getTextWidget();
		int offset = st.getCaretOffset();
		try {
			offset = st.getOffsetAtLocation(pt);
		} catch (IllegalArgumentException e) {
			boolean found = false;
			Point p = new Point((pt.x > 0 ? pt.x : 0), pt.y);
			// search nearest character
			for (; p.x > -1; p.x--) {
				try {
					offset = st.getOffsetAtLocation(p);

					/*
					 * Now that a valid offset has been found, try to place at
					 * the end of the line
					 */
					if (ste.getTextViewer() != null
							&& ste.getTextViewer().getDocument() != null) {
						IRegion lineInfo = null;
						try {
							lineInfo = ste.getTextViewer().getDocument()
									.getLineInformationOfOffset(offset);
						} catch (BadLocationException e1) {
                            // ignore exception and fall-through with lineInfo == null
						}
						if (lineInfo != null)
							offset = lineInfo.getOffset()
									+ lineInfo.getLength();
					}

					found = true;
					break;
				} catch (IllegalArgumentException ex) {
					// for trying location, no need to catch.
				}
			}
			if (!found) {
				offset = st.getCharCount();
			}
		}
		return offset;
	}

	/**
	 * @param textEditor
	 * @param location
	 * @param caret
	 */
	public void updateCaret(StructuredTextEditor textEditor, Point location,
			Point caret) {
		TextViewer textViewer = textEditor.getTextViewer();
		if (textViewer != null) {
			Point pt = toControl(textViewer, location);
			StyledText st = textViewer.getTextWidget();

			// auto scroll
			Rectangle ca = st.getClientArea();
			int margin = st.getLineHeight();

			if (pt.y < margin) { // up
				st.invokeAction(ST.LINE_UP);
			} else if (pt.y > ca.height - margin) { // down
				st.invokeAction(ST.LINE_DOWN);
			}

			// draw insertion point
			int offset = getDropOffset(textEditor, pt);
			if (offset != st.getCaretOffset()) {
				st.setCaretOffset(offset);
				st.setSelection(offset);
			}

			Point newCaret = st.getLocationAtOffset(offset);
			if (newCaret.equals(caret)) {
				return;
			}

			Caret ct = st.getCaret();
			Point size = ct.getSize();

			GC gc = new GC(st);
			//gc.setXORMode(true);
			gc.setLineWidth(size.x);

			// erase old caret
			if (caret != null) {
				Color originalForeground = gc.getForeground();
				gc.setForeground(st.getBackground());
				gc.drawLine(caret.x, caret.y, caret.x, caret.y + size.y);
				gc.setForeground(originalForeground);
			}

			st.redraw();
			st.update();

			// draw new caret
			if (caret == null) {
				caret = newCaret;
			} else {
				caret.x = newCaret.x;
				caret.y = newCaret.y;
			}
			if (ct.getImage() != null) {
				gc.drawImage(ct.getImage(), caret.x, caret.y);
			} else {
				gc.drawLine(caret.x, caret.y, caret.x, caret.y + size.y);
			}

			gc.dispose();
		}
	}

	/**
	 * @param textEditor
	 * @param location
	 */
	public void updateCaret(StructuredTextEditor textEditor, Point location) {
		TextViewer textViewer = textEditor.getTextViewer();
		if (textViewer != null) {
			Point pt = toControl(textViewer, location);
			StyledText st = textViewer.getTextWidget();

			// auto scroll
			Rectangle ca = st.getClientArea();
			int margin = st.getLineHeight();

			if (pt.y < margin) { // up
				st.invokeAction(ST.LINE_UP);
			} else if (pt.y > ca.height - margin) { // down
				st.invokeAction(ST.LINE_DOWN);
			}

			// draw insertion point
			int offset = getDropOffset(textEditor, pt);
			if (offset != st.getCaretOffset()) {
				st.setCaretOffset(offset);
				st.setSelection(offset);
			}
		}
	}

	/**
	 * @param textEditor
	 * @param location
	 * @return the caret offset
	 */
	public int showCaret(StructuredTextEditor textEditor, int location) {
		StyledText text = textEditor.getTextViewer().getTextWidget();
		text.setCursor(SharedCursors.CURSOR_TREE_ADD);
		text.setCaretOffset(location);
		if (!text.isFocusControl()) {
			text.setFocus();
		}
		return text.getCaretOffset();
	}

	/**
	 * @param node
	 * @return the model query for the node or null if not available
	 */
	protected ModelQuery getModelQuery(Node node) {
		if (node.getNodeType() == Node.DOCUMENT_NODE) {
			return ModelQueryUtil.getModelQuery((Document) node);
		}
        return ModelQueryUtil.getModelQuery(node.getOwnerDocument());
	}

	/**
	 * @param caretPos
	 * @param element
	 * @return the position 
	 */
	public IDOMPosition findPosition(int caretPos, Node element) {
		EditValidateUtil.validNode(element);
		IDOMPosition position = EditModelQuery.getInstance().createDomposition(
				((IDOMNode) element).getModel(), caretPos, false);
		return position;
	}

	/**
	 * @param viewer
	 * @param node
	 */
	public void format(TextViewer viewer, Node node) {
		if (node == null) {
			return;
		}
		Node tmp;
		int start, offset;
		if (node.getPreviousSibling() != null) {
			tmp = node.getPreviousSibling();
			start = ((IndexedRegion) tmp).getEndOffset();
		} else {
			tmp = node;
			start = ((IndexedRegion) tmp).getStartOffset();
		}
		if (node.getNextSibling() != null) {
			tmp = node.getNextSibling();
			offset = ((IndexedRegion) tmp).getStartOffset() - start;
		} else {
			tmp = node;
			offset = ((IndexedRegion) tmp).getEndOffset() - start;
		}
		viewer.setSelectedRange(start, offset);
		viewer.doOperation(ISourceViewer.FORMAT);
	}

	/**
	 * @param textEditor
	 * @param reset
	 */
	public void changeCaret(StructuredTextEditor textEditor, boolean reset) {
		if (reset) {
			StyledText text = textEditor.getTextViewer().getTextWidget();
			text.setCursor(new Cursor(null, SWT.CURSOR_IBEAM));
		}
	}

	/**
	 * @param textEditor
	 * @param locationOffset
	 * @return the location offset
	 */
	/*package*/ int getValidLocation(StructuredTextEditor textEditor,
			int locationOffset) {
		Node node = getCaretNode(textEditor, locationOffset);
		if (node == null) {
			// empty page?
			return 0;
		}
		if (node.getNodeType() == Node.TEXT_NODE) {
			return locationOffset;
		}
		return calculateCaretLocation(node, locationOffset);
	}

	/**
	 * @param textEditor
	 * @param location
	 * @return the offset
	 */
	public int getOffset(StructuredTextEditor textEditor, Point location) {
		StyledText text = textEditor.getTextViewer().getTextWidget();
		return text.getOffsetAtLocation(location);
	}

	// private IStructuredModel getModel(StructuredTextEditor textEditor)
	// {
	// IStructuredModel model = null;
	// if (textEditor.getDocumentProvider() != null)
	// {
	// if (textEditor.getDocumentProvider() instanceof IModelProvider)
	// {
	// model = ((IModelProvider)
	// textEditor.getDocumentProvider()).getModel(textEditor.getEditorInput());
	// }
	// else
	// {
	// IDocument doc =
	// textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
	// if (doc instanceof IDocument)
	// {
	// model =
	// StructuredModelManager.getModelManager().getExistingModelForEdit(doc);
	// if (model == null)
	// {
	// model =
	// StructuredModelManager.getModelManager().getExistingModelForEdit((IDocument)
	// doc);
	// }
	// }
	// }
	// }
	// return model;
	// }

	/**
	 * @param textEditor
	 * @param pos
	 * @return the node
	 */
	private Node getCaretNode(StructuredTextEditor textEditor, int pos) {
        // TODO: getModel is deprecated
		IStructuredModel model = textEditor.getModel();
		// getModel(textEditor);
		if (model == null) {
			return null;
		}
		IndexedRegion inode = model.getIndexedRegion(pos);
		if (inode == null) {
			inode = model.getIndexedRegion(pos - 1);
		}
		return (inode instanceof Node) ? (Node) inode : null;
	}

	/**
	 * Calculate and adjust the location in compare with Node.
	 * 
	 * @param node
	 * @param location
	 * @return the location
	 */
	public int calculateCaretLocation(Node node, int location) {
		int pos[][] = new int[2][2];
		pos[0][0] = EditModelQuery.getNodeStartIndex(node);
		pos[0][1] = EditModelQuery.getNodeStartNameEndIndex(node);
		pos[1][0] = EditModelQuery.getNodeEndNameStartIndex(node);
		pos[1][1] = EditModelQuery.getNodeEndIndex(node);
		if (pos[0][0] >= location || pos[1][0] == location
				|| pos[1][1] <= location) {
			return location;
		} else if (pos[0][0] <= location && pos[0][1] >= location) {
			if (((pos[0][1] + pos[0][0]) / 2) >= location) {
				return pos[0][0];
			}
            return pos[0][1];
		} else if (pos[1][0] <= location && pos[1][1] >= location) {
			if (((pos[1][1] + pos[1][0]) / 2) >= location) {
				return pos[1][0];
			}
            return pos[1][1];
		}
		return location;
	}
}
