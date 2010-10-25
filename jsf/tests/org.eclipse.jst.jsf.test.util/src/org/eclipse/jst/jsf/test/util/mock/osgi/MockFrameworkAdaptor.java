package org.eclipse.jst.jsf.test.util.mock.osgi;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.eclipse.osgi.framework.adaptor.BundleClassLoader;
import org.eclipse.osgi.framework.adaptor.BundleData;
import org.eclipse.osgi.framework.adaptor.BundleOperation;
import org.eclipse.osgi.framework.adaptor.BundleProtectionDomain;
import org.eclipse.osgi.framework.adaptor.BundleWatcher;
import org.eclipse.osgi.framework.adaptor.ClassLoaderDelegate;
import org.eclipse.osgi.framework.adaptor.EventPublisher;
import org.eclipse.osgi.framework.adaptor.FrameworkAdaptor;
import org.eclipse.osgi.framework.adaptor.PermissionStorage;
import org.eclipse.osgi.framework.log.FrameworkLog;
import org.eclipse.osgi.framework.log.FrameworkLogEntry;
import org.eclipse.osgi.service.resolver.PlatformAdmin;
import org.eclipse.osgi.service.resolver.State;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.Version;

public class MockFrameworkAdaptor implements FrameworkAdaptor
{
    public void initialize(final EventPublisher eventPublisher)
    {
        // throw new UnsupportedOperationException();
    }

    public void initializeStorage() throws IOException
    {
        // throw new UnsupportedOperationException();
    }

    public void compactStorage() throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public Properties getProperties()
    {
        return new Properties();
    }

    public BundleData[] getInstalledBundles()
    {
        return null;
    }

    public URLConnection mapLocationToURLConnection(final String location)
            throws BundleException
    {
        throw new UnsupportedOperationException();
    }

    public BundleOperation installBundle(final String location,
            final URLConnection source)
    {
        throw new UnsupportedOperationException();
    }

    public BundleOperation updateBundle(final BundleData bundledata,
            final URLConnection source)
    {
        throw new UnsupportedOperationException();

    }

    public BundleOperation uninstallBundle(final BundleData bundledata)
    {
        throw new UnsupportedOperationException();
    }

    public long getTotalFreeSpace() throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public PermissionStorage getPermissionStorage() throws IOException
    {
        return new PermissionStorage()
        {

            public void setPermissionData(final String location,
                    final String[] data) throws IOException
            {
                throw new UnsupportedOperationException();

            }

            public void saveConditionalPermissionInfos(final String[] infos)
                    throws IOException
            {
                throw new UnsupportedOperationException();

            }

            public String[] getPermissionData(final String location)
                    throws IOException
            {
                return null;
            }

            public String[] getLocations() throws IOException
            {
                return null;
            }

            public String[] getConditionalPermissionInfos()
                    throws IOException
            {
                return null;
            }
        };
    }

    public void frameworkStart(final BundleContext context)
            throws BundleException
    {
        throw new UnsupportedOperationException();
    }

    public void frameworkStop(final BundleContext context)
            throws BundleException
    {
        throw new UnsupportedOperationException();
    }

    public void frameworkStopping(final BundleContext context)
    {
        throw new UnsupportedOperationException();
    }

    public int getInitialBundleStartLevel()
    {
        throw new UnsupportedOperationException();
    }

    public void setInitialBundleStartLevel(final int value)
    {
        throw new UnsupportedOperationException();
    }

