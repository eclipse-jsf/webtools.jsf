package org.eclipse.jst.jsf.designtime.internal.resources;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * A fragment identifier for a library folder.
 * 
 * @author cbateman
 *
 */
public class LibraryResourceFragmentIdentifier extends
        ResourceFragmentIdentifier
{
    private final String _libraryName;

    /**
     * @param libraryName
     */
    public LibraryResourceFragmentIdentifier(final String libraryName)
    {
        super();
        _libraryName = libraryName;
    }

    @Override
    public String getLibraryName()
    {
        return _libraryName;
    }
    @Override
    public IStatus validate()
    {
        return Status.OK_STATUS;
    }
}
