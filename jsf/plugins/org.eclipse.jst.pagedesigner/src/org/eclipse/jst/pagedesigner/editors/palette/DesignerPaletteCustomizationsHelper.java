/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.palette;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;

import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.ui.palette.PaletteCustomizer;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.customize.PaletteCustomizerDialog;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.jst.pagedesigner.utils.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Helper class that all clients should use when dealing with palette customizations 
 */
public final class DesignerPaletteCustomizationsHelper {
	private static final String PREFS_TAGLIBHIDE_PREFIX = "hidden_taglib:"; //$NON-NLS-1$

	private static Logger _log = PDPlugin.getLogger(DesignerPaletteCustomizationsHelper.class);
	
	/**
	 * @return new DesignerPaletteCustomizer
	 */
	public static DesignerPaletteCustomizer getNewCustomizer(){
		return new DesignerPaletteCustomizer();
	}
	
	/**
	 * @param paletteViewer
	 * @param paletteCustomizer
	 * @return new DesignerPaletteCustomizerDialog
	 */
	public static PaletteCustomizerDialog getNewCustomizerDialog(PaletteViewer paletteViewer, PaletteCustomizer paletteCustomizer){
		return new DesignerPaletteCustomizerDialog(
				paletteViewer.getControl().getShell(), paletteCustomizer, (DesignerPaletteRoot)paletteViewer.getPaletteRoot());

	}
	
	/**
	 * Save palette item customizations
	 * @param paletteRoot
	 */
	public static void save(DesignerPaletteRoot paletteRoot) {
		IPersistentPreferenceStore prefs = getPreferenceStore();

		for (Iterator it=paletteRoot.getChildren().iterator();it.hasNext();){
			PaletteEntry entry = (PaletteEntry)it.next();
			if (entry instanceof TaglibPaletteDrawer){
				TaglibPaletteDrawer pd = (TaglibPaletteDrawer)entry;
				prefs.putValue(PREFS_TAGLIBHIDE_PREFIX + pd.getURI(), Boolean.toString(! pd.isVisible()));
			}
		}
		PaletteItemManager.notifyPaletteItemManagersOfCustomizationsUpdate(paletteRoot.getPaletteManager());
		
	}
	
	/**
	 * Load user customizations
	 * @param paletteItemManager
	 */
	public static void loadUserCustomizations(IPaletteItemManager paletteItemManager) {
		IPreferenceStore store = getPreferenceStore();
		for (Iterator it=paletteItemManager.getAllCategories().iterator();it.hasNext();){
			TaglibPaletteDrawer tld = (TaglibPaletteDrawer)it.next();
			if (store.contains(PREFS_TAGLIBHIDE_PREFIX+tld.getURI())){
				tld.setVisible( ! store.getBoolean(PREFS_TAGLIBHIDE_PREFIX+tld.getURI()));
			}
		}
	}
	
	/**
	 * Hide a category (TaglibPaletteDrawer) that will be persisted as a customization
	 * @param taglibPaletteDrawer
	 */
	public static void hideTaglibDrawer(TaglibPaletteDrawer taglibPaletteDrawer){
		IPersistentPreferenceStore prefs = getPreferenceStore();
		taglibPaletteDrawer.setVisible(false);
		prefs.putValue(PREFS_TAGLIBHIDE_PREFIX + taglibPaletteDrawer.getURI(), Boolean.TRUE.toString());
		save((DesignerPaletteRoot)taglibPaletteDrawer.getParent());
	}
	
	private static IPersistentPreferenceStore getPreferenceStore() {
		return (IPersistentPreferenceStore)PDPlugin.getDefault().getPreferenceStore();
	}
	
