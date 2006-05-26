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

import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

/**
 * Creates and manages a panel for configuring 
 * 
 * @author cbateman
 */
/*package*/ class ELPrefPanel 
{
    /* view */
    private final Group     _container;
    private final Button    _chkBuildValidation;
    private final Button    _chkIncrementalValidation;
    
    /* model */
    private ELValidationPreferences  _prefs;
    
    /**
     * Allocates new container in parent.
     * @param parent
     */
    public ELPrefPanel(Composite parent)
    {
        _container = new Group(parent, SWT.NONE);
        _container.setText(Messages.JSFValidationPreferencePage_ELPrefPanel_Title);
        _container.setLayout(new RowLayout(SWT.VERTICAL));
        
        _chkBuildValidation = new Button(_container, SWT.CHECK);
        _chkBuildValidation.setText(Messages.JSFValidationPreferencePage_ELPrefPanel_BuildValidationCheckBoxTitle);
        _chkBuildValidation.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e) 
            {
                _prefs.setEnableBuildValidation(_chkBuildValidation.getSelection());
                refresh();
            }
        });
        
        _chkIncrementalValidation = new Button(_container, SWT.CHECK);
        _chkIncrementalValidation.setText(Messages.JSFValidationPreferencePage_ELPrefPanel_IncrementalValidationCheckBoxTitle);
        _chkIncrementalValidation.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e) 
            {
                _prefs.setEnableIncrementalValidation(_chkIncrementalValidation.getSelection());
                refresh();
            }
        });

    }
    
    /**
     * @return the top-level container managed by this panel
     */
    public Control getControl()
    {
        return _container;
    }
    
    /**
     * Sets the model for panel
     * 
     * @param prefs
     */
    public void setModel(ELValidationPreferences  prefs)
    {
        _prefs = prefs;
    }
    
    /**
     * Refreshes the UI from the model
     */
    public void refresh()
    {
        _chkBuildValidation.setSelection(_prefs.isEnableBuildValidation());
        _chkIncrementalValidation.
            setSelection(_prefs.isEnableIncrementalValidation());
    }
}
