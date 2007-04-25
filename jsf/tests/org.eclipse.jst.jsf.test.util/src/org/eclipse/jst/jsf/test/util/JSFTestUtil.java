package org.eclipse.jst.jsf.test.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

import org.eclipse.core.internal.net.ProxyData;
import org.eclipse.core.internal.net.ProxyManager;
import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.net.proxy.IProxyService;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.wst.validation.internal.ConfigurationManager;
import org.eclipse.wst.validation.internal.GlobalConfiguration;
import org.osgi.framework.Bundle;

/**
 * Test utility methods
 * 
 * @author cbateman
 *
 */
public class JSFTestUtil
{
    /**
     * Used to turn off build validation to speed up testing
     * 
     * @param isEnabled
     * @throws InvocationTargetException 
     * @throws InvocationTargetException
     */
    public static void setValidationEnabled(boolean isEnabled) throws InvocationTargetException
    {
        final GlobalConfiguration config = new GlobalConfiguration(ConfigurationManager.getManager().getGlobalConfiguration());
        config.setDisableAllValidation(!isEnabled);
        config.passivate();
        config.store();
    }
    
    /**
     * @param proxied 
     * @param proxyHostName 
     * @param proxyPort 
     */
    public static void setInternetProxyPreferences(final boolean proxied, final String proxyHostName, final String proxyPort)
    {
//        IProxyService proxy = ProxyManager.getProxyManager();
//
//        if (proxied)
//        {
//            ProxyData proxyData = new ProxyData(IProxyData.HTTP_PROXY_TYPE);
//            proxyData.setHost(proxyHostName);
//            proxyData.setPassword(proxyPort);
//            try
//            {
//                proxy.setProxyData(new ProxyData[] {proxyData});
//                proxy.setProxiesEnabled(true);
//            }
//            catch (CoreException ce)
//            {
//                // TODO: is this recoverable? Maybe should throw up.
//                Activator.log("Error setting web proxy.  Tests may fail or take a long time to run", ce);
//            }
//        }
//        else
//        {
//            proxy.setProxiesEnabled(false);
//        }
    }
    
    /**
     * Loads the source file in bundle called fileName into the jdtTestEnvironment
     * under srcFolderName/packageName.beanClassName
     * 
     * @param bundle
     * @param fileName
     * @param beanClassName
     * @param srcFolderName
     * @param packageName
     * @param jdtTestEnvironment
     * @throws Exception
     */
    public static void loadSourceClass(final Bundle bundle, 
                                       final String fileName, 
                                       final String beanClassName,
                                       final String srcFolderName,
                                       final String packageName,
                                       final JDTTestEnvironment jdtTestEnvironment) throws Exception
    {
        TestFileResource codeRes = new TestFileResource();
        codeRes.load(bundle, fileName);
        String code = codeRes.toString();
        jdtTestEnvironment.addSourceFile(srcFolderName, packageName, beanClassName, code);
    }
    
    public static URI getPlatformAbsPath(String relativePath) throws MalformedURLException, URISyntaxException
    {
        URL url = new URL(Platform.getInstanceLocation().getURL(), relativePath);
        return url.toURI();
    }
    
    public static IPath getAbsolutePath(Bundle bundle, String relativePath) throws IOException, URISyntaxException
    {
        URL url = FileLocator.resolve(bundle.getEntry(relativePath));
        return new Path(new File(url.toURI()).getAbsolutePath());//url.toExternalForm();
    }
    
    public static void savePlatformRelative(TestFileResource testFile, String relativePath) throws IOException, URISyntaxException
    {
        saveToFileSystem(testFile, getPlatformAbsPath(relativePath));
    }
    
    public static void saveToFileSystem(TestFileResource testFile, URI absPath) throws IOException
    {
        saveToFileSystem(testFile.toBytes(), absPath);
    }
    
    public static void saveToFileSystem(byte[] buffer, URI absPath) throws IOException
    {
        final File file = new File(absPath);
        
        FileOutputStream  outFile = null;
        
        try
        {
            outFile=new FileOutputStream(file);
            outFile.write(buffer);
        }
        finally
        {
            if (outFile != null)
            {
                outFile.close();
            }
        }

    }
    
    /**
     * @param testFile
     * @param absPath
     * @return true if the contents of testFile and the contents of what absPath point to
     * are the same based on a byte for byte comparison (Arrays.equal(byte[], byte[]).
     * 
     * @throws IOException
     */
    public static boolean areEqual(TestFileResource testFile, URI absPath) throws IOException
    {
        final File file = new File(absPath);
            
        return Arrays.equals(loadFromFile(file).toByteArray(), testFile.toBytes());
    }
    
    public static ByteArrayOutputStream loadFromFile(File file) throws IOException
    {
        FileInputStream  inFile = null;
        ByteArrayOutputStream buffer = null;

        try
        {
            inFile=new FileInputStream(file);
            
            buffer = new ByteArrayOutputStream();
            byte[]  inBuffer = new byte[1024];
            int bytesRead;
            int curPos = 0;
            while ((bytesRead = inFile.read(inBuffer)) != -1)
            {
                buffer.write(inBuffer,0,bytesRead);
                curPos+=bytesRead;
            }
            
            return buffer;
        }
        finally
        {
            if (inFile != null)
            {
                inFile.close();
            }
        }
    }
}
