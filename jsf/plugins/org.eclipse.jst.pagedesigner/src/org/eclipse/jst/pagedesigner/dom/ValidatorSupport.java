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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.pagedesigner.adapters.IBodyInfo;
import org.eclipse.jst.pagedesigner.adapters.internal.BodyInfo;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.jst.pagedesigner.utils.CommandUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Simple utility class.
 * 
 * @author mengbo
 * @version 1.5
 */
public class ValidatorSupport 
{
	/**
	 * Equivalent to checkContainer(position new QName[]{qname}
	 * 
	 * @param position
	 * @param qname
	 * @return true if the tag identified by qname is found as a parent
     * of position.
	 */
	public static boolean checkContainer(IDOMPosition position, QName qname) {
		return checkContainer(position, new QName[] { qname });
	}

	/**
	 * Check for any of the containers listed in qname starting from qname and working
	 * up the DOM parent chain.
	 * 
	 * @param position
	 * @param qname
	 * @return true if one of the tags identified by qname is found as a parent
	 * of position.
	 */
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

    /**
     * @param position
     * @param model 
     * @param creationProvider 
     * @param customizationData
     * @return the new dom position for the inserted container or null if could
     *         not insert
     */
    public static IDOMPosition insertContainer(final IDOMPosition position,
            final IDOMModel model,
            final ITagDropSourceData creationProvider, final IAdaptable customizationData)
    {
        final Element newContainer = CommandUtil.executeInsertion(
                creationProvider, model, position, customizationData);
        if (newContainer != null)
        {
            DOMPosition pos = new DOMPosition(newContainer, 0);
            return pos;
        }
        return null;
    }

	/**
	 * @return the body info
	 */
	public static IBodyInfo getBodyInfo() {
	    //TODO: change this to be meta-data driven.
		return BodyInfo.getInstance();
	}

}
