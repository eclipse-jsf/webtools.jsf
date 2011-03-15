package org.eclipse.jst.pagedesigner.properties.celleditors;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.css.core.internal.document.CSSModelImpl;
import org.eclipse.wst.css.core.internal.provisional.adapters.ICSSModelAdapter;
import org.eclipse.wst.css.core.internal.provisional.contenttype.ContentTypeIdForCSS;
import org.eclipse.wst.css.core.internal.provisional.document.ICSSModel;
import org.eclipse.wst.html.core.internal.htmlcss.StyleListener;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;

/**
 * A slightly modified copy of {@link org.eclipse.wst.html.core.internal.htmlcss.AbstractCSSModelAdapter}.
 */
public abstract class AbstractCSSModelAdapter implements ICSSModelAdapter {
	private final static String CSS_ID = ContentTypeIdForCSS.ContentTypeID_CSS;

	private Element element = null;
	private ICSSModel model = null;

	/**
	 */
	AbstractCSSModelAdapter() {
		super();
	}
	/**
	 * @return ICSSModel
	 */
	protected ICSSModel createModel() {
		// create embedded CSS model (not for external CSS)
		if (getElement() == null)
			return null;
		IStructuredModel baseModel = ((IDOMNode) getElement()).getModel();
		ICSSModel newModel = (ICSSModel) baseModel.getModelManager().createUnManagedStructuredModelFor(CSS_ID);
		((CSSModelImpl) newModel).setOwnerDOMNode(getElement());
		return newModel;
	}

	/**
	 * @return Element
	 */
	public Element getElement() {
		return this.element;
	}

	/**
	 * @return ICSSModel
	 */
	protected ICSSModel getExistingModel() {
		return this.model;
	}

	/**
	 * @param target element
	 * 
	 */
	protected void notifyStyleChanged(Element target) {
		INodeNotifier notifier = (INodeNotifier) target;
		if (notifier == null)
			return;
		Collection adapters = notifier.getAdapters();
		if (adapters == null)
			return;
		Iterator it = adapters.iterator();
		if (it == null)
			return;
		while (it.hasNext()) {
			INodeAdapter adapter = (INodeAdapter) it.next();
			if (adapter instanceof StyleListener) {
				StyleListener listener = (StyleListener) adapter;
				listener.styleChanged();
			}
		}
	}

	/**
	 */
	void setElement(Element element) {
		this.element = element;
	}

	/**
	 * check 
	 * 1. If attributes of element is valid (type,rel ...)
	 * 2. If content model supports this element / attribute (future ?)
	 * @return validness flag
	 */
	protected boolean isValidAttribute() {
		return (getElement() != null);//metadata has already established that this is a style attribute
	}

	/**
	 * @param model 
	 */
	protected void setModel(ICSSModel model) {
		this.model = model;
	}
}
