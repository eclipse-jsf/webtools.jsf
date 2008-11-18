/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.pagedesigner.dtmanager.dtinfo;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfoFactory
 * @model kind="package"
 * @generated
 */
public interface DTInfoPackage extends EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dtinfo"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.eclipse.jsf.pagedesigner/dtinfo.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dti"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DTInfoPackage eINSTANCE = org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoImpl <em>DT Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoImpl
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getDTInfo()
	 * @generated
	 */
	int DT_INFO = 0;

	/**
	 * The feature id for the '<em><b>Tag Convert Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DT_INFO__TAG_CONVERT_INFO = 0;

	/**
	 * The feature id for the '<em><b>Tag Decorate Infos</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DT_INFO__TAG_DECORATE_INFOS = 1;

	/**
	 * The number of structural features of the '<em>DT Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DT_INFO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagConvertInfoImpl <em>Tag Convert Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagConvertInfoImpl
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getTagConvertInfo()
	 * @generated
	 */
	int TAG_CONVERT_INFO = 1;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CONVERT_INFO__OPERATIONS = 0;

	/**
	 * The number of structural features of the '<em>Tag Convert Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CONVERT_INFO_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.OperationImpl
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__ID = 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__PARAMETERS = 1;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__OPERATIONS = 2;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.ParameterImpl
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 3;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl <em>Tag Decorate Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getTagDecorateInfo()
	 * @generated
	 */
	int TAG_DECORATE_INFO = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__ID = 0;

	/**
	 * The feature id for the '<em><b>Min Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__MIN_HEIGHT = 1;

	/**
	 * The feature id for the '<em><b>Min Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__MIN_WIDTH = 2;

	/**
	 * The feature id for the '<em><b>Multi Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__MULTI_LEVEL = 3;

	/**
	 * The feature id for the '<em><b>Need Border Decorator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__NEED_BORDER_DECORATOR = 4;

	/**
	 * The feature id for the '<em><b>Need Table Decorator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__NEED_TABLE_DECORATOR = 5;

	/**
	 * The feature id for the '<em><b>Non Visual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__NON_VISUAL = 6;

	/**
	 * The feature id for the '<em><b>Non Visual Image Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__NON_VISUAL_IMAGE_PATH = 7;

	/**
	 * The feature id for the '<em><b>Resolve Child Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__RESOLVE_CHILD_TEXT = 8;

	/**
	 * The feature id for the '<em><b>Resolve Attribute Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE = 9;

	/**
	 * The feature id for the '<em><b>Set Non Visual Child Elements</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__SET_NON_VISUAL_CHILD_ELEMENTS = 10;

	/**
	 * The feature id for the '<em><b>Widget</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO__WIDGET = 11;

	/**
	 * The number of structural features of the '<em>Tag Decorate Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_DECORATE_INFO_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.ResolveAttributeValueImpl <em>Resolve Attribute Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.ResolveAttributeValueImpl
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getResolveAttributeValue()
	 * @generated
	 */
	int RESOLVE_ATTRIBUTE_VALUE = 5;

	/**
	 * The feature id for the '<em><b>Attribute Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_ATTRIBUTE_VALUE__ATTRIBUTE_NAME = 0;

	/**
	 * The number of structural features of the '<em>Resolve Attribute Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOLVE_ATTRIBUTE_VALUE_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo <em>DT Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DT Info</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo
	 * @generated
	 */
	EClass getDTInfo();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo#getTagConvertInfo <em>Tag Convert Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tag Convert Info</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo#getTagConvertInfo()
	 * @see #getDTInfo()
	 * @generated
	 */
	EReference getDTInfo_TagConvertInfo();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo#getTagDecorateInfos <em>Tag Decorate Infos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tag Decorate Infos</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo#getTagDecorateInfos()
	 * @see #getDTInfo()
	 * @generated
	 */
	EReference getDTInfo_TagDecorateInfos();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagConvertInfo <em>Tag Convert Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Convert Info</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagConvertInfo
	 * @generated
	 */
	EClass getTagConvertInfo();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagConvertInfo#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagConvertInfo#getOperations()
	 * @see #getTagConvertInfo()
	 * @generated
	 */
	EReference getTagConvertInfo_Operations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Operation#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Operation#getId()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_Id();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Operation#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Operation#getParameters()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Parameters();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Operation#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Operation#getOperations()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Operations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Parameter#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.Parameter#getValue()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo <em>Tag Decorate Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Decorate Info</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo
	 * @generated
	 */
	EClass getTagDecorateInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getId()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EAttribute getTagDecorateInfo_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getMinHeight <em>Min Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Height</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getMinHeight()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EAttribute getTagDecorateInfo_MinHeight();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getMinWidth <em>Min Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Width</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getMinWidth()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EAttribute getTagDecorateInfo_MinWidth();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isMultiLevel <em>Multi Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multi Level</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isMultiLevel()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EAttribute getTagDecorateInfo_MultiLevel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNeedBorderDecorator <em>Need Border Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Need Border Decorator</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNeedBorderDecorator()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EAttribute getTagDecorateInfo_NeedBorderDecorator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNeedTableDecorator <em>Need Table Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Need Table Decorator</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNeedTableDecorator()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EAttribute getTagDecorateInfo_NeedTableDecorator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNonVisual <em>Non Visual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Non Visual</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isNonVisual()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EAttribute getTagDecorateInfo_NonVisual();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getNonVisualImagePath <em>Non Visual Image Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Non Visual Image Path</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getNonVisualImagePath()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EAttribute getTagDecorateInfo_NonVisualImagePath();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isResolveChildText <em>Resolve Child Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resolve Child Text</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isResolveChildText()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EAttribute getTagDecorateInfo_ResolveChildText();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getResolveAttributeValue <em>Resolve Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resolve Attribute Value</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#getResolveAttributeValue()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EReference getTagDecorateInfo_ResolveAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isSetNonVisualChildElements <em>Set Non Visual Child Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Set Non Visual Child Elements</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isSetNonVisualChildElements()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EAttribute getTagDecorateInfo_SetNonVisualChildElements();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isWidget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Widget</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo#isWidget()
	 * @see #getTagDecorateInfo()
	 * @generated
	 */
	EAttribute getTagDecorateInfo_Widget();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.ResolveAttributeValue <em>Resolve Attribute Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resolve Attribute Value</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.ResolveAttributeValue
	 * @generated
	 */
	EClass getResolveAttributeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.ResolveAttributeValue#getAttributeName <em>Attribute Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Name</em>'.
	 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.ResolveAttributeValue#getAttributeName()
	 * @see #getResolveAttributeValue()
	 * @generated
	 */
	EAttribute getResolveAttributeValue_AttributeName();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DTInfoFactory getDTInfoFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
    @SuppressWarnings("hiding")
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoImpl <em>DT Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoImpl
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getDTInfo()
		 * @generated
		 */
		EClass DT_INFO = eINSTANCE.getDTInfo();

		/**
		 * The meta object literal for the '<em><b>Tag Convert Info</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DT_INFO__TAG_CONVERT_INFO = eINSTANCE.getDTInfo_TagConvertInfo();

		/**
		 * The meta object literal for the '<em><b>Tag Decorate Infos</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DT_INFO__TAG_DECORATE_INFOS = eINSTANCE.getDTInfo_TagDecorateInfos();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagConvertInfoImpl <em>Tag Convert Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagConvertInfoImpl
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getTagConvertInfo()
		 * @generated
		 */
		EClass TAG_CONVERT_INFO = eINSTANCE.getTagConvertInfo();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_CONVERT_INFO__OPERATIONS = eINSTANCE.getTagConvertInfo_Operations();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.OperationImpl
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__ID = eINSTANCE.getOperation_Id();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__PARAMETERS = eINSTANCE.getOperation_Parameters();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__OPERATIONS = eINSTANCE.getOperation_Operations();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.ParameterImpl
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__VALUE = eINSTANCE.getParameter_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl <em>Tag Decorate Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.TagDecorateInfoImpl
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getTagDecorateInfo()
		 * @generated
		 */
		EClass TAG_DECORATE_INFO = eINSTANCE.getTagDecorateInfo();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_DECORATE_INFO__ID = eINSTANCE.getTagDecorateInfo_Id();

		/**
		 * The meta object literal for the '<em><b>Min Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_DECORATE_INFO__MIN_HEIGHT = eINSTANCE.getTagDecorateInfo_MinHeight();

		/**
		 * The meta object literal for the '<em><b>Min Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_DECORATE_INFO__MIN_WIDTH = eINSTANCE.getTagDecorateInfo_MinWidth();

		/**
		 * The meta object literal for the '<em><b>Multi Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_DECORATE_INFO__MULTI_LEVEL = eINSTANCE.getTagDecorateInfo_MultiLevel();

		/**
		 * The meta object literal for the '<em><b>Need Border Decorator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_DECORATE_INFO__NEED_BORDER_DECORATOR = eINSTANCE.getTagDecorateInfo_NeedBorderDecorator();

		/**
		 * The meta object literal for the '<em><b>Need Table Decorator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_DECORATE_INFO__NEED_TABLE_DECORATOR = eINSTANCE.getTagDecorateInfo_NeedTableDecorator();

		/**
		 * The meta object literal for the '<em><b>Non Visual</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_DECORATE_INFO__NON_VISUAL = eINSTANCE.getTagDecorateInfo_NonVisual();

		/**
		 * The meta object literal for the '<em><b>Non Visual Image Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_DECORATE_INFO__NON_VISUAL_IMAGE_PATH = eINSTANCE.getTagDecorateInfo_NonVisualImagePath();

		/**
		 * The meta object literal for the '<em><b>Resolve Child Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_DECORATE_INFO__RESOLVE_CHILD_TEXT = eINSTANCE.getTagDecorateInfo_ResolveChildText();

		/**
		 * The meta object literal for the '<em><b>Resolve Attribute Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_DECORATE_INFO__RESOLVE_ATTRIBUTE_VALUE = eINSTANCE.getTagDecorateInfo_ResolveAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Set Non Visual Child Elements</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_DECORATE_INFO__SET_NON_VISUAL_CHILD_ELEMENTS = eINSTANCE.getTagDecorateInfo_SetNonVisualChildElements();

		/**
		 * The meta object literal for the '<em><b>Widget</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_DECORATE_INFO__WIDGET = eINSTANCE.getTagDecorateInfo_Widget();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.ResolveAttributeValueImpl <em>Resolve Attribute Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.ResolveAttributeValueImpl
		 * @see org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.impl.DTInfoPackageImpl#getResolveAttributeValue()
		 * @generated
		 */
		EClass RESOLVE_ATTRIBUTE_VALUE = eINSTANCE.getResolveAttributeValue();

		/**
		 * The meta object literal for the '<em><b>Attribute Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOLVE_ATTRIBUTE_VALUE__ATTRIBUTE_NAME = eINSTANCE.getResolveAttributeValue_AttributeName();

	}

} //DTInfoPackage
