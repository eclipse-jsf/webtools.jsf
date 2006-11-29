/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.context.symbol;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
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
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.context.symbol.SymbolFactory
 * @model kind="package"
 * @generated
 */
public interface SymbolPackage extends EPackage {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String copyright = "Copyright 2006 Oracle";

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "symbol";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http:///org/eclipse/jst/jsf/context/symbol.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "org.eclipse.jst.jsf.context.symbol";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    SymbolPackage eINSTANCE = org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol <em>ISymbol</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getISymbol()
	 * @generated
	 */
    int ISYMBOL = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ISYMBOL__NAME = 0;

	/**
	 * The number of structural features of the '<em>ISymbol</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ISYMBOL_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol <em>IObject Symbol</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIObjectSymbol()
	 * @generated
	 */
    int IOBJECT_SYMBOL = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IOBJECT_SYMBOL__NAME = ISYMBOL__NAME;

	/**
	 * The feature id for the '<em><b>Type Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IOBJECT_SYMBOL__TYPE_DESCRIPTOR = ISYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Readable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IOBJECT_SYMBOL__READABLE = ISYMBOL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Writable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IOBJECT_SYMBOL__WRITABLE = ISYMBOL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>IObject Symbol</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IOBJECT_SYMBOL_FEATURE_COUNT = ISYMBOL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl <em>IInstance Symbol</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIInstanceSymbol()
	 * @generated
	 */
    int IINSTANCE_SYMBOL = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IINSTANCE_SYMBOL__NAME = IOBJECT_SYMBOL__NAME;

	/**
	 * The feature id for the '<em><b>Type Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IINSTANCE_SYMBOL__TYPE_DESCRIPTOR = IOBJECT_SYMBOL__TYPE_DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Readable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IINSTANCE_SYMBOL__READABLE = IOBJECT_SYMBOL__READABLE;

	/**
	 * The feature id for the '<em><b>Writable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IINSTANCE_SYMBOL__WRITABLE = IOBJECT_SYMBOL__WRITABLE;

	/**
	 * The feature id for the '<em><b>Type Resolved</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IINSTANCE_SYMBOL__TYPE_RESOLVED = IOBJECT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Runtime Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IINSTANCE_SYMBOL__RUNTIME_SOURCE = IOBJECT_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>IInstance Symbol</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IINSTANCE_SYMBOL_FEATURE_COUNT = IOBJECT_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanInstanceSymbolImpl <em>IBean Instance Symbol</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanInstanceSymbolImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBeanInstanceSymbol()
	 * @generated
	 */
    int IBEAN_INSTANCE_SYMBOL = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_INSTANCE_SYMBOL__NAME = IINSTANCE_SYMBOL__NAME;

	/**
	 * The feature id for the '<em><b>Type Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_INSTANCE_SYMBOL__TYPE_DESCRIPTOR = IINSTANCE_SYMBOL__TYPE_DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Readable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_INSTANCE_SYMBOL__READABLE = IINSTANCE_SYMBOL__READABLE;

	/**
	 * The feature id for the '<em><b>Writable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_INSTANCE_SYMBOL__WRITABLE = IINSTANCE_SYMBOL__WRITABLE;

	/**
	 * The feature id for the '<em><b>Type Resolved</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_INSTANCE_SYMBOL__TYPE_RESOLVED = IINSTANCE_SYMBOL__TYPE_RESOLVED;

	/**
	 * The feature id for the '<em><b>Runtime Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_INSTANCE_SYMBOL__RUNTIME_SOURCE = IINSTANCE_SYMBOL__RUNTIME_SOURCE;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_INSTANCE_SYMBOL__PROPERTIES = IINSTANCE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_INSTANCE_SYMBOL__METHODS = IINSTANCE_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>IBean Instance Symbol</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_INSTANCE_SYMBOL_FEATURE_COUNT = IINSTANCE_SYMBOL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl <em>IProperty Symbol</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIPropertySymbol()
	 * @generated
	 */
    int IPROPERTY_SYMBOL = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IPROPERTY_SYMBOL__NAME = IOBJECT_SYMBOL__NAME;

