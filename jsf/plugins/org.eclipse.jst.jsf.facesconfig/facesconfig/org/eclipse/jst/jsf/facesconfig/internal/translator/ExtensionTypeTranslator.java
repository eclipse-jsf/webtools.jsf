package org.eclipse.jst.jsf.facesconfig.internal.translator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public abstract class ExtensionTypeTranslator extends Translator 
{
    public ExtensionTypeTranslator(String domNameAndPath, EStructuralFeature aFeature) 
    {
        super(domNameAndPath, aFeature);
    }
    
    public Translator[] getChildren() {
        
        FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
        return new Translator[] {
              new Translator("id", facesPackage.getExtensionType_Id(), DOM_ATTRIBUTE), //$NON-NLS-1$
              new DynamicElementTranslator("*", facesPackage.getExtensionType_ChildNodes())
        };
    }
}
