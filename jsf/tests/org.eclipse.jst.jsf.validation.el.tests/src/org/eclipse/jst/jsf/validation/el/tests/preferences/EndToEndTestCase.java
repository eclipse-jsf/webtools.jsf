/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.el.tests.preferences;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.JSPTestCase;
import org.eclipse.jst.jsf.validation.el.tests.base.MockELValidationReporter;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences.Severity;
import org.eclipse.jst.jsf.validation.internal.el.ELExpressionValidator;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

public class EndToEndTestCase extends JSPTestCase
{
    public EndToEndTestCase()
    {
        super(JSFVersion.V1_1, SingleJSPTestCase.FACES_CONFIG_FILE_NAME_1_1);
    }

    protected IFile                 _testJSP;
    private IStructuredModel        _structuredModel;
    private IStructuredDocument     _structuredDocument;
    private ELValidationPreferences _prefs;


    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        _testJSP = loadJSP("/testdata/jsps/preferenceTest1.jsp.data", "/preferenceTest1.jsp");

        _structuredModel = StructuredModelManager.getModelManager().getModelForRead(_testJSP);
        _structuredDocument = _structuredModel.getStructuredDocument();

        _prefs = new ELValidationPreferences();
        _prefs.load(JSFCorePlugin.getDefault().getPreferenceStore());
        PrefTestUtil.assertExpectedDefaults(_prefs);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();

        if (_structuredModel != null)
        {
            _structuredModel.releaseFromRead();
        }

        // ensure that preferences are always forced back to default
        _prefs.setDefaults();
        PrefTestUtil.assertExpectedDefaults(_prefs);
        _prefs.commit(JSFCorePlugin.getDefault().getPreferenceStore());
        ((IPersistentPreferenceStore)JSFCorePlugin.getDefault().getPreferenceStore()).save();
    }

    @Override
    public void testSanity()
    {
        assertEquals("5+3", getELText(_structuredDocument,799));
        assertEquals("null+null", getELText(_structuredDocument,831));
        assertEquals("5 + true", getELText(_structuredDocument,876));
        assertEquals("'a' + 'b'", getELText(_structuredDocument,920));
        assertEquals("5 / 0", getELText(_structuredDocument,958));
        assertEquals("myBean.subClassStringProperty", getELText(_structuredDocument,1028));
        assertEquals("myBean1", getELText(_structuredDocument,1093));
        assertEquals("listBean[-1]", getELText(_structuredDocument,1129));
        assertEquals("myBean.stringArrayProperty > myBean.booleanProperty", getELText(_structuredDocument,1177));
        assertEquals("myBean.coins > myBean.colors", getELText(_structuredDocument,1264));
        assertEquals("false && myBean.booleanProperty", getELText(_structuredDocument,1328));
        assertEquals("myBean.booleanProperty && false", getELText(_structuredDocument,1395));
        assertEquals("5 == true", getELText(_structuredDocument,1462));
        assertEquals("!false", getELText(_structuredDocument,1507));
        assertEquals("!5", getELText(_structuredDocument,1549));
        assertEquals("myBean.doubleProperty + myBean.getIntegerProperty", getELText(_structuredDocument,1587));

    }

    public void testIgnoreAll() throws Exception
    {
        testSeverityAll(Diagnostic.OK, 0);
    }

    public void testWarningsAll() throws Exception
    {
        testSeverityAll(Diagnostic.WARNING, IMessage.NORMAL_SEVERITY);
    }

    public void testErrorAll() throws Exception
    {
        testSeverityAll(Diagnostic.ERROR, IMessage.HIGH_SEVERITY);
    }


    private void testSeverityAll(final int diagSeverity, final int messageSeverity) throws Exception
    {
        final Severity severity =
            ELValidationPreferences.mapDiagToSeverity(diagSeverity);
        PrefTestUtil.setByKey(severity, _prefs);
        PrefTestUtil.assertSetByKey(severity, _prefs);

        final  IPreferenceStore  prefStore =
            JSFCorePlugin.getDefault().getPreferenceStore();
        _prefs.commit(prefStore);
        ((IPersistentPreferenceStore)prefStore).save();
        _prefs.load(prefStore);

        assertErrorLevel(799, messageSeverity);
        assertErrorLevel(831, messageSeverity);
        assertErrorLevel(876, messageSeverity);
        assertErrorLevel(920, messageSeverity);
        assertErrorLevel(958, messageSeverity);
        assertErrorLevel(1028, messageSeverity);
        assertErrorLevel(1093, messageSeverity);
        assertErrorLevel(1129, messageSeverity);
        assertErrorLevel(1177, messageSeverity);
        assertErrorLevel(1264, messageSeverity);
        assertErrorLevel(1328, messageSeverity);
        assertErrorLevel(1395, messageSeverity);
        assertErrorLevel(1462, messageSeverity);
        assertErrorLevel(1507, messageSeverity);
        assertErrorLevel(1549, messageSeverity);
        assertErrorLevel(1587, messageSeverity);
    }

    private void assertErrorLevel(final int docPos, final int severity)
    {
        final MyMockValidationReporter reporter = new MyMockValidationReporter();
        final ELExpressionValidator validator = 
            createELValidator(_structuredDocument, docPos, _testJSP, reporter);
        validator.validateXMLNode();
        final List<ReportedProblem> syntaxProblems = reporter.getSyntaxProblems();

        for (final ReportedProblem message : syntaxProblems)
        {
            assertEquals(severity, message.getSeverity());
        }

        final List<ReportedProblem> semanticProblems = reporter.getSemanticProblems();
        for (final ReportedProblem message : semanticProblems)
        {
            assertEquals(severity, message.getSeverity());
        }
    }
    
    private class MyMockValidationReporter extends MockELValidationReporter
    {
        @Override
        public void report(Diagnostic problem, int start, int length)
        {
            Diagnostic modifiedProblem =
                new SeverityModifiableDiagnostic(problem);
            super.report(modifiedProblem, start, length);
        }
        
        private class SeverityModifiableDiagnostic extends BasicDiagnostic
        {

            /**
             * @param severity
             * @param source
             * @param code
             * @param message
             * @param data
             */
            private SeverityModifiableDiagnostic(final Diagnostic sourceDiag)
            {
                super(_prefs.getDiagnosticSeverity(sourceDiag.getCode()), sourceDiag.getSource(), 
                        sourceDiag.getCode(), sourceDiag.getMessage(), sourceDiag.getData().toArray());
            }
        }
    }
}
