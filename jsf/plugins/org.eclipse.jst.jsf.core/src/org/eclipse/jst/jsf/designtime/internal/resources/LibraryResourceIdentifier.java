package org.eclipse.jst.jsf.designtime.internal.resources;

/**
 * A resource identifier for a library.
 * @author cbateman
 *
 */
public class LibraryResourceIdentifier extends ResourceIdentifier
{
    private final String _libraryName;

    /**
     * @param resName 
     * @param libraryName
     */
    public LibraryResourceIdentifier(final String resName, final String libraryName)
    {
        super(resName);
        _libraryName = libraryName;
    }

    @Override
    public String getLibraryName()
    {
        return _libraryName;
    }
}
