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
package org.eclipse.jst.pagedesigner.css2.style;

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;

/**
 * @author mengbo
 * @version 1.5
 */
public final class StyleUtil {
	/**
	 * @param style
	 * @return true if the style is in a widget
	 */
	public static boolean isInWidget(ICSSStyle style) {
		while (style != null && style != DefaultStyle.getInstance()) {
			ITagEditInfo info = (ITagEditInfo) style
					.getAdapter(ITagEditInfo.class);
			if (info != null && info.isWidget()) {
				return true;
			}

			style = style.getParentStyle();
		}
		return false;
	}
	
	private StyleUtil()
	{
	    // util class
	}
}
