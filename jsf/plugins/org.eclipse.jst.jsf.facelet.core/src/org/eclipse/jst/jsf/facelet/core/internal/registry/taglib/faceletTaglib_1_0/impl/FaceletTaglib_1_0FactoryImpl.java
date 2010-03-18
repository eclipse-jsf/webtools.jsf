/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglib_1_0FactoryImpl.java,v 1.1 2010/03/18 06:24:28 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ComponentTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ConverterTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletLibraryClassTagLib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglibDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Factory;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglib_1_0Package;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletXMLDefnTaglib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.HandlerTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.SourceTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.TagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ValidatorTagDefn;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FaceletTaglib_1_0FactoryImpl extends EFactoryImpl implements FaceletTaglib_1_0Factory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * @return the factory
     * <!-- end-user-doc -->
     * @generated
     */
    public static FaceletTaglib_1_0Factory init()
    {
        try
        {
            FaceletTaglib_1_0Factory theFaceletTaglib_1_0Factory = (FaceletTaglib_1_0Factory)EPackage.Registry.INSTANCE.getEFactory("http://org.eclipse.jst.jsf.facelet.core/faceletTaglib_1_0"); //$NON-NLS-1$ 
            if (theFaceletTaglib_1_0Factory != null)
            {
                return theFaceletTaglib_1_0Factory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new FaceletTaglib_1_0FactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglib_1_0FactoryImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass)
    {
        switch (eClass.getClassifierID())
        {
            case FaceletTaglib_1_0Package.FACELET_LIBRARY_CLASS_TAG_LIB: return createFaceletLibraryClassTagLib();
            case FaceletTaglib_1_0Package.FACELET_XML_DEFN_TAGLIB: return createFaceletXMLDefnTaglib();
            case FaceletTaglib_1_0Package.FACELET_TAGLIB_DEFN: return createFaceletTaglibDefn();
            case FaceletTaglib_1_0Package.COMPONENT_TAG_DEFN: return createComponentTagDefn();
            case FaceletTaglib_1_0Package.VALIDATOR_TAG_DEFN: return createValidatorTagDefn();
            case FaceletTaglib_1_0Package.CONVERTER_TAG_DEFN: return createConverterTagDefn();
            case FaceletTaglib_1_0Package.HANDLER_TAG_DEFN: return createHandlerTagDefn();
            case FaceletTaglib_1_0Package.SOURCE_TAG_DEFN: return createSourceTagDefn();
            case FaceletTaglib_1_0Package.TAG_DEFN: return createTagDefn();
            case FaceletTaglib_1_0Package.FUNCTION_DEFN: return createFunctionDefn();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletLibraryClassTagLib createFaceletLibraryClassTagLib()
    {
        FaceletLibraryClassTagLibImpl faceletLibraryClassTagLib = new FaceletLibraryClassTagLibImpl();
        return faceletLibraryClassTagLib;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletXMLDefnTaglib createFaceletXMLDefnTaglib()
    {
        FaceletXMLDefnTaglibImpl faceletXMLDefnTaglib = new FaceletXMLDefnTaglibImpl();
        return faceletXMLDefnTaglib;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglibDefn createFaceletTaglibDefn()
    {
        FaceletTaglibDefnImpl faceletTaglibDefn = new FaceletTaglibDefnImpl();
        return faceletTaglibDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentTagDefn createComponentTagDefn()
    {
        ComponentTagDefnImpl componentTagDefn = new ComponentTagDefnImpl();
        return componentTagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValidatorTagDefn createValidatorTagDefn()
    {
        ValidatorTagDefnImpl validatorTagDefn = new ValidatorTagDefnImpl();
        return validatorTagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConverterTagDefn createConverterTagDefn()
    {
        ConverterTagDefnImpl converterTagDefn = new ConverterTagDefnImpl();
        return converterTagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HandlerTagDefn createHandlerTagDefn()
    {
        HandlerTagDefnImpl handlerTagDefn = new HandlerTagDefnImpl();
        return handlerTagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SourceTagDefn createSourceTagDefn()
    {
        SourceTagDefnImpl sourceTagDefn = new SourceTagDefnImpl();
        return sourceTagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TagDefn createTagDefn()
    {
        TagDefnImpl tagDefn = new TagDefnImpl();
        return tagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FunctionDefn createFunctionDefn()
    {
        FunctionDefnImpl functionDefn = new FunctionDefnImpl();
        return functionDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglib_1_0Package getFaceletTaglib_1_0Package()
    {
        return (FaceletTaglib_1_0Package)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * @return the package.
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static FaceletTaglib_1_0Package getPackage()
    {
        return FaceletTaglib_1_0Package.eINSTANCE;
    }

} //FaceletTaglib_1_0FactoryImpl
