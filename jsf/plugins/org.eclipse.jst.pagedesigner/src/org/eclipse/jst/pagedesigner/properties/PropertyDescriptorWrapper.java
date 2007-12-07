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
package org.eclipse.jst.pagedesigner.properties;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jst.pagedesigner.meta.EditorCreator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * This is a simple PropertyDescriptor wrapper. It simply changed the category
 * name. This is because the default category name is "Attributes", which will
 * make them appear before other things, and that is not what we want.
 * 
 * @author mengbo
 */
public class PropertyDescriptorWrapper implements IPropertyDescriptor {

	private IPropertyDescriptor _inner;

	private IDOMElement _element;

	private IStatusLineManager _statusLineManager;

//	/**
//	 * Constructor
//	 * @param element 
//	 * @param innerDescriptor 
//	 * @param statusLineManager 
//	 * 
//	 */
//	public PropertyDescriptorWrapper(IDOMElement element,
//			IPropertyDescriptor innerDescriptor, IStatusLineManager statusLineManager) {
//		this._element = element;
//		this._inner = innerDescriptor;
//		this._statusLineManager = statusLineManager;
//	}

	/**
	 * Constructor
	 * @param element
	 * @param innerDescriptor
	 */
	public PropertyDescriptorWrapper(IDOMElement element,
			IPropertyDescriptor innerDescriptor) {
		this._element = element;
		this._inner = innerDescriptor;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	public CellEditor createPropertyEditor(Composite parent) {
		EditorCreator.CellEditorHolder holder = new EditorCreator.CellEditorHolder() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jst.pagedesigner.meta.EditorCreator.CellEditorHolder#createCellEditor(org.eclipse.swt.widgets.Composite)
			 */
			public CellEditor createCellEditor(Composite parent1) {
				CellEditor ed = null;
				if (_inner != null) 
					ed = _inner.createPropertyEditor(parent1);
				else
					ed = new TextCellEditor(parent1);
				
				if (_statusLineManager != null)
					ed.addListener(new StatusBarUpdater(ed));
				return ed;
			}
		};
		return EditorCreator.getInstance().createCellEditorWithWrapper(parent,
				null, holder, _element, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getCategory()
	 */
	public String getCategory() {
		String cat = _inner.getCategory();
		return cat != null ? cat : ITabbedPropertiesConstants.OTHER_CATEGORY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getDescription()
	 */
	public String getDescription() {
		return _inner.getDescription();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getDisplayName()
	 */
	public String getDisplayName() {
		return _inner.getDisplayName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getFilterFlags()
	 */
	public String[] getFilterFlags() {
		return _inner.getFilterFlags();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getHelpContextIds()
	 */
	public Object getHelpContextIds() {
		return _inner.getHelpContextIds();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getId()
	 */
	public Object getId() {
		return _inner.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#getLabelProvider()
	 */
	public ILabelProvider getLabelProvider() {
		return _inner.getLabelProvider();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertyDescriptor#isCompatibleWith(org.eclipse.ui.views.properties.IPropertyDescriptor)
	 */
	public boolean isCompatibleWith(IPropertyDescriptor anotherProperty) {
		if (anotherProperty instanceof PropertyDescriptorWrapper) {
			return _inner
					.isCompatibleWith(((PropertyDescriptorWrapper) anotherProperty)
							.getInner());
		}
		return _inner.isCompatibleWith(anotherProperty);
	}

	/**
	 * @return IPropertyDescriptor
	 */
	public IPropertyDescriptor getInner() {
		return _inner;
	}

	private class StatusBarUpdater implements ICellEditorListener {
		private CellEditor ed;
	
		StatusBarUpdater(CellEditor ed) {
			this.ed = ed;
		}
		public void applyEditorValue() {//
		}
		public void cancelEditor() {//
		}

		public void editorValueChanged(boolean oldValidState,
				boolean newValidState) {
			if (!newValidState)
				_statusLineManager.setErrorMessage(ed.getErrorMessage());				
			else
				_statusLineManager.setErrorMessage(null);
				
		}
		
	}
}
