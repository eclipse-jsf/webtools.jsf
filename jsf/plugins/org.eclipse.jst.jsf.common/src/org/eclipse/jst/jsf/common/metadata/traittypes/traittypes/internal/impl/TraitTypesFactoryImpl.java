/**
 * <copyright>
 * </copyright>
 *
 * $Id: TraitTypesFactoryImpl.java,v 1.3 2007/02/28 21:12:40 cbateman Exp $
 */
package org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.internal.provisional.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TraitTypesFactoryImpl extends EFactoryImpl implements TraitTypesFactory {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007 Oracle Corporation";

    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static TraitTypesFactory init() {
        try {
            TraitTypesFactory theTraitTypesFactory = (TraitTypesFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.eclipse.jst.jsf.common.metadata/metadataTraitTypes.ecore"); 
            if (theTraitTypesFactory != null) {
                return theTraitTypesFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TraitTypesFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TraitTypesFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case TraitTypesPackage.LIST_OF_VALUES: return createListOfValues();
            case TraitTypesPackage.SET_GENERATOR: return createSetGenerator();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ListOfValues createListOfValues() {
        ListOfValuesImpl listOfValues = new ListOfValuesImpl();
        return listOfValues;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SetGenerator createSetGenerator() {
        SetGeneratorImpl setGenerator = new SetGeneratorImpl();
        return setGenerator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TraitTypesPackage getTraitTypesPackage() {
        return (TraitTypesPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    public static TraitTypesPackage getPackage() {
        return TraitTypesPackage.eINSTANCE;
    }

} //TraitTypesFactoryImpl
