/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.ResolveAttributeValue;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag Decorate Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#getMinHeight <em>Min Height</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#getMinWidth <em>Min Width</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#isMultiLevel <em>Multi Level</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#isNeedBorderDecorator <em>Need Border Decorator</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#isNeedTableDecorator <em>Need Table Decorator</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#isNonVisual <em>Non Visual</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#getNonVisualImagePath <em>Non Visual Image Path</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#isResolveChildText <em>Resolve Child Text</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#getResolveAttributeValue <em>Resolve Attribute Value</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#isSetNonVisualChildElements <em>Set Non Visual Child Elements</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl#isWidget <em>Widget</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagDecorateInfoImpl extends EObjectImpl implements TagDecorateInfo {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinHeight() <em>Min Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_HEIGHT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMinHeight() <em>Min Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinHeight()
	 * @generated
	 * @ordered
	 */
	protected int minHeight = MIN_HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinWidth() <em>Min Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_WIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMinWidth() <em>Min Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinWidth()
	 * @generated
	 * @ordered
	 */
	protected int minWidth = MIN_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #isMultiLevel() <em>Multi Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiLevel()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTI_LEVEL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMultiLevel() <em>Multi Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiLevel()
	 * @generated
	 * @ordered
	 */
	protected boolean multiLevel = MULTI_LEVEL_EDEFAULT;

	/**
	 * The default value of the '{@link #isNeedBorderDecorator() <em>Need Border Decorator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeedBorderDecorator()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEED_BORDER_DECORATOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNeedBorderDecorator() <em>Need Border Decorator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeedBorderDecorator()
	 * @generated
	 * @ordered
	 */
	protected boolean needBorderDecorator = NEED_BORDER_DECORATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isNeedTableDecorator() <em>Need Table Decorator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeedTableDecorator()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEED_TABLE_DECORATOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNeedTableDecorator() <em>Need Table Decorator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNeedTableDecorator()
	 * @generated
	 * @ordered
	 */
	protected boolean needTableDecorator = NEED_TABLE_DECORATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isNonVisual() <em>Non Visual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNonVisual()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NON_VISUAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNonVisual() <em>Non Visual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNonVisual()
	 * @generated
	 * @ordered
	 */
	protected boolean nonVisual = NON_VISUAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getNonVisualImagePath() <em>Non Visual Image Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNonVisualImagePath()
	 * @generated
	 * @ordered
	 */
	protected static final String NON_VISUAL_IMAGE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNonVisualImagePath() <em>Non Visual Image Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNonVisualImagePath()
	 * @generated
	 * @ordered
	 */
	protected String nonVisualImagePath = NON_VISUAL_IMAGE_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #isResolveChildText() <em>Resolve Child Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResolveChildText()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RESOLVE_CHILD_TEXT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isResolveChildText() <em>Resolve Child Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResolveChildText()
	 * @generated
	 * @ordered
	 */
	protected boolean resolveChildText = RESOLVE_CHILD_TEXT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResolveAttributeValue() <em>Resolve Attribute Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolveAttributeValue()
	 * @generated
	 * @ordered
	 */
	protected ResolveAttributeValue resolveAttributeValue;

	/**
	 * The default value of the '{@link #isSetNonVisualChildElements() <em>Set Non Visual Child Elements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetNonVisualChildElements()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SET_NON_VISUAL_CHILD_ELEMENTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSetNonVisualChildElements() <em>Set Non Visual Child Elements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetNonVisualChildElements()
	 * @generated
	 * @ordered
	 */
	protected boolean setNonVisualChildElements = SET_NON_VISUAL_CHILD_ELEMENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #isWidget() <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWidget()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WIDGET_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isWidget() <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWidget()
	 * @generated
	 * @ordered
	 */
	protected boolean widget = WIDGET_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TagDecorateInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DTInfoPackage.Literals.TAG_DECORATE_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMinHeight() {
		return minHeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinHeight(int newMinHeight) {
		int oldMinHeight = minHeight;
		minHeight = newMinHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__MIN_HEIGHT, oldMinHeight, minHeight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMinWidth() {
		return minWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinWidth(int newMinWidth) {
		int oldMinWidth = minWidth;
		minWidth = newMinWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__MIN_WIDTH, oldMinWidth, minWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMultiLevel() {
		return multiLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultiLevel(boolean newMultiLevel) {
		boolean oldMultiLevel = multiLevel;
		multiLevel = newMultiLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__MULTI_LEVEL, oldMultiLevel, multiLevel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNeedBorderDecorator() {
		return needBorderDecorator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNeedBorderDecorator(boolean newNeedBorderDecorator) {
		boolean oldNeedBorderDecorator = needBorderDecorator;
		needBorderDecorator = newNeedBorderDecorator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__NEED_BORDER_DECORATOR, oldNeedBorderDecorator, needBorderDecorator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNeedTableDecorator() {
		return needTableDecorator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNeedTableDecorator(boolean newNeedTableDecorator) {
		boolean oldNeedTableDecorator = needTableDecorator;
		needTableDecorator = newNeedTableDecorator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__NEED_TABLE_DECORATOR, oldNeedTableDecorator, needTableDecorator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNonVisual() {
		return nonVisual;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNonVisual(boolean newNonVisual) {
		boolean oldNonVisual = nonVisual;
		nonVisual = newNonVisual;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__NON_VISUAL, oldNonVisual, nonVisual));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNonVisualImagePath() {
		return nonVisualImagePath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNonVisualImagePath(String newNonVisualImagePath) {
		String oldNonVisualImagePath = nonVisualImagePath;
		nonVisualImagePath = newNonVisualImagePath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__NON_VISUAL_IMAGE_PATH, oldNonVisualImagePath, nonVisualImagePath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isResolveChildText() {
		return resolveChildText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolveChildText(boolean newResolveChildText) {
		boolean oldResolveChildText = resolveChildText;
		resolveChildText = newResolveChildText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_CHILD_TEXT, oldResolveChildText, resolveChildText));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResolveAttributeValue getResolveAttributeValue() {
		return resolveAttributeValue;
	}

	/**
	 * <!-- begin-user-doc -->
     * @param newResolveAttributeValue 
     * @param msgs 
     * @return the notification chain 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResolveAttributeValue(ResolveAttributeValue newResolveAttributeValue, NotificationChain msgs) {
		ResolveAttributeValue oldResolveAttributeValue = resolveAttributeValue;
		resolveAttributeValue = newResolveAttributeValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE, oldResolveAttributeValue, newResolveAttributeValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolveAttributeValue(ResolveAttributeValue newResolveAttributeValue) {
		if (newResolveAttributeValue != resolveAttributeValue) {
			NotificationChain msgs = null;
			if (resolveAttributeValue != null)
				msgs = ((InternalEObject)resolveAttributeValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE, null, msgs);
			if (newResolveAttributeValue != null)
				msgs = ((InternalEObject)newResolveAttributeValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE, null, msgs);
			msgs = basicSetResolveAttributeValue(newResolveAttributeValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE, newResolveAttributeValue, newResolveAttributeValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetNonVisualChildElements() {
		return setNonVisualChildElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSetNonVisualChildElements(boolean newSetNonVisualChildElements) {
		boolean oldSetNonVisualChildElements = setNonVisualChildElements;
		setNonVisualChildElements = newSetNonVisualChildElements;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__SET_NON_VISUAL_CHILD_ELEMENTS, oldSetNonVisualChildElements, setNonVisualChildElements));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWidget() {
		return widget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidget(boolean newWidget) {
		boolean oldWidget = widget;
		widget = newWidget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DTInfoPackage.TAG_DECORATE_INFO__WIDGET, oldWidget, widget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE:
				return basicSetResolveAttributeValue(null, msgs);
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
			case DTInfoPackage.TAG_DECORATE_INFO__ID:
				return getId();
			case DTInfoPackage.TAG_DECORATE_INFO__MIN_HEIGHT:
				return new Integer(getMinHeight());
			case DTInfoPackage.TAG_DECORATE_INFO__MIN_WIDTH:
				return new Integer(getMinWidth());
			case DTInfoPackage.TAG_DECORATE_INFO__MULTI_LEVEL:
				return isMultiLevel() ? Boolean.TRUE : Boolean.FALSE;
			case DTInfoPackage.TAG_DECORATE_INFO__NEED_BORDER_DECORATOR:
				return isNeedBorderDecorator() ? Boolean.TRUE : Boolean.FALSE;
			case DTInfoPackage.TAG_DECORATE_INFO__NEED_TABLE_DECORATOR:
				return isNeedTableDecorator() ? Boolean.TRUE : Boolean.FALSE;
			case DTInfoPackage.TAG_DECORATE_INFO__NON_VISUAL:
				return isNonVisual() ? Boolean.TRUE : Boolean.FALSE;
			case DTInfoPackage.TAG_DECORATE_INFO__NON_VISUAL_IMAGE_PATH:
				return getNonVisualImagePath();
			case DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_CHILD_TEXT:
				return isResolveChildText() ? Boolean.TRUE : Boolean.FALSE;
			case DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE:
				return getResolveAttributeValue();
			case DTInfoPackage.TAG_DECORATE_INFO__SET_NON_VISUAL_CHILD_ELEMENTS:
				return isSetNonVisualChildElements() ? Boolean.TRUE : Boolean.FALSE;
			case DTInfoPackage.TAG_DECORATE_INFO__WIDGET:
				return isWidget() ? Boolean.TRUE : Boolean.FALSE;
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
			case DTInfoPackage.TAG_DECORATE_INFO__ID:
				setId((String)newValue);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__MIN_HEIGHT:
				setMinHeight(((Integer)newValue).intValue());
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__MIN_WIDTH:
				setMinWidth(((Integer)newValue).intValue());
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__MULTI_LEVEL:
				setMultiLevel(((Boolean)newValue).booleanValue());
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__NEED_BORDER_DECORATOR:
				setNeedBorderDecorator(((Boolean)newValue).booleanValue());
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__NEED_TABLE_DECORATOR:
				setNeedTableDecorator(((Boolean)newValue).booleanValue());
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__NON_VISUAL:
				setNonVisual(((Boolean)newValue).booleanValue());
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__NON_VISUAL_IMAGE_PATH:
				setNonVisualImagePath((String)newValue);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_CHILD_TEXT:
				setResolveChildText(((Boolean)newValue).booleanValue());
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE:
				setResolveAttributeValue((ResolveAttributeValue)newValue);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__SET_NON_VISUAL_CHILD_ELEMENTS:
				setSetNonVisualChildElements(((Boolean)newValue).booleanValue());
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__WIDGET:
				setWidget(((Boolean)newValue).booleanValue());
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
			case DTInfoPackage.TAG_DECORATE_INFO__ID:
				setId(ID_EDEFAULT);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__MIN_HEIGHT:
				setMinHeight(MIN_HEIGHT_EDEFAULT);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__MIN_WIDTH:
				setMinWidth(MIN_WIDTH_EDEFAULT);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__MULTI_LEVEL:
				setMultiLevel(MULTI_LEVEL_EDEFAULT);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__NEED_BORDER_DECORATOR:
				setNeedBorderDecorator(NEED_BORDER_DECORATOR_EDEFAULT);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__NEED_TABLE_DECORATOR:
				setNeedTableDecorator(NEED_TABLE_DECORATOR_EDEFAULT);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__NON_VISUAL:
				setNonVisual(NON_VISUAL_EDEFAULT);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__NON_VISUAL_IMAGE_PATH:
				setNonVisualImagePath(NON_VISUAL_IMAGE_PATH_EDEFAULT);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_CHILD_TEXT:
				setResolveChildText(RESOLVE_CHILD_TEXT_EDEFAULT);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE:
				setResolveAttributeValue((ResolveAttributeValue)null);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__SET_NON_VISUAL_CHILD_ELEMENTS:
				setSetNonVisualChildElements(SET_NON_VISUAL_CHILD_ELEMENTS_EDEFAULT);
				return;
			case DTInfoPackage.TAG_DECORATE_INFO__WIDGET:
				setWidget(WIDGET_EDEFAULT);
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
			case DTInfoPackage.TAG_DECORATE_INFO__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case DTInfoPackage.TAG_DECORATE_INFO__MIN_HEIGHT:
				return minHeight != MIN_HEIGHT_EDEFAULT;
			case DTInfoPackage.TAG_DECORATE_INFO__MIN_WIDTH:
				return minWidth != MIN_WIDTH_EDEFAULT;
			case DTInfoPackage.TAG_DECORATE_INFO__MULTI_LEVEL:
				return multiLevel != MULTI_LEVEL_EDEFAULT;
			case DTInfoPackage.TAG_DECORATE_INFO__NEED_BORDER_DECORATOR:
				return needBorderDecorator != NEED_BORDER_DECORATOR_EDEFAULT;
			case DTInfoPackage.TAG_DECORATE_INFO__NEED_TABLE_DECORATOR:
				return needTableDecorator != NEED_TABLE_DECORATOR_EDEFAULT;
			case DTInfoPackage.TAG_DECORATE_INFO__NON_VISUAL:
				return nonVisual != NON_VISUAL_EDEFAULT;
			case DTInfoPackage.TAG_DECORATE_INFO__NON_VISUAL_IMAGE_PATH:
				return NON_VISUAL_IMAGE_PATH_EDEFAULT == null ? nonVisualImagePath != null : !NON_VISUAL_IMAGE_PATH_EDEFAULT.equals(nonVisualImagePath);
			case DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_CHILD_TEXT:
				return resolveChildText != RESOLVE_CHILD_TEXT_EDEFAULT;
			case DTInfoPackage.TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE:
				return resolveAttributeValue != null;
			case DTInfoPackage.TAG_DECORATE_INFO__SET_NON_VISUAL_CHILD_ELEMENTS:
				return setNonVisualChildElements != SET_NON_VISUAL_CHILD_ELEMENTS_EDEFAULT;
			case DTInfoPackage.TAG_DECORATE_INFO__WIDGET:
				return widget != WIDGET_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", minHeight: "); //$NON-NLS-1$
		result.append(minHeight);
		result.append(", minWidth: "); //$NON-NLS-1$
		result.append(minWidth);
		result.append(", multiLevel: "); //$NON-NLS-1$
		result.append(multiLevel);
		result.append(", needBorderDecorator: "); //$NON-NLS-1$
		result.append(needBorderDecorator);
		result.append(", needTableDecorator: "); //$NON-NLS-1$
		result.append(needTableDecorator);
		result.append(", nonVisual: "); //$NON-NLS-1$
		result.append(nonVisual);
		result.append(", nonVisualImagePath: "); //$NON-NLS-1$
		result.append(nonVisualImagePath);
		result.append(", resolveChildText: "); //$NON-NLS-1$
		result.append(resolveChildText);
		result.append(", setNonVisualChildElements: "); //$NON-NLS-1$
		result.append(setNonVisualChildElements);
		result.append(", widget: "); //$NON-NLS-1$
		result.append(widget);
		result.append(')');
		return result.toString();
	}

} //TagDecorateInfoImpl
