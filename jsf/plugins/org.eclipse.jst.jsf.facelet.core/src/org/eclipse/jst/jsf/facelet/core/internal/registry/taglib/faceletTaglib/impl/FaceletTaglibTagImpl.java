/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibTagImpl.java,v 1.1 2010/03/18 06:24:38 cbateman Exp $
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
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibCanonicalName;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagBehavior;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagComponent;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagConverter;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagExtension;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagValidator;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FullyQualifiedClass;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.IdentifiableStringValue;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Facelet Taglib Tag</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl#getTagNameElement <em>Tag Name Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl#getHandlerClassElement <em>Handler Class Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl#getConverter <em>Converter</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl#getValidator <em>Validator</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl#getTagExtension <em>Tag Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl#getTagName <em>Tag Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.impl.FaceletTaglibTagImpl#getHandlerClass <em>Handler Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FaceletTaglibTagImpl extends UserVisibleTaglibObjectImpl implements
        FaceletTaglibTag
{
    /**
     * The cached value of the '{@link #getTagNameElement() <em>Tag Name Element</em>}' containment reference.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getTagNameElement()
     * @generated
     * @ordered
     */
    protected FaceletTaglibCanonicalName tagNameElement;

    /**
     * The cached value of the '{@link #getHandlerClassElement() <em>Handler Class Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHandlerClassElement()
     * @generated
     * @ordered
     */
    protected FullyQualifiedClass handlerClassElement;

    /**
     * The cached value of the '{@link #getBehavior() <em>Behavior</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getBehavior()
     * @generated
     * @ordered
     */
    protected FaceletTaglibTagBehavior behavior;

    /**
     * The cached value of the '{@link #getComponent() <em>Component</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getComponent()
     * @generated
     * @ordered
     */
    protected FaceletTaglibTagComponent component;

    /**
     * The cached value of the '{@link #getConverter() <em>Converter</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getConverter()
     * @generated
     * @ordered
     */
    protected FaceletTaglibTagConverter converter;

    /**
     * The cached value of the '{@link #getValidator() <em>Validator</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getValidator()
     * @generated
     * @ordered
     */
    protected FaceletTaglibTagValidator validator;

    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected IdentifiableStringValue source;

    /**
     * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getAttribute()
     * @generated
     * @ordered
     */
    protected EList<FaceletTaglibTagAttribute> attribute;

    /**
     * The cached value of the '{@link #getTagExtension() <em>Tag Extension</em>}' containment reference list.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getTagExtension()
     * @generated
     * @ordered
     */
    protected EList<FaceletTaglibTagExtension> tagExtension;

    /**
     * The default value of the '{@link #getTagName() <em>Tag Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTagName()
     * @generated
     * @ordered
     */
    protected static final String TAG_NAME_EDEFAULT = null;

    /**
     * The default value of the '{@link #getHandlerClass() <em>Handler Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHandlerClass()
     * @generated
     * @ordered
     */
    protected static final String HANDLER_CLASS_EDEFAULT = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FaceletTaglibTagImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglibCanonicalName getTagNameElement()
    {
        return tagNameElement;
    }

    /**
     * <!-- begin-user-doc -->
     * 
     * @param newTagNameElement
     * @param msgs
     * @return the notification chain <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTagNameElement(
            FaceletTaglibCanonicalName newTagNameElement, NotificationChain msgs)
    {
        FaceletTaglibCanonicalName oldTagNameElement = tagNameElement;
        tagNameElement = newTagNameElement;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT, oldTagNameElement, newTagNameElement);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setTagNameElement(FaceletTaglibCanonicalName newTagNameElement)
    {
        if (newTagNameElement != tagNameElement)
        {
            NotificationChain msgs = null;
            if (tagNameElement != null)
                msgs = ((InternalEObject)tagNameElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT, null, msgs);
            if (newTagNameElement != null)
                msgs = ((InternalEObject)newTagNameElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT, null, msgs);
            msgs = basicSetTagNameElement(newTagNameElement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT, newTagNameElement, newTagNameElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FullyQualifiedClass getHandlerClassElement()
    {
        return handlerClassElement;
    }

    /**
     * <!-- begin-user-doc -->
     * @param newHandlerClassElement 
     * @param msgs 
     * @return 
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetHandlerClassElement(FullyQualifiedClass newHandlerClassElement, NotificationChain msgs)
    {
        FullyQualifiedClass oldHandlerClassElement = handlerClassElement;
        handlerClassElement = newHandlerClassElement;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT, oldHandlerClassElement, newHandlerClassElement);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHandlerClassElement(FullyQualifiedClass newHandlerClassElement)
    {
        if (newHandlerClassElement != handlerClassElement)
        {
            NotificationChain msgs = null;
            if (handlerClassElement != null)
                msgs = ((InternalEObject)handlerClassElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT, null, msgs);
            if (newHandlerClassElement != null)
                msgs = ((InternalEObject)newHandlerClassElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT, null, msgs);
            msgs = basicSetHandlerClassElement(newHandlerClassElement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT, newHandlerClassElement, newHandlerClassElement));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public String getTagName()
    {
        return (String) Util.getSimplifiedNestedField(this, FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT, FaceletTaglibPackage.Literals.FACELET_TAGLIB_CANONICAL_NAME__VALUE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    public void setTagName(String newTagName)
    {
        Util.setSimplifiedNestedField(this, FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT, FaceletTaglibPackage.Literals.FACELET_TAGLIB_CANONICAL_NAME__VALUE, newTagName);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getHandlerClass()
    {
        return (String) Util
                .getSimplifiedNestedField(
                        this,
                        FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT,
                        FaceletTaglibPackage.Literals.IDENTIFIABLE_STRING_VALUE__VALUE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public void setHandlerClass(String newHandleClass)
    {
        Util.setSimplifiedNestedField(
                this,
                FaceletTaglibPackage.Literals.FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT,
                FaceletTaglibPackage.Literals.IDENTIFIABLE_STRING_VALUE__VALUE,
                newHandleClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglibTagBehavior getBehavior()
    {
        return behavior;
    }

    /**
     * <!-- begin-user-doc -->
     * 
     * @param newBehavior
     * @param msgs
     * @return the notification chain <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBehavior(
            FaceletTaglibTagBehavior newBehavior, NotificationChain msgs)
    {
        FaceletTaglibTagBehavior oldBehavior = behavior;
        behavior = newBehavior;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__BEHAVIOR, oldBehavior, newBehavior);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setBehavior(FaceletTaglibTagBehavior newBehavior)
    {
        if (newBehavior != behavior)
        {
            NotificationChain msgs = null;
            if (behavior != null)
                msgs = ((InternalEObject)behavior).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__BEHAVIOR, null, msgs);
            if (newBehavior != null)
                msgs = ((InternalEObject)newBehavior).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__BEHAVIOR, null, msgs);
            msgs = basicSetBehavior(newBehavior, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__BEHAVIOR, newBehavior, newBehavior));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglibTagComponent getComponent()
    {
        return component;
    }

    /**
     * <!-- begin-user-doc -->
     * 
     * @param newComponent
     * @param msgs
     * @return the notification chain <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetComponent(
            FaceletTaglibTagComponent newComponent, NotificationChain msgs)
    {
        FaceletTaglibTagComponent oldComponent = component;
        component = newComponent;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__COMPONENT, oldComponent, newComponent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setComponent(FaceletTaglibTagComponent newComponent)
    {
        if (newComponent != component)
        {
            NotificationChain msgs = null;
            if (component != null)
                msgs = ((InternalEObject)component).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__COMPONENT, null, msgs);
            if (newComponent != null)
                msgs = ((InternalEObject)newComponent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__COMPONENT, null, msgs);
            msgs = basicSetComponent(newComponent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__COMPONENT, newComponent, newComponent));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglibTagConverter getConverter()
    {
        return converter;
    }

    /**
     * <!-- begin-user-doc -->
     * 
     * @param newConverter
     * @param msgs
     * @return the notification chain <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConverter(
            FaceletTaglibTagConverter newConverter, NotificationChain msgs)
    {
        FaceletTaglibTagConverter oldConverter = converter;
        converter = newConverter;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__CONVERTER, oldConverter, newConverter);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setConverter(FaceletTaglibTagConverter newConverter)
    {
        if (newConverter != converter)
        {
            NotificationChain msgs = null;
            if (converter != null)
                msgs = ((InternalEObject)converter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__CONVERTER, null, msgs);
            if (newConverter != null)
                msgs = ((InternalEObject)newConverter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__CONVERTER, null, msgs);
            msgs = basicSetConverter(newConverter, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__CONVERTER, newConverter, newConverter));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglibTagValidator getValidator()
    {
        return validator;
    }

    /**
     * <!-- begin-user-doc -->
     * 
     * @param newValidator
     * @param msgs
     * @return the notification chain <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetValidator(
            FaceletTaglibTagValidator newValidator, NotificationChain msgs)
    {
        FaceletTaglibTagValidator oldValidator = validator;
        validator = newValidator;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__VALIDATOR, oldValidator, newValidator);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setValidator(FaceletTaglibTagValidator newValidator)
    {
        if (newValidator != validator)
        {
            NotificationChain msgs = null;
            if (validator != null)
                msgs = ((InternalEObject)validator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__VALIDATOR, null, msgs);
            if (newValidator != null)
                msgs = ((InternalEObject)newValidator).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__VALIDATOR, null, msgs);
            msgs = basicSetValidator(newValidator, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__VALIDATOR, newValidator, newValidator));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public IdentifiableStringValue getSource()
    {
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * 
     * @param newSource
     * @param msgs
     * @return the notification chain <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSource(IdentifiableStringValue newSource,
            NotificationChain msgs)
    {
        IdentifiableStringValue oldSource = source;
        source = newSource;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__SOURCE, oldSource, newSource);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void setSource(IdentifiableStringValue newSource)
    {
        if (newSource != source)
        {
            NotificationChain msgs = null;
            if (source != null)
                msgs = ((InternalEObject)source).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__SOURCE, null, msgs);
            if (newSource != null)
                msgs = ((InternalEObject)newSource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FaceletTaglibPackage.FACELET_TAGLIB_TAG__SOURCE, null, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FaceletTaglibPackage.FACELET_TAGLIB_TAG__SOURCE, newSource, newSource));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<FaceletTaglibTagAttribute> getAttribute()
    {
        if (attribute == null)
        {
            attribute = new EObjectContainmentEList<FaceletTaglibTagAttribute>(FaceletTaglibTagAttribute.class, this, FaceletTaglibPackage.FACELET_TAGLIB_TAG__ATTRIBUTE);
        }
        return attribute;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<FaceletTaglibTagExtension> getTagExtension()
    {
        if (tagExtension == null)
        {
            tagExtension = new EObjectContainmentEList<FaceletTaglibTagExtension>(FaceletTaglibTagExtension.class, this, FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_EXTENSION);
        }
        return tagExtension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd,
            int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT:
                return basicSetTagNameElement(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT:
                return basicSetHandlerClassElement(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__BEHAVIOR:
                return basicSetBehavior(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__COMPONENT:
                return basicSetComponent(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__CONVERTER:
                return basicSetConverter(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__VALIDATOR:
                return basicSetValidator(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__SOURCE:
                return basicSetSource(null, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__ATTRIBUTE:
                return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_EXTENSION:
                return ((InternalEList<?>)getTagExtension()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT:
                return getTagNameElement();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT:
                return getHandlerClassElement();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__BEHAVIOR:
                return getBehavior();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__COMPONENT:
                return getComponent();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__CONVERTER:
                return getConverter();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__VALIDATOR:
                return getValidator();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__SOURCE:
                return getSource();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__ATTRIBUTE:
                return getAttribute();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_EXTENSION:
                return getTagExtension();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME:
                return getTagName();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS:
                return getHandlerClass();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT:
                setTagNameElement((FaceletTaglibCanonicalName)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT:
                setHandlerClassElement((FullyQualifiedClass)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__BEHAVIOR:
                setBehavior((FaceletTaglibTagBehavior)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__COMPONENT:
                setComponent((FaceletTaglibTagComponent)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__CONVERTER:
                setConverter((FaceletTaglibTagConverter)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__VALIDATOR:
                setValidator((FaceletTaglibTagValidator)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__SOURCE:
                setSource((IdentifiableStringValue)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__ATTRIBUTE:
                getAttribute().clear();
                getAttribute().addAll((Collection<? extends FaceletTaglibTagAttribute>)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_EXTENSION:
                getTagExtension().clear();
                getTagExtension().addAll((Collection<? extends FaceletTaglibTagExtension>)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME:
                setTagName((String)newValue);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS:
                setHandlerClass((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT:
                setTagNameElement((FaceletTaglibCanonicalName)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT:
                setHandlerClassElement((FullyQualifiedClass)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__BEHAVIOR:
                setBehavior((FaceletTaglibTagBehavior)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__COMPONENT:
                setComponent((FaceletTaglibTagComponent)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__CONVERTER:
                setConverter((FaceletTaglibTagConverter)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__VALIDATOR:
                setValidator((FaceletTaglibTagValidator)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__SOURCE:
                setSource((IdentifiableStringValue)null);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__ATTRIBUTE:
                getAttribute().clear();
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_EXTENSION:
                getTagExtension().clear();
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME:
                setTagName(TAG_NAME_EDEFAULT);
                return;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS:
                setHandlerClass(HANDLER_CLASS_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID)
    {
        switch (featureID)
        {
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME_ELEMENT:
                return tagNameElement != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS_ELEMENT:
                return handlerClassElement != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__BEHAVIOR:
                return behavior != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__COMPONENT:
                return component != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__CONVERTER:
                return converter != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__VALIDATOR:
                return validator != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__SOURCE:
                return source != null;
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__ATTRIBUTE:
                return attribute != null && !attribute.isEmpty();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_EXTENSION:
                return tagExtension != null && !tagExtension.isEmpty();
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__TAG_NAME:
                return TAG_NAME_EDEFAULT == null ? getTagName() != null : !TAG_NAME_EDEFAULT.equals(getTagName());
            case FaceletTaglibPackage.FACELET_TAGLIB_TAG__HANDLER_CLASS:
                return HANDLER_CLASS_EDEFAULT == null ? getHandlerClass() != null : !HANDLER_CLASS_EDEFAULT.equals(getHandlerClass());
        }
        return super.eIsSet(featureID);
    }

} // FaceletTaglibTagImpl
