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
package org.eclipse.jst.jsf.core.tests.appconfig.provider;

import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.jsfappconfig.JARFileJSFAppConfigProvider;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigManager;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.core.jsfappconfig.RuntimeClasspathJSFAppConfigLocater;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

/**
 * Unit test for JARFileJSFAppConfigProvider, the app config provider
 * used to load faces-config models from jars (i.e. classpath libraries).
 * 
 * @author cbateman
 *
 */
public class TestJARFileJSFAppConfigProvider extends TestCase 
{
    private WebProjectTestEnvironment       _testEnv;
    private JDTTestEnvironment              _jdtTestEnv;
    
    private IClasspathEntry                 _noExtData;
    private IClasspathEntry                 _withExtData;
    
    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
        
        JSFTestUtil.setValidationEnabled(false);
        
        JSFTestUtil.setInternetProxyPreferences
                (true, "www-proxy.us.oracle.com", "80");

        _testEnv = new WebProjectTestEnvironment("ELValidationTest_"+this.getClass().getName()+"_"+getName());
        _testEnv.createProject(false);
        assertNotNull(_testEnv);       
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        _jdtTestEnv = new JDTTestEnvironment(_testEnv);
        _noExtData = _jdtTestEnv.addJarClasspathEntry(TestsPlugin.getDefault().getBundle(), "/testfiles/appconfig/noextdata.jar");
        assertNotNull(_noExtData);
        _withExtData = _jdtTestEnv.addJarClasspathEntry(TestsPlugin.getDefault().getBundle(), "/testfiles/appconfig/withextdata.jar");
        assertNotNull(_withExtData);
        
        // initialize test case for faces 1.2
        JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
        jsfFacedEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_2);
    }
    
    /**
     * Tests model load of a simple jar-based faces config file that has no extension data
     * in it.
     */
    public void testNoExtensionData() throws Exception
    {
        JARFileJSFAppConfigProvider provider = createProvider(_noExtData);
        FacesConfigType facesConfig = provider.getFacesConfigModel();
        assertNotNull(facesConfig);

        verifyCommonElements(facesConfig);
    }
    
    /**
     * This is a regression for https://bugs.eclipse.org/bugs/show_bug.cgi?id=181643
     * 
     * At the present time we are not able to support extension data inside of
     * jar file-based facesConfig files because the EMF2DOMSEE adapter doesn't
     * support jar-loading and we rely on this class for our renderer workarounds.
     * 
     * @throws Exception
     */
    public void testWithExtensionData() throws Exception
    {
        JARFileJSFAppConfigProvider provider = createProvider(_withExtData);
        FacesConfigType facesConfig = provider.getFacesConfigModel();
        assertNotNull(facesConfig);

        verifyCommonElements(facesConfig);
        
        ComponentType componentType = (ComponentType) facesConfig.getComponent().get(0);
        assertEquals(1, componentType.getComponentExtension().size());
        
        ComponentExtensionType extType = 
            (ComponentExtensionType) componentType.getComponentExtension().get(0);
        // this value should actually be 1, but we are not able to use our worked-around
        // translation renderer for jar files.  This assertion is intended to break
        // once translation of ANY content is fixed for JAR-contained faces-config models
        assertEquals(0, extType.getChildNodes().size());
    }
    
    private void verifyCommonElements(FacesConfigType facesConfig)
    {
        assertEquals(1, facesConfig.getComponent().size());
        ComponentType component = 
            (ComponentType) facesConfig.getComponent().get(0);
        assertEquals("com.ibm.odc.jsf.RichTextEditor", component.getComponentType().getTextContent());
        assertEquals("com.ibm.odc.jsf.components.components.rte.UIRichTextEditor", component.getComponentClass().getTextContent());
        
        assertEquals(1, facesConfig.getManagedBean().size());
        ManagedBeanType managedBean = 
            (ManagedBeanType) facesConfig.getManagedBean().get(0);
        assertEquals("jarBean", managedBean.getManagedBeanName().getTextContent());
        assertEquals("java.util.List", managedBean.getManagedBeanClass().getTextContent());
        assertEquals("request", managedBean.getManagedBeanScope().getTextContent());
    }
    
    private JARFileJSFAppConfigProvider createProvider(IClasspathEntry forJar) throws Exception
    {
        final String libName = getLibraryName(forJar);
        
        IJSFAppConfigLocater locator = new RuntimeClasspathJSFAppConfigLocater();
        locator.setJSFAppConfigManager(JSFAppConfigManager.getInstance(_testEnv.getTestProject()));
        JARFileJSFAppConfigProvider  provider = new JARFileJSFAppConfigProvider(libName);
        provider.setJSFAppConfigLocater(locator);
        return provider;
    }
    
    private static String getLibraryName(IClasspathEntry classPathEntry) throws Exception
    {
        IPath libraryPath = classPathEntry.getPath();

        final String libraryPathString = libraryPath.toOSString();         

        JarFile jarFile = null;
        
        try
        {
            jarFile = new JarFile(libraryPathString);
            if (jarFile != null) {
                JarEntry jarEntry = jarFile.getJarEntry(JSFAppConfigUtils.FACES_CONFIG_IN_JAR_PATH);
                if (jarEntry != null) {
                    return libraryPathString;
                }
            }
            return null;
        } finally {
            if (jarFile != null) {
                jarFile.close();
            }
        }
    }
}
