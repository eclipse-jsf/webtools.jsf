package org.eclipse.jst.pagedesigner.properties.celleditors;

import org.eclipse.wst.css.core.internal.provisional.document.ICSSStyleDeclaration;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Node;

/**
 * Creates {@link ICSSStyleDeclaration} adapters for tags where the attribute of interest is not necessarily named "style".
 * This is to be used in conjunction with the inline CSS Style Dialog only where metadata has already
 * established that the tag attribute is styleable.
 */
public class CSSStyleDeclarationFactory  {

	private static CSSStyleDeclarationFactory instance = null;
	/**
	 */
	private CSSStyleDeclarationFactory() {
		super();
	}

	/**
	 * @param element - must not be null
	 * @param attrName - must not be null
	 * @return ICSSStyleDeclaration
	 * 
	 */
	public ICSSStyleDeclaration getStyleDeclaration(final IDOMElement element, final String attrName) {
		if ( element.getNodeType() != Node.ELEMENT_NODE)
			return null;

		final TagStyleAttrAdapter newAdapter = new TagStyleAttrAdapter(attrName);
		newAdapter.setElement(element);
		return (ICSSStyleDeclaration)newAdapter.getStyle();
	}

	/**
	 * @return CSSStyleDeclarationAdapterFactory singleton
	 */
	public synchronized static CSSStyleDeclarationFactory getInstance() {
		if (instance == null)
			instance = new CSSStyleDeclarationFactory();
		return instance;
	}


}
