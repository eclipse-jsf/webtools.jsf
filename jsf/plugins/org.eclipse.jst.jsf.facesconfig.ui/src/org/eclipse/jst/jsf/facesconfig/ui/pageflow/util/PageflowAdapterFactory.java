/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It
 * provides an adapter <code>createXXX</code> method for each class of the
 * model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage
 * @generated
 */
// FIXME: this appears to be a dead class.
public class PageflowAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static PageflowPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public PageflowAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PageflowPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code>
	 * if the object is either the model's package or is an instance object of
	 * the model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PageflowSwitch modelSwitch = new PageflowSwitch() {
		public Object casePageflow(Pageflow object) {
			return createPageflowAdapter();
		}

		public Object casePageflowNode(PageflowNode object) {
			return createPageflowNodeAdapter();
		}

		public Object casePFLink(PageflowLink object) {
			return createPFLinkAdapter();
		}

		public Object casePageflowElement(PageflowElement object) {
			return createPageflowElementAdapter();
		}

		public Object casePFPage(PageflowPage object) {
			return createPFPageAdapter();
		}

		public Object casePFLinkBendpoint(PageflowLinkBendpoint object) {
			return createPFLinkBendpointAdapter();
		}

		public Object defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter) modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow <em>Pageflow</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow
	 * @generated
	 */
	public Adapter createPageflowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode <em>Node</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode
	 * @generated
	 */
	public Adapter createPageflowNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink <em>PF Link</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink
	 * @generated
	 */
	public Adapter createPFLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement <em>Element</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement
	 * @generated
	 */
	public Adapter createPageflowElementAdapter() {
		return null;
	}

	/**
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createPFActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage <em>PF Page</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage
	 * @generated
	 */
	public Adapter createPFPageAdapter() {
		return null;
	}

	/**
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createPFBeginAdapter() {
		return null;
	}

	/**
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createPFEndAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint <em>PF Link Bendpoint</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLinkBendpoint
	 * @generated
	 */
	public Adapter createPFLinkBendpointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

}
// PageflowAdapterFactory