	/**
	 * The feature id for the '<em><b>Type Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IPROPERTY_SYMBOL__TYPE_DESCRIPTOR = IOBJECT_SYMBOL__TYPE_DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Readable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IPROPERTY_SYMBOL__READABLE = IOBJECT_SYMBOL__READABLE;

	/**
	 * The feature id for the '<em><b>Writable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IPROPERTY_SYMBOL__WRITABLE = IOBJECT_SYMBOL__WRITABLE;

	/**
	 * The feature id for the '<em><b>Intermediate</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IPROPERTY_SYMBOL__INTERMEDIATE = IOBJECT_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>IProperty Symbol</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IPROPERTY_SYMBOL_FEATURE_COUNT = IOBJECT_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanPropertySymbolImpl <em>IBean Property Symbol</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanPropertySymbolImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBeanPropertySymbol()
	 * @generated
	 */
    int IBEAN_PROPERTY_SYMBOL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_PROPERTY_SYMBOL__NAME = IPROPERTY_SYMBOL__NAME;

	/**
	 * The feature id for the '<em><b>Type Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_PROPERTY_SYMBOL__TYPE_DESCRIPTOR = IPROPERTY_SYMBOL__TYPE_DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Readable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_PROPERTY_SYMBOL__READABLE = IPROPERTY_SYMBOL__READABLE;

	/**
	 * The feature id for the '<em><b>Writable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_PROPERTY_SYMBOL__WRITABLE = IPROPERTY_SYMBOL__WRITABLE;

	/**
	 * The feature id for the '<em><b>Intermediate</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_PROPERTY_SYMBOL__INTERMEDIATE = IPROPERTY_SYMBOL__INTERMEDIATE;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_PROPERTY_SYMBOL__OWNER = IPROPERTY_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>IBean Property Symbol</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_PROPERTY_SYMBOL_FEATURE_COUNT = IPROPERTY_SYMBOL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaSymbolImpl <em>IJava Symbol</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaSymbolImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIJavaSymbol()
	 * @generated
	 */
    int IJAVA_SYMBOL = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_SYMBOL__NAME = ISYMBOL__NAME;

	/**
	 * The feature id for the '<em><b>Java Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_SYMBOL__JAVA_ELEMENT = ISYMBOL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>IJava Symbol</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_SYMBOL_FEATURE_COUNT = ISYMBOL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl <em>IType Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getITypeDescriptor()
	 * @generated
	 */
    int ITYPE_DESCRIPTOR = 5;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ITYPE_DESCRIPTOR__PROPERTIES = 0;

	/**
	 * The feature id for the '<em><b>Type Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ITYPE_DESCRIPTOR__TYPE_SIGNATURE = 1;

	/**
	 * The feature id for the '<em><b>Super Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES = 2;

	/**
	 * The feature id for the '<em><b>Interface Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES = 3;

	/**
	 * The feature id for the '<em><b>Type Signature Delegate</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE = 4;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ITYPE_DESCRIPTOR__METHODS = 5;

	/**
	 * The number of structural features of the '<em>IType Descriptor</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ITYPE_DESCRIPTOR_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IDescribedInDetail <em>IDescribed In Detail</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IDescribedInDetail
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIDescribedInDetail()
	 * @generated
	 */
    int IDESCRIBED_IN_DETAIL = 6;

	/**
	 * The number of structural features of the '<em>IDescribed In Detail</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IDESCRIBED_IN_DETAIL_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaTypeDescriptor2Impl <em>IJava Type Descriptor2</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaTypeDescriptor2Impl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIJavaTypeDescriptor2()
	 * @generated
	 */
    int IJAVA_TYPE_DESCRIPTOR2 = 7;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_TYPE_DESCRIPTOR2__PROPERTIES = ITYPE_DESCRIPTOR__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Type Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_TYPE_DESCRIPTOR2__TYPE_SIGNATURE = ITYPE_DESCRIPTOR__TYPE_SIGNATURE;

