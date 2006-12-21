package org.eclipse.jst.jsf.facesconfig.internal.translator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public class BaseNameTranslator extends Translator {

    public BaseNameTranslator(String domNameAndPath, EStructuralFeature feature) {
        super(domNameAndPath, feature);
    }

    public Translator[] getChildren()
    {
        FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;

        return new Translator[] {
            new Translator(TEXT_ATTRIBUTE_VALUE, facesPackage.getBaseNameType_TextContent()),
            new Translator("id", facesPackage.getBaseNameType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
        };
    }
}
