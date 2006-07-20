/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.core.internal.provisional.util;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Utility for handling IType's and type signatures
 * 
 * @author cbateman
 *
 */
public final class TypeUtil 
{
    static IType resolveType(final IType owningType, final String typeSignature)
    {
        // if type signature is already resolved then simply look it up
        if (typeSignature.charAt(0) == Signature.C_RESOLVED
        		|| (Signature.getTypeSignatureKind(typeSignature) == Signature.ARRAY_TYPE_SIGNATURE
        			&& Signature.getElementType(typeSignature).charAt(0) == Signature.C_RESOLVED))
        {
            IType type = null;
            
            try
            {
                type = owningType.getJavaProject().
                           findType(getFullyQualifiedName(typeSignature));
            }
            catch (JavaModelException jme)
            {
                // do nothing; return type == null;
            }
            
            return type;
        }
        
        
        return resolveTypeRelative(owningType, typeSignature);
    }

    
    /**
     * @param owningType
     * @param typeSignature
     * @return the resolved type signature for typeSignature in owningType
     */
    public static String resolveTypeSignature(final IType owningType, final String typeSignature)
    {
        final int sigKind = Signature.getTypeSignatureKind(typeSignature);
    
        switch (sigKind)
        {
            case Signature.BASE_TYPE_SIGNATURE:
                return typeSignature;
                
            case Signature.ARRAY_TYPE_SIGNATURE:
            {
                final String elementType = Signature.getElementType(typeSignature);
                
                if (Signature.getTypeSignatureKind(elementType) == Signature.BASE_TYPE_SIGNATURE)
                {
                    return typeSignature;
                }

                final String resolvedElementType = resolveSignatureRelative(owningType, elementType);
                String resultType = "";
                for (int i = 0; i < Signature.getArrayCount(typeSignature);i++)
                {
                    resultType+=Signature.C_ARRAY;
                }
                
                return resultType+resolvedElementType;
            }
            
            case Signature.CLASS_TYPE_SIGNATURE:
                return resolveSignatureRelative(owningType, typeSignature);
    
            default:
                return typeSignature;
        }
    }
    
    /**
     * @param owningType -- type relative to which typeSignature will be resolved
     * @param typeSignature -- non-array type signature
     * @return the resolved type signature if possible or typeSignature if not
     */
    private static String resolveSignatureRelative(final IType owningType, final String typeSignature)
    {
        String  adjustedTypeSignature = typeSignature;
       
        // if already fully resolved, return the input
        if (adjustedTypeSignature.charAt(0) == Signature.C_RESOLVED)
        {
            return typeSignature;
        }

        IType resolvedType = resolveTypeRelative(owningType, adjustedTypeSignature);

        if (resolvedType != null)
        {
            String  resolvedTypeSignature = 
                Signature.createTypeSignature
                    (resolvedType.getFullyQualifiedName(), true);
           
            return resolvedTypeSignature;
        }

        if (Signature.getTypeSignatureKind(typeSignature) == 
                Signature.CLASS_TYPE_SIGNATURE)
        {
            // TODO: is there a better way to handle a failure to resolve
            // than just garbage out?
            JSFCorePlugin.log(new Exception("Failed to resolve type: "+typeSignature), "Failed to resolve type: "+typeSignature);
        }
        
        return typeSignature;
    }

    private static IType resolveTypeRelative(final IType owningType, final String typeSignature)
    {
        final String fullName = getFullyQualifiedName(typeSignature);
        
        IType resolvedType = null;
        
        try
        {
            String[][] resolved = owningType.resolveType(fullName);
    
            if (resolved != null && resolved.length > 0)
            {
                resolvedType = owningType.getJavaProject().findType(resolved[0][0], resolved[0][1]);
            }
            else
            {
                resolvedType = resolveInParents(owningType, fullName);
            }
        }
        catch (JavaModelException jme)
        {
            //  do nothing; newType == null
        }

        return resolvedType;
    }

    /**
     * @param type
     * @return a type signature for a type
     */
    public static String getSignature(IType type)
    {
        final String fullyQualifiedName = type.getFullyQualifiedName();
        return Signature.createTypeSignature(fullyQualifiedName, true);
    }

    
    /**
     * @param owner
     * @param unresolvedSignature
     * @return the resolved method signature for unresolvedSignature in owner
     */
    public static String resolveMethodSignature(final IType  owner, 
                                         final String unresolvedSignature)
    {
        // get the list of parameters
        final String[] parameters = 
            Signature.getParameterTypes(unresolvedSignature);
        
        for (int i = 0; i < parameters.length; i++)
        {
            // try to full resolve the type
            parameters[i] = resolveTypeSignature(owner, parameters[i]);
        }
        
        // resolve return type
        final String resolvedReturn = 
            resolveTypeSignature(owner, 
                                  Signature.getReturnType(unresolvedSignature));
        
        return Signature.createMethodSignature(parameters, resolvedReturn);
    }
    
    private static String getFullyQualifiedName(final String typeSignature)
    {
        final String packageName = Signature.getSignatureQualifier(typeSignature);
        final String typeName = Signature.getSignatureSimpleName(typeSignature);
        return "".equals(packageName) ? typeName : packageName + "." + typeName;
    }
    
    private static IType resolveInParents(IType  childType, String fullyQualifiedName)
                                throws JavaModelException
    {
        IType resolvedType = null;
        
        // not resolved? try the supertypes
        final ITypeHierarchy typeHierarchy =
            childType.newSupertypeHierarchy(new NullProgressMonitor());
        IType[] superTypes = typeHierarchy.getAllSupertypes(childType);
        String[][]   resolved;
        
        LOOP_UNTIL_FIRST_MATCH:
            for (int i = 0; i < superTypes.length; i++)
        {
            IType type = superTypes[i];
            resolved = type.resolveType(fullyQualifiedName);
            
            if (resolved != null && resolved.length > 0)
            {
                resolvedType = childType.getJavaProject().findType(resolved[0][0], resolved[0][1]);
                break LOOP_UNTIL_FIRST_MATCH;
            }
        }

        return resolvedType;
    }
}
