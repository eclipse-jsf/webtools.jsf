package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import org.w3c.dom.Element;

public interface ITransformer {

	public void appendTransformOperation(ITransformOperation operation);

	public Element transform(Element srcElement);

	public void setOutputRenderer(IOutputRenderer outputRenderer);

	public IOutputRenderer getOutputRenderer();

}