	/**
	 * The feature id for the '<em><b>Super Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_TYPE_DESCRIPTOR2__SUPER_TYPE_SIGNATURES = ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES;

	/**
	 * The feature id for the '<em><b>Interface Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_TYPE_DESCRIPTOR2__INTERFACE_TYPE_SIGNATURES = ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES;

	/**
	 * The feature id for the '<em><b>Type Signature Delegate</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_TYPE_DESCRIPTOR2__TYPE_SIGNATURE_DELEGATE = ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_TYPE_DESCRIPTOR2__METHODS = ITYPE_DESCRIPTOR__METHODS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_TYPE_DESCRIPTOR2__TYPE = ITYPE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bean Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_TYPE_DESCRIPTOR2__BEAN_PROPERTIES = ITYPE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bean Methods</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_TYPE_DESCRIPTOR2__BEAN_METHODS = ITYPE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>IJava Type Descriptor2</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IJAVA_TYPE_DESCRIPTOR2_FEATURE_COUNT = ITYPE_DESCRIPTOR_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanMethodSymbolImpl <em>IBean Method Symbol</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanMethodSymbolImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBeanMethodSymbol()
	 * @generated
	 */
    int IBEAN_METHOD_SYMBOL = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_METHOD_SYMBOL__NAME = IDESCRIBED_IN_DETAIL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_METHOD_SYMBOL__SIGNATURE = IDESCRIBED_IN_DETAIL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_METHOD_SYMBOL__OWNER = IDESCRIBED_IN_DETAIL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>IBean Method Symbol</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBEAN_METHOD_SYMBOL_FEATURE_COUNT = IDESCRIBED_IN_DETAIL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IComponentSymbolImpl <em>IComponent Symbol</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IComponentSymbolImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIComponentSymbol()
	 * @generated
	 */
    int ICOMPONENT_SYMBOL = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICOMPONENT_SYMBOL__NAME = IINSTANCE_SYMBOL__NAME;

	/**
	 * The feature id for the '<em><b>Type Descriptor</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICOMPONENT_SYMBOL__TYPE_DESCRIPTOR = IINSTANCE_SYMBOL__TYPE_DESCRIPTOR;

	/**
	 * The feature id for the '<em><b>Readable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICOMPONENT_SYMBOL__READABLE = IINSTANCE_SYMBOL__READABLE;

	/**
	 * The feature id for the '<em><b>Writable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICOMPONENT_SYMBOL__WRITABLE = IINSTANCE_SYMBOL__WRITABLE;

	/**
	 * The feature id for the '<em><b>Type Resolved</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICOMPONENT_SYMBOL__TYPE_RESOLVED = IINSTANCE_SYMBOL__TYPE_RESOLVED;

	/**
	 * The feature id for the '<em><b>Runtime Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICOMPONENT_SYMBOL__RUNTIME_SOURCE = IINSTANCE_SYMBOL__RUNTIME_SOURCE;

	/**
	 * The number of structural features of the '<em>IComponent Symbol</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ICOMPONENT_SYMBOL_FEATURE_COUNT = IINSTANCE_SYMBOL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IMapTypeDescriptorImpl <em>IMap Type Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IMapTypeDescriptorImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIMapTypeDescriptor()
	 * @generated
	 */
    int IMAP_TYPE_DESCRIPTOR = 11;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMAP_TYPE_DESCRIPTOR__PROPERTIES = ITYPE_DESCRIPTOR__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Type Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMAP_TYPE_DESCRIPTOR__TYPE_SIGNATURE = ITYPE_DESCRIPTOR__TYPE_SIGNATURE;

	/**
	 * The feature id for the '<em><b>Super Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMAP_TYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES = ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES;

	/**
	 * The feature id for the '<em><b>Interface Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMAP_TYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES = ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES;

	/**
	 * The feature id for the '<em><b>Type Signature Delegate</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMAP_TYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE = ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMAP_TYPE_DESCRIPTOR__METHODS = ITYPE_DESCRIPTOR__METHODS;

	/**
	 * The feature id for the '<em><b>Map Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMAP_TYPE_DESCRIPTOR__MAP_SOURCE = ITYPE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Immutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMAP_TYPE_DESCRIPTOR__IMMUTABLE = ITYPE_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>IMap Type Descriptor</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMAP_TYPE_DESCRIPTOR_FEATURE_COUNT = ITYPE_DESCRIPTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IMethodSymbolImpl <em>IMethod Symbol</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IMethodSymbolImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIMethodSymbol()
	 * @generated
	 */
    int IMETHOD_SYMBOL = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMETHOD_SYMBOL__NAME = ISYMBOL__NAME;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMETHOD_SYMBOL__SIGNATURE = ISYMBOL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>IMethod Symbol</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IMETHOD_SYMBOL_FEATURE_COUNT = ISYMBOL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedTypeDescriptor <em>IBounded Type Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedTypeDescriptor
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBoundedTypeDescriptor()
	 * @generated
	 */
    int IBOUNDED_TYPE_DESCRIPTOR = 14;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_TYPE_DESCRIPTOR__PROPERTIES = ITYPE_DESCRIPTOR__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Type Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_TYPE_DESCRIPTOR__TYPE_SIGNATURE = ITYPE_DESCRIPTOR__TYPE_SIGNATURE;

