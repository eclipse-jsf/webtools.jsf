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
package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.jst.jsf.ui.internal.common.ViewObjectPresenter;
import org.eclipse.swt.widgets.Composite;

/**
 * Details page for component details.
 * 
 * @author cbateman
 * 
 */
public class ComponentDetailSubForm extends AbstractXMLSectionsDetailsForm
{
    private final static String  COMPONENT_TYPE_SECTION_KEY = "componentSection"; //$NON-NLS-1$
    private static final String INTERFACES_SECTION_KEY = "interfacesSection"; //$NON-NLS-1$
    private XMLTextSection       _componentTypeSection;
    private XMLTextSection       _interfacesSection;

    @Override
    protected Map<? extends Object, XMLTextSection> createXMLTextSections(Composite parent)
    {
        final Map<String, XMLTextSection> sections = new HashMap<String, XMLTextSection>();
        _componentTypeSection = new XMLTextSection(getToolkit(), parent, Messages.ComponentDetailSubForm_TypeInfo);
        sections.put(COMPONENT_TYPE_SECTION_KEY, _componentTypeSection);
        
        _interfacesSection = new XMLTextSection(getToolkit(), parent, Messages.ComponentDetailSubForm_InterfaceInfo);
        sections.put(INTERFACES_SECTION_KEY, _interfacesSection);
        return sections;
    }


    @Override
    protected Set<XMLTextSection> getInitiallyExpanded(
            Map<Object, XMLTextSection> sections)
    {
        return Collections.singleton(_componentTypeSection);
    }


    @Override
    protected void doUpdateSelection(final Object newSelection)
    {
        if (newSelection instanceof IComponentTagElement)
        {
            final IComponentTagElement curTagElement = (IComponentTagElement) newSelection;
            final ComponentTypeInfo typeInfo = curTagElement.getComponent();
            if (typeInfo != null)
            {
                _componentTypeSection.setText(ViewObjectPresenter.present(typeInfo), true, false);
                _interfacesSection.setText(ViewObjectPresenter.presentCompInterfaces(typeInfo, null), true, false);
                _componentTypeSection.refresh();
            }
        }
    }
}
