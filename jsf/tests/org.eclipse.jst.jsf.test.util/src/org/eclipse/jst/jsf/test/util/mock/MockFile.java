package org.eclipse.jst.jsf.test.util.mock;

import static junit.framework.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.jst.jsf.test.util.Activator;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;

public class MockFile extends MockResource implements IFile
{

    private byte[]  _contents;
    private File    _concreteFile;

    public MockFile(final IPath path)
    {
        super(IResource.FILE, path);
    }

    public void appendContents(final InputStream source, final boolean force,
            final boolean keepHistory, final IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void appendContents(final InputStream source, final int updateFlags,
            final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void create(final InputStream source, final boolean force,
            final IProgressMonitor monitor) throws CoreException
    {
        setContents(source, 0, monitor);
    }

    public void create(final InputStream source, final int updateFlags,
            final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void createLink(final IPath localLocation, final int updateFlags,
            final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void createLink(final URI location, final int updateFlags,
            final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void delete(final boolean force, final boolean keepHistory,
            final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public String getCharset() throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public String getCharset(final boolean checkImplicit) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public String getCharsetFor(final Reader reader) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public IContentDescription getContentDescription() throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public InputStream getContents() throws CoreException
    {
        return getContents(false);

    }

    public InputStream getContents(final boolean force) throws CoreException
    {
        return new ByteArrayInputStream(_contents);
    }

    public int getEncoding() throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IFileState[] getHistory(final IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void move(final IPath destination, final boolean force,
            final boolean keepHistory, final IProgressMonitor monitor)
            throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setCharset(final String newCharset) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setCharset(final String newCharset,
            final IProgressMonitor monitor) throws CoreException
    {
        throw new UnsupportedOperationException();

    }

    public void setContents(final InputStream source, final boolean force,
            final boolean keepHistory, final IProgressMonitor monitor)
            throws CoreException
    {
        if (_contents != null && !force)
        {
            throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Attempt to reset contents without force"));
        }
        ByteArrayOutputStream captureBytes;
        try
        {
            captureBytes = JSFTestUtil.loadFromInputStream(source);
            // keep concrete file in sync if we have one.
            if (_concreteFile != null && _concreteFile.exists())
            {
                JSFTestUtil.saveToFileSystem(captureBytes.toByteArray(), _concreteFile.toURI());
            }
        } catch (IOException e)
        {
            throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Failed loading mock contents from stream"));
        }
        _contents = captureBytes.toByteArray();
        
    }

    public void setContents(final IFileState source, final boolean force,
            final boolean keepHistory, final IProgressMonitor monitor)
            throws CoreException
    {
        setContents(source.getContents(), force, keepHistory, monitor);
    }

    public void setContents(final InputStream source, final int updateFlags,
            final IProgressMonitor monitor) throws CoreException
    {
        setContents(source, (updateFlags | IResource.FORCE)!= 0, (updateFlags | IResource.KEEP_HISTORY) != 0, monitor);
    }

    public void setContents(final IFileState source, final int updateFlags,
            final IProgressMonitor monitor) throws CoreException
    {
        setContents(source, (updateFlags | IResource.FORCE)!= 0, (updateFlags | IResource.KEEP_HISTORY) != 0, monitor);
    }

    @Override
    public IPath getLocation()
    {
        File concreteFile = ensureConcreteFile();
        return Path.fromOSString(concreteFile.getAbsolutePath());
    }

    private File ensureConcreteFile()
    {
        if (_concreteFile == null)
        {
            String tempFileName = getFullPath().toString().replace('/', '_');
            try
            {
                _concreteFile = File.createTempFile(tempFileName, "."+getFullPath().getFileExtension());
                _concreteFile.deleteOnExit();
                assertTrue(_concreteFile.exists());
                if (_contents != null)
                {
                    JSFTestUtil.saveToFileSystem(_contents, _concreteFile.toURI());
                }
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        return _concreteFile;
    }
}
