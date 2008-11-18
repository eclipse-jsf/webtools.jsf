/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - revision
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.emf.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jst.jsf.facesconfig.emf.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FacesConfigPackageImpl extends EPackageImpl implements FacesConfigPackage {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass actionListenerTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass applicationFactoryTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass applicationTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass attributeClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass attributeExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass attributeNameTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass attributeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass componentClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass componentExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass componentFamilyTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass componentTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass componentTypeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass converterClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass converterForClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass converterIdTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass converterTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass defaultLocaleTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass defaultRenderKitIdTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass defaultValueTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass descriptionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass displayNameTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass documentRootEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dynamicAttributeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dynamicElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass elResolverTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass facesConfigTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass facesContextFactoryTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass facetExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass facetNameTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass facetTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass factoryTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass fromActionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass fromOutcomeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass fromViewIdTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass iconTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass keyClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass keyTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass largeIconTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass lifecycleFactoryTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass lifecycleTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass listEntriesTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass localeConfigTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass managedBeanClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass managedBeanNameTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass managedBeanScopeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass managedBeanTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass managedPropertyTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass mapEntriesTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass mapEntryTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass messageBundleTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass navigationCaseTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass navigationHandlerTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass navigationRuleTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass nullValueTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass phaseListenerTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass propertyClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass propertyExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass propertyNameTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass propertyResolverTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass propertyTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass redirectTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass referencedBeanClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass referencedBeanNameTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass referencedBeanTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass rendererClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass rendererExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass rendererTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass rendererTypeTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass renderKitClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass renderKitFactoryTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass renderKitIdTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass renderKitTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass smallIconTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass stateManagerTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass suggestedValueTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass supportedLocaleTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass toViewIdTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass validatorClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass validatorIdTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass validatorTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueClassTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass valueTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass variableResolverTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass viewHandlerTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass resourceBundleTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass baseNameTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass varTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass renderKitExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass navigationRuleExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass validatorExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass facesConfigExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass factoryExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass lifecycleExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass managedBeanExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass converterExtensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass extensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass applicationExtensionTypeEClass = null;

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
     * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#eNS_URI
     * @see #init()
     * @generated
     */
	private FacesConfigPackageImpl() {
        super(eNS_URI, FacesConfigFactory.eINSTANCE);
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
     * @return the package 
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static FacesConfigPackage init() {
        if (isInited) return (FacesConfigPackage)EPackage.Registry.INSTANCE.getEPackage(FacesConfigPackage.eNS_URI);

        // Obtain or create and register package
        FacesConfigPackageImpl theFacesConfigPackage = (FacesConfigPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof FacesConfigPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new FacesConfigPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theFacesConfigPackage.createPackageContents();

        // Initialize created meta-data
        theFacesConfigPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theFacesConfigPackage.freeze();

        return theFacesConfigPackage;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getActionListenerType() {
        return actionListenerTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getActionListenerType_TextContent() {
        return (EAttribute)actionListenerTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getActionListenerType_Id() {
        return (EAttribute)actionListenerTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getApplicationFactoryType() {
        return applicationFactoryTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getApplicationFactoryType_TextContent() {
        return (EAttribute)applicationFactoryTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getApplicationFactoryType_Id() {
        return (EAttribute)applicationFactoryTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getApplicationType() {
        return applicationTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getApplicationType_ActionListener() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getApplicationType_DefaultRenderKitId() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getApplicationType_MessageBundle() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getApplicationType_NavigationHandler() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getApplicationType_ViewHandler() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getApplicationType_StateManager() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getApplicationType_PropertyResolver() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getApplicationType_VariableResolver() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getApplicationType_LocaleConfig() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getApplicationType_Id() {
        return (EAttribute)applicationTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getApplicationType_ELResolver() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getApplicationType_ResourceBundle() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getApplicationType_ApplicationExtension() {
        return (EReference)applicationTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getAttributeClassType() {
        return attributeClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getAttributeClassType_TextContent() {
        return (EAttribute)attributeClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getAttributeClassType_Id() {
        return (EAttribute)attributeClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getAttributeExtensionType() {
        return attributeExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getAttributeNameType() {
        return attributeNameTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getAttributeNameType_TextContent() {
        return (EAttribute)attributeNameTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getAttributeNameType_Id() {
        return (EAttribute)attributeNameTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getAttributeType() {
        return attributeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAttributeType_Description() {
        return (EReference)attributeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAttributeType_DisplayName() {
        return (EReference)attributeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAttributeType_Icon() {
        return (EReference)attributeTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAttributeType_AttributeName() {
        return (EReference)attributeTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAttributeType_AttributeClass() {
        return (EReference)attributeTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAttributeType_DefaultValue() {
        return (EReference)attributeTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAttributeType_SuggestedValue() {
        return (EReference)attributeTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAttributeType_AttributeExtension() {
        return (EReference)attributeTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getAttributeType_Id() {
        return (EAttribute)attributeTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getComponentClassType() {
        return componentClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getComponentClassType_TextContent() {
        return (EAttribute)componentClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getComponentClassType_Id() {
        return (EAttribute)componentClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getComponentExtensionType() {
        return componentExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getComponentFamilyType() {
        return componentFamilyTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getComponentFamilyType_TextContent() {
        return (EAttribute)componentFamilyTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getComponentFamilyType_Id() {
        return (EAttribute)componentFamilyTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getComponentType() {
        return componentTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getComponentType_Description() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getComponentType_DisplayName() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getComponentType_Icon() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getComponentType_ComponentType() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getComponentType_ComponentClass() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getComponentType_Facet() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getComponentType_Attribute() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getComponentType_Property() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getComponentType_ComponentExtension() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getComponentType_Id() {
        return (EAttribute)componentTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getComponentTypeType() {
        return componentTypeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getComponentTypeType_TextContent() {
        return (EAttribute)componentTypeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getComponentTypeType_Id() {
        return (EAttribute)componentTypeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getConverterClassType() {
        return converterClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getConverterClassType_TextContent() {
        return (EAttribute)converterClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getConverterClassType_Id() {
        return (EAttribute)converterClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getConverterForClassType() {
        return converterForClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getConverterForClassType_TextContent() {
        return (EAttribute)converterForClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getConverterForClassType_Id() {
        return (EAttribute)converterForClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getConverterIdType() {
        return converterIdTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getConverterIdType_TextContent() {
        return (EAttribute)converterIdTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getConverterIdType_Id() {
        return (EAttribute)converterIdTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getConverterType() {
        return converterTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getConverterType_Description() {
        return (EReference)converterTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getConverterType_DisplayName() {
        return (EReference)converterTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getConverterType_Icon() {
        return (EReference)converterTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getConverterType_ConverterId() {
        return (EReference)converterTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getConverterType_ConverterForClass() {
        return (EReference)converterTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getConverterType_ConverterClass() {
        return (EReference)converterTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getConverterType_Attribute() {
        return (EReference)converterTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getConverterType_Property() {
        return (EReference)converterTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getConverterType_ConverterExtension() {
        return (EReference)converterTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getConverterType_Id() {
        return (EAttribute)converterTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getDefaultLocaleType() {
        return defaultLocaleTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDefaultLocaleType_TextContent() {
        return (EAttribute)defaultLocaleTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDefaultLocaleType_Id() {
        return (EAttribute)defaultLocaleTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getDefaultRenderKitIdType() {
        return defaultRenderKitIdTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDefaultRenderKitIdType_TextContent() {
        return (EAttribute)defaultRenderKitIdTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDefaultRenderKitIdType_Id() {
        return (EAttribute)defaultRenderKitIdTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getDefaultValueType() {
        return defaultValueTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDefaultValueType_TextContent() {
        return (EAttribute)defaultValueTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDefaultValueType_Id() {
        return (EAttribute)defaultValueTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getDescriptionType() {
        return descriptionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDescriptionType_TextContent() {
        return (EAttribute)descriptionTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDescriptionType_Lang() {
        return (EAttribute)descriptionTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDescriptionType_Id() {
        return (EAttribute)descriptionTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getDisplayNameType() {
        return displayNameTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDisplayNameType_TextContent() {
        return (EAttribute)displayNameTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDisplayNameType_Lang() {
        return (EAttribute)displayNameTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDisplayNameType_Id() {
        return (EAttribute)displayNameTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getDocumentRoot() {
        return documentRootEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ActionListener() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Application() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ApplicationFactory() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Attribute() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_AttributeClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_AttributeExtension() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_AttributeName() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Component() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ComponentClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ComponentExtension() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ComponentFamily() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ComponentType() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Converter() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ConverterClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ConverterForClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ConverterId() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_DefaultLocale() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_DefaultRenderKitId() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_DefaultValue() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Description() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_DisplayName() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_FacesConfig() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_FacesContextFactory() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Facet() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_FacetExtension() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_FacetName() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Factory() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(29);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_FromAction() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(30);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_FromOutcome() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(31);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_FromViewId() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(32);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Icon() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(33);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Key() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(34);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_KeyClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(35);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_LargeIcon() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(36);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Lifecycle() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(37);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_LifecycleFactory() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(38);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ListEntries() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(39);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_LocaleConfig() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(40);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ManagedBean() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(41);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ManagedBeanClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(42);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ManagedBeanName() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(43);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ManagedBeanScope() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(44);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ManagedProperty() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(45);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_MapEntries() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(46);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_MapEntry() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(47);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_MessageBundle() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(48);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_NavigationCase() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(49);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_NavigationHandler() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(50);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_NavigationRule() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(51);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_NullValue() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(52);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_PhaseListener() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(53);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Property() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(54);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_PropertyClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(55);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_PropertyExtension() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(56);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_PropertyName() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(57);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_PropertyResolver() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(58);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Redirect() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(59);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ReferencedBean() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(60);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ReferencedBeanClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(61);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ReferencedBeanName() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(62);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Renderer() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(63);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_RendererClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(64);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_RendererExtension() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(65);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_RendererType() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(66);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_RenderKit() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(67);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_RenderKitClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(68);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_RenderKitFactory() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(69);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_RenderKitId() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(70);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_SmallIcon() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(71);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_StateManager() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(72);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_SuggestedValue() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(73);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_SupportedLocale() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(74);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ToViewId() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(75);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Validator() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(76);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ValidatorClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(77);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ValidatorId() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(78);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_Value() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(79);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ValueClass() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(80);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_VariableResolver() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(81);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getDocumentRoot_ViewHandler() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(82);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDynamicAttribute() {
        return dynamicAttributeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicAttribute_Name() {
        return (EAttribute)dynamicAttributeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicAttribute_Value() {
        return (EAttribute)dynamicAttributeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDynamicElement() {
        return dynamicElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDynamicElement_ChildNodes() {
        return (EReference)dynamicElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDynamicElement_Attributes() {
        return (EReference)dynamicElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicElement_TextContent() {
        return (EAttribute)dynamicElementEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getELResolverType() {
        return elResolverTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getELResolverType_TextContent() {
        return (EAttribute)elResolverTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getELResolverType_Id() {
        return (EAttribute)elResolverTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicElement_Name() {
        return (EAttribute)dynamicElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getFacesConfigType() {
        return facesConfigTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacesConfigType_Application() {
        return (EReference)facesConfigTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacesConfigType_Factory() {
        return (EReference)facesConfigTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacesConfigType_Component() {
        return (EReference)facesConfigTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacesConfigType_Converter() {
        return (EReference)facesConfigTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacesConfigType_ManagedBean() {
        return (EReference)facesConfigTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacesConfigType_NavigationRule() {
        return (EReference)facesConfigTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacesConfigType_ReferencedBean() {
        return (EReference)facesConfigTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacesConfigType_RenderKit() {
        return (EReference)facesConfigTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacesConfigType_Lifecycle() {
        return (EReference)facesConfigTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacesConfigType_Validator() {
        return (EReference)facesConfigTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFacesConfigType_FacesConfigExtension() {
        return (EReference)facesConfigTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFacesConfigType_Xmlns() {
        return (EAttribute)facesConfigTypeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFacesConfigType_Id() {
        return (EAttribute)facesConfigTypeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getFacesContextFactoryType() {
        return facesContextFactoryTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFacesContextFactoryType_TextContent() {
        return (EAttribute)facesContextFactoryTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFacesContextFactoryType_Id() {
        return (EAttribute)facesContextFactoryTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getFacetExtensionType() {
        return facetExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getFacetNameType() {
        return facetNameTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFacetNameType_TextContent() {
        return (EAttribute)facetNameTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFacetNameType_Id() {
        return (EAttribute)facetNameTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getFacetType() {
        return facetTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacetType_Description() {
        return (EReference)facetTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacetType_DisplayName() {
        return (EReference)facetTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacetType_Icon() {
        return (EReference)facetTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacetType_FacetName() {
        return (EReference)facetTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFacetType_FacetExtension() {
        return (EReference)facetTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFacetType_Id() {
        return (EAttribute)facetTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getFactoryType() {
        return factoryTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFactoryType_ApplicationFactory() {
        return (EReference)factoryTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFactoryType_FacesContextFactory() {
        return (EReference)factoryTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFactoryType_LifecycleFactory() {
        return (EReference)factoryTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getFactoryType_RenderKitFactory() {
        return (EReference)factoryTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFactoryType_FactoryExtension() {
        return (EReference)factoryTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFactoryType_Id() {
        return (EAttribute)factoryTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getFromActionType() {
        return fromActionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFromActionType_TextContent() {
        return (EAttribute)fromActionTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFromActionType_Id() {
        return (EAttribute)fromActionTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getFromOutcomeType() {
        return fromOutcomeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFromOutcomeType_TextContent() {
        return (EAttribute)fromOutcomeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFromOutcomeType_Id() {
        return (EAttribute)fromOutcomeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getFromViewIdType() {
        return fromViewIdTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFromViewIdType_TextContent() {
        return (EAttribute)fromViewIdTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getFromViewIdType_Id() {
        return (EAttribute)fromViewIdTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getIconType() {
        return iconTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getIconType_SmallIcon() {
        return (EReference)iconTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getIconType_LargeIcon() {
        return (EReference)iconTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getIconType_Lang() {
        return (EAttribute)iconTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getIconType_Id() {
        return (EAttribute)iconTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getKeyClassType() {
        return keyClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getKeyClassType_TextContent() {
        return (EAttribute)keyClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getKeyClassType_Id() {
        return (EAttribute)keyClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getKeyType() {
        return keyTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getKeyType_TextContent() {
        return (EAttribute)keyTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getKeyType_Id() {
        return (EAttribute)keyTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getLargeIconType() {
        return largeIconTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getLargeIconType_TextContent() {
        return (EAttribute)largeIconTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getLargeIconType_Id() {
        return (EAttribute)largeIconTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getLifecycleFactoryType() {
        return lifecycleFactoryTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getLifecycleFactoryType_TextContent() {
        return (EAttribute)lifecycleFactoryTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getLifecycleFactoryType_Id() {
        return (EAttribute)lifecycleFactoryTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getLifecycleType() {
        return lifecycleTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getLifecycleType_PhaseListener() {
        return (EReference)lifecycleTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLifecycleType_LifecycleExtension() {
        return (EReference)lifecycleTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getLifecycleType_Id() {
        return (EAttribute)lifecycleTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getListEntriesType() {
        return listEntriesTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getListEntriesType_ValueClass() {
        return (EReference)listEntriesTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getListEntriesType_NullValue() {
        return (EReference)listEntriesTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getListEntriesType_Value() {
        return (EReference)listEntriesTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getListEntriesType_Id() {
        return (EAttribute)listEntriesTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getLocaleConfigType() {
        return localeConfigTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getLocaleConfigType_DefaultLocale() {
        return (EReference)localeConfigTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getLocaleConfigType_SupportedLocale() {
        return (EReference)localeConfigTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getLocaleConfigType_Id() {
        return (EAttribute)localeConfigTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getManagedBeanClassType() {
        return managedBeanClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getManagedBeanClassType_TextContent() {
        return (EAttribute)managedBeanClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getManagedBeanClassType_Id() {
        return (EAttribute)managedBeanClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getManagedBeanNameType() {
        return managedBeanNameTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getManagedBeanNameType_TextContent() {
        return (EAttribute)managedBeanNameTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getManagedBeanNameType_Id() {
        return (EAttribute)managedBeanNameTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getManagedBeanScopeType() {
        return managedBeanScopeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getManagedBeanScopeType_TextContent() {
        return (EAttribute)managedBeanScopeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getManagedBeanScopeType_Id() {
        return (EAttribute)managedBeanScopeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getManagedBeanType() {
        return managedBeanTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedBeanType_Description() {
        return (EReference)managedBeanTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedBeanType_DisplayName() {
        return (EReference)managedBeanTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedBeanType_Icon() {
        return (EReference)managedBeanTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedBeanType_ManagedBeanName() {
        return (EReference)managedBeanTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedBeanType_ManagedBeanClass() {
        return (EReference)managedBeanTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedBeanType_ManagedBeanScope() {
        return (EReference)managedBeanTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedBeanType_ManagedProperty() {
        return (EReference)managedBeanTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedBeanType_MapEntries() {
        return (EReference)managedBeanTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedBeanType_ListEntries() {
        return (EReference)managedBeanTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getManagedBeanType_ManagedBeanExtension() {
        return (EReference)managedBeanTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getManagedBeanType_Id() {
        return (EAttribute)managedBeanTypeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getManagedPropertyType() {
        return managedPropertyTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedPropertyType_Description() {
        return (EReference)managedPropertyTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedPropertyType_DisplayName() {
        return (EReference)managedPropertyTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedPropertyType_Icon() {
        return (EReference)managedPropertyTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedPropertyType_PropertyName() {
        return (EReference)managedPropertyTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedPropertyType_PropertyClass() {
        return (EReference)managedPropertyTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedPropertyType_MapEntries() {
        return (EReference)managedPropertyTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedPropertyType_NullValue() {
        return (EReference)managedPropertyTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedPropertyType_Value() {
        return (EReference)managedPropertyTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getManagedPropertyType_ListEntries() {
        return (EReference)managedPropertyTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getManagedPropertyType_Id() {
        return (EAttribute)managedPropertyTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getMapEntriesType() {
        return mapEntriesTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getMapEntriesType_KeyClass() {
        return (EReference)mapEntriesTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getMapEntriesType_ValueClass() {
        return (EReference)mapEntriesTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getMapEntriesType_MapEntry() {
        return (EReference)mapEntriesTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getMapEntriesType_Id() {
        return (EAttribute)mapEntriesTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getMapEntryType() {
        return mapEntryTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getMapEntryType_Key() {
        return (EReference)mapEntryTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getMapEntryType_NullValue() {
        return (EReference)mapEntryTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getMapEntryType_Value() {
        return (EReference)mapEntryTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getMapEntryType_Id() {
        return (EAttribute)mapEntryTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getMessageBundleType() {
        return messageBundleTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getMessageBundleType_TextContent() {
        return (EAttribute)messageBundleTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getMessageBundleType_Id() {
        return (EAttribute)messageBundleTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getNavigationCaseType() {
        return navigationCaseTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationCaseType_Description() {
        return (EReference)navigationCaseTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationCaseType_DisplayName() {
        return (EReference)navigationCaseTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationCaseType_Icon() {
        return (EReference)navigationCaseTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationCaseType_FromAction() {
        return (EReference)navigationCaseTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationCaseType_FromOutcome() {
        return (EReference)navigationCaseTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationCaseType_ToViewId() {
        return (EReference)navigationCaseTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationCaseType_Redirect() {
        return (EReference)navigationCaseTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getNavigationCaseType_Id() {
        return (EAttribute)navigationCaseTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getNavigationHandlerType() {
        return navigationHandlerTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getNavigationHandlerType_TextContent() {
        return (EAttribute)navigationHandlerTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getNavigationHandlerType_Id() {
        return (EAttribute)navigationHandlerTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getNavigationRuleType() {
        return navigationRuleTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationRuleType_Description() {
        return (EReference)navigationRuleTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationRuleType_DisplayName() {
        return (EReference)navigationRuleTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationRuleType_Icon() {
        return (EReference)navigationRuleTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationRuleType_FromViewId() {
        return (EReference)navigationRuleTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getNavigationRuleType_NavigationCase() {
        return (EReference)navigationRuleTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getNavigationRuleType_NavigationRuleExtension() {
        return (EReference)navigationRuleTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getNavigationRuleType_Id() {
        return (EAttribute)navigationRuleTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getNullValueType() {
        return nullValueTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getNullValueType_Id() {
        return (EAttribute)nullValueTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPhaseListenerType() {
        return phaseListenerTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPhaseListenerType_TextContent() {
        return (EAttribute)phaseListenerTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPhaseListenerType_Id() {
        return (EAttribute)phaseListenerTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPropertyClassType() {
        return propertyClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPropertyClassType_TextContent() {
        return (EAttribute)propertyClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPropertyClassType_Id() {
        return (EAttribute)propertyClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPropertyExtensionType() {
        return propertyExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPropertyNameType() {
        return propertyNameTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPropertyNameType_TextContent() {
        return (EAttribute)propertyNameTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPropertyNameType_Id() {
        return (EAttribute)propertyNameTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPropertyResolverType() {
        return propertyResolverTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPropertyResolverType_TextContent() {
        return (EAttribute)propertyResolverTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPropertyResolverType_Id() {
        return (EAttribute)propertyResolverTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getPropertyType() {
        return propertyTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPropertyType_Description() {
        return (EReference)propertyTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPropertyType_DisplayName() {
        return (EReference)propertyTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPropertyType_Icon() {
        return (EReference)propertyTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPropertyType_PropertyName() {
        return (EReference)propertyTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPropertyType_PropertyClass() {
        return (EReference)propertyTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPropertyType_DefaultValue() {
        return (EReference)propertyTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPropertyType_SuggestedValue() {
        return (EReference)propertyTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getPropertyType_PropertyExtension() {
        return (EReference)propertyTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getPropertyType_Id() {
        return (EAttribute)propertyTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getRedirectType() {
        return redirectTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRedirectType_Id() {
        return (EAttribute)redirectTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getReferencedBeanClassType() {
        return referencedBeanClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getReferencedBeanClassType_TextContent() {
        return (EAttribute)referencedBeanClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getReferencedBeanClassType_Id() {
        return (EAttribute)referencedBeanClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getReferencedBeanNameType() {
        return referencedBeanNameTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getReferencedBeanNameType_TextContent() {
        return (EAttribute)referencedBeanNameTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getReferencedBeanNameType_Id() {
        return (EAttribute)referencedBeanNameTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getReferencedBeanType() {
        return referencedBeanTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getReferencedBeanType_Description() {
        return (EReference)referencedBeanTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getReferencedBeanType_DisplayName() {
        return (EReference)referencedBeanTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getReferencedBeanType_Icon() {
        return (EReference)referencedBeanTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getReferencedBeanType_ReferencedBeanName() {
        return (EReference)referencedBeanTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getReferencedBeanType_ReferencedBeanClass() {
        return (EReference)referencedBeanTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getReferencedBeanType_Id() {
        return (EAttribute)referencedBeanTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getRendererClassType() {
        return rendererClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRendererClassType_TextContent() {
        return (EAttribute)rendererClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRendererClassType_Id() {
        return (EAttribute)rendererClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getRendererExtensionType() {
        return rendererExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getRendererType() {
        return rendererTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRendererType_Description() {
        return (EReference)rendererTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRendererType_DisplayName() {
        return (EReference)rendererTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRendererType_Icon() {
        return (EReference)rendererTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRendererType_ComponentFamily() {
        return (EReference)rendererTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRendererType_RendererType() {
        return (EReference)rendererTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRendererType_RendererClass() {
        return (EReference)rendererTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRendererType_Facet() {
        return (EReference)rendererTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRendererType_Attribute() {
        return (EReference)rendererTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRendererType_RendererExtension() {
        return (EReference)rendererTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRendererType_Id() {
        return (EAttribute)rendererTypeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getRendererTypeType() {
        return rendererTypeTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRendererTypeType_TextContent() {
        return (EAttribute)rendererTypeTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRendererTypeType_Id() {
        return (EAttribute)rendererTypeTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getRenderKitClassType() {
        return renderKitClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRenderKitClassType_TextContent() {
        return (EAttribute)renderKitClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRenderKitClassType_Id() {
        return (EAttribute)renderKitClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getRenderKitFactoryType() {
        return renderKitFactoryTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRenderKitFactoryType_TextContent() {
        return (EAttribute)renderKitFactoryTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRenderKitFactoryType_Id() {
        return (EAttribute)renderKitFactoryTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getRenderKitIdType() {
        return renderKitIdTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRenderKitIdType_TextContent() {
        return (EAttribute)renderKitIdTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRenderKitIdType_Id() {
        return (EAttribute)renderKitIdTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getRenderKitType() {
        return renderKitTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRenderKitType_Description() {
        return (EReference)renderKitTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRenderKitType_DisplayName() {
        return (EReference)renderKitTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRenderKitType_Icon() {
        return (EReference)renderKitTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRenderKitType_RenderKitId() {
        return (EReference)renderKitTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRenderKitType_RenderKitClass() {
        return (EReference)renderKitTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRenderKitType_Renderer() {
        return (EReference)renderKitTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getRenderKitType_RenderKitExtension() {
        return (EReference)renderKitTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getRenderKitType_Id() {
        return (EAttribute)renderKitTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getSmallIconType() {
        return smallIconTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSmallIconType_TextContent() {
        return (EAttribute)smallIconTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSmallIconType_Id() {
        return (EAttribute)smallIconTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getStateManagerType() {
        return stateManagerTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getStateManagerType_TextContent() {
        return (EAttribute)stateManagerTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getStateManagerType_Id() {
        return (EAttribute)stateManagerTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getSuggestedValueType() {
        return suggestedValueTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSuggestedValueType_TextContent() {
        return (EAttribute)suggestedValueTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSuggestedValueType_Id() {
        return (EAttribute)suggestedValueTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getSupportedLocaleType() {
        return supportedLocaleTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSupportedLocaleType_TextContent() {
        return (EAttribute)supportedLocaleTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getSupportedLocaleType_Id() {
        return (EAttribute)supportedLocaleTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getToViewIdType() {
        return toViewIdTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getToViewIdType_TextContent() {
        return (EAttribute)toViewIdTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getToViewIdType_Id() {
        return (EAttribute)toViewIdTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValidatorClassType() {
        return validatorClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValidatorClassType_TextContent() {
        return (EAttribute)validatorClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValidatorClassType_Id() {
        return (EAttribute)validatorClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValidatorIdType() {
        return validatorIdTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValidatorIdType_TextContent() {
        return (EAttribute)validatorIdTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValidatorIdType_Id() {
        return (EAttribute)validatorIdTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValidatorType() {
        return validatorTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValidatorType_Description() {
        return (EReference)validatorTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValidatorType_DisplayName() {
        return (EReference)validatorTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValidatorType_Icon() {
        return (EReference)validatorTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValidatorType_ValidatorId() {
        return (EReference)validatorTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValidatorType_ValidatorClass() {
        return (EReference)validatorTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValidatorType_Attribute() {
        return (EReference)validatorTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getValidatorType_Property() {
        return (EReference)validatorTypeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getValidatorType_ValidatorExtension() {
        return (EReference)validatorTypeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValidatorType_Id() {
        return (EAttribute)validatorTypeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueClassType() {
        return valueClassTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValueClassType_TextContent() {
        return (EAttribute)valueClassTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValueClassType_Id() {
        return (EAttribute)valueClassTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getValueType() {
        return valueTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValueType_TextContent() {
        return (EAttribute)valueTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getValueType_Id() {
        return (EAttribute)valueTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getVariableResolverType() {
        return variableResolverTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getVariableResolverType_TextContent() {
        return (EAttribute)variableResolverTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getVariableResolverType_Id() {
        return (EAttribute)variableResolverTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getViewHandlerType() {
        return viewHandlerTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getViewHandlerType_TextContent() {
        return (EAttribute)viewHandlerTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getViewHandlerType_Id() {
        return (EAttribute)viewHandlerTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getResourceBundleType() {
        return resourceBundleTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResourceBundleType_Description() {
        return (EReference)resourceBundleTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResourceBundleType_DisplayName() {
        return (EReference)resourceBundleTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResourceBundleType_Icon() {
        return (EReference)resourceBundleTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResourceBundleType_BaseName() {
        return (EReference)resourceBundleTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResourceBundleType_Var() {
        return (EReference)resourceBundleTypeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getResourceBundleType_Id() {
        return (EAttribute)resourceBundleTypeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getBaseNameType() {
        return baseNameTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBaseNameType_TextContent() {
        return (EAttribute)baseNameTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getBaseNameType_Id() {
        return (EAttribute)baseNameTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getVarType() {
        return varTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVarType_TextContent() {
        return (EAttribute)varTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getVarType_Id() {
        return (EAttribute)varTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getRenderKitExtensionType() {
        return renderKitExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNavigationRuleExtensionType() {
        return navigationRuleExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getValidatorExtensionType() {
        return validatorExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFacesConfigExtensionType() {
        return facesConfigExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFactoryExtensionType() {
        return factoryExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLifecycleExtensionType() {
        return lifecycleExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getManagedBeanExtensionType() {
        return managedBeanExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getConverterExtensionType() {
        return converterExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExtensionType() {
        return extensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getExtensionType_ChildNodes() {
        return (EReference)extensionTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExtensionType_TextContent() {
        return (EAttribute)extensionTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExtensionType_Id() {
        return (EAttribute)extensionTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getApplicationExtensionType() {
        return applicationExtensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public FacesConfigFactory getFacesConfigFactory() {
        return (FacesConfigFactory)getEFactoryInstance();
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
        actionListenerTypeEClass = createEClass(ACTION_LISTENER_TYPE);
        createEAttribute(actionListenerTypeEClass, ACTION_LISTENER_TYPE__TEXT_CONTENT);
        createEAttribute(actionListenerTypeEClass, ACTION_LISTENER_TYPE__ID);

        applicationFactoryTypeEClass = createEClass(APPLICATION_FACTORY_TYPE);
        createEAttribute(applicationFactoryTypeEClass, APPLICATION_FACTORY_TYPE__TEXT_CONTENT);
        createEAttribute(applicationFactoryTypeEClass, APPLICATION_FACTORY_TYPE__ID);

        applicationTypeEClass = createEClass(APPLICATION_TYPE);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__ACTION_LISTENER);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__DEFAULT_RENDER_KIT_ID);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__MESSAGE_BUNDLE);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__NAVIGATION_HANDLER);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__VIEW_HANDLER);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__STATE_MANAGER);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__PROPERTY_RESOLVER);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__VARIABLE_RESOLVER);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__LOCALE_CONFIG);
        createEAttribute(applicationTypeEClass, APPLICATION_TYPE__ID);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__EL_RESOLVER);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__RESOURCE_BUNDLE);
        createEReference(applicationTypeEClass, APPLICATION_TYPE__APPLICATION_EXTENSION);

        attributeClassTypeEClass = createEClass(ATTRIBUTE_CLASS_TYPE);
        createEAttribute(attributeClassTypeEClass, ATTRIBUTE_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(attributeClassTypeEClass, ATTRIBUTE_CLASS_TYPE__ID);

        attributeExtensionTypeEClass = createEClass(ATTRIBUTE_EXTENSION_TYPE);

        attributeNameTypeEClass = createEClass(ATTRIBUTE_NAME_TYPE);
        createEAttribute(attributeNameTypeEClass, ATTRIBUTE_NAME_TYPE__TEXT_CONTENT);
        createEAttribute(attributeNameTypeEClass, ATTRIBUTE_NAME_TYPE__ID);

        attributeTypeEClass = createEClass(ATTRIBUTE_TYPE);
        createEReference(attributeTypeEClass, ATTRIBUTE_TYPE__DESCRIPTION);
        createEReference(attributeTypeEClass, ATTRIBUTE_TYPE__DISPLAY_NAME);
        createEReference(attributeTypeEClass, ATTRIBUTE_TYPE__ICON);
        createEReference(attributeTypeEClass, ATTRIBUTE_TYPE__ATTRIBUTE_NAME);
        createEReference(attributeTypeEClass, ATTRIBUTE_TYPE__ATTRIBUTE_CLASS);
        createEReference(attributeTypeEClass, ATTRIBUTE_TYPE__DEFAULT_VALUE);
        createEReference(attributeTypeEClass, ATTRIBUTE_TYPE__SUGGESTED_VALUE);
        createEReference(attributeTypeEClass, ATTRIBUTE_TYPE__ATTRIBUTE_EXTENSION);
        createEAttribute(attributeTypeEClass, ATTRIBUTE_TYPE__ID);

        componentClassTypeEClass = createEClass(COMPONENT_CLASS_TYPE);
        createEAttribute(componentClassTypeEClass, COMPONENT_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(componentClassTypeEClass, COMPONENT_CLASS_TYPE__ID);

        componentExtensionTypeEClass = createEClass(COMPONENT_EXTENSION_TYPE);

        componentFamilyTypeEClass = createEClass(COMPONENT_FAMILY_TYPE);
        createEAttribute(componentFamilyTypeEClass, COMPONENT_FAMILY_TYPE__TEXT_CONTENT);
        createEAttribute(componentFamilyTypeEClass, COMPONENT_FAMILY_TYPE__ID);

        componentTypeEClass = createEClass(COMPONENT_TYPE);
        createEReference(componentTypeEClass, COMPONENT_TYPE__DESCRIPTION);
        createEReference(componentTypeEClass, COMPONENT_TYPE__DISPLAY_NAME);
        createEReference(componentTypeEClass, COMPONENT_TYPE__ICON);
        createEReference(componentTypeEClass, COMPONENT_TYPE__COMPONENT_TYPE);
        createEReference(componentTypeEClass, COMPONENT_TYPE__COMPONENT_CLASS);
        createEReference(componentTypeEClass, COMPONENT_TYPE__FACET);
        createEReference(componentTypeEClass, COMPONENT_TYPE__ATTRIBUTE);
        createEReference(componentTypeEClass, COMPONENT_TYPE__PROPERTY);
        createEReference(componentTypeEClass, COMPONENT_TYPE__COMPONENT_EXTENSION);
        createEAttribute(componentTypeEClass, COMPONENT_TYPE__ID);

        componentTypeTypeEClass = createEClass(COMPONENT_TYPE_TYPE);
        createEAttribute(componentTypeTypeEClass, COMPONENT_TYPE_TYPE__TEXT_CONTENT);
        createEAttribute(componentTypeTypeEClass, COMPONENT_TYPE_TYPE__ID);

        converterClassTypeEClass = createEClass(CONVERTER_CLASS_TYPE);
        createEAttribute(converterClassTypeEClass, CONVERTER_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(converterClassTypeEClass, CONVERTER_CLASS_TYPE__ID);

        converterForClassTypeEClass = createEClass(CONVERTER_FOR_CLASS_TYPE);
        createEAttribute(converterForClassTypeEClass, CONVERTER_FOR_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(converterForClassTypeEClass, CONVERTER_FOR_CLASS_TYPE__ID);

        converterIdTypeEClass = createEClass(CONVERTER_ID_TYPE);
        createEAttribute(converterIdTypeEClass, CONVERTER_ID_TYPE__TEXT_CONTENT);
        createEAttribute(converterIdTypeEClass, CONVERTER_ID_TYPE__ID);

        converterTypeEClass = createEClass(CONVERTER_TYPE);
        createEReference(converterTypeEClass, CONVERTER_TYPE__DESCRIPTION);
        createEReference(converterTypeEClass, CONVERTER_TYPE__DISPLAY_NAME);
        createEReference(converterTypeEClass, CONVERTER_TYPE__ICON);
        createEReference(converterTypeEClass, CONVERTER_TYPE__CONVERTER_ID);
        createEReference(converterTypeEClass, CONVERTER_TYPE__CONVERTER_FOR_CLASS);
        createEReference(converterTypeEClass, CONVERTER_TYPE__CONVERTER_CLASS);
        createEReference(converterTypeEClass, CONVERTER_TYPE__ATTRIBUTE);
        createEReference(converterTypeEClass, CONVERTER_TYPE__PROPERTY);
        createEReference(converterTypeEClass, CONVERTER_TYPE__CONVERTER_EXTENSION);
        createEAttribute(converterTypeEClass, CONVERTER_TYPE__ID);

        defaultLocaleTypeEClass = createEClass(DEFAULT_LOCALE_TYPE);
        createEAttribute(defaultLocaleTypeEClass, DEFAULT_LOCALE_TYPE__TEXT_CONTENT);
        createEAttribute(defaultLocaleTypeEClass, DEFAULT_LOCALE_TYPE__ID);

        defaultRenderKitIdTypeEClass = createEClass(DEFAULT_RENDER_KIT_ID_TYPE);
        createEAttribute(defaultRenderKitIdTypeEClass, DEFAULT_RENDER_KIT_ID_TYPE__TEXT_CONTENT);
        createEAttribute(defaultRenderKitIdTypeEClass, DEFAULT_RENDER_KIT_ID_TYPE__ID);

        defaultValueTypeEClass = createEClass(DEFAULT_VALUE_TYPE);
        createEAttribute(defaultValueTypeEClass, DEFAULT_VALUE_TYPE__TEXT_CONTENT);
        createEAttribute(defaultValueTypeEClass, DEFAULT_VALUE_TYPE__ID);

        descriptionTypeEClass = createEClass(DESCRIPTION_TYPE);
        createEAttribute(descriptionTypeEClass, DESCRIPTION_TYPE__TEXT_CONTENT);
        createEAttribute(descriptionTypeEClass, DESCRIPTION_TYPE__LANG);
        createEAttribute(descriptionTypeEClass, DESCRIPTION_TYPE__ID);

        displayNameTypeEClass = createEClass(DISPLAY_NAME_TYPE);
        createEAttribute(displayNameTypeEClass, DISPLAY_NAME_TYPE__TEXT_CONTENT);
        createEAttribute(displayNameTypeEClass, DISPLAY_NAME_TYPE__LANG);
        createEAttribute(displayNameTypeEClass, DISPLAY_NAME_TYPE__ID);

        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ACTION_LISTENER);
        createEReference(documentRootEClass, DOCUMENT_ROOT__APPLICATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__APPLICATION_FACTORY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ATTRIBUTE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ATTRIBUTE_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ATTRIBUTE_EXTENSION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ATTRIBUTE_NAME);
        createEReference(documentRootEClass, DOCUMENT_ROOT__COMPONENT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__COMPONENT_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__COMPONENT_EXTENSION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__COMPONENT_FAMILY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__COMPONENT_TYPE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CONVERTER);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CONVERTER_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CONVERTER_FOR_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CONVERTER_ID);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DEFAULT_LOCALE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DEFAULT_RENDER_KIT_ID);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DEFAULT_VALUE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DESCRIPTION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DISPLAY_NAME);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FACES_CONFIG);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FACES_CONTEXT_FACTORY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FACET);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FACET_EXTENSION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FACET_NAME);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FACTORY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FROM_ACTION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FROM_OUTCOME);
        createEReference(documentRootEClass, DOCUMENT_ROOT__FROM_VIEW_ID);
        createEReference(documentRootEClass, DOCUMENT_ROOT__ICON);
        createEReference(documentRootEClass, DOCUMENT_ROOT__KEY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__KEY_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LARGE_ICON);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LIFECYCLE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LIFECYCLE_FACTORY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LIST_ENTRIES);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LOCALE_CONFIG);
        createEReference(documentRootEClass, DOCUMENT_ROOT__MANAGED_BEAN);
        createEReference(documentRootEClass, DOCUMENT_ROOT__MANAGED_BEAN_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__MANAGED_BEAN_NAME);
        createEReference(documentRootEClass, DOCUMENT_ROOT__MANAGED_BEAN_SCOPE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__MANAGED_PROPERTY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__MAP_ENTRIES);
        createEReference(documentRootEClass, DOCUMENT_ROOT__MAP_ENTRY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__MESSAGE_BUNDLE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NAVIGATION_CASE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NAVIGATION_HANDLER);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NAVIGATION_RULE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NULL_VALUE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PHASE_LISTENER);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PROPERTY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PROPERTY_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PROPERTY_EXTENSION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PROPERTY_NAME);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PROPERTY_RESOLVER);
        createEReference(documentRootEClass, DOCUMENT_ROOT__REDIRECT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__REFERENCED_BEAN);
        createEReference(documentRootEClass, DOCUMENT_ROOT__REFERENCED_BEAN_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__REFERENCED_BEAN_NAME);
        createEReference(documentRootEClass, DOCUMENT_ROOT__RENDERER);
        createEReference(documentRootEClass, DOCUMENT_ROOT__RENDERER_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__RENDERER_EXTENSION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__RENDERER_TYPE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__RENDER_KIT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__RENDER_KIT_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__RENDER_KIT_FACTORY);
        createEReference(documentRootEClass, DOCUMENT_ROOT__RENDER_KIT_ID);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SMALL_ICON);
        createEReference(documentRootEClass, DOCUMENT_ROOT__STATE_MANAGER);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SUGGESTED_VALUE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SUPPORTED_LOCALE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__TO_VIEW_ID);
        createEReference(documentRootEClass, DOCUMENT_ROOT__VALIDATOR);
        createEReference(documentRootEClass, DOCUMENT_ROOT__VALIDATOR_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__VALIDATOR_ID);
        createEReference(documentRootEClass, DOCUMENT_ROOT__VALUE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__VALUE_CLASS);
        createEReference(documentRootEClass, DOCUMENT_ROOT__VARIABLE_RESOLVER);
        createEReference(documentRootEClass, DOCUMENT_ROOT__VIEW_HANDLER);

        dynamicAttributeEClass = createEClass(DYNAMIC_ATTRIBUTE);
        createEAttribute(dynamicAttributeEClass, DYNAMIC_ATTRIBUTE__NAME);
        createEAttribute(dynamicAttributeEClass, DYNAMIC_ATTRIBUTE__VALUE);

        dynamicElementEClass = createEClass(DYNAMIC_ELEMENT);
        createEReference(dynamicElementEClass, DYNAMIC_ELEMENT__CHILD_NODES);
        createEAttribute(dynamicElementEClass, DYNAMIC_ELEMENT__NAME);
        createEReference(dynamicElementEClass, DYNAMIC_ELEMENT__ATTRIBUTES);
        createEAttribute(dynamicElementEClass, DYNAMIC_ELEMENT__TEXT_CONTENT);

        facesConfigTypeEClass = createEClass(FACES_CONFIG_TYPE);
        createEReference(facesConfigTypeEClass, FACES_CONFIG_TYPE__APPLICATION);
        createEReference(facesConfigTypeEClass, FACES_CONFIG_TYPE__FACTORY);
        createEReference(facesConfigTypeEClass, FACES_CONFIG_TYPE__COMPONENT);
        createEReference(facesConfigTypeEClass, FACES_CONFIG_TYPE__CONVERTER);
        createEReference(facesConfigTypeEClass, FACES_CONFIG_TYPE__MANAGED_BEAN);
        createEReference(facesConfigTypeEClass, FACES_CONFIG_TYPE__NAVIGATION_RULE);
        createEReference(facesConfigTypeEClass, FACES_CONFIG_TYPE__REFERENCED_BEAN);
        createEReference(facesConfigTypeEClass, FACES_CONFIG_TYPE__RENDER_KIT);
        createEReference(facesConfigTypeEClass, FACES_CONFIG_TYPE__LIFECYCLE);
        createEReference(facesConfigTypeEClass, FACES_CONFIG_TYPE__VALIDATOR);
        createEReference(facesConfigTypeEClass, FACES_CONFIG_TYPE__FACES_CONFIG_EXTENSION);
        createEAttribute(facesConfigTypeEClass, FACES_CONFIG_TYPE__XMLNS);
        createEAttribute(facesConfigTypeEClass, FACES_CONFIG_TYPE__ID);

        facesContextFactoryTypeEClass = createEClass(FACES_CONTEXT_FACTORY_TYPE);
        createEAttribute(facesContextFactoryTypeEClass, FACES_CONTEXT_FACTORY_TYPE__TEXT_CONTENT);
        createEAttribute(facesContextFactoryTypeEClass, FACES_CONTEXT_FACTORY_TYPE__ID);

        facetExtensionTypeEClass = createEClass(FACET_EXTENSION_TYPE);

        facetNameTypeEClass = createEClass(FACET_NAME_TYPE);
        createEAttribute(facetNameTypeEClass, FACET_NAME_TYPE__TEXT_CONTENT);
        createEAttribute(facetNameTypeEClass, FACET_NAME_TYPE__ID);

        facetTypeEClass = createEClass(FACET_TYPE);
        createEReference(facetTypeEClass, FACET_TYPE__DESCRIPTION);
        createEReference(facetTypeEClass, FACET_TYPE__DISPLAY_NAME);
        createEReference(facetTypeEClass, FACET_TYPE__ICON);
        createEReference(facetTypeEClass, FACET_TYPE__FACET_NAME);
        createEReference(facetTypeEClass, FACET_TYPE__FACET_EXTENSION);
        createEAttribute(facetTypeEClass, FACET_TYPE__ID);

        factoryTypeEClass = createEClass(FACTORY_TYPE);
        createEReference(factoryTypeEClass, FACTORY_TYPE__APPLICATION_FACTORY);
        createEReference(factoryTypeEClass, FACTORY_TYPE__FACES_CONTEXT_FACTORY);
        createEReference(factoryTypeEClass, FACTORY_TYPE__LIFECYCLE_FACTORY);
        createEReference(factoryTypeEClass, FACTORY_TYPE__RENDER_KIT_FACTORY);
        createEReference(factoryTypeEClass, FACTORY_TYPE__FACTORY_EXTENSION);
        createEAttribute(factoryTypeEClass, FACTORY_TYPE__ID);

        fromActionTypeEClass = createEClass(FROM_ACTION_TYPE);
        createEAttribute(fromActionTypeEClass, FROM_ACTION_TYPE__TEXT_CONTENT);
        createEAttribute(fromActionTypeEClass, FROM_ACTION_TYPE__ID);

        fromOutcomeTypeEClass = createEClass(FROM_OUTCOME_TYPE);
        createEAttribute(fromOutcomeTypeEClass, FROM_OUTCOME_TYPE__TEXT_CONTENT);
        createEAttribute(fromOutcomeTypeEClass, FROM_OUTCOME_TYPE__ID);

        fromViewIdTypeEClass = createEClass(FROM_VIEW_ID_TYPE);
        createEAttribute(fromViewIdTypeEClass, FROM_VIEW_ID_TYPE__TEXT_CONTENT);
        createEAttribute(fromViewIdTypeEClass, FROM_VIEW_ID_TYPE__ID);

        iconTypeEClass = createEClass(ICON_TYPE);
        createEReference(iconTypeEClass, ICON_TYPE__SMALL_ICON);
        createEReference(iconTypeEClass, ICON_TYPE__LARGE_ICON);
        createEAttribute(iconTypeEClass, ICON_TYPE__LANG);
        createEAttribute(iconTypeEClass, ICON_TYPE__ID);

        keyClassTypeEClass = createEClass(KEY_CLASS_TYPE);
        createEAttribute(keyClassTypeEClass, KEY_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(keyClassTypeEClass, KEY_CLASS_TYPE__ID);

        keyTypeEClass = createEClass(KEY_TYPE);
        createEAttribute(keyTypeEClass, KEY_TYPE__TEXT_CONTENT);
        createEAttribute(keyTypeEClass, KEY_TYPE__ID);

        largeIconTypeEClass = createEClass(LARGE_ICON_TYPE);
        createEAttribute(largeIconTypeEClass, LARGE_ICON_TYPE__TEXT_CONTENT);
        createEAttribute(largeIconTypeEClass, LARGE_ICON_TYPE__ID);

        lifecycleFactoryTypeEClass = createEClass(LIFECYCLE_FACTORY_TYPE);
        createEAttribute(lifecycleFactoryTypeEClass, LIFECYCLE_FACTORY_TYPE__TEXT_CONTENT);
        createEAttribute(lifecycleFactoryTypeEClass, LIFECYCLE_FACTORY_TYPE__ID);

        lifecycleTypeEClass = createEClass(LIFECYCLE_TYPE);
        createEReference(lifecycleTypeEClass, LIFECYCLE_TYPE__PHASE_LISTENER);
        createEReference(lifecycleTypeEClass, LIFECYCLE_TYPE__LIFECYCLE_EXTENSION);
        createEAttribute(lifecycleTypeEClass, LIFECYCLE_TYPE__ID);

        listEntriesTypeEClass = createEClass(LIST_ENTRIES_TYPE);
        createEReference(listEntriesTypeEClass, LIST_ENTRIES_TYPE__VALUE_CLASS);
        createEReference(listEntriesTypeEClass, LIST_ENTRIES_TYPE__NULL_VALUE);
        createEReference(listEntriesTypeEClass, LIST_ENTRIES_TYPE__VALUE);
        createEAttribute(listEntriesTypeEClass, LIST_ENTRIES_TYPE__ID);

        localeConfigTypeEClass = createEClass(LOCALE_CONFIG_TYPE);
        createEReference(localeConfigTypeEClass, LOCALE_CONFIG_TYPE__DEFAULT_LOCALE);
        createEReference(localeConfigTypeEClass, LOCALE_CONFIG_TYPE__SUPPORTED_LOCALE);
        createEAttribute(localeConfigTypeEClass, LOCALE_CONFIG_TYPE__ID);

        managedBeanClassTypeEClass = createEClass(MANAGED_BEAN_CLASS_TYPE);
        createEAttribute(managedBeanClassTypeEClass, MANAGED_BEAN_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(managedBeanClassTypeEClass, MANAGED_BEAN_CLASS_TYPE__ID);

        managedBeanNameTypeEClass = createEClass(MANAGED_BEAN_NAME_TYPE);
        createEAttribute(managedBeanNameTypeEClass, MANAGED_BEAN_NAME_TYPE__TEXT_CONTENT);
        createEAttribute(managedBeanNameTypeEClass, MANAGED_BEAN_NAME_TYPE__ID);

        managedBeanScopeTypeEClass = createEClass(MANAGED_BEAN_SCOPE_TYPE);
        createEAttribute(managedBeanScopeTypeEClass, MANAGED_BEAN_SCOPE_TYPE__TEXT_CONTENT);
        createEAttribute(managedBeanScopeTypeEClass, MANAGED_BEAN_SCOPE_TYPE__ID);

        managedBeanTypeEClass = createEClass(MANAGED_BEAN_TYPE);
        createEReference(managedBeanTypeEClass, MANAGED_BEAN_TYPE__DESCRIPTION);
        createEReference(managedBeanTypeEClass, MANAGED_BEAN_TYPE__DISPLAY_NAME);
        createEReference(managedBeanTypeEClass, MANAGED_BEAN_TYPE__ICON);
        createEReference(managedBeanTypeEClass, MANAGED_BEAN_TYPE__MANAGED_BEAN_NAME);
        createEReference(managedBeanTypeEClass, MANAGED_BEAN_TYPE__MANAGED_BEAN_CLASS);
        createEReference(managedBeanTypeEClass, MANAGED_BEAN_TYPE__MANAGED_BEAN_SCOPE);
        createEReference(managedBeanTypeEClass, MANAGED_BEAN_TYPE__MANAGED_PROPERTY);
        createEReference(managedBeanTypeEClass, MANAGED_BEAN_TYPE__MAP_ENTRIES);
        createEReference(managedBeanTypeEClass, MANAGED_BEAN_TYPE__LIST_ENTRIES);
        createEReference(managedBeanTypeEClass, MANAGED_BEAN_TYPE__MANAGED_BEAN_EXTENSION);
        createEAttribute(managedBeanTypeEClass, MANAGED_BEAN_TYPE__ID);

        managedPropertyTypeEClass = createEClass(MANAGED_PROPERTY_TYPE);
        createEReference(managedPropertyTypeEClass, MANAGED_PROPERTY_TYPE__DESCRIPTION);
        createEReference(managedPropertyTypeEClass, MANAGED_PROPERTY_TYPE__DISPLAY_NAME);
        createEReference(managedPropertyTypeEClass, MANAGED_PROPERTY_TYPE__ICON);
        createEReference(managedPropertyTypeEClass, MANAGED_PROPERTY_TYPE__PROPERTY_NAME);
        createEReference(managedPropertyTypeEClass, MANAGED_PROPERTY_TYPE__PROPERTY_CLASS);
        createEReference(managedPropertyTypeEClass, MANAGED_PROPERTY_TYPE__MAP_ENTRIES);
        createEReference(managedPropertyTypeEClass, MANAGED_PROPERTY_TYPE__NULL_VALUE);
        createEReference(managedPropertyTypeEClass, MANAGED_PROPERTY_TYPE__VALUE);
        createEReference(managedPropertyTypeEClass, MANAGED_PROPERTY_TYPE__LIST_ENTRIES);
        createEAttribute(managedPropertyTypeEClass, MANAGED_PROPERTY_TYPE__ID);

        mapEntriesTypeEClass = createEClass(MAP_ENTRIES_TYPE);
        createEReference(mapEntriesTypeEClass, MAP_ENTRIES_TYPE__KEY_CLASS);
        createEReference(mapEntriesTypeEClass, MAP_ENTRIES_TYPE__VALUE_CLASS);
        createEReference(mapEntriesTypeEClass, MAP_ENTRIES_TYPE__MAP_ENTRY);
        createEAttribute(mapEntriesTypeEClass, MAP_ENTRIES_TYPE__ID);

        mapEntryTypeEClass = createEClass(MAP_ENTRY_TYPE);
        createEReference(mapEntryTypeEClass, MAP_ENTRY_TYPE__KEY);
        createEReference(mapEntryTypeEClass, MAP_ENTRY_TYPE__NULL_VALUE);
        createEReference(mapEntryTypeEClass, MAP_ENTRY_TYPE__VALUE);
        createEAttribute(mapEntryTypeEClass, MAP_ENTRY_TYPE__ID);

        messageBundleTypeEClass = createEClass(MESSAGE_BUNDLE_TYPE);
        createEAttribute(messageBundleTypeEClass, MESSAGE_BUNDLE_TYPE__TEXT_CONTENT);
        createEAttribute(messageBundleTypeEClass, MESSAGE_BUNDLE_TYPE__ID);

        navigationCaseTypeEClass = createEClass(NAVIGATION_CASE_TYPE);
        createEReference(navigationCaseTypeEClass, NAVIGATION_CASE_TYPE__DESCRIPTION);
        createEReference(navigationCaseTypeEClass, NAVIGATION_CASE_TYPE__DISPLAY_NAME);
        createEReference(navigationCaseTypeEClass, NAVIGATION_CASE_TYPE__ICON);
        createEReference(navigationCaseTypeEClass, NAVIGATION_CASE_TYPE__FROM_ACTION);
        createEReference(navigationCaseTypeEClass, NAVIGATION_CASE_TYPE__FROM_OUTCOME);
        createEReference(navigationCaseTypeEClass, NAVIGATION_CASE_TYPE__TO_VIEW_ID);
        createEReference(navigationCaseTypeEClass, NAVIGATION_CASE_TYPE__REDIRECT);
        createEAttribute(navigationCaseTypeEClass, NAVIGATION_CASE_TYPE__ID);

        navigationHandlerTypeEClass = createEClass(NAVIGATION_HANDLER_TYPE);
        createEAttribute(navigationHandlerTypeEClass, NAVIGATION_HANDLER_TYPE__TEXT_CONTENT);
        createEAttribute(navigationHandlerTypeEClass, NAVIGATION_HANDLER_TYPE__ID);

        navigationRuleTypeEClass = createEClass(NAVIGATION_RULE_TYPE);
        createEReference(navigationRuleTypeEClass, NAVIGATION_RULE_TYPE__DESCRIPTION);
        createEReference(navigationRuleTypeEClass, NAVIGATION_RULE_TYPE__DISPLAY_NAME);
        createEReference(navigationRuleTypeEClass, NAVIGATION_RULE_TYPE__ICON);
        createEReference(navigationRuleTypeEClass, NAVIGATION_RULE_TYPE__FROM_VIEW_ID);
        createEReference(navigationRuleTypeEClass, NAVIGATION_RULE_TYPE__NAVIGATION_CASE);
        createEReference(navigationRuleTypeEClass, NAVIGATION_RULE_TYPE__NAVIGATION_RULE_EXTENSION);
        createEAttribute(navigationRuleTypeEClass, NAVIGATION_RULE_TYPE__ID);

        nullValueTypeEClass = createEClass(NULL_VALUE_TYPE);
        createEAttribute(nullValueTypeEClass, NULL_VALUE_TYPE__ID);

        phaseListenerTypeEClass = createEClass(PHASE_LISTENER_TYPE);
        createEAttribute(phaseListenerTypeEClass, PHASE_LISTENER_TYPE__TEXT_CONTENT);
        createEAttribute(phaseListenerTypeEClass, PHASE_LISTENER_TYPE__ID);

        propertyClassTypeEClass = createEClass(PROPERTY_CLASS_TYPE);
        createEAttribute(propertyClassTypeEClass, PROPERTY_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(propertyClassTypeEClass, PROPERTY_CLASS_TYPE__ID);

        propertyExtensionTypeEClass = createEClass(PROPERTY_EXTENSION_TYPE);

        propertyNameTypeEClass = createEClass(PROPERTY_NAME_TYPE);
        createEAttribute(propertyNameTypeEClass, PROPERTY_NAME_TYPE__TEXT_CONTENT);
        createEAttribute(propertyNameTypeEClass, PROPERTY_NAME_TYPE__ID);

        propertyResolverTypeEClass = createEClass(PROPERTY_RESOLVER_TYPE);
        createEAttribute(propertyResolverTypeEClass, PROPERTY_RESOLVER_TYPE__TEXT_CONTENT);
        createEAttribute(propertyResolverTypeEClass, PROPERTY_RESOLVER_TYPE__ID);

        propertyTypeEClass = createEClass(PROPERTY_TYPE);
        createEReference(propertyTypeEClass, PROPERTY_TYPE__DESCRIPTION);
        createEReference(propertyTypeEClass, PROPERTY_TYPE__DISPLAY_NAME);
        createEReference(propertyTypeEClass, PROPERTY_TYPE__ICON);
        createEReference(propertyTypeEClass, PROPERTY_TYPE__PROPERTY_NAME);
        createEReference(propertyTypeEClass, PROPERTY_TYPE__PROPERTY_CLASS);
        createEReference(propertyTypeEClass, PROPERTY_TYPE__DEFAULT_VALUE);
        createEReference(propertyTypeEClass, PROPERTY_TYPE__SUGGESTED_VALUE);
        createEReference(propertyTypeEClass, PROPERTY_TYPE__PROPERTY_EXTENSION);
        createEAttribute(propertyTypeEClass, PROPERTY_TYPE__ID);

        redirectTypeEClass = createEClass(REDIRECT_TYPE);
        createEAttribute(redirectTypeEClass, REDIRECT_TYPE__ID);

        referencedBeanClassTypeEClass = createEClass(REFERENCED_BEAN_CLASS_TYPE);
        createEAttribute(referencedBeanClassTypeEClass, REFERENCED_BEAN_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(referencedBeanClassTypeEClass, REFERENCED_BEAN_CLASS_TYPE__ID);

        referencedBeanNameTypeEClass = createEClass(REFERENCED_BEAN_NAME_TYPE);
        createEAttribute(referencedBeanNameTypeEClass, REFERENCED_BEAN_NAME_TYPE__TEXT_CONTENT);
        createEAttribute(referencedBeanNameTypeEClass, REFERENCED_BEAN_NAME_TYPE__ID);

        referencedBeanTypeEClass = createEClass(REFERENCED_BEAN_TYPE);
        createEReference(referencedBeanTypeEClass, REFERENCED_BEAN_TYPE__DESCRIPTION);
        createEReference(referencedBeanTypeEClass, REFERENCED_BEAN_TYPE__DISPLAY_NAME);
        createEReference(referencedBeanTypeEClass, REFERENCED_BEAN_TYPE__ICON);
        createEReference(referencedBeanTypeEClass, REFERENCED_BEAN_TYPE__REFERENCED_BEAN_NAME);
        createEReference(referencedBeanTypeEClass, REFERENCED_BEAN_TYPE__REFERENCED_BEAN_CLASS);
        createEAttribute(referencedBeanTypeEClass, REFERENCED_BEAN_TYPE__ID);

        rendererClassTypeEClass = createEClass(RENDERER_CLASS_TYPE);
        createEAttribute(rendererClassTypeEClass, RENDERER_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(rendererClassTypeEClass, RENDERER_CLASS_TYPE__ID);

        rendererExtensionTypeEClass = createEClass(RENDERER_EXTENSION_TYPE);

        rendererTypeEClass = createEClass(RENDERER_TYPE);
        createEReference(rendererTypeEClass, RENDERER_TYPE__DESCRIPTION);
        createEReference(rendererTypeEClass, RENDERER_TYPE__DISPLAY_NAME);
        createEReference(rendererTypeEClass, RENDERER_TYPE__ICON);
        createEReference(rendererTypeEClass, RENDERER_TYPE__COMPONENT_FAMILY);
        createEReference(rendererTypeEClass, RENDERER_TYPE__RENDERER_TYPE);
        createEReference(rendererTypeEClass, RENDERER_TYPE__RENDERER_CLASS);
        createEReference(rendererTypeEClass, RENDERER_TYPE__FACET);
        createEReference(rendererTypeEClass, RENDERER_TYPE__ATTRIBUTE);
        createEReference(rendererTypeEClass, RENDERER_TYPE__RENDERER_EXTENSION);
        createEAttribute(rendererTypeEClass, RENDERER_TYPE__ID);

        rendererTypeTypeEClass = createEClass(RENDERER_TYPE_TYPE);
        createEAttribute(rendererTypeTypeEClass, RENDERER_TYPE_TYPE__TEXT_CONTENT);
        createEAttribute(rendererTypeTypeEClass, RENDERER_TYPE_TYPE__ID);

        renderKitClassTypeEClass = createEClass(RENDER_KIT_CLASS_TYPE);
        createEAttribute(renderKitClassTypeEClass, RENDER_KIT_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(renderKitClassTypeEClass, RENDER_KIT_CLASS_TYPE__ID);

        renderKitFactoryTypeEClass = createEClass(RENDER_KIT_FACTORY_TYPE);
        createEAttribute(renderKitFactoryTypeEClass, RENDER_KIT_FACTORY_TYPE__TEXT_CONTENT);
        createEAttribute(renderKitFactoryTypeEClass, RENDER_KIT_FACTORY_TYPE__ID);

        renderKitIdTypeEClass = createEClass(RENDER_KIT_ID_TYPE);
        createEAttribute(renderKitIdTypeEClass, RENDER_KIT_ID_TYPE__TEXT_CONTENT);
        createEAttribute(renderKitIdTypeEClass, RENDER_KIT_ID_TYPE__ID);

        renderKitTypeEClass = createEClass(RENDER_KIT_TYPE);
        createEReference(renderKitTypeEClass, RENDER_KIT_TYPE__DESCRIPTION);
        createEReference(renderKitTypeEClass, RENDER_KIT_TYPE__DISPLAY_NAME);
        createEReference(renderKitTypeEClass, RENDER_KIT_TYPE__ICON);
        createEReference(renderKitTypeEClass, RENDER_KIT_TYPE__RENDER_KIT_ID);
        createEReference(renderKitTypeEClass, RENDER_KIT_TYPE__RENDER_KIT_CLASS);
        createEReference(renderKitTypeEClass, RENDER_KIT_TYPE__RENDERER);
        createEReference(renderKitTypeEClass, RENDER_KIT_TYPE__RENDER_KIT_EXTENSION);
        createEAttribute(renderKitTypeEClass, RENDER_KIT_TYPE__ID);

        smallIconTypeEClass = createEClass(SMALL_ICON_TYPE);
        createEAttribute(smallIconTypeEClass, SMALL_ICON_TYPE__TEXT_CONTENT);
        createEAttribute(smallIconTypeEClass, SMALL_ICON_TYPE__ID);

        stateManagerTypeEClass = createEClass(STATE_MANAGER_TYPE);
        createEAttribute(stateManagerTypeEClass, STATE_MANAGER_TYPE__TEXT_CONTENT);
        createEAttribute(stateManagerTypeEClass, STATE_MANAGER_TYPE__ID);

        suggestedValueTypeEClass = createEClass(SUGGESTED_VALUE_TYPE);
        createEAttribute(suggestedValueTypeEClass, SUGGESTED_VALUE_TYPE__TEXT_CONTENT);
        createEAttribute(suggestedValueTypeEClass, SUGGESTED_VALUE_TYPE__ID);

        supportedLocaleTypeEClass = createEClass(SUPPORTED_LOCALE_TYPE);
        createEAttribute(supportedLocaleTypeEClass, SUPPORTED_LOCALE_TYPE__TEXT_CONTENT);
        createEAttribute(supportedLocaleTypeEClass, SUPPORTED_LOCALE_TYPE__ID);

        toViewIdTypeEClass = createEClass(TO_VIEW_ID_TYPE);
        createEAttribute(toViewIdTypeEClass, TO_VIEW_ID_TYPE__TEXT_CONTENT);
        createEAttribute(toViewIdTypeEClass, TO_VIEW_ID_TYPE__ID);

        validatorClassTypeEClass = createEClass(VALIDATOR_CLASS_TYPE);
        createEAttribute(validatorClassTypeEClass, VALIDATOR_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(validatorClassTypeEClass, VALIDATOR_CLASS_TYPE__ID);

        validatorIdTypeEClass = createEClass(VALIDATOR_ID_TYPE);
        createEAttribute(validatorIdTypeEClass, VALIDATOR_ID_TYPE__TEXT_CONTENT);
        createEAttribute(validatorIdTypeEClass, VALIDATOR_ID_TYPE__ID);

        validatorTypeEClass = createEClass(VALIDATOR_TYPE);
        createEReference(validatorTypeEClass, VALIDATOR_TYPE__DESCRIPTION);
        createEReference(validatorTypeEClass, VALIDATOR_TYPE__DISPLAY_NAME);
        createEReference(validatorTypeEClass, VALIDATOR_TYPE__ICON);
        createEReference(validatorTypeEClass, VALIDATOR_TYPE__VALIDATOR_ID);
        createEReference(validatorTypeEClass, VALIDATOR_TYPE__VALIDATOR_CLASS);
        createEReference(validatorTypeEClass, VALIDATOR_TYPE__ATTRIBUTE);
        createEReference(validatorTypeEClass, VALIDATOR_TYPE__PROPERTY);
        createEReference(validatorTypeEClass, VALIDATOR_TYPE__VALIDATOR_EXTENSION);
        createEAttribute(validatorTypeEClass, VALIDATOR_TYPE__ID);

        valueClassTypeEClass = createEClass(VALUE_CLASS_TYPE);
        createEAttribute(valueClassTypeEClass, VALUE_CLASS_TYPE__TEXT_CONTENT);
        createEAttribute(valueClassTypeEClass, VALUE_CLASS_TYPE__ID);

        valueTypeEClass = createEClass(VALUE_TYPE);
        createEAttribute(valueTypeEClass, VALUE_TYPE__TEXT_CONTENT);
        createEAttribute(valueTypeEClass, VALUE_TYPE__ID);

        variableResolverTypeEClass = createEClass(VARIABLE_RESOLVER_TYPE);
        createEAttribute(variableResolverTypeEClass, VARIABLE_RESOLVER_TYPE__TEXT_CONTENT);
        createEAttribute(variableResolverTypeEClass, VARIABLE_RESOLVER_TYPE__ID);

        viewHandlerTypeEClass = createEClass(VIEW_HANDLER_TYPE);
        createEAttribute(viewHandlerTypeEClass, VIEW_HANDLER_TYPE__TEXT_CONTENT);
        createEAttribute(viewHandlerTypeEClass, VIEW_HANDLER_TYPE__ID);

        extensionTypeEClass = createEClass(EXTENSION_TYPE);
        createEReference(extensionTypeEClass, EXTENSION_TYPE__CHILD_NODES);
        createEAttribute(extensionTypeEClass, EXTENSION_TYPE__TEXT_CONTENT);
        createEAttribute(extensionTypeEClass, EXTENSION_TYPE__ID);

        applicationExtensionTypeEClass = createEClass(APPLICATION_EXTENSION_TYPE);

        converterExtensionTypeEClass = createEClass(CONVERTER_EXTENSION_TYPE);

        elResolverTypeEClass = createEClass(EL_RESOLVER_TYPE);
        createEAttribute(elResolverTypeEClass, EL_RESOLVER_TYPE__TEXT_CONTENT);
        createEAttribute(elResolverTypeEClass, EL_RESOLVER_TYPE__ID);

        facesConfigExtensionTypeEClass = createEClass(FACES_CONFIG_EXTENSION_TYPE);

        factoryExtensionTypeEClass = createEClass(FACTORY_EXTENSION_TYPE);

        lifecycleExtensionTypeEClass = createEClass(LIFECYCLE_EXTENSION_TYPE);

        managedBeanExtensionTypeEClass = createEClass(MANAGED_BEAN_EXTENSION_TYPE);

        navigationRuleExtensionTypeEClass = createEClass(NAVIGATION_RULE_EXTENSION_TYPE);

        validatorExtensionTypeEClass = createEClass(VALIDATOR_EXTENSION_TYPE);

        resourceBundleTypeEClass = createEClass(RESOURCE_BUNDLE_TYPE);
        createEReference(resourceBundleTypeEClass, RESOURCE_BUNDLE_TYPE__DESCRIPTION);
        createEReference(resourceBundleTypeEClass, RESOURCE_BUNDLE_TYPE__DISPLAY_NAME);
        createEReference(resourceBundleTypeEClass, RESOURCE_BUNDLE_TYPE__ICON);
        createEReference(resourceBundleTypeEClass, RESOURCE_BUNDLE_TYPE__BASE_NAME);
        createEReference(resourceBundleTypeEClass, RESOURCE_BUNDLE_TYPE__VAR);
        createEAttribute(resourceBundleTypeEClass, RESOURCE_BUNDLE_TYPE__ID);

        baseNameTypeEClass = createEClass(BASE_NAME_TYPE);
        createEAttribute(baseNameTypeEClass, BASE_NAME_TYPE__TEXT_CONTENT);
        createEAttribute(baseNameTypeEClass, BASE_NAME_TYPE__ID);

        varTypeEClass = createEClass(VAR_TYPE);
        createEAttribute(varTypeEClass, VAR_TYPE__TEXT_CONTENT);
        createEAttribute(varTypeEClass, VAR_TYPE__ID);

        renderKitExtensionTypeEClass = createEClass(RENDER_KIT_EXTENSION_TYPE);
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
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Add supertypes to classes
        attributeExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        componentExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        facetExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        propertyExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        rendererExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        applicationExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        converterExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        facesConfigExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        factoryExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        lifecycleExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        managedBeanExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        navigationRuleExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        validatorExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());
        renderKitExtensionTypeEClass.getESuperTypes().add(this.getExtensionType());

        // Initialize classes and features; add operations and parameters
        initEClass(actionListenerTypeEClass, ActionListenerType.class, "ActionListenerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getActionListenerType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ActionListenerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getActionListenerType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ActionListenerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(applicationFactoryTypeEClass, ApplicationFactoryType.class, "ApplicationFactoryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getApplicationFactoryType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ApplicationFactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getApplicationFactoryType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ApplicationFactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(applicationTypeEClass, ApplicationType.class, "ApplicationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getApplicationType_ActionListener(), this.getActionListenerType(), null, "actionListener", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getApplicationType_DefaultRenderKitId(), this.getDefaultRenderKitIdType(), null, "defaultRenderKitId", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getApplicationType_MessageBundle(), this.getMessageBundleType(), null, "messageBundle", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getApplicationType_NavigationHandler(), this.getNavigationHandlerType(), null, "navigationHandler", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getApplicationType_ViewHandler(), this.getViewHandlerType(), null, "viewHandler", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getApplicationType_StateManager(), this.getStateManagerType(), null, "stateManager", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getApplicationType_PropertyResolver(), this.getPropertyResolverType(), null, "propertyResolver", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getApplicationType_VariableResolver(), this.getVariableResolverType(), null, "variableResolver", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getApplicationType_LocaleConfig(), this.getLocaleConfigType(), null, "localeConfig", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getApplicationType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getApplicationType_ELResolver(), this.getELResolverType(), null, "eLResolver", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getApplicationType_ResourceBundle(), this.getResourceBundleType(), null, "resourceBundle", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getApplicationType_ApplicationExtension(), this.getApplicationExtensionType(), null, "applicationExtension", null, 0, -1, ApplicationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(attributeClassTypeEClass, AttributeClassType.class, "AttributeClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAttributeClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, AttributeClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getAttributeClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, AttributeClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(attributeExtensionTypeEClass, AttributeExtensionType.class, "AttributeExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(attributeNameTypeEClass, AttributeNameType.class, "AttributeNameType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAttributeNameType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, AttributeNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getAttributeNameType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, AttributeNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(attributeTypeEClass, AttributeType.class, "AttributeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getAttributeType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, AttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAttributeType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, AttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAttributeType_Icon(), this.getIconType(), null, "icon", null, 0, -1, AttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAttributeType_AttributeName(), this.getAttributeNameType(), null, "attributeName", null, 1, 1, AttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAttributeType_AttributeClass(), this.getAttributeClassType(), null, "attributeClass", null, 1, 1, AttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAttributeType_DefaultValue(), this.getDefaultValueType(), null, "defaultValue", null, 0, 1, AttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAttributeType_SuggestedValue(), this.getSuggestedValueType(), null, "suggestedValue", null, 0, 1, AttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getAttributeType_AttributeExtension(), this.getAttributeExtensionType(), null, "attributeExtension", null, 0, -1, AttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getAttributeType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, AttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(componentClassTypeEClass, ComponentClassType.class, "ComponentClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getComponentClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ComponentClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getComponentClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ComponentClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(componentExtensionTypeEClass, ComponentExtensionType.class, "ComponentExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(componentFamilyTypeEClass, ComponentFamilyType.class, "ComponentFamilyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getComponentFamilyType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ComponentFamilyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getComponentFamilyType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ComponentFamilyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(componentTypeEClass, ComponentType.class, "ComponentType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getComponentType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getComponentType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getComponentType_Icon(), this.getIconType(), null, "icon", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getComponentType_ComponentType(), this.getComponentTypeType(), null, "componentType", null, 1, 1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getComponentType_ComponentClass(), this.getComponentClassType(), null, "componentClass", null, 1, 1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getComponentType_Facet(), this.getFacetType(), null, "facet", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getComponentType_Attribute(), this.getAttributeType(), null, "attribute", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getComponentType_Property(), this.getPropertyType(), null, "property", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getComponentType_ComponentExtension(), this.getComponentExtensionType(), null, "componentExtension", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getComponentType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(componentTypeTypeEClass, ComponentTypeType.class, "ComponentTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getComponentTypeType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ComponentTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getComponentTypeType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ComponentTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(converterClassTypeEClass, ConverterClassType.class, "ConverterClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getConverterClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ConverterClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConverterClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ConverterClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(converterForClassTypeEClass, ConverterForClassType.class, "ConverterForClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getConverterForClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ConverterForClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConverterForClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ConverterForClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(converterIdTypeEClass, ConverterIdType.class, "ConverterIdType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getConverterIdType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ConverterIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConverterIdType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ConverterIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(converterTypeEClass, ConverterType.class, "ConverterType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getConverterType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, ConverterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConverterType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, ConverterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConverterType_Icon(), this.getIconType(), null, "icon", null, 0, -1, ConverterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConverterType_ConverterId(), this.getConverterIdType(), null, "converterId", null, 0, 1, ConverterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConverterType_ConverterForClass(), this.getConverterForClassType(), null, "converterForClass", null, 0, 1, ConverterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConverterType_ConverterClass(), this.getConverterClassType(), null, "converterClass", null, 1, 1, ConverterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConverterType_Attribute(), this.getAttributeType(), null, "attribute", null, 0, -1, ConverterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConverterType_Property(), this.getPropertyType(), null, "property", null, 0, -1, ConverterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConverterType_ConverterExtension(), this.getConverterExtensionType(), null, "converterExtension", null, 0, -1, ConverterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConverterType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ConverterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(defaultLocaleTypeEClass, DefaultLocaleType.class, "DefaultLocaleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDefaultLocaleType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, DefaultLocaleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDefaultLocaleType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, DefaultLocaleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(defaultRenderKitIdTypeEClass, DefaultRenderKitIdType.class, "DefaultRenderKitIdType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDefaultRenderKitIdType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, DefaultRenderKitIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDefaultRenderKitIdType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, DefaultRenderKitIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(defaultValueTypeEClass, DefaultValueType.class, "DefaultValueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDefaultValueType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, DefaultValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDefaultValueType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, DefaultValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(descriptionTypeEClass, DescriptionType.class, "DescriptionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDescriptionType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, DescriptionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDescriptionType_Lang(), theXMLTypePackage.getLanguage(), "lang", null, 0, 1, DescriptionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDescriptionType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, DescriptionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(displayNameTypeEClass, DisplayNameType.class, "DisplayNameType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDisplayNameType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, DisplayNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDisplayNameType_Lang(), theXMLTypePackage.getLanguage(), "lang", null, 0, 1, DisplayNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDisplayNameType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, DisplayNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ActionListener(), this.getActionListenerType(), null, "actionListener", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Application(), this.getApplicationType(), null, "application", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ApplicationFactory(), this.getApplicationFactoryType(), null, "applicationFactory", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Attribute(), this.getAttributeType(), null, "attribute", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_AttributeClass(), this.getAttributeClassType(), null, "attributeClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_AttributeExtension(), this.getAttributeExtensionType(), null, "attributeExtension", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_AttributeName(), this.getAttributeNameType(), null, "attributeName", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Component(), this.getComponentType(), null, "component", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ComponentClass(), this.getComponentClassType(), null, "componentClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ComponentExtension(), this.getComponentExtensionType(), null, "componentExtension", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ComponentFamily(), this.getComponentFamilyType(), null, "componentFamily", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ComponentType(), this.getComponentTypeType(), null, "componentType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Converter(), this.getConverterType(), null, "converter", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ConverterClass(), this.getConverterClassType(), null, "converterClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ConverterForClass(), this.getConverterForClassType(), null, "converterForClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ConverterId(), this.getConverterIdType(), null, "converterId", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_DefaultLocale(), this.getDefaultLocaleType(), null, "defaultLocale", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_DefaultRenderKitId(), this.getDefaultRenderKitIdType(), null, "defaultRenderKitId", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_DefaultValue(), this.getDefaultValueType(), null, "defaultValue", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Description(), this.getDescriptionType(), null, "description", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_FacesConfig(), this.getFacesConfigType(), null, "facesConfig", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_FacesContextFactory(), this.getFacesContextFactoryType(), null, "facesContextFactory", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Facet(), this.getFacetType(), null, "facet", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_FacetExtension(), this.getFacetExtensionType(), null, "facetExtension", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_FacetName(), this.getFacetNameType(), null, "facetName", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Factory(), this.getFactoryType(), null, "factory", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_FromAction(), this.getFromActionType(), null, "fromAction", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_FromOutcome(), this.getFromOutcomeType(), null, "fromOutcome", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_FromViewId(), this.getFromViewIdType(), null, "fromViewId", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Icon(), this.getIconType(), null, "icon", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Key(), this.getKeyType(), null, "key", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_KeyClass(), this.getKeyClassType(), null, "keyClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_LargeIcon(), this.getLargeIconType(), null, "largeIcon", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Lifecycle(), this.getLifecycleType(), null, "lifecycle", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_LifecycleFactory(), this.getLifecycleFactoryType(), null, "lifecycleFactory", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ListEntries(), this.getListEntriesType(), null, "listEntries", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_LocaleConfig(), this.getLocaleConfigType(), null, "localeConfig", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ManagedBean(), this.getManagedBeanType(), null, "managedBean", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ManagedBeanClass(), this.getManagedBeanClassType(), null, "managedBeanClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ManagedBeanName(), this.getManagedBeanNameType(), null, "managedBeanName", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ManagedBeanScope(), this.getManagedBeanScopeType(), null, "managedBeanScope", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ManagedProperty(), this.getManagedPropertyType(), null, "managedProperty", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_MapEntries(), this.getMapEntriesType(), null, "mapEntries", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_MapEntry(), this.getMapEntryType(), null, "mapEntry", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_MessageBundle(), this.getMessageBundleType(), null, "messageBundle", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_NavigationCase(), this.getNavigationCaseType(), null, "navigationCase", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_NavigationHandler(), this.getNavigationHandlerType(), null, "navigationHandler", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_NavigationRule(), this.getNavigationRuleType(), null, "navigationRule", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_NullValue(), this.getNullValueType(), null, "nullValue", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_PhaseListener(), this.getPhaseListenerType(), null, "phaseListener", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Property(), this.getPropertyType(), null, "property", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_PropertyClass(), this.getPropertyClassType(), null, "propertyClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_PropertyExtension(), this.getPropertyExtensionType(), null, "propertyExtension", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_PropertyName(), this.getPropertyNameType(), null, "propertyName", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_PropertyResolver(), this.getPropertyResolverType(), null, "propertyResolver", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Redirect(), this.getRedirectType(), null, "redirect", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ReferencedBean(), this.getReferencedBeanType(), null, "referencedBean", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ReferencedBeanClass(), this.getReferencedBeanClassType(), null, "referencedBeanClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ReferencedBeanName(), this.getReferencedBeanNameType(), null, "referencedBeanName", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Renderer(), this.getRendererType(), null, "renderer", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_RendererClass(), this.getRendererClassType(), null, "rendererClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_RendererExtension(), this.getRendererExtensionType(), null, "rendererExtension", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_RendererType(), this.getRendererTypeType(), null, "rendererType", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_RenderKit(), this.getRenderKitType(), null, "renderKit", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_RenderKitClass(), this.getRenderKitClassType(), null, "renderKitClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_RenderKitFactory(), this.getRenderKitFactoryType(), null, "renderKitFactory", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_RenderKitId(), this.getRenderKitIdType(), null, "renderKitId", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_SmallIcon(), this.getSmallIconType(), null, "smallIcon", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_StateManager(), this.getStateManagerType(), null, "stateManager", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_SuggestedValue(), this.getSuggestedValueType(), null, "suggestedValue", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_SupportedLocale(), this.getSupportedLocaleType(), null, "supportedLocale", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ToViewId(), this.getToViewIdType(), null, "toViewId", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Validator(), this.getValidatorType(), null, "validator", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ValidatorClass(), this.getValidatorClassType(), null, "validatorClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ValidatorId(), this.getValidatorIdType(), null, "validatorId", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_Value(), this.getValueType(), null, "value", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ValueClass(), this.getValueClassType(), null, "valueClass", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_VariableResolver(), this.getVariableResolverType(), null, "variableResolver", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ViewHandler(), this.getViewHandlerType(), null, "viewHandler", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(dynamicAttributeEClass, DynamicAttribute.class, "DynamicAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDynamicAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, DynamicAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDynamicAttribute_Value(), ecorePackage.getEString(), "value", null, 0, 1, DynamicAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(dynamicElementEClass, DynamicElement.class, "DynamicElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDynamicElement_ChildNodes(), this.getDynamicElement(), null, "childNodes", null, 0, -1, DynamicElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDynamicElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, DynamicElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDynamicElement_Attributes(), this.getDynamicAttribute(), null, "attributes", null, 0, -1, DynamicElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getDynamicElement_TextContent(), ecorePackage.getEString(), "textContent", null, 0, 1, DynamicElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(facesConfigTypeEClass, FacesConfigType.class, "FacesConfigType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getFacesConfigType_Application(), this.getApplicationType(), null, "application", null, 0, -1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacesConfigType_Factory(), this.getFactoryType(), null, "factory", null, 0, -1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacesConfigType_Component(), this.getComponentType(), null, "component", null, 0, -1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacesConfigType_Converter(), this.getConverterType(), null, "converter", null, 0, -1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacesConfigType_ManagedBean(), this.getManagedBeanType(), null, "managedBean", null, 0, -1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacesConfigType_NavigationRule(), this.getNavigationRuleType(), null, "navigationRule", null, 0, -1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacesConfigType_ReferencedBean(), this.getReferencedBeanType(), null, "referencedBean", null, 0, -1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacesConfigType_RenderKit(), this.getRenderKitType(), null, "renderKit", null, 0, -1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacesConfigType_Lifecycle(), this.getLifecycleType(), null, "lifecycle", null, 0, -1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacesConfigType_Validator(), this.getValidatorType(), null, "validator", null, 0, -1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacesConfigType_FacesConfigExtension(), this.getFacesConfigExtensionType(), null, "facesConfigExtension", null, 0, -1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getFacesConfigType_Xmlns(), theXMLTypePackage.getString(), "xmlns", "http://java.sun.com/JSF/Configuration", 0, 1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getFacesConfigType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FacesConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(facesContextFactoryTypeEClass, FacesContextFactoryType.class, "FacesContextFactoryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFacesContextFactoryType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, FacesContextFactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getFacesContextFactoryType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FacesContextFactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(facetExtensionTypeEClass, FacetExtensionType.class, "FacetExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(facetNameTypeEClass, FacetNameType.class, "FacetNameType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFacetNameType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, FacetNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getFacetNameType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FacetNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(facetTypeEClass, FacetType.class, "FacetType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getFacetType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, FacetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacetType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, FacetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacetType_Icon(), this.getIconType(), null, "icon", null, 0, -1, FacetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacetType_FacetName(), this.getFacetNameType(), null, "facetName", null, 1, 1, FacetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFacetType_FacetExtension(), this.getFacetExtensionType(), null, "facetExtension", null, 0, -1, FacetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getFacetType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FacetType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(factoryTypeEClass, FactoryType.class, "FactoryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getFactoryType_ApplicationFactory(), this.getApplicationFactoryType(), null, "applicationFactory", null, 0, -1, FactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFactoryType_FacesContextFactory(), this.getFacesContextFactoryType(), null, "facesContextFactory", null, 0, -1, FactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFactoryType_LifecycleFactory(), this.getLifecycleFactoryType(), null, "lifecycleFactory", null, 0, -1, FactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFactoryType_RenderKitFactory(), this.getRenderKitFactoryType(), null, "renderKitFactory", null, 0, -1, FactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getFactoryType_FactoryExtension(), this.getFactoryExtensionType(), null, "factoryExtension", null, 0, -1, FactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getFactoryType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(fromActionTypeEClass, FromActionType.class, "FromActionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFromActionType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, FromActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getFromActionType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FromActionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(fromOutcomeTypeEClass, FromOutcomeType.class, "FromOutcomeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFromOutcomeType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, FromOutcomeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getFromOutcomeType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FromOutcomeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(fromViewIdTypeEClass, FromViewIdType.class, "FromViewIdType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFromViewIdType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, FromViewIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getFromViewIdType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, FromViewIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(iconTypeEClass, IconType.class, "IconType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getIconType_SmallIcon(), this.getSmallIconType(), null, "smallIcon", null, 0, 1, IconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getIconType_LargeIcon(), this.getLargeIconType(), null, "largeIcon", null, 0, 1, IconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getIconType_Lang(), theXMLTypePackage.getLanguage(), "lang", null, 0, 1, IconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getIconType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, IconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(keyClassTypeEClass, KeyClassType.class, "KeyClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getKeyClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, KeyClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getKeyClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, KeyClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(keyTypeEClass, KeyType.class, "KeyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getKeyType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, KeyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getKeyType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, KeyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(largeIconTypeEClass, LargeIconType.class, "LargeIconType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getLargeIconType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, LargeIconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getLargeIconType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, LargeIconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(lifecycleFactoryTypeEClass, LifecycleFactoryType.class, "LifecycleFactoryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getLifecycleFactoryType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, LifecycleFactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getLifecycleFactoryType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, LifecycleFactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(lifecycleTypeEClass, LifecycleType.class, "LifecycleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getLifecycleType_PhaseListener(), this.getPhaseListenerType(), null, "phaseListener", null, 0, -1, LifecycleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getLifecycleType_LifecycleExtension(), this.getLifecycleExtensionType(), null, "lifecycleExtension", null, 0, -1, LifecycleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getLifecycleType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, LifecycleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(listEntriesTypeEClass, ListEntriesType.class, "ListEntriesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getListEntriesType_ValueClass(), this.getValueClassType(), null, "valueClass", null, 0, 1, ListEntriesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getListEntriesType_NullValue(), this.getNullValueType(), null, "nullValue", null, 0, -1, ListEntriesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getListEntriesType_Value(), this.getValueType(), null, "value", null, 0, -1, ListEntriesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getListEntriesType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ListEntriesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(localeConfigTypeEClass, LocaleConfigType.class, "LocaleConfigType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getLocaleConfigType_DefaultLocale(), this.getDefaultLocaleType(), null, "defaultLocale", null, 0, 1, LocaleConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getLocaleConfigType_SupportedLocale(), this.getSupportedLocaleType(), null, "supportedLocale", null, 0, -1, LocaleConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getLocaleConfigType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, LocaleConfigType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(managedBeanClassTypeEClass, ManagedBeanClassType.class, "ManagedBeanClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getManagedBeanClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ManagedBeanClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getManagedBeanClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ManagedBeanClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(managedBeanNameTypeEClass, ManagedBeanNameType.class, "ManagedBeanNameType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getManagedBeanNameType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ManagedBeanNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getManagedBeanNameType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ManagedBeanNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(managedBeanScopeTypeEClass, ManagedBeanScopeType.class, "ManagedBeanScopeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getManagedBeanScopeType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ManagedBeanScopeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getManagedBeanScopeType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ManagedBeanScopeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(managedBeanTypeEClass, ManagedBeanType.class, "ManagedBeanType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getManagedBeanType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, ManagedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedBeanType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, ManagedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedBeanType_Icon(), this.getIconType(), null, "icon", null, 0, -1, ManagedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedBeanType_ManagedBeanName(), this.getManagedBeanNameType(), null, "managedBeanName", null, 1, 1, ManagedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedBeanType_ManagedBeanClass(), this.getManagedBeanClassType(), null, "managedBeanClass", null, 1, 1, ManagedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedBeanType_ManagedBeanScope(), this.getManagedBeanScopeType(), null, "managedBeanScope", null, 1, 1, ManagedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedBeanType_ManagedProperty(), this.getManagedPropertyType(), null, "managedProperty", null, 0, -1, ManagedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedBeanType_MapEntries(), this.getMapEntriesType(), null, "mapEntries", null, 0, 1, ManagedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedBeanType_ListEntries(), this.getListEntriesType(), null, "listEntries", null, 0, 1, ManagedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedBeanType_ManagedBeanExtension(), this.getManagedBeanExtensionType(), null, "managedBeanExtension", null, 0, -1, ManagedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getManagedBeanType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ManagedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(managedPropertyTypeEClass, ManagedPropertyType.class, "ManagedPropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getManagedPropertyType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, ManagedPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedPropertyType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, ManagedPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedPropertyType_Icon(), this.getIconType(), null, "icon", null, 0, -1, ManagedPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedPropertyType_PropertyName(), this.getPropertyNameType(), null, "propertyName", null, 1, 1, ManagedPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedPropertyType_PropertyClass(), this.getPropertyClassType(), null, "propertyClass", null, 0, 1, ManagedPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedPropertyType_MapEntries(), this.getMapEntriesType(), null, "mapEntries", null, 0, 1, ManagedPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedPropertyType_NullValue(), this.getNullValueType(), null, "nullValue", null, 0, 1, ManagedPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedPropertyType_Value(), this.getValueType(), null, "value", null, 0, 1, ManagedPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getManagedPropertyType_ListEntries(), this.getListEntriesType(), null, "listEntries", null, 0, 1, ManagedPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getManagedPropertyType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ManagedPropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(mapEntriesTypeEClass, MapEntriesType.class, "MapEntriesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getMapEntriesType_KeyClass(), this.getKeyClassType(), null, "keyClass", null, 0, 1, MapEntriesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMapEntriesType_ValueClass(), this.getValueClassType(), null, "valueClass", null, 0, 1, MapEntriesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMapEntriesType_MapEntry(), this.getMapEntryType(), null, "mapEntry", null, 0, -1, MapEntriesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getMapEntriesType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MapEntriesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(mapEntryTypeEClass, MapEntryType.class, "MapEntryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getMapEntryType_Key(), this.getKeyType(), null, "key", null, 1, 1, MapEntryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMapEntryType_NullValue(), this.getNullValueType(), null, "nullValue", null, 0, 1, MapEntryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getMapEntryType_Value(), this.getValueType(), null, "value", null, 0, 1, MapEntryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getMapEntryType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MapEntryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(messageBundleTypeEClass, MessageBundleType.class, "MessageBundleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getMessageBundleType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, MessageBundleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getMessageBundleType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, MessageBundleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(navigationCaseTypeEClass, NavigationCaseType.class, "NavigationCaseType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getNavigationCaseType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, NavigationCaseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNavigationCaseType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, NavigationCaseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNavigationCaseType_Icon(), this.getIconType(), null, "icon", null, 0, -1, NavigationCaseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNavigationCaseType_FromAction(), this.getFromActionType(), null, "fromAction", null, 0, 1, NavigationCaseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNavigationCaseType_FromOutcome(), this.getFromOutcomeType(), null, "fromOutcome", null, 0, 1, NavigationCaseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNavigationCaseType_ToViewId(), this.getToViewIdType(), null, "toViewId", null, 1, 1, NavigationCaseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNavigationCaseType_Redirect(), this.getRedirectType(), null, "redirect", null, 0, 1, NavigationCaseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getNavigationCaseType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, NavigationCaseType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(navigationHandlerTypeEClass, NavigationHandlerType.class, "NavigationHandlerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getNavigationHandlerType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, NavigationHandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getNavigationHandlerType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, NavigationHandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(navigationRuleTypeEClass, NavigationRuleType.class, "NavigationRuleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getNavigationRuleType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, NavigationRuleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNavigationRuleType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, NavigationRuleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNavigationRuleType_Icon(), this.getIconType(), null, "icon", null, 0, -1, NavigationRuleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNavigationRuleType_FromViewId(), this.getFromViewIdType(), null, "fromViewId", null, 0, 1, NavigationRuleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNavigationRuleType_NavigationCase(), this.getNavigationCaseType(), null, "navigationCase", null, 0, -1, NavigationRuleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getNavigationRuleType_NavigationRuleExtension(), this.getNavigationRuleExtensionType(), null, "navigationRuleExtension", null, 0, -1, NavigationRuleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getNavigationRuleType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, NavigationRuleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(nullValueTypeEClass, NullValueType.class, "NullValueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getNullValueType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, NullValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(phaseListenerTypeEClass, PhaseListenerType.class, "PhaseListenerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getPhaseListenerType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, PhaseListenerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getPhaseListenerType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, PhaseListenerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(propertyClassTypeEClass, PropertyClassType.class, "PropertyClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getPropertyClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, PropertyClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getPropertyClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, PropertyClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(propertyExtensionTypeEClass, PropertyExtensionType.class, "PropertyExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(propertyNameTypeEClass, PropertyNameType.class, "PropertyNameType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getPropertyNameType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, PropertyNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getPropertyNameType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, PropertyNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(propertyResolverTypeEClass, PropertyResolverType.class, "PropertyResolverType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getPropertyResolverType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, PropertyResolverType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getPropertyResolverType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, PropertyResolverType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(propertyTypeEClass, PropertyType.class, "PropertyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getPropertyType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getPropertyType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getPropertyType_Icon(), this.getIconType(), null, "icon", null, 0, -1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getPropertyType_PropertyName(), this.getPropertyNameType(), null, "propertyName", null, 1, 1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getPropertyType_PropertyClass(), this.getPropertyClassType(), null, "propertyClass", null, 1, 1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getPropertyType_DefaultValue(), this.getDefaultValueType(), null, "defaultValue", null, 0, 1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getPropertyType_SuggestedValue(), this.getSuggestedValueType(), null, "suggestedValue", null, 0, 1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getPropertyType_PropertyExtension(), this.getPropertyExtensionType(), null, "propertyExtension", null, 0, -1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getPropertyType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, PropertyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(redirectTypeEClass, RedirectType.class, "RedirectType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getRedirectType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RedirectType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(referencedBeanClassTypeEClass, ReferencedBeanClassType.class, "ReferencedBeanClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getReferencedBeanClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ReferencedBeanClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getReferencedBeanClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ReferencedBeanClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(referencedBeanNameTypeEClass, ReferencedBeanNameType.class, "ReferencedBeanNameType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getReferencedBeanNameType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ReferencedBeanNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getReferencedBeanNameType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ReferencedBeanNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(referencedBeanTypeEClass, ReferencedBeanType.class, "ReferencedBeanType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getReferencedBeanType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, ReferencedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getReferencedBeanType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, ReferencedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getReferencedBeanType_Icon(), this.getIconType(), null, "icon", null, 0, -1, ReferencedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getReferencedBeanType_ReferencedBeanName(), this.getReferencedBeanNameType(), null, "referencedBeanName", null, 1, 1, ReferencedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getReferencedBeanType_ReferencedBeanClass(), this.getReferencedBeanClassType(), null, "referencedBeanClass", null, 1, 1, ReferencedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getReferencedBeanType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ReferencedBeanType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(rendererClassTypeEClass, RendererClassType.class, "RendererClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getRendererClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, RendererClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getRendererClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RendererClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(rendererExtensionTypeEClass, RendererExtensionType.class, "RendererExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(rendererTypeEClass, RendererType.class, "RendererType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getRendererType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, RendererType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRendererType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, RendererType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRendererType_Icon(), this.getIconType(), null, "icon", null, 0, -1, RendererType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRendererType_ComponentFamily(), this.getComponentFamilyType(), null, "componentFamily", null, 1, 1, RendererType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRendererType_RendererType(), this.getRendererTypeType(), null, "rendererType", null, 1, 1, RendererType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRendererType_RendererClass(), this.getRendererClassType(), null, "rendererClass", null, 1, 1, RendererType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRendererType_Facet(), this.getFacetType(), null, "facet", null, 0, -1, RendererType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRendererType_Attribute(), this.getAttributeType(), null, "attribute", null, 0, -1, RendererType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRendererType_RendererExtension(), this.getRendererExtensionType(), null, "rendererExtension", null, 0, -1, RendererType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getRendererType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RendererType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(rendererTypeTypeEClass, RendererTypeType.class, "RendererTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getRendererTypeType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, RendererTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getRendererTypeType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RendererTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(renderKitClassTypeEClass, RenderKitClassType.class, "RenderKitClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getRenderKitClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, RenderKitClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getRenderKitClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RenderKitClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(renderKitFactoryTypeEClass, RenderKitFactoryType.class, "RenderKitFactoryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getRenderKitFactoryType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, RenderKitFactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getRenderKitFactoryType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RenderKitFactoryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(renderKitIdTypeEClass, RenderKitIdType.class, "RenderKitIdType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getRenderKitIdType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, RenderKitIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getRenderKitIdType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RenderKitIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(renderKitTypeEClass, RenderKitType.class, "RenderKitType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getRenderKitType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, RenderKitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRenderKitType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, RenderKitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRenderKitType_Icon(), this.getIconType(), null, "icon", null, 0, -1, RenderKitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRenderKitType_RenderKitId(), this.getRenderKitIdType(), null, "renderKitId", null, 0, 1, RenderKitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRenderKitType_RenderKitClass(), this.getRenderKitClassType(), null, "renderKitClass", null, 0, 1, RenderKitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRenderKitType_Renderer(), this.getRendererType(), null, "renderer", null, 0, -1, RenderKitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getRenderKitType_RenderKitExtension(), this.getRenderKitExtensionType(), null, "renderKitExtension", null, 0, -1, RenderKitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getRenderKitType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, RenderKitType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(smallIconTypeEClass, SmallIconType.class, "SmallIconType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSmallIconType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, SmallIconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getSmallIconType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, SmallIconType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(stateManagerTypeEClass, StateManagerType.class, "StateManagerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getStateManagerType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, StateManagerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getStateManagerType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, StateManagerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(suggestedValueTypeEClass, SuggestedValueType.class, "SuggestedValueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSuggestedValueType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, SuggestedValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getSuggestedValueType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, SuggestedValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(supportedLocaleTypeEClass, SupportedLocaleType.class, "SupportedLocaleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSupportedLocaleType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, SupportedLocaleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getSupportedLocaleType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, SupportedLocaleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(toViewIdTypeEClass, ToViewIdType.class, "ToViewIdType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getToViewIdType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ToViewIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getToViewIdType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ToViewIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(validatorClassTypeEClass, ValidatorClassType.class, "ValidatorClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getValidatorClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ValidatorClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getValidatorClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ValidatorClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(validatorIdTypeEClass, ValidatorIdType.class, "ValidatorIdType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getValidatorIdType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ValidatorIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getValidatorIdType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ValidatorIdType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(validatorTypeEClass, ValidatorType.class, "ValidatorType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getValidatorType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, ValidatorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getValidatorType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, ValidatorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getValidatorType_Icon(), this.getIconType(), null, "icon", null, 0, -1, ValidatorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getValidatorType_ValidatorId(), this.getValidatorIdType(), null, "validatorId", null, 1, 1, ValidatorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getValidatorType_ValidatorClass(), this.getValidatorClassType(), null, "validatorClass", null, 1, 1, ValidatorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getValidatorType_Attribute(), this.getAttributeType(), null, "attribute", null, 0, -1, ValidatorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getValidatorType_Property(), this.getPropertyType(), null, "property", null, 0, -1, ValidatorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getValidatorType_ValidatorExtension(), this.getValidatorExtensionType(), null, "validatorExtension", null, 0, -1, ValidatorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getValidatorType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ValidatorType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(valueClassTypeEClass, ValueClassType.class, "ValueClassType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getValueClassType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ValueClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getValueClassType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ValueClassType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(valueTypeEClass, ValueType.class, "ValueType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getValueType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getValueType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ValueType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(variableResolverTypeEClass, VariableResolverType.class, "VariableResolverType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getVariableResolverType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, VariableResolverType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getVariableResolverType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, VariableResolverType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(viewHandlerTypeEClass, ViewHandlerType.class, "ViewHandlerType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getViewHandlerType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ViewHandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getViewHandlerType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ViewHandlerType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(extensionTypeEClass, ExtensionType.class, "ExtensionType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getExtensionType_ChildNodes(), this.getDynamicElement(), null, "childNodes", null, 0, -1, ExtensionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getExtensionType_TextContent(), ecorePackage.getEString(), "textContent", null, 0, 1, ExtensionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getExtensionType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ExtensionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(applicationExtensionTypeEClass, ApplicationExtensionType.class, "ApplicationExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(converterExtensionTypeEClass, ConverterExtensionType.class, "ConverterExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(elResolverTypeEClass, ELResolverType.class, "ELResolverType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getELResolverType_TextContent(), theXMLTypePackage.getString(), "textContent", null, 0, 1, ELResolverType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getELResolverType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ELResolverType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(facesConfigExtensionTypeEClass, FacesConfigExtensionType.class, "FacesConfigExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(factoryExtensionTypeEClass, FactoryExtensionType.class, "FactoryExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(lifecycleExtensionTypeEClass, LifecycleExtensionType.class, "LifecycleExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(managedBeanExtensionTypeEClass, ManagedBeanExtensionType.class, "ManagedBeanExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(navigationRuleExtensionTypeEClass, NavigationRuleExtensionType.class, "NavigationRuleExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(validatorExtensionTypeEClass, ValidatorExtensionType.class, "ValidatorExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(resourceBundleTypeEClass, ResourceBundleType.class, "ResourceBundleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getResourceBundleType_Description(), this.getDescriptionType(), null, "description", null, 0, -1, ResourceBundleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getResourceBundleType_DisplayName(), this.getDisplayNameType(), null, "displayName", null, 0, -1, ResourceBundleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getResourceBundleType_Icon(), this.getIconType(), null, "icon", null, 0, -1, ResourceBundleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getResourceBundleType_BaseName(), this.getBaseNameType(), null, "baseName", null, 0, 1, ResourceBundleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getResourceBundleType_Var(), this.getVarType(), null, "var", null, 0, 1, ResourceBundleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getResourceBundleType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, ResourceBundleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(baseNameTypeEClass, BaseNameType.class, "BaseNameType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getBaseNameType_TextContent(), ecorePackage.getEString(), "textContent", null, 0, 1, BaseNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getBaseNameType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, BaseNameType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(varTypeEClass, VarType.class, "VarType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getVarType_TextContent(), ecorePackage.getEString(), "textContent", null, 0, 1, VarType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getVarType_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, VarType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(renderKitExtensionTypeEClass, RenderKitExtensionType.class, "RenderKitExtensionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

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
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";			 //$NON-NLS-1$
        addAnnotation
          (this, 
           source, 
           new String[] {
             "qualified", "false" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (actionListenerTypeEClass, 
           source, 
           new String[] {
             "name", "action-listener_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getActionListenerType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getActionListenerType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (applicationFactoryTypeEClass, 
           source, 
           new String[] {
             "name", "application-factory_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationFactoryType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationFactoryType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (applicationTypeEClass, 
           source, 
           new String[] {
             "name", "application_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_ActionListener(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "action-listener", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_DefaultRenderKitId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "default-render-kit-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_MessageBundle(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "message-bundle", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_NavigationHandler(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "navigation-handler", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_ViewHandler(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "view-handler", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_StateManager(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "state-manager", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_PropertyResolver(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property-resolver", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_VariableResolver(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "variable-resolver", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_LocaleConfig(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "locale-config", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_ELResolver(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "el-resolver", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_ResourceBundle(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "resource-bundle", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getApplicationType_ApplicationExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "application-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (applicationExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "application-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (attributeClassTypeEClass, 
           source, 
           new String[] {
             "name", "attribute-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (attributeExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "attribute-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (attributeNameTypeEClass, 
           source, 
           new String[] {
             "name", "attribute-name_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeNameType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeNameType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (attributeTypeEClass, 
           source, 
           new String[] {
             "name", "attribute_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeType_AttributeName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "attribute-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeType_AttributeClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "attribute-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeType_DefaultValue(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "default-value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeType_SuggestedValue(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "suggested-value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeType_AttributeExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "attribute-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getAttributeType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (componentClassTypeEClass, 
           source, 
           new String[] {
             "name", "component-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (componentExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "component-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (componentFamilyTypeEClass, 
           source, 
           new String[] {
             "name", "component-family_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentFamilyType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentFamilyType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (componentTypeEClass, 
           source, 
           new String[] {
             "name", "component_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentType_ComponentType(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "component-type", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentType_ComponentClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "component-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentType_Facet(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "facet", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentType_Attribute(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentType_Property(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentType_ComponentExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "component-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (componentTypeTypeEClass, 
           source, 
           new String[] {
             "name", "component-type_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentTypeType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getComponentTypeType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (converterClassTypeEClass, 
           source, 
           new String[] {
             "name", "converter-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (converterForClassTypeEClass, 
           source, 
           new String[] {
             "name", "converter-for-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterForClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterForClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (converterIdTypeEClass, 
           source, 
           new String[] {
             "name", "converter-id_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterIdType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterIdType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (converterTypeEClass, 
           source, 
           new String[] {
             "name", "converter_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterType_ConverterId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "converter-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterType_ConverterForClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "converter-for-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterType_ConverterClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "converter-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterType_Attribute(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterType_Property(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterType_ConverterExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "converter-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getConverterType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (converterExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "converter-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (defaultLocaleTypeEClass, 
           source, 
           new String[] {
             "name", "default-locale_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDefaultLocaleType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDefaultLocaleType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (defaultRenderKitIdTypeEClass, 
           source, 
           new String[] {
             "name", "default-render-kit-id_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDefaultRenderKitIdType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDefaultRenderKitIdType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (defaultValueTypeEClass, 
           source, 
           new String[] {
             "name", "default-value_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDefaultValueType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDefaultValueType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (descriptionTypeEClass, 
           source, 
           new String[] {
             "name", "description_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDescriptionType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDescriptionType_Lang(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "lang", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "http://www.w3.org/XML/1998/namespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDescriptionType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (displayNameTypeEClass, 
           source, 
           new String[] {
             "name", "display-name_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDisplayNameType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDisplayNameType_Lang(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "lang", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "http://www.w3.org/XML/1998/namespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDisplayNameType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (documentRootEClass, 
           source, 
           new String[] {
             "name", "", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_Mixed(), 
           source, 
           new String[] {
             "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
             "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_XMLNSPrefixMap(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "xmlns:prefix" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getDocumentRoot_XSISchemaLocation(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "xsi:schemaLocation" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ActionListener(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "action-listener", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Application(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "application", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ApplicationFactory(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "application-factory", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Attribute(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_AttributeClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "attribute-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_AttributeExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "attribute-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_AttributeName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "attribute-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Component(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "component", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ComponentClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "component-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ComponentExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "component-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ComponentFamily(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "component-family", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ComponentType(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "component-type", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Converter(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "converter", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ConverterClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "converter-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ConverterForClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "converter-for-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ConverterId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "converter-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_DefaultLocale(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "default-locale", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_DefaultRenderKitId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "default-render-kit-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_DefaultValue(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "default-value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_FacesConfig(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "faces-config", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_FacesContextFactory(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "faces-context-factory", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Facet(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "facet", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_FacetExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "facet-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_FacetName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "facet-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Factory(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "factory", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_FromAction(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "from-action", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_FromOutcome(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "from-outcome", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_FromViewId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "from-view-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Key(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "key", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_KeyClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "key-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_LargeIcon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "large-icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Lifecycle(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "lifecycle", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_LifecycleFactory(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "lifecycle-factory", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ListEntries(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "list-entries", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_LocaleConfig(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "locale-config", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ManagedBean(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "managed-bean", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ManagedBeanClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "managed-bean-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ManagedBeanName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "managed-bean-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ManagedBeanScope(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "managed-bean-scope", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ManagedProperty(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "managed-property", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_MapEntries(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "map-entries", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_MapEntry(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "map-entry", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_MessageBundle(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "message-bundle", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_NavigationCase(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "navigation-case", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_NavigationHandler(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "navigation-handler", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_NavigationRule(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "navigation-rule", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_NullValue(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "null-value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_PhaseListener(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "phase-listener", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Property(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_PropertyClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_PropertyExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_PropertyName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_PropertyResolver(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property-resolver", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Redirect(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "redirect", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ReferencedBean(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "referenced-bean", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ReferencedBeanClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "referenced-bean-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ReferencedBeanName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "referenced-bean-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Renderer(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "renderer", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_RendererClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "renderer-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_RendererExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "renderer-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_RendererType(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "renderer-type", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_RenderKit(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "render-kit", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_RenderKitClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "render-kit-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_RenderKitFactory(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "render-kit-factory", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_RenderKitId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "render-kit-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_SmallIcon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "small-icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_StateManager(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "state-manager", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_SuggestedValue(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "suggested-value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_SupportedLocale(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "supported-locale", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ToViewId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "to-view-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Validator(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "validator", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ValidatorClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "validator-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ValidatorId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "validator-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_Value(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ValueClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "value-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_VariableResolver(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "variable-resolver", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });			
        addAnnotation
          (getDocumentRoot_ViewHandler(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "view-handler", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (elResolverTypeEClass, 
           source, 
           new String[] {
             "name", "el-resolver_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getELResolverType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getELResolverType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getExtensionType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getExtensionType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (facesConfigTypeEClass, 
           source, 
           new String[] {
             "name", "faces-config_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_Application(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "application", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_Factory(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "factory", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_Component(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "component", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_Converter(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "converter", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_ManagedBean(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "managed-bean", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_NavigationRule(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "navigation-rule", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_ReferencedBean(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "referenced-bean", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_RenderKit(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "render-kit", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_Lifecycle(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "lifecycle", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_Validator(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "validator", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_FacesConfigExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "faces-config-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_Xmlns(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "xmlns", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesConfigType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (facesConfigExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "faces-config-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (facesContextFactoryTypeEClass, 
           source, 
           new String[] {
             "name", "faces-context-factory_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesContextFactoryType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacesContextFactoryType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (facetExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "facet-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (facetNameTypeEClass, 
           source, 
           new String[] {
             "name", "facet-name_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacetNameType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacetNameType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (facetTypeEClass, 
           source, 
           new String[] {
             "name", "facet_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacetType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacetType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacetType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacetType_FacetName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "facet-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacetType_FacetExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "facet-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFacetType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (factoryTypeEClass, 
           source, 
           new String[] {
             "name", "factory_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFactoryType_ApplicationFactory(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "application-factory", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFactoryType_FacesContextFactory(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "faces-context-factory", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFactoryType_LifecycleFactory(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "lifecycle-factory", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFactoryType_RenderKitFactory(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "render-kit-factory", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFactoryType_FactoryExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "factory-type", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFactoryType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (factoryExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "factory-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (fromActionTypeEClass, 
           source, 
           new String[] {
             "name", "from-action_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFromActionType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFromActionType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (fromOutcomeTypeEClass, 
           source, 
           new String[] {
             "name", "from-outcome_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFromOutcomeType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFromOutcomeType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (fromViewIdTypeEClass, 
           source, 
           new String[] {
             "name", "from-view-id_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFromViewIdType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getFromViewIdType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (iconTypeEClass, 
           source, 
           new String[] {
             "name", "icon_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getIconType_SmallIcon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "small-icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getIconType_LargeIcon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "large-icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getIconType_Lang(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "lang", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "http://www.w3.org/XML/1998/namespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getIconType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (keyClassTypeEClass, 
           source, 
           new String[] {
             "name", "key-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getKeyClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getKeyClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (keyTypeEClass, 
           source, 
           new String[] {
             "name", "key_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getKeyType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getKeyType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (largeIconTypeEClass, 
           source, 
           new String[] {
             "name", "large-icon_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLargeIconType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLargeIconType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (lifecycleFactoryTypeEClass, 
           source, 
           new String[] {
             "name", "lifecycle-factory_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLifecycleFactoryType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLifecycleFactoryType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (lifecycleTypeEClass, 
           source, 
           new String[] {
             "name", "lifecycle_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLifecycleType_PhaseListener(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "phase-listener", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLifecycleType_LifecycleExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "lifecycle-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLifecycleType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (lifecycleExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "lifecycle-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (listEntriesTypeEClass, 
           source, 
           new String[] {
             "name", "list-entries_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getListEntriesType_ValueClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "value-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getListEntriesType_NullValue(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "null-value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getListEntriesType_Value(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getListEntriesType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (localeConfigTypeEClass, 
           source, 
           new String[] {
             "name", "locale-config_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLocaleConfigType_DefaultLocale(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "default-locale", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLocaleConfigType_SupportedLocale(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "supported-locale", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getLocaleConfigType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (managedBeanClassTypeEClass, 
           source, 
           new String[] {
             "name", "managed-bean-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (managedBeanNameTypeEClass, 
           source, 
           new String[] {
             "name", "managed-bean-name_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanNameType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanNameType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (managedBeanScopeTypeEClass, 
           source, 
           new String[] {
             "name", "managed-bean-scope_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanScopeType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanScopeType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (managedBeanTypeEClass, 
           source, 
           new String[] {
             "name", "managed-bean_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanType_ManagedBeanName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "managed-bean-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanType_ManagedBeanClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "managed-bean-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanType_ManagedBeanScope(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "managed-bean-scope", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanType_ManagedProperty(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "managed-property", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanType_MapEntries(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "map-entries", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanType_ListEntries(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "list-entries", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanType_ManagedBeanExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "managed-bean-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedBeanType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (managedBeanExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "managed-bean-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (managedPropertyTypeEClass, 
           source, 
           new String[] {
             "name", "managed-property_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedPropertyType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedPropertyType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedPropertyType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedPropertyType_PropertyName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedPropertyType_PropertyClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedPropertyType_MapEntries(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "map-entries", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedPropertyType_NullValue(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "null-value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedPropertyType_Value(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedPropertyType_ListEntries(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "list-entries", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getManagedPropertyType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (mapEntriesTypeEClass, 
           source, 
           new String[] {
             "name", "map-entries_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMapEntriesType_KeyClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "key-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMapEntriesType_ValueClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "value-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMapEntriesType_MapEntry(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "map-entry", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMapEntriesType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (mapEntryTypeEClass, 
           source, 
           new String[] {
             "name", "map-entry_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMapEntryType_Key(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "key", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMapEntryType_NullValue(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "null-value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMapEntryType_Value(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMapEntryType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (messageBundleTypeEClass, 
           source, 
           new String[] {
             "name", "message-bundle_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMessageBundleType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getMessageBundleType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (navigationCaseTypeEClass, 
           source, 
           new String[] {
             "name", "navigation-case_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationCaseType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationCaseType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationCaseType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationCaseType_FromAction(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "from-action", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationCaseType_FromOutcome(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "from-outcome", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationCaseType_ToViewId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "to-view-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationCaseType_Redirect(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "redirect", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationCaseType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (navigationHandlerTypeEClass, 
           source, 
           new String[] {
             "name", "navigation-handler_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationHandlerType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationHandlerType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (navigationRuleTypeEClass, 
           source, 
           new String[] {
             "name", "navigation-rule_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationRuleType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationRuleType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationRuleType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationRuleType_FromViewId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "from-view-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationRuleType_NavigationCase(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "navigation-case", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationRuleType_NavigationRuleExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "navigation-rule-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNavigationRuleType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (navigationRuleExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "navigation-rule-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (nullValueTypeEClass, 
           source, 
           new String[] {
             "name", "null-value_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getNullValueType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (phaseListenerTypeEClass, 
           source, 
           new String[] {
             "name", "phase-listener_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPhaseListenerType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPhaseListenerType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (propertyClassTypeEClass, 
           source, 
           new String[] {
             "name", "property-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (propertyExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "property-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (propertyNameTypeEClass, 
           source, 
           new String[] {
             "name", "property-name_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyNameType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyNameType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (propertyResolverTypeEClass, 
           source, 
           new String[] {
             "name", "property-resolver_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyResolverType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyResolverType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (propertyTypeEClass, 
           source, 
           new String[] {
             "name", "property_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyType_PropertyName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyType_PropertyClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyType_DefaultValue(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "default-value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyType_SuggestedValue(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "suggested-value", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyType_PropertyExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getPropertyType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (redirectTypeEClass, 
           source, 
           new String[] {
             "name", "redirect_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRedirectType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (referencedBeanClassTypeEClass, 
           source, 
           new String[] {
             "name", "referenced-bean-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getReferencedBeanClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getReferencedBeanClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (referencedBeanNameTypeEClass, 
           source, 
           new String[] {
             "name", "referenced-bean-name_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getReferencedBeanNameType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getReferencedBeanNameType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (referencedBeanTypeEClass, 
           source, 
           new String[] {
             "name", "referenced-bean_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getReferencedBeanType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getReferencedBeanType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getReferencedBeanType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getReferencedBeanType_ReferencedBeanName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "referenced-bean-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getReferencedBeanType_ReferencedBeanClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "referenced-bean-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getReferencedBeanType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (rendererClassTypeEClass, 
           source, 
           new String[] {
             "name", "renderer-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (rendererExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "renderer-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (rendererTypeEClass, 
           source, 
           new String[] {
             "name", "renderer_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererType_ComponentFamily(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "component-family", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererType_RendererType(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "renderer-type", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererType_RendererClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "renderer-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererType_Facet(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "facet", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererType_Attribute(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererType_RendererExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "renderer-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (rendererTypeTypeEClass, 
           source, 
           new String[] {
             "name", "renderer-type_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererTypeType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRendererTypeType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (renderKitClassTypeEClass, 
           source, 
           new String[] {
             "name", "render-kit-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (renderKitFactoryTypeEClass, 
           source, 
           new String[] {
             "name", "render-kit-factory_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitFactoryType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitFactoryType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (renderKitIdTypeEClass, 
           source, 
           new String[] {
             "name", "render-kit-id_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitIdType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitIdType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (renderKitTypeEClass, 
           source, 
           new String[] {
             "name", "render-kit_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitType_RenderKitId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "render-kit-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitType_RenderKitClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "render-kit-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitType_Renderer(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "renderer", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitType_RenderKitExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "render-kit-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getRenderKitType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (renderKitExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "render-kit-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (smallIconTypeEClass, 
           source, 
           new String[] {
             "name", "small-icon_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getSmallIconType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getSmallIconType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (stateManagerTypeEClass, 
           source, 
           new String[] {
             "name", "state-manager_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getStateManagerType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getStateManagerType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (suggestedValueTypeEClass, 
           source, 
           new String[] {
             "name", "suggested-value_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getSuggestedValueType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getSuggestedValueType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (supportedLocaleTypeEClass, 
           source, 
           new String[] {
             "name", "supported-locale_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getSupportedLocaleType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getSupportedLocaleType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (toViewIdTypeEClass, 
           source, 
           new String[] {
             "name", "to-view-id_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getToViewIdType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getToViewIdType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (validatorClassTypeEClass, 
           source, 
           new String[] {
             "name", "validator-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (validatorIdTypeEClass, 
           source, 
           new String[] {
             "name", "validator-id_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorIdType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorIdType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (validatorTypeEClass, 
           source, 
           new String[] {
             "name", "validator_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorType_ValidatorId(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "validator-id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorType_ValidatorClass(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "validator-class", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorType_Attribute(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorType_Property(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "property", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorType_ValidatorExtension(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "validator-extension", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValidatorType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (validatorExtensionTypeEClass, 
           source, 
           new String[] {
             "name", "validator-extension_._type" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (valueClassTypeEClass, 
           source, 
           new String[] {
             "name", "value-class_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValueClassType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValueClassType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (valueTypeEClass, 
           source, 
           new String[] {
             "name", "value_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValueType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getValueType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (variableResolverTypeEClass, 
           source, 
           new String[] {
             "name", "variable-resolver_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getVariableResolverType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getVariableResolverType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (viewHandlerTypeEClass, 
           source, 
           new String[] {
             "name", "view-handler_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getViewHandlerType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getViewHandlerType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (resourceBundleTypeEClass, 
           source, 
           new String[] {
             "name", "resourceBundle_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getResourceBundleType_Description(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "description", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getResourceBundleType_DisplayName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "display-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getResourceBundleType_Icon(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "icon", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getResourceBundleType_BaseName(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "base-name", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getResourceBundleType_Var(), 
           source, 
           new String[] {
             "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "var", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getResourceBundleType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (baseNameTypeEClass, 
           source, 
           new String[] {
             "name", "base-name_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getBaseNameType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getBaseNameType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (varTypeEClass, 
           source, 
           new String[] {
             "name", "var_._type", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getVarType_TextContent(), 
           source, 
           new String[] {
             "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
             "kind", "simple" //$NON-NLS-1$ //$NON-NLS-2$
           });		
        addAnnotation
          (getVarType_Id(), 
           source, 
           new String[] {
             "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
             "name", "id", //$NON-NLS-1$ //$NON-NLS-2$
             "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });
    }

} //FacesConfigPackageImpl
