/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.itemcreation.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.MetadataFactory;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.dom.JSFValidatorSupport;
import org.eclipse.jst.pagedesigner.dom.ValidatorSupport;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteElementTemplateHelper;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfos;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfosFactory;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationAttribute;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationInfo;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.utils.BodyHelper;
import org.eclipse.jst.pagedesigner.utils.JSPUtil;
import org.eclipse.wst.xml.core.internal.contentmodel.CMAttributeDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * {@link ITagCreator} used by the Web Page Editor palette. 
 * 
 * Uses org.eclipse.jst.jsf.common.metadata 
 */
public class DefaultTagCreator implements ITagCreator {
	
	private static final QName _qnameHTMLForm = new QName(CMDocType.HTML_DOC_TYPE, IHTMLConstants.TAG_FORM);
	
	/**
	 * The {@link TagToolPaletteEntry} being used
	 */
	protected TagToolPaletteEntry _tagItem;
	/**
	 * The {@link ITaglibDomainMetaDataModelContext} for the tag creation
	 */
	protected ITaglibDomainMetaDataModelContext _modelContext;
	/**
	 * The tag {@link Entity} being created
	 */
	private Entity _tagEntity;
	
	/**
	 * Construct a tag creator for the _modelContext
	 * @param modelContext
	 */
	public DefaultTagCreator(ITaglibDomainMetaDataModelContext modelContext) {
		this._modelContext = modelContext;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.itemcreation.ITagCreator#createTag(org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry, org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel, org.eclipse.jst.pagedesigner.dom.IDOMPosition)
	 */
	public Element createTag(TagToolPaletteEntry tagToolPaletteEntry, IDOMModel model, IDOMPosition domPosition) {
		this._tagItem = tagToolPaletteEntry;
	
		IDOMPosition position = preAdjustPositionForNecessaryContainers(model, domPosition);
		if (position == null)//throw exception?
			return null;

		String uri = getURI();
		String defaultPrefix = getDefaultPrefix();		
		String prefix = getPrefix(uri, model, defaultPrefix);
		
		Element ele =  createElement(model, prefix);
		if (ele == null)//throw exception?
			return null;
		
		applyTemplate(model, ele);
		applyRequiredAttrs(ele);
		
		addTagToContainer(position, ele);
		
		return ele;
	}

	/**
	 * @param model
	 * @param prefix
	 * @return {@link Element}
	 */
	protected Element createElement(IDOMModel model, String prefix){
		Element ele =  model.getDocument().createElement(_tagItem.getTagName());
		if (ele == null)
			return null;
		
		//ugly... fix me
		
		// XXX: we are using "startsWith("directive.")" to test whether
		// should setJSPTag, this
		// maybe is not the best way. Need check whether SSE have special
		// API for it.
		if (ITLDConstants.URI_JSP.equals(getURI())
				&& (ele.getLocalName().startsWith("directive.")
						|| "declaration".equals(ele.getLocalName())
						|| "expression".equals(ele.getLocalName()) || "scriptlet"
						.equals(ele.getLocalName()))) {
			// it is a jsp tag
			((IDOMElement) ele).setJSPTag(true);
		}
		if (prefix != null)
			ele.setPrefix(prefix);
		return ele;
	}
	/**
	 * @param position
	 * @param tagElement
	 */
	protected void addTagToContainer(IDOMPosition position, Element tagElement) {
		if (position == null || position.getContainerNode() == null) {
			return;
		}
		
		if (position.getNextSiblingNode() == null) {
			position.getContainerNode().appendChild(tagElement);
		} else {
			position.getContainerNode().insertBefore(tagElement,
					position.getNextSiblingNode());
		}
		
	}

	/**
	 * Return position for tag after all necessary containers are created.  
	 * If nnecessary containers cannot be created and do not exist for any reason, implementer should return null
	 * to signal that the tag creation should not occur.
	 * @param model
	 * @param domPosition
	 * @return position after necessary containers are created
	 */
	protected IDOMPosition preAdjustPositionForNecessaryContainers(IDOMModel model,
			IDOMPosition domPosition) {
				
		IDOMPosition position = DOMPositionHelper.splitText(domPosition);

		position = BodyHelper
				.adjustInsertPosition(getURI(), getLocalTagName(), position);
		
		position = createContainers(position);
		if (position == null) {
			// user cancelled
			return null;
		}
		
		// because the next call of getPrefix() may insert new taglib node
		// into the document, if we use the normal
		// DOMPositin which use index, maybe it will be invalidated by the
		// new taglib node. So use RefPosition here.
		return DOMPositionHelper.toDOMRefPosition(position);
	}

	/**
	 * @param position
	 * @return position after creating required containers
	 */
	protected IDOMPosition createContainers(IDOMPosition position) {
		
		//FIX ME - should be done in subclass
		if (isJSFComponent()) {
			return JSFValidatorSupport.prepareInsertJSFComponent(position, getURI(), getLocalTagName(), isHTMLFormRequired());
		}
		else if (isHTMLFormRequired()){
			boolean hasform = ValidatorSupport.checkContainer(position, _qnameHTMLForm);
			IDOMPosition newPosition = position;
			if (!hasform) {
				newPosition = ValidatorSupport
						.insertContainer(position, _qnameHTMLForm);
				if (newPosition == null) {
					newPosition = position;
				}
			}
			return newPosition;
		}
        return position;
	}

	private boolean isJSFComponent() {		
		Model model = TaglibDomainMetaDataQueryHelper.getModel(_modelContext);
		Trait t = TaglibDomainMetaDataQueryHelper.getTrait(model, "is-jsf-component-library");
		if (t != null)
			return TraitValueHelper.getValueAsBoolean(t);
		
		return false;
	}

	/**
	 * @return convenience method returning default prefix to be used by the tag item
	 */
	private  String getDefaultPrefix() {
		return _tagItem.getDefaultPrefix();
	}

	/**
	 * @return convenience method returning uri of the tag item's library
	 */
	private String getURI() {		
		return _tagItem.getURI();
	}
	
	/**
	 * @return convenience method returning the tag name without prefix
	 * 
	 */
	private String getLocalTagName(){
		return _tagItem.getTagName();
	}
	/**
	 * Add required attributes and default values, if set, to the created tag element
	 * @param element
	 */
	protected void applyRequiredAttrs(Element element) {
		TagCreationInfo info = getTagCreationInfo();
		if (info != null){
			EList list = info.getAttributes();
			if (list != null) {
				for (Iterator it = list.iterator(); it.hasNext();) {
					TagCreationAttribute attr = (TagCreationAttribute)it.next();
					element.setAttribute(attr.getId(), (attr.getValue() == null ? "" : attr.getValue()));
				}
			}
		}			
	}

	/**
	 * If TagCreationInfo is defined for the tag element, apply the template if defined
	 * @param model
	 * @param tagElement
	 */
	protected void applyTemplate(IDOMModel model, Element tagElement) {
		TagCreationInfo tagCreationInfo = getTagCreationInfo();
		if (tagCreationInfo != null)
			PaletteElementTemplateHelper.applyTemplate(model, tagElement, getTagToolItem(), tagCreationInfo); 		
	}

	/**
	 * @return {@link TagCreationInfo} for the tag entity
	 */
	protected TagCreationInfo getTagCreationInfo(){
		Model model = TaglibDomainMetaDataQueryHelper.getModel(_modelContext);
		if (model != null){
			Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(model, PaletteInfos.TRAIT_ID);
			if (trait != null){
				PaletteInfos pis = (PaletteInfos)trait.getValue();
				PaletteInfo pi = pis.findPaletteInfoById(_tagItem.getId());
				if (pi != null){
					return pi.getTagCreation();					
				}
			}
			//tag-creation trait on entity directly?
			Entity tag = getTagEntity();
			if (tag != null){//metadata exists
				trait = TaglibDomainMetaDataQueryHelper.getTrait(tag, "tag-create");
				if (trait != null && trait.getValue() != null){
					return (TagCreationInfo)trait.getValue();					
				}
			}
		}
		return null;
	}

	/**
	 * @return the {@link TagToolPaletteEntry}
	 */
	protected TagToolPaletteEntry getTagToolItem(){
		return _tagItem;
	}
	
	
	/**
	 * Returns the ns prefix for the tag and also creates taglib reference if necessary
	 * @param uri
	 * @param model
	 * @param suggested
	 * @return prefix to use
	 */
	protected String getPrefix(String uri, IDOMModel model,
			String suggested) {
		if (ITLDConstants.URI_HTML.equalsIgnoreCase(uri)
				|| ITLDConstants.URI_JSP.equalsIgnoreCase(uri)
				|| CMDocType.JSP11_DOC_TYPE.equalsIgnoreCase(uri)) {
			return null;
		}

		// now handles custom tag lib
		return JSPUtil.getOrCreatePrefix(model, uri, suggested);
	}
	
	/**
	 * @param tag
	 * @return a "create-tag" {@link Trait} for the tag.  May be null there are no required attrs.
	 */
	protected Trait createRequiredAttrTraits(CMElementDeclaration tag) {

		List reqs = new ArrayList();
		for (Iterator it=tag.getAttributes().iterator();it.hasNext();){
			CMAttributeDeclaration attr = (CMAttributeDeclaration)it.next();
			if (attr.getUsage() == CMAttributeDeclaration.REQUIRED){
				reqs.add(attr);
			}			
		}
		if (reqs.size() > 0)
			return internalCreateTagCreateAttributes(reqs);
		
		return null;
	}		
	
	private Trait internalCreateTagCreateAttributes(List reqs) {
		Trait t = MetadataFactory.eINSTANCE.createTrait();
		t.setId("create-trait");
		
		TagCreationInfo info = PaletteInfosFactory.eINSTANCE.createTagCreationInfo();
		for (Iterator it=reqs.iterator();it.hasNext();){
			TagCreationAttribute attrInfo = PaletteInfosFactory.eINSTANCE.createTagCreationAttribute();
			CMAttributeDeclaration attr = (CMAttributeDeclaration)it.next();
			attrInfo.setId(attr.getAttrName());
			attrInfo.setValue("");//TODO: use default value?
			info.getAttributes().add(attrInfo);
		}
		t.setValue(info); 
		return t;
	}
	
	/**
	 * @return flag indicating that html form container ancestor is required
	 */
	protected boolean isHTMLFormRequired() {
		Trait t = TaglibDomainMetaDataQueryHelper.getTrait(getTagEntity(), "requires-html-form");
		if (t != null)
			return TraitValueHelper.getValueAsBoolean(t);
		
		return false;
	}

	/**
	 * @return the {@link Entity} for this tag element being created
	 */
	protected Entity getTagEntity() {
		if (_tagEntity == null){
			_tagEntity = TaglibDomainMetaDataQueryHelper.getEntity(_modelContext, _tagItem.getTagName());
			
		}
		return _tagEntity;
	}
}
