package org.eclipse.jst.jsf.designtime.internal.resources;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Encapsulates the concept of a JSF (2.0+) resource identifier per 2.6.1 of the
 * specification. The class does not ensure validity of the identifier.
 * 
 * You can call validate to determine if the contents are correct.
 * 
 * @author cbateman
 * 
 */
public class ResourceIdentifier extends ResourceFragmentIdentifier
{
    /**
     * Resource name is not optional on identifiers.
     */
    public static final int RESOURCE_NAME_MUST_NOT_BE_EMPTY = 0;
    /**
     * The namespace for validation diagnositics created by validate.
     */
    public static final String ResourceIdentifierValidationNamespace = JSFCorePlugin.PLUGIN_ID
            + "_JSFResourceIdentifier"; //$NON-NLS-1$
    private final String _resName;

    /**
     * @param resName
     */
    public ResourceIdentifier(String resName)
    {
        super();
        _resName = resName;
    }

    /**
     * Allow fragments to be 
     */
    protected ResourceIdentifier()
    {
        super();
        _resName = null;
    }
    /**
     * @return the resource name or null if none.
     */
    @Override
    public String getResourceName()
    {
        return _resName;
    }

    /**
     * @return a status object multi-value that contains the results of
     *         validating this resoure identifier.
     */
    @Override
    public IStatus validate()
    {
        final String id = ResourceIdentifierValidationNamespace;
        final MultiStatus status = new MultiStatus(id, 0,
                "Resource identifier problems", null); //$NON-NLS-1$

        if (getResourceName() == null)
        {
            status.add(new Status(IStatus.ERROR, id,
                    RESOURCE_NAME_MUST_NOT_BE_EMPTY,
                    Messages.ResourceIdentifier_0, null));
        }

        return status;
    }

    @Override
    public String toString()
    {
        if (!validate().isOK())
        {
            throw new IllegalStateException();
        }
        String idString = getResourceName();
        if (getResourceVersion() != null)
        {
            idString = String.format("%s/%s", getResourceVersion(), idString); //$NON-NLS-1$
        }
        if (getLibraryName() != null)
        {
            idString = String.format("%s/%s", getLibraryName(), idString); //$NON-NLS-1$
        }
    
        if (getLocalePrefix() != null)
        {
            idString = String.format("%s/%s", getLocalePrefix(), idString); //$NON-NLS-1$
        }
    
        if (getResourceVersion() != null)
        {
            idString += "/" + getResourceVersion(); //$NON-NLS-1$
        }
        return idString;
    }

    @Override
    public final boolean isFragment()
    {
        return false;
    }

}
