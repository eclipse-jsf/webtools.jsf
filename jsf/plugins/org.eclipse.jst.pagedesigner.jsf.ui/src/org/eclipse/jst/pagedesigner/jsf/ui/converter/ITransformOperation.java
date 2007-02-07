package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import org.w3c.dom.Element;

public interface ITransformOperation {

	public Element transform(Element srcElement, Element curElement);

	public void setTransformer(ITransformer transformer);

	public ITransformer getTransformer();

}
