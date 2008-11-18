/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.pagedesigner.dtmanager.dtinfo;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage
 * @generated
 */
public interface DTInfoFactory extends EFactory {
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
	DTInfoFactory eINSTANCE = org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>DT Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DT Info</em>'.
	 * @generated
	 */
	DTInfo createDTInfo();

	/**
	 * Returns a new object of class '<em>Tag Convert Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Convert Info</em>'.
	 * @generated
	 */
	TagConvertInfo createTagConvertInfo();

	/**
	 * Returns a new object of class '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation</em>'.
	 * @generated
	 */
	Operation createOperation();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns a new object of class '<em>Tag Decorate Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Decorate Info</em>'.
	 * @generated
	 */
	TagDecorateInfo createTagDecorateInfo();

	/**
	 * Returns a new object of class '<em>Resolve Attribute Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resolve Attribute Value</em>'.
	 * @generated
	 */
	ResolveAttributeValue createResolveAttributeValue();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DTInfoPackage getDTInfoPackage();

} //DTInfoFactory
