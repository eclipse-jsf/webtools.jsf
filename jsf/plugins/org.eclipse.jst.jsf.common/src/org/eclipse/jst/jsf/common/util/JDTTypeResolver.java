/*******************************************************************************
 * Copyright (c) 2020, 2021 Axon Ivy.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Reto Weiss/Axon Ivy      Cache resolved types
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeParameter;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;

/**
 * Resolves types of a type and caches them
 * @author rwei
 * @since 27.11.2020
 */
public class JDTTypeResolver
{
  private final Map<String, IType> resolvedTypes = new HashMap<String, IType>();
  private final IType type;
  private final IType[] superTypes;

  /**
   * Constructor
   * @param type
   * @param superTypes
   */
  public JDTTypeResolver(IType type, IType[] superTypes)
  {
    this.type = type;
    this.superTypes = superTypes;
  }

  /**
   * @param unresolvedSignature
   * @return resolved method signatures with erased type parameters
   */
  public String resolveMethodEraseTypeParams(String unresolvedSignature)
  {
    return resolveMethod(unresolvedSignature, TypeParameters.ERASE);
  }

  /**
   * @param unresolvedSignature
   * @return resolve method signature with type parameters
   */
  public String resolveMethodKeepTypeParams(String unresolvedSignature)
  {
    return resolveMethod(unresolvedSignature, TypeParameters.KEEP);
  }

  private String resolveMethod(String unresolvedSignature, TypeParameters eraseTypeParameters)
  {
    final String unresolvedSignatureNormalized = unresolvedSignature.replaceAll("/", "."); //$NON-NLS-1$ //$NON-NLS-2$

    // get the list of parameters
    final String[] parameters = Signature.getParameterTypes(unresolvedSignatureNormalized);

    for (int i = 0; i < parameters.length; i++)
    {
      // try to full resolve the type
      parameters[i] = resolve(parameters[i], eraseTypeParameters);
    }

    // resolve return type
    final String resolvedReturn = resolve(Signature.getReturnType(unresolvedSignatureNormalized),
            eraseTypeParameters);
    return Signature.createMethodSignature(parameters, resolvedReturn);
  }

  /**
   * @param unresolvedSignature
   * @return resolved signature with erased type params
   */
  public String resolveEraseTypeParams(String unresolvedSignature)
  {
    return resolve(unresolvedSignature, TypeParameters.ERASE);
  }

  /**
   * @param unresolvedSignature
   * @return resolved signature with type params
   */
  public String resolveKeepTypeParams(String unresolvedSignature)
  {
    return resolve(unresolvedSignature, TypeParameters.KEEP);
  }

  private String resolve(String unresolvedSignature, TypeParameters eraseTypeParameters)
  {
    final int sigKind = Signature.getTypeSignatureKind(unresolvedSignature);

    switch (sigKind)
    {
      case Signature.BASE_TYPE_SIGNATURE:
        return unresolvedSignature;

      case Signature.ARRAY_TYPE_SIGNATURE:
      {
        final String elementType = Signature.getElementType(unresolvedSignature);

        if (Signature.getTypeSignatureKind(elementType) == Signature.BASE_TYPE_SIGNATURE)
        {
          return unresolvedSignature;
        }

        final String resolvedElementType = resolveRelative(elementType, eraseTypeParameters);
        String resultType = ""; //$NON-NLS-1$
        for (int i = 0; i < Signature.getArrayCount(unresolvedSignature); i++)
        {
          resultType += Signature.C_ARRAY;
        }

        return resultType + resolvedElementType;
      }

      case Signature.TYPE_VARIABLE_SIGNATURE:
        return resolveRelative(unresolvedSignature, eraseTypeParameters);

      case Signature.CLASS_TYPE_SIGNATURE:
        return resolveRelative(unresolvedSignature, eraseTypeParameters);

      case Signature.WILDCARD_TYPE_SIGNATURE:
        // strip the wildcard and try again. Too bad Signature doesn't seem to
        // have a method
        // for this
        if (unresolvedSignature.charAt(0) == Signature.C_STAR)
        {
          return TypeConstants.TYPE_JAVAOBJECT;
        }
        return resolve(unresolvedSignature.substring(1), eraseTypeParameters);

      case Signature.CAPTURE_TYPE_SIGNATURE:
        // strip the capture and try again
        return resolve(Signature.removeCapture(unresolvedSignature), eraseTypeParameters);
      // case Signature.TYPE_VARIABLE_SIGNATURE:
      // resolveSignatureRelative(owningType, typeSignature,
      // eraseTypeParameters);

      default:
        return unresolvedSignature;
    }
  }

