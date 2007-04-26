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
     * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.ISymbol <em>ISymbol</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.context.symbol.ISymbol
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
     * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.IObjectSymbol <em>IObject Symbol</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.context.symbol.IObjectSymbol
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
     * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.IDescribedInDetail <em>IDescribed In Detail</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.context.symbol.IDescribedInDetail
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
     * The feature id for the '<em><b>Array Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IJAVA_TYPE_DESCRIPTOR2__ARRAY_COUNT = ITYPE_DESCRIPTOR_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>IJava Type Descriptor2</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IJAVA_TYPE_DESCRIPTOR2_FEATURE_COUNT = ITYPE_DESCRIPTOR_FEATURE_COUNT + 4;

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
     * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.IBoundedTypeDescriptor <em>IBounded Type Descriptor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.context.symbol.IBoundedTypeDescriptor
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
     * The feature id for the '<em><b>Array Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR__ARRAY_COUNT = IJAVA_TYPE_DESCRIPTOR2__ARRAY_COUNT;

    /**
     * The number of structural features of the '<em>IBounded Java Type Descriptor</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IBOUNDED_JAVA_TYPE_DESCRIPTOR_FEATURE_COUNT = IJAVA_TYPE_DESCRIPTOR2_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IListTypeDescriptorImpl <em>IList Type Descriptor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IListTypeDescriptorImpl
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIListTypeDescriptor()
     * @generated
     */
    int ILIST_TYPE_DESCRIPTOR = 17;

    /**
     * The feature id for the '<em><b>Properties</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ILIST_TYPE_DESCRIPTOR__PROPERTIES = ITYPE_DESCRIPTOR__PROPERTIES;

    /**
     * The feature id for the '<em><b>Type Signature</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ILIST_TYPE_DESCRIPTOR__TYPE_SIGNATURE = ITYPE_DESCRIPTOR__TYPE_SIGNATURE;

    /**
     * The feature id for the '<em><b>Super Type Signatures</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ILIST_TYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES = ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES;

    /**
     * The feature id for the '<em><b>Interface Type Signatures</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ILIST_TYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES = ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES;

    /**
     * The feature id for the '<em><b>Type Signature Delegate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ILIST_TYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE = ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE;

    /**
     * The feature id for the '<em><b>Methods</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ILIST_TYPE_DESCRIPTOR__METHODS = ITYPE_DESCRIPTOR__METHODS;

    /**
     * The feature id for the '<em><b>List Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ILIST_TYPE_DESCRIPTOR__LIST_SOURCE = ITYPE_DESCRIPTOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>IList Type Descriptor</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ILIST_TYPE_DESCRIPTOR_FEATURE_COUNT = ITYPE_DESCRIPTOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedListTypeDescriptorImpl <em>IBounded List Type Descriptor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedListTypeDescriptorImpl
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBoundedListTypeDescriptor()
     * @generated
     */
    int IBOUNDED_LIST_TYPE_DESCRIPTOR = 18;

    /**
     * The feature id for the '<em><b>Properties</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IBOUNDED_LIST_TYPE_DESCRIPTOR__PROPERTIES = ILIST_TYPE_DESCRIPTOR__PROPERTIES;

    /**
     * The feature id for the '<em><b>Type Signature</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IBOUNDED_LIST_TYPE_DESCRIPTOR__TYPE_SIGNATURE = ILIST_TYPE_DESCRIPTOR__TYPE_SIGNATURE;

    /**
     * The feature id for the '<em><b>Super Type Signatures</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IBOUNDED_LIST_TYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES = ILIST_TYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES;

    /**
     * The feature id for the '<em><b>Interface Type Signatures</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IBOUNDED_LIST_TYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES = ILIST_TYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES;

    /**
     * The feature id for the '<em><b>Type Signature Delegate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IBOUNDED_LIST_TYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE = ILIST_TYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE;

    /**
     * The feature id for the '<em><b>Methods</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IBOUNDED_LIST_TYPE_DESCRIPTOR__METHODS = ILIST_TYPE_DESCRIPTOR__METHODS;

    /**
     * The feature id for the '<em><b>List Source</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IBOUNDED_LIST_TYPE_DESCRIPTOR__LIST_SOURCE = ILIST_TYPE_DESCRIPTOR__LIST_SOURCE;

    /**
     * The number of structural features of the '<em>IBounded List Type Descriptor</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IBOUNDED_LIST_TYPE_DESCRIPTOR_FEATURE_COUNT = ILIST_TYPE_DESCRIPTOR_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.context.symbol.ERuntimeSource <em>ERuntime Source</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.context.symbol.ERuntimeSource
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getERuntimeSource()
     * @generated
     */
    int ERUNTIME_SOURCE = 19;

    /**
     * The meta object id for the '<em>IType</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jdt.core.IType
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIType()
     * @generated
     */
    int ITYPE = 20;

    /**
     * The meta object id for the '<em>IJava Element</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jdt.core.IJavaElement
     * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIJavaElement()
     * @generated
     */
    int IJAVA_ELEMENT = 21;


    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol <em>IBean Instance Symbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IBean Instance Symbol</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol
     * @generated
     */
    EClass getIBeanInstanceSymbol();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol#getProperties <em>Properties</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Properties</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol#getProperties()
     * @see #getIBeanInstanceSymbol()
     * @generated
     */
    EReference getIBeanInstanceSymbol_Properties();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol#getMethods <em>Methods</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Methods</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol#getMethods()
     * @see #getIBeanInstanceSymbol()
     * @generated
     */
    EReference getIBeanInstanceSymbol_Methods();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol <em>IBean Property Symbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IBean Property Symbol</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol
     * @generated
     */
    EClass getIBeanPropertySymbol();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol#getOwner <em>Owner</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Owner</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol#getOwner()
     * @see #getIBeanPropertySymbol()
     * @generated
     */
    EReference getIBeanPropertySymbol_Owner();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IInstanceSymbol <em>IInstance Symbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IInstance Symbol</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IInstanceSymbol
     * @generated
     */
    EClass getIInstanceSymbol();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IInstanceSymbol#isTypeResolved <em>Type Resolved</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type Resolved</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IInstanceSymbol#isTypeResolved()
     * @see #getIInstanceSymbol()
     * @generated
     */
    EAttribute getIInstanceSymbol_TypeResolved();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IInstanceSymbol#getRuntimeSource <em>Runtime Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Runtime Source</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IInstanceSymbol#getRuntimeSource()
     * @see #getIInstanceSymbol()
     * @generated
     */
    EAttribute getIInstanceSymbol_RuntimeSource();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IJavaSymbol <em>IJava Symbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IJava Symbol</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IJavaSymbol
     * @generated
     */
    EClass getIJavaSymbol();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IJavaSymbol#getJavaElement <em>Java Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Java Element</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IJavaSymbol#getJavaElement()
     * @see #getIJavaSymbol()
     * @generated
     */
    EAttribute getIJavaSymbol_JavaElement();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.ISymbol <em>ISymbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>ISymbol</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.ISymbol
     * @generated
     */
    EClass getISymbol();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.ISymbol#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.ISymbol#getName()
     * @see #getISymbol()
     * @generated
     */
    EAttribute getISymbol_Name();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.ITypeDescriptor <em>IType Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IType Descriptor</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.ITypeDescriptor
     * @generated
     */
    EClass getITypeDescriptor();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getProperties <em>Properties</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Properties</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getProperties()
     * @see #getITypeDescriptor()
     * @generated
     */
    EReference getITypeDescriptor_Properties();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getTypeSignature <em>Type Signature</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type Signature</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getTypeSignature()
     * @see #getITypeDescriptor()
     * @generated
     */
    EAttribute getITypeDescriptor_TypeSignature();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getSuperTypeSignatures <em>Super Type Signatures</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Super Type Signatures</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getSuperTypeSignatures()
     * @see #getITypeDescriptor()
     * @generated
     */
    EAttribute getITypeDescriptor_SuperTypeSignatures();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getInterfaceTypeSignatures <em>Interface Type Signatures</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Interface Type Signatures</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getInterfaceTypeSignatures()
     * @see #getITypeDescriptor()
     * @generated
     */
    EAttribute getITypeDescriptor_InterfaceTypeSignatures();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getTypeSignatureDelegate <em>Type Signature Delegate</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type Signature Delegate</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getTypeSignatureDelegate()
     * @see #getITypeDescriptor()
     * @generated
     */
    EAttribute getITypeDescriptor_TypeSignatureDelegate();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getMethods <em>Methods</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Methods</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.ITypeDescriptor#getMethods()
     * @see #getITypeDescriptor()
     * @generated
     */
    EReference getITypeDescriptor_Methods();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IDescribedInDetail <em>IDescribed In Detail</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IDescribed In Detail</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IDescribedInDetail
     * @generated
     */
    EClass getIDescribedInDetail();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2 <em>IJava Type Descriptor2</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IJava Type Descriptor2</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2
     * @generated
     */
    EClass getIJavaTypeDescriptor2();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getType()
     * @see #getIJavaTypeDescriptor2()
     * @generated
     */
    EAttribute getIJavaTypeDescriptor2_Type();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getBeanProperties <em>Bean Properties</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Bean Properties</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getBeanProperties()
     * @see #getIJavaTypeDescriptor2()
     * @generated
     */
    EReference getIJavaTypeDescriptor2_BeanProperties();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getBeanMethods <em>Bean Methods</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Bean Methods</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getBeanMethods()
     * @see #getIJavaTypeDescriptor2()
     * @generated
     */
    EReference getIJavaTypeDescriptor2_BeanMethods();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getArrayCount <em>Array Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Array Count</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2#getArrayCount()
     * @see #getIJavaTypeDescriptor2()
     * @generated
     */
    EAttribute getIJavaTypeDescriptor2_ArrayCount();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol <em>IBean Method Symbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IBean Method Symbol</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol
     * @generated
     */
    EClass getIBeanMethodSymbol();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol#getOwner <em>Owner</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Owner</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol#getOwner()
     * @see #getIBeanMethodSymbol()
     * @generated
     */
    EReference getIBeanMethodSymbol_Owner();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IComponentSymbol <em>IComponent Symbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IComponent Symbol</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IComponentSymbol
     * @generated
     */
    EClass getIComponentSymbol();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IPropertySymbol <em>IProperty Symbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IProperty Symbol</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IPropertySymbol
     * @generated
     */
    EClass getIPropertySymbol();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IPropertySymbol#isIntermediate <em>Intermediate</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Intermediate</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IPropertySymbol#isIntermediate()
     * @see #getIPropertySymbol()
     * @generated
     */
    EAttribute getIPropertySymbol_Intermediate();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor <em>IMap Type Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IMap Type Descriptor</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor
     * @generated
     */
    EClass getIMapTypeDescriptor();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor#getMapSource <em>Map Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Map Source</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor#getMapSource()
     * @see #getIMapTypeDescriptor()
     * @generated
     */
    EAttribute getIMapTypeDescriptor_MapSource();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor#isImmutable <em>Immutable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Immutable</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor#isImmutable()
     * @see #getIMapTypeDescriptor()
     * @generated
     */
    EAttribute getIMapTypeDescriptor_Immutable();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IMethodSymbol <em>IMethod Symbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IMethod Symbol</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IMethodSymbol
     * @generated
     */
    EClass getIMethodSymbol();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IMethodSymbol#getSignature <em>Signature</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Signature</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IMethodSymbol#getSignature()
     * @see #getIMethodSymbol()
     * @generated
     */
    EAttribute getIMethodSymbol_Signature();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IObjectSymbol <em>IObject Symbol</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IObject Symbol</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IObjectSymbol
     * @generated
     */
    EClass getIObjectSymbol();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.context.symbol.IObjectSymbol#getTypeDescriptor <em>Type Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Type Descriptor</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IObjectSymbol#getTypeDescriptor()
     * @see #getIObjectSymbol()
     * @generated
     */
    EReference getIObjectSymbol_TypeDescriptor();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IObjectSymbol#isReadable <em>Readable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Readable</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IObjectSymbol#isReadable()
     * @see #getIObjectSymbol()
     * @generated
     */
    EAttribute getIObjectSymbol_Readable();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IObjectSymbol#isWritable <em>Writable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Writable</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IObjectSymbol#isWritable()
     * @see #getIObjectSymbol()
     * @generated
     */
    EAttribute getIObjectSymbol_Writable();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IBoundedTypeDescriptor <em>IBounded Type Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IBounded Type Descriptor</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IBoundedTypeDescriptor
     * @generated
     */
    EClass getIBoundedTypeDescriptor();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IBoundedMapTypeDescriptor <em>IBounded Map Type Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IBounded Map Type Descriptor</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IBoundedMapTypeDescriptor
     * @generated
     */
    EClass getIBoundedMapTypeDescriptor();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IBoundedJavaTypeDescriptor <em>IBounded Java Type Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IBounded Java Type Descriptor</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IBoundedJavaTypeDescriptor
     * @generated
     */
    EClass getIBoundedJavaTypeDescriptor();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IListTypeDescriptor <em>IList Type Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IList Type Descriptor</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IListTypeDescriptor
     * @generated
     */
    EClass getIListTypeDescriptor();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.context.symbol.IListTypeDescriptor#getListSource <em>List Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>List Source</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IListTypeDescriptor#getListSource()
     * @see #getIListTypeDescriptor()
     * @generated
     */
    EAttribute getIListTypeDescriptor_ListSource();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.context.symbol.IBoundedListTypeDescriptor <em>IBounded List Type Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>IBounded List Type Descriptor</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.IBoundedListTypeDescriptor
     * @generated
     */
    EClass getIBoundedListTypeDescriptor();

    /**
     * Returns the meta object for enum '{@link org.eclipse.jst.jsf.context.symbol.ERuntimeSource <em>ERuntime Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>ERuntime Source</em>'.
     * @see org.eclipse.jst.jsf.context.symbol.ERuntimeSource
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
        @SuppressWarnings("hiding")
        EClass IBEAN_INSTANCE_SYMBOL = eINSTANCE.getIBeanInstanceSymbol();

        /**
         * The meta object literal for the '<em><b>Properties</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EReference IBEAN_INSTANCE_SYMBOL__PROPERTIES = eINSTANCE.getIBeanInstanceSymbol_Properties();

        /**
         * The meta object literal for the '<em><b>Methods</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EReference IBEAN_INSTANCE_SYMBOL__METHODS = eINSTANCE.getIBeanInstanceSymbol_Methods();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanPropertySymbolImpl <em>IBean Property Symbol</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanPropertySymbolImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBeanPropertySymbol()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IBEAN_PROPERTY_SYMBOL = eINSTANCE.getIBeanPropertySymbol();

        /**
         * The meta object literal for the '<em><b>Owner</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EReference IBEAN_PROPERTY_SYMBOL__OWNER = eINSTANCE.getIBeanPropertySymbol_Owner();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl <em>IInstance Symbol</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IInstanceSymbolImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIInstanceSymbol()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IINSTANCE_SYMBOL = eINSTANCE.getIInstanceSymbol();

        /**
         * The meta object literal for the '<em><b>Type Resolved</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute IINSTANCE_SYMBOL__TYPE_RESOLVED = eINSTANCE.getIInstanceSymbol_TypeResolved();

        /**
         * The meta object literal for the '<em><b>Runtime Source</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute IINSTANCE_SYMBOL__RUNTIME_SOURCE = eINSTANCE.getIInstanceSymbol_RuntimeSource();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaSymbolImpl <em>IJava Symbol</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaSymbolImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIJavaSymbol()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IJAVA_SYMBOL = eINSTANCE.getIJavaSymbol();

        /**
         * The meta object literal for the '<em><b>Java Element</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute IJAVA_SYMBOL__JAVA_ELEMENT = eINSTANCE.getIJavaSymbol_JavaElement();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.ISymbol <em>ISymbol</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.ISymbol
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getISymbol()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass ISYMBOL = eINSTANCE.getISymbol();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute ISYMBOL__NAME = eINSTANCE.getISymbol_Name();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl <em>IType Descriptor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.ITypeDescriptorImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getITypeDescriptor()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass ITYPE_DESCRIPTOR = eINSTANCE.getITypeDescriptor();

        /**
         * The meta object literal for the '<em><b>Properties</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EReference ITYPE_DESCRIPTOR__PROPERTIES = eINSTANCE.getITypeDescriptor_Properties();

        /**
         * The meta object literal for the '<em><b>Type Signature</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute ITYPE_DESCRIPTOR__TYPE_SIGNATURE = eINSTANCE.getITypeDescriptor_TypeSignature();

        /**
         * The meta object literal for the '<em><b>Super Type Signatures</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES = eINSTANCE.getITypeDescriptor_SuperTypeSignatures();

        /**
         * The meta object literal for the '<em><b>Interface Type Signatures</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES = eINSTANCE.getITypeDescriptor_InterfaceTypeSignatures();

        /**
         * The meta object literal for the '<em><b>Type Signature Delegate</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE = eINSTANCE.getITypeDescriptor_TypeSignatureDelegate();

        /**
         * The meta object literal for the '<em><b>Methods</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EReference ITYPE_DESCRIPTOR__METHODS = eINSTANCE.getITypeDescriptor_Methods();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.IDescribedInDetail <em>IDescribed In Detail</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.IDescribedInDetail
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIDescribedInDetail()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IDESCRIBED_IN_DETAIL = eINSTANCE.getIDescribedInDetail();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaTypeDescriptor2Impl <em>IJava Type Descriptor2</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IJavaTypeDescriptor2Impl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIJavaTypeDescriptor2()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IJAVA_TYPE_DESCRIPTOR2 = eINSTANCE.getIJavaTypeDescriptor2();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute IJAVA_TYPE_DESCRIPTOR2__TYPE = eINSTANCE.getIJavaTypeDescriptor2_Type();

        /**
         * The meta object literal for the '<em><b>Bean Properties</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EReference IJAVA_TYPE_DESCRIPTOR2__BEAN_PROPERTIES = eINSTANCE.getIJavaTypeDescriptor2_BeanProperties();

        /**
         * The meta object literal for the '<em><b>Bean Methods</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EReference IJAVA_TYPE_DESCRIPTOR2__BEAN_METHODS = eINSTANCE.getIJavaTypeDescriptor2_BeanMethods();

        /**
         * The meta object literal for the '<em><b>Array Count</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute IJAVA_TYPE_DESCRIPTOR2__ARRAY_COUNT = eINSTANCE.getIJavaTypeDescriptor2_ArrayCount();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanMethodSymbolImpl <em>IBean Method Symbol</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBeanMethodSymbolImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBeanMethodSymbol()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IBEAN_METHOD_SYMBOL = eINSTANCE.getIBeanMethodSymbol();

        /**
         * The meta object literal for the '<em><b>Owner</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EReference IBEAN_METHOD_SYMBOL__OWNER = eINSTANCE.getIBeanMethodSymbol_Owner();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IComponentSymbolImpl <em>IComponent Symbol</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IComponentSymbolImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIComponentSymbol()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass ICOMPONENT_SYMBOL = eINSTANCE.getIComponentSymbol();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl <em>IProperty Symbol</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IPropertySymbolImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIPropertySymbol()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IPROPERTY_SYMBOL = eINSTANCE.getIPropertySymbol();

        /**
         * The meta object literal for the '<em><b>Intermediate</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute IPROPERTY_SYMBOL__INTERMEDIATE = eINSTANCE.getIPropertySymbol_Intermediate();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IMapTypeDescriptorImpl <em>IMap Type Descriptor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IMapTypeDescriptorImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIMapTypeDescriptor()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IMAP_TYPE_DESCRIPTOR = eINSTANCE.getIMapTypeDescriptor();

        /**
         * The meta object literal for the '<em><b>Map Source</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute IMAP_TYPE_DESCRIPTOR__MAP_SOURCE = eINSTANCE.getIMapTypeDescriptor_MapSource();

        /**
         * The meta object literal for the '<em><b>Immutable</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute IMAP_TYPE_DESCRIPTOR__IMMUTABLE = eINSTANCE.getIMapTypeDescriptor_Immutable();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IMethodSymbolImpl <em>IMethod Symbol</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IMethodSymbolImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIMethodSymbol()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IMETHOD_SYMBOL = eINSTANCE.getIMethodSymbol();

        /**
         * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute IMETHOD_SYMBOL__SIGNATURE = eINSTANCE.getIMethodSymbol_Signature();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.IObjectSymbol <em>IObject Symbol</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.IObjectSymbol
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIObjectSymbol()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IOBJECT_SYMBOL = eINSTANCE.getIObjectSymbol();

        /**
         * The meta object literal for the '<em><b>Type Descriptor</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EReference IOBJECT_SYMBOL__TYPE_DESCRIPTOR = eINSTANCE.getIObjectSymbol_TypeDescriptor();

        /**
         * The meta object literal for the '<em><b>Readable</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute IOBJECT_SYMBOL__READABLE = eINSTANCE.getIObjectSymbol_Readable();

        /**
         * The meta object literal for the '<em><b>Writable</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute IOBJECT_SYMBOL__WRITABLE = eINSTANCE.getIObjectSymbol_Writable();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.IBoundedTypeDescriptor <em>IBounded Type Descriptor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.IBoundedTypeDescriptor
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBoundedTypeDescriptor()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IBOUNDED_TYPE_DESCRIPTOR = eINSTANCE.getIBoundedTypeDescriptor();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedMapTypeDescriptorImpl <em>IBounded Map Type Descriptor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedMapTypeDescriptorImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBoundedMapTypeDescriptor()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IBOUNDED_MAP_TYPE_DESCRIPTOR = eINSTANCE.getIBoundedMapTypeDescriptor();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedJavaTypeDescriptorImpl <em>IBounded Java Type Descriptor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedJavaTypeDescriptorImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBoundedJavaTypeDescriptor()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IBOUNDED_JAVA_TYPE_DESCRIPTOR = eINSTANCE.getIBoundedJavaTypeDescriptor();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IListTypeDescriptorImpl <em>IList Type Descriptor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IListTypeDescriptorImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIListTypeDescriptor()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass ILIST_TYPE_DESCRIPTOR = eINSTANCE.getIListTypeDescriptor();

        /**
         * The meta object literal for the '<em><b>List Source</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        @SuppressWarnings("hiding")
        EAttribute ILIST_TYPE_DESCRIPTOR__LIST_SOURCE = eINSTANCE.getIListTypeDescriptor_ListSource();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedListTypeDescriptorImpl <em>IBounded List Type Descriptor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.IBoundedListTypeDescriptorImpl
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIBoundedListTypeDescriptor()
         * @generated
         */
        @SuppressWarnings("hiding")
        EClass IBOUNDED_LIST_TYPE_DESCRIPTOR = eINSTANCE.getIBoundedListTypeDescriptor();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.context.symbol.ERuntimeSource <em>ERuntime Source</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.context.symbol.ERuntimeSource
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getERuntimeSource()
         * @generated
         */
        @SuppressWarnings("hiding")
        EEnum ERUNTIME_SOURCE = eINSTANCE.getERuntimeSource();

        /**
         * The meta object literal for the '<em>IType</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jdt.core.IType
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIType()
         * @generated
         */
        @SuppressWarnings("hiding")
        EDataType ITYPE = eINSTANCE.getIType();

        /**
         * The meta object literal for the '<em>IJava Element</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jdt.core.IJavaElement
         * @see org.eclipse.jst.jsf.context.symbol.internal.impl.SymbolPackageImpl#getIJavaElement()
         * @generated
         */
        @SuppressWarnings("hiding")
        EDataType IJAVA_ELEMENT = eINSTANCE.getIJavaElement();

    }

} //SymbolPackage
