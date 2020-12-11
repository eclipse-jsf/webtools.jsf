/*******************************************************************************
 * Copyright (c) 2007, 2021 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    Reto Weiss/Axon Ivy    - Cache JDTBeanIntrospector
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.common.util.JDTBeanIntrospector;
import org.eclipse.jst.jsf.common.util.JDTBeanMethod;
import org.eclipse.jst.jsf.common.util.TypeUtil;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;

final class Util 
{
    static ISymbol call(String methodName, EList methodArguments,
            String symbolName, ITypeDescriptor typeDesc)
    {
        // first, see if the type descriptor wants to handle the call
        ISymbol result = typeDesc.calculateSyntheticCall(methodName, methodArguments, symbolName);

        final IType type= typeDesc.resolveType(typeDesc.getTypeSignature());
        
        // if the type is resolved and the typeDesc didn't already handle
        // the call then do this the hard way...
        if (type != null && result == null)
        {
            final JDTBeanIntrospector introspector = JDTBeanIntrospector.forType(type);

            final JDTBeanMethod callMethod = 
                Util.matchMethod(methodName, methodArguments, introspector.getMethods(),typeDesc.getTypeParameterSignatures());

            if (callMethod != null)
            {
                try
                {
                  // resolve the method's return type; don't erase parameters
                  String retTypeSignature = callMethod.getUnresolvedReturnTypeUnerased();
                      
                  // if we have a type variable, try to parameter match it
                  if (Signature.getTypeSignatureKind(retTypeSignature) == Signature.TYPE_VARIABLE_SIGNATURE)
                  {
                      retTypeSignature = TypeUtil.matchTypeParameterToArgument
                          (type
                             , retTypeSignature, typeDesc.getTypeParameterSignatures());
                      
                      if (retTypeSignature == null)
                      {
                          retTypeSignature = TypeConstants.TYPE_JAVAOBJECT;
                      }
                  }
                  else
                  {
                    retTypeSignature = callMethod.getResolvedReturnTypeUnerased();
                  }
  
                  final IPropertySymbol  propSymbol = 
                      SymbolFactory.eINSTANCE.createIPropertySymbol();
  
                  // TODO: there is a possible problem here for non-string keyed maps
                  propSymbol.setName(symbolName);
                  propSymbol.setReadable(true);
                  
                  {
                      IJavaTypeDescriptor2 newTypeDesc = null;
                      
                      if (retTypeSignature.equals(TypeConstants.TYPE_JAVAOBJECT))
                      {
                          newTypeDesc = SymbolFactory.eINSTANCE.createIBoundedJavaTypeDescriptor();
                      }
                      else
                      {
                          newTypeDesc = SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
                      }
                      
                      newTypeDesc.setArrayCount(Signature.getArrayCount(retTypeSignature));
                      
                      // may be null
                      newTypeDesc.setType(typeDesc.resolveType(retTypeSignature));
                      newTypeDesc.setTypeSignatureDelegate(retTypeSignature);
                      propSymbol.setTypeDescriptor(newTypeDesc);
                  }
                  
                  result = propSymbol;
              } 
              catch (JavaModelException e) 
              {
                  JSFCommonPlugin.log(e);
                  // fall-through and return null result
              }
            }
        }

        return result;
    }

    static JDTBeanMethod matchMethod(String methodName, List methodArguments, JDTBeanMethod[] allMethods, List typeParameterSignatures)
    {
        for (int i = 0; i < allMethods.length; i++)
        {
            final JDTBeanMethod method = allMethods[i];
            
            // check for names and argument count match
            if (method.getResolvedParameterTypesUnerased().length == methodArguments.size()
                    && method.getElementName().equals(methodName))
            {
                List<String> params = resolveMethodParameters(method, typeParameterSignatures);
                
                // need to verify argument matches
                boolean isMatched = true;
                CHECK_ARGUMENTS: for (int j = 0; j < params.size(); j++)
                {
                    final ValueType valueType = (ValueType) methodArguments.get(j);

                    // if the parameters match, or if the method expects an object
                    // and we have a class
                    // TODO: there are some cases not supported here like:
                    // - method name overloading
                    // - autoboxing primitives
                    // - certain kinds of parameterized args
                    if (!params.get(j).equals(valueType.getSignature())
                         && !(params.get(j).equals(TypeConstants.TYPE_JAVAOBJECT)
                                 && Signature.getTypeSignatureKind(valueType.getSignature())==Signature.CLASS_TYPE_SIGNATURE))
                    {
                        // not a match
                        isMatched = false;
                        break CHECK_ARGUMENTS;
                    }
                }
                
                if (isMatched)
                {
                    return method;
                }
            }
        }

        return null;
    }
    
    static List<String> resolveMethodParameters(JDTBeanMethod method, List typeParametersSignatures)
    {
        List<String>   resolved = new ArrayList<String>();
        String[] parameterTypes = method.getResolvedParameterTypesUnerased();
        for (String parameter : parameterTypes)
        { 
            if (Signature.getTypeSignatureKind(parameter) == Signature.TYPE_VARIABLE_SIGNATURE)
            {
                parameter = TypeUtil.matchTypeParameterToArgument
                    (method.getDeclaringType(), parameter, typeParametersSignatures);
            }
            
            if (parameter == null)
            {
                parameter = TypeConstants.TYPE_JAVAOBJECT;
            }
            
            resolved.add(parameter);
        }
        
        return resolved;
    }
}
