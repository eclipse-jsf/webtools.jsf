/*******************************************************************************
 * Copyright (c) 2013, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.metadataprocessors.internal;

import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;

/**
 * Add the ability to inject a more general model context than IStructuredDocumentContext.
 *
 */
public interface IMetaDataEnabledFeature2 extends IMetaDataEnabledFeature
{
    /**
     * @return the model context
     */
    IModelContext getModelContext();
    
    /**
     * @param modelContext
     */
    void setModelContext(IModelContext modelContext);

}
