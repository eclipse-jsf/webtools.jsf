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
package org.eclipse.jst.jsf.ui.internal.component;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractDetailsForm;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractMasterDetailBlock;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractMasterForm;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/*package*/ class ComponentMasterDetailBlock extends AbstractMasterDetailBlock
{
    private final DTJSFViewModel _model;
    private AbstractXMLSectionsDetailsForm _componentInstanceDetailsForm;

    public ComponentMasterDetailBlock(final DTJSFViewModel model)
    {
        _model = model;
    }

    @Override
    protected List<AbstractDetailsForm> createDetailPages()
    {
        final List<AbstractDetailsForm> pages =
            new ArrayList<AbstractDetailsForm>();

        _componentInstanceDetailsForm = new ComponentInstanceDetailsForm();
        pages.add(_componentInstanceDetailsForm);

        return pages;
    }

    @Override
    protected AbstractMasterForm createMasterPart(final FormToolkit toolkit)
    {
        return new ComponentMasterForm(toolkit, _model);
    }

    @Override
    protected AbstractXMLSectionsDetailsForm doSelectPage(final Object forModel)
    {
        if (forModel instanceof ComponentInfo)
        {
            return _componentInstanceDetailsForm;
        }
        return null;
    }

}
