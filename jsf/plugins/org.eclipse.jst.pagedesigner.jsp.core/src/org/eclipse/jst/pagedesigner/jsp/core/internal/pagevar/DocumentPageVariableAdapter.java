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
import java.util.List;

import org.eclipse.jst.pagedesigner.jsp.core.ListenerList;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.IChangeListener;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.IVariableInfo;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.adapter.IDocumentPageVariableAdapter;
import org.eclipse.jst.pagedesigner.jsp.core.pagevar.adapter.IPageVariableAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This adapter will be adapted on the JSP document.
 * 
 * @author mengbo
 * @version 1.5
 */
public class DocumentPageVariableAdapter implements
		IDocumentPageVariableAdapter {
	private IDOMDocument _document;

	private ListenerList _changeListeners = new ListenerList(2);

	private List _variableInfos = new ArrayList();

	private boolean _readingInfo = false;

	/**
	 * 
	 */
	public DocumentPageVariableAdapter(IDOMDocument doc) {
		super();
		this._document = doc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.jsp.core.pagevar.IPageVariablesProvider#getBeanInfos()
	 */
	public IVariableInfo[] getBeanInfos() {
		IVariableInfo[] ret = new IVariableInfo[_variableInfos.size()];
		return (IVariableInfo[]) _variableInfos.toArray(ret);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.jsp.core.pagevar.IPageVariablesProvider#addChangeListener(org.eclipse.jst.pagedesigner.jsp.core.pagevar.IChangeListener)
	 */
	public void addChangeListener(IChangeListener listener) {
		this.reReadInfo();
		_changeListeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.jsp.core.pagevar.IPageVariablesProvider#removeChangeListener(org.eclipse.jst.pagedesigner.jsp.core.pagevar.IChangeListener)
	 */
	public void removeChangeListener(IChangeListener listener) {
		_changeListeners.remove(listener);
	}

	protected void fireChanged() {
		Object[] listeners = _changeListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			IChangeListener l = (IChangeListener) listeners[i];
			l.changed();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return IDocumentPageVariableAdapter.class.equals(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.sse.core.internal.provisional.INodeAdapter#notifyChanged(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier,
	 *      int, java.lang.Object, java.lang.Object, java.lang.Object, int)
	 */
	public void notifyChanged(INodeNotifier notifier, int eventType,
			Object changedFeature, Object oldValue, Object newValue, int pos) {
		switch (eventType) {
		case INodeNotifier.STRUCTURE_CHANGED:
		case INodeNotifier.CONTENT_CHANGED:
		case INodeNotifier.CHANGE:
			refresh();
			break;
		default:
			// skip. Ignore other kinds of change.
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.jsp.core.pagevar.IPageVariablesProvider#refresh()
	 */
	public void refresh() {
		reReadInfo();
	}

	protected void reReadInfo() {
		if (this._readingInfo) {
			return;
		}
		try {
			List oldInfo = this._variableInfos;
			this._variableInfos = new ArrayList();
			readNode(_document);
			if (!isSame(oldInfo, this._variableInfos)) {
				fireChanged();
			}
		} finally {
			this._readingInfo = false;
		}
	}

	/**
	 * compare two variable info list.
	 * 
	 * @param oldInfo
	 * @param list
	 * @return true if same.
	 */
	private boolean isSame(List oldInfo, List list) {
		if (oldInfo.size() != list.size()) {
			return false;
		}
		for (int i = 0, size = oldInfo.size(); i < size; i++) {
			IVariableInfo info1 = (IVariableInfo) oldInfo.get(i);
			IVariableInfo info2 = (IVariableInfo) list.get(i);
			if (info1 == null) {
				return false;// should not happen
			}
			if (!info1.equals(info2)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * recursively read all element, and see whether they have
	 * IPageVariableAdapter, and then get variable info.
	 * 
	 * @param element
	 */
	private void readNode(Node node) {
		if (node instanceof INodeNotifier && node instanceof Element) {
			Element element = (Element) node;
			Object obj = ((INodeNotifier) element)
					.getAdapterFor(IPageVariableAdapter.class);
			if (obj instanceof IPageVariableAdapter) {
				IPageVariableAdapter adapter = (IPageVariableAdapter) obj;
				if (adapter.supportMultipleVariable(element)) {
					List infos = ((IPageVariableAdapter) obj)
							.getVariableInfos(element);
					if (infos != null) {
						this._variableInfos.addAll(infos);
					}
				} else {
					// hope not doing addAll could improve some performance.
					IVariableInfo info = adapter.getVariableInfo(element);
					if (info != null) {
						this._variableInfos.add(info);
					}
				}
			}
		}

		NodeList childNodes = node.getChildNodes();
		if (childNodes != null) {
			for (int i = 0, length = childNodes.getLength(); i < length; i++) {
				Node childNode = childNodes.item(i);
				readNode(childNode);
			}
		}
	}
}
