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
import org.eclipse.jst.jsf.validation.internal.facelet.FaceletValidationPreferences;

/**
 * @author cbateman
 *
 */
public class ValidationPreferences 
{
    private final IPreferenceStore                      _prefStore;
    private final ELValidationPreferences               _elPrefs;
    private final JSFTypeComparatorPreferences          _typeComparatorPrefs;
    private final FaceletValidationPreferences          _faceletPrefs;
    /**
     * Constructor
     * @param prefStore 
     */
    public ValidationPreferences(IPreferenceStore  prefStore)
    {
        _prefStore = prefStore;
        _elPrefs = new ELValidationPreferences();
        _typeComparatorPrefs = new JSFTypeComparatorPreferences();
        _faceletPrefs = new FaceletValidationPreferences();
    }

    /**
     * @return the el preferences
     */
    public ELValidationPreferences getElPrefs() 
    {
        return _elPrefs;
    }

    /**
     * @return the type comparator preferences.
     */
    public JSFTypeComparatorPreferences getTypeComparatorPrefs()
    {
        return _typeComparatorPrefs;
    }

    /**
     * @return the el preferences
     */
    public FaceletValidationPreferences getFaceletValidationPrefs() 
    {
        return _faceletPrefs;
    }

    /**IPreferenceStore
     * The default preference loader
     */
    public void load()
    {
        load(_prefStore);
    }
    
    /**
     * Loads preferences from prefStore
     * 
     * @param prefStore
     */
    private void load(IPreferenceStore prefStore)
    {
        _elPrefs.load(prefStore);
        _typeComparatorPrefs.load(prefStore);
        _faceletPrefs.load(prefStore);
    }

    /**
     * Commits but does not store the preferences
     * 
     * @param prefStore
     */
    public void commit(IPreferenceStore prefStore)
    {
        _elPrefs.commit(prefStore);
        _typeComparatorPrefs.commit(prefStore);
        _faceletPrefs.commit(prefStore);
    }
    
    /**
     * Reverts the model to it's defaults.  Does not commit to pref store.
     */
    public void setDefaults()
    {
        _elPrefs.setDefaults();
        _typeComparatorPrefs.setDefaults();
        _faceletPrefs.setDefaults();
    }
}
