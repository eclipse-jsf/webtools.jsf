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
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * @author cbateman
 *
 */
public class ValidationPreferences 
{
    private final ELValidationPreferences _elPrefs;

    /**
     * Constructor
     */
    public ValidationPreferences()
    {
        _elPrefs = new ELValidationPreferences();
    }

    /**
     * @return the el preferences
     */
    public ELValidationPreferences getElPrefs() 
    {
        return _elPrefs;
    }

    /**
     * The default preference loader
     */
    public void load()
    {
        load(JSFCorePlugin.getDefault().getPreferenceStore());
    }
    
    /**
     * Loads preferences from prefStore
     * 
     * @param prefStore
     */
    public void load(IPreferenceStore prefStore)
    {
        _elPrefs.load(prefStore);
    }

    /**
     * Commits but does not store the preferences
     * 
     * @param prefStore
     */
    public void commit(IPreferenceStore prefStore)
    {
        _elPrefs.commit(prefStore);
    }
    
    /**
     * Reverts the model to it's defaults.  Does not commit to pref store.
     */
    public void setDefaults()
    {
        _elPrefs.setDefaults();
    }
}
