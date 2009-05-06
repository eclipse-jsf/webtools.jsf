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
package org.eclipse.jst.pagedesigner.editors.palette.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;
import org.eclipse.jst.pagedesigner.editors.palette.DesignerPaletteCustomizationsHelper;
import org.eclipse.jst.pagedesigner.editors.palette.IEntryChangeListener;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteConstants;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemManager;
import org.eclipse.wst.html.core.internal.contentmodel.HTMLCMDocumentFactory;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;

/**
 * TODO: C.B.: parts of this class need a re-write.  This is very complex.
 *  
 * @author mengbo
 */
public class PaletteItemManager implements IPaletteItemManager,
		IPaletteConstants {
	
	private static Map _managers = new HashMap();
	private List _categories = new ArrayList();
	
	// if _curProject is null, then is the default manager.
	private IProject _curProject = null;
	private IEntryChangeListener[] _listeners;

	// the default manager is for those _curProject == null
	private static PaletteItemManager _defaultManager = null;
	private static PaletteItemManager _currentInstance;


	/**
	 * Return singleton paletteItemManager for a given project
	 * @param project
	 * @return PaletteItemManager
	 */
	public static PaletteItemManager getInstance(final IProject project) {
		if (project == null) {
			// sometimes when the editor is editing a file in jar file, may not
			// be able to
			// get the project.
			return getDefaultPaletteItemManager();
		}
		
		synchronized (_managers) {
			PaletteItemManager manager = (PaletteItemManager) _managers
					.get(project);
			if (manager == null) {
				manager = new PaletteItemManager(project);
				_managers.put(project, manager);
			}
			_currentInstance = manager;
			return manager;
		}

	}
	
	/**
	 * @return current PaletteItemManager instance.  May return null
	 */
	public static synchronized PaletteItemManager getCurrentInstance(){
		return _currentInstance;
	}

	private IProject getCurProject() {
		return _curProject;
	}

	/**
	 * Removes an intstance of a paletteItemManager from the system
	 * @param manager
	 */
	public static void removePaletteItemManager(
			final PaletteItemManager manager) {
		synchronized(_managers) {
			_managers.remove(manager.getCurProject());
		}
	}


	/**
	 * 
	 */
	public static void clearPaletteItemManager() {
		synchronized(_managers) {
			_managers.clear();
		}
	}

	/**
	 * @return PaletteItemManager for when no project case
	 */
	private synchronized static PaletteItemManager getDefaultPaletteItemManager() {
		if (_defaultManager == null) {
			_defaultManager = new PaletteItemManager(null);
			
		}
		return _defaultManager;
	}

	/**
	 * 
	 */
	private PaletteItemManager(final IProject project) {
		_curProject = project;

		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.pagedesigner.editors.palette.IPaletteItemManager#getAllCategories()
	 */
	public synchronized List getAllCategories() {
		return _categories;
	}

	/**
	 * Initializes the palette items for the current project
	 */
	protected synchronized void init() {
		getAllCategories().clear();
		initFromProject(_curProject);

		DesignerPaletteCustomizationsHelper.loadUserCustomizations(this);
		
		sortCategories();
		
	}

	private void sortCategories() {
		//note that once we store ordering customizations, we will need to do something different
		Collections.sort(getAllCategories(), new Comparator(){

			public int compare(Object o1, Object o2) {
				String label1 = ((PaletteEntry)o1).getLabel();
				String label2 = ((PaletteEntry)o2).getLabel();
				
				return label1.compareTo(label2);
			}
			
		});

	}

	/**
	 * Reinitializes the palatteItemManager and informs all palette roots that use the manager to refresh
	 */
	public void reset() {
		init();
		fireModelChanged(null, null);
	}

	private void initFromProject(final IProject project) {
		registerHTMLCategory();
		registerJSPCategory();
		registerTldFromClasspath(project);
	}

	private void registerHTMLCategory() {
		CMDocument doc = HTMLCMDocumentFactory.getCMDocument(CMDocType.HTML_DOC_TYPE);
		PaletteHelper.getOrCreateTaglibPaletteDrawer(this, doc, CMDocType.HTML_DOC_TYPE, getCurProject());
	}

	private void registerJSPCategory() {
		CMDocument doc = HTMLCMDocumentFactory.getCMDocument(CMDocType.JSP11_DOC_TYPE);
		PaletteHelper.getOrCreateTaglibPaletteDrawer(this, doc, CMDocType.JSP11_DOC_TYPE, getCurProject());
	}

	/**
	 * Search Classpath entry list to find if the entry is jar libraray and the
	 * libray have the tld descriptor, if have ,build a palette category mapping
	 * the tld descriptor.
	 * 
	 * @param project
	 */
	private void registerTldFromClasspath(final IProject project) {
		if (project != null) {
			ITaglibRecord[] tldrecs = TaglibIndex.getAvailableTaglibRecords(project.getFullPath());
			for (int i=0;i<tldrecs.length;i++){				
				PaletteHelper.configPaletteItemsByTLD(this, getCurProject(), tldrecs[i]);			
			}
		}			
	}

	/**
	 * @param id (most likely the uri)
	 * @param label 
	 * @return TaglibPaletteDrawer
	 */
	public TaglibPaletteDrawer findOrCreateCategory(final String id, final String label) {
		TaglibPaletteDrawer category;
		for (Iterator iter = getAllCategories().iterator(); iter.hasNext();) {
			category = (TaglibPaletteDrawer) iter.next();
			if (id.equals(category.getId())) {
				return category;
			}
		}
		category = createTaglibPaletteDrawer(id, label);
		return category;
	}

	/**
	 * @param uri
	 * @return TaglibPaletteDrawer
	 */
	public TaglibPaletteDrawer findCategoryByURI(final String uri) {
		TaglibPaletteDrawer category;
		for (Iterator iter = getAllCategories().iterator(); iter.hasNext();) {
			category = (TaglibPaletteDrawer) iter.next();
			if (uri.equals(category.getURI())) {
				return category;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.pagedesigner.editors.palette.IPaletteItemManager#createCategory(java.lang.String)
	 */
	public TaglibPaletteDrawer createTaglibPaletteDrawer(final String uri, final String label) {
		TaglibPaletteDrawer r = new TaglibPaletteDrawer(uri, label);
		getAllCategories().add(r);
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.pagedesigner.editors.palette.IPaletteItemManager#getCategoryByURI(java.lang.String)
	 */
	public TaglibPaletteDrawer getTaglibPalletteDrawer(final String uri) {
		for (Iterator iter = getAllCategories().iterator(); iter.hasNext();) {
			TaglibPaletteDrawer cat = (TaglibPaletteDrawer) iter.next();
			if (uri.equals(cat.getId())) {
				return cat;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.pagedesigner.editors.palette.IPaletteItemManager#addEntryChangeListener(com.sybase.stf.jmt.pagedesigner.editors.palette.IEntryChangeListener)
	 */
	public void addEntryChangeListener(final IEntryChangeListener listener) {

		if (_listeners == null) {
			_listeners = new IEntryChangeListener[] { listener };
		} else {
			IEntryChangeListener[] newListeners = new IEntryChangeListener[_listeners.length + 1];
			newListeners[0] = listener;
			System.arraycopy(_listeners, 0, newListeners, 1, _listeners.length);
			_listeners = newListeners;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.pagedesigner.editors.palette.IPaletteItemManager#removeEntryChangeListener(com.sybase.stf.jmt.pagedesigner.editors.palette.IEntryChangeListener)
	 */
	public void removeEntryChangeListener(final IEntryChangeListener listener) {
		if (_listeners == null) {
			return;
		}
		if (_listeners.length == 1) {
			_listeners = null;
		} else {
			List newListenersList = new ArrayList(Arrays.asList(_listeners));
			newListenersList.remove(listener);
			IEntryChangeListener[] newListeners = new IEntryChangeListener[newListenersList
					.size() - 1];
			newListeners = (IEntryChangeListener[]) newListenersList
					.toArray(newListeners);
			_listeners = newListeners;
		}
	}

	/**
	 * Notify model change event
	 * 
	 * @param oldDefinitions
	 * @param newDefinitions
	 */
	private void fireModelChanged(final List oldDefinitions, final List newDefinitions) {
		if (_listeners == null) {
			return;
		}
		synchronized (_listeners) {
			for (int i = 0; i < _listeners.length; i++) {
				_listeners[i].modelChanged(oldDefinitions, newDefinitions);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemManager#getProject()
	 */
	public IProject getProject() {
		return getCurProject();
	}
	
	/**
	 * Informs all paletteItemManagers, except the notifying paletteManager, of updates to the customizations
	 * All palette viewer roots will be notifed of possible updates
	 * @param notifyingManager 
	 */
	public static void notifyPaletteItemManagersOfCustomizationsUpdate(final IPaletteItemManager notifyingManager){
		synchronized (_managers) {
			for (Iterator it=_managers.values().iterator();it.hasNext();){
				PaletteItemManager mgr = (PaletteItemManager)it.next();
				if (mgr != null && notifyingManager != mgr)
					mgr.reset();
			}
		}
	}
}
