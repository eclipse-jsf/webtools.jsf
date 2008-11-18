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
package org.eclipse.jst.pagedesigner.css2.layout;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.jst.pagedesigner.css2.style.AbstractStyle;
import org.eclipse.swt.graphics.Font;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
// NOTE: CSSTextLayout does not extends CSSFlowLayout. Since text is a little
// special,
// we don't want to do things like "preLayout()" as in CSSFlowLayout.
public class CSSTextLayout extends FlowFigureLayout {
	/**
	 * Wrapping will ONLY occur at valid line breaks
	 */
	public static final int WORD_WRAP_HARD = 0;

	/**
	 * Wrapping will always occur at the end of the available space, breaking in
	 * the middle of a word.
	 */
	public static final int WORD_WRAP_SOFT = 1;

	/**
	 * Wrapping will always occur at the end of available space, truncating a
	 * word if it doesn't fit.
	 */
	// don't support this flag
	// public static final int WORD_WRAP_TRUNCATE = 2;
	private int _wrappingStyle = WORD_WRAP_HARD;

	/**
	 * @param textfigure
	 */
	public CSSTextLayout(CSSTextFigure textfigure) {
		super(textfigure);
	}

	// --------------------------------------------------------------------------------------------------
	FlowBox findLastNonLineBox(LineBox box) {
		List fragments = box.getFragments();
		for (int i = fragments.size() - 1; i >= 0; i--) {
			FlowBox item = (FlowBox) fragments.get(i);
			if (item instanceof LineBox) {
				FlowBox found = findLastNonLineBox((LineBox) item);
				if (found != null) {
					return found;
				}
			} else {
				return item;
			}
		}
		return null;
	}

	// boolean isElementContentWhitespaceEnding()
	// {
	// if (!this._context.isCurrentLineOccupied())
	// return true;
	// LineBox line = this._context.getCurrentLine();
	// FlowBox lastNoneLinebox = findLastNonLineBox(line);
	// if (lastNoneLinebox instanceof TextFragmentBox)
	// return ((TextFragmentBox) lastNoneLinebox)._isLastCharWhitespace;
	// else
	// return true;
	// }
	//
	// String normalize(String text)
	// {
	// text = EntityMap.translateAndCompact(text);
	// if (text.length() > 0 &&
	// Character.isElementContentWhitespace(text.charAt(0)) &&
	// isElementContentWhitespaceEnding())
	// return text.substring(1);
	// else
	// return text;
	// }

	private void layoutEmptyString(List fragments, Font font) {
		// empty node! we want to create a fake fragment, so things can be
		// consistent
		// that all the CSSTextFigure will have something inside, also in this
		// way, even
		// empty text node will have a position, thus we can support showing
		// caret associated
		// with this text figure.
		fragments.clear();
		TextFragmentBox box = TextLayoutSupport.getFragment(0, fragments);
		box._length = 0;
		box._offset = 0;
		box._height = 0;
		box._width = 0;
		box.setTextData(""); //$NON-NLS-1$

		// {following comments deprecated XXX: If is empty string, we only want
		// to this figure to have a size, but don't
		// want to it to be added into current line. Otherwise, a line with only
		// a empty string
		// will also take a line's space.}

		// please reference LineBox.isOccupied()
		// now we treat a line with only an empty text as not occupied.
		getFlowContext().getCurrentLine().add(box);
	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowFigureLayout#layout()
	 */
	protected void layout() {
		CSSTextFigure flowFigure = (CSSTextFigure) getFlowFigure();

		List fragments = flowFigure.getFragments();// Reuse the previous List
		// of fragments
		String text = flowFigure.getText();
		Font font = flowFigure.getCSSStyle().getCSSFont().getSwtFont();
		Object whitespace = flowFigure.getCSSStyle().getStyleProperty(
				ICSSPropertyID.ATTR_WHITESPACE);

		if (whitespace == ICSSPropertyID.VAL_PRE) {
			if (text == null || text.length() == 0)
				layoutEmptyString(fragments, font);
			else
				TextLayoutSupport.layoutNoWrap(getFlowContext(), text,
						fragments, font);
		} else if (whitespace == ICSSPropertyID.VAL_NOWRAP) {
			if (text == null || text.length() == 0)
				layoutEmptyString(fragments, font);
			else
				TextLayoutSupport.layoutNoWrap(getFlowContext(), text,
						fragments, font);
		} else {
			if (text == null || text.length() == 0)
				layoutEmptyString(fragments, font);
			else {
				//fix for bug #221629 - BEGIN
				boolean useShouldTrimLeadingWSInlineMethod = false;
				IFigure parentFigure = flowFigure.getParent();
				if (parentFigure instanceof CSSFigure) {
					ICSSStyle style = ((CSSFigure)parentFigure).getCSSStyle();
					if (style instanceof AbstractStyle) {
						Element element = ((AbstractStyle)style).getElement();
						if (element != null &&
								element.getNodeName().equals(IHTMLConstants.TAG_SPAN)) {
							useShouldTrimLeadingWSInlineMethod = true;
						}
					}
				}
				boolean trimLeadingChar;
				if (!useShouldTrimLeadingWSInlineMethod) {
					trimLeadingChar = (text.charAt(0) == ' ' && shouldTrimLeadingWhitespace(getFlowContext()));
				} else {
					trimLeadingChar = (text.charAt(0) == ' ' && shouldTrimLeadingWhitespaceInline(getFlowContext()));
				}
				//fix for bug #221629 - END
				TextLayoutSupport.layoutNormal(getFlowContext(), text,
						fragments, font, _wrappingStyle, trimLeadingChar);
			}
		}
	}

	/**
	 * @param context
	 * @return true if should trim leading whitespace
	 */
    // XXX: maybe should move to TextSupport later.
	public boolean shouldTrimLeadingWhitespace(FlowContext context) {
		if (!context.isCurrentLineOccupied()) {
			return true;
		}
		while (context instanceof CSSInlineFlowLayout) {
			context = ((CSSInlineFlowLayout) context).getFlowContext();
		}
		LineBox line = context.getCurrentLine();
		if (line == null || !line.isOccupied()) {
			return true;
		}
		FlowBox lastNoneLinebox = findLastNonLineBox(line);
		if (lastNoneLinebox == null || lastNoneLinebox.getWidth() == 0) {
			return true;
		} else if (lastNoneLinebox instanceof TextFragmentBox) {
			return ((TextFragmentBox) lastNoneLinebox)._isLastCharWhitespace;
		} else {
			return false;
		}
	}

	/**
	 * Used instead of shouldTrimLeadingWhitespace(FlowContext) if parent
	 * figure's style is for an appropriate in-line element, such as "span".
	 * 
	 * @param context FlowContext instance.
	 * @return true if should trim leading whitespace, else false.
	 */
	private boolean shouldTrimLeadingWhitespaceInline(FlowContext context) {
		if (!context.isCurrentLineOccupied()) {
			return true;
		}
		LineBox line = context.getCurrentLine();
		if (line == null || !line.isOccupied()) {
			return true;
		}
		FlowBox lastNoneLinebox = findLastNonLineBox(line);
		if (lastNoneLinebox == null || lastNoneLinebox.getWidth() == 0) {
			return true;
		} else if (lastNoneLinebox instanceof TextFragmentBox) {
			return ((TextFragmentBox) lastNoneLinebox)._isLastCharWhitespace;
		} else {
			return false;
		}
	}

	public void dispose() {
        // TODO: anything to dispose?
	}
}
