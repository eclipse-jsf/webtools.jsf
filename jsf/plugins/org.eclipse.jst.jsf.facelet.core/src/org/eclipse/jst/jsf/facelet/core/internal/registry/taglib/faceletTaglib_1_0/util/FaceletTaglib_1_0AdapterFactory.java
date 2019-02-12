/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglib_1_0AdapterFactory.java,v 1.1 2010/03/18 06:24:40 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ComponentTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ConverterTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletLibraryClassTagLib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglibDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletXMLDefnTaglib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.HandlerTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.SourceTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.TagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ValidatorTagDefn;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package
 * @generated
 */
public class FaceletTaglib_1_0AdapterFactory extends AdapterFactoryImpl
{
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static FaceletTaglib_1_0Package modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglib_1_0AdapterFactory()
    {
        if (modelPackage == null)
        {
            modelPackage = FaceletTaglib_1_0Package.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object)
    {
        if (object == modelPackage)
        {
            return true;
        }
        if (object instanceof EObject)
        {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FaceletTaglib_1_0Switch<Adapter> modelSwitch =
        new FaceletTaglib_1_0Switch<Adapter>()
        {
            @Override
            public Adapter caseFaceletLibraryClassTagLib(FaceletLibraryClassTagLib object)
            {
                return createFaceletLibraryClassTagLibAdapter();
            }
            @Override
            public Adapter caseFaceletXMLDefnTaglib(FaceletXMLDefnTaglib object)
            {
                return createFaceletXMLDefnTaglibAdapter();
            }
            @Override
            public Adapter caseFaceletTaglibDefn(FaceletTaglibDefn object)
            {
                return createFaceletTaglibDefnAdapter();
            }
            @Override
            public Adapter caseComponentTagDefn(ComponentTagDefn object)
            {
                return createComponentTagDefnAdapter();
            }
            @Override
            public Adapter caseValidatorTagDefn(ValidatorTagDefn object)
            {
                return createValidatorTagDefnAdapter();
            }
            @Override
            public Adapter caseConverterTagDefn(ConverterTagDefn object)
            {
                return createConverterTagDefnAdapter();
            }
            @Override
            public Adapter caseHandlerTagDefn(HandlerTagDefn object)
            {
                return createHandlerTagDefnAdapter();
            }
            @Override
            public Adapter caseSourceTagDefn(SourceTagDefn object)
            {
                return createSourceTagDefnAdapter();
            }
            @Override
            public Adapter caseTagDefn(TagDefn object)
            {
                return createTagDefnAdapter();
            }
            @Override
            public Adapter caseFunctionDefn(FunctionDefn object)
            {
                return createFunctionDefnAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object)
            {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target)
    {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletLibraryClassTagLib <em>Facelet Library Class Tag Lib</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletLibraryClassTagLib
     * @generated
     */
    public Adapter createFaceletLibraryClassTagLibAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletXMLDefnTaglib <em>Facelet XML Defn Taglib</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletXMLDefnTaglib
     * @generated
     */
    public Adapter createFaceletXMLDefnTaglibAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglibDefn <em>Facelet Taglib Defn</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglibDefn
     * @generated
     */
    public Adapter createFaceletTaglibDefnAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ComponentTagDefn <em>Component Tag Defn</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ComponentTagDefn
     * @generated
     */
    public Adapter createComponentTagDefnAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ValidatorTagDefn <em>Validator Tag Defn</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ValidatorTagDefn
     * @generated
     */
    public Adapter createValidatorTagDefnAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ConverterTagDefn <em>Converter Tag Defn</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ConverterTagDefn
     * @generated
     */
    public Adapter createConverterTagDefnAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.HandlerTagDefn <em>Handler Tag Defn</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.HandlerTagDefn
     * @generated
     */
    public Adapter createHandlerTagDefnAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.SourceTagDefn <em>Source Tag Defn</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.SourceTagDefn
     * @generated
     */
    public Adapter createSourceTagDefnAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.TagDefn <em>Tag Defn</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.TagDefn
     * @generated
     */
    public Adapter createTagDefnAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn <em>Function Defn</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn
     * @generated
     */
    public Adapter createFunctionDefnAdapter()
    {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter()
    {
        return null;
    }

} //FaceletTaglib_1_0AdapterFactory
