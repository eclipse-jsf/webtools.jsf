/*******************************************************************************
 * Copyright (c) 2001, 2021 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation  - initial API and implementation
 *     Reto Weiss/Axon Ivy - Cache JDTBeanIntrospector
 *******************************************************************************/
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import java.io.IOException;
import java.io.Reader;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavadocContentAccess;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.util.JDTBeanIntrospector;
import org.eclipse.jst.jsf.common.util.JDTBeanMethod;
import org.eclipse.jst.jsf.common.util.JDTBeanProperty;
import org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol;

/**
 * Utility class for accessing jdt elements for jsf el symbols
 */
public class JavaUtil {

    private JavaUtil() {
        // utility class
    }

    /**
     * @param method
     * @return the javadoc text for the given method. Will return null if no javadoc is found
     */
    public static String getMethodJavadoc(final IMethod method) {
        try {
            final Reader contentReader = JavadocContentAccess.getHTMLContentReader(method, true, true);
            if (contentReader != null) {
                return read(contentReader);
            }
        } catch (final JavaModelException e) {
            JSFCommonPlugin.log(IStatus.WARNING, "error reading javadoc for method '" + method.getElementName() + "'.", e); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return null;
    }

    private static String read(final Reader rd) {
        final StringBuffer buffer= new StringBuffer();
        final char[] readBuffer= new char[2048];

        try {
            int n= rd.read(readBuffer);
            while (n > 0) {
                buffer.append(readBuffer, 0, n);
                n= rd.read(readBuffer);
            }
            return buffer.toString();
        } catch (final IOException x) {
            // should not happen, but otherwise simply ignore
        }

        return null;
    }

    /**
     * @param symbol - the method symbol to look for
     * @return the IMethod for the given method symbol. Will return <code>null</code>, if none is found.
     */
    public static IMethod findCorrespondingMethod(final IBeanMethodSymbol symbol) {
        final IType type = symbol.getOwner().getType();
        final JDTBeanIntrospector introspector = JDTBeanIntrospector.forType(type);
        final JDTBeanMethod[] methods = introspector.getMethods();
        for (final JDTBeanMethod method : methods) {
            if (method.getElementName().equals(symbol.getName())) {
                final String currentMethodsSignature = method.getResolvedSignatureErased();
                if (currentMethodsSignature.equals(symbol.getSignature())) {
                    return method.getMethod();
                }
            }
        }
        return null;
    }

    /**
     * @param propertySymbol - the property symbol to look for
     * @return a JDTBeanProperty for the given property symbol
     */
    public static JDTBeanProperty findCorrespondingJDTProperty(final IBeanPropertySymbol propertySymbol) {
        final JDTBeanIntrospector introspector = JDTBeanIntrospector.forType(propertySymbol.getOwner().getType());
        return introspector.getProperties().get(propertySymbol.getName());
    }
}
