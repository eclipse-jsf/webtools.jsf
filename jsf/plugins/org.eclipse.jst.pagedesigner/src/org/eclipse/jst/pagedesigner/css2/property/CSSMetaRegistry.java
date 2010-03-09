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
package org.eclipse.jst.pagedesigner.css2.property;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.pagedesigner.ui.preferences.PDPreferences;

/**
 * @author mengbo
 */
public class CSSMetaRegistry {
	Map _map = new HashMap();

	private static CSSMetaRegistry _instance;

	private CSSMetaRegistry() {
		initialize();
	}

	/**
	 * class initializer
	 */
	private void initialize() 
	{
	    final PDPreferences prefs = new PDPreferences();
		_map.put(ICSSPropertyID.ATTR_DISPLAY, new DisplayMeta());
		_map.put(ICSSPropertyID.ATTR_TEXTALIGN, new TextAlignMeta());
		_map.put(ICSSPropertyID.ATTR_HORIZONTAL_ALIGN, new HorizontalAlignMeta());
		_map.put(ICSSPropertyID.ATTR_TEXTDECORATION, new TextDecorationMeta());
		_map.put(ICSSPropertyID.ATTR_WHITESPACE, new WhiteSpaceMeta());
		_map.put(ICSSPropertyID.ATTR_WIDTH, new WidthMeta());
		_map.put(ICSSPropertyID.ATTR_MIN_WIDTH, new WidthMeta());
		_map.put(ICSSPropertyID.ATTR_HEIGHT, new HeightMeta());
		_map.put(ICSSPropertyID.ATTR_MIN_HEIGHT, new HeightMeta());

		_map.put(ICSSPropertyID.ATTR_BORDER_LEFT_WIDTH, new BorderWidthMeta());
		_map.put(ICSSPropertyID.ATTR_BORDER_RIGHT_WIDTH, new BorderWidthMeta());
		_map.put(ICSSPropertyID.ATTR_BORDER_BOTTOM_WIDTH, new BorderWidthMeta());
		_map.put(ICSSPropertyID.ATTR_BORDER_TOP_WIDTH, new BorderWidthMeta());

		_map.put(ICSSPropertyID.ATTR_BORDER_LEFT_STYLE, new BorderStyleMeta());
		_map.put(ICSSPropertyID.ATTR_BORDER_RIGHT_STYLE, new BorderStyleMeta());
		_map.put(ICSSPropertyID.ATTR_BORDER_BOTTOM_STYLE, new BorderStyleMeta());
		_map.put(ICSSPropertyID.ATTR_BORDER_TOP_STYLE, new BorderStyleMeta());

		_map.put(ICSSPropertyID.ATTR_BORDER_LEFT_COLOR, new BorderColorMeta());
		_map.put(ICSSPropertyID.ATTR_BORDER_RIGHT_COLOR, new BorderColorMeta());
		_map.put(ICSSPropertyID.ATTR_BORDER_BOTTOM_COLOR, new BorderColorMeta());
		_map.put(ICSSPropertyID.ATTR_BORDER_TOP_COLOR, new BorderColorMeta());

		_map.put(ICSSPropertyID.ATTR_PADDING_LEFT, new PaddingWidthMeta(prefs));
		_map.put(ICSSPropertyID.ATTR_PADDING_RIGHT, new PaddingWidthMeta(prefs));
		_map.put(ICSSPropertyID.ATTR_PADDING_BOTTOM, new PaddingWidthMeta(prefs));
		_map.put(ICSSPropertyID.ATTR_PADDING_TOP, new PaddingWidthMeta(prefs));

		_map.put(ICSSPropertyID.ATTR_MARGIN_LEFT, new MarginWidthMeta());
		_map.put(ICSSPropertyID.ATTR_MARGIN_RIGHT, new MarginWidthMeta());
		_map.put(ICSSPropertyID.ATTR_MARGIN_BOTTOM, new MarginWidthMeta());
		_map.put(ICSSPropertyID.ATTR_MARGIN_TOP, new MarginWidthMeta());

		_map.put(ICSSPropertyID.ATTR_FONT_FAMILY, new FontFamilyMeta());
		_map.put(ICSSPropertyID.ATTR_FONT_SIZE, new FontSizeMeta());
		_map.put(ICSSPropertyID.ATTR_FONT_STYLE, new FontStyleMeta());
		_map.put(ICSSPropertyID.ATTR_FONT_WEIGHT, new FontWeightMeta());

		_map.put(ICSSPropertyID.ATTR_BACKGROUND_COLOR, new BackgroundColorMeta());
		_map.put(ICSSPropertyID.ATTR_BACKGROUND_IMAGE, new BackgroundImageMeta());
		_map.put(ICSSPropertyID.ATTR_COLOR, new ColorPropertyMeta());
		_map.put(ICSSPropertyID.ATTR_TEXTCOLOR, new ColorPropertyMeta());

		_map.put(ICSSPropertyID.ATTR_BORDER_COLLAPSE, new BorderCollapseMeta());
		_map.put(ICSSPropertyID.ATTR_BORDER_SPACING, new BorderSpacingMeta());

		_map.put(ICSSPropertyID.ATTR_LIST_STYLE_TYPE, new ListStyleTypeMeta());
		_map.put(ICSSPropertyID.ATTR_LIST_STYLE_IMAGE, new ListStyleImageMeta());
		_map.put(ICSSPropertyID.ATTR_LIST_STYLE_POSITION, new ListStylePositionMeta());
		_map.put(ICSSPropertyID.ATTR_COUNTER_RESET, new CounterResetMeta());
		_map.put(ICSSPropertyID.ATTR_COUNTER_INCREMENT, new CounterIncrementMeta());

		_map.put(ICSSPropertyID.ATTR_TOP, new PositionOffsetMeta());
		_map.put(ICSSPropertyID.ATTR_RIGHT, new PositionOffsetMeta());
		_map.put(ICSSPropertyID.ATTR_BOTTOM, new PositionOffsetMeta());
		_map.put(ICSSPropertyID.ATTR_LEFT, new PositionOffsetMeta());
		_map.put(ICSSPropertyID.ATTR_POSITION, new PositionMeta());

		_map.put(ICSSPropertyID.ATTR_EMPTY_CELLS, new EmptyCellsMeta());
		_map.put(ICSSPropertyID.ATTR_VISIBILITY, new VisibilityMeta());
		_map.put(ICSSPropertyID.ATTR_VERTICAL_ALIGN, new VerticalAlignMeta());

		_map.put(ICSSPropertyID.ATTR_OVERFLOW, new OverflowMeta());
	}

	/**
	 * @param property
	 * @return the meta
	 */
	public ICSSPropertyMeta getMeta(String property) {
		return (ICSSPropertyMeta) _map.get(property);
	}

	/**
	 * @return the singleton instance
	 */
	public static final CSSMetaRegistry getInstance() {
		if (_instance == null) {
			_instance = new CSSMetaRegistry();
		}
		return _instance;
	}
}
