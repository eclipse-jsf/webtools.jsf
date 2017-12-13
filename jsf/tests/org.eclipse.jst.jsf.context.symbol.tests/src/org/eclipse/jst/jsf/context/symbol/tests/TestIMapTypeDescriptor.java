package org.eclipse.jst.jsf.context.symbol.tests;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.designtime.internal.symbols.ResourceBundleMapSourceFactory;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;

public class TestIMapTypeDescriptor extends ModelBaseTestCase
{
    private IFile _propertyFile;

    protected void setUp() throws Exception
    {
        super.setUp();
        // add a resource bundle to the default package to test regression on
        // bug 144525
        final TestFileResource resource = new TestFileResource();
        resource.load(ContextSymbolTestPlugin.getDefault().getBundle(),
                "/testdata/bundle.properties.data");
        _propertyFile = _jdtTestEnvironment.addResourceFile("src",
                new ByteArrayInputStream(resource.toBytes()), "res",
                "Bundle.properties");
        assertTrue(_propertyFile.isAccessible());
    }

    public void testResBoundDescriptor() throws JavaModelException,
            IOException, CoreException
    {
        final Map mapSource = ResourceBundleMapSourceFactory
                .getResourceBundleMapSource(_jdtTestEnvironment
                        .getProjectEnvironment().getTestProject(), "res.Bundle");
        final IMapTypeDescriptor typeDesc = SymbolFactory.eINSTANCE
                .createIMapTypeDescriptor();
        typeDesc.setMapSource(mapSource);
        Map<String, IPropertySymbol> map = createMap(typeDesc);
        assertEquals(3, map.size());

        assertTrue(map.containsKey("simpleprop"));
        assertFalse(map.get("simpleprop").isIntermediate());
        assertTrue(map.containsKey("two"));
        assertTrue(map.get("two").isIntermediate());
        assertTrue(map.containsKey("three"));
        assertTrue(map.get("three").isIntermediate());
    }

    public void testDeleteBundle() throws Exception
    {
        final Map mapSource = ResourceBundleMapSourceFactory
                .getResourceBundleMapSource(_jdtTestEnvironment
                        .getProjectEnvironment().getTestProject(), "res.Bundle");
        final IMapTypeDescriptor typeDesc = SymbolFactory.eINSTANCE
                .createIMapTypeDescriptor();
        typeDesc.setMapSource(mapSource);
        Map<String, IPropertySymbol> map = createMap(typeDesc);
        assertEquals(3, map.size());
        deleteAndWaitFor(_propertyFile);
        EList properties = typeDesc.getProperties();
        assertEquals(0, properties.size());
    }

    public void testDeleteAndRecreate() throws Exception
    {
        {
            final Map mapSource = ResourceBundleMapSourceFactory
                    .getResourceBundleMapSource(_jdtTestEnvironment
                            .getProjectEnvironment().getTestProject(),
                            "res.Bundle");
            final IMapTypeDescriptor typeDesc = SymbolFactory.eINSTANCE
                    .createIMapTypeDescriptor();
            typeDesc.setMapSource(mapSource);
            Map<String, IPropertySymbol> map = createMap(typeDesc);
            assertEquals(3, map.size());
        }

        deleteAndWaitFor(_propertyFile);

        {
            final TestFileResource resource = new TestFileResource();
            resource.load(ContextSymbolTestPlugin.getDefault().getBundle(),
                    "/testdata/bundle2.properties.data");
            _propertyFile = _jdtTestEnvironment.addResourceFile("src",
                    new ByteArrayInputStream(resource.toBytes()), "res",
                    "Bundle.properties");

            final Map mapSource = ResourceBundleMapSourceFactory
                    .getResourceBundleMapSource(_jdtTestEnvironment
                            .getProjectEnvironment().getTestProject(),
                            "res.Bundle");
            final IMapTypeDescriptor typeDesc = SymbolFactory.eINSTANCE
                    .createIMapTypeDescriptor();
            typeDesc.setMapSource(mapSource);
            Map<String, IPropertySymbol> map = createMap(typeDesc);
            // it's different bundle but with the same name
            assertEquals(4, map.size());
        }
    }

    private void deleteAndWaitFor(final IResource res)
    {
        JSFTestUtil.safeDelete(res, 10, 500);
        for (int i = 0; i < 10; i++)
        {
            if (res.isAccessible())
            {
                try
                {
                    Thread.sleep(500);
                } catch (InterruptedException e)
                {
                    // do nothing
                }
            }
        }
        assertFalse(res.isAccessible());
    }

    private Map<String, IPropertySymbol> createMap(final IMapTypeDescriptor desc)
    {
        final Map<String, IPropertySymbol> map = new HashMap<String, IPropertySymbol>();
        EList properties = desc.getProperties();
        for (final Iterator it = properties.iterator(); it.hasNext();)
        {
            IPropertySymbol prop = (IPropertySymbol) it.next();
            map.put(prop.getName(), prop);
        }
        return map;
    }
}