	/**
	 * Exports the current state of customizations of the current palette instance.
	 * 
	 * Unsaved data will be exported.
	 * 
	 * Currently only the "hide" flag of a category is exported.  All categories are exported.
	 * 
	 * @param paletteItemManager
	 * @param filename
	 */
	public static void exportCustomizations(IPaletteItemManager paletteItemManager, String filename){

		Document document = XMLUtil.getDocumentBuilder().getDOMImplementation()
			.createDocument(null, IPaletteConstants.ROOT, null);
		try {
			FileOutputStream ostream = null;
			int index = filename.lastIndexOf(File.separator);
			String foldername = filename.substring(0, index); 
			File folder = new File(foldername);
			if (folder != null && !folder.exists()) {
				folder.mkdir();
			}
			
			ostream = new FileOutputStream(filename);

			for (Iterator iter = paletteItemManager.getAllCategories().iterator(); iter.hasNext();) {
				TaglibPaletteDrawer category = (TaglibPaletteDrawer) iter
						.next();	
				if (category == null) continue;

				Element categoryElement = document.createElement(IPaletteConstants.CATEGORY_TAG);
				categoryElement.setAttribute(IPaletteConstants.ID, category.getURI());
				categoryElement.setAttribute(IPaletteConstants.ISHIDDEN, Boolean.toString(! category.isVisible()));
				document.getDocumentElement().appendChild(categoryElement);
			}
			XMLUtil.serialize(document, ostream);
			ostream.close();
		} catch (IOException e) {
			_log.error("DesignerPaletteCustomizationsHelper.save.error.IOException", e); //$NON-NLS-1$
		}
	}
	
	/**
	 * Imports a palette customization export file.
	 * 
	 * If there are entries matching in the existing workspace customizations, they will be overridden.
	 * 
	 * @param customizer 
	 * @param filename
	 */
	public static void importCustomizations(DesignerPaletteCustomizer customizer, String filename){
		Document document = null;
		try {
			DocumentBuilder builder = XMLUtil.getDocumentBuilder();
			if (builder != null) {
				document = builder.parse(filename);
			} else {
				_log.error("DesignerPaletteCustomizationsHelper.importCustomizations.error.getDocumentBuilderFail");// $NON-NLS-1$ //$NON-NLS-1$
			}
		} catch (FileNotFoundException e) {
			//should never get here as user chose file thru a file chooser dialog
			_log.error(
					"DesignerPaletteCustomizationsHelper.importCustomizations.error.FileNotFoundException");// $NON-NLS-1$ //$NON-NLS-1$
			document = null;
		} catch (IOException e) {
			_log.error(
					"DesignerPaletteCustomizationsHelper.importCustomizations.error.IOException", e.toString(), e); //$NON-NLS-1$
		} catch (SAXException e) {
			_log.error(
					"DesignerPaletteCustomizationsHelper.importCustomizations.error.SAXException", e.toString(), e); //$NON-NLS-1$
		}
		if (document != null) {
			Element root = document.getDocumentElement();
			if (root != null) {
				NodeList catetorylist = root.getChildNodes();
				for (int i = 0, n = catetorylist.getLength(); i < n; i++) {
					Node childNode = catetorylist.item(i);
					if (childNode.getNodeType() == Node.ELEMENT_NODE
							&& childNode.getNodeName().equals(
									IPaletteConstants.CATEGORY_TAG)) {
						
						Element categoryElement = (Element) childNode;
						//only checking for hidden flag currently
						if (categoryElement.hasAttribute(IPaletteConstants.ISHIDDEN)){
							boolean isHidden =Boolean.valueOf(categoryElement.getAttribute(IPaletteConstants.ISHIDDEN)).booleanValue();
							
							String uri = categoryElement.getAttribute(IPaletteConstants.ID);
							applyImport(customizer, uri, isHidden);							
						}
						
						
					}
				}
			}
		}
	}

	private static void applyImport(
			DesignerPaletteCustomizer customizer, String uri, boolean isHidden) {
		//apply the isHidden exported flag to the paletteRoot child if present 
		for (Iterator it=customizer.getDesignerPaletteRoot().getChildren().iterator();it.hasNext();){
			Object o = it.next();
			if (o instanceof TaglibPaletteDrawer){
				TaglibPaletteDrawer pd = (TaglibPaletteDrawer)o;
				if (uri.equals(pd.getURI())){
					pd.setVisible(! isHidden);
					break;
				}
			}
		}	
	}

}
