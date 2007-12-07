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

import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StyleComboDialogField;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.commands.single.ChangeAttributeCommand;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.jst.pagedesigner.meta.EditorCreator;
import org.eclipse.jst.pagedesigner.properties.internal.QuickEditAttributeGroup;
import org.eclipse.jst.pagedesigner.ui.dialogfields.DialogFieldWrapper;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

/**
 * @author mengbo
 */
public class JSFCoreConvertNumberGroup extends QuickEditAttributeGroup
{
    private StyleComboDialogField _typeField;
    private StyleComboDialogField _patternField;
    private StyleComboDialogField _currencyCodeField;
    private StringDialogField     _currencySymbolField;

    final private static String[] TYPES = { "number", "currency", "percentage", "custom" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                                        };

    /**
     * The default constructor
     */
    public JSFCoreConvertNumberGroup()
    {
        super(ITLDConstants.URI_JSF_CORE, IJSFConstants.TAG_CONVERTNUMBER, new String[] { IJSFConstants.ATTR_TYPE,
                IJSFConstants.ATTR_CURRENCYCODE, IJSFConstants.ATTR_CURRENCYSYMBOL, IJSFConstants.ATTR_PATTERN});
    }

    protected DialogField createDialogField(IPropertyPageDescriptor ppd)
    {
    	EditorCreator creator = EditorCreator.getInstance();
        if (ppd.getAttributeName().equals(IJSFConstants.ATTR_TYPE))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
            	.createDialogFieldWithWrapper(getURI(), getTagName(), ppd, null);
            _typeField = (StyleComboDialogField) wrapper.getWrappedDialogField();
                    _typeField.setItems(TYPES);
            return wrapper;
        }
        else if (ppd.getAttributeName().equals(IJSFConstants.ATTR_CURRENCYCODE))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
            	.createDialogFieldWithWrapper(getURI(), getTagName(), ppd, null);
            _currencyCodeField = (StyleComboDialogField) wrapper.getWrappedDialogField();
            return wrapper;
        }
        else if (ppd.getAttributeName().equals(IJSFConstants.ATTR_CURRENCYSYMBOL))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
            		.createDialogFieldWithWrapper(getURI(), getTagName(), ppd, null);
            _currencySymbolField = (StringDialogField) wrapper.getWrappedDialogField();
            return wrapper;
        }
        else if (ppd.getAttributeName().equals(IJSFConstants.ATTR_PATTERN))
        {
            DialogFieldWrapper wrapper = (DialogFieldWrapper) creator
            	.createDialogFieldWithWrapper(getURI(), getTagName(), ppd, null);
            _patternField = (StyleComboDialogField) wrapper.getWrappedDialogField();
            return wrapper;
        }
        else
        {
            return null;
        }
    }

    public IDialogFieldApplyListener getDialogFieldApplyListener(IPropertyPageDescriptor ppd)
    {
        String attribute = ppd.getAttributeName();
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
