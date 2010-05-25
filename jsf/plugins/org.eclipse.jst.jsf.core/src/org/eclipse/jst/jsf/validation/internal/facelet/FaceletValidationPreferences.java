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
package org.eclipse.jst.jsf.validation.internal.facelet;

import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.jsf.core.internal.IJSFPreferenceModel;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.validation.internal.Severity;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Model object for EL validation preferences
 * 
 * @author cbateman
 */
public class FaceletValidationPreferences implements IJSFPreferenceModel
{
    private int[] _severities;

    /**
     * Loads the object from the preference store provided
     * 
     * @param prefStore
     */
    public void load(final IPreferenceStore prefStore)
    {
        loadSeverities(prefStore);
    }

    private void loadSeverities(final IPreferenceStore prefStore)
    {
        final int severities[] = getSeverities();
        for (int i = 0; i < FaceletDiagnosticFactory.NUM_IDS; i++)
        {
            final String key = getKeyById(i);
            if (!prefStore.contains(key))
            {
                final int diagSeverity = getDefaultSeverity(i);
                final Severity severity = mapDiagToSeverity(diagSeverity);
                prefStore.setDefault(key, severity.toString());
            }
            final String storedSeverity = prefStore.getString(key);
            severities[i] = mapSeverityToDiag(storedSeverity);
        }
    }

    /**
     * Copies the object into the preference store but DOES NOT SAVE IT
     * 
     * @param prefStore
     */
    public void commit(final IPreferenceStore prefStore)
    {
        commitSeverities(prefStore);
    }

    private void commitSeverities(final IPreferenceStore prefStore)
    {
        final int severities[] = getSeverities();
        for (int i = 0; i < severities.length; i++)
        {
            final String key = getKeyById(i);
            prefStore
                    .setValue(key, mapDiagToSeverity(severities[i]).toString());
        }
    }

    /**
     * Reverts the model to it's defaults. Does not commit to pref store.
     */
    public void setDefaults()
    {
        setProblemSeverityDefaults();
    }

    private void setProblemSeverityDefaults()
    {
        final int[] severities = getSeverities();
        for (int i = 0; i < DiagnosticFactory.NUM_IDS; i++)
        {
            severities[i] = getDefaultSeverity(i);
        }
    }

