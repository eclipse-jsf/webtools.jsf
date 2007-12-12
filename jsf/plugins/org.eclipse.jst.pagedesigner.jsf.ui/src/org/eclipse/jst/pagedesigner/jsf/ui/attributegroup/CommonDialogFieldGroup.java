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

import java.util.HashMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup;

/**
 * @author mengbo
 * @version 1.5
 * @TODO - unused now?
 */
public class CommonDialogFieldGroup extends DialogFieldGroup
{
    private HashMap         map           = new HashMap();
    private AttributeData[] attributeData = null;

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup#initialize()
     */
    public void initialize()
    {
        if (attributeData != null)
        {
            for (int i = 0; i < attributeData.length; i++)
            {
                DialogField filed = DialogFieldFactory.getDialogField(attributeData[i]);
                filed.setDialogFieldChangeListener(getDefaultChangeListener());
                map.put(attributeData[i], filed);
            }
        }
    }

    /**
     * prepare the dialog field data
     */
    public void prepareData()
    {
        if (attributeData != null)
        {
            for (int i = 0; i < attributeData.length; i++)
            {
                DialogField field = (DialogField) map.get(attributeData[i]);
                DialogFieldFactory.prepareDialogFieldValue(field, attributeData[i]);
            }
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup#refreshData()
     */
    public void refreshData()
    {
        if (attributeData != null)
        {
            for (int i = 0; i < attributeData.length; i++)
            {
                DialogField field = (DialogField) map.get(attributeData[i]);
                DialogFieldFactory.setDialogFieldValue(field, attributeData[i].getValue());
            }
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup#layoutDialogFields(org.eclipse.ui.forms.widgets.FormToolkit, org.eclipse.swt.widgets.Composite)
     */
    public void layoutDialogFields(FormToolkit toolkit, Composite parent)
    {
        GridData data = new GridData(GridData.FILL_BOTH);
        parent.setLayoutData(data);

        GridLayout gl = new GridLayout();
        gl.numColumns = getMaxColumnNum();
        parent.setLayout(gl);

        if (attributeData != null)
        {
            for (int i = 0; i < attributeData.length; i++)
            {
                DialogField field = (DialogField) map.get(attributeData[i]);
                Control[] controls = field.doFillIntoGrid(toolkit, parent, gl.numColumns);
                if (controls.length > 1)
                {
                    GridData gridData = (GridData) controls[1].getLayoutData();
                    if(field.getNumberOfControls() == gl.numColumns)
                    {
                        gridData.grabExcessHorizontalSpace = true;
                    }
                    gridData.horizontalAlignment = GridData.FILL;
                }
                field.setLabelText(DialogFieldFactory.getDialogFieldLabel(attributeData[i]));
            }
        }
    }

    private int getMaxColumnNum()
    {
        int cols = 0;
        if (attributeData != null)
        {
            for (int i = 0; i < attributeData.length; i++)
            {
                DialogField field = (DialogField) map.get(attributeData[i]);
                int fieldCols = field.getNumberOfControls();
                if (fieldCols > cols)
                {
                    cols = fieldCols;
                }
            }
        }
        return cols;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup#validateDialogFields()
     */
    public IStatus[] validateDialogFields()
    {
        // TODO Now we let it be.
        return null;
    }

    /**
     * @return Returns the attributePairs.
     */
    public AttributeData[] getAttributeData()
    {
        return attributeData;
    }

    /**
     * @param attributePairs The attributePairs to set.
     */
    public void setAttributeData(AttributeData[] attributePairs)
    {
        this.attributeData = attributePairs;
    }
}
