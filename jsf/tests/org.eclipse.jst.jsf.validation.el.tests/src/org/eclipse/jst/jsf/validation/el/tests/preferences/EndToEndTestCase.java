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

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.validation.el.tests.base.JSPTestCase;
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

    public void testSanity()
    {
        assertEquals("5+3", getELText(_structuredDocument,823));
        assertEquals("null+null", getELText(_structuredDocument,856));
        assertEquals("5 + true", getELText(_structuredDocument,902));
        assertEquals("'a' + 'b'", getELText(_structuredDocument,947));
        assertEquals("5 / 0", getELText(_structuredDocument,986));
        assertEquals("myBean.subClassStringProperty", getELText(_structuredDocument,1058));
        assertEquals("myBean1", getELText(_structuredDocument,1124));
        assertEquals("listBean[-1]", getELText(_structuredDocument,1161));
        assertEquals("myBean.stringArrayProperty > myBean.booleanProperty", getELText(_structuredDocument,1210));
        assertEquals("myBean.coins > myBean.colors", getELText(_structuredDocument,1298));
        assertEquals("false && myBean.booleanProperty", getELText(_structuredDocument,1363));
        assertEquals("myBean.booleanProperty && false", getELText(_structuredDocument,1431));
        assertEquals("5 == true", getELText(_structuredDocument,1499));
        assertEquals("!false", getELText(_structuredDocument,1545));
        assertEquals("!5", getELText(_structuredDocument,1588));
        assertEquals("myBean.doubleProperty + myBean.getIntegerProperty", getELText(_structuredDocument,1627));
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
    
    
    private void testSeverityAll(int diagSeverity, int messageSeverity) throws Exception
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
        
        assertErrorLevel(823, messageSeverity);
        assertErrorLevel(856, messageSeverity);
        assertErrorLevel(902, messageSeverity);
        assertErrorLevel(947, messageSeverity);
        assertErrorLevel(986, messageSeverity);
        assertErrorLevel(1058, messageSeverity);
        assertErrorLevel(1124, messageSeverity);
        assertErrorLevel(1161, messageSeverity);
        assertErrorLevel(1210, messageSeverity);
        assertErrorLevel(1298, messageSeverity);
        assertErrorLevel(1363, messageSeverity);
        assertErrorLevel(1431, messageSeverity);
        assertErrorLevel(1499, messageSeverity);
        assertErrorLevel(1545, messageSeverity);
        assertErrorLevel(1588, messageSeverity);
        assertErrorLevel(1627, messageSeverity);
    }
    
    private void assertErrorLevel(final int docPos, final int severity)
    {
        ELExpressionValidator validator = createELValidator(_structuredDocument, docPos, _testJSP);
        validator.validateXMLNode();
        List<IMessage> syntaxProblems = validator.getSyntaxProblems();
        
        for (final Iterator<IMessage> it = syntaxProblems.iterator(); it.hasNext();)
        {
            IMessage message = it.next();
            assertEquals(severity, message.getSeverity());
        }

        List<IMessage> semanticProblems = validator.getSemanticValidator().getMessages();
        for (final Iterator<IMessage> it = semanticProblems.iterator(); it.hasNext();)
        {
            IMessage message = it.next();
            assertEquals(severity, message.getSeverity());
        }
    }
}
