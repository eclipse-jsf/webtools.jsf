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
package org.eclipse.jst.pagedesigner.css2;

import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.jst.pagedesigner.css2.font.ICSSFont;
import org.eclipse.jst.pagedesigner.css2.list.ICounterValueGenerator;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;

/**
 * The style declaration for an element can be cached.
 * 
 * @author mengbo
 */
public interface ICSSStyle extends INodeAdapter, IAdaptable 
{
    /**
     * 
     */
    public static final int INHERIT = Integer.MIN_VALUE;
    
	/**
	 * the top attribute vale
	 */
	public static final String TOP = "top"; //$NON-NLS-1$

	/**
	 * the right attribute value
	 */
	public static final String RIGHT = "right"; //$NON-NLS-1$

	/**
	 * the left attribute value
	 */
	public static final String LEFT = "left"; //$NON-NLS-1$

	/**
	 * the bottom attribute value
	 */
	public static final String BOTTOM = "bottom"; //$NON-NLS-1$

	/**
	 * 
	 */
	public void reset();

	/**
	 * @return the font
	 */
	public ICSSFont getCSSFont();

	/**
	 * @param property
	 * @return the style property
	 */
	public Object getStyleProperty(String property);

	/**
	 * @return the margin insets
	 */
	public Insets getMarginInsets();

	/**
	 * @return the border insets
	 */
	public Insets getBorderInsets();

	/**
	 * @return the padding insets
	 */
	public Insets getPaddingInsets();

	/**
	 * shortcut method to get the CSS display.
	 * 
	 * see http://www.w3.org/TR/REC-CSS2/visuren.html#propdef-display
	 * @return the display string
	 */
	public String getDisplay();

	/**
	 * null means transparent.
	 * 
	 * @return the background color
	 */
	public Object getBackgroundColor();

	/**
	 * @return the foreground color
	 */
	public Object getColor();

	/**
	 * @return true if size includes border padding
	 */
	public boolean isSizeIncludeBorderPadding();

	/**
	 * 
	 */
	public void dispose();

	/**
	 * @return the parent style
	 */
	public ICSSStyle getParentStyle();

	/**
	 * Get counters declared on this style. the counters are either created by
	 * counter-reset or refered by counter-increment
	 * 
	 * @return the counters
	 */
	public Map getCounters();

	/**
	 * Search a named counter declared on this style or its ancestors' styles
	 * 
	 * @param name
	 * @param must
	 * @return the generator
	 */
	public ICounterValueGenerator findCounter(String name, boolean must);

	/**
	 * Currently, rowspan and colspan are not CSS property. But based on the CSS
	 * specification, it is expected in the future this two will be added as CSS
	 * property, so we also include them into ICSSStyle
	 * 
	 * @return the row span
	 */
	public int getRowSpan();

	/**
	 * @return the column span
	 */
	public int getColSpan();

	/**
	 * Normally, when layout a figure and its children. We'll reset the counters
	 * declared on this style. And if there are "counter-increment" on this
	 * style, they'll also be processed.
	 * 
	 */
	public void processCounters();

	/**
	 * Whether the corresponding figure should be draw in selected mode. This is
	 * not a real CSS property. This is a shortcut method. implemented through
	 * getAdapter() on IRangeSelectionProxy
	 * 
	 * @return true if in selection
	 */
	public boolean isInSelection();

	/**
	 * @param propertyName
	 * @return the element init value
	 */
	public Object getHTMLelementInitValue(String propertyName);
}
