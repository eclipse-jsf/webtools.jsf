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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.common.IFileFolderConstants;
import org.eclipse.jst.pagedesigner.common.logging.Logger;
import org.eclipse.jst.pagedesigner.common.utils.PathUtil;
import org.eclipse.jst.pagedesigner.css2.property.ICSSPropertyID;
import org.eclipse.ui.IEditorInput;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 */
public class PreviewUtil {
	/** Create the logger for this class */
	private static Logger _log = PDPlugin.getLogger(PreviewUtil.class);

	/** line separator */
	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator"); //$NON-NLS-1$

	/** web root path */
	public static String WEBROOT_PATH = null;

	/** the file being previewed */
	public static IFile CURRENT_FILE = null;

	/** the property bundel */
	public static PropertyResourceBundle BUNDLE = null;

	/** the property bundel map used for loadbundle preview action */
	public static Map BUNDLE_MAP = null;

	/** the variable name used for loadbundel preview action */
	public static String VAR = null;

	/** key is prefix value is uri */
	private static Map _taglibMap = new HashMap();

	private static final String PAGE_EXTEND = "_jsppreview_.html"; //$NON-NLS-1$

	/**
	 * @return Returns the _taglibMap.
	 */
	public static Map getTaglibMap() {
		return _taglibMap;
	}

	/**
	 * @param map
	 *            The _taglibMap to set.
	 */
	public static void setTaglibMap(Map map) {
		_taglibMap = map;
	}

	/**
	 * Get tag attribute string from attribute map
	 * 
	 * @param map
	 *            tag attribute map
	 */
	public static String getAttributesAsString(Map map) {
		return getAttributesAsString(map, true);
	}

