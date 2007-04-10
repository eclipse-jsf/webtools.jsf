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
package org.eclipse.jst.pagedesigner.jsf.ui.actions;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;

import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.properties.attrgroup.DialogUtil;

/**
 * @author mengbo
 * @version 1.5
 */
public class AddAttributeAction extends JSFAddChildAction
{

    /**
     * @param parentNode
     */
    public AddAttributeAction(IDOMElement parentNode)
    {
        super(ActionsResources.getString("AddAttributeAction.ActionLabel.Attribute"), parentNode); //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run()
    {
        DialogUtil.createSubElement(getShell(), getParentElement(), ITLDConstants.URI_JSF_CORE, "attribute", null);
//        CommonDialogFieldGroup group = new CommonDialogFieldGroup();
//
//        AttributeData[] data = createAttributeData();
//        group.setAttributeData(data);
//
//        DialogFieldGroupPage page = new DialogFieldGroupPage("Attribute", group); //$NON-NLS-1$
//        page.setTitle(ActionsResources.getString("AddAttributeAction.Wizard.NewAttribute")); //$NON-NLS-1$
//        page.setDescription(ActionsResources.getString("AddAttributeAction.Wizard.Description")); //$NON-NLS-1$
//        CommonDialogFieldWizard wizard = new CommonDialogFieldWizard(page);
//        wizard.setWindowTitle(ActionsResources.getString("AddAttributeAction.Wizard.NewAttribute")); //$NON-NLS-1$
//        WizardDialog dialog = new WizardDialog(getShell(), wizard);
//        dialog.create();
//        if (dialog.open() == WizardDialog.OK)
//        {
//            group.prepareData();
//            Map attributesMap = new HashMap();
//            for (int i = 0; i < data.length; i++)
//            {
//                attributesMap.put(data[i].attributeName, data[i].value);
//            }
//            AddJSFCoreChildCommand command = new AddJSFCoreChildCommand(this.getParentElement(),
//                IJSFConstants.TAG_ATTRIBUTE, attributesMap);
//            command.execute();
//        }
    }

//    private AttributeData[] createAttributeData()
//    {
//        return new AttributeData[] 
//        {
//            new AttributeData(IJMTConstants.URI_JSF_CORE, IJSFConstants.TAG_ATTRIBUTE, IJSFConstants.ATTR_NAME),
//                new AttributeData(IJMTConstants.URI_JSF_CORE, IJSFConstants.TAG_ATTRIBUTE, IJSFConstants.ATTR_VALUE)
//        }
//        ;
//    }
}
