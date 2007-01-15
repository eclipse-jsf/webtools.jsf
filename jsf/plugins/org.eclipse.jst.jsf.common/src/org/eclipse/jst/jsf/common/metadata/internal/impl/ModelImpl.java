/**
 * <copyright>
 * </copyright>
 *
 * $Id: ModelImpl.java,v 1.1 2007/01/15 23:26:15 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.ModelImpl#getSourceModelProvider <em>Source Model Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelImpl extends EntityImpl implements Model {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * The default value of the '{@link #getSourceModelProvider() <em>Source Model Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceModelProvider()
	 * @generated
	 * @ordered
	 */
	protected static final IMetaDataSourceModelProvider SOURCE_MODEL_PROVIDER_EDEFAULT = null;

	private IMetaDataSourceModelProvider sourceModelProvider;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * May return null.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public IMetaDataSourceModelProvider getSourceModelProvider() {
		return sourceModelProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setSourceModelProvider(IMetaDataSourceModelProvider newSourceModelProvider) {
		sourceModelProvider = newSourceModelProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.MODEL__SOURCE_MODEL_PROVIDER:
				return getSourceModelProvider();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetadataPackage.MODEL__SOURCE_MODEL_PROVIDER:
				setSourceModelProvider((IMetaDataSourceModelProvider)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case MetadataPackage.MODEL__SOURCE_MODEL_PROVIDER:
				setSourceModelProvider(SOURCE_MODEL_PROVIDER_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MetadataPackage.MODEL__SOURCE_MODEL_PROVIDER:
				return SOURCE_MODEL_PROVIDER_EDEFAULT == null ? getSourceModelProvider() != null : !SOURCE_MODEL_PROVIDER_EDEFAULT.equals(getSourceModelProvider());
		}
		return super.eIsSet(featureID);
	}

} //ModelImpl
