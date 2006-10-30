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

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jsp.core.internal.contentmodel.TaglibController;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TLDCMDocumentManager;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TaglibTracker;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.wst.html.core.internal.format.HTMLFormatProcessorImpl;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * utility class for JSP related information.
 * 
 * @author mengbo
 */
public class JSPUtil {
	/**
	 * find out whether the specified taglib has been defined in the IDOMModel.
	 * If found, then return the prefix. If can't find, then will try to add a
	 * taglib declaration into the model, and try to use the specified default
	 * prefix
	 * 
	 * @param model
	 * @param uri
	 * @return
	 */
	public static String getOrCreatePrefix(IDOMModel model, String uri,
			String defaultPrefix) {
		return getOrCreatePrefix(model, uri, defaultPrefix, null);
	}

	/**
	 * 
	 * @param model
	 * @param uri
	 * @param defaultPrefix
	 * @param nodes
	 *            if a taglib node is created, then the created tag lib node is
	 *            returned in this.
	 * @return
	 */
	public static String getOrCreatePrefix(IDOMModel model, String uri,
			String defaultPrefix, Node[] nodes) {
		String prefix = getPrefix(model, uri);
		if (prefix != null) {
			return prefix;
		}
		String s = findUnusedPrefix(model, defaultPrefix);

		// TODO: should create the taglib inside the IDOMModel
		Node[] ref = new Node[1];
		BodyHelper.findHeaderInsertPosition(IJMTConstants.URI_JSP, "taglib",
				model.getDocument(), ref);
		Element ele = model.getDocument().createElement("jsp:directive.taglib");
		((IDOMElement) ele).setJSPTag(true);
		ele.setAttribute(ICSSPropertyID.ATTR_URI, uri);
		ele.setAttribute(ICSSPropertyID.ATTR_PREFIX, s);
		if (nodes != null && nodes.length > 0) {
			nodes[0] = ele;
		}
		model.getDocument().insertBefore(ele, ref[0]);
		new HTMLFormatProcessorImpl().formatNode(ele);
		return s;
	}

	/**
	 * 
	 * @param model
	 * @param uri
	 * @return null means this is tld is not declared in the jsp file
	 */
	public static String getPrefix(IDOMModel model, String uri) {
		TLDCMDocumentManager m = TaglibController.getTLDCMDocumentManager(model
				.getStructuredDocument());
		if (m == null) {
			return null;
		}
		List trackers = m.getTaglibTrackers();
		for (Iterator iter = trackers.iterator(); iter.hasNext();) {
			TaglibTracker tracker = (TaglibTracker) iter.next();
			if (uri.equals(tracker.getURI())) {
				return tracker.getPrefix();
			} else {
				CMDocument cmdoc = tracker.getDocument();
				if (cmdoc instanceof TLDDocument
						&& uri.equals(((TLDDocument) cmdoc).getUri())) {
					return tracker.getPrefix();
				}
			}
		}
		return null;
	}

	/**
	 * create specified taglib declaration
	 * 
	 * @param model
	 * @param uri
	 * @param prefix
	 * @return
	 */
	public static Element createTaglibDeclaration(IDOMModel model, String uri,
			String prefix) {
		Node[] ref = new Node[1];
		BodyHelper.findHeaderInsertPosition(IJMTConstants.URI_JSP, "taglib",
				model.getDocument(), ref);
		Element ele = model.getDocument().createElement("jsp:directive.taglib");
		((IDOMElement) ele).setJSPTag(true);
		ele.setAttribute("uri", uri);
		ele.setAttribute("prefix", prefix);
		model.getDocument().insertBefore(ele, ref[0]);
		return ele;
	}

	public static String findUnusedPrefix(IDOMModel model, String suggestion) {
		if (suggestion == null) {
			suggestion = "p";
		}
		TLDCMDocumentManager m = TaglibController.getTLDCMDocumentManager(model
				.getStructuredDocument());
		if (m == null) {
			return suggestion;
		}
		List trackers = m.getTaglibTrackers();
		Set map = new HashSet();
		for (Iterator iter = trackers.iterator(); iter.hasNext();) {
			TaglibTracker tracker = (TaglibTracker) iter.next();
			map.add(tracker.getPrefix());
		}
		if (!map.contains(suggestion)) {
			return suggestion;
		}
		for (int i = 1;; i++) {
			if (!map.contains(suggestion + i)) {
				return suggestion + i;
			}
		}
	}

	/**
	 * given the prefix, find the corresponding jsp tld URI.
	 * 
	 * @param model
	 * @param prefix
	 * @return
	 */
	public static String findURIForPrefix(IDOMModel model, String prefix) {
		if (prefix == null || model == null) {
			return null;
		}
		TLDCMDocumentManager m = TaglibController.getTLDCMDocumentManager(model
				.getStructuredDocument());
		if (m == null) {
			return null;
		}
		List trackers = m.getTaglibTrackers();
		for (Iterator iter = trackers.iterator(); iter.hasNext();) {
			TaglibTracker tracker = (TaglibTracker) iter.next();
			if (prefix.equals(tracker.getPrefix())) {
				CMDocument cmdoc = tracker.getDocument();
				if (cmdoc instanceof TLDDocument) {
					return ((TLDDocument) cmdoc).getUri();
				} else {
					return null;
				}
			}
		}
		return null;
	}

	/**
	 * judge whether the the baseFile belonged project can support uri specified
	 * tag lib
	 * 
	 * @param uri
	 *            tag lib uri
	 * @param baseFile
	 * @return
	 */
	public static boolean supportTaglib(String uri, IFile baseFile) {
		IPath location = baseFile.getLocation();
		if (location != null) {
			return TaglibIndex.resolve(location.toString(), uri, false) != null;
		}
		return false;
	}
}
