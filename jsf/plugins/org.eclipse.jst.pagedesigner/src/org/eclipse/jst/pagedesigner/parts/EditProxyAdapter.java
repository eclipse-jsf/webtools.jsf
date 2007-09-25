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

import org.eclipse.jst.pagedesigner.css2.style.IRangeSelectionProxy;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;

/**
 * EditProxyAdapter is used to provide additional information to the underlying
 * figures. As when we doing the "convert", we are creating new nodes and
 * generate figure using those new nodes. This class is used to adapt to those
 * new nodes, and providing additional information to them.
 * 
 * @author mengbo
 * @version 1.5
 */
public class EditProxyAdapter implements INodeAdapter, IRangeSelectionProxy {
	ElementEditPart _part;

	/**
	 * @param part 
	 */
	public EditProxyAdapter(ElementEditPart part) {
		_part = part;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		if (type == IRangeSelectionProxy.class) {
			return true;
		} else if (type == EditProxyAdapter.class) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#notifyChanged(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier,
	 *      int, java.lang.Object, java.lang.Object, java.lang.Object, int)
	 */
	public void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
        // do nothing?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.style.IRangeSelectionProxy#isRangeSelected()
	 */
	public boolean isRangeSelected() {
		return _part.isRangeSelected();
	}
}
