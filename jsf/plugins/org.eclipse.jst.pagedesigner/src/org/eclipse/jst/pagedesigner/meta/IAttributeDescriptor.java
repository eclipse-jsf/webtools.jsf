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
package org.eclipse.jst.pagedesigner.meta;

import java.util.Map;

/**
 * 
 * @author mengbo
 * @deprecated
 */
public interface IAttributeDescriptor {
	/**
	 * suffix
	 */
	final String PARAMETER_SUFFIX = "suffix"; //$NON-NLS-1$

	/**
	 * style
	 */
	final String PARAMETER_STYLE = "style"; //$NON-NLS-1$

	/**
	 * super type
	 */
	final String PARAMETER_SUPER_TYPE = "superType"; //$NON-NLS-1$

	/**
	 * separator
	 */
	final String PARAMETER_SEPARATOR = "separator"; //$NON-NLS-1$

	/**
	 * default
	 */
	final String PARAMETER_DEFAULT = "default"; //$NON-NLS-1$

	/**
	 * get the name of the attribute.
	 * 
	 * @return the attribute name
	 */
	public String getAttributeName();

	/**
	 * Returns a brief description of this property. This localized string is
	 * shown to the user when this property is selected. and it is used as
	 * tooltip of the property now.
	 * 
	 * @return a brief description, or <code>null</code> if none
	 */
	public String getDescription();

	/**
	 * return the category for this attribute.
	 * 
	 * @return the category
	 */
	public String getCategory();

	/**
	 * value type is used to construct the cell editor.
	 * 
	 * @return the value type
	 */
	public String getValueType();

	/**
	 * Some value type contains additional parameter information. For example,
	 * if valueType is CLASSNAME, the typeParameter could be super
	 * interface/super class name.
	 * 
	 * NOTE: if valueType is ENUMERATION, caller should use
	 * <code>getOptions()</code>
	 * 
	 * @return null if there is no type parameter.
	 */
	public String getTypeParameter();

	/**
	 * @param name
	 * @return the parameter
	 */
	public String getParameterByName(String name);

	/**
	 * when the value type is "enumeration", this method will be called to
	 * construct the drop downlist.
	 * 
	 * The Map will be (key->display string)
	 * 
	 * @return the options
	 */
	public Map getOptions();

	/**
	 * Gets the default value of Options
	 * 
	 * @return the default value
	 */
	public String getDefaultValue();

	/**
	 * A human readable string as the label of the attribute.
	 * 
	 * @return the label
	 */
	public String getLabelString();

	/**
	 * Indicate whether the attribute is required.
	 * 
	 * @return true if is required
	 */
	public boolean isRequired();
}
