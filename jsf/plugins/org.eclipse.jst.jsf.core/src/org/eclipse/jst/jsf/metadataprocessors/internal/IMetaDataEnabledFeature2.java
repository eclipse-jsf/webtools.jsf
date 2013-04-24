package org.eclipse.jst.jsf.metadataprocessors.internal;

import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;

/**
 * Add the ability to inject a more general model context than IStructuredDocumentContext.
 *
 */
public interface IMetaDataEnabledFeature2 extends IMetaDataEnabledFeature
{
    /**
     * @return the model context
     */
    IModelContext getModelContext();
    
    /**
     * @param modelContext
     */
    void setModelContext(IModelContext modelContext);

}
