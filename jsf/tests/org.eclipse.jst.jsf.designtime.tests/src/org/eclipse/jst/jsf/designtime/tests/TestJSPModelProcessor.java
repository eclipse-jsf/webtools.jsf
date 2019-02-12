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
package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.internal.jsp.JSPModelProcessor;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;

public class TestJSPModelProcessor extends TestCase
{
    private static final int NUM_JSPS = 25;
    private static final int WAIT_ITERATIONS = 50;
    private static final int WAIT_SLEEP_TIME_MS = 100;

    private IFile _testJSP1;
    private List<IFile> _jsps;

    private JSFFacetedTestEnvironment _jsfFactedTestEnvironment;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment =
            new WebProjectTestEnvironment("TestJSPModelProcessor_"+getName());
        projectTestEnvironment.createProject(false);

        final JDTTestEnvironment jdtTestEnvironment =
            new JDTTestEnvironment(projectTestEnvironment);

        final TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle(),
        "/testdata/bundle1.resources.data");
        jdtTestEnvironment.addResourceFile("src"
                , new ByteArrayInputStream(input.toBytes())
                , "bundles", "bundle1.properties");

        _testJSP1 = (IFile) projectTestEnvironment.loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle()
                , "/testdata/testdata1.jsp.data", "testdata1.jsp");

        _jsps = new ArrayList<IFile>(NUM_JSPS);
        for (int i = 0; i < NUM_JSPS; i++)
        {
            _jsps.add((IFile) projectTestEnvironment.loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle()
                    , "/testdata/testdata1.jsp.data", "testdata_"+i+".jsp"));
        }

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
    }

    @Override
    protected void tearDown() throws Exception {
    }

    public void testGet() throws Exception
    {
        final JSPModelProcessor processor = JSPModelProcessor.get(_testJSP1);
        assertNotNull(processor);
        assertFalse(processor.isDisposed());
    }

    public void testGetMapForScope() throws Exception
    {
        // if we not refreshed yet, then should be no symbols
        final JSPModelProcessor processor = JSPModelProcessor.get(_testJSP1);
        assertNotNull(processor);

        Map<Object, ISymbol> scopeMap =
            processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_REQUEST_STRING);
        assertTrue(scopeMap.isEmpty());

        scopeMap =
            processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_SESSION_STRING);
        assertTrue(scopeMap.isEmpty());

        scopeMap =
            processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_APPLICATION_STRING);
        assertTrue(scopeMap.isEmpty());

        scopeMap =
            processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_NONE_STRING);
        assertTrue(scopeMap.isEmpty());
    }

    public void testRefreshAndGet() throws Exception
    {
        final IModelManager modelManager = StructuredModelManager.getModelManager();

        IStructuredModel model = null;

        try
        {
            model = modelManager.getModelForRead(_testJSP1);

            // we should be the only one with a handle
            System.out.println(model.getReferenceCountForRead());
            assertFalse(model.isSharedForRead());

            // if we not refreshed yet, then should be no symbols
            final JSPModelProcessor processor = JSPModelProcessor.get(_testJSP1);
            assertNotNull(processor);
            System.out.println(model.getReferenceCountForRead());
            // we should be the only one with a handle
            assertFalse(model.isSharedForRead());

            Map<Object, ISymbol> scopeMap =
                processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_REQUEST_STRING);
            assertTrue(scopeMap.isEmpty());
            System.out.println(model.getReferenceCountForRead());
            // we should be the only one with a handle
            assertFalse(model.isSharedForRead());

            processor.refresh(!JSPModelProcessor.FORCE_REFRESH, JSPModelProcessor.RUN_ON_CURRENT_THREAD);
            System.out.println(model.getReferenceCountForRead());
            // we should be the only one with a handle
            assertFalse(model.isSharedForRead());

            // after refresh we should have a symbol for the loadBundle and the dataTable
            scopeMap =
                processor.getMapForScope(ISymbolConstants.SYMBOL_SCOPE_REQUEST_STRING);
            assertFalse(scopeMap.isEmpty());
            assertEquals(2, scopeMap.size());

            // we should be the only one with a handle
            System.out.println(model.getReferenceCountForRead());
            assertFalse(model.isSharedForRead());
        }
        finally
        {
            if (model != null)
            {
                model.releaseFromRead();
            }
        }
    }

    public void testFileDeletion_RegressionBug199480() throws Exception
    {
        // Regression for bug 199480
        // ensure that the deletion of a resource with a JSPModelProcessor
        // on it without an editor close event is still disposed of due
        // to the resource change event.
        // if we not refreshed yet, then should be no symbols
        final JSPModelProcessor processor = JSPModelProcessor.get(_testJSP1);
        assertNotNull(processor);
        assertFalse(processor.isDisposed());

        JSFTestUtil.safeDelete(_testJSP1, 10, 1000);

        // file is deleted, so the processor should dispose itself on the
        // resource change event
        waitForAndAssertProcessorDisposed(processor, true);
    }

    public void testProjectClosure() throws Exception
    {
        final IModelManager modelManager = StructuredModelManager.getModelManager();

        IStructuredModel model = null;

        try
        {
            model = modelManager.getModelForRead(_testJSP1);

            // we should be the only one with a handle
//            assertFalse(model.isSharedForRead());

            // ensure that if the enclosing project of the associated IFile
            // is closed, then the processor gets disposed
            final JSPModelProcessor processor = JSPModelProcessor.get(_testJSP1);
            assertFalse(model.isSharedForRead());
            assertNotNull(processor);
            // we should still be the only one with a handle since JSPModelProcessor
            // doesn't hold it.
            assertFalse(processor.isDisposed());
            processor.refresh(!JSPModelProcessor.FORCE_REFRESH, JSPModelProcessor.RUN_ON_CURRENT_THREAD);
            assertFalse(model.isSharedForRead());

            _testJSP1.getProject().close(null);

            // file is deleted, so the processor should dispose itself on the
            // resource change event
            waitForAndAssertProcessorDisposed(processor, true);
            // final check, with processor disposed, still not shared
            assertFalse(model.isSharedForRead());
        }
        finally
        {
            if (model != null)
            {
                model.releaseFromRead();
            }
        }
    }

    public void testProjectDeletion() throws Exception
    {
        final IModelManager modelManager = StructuredModelManager.getModelManager();

        IStructuredModel model = null;

        try
        {
            model = modelManager.getModelForRead(_testJSP1);

            // we should be the only one with a handle
            //assertFalse(model.isSharedForRead());

            // ensure that if the enclosing project of the associated IFile
            // is deleted, then the processor gets disposed
            final JSPModelProcessor processor = JSPModelProcessor.get(_testJSP1);
            assertNotNull(processor);
            assertFalse(processor.isDisposed());
//            assertFalse(model.isSharedForRead());
            // we should still be the only one with a handle since JSPModelProcessor
            // doesn't hold it.
            processor.refresh(!JSPModelProcessor.FORCE_REFRESH, JSPModelProcessor.RUN_ON_CURRENT_THREAD);
            assertFalse(model.isSharedForRead());

            JSFTestUtil.safeDelete(_testJSP1, 10, 1000);

            // file is deleted, so the processor should dispose itself on the
            // resource change event
            waitForAndAssertProcessorDisposed(processor, true);
            assertFalse(model.isSharedForRead());
        }
        finally
        {
            if (model != null)
            {
                model.releaseFromRead();
            }
        }
    }

    public void testChangeRefresh() throws Exception
    {
        // random order of access to the jsps, but always the same between runs
        final int order[] = new int[] {6,19,10,16,14,4,13,11,24,2,3,23,20,15,17,9,1,5,22,12,21,8,18,0,7};
        assertEquals(NUM_JSPS, order.length);

        for (final int element : order)
        {
            final IFile file = _jsps.get(element);

            final JSPModelProcessor processor = JSPModelProcessor.get(file);
            // the processor model should start out dirty since it won't
            // get refreshed unless the resource detects a change or if
            // it is explicitly refreshed
            assertTrue(processor.isModelDirty());

            // this should trigger a change event and update the model
            file.touch(null);

            Thread.sleep(2000);
            waitForAndAssertProcessorDirty(processor, false);

            // now delete the file and ensure the processor is disposed
            JSFTestUtil.safeDelete(file, 10, 1000);

            // file is deleted, so the processor should dispose itself on the
            // resource change event
            waitForAndAssertProcessorDisposed(processor, true);
        }
    }

    public void testFileDoesnotExist()
    {
        IFile file = _testJSP1.getProject().getFile("/doesNotExist.jsp");
        assertFalse(file.isAccessible());
        boolean caughtException = false;
        try
        {
            JSPModelProcessor.get(file);
        }
        catch (CoreException e)
        {
            caughtException = true;
        }
        assertTrue(caughtException);
    }

    public void testExplicitRefresh() throws Exception
    {
        // random order of access to the jsps, but always the same between runs
        final int order[] = new int[] {6,19,10,16,14,4,13,11,24,2,3,23,20,15,17,9,1,5,22,12,21,8,18,0,7};
        assertEquals(NUM_JSPS, order.length);

        for (final int element : order)
        {
            final IFile file = _jsps.get(element);

            final JSPModelProcessor processor = JSPModelProcessor.get(file);
            // the processor model should start out dirty since it won't
            // get refreshed unless the resource detects a change or if
            // it is explicitly refreshed
            // we should be the only one with a handle
            waitForAndAssertProcessorDirty(processor, true);

            // since the model is dirty this should trigger a refresh
            processor.refresh(!JSPModelProcessor.FORCE_REFRESH, JSPModelProcessor.RUN_ON_CURRENT_THREAD);

            waitForAndAssertProcessorDirty(processor, false);

            // now delete the file and ensure the processor is disposed
            JSFTestUtil.safeDelete(file, 10, 1000);

            waitForAndAssertProcessorDisposed(processor, true);
        }
    }

    private void waitForAndAssertProcessorDirty(
            final JSPModelProcessor processor, final boolean expectedValue)
            throws Exception
    {
        int i = 0;

        while (i++ < WAIT_ITERATIONS
                && (processor.isModelDirty() != expectedValue))
        {
            Thread.sleep(WAIT_SLEEP_TIME_MS);
        }
        assertEquals(expectedValue, processor.isModelDirty());
    }

    private void waitForAndAssertProcessorDisposed(
            final JSPModelProcessor processor, final boolean expectedValue)
            throws Exception
    {
        int i = 0;

        while (i++ < WAIT_ITERATIONS
                && (processor.isDisposed() != expectedValue))
        {
            Thread.sleep(WAIT_SLEEP_TIME_MS);
        }
        // file is deleted, so the processor should dispose itself on the
        // resource change event
        assertEquals(expectedValue, processor.isDisposed());
    }

    public static void main(final String[] args)
    {
        final Set<Integer> set = new TreeSet<Integer>();

        final Random random = new Random();

        while(set.size() < NUM_JSPS)
        {
            final Integer value = Integer.valueOf(Math.abs(random.nextInt()) % NUM_JSPS);

            if (!set.contains(value))
            {
                System.out.printf("%d,", value);
                set.add(value);
            }
        }
    }
}

