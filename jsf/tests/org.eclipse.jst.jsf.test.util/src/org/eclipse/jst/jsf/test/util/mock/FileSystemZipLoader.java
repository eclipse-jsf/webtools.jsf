package org.eclipse.jst.jsf.test.util.mock;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.jst.jsf.test.util.mock.IWorkspaceContext.ZipFileLoader;

/**
 * Allows a zip to be loaded directly from the file system.
 * 
 * @author cbateman
 *
 */
public class FileSystemZipLoader extends ZipFileLoader
{
    private final File _file;
    private String _pathIntoZip;

    public FileSystemZipLoader(final File file)
    {
        this(file, "");
    }
    
    
    public FileSystemZipLoader(File file, String pathIntoZip)
    {
        _file = file;
        _pathIntoZip = pathIntoZip;
    }


    @Override
    public ZipFile getZipFile() throws ZipException, IOException
    {
        return new ZipFile(_file);
    }

    @Override
    public String getPathInZip()
    {
        return _pathIntoZip;
    }


    @Override
    public File getFile()
    {
        return _file;
    }
}
