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
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Abstract validation visitor for classname based eobjects
 * 
 * @author cbateman
 *
 */
public abstract class ClassNameEObjectValidationVisitor extends
        EObjectValidationVisitor 
{
    /**
     * @param structuralFeature
     * @param version 
     */
    public ClassNameEObjectValidationVisitor(
            EStructuralFeature structuralFeature, String version) {
        super(structuralFeature, version);
    }

    protected void doValidate(EObject object, List messages, IFile file) 
    {
        String fullyQualifiedName = getFullyQualifiedName(object);
        // protect against null
        fullyQualifiedName = fullyQualifiedName == null ? "" : fullyQualifiedName; //$NON-NLS-1$
        addMessageInfo(messages,  
            AppConfigValidationUtil
                .validateClassName(fullyQualifiedName, getInstanceOf()
                        , false, file.getProject()),object, file);
    }

    /**
     * @param eobj
     * @return the fully qualified name from the eobject
     */
    protected abstract String getFullyQualifiedName(EObject eobj);
    
    /**
     * @return a fully-qualified 
     */
    protected abstract String getInstanceOf();
    
    /**
     * @return true if the class being named must be a class
     * and may not be an enum or interface
     */
    protected abstract boolean mustBeClass();
}
