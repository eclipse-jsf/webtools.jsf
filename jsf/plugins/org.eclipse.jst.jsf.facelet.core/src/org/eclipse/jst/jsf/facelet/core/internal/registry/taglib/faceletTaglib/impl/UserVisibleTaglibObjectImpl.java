/**
 * <copyright>
 * </copyright>
 *
 * $Id: UserVisibleTaglibObjectImpl.java,v 1.1 2010/03/18 06:24:38 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Description;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.DisplayName;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.UserVisibleTaglibObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Visible Taglib Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.UserVisibleTaglibObjectImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.UserVisibleTaglibObjectImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.UserVisibleTaglibObjectImpl#getIcon <em>Icon</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class UserVisibleTaglibObjectImpl extends EObjectImpl implements UserVisibleTaglibObject
{
    /**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
    protected EList<Description> description;

    /**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
    protected EList<DisplayName> displayName;

    /**
	 * The cached value of the '{@link #getIcon() <em>Icon</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
    protected EList<Icon> icon;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected UserVisibleTaglibObjectImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass()
    {
		return FaceletTaglibPackage.Literals.USER_VISIBLE_TAGLIB_OBJECT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Description> getDescription()
    {
		if (description == null) {
			description = new EObjectContainmentEList<Description>(Description.class, this, FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION);
		}
		return description;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<DisplayName> getDisplayName()
    {
		if (displayName == null) {
			displayName = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME);
		}
		return displayName;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList<Icon> getIcon()
    {
		if (icon == null) {
			icon = new EObjectContainmentEList<Icon>(Icon.class, this, FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__ICON);
		}
		return icon;
	}

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public String getDescription(String language, String separationString)
    {
        return Util.concatDesc(this, getDescription(), language, separationString);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public String getDefaultDescription(String separationString)
    {
        return getDescription("", separationString); //$NON-NLS-1$
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getDisplayName(String language, String separationString)
    {
        return Util.concatDesc(this, getDescription(), language, separationString);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getDefaultDisplayName(String separationString)
    {
        return getDisplayName("", separationString); //$NON-NLS-1$
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
		switch (featureID) {
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION:
				return ((InternalEList<?>)getDescription()).basicRemove(otherEnd, msgs);
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME:
				return ((InternalEList<?>)getDisplayName()).basicRemove(otherEnd, msgs);
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__ICON:
				return ((InternalEList<?>)getIcon()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION:
				return getDescription();
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME:
				return getDisplayName();
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__ICON:
				return getIcon();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION:
				getDescription().clear();
				getDescription().addAll((Collection<? extends Description>)newValue);
				return;
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME:
				getDisplayName().clear();
				getDisplayName().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__ICON:
				getIcon().clear();
				getIcon().addAll((Collection<? extends Icon>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public void eUnset(int featureID)
    {
		switch (featureID) {
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION:
				getDescription().clear();
				return;
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME:
				getDisplayName().clear();
				return;
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__ICON:
				getIcon().clear();
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public boolean eIsSet(int featureID)
    {
		switch (featureID) {
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DESCRIPTION:
				return description != null && !description.isEmpty();
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__DISPLAY_NAME:
				return displayName != null && !displayName.isEmpty();
			case FaceletTaglibPackage.USER_VISIBLE_TAGLIB_OBJECT__ICON:
				return icon != null && !icon.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UserVisibleTaglibObjectImpl
