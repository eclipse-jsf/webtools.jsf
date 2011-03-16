package org.eclipse.jst.pagedesigner.preview;

import org.w3c.dom.Element;

/**
 * Implementors are capable of resolving an EL expression into a value.
 */
public interface IELValueResolver {

	/**
	 * Resolve specified EL expression into a value.
	 * @param element Element instance that can provide the document that the EL expression comes
	 * from, thus providing context.
	 * @param elExpression EL expression to resolve.
	 * @return Value resolved from EL expression - if not resolvable, it is recommended that
	 * implementors return the EL expression itself, as there may be more than one registered
	 * resolver, and all get an opportunity to resolve.
	 */
	public String resolve(Element element, String elExpression);

}
