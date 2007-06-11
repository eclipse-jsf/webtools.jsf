package org.eclipse.jst.jsf.validation.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.w3c.dom.Element;

/**
 * Exposes certain private members for use by testing. 
 * 
 * NOT intended for use by production code.
 * 
 * @author cbateman
 *
 */
public interface IJSPSemanticValidatorTest 
{
	/**
	 * Proxies the internal call to validate the containment of a particular
	 * tag. 
	 * 
	 * @param node
	 * @param uri
	 * @param tagName
	 * @param reporter
	 * @param file
	 * @param context
	 */
	void validateContainment(Element node, String uri, String tagName, IReporter reporter, IFile file, IStructuredDocumentContext context);
}
