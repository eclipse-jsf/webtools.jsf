/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.internal.translator;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * The factory extension translator
 *
 */
public class FactoryExtensionTranslator extends ExtensionTypeTranslator {

    /**
     * @param domNameAndPath
     * @param feature
     */
    public FactoryExtensionTranslator(String domNameAndPath,
            EStructuralFeature feature) {
        super(domNameAndPath, feature);
    }
}