	/**
	 * Get file path from uri
	 * 
	 * @param uri
	 *            taglib uri
	 */
	public static String getPathFromURI(String uri) {
		if (uri == null) {
			return uri;
		}

		if (uri.startsWith(IFileFolderConstants.PATH_SEPARATOR)) {
			return PreviewUtil.WEBROOT_PATH + uri;
		}
		IFile curFile = PreviewUtil.CURRENT_FILE;
		if (curFile != null) {
			IContainer con = curFile.getParent();
			if (con != null) {
				IPath path = con.getLocation();
				if (path != null) {
					String aPath = path.toString() + File.separator + uri;

					aPath = aPath.replace('/', File.separatorChar);
					aPath = aPath.replace('\\', File.separatorChar);
					if (aPath.endsWith(File.separator)) {
						aPath += File.separator;
					}
					File file = new File(aPath);
					if (file.exists()) {
						return aPath;
					} else {
						return uri;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Get bundle value for expression
	 * 
	 * @param attrValue
	 *            expression
	 */
	public static String getValueOFEP(String attrValue) {
		if (attrValue != null) {
			if (attrValue.startsWith("#{")) //$NON-NLS-1$
			{
				String key, value = null;
				int i = attrValue.lastIndexOf("."); //$NON-NLS-1$
				if (i > 0) {
					key = attrValue.substring(i + 1, attrValue.length() - 1)
							.trim();
					String bundleVariable = attrValue.substring(2, i).trim();
					if (BUNDLE_MAP == null) {
						return attrValue; //$NON-NLS-1$
					}
					PropertyResourceBundle bundle = (PropertyResourceBundle) BUNDLE_MAP
							.get(bundleVariable);

					if (bundle != null) {
						try {
							value = bundle.getString(key);
							if (value != null) {
								return value;
							} else {
								return attrValue; //$NON-NLS-1$
							}
						} catch (MissingResourceException e1) {
							// "Error in resource bundle processing:"
							_log.info("PreviewUtil.Error.0", e1); //$NON-NLS-1$
						}
					}
				}
			}
		}
		return attrValue;
	}

	/**
	 * Get tag attribute string from attribute map
	 * 
	 * @param map
	 *            tag attribute map
	 * @param flag
	 *            state
	 */
	public static String getAttributesAsString(Map map, boolean flag) {
		if (map == null) {
			return null;
		}

		StringBuffer stringbuffer = new StringBuffer();
		for (Iterator e = map.keySet().iterator(); e.hasNext();) {
			String attrName = (String) e.next();
			String attrValue = (String) map.get(attrName);
			attrValue = getValueOFEP(attrValue);
			if (ICSSPropertyID.ATTR_SRC.equalsIgnoreCase(attrName)
					|| ICSSPropertyID.ATTR_HREF.equalsIgnoreCase(attrName)
					|| ICSSPropertyID.ATTR_URI.equalsIgnoreCase(attrName)
					|| ICSSPropertyID.ATTR_BINDING.equalsIgnoreCase(attrName)
					|| ICSSPropertyID.ATTR_PAGE.equalsIgnoreCase(attrName)) {
				if (PreviewUtil.WEBROOT_PATH != null && attrValue != null
						&& !attrValue.startsWith("http") //$NON-NLS-1$
						&& !attrValue.startsWith("file")) //$NON-NLS-1$
				{
					attrValue = getPathFromURI(attrValue);
				}
			}
			if (attrValue != null) {
				stringbuffer.append(" ").append(attrName); //$NON-NLS-1$
				if (attrValue.indexOf(34) != -1) {
					StringBuffer stringbuffer1 = new StringBuffer();
					for (int j = 0; j < attrValue.length(); j++) {
						char c = attrValue.charAt(j);
						if (c != '"') {
							stringbuffer1.append(c);
						}
					}

					attrValue = stringbuffer1.toString();
				}
				if (attrValue != null && attrValue.startsWith("#{")) //$NON-NLS-1$
				{
					attrValue = ""; //$NON-NLS-1$
				}
				stringbuffer.append("=\"").append(attrValue).append('"'); //$NON-NLS-1$
			}
		}
		// System.out.println("BBBB:" +stringbuffer.toString());

		return stringbuffer.toString();
	}

	/**
	 * Change NamedNodeMap type to Map type
	 * 
	 * @param nodeMap
	 *            NamedNodeMap type
	 */
	public static Map getAttributeMap(NamedNodeMap nodeMap) {
		if (nodeMap != null) {
			int len = nodeMap.getLength();
			HashMap map = new HashMap();
			for (int i = 0; i < len; i++) {
				Node node = nodeMap.item(i);
				String name = node.getNodeName();
				String value = node.getNodeValue();
				if (name != null
						&& !name.trim().equalsIgnoreCase("") && value != null) //$NON-NLS-1$
				{
					map.put(name, value);
				}
			}
			return map;
		}
		return null;
	}

	/**
	 * @param result
	 * @param editorInput
	 * @return
	 */
	public static File toFile(StringBuffer result, IEditorInput editorInput) {
		try {
			File file = File.createTempFile("previewtmp", ".html"); //$NON-NLS-1$ //$NON-NLS-2$
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos, true, "UTF-8"); //$NON-NLS-1$
			ps.print(result.toString());
			ps.close();
			fos.close();
			return file;
		} catch (IOException e) {
			// "Error in file open:"
			_log.info("PreviewUtil.Error.3", e); //$NON-NLS-1$
			return null;
		}
	}

	/**
	 * do preivew on Node recursively translate escape char for Node and Node's
	 * child translate relative path for Node and Node's child
	 * 
	 * @param node
	 *            root node that will be previewed
	 */
	public static void previewNode(Node node) {
		if (node == null) {
			return;
		}
		NodeList nodeList = node.getChildNodes();
		if (nodeList == null) {
			return;
		}
		NamedNodeMap attrMap = node.getAttributes();

		if (attrMap != null) {
			for (int i = 0, n = attrMap.getLength(); i < n; i++) {
				Node attrNode = attrMap.item(i);
				if (attrNode != null && attrNode instanceof Attr) {
					Attr attr = (Attr) attrNode;
					attr.setNodeValue(getValueOFEP(attr.getNodeValue()));
					attr.setNodeValue(PathUtil.convertToAbsolutePath(attr
							.getNodeValue(), null));
					StringBuffer buf = new StringBuffer();
					String attrValue = attr.getNodeValue();
				}
			}
		}
		for (int i = 0, n = nodeList.getLength(); i < n; i++) {
			previewNode(nodeList.item(i));
		}
	}

	// /**
	// * handle escape attebute of tag
	// *
	// * @param node
	// * @return
	// */
	// public static boolean escapeFoeNode(Node node)
	// {
	// if (node == null)
	// {
	// return false;
	// }
	// NamedNodeMap attrMap = node.getAttributes();
	// if (attrMap != null)
	// {
	// for (int i = 0, n = attrMap.getLength(); i < n; i++)
	// {
	// Node attrNode = attrMap.item(i);
	// if (attrNode != null && attrNode instanceof Attr)
	// {
	// if ("escape".equalsIgnoreCase(attrNode.getNodeName())
	// && "true".equalsIgnoreCase(attrNode.getNodeValue()))
	// {
	// return true;
	// }
	// }
	// }
	// }
	// return false;
	// }

}