	/**
	 * The feature id for the '<em><b>Super Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_TYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES = ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES;

	/**
	 * The feature id for the '<em><b>Interface Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_TYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES = ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES;

	/**
	 * The feature id for the '<em><b>Type Signature Delegate</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_TYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE = ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_TYPE_DESCRIPTOR__METHODS = ITYPE_DESCRIPTOR__METHODS;

	/**
	 * The number of structural features of the '<em>IBounded Type Descriptor</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_TYPE_DESCRIPTOR_FEATURE_COUNT = ITYPE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedMapTypeDescriptorImpl <em>IBounded Map Type Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedMapTypeDescriptorImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBoundedMapTypeDescriptor()
	 * @generated
	 */
    int IBOUNDED_MAP_TYPE_DESCRIPTOR = 15;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_MAP_TYPE_DESCRIPTOR__PROPERTIES = IMAP_TYPE_DESCRIPTOR__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Type Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_MAP_TYPE_DESCRIPTOR__TYPE_SIGNATURE = IMAP_TYPE_DESCRIPTOR__TYPE_SIGNATURE;

	/**
	 * The feature id for the '<em><b>Super Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_MAP_TYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES = IMAP_TYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES;

	/**
	 * The feature id for the '<em><b>Interface Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_MAP_TYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES = IMAP_TYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES;

	/**
	 * The feature id for the '<em><b>Type Signature Delegate</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_MAP_TYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE = IMAP_TYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_MAP_TYPE_DESCRIPTOR__METHODS = IMAP_TYPE_DESCRIPTOR__METHODS;

	/**
	 * The feature id for the '<em><b>Map Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_MAP_TYPE_DESCRIPTOR__MAP_SOURCE = IMAP_TYPE_DESCRIPTOR__MAP_SOURCE;

	/**
	 * The feature id for the '<em><b>Immutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_MAP_TYPE_DESCRIPTOR__IMMUTABLE = IMAP_TYPE_DESCRIPTOR__IMMUTABLE;

	/**
	 * The number of structural features of the '<em>IBounded Map Type Descriptor</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_MAP_TYPE_DESCRIPTOR_FEATURE_COUNT = IMAP_TYPE_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedJavaTypeDescriptorImpl <em>IBounded Java Type Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedJavaTypeDescriptorImpl
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBoundedJavaTypeDescriptor()
	 * @generated
	 */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR = 16;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR__PROPERTIES = IJAVA_TYPE_DESCRIPTOR2__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Type Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR__TYPE_SIGNATURE = IJAVA_TYPE_DESCRIPTOR2__TYPE_SIGNATURE;

	/**
	 * The feature id for the '<em><b>Super Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES = IJAVA_TYPE_DESCRIPTOR2__SUPER_TYPE_SIGNATURES;

	/**
	 * The feature id for the '<em><b>Interface Type Signatures</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES = IJAVA_TYPE_DESCRIPTOR2__INTERFACE_TYPE_SIGNATURES;

	/**
	 * The feature id for the '<em><b>Type Signature Delegate</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE = IJAVA_TYPE_DESCRIPTOR2__TYPE_SIGNATURE_DELEGATE;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR__METHODS = IJAVA_TYPE_DESCRIPTOR2__METHODS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR__TYPE = IJAVA_TYPE_DESCRIPTOR2__TYPE;

	/**
	 * The feature id for the '<em><b>Bean Properties</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR__BEAN_PROPERTIES = IJAVA_TYPE_DESCRIPTOR2__BEAN_PROPERTIES;

	/**
	 * The feature id for the '<em><b>Bean Methods</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR__BEAN_METHODS = IJAVA_TYPE_DESCRIPTOR2__BEAN_METHODS;

	/**
	 * The number of structural features of the '<em>IBounded Java Type Descriptor</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR_FEATURE_COUNT = IJAVA_TYPE_DESCRIPTOR2_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ERuntimeSource <em>ERuntime Source</em>}' enum.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ERuntimeSource
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getERuntimeSource()
	 * @generated
	 */
    int ERUNTIME_SOURCE = 17;

	/**
	 * The meta object id for the '<em>IType</em>' data type.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jdt.core.IType
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIType()
	 * @generated
	 */
    int ITYPE = 18;

