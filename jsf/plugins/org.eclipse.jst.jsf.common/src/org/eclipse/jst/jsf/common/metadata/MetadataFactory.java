/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.jsf.common.metadata;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage
 * @generated
 */
public interface MetadataFactory extends EFactory {
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
	MetadataFactory eINSTANCE = org.eclipse.jst.jsf.common.metadata.internal.impl.MetadataFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Trait</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trait</em>'.
	 * @generated
	 */
	Trait createTrait();

	/**
	 * Returns a new object of class '<em>Include Entity Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Include Entity Group</em>'.
	 * @generated
	 */
	IncludeEntityGroup createIncludeEntityGroup();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	Model createModel();

	/**
	 * Returns a new object of class '<em>Entity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity</em>'.
	 * @generated
	 */
	Entity createEntity();

	/**
	 * Returns a new object of class '<em>Entity Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entity Group</em>'.
	 * @generated
	 */
	EntityGroup createEntityGroup();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MetadataPackage getMetadataPackage();

} //MetadataFactory