    public Object getValueByKey(final IScopeContext context, final String key)
    {
        // ignore context for now; will be used when we have project overrides
        try
        {
            final Severity severity = getSeverity(key);
            return severity.toString();
        } catch (final IllegalArgumentException e)
        {
            // getIdByKey will throw this exception if key is not a valid
            // severity key. Ignore the exception here and fall-through
        }
        return null; // not found
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.jsf.core.internal.IJSFPreferenceModel#getStoredValueByKey
     * (org.eclipse.core.runtime.preferences.IScopeContext, java.lang.String)
     */
    public Object getStoredValueByKey(final IScopeContext context, final String key)
    {
        // ignore context for now; will be used when we have project overrides
        try
        {
            return context
                    .getNode("org.eclipse.jst.jsf.core").get(key, mapDiagToSeverity(getDefaultSeverity(getIdByKey(key))).toString()); //$NON-NLS-1$
        } catch (final IllegalArgumentException e)
        {
            // getIdByKey will throw this exception if key is not a valid
            // severity key. Ignore the exception here and fall-through
        }
        return null; // not found
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jst.jsf.core.internal.IJSFPreferenceModel#setValueByKey(org
     * .eclipse.core.runtime.preferences.IScopeContext, java.lang.String,
     * java.lang.Object)
     */
    public Object setValueByKey(final IScopeContext context, final String key, final Object value)
    {
        // ignore context for now; will be used when we have project overrides
        final Severity oldValue = getSeverity(key);
        setSeverity(key, (Severity) value);
        return oldValue;
    }

    /**
     * @param key
     * @return the severity
     */
    public Severity getSeverity(final String key)
    {
        final int severityDiag = _severities[getIdByKey(key)];
        final Severity severity = mapDiagToSeverity(severityDiag);
        return severity;
    }

    /**
     * @param key
     * @param severity
     */
    public void setSeverity(final String key, final Severity severity)
    {
        final int newSeverityDiag = mapSeverityToDiag(severity.toString());
        final int diagId = getIdByKey(key);
        _severities[diagId] = newSeverityDiag;
    }

    /**
     * @param diagnosticId
     * @return the severity as configured for diagnosticId. The value is
     *         relative to the Diagnostic class severity scheme
     */
    public final int getDiagnosticSeverity(final int diagnosticId)
    {
        return getSeverities()[diagnosticId];
    }

    private int[] getSeverities()
    {
        if (_severities == null)
        {
            _severities = new int[FaceletDiagnosticFactory.NUM_IDS];
        }
        return _severities;
    }

    /**
     * @param diagSeverity
     * @return a Severity preference value for a diagnostic severity
     */
    public static Severity mapDiagToSeverity(final int diagSeverity)
    {
        switch (diagSeverity)
        {
            case Diagnostic.ERROR:
                return Severity.ERROR;
            case Diagnostic.WARNING:
                return Severity.WARNING;
            default:
                return Severity.IGNORE;
        }
    }

    /**
     * @param severity
     * @return a Diagnostic severity level for a severity pref string
     */
    public static int mapSeverityToDiag(final String severity)
    {
        if ("error".equals(severity)) //$NON-NLS-1$
        {
            return Diagnostic.ERROR;
        } else if ("warning".equals(severity)) //$NON-NLS-1$
        {
            return Diagnostic.WARNING;
        } else if ("ignore".equals(severity)) //$NON-NLS-1$
        {
            return Diagnostic.OK;
        } else
        {
            throw new IllegalArgumentException("Invalid enum name: " + severity); //$NON-NLS-1$
        }
    }

    /**
     * @param diagnosticId
     * @return the default severity of a diagnostic
     */
    public static int getDefaultSeverity(final int diagnosticId)
    {
        switch (diagnosticId)
        {
            case FaceletDiagnosticFactory.CANNOT_FIND_FACELET_TAGLIB_ID:
                return Diagnostic.WARNING;
            default:
                throw new IllegalArgumentException(
                        "Diagnostic Id: " + diagnosticId + " is out of range"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * @param diagnosticId
     * @return the preference key for the corresponding diagnosticId in the el
     *         DiagnosticFactory
     */
    public static String getKeyById(final int diagnosticId)
    {
        switch (diagnosticId)
        {
            case FaceletDiagnosticFactory.CANNOT_FIND_FACELET_TAGLIB_ID:
                return CANNOT_FIND_FACELET_TAGLIB;
            default:
                throw new IllegalArgumentException(
                        "Diagnostic Id: " + diagnosticId + " is out of range"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * @param key
     * @return the preference key for the corresponding diagnosticId in the el
     *         DiagnosticFactory
     */
    public static int getIdByKey(final String key)
    {
        if (CANNOT_FIND_FACELET_TAGLIB.equals(key))
        {
            return FaceletDiagnosticFactory.CANNOT_FIND_FACELET_TAGLIB_ID;
        }
        throw new IllegalArgumentException("Severity Key: " + key); //$NON-NLS-1$
    }

    /**
     * e.g. createQualifiedKeyName("foo") -> org.eclipse.jst.jsf.core.foo
     * 
     * @param baseName
     * @return a plugin qualified key given the baseName
     * 
     */
    private static String createQualifiedKeyName(final String baseName)
    {
        return JSFCorePlugin.PLUGIN_ID + "." + baseName; //$NON-NLS-1$
    }

    /**
     * preference key. Match to DiagnosticFactory constants
     */
    public final static String CANNOT_FIND_FACELET_TAGLIB = createQualifiedKeyName("CANNOT_FIND_FACELET_TAGLIB"); //$NON-NLS-1$
}
