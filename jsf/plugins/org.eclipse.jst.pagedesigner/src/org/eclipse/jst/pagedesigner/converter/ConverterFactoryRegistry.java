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
package org.eclipse.jst.pagedesigner.converter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.utils.JSFSharedImages;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.converter.html.HTMLConverterFactory;
import org.eclipse.jst.pagedesigner.converter.jsp.JSPConverterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class ConverterFactoryRegistry {
	List _factories = new ArrayList();

	private static ConverterFactoryRegistry _instance;

	/**
	 * 
	 */
	private ConverterFactoryRegistry() {
		_factories.add(new JSPConverterFactory());
		_factories.add(new HTMLConverterFactory());

		List<IConverterFactory> facs = ConverterFacRegistryReader.getAllHandlers();
		if (facs != null) {
			for (IConverterFactory fac : facs) {
				addFactory(fac);
			}
		}
	}

	/**
	 * @param fac
	 */
	public void addFactory(IConverterFactory fac) {
		_factories.add(fac);
	}

	/**
	 * @param ele
	 * @param mode
	 * @param targetDocument
	 * @return the new btag converter
	 */
	public ITagConverter createTagConverter(Element ele, int mode,
			IDOMDocument targetDocument) {
		ITagConverter converter = internalCreateTagConverter(ele, mode);
		if (converter != null) {
			converter.setDestDocument(targetDocument);
		}
		return converter;
	}

	/**
	 * @param ele
	 * @param mode
	 * @return the new tag converter
	 */
	protected final ITagConverter internalCreateTagConverter(Element ele, int mode) {
		String uri = CMUtil.getElementNamespaceURI(ele);
		// first round, match uri
		for (int i = 0, size = _factories.size(); i < size; i++) {
			IConverterFactory fac = (IConverterFactory) _factories.get(i);
			String facuri = fac.getSupportedURI();
			if (facuri != null && facuri.equals(uri)) {
				ITagConverter converter = fac.createConverter(ele, mode);
				if (converter != null) {
					return converter;
				}
			}
		}
		// second round
		for (int i = 0, size = _factories.size(); i < size; i++) {
			IConverterFactory fac = (IConverterFactory) _factories.get(i);
			String facuri = fac.getSupportedURI();
			if (facuri == null) {
				ITagConverter converter = fac.createConverter(ele, mode);
				if (converter != null) {
					return converter;
				}
			}
		}

		// can't find. We need some default tag converter for it.
		// if the tag is empty, show it as icon.
		if (uri == null || ITLDConstants.URI_HTML.equals(uri)) {
			// basically, for HTML or non JSP tag, directly renders it.
			return new DumTagConverter(ele);
		}
        CMElementDeclaration decl = CMUtil.getElementDeclaration(ele);
        if (decl == null) {
        	return new DumTagConverter(ele);
        }
        int contentType = decl.getContentType();
        if (contentType == CMElementDeclaration.EMPTY) {
        	// if the tag is empty, show it as icon.
        	return new HiddenTagConverter(ele,
                    new LabelProvider()
                    {
                        public Image getImage(Object element) {
                            return getUnknownImage();
                        }
                    }
             );
        }
        return new DefaultUnknownTagConverter(ele, mode);

	}

	Image getUnknownImage() {
		return JSFUICommonPlugin.getDefault().getImage(
				JSFSharedImages.DEFAULT_PALETTE_TAG_IMG);
	}

	/**
	 * @return the singleton instance of the registry
	 */ 
	public static ConverterFactoryRegistry getInstance() {
		if (_instance == null) {
			_instance = new ConverterFactoryRegistry();
		}
		return _instance;
	}
}
