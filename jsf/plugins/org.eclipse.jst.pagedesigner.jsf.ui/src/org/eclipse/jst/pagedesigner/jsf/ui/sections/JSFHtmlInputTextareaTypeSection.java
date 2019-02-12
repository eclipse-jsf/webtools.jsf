/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http:// https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.sections;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.LayoutUtil;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.RadiosDialogField;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.commands.single.ChangeTagCommand;
import org.eclipse.jst.pagedesigner.properties.BaseCustomSection;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;

/**
 * FIXME: there is a lot of copied code for this input sections
 * @author mengbo
 * @version 1.5
 */
public class JSFHtmlInputTextareaTypeSection extends BaseCustomSection
{
    private RadiosDialogField     _typesField;

    final private static String[] TYPES    = { SectionResources.getString("JSFHtmlInputHiddenSection.Type0"), //$NON-NLS-1$
                                           SectionResources.getString("JSFHtmlInputHiddenSection.Type1"), //$NON-NLS-1$
                                           SectionResources.getString("JSFHtmlInputHiddenSection.Type2"), //$NON-NLS-1$
                                           SectionResources.getString("JSFHtmlInputHiddenSection.Type3") //$NON-NLS-1$
                                           };

    /**
     * The default constructor 
     */
    public JSFHtmlInputTextareaTypeSection()
    {
        super();
        _typesField = new RadiosDialogField();
        _typesField.setLabelText(SectionResources.getString("JSFHtmlInputHiddenSection.Type")); //$NON-NLS-1$
        _typesField.setItems(TYPES);
        _typesField.setDialogFieldApplyListener(new IDialogFieldApplyListener()
        {
            public void dialogFieldApplied(DialogField field)
            {
                int type = _typesField.getSelectedIndex();
                String prefix = _element.getPrefix();
                String localTag = toLocalTag(type);
                String newtag = prefix + ":" + localTag; //$NON-NLS-1$
                Map attrs = new HashMap();
                if (type == IInputWidgetTypes.TEXTAREA)
                {
                    // need to change "size" attribute to "cols"
                    String size = _element.getAttribute(IJSFConstants.ATTR_SIZE);
                    if (size != null && size.length() != 0)
                    {
                        attrs.put(IJSFConstants.ATTR_SIZE, null);
                        attrs.put(IJSFConstants.ATTR_COLS, size);
                    }
                }
                else
                {
                    // need to change "cols" attribute to "size"
                    String size = _element.getAttribute(IJSFConstants.ATTR_COLS);
                    if (size != null && size.length() != 0)
                    {
                        attrs.put(IJSFConstants.ATTR_COLS, null);
                        attrs.put(IJSFConstants.ATTR_SIZE, size);
                    }
                }
                ChangeTagCommand c = new ChangeTagCommand(SectionResources
                        .getString("JSFHtmlInputHidden.CommandLabel.ChangeType"), _element, newtag, attrs, true); //$NON-NLS-1$
                c.execute();
                _element = c.getNewElement();
            }
        });
    }

    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage)
    {
        super.createControls(parent, aTabbedPropertySheetPage);
        TabbedPropertySheetWidgetFactory factory = aTabbedPropertySheetPage.getWidgetFactory();
        Composite top = factory.createFlatFormComposite(parent);

        int numberOfColumns = _typesField.getNumberOfControls();
        GridLayout layout = new GridLayout(numberOfColumns, false);
        top.setLayout(layout);

        _typesField.doFillIntoGrid(factory, top, numberOfColumns);
        LayoutUtil.setGrabHorizontal(_typesField.getGroup(factory, top), true);
    }

    private String toLocalTag(int type)
    {
        switch (type)
        {
            case IInputWidgetTypes.SECRET:
                return IJSFConstants.TAG_INPUTSECRET;
            case IInputWidgetTypes.HIDDEN:
                return IJSFConstants.TAG_INPUTHIDDEN;
            case IInputWidgetTypes.TEXTAREA:
                return IJSFConstants.TAG_INPUTTEXTAREA;
            case IInputWidgetTypes.TEXT:
                return IJSFConstants.TAG_INPUTTEXT;
            default:
                return IJSFConstants.TAG_INPUTHIDDEN;
        }
    }

    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);

        _typesField.setSelectedIndexWithoutUpdate(getInputType());
    }

    /**
     * @return one of IInputWidgetTypes
     */
    public int getInputType()
    {
        String localTag = _element.getLocalName();
        if (IJSFConstants.TAG_INPUTTEXT.equals(localTag))
        {
            return IInputWidgetTypes.TEXT;
        }
        else if (IJSFConstants.TAG_INPUTSECRET.equals(localTag))
        {
            return IInputWidgetTypes.SECRET;
        }
        else if (IJSFConstants.TAG_INPUTHIDDEN.equals(localTag))
        {
            return IInputWidgetTypes.HIDDEN;
        }
        else if (IJSFConstants.TAG_INPUTTEXTAREA.equals(localTag))
        {
            return IInputWidgetTypes.TEXTAREA;
        }
        else
        {
            return IInputWidgetTypes.HIDDEN;
        }
    }

    protected void notifyChanged(INodeNotifier notifier, int eventType, Object changedFeature, Object oldValue,
            Object newValue, int pos)
    {
        if (_typesField != null)
        {
            _typesField.setSelectedIndexWithoutUpdate(getInputType());
        }

    }
}
