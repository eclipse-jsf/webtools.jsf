/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;

/**
 * Utility methods for model handling in tests
 * 
 */
public final class FacesConfigModelUtil 
{
    /**
     * @param navHandlers
     * @param descriptionText
     * @return the navigation rule from the list navRules
     * whose description text matches descriptionText or null if 
     * not found
     */
    public static NavigationRuleType findNavigationHandlerRuleByDescriptionText(List navRules, String descriptionText)
    {
        if (descriptionText == null)
        {
            return null;
        }
        
        for (final Iterator it = navRules.iterator(); it.hasNext();)
        {
            final NavigationRuleType navHandlerType = 
                (NavigationRuleType) it.next();
            
            if (findDescriptionType(navHandlerType.getDescription(), descriptionText) != null)
            {
                return navHandlerType;
            }
        }
        
        // not found
        return null;
    }
    
    /**
     * @param navRules
     * @param displayName
     * @return the the navigation rule from the list navRules
     * whose display nmae text matches displayName or null if 
     * not found
     */
    public static NavigationRuleType findNavigationHandlerRuleByDisplayName(List navRules, String displayName)
    {
        if (displayName == null)
        {
            return null;
        }
        
        for (final Iterator it = navRules.iterator(); it.hasNext();)
        {
            final NavigationRuleType navHandlerType = 
                (NavigationRuleType) it.next();
            
            if (findDisplayNameType(navHandlerType.getDisplayName(), displayName) != null)
            {
                return navHandlerType;
            }
        }
        
        // not found
        return null;
    }
    
    
    /**
     * @param descTypes
     * @param matchText
     * @return the DisplayNameType from descTypes corresponding to
     * matchText or null if not in list.  String.equals() is used
     * to match
     */
    public static DisplayNameType findDisplayNameType(List descTypes, String matchText)
    {
        if (matchText == null)
        {
            return null;
        }
        
        for (final Iterator it = descTypes.iterator(); it.hasNext();)
        {
            final DisplayNameType descType = (DisplayNameType) it.next();
            if (matchText.equals(descType.getTextContent()))
            {
                return descType;
            }
        }
        
        // if get through list and not found, then return null
        return null;
    }
    
    /**
     * @param descTypes
     * @param matchText
     * @return the DescriptionType from descTypes corresponding to
     * matchText or null if not in list.  String.equals() is used
     * to match
     */
    public static DescriptionType findDescriptionType(List descTypes, String matchText)
    {
        if (matchText == null)
        {
            return null;
        }
        
        for (final Iterator it = descTypes.iterator(); it.hasNext();)
        {
            final DescriptionType descType = (DescriptionType) it.next();
            if (matchText.equals(descType.getTextContent()))
            {
                return descType;
            }
        }
        
        // if get through list and not found, then return null
        return null;
    }
    
    public static IconType findIconTypeByLang(List iconTypes, String xmlLangMatch)
    {
        for (final Iterator it = iconTypes.iterator(); it.hasNext();)
        {
            final IconType icon = (IconType) it.next();
            if (xmlLangMatch == null && icon.getLang() == null)
            {
                return icon;
            }
            else if (xmlLangMatch != null && xmlLangMatch.equals(icon.getLang()))
            {
                return icon;
            }
        }
        
        // not found
        return null;
    }
    
    public static NavigationCaseType findNavigationCaseByDisplayName(List navigationCases, String displayName)
    {
        for (final Iterator it = navigationCases.iterator(); it.hasNext();)
        {
            final NavigationCaseType navCase = (NavigationCaseType) it.next();

            if (displayName != null && 
                    findDisplayNameType(navCase.getDisplayName(), displayName) != null)
            {
                return navCase;
            }
        }
        
        // not found
        return null;
    }
    
    public static ApplicationType findApplicationById(List applications, String id)
    {
        for (final Iterator it = applications.iterator(); it.hasNext();)
        {
            final ApplicationType app = (ApplicationType) it.next();

            if (id != null && id.equals(app.getId()))
            {
                return app;
            }
        }

        // not found
        return null;
    }
    
    public static ActionListenerType findActionListenerById(List actionListeners, String id)
    {
        for (final Iterator it = actionListeners.iterator(); it.hasNext();)
        {
            final ActionListenerType app = (ActionListenerType) it.next();

            if (id != null && id.equals(app.getId()))
            {
                return app;
            }
        }

        // not found
        return null;
    }
    
    /**
     * @param defaultRenderKits
     * @param id
     * @return
     */
    public static DefaultRenderKitIdType findDefaultRenderKitIdTypeById(List defaultRenderKits, String id)
    {
        for (final Iterator it = defaultRenderKits.iterator(); it.hasNext();)
        {
            final DefaultRenderKitIdType renderKitId = (DefaultRenderKitIdType) it.next();

            if (id != null && id.equals(renderKitId.getId()))
            {
                return renderKitId;
            }
        }

        // not found
        return null;
    }
    
    public static MessageBundleType findMessageBundleTypeById(List messageBundles, String id)
    {
        for (final Iterator it = messageBundles.iterator(); it.hasNext();)
        {
            final MessageBundleType messageBundle = (MessageBundleType) it.next();

            if (id != null && id.equals(messageBundle.getId()))
            {
                return messageBundle;
            }
        }

        // not found
        return null;
    }
    
    public static EObject findEObjectElementById(List eObjects, String id)
    {
        for (final Iterator it = eObjects.iterator(); it.hasNext();)
        {
            final EObject eObject = (EObject) it.next();
            
            final EStructuralFeature feature = 
                eObject.eClass().getEStructuralFeature("id");
            if (feature != null)
            {
                Object value = eObject.eGet(feature);
                
                if (value instanceof String
                        && value.equals(id))
                {
                    return eObject;
                }
            }
        }           
        // not found
        return null;
    }
}
