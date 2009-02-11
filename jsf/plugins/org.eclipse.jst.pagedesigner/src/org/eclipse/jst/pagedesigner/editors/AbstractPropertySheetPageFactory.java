/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.pagedesigner.editors;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.views.properties.IPropertySheetPage;

/**
 * Abstract class that should be extended to provide a property sheet page
 * factory.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractPropertySheetPageFactory implements
        IPropertySheetPageFactory
{

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.editors.IPropertySheetPageFactory#createPage(org.eclipse.core.resources.IFile)
     */
    public abstract IPropertySheetPage createPage(IFile file);
}
