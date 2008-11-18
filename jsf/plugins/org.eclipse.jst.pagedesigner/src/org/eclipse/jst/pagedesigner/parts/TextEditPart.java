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
package org.eclipse.jst.pagedesigner.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.SharedCursors;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.layout.CSSTextFigure;
import org.eclipse.jst.pagedesigner.css2.layout.ICSSFigure;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.provider.ICSSTextProvider;
import org.eclipse.jst.pagedesigner.css2.style.DefaultStyle;
import org.eclipse.jst.pagedesigner.editpolicies.LinkEditPolicy;
import org.eclipse.jst.pagedesigner.range.RangeUtil;
import org.eclipse.jst.pagedesigner.utils.HTMLUtil;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * @author mengbo
 */
public class TextEditPart extends SubNodeEditPart implements ICSSTextProvider {
	private String _cachedData;

	private Text _textNode;

	private Text _textNodeForFigure;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPart#setModel(java.lang.Object)
	 */
	public void setModel(Object model) {
		super.setModel(model);
		_textNode = (Text) model;
		_cachedData = _textNode.getData();
		_textNodeForFigure = getDestDocumentForDesign().createTextNode(
				_cachedData);
	}

	protected IFigure createFigure() {
		// XXX: currently creating of CSSTextFigure is distributed both here
		// and FigureFactory. We may want to unify them later.
		return new CSSTextFigure(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();
		this.installEditPolicy("link editpolicy", new LinkEditPolicy()); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.parts.SubNodeEditPart#getNodeForFigure()
	 */
	public Node getNodeForFigure() {
		return _textNodeForFigure;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.sse.model.INodeAdapter#notifyChanged(com.ibm.sse.model.INodeNotifier,
	 *      int, java.lang.Object, java.lang.Object, java.lang.Object, int)
	 */
	public void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
		_cachedData = _textNode.getData();
		_textNodeForFigure.setData(_cachedData);
		if (eventType == INodeNotifier.CHANGE) {
			getFigure().revalidate();
		} else {
			// XXX: been removed? parent EditPart should have handled it.
		}
	}


	/**
	 * @return the associated css style for this text node
	 */
	public ICSSStyle getCSSStyle() {
		IFigure figure1 = this.getFigure();
		if (figure1 instanceof ICSSFigure) {
			ICSSStyle style = ((ICSSFigure) figure1).getCSSStyle();
			if (style != null) {
				return style;
			}
		}
		return DefaultStyle.getInstance();
	}

	/**
	 * As when text are displayed in HTML, they are "normalized". For example,
	 * leading whitespace may be removed dure to previous node as trailing
	 * whitespace. Entity reference may have be resolved. Sequence whitespace
	 * been merged.
	 * 
	 * It is also possible that the text node is in "PRE" mode, in that case the
	 * above things are not done.
	 * 
	 * This method return the really value that is going to be presented to
	 * user. EditPartPosition's offset is referencing this value.
	 * 
	 * @return the text data
	 * @see org.eclipse.jst.pagedesigner.viewer.DesignPosition
	 */
	public String getTextData() {
		ICSSStyle style = getCSSStyle();
		String data = _cachedData;
		if (style.getStyleProperty(ICSSPropertyID.ATTR_WHITESPACE) != ICSSPropertyID.VAL_PRE) {
			return HTMLUtil.compactWhitespaces(_textNode, data);
		}
        return data;
	}

	/**
	 * check what part of this text node is in the range selection.
	 * 
	 * @return text node ranges
	 */
	public int[] getSelectedRange() {
		IHTMLGraphicalViewer viewer = (IHTMLGraphicalViewer) this.getViewer();
		if (viewer == null || !viewer.isInRangeMode()) {
			return null;
		}
		DesignRange range = viewer.getRangeSelection();
		if (range == null || !range.isValid()) {
			return null;
		}
		if (!RangeUtil.intersect(range, this)) {
			return null;
		}
		// ok, we intersect with the range.
		range = RangeUtil.normalize(range);
		EditPart startContainer = range.getStartPosition().getContainerPart();
		EditPart endContainer = range.getEndPosition().getContainerPart();
		int[] ret = new int[2];
		if (startContainer != this) {
			ret[0] = 0;
		} else {
			ret[0] = range.getStartPosition().getOffset();
		}
		if (endContainer != this) {
			ret[1] = this.getTextData().length();
		} else {
			{
				ret[1] = range.getEndPosition().getOffset();
			}
		}
		return ret;
	}

	public boolean isSelectable() {
        // controls, amongst other things, whether or not a standard 
        // hit test in SelectionTool for mouse over will find this edit part 
		return true;
	}

    public Cursor getCursor(Point mouseLocation) {
        return SharedCursors.IBEAM;
    }
    
    
}
