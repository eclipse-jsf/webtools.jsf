/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory
 * @generated
 */
public interface PageflowPackage extends EPackage {
	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "pageflow";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.sybase.com/suade/pageflow";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "pageflow";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	PageflowPackage eINSTANCE = org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowElementImpl <em>Element</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowElementImpl
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPackageImpl#getPageflowElement()
	 * @generated
	 */
	int PAGEFLOW_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_ELEMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_ELEMENT__COMMENT = 1;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_ELEMENT__X = 2;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_ELEMENT__Y = 3;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_ELEMENT__WIDTH = 4;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_ELEMENT__HEIGHT = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_ELEMENT__ID = 6;

	/**
	 * The feature id for the '<em><b>Reference Link</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_ELEMENT__REFERENCE_LINK = 7;

	/**
	 * The number of structural features of the the '<em>Element</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_ELEMENT_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowImpl <em>Pageflow</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowImpl
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPackageImpl#getPageflow()
	 * @generated
	 */
	int PAGEFLOW = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW__NAME = PAGEFLOW_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW__COMMENT = PAGEFLOW_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW__X = PAGEFLOW_ELEMENT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW__Y = PAGEFLOW_ELEMENT__Y;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW__WIDTH = PAGEFLOW_ELEMENT__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW__HEIGHT = PAGEFLOW_ELEMENT__HEIGHT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW__ID = PAGEFLOW_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Reference Link</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW__REFERENCE_LINK = PAGEFLOW_ELEMENT__REFERENCE_LINK;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW__NODES = PAGEFLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Links</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW__LINKS = PAGEFLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Configfile</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW__CONFIGFILE = PAGEFLOW_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>Pageflow</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_FEATURE_COUNT = PAGEFLOW_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowNodeImpl <em>Node</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowNodeImpl
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPackageImpl#getPageflowNode()
	 * @generated
	 */
	int PAGEFLOW_NODE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE__NAME = PAGEFLOW_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE__COMMENT = PAGEFLOW_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE__X = PAGEFLOW_ELEMENT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE__Y = PAGEFLOW_ELEMENT__Y;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE__WIDTH = PAGEFLOW_ELEMENT__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE__HEIGHT = PAGEFLOW_ELEMENT__HEIGHT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE__ID = PAGEFLOW_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Reference Link</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE__REFERENCE_LINK = PAGEFLOW_ELEMENT__REFERENCE_LINK;

	/**
	 * The feature id for the '<em><b>Pageflow</b></em>' container
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE__PAGEFLOW = PAGEFLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outlinks</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE__OUTLINKS = PAGEFLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Inlinks</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE__INLINKS = PAGEFLOW_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>Node</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PAGEFLOW_NODE_FEATURE_COUNT = PAGEFLOW_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkImpl <em>PF Link</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkImpl
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPackageImpl#getPFLink()
	 * @generated
	 */
	int PF_LINK = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__NAME = PAGEFLOW_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__COMMENT = PAGEFLOW_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__X = PAGEFLOW_ELEMENT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__Y = PAGEFLOW_ELEMENT__Y;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__WIDTH = PAGEFLOW_ELEMENT__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__HEIGHT = PAGEFLOW_ELEMENT__HEIGHT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__ID = PAGEFLOW_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Reference Link</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__REFERENCE_LINK = PAGEFLOW_ELEMENT__REFERENCE_LINK;

	/**
	 * The feature id for the '<em><b>Pageflow</b></em>' container
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__PAGEFLOW = PAGEFLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__TARGET = PAGEFLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__SOURCE = PAGEFLOW_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Outcome</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__OUTCOME = PAGEFLOW_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Redirect</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__REDIRECT = PAGEFLOW_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Bend Points</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__BEND_POINTS = PAGEFLOW_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Smallicon</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__SMALLICON = PAGEFLOW_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Largeicon</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__LARGEICON = PAGEFLOW_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Fromaction</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK__FROMACTION = PAGEFLOW_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the the '<em>PF Link</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK_FEATURE_COUNT = PAGEFLOW_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPageImpl <em>PF Page</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPageImpl
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPackageImpl#getPFPage()
	 * @generated
	 */
	int PF_PAGE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__NAME = PAGEFLOW_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__COMMENT = PAGEFLOW_NODE__COMMENT;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__X = PAGEFLOW_NODE__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__Y = PAGEFLOW_NODE__Y;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__WIDTH = PAGEFLOW_NODE__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__HEIGHT = PAGEFLOW_NODE__HEIGHT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__ID = PAGEFLOW_NODE__ID;

	/**
	 * The feature id for the '<em><b>Pageflow</b></em>' container
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__PAGEFLOW = PAGEFLOW_NODE__PAGEFLOW;

	/**
	 * The feature id for the '<em><b>Outlinks</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__OUTLINKS = PAGEFLOW_NODE__OUTLINKS;

	/**
	 * The feature id for the '<em><b>Inlinks</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__INLINKS = PAGEFLOW_NODE__INLINKS;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__PATH = PAGEFLOW_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Smallicon</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__SMALLICON = PAGEFLOW_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Largeicon</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE__LARGEICON = PAGEFLOW_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>PF Page</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_PAGE_FEATURE_COUNT = PAGEFLOW_NODE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkBendpointImpl <em>PF Link Bendpoint</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowLinkBendpointImpl
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPackageImpl#getPFLinkBendpoint()
	 * @generated
	 */
	int PF_LINK_BENDPOINT = 8;