	/**
	 * The meta object id for the '<em>IJava Element</em>' data type.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.jdt.core.IJavaElement
	 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIJavaElement()
	 * @generated
	 */
    int IJAVA_ELEMENT = 19;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol <em>IBean Instance Symbol</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IBean Instance Symbol</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol
	 * @generated
	 */
    EClass getIBeanInstanceSymbol();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Properties</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol#getProperties()
	 * @see #getIBeanInstanceSymbol()
	 * @generated
	 */
    EReference getIBeanInstanceSymbol_Properties();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Methods</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol#getMethods()
	 * @see #getIBeanInstanceSymbol()
	 * @generated
	 */
    EReference getIBeanInstanceSymbol_Methods();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol <em>IBean Property Symbol</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IBean Property Symbol</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol
	 * @generated
	 */
    EClass getIBeanPropertySymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Owner</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol#getOwner()
	 * @see #getIBeanPropertySymbol()
	 * @generated
	 */
    EReference getIBeanPropertySymbol_Owner();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol <em>IInstance Symbol</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IInstance Symbol</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol
	 * @generated
	 */
    EClass getIInstanceSymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol#isTypeResolved <em>Type Resolved</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Resolved</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol#isTypeResolved()
	 * @see #getIInstanceSymbol()
	 * @generated
	 */
    EAttribute getIInstanceSymbol_TypeResolved();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol#getRuntimeSource <em>Runtime Source</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Runtime Source</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol#getRuntimeSource()
	 * @see #getIInstanceSymbol()
	 * @generated
	 */
    EAttribute getIInstanceSymbol_RuntimeSource();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaSymbol <em>IJava Symbol</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IJava Symbol</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaSymbol
	 * @generated
	 */
    EClass getIJavaSymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaSymbol#getJavaElement <em>Java Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Java Element</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaSymbol#getJavaElement()
	 * @see #getIJavaSymbol()
	 * @generated
	 */
    EAttribute getIJavaSymbol_JavaElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol <em>ISymbol</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ISymbol</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol
	 * @generated
	 */
    EClass getISymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol#getName()
	 * @see #getISymbol()
	 * @generated
	 */
    EAttribute getISymbol_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor <em>IType Descriptor</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IType Descriptor</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor
	 * @generated
	 */
    EClass getITypeDescriptor();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Properties</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getProperties()
	 * @see #getITypeDescriptor()
	 * @generated
	 */
    EReference getITypeDescriptor_Properties();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getTypeSignature <em>Type Signature</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Signature</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getTypeSignature()
	 * @see #getITypeDescriptor()
	 * @generated
	 */
    EAttribute getITypeDescriptor_TypeSignature();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getSuperTypeSignatures <em>Super Type Signatures</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Super Type Signatures</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getSuperTypeSignatures()
	 * @see #getITypeDescriptor()
	 * @generated
	 */
    EAttribute getITypeDescriptor_SuperTypeSignatures();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getInterfaceTypeSignatures <em>Interface Type Signatures</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Interface Type Signatures</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getInterfaceTypeSignatures()
	 * @see #getITypeDescriptor()
	 * @generated
	 */
    EAttribute getITypeDescriptor_InterfaceTypeSignatures();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getTypeSignatureDelegate <em>Type Signature Delegate</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Signature Delegate</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getTypeSignatureDelegate()
	 * @see #getITypeDescriptor()
	 * @generated
	 */
    EAttribute getITypeDescriptor_TypeSignatureDelegate();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Methods</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor#getMethods()
	 * @see #getITypeDescriptor()
	 * @generated
	 */
    EReference getITypeDescriptor_Methods();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IDescribedInDetail <em>IDescribed In Detail</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IDescribed In Detail</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IDescribedInDetail
	 * @generated
	 */
    EClass getIDescribedInDetail();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2 <em>IJava Type Descriptor2</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IJava Type Descriptor2</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2
	 * @generated
	 */
    EClass getIJavaTypeDescriptor2();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2#getType()
	 * @see #getIJavaTypeDescriptor2()
	 * @generated
	 */
    EAttribute getIJavaTypeDescriptor2_Type();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2#getBeanProperties <em>Bean Properties</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Bean Properties</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2#getBeanProperties()
	 * @see #getIJavaTypeDescriptor2()
	 * @generated
	 */
    EReference getIJavaTypeDescriptor2_BeanProperties();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2#getBeanMethods <em>Bean Methods</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Bean Methods</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2#getBeanMethods()
	 * @see #getIJavaTypeDescriptor2()
	 * @generated
	 */
    EReference getIJavaTypeDescriptor2_BeanMethods();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanMethodSymbol <em>IBean Method Symbol</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IBean Method Symbol</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanMethodSymbol
	 * @generated
	 */
    EClass getIBeanMethodSymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanMethodSymbol#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Owner</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanMethodSymbol#getOwner()
	 * @see #getIBeanMethodSymbol()
	 * @generated
	 */
    EReference getIBeanMethodSymbol_Owner();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IComponentSymbol <em>IComponent Symbol</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IComponent Symbol</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IComponentSymbol
	 * @generated
	 */
    EClass getIComponentSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol <em>IProperty Symbol</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IProperty Symbol</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol
	 * @generated
	 */
    EClass getIPropertySymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol#isIntermediate <em>Intermediate</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Intermediate</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol#isIntermediate()
	 * @see #getIPropertySymbol()
	 * @generated
	 */
    EAttribute getIPropertySymbol_Intermediate();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor <em>IMap Type Descriptor</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IMap Type Descriptor</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor
	 * @generated
	 */
    EClass getIMapTypeDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor#getMapSource <em>Map Source</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Map Source</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor#getMapSource()
	 * @see #getIMapTypeDescriptor()
	 * @generated
	 */
    EAttribute getIMapTypeDescriptor_MapSource();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor#isImmutable <em>Immutable</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Immutable</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor#isImmutable()
	 * @see #getIMapTypeDescriptor()
	 * @generated
	 */
    EAttribute getIMapTypeDescriptor_Immutable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol <em>IMethod Symbol</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IMethod Symbol</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol
	 * @generated
	 */
    EClass getIMethodSymbol();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signature</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol#getSignature()
	 * @see #getIMethodSymbol()
	 * @generated
	 */
    EAttribute getIMethodSymbol_Signature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol <em>IObject Symbol</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IObject Symbol</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol
	 * @generated
	 */
    EClass getIObjectSymbol();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#getTypeDescriptor <em>Type Descriptor</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type Descriptor</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#getTypeDescriptor()
	 * @see #getIObjectSymbol()
	 * @generated
	 */
    EReference getIObjectSymbol_TypeDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#isReadable <em>Readable</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Readable</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#isReadable()
	 * @see #getIObjectSymbol()
	 * @generated
	 */
    EAttribute getIObjectSymbol_Readable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#isWritable <em>Writable</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Writable</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol#isWritable()
	 * @see #getIObjectSymbol()
	 * @generated
	 */
    EAttribute getIObjectSymbol_Writable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedTypeDescriptor <em>IBounded Type Descriptor</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IBounded Type Descriptor</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedTypeDescriptor
	 * @generated
	 */
    EClass getIBoundedTypeDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedMapTypeDescriptor <em>IBounded Map Type Descriptor</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IBounded Map Type Descriptor</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedMapTypeDescriptor
	 * @generated
	 */
    EClass getIBoundedMapTypeDescriptor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedJavaTypeDescriptor <em>IBounded Java Type Descriptor</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IBounded Java Type Descriptor</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedJavaTypeDescriptor
	 * @generated
	 */
    EClass getIBoundedJavaTypeDescriptor();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ERuntimeSource <em>ERuntime Source</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>ERuntime Source</em>'.
	 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ERuntimeSource
	 * @generated
	 */
    EEnum getERuntimeSource();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jdt.core.IType <em>IType</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IType</em>'.
	 * @see org.eclipse.jdt.core.IType
	 * @model instanceClass="org.eclipse.jdt.core.IType"
	 * @generated
	 */
    EDataType getIType();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.jdt.core.IJavaElement <em>IJava Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IJava Element</em>'.
	 * @see org.eclipse.jdt.core.IJavaElement
	 * @model instanceClass="org.eclipse.jdt.core.IJavaElement"
	 * @generated
	 */
    EDataType getIJavaElement();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    SymbolFactory getSymbolFactory();

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
    interface Literals  {
		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanInstanceSymbolImpl <em>IBean Instance Symbol</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanInstanceSymbolImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBeanInstanceSymbol()
		 * @generated
		 */
        EClass IBEAN_INSTANCE_SYMBOL = eINSTANCE.getIBeanInstanceSymbol();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference IBEAN_INSTANCE_SYMBOL__PROPERTIES = eINSTANCE.getIBeanInstanceSymbol_Properties();

