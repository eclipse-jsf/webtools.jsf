package org.eclipse.jst.jsf.core.internal.project.facet;

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.core.JSFVersion;

/**
 * JSF Utils instance for JSF 2.1.
 * 
 * @author ian.trimble@oracle.com
 */
/* package: use JSFUtilFactory */class JSFUtils21 extends JSFUtils20
{

    /**
     * @param modelProvider
     */
    protected JSFUtils21(final IModelProvider modelProvider)
    {
        super(JSFVersion.V2_1, modelProvider);
    }

    /**
     * @param jsfVersion
     * @param modelProvider 
     */
    protected JSFUtils21(final JSFVersion jsfVersion, final IModelProvider modelProvider)
    {
        super(jsfVersion, modelProvider);
        if (jsfVersion.compareTo(JSFVersion.V2_1) < 0)
        {
            throw new IllegalArgumentException(
                    "JSFVersion must be at least 2.1"); //$NON-NLS-1$
        }
    }

}
