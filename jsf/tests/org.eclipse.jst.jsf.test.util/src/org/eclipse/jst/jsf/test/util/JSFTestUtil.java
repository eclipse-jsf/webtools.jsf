/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.test.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.zip.ZipFile;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.validation.ValidationFramework;
import org.eclipse.wst.validation.internal.ConfigurationManager;
import org.eclipse.wst.validation.internal.GlobalConfiguration;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.osgi.framework.Bundle;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Test utility methods
 *
 * @author cbateman
 *
 */
public final class JSFTestUtil
{
    private static boolean     _enableTrace = false;
    
    /**
     * Setting to true enables internal tracing in this class.  The default
     * is false.  There is no thread safety.
     * 
     * @param enableTrace
     */
    public static void setEnableTrace(final boolean enableTrace)
    {
        _enableTrace = enableTrace;
    }
    
    /**
     * Used to turn off build validation to speed up testing
     *
     * @param isEnabled
     * @throws InvocationTargetException
     * @throws InvocationTargetException
     */
    public static void setValidationEnabled(final boolean isEnabled) throws InvocationTargetException
    {
        // old way (just in case)
        final GlobalConfiguration config = new GlobalConfiguration(ConfigurationManager.getManager().getGlobalConfiguration());
        config.setDisableAllValidation(!isEnabled);
        config.passivate();
        config.store();
        
        // new way
        ValidationFramework.getDefault().suspendAllValidation(!isEnabled);
    }

    /**
     * @param proxied
     * @param proxyHostName
     * @param proxyPort
     */
    public static void setInternetProxyPreferences(final boolean proxied, final String proxyHostName, final String proxyPort)
    {
        // disable
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
//
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
        final TestFileResource codeRes = new TestFileResource();
        codeRes.load(bundle, fileName);
        final String code = codeRes.toString();
        jdtTestEnvironment.addSourceFile(srcFolderName, packageName, beanClassName, code);
    }
    
    public static ZipFile createZipFile(final Bundle bundle, final String entryName) throws IOException, URISyntaxException
    {
        return new ZipFile(new File(getAbsolutePath(bundle, entryName).toOSString()));
    }

    public static URI getPlatformAbsPath(final String relativePath) throws MalformedURLException, URISyntaxException
    {
    	
        final File file = new File(Platform.getInstanceLocation().getURL().getFile() + File.separator + relativePath);
        return file.toURI();
    }

    public static IPath getAbsolutePath(final Bundle bundle, final String relativePath) throws IOException, URISyntaxException
    {
        final URL bundleUrl = bundle.getEntry(relativePath);
        Assert.assertNotNull(bundleUrl);
        final URL url = FileLocator.resolve(bundleUrl);
        return new Path(new File(url.getFile()).getAbsolutePath());
    }

    public static void savePlatformRelative(final TestFileResource testFile, final String relativePath) throws IOException, URISyntaxException
    {
        saveToFileSystem(testFile, getPlatformAbsPath(relativePath));
    }

    public static void saveToFileSystem(final TestFileResource testFile, final URI absPath) throws IOException
    {
        saveToFileSystem(testFile.toBytes(), absPath);
    }

    public static void saveToFileSystem(final byte[] buffer, final URI absPath) throws IOException
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
    public static boolean areEqual(final TestFileResource testFile, final URI absPath) throws IOException
    {
        final File file = new File(absPath);

        return Arrays.equals(loadFromFile(file).toByteArray(), testFile.toBytes());
    }

    public static ByteArrayOutputStream loadFromFile(final IFile file) throws IOException
    {
        InputStream inStream = null;

        try
        {
            inStream = file.getContents();
            return loadFromInputStream(inStream);
        }
        catch(final CoreException ce)
        {
            return new ByteArrayOutputStream();
        }
        finally
        {
            if (inStream != null)
            {
                inStream.close();
            }
        }
    }

    public static ByteArrayOutputStream loadFromFile(final File file) throws IOException
    {
        FileInputStream  inFile = null;

        try
        {
            inFile=new FileInputStream(file);
            return loadFromInputStream(inFile);
        }
        finally
        {
            if (inFile != null)
            {
                inFile.close();
            }
        }
    }

