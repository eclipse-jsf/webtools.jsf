/**
 * <copyright>
 * </copyright>
 *
 * $Id: TraitTypesXMLProcessor.java,v 1.3.4.1 2007/04/16 19:40:16 itrimble Exp $
 */
package org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.TraitTypesPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TraitTypesXMLProcessor extends XMLProcessor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007 Oracle Corporation";


    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TraitTypesXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        TraitTypesPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the TraitTypesResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Map getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new TraitTypesResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new TraitTypesResourceFactoryImpl());
        }
        return registrations;
    }

} //TraitTypesXMLProcessor
