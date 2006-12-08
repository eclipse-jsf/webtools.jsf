/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryPackage;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plugin Provided JSF Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.PluginProvidedJSFLibraryImpl#getPluginID <em>Plugin ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PluginProvidedJSFLibraryImpl extends JSFLibraryImpl implements PluginProvidedJSFLibrary {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005 Oracle Corporation";

	/**
	 * The default value of the '{@link #getPluginID() <em>Plugin ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginID()
	 * @generated
	 * @ordered
	 */
	protected static final String PLUGIN_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPluginID() <em>Plugin ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginID()
	 * @generated
	 * @ordered
	 */
	protected String pluginID = PLUGIN_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PluginProvidedJSFLibraryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the static eclass 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JSFLibraryRegistryPackage.eINSTANCE.getPluginProvidedJSFLibrary();
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the plugin id 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPluginID() {
		return pluginID;
	}

	/**
	 * <!-- begin-user-doc -->
     * @param newPluginID 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPluginID(String newPluginID) {
		String oldPluginID = pluginID;
		pluginID = newPluginID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__PLUGIN_ID, oldPluginID, pluginID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @return the working copy 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public JSFLibrary getWorkingCopy() {
		PluginProvidedJSFLibrary workingCopyLib = JSFLibraryRegistryFactory.eINSTANCE.createPluginProvidedJSFLibrary();
		workingCopyLib.setID(getID());
		workingCopyLib.setName(getName());
		workingCopyLib.setJSFVersion(getJSFVersion());
		workingCopyLib.setDeployed(isDeployed());
		workingCopyLib.setImplementation(isImplementation());
		workingCopyLib.setPluginID(getPluginID());
		Iterator itArchiveFiles = getArchiveFiles().iterator();
		while (itArchiveFiles.hasNext()) {
			ArchiveFile srcArchiveFile = (ArchiveFile)itArchiveFiles.next();
			ArchiveFile destArchiveFile = JSFLibraryRegistryFactory.eINSTANCE.createArchiveFile();
			destArchiveFile.setRelativeToWorkspace(srcArchiveFile.isRelativeToWorkspace());
			destArchiveFile.setSourceLocation(srcArchiveFile.getSourceLocation());
			destArchiveFile.setRelativeDestLocation(srcArchiveFile.getRelativeDestLocation());
			workingCopyLib.getArchiveFiles().add(destArchiveFile);
		}
		return workingCopyLib;
	}

	/**
	 * <!-- begin-user-doc -->
     * @param otherEnd 
     * @param featureID 
     * @param baseClass 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__ARCHIVE_FILES:
					return ((InternalEList)getArchiveFiles()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * @param otherEnd 
     * @param featureID 
     * @param baseClass 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__ARCHIVE_FILES:
					return ((InternalEList)getArchiveFiles()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * @param eFeature 
     * @param resolve 
     * @return the value of the feature 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__ID:
				return getID();
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__NAME:
				return getName();
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__JSF_VERSION:
				return getJSFVersion();
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__DEPLOYED:
				return isDeployed() ? Boolean.TRUE : Boolean.FALSE;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__IMPLEMENTATION:
				return isImplementation() ? Boolean.TRUE : Boolean.FALSE;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__ARCHIVE_FILES:
				return getArchiveFiles();
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__PLUGIN_ID:
				return getPluginID();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
     * @param eFeature 
     * @param newValue 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__ID:
				setID((String)newValue);
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__NAME:
				setName((String)newValue);
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__JSF_VERSION:
				setJSFVersion((JSFVersion)newValue);
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__DEPLOYED:
				setDeployed(((Boolean)newValue).booleanValue());
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__IMPLEMENTATION:
				setImplementation(((Boolean)newValue).booleanValue());
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__ARCHIVE_FILES:
				getArchiveFiles().clear();
				getArchiveFiles().addAll((Collection)newValue);
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__PLUGIN_ID:
				setPluginID((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
     * @param eFeature 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__ID:
				setID(ID_EDEFAULT);
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__JSF_VERSION:
				setJSFVersion(JSF_VERSION_EDEFAULT);
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__DEPLOYED:
				setDeployed(DEPLOYED_EDEFAULT);
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__IMPLEMENTATION:
				setImplementation(IMPLEMENTATION_EDEFAULT);
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__ARCHIVE_FILES:
				getArchiveFiles().clear();
				return;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__PLUGIN_ID:
				setPluginID(PLUGIN_ID_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * @param eFeature 
     * @return true if the value of eFeature is set 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__JSF_VERSION:
				return jsfVersion != JSF_VERSION_EDEFAULT;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__DEPLOYED:
				return deployed != DEPLOYED_EDEFAULT;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__IMPLEMENTATION:
				return implementation != IMPLEMENTATION_EDEFAULT;
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__ARCHIVE_FILES:
				return archiveFiles != null && !archiveFiles.isEmpty();
			case JSFLibraryRegistryPackage.PLUGIN_PROVIDED_JSF_LIBRARY__PLUGIN_ID:
				return PLUGIN_ID_EDEFAULT == null ? pluginID != null : !PLUGIN_ID_EDEFAULT.equals(pluginID);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * @return the string representation 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (pluginID: ");
		result.append(pluginID);
		result.append(')');
		return result.toString();
	}

} //PluginProvidedJSFLibraryImpl
