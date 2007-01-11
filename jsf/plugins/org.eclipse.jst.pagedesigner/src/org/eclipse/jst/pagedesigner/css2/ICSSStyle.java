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
public interface ICSSStyle extends INodeAdapter, IAdaptable {

    // the number of extra pixels to add to top, bottom, left and right padding insets
    // in the rendering so that separation between contained components is more
    // apparent at design time.  These extra pixels are design mode only
    // Note: margin padding would be preferred but it doesn't seem to affect
    // bottom padding the way border insets do.
    // TODO:  this should be set to a preference and probably also use an
    // algorithm to determine if the the current box style already has a large
    // enough separation offset (perhaps a threshold instead of an additive value)
    public static final int ARTIFICIAL_BORDER_OFFSET = 4;
    
    public static final int INHERIT = Integer.MIN_VALUE;
    
	public static final String TOP = "top";

	public static final String RIGHT = "right";

	public static final String LEFT = "left";

	public static final String BOTTOM = "bottom";

	public void reset();

	public ICSSFont getCSSFont();

	public Object getStyleProperty(String property);

	public Insets getMarginInsets();

	public Insets getBorderInsets();

	public Insets getPaddingInsets();

	/**
	 * shortcut method to get the CSS display.
	 * 
	 * @see http://www.w3.org/TR/REC-CSS2/visuren.html#propdef-display
	 * @return
	 */
	public String getDisplay();

	/**
	 * null means transparent.
	 * 
	 * @return
	 */
	public Object getBackgroundColor();

	public Object getColor();

	/**
	 * @return
	 */
	public boolean isSizeIncludeBorderPadding();

	public void dispose();

	/**
	 * @return
	 */
	public ICSSStyle getParentStyle();

	/**
	 * Get counters declared on this style. the counters are either created by
	 * counter-reset or refered by counter-increment
	 * 
	 * @return
	 */
	public Map getCounters();

	/**
	 * Search a named counter declared on this style or its ancestors' styles
	 * 
	 * @param name
	 * @param must
	 * @return
	 */
	public ICounterValueGenerator findCounter(String name, boolean must);

	/**
	 * Currently, rowspan and colspan are not CSS property. But based on the CSS
	 * specification, it is expected in the future this two will be added as CSS
	 * property, so we also include them into ICSSStyle
	 * 
	 * @return
	 */
	public int getRowSpan();

	/**
	 * @return
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
	 * @return
	 */
	public boolean isInSelection();

	public Object getHTMLelementInitValue(String propertyName);
}
