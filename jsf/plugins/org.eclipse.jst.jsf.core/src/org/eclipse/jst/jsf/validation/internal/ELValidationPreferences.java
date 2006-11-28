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

package org.eclipse.jst.jsf.validation.internal;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Model object for EL validation preferences
 * 
 * @author cbateman
 */
public class ELValidationPreferences 
{
    private final static String KEY_ENABLE_BUILD_VALIDATION = 
        "org.eclipse.jst.jsf.ui.ValidateJSFELBuild";
    private final static boolean DEFAULT_ENABLE_BUILD_VALIDATION = true;

    private final static String KEY_ENABLE_INCREMENTAL_VALIDATION = 
        "org.eclipse.jst.jsf.ui.ValidateJSFELIncremental";
    private final static boolean DEFAULT_ENABLE_INCREMENTAL_VALIDATION = false;
    
    private boolean _enableBuildValidation;
    private boolean _enableIncrementalValidation;

    
    /**
     * Loads the object from the preference store provided
     * 
     * @param prefStore
     */
    public void load(IPreferenceStore  prefStore)
    {
        if (!prefStore.contains(KEY_ENABLE_BUILD_VALIDATION))
        {
            prefStore.setDefault(KEY_ENABLE_BUILD_VALIDATION, DEFAULT_ENABLE_BUILD_VALIDATION);
        }
        _enableBuildValidation = 
            prefStore.getBoolean(KEY_ENABLE_BUILD_VALIDATION);
        
        if (!prefStore.contains(KEY_ENABLE_INCREMENTAL_VALIDATION))
        {
            prefStore.setDefault(KEY_ENABLE_INCREMENTAL_VALIDATION, DEFAULT_ENABLE_INCREMENTAL_VALIDATION);
        }
        _enableIncrementalValidation = 
            prefStore.getBoolean(KEY_ENABLE_INCREMENTAL_VALIDATION);
    }

    /**
     * Copies the object into the preference store but DOES NOT SAVE IT
     * 
     * @param prefStore
     */
    public void commit(IPreferenceStore prefStore)
    {
        prefStore.setValue(KEY_ENABLE_BUILD_VALIDATION, _enableBuildValidation);
        prefStore.setValue(KEY_ENABLE_INCREMENTAL_VALIDATION, 
                           _enableIncrementalValidation);
    }
    
    /**
     * Reverts the model to it's defaults.  Does not commit to pref store. 
     */
    public void setDefaults()
    {
        setEnableBuildValidation(DEFAULT_ENABLE_BUILD_VALIDATION);
        setEnableIncrementalValidation(DEFAULT_ENABLE_INCREMENTAL_VALIDATION);
    }
    
    /**
     * @return the build validation enablement
     */
    public boolean isEnableBuildValidation() 
    {
        return _enableBuildValidation;
    }

    /**
     * @return the incremental validation enablement
     */
    public boolean isEnableIncrementalValidation() 
    {
        return _enableIncrementalValidation;
    }

    /**
     * @param enableBuildValidation
     */
    public void setEnableBuildValidation(boolean enableBuildValidation) {
        _enableBuildValidation = enableBuildValidation;
    }

    /**
     * @param enableIncrementalValidation
     */
    public void setEnableIncrementalValidation(boolean enableIncrementalValidation) {
        _enableIncrementalValidation = enableIncrementalValidation;
    }
}
