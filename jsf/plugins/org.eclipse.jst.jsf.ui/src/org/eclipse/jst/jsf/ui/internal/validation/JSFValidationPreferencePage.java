/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.ui.internal.validation;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

/**
 * Workbench preference page for configuring JSF validation
 * 
 * @author cbateman
 *
 */
public class JSFValidationPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage 
{
    private final ValidationPreferences     _prefs;
    
    private ELPrefPanel                     _elPrefPanel;
    
    /**
     * Constructor
     */
    public JSFValidationPreferencePage()
    {
        super(/* TODO: title*/);
        _prefs = new ValidationPreferences(getPreferenceStore());
        _prefs.load();
    }
    
    protected Control createContents(Composite parent)
    {
        _elPrefPanel = new ELPrefPanel(parent,
                (IWorkbenchPreferenceContainer) getContainer(), _prefs);
        _elPrefPanel.refresh();
        return _elPrefPanel.getControl();
    }

    public void init(IWorkbench workbench) 
    {
        // do nothing
    }

    protected void performApply() 
    {
        // process changes before committing to pref store
        _elPrefPanel.processChanges();
        _prefs.commit(getPreferenceStore());
    }

    protected void performDefaults() 
    {
        _prefs.setDefaults();
        _elPrefPanel.refresh();
        super.performDefaults();
    }

    public boolean performOk() 
    {
        performApply();
        return true;
    }

    protected IPreferenceStore doGetPreferenceStore() 
    {
        // load the validation pref store
        return JSFCorePlugin.getDefault().getPreferenceStore();
    }
    
}
