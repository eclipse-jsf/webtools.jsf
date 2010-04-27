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
package org.eclipse.jst.jsf.core.metadata.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.AbstractTagLibDomainContentModelMetaDataTranslator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataTranslator;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDAttributeDeclaration;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMAttributeDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;

/**
 * Translates a TLD CMDocument to standard metadata model entities and traits
 *
 */
public class TaglibMetaDataTLDTranslator extends AbstractTagLibDomainContentModelMetaDataTranslator implements IMetaDataTranslator {

	public boolean canTranslate(IMetaDataSourceModelProvider modelProvider) {		
		if (modelProvider != null && 
				modelProvider.getSourceModel() instanceof TLDDocument)
			return true;
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataTranslator#translate(org.eclipse.jst.jsf.common.metadata.internal.IMetaDataModelMergeAssistant)
	 */
	public void translate(final IMetaDataModelMergeAssistant assistant) {
		setAssistant(assistant);
		CMDocument doc = getSourceModel();
		if (doc instanceof TLDDocument){
			doTranslate(doc);
			
		}
	}

	@Override
	protected void createTagfileTraits(CMDocument doc) {		
		super.createTagfileTraits(doc);
		TLDDocument tldDoc = (TLDDocument)doc;
		//add special model trait if this comes from a TagDir
		if (CMUtil.isTagDirDocument(tldDoc, getProject())){ 
			Model model = getMergedModel();
			createSimpleBooleanObjectEntityTraitIfNecessary(model, "isTagDir", true); //$NON-NLS-1$
		}
	}


	
	private IProject getProject() {
		return (IProject)_assistant.getMergedModel().getModelContext().getAdapter(IProject.class);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.AbstractTagLibDomainContentModelMetaDataTranslator#getURIDefaultPrefix()
	 */
	protected String getURIDefaultPrefix(){
		return getTLDDocument().getShortname();
	}
	
	/**
	 * @param tag
	 * @return the tag.getDisplayName() if available
	 */
	protected String getTagDisplayLabel(CMElementDeclaration tag){
		String label = ((TLDElementDeclaration)tag).getDisplayName();
		if (label == null)
			label = super.getTagDisplayName(tag);
		return label;		
	}
	
	/**
	 * @param tag
	 * @return the tag.getDescription() if available
	 */
	protected String getTagDescription(CMElementDeclaration tag){
		String desc = ((TLDElementDeclaration)tag).getDescription();
		if (desc == null)
			desc = super.getTagDescription(tag);
		return desc;		
	}
	
	/**
	 * @param tag
	 * @return the tag.getSmallIcon() if available
	 */
	protected String getTagSmallIcon(CMElementDeclaration tag){
		String smallIcon = ((TLDElementDeclaration)tag).getSmallIcon();
		if (smallIcon == null)
			smallIcon = super.getTagSmallIcon(tag);
		return smallIcon;		
	}
	
	/**
	 * @param tag
	 * @return the tag.getLargeIcon() if available
	 */
	protected String getTagLargeIcon(CMElementDeclaration tag){
		String largeIcon = ((TLDElementDeclaration)tag).getLargeIcon();
		if (largeIcon == null)
			largeIcon = super.getTagLargeIcon(tag);
		return largeIcon;		
	}
	
	/**
	 * @return the tag.getDescription() if available
	 */
	protected String getURIDescription() {		
		return getTLDDocument().getDescription();
	}

	/**
	 * @return the tag.getDisplayName()
	 */
	protected String getURIDisplayLabel() {		
		return getTLDDocument().getDisplayName();
	}
	
	/**
	 * @return the tld document for the source model
	 */
	protected TLDDocument getTLDDocument(){
		return (TLDDocument)getSourceModel();
	}

	@Override
	protected String getTagAttributeDescription(CMAttributeDeclaration cmAttr) {
		String desc = ((TLDAttributeDeclaration)cmAttr).getDescription();
		if (desc == null)
			desc = super.getTagAttributeDescription(cmAttr);
		return desc;
	}

	@Override
	protected boolean getTagAttributeIsRequired(CMAttributeDeclaration cmAttr) {
		// TODO Auto-generated method stub
		return super.getTagAttributeIsRequired(cmAttr);
	}
	
	@Override
	protected String getTagAttributeDefaultValue(CMAttributeDeclaration cmAttr) {
		String val = ((TLDAttributeDeclaration)cmAttr).getAttrType().generateInstanceValue();
		if (val == null)
			val = super.getTagAttributeDefaultValue(cmAttr);
		return val;
	}
}