    public FrameworkLog getFrameworkLog()
    {
        return new FrameworkLog()
        {

            public void log(final FrameworkEvent frameworkEvent)
            {
                log(new FrameworkLogEntry(
                        frameworkEvent.getBundle().getSymbolicName() == null ? frameworkEvent
                                .getBundle().getLocation() : frameworkEvent
                                .getBundle().getSymbolicName(),
                        FrameworkLogEntry.ERROR,
                        0,
                        "FrameworkEvent.ERROR", 0, frameworkEvent.getThrowable(), null)); //$NON-NLS-1$
            }

            public void log(final FrameworkLogEntry logEntry)
            {
                System.err.print(logEntry.getEntry() + " "); //$NON-NLS-1$
                System.err.println(logEntry.getMessage());
                if (logEntry.getThrowable() != null)
                {
                    logEntry.getThrowable().printStackTrace(System.err);
                }
            }

            public void setWriter(final Writer newWriter,
                    final boolean append)
            {
                // do nothing
            }

            public void setFile(final File newFile, final boolean append)
                    throws IOException
            {
                // do nothing
            }

            public File getFile()
            {
                // do nothing
                return null;
            }

            public void setConsoleLog(final boolean consoleLog)
            {
                // do nothing
            }

            public void close()
            {
                // do nothing
            }
        };
    }

    public BundleData createSystemBundleData() throws BundleException
    {
        return new BundleData()
        {

            public void setStatus(final int value)
            {
                // TODO Auto-generated method stub

            }

            public void setStartLevel(final int value)
            {
                // TODO Auto-generated method stub

            }

            public void setBundle(final Bundle bundle)
            {
                // TODO Auto-generated method stub

            }

            public void save() throws IOException
            {
                // TODO Auto-generated method stub

            }

            public void open() throws IOException
            {
                // TODO Auto-generated method stub

            }

            public void installNativeCode(final String[] nativepaths)
                    throws BundleException
            {
                // TODO Auto-generated method stub

            }

            public Version getVersion()
            {
                // TODO Auto-generated method stub
                return null;
            }

            public int getType()
            {
                // TODO Auto-generated method stub
                return 0;
            }

            public String getSymbolicName()
            {
                // TODO Auto-generated method stub
                return null;
            }

            public int getStatus()
            {
                // TODO Auto-generated method stub
                return 0;
            }

            public int getStartLevel()
            {
                // TODO Auto-generated method stub
                return 0;
            }

            @SuppressWarnings("rawtypes")
            public Dictionary getManifest() throws BundleException
            {
                // TODO Auto-generated method stub
                return null;
            }

            public String getLocation()
            {
                // TODO Auto-generated method stub
                return null;
            }

            public long getLastModified()
            {
                // TODO Auto-generated method stub
                return 0;
            }

            public String getExecutionEnvironment()
            {
                // TODO Auto-generated method stub
                return null;
            }

            @SuppressWarnings("rawtypes")
            public Enumeration getEntryPaths(final String path)
            {
                // TODO Auto-generated method stub
                return null;
            }

            public URL getEntry(final String path)
            {
                // TODO Auto-generated method stub
                return null;
            }

            public String getDynamicImports()
            {
                // TODO Auto-generated method stub
                return null;
            }

            public File getDataFile(final String path)
            {
                // TODO Auto-generated method stub
                return null;
            }

            public String[] getClassPath() throws BundleException
            {
                // TODO Auto-generated method stub
                return null;
            }

            public long getBundleID()
            {
                // TODO Auto-generated method stub
                return 0;
            }

            public String getActivator()
            {
                // TODO Auto-generated method stub
                return null;
            }

            public String findLibrary(final String libname)
            {
                // TODO Auto-generated method stub
                return null;
            }

            public BundleClassLoader createClassLoader(
                    final ClassLoaderDelegate delegate,
                    final BundleProtectionDomain domain,
                    final String[] bundleclasspath)
            {
                // TODO Auto-generated method stub
                return null;
            }

            public void close() throws IOException
            {
                // TODO Auto-generated method stub

            }
        };
    }

    public BundleWatcher getBundleWatcher()
    {
        throw new UnsupportedOperationException();

    }

    public PlatformAdmin getPlatformAdmin()
    {
        throw new UnsupportedOperationException();

    }

    public State getState()
    {
        throw new UnsupportedOperationException();

    }

    public ClassLoader getBundleClassLoaderParent()
    {
        throw new UnsupportedOperationException();

    }

    public void handleRuntimeError(final Throwable error)
    {
        throw new UnsupportedOperationException();
    }

	public Enumeration<URL> findEntries(List<BundleData> datas, String path,
			String filePattern, int options) {
		return null;
	}
}