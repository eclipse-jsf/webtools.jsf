package org.eclipse.jst.jsf.test.util.mock;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;

public class MockBundle implements Bundle
{
    private final String _bundleRootPath;

    public MockBundle(final String bundleRootPath)
    {
        _bundleRootPath = bundleRootPath;
    }

    public int getState()
    {
        throw new UnsupportedOperationException();
    }

    public void start(int options) throws BundleException
    {
        throw new UnsupportedOperationException();
    }

    public void start() throws BundleException
    {
        throw new UnsupportedOperationException();
    }

    public void stop(int options) throws BundleException
    {
        throw new UnsupportedOperationException();
    }

    public void stop() throws BundleException
    {
        throw new UnsupportedOperationException();
    }

    public void update(InputStream input) throws BundleException
    {
        throw new UnsupportedOperationException();
    }

    public void update() throws BundleException
    {
        throw new UnsupportedOperationException();
    }

    public void uninstall() throws BundleException
    {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("rawtypes")
    public Dictionary getHeaders()
    {
        throw new UnsupportedOperationException();
    }

    public long getBundleId()
    {
        throw new UnsupportedOperationException();
    }

    public String getLocation()
    {
        throw new UnsupportedOperationException();
    }

    public ServiceReference[] getRegisteredServices()
    {
        throw new UnsupportedOperationException();
    }

    public ServiceReference[] getServicesInUse()
    {
        throw new UnsupportedOperationException();
    }

    public boolean hasPermission(Object permission)
    {
        throw new UnsupportedOperationException();
    }

    public URL getResource(String name)
    {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("rawtypes")
    public Dictionary getHeaders(String locale)
    {
        throw new UnsupportedOperationException();
    }

    public String getSymbolicName()
    {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("rawtypes")
    public Class loadClass(String name) throws ClassNotFoundException
    {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getResources(String name) throws IOException
    {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getEntryPaths(String path)
    {
        throw new UnsupportedOperationException();
    }

    public URL getEntry(String path)
    {
        IPath entryPath = new Path(_bundleRootPath).append(path);
        try
        {
            return entryPath.toFile().toURL();
        } catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public long getLastModified()
    {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("rawtypes")
    public Enumeration findEntries(String path, String filePattern,
            boolean recurse)
    {
        throw new UnsupportedOperationException();
    }

    public BundleContext getBundleContext()
    {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("rawtypes")
    public Map getSignerCertificates(int signersType)
    {
        throw new UnsupportedOperationException();
    }

    public Version getVersion()
    {
        throw new UnsupportedOperationException();
    }
}