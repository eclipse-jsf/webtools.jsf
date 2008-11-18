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

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.dom.DOMUtil;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * For some tag, could generate some XML code.
 * 
 * @author mengbo
 * @version 1.5
 */
public abstract class HTMLStringTagConverter extends AbstractTagConverter {

	static Logger _log = PDPlugin.getLogger(HTMLStringTagConverter.class);

	/**
	 * @param host
	 */
	public HTMLStringTagConverter(Element host) {
		super(host);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.AbstractTagConverter#doConvertRefresh()
	 */
	protected Element doConvertRefresh() {
		// following are XML implementation. Assume the string is welformed HTML
		// try
		// {
		// String html = getGeneratedHTML();
		// DocumentBuilder builder =
		// DocumentBuilderFactory.newInstance().newDocumentBuilder();
		// Element result = builder.parse(new InputSource(new
		// StringReader(html))).getDocumentElement();
		// return (Element)DOMUtil.cloneNodeDeep(this.getDestDocument(),
		// result);
		// }
		// catch(Exception ex)
		// {
		// Element temp = createElement("div");
		// temp.appendChild(createText("ERROR: "+ex.getMessage()));
		// return temp;
		// }
		InputStream stream = null;
		try {
			String id = "" + System.currentTimeMillis() + ".html"; //$NON-NLS-1$ //$NON-NLS-2$
			IModelManager manager = StructuredModelManager.getModelManager();
			stream = new ByteArrayInputStream(getGeneratedHTML().getBytes());
			IDOMModel model = (IDOMModel) manager.getModelForRead(id, stream,
					null);
			Element root = model.getDocument().getDocumentElement();
			Element resultEle = (Element) DOMUtil.cloneNodeDeepIgnoreError(
					getDestDocument(), root);
			model.releaseFromRead();
			return resultEle;
		} catch (Exception ex) {
			_log.error("Log.Error.HTMLStringTagConverter.Error", ex); //$NON-NLS-1$
			Element temp = createElement("div"); //$NON-NLS-1$
			temp.appendChild(createText("Error loading: " + ex.getMessage())); //$NON-NLS-1$
			return temp;
		} finally {
			ResourceUtils.ensureClosed(stream);
		}
	}

	/**
	 * @return the generated HTML string
	 * @throws Exception
	 */
	public abstract String getGeneratedHTML() throws Exception;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#isMultiLevel()
	 */
	public boolean isMultiLevel() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.css2.style.ITagEditInfo#isWidget()
	 */
	public boolean isWidget() {
		return true;
	}
}
