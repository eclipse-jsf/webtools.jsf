package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations;

import org.eclipse.jst.pagedesigner.jsf.ui.converter.DTTagConverter;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.IOutputRenderer;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.jsf.ui.converter.ITransformer;
import org.w3c.dom.Element;

public abstract class AbstractTransformOperation implements ITransformOperation {

	/**
	 * ITransformer instance to which this operation belongs (may be null).
	 */
	private ITransformer transformer;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public abstract Element transform(Element srcElement, Element curElement);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformOperation#setTransformer(org.eclipse.jst.pagedesigner.dtmanager.ITransformer)
	 */
	public void setTransformer(ITransformer transformer) {
		this.transformer = transformer;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformOperation#getTransformer()
	 */
	public ITransformer getTransformer() {
		return transformer;
	}

	/**
	 * Convenience method to get the parent ITransformer instance's parent
	 * IOutputRenderer instance.
	 * 
	 * @return The parent ITransformer instance's parent IOutputRenderer
	 * instance (may be null).
	 */
	public IOutputRenderer getOutputRenderer() {
		IOutputRenderer outputRenderer = null;
		if (transformer != null) {
			outputRenderer = transformer.getOutputRenderer();
		}
		return outputRenderer;
	}

	/**
	 * Convenience method to get the parent ITransformer instance's parent
	 * IOutputRenderer instance's parent DTTagConverter instance.
	 * 
	 * @return The parent ITransformer instance's parent IOutputRenderer
	 * instance's parent DTTagConverter instance (may be null).
	 */
	public DTTagConverter getTagConverter() {
		DTTagConverter tagConverter = null;
		IOutputRenderer outputRenderer = getOutputRenderer();
		if (outputRenderer != null) {
			tagConverter = outputRenderer.getTagConverter();
		}
		return tagConverter;
	}

}
