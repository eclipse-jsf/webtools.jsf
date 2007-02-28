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
package org.eclipse.jst.pagedesigner.dom;

import javax.xml.namespace.QName;

import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.pagedesigner.adapters.IBodyInfo;
import org.eclipse.jst.pagedesigner.adapters.internal.BodyInfo;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.jst.pagedesigner.utils.CommandUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Simple utility class.
 * 
 * @author mengbo
 * @version 1.5
 */
public class ValidatorSupport {
	public static boolean checkContainer(IDOMPosition position, QName qname) {
		return checkContainer(position, new QName[] { qname });
	}

	public static boolean checkContainer(IDOMPosition position, QName qname[]) {
		Node node = position.getContainerNode();
		while (node != null) {
			if (node instanceof Text) {
				node = node.getParentNode();
				continue;
			}
			if (node instanceof Element) {
				Element ele = (Element) node;
				String url = CMUtil.getElementNamespaceURI(ele);
				String tag = ele.getLocalName();

				for (int i = 0; i < qname.length; i++) {
					if (tag.equalsIgnoreCase(qname[i].getLocalPart())) {
						if (url == null) {
							// this means something wrong. To be error tolerant,
							// we treat it
							// as if url is same
							return true;
						} else if (url.equalsIgnoreCase(qname[i].getNamespaceURI())) {
							return true;
						}
					}
				}

				node = node.getParentNode();
				continue;
			}
			break;
		}
		return false;
	}

	public static IDOMPosition insertContainer(IDOMPosition position,
			QName container) {
		TaglibPaletteDrawer category = PaletteItemManager.getCurrentInstance().findCategoryByURI(container.getNamespaceURI());
		if (category != null){
			TagToolPaletteEntry tagItem = category.getTagPaletteEntryByTagName(container.getLocalPart());
			IDOMModel model = ((IDOMNode) position.getContainerNode()).getModel();
			Element form = CommandUtil.excuteInsertion(tagItem, model, position);
			if (form != null) {
				DOMPosition pos = new DOMPosition(form, 0);
				return pos;
			}
		}
        return null;
	}

	public static IBodyInfo getBodyInfo() {
		return BodyInfo.getInstance();
	}

}
