/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.pagedesigner.jsp.core.internal.metadata;

import org.eclipse.jst.jsf.common.metadata.internal.AbstractTagLibDomainContentModelMetaDataTranslator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataTranslator;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;

/**
 * Translator for tag lib meta-data
 *
 */
public class TaglibMetaDataTLDTranslator extends AbstractTagLibDomainContentModelMetaDataTranslator implements IMetaDataTranslator {

	public void translate(final IMetaDataModelMergeAssistant assistant) {
		setAssistant(assistant);
		CMDocument doc = getSourceModel();
		if (doc != null && doc instanceof TLDDocument){
			doTranslate(doc);
			
		}
	}

	protected String getURIDefaultPrefix(){
		return getTLDDocument().getShortname();
	}
	
	/**
	 * @param tag
	 * @return the label for the tag
	 */
	protected String getTagDisplayLabel(CMElementDeclaration tag){
		String label = ((TLDElementDeclaration)tag).getDisplayName();
		if (label == null)
			label = super.getTagDisplayName(tag);
		return label;		
	}
	
	protected String getTagDescription(CMElementDeclaration tag){
		String desc = ((TLDElementDeclaration)tag).getDescription();
		if (desc == null)
			desc = super.getTagDescription(tag);
		return desc;		
	}
	
	protected String getTagSmallIcon(CMElementDeclaration tag){
		String smallIcon = ((TLDElementDeclaration)tag).getSmallIcon();
		if (smallIcon == null)
			smallIcon = super.getTagSmallIcon(tag);
		return smallIcon;		
	}
	
	protected String getTagLargeIcon(CMElementDeclaration tag){
		String largeIcon = ((TLDElementDeclaration)tag).getLargeIcon();
		if (largeIcon == null)
			largeIcon = super.getTagLargeIcon(tag);
		return largeIcon;		
	}
	
	protected String getURIDescription() {		
		return getTLDDocument().getDescription();
	}

	protected String getURIDisplayLabel() {		
		return getTLDDocument().getDisplayName();
	}
	
	/**
	 * @return the tld document for the source model
	 */
	protected TLDDocument getTLDDocument(){
		return (TLDDocument)getSourceModel();
	}

}
