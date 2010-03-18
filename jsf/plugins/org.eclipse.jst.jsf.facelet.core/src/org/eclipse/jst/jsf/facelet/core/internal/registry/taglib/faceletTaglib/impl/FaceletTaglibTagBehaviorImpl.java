/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibTagBehaviorImpl.java,v 1.1 2010/03/18 06:24:38 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehaviorExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facelet Taglib Tag Behavior</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagBehaviorImpl#getBehaviorId <em>Behavior Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagBehaviorImpl#getHandlerClass <em>Handler Class</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagBehaviorImpl#getBehaviorExtension <em>Behavior Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FaceletTaglibTagBehaviorImpl extends UserVisibleTaglibObjectImpl implements FaceletTaglibTagBehavior
{
    /**
     * The cached value of the '{@link #getBehaviorId() <em>Behavior Id</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBehaviorId()
     * @generated
     * @ordered
     */
    protected IdentifiableStringValue behaviorId;

    /**
     * The cached value of the '{@link #getHandlerClass() <em>Handler Class</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHandlerClass()
     * @generated
     * @ordered
     */
    protected FullyQualifiedClass handlerClass;

    /**
     * The cached value of the '{@link #getBehaviorExtension() <em>Behavior Extension</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBehaviorExtension()
     * @generated
     * @ordered
     */
    protected EList<FaceletTaglibTagBehaviorExtension> behaviorExtension;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FaceletTaglibTagBehaviorImpl()
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
        return FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG_BEHAVIOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IdentifiableStringValue getBehaviorId()
    {
        return behaviorId;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newBehaviorId 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBehaviorId(IdentifiableStringValue newBehaviorId, NotificationChain msgs)
    {
        IdentifiableStringValue oldBehaviorId = behaviorId;
        behaviorId = newBehaviorId;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID, oldBehaviorId, newBehaviorId);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBehaviorId(IdentifiableStringValue newBehaviorId)
    {
        if (newBehaviorId != behaviorId)
        {
            NotificationChain msgs = null;
            if (behaviorId != null)
                msgs = ((InternalEObject)behaviorId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID, null, msgs);
            if (newBehaviorId != null)
                msgs = ((InternalEObject)newBehaviorId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID, null, msgs);
            msgs = basicSetBehaviorId(newBehaviorId, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID, newBehaviorId, newBehaviorId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FullyQualifiedClass getHandlerClass()
    {
        return handlerClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newHandlerClass 
     * @param msgs 
     * @return the notification chain
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetHandlerClass(FullyQualifiedClass newHandlerClass, NotificationChain msgs)
    {
        FullyQualifiedClass oldHandlerClass = handlerClass;
        handlerClass = newHandlerClass;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS, oldHandlerClass, newHandlerClass);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHandlerClass(FullyQualifiedClass newHandlerClass)
    {
        if (newHandlerClass != handlerClass)
        {
            NotificationChain msgs = null;
            if (handlerClass != null)
                msgs = ((InternalEObject)handlerClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS, null, msgs);
            if (newHandlerClass != null)
                msgs = ((InternalEObject)newHandlerClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS, null, msgs);
            msgs = basicSetHandlerClass(newHandlerClass, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS, newHandlerClass, newHandlerClass));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<FaceletTaglibTagBehaviorExtension> getBehaviorExtension()
    {
        if (behaviorExtension == null)
        {
            behaviorExtension = new EObjectContainmentEList<FaceletTaglibTagBehaviorExtension>(FaceletTaglibTagBehaviorExtension.class, this, FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_EXTENSION);
        }
        return behaviorExtension;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID:
                return basicSetBehaviorId(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS:
                return basicSetHandlerClass(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_EXTENSION:
                return ((InternalEList<?>)getBehaviorExtension()).basicRemove(otherEnd, msgs);
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
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID:
                return getBehaviorId();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS:
                return getHandlerClass();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_EXTENSION:
                return getBehaviorExtension();
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
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID:
                setBehaviorId((IdentifiableStringValue)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS:
                setHandlerClass((FullyQualifiedClass)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_EXTENSION:
                getBehaviorExtension().clear();
                getBehaviorExtension().addAll((Collection<? extends FaceletTaglibTagBehaviorExtension>)newValue);
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
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID:
                setBehaviorId((IdentifiableStringValue)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS:
                setHandlerClass((FullyQualifiedClass)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_EXTENSION:
                getBehaviorExtension().clear();
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
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_ID:
                return behaviorId != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__HANDLER_CLASS:
                return handlerClass != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG_BEHAVIOR__BEHAVIOR_EXTENSION:
                return behaviorExtension != null && !behaviorExtension.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //FaceletTaglibTagBehaviorImpl
