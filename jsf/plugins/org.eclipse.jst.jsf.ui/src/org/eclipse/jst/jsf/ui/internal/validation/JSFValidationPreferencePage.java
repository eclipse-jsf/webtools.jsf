package org.eclipse.jst.jsf.ui.internal.validation;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jst.jsf.validation.el.Activator;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

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
        _prefs = new ValidationPreferences();
        _prefs.load(getPreferenceStore());
    }
    
    protected Control createContents(Composite parent) 
    {
        _elPrefPanel = new ELPrefPanel(parent);
        _elPrefPanel.setModel(_prefs.getElPrefs());
        _elPrefPanel.refresh();
        return _elPrefPanel.getControl();
    }

    public void init(IWorkbench workbench) 
    {
        // do nothing
    }

    protected void performApply() 
    {
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
        return Activator.getDefault().getPreferenceStore();
    }
    
}
