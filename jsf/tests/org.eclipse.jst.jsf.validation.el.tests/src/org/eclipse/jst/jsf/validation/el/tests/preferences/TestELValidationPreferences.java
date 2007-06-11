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

import java.io.IOException;

import junit.framework.TestCase;

import org.eclipse.jface.preference.IPersistentPreferenceStore;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences.Severity;

/**
 * Basic unit tests for ELValidationPreferences
 * @author cbateman
 *
 */
public class TestELValidationPreferences extends TestCase 
{
    private ELValidationPreferences     _prefs;
    
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        _prefs =
            new ELValidationPreferences();
        _prefs.load(JSFCorePlugin.getDefault().getPreferenceStore());
        PrefTestUtil.assertExpectedDefaults(_prefs);
    }

    @Override
    protected void tearDown() throws Exception 
    {
        super.tearDown();
        
        // ensure that preferences are always forced back to default
        _prefs.setDefaults();
        PrefTestUtil.assertExpectedDefaults(_prefs);
        _prefs.commit(JSFCorePlugin.getDefault().getPreferenceStore());
        ((IPersistentPreferenceStore)JSFCorePlugin.getDefault().getPreferenceStore()).save();
    }

    public void testLoad()
    {
        PrefTestUtil.assertExpectedDefaults(_prefs);
    }

    public void testSetDefaults()
    {
        _prefs.setDefaults();

        PrefTestUtil.assertExpectedDefaults(_prefs);
    }
    
    public void testSetByKey()
    {
        PrefTestUtil.setByKey(Severity.IGNORE, _prefs);
        PrefTestUtil.assertSetByKey(Severity.IGNORE, _prefs);
    }
    
    public void testSetByKeyAndSave() throws IOException
    {
        final  IPreferenceStore  prefStore = 
            JSFCorePlugin.getDefault().getPreferenceStore();
        PrefTestUtil.setByKey(Severity.IGNORE, _prefs);
        PrefTestUtil.assertSetByKey(Severity.IGNORE, _prefs);
        _prefs.commit(prefStore);
        ((IPersistentPreferenceStore)prefStore).save();
        _prefs.load(prefStore);
        PrefTestUtil.assertSetByKey(Severity.IGNORE, _prefs);
    }


}
