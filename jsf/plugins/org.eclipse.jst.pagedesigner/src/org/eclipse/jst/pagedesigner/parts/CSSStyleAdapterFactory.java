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

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.eclipse.jst.pagedesigner.css2.style.AbstractStyle;
import org.eclipse.jst.pagedesigner.ui.preferences.PDPreferences;
import org.eclipse.wst.sse.core.internal.provisional.AbstractAdapterFactory;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class CSSStyleAdapterFactory extends AbstractAdapterFactory {
	static Class ADAPTERKEY = ICSSStyle.class;
    private final PDPreferences _prefs;

	private CSSStyleAdapterFactory() {
		super(ADAPTERKEY, true);
		_prefs = new PDPreferences();
	}

	/*
	 * (non-Javadoc)
	 * @seeorg.eclipse.wst.sse.core.internal.provisional.AbstractAdapterFactory#createAdapter(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier)
	 */
	protected INodeAdapter createAdapter(INodeNotifier target) {
		if (target instanceof Element) {
			return new AbstractStyle((Element) target, _prefs);
		}
        return null;
	}

	/**
	 * @return the factory instance
	 */
	public static CSSStyleAdapterFactory getInstance() {
        // TODO: this 'singleton' is broken
		return new CSSStyleAdapterFactory();
	}

}
