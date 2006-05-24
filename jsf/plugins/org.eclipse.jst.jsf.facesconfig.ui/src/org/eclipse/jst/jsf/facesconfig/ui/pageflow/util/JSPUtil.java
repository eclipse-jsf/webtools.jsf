/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsp.core.internal.contentmodel.TaglibController;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TLDCMDocumentManager;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TaglibTracker;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;

/**
 * utility class for JSP related information.
 * 
 * @author Yang Liu
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
		String prefix = getPrefix(model, uri);
		if (prefix != null)
			return prefix;
		String s = findUnusedPrefix(model, defaultPrefix);

		// TODO: should create the taglib inside the IDOMModel
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
		if (m == null)
			return null;
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

	public static String findUnusedPrefix(IDOMModel model, String suggestion) {
		if (suggestion == null)
			suggestion = "p";
		TLDCMDocumentManager m = TaglibController.getTLDCMDocumentManager(model
				.getStructuredDocument());
		if (m == null)
			return suggestion;
		List trackers = m.getTaglibTrackers();
		Set map = new HashSet();
		for (Iterator iter = trackers.iterator(); iter.hasNext();) {
			TaglibTracker tracker = (TaglibTracker) iter.next();
			map.add(tracker.getPrefix());
		}
		if (!map.contains(suggestion))
			return suggestion;
		for (int i = 1;; i++) {
			if (!map.contains(suggestion + i))
				return suggestion + i;
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
		if (prefix == null || model == null)
			return null;
		TLDCMDocumentManager m = TaglibController.getTLDCMDocumentManager(model
				.getStructuredDocument());
		if (m == null)
			return null;
		List trackers = m.getTaglibTrackers();
		for (Iterator iter = trackers.iterator(); iter.hasNext();) {
			TaglibTracker tracker = (TaglibTracker) iter.next();
			if (prefix.equals(tracker.getPrefix())) {
				CMDocument cmdoc = tracker.getDocument();
				if (cmdoc instanceof TLDDocument) {
					return ((TLDDocument) cmdoc).getUri();
				} else
					return null;
			}
		}
		return null;
	}
	
	/**
	 * get the action list in the jsp file
	 * 
	 * @return - action list
	 */
	public static List getActionListInJSPFile(String jspFileName) {
		/** jsp dom adapter */
		JSPDomAdapter jspAdapter;

		List actions = new ArrayList();
		jspAdapter = new JSPDomAdapter();
		// convert the relative directory to project directory, e.g., /a.jsp to
		// /testproject/webroot/a.sjp
		String physicalJspPath = jspFileName;
		if (physicalJspPath != null && physicalJspPath.length() > 0) {
			IPath jspPath = new Path(physicalJspPath);
			IFile jspFile = ResourcesPlugin.getWorkspace().getRoot().getFile(
					jspPath);

			if (jspFile != null && jspFile.exists()) {
				// initialize the adapter to initialize the model of jsp
				if (jspAdapter.initialize(jspFile)) {
					// the prefix of JSF HTML TagLib
					String prefix = jspAdapter
							.getTagLibPrefix(JSPDomAdapter.JSF_HTML_TAGLIB);

					// get the command butonns
					List buttonActions = jspAdapter.getElementsByTagNameNS(
							prefix, "commandButton");//$NON-NLS-1$
					if (buttonActions != null) {
						actions.addAll(buttonActions);
					}

					// get the command links
					List linkActions = jspAdapter.getElementsByTagNameNS(
							prefix, "commandLink");//$NON-NLS-1$
					if (linkActions != null) {
						actions.addAll(linkActions);
					}
				}
			}
		}
		jspAdapter.releaseModel();
		return actions;
	}
}
