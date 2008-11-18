/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.pagedesigner.dtmanager.dtinfo;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Decorate Info</b></em>'.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getMinHeight <em>Min Height</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getMinWidth <em>Min Width</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isMultiLevel <em>Multi Level</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNeedBorderDecorator <em>Need Border Decorator</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNeedTableDecorator <em>Need Table Decorator</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNonVisual <em>Non Visual</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getNonVisualImagePath <em>Non Visual Image Path</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isResolveChildText <em>Resolve Child Text</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getResolveAttributeValue <em>Resolve Attribute Value</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isSetNonVisualChildElements <em>Set Non Visual Child Elements</em>}</li>
 *   <li>{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isWidget <em>Widget</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo()
 * @model
 * @generated
 */
public interface TagDecorateInfo extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Min Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Height</em>' attribute.
	 * @see #setMinHeight(int)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_MinHeight()
	 * @model
	 * @generated
	 */
	int getMinHeight();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getMinHeight <em>Min Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Height</em>' attribute.
	 * @see #getMinHeight()
	 * @generated
	 */
	void setMinHeight(int value);

	/**
	 * Returns the value of the '<em><b>Min Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Width</em>' attribute.
	 * @see #setMinWidth(int)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_MinWidth()
	 * @model
	 * @generated
	 */
	int getMinWidth();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getMinWidth <em>Min Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Width</em>' attribute.
	 * @see #getMinWidth()
	 * @generated
	 */
	void setMinWidth(int value);

	/**
	 * Returns the value of the '<em><b>Multi Level</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multi Level</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multi Level</em>' attribute.
	 * @see #setMultiLevel(boolean)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_MultiLevel()
	 * @model default="false"
	 * @generated
	 */
	boolean isMultiLevel();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isMultiLevel <em>Multi Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multi Level</em>' attribute.
	 * @see #isMultiLevel()
	 * @generated
	 */
	void setMultiLevel(boolean value);

	/**
	 * Returns the value of the '<em><b>Need Border Decorator</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Need Border Decorator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Need Border Decorator</em>' attribute.
	 * @see #setNeedBorderDecorator(boolean)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_NeedBorderDecorator()
	 * @model default="false"
	 * @generated
	 */
	boolean isNeedBorderDecorator();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNeedBorderDecorator <em>Need Border Decorator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Need Border Decorator</em>' attribute.
	 * @see #isNeedBorderDecorator()
	 * @generated
	 */
	void setNeedBorderDecorator(boolean value);

	/**
	 * Returns the value of the '<em><b>Need Table Decorator</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Need Table Decorator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Need Table Decorator</em>' attribute.
	 * @see #setNeedTableDecorator(boolean)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_NeedTableDecorator()
	 * @model default="false"
	 * @generated
	 */
	boolean isNeedTableDecorator();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNeedTableDecorator <em>Need Table Decorator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Need Table Decorator</em>' attribute.
	 * @see #isNeedTableDecorator()
	 * @generated
	 */
	void setNeedTableDecorator(boolean value);

	/**
	 * Returns the value of the '<em><b>Non Visual</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Non Visual</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Non Visual</em>' attribute.
	 * @see #setNonVisual(boolean)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_NonVisual()
	 * @model default="false"
	 * @generated
	 */
	boolean isNonVisual();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNonVisual <em>Non Visual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Non Visual</em>' attribute.
	 * @see #isNonVisual()
	 * @generated
	 */
	void setNonVisual(boolean value);

	/**
	 * Returns the value of the '<em><b>Non Visual Image Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Non Visual Image Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Non Visual Image Path</em>' attribute.
	 * @see #setNonVisualImagePath(String)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_NonVisualImagePath()
	 * @model
	 * @generated
	 */
	String getNonVisualImagePath();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getNonVisualImagePath <em>Non Visual Image Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Non Visual Image Path</em>' attribute.
	 * @see #getNonVisualImagePath()
	 * @generated
	 */
	void setNonVisualImagePath(String value);

	/**
	 * Returns the value of the '<em><b>Resolve Child Text</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolve Child Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolve Child Text</em>' attribute.
	 * @see #setResolveChildText(boolean)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_ResolveChildText()
	 * @model default="false"
	 * @generated
	 */
	boolean isResolveChildText();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isResolveChildText <em>Resolve Child Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolve Child Text</em>' attribute.
	 * @see #isResolveChildText()
	 * @generated
	 */
	void setResolveChildText(boolean value);

	/**
	 * Returns the value of the '<em><b>Resolve Attribute Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolve Attribute Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolve Attribute Value</em>' containment reference.
	 * @see #setResolveAttributeValue(ResolveAttributeValue)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_ResolveAttributeValue()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='resolve-attribute-value'"
	 * @generated
	 */
	ResolveAttributeValue getResolveAttributeValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getResolveAttributeValue <em>Resolve Attribute Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolve Attribute Value</em>' containment reference.
	 * @see #getResolveAttributeValue()
	 * @generated
	 */
	void setResolveAttributeValue(ResolveAttributeValue value);

	/**
	 * Returns the value of the '<em><b>Set Non Visual Child Elements</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Set Non Visual Child Elements</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Set Non Visual Child Elements</em>' attribute.
	 * @see #setSetNonVisualChildElements(boolean)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_SetNonVisualChildElements()
	 * @model default="false"
	 * @generated
	 */
	boolean isSetNonVisualChildElements();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isSetNonVisualChildElements <em>Set Non Visual Child Elements</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Set Non Visual Child Elements</em>' attribute.
	 * @see #isSetNonVisualChildElements()
	 * @generated
	 */
	void setSetNonVisualChildElements(boolean value);

	/**
	 * Returns the value of the '<em><b>Widget</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Widget</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Widget</em>' attribute.
	 * @see #setWidget(boolean)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoPackage#getTagDecorateInfo_Widget()
	 * @model default="false"
	 * @generated
	 */
	boolean isWidget();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isWidget <em>Widget</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Widget</em>' attribute.
	 * @see #isWidget()
	 * @generated
	 */
	void setWidget(boolean value);

} // TagDecorateInfo
