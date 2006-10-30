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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemDescriptor;

/**
 * @author mengbo
 */
public class PaletteItemCategory implements IPaletteItemCategory {
	private String _id;

	private String _uri;

	private String _label;

	private String _description;

	private boolean _isVisible = true;

	private List _descriptors = new ArrayList();

	private String _prefix;

	private PaletteEntry _entry;

	private ImageDescriptor _smallIcon;

	private ImageDescriptor _largeIcon;

	private String _smallIconString;

	private String _largeIconString;

	private int _initState = PaletteDrawer.INITIAL_STATE_CLOSED;

	private boolean _jsfComponentCategory = false;

	/**
	 * 
	 */
	public PaletteItemCategory(String uri, String label) {
		_uri = uri;
		_label = label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory#getCategoryLabel()
	 */
	public String getCategoryLabel() {
		return _label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory#getPaletteItems()
	 */
	public List getPaletteItems() {
		if (_descriptors != null) {
			Collections.sort(_descriptors, new Comparator() {
				public int compare(Object o1, Object o2) {
					if (o1 != null && o2 != null) {
						String label1 = ((IPaletteItemDescriptor) o1)
								.getLabel();
						if (label1 == null) {
							label1 = ((IPaletteItemDescriptor) o1).getTagName();
						}
						String label2 = ((IPaletteItemDescriptor) o2)
								.getLabel();
						if (label2 == null) {
							label2 = ((IPaletteItemDescriptor) o2).getTagName();
						}
						if (label1 != null && label2 != null) {
							int cc = label2.compareTo(label1);
							return (cc < 0 ? 1 : cc > 0 ? -1 : 0);
						}
					}
					return -1;
				}
			});
		}
		return _descriptors;
	}

	/**
	 * @param item
	 */
	public void addPaletteItem(IPaletteItemDescriptor item) {
		_descriptors.add(item);
		// TODO: fire change event.
	}

	/**
	 * @return
	 */
	public String getURI() {
		return _uri;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory#getItemByTagName(java.lang.String)
	 */
	public IPaletteItemDescriptor getItemByTagName(String tagName) {
		if (_descriptors == null) {
			return null;
		}
		for (int i = 0, n = _descriptors.size(); i < n; i++) {
			IPaletteItemDescriptor item = (IPaletteItemDescriptor) _descriptors
					.get(i);
			if (item != null && tagName.equals(item.getTagName())) {
				return item;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory#getItemByID(java.lang.String)
	 *      id format [uri:tagname:label] example : [html:input:Radio Button]
	 */
	public IPaletteItemDescriptor getItemByID(String id) {
		if (_descriptors == null) {
			return null;
		}
		for (int i = 0, n = _descriptors.size(); i < n; i++) {
			IPaletteItemDescriptor item = (IPaletteItemDescriptor) _descriptors
					.get(i);
			if (item != null && id.equals(item.getId())) {
				return item;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory#createItem(java.lang.String)
	 */
	public IPaletteItemDescriptor createItem(String tagName) {
		IPaletteItemDescriptor item = new PaletteItemDescriptor();
		item.setTagName(tagName);
		item.setJSFComponent(this.isJSFComponentCategory());
		this.addPaletteItem(item);
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory#setCategoryLabel(java.lang.String)
	 */
	public void setCategoryLabel(String displayName) {
		this._label = displayName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory#setDefaultPrefix(java.lang.String)
	 */
	public void setDefaultPrefix(String prefix) {
		this._prefix = prefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory#getDefaultPrefix()
	 */
	public String getDefaultPrefix() {
		return _prefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory#getPaletteEntry()
	 */
	public PaletteEntry getPaletteEntry() {
		return _entry;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory#setPaletteEntry(org.eclipse.gef.palette.PaletteEntry)
	 */
	public void setPaletteEntry(PaletteEntry entry) {
		this._entry = entry;
		/*
		 * if (entry.getDescription()==null && this.getDescription()!=null) {
		 * entry.setDescription(this.getDescription()); } if
		 * (entry.getId()==null && this.getId()!=null) {
		 * entry.setId(this.getId()); } if (entry.getLabel()==null &&
		 * this.getLabel()!=null) { entry.setLabel(this.getLabel()); }
		 * 
		 * entry.setVisible(this.isVisible());
		 */

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemEntry#getId()
	 */
	public String getId() {
		if (_id == null) {
			_id = _uri;
		}
		return _id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemEntry#setId(java.lang.String)
	 */
	public void setId(String id) {
		this._id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemEntry#getLabel()
	 */
	public String getLabel() {
		return _label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemEntry#setlabel(java.lang.String)
	 */
	public void setLabel(String label) {
		this._label = label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemEntry#getDescription()
	 */
	public String getDescription() {
		return _description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemEntry#setDescription(java.lang.String)
	 */
	public void setDescription(String desc) {
		this._description = desc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemEntry#isVisible()
	 */
	public boolean isVisible() {
		return _isVisible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemEntry#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		this._isVisible = visible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemDescriptor#getSmallIconString()
	 */
	public String getSmallIconString() {
		return this._smallIconString;
	}

	public void setSmallIconString(String icon) {
		this._smallIconString = icon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemDescriptor#getLargeIconString()
	 */
	public String getLargeIconString() {
		return this._largeIconString;
	}

	public void setLargeIconString(String icon) {
		this._largeIconString = icon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemDescriptor#getSmallIcon()
	 */
	public ImageDescriptor getSmallIcon() {
		return this._smallIcon;
	}

	public void setSmallIcon(ImageDescriptor icon) {
		this._smallIcon = icon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemDescriptor#getLargeIcon()
	 */
	public ImageDescriptor getLargeIcon() {
		return this._largeIcon;
	}

	public void setLargeIcon(ImageDescriptor icon) {
		this._largeIcon = icon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemEntry#setInitialState(int)
	 */
	public void setInitialState(int state) {
		_initState = state;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemEntry#getInitialState()
	 */
	public int getInitialState() {
		return _initState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemCategory#isJSFComponentCategory()
	 */
	public boolean isJSFComponentCategory() {
		return this._jsfComponentCategory;
	}

	public void setJSFComponentCategory(boolean b) {
		this._jsfComponentCategory = b;
	}
}
