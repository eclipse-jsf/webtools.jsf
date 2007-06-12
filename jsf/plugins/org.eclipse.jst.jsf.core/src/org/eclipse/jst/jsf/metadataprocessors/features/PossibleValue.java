/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadataprocessors.features;

import org.eclipse.jface.resource.ImageDescriptor;


/**
 * Implements {@link IPossibleValues}
 * 
 * <p><b>Provisional API - subject to change</b></p>
 *
 */
public class PossibleValue implements IPossibleValue {
	private String value;
	private String displayValue;
	private ImageDescriptor smallIcon;
	private boolean isDefault = false;
	private String additionalInfo;

	/**
	 * Constructor when display value is same as stored value
	 * @param value
	 */
	public PossibleValue(String value){
		this.value = value;
	}
	
	/**
	 * Constructor when display-value may be different than stored value
	 * @param value
	 * @param displayValue
	 */
	public PossibleValue(String value, String displayValue){
		this.value = value;
		this.displayValue = displayValue;
	}

	/**
	 * Constructor when display-value may be different than stored value 
	 * and a default value is known
	 * @param value
	 * @param displayValue
	 * @param isDefaultValue
	 */
	public PossibleValue(String value, String displayValue, boolean isDefaultValue) {
		this.value = value;
		this.displayValue = displayValue;
		this.isDefault = isDefaultValue;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue#getValue()
	 */
	public String getValue() { 
		return value;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue#getDisplayValue()
	 */
	public String getDisplayValue() {
		if (displayValue == null)
			return value;
		return displayValue;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue#getIcon()
	 */
	public ImageDescriptor getIcon() {		
		return smallIcon;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue#isDefaultValue()
	 */
	public boolean isDefaultValue() {
		return isDefault;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue#getAdditionalInformation()
	 */
	public String getAdditionalInformation() {
		return additionalInfo;
	}
	
	/**
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param displayValue
	 */
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	/**
	 * @param smallIcon
	 */
	public void setIcon(ImageDescriptor smallIcon) {
		this.smallIcon = smallIcon;
	}

	/**
	 * @param isDefault
	 */
	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	/**
	 * @param additionalInfo
	 */
	public void setAdditionalInformation(String additionalInfo){
		this.additionalInfo = additionalInfo;
	}

	
}
