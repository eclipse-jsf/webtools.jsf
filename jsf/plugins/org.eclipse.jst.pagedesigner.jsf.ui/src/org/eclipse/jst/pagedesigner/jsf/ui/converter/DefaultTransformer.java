package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.w3c.dom.Element;

public class DefaultTransformer implements ITransformer {

	private IOutputRenderer outputRenderer;
	private Collection transformOperations;

	public DefaultTransformer() {
		transformOperations = new ArrayList();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformer#appendTransformOperation(org.eclipse.jst.pagedesigner.dtmanager.ITransformOperation)
	 */
	public void appendTransformOperation(ITransformOperation operation) {
		synchronized(transformOperations) {
			if (operation != null) {
				operation.setTransformer(this);
				transformOperations.add(operation);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformer#transform(org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement) {
		Element curElement = null;
		if (srcElement != null) {
			synchronized(transformOperations) {
				if (transformOperations.size() > 0) {
					curElement = srcElement;
					Iterator itOperations = transformOperations.iterator();
					while (itOperations.hasNext()) {
						ITransformOperation operation = (ITransformOperation)itOperations.next();
						curElement = operation.transform(srcElement, curElement);
					}
				}
			}
		}
		return curElement;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformer#setOutputRenderer(org.eclipse.jst.pagedesigner.dtmanager.IOutputRenderer)
	 */
	public void setOutputRenderer(IOutputRenderer outputRenderer) {
		this.outputRenderer = outputRenderer;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.ITransformer#getOutputRenderer()
	 */
	public IOutputRenderer getOutputRenderer() {
		return outputRenderer;
	}

	/**
	 * Convenience method to get the parent IOutputRenderer instance's parent
	 * DTTagConverter instance.
	 * 
	 * @return The parent IOutputRenderer instance's parent DTTagConverter
	 * instance (may be null).
	 */
	public DTTagConverter getTagConverter() {
		DTTagConverter tagConverter = null;
		if (outputRenderer != null) {
			tagConverter = outputRenderer.getTagConverter();
		}
		return tagConverter;
	}

}