  /**
   * @param owningType -- type relative to which typeSignature will be resolved
   * @param typeSignature -- non-array type signature
   * @return the resolved type signature if possible or typeSignature if not
   */
  private String resolveRelative(final String typeSignature, final TypeParameters eraseTypeParameters)
  {
    // if already fully resolved, return the input
    if (typeSignature.charAt(0) == Signature.C_RESOLVED)
    {
      return typeSignature;
    }

    List<String> typeParameters = new ArrayList<String>();

    IType resolvedType = resolveType(typeSignature);

    if (resolvedType != null)
    {
      if (eraseTypeParameters == TypeParameters.KEEP)
      {
        // ensure that type parameters are resolved recursively
        for (String typeParam : Signature.getTypeArguments(typeSignature))
        {
          typeParam = Signature.removeCapture(typeParam);
          // check and remove bound wildcarding (extends/super/?)
          if (Signature.getTypeSignatureKind(typeParam) == Signature.WILDCARD_TYPE_SIGNATURE)
          {
            // convert ? to Object, strip extends/super
            if (typeParam.charAt(0) == Signature.C_STAR)
            {
              typeParam = TypeConstants.TYPE_JAVAOBJECT;
            }
            else
            {
              typeParam = typeParam.substring(1);
            }
          }
          final String resolvedParameter = resolveRelative(
                  // use the enclosing type,
                  // *not* the resolved type because
                  // we need to resolve in that context
                  typeParam, eraseTypeParameters);
          typeParameters.add(resolvedParameter);
        }
      }

      final String resolvedTypeSignature = Signature.createTypeSignature(resolvedType.getFullyQualifiedName(),
              true);

      if (typeParameters.size() > 0 && eraseTypeParameters == TypeParameters.KEEP)
      {
        StringBuffer sb = new StringBuffer(resolvedTypeSignature);

        if (sb.charAt(sb.length() - 1) == ';')
        {
          sb = sb.delete(sb.length() - 1, sb.length());
        }

        sb.append("<"); //$NON-NLS-1$
        for (String param : typeParameters)
        {
          // System.out.println("type param:
          // "+resolvedType.getTypeParameter(param));
          sb.append(param);
        }

        // replace the dangling ',' with the closing ">"
        sb.append(">;"); //$NON-NLS-1$
        return sb.toString();
      }

      return resolvedTypeSignature;
    }

    if (Signature.getTypeSignatureKind(typeSignature) == Signature.CLASS_TYPE_SIGNATURE
            || Signature.getTypeSignatureKind(typeSignature) == Signature.TYPE_VARIABLE_SIGNATURE)
    {
      // if we are unable to resolve, check to see if the owning type has
      // a parameter by this name
      ITypeParameter typeParam = type.getTypeParameter(Signature.getSignatureSimpleName(typeSignature));

      // if we have a type parameter and it hasn't been resolved to a type,
      // then assume it is a method template placeholder (i.e. T in ArrayList).
      // at runtime these unresolved parameter variables are effectively
      // turned into Object's. For example, think List.add(E o). At runtime,
      // E will behave exactly like java.lang.Object in that signature
      if (typeParam.exists())
      {
        return TypeConstants.TYPE_JAVAOBJECT;
      }

      // TODO: is there a better way to handle a failure to resolve
      // than just garbage out?
      // JSFCommonPlugin.log(new Exception("Failed to resolve type:
      // "+typeSignature), "Failed to resolve type: "+typeSignature);
      // //$NON-NLS-1$ //$NON-NLS-2$
    }

    return typeSignature;
  }

  private IType resolveType(final String typeSignature)
  {
    final String typeName = TypeUtil.getFullyQualifiedName(typeSignature);
    return resolveTypeName(typeName);
  }
  
  IType resolveTypeName(String typeName)
  {
    IType resolved = resolvedTypes.get(typeName);
    if (resolved != null)
    {
      return resolved;
    }
    resolved = resolveTypeNameRelative(typeName);
    resolvedTypes.put(typeName, resolved);
    return resolved;
  }

  private IType resolveTypeNameRelative(String typeName)
  {
    try
    {
      String[][] resolved = type.resolveType(typeName);
      if (resolved != null && resolved.length > 0)
      {
        return type.getJavaProject().findType(resolved[0][0], resolved[0][1]);
      }
      return resolveInParents(typeName);
    }
    catch (JavaModelException jme)
    {
      return null;
    }
  }

  private IType resolveInParents(String fullyQualifiedName)
          throws JavaModelException
  {
    for (IType superType : superTypes)
    {
      String[][] resolved = superType.resolveType(fullyQualifiedName);
      if (resolved != null && resolved.length > 0)
      {
        return type.getJavaProject().findType(resolved[0][0], resolved[0][1]);
      }
    }
    return null;
  }

  private enum TypeParameters
  {
    ERASE, KEEP
  }
}
