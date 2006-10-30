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
package org.eclipse.jst.pagedesigner.jsf.ui.sections;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import org.eclipse.jst.pagedesigner.common.dialogfield.ComboDialogField;
import org.eclipse.jst.pagedesigner.common.dialogfield.DialogField;
import org.eclipse.jst.pagedesigner.common.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.pagedesigner.common.dialogfield.StringDialogField;
import org.eclipse.jst.pagedesigner.common.dialogfield.StyleComboDialogField;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.commands.single.ChangeAttributeCommand;
import org.eclipse.jst.pagedesigner.meta.EditorCreator;
import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.jst.pagedesigner.properties.attrgroup.AttributeGroup;
import org.eclipse.jst.pagedesigner.ui.dialogfields.DialogFieldWrapper;

/**
 * @author mengbo
 */
public class JSFCoreConvertNumberGroup extends AttributeGroup
{
    private StyleComboDialogField _typeField;
    private StyleComboDialogField _patternField;
    private ComboDialogField      _currencyCodeField;
    private StringDialogField     _currencySymbolField;

    final private static String[] TYPES = { "number", "currency", "percentage", "custom" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                                        };

    /**
     * @param uri
     * @param tagName
     * @param attrNames
     */
    public JSFCoreConvertNumberGroup()
    {
        super(IJMTConstants.URI_JSF_CORE, IJSFConstants.TAG_CONVERTNUMBER, new String[] { IJSFConstants.ATTR_TYPE,
                IJSFConstants.ATTR_CURRENCYCODE, IJSFConstants.ATTR_CURRENCYSYMBOL, IJSFConstants.ATTR_PATTERN});
    }

    protected DialogField createDialogField(String uri, String tag, IAttributeDescriptor attr)
    {
        EditorCreator creator = EditorCreator.getInstance();
        if (attr.getAttributeName().equals(IJSFConstants.ATTR_TYPE))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
                    .createDialogFieldWithWrapper(uri, tag, attr, null);
            _typeField = (StyleComboDialogField) wrapper.getWrappedDialogField();
                    _typeField.setItems(TYPES);
            return wrapper;
        }
        else if (attr.getAttributeName().equals(IJSFConstants.ATTR_CURRENCYCODE))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
                    .createDialogFieldWithWrapper(uri, tag, attr, null);
            _currencyCodeField = (ComboDialogField) wrapper.getWrappedDialogField();
            return wrapper;
        }
        else if (attr.getAttributeName().equals(IJSFConstants.ATTR_CURRENCYSYMBOL))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
                    .createDialogFieldWithWrapper(uri, tag, attr, null);
            _currencySymbolField = (StringDialogField) wrapper.getWrappedDialogField();
            return wrapper;
        }
        else if (attr.getAttributeName().equals(IJSFConstants.ATTR_PATTERN))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
                    .createDialogFieldWithWrapper(uri, tag, attr, null);
            _patternField = (StyleComboDialogField) wrapper.getWrappedDialogField();
            return wrapper;
        }
        else
        {
            return null;
        }
    }

    public IDialogFieldApplyListener getDialogFieldApplyListener(String uri, String tag, IAttributeDescriptor attr)
    {
        String attribute = attr.getAttributeName();
        if (attribute.equals(IJSFConstants.ATTR_CURRENCYCODE) || attribute.equals(IJSFConstants.ATTR_CURRENCYSYMBOL)
                || attribute.equals(IJSFConstants.ATTR_PATTERN))
        {
            IDialogFieldApplyListener listener = new IDialogFieldApplyListener()
            {
                public void dialogFieldApplied(DialogField field)
                {
                    updateFieldData();
                }
            };
            return listener;
        }
        else if (attribute.equals(IJSFConstants.ATTR_TYPE))
        {
            IDialogFieldApplyListener listener = new IDialogFieldApplyListener()
            {
                public void dialogFieldApplied(DialogField field)
                {
                    updateFieldStatus();
                    updateFieldData();
                }
            };
            return listener;
        }
        else
        {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.pagedesigner.properties.attrgroup.AttributeGroup#refreshData()
     */
    public void refreshData()
    {
        IDOMElement element = getElement();

        String type = element.getAttribute(IJSFConstants.ATTR_TYPE);
        if (!_typeField.getText().equalsIgnoreCase(TYPES[3]))
        {
            _typeField.setTextWithoutUpdate(type);
        }

        String currencyCode = element.getAttribute(IJSFConstants.ATTR_CURRENCYCODE);
        _currencyCodeField.setTextWithoutUpdate(currencyCode);

        String currencySymbol = element.getAttribute(IJSFConstants.ATTR_CURRENCYSYMBOL);
        _currencySymbolField.setTextWithoutUpdate(currencySymbol);

        String pattern = element.getAttribute(IJSFConstants.ATTR_PATTERN);
        _patternField.setTextWithoutUpdate(pattern);

        updateFieldStatus();
    }

    private void updateFieldStatus()
    {
        String type = _typeField.getText();

        if (type.equalsIgnoreCase(TYPES[0]))
        {
            _currencyCodeField.setEnabled(false);
            _currencySymbolField.setEnabled(false);
            _patternField.setEnabled(false);
        }
        else if (type.equalsIgnoreCase(TYPES[1]))
        {
            _currencyCodeField.setEnabled(true);
            _currencySymbolField.setEnabled(true);
            _patternField.setEnabled(false);
        }
        else if (type.equalsIgnoreCase(TYPES[2]))
        {
            _currencyCodeField.setEnabled(false);
            _currencySymbolField.setEnabled(false);
            _patternField.setEnabled(false);
        }
        else if (type.equalsIgnoreCase(TYPES[3]))
        {
            _currencyCodeField.setEnabled(false);
            _currencySymbolField.setEnabled(false);
            _patternField.setEnabled(true);
        }
    }

    private void updateFieldData()
    {
        String type = _typeField.getText();
        String currencyCode = _currencyCodeField.getText();
        String currencySymbol = _currencySymbolField.getText();
        String pattern = _patternField.getText();

        // update the model
        if (type.equalsIgnoreCase(TYPES[3]))
        {
            type = "";
        }

        if (!_currencyCodeField.isEnabled())
        {
            currencyCode = "";
        }

        if (!_currencySymbolField.isEnabled())
        {
            currencySymbol = "";
        }

        if (!_patternField.isEnabled())
        {
            pattern = "";
        }

        Map map = new HashMap();
        map.put(IJSFConstants.ATTR_TYPE, type);
        map.put(IJSFConstants.ATTR_CURRENCYCODE, currencyCode);
        map.put(IJSFConstants.ATTR_CURRENCYSYMBOL, currencySymbol);
        map.put(IJSFConstants.ATTR_PATTERN, pattern);

        ChangeAttributeCommand c = new ChangeAttributeCommand(SectionResources
                .getString("JSFCoreConvertNumberSection.CommandLabel.ChangeAttribute"), getElement(), map);
        c.execute();
    }
}
