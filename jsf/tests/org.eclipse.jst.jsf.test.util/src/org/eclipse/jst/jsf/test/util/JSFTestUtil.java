/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
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
    /**
     * Used to turn off build validation to speed up testing
     *
     * @param isEnabled
     * @throws InvocationTargetException
     * @throws InvocationTargetException
     */
    public static void setValidationEnabled(final boolean isEnabled) throws InvocationTargetException
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

    public static URI getPlatformAbsPath(final String relativePath) throws MalformedURLException, URISyntaxException
    {
        final URL url = new URL(Platform.getInstanceLocation().getURL(), relativePath);
        return url.toURI();
    }

    public static IPath getAbsolutePath(final Bundle bundle, final String relativePath) throws IOException, URISyntaxException
    {
        final URL url = FileLocator.resolve(bundle.getEntry(relativePath));
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

    public static ByteArrayOutputStream loadFromFile(final File file) throws IOException
    {
        FileInputStream  inFile = null;
        ByteArrayOutputStream buffer = null;

        try
        {
            inFile=new FileInputStream(file);

            buffer = new ByteArrayOutputStream();
            final byte[]  inBuffer = new byte[1024];
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
    	System.out.println("getIndexedRegion: "+message);
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

    private JSFTestUtil()
    {
    	// no instantiation
    }
}
