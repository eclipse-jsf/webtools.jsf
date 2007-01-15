/**
 * <copyright>
 * </copyright>
 *
 * $Id: Entity.java,v 1.1 2007/01/15 23:26:14 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.provisional;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IEntityVisitor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getChildEntities <em>Child Entities</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getTraits <em>Traits</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity()
 * @model extendedMetaData="kind='element' name='entity'"
 * @generated
 */
public interface Entity extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation";

	/**
	 * Returns the value of the '<em><b>Child Entities</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Entities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Entities</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity_ChildEntities()
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getParent
	 * @model type="org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity" opposite="parent" containment="true"
	 *        extendedMetaData="kind='element' name='entity'"
	 * @generated
	 */
	EList getChildEntities();

	/**
	 * Returns the value of the '<em><b>Traits</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traits</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traits</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity_Traits()
	 * @model type="org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait" containment="true"
	 *        extendedMetaData="kind='element' name='trait'"
	 * @generated
	 */
	EList getTraits();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getChildEntities <em>Child Entities</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(Entity)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity_Parent()
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getChildEntities
	 * @model opposite="childEntities"
	 * @generated
	 */
	Entity getParent();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Entity value);

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
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage#getEntity_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model visitorDataType="org.eclipse.jst.jsf.common.metadata.internal.provisional.IEntityVisitor"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if (visitor.stopVisiting())\r\n\treturn;\r\nvisitor.visit(this);\r\n\r\nif (!getChildEntities().isEmpty()){\r\n\tfor (Iterator/*<Entity>\052/ it = getChildEntities().iterator(); it.hasNext();){\r\n\t\tEntity k = (Entity)it.next();\r\n\t\tk.accept(visitor);\r\n\t\tif (visitor.stopVisiting())\r\n\t\t\treturn;\r\n\t}\r\n}\r\nvisitor.visitCompleted();'"
	 * @generated
	 */
	void accept(IEntityVisitor visitor);

} // Entity