    /**
     * Caller is responsible for closing the stream
     * @param stream
     * @return the byte stream loaded from the file
     * @throws IOException
     */
    public static ByteArrayOutputStream loadFromInputStream(final InputStream stream) throws IOException
    {
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final byte[]  inBuffer = new byte[1024];
        int bytesRead;
        int curPos = 0;
        while ((bytesRead = stream.read(inBuffer)) != -1)
        {
            buffer.write(inBuffer,0,bytesRead);
            curPos+=bytesRead;
        }

        return buffer;
    }

    public static IndexedRegion getIndexedRegion(final IStructuredDocument document, final int documentOffset)
    {
        // C.B: most of this logic was copied from ContentAssistUtils.getNodeAt
        // I chose to copy rather than just call that because ContentAssistUtils is
        // internal
        final IStructuredModel model = getStructuredModel(document);
        IndexedRegion             region = null;
        if (model != null)
        {
            try
            {
                int lastOffset = documentOffset;
                region = model.getIndexedRegion(documentOffset);
                trace("Starting at region: "+region.toString());
                while (region == null && lastOffset >= 0) {
                    lastOffset--;
                    region = model.getIndexedRegion(lastOffset);
                    trace("Iterating on region: "+region.toString());
                }

                trace("Finished with: "+region.toString()+", Class: "+region.getClass());

                // now we assume we have an element.  But our context may be
                // on an attribute in that node, so we need to check
                if (region instanceof IDOMElement)
                {
                    trace("Region is an IDOMElement");
                    final IDOMElement domElement = (IDOMElement) region;

                    final NamedNodeMap attributes = domElement.getAttributes();

                    for (int i = 0; i < attributes.getLength(); i++)
                    {
                        final Node  attrNode = attributes.item(i);

                        if (attrNode instanceof IDOMAttr)
                        {
                            final IDOMAttr attr = (IDOMAttr) attrNode;
                            trace("Examining attribute: "+attr.toString());

                            if (documentOffset >= attr.getStartOffset()
                                    && documentOffset < attr.getEndOffset())
                            {
                                region = attr;
                                trace("Found attribute: "+region.toString());
                                break;
                            }
                        }
                    }
                }
            }
            finally
            {
            	trace("releasing model");
                model.releaseFromRead();
            }
        }

        trace("returning: "+region);
        return region;
    }

    private static void trace(final String message)
    {
        if (_enableTrace)
        {
            System.out.println("getIndexedRegion: "+message);
        }
    }

    /**
     * @param document
     * @return a structured model or null if one cannot be opened for document.
     * Note: the caller is responsible for releasing the instance of structured
     * model that gets returned.
     */
    private static IStructuredModel getStructuredModel(final IStructuredDocument document)
    {
        final IModelManager modelManager = StructuredModelManager.getModelManager();

        if (modelManager != null)
        {
            return StructuredModelManager.getModelManager().getModelForRead(document);
        }

        return null;
    }
    
    /**
     * Attempts to delete resource.  If the delete operation throws a 
     * CoreException, the call will sleep the thread for backOffInMs
     * before trying again.  Will try a maximum of 'maxTries' times.  If at that
     * point the resource still exists, an error will be logged and the method
     * will return.
     * 
     * @param resource
     * @param maxTries
     * @param backInMs
     * @return true if the delete was successful.
     */
    public static  boolean safeDelete(final IResource resource, final int maxTries, final int backOffInMs)
    {
        boolean success = false;

        DELETE_LOOP: for (int attempt = 0; attempt < maxTries; attempt++)
        {
            try
            {
                resource.delete(true, null);
                success = true;
                break DELETE_LOOP;
            }
            catch (CoreException e)
            {
                try
                {
                    Thread.sleep(backOffInMs);
                }
                catch (InterruptedException e1)
                {
                    // do nothing, just continue
                }
            }
        }
        if (!success)
        {
            System.err.println("Could not delete resource: "+resource.getLocation().toOSString());
        }
        return success;
    }

    private JSFTestUtil()
    {
    	// no instantiation
    }
}
