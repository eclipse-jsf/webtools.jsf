package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableLangStringValue;

/**
 * A private utility class for use by Facelet Taglib model impls.
 * 
 * @author cbateman
 * 
 */
class Util
{
    public static String concat(final EObject listOwner,
            final EList<? extends IdentifiableLangStringValue> list,
            final String filterString,
            final String separationString)
    {
        if (filterString == null)
        {
            throw new NullPointerException("language must not be null"); //$NON-NLS-1$
        }

        String retString = ""; //$NON-NLS-1$
        for (final IdentifiableLangStringValue obj : list)
        {
            if (obj != null
                    && passesFilter(filterString.length() == 0 ? null
                            : filterString, obj))
            {
                String value = obj.getValue();
                if (value != null)
                {
                    value = value.trim();
                    if (retString.length() != 0)
                    {
                        retString += separationString;
                    }
                    retString += value;
                }
            }
        }
        return retString;
    }

    private static boolean passesFilter(final String expectedLang,
            final IdentifiableLangStringValue langOwner)
    {
        final Object lang = langOwner.getLang();
        return ((expectedLang == null && lang == null) || (expectedLang != null && expectedLang
                .equals(lang)));
    }
    
    public static Object getSimplifiedNestedField(final EObject owner,
            final EReference firstLevelFeature, EAttribute simplifiedFeature)
    {
        Object complexObject = owner.eGet(firstLevelFeature);
        if (complexObject instanceof EObject)
        {
            return ((EObject) complexObject).eGet(simplifiedFeature);
        }
        return null;
    }
    
    public static void setSimplifiedNestedField(final EObject owner,
            final EReference firstLevelFeature, EAttribute simplifiedFeature,
            final Object newSimpleValue)
    {
        Object complexObject = owner.eGet(firstLevelFeature);
        if (complexObject == null)
        {
            complexObject = EcoreUtil.create((EClass) firstLevelFeature.getEType());
            ((EObject) complexObject).eSet(simplifiedFeature, newSimpleValue);
            owner.eSet(firstLevelFeature, complexObject);
        } else
        {
            ((EObject) complexObject).eSet(simplifiedFeature, newSimpleValue);
        }

    }
}
