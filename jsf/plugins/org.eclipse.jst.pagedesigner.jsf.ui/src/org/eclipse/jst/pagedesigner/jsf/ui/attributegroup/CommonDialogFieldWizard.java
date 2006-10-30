/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.attributegroup;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.pagedesigner.common.dialogfield.DialogFieldGroupPage;
import org.eclipse.ui.internal.dialogs.NewWizard;

/**
 * @author mengbo
 * @version 1.5
 */
public class CommonDialogFieldWizard extends NewWizard
{
    DialogFieldGroupPage mainPage;
    public CommonDialogFieldWizard(DialogFieldGroupPage page)
    {
        this.mainPage = page;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizard#performFinish()
     */
    public boolean performFinish()
    {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages()
    {
        addPage(mainPage);        
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.wizards.NewWizard#doFinish(org.eclipse.core.runtime.IProgressMonitor)
     */
    protected void doFinish(IProgressMonitor monitor) throws CoreException
    {
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.wizards.NewWizard#setupFinish()
     */
    protected void setupFinish()
    {
    }
}
