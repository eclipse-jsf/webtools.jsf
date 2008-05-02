/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal;

import java.util.List;

import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Composite of preference models
 * 
 * @author cbateman
 *
 */
public class CompositeJSFPreferenceModel implements IJSFPreferenceModel
{
    private final List<IJSFPreferenceModel> _models;

    /**
     * @param models
     */
    public CompositeJSFPreferenceModel(final List<IJSFPreferenceModel> models)
    {
        _models = models;
    }

    /* 
     * Commits all child instances
     */
    public void commit(final IPreferenceStore prefStore)
    {
        for (final IJSFPreferenceModel model : _models)
        {
            model.commit(prefStore);
        }
    }

    /**
     * Searches each child until it finds the value associated with key.
     * @return null if not found.
     */
    public Object getStoredValueByKey(final IScopeContext context, final String key)
    {
        Object value = null;
        
        SEARCH: for (final IJSFPreferenceModel model : _models)
        {
            value = model.getStoredValueByKey(context, key);
            if (value != null)
            {
                break SEARCH;
            }
        }
        return value;
    }

    /**
     * Same as getStoredValueByKey but using getValueByKey instead
     */
    public Object getValueByKey(final IScopeContext context, final String key)
    {
        Object value = null;
        
        SEARCH: for (final IJSFPreferenceModel model : _models)
        {
            value = model.getValueByKey(context, key);
            if (value != null)
            {
                break SEARCH;
            }
        }
        return value;
    }

    /**
     * Calls load on all children with prefStore
     */
    public void load(final IPreferenceStore prefStore)
    {
        for (final IJSFPreferenceModel model : _models)
        {
            model.load(prefStore);
        }
    }

    /**
     * Calls set defaults on all children
     */
    public void setDefaults()
    {
        for (final IJSFPreferenceModel model : _models)
        {
            model.setDefaults();
        }
    }

    public Object setValueByKey(final IScopeContext context, final String key, final Object value)
    {
        for (final IJSFPreferenceModel model : _models)
        {
            if (model.getValueByKey(context, key) != null)
            {
                return model.setValueByKey(context, key, value);
            }
        }
        return null;
    }

}
