/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DTInfoFactoryImpl extends EFactoryImpl implements DTInfoFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * @return the factory 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DTInfoFactory init() {
		try {
			DTInfoFactory theDTInfoFactory = (DTInfoFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.eclipse.jsf.pagedesigner/dtinfo.ecore");  //$NON-NLS-1$
			if (theDTInfoFactory != null) {
				return theDTInfoFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DTInfoFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DTInfoFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DTInfoPackage.DT_INFO: return createDTInfo();
			case DTInfoPackage.TAG_CONVERT_INFO: return createTagConvertInfo();
			case DTInfoPackage.OPERATION: return createOperation();
			case DTInfoPackage.PARAMETER: return createParameter();
			case DTInfoPackage.TAG_DECORATE_INFO: return createTagDecorateInfo();
			case DTInfoPackage.RESOLVE_ATTRIBUTE_VALUE: return createResolveAttributeValue();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DTInfo createDTInfo() {
		DTInfoImpl dtInfo = new DTInfoImpl();
		return dtInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagConvertInfo createTagConvertInfo() {
		TagConvertInfoImpl tagConvertInfo = new TagConvertInfoImpl();
		return tagConvertInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation createOperation() {
		OperationImpl operation = new OperationImpl();
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagDecorateInfo createTagDecorateInfo() {
		TagDecorateInfoImpl tagDecorateInfo = new TagDecorateInfoImpl();
		return tagDecorateInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResolveAttributeValue createResolveAttributeValue() {
		ResolveAttributeValueImpl resolveAttributeValue = new ResolveAttributeValueImpl();
		return resolveAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DTInfoPackage getDTInfoPackage() {
		return (DTInfoPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
     * @return  the package
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static DTInfoPackage getPackage() {
		return DTInfoPackage.eINSTANCE;
	}

} //DTInfoFactoryImpl
