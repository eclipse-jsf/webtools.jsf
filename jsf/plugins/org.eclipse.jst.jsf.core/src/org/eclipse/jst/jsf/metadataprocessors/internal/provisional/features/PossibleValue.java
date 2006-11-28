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

package org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features;

import org.eclipse.jface.resource.ImageDescriptor;


public class PossibleValue implements IPossibleValue {
	private String value;
	private String displayValue;
	private ImageDescriptor smallIcon;
	private boolean isDefault = false;
	private String additionalInfo;

	public PossibleValue(String value){
		this.value = value;
	}
	
	public PossibleValue(String value, String displayValue){
		this.value = value;
		this.displayValue = displayValue;
	}

	public PossibleValue(String value, String displayValue, boolean isDefaultValue) {
		this.value = value;
		this.displayValue = displayValue;
		this.isDefault = isDefaultValue;
	}

	public String getValue() { 
		return value;
	}

	public String getDisplayValue() {
		if (displayValue == null)
			return value;
		return displayValue;
	}

	public ImageDescriptor getIcon() {		
		return smallIcon;
	}

	public boolean isDefaultValue() {
		return isDefault;
	}
	
	public String getAdditionalInformation() {
		return additionalInfo;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public void setIcon(ImageDescriptor smallIcon) {
		this.smallIcon = smallIcon;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	public void setAdditionalInformation(String additionalInfo){
		this.additionalInfo = additionalInfo;
	}

	
}
