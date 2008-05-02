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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The parent of all detail forms.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDetailsForm
{
    private FormToolkit _toolkit;

    /**
     * 
     */
    protected AbstractDetailsForm()
    {
        super();
    }

    /**
     * Called to refresh model if stale
     */
    public void refresh()
    {
        // do nothing by default
    }

    /**
     * Called before createContents.
     * 
     * @param toolkit
     */
    public final void initialize(final FormToolkit toolkit)
    {
        _toolkit = toolkit;
        doInitialize();
    }
    
    /**
     * Override to customize initialization.  getToolkit() is safe to call
     * from this method.  You should not make other state assumptions.
     */
    protected void doInitialize()
    {
        // do nothing by default; 
    }
    
    /**
     * Callled by MasterDetailBlock during it's dispose
     */
    public abstract void dispose();

    /**
     * @param onSave
     */
    public abstract void commit(boolean onSave);

    /**
     * Called on this details form before it is shown, when it becomes active
     * due to a matching selection change in the master form.  Implementer
     * should use newSelection as the new instance of their model and 
     * reinitialize UI as appropriate.
     * @param newSelection
     */
    protected abstract void doUpdateSelection(final Object newSelection);

    /**
     * @return the contents control.  Must not be called before createContents.
     */
    public abstract Control getControl();

    /**
     * Fired to indicate that this detail form is now selected based on the
     * master form selected.
     * 
     * @param selection
     */
    public void selectionChanged(final ISelection selection)
    {
        // by default, get the first element of the selection if there is
        // one and call doUpdateSelection on it.  Sub-classes may change
        // this behaviour
        if (selection instanceof IStructuredSelection)
        {
            final Object selectionObj = ((IStructuredSelection) selection)
                    .getFirstElement();
            doUpdateSelection(selectionObj);
        }
    }

    /**
     * Create the contents of the detail form using 'parent'.  Should not be
     * called before initialize.
     * 
     * @param parent
     */
    public abstract void createContents(Composite parent);

    /**
     * @return the form toolkit in use.  Should not be called before 
     * initialize.
     */
    protected final FormToolkit getToolkit()
    {
        return _toolkit;
    }

    /**
     * @return true if the form is dirty
     */
    public boolean isDirty()
    {
        // never dirty by default
        return false;
    }

    /**
     * @return true if form is stale
     */
    public boolean isStale()
    {
        // always stale by default
        return true;
    }

    /**
     * Called to set focus on the detail form's control
     */
    public abstract void setFocus();
}