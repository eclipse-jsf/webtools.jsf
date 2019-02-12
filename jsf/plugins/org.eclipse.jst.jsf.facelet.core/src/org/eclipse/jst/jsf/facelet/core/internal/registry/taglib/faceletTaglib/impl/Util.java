/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description;
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

    public static String concatDesc(final EObject listOwner, final EList<Description> list, final String filterString,
    		final String separationString)
    {
        if (filterString == null)
        {
            throw new NullPointerException("language must not be null"); //$NON-NLS-1$
        }

        String retString = ""; //$NON-NLS-1$
        for (final Description obj : list)
        {
            if (obj != null
                    && passesFilter(filterString.length() == 0 ? null
                            : filterString, obj)) {
                FeatureMap contents = obj.getAny();
                Object object = getDescriptionValues(contents);
                if (object instanceof String) {
                    
                    retString += (String) object;
                }
                else if (object instanceof Collection)
                {
                    Iterator<Object> it = ((Collection)object).iterator();
                    while (it.hasNext())
                    {
                        Object next = it.next();
                        if (next instanceof String)
                        {
                            retString += (String) next;
                        }
                    }
                }
            }
        }
        return retString;
    }
    
    private static Object getDescriptionValues(FeatureMap contents)
    {
        Object object = contents.get(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, true);
        if (object instanceof Collection && hasNoSignificantContent((Collection) object))
        {
            object = contents.get(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA, true);
        }
        return object;
    }

    private static boolean hasNoSignificantContent(Collection col)
    {
    	boolean empty = true;
    	if (col != null && !col.isEmpty())
    	{
    		for (Object obj: col)
    		{
    			if (obj instanceof String)
    			{
    				String str = (String) obj;
    				if (str.replace('\n', ' ').trim().length() > 0)
    				{
    					empty = false;
    					break;
    				}
    			}
    		}
    	}
    	return empty;
    }

    private static boolean passesFilter(final String expectedLang,
            final IdentifiableLangStringValue langOwner)
    {
        final Object lang = langOwner.getLang();
        return ((expectedLang == null && lang == null) || (expectedLang != null && expectedLang
                .equals(lang)));
    }
    
    private static boolean passesFilter(final String expectedLang,
    		final Description langOwner)
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
