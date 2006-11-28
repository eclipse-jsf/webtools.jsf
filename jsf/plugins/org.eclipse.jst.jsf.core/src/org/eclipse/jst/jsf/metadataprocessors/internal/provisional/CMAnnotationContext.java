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

package org.eclipse.jst.jsf.metadataprocessors.internal.provisional;


/**
 * The current context of the annotation file being used for processing
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class CMAnnotationContext {
	private String bundleId;
	private String uri;
	private String elementName;
	private String attributeName;
	
	
	public CMAnnotationContext(String bundleId, String uri, String elementName){
		this.bundleId = bundleId;
		this.uri = uri;
		this.elementName = elementName;
	}
	
	public CMAnnotationContext(String bundleId, String uri, String elementName, String attributeName){
		this.bundleId = bundleId;
		this.uri = uri;
		this.elementName = elementName;
		this.attributeName = attributeName;
	}
	
	public boolean isAttributeContext(){
		return attributeName != null;
	}
	
	public boolean isElementContext(){
		return attributeName == null;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public String getElementName() {
		return elementName;
	}

	public String getUri() {
		return uri;
	}
	
	public String getBundleId() {
		return bundleId;
	}

}
