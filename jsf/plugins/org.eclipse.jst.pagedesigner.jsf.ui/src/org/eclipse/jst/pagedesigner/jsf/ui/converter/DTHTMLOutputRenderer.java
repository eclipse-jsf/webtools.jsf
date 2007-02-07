package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.dom.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.ConvertAttributeToTextOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CopyAllAttributesOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CopyChildrenOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CreateAttributeOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.CreateElementOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.RenameAttributeOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.SetIsMultiLevelOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.SetIsWidgetOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.SetNeedsBorderDecoratorOperation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class DTHTMLOutputRenderer implements IOutputRenderer {

	private DTTagConverter tagConverter;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.IOutputRenderer#render(org.eclipse.jst.pagedesigner.dtmanager.DTTagConverter)
	 */
	public Element render(DTTagConverter tagConverter) {
		this.tagConverter = tagConverter;
		Element srcElement = tagConverter.getHostElement();
		Element resultElement = null;
		if (srcElement != null) {
			ITransformer transformer = getTransformer(srcElement);
			resultElement = transformer.transform(srcElement);
			if (resultElement == null) {
				resultElement = getUnknownTagRepresentation();
			}
		}
		return resultElement;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.IOutputRenderer#getTagConverter()
	 */
	public DTTagConverter getTagConverter() {
		return tagConverter;
	}

	protected ITransformer getTransformer(Element srcElement) {
		ITransformer transformer = new DefaultTransformer();
		transformer.setOutputRenderer(this);
        
        TagIdentifier srcTagIdentifier = 
            TagIdentifierFactory.createDocumentTagWrapper(srcElement); 
        
		if (IJSFConstants.TAG_IDENTIFIER_VIEW.isSameTagType(srcTagIdentifier)) 
        {
			transformer.appendTransformOperation(new CreateElementOperation("div"));
			transformer.appendTransformOperation(new CopyChildrenOperation());
			transformer.appendTransformOperation(new SetNeedsBorderDecoratorOperation(true));
		}
		else if (IJSFConstants.TAG_IDENTIFIER_FORM.isSameTagType(srcTagIdentifier)) 
        {
            transformer.appendTransformOperation(new CreateElementOperation("form"));
            transformer.appendTransformOperation(new CopyAllAttributesOperation());
            transformer.appendTransformOperation(new RenameAttributeOperation("styleClass", "class"));
            transformer.appendTransformOperation(new CopyChildrenOperation());
            transformer.appendTransformOperation(new SetNeedsBorderDecoratorOperation(true));
        }
        else if (IJSFConstants.TAG_IDENTIFIER_INPUTTEXT.isSameTagType(srcTagIdentifier))
        {
			transformer.appendTransformOperation(new CreateElementOperation("input"));
			transformer.appendTransformOperation(new CopyAllAttributesOperation());
			transformer.appendTransformOperation(new RenameAttributeOperation("styleClass", "class"));
			transformer.appendTransformOperation(new CreateAttributeOperation("type", "text"));
			transformer.appendTransformOperation(new SetIsMultiLevelOperation(true));
			transformer.appendTransformOperation(new SetIsWidgetOperation(true));
        }
        else if (IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT.isSameTagType(srcTagIdentifier)) 
        {
			transformer.appendTransformOperation(new CreateElementOperation("span"));
			transformer.appendTransformOperation(new CopyAllAttributesOperation());
			transformer.appendTransformOperation(new RenameAttributeOperation("styleClass", "class"));
			transformer.appendTransformOperation(new ConvertAttributeToTextOperation("value", true));
			transformer.appendTransformOperation(new SetNeedsBorderDecoratorOperation(true));
			transformer.appendTransformOperation(new SetIsMultiLevelOperation(true));
			transformer.appendTransformOperation(new SetIsWidgetOperation(true));
        }
		else if (IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL.isSameTagType(srcTagIdentifier)) 
        {
			transformer.appendTransformOperation(new CreateElementOperation("label"));
			transformer.appendTransformOperation(new CopyAllAttributesOperation());
			transformer.appendTransformOperation(new RenameAttributeOperation("styleClass", "class"));
			transformer.appendTransformOperation(new ConvertAttributeToTextOperation("value", true));
			transformer.appendTransformOperation(new CopyChildrenOperation());
			transformer.appendTransformOperation(new SetNeedsBorderDecoratorOperation(true));
			transformer.appendTransformOperation(new SetIsMultiLevelOperation(true));
			transformer.appendTransformOperation(new SetIsWidgetOperation(true));
		}
		return transformer;
	}

	protected Element getUnknownTagRepresentation() {
		Element element = null;
		Element srcElement = tagConverter.getHostElement();
		if (srcElement != null) {
			Document document = srcElement.getOwnerDocument();
			if (document != null) {
				element = document.createElement("span");
				element.setAttribute("style", "font-style:italic;color:silver;");
				Text textNode = document.createTextNode(" <" + srcElement.getTagName() + "/> ");
				element.appendChild(textNode);
				tagConverter.setMultiLevel(true);
				tagConverter.setWidget(true);
			}
		}
		return element;
	}

}
