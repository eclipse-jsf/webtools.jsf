/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: QuickEditTabSectionsFactory.java,v 1.2 2008/11/18 22:22:35 gkessler Exp $
 */
package org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSectionsPackage
 * @generated
 */
public interface QuickEditTabSectionsFactory extends EFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QuickEditTabSectionsFactory eINSTANCE = org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.internal.impl.QuickEditTabSectionsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Quick Edit Tab Sections</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quick Edit Tab Sections</em>'.
	 * @generated
	 */
	QuickEditTabSections createQuickEditTabSections();

	/**
	 * Returns a new object of class '<em>Section Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Section Info</em>'.
	 * @generated
	 */
	SectionInfo createSectionInfo();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	QuickEditTabSectionsPackage getQuickEditTabSectionsPackage();

} //QuickEditTabSectionsFactory
