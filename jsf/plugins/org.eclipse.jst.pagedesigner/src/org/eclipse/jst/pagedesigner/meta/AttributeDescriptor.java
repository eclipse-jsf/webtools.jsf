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

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.jst.jsf.common.ui.internal.utils.StringUtil;

/**
 * @author mengbo
 * @deprecated
 */
public class AttributeDescriptor implements IAttributeDescriptor {
	private String _attributeName;

	private String _category;

	private String _valueType;

	private String _typeParameter;

	private String _labelString;

	private String _defaultValue;

	private String _description;

	private Map _parameterMap;

	private Map _options;

	private boolean _required;

	/**
	 * 
	 */
	public AttributeDescriptor() {
		super();
	}

	/**
	 * @param attrName
	 */
	public AttributeDescriptor(String attrName) {
		this.setAttributeName(attrName);
	}

	/**
	 * @param attributeName
	 */
	public void setAttributeName(String attributeName) {
		this._attributeName = attributeName;
	}

	/**
	 * @param category
	 */
	public void setCategory(String category) {
		this._category = category;
	}

	/**
	 * @param valueType
	 */
	public void setValueType(String valueType) {
		this._valueType = valueType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.cm.IAttributeDescriptor#getAttributeName()
	 */
	public String getAttributeName() {
		return _attributeName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.cm.IAttributeDescriptor#getDescription()
	 */
	public String getDescription() {
		return _description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.cm.IAttributeDescriptor#getCategory()
	 */
	public String getCategory() {
		return _category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.cm.IAttributeDescriptor#getValueType()
	 */
	public String getValueType() {
		return _valueType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.cm.IAttributeDescriptor#getOptions()
	 */
	public Map getOptions() {
		return _options;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		_description = description;
	}

	/**
	 * @param map
	 * @param defaultValue
	 */
	public void setOptions(Map map, String defaultValue) {
		_options = map;
		_defaultValue = defaultValue;
	}

	/**
	 * @return Returns the typeParameter.
	 */
	public String getTypeParameter() {
		return _typeParameter;
	}

	/**
	 * @param typeParameter
	 *            The typeParameter to set.
	 */
	public void setTypeParameter(String typeParameter) {
		this._typeParameter = typeParameter;
		_parameterMap = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor#getLabelString()
	 */
	public String getLabelString() {
		if (_labelString == null) {
			_labelString = StringUtil.splitVariable(getAttributeName());
		}
		return _labelString;
	}

	/**
	 * @param labelString
	 *            The labelString to set.
	 */
	public void setLabelString(String labelString) {
		this._labelString = labelString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor#getParameterByName(java.lang.String)
	 */
	public String getParameterByName(String name) {
		if (_parameterMap == null) {
			parseParameter();
		}
		Object value = _parameterMap.get(name);
		return value == null ? "" : value.toString(); //$NON-NLS-1$
	}

	/**
	 * 
	 */
	private void parseParameter() {
		_parameterMap = new HashMap();
		if (_typeParameter == null) {
			return;
		}
		StringTokenizer tokenizer = new StringTokenizer(_typeParameter, "||"); //$NON-NLS-1$
		while (tokenizer.hasMoreTokens()) {
			String parameterEntry = tokenizer.nextToken();
			int index = parameterEntry.indexOf('=');
			if (index != -1) {
				_parameterMap.put(parameterEntry.substring(0, index),
						parameterEntry.substring(index + 1));
			}
		}
	}

	public String getDefaultValue() {
		return _defaultValue;
	}

	/**
	 * @param value
	 */
	public void setDefaultValue(String value) {
		_defaultValue = value;
	}

	public boolean isRequired() {
		return _required;
	}

	/**
	 * @param required
	 */
	public void setRequired(boolean required) {
		this._required = required;
	}
	
	public String toString(){
		StringBuffer buf = new StringBuffer("AttributeDescriptor: "); //$NON-NLS-1$
		buf.append(this.getAttributeName());
		return buf.toString();
	}
}
