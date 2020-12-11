/*******************************************************************************
 * Copyright (c) 2006, 2021 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    Reto Weiss/Axon Ivy    - Cache resolved types
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.util;

import java.util.List;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeParameter;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.TypeInfoCache;

/**
 * Utility for handling IType's and type signatures
 * 
 * Class is static and cannot be extended or instantiated.
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
        		|| (Signature.getTypeSignatureKind(typeSignature) == Signature.BASE_TYPE_SIGNATURE)
        		|| (Signature.getTypeSignatureKind(typeSignature) == Signature.ARRAY_TYPE_SIGNATURE
        			&& Signature.getElementType(typeSignature).charAt(0) == Signature.C_RESOLVED))
        {
            try
            {
                return owningType.getJavaProject().
                           findType(getFullyQualifiedName(typeSignature));
            }
            catch (JavaModelException jme)
            {
                // do nothing; return type == null;
            }
            return null;
        }
        return resolveTypeRelative(owningType, typeSignature);
    }

    private static IType resolveTypeRelative(final IType owningType, final String typeSignature)
    {
        final String fullName = getFullyQualifiedName(typeSignature);
        
        IType resolvedType = null;
        
        try
        {
            // TODO: this call is only supported on sourceTypes!
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
     * @param typeSignature     
     * @return a fully qualified Java class name from a type signature
     * i.e. Ljava.lang.String; -> java.lang.String
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

        final TypeInfoCache typeInfoCache = TypeInfoCache.getInstance();
        IType[] superTypes = typeInfoCache.getCachedSupertypes(childType);
        if (superTypes == null) {
        	superTypes = typeInfoCache.cacheSupertypesFor(childType);
        }
        
        String[][]   resolved;
        
        LOOP_UNTIL_FIRST_MATCH:
            for (int i = 0; i < superTypes.length; i++)
        {
            final IType type = superTypes[i];

            resolved = type.resolveType(fullyQualifiedName);

            if (resolved != null && resolved.length > 0)
            {
                resolvedType = childType.getJavaProject().findType(resolved[0][0], resolved[0][1]);
                break LOOP_UNTIL_FIRST_MATCH;
            }
        }

        return resolvedType;
    }
    
    /**
     * Attempts to get a Java IType for a fully qualified signature.  Note that
     * generic type arguments are generally ignored by JDT when doing such 
     * look ups.
     * 
     * @param javaProject the project context inside which to resolve the type
     * @param fullyResolvedTypeSignature a fully resolved type signature
     * @return the IType if resolved, null otherwise
     */
    public static IType resolveType(final IJavaProject javaProject, final String fullyResolvedTypeSignature)
    {
        String fullyQualifiedName = getFullyQualifiedName(fullyResolvedTypeSignature);
        fullyQualifiedName = Signature.getTypeErasure(fullyQualifiedName);
        try {
            return javaProject.findType(fullyQualifiedName);
        } catch (JavaModelException e) {
            // accessible problem
            JSFCommonPlugin.log(e);
            return null;
        }
    }

    /**
     * @param type
     * @param typeParamSignature -- must be a Type Variable Signature
     * @param typeArguments
     * @return the signature for the type argument in typeArguments that matches the
     * named typeParamSignature in type.
     * @throws IllegalArgumentException if typeParamSignature is not valid
     * 
     * For example, given type for java.util.Map, typeParamSignature == "V" and
     * typeArguments = {Ljava.util.String;, Lcom.test.Blah;}, the result would be
     * the typeArgument that matches "V", which is "Lcom.test.Blah;}
     * 
     * returns null if the match cannot be found.
     */
    public static String matchTypeParameterToArgument(final IType type, final String typeParamSignature, final List<String> typeArguments)
    {
    	if (Signature.getTypeSignatureKind(typeParamSignature) != Signature.TYPE_VARIABLE_SIGNATURE)
    	{
    		throw new IllegalArgumentException();
    	}
    	
        try
        {
            ITypeParameter[] typeParams = type.getTypeParameters();

            for (int pos = 0; pos < typeParams.length; pos++)
            {
                if (typeParams[pos].getElementName().equals(Signature.getSignatureSimpleName(typeParamSignature)))
                {
                    if (pos < typeArguments.size())
                    {
                        // TODO: should typeArguments.size ever != typeParams.length?
                        return typeArguments.get(pos);
                    }
                }
            }
        } 
        catch (JavaModelException e) 
        {
            JSFCommonPlugin.log(e);
        }
        
        return null;
    }

    /**
     * @param type
     * @param fieldName
     * @return true if fieldName is a member of type.  Note that if type is java.lang.Enum
     * then this will always return true since we cannot know what fields the instance has (it could be any enum)
     */
    public static boolean isEnumMember(final IType type, final String fieldName)
    {
        try
        {
            if (type == null || !isEnumType(type))
            {
                throw new IllegalArgumentException("type must be non-null and isEnum()==true"); //$NON-NLS-1$
            }
            
            if (fieldName == null)
            {
                throw new IllegalArgumentException("fieldName must be non-null"); //$NON-NLS-1$
            }

            // if type is the java.lang.Enum, always true
            if (TypeConstants.TYPE_ENUM_BASE.equals(Signature.createTypeSignature(type.getFullyQualifiedName(), true)))
            {
                return true;
            }
            
            final IField field = type.getField(fieldName);

            if (field.exists() && field.isEnumConstant())
            {
                return true;
            }
        }
        catch (JavaModelException jme)
        {
            // fall through and return false
        }
        
        return false;
    }

    /**
     * @param typeSig1 the type signature of the first enum. Must be non-null, fully resolved enum type.
     * @param typeSig2 the type signature of the second enum.  Must be non-null, fully resolved enum type.
     * 
     * @return true if typeSig1.compareTo(typeSig2) is a legal operation (won't throw a CCE)
     */
    public static boolean isEnumsCompareCompatible(final String typeSig1, final String typeSig2)
    {
        if (typeSig1 == null || typeSig2 == null)
        {
            throw new IllegalArgumentException("args must not be null"); //$NON-NLS-1$
        }
        
        if (Signature.getTypeSignatureKind(typeSig1) != Signature.CLASS_TYPE_SIGNATURE
             || Signature.getTypeSignatureKind(typeSig2) != Signature.CLASS_TYPE_SIGNATURE)
        {
            throw new IllegalArgumentException("args must be resolved class types"); //$NON-NLS-1$
        }
        
        // if one or the other is the raw enum type, then they *may* be comparable; we don't know
        if (TypeConstants.TYPE_ENUM_BASE.equals(typeSig1) 
                || TypeConstants.TYPE_ENUM_BASE.equals(typeSig2))
        {
            return true;
        }
        
        // TODO: support the case of enum base type with generic type argument
        
        // only comparable if is the same class
        return typeSig1.equals(typeSig2);
    }

    /**
     * @param typeSig1 the type signature of the first enum. Must be non-null, fully resolved enum type.
     * @param typeSig2 the type signature of the second enum. Must be non-null, fully resolved enum type.
     * @return true if instances typeSig1 and typeSig2 can never be equal due
     * their being definitively different enum types
     */
    public static boolean canNeverBeEqual(final String typeSig1, final String typeSig2)
    {
        if (typeSig1 == null || typeSig2 == null)
        {
            throw new IllegalArgumentException("args must not be null"); //$NON-NLS-1$
        }
        
        if (Signature.getTypeSignatureKind(typeSig1) != Signature.CLASS_TYPE_SIGNATURE
             || Signature.getTypeSignatureKind(typeSig2) != Signature.CLASS_TYPE_SIGNATURE)
        {
            throw new IllegalArgumentException("args must be resolved class types"); //$NON-NLS-1$
        }

        // if either one is the base enum type, then we can't be sure
        if (TypeConstants.TYPE_ENUM_BASE.equals(typeSig1) 
                || TypeConstants.TYPE_ENUM_BASE.equals(typeSig2))
        {
            return false;
        }

        // if they are definitely not the same enum types, then their values
        // can never be equal
        return !typeSig1.equals(typeSig2);
    }

    /**
     * NOTE: we diverge from IType.isEnum() because we also return true if the base type
     * is a java.lang.Enum since we consider this to be "any enumeration type" whereas JDT considers
     * it merely a class since it doesn't use an "enum" keyword declaration.
     * @param type
     * @return true if type is an enum type or is java.lang.Enum
     */
    static boolean isEnumType(IType type)
    {
        if (type == null)
        {
            return false;
        }
        
        // check if it's the enumeration base type
        if (TypeConstants.TYPE_ENUM_BASE.equals(Signature.createTypeSignature(type.getFullyQualifiedName(), true)))
        {
            return true;
        }
    
        try
        {
            return type.isEnum();
        }
        catch (JavaModelException jme)
        {
            // log and fallthrough to return false
            JSFCommonPlugin.log(jme, "Problem resolving isEnum"); //$NON-NLS-1$
        }
        
        // if unresolved assume false
        return false;
    }

    private TypeUtil()
    {
        // no external instantiation
    }
}
