/**
 * <copyright>
 * </copyright>
 *
 * $Id: ConstraintsPackageImpl.java,v 1.4 2008/11/18 22:23:52 gkessler Exp $
 */
package org.eclipse.jst.jsf.validation.internal.constraints.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.TraitTypesPackage;

import org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsFactory;
import org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage;
import org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint;
import org.eclipse.jst.jsf.validation.internal.constraints.TagId;
import org.eclipse.jst.jsf.validation.internal.constraints.TagSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConstraintsPackageImpl extends EPackageImpl implements ConstraintsPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass tagIdEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass tagSetEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass containsTagConstraintEClass = null;

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
     * @see org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ConstraintsPackageImpl() {
        super(eNS_URI, ConstraintsFactory.eINSTANCE);
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
     * @return the initialized package 
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ConstraintsPackage init() {
        if (isInited) return (ConstraintsPackage)EPackage.Registry.INSTANCE.getEPackage(ConstraintsPackage.eNS_URI);

        // Obtain or create and register package
        ConstraintsPackageImpl theConstraintsPackage = (ConstraintsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ConstraintsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ConstraintsPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        TraitTypesPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theConstraintsPackage.createPackageContents();

        // Initialize created meta-data
        theConstraintsPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theConstraintsPackage.freeze();

        return theConstraintsPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTagId() {
        return tagIdEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTagId_Uri() {
        return (EAttribute)tagIdEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTagId_Name() {
        return (EAttribute)tagIdEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTagSet() {
        return tagSetEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTagSet_Tags() {
        return (EReference)tagSetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getContainsTagConstraint() {
        return containsTagConstraintEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getContainsTagConstraint_SetGenerator() {
        return (EReference)containsTagConstraintEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getContainsTagConstraint_SatisfiesSet() {
        return (EReference)containsTagConstraintEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConstraintsFactory getConstraintsFactory() {
        return (ConstraintsFactory)getEFactoryInstance();
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
        tagIdEClass = createEClass(TAG_ID);
        createEAttribute(tagIdEClass, TAG_ID__URI);
        createEAttribute(tagIdEClass, TAG_ID__NAME);

        tagSetEClass = createEClass(TAG_SET);
        createEReference(tagSetEClass, TAG_SET__TAGS);

        containsTagConstraintEClass = createEClass(CONTAINS_TAG_CONSTRAINT);
        createEReference(containsTagConstraintEClass, CONTAINS_TAG_CONSTRAINT__SET_GENERATOR);
        createEReference(containsTagConstraintEClass, CONTAINS_TAG_CONSTRAINT__SATISFIES_SET);
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

        // Obtain other dependent packages
        TraitTypesPackage theTraitTypesPackage = (TraitTypesPackage)EPackage.Registry.INSTANCE.getEPackage(TraitTypesPackage.eNS_URI);

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(tagIdEClass, TagId.class, "TagId", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getTagId_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, TagId.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getTagId_Name(), ecorePackage.getEString(), "name", null, 0, 1, TagId.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(tagSetEClass, TagSet.class, "TagSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getTagSet_Tags(), this.getTagId(), null, "tags", null, 0, -1, TagSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(containsTagConstraintEClass, ContainsTagConstraint.class, "ContainsTagConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getContainsTagConstraint_SetGenerator(), theTraitTypesPackage.getSetGenerator(), null, "setGenerator", null, 0, 1, ContainsTagConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getContainsTagConstraint_SatisfiesSet(), this.getTagSet(), null, "satisfiesSet", null, 0, 1, ContainsTagConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		 //$NON-NLS-1$
        addAnnotation
          (getTagId_Uri(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "uri" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getTagId_Name(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getTagSet_Tags(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "tagId" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getContainsTagConstraint_SetGenerator(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "set-generator" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getContainsTagConstraint_SatisfiesSet(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "satisfies-set" //$NON-NLS-1$ //$NON-NLS-2$
           });
    }

} //ConstraintsPackageImpl
