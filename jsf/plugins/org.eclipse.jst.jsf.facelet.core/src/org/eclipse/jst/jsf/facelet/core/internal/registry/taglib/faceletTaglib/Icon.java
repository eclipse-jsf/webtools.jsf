/**
 * <copyright>
 * </copyright>
 *
 * $Id: Icon.java,v 1.1 2010/03/18 06:24:29 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Icon</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *     The icon type contains small-icon and large-icon elements
 *     that specify the file names for small and large GIF, JPEG,
 *     or PNG icon images used to represent the parent element in a
 *     GUI tool.
 * 
 *     The xml:lang attribute defines the language that the
 *     icon file names are provided in. Its value is "en" (English)
 *     by default.
 * 
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getLang <em>Lang</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getIcon()
 * @model extendedMetaData="name='iconType' kind='elementOnly'"
 * @generated
 */
public interface Icon extends EObject
{
    /**
     * Returns the value of the '<em><b>Small Icon</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     *         <![CDATA[
     * 
     *           The small-icon element contains the name of a file
     *           containing a small (16 x 16) icon image. The file
     *           name is a relative path within the Deployment
     *           Component's Deployment File.
     * 
     *           The image may be in the GIF, JPEG, or PNG format.
     *           The icon can be used by tools.
     * 
     *           Example:
     * 
     *           <small-icon>employee-service-icon16x16.jpg</small-icon>
     * 
     *           ]]>
     *       
     * <!-- end-model-doc -->
     * @return the value of the '<em>Small Icon</em>' containment reference.
     * @see #setSmallIcon(Path)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getIcon_SmallIcon()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='small-icon' namespace='##targetNamespace'"
     * @generated
     */
    Path getSmallIcon();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getSmallIcon <em>Small Icon</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Small Icon</em>' containment reference.
     * @see #getSmallIcon()
     * @generated
     */
    void setSmallIcon(Path value);

    /**
     * Returns the value of the '<em><b>Large Icon</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     *         <![CDATA[
     * 
     *           The large-icon element contains the name of a file
     *           containing a large
     *           (32 x 32) icon image. The file name is a relative
     *           path within the Deployment Component's Deployment
     *           File.
     * 
     *           The image may be in the GIF, JPEG, or PNG format.
     *           The icon can be used by tools.
     * 
     *           Example:
     * 
     *           <large-icon>employee-service-icon32x32.jpg</large-icon>
     * 
     *           ]]>
     *       
     * <!-- end-model-doc -->
     * @return the value of the '<em>Large Icon</em>' containment reference.
     * @see #setLargeIcon(Path)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getIcon_LargeIcon()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='large-icon' namespace='##targetNamespace'"
     * @generated
     */
    Path getLargeIcon();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getLargeIcon <em>Large Icon</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Large Icon</em>' containment reference.
     * @see #getLargeIcon()
     * @generated
     */
    void setLargeIcon(Path value);

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
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getIcon_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Lang</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     *     <div xmlns="http://www.w3.org/1999/xhtml">
     *      
     *       <h3>lang (as an attribute name)</h3>
     *       <p>
     *        denotes an attribute whose value
     *        is a language code for the natural language of the content of
     *        any element; its value is inherited.  This name is reserved
     *        by virtue of its definition in the XML specification.</p>
     *      
     *     </div>
     *     <div xmlns="http://www.w3.org/1999/xhtml">
     *      <h4>Notes</h4>
     *      <p>
     *       Attempting to install the relevant ISO 2- and 3-letter
     *       codes as the enumerated possible values is probably never
     *       going to be a realistic possibility.  
     *      </p>
     *      <p>
     *       See BCP 47 at <a href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt">
     *        http://www.rfc-editor.org/rfc/bcp/bcp47.txt</a>
     *       and the IANA language subtag registry at
     *       <a href="http://www.iana.org/assignments/language-subtag-registry">
     *        http://www.iana.org/assignments/language-subtag-registry</a>
     *       for further information.
     *      </p>
     *      <p>
     *       The union allows for the 'un-declaration' of xml:lang with
     *       the empty string.
     *      </p>
     *     </div>
     *    
     * <!-- end-model-doc -->
     * @return the value of the '<em>Lang</em>' attribute.
     * @see #setLang(String)
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibPackage#getIcon_Lang()
     * @model extendedMetaData="kind='attribute' name='lang' namespace='http://www.w3.org/XML/1998/namespace'"
     * @generated
     */
    String getLang();

    /**
     * Sets the value of the '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.Icon#getLang <em>Lang</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lang</em>' attribute.
     * @see #getLang()
     * @generated
     */
    void setLang(String value);

} // Icon
