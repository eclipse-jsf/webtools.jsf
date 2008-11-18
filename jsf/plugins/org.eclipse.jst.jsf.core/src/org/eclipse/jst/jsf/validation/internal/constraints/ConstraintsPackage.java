/**
 * <copyright>
 * </copyright>
 *
 * $Id: ConstraintsPackage.java,v 1.5 2008/11/18 22:24:09 gkessler Exp $
 */
package org.eclipse.jst.jsf.validation.internal.constraints;

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
 * @see org.eclipse.jst.jsf.validation.internal.constraints.ConstraintsFactory
 * @model kind="package"
 * @generated
 */
@SuppressWarnings("hiding")
public interface ConstraintsPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "constraints"; //$NON-NLS-1$

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://org.eclipse.jst.jsf.core/constraints.ecore"; //$NON-NLS-1$

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "cnst"; //$NON-NLS-1$

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ConstraintsPackage eINSTANCE = org.eclipse.jst.jsf.validation.internal.constraints.impl.ConstraintsPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.validation.internal.constraints.impl.TagIdImpl <em>Tag Id</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.TagIdImpl
     * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.ConstraintsPackageImpl#getTagId()
     * @generated
     */
    int TAG_ID = 0;

    /**
     * The feature id for the '<em><b>Uri</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_ID__URI = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_ID__NAME = 1;

    /**
     * The number of structural features of the '<em>Tag Id</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_ID_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.validation.internal.constraints.impl.TagSetImpl <em>Tag Set</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.TagSetImpl
     * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.ConstraintsPackageImpl#getTagSet()
     * @generated
     */
    int TAG_SET = 1;

    /**
     * The feature id for the '<em><b>Tags</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_SET__TAGS = 0;

    /**
     * The number of structural features of the '<em>Tag Set</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TAG_SET_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.eclipse.jst.jsf.validation.internal.constraints.impl.ContainsTagConstraintImpl <em>Contains Tag Constraint</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.ContainsTagConstraintImpl
     * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.ConstraintsPackageImpl#getContainsTagConstraint()
     * @generated
     */
    int CONTAINS_TAG_CONSTRAINT = 2;

    /**
     * The feature id for the '<em><b>Set Generator</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINS_TAG_CONSTRAINT__SET_GENERATOR = 0;

    /**
     * The feature id for the '<em><b>Satisfies Set</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINS_TAG_CONSTRAINT__SATISFIES_SET = 1;

    /**
     * The number of structural features of the '<em>Contains Tag Constraint</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINS_TAG_CONSTRAINT_FEATURE_COUNT = 2;


    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.validation.internal.constraints.TagId <em>Tag Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Tag Id</em>'.
     * @see org.eclipse.jst.jsf.validation.internal.constraints.TagId
     * @generated
     */
    EClass getTagId();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.validation.internal.constraints.TagId#getUri <em>Uri</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Uri</em>'.
     * @see org.eclipse.jst.jsf.validation.internal.constraints.TagId#getUri()
     * @see #getTagId()
     * @generated
     */
    EAttribute getTagId_Uri();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.validation.internal.constraints.TagId#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.jst.jsf.validation.internal.constraints.TagId#getName()
     * @see #getTagId()
     * @generated
     */
    EAttribute getTagId_Name();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.validation.internal.constraints.TagSet <em>Tag Set</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Tag Set</em>'.
     * @see org.eclipse.jst.jsf.validation.internal.constraints.TagSet
     * @generated
     */
    EClass getTagSet();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.validation.internal.constraints.TagSet#getTags <em>Tags</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Tags</em>'.
     * @see org.eclipse.jst.jsf.validation.internal.constraints.TagSet#getTags()
     * @see #getTagSet()
     * @generated
     */
    EReference getTagSet_Tags();

    /**
     * Returns the meta object for class '{@link org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint <em>Contains Tag Constraint</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Contains Tag Constraint</em>'.
     * @see org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint
     * @generated
     */
    EClass getContainsTagConstraint();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint#getSetGenerator <em>Set Generator</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Set Generator</em>'.
     * @see org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint#getSetGenerator()
     * @see #getContainsTagConstraint()
     * @generated
     */
    EReference getContainsTagConstraint_SetGenerator();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint#getSatisfiesSet <em>Satisfies Set</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Satisfies Set</em>'.
     * @see org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint#getSatisfiesSet()
     * @see #getContainsTagConstraint()
     * @generated
     */
    EReference getContainsTagConstraint_SatisfiesSet();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ConstraintsFactory getConstraintsFactory();

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
    interface Literals {
        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.validation.internal.constraints.impl.TagIdImpl <em>Tag Id</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.TagIdImpl
         * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.ConstraintsPackageImpl#getTagId()
         * @generated
         */
 //       @SuppressWarnings("hiding")
        EClass TAG_ID = eINSTANCE.getTagId();

        /**
         * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
 //       @SuppressWarnings("hiding")
        EAttribute TAG_ID__URI = eINSTANCE.getTagId_Uri();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
 //       @SuppressWarnings("hiding")
        EAttribute TAG_ID__NAME = eINSTANCE.getTagId_Name();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.validation.internal.constraints.impl.TagSetImpl <em>Tag Set</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.TagSetImpl
         * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.ConstraintsPackageImpl#getTagSet()
         * @generated
         */
 //       @SuppressWarnings("hiding")
        EClass TAG_SET = eINSTANCE.getTagSet();

        /**
         * The meta object literal for the '<em><b>Tags</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
 //       @SuppressWarnings("hiding")
        EReference TAG_SET__TAGS = eINSTANCE.getTagSet_Tags();

        /**
         * The meta object literal for the '{@link org.eclipse.jst.jsf.validation.internal.constraints.impl.ContainsTagConstraintImpl <em>Contains Tag Constraint</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.ContainsTagConstraintImpl
         * @see org.eclipse.jst.jsf.validation.internal.constraints.impl.ConstraintsPackageImpl#getContainsTagConstraint()
         * @generated
         */
 //       @SuppressWarnings("hiding")
        EClass CONTAINS_TAG_CONSTRAINT = eINSTANCE.getContainsTagConstraint();

        /**
         * The meta object literal for the '<em><b>Set Generator</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
 //       @SuppressWarnings("hiding")
        EReference CONTAINS_TAG_CONSTRAINT__SET_GENERATOR = eINSTANCE.getContainsTagConstraint_SetGenerator();

        /**
         * The meta object literal for the '<em><b>Satisfies Set</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
 //       @SuppressWarnings("hiding")
        EReference CONTAINS_TAG_CONSTRAINT__SATISFIES_SET = eINSTANCE.getContainsTagConstraint_SatisfiesSet();

    }

} //ConstraintsPackage
