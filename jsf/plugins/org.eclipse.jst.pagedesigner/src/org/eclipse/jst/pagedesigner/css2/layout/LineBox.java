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

import org.eclipse.jst.pagedesigner.css2.property.VerticalAlignMeta;
import org.eclipse.jst.pagedesigner.css2.value.Length;
import org.eclipse.swt.graphics.FontMetrics;

/**
 * A composite box representing a single line. LineBox calculates its ascent and
 * descent from the child boxes it contains. Clients can call
 * {@link #getAscent()} at any time and expect valid
 * values. The child boxes that are added to a line have unspecied locations
 * until {@link #commit()}is called, at which time the child boxes are layed
 * out in left-to-right order, and their baselines are all aligned vertically.
 * 
 */
public class LineBox extends CompositeBox {
	private final static int BASELINE = 0;

	private final static int MIDDLE = 1;

	private final static int SUB = 2;

	private final static int SUPER = 3;

	private final static int TEXT_TOP = 4;

	private final static int TEXT_BOTTOM = 5;

	private final static int TOP = 6;

	private final static int BOTTOM = 7;

	private final static int LENGTH = 8;

	private int _ascent = 0;

	private int _descent = 0;

	private int _fontAscent = 0;

	private int _fontDescent = 0;

	private int _fontLeading = 0;

	private Object _horizonalData = null;

	private Object _htmlInitData = null;

	private int _accumlatedWidth = 0;

	/**
	 * Removes all owned fragments and invalidates this CompositeBox.
	 */
	public void clear() {
		super.clear();
		_horizonalData = null;
		_htmlInitData = null;
	}

	/**
	 * Committing a LineBox will position its children correctly. All children
	 * boxes are made to have the same baseline, and are layed out from
	 * left-to-right.
	 */
	public void commit() {
		int baseline = getBaseline();
		int xLocation = _x;
		for (int i = 0; i < _fragments.size(); i++) {
			FlowBox block = (FlowBox) _fragments.get(i);
			block._x = xLocation + block.getMarginInsets().left;
			xLocation = block._x + block._width + block.getMarginInsets().right;

			if (_fragments.size() > 1 && block instanceof TextFragmentBox) {
				TextFragmentBox textBox = (TextFragmentBox) block;
				if (textBox.getTextData().length() == 0) {
					textBox._height = _fontAscent + _fontDescent + _fontLeading;
					textBox.setAscent(_fontAscent + _fontLeading);
					block._y = this._y;
					continue;
				}
			}

			switch (getVerticalAlignType(block)) {
			case TOP:
				block._y = this._y;
				break;
			case BOTTOM:
				block._y = this.getBaseline() - (block.getHeight() - _descent);
				break;
			case MIDDLE:
				int halfXHeight = getHalfXHeight();
				block._y = this.getBaseline() - halfXHeight
						- (block.getHeight() + 1) / 2;
				break;
			case TEXT_TOP:
				block._y = this.getBaseline() - _fontAscent - _fontLeading;
				break;
			case TEXT_BOTTOM:
				block._y = this.getBaseline() - (block._height - _fontDescent);
				break;
			case LENGTH:
				block._y = this.getBaseline() + getIncrement(block);
				break;
			case SUPER:
				block._y = this.getBaseline() - getHalfXHeight() * 2
						- block._height;
				break;
			case SUB:
				block._y = this.getBaseline() - block._height * _fontLeading
						/ getFontHeight();
				break;
			case BASELINE:
			default:
				block.makeBaseline(baseline);
				break;
			}
			if (block instanceof LineBox) {
				((LineBox) block).commit();
			}
		}
	}

	private int getVerticalAlignType(FlowBox box) {
		Object data = box.getVerticalAlignData();

		if (data != null) {
			if (data instanceof Length) {
				return LENGTH;
			} else if (VerticalAlignMeta.BASELINE.equals(data)) {
				return BASELINE;
			} else if (VerticalAlignMeta.MIDDLE.equals(data)) {
				return MIDDLE;
			} else if (VerticalAlignMeta.SUB.equals(data)) {
				return SUB;
			} else if (VerticalAlignMeta.SUPER.equals(data)) {
				return SUPER;
			} else if (VerticalAlignMeta.TEXT_TOP.equals(data)) {
				return TEXT_TOP;
			} else if (VerticalAlignMeta.TEXT_BOTTOM.equals(data)) {
				return TEXT_BOTTOM;
			} else if (VerticalAlignMeta.TOP.equals(data)) {
				return TOP;
			} else if (VerticalAlignMeta.BOTTOM.equals(data)) {
				return BOTTOM;
			}
			return BASELINE;
		}
		return BASELINE;
	}

