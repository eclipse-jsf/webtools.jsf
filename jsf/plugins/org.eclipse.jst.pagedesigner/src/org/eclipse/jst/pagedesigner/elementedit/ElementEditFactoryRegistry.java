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
package org.eclipse.jst.pagedesigner.elementedit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.pagedesigner.elementedit.html.HTMLElementEditFactory;
import org.eclipse.jst.pagedesigner.elementedit.jsp.JSPElementEditFactory;
import org.eclipse.jst.pagedesigner.utils.CMUtil;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class ElementEditFactoryRegistry {
	List _factories = new ArrayList();

	private static ElementEditFactoryRegistry _instance;

	/**
	 * 
	 */
	private ElementEditFactoryRegistry() {
		_factories.add(new HTMLElementEditFactory());
		_factories.add(new JSPElementEditFactory());

		IElementEditFactory facs[] = ElementEditFacRegistryReader
				.getAllHandlers();
		if (facs != null) {
			for (int i = 0; i < facs.length; i++) {
				addFactory(facs[i]);
			}
		}
	}

	public void addFactory(IElementEditFactory fac) {
		_factories.add(fac);
	}

	public IElementEdit createElementEdit(Element ele) {
		String uri = CMUtil.getElementNamespaceURI(ele);
		// first round, match uri
		for (int i = 0, size = _factories.size(); i < size; i++) {
			IElementEditFactory fac = (IElementEditFactory) _factories.get(i);
			String facuri = fac.getSupportedURI();
			if (facuri != null && facuri.equals(uri)) {
				IElementEdit elementEdit = fac.createElementEdit(ele);
				if (elementEdit != null) {
					return elementEdit;
				}
			}
		}
		// second round
		for (int i = 0, size = _factories.size(); i < size; i++) {
			IElementEditFactory fac = (IElementEditFactory) _factories.get(i);
			String facuri = fac.getSupportedURI();
			if (facuri == null) {
				IElementEdit elementEdit = fac.createElementEdit(ele);
				if (elementEdit != null) {
					return elementEdit;
				}
			}
		}
		return null;
	}

	public static ElementEditFactoryRegistry getInstance() {
		if (_instance == null) {
			_instance = new ElementEditFactoryRegistry();
		}
		return _instance;
	}
}
