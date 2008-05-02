/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * Recursive validator for navigation-rule
 * 
 * @author cbateman
 *
 */
public class NavigationRuleValidator extends EObjectValidationVisitor 
{
    /**
     * Constructor
     * @param version 
     */
    public NavigationRuleValidator(final String version) 
    {
        super(FacesConfigPackage.eINSTANCE.getFacesConfigType_NavigationRule()
                , version);
    }

    protected void doValidate(EObject eobj, List messages, IFile file) 
    {
        // nothing to do
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() 
    {
        return new EObjectValidationVisitor[]
        {
            new NavigationCaseValidationVisitor(getVersion())
        };
    }
//    private static class FromViewIdValidator extends ViewIdValidator
//    {
//        FromViewIdValidator()
//        {
//            super(Node.ELEMENT_NODE, "from-view-id");
//        }
//
//        protected void doValidate(Node node, List messages, IFile file) 
//        {
//            // only validate if a custom navigation handler is not set
//            // since we can only validate against what the default navigation
//            // handler will expect
//            if (!hasCustomNavigationHandler(file))
//            {
//                final String textContent = node.getTextContent();
//                final IProject project = file.getProject();
//                
//                
//            }
//        }
//        
//        private boolean hasCustomNavigationHandler(IFile file)
//        {
//            JSFAppConfigManager configManager = 
//                JSFAppConfigManager.getInstance(file.getProject());
//            
//            for (final Iterator appIt = configManager.getApplications().iterator(); appIt.hasNext();)
//            {
//                ApplicationType appType = (ApplicationType) appIt.next();
//                if (appType.getNavigationHandler().size() > 0)
//                {
//                    return true;
//                }
//            }
//            
//            return false;
//        }
//
//        protected NodeValidationVisitor[] getChildNodeValidators() {
//            return EMPTY_CHILDREN;
//        }
//    }
}
