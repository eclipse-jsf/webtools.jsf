package org.eclipse.jst.jsf.validation.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.internal.region.Region2ElementAdapter;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator.IValidationReporter;

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
     * @param adapter
     * @param node
     * @param uri
     * @param tagName
     * @param reporter
     * @param file
     * @param context
     */
    void validateContainment(Region2ElementAdapter adapter, IValidationReporter reporter, IFile file, IStructuredDocumentContext context);
}
