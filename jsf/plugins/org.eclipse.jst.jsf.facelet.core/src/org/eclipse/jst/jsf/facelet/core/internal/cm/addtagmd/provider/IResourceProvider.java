/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.cm.addtagmd.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A translated string provider.
 * 
 * @author cbateman
 * 
 */
public interface IResourceProvider
{
    /**
     * @param object
     * @param feature
     * @return the string value of object.eGet(feature)
     */
    String getTranslatedString(final EObject object,
            final EStructuralFeature feature);
}
