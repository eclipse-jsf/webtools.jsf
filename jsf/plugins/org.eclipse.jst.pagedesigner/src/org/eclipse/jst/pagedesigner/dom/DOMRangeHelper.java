/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.dom;

import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public final class DOMRangeHelper {
	/**
	 * @param range
	 * @return the dom range
	 */
	public static DOMRange toDOMRange(DesignRange range) {
		if (range.getStartPosition() == range.getEndPosition()) {
			IDOMPosition dp = DOMPositionHelper.toDOMPosition(range
					.getStartPosition());
			return new DOMRange(dp, dp);
		}
        return new DOMRange(DOMPositionHelper.toDOMPosition(range
        		.getStartPosition()), DOMPositionHelper.toDOMPosition(range
        		.getEndPosition()));
	}

	/**
	 * @param range
	 * @return the design range
	 */
	public static DesignRange toDesignRange(DOMRange range) {
		if (range.getStartPosition() == range.getEndPosition()) {
			DesignPosition dp = DOMPositionHelper.toDesignPosition(range
					.getStartPosition());
			return new DesignRange(dp, dp);
		}
		return new DesignRange(DOMPositionHelper.toDesignPosition(range
				.getStartPosition()), DOMPositionHelper.toDesignPosition(range
				.getEndPosition()));
	}

	/**
	 * @param range
	 * @param original
	 * @param replacement
	 * @return the dom range
	 */
	public static DOMRange handleReplacement(DOMRange range, Node original,
			Node replacement) {
		if (range.getStartPosition() == range.getEndPosition()) {
			IDOMPosition pos = range.getStartPosition().handleReplacement(
					original, replacement);
			return new DOMRange(pos, pos);
		}
        return new DOMRange(range.getStartPosition().handleReplacement(
        		original, replacement), range.getEndPosition()
        		.handleReplacement(original, replacement));
	}
	
	private DOMRangeHelper()
	{
	    // util class, no instantiation
	}
}
