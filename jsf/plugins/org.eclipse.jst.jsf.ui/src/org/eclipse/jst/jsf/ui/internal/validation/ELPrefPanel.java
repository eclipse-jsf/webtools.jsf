/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.ui.internal.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.core.internal.CompositeJSFPreferenceModel;
import org.eclipse.jst.jsf.core.internal.IJSFPreferenceModel;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

/**
 * Creates and manages a panel for configuring 
 * 
 * @author cbateman
 */
/*package*/ class ELPrefPanel 
{
    /* view */
    private final Group                                 _container;
    private final Button                                _chkBuildValidation;
    private final Button                                _chkIncrementalValidation;
    private final ProblemSeveritiesConfigurationBlock   _problemSeverities;
    
    /* model */
    private final ValidationPreferences  _prefs;
    
    /**
     * Allocates new container in parent.
     * @param parent
     * @param container 
     * @param prefs 
     */
    public ELPrefPanel(Composite parent, IWorkbenchPreferenceContainer container, ValidationPreferences prefs)
    {
        _prefs = prefs;

        _container = new Group(parent, SWT.NONE);
        _container.setText(Messages.JSFValidationPreferencePage_ELPrefPanel_Title);
        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
        rowLayout.marginTop = 5;
        rowLayout.marginLeft = 5; 
        _container.setLayout(rowLayout);

        _chkBuildValidation = new Button(_container, SWT.CHECK);
        _chkBuildValidation.setText(Messages.JSFValidationPreferencePage_ELPrefPanel_BuildValidationCheckBoxTitle);
        _chkBuildValidation.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e) 
            {
                _prefs.getElPrefs().setEnableBuildValidation(_chkBuildValidation.getSelection());
                refresh();
            }
        });
        
        _chkIncrementalValidation = new Button(_container, SWT.CHECK);
        _chkIncrementalValidation.setText(Messages.JSFValidationPreferencePage_ELPrefPanel_IncrementalValidationCheckBoxTitle);
        _chkIncrementalValidation.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e) 
            {
                _prefs.getElPrefs().setEnableIncrementalValidation(_chkIncrementalValidation.getSelection());
                refresh();
            }
        });

        new Label(_container, SWT.NONE);
        
        final List<IJSFPreferenceModel> models = new ArrayList<IJSFPreferenceModel>();
        models.add(_prefs.getElPrefs());
        models.add(_prefs.getTypeComparatorPrefs());
        final IJSFPreferenceModel compositeModel = new CompositeJSFPreferenceModel(
                models);

        _problemSeverities = new ProblemSeveritiesConfigurationBlock(compositeModel, null, container);
        _problemSeverities.createContents(_container);
    }
    
    /**
     * @return the top-level container managed by this panel
     */
    public Control getControl()
    {
        return _container;
    }
    
    
    /**
     * Refreshes the UI from the model
     */
    public void refresh()
    {
        _chkBuildValidation.setSelection(_prefs.getElPrefs().isEnableBuildValidation());
        _chkIncrementalValidation.
            setSelection(_prefs.getElPrefs().isEnableIncrementalValidation());
        _problemSeverities.updateControls();
    }

    /**
     * 
     */
    public void processChanges() {
        _problemSeverities.performOk();
    }
}
