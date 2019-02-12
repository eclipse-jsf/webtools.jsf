/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;

/**
 * Details form for a converter tag.
 * 
 * @author cbateman
 *
 */
public class ConverterDetailsForm extends AbstractXMLSectionsDetailsForm
{
    private final static String  CONVERTER_TYPE_SECTION_KEY = "converterSection"; //$NON-NLS-1$
    private XMLTextSection       _converterTypeSection;

    @Override
    protected Map<? extends Object, XMLTextSection> createXMLTextSections(Composite parent)
    {
        final Map<String, XMLTextSection> sections = new HashMap<String, XMLTextSection>();
        _converterTypeSection = new XMLTextSection(getToolkit(), parent, Messages.ConverterDetailsForm_ConverterInfo);
        sections.put(CONVERTER_TYPE_SECTION_KEY, _converterTypeSection);
        return sections;
    }


    @Override
    protected Set<XMLTextSection> getInitiallyExpanded(
            Map<Object, XMLTextSection> sections)
    {
        return Collections.singleton(_converterTypeSection);
    }

    @Override
    protected void doUpdateSelection(Object newSelection)
    {
        if (newSelection instanceof IConverterTagElement)
        {
            IConverterTagElement converterTagElement = (IConverterTagElement) newSelection;
            final ConverterTypeInfo typeInfo = converterTagElement.getConverter();

            if (typeInfo != null)
            {
                final String className = typeInfo.getClassName();
                final String converterId = typeInfo.getConverterId();

                final String formatText = "<form><p><b>{0}</b> {1}</p>  <p><b>{2}</b> {3}</p></form>"; //$NON-NLS-1$
                Object[] bindings = new String[4];
                bindings[0] = Messages.ConverterDetailsForm_Class;
                bindings[1] = className == null ? "" : className; //$NON-NLS-1$
                bindings[2] = Messages.ConverterDetailsForm_Converterid;
                bindings[3] = converterId == null ? "" : converterId; //$NON-NLS-1$
                String text = NLS.bind(formatText, bindings);
                _converterTypeSection.setText(text, true, false); 
                
                _converterTypeSection.refresh();
            }
        }
    }
}