	/**
	 * @see org.eclipse.jst.pagedesigner.css2.layout.FlowBox#getAscent()
	 */
	public int getAscent() {
		// because at initial, ascent is 0. And the linebox
		// could have some size setting without children. In
		// that case, we need handle differently.
		if (_ascent == 0 && _fragments.isEmpty()) {
			return getHeight();
		}
		return _ascent;
	}

	/**
	 * Returns the width available to child fragments.
	 * 
	 * @return the width in pixels
	 */
	public int getAvailableWidth() {
		if (_recommendedWidth < 0) {
			return Integer.MAX_VALUE;
		}
		int availableWidth = _recommendedWidth - _accumlatedWidth;
		if (availableWidth < 0) {
			availableWidth = 0;
		}
		return availableWidth;
	}

	/**
	 * Returns the baseline of this LineBox, which is the y value plus the
	 * ascent.
	 * 
	 * @return the baseline value.
	 */
	public int getBaseline() {
		return _y + getAscent();
	}

	/**
	 * @see CompositeBox#resetInfo()
	 */
	protected void resetInfo() {
		super.resetInfo();
		_accumlatedWidth = 0;
		_ascent = 0;
	}

	/**
	 * @see CompositeBox#unionInfo(FlowBox)
	 */
	protected void unionInfo(FlowBox blockInfo) {
		if (blockInfo instanceof TextFragmentBox) {
			if (((TextFragmentBox) blockInfo).getTextData().length() == 0) {
				return;
			}
		}

		if (_fragments == null || _fragments.isEmpty()) {
			this._ascent = 0;
			this._descent = 0;
			this._height = 0;
		}

		int valign = getVerticalAlignType(blockInfo);

		if (valign == BASELINE) {
			_ascent = Math.max(_ascent, blockInfo.getAscent());
			if (blockInfo instanceof WidgetBox) {
				_descent = 0;
			} else {
				_descent = Math.max(_descent, blockInfo.getDescent());
			}
			_height = Math.max(_height, _ascent + _descent);
		} else if (valign == MIDDLE) {
			int halfXHeight = getHalfXHeight();
			_ascent = Math.max(_ascent, (blockInfo.getHeight() + 1) / 2
					+ halfXHeight);
			_descent = Math.max(_descent, blockInfo.getHeight() / 2
					- halfXHeight);
			_height = Math.max(_height, _ascent + _descent);
		} else if (valign == TEXT_TOP) {
			_ascent = Math.max(_ascent, _fontAscent + _fontLeading);
			_descent = Math.max(_descent, blockInfo.getHeight() - _fontAscent
					- _fontLeading);
			_height = Math.max(_height, _ascent + _descent);
		} else if (valign == TEXT_BOTTOM) {
			_ascent = Math.max(_ascent, blockInfo.getHeight() - _fontDescent);
			_descent = Math.max(_descent, _fontDescent);
			_height = Math.max(_height, _ascent + _descent);
		} else if (valign == SUB) {
			int blockTop = blockInfo._height * _fontLeading / getFontHeight();
			_ascent = Math.max(_ascent, blockTop);
			_descent = Math.max(_descent, blockInfo.getHeight() - blockTop);
			_height = Math.max(_height, _ascent + _descent);
		} else if (valign == SUPER) {
			int blockTop = blockInfo._height;
			_ascent = Math.max(_ascent, getHalfXHeight() * 2 + blockTop);
			_height = Math.max(_height, _ascent + _descent);
		} else if (valign == LENGTH) {
			int increment = getIncrement(blockInfo);
			_ascent = Math.max(_ascent, blockInfo.getAscent() + increment);
			_descent = Math.max(_descent, blockInfo.getDescent() - increment);
			_height = Math.max(_height, _ascent + _descent);
		} else if (valign == TOP) {
			_descent = Math.max(_descent, blockInfo.getHeight() - _ascent);
			_height = Math.max(_height, _ascent + _descent);
		} else if (valign == BOTTOM) {
			// XXX:the render of IE is not consistent with spec, mozilla is. so
			// we follow mozilla's implementation.
			_ascent = Math.max(_ascent, blockInfo.getHeight() - _descent);
			_height = Math.max(_height, _ascent + _descent);
		} else {
			_ascent = Math.max(_ascent, blockInfo.getAscent());
			_descent = Math.max(_descent, blockInfo.getDescent());
			_height = Math.max(_height, blockInfo.getHeight());
		}

		_accumlatedWidth += blockInfo._width
				+ blockInfo.getMarginInsets().getWidth();
		if (_accumlatedWidth > _width) {
			_width = _accumlatedWidth;
		}
	}

