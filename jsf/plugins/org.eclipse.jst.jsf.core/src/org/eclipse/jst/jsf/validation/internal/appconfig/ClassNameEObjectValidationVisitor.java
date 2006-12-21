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
        fullyQualifiedName = fullyQualifiedName == null ? "" : fullyQualifiedName;
        addMessageInfo(messages,  
            AppConfigValidationUtil
                .validateClassName(fullyQualifiedName, getInstanceOf(), 
                        file.getProject())
                        ,object, file);
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
}
