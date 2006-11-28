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
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedJavaTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IDescribedInDetail;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ITypeDescriptor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SymbolPackageImpl extends EPackageImpl implements SymbolPackage {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright 2006 Oracle";

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iBeanInstanceSymbolEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iBeanPropertySymbolEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iInstanceSymbolEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iJavaSymbolEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iSymbolEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iTypeDescriptorEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iDescribedInDetailEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iJavaTypeDescriptor2EClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iBeanMethodSymbolEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iComponentSymbolEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iPropertySymbolEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iMapTypeDescriptorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iMethodSymbolEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iObjectSymbolEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iBoundedTypeDescriptorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iBoundedMapTypeDescriptorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass iBoundedJavaTypeDescriptorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum eRuntimeSourceEEnum = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EDataType iTypeEDataType = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EDataType iJavaElementEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.jst.jsf.context.symbol.SymbolPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private SymbolPackageImpl() {
        super(eNS_URI, SymbolFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this
     * model, and for any others upon which it depends.  Simple
     * dependencies are satisfied by calling this method on all
     * dependent packages before doing anything else.  This method drives
     * initialization for interdependent packages directly, in parallel
     * with this package, itself.
     * <p>Of this package and its interdependencies, all packages which
     * have not yet been registered by their URI values are first created
     * and registered.  The packages are then initialized in two steps:
     * meta-model objects for all of the packages are created before any
     * are initialized, since one package's meta-model objects may refer to
     * those of another.
     * <p>Invocation of this method will not affect any packages that have
     * already been initialized.
     * <!-- begin-user-doc -->
	 * @return a symbol package
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static SymbolPackage init() {
        if (isInited) return (SymbolPackage)EPackage.Registry.INSTANCE.getEPackage(SymbolPackage.eNS_URI);

        // Obtain or create and register package
        SymbolPackageImpl theSymbolPackage = (SymbolPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof SymbolPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new SymbolPackageImpl());

        isInited = true;

        // Create package meta-data objects
        theSymbolPackage.createPackageContents();

        // Initialize created meta-data
        theSymbolPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theSymbolPackage.freeze();

        return theSymbolPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIBeanInstanceSymbol() {
        return iBeanInstanceSymbolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return ereference
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getIBeanInstanceSymbol_Properties() {
        return (EReference)iBeanInstanceSymbolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return ereference
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getIBeanInstanceSymbol_Methods() {
        return (EReference)iBeanInstanceSymbolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
     * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIBeanPropertySymbol() {
        return iBeanPropertySymbolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return ereference
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getIBeanPropertySymbol_Owner() {
        return (EReference)iBeanPropertySymbolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIInstanceSymbol() {
        return iInstanceSymbolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return eattribute
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getIInstanceSymbol_TypeResolved() {
        return (EAttribute)iInstanceSymbolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return eattribute 
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIInstanceSymbol_RuntimeSource() {
        return (EAttribute)iInstanceSymbolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIJavaSymbol() {
        return iJavaSymbolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return eattribute
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getIJavaSymbol_JavaElement() {
        return (EAttribute)iJavaSymbolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getISymbol() {
        return iSymbolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return eattribute
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getISymbol_Name() {
        return (EAttribute)iSymbolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getITypeDescriptor() {
        return iTypeDescriptorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return ereference
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getITypeDescriptor_Properties() {
        return (EReference)iTypeDescriptorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the type signature eattribute 
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getITypeDescriptor_TypeSignature() {
        return (EAttribute)iTypeDescriptorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the eattribute
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getITypeDescriptor_SuperTypeSignatures() {
        return (EAttribute)iTypeDescriptorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the eattribute
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getITypeDescriptor_InterfaceTypeSignatures() {
        return (EAttribute)iTypeDescriptorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * @return  the eattribute
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getITypeDescriptor_TypeSignatureDelegate() {
        return (EAttribute)iTypeDescriptorEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the ereference 
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getITypeDescriptor_Methods() {
        return (EReference)iTypeDescriptorEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIDescribedInDetail() {
        return iDescribedInDetailEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIJavaTypeDescriptor2() {
        return iJavaTypeDescriptor2EClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return eattribute
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getIJavaTypeDescriptor2_Type() {
        return (EAttribute)iJavaTypeDescriptor2EClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return ereference
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getIJavaTypeDescriptor2_BeanProperties() {
        return (EReference)iJavaTypeDescriptor2EClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * @return ereference
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getIJavaTypeDescriptor2_BeanMethods() {
        return (EReference)iJavaTypeDescriptor2EClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIBeanMethodSymbol() {
        return iBeanMethodSymbolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return ereference
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getIBeanMethodSymbol_Owner() {
        return (EReference)iBeanMethodSymbolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIComponentSymbol() {
        return iComponentSymbolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIPropertySymbol() {
        return iPropertySymbolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return eattribute
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getIPropertySymbol_Intermediate() {
        return (EAttribute)iPropertySymbolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIMapTypeDescriptor() {
        return iMapTypeDescriptorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return eattribute
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getIMapTypeDescriptor_MapSource() {
        return (EAttribute)iMapTypeDescriptorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the eattribute 
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIMapTypeDescriptor_Immutable() {
        return (EAttribute)iMapTypeDescriptorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the eclass for a method symbol 
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIMethodSymbol() {
        return iMethodSymbolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the signature attribute of the method symbol 
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIMethodSymbol_Signature() {
        return (EAttribute)iMethodSymbolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return eclass 
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIObjectSymbol() {
        return iObjectSymbolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return ereference 
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIObjectSymbol_TypeDescriptor() {
        return (EReference)iObjectSymbolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the eattribute 
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIObjectSymbol_Readable() {
        return (EAttribute)iObjectSymbolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * @return the eattribute 
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIObjectSymbol_Writable() {
        return (EAttribute)iObjectSymbolEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIBoundedTypeDescriptor() {
        return iBoundedTypeDescriptorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIBoundedMapTypeDescriptor() {
        return iBoundedMapTypeDescriptorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getIBoundedJavaTypeDescriptor() {
        return iBoundedJavaTypeDescriptorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * @return eenum 
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getERuntimeSource() {
        return eRuntimeSourceEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * @return edatatype
	 * <!-- end-user-doc -->
     * @generated
     */
	public EDataType getIType() {
        return iTypeEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * @return edatatype
	 * <!-- end-user-doc -->
     * @generated
     */
	public EDataType getIJavaElement() {
        return iJavaElementEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * @return the symbol factory
	 * <!-- end-user-doc -->
     * @generated
     */
	public SymbolFactory getSymbolFactory() {
        return (SymbolFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        iBeanInstanceSymbolEClass = createEClass(IBEAN_INSTANCE_SYMBOL);
        createEReference(iBeanInstanceSymbolEClass, IBEAN_INSTANCE_SYMBOL__PROPERTIES);
        createEReference(iBeanInstanceSymbolEClass, IBEAN_INSTANCE_SYMBOL__METHODS);

        iBeanPropertySymbolEClass = createEClass(IBEAN_PROPERTY_SYMBOL);
        createEReference(iBeanPropertySymbolEClass, IBEAN_PROPERTY_SYMBOL__OWNER);

        iInstanceSymbolEClass = createEClass(IINSTANCE_SYMBOL);
        createEAttribute(iInstanceSymbolEClass, IINSTANCE_SYMBOL__TYPE_RESOLVED);
        createEAttribute(iInstanceSymbolEClass, IINSTANCE_SYMBOL__RUNTIME_SOURCE);

        iJavaSymbolEClass = createEClass(IJAVA_SYMBOL);
        createEAttribute(iJavaSymbolEClass, IJAVA_SYMBOL__JAVA_ELEMENT);

        iSymbolEClass = createEClass(ISYMBOL);
        createEAttribute(iSymbolEClass, ISYMBOL__NAME);

        iTypeDescriptorEClass = createEClass(ITYPE_DESCRIPTOR);
        createEReference(iTypeDescriptorEClass, ITYPE_DESCRIPTOR__PROPERTIES);
        createEAttribute(iTypeDescriptorEClass, ITYPE_DESCRIPTOR__TYPE_SIGNATURE);
        createEAttribute(iTypeDescriptorEClass, ITYPE_DESCRIPTOR__SUPER_TYPE_SIGNATURES);
        createEAttribute(iTypeDescriptorEClass, ITYPE_DESCRIPTOR__INTERFACE_TYPE_SIGNATURES);
        createEAttribute(iTypeDescriptorEClass, ITYPE_DESCRIPTOR__TYPE_SIGNATURE_DELEGATE);
        createEReference(iTypeDescriptorEClass, ITYPE_DESCRIPTOR__METHODS);

        iDescribedInDetailEClass = createEClass(IDESCRIBED_IN_DETAIL);

        iJavaTypeDescriptor2EClass = createEClass(IJAVA_TYPE_DESCRIPTOR2);
        createEAttribute(iJavaTypeDescriptor2EClass, IJAVA_TYPE_DESCRIPTOR2__TYPE);
        createEReference(iJavaTypeDescriptor2EClass, IJAVA_TYPE_DESCRIPTOR2__BEAN_PROPERTIES);
        createEReference(iJavaTypeDescriptor2EClass, IJAVA_TYPE_DESCRIPTOR2__BEAN_METHODS);

        iBeanMethodSymbolEClass = createEClass(IBEAN_METHOD_SYMBOL);
        createEReference(iBeanMethodSymbolEClass, IBEAN_METHOD_SYMBOL__OWNER);

        iComponentSymbolEClass = createEClass(ICOMPONENT_SYMBOL);

        iPropertySymbolEClass = createEClass(IPROPERTY_SYMBOL);
        createEAttribute(iPropertySymbolEClass, IPROPERTY_SYMBOL__INTERMEDIATE);

        iMapTypeDescriptorEClass = createEClass(IMAP_TYPE_DESCRIPTOR);
        createEAttribute(iMapTypeDescriptorEClass, IMAP_TYPE_DESCRIPTOR__MAP_SOURCE);
        createEAttribute(iMapTypeDescriptorEClass, IMAP_TYPE_DESCRIPTOR__IMMUTABLE);

        iMethodSymbolEClass = createEClass(IMETHOD_SYMBOL);
        createEAttribute(iMethodSymbolEClass, IMETHOD_SYMBOL__SIGNATURE);

        iObjectSymbolEClass = createEClass(IOBJECT_SYMBOL);
        createEReference(iObjectSymbolEClass, IOBJECT_SYMBOL__TYPE_DESCRIPTOR);
        createEAttribute(iObjectSymbolEClass, IOBJECT_SYMBOL__READABLE);
        createEAttribute(iObjectSymbolEClass, IOBJECT_SYMBOL__WRITABLE);

        iBoundedTypeDescriptorEClass = createEClass(IBOUNDED_TYPE_DESCRIPTOR);

        iBoundedMapTypeDescriptorEClass = createEClass(IBOUNDED_MAP_TYPE_DESCRIPTOR);

        iBoundedJavaTypeDescriptorEClass = createEClass(IBOUNDED_JAVA_TYPE_DESCRIPTOR);

        // Create enums
        eRuntimeSourceEEnum = createEEnum(ERUNTIME_SOURCE);

        // Create data types
        iTypeEDataType = createEDataType(ITYPE);
        iJavaElementEDataType = createEDataType(IJAVA_ELEMENT);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Add supertypes to classes
        iBeanInstanceSymbolEClass.getESuperTypes().add(this.getIInstanceSymbol());
        iBeanInstanceSymbolEClass.getESuperTypes().add(this.getIDescribedInDetail());
        iBeanPropertySymbolEClass.getESuperTypes().add(this.getIPropertySymbol());
        iBeanPropertySymbolEClass.getESuperTypes().add(this.getIDescribedInDetail());
        iInstanceSymbolEClass.getESuperTypes().add(this.getIObjectSymbol());
        iJavaSymbolEClass.getESuperTypes().add(this.getISymbol());
        iJavaTypeDescriptor2EClass.getESuperTypes().add(this.getITypeDescriptor());
        iBeanMethodSymbolEClass.getESuperTypes().add(this.getIDescribedInDetail());
        iBeanMethodSymbolEClass.getESuperTypes().add(this.getIMethodSymbol());
        iComponentSymbolEClass.getESuperTypes().add(this.getIInstanceSymbol());
        iComponentSymbolEClass.getESuperTypes().add(this.getIDescribedInDetail());
        iPropertySymbolEClass.getESuperTypes().add(this.getIObjectSymbol());
        iMapTypeDescriptorEClass.getESuperTypes().add(this.getITypeDescriptor());
        iMethodSymbolEClass.getESuperTypes().add(this.getISymbol());
        iObjectSymbolEClass.getESuperTypes().add(this.getISymbol());
        iBoundedTypeDescriptorEClass.getESuperTypes().add(this.getITypeDescriptor());
        iBoundedMapTypeDescriptorEClass.getESuperTypes().add(this.getIMapTypeDescriptor());
        iBoundedMapTypeDescriptorEClass.getESuperTypes().add(this.getIBoundedTypeDescriptor());
        iBoundedJavaTypeDescriptorEClass.getESuperTypes().add(this.getIJavaTypeDescriptor2());
        iBoundedJavaTypeDescriptorEClass.getESuperTypes().add(this.getIBoundedTypeDescriptor());

        // Initialize classes and features; add operations and parameters
        initEClass(iBeanInstanceSymbolEClass, IBeanInstanceSymbol.class, "IBeanInstanceSymbol", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIBeanInstanceSymbol_Properties(), this.getIBeanPropertySymbol(), null, "properties", null, 0, -1, IBeanInstanceSymbol.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIBeanInstanceSymbol_Methods(), this.getIBeanMethodSymbol(), null, "methods", null, 0, -1, IBeanInstanceSymbol.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(iBeanInstanceSymbolEClass, this.getIJavaTypeDescriptor2(), "getJavaTypeDescriptor", 0, 1);

        EOperation op = addEOperation(iBeanInstanceSymbolEClass, null, "setJavaTypeDescriptor");
        addEParameter(op, this.getIJavaTypeDescriptor2(), "newTypeDescriptor", 0, 1);

        initEClass(iBeanPropertySymbolEClass, IBeanPropertySymbol.class, "IBeanPropertySymbol", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIBeanPropertySymbol_Owner(), this.getIJavaTypeDescriptor2(), null, "owner", null, 0, 1, IBeanPropertySymbol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iInstanceSymbolEClass, IInstanceSymbol.class, "IInstanceSymbol", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIInstanceSymbol_TypeResolved(), ecorePackage.getEBoolean(), "typeResolved", null, 0, 1, IInstanceSymbol.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIInstanceSymbol_RuntimeSource(), this.getERuntimeSource(), "runtimeSource", "TAG_INSTANTIATED_SYMBOL", 0, 1, IInstanceSymbol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iJavaSymbolEClass, IJavaSymbol.class, "IJavaSymbol", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIJavaSymbol_JavaElement(), this.getIJavaElement(), "javaElement", null, 0, 1, IJavaSymbol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iSymbolEClass, ISymbol.class, "ISymbol", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getISymbol_Name(), ecorePackage.getEString(), "name", null, 0, 1, ISymbol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iTypeDescriptorEClass, ITypeDescriptor.class, "ITypeDescriptor", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getITypeDescriptor_Properties(), this.getIPropertySymbol(), null, "properties", null, 0, -1, ITypeDescriptor.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getITypeDescriptor_TypeSignature(), ecorePackage.getEString(), "typeSignature", "", 0, 1, ITypeDescriptor.class, !IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getITypeDescriptor_SuperTypeSignatures(), ecorePackage.getEString(), "superTypeSignatures", null, 0, -1, ITypeDescriptor.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getITypeDescriptor_InterfaceTypeSignatures(), ecorePackage.getEString(), "interfaceTypeSignatures", null, 0, -1, ITypeDescriptor.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getITypeDescriptor_TypeSignatureDelegate(), ecorePackage.getEString(), "typeSignatureDelegate", null, 0, 1, ITypeDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getITypeDescriptor_Methods(), this.getIMethodSymbol(), null, "methods", null, 0, -1, ITypeDescriptor.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(iTypeDescriptorEClass, ecorePackage.getEBoolean(), "instanceOf", 0, 1);
        addEParameter(op, ecorePackage.getEString(), "typeSignature", 0, 1);

        initEClass(iDescribedInDetailEClass, IDescribedInDetail.class, "IDescribedInDetail", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(iJavaTypeDescriptor2EClass, IJavaTypeDescriptor2.class, "IJavaTypeDescriptor2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIJavaTypeDescriptor2_Type(), this.getIType(), "type", null, 0, 1, IJavaTypeDescriptor2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIJavaTypeDescriptor2_BeanProperties(), this.getIBeanPropertySymbol(), null, "beanProperties", null, 0, -1, IJavaTypeDescriptor2.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIJavaTypeDescriptor2_BeanMethods(), this.getIBeanPropertySymbol(), null, "beanMethods", null, 0, -1, IJavaTypeDescriptor2.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iBeanMethodSymbolEClass, IBeanMethodSymbol.class, "IBeanMethodSymbol", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIBeanMethodSymbol_Owner(), this.getIJavaTypeDescriptor2(), null, "owner", null, 0, 1, IBeanMethodSymbol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iComponentSymbolEClass, IComponentSymbol.class, "IComponentSymbol", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(iPropertySymbolEClass, IPropertySymbol.class, "IPropertySymbol", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIPropertySymbol_Intermediate(), ecorePackage.getEBoolean(), "intermediate", null, 0, 1, IPropertySymbol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iMapTypeDescriptorEClass, IMapTypeDescriptor.class, "IMapTypeDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIMapTypeDescriptor_MapSource(), ecorePackage.getEMap(), "mapSource", null, 0, 1, IMapTypeDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIMapTypeDescriptor_Immutable(), ecorePackage.getEBoolean(), "immutable", "true", 0, 1, IMapTypeDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iMethodSymbolEClass, IMethodSymbol.class, "IMethodSymbol", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIMethodSymbol_Signature(), ecorePackage.getEString(), "signature", null, 0, 1, IMethodSymbol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iObjectSymbolEClass, IObjectSymbol.class, "IObjectSymbol", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIObjectSymbol_TypeDescriptor(), this.getITypeDescriptor(), null, "typeDescriptor", null, 0, 1, IObjectSymbol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIObjectSymbol_Readable(), ecorePackage.getEBoolean(), "readable", null, 0, 1, IObjectSymbol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIObjectSymbol_Writable(), ecorePackage.getEBoolean(), "writable", null, 0, 1, IObjectSymbol.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        op = addEOperation(iObjectSymbolEClass, ecorePackage.getEBoolean(), "supportsCoercion", 0, 1);
        addEParameter(op, ecorePackage.getEString(), "typeSignature", 0, 1);

        op = addEOperation(iObjectSymbolEClass, this.getITypeDescriptor(), "coerce", 0, 1);
        addEParameter(op, ecorePackage.getEString(), "typeSignature", 0, 1);

        initEClass(iBoundedTypeDescriptorEClass, IBoundedTypeDescriptor.class, "IBoundedTypeDescriptor", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        op = addEOperation(iBoundedTypeDescriptorEClass, ecorePackage.getEBoolean(), "isUnboundedForType", 0, 1);
        addEParameter(op, ecorePackage.getEString(), "typeSignature", 0, 1);

        op = addEOperation(iBoundedTypeDescriptorEClass, this.getISymbol(), "getUnboundedProperty", 0, 1);
        addEParameter(op, ecorePackage.getEJavaObject(), "name", 0, 1);
        addEParameter(op, ecorePackage.getEString(), "typeSignature", 0, 1);

        initEClass(iBoundedMapTypeDescriptorEClass, IBoundedMapTypeDescriptor.class, "IBoundedMapTypeDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(iBoundedJavaTypeDescriptorEClass, IBoundedJavaTypeDescriptor.class, "IBoundedJavaTypeDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Initialize enums and add enum literals
        initEEnum(eRuntimeSourceEEnum, ERuntimeSource.class, "ERuntimeSource");
        addEEnumLiteral(eRuntimeSourceEEnum, ERuntimeSource.BUILT_IN_SYMBOL_LITERAL);
        addEEnumLiteral(eRuntimeSourceEEnum, ERuntimeSource.MANAGED_BEAN_SYMBOL_LITERAL);
        addEEnumLiteral(eRuntimeSourceEEnum, ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL);
        addEEnumLiteral(eRuntimeSourceEEnum, ERuntimeSource.OTHER_LITERAL);

        // Initialize data types
        initEDataType(iTypeEDataType, IType.class, "IType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(iJavaElementEDataType, IJavaElement.class, "IJavaElement", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //SymbolPackageImpl
