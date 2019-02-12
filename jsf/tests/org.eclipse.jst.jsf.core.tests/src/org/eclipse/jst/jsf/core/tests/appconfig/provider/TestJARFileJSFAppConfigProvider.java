/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.appconfig.provider;

import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import junit.framework.TestCase;

import org.eclipse.core.internal.net.ProxyData;
import org.eclipse.core.internal.net.ProxyManager;
import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.net.proxy.IProxyService;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.JARFileJSFAppConfigProvider;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.core.jsfappconfig.RuntimeClasspathJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.internal.IJSFAppConfigManager;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.common.componentcore.resources.IVirtualContainer;

/**
 * Unit test for JARFileJSFAppConfigProvider, the app config provider used to
 * load faces-config models from jars (i.e. classpath libraries).
 * 
 * @author cbateman
 * 
 */
public class TestJARFileJSFAppConfigProvider extends TestCase
{
    private final static String       NO_EXT_DATA_JAR_PATH   =
        "/testfiles/appconfig/noextdata.jar";
    private final static String       WITH_EXT_DATA_JAR_PATH =
        "/testfiles/appconfig/withextdata.jar";
    private final static String       NO_FACES_CONFIG_FILE   =
        "/testfiles/appconfig/fail2_nofacesconfig.jar";

    private final static String       FAIL_JAR_PATH          =
        "/testfiles/appconfig/fail2.jar";

    private WebProjectTestEnvironment _testEnv;
    private JDTTestEnvironment        _jdtTestEnv;

    private IClasspathEntry           _noExtData;
    private IClasspathEntry           _withExtData;
    private IClasspathEntry           _noFacesConfigFile;
    private IResource                 _copiedIntoProject;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        setInternetPrefs();
        // JSFTestUtil.setInternetProxyPreferences
        // (true, "www-proxy.us.oracle.com", "80");

        _testEnv =
            new WebProjectTestEnvironment("ELValidationTest_"
                    + this.getClass().getName() + "_" + getName());
        _testEnv.createProject(false);
        assertNotNull(_testEnv);
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        _copiedIntoProject =
            _testEnv.loadResourceInWebRoot(TestsPlugin.getDefault()
                    .getBundle(), FAIL_JAR_PATH, "WEB-INF/lib/fail2.jar");
        assertNotNull(_copiedIntoProject);
        assertTrue(_copiedIntoProject.exists());

        _jdtTestEnv = new JDTTestEnvironment(_testEnv);
        _noExtData =
            _jdtTestEnv.addJarClasspathEntry(TestsPlugin.getDefault()
                    .getBundle(), NO_EXT_DATA_JAR_PATH);
        assertNotNull(_noExtData);

        _withExtData =
            _jdtTestEnv.addJarClasspathEntry(TestsPlugin.getDefault()
                    .getBundle(), WITH_EXT_DATA_JAR_PATH);
        assertNotNull(_withExtData);

        _noFacesConfigFile =
            _jdtTestEnv.addJarClasspathEntry(TestsPlugin.getDefault()
                    .getBundle(), NO_FACES_CONFIG_FILE);
        assertNotNull(_noFacesConfigFile);

        // initialize test case for faces 1.1
        final JSFFacetedTestEnvironment jsfFacedEnv =
            new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();

