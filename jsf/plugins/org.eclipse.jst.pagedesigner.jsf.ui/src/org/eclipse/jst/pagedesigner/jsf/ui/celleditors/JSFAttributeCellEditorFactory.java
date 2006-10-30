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
package org.eclipse.jst.pagedesigner.jsf.ui.celleditors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jst.pagedesigner.common.dialogfield.DialogField;
import org.eclipse.jst.pagedesigner.meta.IAttributeCellEditorFactory;
import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.jst.pagedesigner.properties.celleditors.LabeledComboBoxCellEditor;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSFAttributeCellEditorFactory implements IAttributeCellEditorFactory
{
    private static final String CONVERTERID = "converterId"; //$NON-NLS-1$
    private static final String VALIDATORID = "validatorId"; //$NON-NLS-1$

    /**
     * The constructor
     */
    public JSFAttributeCellEditorFactory()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.meta.IAttributeCellEditorFactory#createCellEditor(org.eclipse.swt.widgets.Composite, org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor, org.w3c.dom.Element)
     */
    public CellEditor createCellEditor(Composite parent, IAttributeDescriptor attr, Element element)
    {
        String type = attr.getValueType();
        IProject project = StructuredModelUtil.getProjectFor(((IDOMElement) element).getModel());
        String[] results = null;
        if (type.equalsIgnoreCase(CONVERTERID))
        {
        	// XXX
            // results = FacesUtil.getRegisteredConverterIds(project);
            Map map = new HashMap();
            if (results != null)
            {
                for (int i = 0; i < results.length; i++)
                {
                    map.put(results[i], results[i]);
                }
            }
            return LabeledComboBoxCellEditor.newInstance(parent, map, SWT.NONE);
        }
        else if (type.equalsIgnoreCase(VALIDATORID))
        {
        	// XXX
            // results = FacesUtil.getRegisteredValidatorIds(project);
            Map map = new HashMap();
            if (results != null)
            {
                for (int i = 0; i < results.length; i++)
                {
                    map.put(results[i], results[i]);
                }
            }
            return LabeledComboBoxCellEditor.newInstance(parent, map, SWT.NONE);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.meta.IAttributeCellEditorFactory#createDialogField(org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor, org.w3c.dom.Element, org.w3c.dom.Element)
     */
    public DialogField createDialogField(IAttributeDescriptor attr)
    {
        String type = attr.getValueType();

        if (type.equalsIgnoreCase(CONVERTERID))
        {
            ConverterValidatorIdDialogField dialogField = new ConverterValidatorIdDialogField();
            dialogField.setElementId("converter-id");
            dialogField.setRequired(attr.isRequired());
            dialogField.setToolTip(attr.getDescription());
            return dialogField;
        }
        else if (type.equalsIgnoreCase(VALIDATORID))
        {
            ConverterValidatorIdDialogField dialogField = new ConverterValidatorIdDialogField();
            dialogField.setElementId("validator-id");
            dialogField.setRequired(attr.isRequired());
            dialogField.setToolTip(attr.getDescription());
            return dialogField;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.meta.IAttributeCellEditorFactory#getSupportedValueTypes()
     */
    public String[] getSupportedValueTypes()
    {
        return new String[] { CONVERTERID, VALIDATORID};
    }
}