		/**
		 * The meta object literal for the '<em><b>Methods</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference IBEAN_INSTANCE_SYMBOL__METHODS = eINSTANCE.getIBeanInstanceSymbol_Methods();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanPropertySymbolImpl <em>IBean Property Symbol</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanPropertySymbolImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBeanPropertySymbol()
		 * @generated
		 */
        EClass IBEAN_PROPERTY_SYMBOL = eINSTANCE.getIBeanPropertySymbol();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference IBEAN_PROPERTY_SYMBOL__OWNER = eINSTANCE.getIBeanPropertySymbol_Owner();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl <em>IInstance Symbol</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIInstanceSymbol()
		 * @generated
		 */
        EClass IINSTANCE_SYMBOL = eINSTANCE.getIInstanceSymbol();

		/**
		 * The meta object literal for the '<em><b>Type Resolved</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IINSTANCE_SYMBOL__TYPE_RESOLVED = eINSTANCE.getIInstanceSymbol_TypeResolved();

		/**
		 * The meta object literal for the '<em><b>Runtime Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IINSTANCE_SYMBOL__RUNTIME_SOURCE = eINSTANCE.getIInstanceSymbol_RuntimeSource();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaSymbolImpl <em>IJava Symbol</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaSymbolImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIJavaSymbol()
		 * @generated
		 */
        EClass IJAVA_SYMBOL = eINSTANCE.getIJavaSymbol();

