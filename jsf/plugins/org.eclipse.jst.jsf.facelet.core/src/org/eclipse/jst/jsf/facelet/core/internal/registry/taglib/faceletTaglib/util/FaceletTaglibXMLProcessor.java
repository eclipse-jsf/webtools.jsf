/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibXMLProcessor.java,v 1.1 2010/03/18 06:24:40 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FaceletTaglibXMLProcessor extends XMLProcessor
{

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglibXMLProcessor()
    {
        super((EPackage.Registry.INSTANCE));
        FaceletTaglibPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the FaceletTaglibResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations()
    {
        if (registrations == null)
        {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new FaceletTaglibResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new FaceletTaglibResourceFactoryImpl());
        }
        return registrations;
    }

} //FaceletTaglibXMLProcessor