	private int getIncrement(FlowBox blockInfo) {
		int valign = getVerticalAlignType(blockInfo);
		if (valign == LENGTH) {
			int increment = 0;
			Length length = (Length) blockInfo.getVerticalAlignData();
			if (length.isPercentage()) {
				increment = length.getValue() * getFontHeight() / 100;
			} else {
				increment = length.getValue();
			}
			return increment;
		}
		return 0;
	}

	/**
	 * @return true if is occupied
	 * @see org.eclipse.draw2d.geometry.Rectangle#isEmpty()
	 */
	public boolean isOccupied() {
		if (_width > 0) {
			return true;
		}

		if (_fragments.isEmpty()) {
			return false;
		}
		// int size = _fragments.size();
		// if (size > 1)
		// {
		// return true;
		// }
		// ok, we have one segment
		// FlowBox box = (FlowBox) _fragments.get(0);
		// if (box instanceof TextFragmentBox)
		// {
		// if (((TextFragmentBox) box).getTextData().length() == 0)
		// {
		// // this is an empty string text box.
		// return false;
		// }
		// }
		return true;
	}

	/**
	 * @return true if is empty string line
	 */
	public boolean isEmptyStringLine() {
		// if(this.getWidth() == 0)
		// {
		// return true;
		// }
		// else
		// {
		// return false;
		// }
		if (_fragments.size() == 1) {
			FlowBox box = (FlowBox) _fragments.get(0);
			if (box instanceof TextFragmentBox) {
				if (box instanceof TextFragmentBox) {
					if (((TextFragmentBox) box).getTextData().length() == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @param fontMetrics
	 */
	public void setFontMetrics(FontMetrics fontMetrics) {
		if (fontMetrics != null) {
			_fontAscent = fontMetrics.getAscent();
			_fontDescent = fontMetrics.getDescent();
			_fontLeading = fontMetrics.getLeading();
			// if (_fragments == null || _fragments.isEmpty())
			// {
			// this._ascent = _fontAscent + _fontLeading;
			// this._descent = _fontDescent;
			// if (this._height < this._ascent + this._descent)
			// {
			// this._height = this._ascent + this._descent;
			// }
			// }
		} else {
			_fontAscent = 0;
			_fontDescent = 0;
			_fontLeading = 0;
		}
	}

	private int getHalfXHeight() {
		return (_fontAscent + _fontDescent + _fontLeading) / 5;
	}

	private int getFontHeight() {
		return _fontAscent + _fontDescent + _fontLeading;
	}

	/**
	 * @return Returns the horizonalData.
	 */
	public Object getHorizonalData() {
		return _horizonalData;
	}

	/**
	 * @param horizonalData
	 *            The horizonalData to set.
	 */
	public void setHorizonalData(Object horizonalData) {
		this._horizonalData = horizonalData;
	}

	/**
	 * @return Returns the htmlInitData.
	 */
	public Object getHtmlInitData() {
		return _htmlInitData;
	}

	/**
	 * @param htmlInitData
	 *            The htmlInitData to set.
	 */
	public void setHtmlInitData(Object htmlInitData) {
		this._htmlInitData = htmlInitData;
	}

    @Override
    public void setY(int y) {
        // make set y public
        super.setY(y);
    }
}
