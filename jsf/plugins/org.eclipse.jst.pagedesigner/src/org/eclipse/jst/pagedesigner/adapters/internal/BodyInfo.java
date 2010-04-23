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
package org.eclipse.jst.pagedesigner.adapters.internal;

import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.adapters.IBodyInfo;
import org.eclipse.jst.pagedesigner.jsp.core.IJSPCoreConstants;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
// XXX: currently hard coded as singleton implementation,
// it is believed in the future will not use singleton, because
// we want to dynamically support other taglibs.
public class BodyInfo implements IBodyInfo {
	private static final BodyInfo _instance = new BodyInfo();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.adapters.IDesignInfo#isBodyContainer(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier)
	 */
	public boolean isBodyContainer(IDOMNode node) {
		switch (node.getNodeType()) {
		case Node.DOCUMENT_FRAGMENT_NODE:
		case Node.DOCUMENT_NODE:
			return true;
		case Node.ELEMENT_NODE:
			return isBodyContainerElement((Element) node);
		default:
			return false;
		}
	}

	/**
	 * @param element
	 * @return
	 */
	private boolean isBodyContainerElement(Element element) {
		String localname = element.getLocalName();
		/*
		 * String namespaceURI = CMUtil.getElementNamespaceURI(element); if
		 * (IJMTConstants.URI_HTML.equals(namespaceURI)) { return
		 * "html".equalsIgnoreCase(localname) ||
		 * "body".equalsIgnoreCase(localname); } if
		 * (IJMTConstants.URI_JSF_CORE.equals(namespaceURI)) { return
		 * "view".equals(localname) || "subview".equals(localname); }
		 */
		return IHTMLConstants.TAG_HTML.equalsIgnoreCase(localname)
				|| IHTMLConstants.TAG_BODY.equalsIgnoreCase(localname)
				|| IJSFConstants.TAG_VIEW.equals(localname)
				|| IJSFConstants.TAG_SUBVIEW.equals(localname);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.adapters.IDesignInfo#isBodyHeader(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier,
	 *      java.lang.String, java.lang.String)
	 */
	public boolean isBodyHeader(IDOMNode node, String uri, String localname) {
		switch (node.getNodeType()) {
		case Node.DOCUMENT_FRAGMENT_NODE:
		case Node.DOCUMENT_NODE:
			return isDocumentHeader(uri, localname);
		case Node.ELEMENT_NODE:
			return isElementHeader((Element) node, uri, localname);
		default:
			return false;
		}
	}

	/**
	 * @param element
	 * @param uri
	 * @param localname
	 * @return
	 */
	private boolean isElementHeader(Element element, String uri,
			String localname) {
		String elelocalname = element.getLocalName();
		String namespaceURI = CMUtil.getElementNamespaceURI(element);
		if (ITLDConstants.URI_HTML.equals(namespaceURI)
				&& IHTMLConstants.TAG_HTML.equalsIgnoreCase(elelocalname)) {
			return IHTMLConstants.TAG_HEAD.equalsIgnoreCase(localname);
		}
		if (ITLDConstants.URI_JSF_CORE.equals(namespaceURI)
				&& IJSFConstants.TAG_VIEW.equalsIgnoreCase(elelocalname)) {
			return IJSFConstants.TAG_LOADBUNDLE.equalsIgnoreCase(localname);
		}
		return false;
	}

	/**
	 * @param uri
	 * @param localname
	 * @return
	 */
	private boolean isDocumentHeader(String uri, String localname) {
		// FIXME: temparary commented out, since the dragged node do not have
		// uri information for now.
		// if (IJMTConstants.URI_JSP.equals(uri))
		return IJSPCoreConstants.TAG_DIRECTIVE_PAGE.equals(localname)
				|| IJSPCoreConstants.TAG_DIRECTIVE_TAGLIB.equals(localname);

	}

	/**
	 * @return the singleton
	 */
	public static IBodyInfo getInstance() {
		return _instance;
	}

	private BodyInfo()
	{
	    // no external instantiation
	}
}
