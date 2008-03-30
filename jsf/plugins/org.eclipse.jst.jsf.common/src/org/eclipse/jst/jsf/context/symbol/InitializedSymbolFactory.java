/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthias Fuessel -- extracted from https://bugs.eclipse.org/bugs/show_bug.cgi?id=215461
 *    Cameron Bateman/Oracle - integrated.
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.context.symbol;

import java.util.Arrays;
import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.common.util.TypeUtil;

/**
 * Creates purpose-built symbols and descriptors fully initialized (unlike the
 * EMF factory that simply creates empty instances.
 * 
 * This class is for convenience only and should not do anything that clients
 * could not do by hand (though with more work).
 * 
 * Clients may use or subclass.
 * 
 * @author cbateman
 * 
 */
public class InitializedSymbolFactory
{
    /**
     * If fullyQualifiedClass can be resolved to an IType, then a bean instance
     * symbol will be created. If the type cannot be resolved, then
     * createUnknownInstanceSymbol is called with the type descriptor on the
     * returned symbol forced to fullyQualifiedClass.
     * 
     * @param project
     * @param fullyQualifiedClass
     * @param symbolName
     * @param source
     * @return a symbol
     */
    public final ISymbol createBeanOrUnknownInstanceSymbol(
            final IProject project, final String fullyQualifiedClass,
            final String symbolName, final ERuntimeSource source)
    {
        final IJavaProject javaProject = JavaCore.create(project);
        try
        {
            final IType type = javaProject.findType(fullyQualifiedClass);

            // TODO: this is a high-bred since it consists of a java instance
            // but also has properties we can populate at designtime such as
            // the maps. Need to add the second part
            if (type != null)
            {
                final IJavaTypeDescriptor2 typeDesc = SymbolFactory.eINSTANCE
                        .createIJavaTypeDescriptor2();
                typeDesc.setType(type);
                final IBeanInstanceSymbol facesContextVar = SymbolFactory.eINSTANCE
                        .createIBeanInstanceSymbol();
                facesContextVar.setTypeDescriptor(typeDesc);
                facesContextVar.setName(symbolName);
                facesContextVar.setRuntimeSource(source);
                return facesContextVar;
            }
        }
        catch (final JavaModelException jme)
        {
            // fall-through and fail with unresolved map
        }

        final ISymbol symbol = createUnknownInstanceSymbol(symbolName, source);
        ((IInstanceSymbol) symbol)
                .getTypeDescriptor()
                .setTypeSignatureDelegate(
                        Signature
                                .createTypeSignature(fullyQualifiedClass, true));

        return symbol;
    }

    /**
     * @param symbolName
     * @param source
     * @return a symbol for a variable of unknown type
     */
    public final IComponentSymbol createUnknownComponentSymbol(
            final String symbolName, final ERuntimeSource source)
    {
        final IComponentSymbol symbol = SymbolFactory.eINSTANCE
                .createIComponentSymbol();
        populateUnknownInstanceSymbol(symbol, symbolName, source);
        return symbol;
    }

    /**
     * @param symbolName
     * @param source
     * @return the unknown instance symbol as an IInstanceSymbol
     */
    public final IInstanceSymbol createUnknownInstanceSymbol(
            final String symbolName, final ERuntimeSource source)
    {
        final IInstanceSymbol symbol = SymbolFactory.eINSTANCE
                .createIInstanceSymbol();
        populateUnknownInstanceSymbol(symbol, symbolName, source);
        return symbol;
    }

    /**
     * @param name
     *            may NOT be null.
     * @param typeDesc
     *            may NOT be null.
     * @param description
     *            may be null
     * @return a component symbol using the java type descriptor
     * @throws IllegalArgumentException
     *             if non-null argument is null
     */
    public final IComponentSymbol createJavaComponentSymbol(final String name,
            final IJavaTypeDescriptor2 typeDesc, final String description)
    {
        if (name == null || typeDesc == null)
        {
            throw new IllegalArgumentException(
                    "name and typeDesc must not be null");
        }

        final IComponentSymbol symbol = SymbolFactory.eINSTANCE
                .createIComponentSymbol();
        symbol.setName(name);
        symbol.setTypeDescriptor(typeDesc);
        symbol.setRuntimeSource(ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL);
        return symbol;
    }

    /**
     * @param name
     * @param valueType
     * @param description
     * @param javaProject
     * @return an IComponentSymbol that uses valueType to derive the type
     * of its type descriptor
     */
    public final IComponentSymbol createJavaComponentSymbol(final String name,
            final ValueType valueType, final String description,
            final IJavaProject javaProject)
    {
        final IJavaTypeDescriptor2 typeDesc = createTypeDescriptorFromSignature(
                valueType.getSignature(), javaProject);
        return createJavaComponentSymbol(name, typeDesc, description);
    }

    private void populateUnknownInstanceSymbol(final IInstanceSymbol symbol,
            final String symbolName, final ERuntimeSource source)
    {
        final IMapTypeDescriptor typeDesc = SymbolFactory.eINSTANCE
                .createIBoundedMapTypeDescriptor();
        // empty map source
        typeDesc.setMapSource(Collections.emptyMap());
        symbol.setName(symbolName);
        symbol.setTypeDescriptor(typeDesc);
        symbol.setRuntimeSource(source);
    }

    /**
     * @param type
     * @return the signature of the element type of a collection/array,
     *         <code>null</code>, if untyped Collection or no container type
     *         at all.
     */
    public final String getElementSignatureFromContainerType(ValueType type)
    {
        if (type.isArray())
        {
            // TODO full signature
            String signature = type.getSignature();
            int arrayCount = Signature.getArrayCount(signature);
            String elementSig = Signature.getElementType(signature);
            return Signature.createArraySignature(elementSig, arrayCount - 1);
        }
        if (type.isInstanceOf(TypeConstants.TYPE_COLLECTION))
        {
            final String[] typeArguments = type.getTypeArguments();
            if (typeArguments.length > 0)
            {
                return typeArguments[0];
            }
        }
        return null;
    }

    /**
     * @param signature
     * @param javaProject
     * @return a java type descriptor based on the fully qualified type
     *         specified by signature using javaProject as the lookup classpath.
     *         If the IType for signature cannot be found, the descriptor's
     *         typeSignatureDelegate will be used.
     */
    public final IJavaTypeDescriptor2 createTypeDescriptorFromSignature(
            final String signature, final IJavaProject javaProject)
    {
        final String elementType = Signature.getElementType(signature);

        IJavaTypeDescriptor2 desc = SymbolFactory.eINSTANCE
                .createIJavaTypeDescriptor2();
        final int arrayCount = Signature.getArrayCount(signature);
        if (arrayCount > 0)
        {
            desc.setArrayCount(arrayCount);
        }

        IType type = TypeUtil.resolveType(javaProject, elementType);
        if (type != null)
        {
            desc.setType(type);
        }
        else
        {
            desc.setTypeSignatureDelegate(Signature.getTypeErasure(signature));
        }
        desc.getTypeParameterSignatures().addAll(
                Arrays.asList(Signature.getTypeArguments(signature)));
        return desc;
    }
}
