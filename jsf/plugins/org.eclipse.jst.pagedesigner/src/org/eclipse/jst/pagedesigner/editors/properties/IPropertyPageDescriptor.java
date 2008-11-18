/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;
import org.eclipse.swt.widgets.Composite;

/**
 * Property descriptor for metadata enabled tag attributes in the WPE property pages.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * Not intended to be implemented by clients. 
 */
public interface IPropertyPageDescriptor extends IMetaDataEnabledFeature, IAdaptable{
	/**
	 * Trait id for defining quick edit tab sections.   Value must be of type qe:QuickEditTabSections
	 * eg. <value xsi:type="qe:QuickEditTabSections">
	 */
	public static final String QUICK_EDIT_TAB = "quick-edit-tab"; //$NON-NLS-1$
	/**
	 * Trait id for category name value
	 */
	public static final String PROP_DESC_CATEGORY = "category"; //$NON-NLS-1$
	
//	/**
//	 * Trait id for fully qualified cell editor class name
//	 */
//	public static final String PROP_DESC_CELL_EDITOR = "cell-editor";
//	/**
//	 * Trait id for fully qualified dialog field editor name
//	 */
//	public static final String PROP_DESC_DIALOG_FIELD_EDITOR = "dialog-field-editor";
	
	/**
	 * @return name of attribute.  Must not be null.
	 */
	public String getAttributeName();
	/**
	 * @return category name.  Must not be null.
	 */
	public String getCategory();
	/**
	 * @return label to use.  Must not be null.
	 */
	public String getLabel();
	/**
	 * @return description.   May return null.
	 */
	public String getDescription();
	/**
	 * @return whether this is a required property.   Default false.
	 */
	public boolean isRequired();
	/**
	 * @param parent
	 * @return cell editor to use.   May be null.
	 */
	public CellEditor getCellEditor(Composite parent);
	/**
	 * @return dialog field editor to use.   May be null.
	 */
	public DialogField getDialogFieldEditor();
	/**
	 * @return uri. Must not be null.
	 */
	public String getUri();
	/**
	 * @return tag name.  Must not be null.
	 */
	public String getTagName();
	/**
	 * @return fully qualified attribute-value-runtime-type as String
	 */
	public String getValueType();
}
