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
package org.eclipse.jst.pagedesigner.utils;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.common.logging.Logger;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.dom.JSFValidatorSupport;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemDescriptor;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteElementTemplateHelper;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class CommandUtil {
	private static final Logger _log = PDPlugin.getLogger(CommandUtil.class);

	public static Element excuteInsertion(IPaletteItemDescriptor itemDes,
			IHTMLGraphicalViewer viewer, IDOMPosition domPosition) {
		return excuteInsertion(itemDes, viewer.getModel(), domPosition);
	}

	public static Element excuteInsertion(IPaletteItemDescriptor itemDes,
			IDOMModel model, IDOMPosition domPosition) {
		try {
			IDOMPosition position = DOMPositionHelper.splitText(domPosition);

			String uri = itemDes.getURI();
			String localname = itemDes.getTagName();
			if (IJMTConstants.URI_HTML.equals(uri)) {
				localname = localname.toUpperCase();
			}
			position = BodyHelper
					.adjustInsertPosition(uri, localname, position);

			position = prepareJSFValidity(position, itemDes);
			if (position == null) {
				// user cancelled
				return null;
			}

			// because the next call of getPrefix() may insert new taglib node
			// into the document, if we use the normal
			// DOMPositin which use index, maybe it will be invalidated by the
			// new taglib node. So use RefPosition here.
			position = DOMPositionHelper.toDOMRefPosition(position);

			String prefix = getPrefix(uri, model, itemDes.getDefaultPrefix());
			Element ele = model.getDocument().createElement(localname);

			// XXX: we are using "startsWith("directive.")" to test whether
			// should setJSPTag, this
			// maybe is not the best way. Need check whether SSE have special
			// API for it.
			if (IJMTConstants.URI_JSP.equals(uri)
					&& (ele.getLocalName().startsWith("directive.")
							|| "declaration".equals(ele.getLocalName())
							|| "expression".equals(ele.getLocalName()) || "scriptlet"
							.equals(ele.getLocalName()))) {
				// it is a jsp tag
				((IDOMElement) ele).setJSPTag(true);
			}
			if (prefix != null) {
				ele.setPrefix(prefix);
			}
			// Generate the node according to template.
			PaletteElementTemplateHelper.applyTemplate(model, ele, itemDes);

			Map map = itemDes.getInitialAttributes();
			if (map != null) {
				for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
					String attrname = (String) iter.next();
					String attrvalue = (String) map.get(attrname);
					ele.setAttribute(attrname, attrvalue);
				}
			}
			if (position.getNextSiblingNode() == null) {
				position.getContainerNode().appendChild(ele);
			} else {
				position.getContainerNode().insertBefore(ele,
						position.getNextSiblingNode());
			}

			return ele;
		} catch (Exception e) {
			_log.info("Invalid insertion in position:" + domPosition + "\n", e);
			return null;
		}
	}

	public static IDOMPosition prepareJSFValidity(IDOMPosition position,
			IPaletteItemDescriptor item) {
		if (item.isJSFComponent()) {
			return JSFValidatorSupport.prepareInsertJSFComponent(position, item
					.getURI(), item.getTagName(), item.isRequireHForm());
		}
        return position;
	}

	// /**
	// * Simple validity checking. Currently only support automatically insert
	// <f:view>and <f:form>tag for user.
	// * @param position
	// * @param uri
	// * @param localname
	// * @return
	// */
	// private static IDOMPosition checkValidity(IDOMPosition position, String
	// uri, String localname)
	// {
	// if (IJMTConstants.URI_JSF_CORE.equals(uri) ||
	// IJMTConstants.URI_JSF_HTML.equals(uri))
	// {
	// return JSFValidatorSupport.prepareInsertJSFComponent(position, uri,
	// localname);
	// }
	// return position;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.requests.NodeCreationFactory#getPrefix(int)
	 */
	private static String getPrefix(String uri, IDOMModel model,
			String suggested) {
		if (IJMTConstants.URI_HTML.equals(uri)
				|| IJMTConstants.URI_JSP.equals(uri)) {
			return null;
		}

		// now handles custom tag lib
		return JSPUtil.getOrCreatePrefix(model, uri, suggested);
	}

}
