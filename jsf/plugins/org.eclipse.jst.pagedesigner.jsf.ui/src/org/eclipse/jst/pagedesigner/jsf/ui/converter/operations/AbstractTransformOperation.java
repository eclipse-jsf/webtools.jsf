package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.ITransformOperation;
import org.w3c.dom.Element;

public abstract class AbstractTransformOperation implements ITransformOperation {

	protected ITagConverterContext tagConverterContext;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public abstract Element transform(Element srcElement, Element curElement);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITransformOperation#setTagConverterContext(org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext)
	 */
	public void setTagConverterContext(ITagConverterContext tagConverterContext) {
		this.tagConverterContext = tagConverterContext;
	}

}
