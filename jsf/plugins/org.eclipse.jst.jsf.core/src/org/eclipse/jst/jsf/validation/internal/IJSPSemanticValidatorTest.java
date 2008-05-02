/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.internal.region.Region2ElementAdapter;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator.IValidationReporter;

/**
 * Exposes certain private members for use by testing.
 * 
 * NOT intended for use by production code.
 * 
 * @author cbateman
 *
 */
public interface IJSPSemanticValidatorTest
{
    /**
     * Proxies the internal call to validate the containment of a particular
     * tag.
     * 
     * @param adapter
     * @param node
     * @param uri
     * @param tagName
     * @param reporter
     * @param file
     * @param context
     */
    void validateContainment(Region2ElementAdapter adapter, IValidationReporter reporter, IFile file, IStructuredDocumentContext context);
}
