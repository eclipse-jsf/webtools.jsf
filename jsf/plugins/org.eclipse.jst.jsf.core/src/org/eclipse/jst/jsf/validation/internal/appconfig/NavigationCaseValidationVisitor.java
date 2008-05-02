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
import org.eclipse.jst.jsf.facesconfig.emf.FromActionType;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * NavigationCase validation visitor.
 * 
 * @author cbateman
 *
 */
public class NavigationCaseValidationVisitor extends EObjectValidationVisitor 
{
    /**
     * @param version 
     * 
     */
    public NavigationCaseValidationVisitor(final String version) 
    {
        super(FacesConfigPackage.eINSTANCE.getNavigationRuleType_NavigationCase()
                , version);
    }

    protected void doValidate(EObject object, List messages, IFile file) 
    {
        // nothing to do
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() 
    {
        return new EObjectValidationVisitor[]
        {
            new FromActionValidationVisitor(getVersion())
        };
    }

    private static class FromActionValidationVisitor extends EObjectValidationVisitor
    {
        /**
         * @param version 
         * 
         */
        public FromActionValidationVisitor(String version) {
            super(FacesConfigPackage.eINSTANCE.getNavigationCaseType_FromAction(),
                    version);
        }

        protected void doValidate(EObject object, List messages, IFile file) 
        {
            if (object instanceof FromActionType)
            {
                final FromActionType actionType = (FromActionType) object;
                IMessage message = AppConfigValidationUtil.validateELExpression
                    (actionType.getTextContent());
                addMessageInfo(messages, message, actionType, file);
            }
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() 
        {
            return NO_CHILDREN;
        }
    }
}
