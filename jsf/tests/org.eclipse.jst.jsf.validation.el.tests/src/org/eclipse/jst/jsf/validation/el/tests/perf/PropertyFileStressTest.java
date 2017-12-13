package org.eclipse.jst.jsf.validation.el.tests.perf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.designtime.internal.symbols.ResourceBundleMapSourceFactory;
import org.eclipse.jst.jsf.test.util.PerfTracker;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.validation.el.tests.ELValidationTestPlugin;
import org.eclipse.jst.jsf.validation.el.tests.base.JSPTestCase;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;

public class PropertyFileStressTest extends JSPTestCase
{

    private IFile _propertiesFile;

    public PropertyFileStressTest()
    {
        super(JSFVersion.V1_1, SingleJSPTestCase.FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        // add a resource bundle
        final TestFileResource resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(),
                "/testdata/classes/stress1.properties.data");
        _propertiesFile = _jdtTestEnv.addResourceFile("src",
                new ByteArrayInputStream(resource.toBytes()), "res",
                "stress1.properties");
        assertTrue(_propertiesFile.isAccessible());
    }

    @Override
    protected void tearDown() throws Exception
    {
        // TODO Auto-generated method stub
        super.tearDown();
    }

    @Override
    public void testSanity() throws RuntimeException
    {
        super.testSanity();
        Map<?,?> resourceBundleMapSource;
        try
        {
            resourceBundleMapSource = ResourceBundleMapSourceFactory
                    .getResourceBundleMapSource(_testEnv.getTestProject(),
                            "res.stress1");
        } catch (JavaModelException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        } catch (CoreException e)
        {
            throw new RuntimeException(e);
        }
        assertEquals(8687, resourceBundleMapSource.size());
    }

    public void testMapDescriptor_GetProperties() throws JavaModelException,
            IOException, CoreException
    {
        final int NUM_RESOURCE_TESTS = 1000;
        final PerfTracker perfTracker = new PerfTracker(
                "testMapDescriptor_GetProperties", NUM_RESOURCE_TESTS);

        for (int i = 0; i < NUM_RESOURCE_TESTS; i++)
        {
            final Map<?,?> mapSource = ResourceBundleMapSourceFactory
                    .getResourceBundleMapSource(_testEnv.getTestProject(),
                            "res.stress1");
            final IMapTypeDescriptor typeDesc = SymbolFactory.eINSTANCE
                    .createIMapTypeDescriptor();
            typeDesc.setMapSource(mapSource);
            final long startTime = System.nanoTime();
            typeDesc.getProperties();
            perfTracker.recordTime(System.nanoTime() - startTime);
        }
        perfTracker.printReport(System.out);
    }

    public void testResourceMapSourceFactory() throws JavaModelException,
            IOException, CoreException
    {
        final int NUM_RESOURCE_TESTS = 1000;
        final PerfTracker perfTracker = new PerfTracker(
                "testResourceMapSourceFactory", NUM_RESOURCE_TESTS);

        for (int i = 0; i < NUM_RESOURCE_TESTS; i++)
        {
            final long startTime = System.nanoTime();
            @SuppressWarnings("unused")
            Map<?,?> resourceBundleMapSource = ResourceBundleMapSourceFactory
                    .getResourceBundleMapSource(_testEnv.getTestProject(),
                            "res.stress1");
            perfTracker.recordTime(System.nanoTime() - startTime);
        }
        perfTracker.printReport(System.out);
    }

    public void testResourceMapSource_GetEntrySet() throws JavaModelException,
            IOException, CoreException
    {
        final int NUM_RESOURCE_TESTS = 1000;
        final PerfTracker perfTracker = new PerfTracker(
                "testResourceMapSource_GetEntrySet", NUM_RESOURCE_TESTS);

        for (int i = 0; i < NUM_RESOURCE_TESTS; i++)
        {
            Map<?,?> resourceBundleMapSource = ResourceBundleMapSourceFactory
                    .getResourceBundleMapSource(_testEnv.getTestProject(),
                            "res.stress1");
            final long startTime = System.nanoTime();
                resourceBundleMapSource.entrySet();
            perfTracker.recordTime(System.nanoTime() - startTime);
        }
        perfTracker.printReport(System.out);
    }

}