        // ensure the project can be deleted (no jar lock:
        // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=195867)
        final IVirtualContainer container = _testEnv.getWebRoot(false, false);
        assertNotNull(container);
        final IFile file =
            container.getFile("WEB-INF/lib/fail2.jar").getUnderlyingFile();
        assertNotNull(file);
        assertTrue(file.exists());
        _testEnv.getTestProject().delete(true, null);
        assertFalse(_testEnv.getTestProject().exists());
        assertFalse(file.exists());
    }

    /**
     * Tests model load of a simple jar-based faces config file that has no
     * extension data in it.
     */
    public void testNoExtensionData() throws Exception
    {
        final JARFileJSFAppConfigProvider provider = createProvider(_noExtData, true);
        final FacesConfigType facesConfig = provider.getFacesConfigModel();
        assertNotNull(facesConfig);

        verifyCommonElements(facesConfig);
    }

    /**
     * This is a regression for
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=181643
     * 
     * At the present time we are not able to support extension data inside of
     * jar file-based facesConfig files because the EMF2DOMSEE adapter doesn't
     * support jar-loading and we rely on this class for our renderer
     * workarounds.
     * 
     * @throws Exception
     */
    public void testWithExtensionData() throws Exception
    {
        final JARFileJSFAppConfigProvider provider =
            createProvider(_withExtData, true);
        final FacesConfigType facesConfig = provider.getFacesConfigModel();
        assertNotNull(facesConfig);

        verifyCommonElements(facesConfig);

        final ComponentType componentType =
            (ComponentType) facesConfig.getComponent().get(0);
        assertEquals(1, componentType.getComponentExtension().size());

        final ComponentExtensionType extType =
            (ComponentExtensionType) componentType.getComponentExtension()
            .get(0);
        // this value should actually be 1, but we are not able to use our
        // worked-around
        // translation renderer for jar files. This assertion is intended to
        // break
        // once translation of ANY content is fixed for JAR-contained
        // faces-config models
        assertEquals(0, extType.getChildNodes().size());
    }

    public void testJarCopiedInProject() throws Exception
    {
        final JARFileJSFAppConfigProvider provider =
            createProvider((IFile) _copiedIntoProject);
        final FacesConfigType facesConfig = provider.getFacesConfigModel();
        assertNotNull(facesConfig);
    }

    public void testNoFacesConfigJar() throws Exception
    {
        final JARFileJSFAppConfigProvider provider =
            createProvider(_noFacesConfigFile, false);
        final FacesConfigType facesConfig = provider.getFacesConfigModel();
        assertNull(facesConfig);
    }

    private void verifyCommonElements(final FacesConfigType facesConfig)
    {
        assertEquals(1, facesConfig.getComponent().size());
        final ComponentType component =
            (ComponentType) facesConfig.getComponent().get(0);
        assertEquals("com.ibm.odc.jsf.RichTextEditor", component
                .getComponentType().getTextContent());
        assertEquals(
                "com.ibm.odc.jsf.components.components.rte.UIRichTextEditor",
                component.getComponentClass().getTextContent());

        assertEquals(1, facesConfig.getManagedBean().size());
        final ManagedBeanType managedBean =
            (ManagedBeanType) facesConfig.getManagedBean().get(0);
        assertEquals("jarBean", managedBean.getManagedBeanName()
                .getTextContent());
        assertEquals("java.util.List", managedBean.getManagedBeanClass()
                .getTextContent());
        assertEquals("request", managedBean.getManagedBeanScope()
                .getTextContent());
    }

    private JARFileJSFAppConfigProvider createProvider(final IFile file)
    throws Exception
    {
        final String libName = file.getLocation().toOSString();

        final IJSFAppConfigLocater locator =
            new RuntimeClasspathJSFAppConfigLocater();
        
        //until setJSFAppConfigManager accepts IJSFAppConfigManagers, leave deprecated method call
        locator.setJSFAppConfigManager((IJSFAppConfigManager)JSFAppConfigManager.getInstance(_testEnv
                .getTestProject()));
        final JARFileJSFAppConfigProvider provider =
            new JARFileJSFAppConfigProvider(libName);
        provider.setJSFAppConfigLocater(locator);
        return provider;
    }

    private JARFileJSFAppConfigProvider createProvider(final IClasspathEntry forJar,
            final boolean assertFacesConfig) throws Exception
            {
        final String libName = getLibraryName(forJar, assertFacesConfig);

        final IJSFAppConfigLocater locator =
            new RuntimeClasspathJSFAppConfigLocater();
        
        //until setJSFAppConfigManager accepts IJSFAppConfigManagers, leave deprecated method call
        locator.setJSFAppConfigManager((IJSFAppConfigManager)JSFAppConfigManager.getInstance(_testEnv
                .getTestProject()));
        final JARFileJSFAppConfigProvider provider =
            new JARFileJSFAppConfigProvider(libName);
        provider.setJSFAppConfigLocater(locator);
        return provider;
            }

    private static String getLibraryName(final IClasspathEntry classPathEntry,
            final boolean assertFacesConfig) throws Exception
            {
        final IPath libraryPath = classPathEntry.getPath();

        final String libraryPathString = libraryPath.toOSString();

        if (assertFacesConfig)
        {
            JarFile jarFile = null;

            try
            {
                jarFile = new JarFile(libraryPathString, false);
                if (jarFile != null)
                {
                    final JarEntry jarEntry =
                        jarFile
                        .getJarEntry(JSFAppConfigUtils.FACES_CONFIG_IN_JAR_PATH);
                    assertNotNull(jarEntry);
                }
            }
            finally
            {
                if (jarFile != null)
                {
                    jarFile.close();
                }
            }
        }

        return libraryPathString;
            }

    private static void setInternetPrefs() throws Exception
    {
        final IProxyService proxy = ProxyManager.getProxyManager();

        final ProxyData proxyData = new ProxyData(IProxyData.HTTP_PROXY_TYPE);
        proxyData.setHost("www-proxy.us.oracle.com");
        proxyData.setPassword("80");
        proxy.setProxyData(new ProxyData[]
                                         { proxyData });
        proxy.setProxiesEnabled(true);
    }
}
