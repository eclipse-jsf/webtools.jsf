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

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;


/**
 * Details form for a namespace
 * 
 * @author cbateman
 *
 */
public class NamespaceDetailsForm extends AbstractXMLSectionsDetailsForm
{
    private final static String  NAMESPACE_SECTION_KEY = "namespaceSection"; //$NON-NLS-1$
    private XMLTextSection       _namespaceSection;

    @Override
    protected Map<? extends Object, XMLTextSection> createXMLTextSections(Composite parent)
    {
        final Map<String, XMLTextSection> sections = new HashMap<String, XMLTextSection>();
        _namespaceSection = new XMLTextSection(getToolkit(), parent, Messages.NamespaceDetailsForm_SectionLabel);
        sections.put(NAMESPACE_SECTION_KEY, _namespaceSection);
        return sections;
    }

    @Override
    protected Set<XMLTextSection> getInitiallyExpanded(
            Map<Object, XMLTextSection> sections)
    {
        return Collections.singleton(_namespaceSection);
    }

    @Override
    protected void doUpdateSelection(Object newSelection)
    {
        if (newSelection instanceof Namespace)
            
        {
            Namespace namespace = (Namespace) newSelection;

            final String displayName = namespace.getDisplayName();
            final String uri = namespace.getNSUri();

            String namespaceSectionText = NLS.bind(Messages.NamespaceDetailsForm_SectionText, displayName == null ? "" : displayName, uri == null ? "" : uri ); //$NON-NLS-1$ //$NON-NLS-2$
            
            _namespaceSection.setText(namespaceSectionText, true, false);
            _namespaceSection.refresh();
        }
    }
}