	/**
	 * The feature id for the '<em><b>D1 Width</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK_BENDPOINT__D1_WIDTH = 0;

	/**
	 * The feature id for the '<em><b>D1 Height</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK_BENDPOINT__D1_HEIGHT = 1;

	/**
	 * The feature id for the '<em><b>D2 Width</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK_BENDPOINT__D2_WIDTH = 2;

	/**
	 * The feature id for the '<em><b>D2 Height</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK_BENDPOINT__D2_HEIGHT = 3;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK_BENDPOINT__WEIGHT = 4;

	/**
	 * The feature id for the '<em><b>Link</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK_BENDPOINT__LINK = 5;

	/**
	 * The number of structural features of the the '<em>PF Link Bendpoint</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PF_LINK_BENDPOINT_FEATURE_COUNT = 6;

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow <em>Pageflow</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Pageflow</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow
	 * @generated
	 */
	EClass getPageflow();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getNodes()
	 * @see #getPageflow()
	 * @generated
	 */
	EReference getPageflow_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Links</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getLinks()
	 * @see #getPageflow()
	 * @generated
	 */
	EReference getPageflow_Links();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getConfigfile <em>Configfile</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Configfile</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow#getConfigfile()
	 * @see #getPageflow()
	 * @generated
	 */
	EAttribute getPageflow_Configfile();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode <em>Node</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Node</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode
	 * @generated
	 */
	EClass getPageflowNode();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getPageflow <em>Pageflow</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Pageflow</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getPageflow()
	 * @see #getPageflowNode()
	 * @generated
	 */
	EReference getPageflowNode_Pageflow();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getOutlinks <em>Outlinks</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Outlinks</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getOutlinks()
	 * @see #getPageflowNode()
	 * @generated
	 */
	EReference getPageflowNode_Outlinks();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getInlinks <em>Inlinks</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Inlinks</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode#getInlinks()
	 * @see #getPageflowNode()
	 * @generated
	 */
	EReference getPageflowNode_Inlinks();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink <em>PF Link</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>PF Link</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink
	 * @generated
	 */
	EClass getPFLink();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getPageflow <em>Pageflow</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Pageflow</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getPageflow()
	 * @see #getPFLink()
	 * @generated
	 */
	EReference getPFLink_Pageflow();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getTarget()
	 * @see #getPFLink()
	 * @generated
	 */
	EReference getPFLink_Target();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getSource()
	 * @see #getPFLink()
	 * @generated
	 */
	EReference getPFLink_Source();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getOutcome <em>Outcome</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Outcome</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getOutcome()
	 * @see #getPFLink()
	 * @generated
	 */
	EAttribute getPFLink_Outcome();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#isRedirect <em>Redirect</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Redirect</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#isRedirect()
	 * @see #getPFLink()
	 * @generated
	 */
	EAttribute getPFLink_Redirect();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getBendPoints <em>Bend Points</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Bend Points</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink#getBendPoints()
	 * @see #getPFLink()
	 * @generated
	 */
	EReference getPFLink_BendPoints();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement <em>Element</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement
	 * @generated
	 */
	EClass getPageflowElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getName()
	 * @see #getPageflowElement()
	 * @generated
	 */
	EAttribute getPageflowElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getComment()
	 * @see #getPageflowElement()
	 * @generated
	 */
	EAttribute getPageflowElement_Comment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getX <em>X</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getX()
	 * @see #getPageflowElement()
	 * @generated
	 */
	EAttribute getPageflowElement_X();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getY <em>Y</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getY()
	 * @see #getPageflowElement()
	 * @generated
	 */
	EAttribute getPageflowElement_Y();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getWidth()
	 * @see #getPageflowElement()
	 * @generated
	 */
	EAttribute getPageflowElement_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getHeight()
	 * @see #getPageflowElement()
	 * @generated
	 */
	EAttribute getPageflowElement_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement#getId()
	 * @see #getPageflowElement()
	 * @generated
	 */
	EAttribute getPageflowElement_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage <em>PF Page</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>PF Page</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage
	 * @generated
	 */
	EClass getPFPage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getPath()
	 * @see #getPFPage()
	 * @generated
	 */
	EAttribute getPFPage_Path();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getSmallicon <em>Smallicon</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Smallicon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getSmallicon()
	 * @see #getPFPage()
	 * @generated
	 */
	EAttribute getPFPage_Smallicon();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getLargeicon <em>Largeicon</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Largeicon</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage#getLargeicon()
	 * @see #getPFPage()
	 * @generated
	 */
	EAttribute getPFPage_Largeicon();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint <em>PF Link Bendpoint</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>PF Link Bendpoint</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint
	 * @generated
	 */
	EClass getPFLinkBendpoint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getD1Width <em>D1 Width</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>D1 Width</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getD1Width()
	 * @see #getPFLinkBendpoint()
	 * @generated
	 */
	EAttribute getPFLinkBendpoint_D1Width();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getD1Height <em>D1 Height</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>D1 Height</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getD1Height()
	 * @see #getPFLinkBendpoint()
	 * @generated
	 */
	EAttribute getPFLinkBendpoint_D1Height();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getD2Width <em>D2 Width</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>D2 Width</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getD2Width()
	 * @see #getPFLinkBendpoint()
	 * @generated
	 */
	EAttribute getPFLinkBendpoint_D2Width();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getD2Height <em>D2 Height</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>D2 Height</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getD2Height()
	 * @see #getPFLinkBendpoint()
	 * @generated
	 */
	EAttribute getPFLinkBendpoint_D2Height();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getWeight()
	 * @see #getPFLinkBendpoint()
	 * @generated
	 */
	EAttribute getPFLinkBendpoint_Weight();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Link</em>'.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint#getLink()
	 * @see #getPFLinkBendpoint()
	 * @generated
	 */
	EReference getPFLinkBendpoint_Link();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PageflowFactory getPageflowFactory();

}
// PageflowPackage
