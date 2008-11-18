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
package org.eclipse.jst.jsf.validation.internal;

import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.jsf.common.internal.types.TypeComparatorDiagnosticFactory;
import org.eclipse.jst.jsf.common.internal.types.TypeComparatorPreferences;
import org.eclipse.jst.jsf.core.internal.IJSFPreferenceModel;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Type comparator preferences for JSF.
 * 
 * @author cbateman
 * 
 */
public class JSFTypeComparatorPreferences extends TypeComparatorPreferences
        implements IJSFPreferenceModel
{

    private int[] _severities;

    /**
     * Loads the object from the preference store provided
     * 
     * @param prefStore
     */
    public void load(IPreferenceStore prefStore)
    {
        loadSeverities(prefStore);
    }

    private void loadSeverities(final IPreferenceStore prefStore)
    {
        final int severities[] = getSeverities();

        for (int i = 0; i < TypeComparatorDiagnosticFactory.NUM_IDS; i++)
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
    public void commit(IPreferenceStore prefStore)
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

        for (int i = 0; i < TypeComparatorDiagnosticFactory.NUM_IDS; i++)
        {
            severities[i] = getDefaultSeverity(i);
        }
    }

    public Object getValueByKey(IScopeContext context, String key)
    {
        try
        {
            final Severity severity = getSeverity(key);
            return severity.toString();
        }
        catch (IllegalArgumentException e)
        {
            // getIdByKey will throw this exception if key is not a valid
            // severity key. Ignore the exception here and fall-through
        }

        return null; // not found
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.jsf.core.internal.IJSFPreferenceModel#getStoredValueByKey(org.eclipse.core.runtime.preferences.IScopeContext,
     *      java.lang.String)
     */
    public Object getStoredValueByKey(IScopeContext context, String key)
    {
        try
        {
            return context.getNode("org.eclipse.jst.jsf.core").get( //$NON-NLS-1$
                    key,
                    mapDiagToSeverity(getDefaultSeverity(getIdByKey(key)))
                            .toString());
        }
        catch (IllegalArgumentException e)
        {
            // getIdByKey will throw this exception if key is not a valid
            // severity key. Ignore the exception here and fall-through
        }

        return null; // not found
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.jsf.core.internal.IJSFPreferenceModel#setValueByKey(org.eclipse.core.runtime.preferences.IScopeContext,
     *      java.lang.String, java.lang.Object)
     */
    public Object setValueByKey(IScopeContext context, String key, Object value)
    {
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
            _severities = new int[TypeComparatorDiagnosticFactory.NUM_IDS];
        }

        return _severities;
    }

    /**
     * @param diagSeverity
     * @return a Severity preference value for a diagnostic severity
     */
    public static Severity mapDiagToSeverity(int diagSeverity)
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
    public static int mapSeverityToDiag(String severity)
    {
        if ("error".equals(severity)) //$NON-NLS-1$
        {
            return Diagnostic.ERROR;
        }
        else if ("warning".equals(severity)) //$NON-NLS-1$
        {
            return Diagnostic.WARNING;
        }
        else if ("ignore".equals(severity)) //$NON-NLS-1$
        {
            return Diagnostic.OK;
        }
        else
        {
            throw new IllegalArgumentException("Invalid enum name: " + severity); //$NON-NLS-1$
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
            case TypeComparatorDiagnosticFactory.INCOMPATIBLE_METHOD_TYPES_ID:
                return INCOMPATIBLE_METHOD_TYPES;
            case TypeComparatorDiagnosticFactory.INCOMPATIBLE_TYPES_ID:
                return INCOMPATIBLE_TYPES;
            case TypeComparatorDiagnosticFactory.METHOD_EXPRESSION_EXPECTED_ID:
                return METHOD_EXPRESSION_EXPECTED;
            case TypeComparatorDiagnosticFactory.PROPERTY_NOT_READABLE_ID:
                return PROPERTY_NOT_READABLE;
            case TypeComparatorDiagnosticFactory.PROPERTY_NOT_WRITABLE_ID:
                return PROPERTY_NOT_WRITABLE;
            case TypeComparatorDiagnosticFactory.VALUE_EXPRESSION_EXPECTED_ID:
                return VALUE_EXPRESSION_EXPECTED;
            default:
                throw new IllegalArgumentException("Diagnostic Id: " //$NON-NLS-1$
                        + diagnosticId + " is out of range"); //$NON-NLS-1$
        }
    }

    /**
     * @param key
     * @return the preference key for the corresponding diagnosticId in the el
     *         DiagnosticFactory
     */
    public static int getIdByKey(final String key)
    {
        if (INCOMPATIBLE_METHOD_TYPES.equals(key))
        {
            return TypeComparatorDiagnosticFactory.INCOMPATIBLE_METHOD_TYPES_ID;
        }
        else if (INCOMPATIBLE_TYPES.equals(key))
        {
            return TypeComparatorDiagnosticFactory.INCOMPATIBLE_TYPES_ID;
        }
        else if (METHOD_EXPRESSION_EXPECTED.equals(key))
        {
            return TypeComparatorDiagnosticFactory.METHOD_EXPRESSION_EXPECTED_ID;
        }
        else if (PROPERTY_NOT_READABLE.equals(key))
        {
            return TypeComparatorDiagnosticFactory.PROPERTY_NOT_READABLE_ID;
        }
        else if (PROPERTY_NOT_WRITABLE.equals(key))
        {
            return TypeComparatorDiagnosticFactory.PROPERTY_NOT_WRITABLE_ID;
        }
        else if (VALUE_EXPRESSION_EXPECTED.equals(key))
        {
            return TypeComparatorDiagnosticFactory.VALUE_EXPRESSION_EXPECTED_ID;
        }
        else
        {
            throw new IllegalArgumentException("Severity Key: " + key); //$NON-NLS-1$
        }
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
    public final static String INCOMPATIBLE_METHOD_TYPES  = createQualifiedKeyName("INCOMPATIBLE_METHOD_TYPES"); //$NON-NLS-1$
    /**
     * preference key. Match to DiagnosticFactory constants
     */
    public final static String INCOMPATIBLE_TYPES         = createQualifiedKeyName("INCOMPATIBLE_TYPES"); //$NON-NLS-1$
    /**
     * preference key. Match to DiagnosticFactory constants
     */
    public final static String METHOD_EXPRESSION_EXPECTED = createQualifiedKeyName("METHOD_EXPRESSION_EXPECTED"); //$NON-NLS-1$
    /**
     * preference key. Match to DiagnosticFactory constants
     */
    public final static String PROPERTY_NOT_READABLE      = createQualifiedKeyName("PROPERTY_NOT_READABLE"); //$NON-NLS-1$
    /**
     * preference key. Match to DiagnosticFactory constants
     */
    public final static String PROPERTY_NOT_WRITABLE      = createQualifiedKeyName("PROPERTY_NOT_WRITABLE"); //$NON-NLS-1$
    /**
     * preference key. Match to DiagnosticFactory constants
     */
    public final static String VALUE_EXPRESSION_EXPECTED  = createQualifiedKeyName("VALUE_EXPRESSION_EXPECTED"); //$NON-NLS-1$
}
