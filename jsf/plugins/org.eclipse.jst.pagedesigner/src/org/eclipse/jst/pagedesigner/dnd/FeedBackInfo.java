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
package org.eclipse.jst.pagedesigner.dnd;

/**
 * @author mengbo
 */
public class FeedBackInfo {
	private String _description;

	private int _order;

	/**
	 * @param desc 
	 * @param order 
	 */
	public FeedBackInfo(String desc, int order) {
		this._description = desc;
	}

	/**
	 * the description may be used in tooltip or wizard dialog selection to tell
	 * user the effect of the drop.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * how important this feedback is. This information may be used when we
	 * ordering the choices in the wizard.
	 * 
	 * range from 0-10, the bigger, the more important.
	 * 
	 * @return the order
	 */
	public int getOrder() {
		return _order;
	}
}
