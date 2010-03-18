/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibTag.java,v 1.1 2010/03/18 06:24:28 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facelet Taglib Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *                 If the tag library XML file contains individual tag
 *                 declarations rather than pointing to a library-class or a
 *                 declaring a composite-library name, the individual tags are
 *                 enclosed in tag elements.
 *             
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getTagNameElement <em>Tag Name Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getHandlerClassElement <em>Handler Class Element</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getComponent <em>Component</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getConverter <em>Converter</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getValidator <em>Validator</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getTagExtension <em>Tag Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getTagName <em>Tag Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getHandlerClass <em>Handler Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag()
 * @model extendedMetaData="name='facelet-taglib-tagType' kind='elementOnly'"
 * @generated
 */
public interface FaceletTaglibTag extends UserVisibleTaglibObject
{
    /**
     * Returns the value of the '<em><b>Tag Name Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tag Name Element</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tag Name Element</em>' containment reference.
     * @see #setTagNameElement(FaceletTaglibCanonicalName)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag_TagNameElement()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='tag-name' namespace='##targetNamespace'"
     * @generated
     */
    FaceletTaglibCanonicalName getTagNameElement();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getTagNameElement <em>Tag Name Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tag Name Element</em>' containment reference.
     * @see #getTagNameElement()
     * @generated
     */
    void setTagNameElement(FaceletTaglibCanonicalName value);

    /**
     * Returns the value of the '<em><b>Handler Class Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Handler Class Element</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Handler Class Element</em>' containment reference.
     * @see #setHandlerClassElement(FullyQualifiedClass)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag_HandlerClassElement()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='handler-class' namespace='##targetNamespace'"
     * @generated
     */
    FullyQualifiedClass getHandlerClassElement();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getHandlerClassElement <em>Handler Class Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Handler Class Element</em>' containment reference.
     * @see #getHandlerClassElement()
     * @generated
     */
    void setHandlerClassElement(FullyQualifiedClass value);

    /**
     * Returns the value of the '<em><b>Tag Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tag Name</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tag Name</em>' attribute.
     * @see #setTagName(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag_TagName()
     * @model transient="true" volatile="true"
     * @generated
     */
    String getTagName();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getTagName <em>Tag Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Tag Name</em>' attribute.
     * @see #getTagName()
     * @generated
     */
    void setTagName(String value);

    /**
     * Returns the value of the '<em><b>Handler Class</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Handle Class</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Handler Class</em>' attribute.
     * @see #setHandlerClass(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag_HandlerClass()
     * @model transient="true" volatile="true"
     * @generated
     */
    String getHandlerClass();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getHandlerClass <em>Handler Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Handler Class</em>' attribute.
     * @see #getHandlerClass()
     * @generated
     */
    void setHandlerClass(String value);

    /**
     * Returns the value of the '<em><b>Behavior</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Behavior</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Behavior</em>' containment reference.
     * @see #setBehavior(FaceletTaglibTagBehavior)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag_Behavior()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='behavior' namespace='##targetNamespace'"
     * @generated
     */
    FaceletTaglibTagBehavior getBehavior();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getBehavior <em>Behavior</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Behavior</em>' containment reference.
     * @see #getBehavior()
     * @generated
     */
    void setBehavior(FaceletTaglibTagBehavior value);

    /**
     * Returns the value of the '<em><b>Component</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Component</em>' containment reference.
     * @see #setComponent(FaceletTaglibTagComponent)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag_Component()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='component' namespace='##targetNamespace'"
     * @generated
     */
    FaceletTaglibTagComponent getComponent();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getComponent <em>Component</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Component</em>' containment reference.
     * @see #getComponent()
     * @generated
     */
    void setComponent(FaceletTaglibTagComponent value);

    /**
     * Returns the value of the '<em><b>Converter</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Converter</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Converter</em>' containment reference.
     * @see #setConverter(FaceletTaglibTagConverter)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag_Converter()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='converter' namespace='##targetNamespace'"
     * @generated
     */
    FaceletTaglibTagConverter getConverter();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getConverter <em>Converter</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Converter</em>' containment reference.
     * @see #getConverter()
     * @generated
     */
    void setConverter(FaceletTaglibTagConverter value);

    /**
     * Returns the value of the '<em><b>Validator</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Validator</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Validator</em>' containment reference.
     * @see #setValidator(FaceletTaglibTagValidator)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag_Validator()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='validator' namespace='##targetNamespace'"
     * @generated
     */
    FaceletTaglibTagValidator getValidator();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getValidator <em>Validator</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Validator</em>' containment reference.
     * @see #getValidator()
     * @generated
     */
    void setValidator(FaceletTaglibTagValidator value);

    /**
     * Returns the value of the '<em><b>Source</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' containment reference.
     * @see #setSource(IdentifiableStringValue)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag_Source()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='source' namespace='##targetNamespace'"
     * @generated
     */
    IdentifiableStringValue getSource();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag#getSource <em>Source</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' containment reference.
     * @see #getSource()
     * @generated
     */
    void setSource(IdentifiableStringValue value);

    /**
     * Returns the value of the '<em><b>Attribute</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagAttribute}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Attribute</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attribute</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag_Attribute()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='attribute' namespace='##targetNamespace'"
     * @generated
     */
    EList<FaceletTaglibTagAttribute> getAttribute();

    /**
     * Returns the value of the '<em><b>Tag Extension</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTagExtension}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tag Extension</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Tag Extension</em>' containment reference list.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getFaceletTaglibTag_TagExtension()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='tag-extension' namespace='##targetNamespace'"
     * @generated
     */
    EList<FaceletTaglibTagExtension> getTagExtension();

} // FaceletTaglibTag
