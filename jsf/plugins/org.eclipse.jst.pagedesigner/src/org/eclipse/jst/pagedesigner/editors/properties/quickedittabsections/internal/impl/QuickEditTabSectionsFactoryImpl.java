/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: QuickEditTabSectionsFactoryImpl.java,v 1.3 2008/11/18 22:22:35 gkessler Exp $
 */
package org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSections;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsFactory;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsPackage;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QuickEditTabSectionsFactoryImpl extends EFactoryImpl implements QuickEditTabSectionsFactory {
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
	public static QuickEditTabSectionsFactory init() {
		try {
			QuickEditTabSectionsFactory theQuickEditTabSectionsFactory = (QuickEditTabSectionsFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.eclipse.jsf.pagedesigner/QuickEditTabSections.ecore");  //$NON-NLS-1$
			if (theQuickEditTabSectionsFactory != null) {
				return theQuickEditTabSectionsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new QuickEditTabSectionsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuickEditTabSectionsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case QuickEditTabSectionsPackage.QUICK_EDIT_TAB_SECTIONS: return createQuickEditTabSections();
			case QuickEditTabSectionsPackage.SECTION_INFO: return createSectionInfo();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case QuickEditTabSectionsPackage.SECTION_TYPE:
				return createSECTION_TYPEFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case QuickEditTabSectionsPackage.SECTION_TYPE:
				return convertSECTION_TYPEToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuickEditTabSections createQuickEditTabSections() {
		QuickEditTabSectionsImpl quickEditTabSections = new QuickEditTabSectionsImpl();
		return quickEditTabSections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SectionInfo createSectionInfo() {
		SectionInfoImpl sectionInfo = new SectionInfoImpl();
		return sectionInfo;
	}

	/**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param initialValue 
     * @return the SECTION_TYPE enumerator 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SECTION_TYPE createSECTION_TYPEFromString(EDataType eDataType, String initialValue) {
		SECTION_TYPE result = SECTION_TYPE.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
     * @param eDataType 
     * @param instanceValue 
     * @return the String version of the section type 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSECTION_TYPEToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuickEditTabSectionsPackage getQuickEditTabSectionsPackage() {
		return (QuickEditTabSectionsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the package 
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static QuickEditTabSectionsPackage getPackage() {
		return QuickEditTabSectionsPackage.eINSTANCE;
	}

} //QuickEditTabSectionsFactoryImpl
