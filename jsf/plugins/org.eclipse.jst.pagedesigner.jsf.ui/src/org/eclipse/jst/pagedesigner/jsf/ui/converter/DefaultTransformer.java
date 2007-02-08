package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.w3c.dom.Element;

public class DefaultTransformer implements ITransformer {

	private ITagConverterContext tagConverterContext;
	private Collection transformOperations;

	public DefaultTransformer() {
		transformOperations = new ArrayList();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITransformer#appendTransformOperation(org.eclipse.jst.pagedesigner.jsf.ui.converter.ITransformOperation)
	 */
	public void appendTransformOperation(ITransformOperation operation) {
		synchronized(transformOperations) {
			if (operation != null) {
				operation.setTagConverterContext(tagConverterContext);
				transformOperations.add(operation);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITransformer#transform(org.w3c.dom.Element)
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
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.ITransformer#setTagConverterContext(org.eclipse.jst.pagedesigner.jsf.ui.converter.ITagConverterContext)
	 */
	public void setTagConverterContext(ITagConverterContext tagConverterContext) {
		this.tagConverterContext = tagConverterContext;
	}

}
