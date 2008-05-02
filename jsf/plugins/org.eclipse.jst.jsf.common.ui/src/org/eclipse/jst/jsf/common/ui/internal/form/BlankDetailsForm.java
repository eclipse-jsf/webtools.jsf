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
package org.eclipse.jst.jsf.common.ui.internal.form;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * A blank details form.
 * 
 * @author cbateman
 * 
 */
public final class BlankDetailsForm extends AbstractDetailsForm
{
    private Composite _emptyPanel;

    @Override
    public void createContents(Composite parent)
    {
        _emptyPanel = getToolkit().createComposite(parent);
    }

    @Override
    public Control getControl()
    {
        return _emptyPanel;
    }

    @Override
    protected void doUpdateSelection(Object newSelection)
    {
        // do nothing
    }

    @Override
    public void commit(boolean onSave)
    {
        // nothing to commit
    }

    @Override
    public void dispose()
    {
       // nothing to dispose
    }

    @Override
    public void setFocus()
    {
        // don't bother changing focus
    }
}
