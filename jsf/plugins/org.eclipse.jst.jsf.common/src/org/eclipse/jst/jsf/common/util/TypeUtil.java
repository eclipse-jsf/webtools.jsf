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

package org.eclipse.jst.jsf.common.util;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.ITypeParameter;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;

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
     * @return the resolved type signature for typeSignature in owningType or
     * typeSignature unchanged if cannot resolve.
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
                String resultType = ""; //$NON-NLS-1$
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
            // if we are unable to resolve, check to see if the owning type has
            // a parameter by this name
            ITypeParameter typeParam = owningType.getTypeParameter(Signature.getSignatureSimpleName(typeSignature));
            
            // if we have a type parameter and it hasn't been resolved to a type,
            // then assume it is a method template placeholder (i.e. T in ArrayList).
            // at runtime these unresolved parameter variables are effectively 
            // turned into Object's.  For example, think List.add(E o).  At runtime,
            // E will behave exactly like java.lang.Object in that signature
            if (typeParam.exists())
            {
                return TypeConstants.TYPE_JAVAOBJECT;
            }
            
            // TODO: is there a better way to handle a failure to resolve
            // than just garbage out?
            JSFCommonPlugin.log(new Exception("Failed to resolve type: "+typeSignature), "Failed to resolve type: "+typeSignature); //$NON-NLS-1$ //$NON-NLS-2$
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
    
    /**
     * @param typeSignature     * @return a fully qualified Java class name from a type signature
     * i.e. Ljava.lang.String; -> java.lang.String
     * @return the fully qualifed classname
     */
    public static String getFullyQualifiedName(final String typeSignature)
    {
        final String packageName = Signature.getSignatureQualifier(typeSignature);
        final String typeName = Signature.getSignatureSimpleName(typeSignature);
        return "".equals(packageName) ? typeName : packageName + "." + typeName;  //$NON-NLS-1$//$NON-NLS-2$
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
