package org.eclipse.jst.jsf.core.internal.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * Creates a new JSFUtil for a particular JSF project version.
 * 
 * @author cbateman
 *
 */
public class JSFUtilFactory
{
    /**
     * @param project
     * @return the jsf version of the project if it is valid JSF faceted
     * project or null otherwise.
     */
    public JSFUtils create(final IProject project)
    {
        final JSFVersion jsfVersion = JSFVersion.valueOfProject(project);
        if (jsfVersion != null)
        {
            return create(jsfVersion, ModelProviderManager.getModelProvider(project));
        }
        return null;
    }
    /**
     * @param version
     * @param modelProvider 
     * @return the JSF utils object for the version or null if none.
     * @throw {@link IllegalArgumentException} if version is not related
     * to a JSF facet.
     */
    public JSFUtils create(final IProjectFacetVersion version, final IModelProvider modelProvider)
    {
        final JSFVersion jsfVersion = JSFVersion.valueOfFacetVersion(version);
        if (jsfVersion != null)
        {
            return create(jsfVersion, modelProvider);
        }
        return null;
    }

    /**
     * @param version
     * @param modelProvider 
     * @return the JSF utils object for the version or null if none.
     */
    public JSFUtils create(final JSFVersion version, final IModelProvider modelProvider)
    {
        switch (version)
        {
        case V1_0:
        case V1_1:
            return new JSFUtils11(modelProvider);
        case V1_2:
            return new JSFUtils12(modelProvider);
        case V2_0:
            return new JSFUtils20(modelProvider);
        default:
            throw new IllegalArgumentException("Unknown version: "+version); //$NON-NLS-1$
        }
    }
}
