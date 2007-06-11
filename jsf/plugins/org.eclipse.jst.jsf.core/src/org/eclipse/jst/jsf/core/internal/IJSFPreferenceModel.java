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

package org.eclipse.jst.jsf.core.internal;

import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Defines a generic JSF preferences model
 * 
 * @author cbateman
 *
 */
public interface IJSFPreferenceModel 
{
    /**
     * Loads the object from the preference store provided
     * 
     * @param prefStore
     */
    public void load(IPreferenceStore  prefStore);
    
    /**
     * Copies the object into the preference store but DOES NOT SAVE IT
     * 
     * @param prefStore
     */
    public void commit(IPreferenceStore prefStore);
    
    /**
     * Reverts the model to it's defaults.  Does not commit to pref store. 
     */
    public void setDefaults();

    /**
     * @param context
     * @param key
     * @return the preference value in context with using key as the id
     */
    public Object getValueByKey(final IScopeContext context, final String key);

    /**
     * @param context
     * @param key
     * @return the preference value in context using key as the id and drawing
     * it directly from the preference store
     */
    public Object getStoredValueByKey(final IScopeContext context, final String key);
    
    /**
     * Set the preference value indicated by key in context to value.  May
     * throw ClassCastException if value is not the type expected for the key
     * @param context
     * @param key
     * @param value
     * @return the old value
     */
    public Object setValueByKey(final IScopeContext context, final String key, final Object value);
}
