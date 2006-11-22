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
package org.eclipse.jst.pagedesigner.jsp.core.internal.pagevar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.pagedesigner.jsp.core.pagevar.IVariableInfo;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.adapter.IDocumentPageVariableAdapter;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.adapter.IPageVariableAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class PageVariableAdapter implements IPageVariableAdapter {
	static final PageVariableAdapter _instance = new PageVariableAdapter();

	public static PageVariableAdapter getInstance() {
		return _instance;
	}

	/**
	 * 
	 */
	private PageVariableAdapter() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return IPageVariableAdapter.class.equals(type);
	}

	private IDocumentPageVariableAdapter getDocumentAdapter(
			INodeNotifier notifier) {
		if (notifier instanceof IDOMNode) {
			IDOMModel model = ((IDOMNode) notifier).getModel();
			if (model != null) {
				IDOMDocument document = model.getDocument();
				if (document != null) {
					return (IDocumentPageVariableAdapter) document
							.getAdapterFor(IDocumentPageVariableAdapter.class);
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#notifyChanged(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier,
	 *      int, java.lang.Object, java.lang.Object, java.lang.Object, int)
	 */
	public void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
		IDocumentPageVariableAdapter docadapter = getDocumentAdapter(notifier);

		switch (eventType) {
		case INodeNotifier.STRUCTURE_CHANGED:
			docadapter.refresh();
			break;
		case INodeNotifier.CONTENT_CHANGED:
		case INodeNotifier.CHANGE:
			// only this node changed, only refresh if this node is page
			// variable node
			if (notifier instanceof Element
					&& supportVariableInfo((Element) notifier)) {
				docadapter.refresh();
			}
			break;
		default:
			// skip. Ignore other kinds of change.
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.jsp.core.pagevar.adapter.IPageVariableAdapter#supportMultipleVariable(org.w3c.dom.Element)
	 */
	public boolean supportMultipleVariable(Element element) {
		return false;
	}

	/**
	 * @param element
	 * @return
	 */
	private boolean supportVariableInfo(Element element) {
		return PageVariableAdatperRegistry.getInstance().getTagVarDescriptor(
				element) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.jsp.core.pagevar.adapter.IPageVariableAdapter#getVariableInfo(org.w3c.dom.Element)
	 */
	public IVariableInfo getVariableInfo(Element element) {
		TagVarDescriptor desc = PageVariableAdatperRegistry.getInstance()
				.getTagVarDescriptor(element);
		if (desc == null) {
			return null;
		}
		String name;
		if (desc.isVarNameIsAttr()) {
			name = element.getAttribute(desc.getVarName());
			if (name == null || name.length() == 0) {
				return null; // missing name.
			}
		} else {
			name = desc.getVarName();
		}
		String type;
		if (desc.isVarTypeStringIsAttr()) {
			type = element.getAttribute(desc.getVarTypeString());
			if (type == null || type.length() == 0) {
				return null; // missing type
			}
		} else {
			type = desc.getVarTypeString();
		}
		return new VariableInfo(name, desc.getVarTypeMode(), type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.jsp.core.pagevar.adapter.IPageVariableAdapter#getVariableInfos(org.w3c.dom.Element)
	 */
	public List getVariableInfos(Element element) {
		IVariableInfo info = getVariableInfo(element);
		if (info == null) {
			return Collections.EMPTY_LIST;
		}
        List ret = new ArrayList(1);
        ret.add(info);
        return ret;
	}
}
