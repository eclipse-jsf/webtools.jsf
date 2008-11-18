/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: PaletteInfosImpl.java,v 1.2 2008/11/18 22:24:03 gkessler Exp $
 */
package org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfos;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Palette Infos</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.impl.PaletteInfosImpl#getInfos <em>Infos</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PaletteInfosImpl extends EObjectImpl implements PaletteInfos {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getInfos() <em>Infos</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInfos()
	 * @generated
	 * @ordered
	 */
	protected EList infos;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PaletteInfosImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PaletteInfosPackage.Literals.PALETTE_INFOS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getInfos() {
		if (infos == null) {
			infos = new EObjectContainmentEList(PaletteInfo.class, this, PaletteInfosPackage.PALETTE_INFOS__INFOS);
		}
		return infos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public PaletteInfo findPaletteInfoById(String id) {
		for (Iterator it=getInfos().iterator();it.hasNext();){
			PaletteInfo info = (PaletteInfo)it.next();
			if (id.equals(info.getId()))
				return info;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PaletteInfosPackage.PALETTE_INFOS__INFOS:
				return ((InternalEList)getInfos()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PaletteInfosPackage.PALETTE_INFOS__INFOS:
				return getInfos();
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
			case PaletteInfosPackage.PALETTE_INFOS__INFOS:
				getInfos().clear();
				getInfos().addAll((Collection)newValue);
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
			case PaletteInfosPackage.PALETTE_INFOS__INFOS:
				getInfos().clear();
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
			case PaletteInfosPackage.PALETTE_INFOS__INFOS:
				return infos != null && !infos.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PaletteInfosImpl
