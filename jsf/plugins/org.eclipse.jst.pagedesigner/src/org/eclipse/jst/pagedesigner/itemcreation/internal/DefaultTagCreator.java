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
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;
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

public class DefaultTagCreator implements ITagCreator {
	
	private static final QName _qnameHTMLForm = new QName(CMDocType.HTML_DOC_TYPE, IHTMLConstants.TAG_FORM);
	
	protected TagToolPaletteEntry tagItem;
	protected IMetaDataModelContext modelContext;
	private Entity _tagEntity;
	
	public DefaultTagCreator(IMetaDataModelContext modelContext) {
		this.modelContext = modelContext;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.itemcreation.ITagCreator#createTag(org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry, org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel, org.eclipse.jst.pagedesigner.dom.IDOMPosition)
	 */
	public Element createTag(TagToolPaletteEntry tagItem, IDOMModel model, IDOMPosition domPosition) {
		this.tagItem = tagItem;
	
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

	protected Element createElement(IDOMModel model, String prefix){
		Element ele =  model.getDocument().createElement(tagItem.getTagName());
		if (ele == null)
			return null;
		
		//ugly... fix me
		if (ITLDConstants.URI_JSP.equals(getUri())
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
	 * @param element
	 */
	protected void addTagToContainer(IDOMPosition position, Element ele) {
		if (position.getNextSiblingNode() == null) {
			position.getContainerNode().appendChild(ele);
		} else {
			position.getContainerNode().insertBefore(ele,
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
				.adjustInsertPosition(getUri(), getLocalTagName(), domPosition);
		
		position = createContainers(position, getTagToolItem());
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
	 * @param tagToolItem
	 * @return position after creating necessary containers
	 */
	protected IDOMPosition createContainers(IDOMPosition position,
			TagToolPaletteEntry tagToolItem) {
		
		//FIX ME - should be done in subclass
		if (isJSFComponent()) {
			return JSFValidatorSupport.prepareInsertJSFComponent(position, tagItem
					.getURI(), tagItem.getTagName(), isHTMLFormRequired());
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
		Model model = MetaDataQueryHelper.getModel(modelContext);
		Trait t = MetaDataQueryHelper.getTrait(model, "is-jsf-component-library");
		if (t != null)
			return TraitValueHelper.getValueAsBoolean(t);
		
		return false;
	}
	protected String getFullTagName(String localName, String prefix) {		
		if (prefix != null)
			return prefix + ":" + localName;
		return localName;
	}

	protected String getDefaultPrefix() {
		return tagItem.getDefaultPrefix();
	}

	protected String getURI() {		
		return tagItem.getURI();
	}
	
	protected void applyRequiredAttrs(Element ele) {
		TagCreationInfo info = getTagCreationInfo();
		if (info != null){
			EList list = info.getAttributes();
			if (list != null) {
				for (Iterator it = list.iterator(); it.hasNext();) {
					TagCreationAttribute attr = (TagCreationAttribute)it.next();
					ele.setAttribute(attr.getId(), (attr.getValue() == null ? "" : attr.getValue()));
				}
			}
			
		} else {
//			createRequiredAttributes(ele);
		}
			
		
	}

	protected void applyTemplate(IDOMModel model, Element ele) {
		TagCreationInfo tagCreationInfo = getTagCreationInfo();
		if (tagCreationInfo != null)
			PaletteElementTemplateHelper.applyTemplate(model, ele, getTagToolItem(), tagCreationInfo); 		
	}

	protected TagCreationInfo getTagCreationInfo(){
		Model model = MetaDataQueryHelper.getModel(modelContext);
		if (model != null){
			Trait trait = MetaDataQueryHelper.getTrait(model, PaletteInfos.TRAIT_ID);
			if (trait != null){
				PaletteInfos pis = (PaletteInfos)trait.getValue();
				PaletteInfo pi = pis.findPaletteInfoById(tagItem.getId());
				if (pi != null){
					return pi.getTagCreation();					
				}
			}
			//tag-creation trait on entity directly?
			Entity tag = getTagEntity();
			if (tag != null){//metadata exists
				trait = MetaDataQueryHelper.getTrait(tag, "tag-create");
				if (trait != null && trait.getValue() != null){
					return (TagCreationInfo)trait.getValue();					
				}
			}
		}
		return null;
	}
//	protected void createRequiredAttributes(Element ele) {
//		Entity tagEntity = MetaDataQueryHelper.getEntity(modelContext, tagItem.getTagName());
//		if(tagEntity != null){
//			
//		}
//		CMElementDeclaration elemDecl = getElementDeclaration();
//		if (elemDecl != null){
//			Trait t = createRequiredAttrTraits(elemDecl);
//			TagCreationInfo info = (TagCreationInfo)t.getValue();
//			for (Iterator it=info.getAttributes().iterator();it.hasNext();){
//				TagCreationAttribute attrInfo = (TagCreationAttribute)it.next();
//				ele.setAttribute(attrInfo.getId(), attrInfo.getValue() == null ? "" : attrInfo.getValue());
//			}
//		}
//	}

	protected TagToolPaletteEntry getTagToolItem(){
		return tagItem;
	}
	
	protected String getUri(){
		return tagItem.getURI();
	}
	
	protected String getLocalTagName(){
		return tagItem.getTagName();
	}
	
	/**
	 * Creates taglib directive if necessary
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
//		entity.getTraits().add(t);
		return t;
	}
	/**
	 * @return CMElementDeclaration - may be null
	 */
	protected CMElementDeclaration getElementDeclaration(){
		CMElementDeclaration decl = (CMElementDeclaration)getTagToolItem().getToolProperty("CMElementDeclaration");
		return decl;
	}
	
	protected boolean isHTMLFormRequired() {
		Trait t = MetaDataQueryHelper.getTrait(getTagEntity(), "requires-html-form");
		if (t != null)
			return TraitValueHelper.getValueAsBoolean(t);
		
		return false;
	}

	protected Entity getTagEntity() {
		if (_tagEntity == null){
			_tagEntity = MetaDataQueryHelper.getEntity(modelContext, tagItem.getTagName());
			
		}
		return _tagEntity;
	}
}
