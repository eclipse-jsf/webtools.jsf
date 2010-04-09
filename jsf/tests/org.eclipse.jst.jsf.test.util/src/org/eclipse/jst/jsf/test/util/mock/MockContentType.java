package org.eclipse.jst.jsf.test.util.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeSettings;
import org.eclipse.core.runtime.preferences.IScopeContext;

public class MockContentType implements IContentType
{
    private final Set<String> _associatedFileExtensions;

    public MockContentType(final Set<String> associatedFileExtensions)
    {
        _associatedFileExtensions = associatedFileExtensions;
    }

    public void addFileSpec(final String fileSpec, final int type)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void removeFileSpec(final String fileSpec, final int type)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setDefaultCharset(final String userCharset)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IContentType getBaseType()
    {
        throw new UnsupportedOperationException();

    }

    public IContentDescription getDefaultDescription()
    {
        throw new UnsupportedOperationException();

    }

    public IContentDescription getDescriptionFor(final InputStream contents,
            final QualifiedName[] options) throws IOException
    {
        throw new UnsupportedOperationException();

    }

    public IContentDescription getDescriptionFor(final Reader contents,
            final QualifiedName[] options) throws IOException
    {
        throw new UnsupportedOperationException();

    }

    public String getDefaultCharset()
    {
        throw new UnsupportedOperationException();

    }

    public String[] getFileSpecs(final int type)
    {
        throw new UnsupportedOperationException();

    }

    public String getId()
    {
        throw new UnsupportedOperationException();

    }

    public String getName()
    {
        throw new UnsupportedOperationException();

    }

    public boolean isAssociatedWith(final String fileName)
    {
        IPath path = new Path(fileName);
        String fileExtension = path.getFileExtension();
        if (fileExtension != null && _associatedFileExtensions.contains(fileExtension))
        {
            return true;
        }
        return false;
    }

    public boolean isAssociatedWith(final String fileName,
            final IScopeContext context)
    {
        // ignore scope context
        return isAssociatedWith(fileName);
    }

    public boolean isKindOf(final IContentType another)
    {
        throw new UnsupportedOperationException();

    }

    public IContentTypeSettings getSettings(final IScopeContext context)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

}
