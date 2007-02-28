/**
 * <copyright>
 * </copyright>
 *
 * $Id: ConstraintsXMLProcessor.java,v 1.1 2007/02/28 21:16:02 cbateman Exp $
 */
package org.eclipse.jst.jsf.validation.internal.constraints.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConstraintsXMLProcessor extends XMLProcessor {
    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConstraintsXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        ConstraintsPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the ConstraintsResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Map getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new ConstraintsResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new ConstraintsResourceFactoryImpl());
        }
        return registrations;
    }

} //ConstraintsXMLProcessor
