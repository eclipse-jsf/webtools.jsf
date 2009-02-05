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
 * A factory that can be used with the pageDesignerExtension to override
 * the default property sheet page provided by the WPE when tag elements are
 * selected by the user.
 * 
 * This interface should not be implemented or extended by clients.  Use
 * AbstractPropertySheetPageFactory instead.
 * 
 * @author cbateman
 *
 */
public interface IPropertySheetPageFactory
{
    /**
     * @param file
     * @return the  property sheet page for the file.
     */
    IPropertySheetPage  createPage(final IFile file);
}