		/**
		 * The meta object literal for the '<em><b>Java Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IJAVA_SYMBOL__JAVA_ELEMENT = eINSTANCE.getIJavaSymbol_JavaElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol <em>ISymbol</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getISymbol()
		 * @generated
		 */
        EClass ISYMBOL = eINSTANCE.getISymbol();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ISYMBOL__NAME = eINSTANCE.getISymbol_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl <em>IType Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getITypeDescriptor()
		 * @generated
		 */
        EClass ITYPE_DESCRIPTOR = eINSTANCE.getITypeDescriptor();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ITYPE_DESCRIPTOR__PROPERTIES = eINSTANCE.getITypeDescriptor_Properties();

		/**
		 * The meta object literal for the '<em><b>Type Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ITYPE_DESCRIPTOR__TYPE_SIGNATURE = eINSTANCE.getITypeDescriptor_TypeSignature();

		/**
		 * The meta object literal for the '<em><b>Super Type Signatures</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES = eINSTANCE.getITypeDescriptor_SuperTypeSignatures();

		/**
		 * The meta object literal for the '<em><b>Interface Type Signatures</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES = eINSTANCE.getITypeDescriptor_InterfaceTypeSignatures();

		/**
		 * The meta object literal for the '<em><b>Type Signature Delegate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE = eINSTANCE.getITypeDescriptor_TypeSignatureDelegate();

		/**
		 * The meta object literal for the '<em><b>Methods</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference ITYPE_DESCRIPTOR__METHODS = eINSTANCE.getITypeDescriptor_Methods();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IDescribedInDetail <em>IDescribed In Detail</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IDescribedInDetail
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIDescribedInDetail()
		 * @generated
		 */
        EClass IDESCRIBED_IN_DETAIL = eINSTANCE.getIDescribedInDetail();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaTypeDescriptor2Impl <em>IJava Type Descriptor2</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaTypeDescriptor2Impl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIJavaTypeDescriptor2()
		 * @generated
		 */
        EClass IJAVA_TYPE_DESCRIPTOR2 = eINSTANCE.getIJavaTypeDescriptor2();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IJAVA_TYPE_DESCRIPTOR2__TYPE = eINSTANCE.getIJavaTypeDescriptor2_Type();

