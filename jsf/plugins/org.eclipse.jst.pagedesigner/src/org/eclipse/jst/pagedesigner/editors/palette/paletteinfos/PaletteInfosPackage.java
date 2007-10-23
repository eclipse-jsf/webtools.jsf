/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.jst.pagedesigner.editors.palette.paletteinfos;

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
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfosFactory
 * @model kind="package"
 * @generated
 */
public interface PaletteInfosPackage extends EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "paletteinfos";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.eclipse.jsf.pagedesigner/paletteInfos.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "pi";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PaletteInfosPackage eINSTANCE = org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfoImpl <em>Palette Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfoImpl
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosPackageImpl#getPaletteInfo()
	 * @generated
	 */
	int PALETTE_INFO = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosImpl <em>Palette Infos</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosImpl
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosPackageImpl#getPaletteInfos()
	 * @generated
	 */
	int PALETTE_INFOS = 0;

	/**
	 * The feature id for the '<em><b>Infos</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFOS__INFOS = 0;

	/**
	 * The number of structural features of the '<em>Palette Infos</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFOS_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFO__ID = 0;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFO__TAG = 1;

	/**
	 * The feature id for the '<em><b>Display Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFO__DISPLAY_LABEL = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFO__DESCRIPTION = 3;

	/**
	 * The feature id for the '<em><b>Expert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFO__EXPERT = 4;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFO__HIDDEN = 5;

	/**
	 * The feature id for the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFO__SMALL_ICON = 6;

	/**
	 * The feature id for the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFO__LARGE_ICON = 7;

	/**
	 * The feature id for the '<em><b>Tag Creation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFO__TAG_CREATION = 8;

	/**
	 * The number of structural features of the '<em>Palette Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PALETTE_INFO_FEATURE_COUNT = 9;


	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationInfoImpl <em>Tag Creation Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationInfoImpl
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosPackageImpl#getTagCreationInfo()
	 * @generated
	 */
	int TAG_CREATION_INFO = 2;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CREATION_INFO__ATTRIBUTES = 0;

	/**
	 * The feature id for the '<em><b>Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CREATION_INFO__TEMPLATE = 1;

	/**
	 * The number of structural features of the '<em>Tag Creation Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CREATION_INFO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationTemplateImpl <em>Tag Creation Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationTemplateImpl
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosPackageImpl#getTagCreationTemplate()
	 * @generated
	 */
	int TAG_CREATION_TEMPLATE = 3;

	/**
	 * The feature id for the '<em><b>Template</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CREATION_TEMPLATE__TEMPLATE = 0;

	/**
	 * The number of structural features of the '<em>Tag Creation Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CREATION_TEMPLATE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationAttributeImpl <em>Tag Creation Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationAttributeImpl
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosPackageImpl#getTagCreationAttribute()
	 * @generated
	 */
	int TAG_CREATION_ATTRIBUTE = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CREATION_ATTRIBUTE__ID = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CREATION_ATTRIBUTE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Tag Creation Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_CREATION_ATTRIBUTE_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo <em>Palette Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Palette Info</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo
	 * @generated
	 */
	EClass getPaletteInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getId()
	 * @see #getPaletteInfo()
	 * @generated
	 */
	EAttribute getPaletteInfo_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getTag()
	 * @see #getPaletteInfo()
	 * @generated
	 */
	EAttribute getPaletteInfo_Tag();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getDisplayLabel <em>Display Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Label</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getDisplayLabel()
	 * @see #getPaletteInfo()
	 * @generated
	 */
	EAttribute getPaletteInfo_DisplayLabel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getDescription()
	 * @see #getPaletteInfo()
	 * @generated
	 */
	EAttribute getPaletteInfo_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getExpert <em>Expert</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expert</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getExpert()
	 * @see #getPaletteInfo()
	 * @generated
	 */
	EAttribute getPaletteInfo_Expert();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getHidden()
	 * @see #getPaletteInfo()
	 * @generated
	 */
	EAttribute getPaletteInfo_Hidden();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getSmallIcon <em>Small Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Small Icon</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getSmallIcon()
	 * @see #getPaletteInfo()
	 * @generated
	 */
	EAttribute getPaletteInfo_SmallIcon();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getLargeIcon <em>Large Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Large Icon</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getLargeIcon()
	 * @see #getPaletteInfo()
	 * @generated
	 */
	EAttribute getPaletteInfo_LargeIcon();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getTagCreation <em>Tag Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Tag Creation</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfo#getTagCreation()
	 * @see #getPaletteInfo()
	 * @generated
	 */
	EReference getPaletteInfo_TagCreation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationInfo <em>Tag Creation Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Creation Info</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationInfo
	 * @generated
	 */
	EClass getTagCreationInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationInfo#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationInfo#getTemplate()
	 * @see #getTagCreationInfo()
	 * @generated
	 */
	EAttribute getTagCreationInfo_Template();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationInfo#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationInfo#getAttributes()
	 * @see #getTagCreationInfo()
	 * @generated
	 */
	EReference getTagCreationInfo_Attributes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationTemplate <em>Tag Creation Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Creation Template</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationTemplate
	 * @generated
	 */
	EClass getTagCreationTemplate();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationTemplate#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Template</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationTemplate#getTemplate()
	 * @see #getTagCreationTemplate()
	 * @generated
	 */
	EReference getTagCreationTemplate_Template();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationAttribute <em>Tag Creation Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Creation Attribute</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationAttribute
	 * @generated
	 */
	EClass getTagCreationAttribute();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationAttribute#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationAttribute#getId()
	 * @see #getTagCreationAttribute()
	 * @generated
	 */
	EAttribute getTagCreationAttribute_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationAttribute#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.TagCreationAttribute#getValue()
	 * @see #getTagCreationAttribute()
	 * @generated
	 */
	EAttribute getTagCreationAttribute_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfos <em>Palette Infos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Palette Infos</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfos
	 * @generated
	 */
	EClass getPaletteInfos();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfos#getInfos <em>Infos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Infos</em>'.
	 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.PaletteInfos#getInfos()
	 * @see #getPaletteInfos()
	 * @generated
	 */
	EReference getPaletteInfos_Infos();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PaletteInfosFactory getPaletteInfosFactory();

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
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfoImpl <em>Palette Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfoImpl
		 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosPackageImpl#getPaletteInfo()
		 * @generated
		 */
		EClass PALETTE_INFO = eINSTANCE.getPaletteInfo();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PALETTE_INFO__ID = eINSTANCE.getPaletteInfo_Id();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE_INFO__TAG = eINSTANCE.getPaletteInfo_Tag();

		/**
		 * The meta object literal for the '<em><b>Display Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE_INFO__DISPLAY_LABEL = eINSTANCE.getPaletteInfo_DisplayLabel();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE_INFO__DESCRIPTION = eINSTANCE.getPaletteInfo_Description();

		/**
		 * The meta object literal for the '<em><b>Expert</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE_INFO__EXPERT = eINSTANCE.getPaletteInfo_Expert();

		/**
		 * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE_INFO__HIDDEN = eINSTANCE.getPaletteInfo_Hidden();

		/**
		 * The meta object literal for the '<em><b>Small Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE_INFO__SMALL_ICON = eINSTANCE.getPaletteInfo_SmallIcon();

		/**
		 * The meta object literal for the '<em><b>Large Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PALETTE_INFO__LARGE_ICON = eINSTANCE.getPaletteInfo_LargeIcon();

		/**
		 * The meta object literal for the '<em><b>Tag Creation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PALETTE_INFO__TAG_CREATION = eINSTANCE.getPaletteInfo_TagCreation();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationInfoImpl <em>Tag Creation Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationInfoImpl
		 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosPackageImpl#getTagCreationInfo()
		 * @generated
		 */
		EClass TAG_CREATION_INFO = eINSTANCE.getTagCreationInfo();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_CREATION_INFO__TEMPLATE = eINSTANCE.getTagCreationInfo_Template();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_CREATION_INFO__ATTRIBUTES = eINSTANCE.getTagCreationInfo_Attributes();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationTemplateImpl <em>Tag Creation Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationTemplateImpl
		 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosPackageImpl#getTagCreationTemplate()
		 * @generated
		 */
		EClass TAG_CREATION_TEMPLATE = eINSTANCE.getTagCreationTemplate();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_CREATION_TEMPLATE__TEMPLATE = eINSTANCE.getTagCreationTemplate_Template();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationAttributeImpl <em>Tag Creation Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.TagCreationAttributeImpl
		 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosPackageImpl#getTagCreationAttribute()
		 * @generated
		 */
		EClass TAG_CREATION_ATTRIBUTE = eINSTANCE.getTagCreationAttribute();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_CREATION_ATTRIBUTE__ID = eINSTANCE.getTagCreationAttribute_Id();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_CREATION_ATTRIBUTE__VALUE = eINSTANCE.getTagCreationAttribute_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosImpl <em>Palette Infos</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosImpl
		 * @see org.eclipse.jst.pagedesigner.editors.palette.paletteinfos.internal.impl.PaletteInfosPackageImpl#getPaletteInfos()
		 * @generated
		 */
		EClass PALETTE_INFOS = eINSTANCE.getPaletteInfos();

		/**
		 * The meta object literal for the '<em><b>Infos</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PALETTE_INFOS__INFOS = eINSTANCE.getPaletteInfos_Infos();

	}

} //PaletteInfosPackage
