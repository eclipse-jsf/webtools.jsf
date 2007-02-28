package org.eclipse.jst.pagedesigner.itemcreation.internal;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.MetaDataQueryHelper;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.dom.JSFValidatorSupport;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteElementTemplateHelper;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationAttribute;
import org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.provisional.TagCreationInfo;
import org.eclipse.jst.pagedesigner.utils.BodyHelper;
import org.eclipse.jst.pagedesigner.utils.JSPUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

//JUNK!
public class TagCreatorFromMetaData extends DefaultTagCreator {

	public TagCreatorFromMetaData(IMetaDataModelContext modelContext) {
		super(modelContext);
		// TODO Auto-generated constructor stub
	}

//	private TagCreationInfo tagCreationInfo;
//	private IMetaDataModelContext modelContext;
//
//	
//	public TagCreatorFromMetaData(IMetaDataModelContext modelContext, TagToolPaletteEntry tag, TagCreationInfo tagCreationInfo) {
//		super(tag);
//		this.modelContext = modelContext;
//		this.tagCreationInfo = tagCreationInfo;
//	}
//
//	public Element createTag(TagToolPaletteEntry tagItem, IDOMModel model, IDOMPosition domPosition) {
//		IDOMPosition position = DOMPositionHelper.splitText(domPosition);
//		position = BodyHelper
//				.adjustInsertPosition(getUri(), getLocalTagName(), domPosition);
//		
//		position = prepareJSFValidity(position, getTagToolItem());
//		if (position == null) {
//			// user cancelled
//			return null;
//		}
//		
//		// because the next call of getPrefix() may insert new taglib node
//		// into the document, if we use the normal
//		// DOMPositin which use index, maybe it will be invalidated by the
//		// new taglib node. So use RefPosition here.
//		position = DOMPositionHelper.toDOMRefPosition(position);
//		
//		String prefix = getPrefix(getUri(), model, getTagToolItem().getDefaultPrefix());
//		Element ele = model.getDocument().createElement(getLocalTagName());
//		
//		// XXX: we are using "startsWith("directive.")" to test whether
//		// should setJSPTag, this
//		// maybe is not the best way. Need check whether SSE have special
//		// API for it.
//		if (IJMTConstants.URI_JSP.equals(getUri())
//				&& (ele.getLocalName().startsWith("directive.")
//						|| "declaration".equals(ele.getLocalName())
//						|| "expression".equals(ele.getLocalName()) || "scriptlet"
//						.equals(ele.getLocalName()))) {
//			// it is a jsp tag
//			((IDOMElement) ele).setJSPTag(true);
//		}
//		if (prefix != null) {
//			ele.setPrefix(prefix);
//		}
//		
//		applyTemplate(model, ele);
//		applyRequiredAttrs(ele);
//		
//		if (position.getNextSiblingNode() == null) {
//			position.getContainerNode().appendChild(ele);
//		} else {
//			position.getContainerNode().insertBefore(ele,
//					position.getNextSiblingNode());
//		}
//		
//		return ele;
//	}
//	
//	private void applyRequiredAttrs(Element ele) {
//		EList list = tagCreationInfo.getAttributes();
//		if (list != null) {
//			for (Iterator it = list.iterator(); it.hasNext();) {
//				TagCreationAttribute attr = (TagCreationAttribute)it.next();
//				ele.setAttribute(attr.getId(), (attr.getValue() == null ? "" : attr.getValue()));
//			}
//		}
//		else {
//			//this should be removed once we start loading tagCreation infos from TLDTranslator
//			createRequiredAttributes(ele);
//		}
//
//		
//	}
//
//	private void applyTemplate(IDOMModel model, Element ele) {
//		PaletteElementTemplateHelper.applyTemplate(model, ele, getTagToolItem(), tagCreationInfo); //needs fixes		
//	}
//
//	private IDOMPosition prepareJSFValidity(IDOMPosition position,
//			TagToolPaletteEntry item) {
//		if (isJSFComponent()) {
//			return JSFValidatorSupport.prepareInsertJSFComponent(position, item
//					.getURI(), item.getTagName(), isHTMLFormRequired());
//		}
//        return position;
//	}
//	
//	protected boolean isHTMLFormRequired() {
//		Trait t = MetaDataQueryHelper.getTrait(modelContext, getUri(), "requires-html-form");
//		if (t != null)
//			return TraitValueHelper.getValueAsBoolean(t);
//		
//		return false;
//	}
//
//	private boolean isJSFComponent() {		
//		Model model = MetaDataQueryHelper.getModel(modelContext);
//		Trait t = MetaDataQueryHelper.getTrait(model, "is-jsf-component-library");
//		if (t != null)
//			return TraitValueHelper.getValueAsBoolean(t);
//		
//		return false;
//	}
//
//
}
