/**
 * Copyright (c) 2007 Oracle Corporation
 *
 * $Id: PaletteInfo.java,v 1.3 2008/11/18 22:23:54 gkessler Exp $
 */
package org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Palette Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getTag <em>Tag</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getDisplayLabel <em>Display Label</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getExpert <em>Expert</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getHidden <em>Hidden</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getTagCreation <em>Tag Creation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfo()
 * @model
 * @generated
 */
public interface PaletteInfo extends EObject {
	/**
	 * the trait id name
	 */
	public static final String TRAIT_ID				= "paletteInfo"; //$NON-NLS-1$
	/**
	 * the display label name
	 */
	public static final String TRAIT_DISPLAY_LABEL 	= "display-label"; //$NON-NLS-1$
	/**
	 * the trait description name
	 */
	public static final String TRAIT_DESCRIPTION 	= "description"; //$NON-NLS-1$
	/**
	 * the expert trait
	 */
	public static final String TRAIT_IS_EXPERT 		= "expert"; //$NON-NLS-1$
	/**
	 * the hiddent trait
	 */
	public static final String TRAIT_IS_HIDDEN 		= "hidden"; //$NON-NLS-1$
	/**
	 * the small icon trait
	 */
	public static final String TRAIT_SMALL_ICON 	= "small-icon"; //$NON-NLS-1$
	/**
	 * the large icon trait
	 */
	public static final String TRAIT_LARGE_ICON 	= "large-icon"; //$NON-NLS-1$

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
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfo_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag</em>' attribute.
	 * @see #setTag(String)
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfo_Tag()
	 * @model
	 * @generated
	 */
	String getTag();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getTag <em>Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag</em>' attribute.
	 * @see #getTag()
	 * @generated
	 */
	void setTag(String value);

	/**
	 * Returns the value of the '<em><b>Display Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Label</em>' attribute.
	 * @see #setDisplayLabel(String)
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfo_DisplayLabel()
	 * @model extendedMetaData="kind='element' name='display-label'"
	 * @generated
	 */
	String getDisplayLabel();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getDisplayLabel <em>Display Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Label</em>' attribute.
	 * @see #getDisplayLabel()
	 * @generated
	 */
	void setDisplayLabel(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfo_Description()
	 * @model extendedMetaData="kind='element'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Expert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expert</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expert</em>' attribute.
	 * @see #setExpert(Boolean)
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfo_Expert()
	 * @model extendedMetaData="kind='element'"
	 * @generated
	 */
	Boolean getExpert();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getExpert <em>Expert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expert</em>' attribute.
	 * @see #getExpert()
	 * @generated
	 */
	void setExpert(Boolean value);

	/**
	 * Returns the value of the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hidden</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hidden</em>' attribute.
	 * @see #setHidden(Boolean)
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfo_Hidden()
	 * @model extendedMetaData="kind='element'"
	 * @generated
	 */
	Boolean getHidden();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getHidden <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hidden</em>' attribute.
	 * @see #getHidden()
	 * @generated
	 */
	void setHidden(Boolean value);

	/**
	 * Returns the value of the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Small Icon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Small Icon</em>' attribute.
	 * @see #setSmallIcon(String)
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfo_SmallIcon()
	 * @model extendedMetaData="kind='element' name='small-icon'"
	 * @generated
	 */
	String getSmallIcon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getSmallIcon <em>Small Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Small Icon</em>' attribute.
	 * @see #getSmallIcon()
	 * @generated
	 */
	void setSmallIcon(String value);

	/**
	 * Returns the value of the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Large Icon</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Large Icon</em>' attribute.
	 * @see #setLargeIcon(String)
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfo_LargeIcon()
	 * @model extendedMetaData="kind='element' name='large-icon'"
	 * @generated
	 */
	String getLargeIcon();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getLargeIcon <em>Large Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Large Icon</em>' attribute.
	 * @see #getLargeIcon()
	 * @generated
	 */
	void setLargeIcon(String value);

	/**
	 * Returns the value of the '<em><b>Tag Creation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Creation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Creation</em>' reference.
	 * @see #setTagCreation(TagCreationInfo)
	 * @see org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfosPackage#getPaletteInfo_TagCreation()
	 * @model extendedMetaData="kind='element' name='tag-create'"
	 * @generated
	 */
	TagCreationInfo getTagCreation();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo#getTagCreation <em>Tag Creation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag Creation</em>' reference.
	 * @see #getTagCreation()
	 * @generated
	 */
	void setTagCreation(TagCreationInfo value);

	/**
	 * <!-- begin-user-doc -->
     * @return true if is expert
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isExpert();

	/**
	 * <!-- begin-user-doc -->
     * @return true if is hidden 
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isHidden();

} // PaletteInfo
