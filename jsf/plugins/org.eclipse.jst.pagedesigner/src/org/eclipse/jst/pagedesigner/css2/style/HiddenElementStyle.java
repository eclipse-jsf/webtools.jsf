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

import org.eclipse.jst.pagedesigner.parts.EditProxyAdapter;
import org.eclipse.jst.pagedesigner.ui.preferences.PDPreferences;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.w3c.dom.Element;

/**
 * A style for hidden elements
 *
 */
public class HiddenElementStyle extends DefaultStyle {
	private EditProxyAdapter _editProxyAdapter;

	private Element _convertedElement;

	private static ITagEditInfo _tagEditInfo = new ITagEditInfo() {

		public boolean isWidget() {
			return true;
		}

		public boolean needBorderDecorator() {
			return false;
		}

		public boolean needTableDecorator() {
			return false;
		}

		public int getMinWidth() {
			return 0;
		}

		public int getMinHeight() {
			return 0;
		}
	};

	/**
	 * @param adapter
	 * @param prefs 
	 */
	public HiddenElementStyle(EditProxyAdapter adapter, PDPreferences prefs) 
	{
	    super(prefs);
		this._editProxyAdapter = adapter;
	}

	/**
	 * @param convertedElement
	 * @param prefs 
	 */
	public HiddenElementStyle(Element convertedElement, PDPreferences prefs) 
	{
        super(prefs);
		this._convertedElement = convertedElement;
	}

	public boolean isInSelection() {
		if (_convertedElement instanceof INodeNotifier) {
			Object ret = ((INodeNotifier) _convertedElement)
					.getAdapterFor(AbstractStyle.class);
			if (ret instanceof AbstractStyle) {
				return ((AbstractStyle) ret).isInSelection();
			}
		}

		if (_editProxyAdapter != null) {
			return _editProxyAdapter.isRangeSelected();
		}

		return false;
	}

	public Object getAdapter(Class adapter) {
		if (_convertedElement instanceof INodeNotifier) {
			Object ret = ((INodeNotifier) _convertedElement)
					.getAdapterFor(AbstractStyle.class);
			if (ret instanceof AbstractStyle) {
				return ((AbstractStyle) ret).getAdapter(adapter);
			}
		}
		if (_editProxyAdapter != null && adapter == ITagEditInfo.class) {
			return _tagEditInfo;
		}
		return null;
	}

}