		/**
		 * The meta object literal for the '<em><b>Bean Properties</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference IJAVA_TYPE_DESCRIPTOR2__BEAN_PROPERTIES = eINSTANCE.getIJavaTypeDescriptor2_BeanProperties();

		/**
		 * The meta object literal for the '<em><b>Bean Methods</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference IJAVA_TYPE_DESCRIPTOR2__BEAN_METHODS = eINSTANCE.getIJavaTypeDescriptor2_BeanMethods();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanMethodSymbolImpl <em>IBean Method Symbol</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanMethodSymbolImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBeanMethodSymbol()
		 * @generated
		 */
        EClass IBEAN_METHOD_SYMBOL = eINSTANCE.getIBeanMethodSymbol();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference IBEAN_METHOD_SYMBOL__OWNER = eINSTANCE.getIBeanMethodSymbol_Owner();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IComponentSymbolImpl <em>IComponent Symbol</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IComponentSymbolImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIComponentSymbol()
		 * @generated
		 */
        EClass ICOMPONENT_SYMBOL = eINSTANCE.getIComponentSymbol();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl <em>IProperty Symbol</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIPropertySymbol()
		 * @generated
		 */
        EClass IPROPERTY_SYMBOL = eINSTANCE.getIPropertySymbol();

		/**
		 * The meta object literal for the '<em><b>Intermediate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IPROPERTY_SYMBOL__INTERMEDIATE = eINSTANCE.getIPropertySymbol_Intermediate();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IMapTypeDescriptorImpl <em>IMap Type Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IMapTypeDescriptorImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIMapTypeDescriptor()
		 * @generated
		 */
        EClass IMAP_TYPE_DESCRIPTOR = eINSTANCE.getIMapTypeDescriptor();

		/**
		 * The meta object literal for the '<em><b>Map Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IMAP_TYPE_DESCRIPTOR__MAP_SOURCE = eINSTANCE.getIMapTypeDescriptor_MapSource();

		/**
		 * The meta object literal for the '<em><b>Immutable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IMAP_TYPE_DESCRIPTOR__IMMUTABLE = eINSTANCE.getIMapTypeDescriptor_Immutable();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IMethodSymbolImpl <em>IMethod Symbol</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IMethodSymbolImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIMethodSymbol()
		 * @generated
		 */
        EClass IMETHOD_SYMBOL = eINSTANCE.getIMethodSymbol();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IMETHOD_SYMBOL__SIGNATURE = eINSTANCE.getIMethodSymbol_Signature();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol <em>IObject Symbol</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIObjectSymbol()
		 * @generated
		 */
        EClass IOBJECT_SYMBOL = eINSTANCE.getIObjectSymbol();

		/**
		 * The meta object literal for the '<em><b>Type Descriptor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference IOBJECT_SYMBOL__TYPE_DESCRIPTOR = eINSTANCE.getIObjectSymbol_TypeDescriptor();

		/**
		 * The meta object literal for the '<em><b>Readable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IOBJECT_SYMBOL__READABLE = eINSTANCE.getIObjectSymbol_Readable();

		/**
		 * The meta object literal for the '<em><b>Writable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute IOBJECT_SYMBOL__WRITABLE = eINSTANCE.getIObjectSymbol_Writable();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedTypeDescriptor <em>IBounded Type Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedTypeDescriptor
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBoundedTypeDescriptor()
		 * @generated
		 */
        EClass IBOUNDED_TYPE_DESCRIPTOR = eINSTANCE.getIBoundedTypeDescriptor();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedMapTypeDescriptorImpl <em>IBounded Map Type Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedMapTypeDescriptorImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBoundedMapTypeDescriptor()
		 * @generated
		 */
        EClass IBOUNDED_MAP_TYPE_DESCRIPTOR = eINSTANCE.getIBoundedMapTypeDescriptor();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedJavaTypeDescriptorImpl <em>IBounded Java Type Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedJavaTypeDescriptorImpl
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBoundedJavaTypeDescriptor()
		 * @generated
		 */
        EClass IBOUNDED_JAVA_TYPE_DESCRIPTOR = eINSTANCE.getIBoundedJavaTypeDescriptor();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.provisional.ERuntimeSource <em>ERuntime Source</em>}' enum.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jst.jsf.context.symbol.internal.provisional.ERuntimeSource
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getERuntimeSource()
		 * @generated
		 */
        EEnum ERUNTIME_SOURCE = eINSTANCE.getERuntimeSource();

		/**
		 * The meta object literal for the '<em>IType</em>' data type.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jdt.core.IType
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIType()
		 * @generated
		 */
        EDataType ITYPE = eINSTANCE.getIType();

		/**
		 * The meta object literal for the '<em>IJava Element</em>' data type.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.jdt.core.IJavaElement
		 * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIJavaElement()
		 * @generated
		 */
        EDataType IJAVA_ELEMENT = eINSTANCE.getIJavaElement();

    }

} //SymbolPackage
