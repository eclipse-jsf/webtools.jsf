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

/**
 * A Geometric object for representing a TextFragment region on a line of Text.
 */
public class TextFragmentBox extends FlowBox {

	/** The offset in pixels * */
	public int _offset;

	/** The length in pixels * */
	public int _length;

	private int _ascent;

	// boolean _truncated;

	/*package*/ boolean _isLastCharWhitespace = false;

	private String _textData;

	/**
	 * Creates a new TextFragmentBox
	 */
	public TextFragmentBox() {
        // do nothgin
	}

	/**
	 * Returns the ascent of this TextFragmentBox
	 * 
	 * @return the ascent
	 */
	public int getAscent() {
		return _ascent;
	}

	/**
	 * Sets the ascent of this TextFragmentBox to the given value
	 * 
	 * @param a
	 *            the ascent
	 */
	public void setAscent(int a) {
		_ascent = a;
	}

	/**
	 * Sets the height of this TextFragmentBox to the given value
	 * 
	 * @param h
	 *            the height
	 */
	public void setHeight(int h) {
		_height = h;
	}

	/**
	 * Sets the width of this TextFragmentBox to the given value
	 * 
	 * @param w
	 *            the width
	 */
	public void setWidth(int w) {
		_width = w;
	}

	/**
	 * @return the text data
	 */
	public String getTextData() {
		return _textData;
	}

	/**
	 * @param txt
	 */
	public void setTextData(String txt) {
		_textData = txt;
	}
}
