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

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;

/**
 * Details form for validator tags.
 * @author cbateman
 *
 */
public class ValidatorDetailsForm extends AbstractXMLSectionsDetailsForm
{
    private final static String  VALIDATOR_TYPE_SECTION_KEY = "validatorSection"; //$NON-NLS-1$
    private XMLTextSection       _validatorTypeSection;

    @Override
    protected Map<? extends Object, XMLTextSection> createXMLTextSections(Composite parent)
    {
        final Map<String, XMLTextSection> sections = new HashMap<String, XMLTextSection>();
        _validatorTypeSection = new XMLTextSection(getToolkit(), parent, Messages.ValidatorDetailsForm_SectionLabel);
        sections.put(VALIDATOR_TYPE_SECTION_KEY, _validatorTypeSection);
        return sections;
    }


    @Override
    protected Set<XMLTextSection> getInitiallyExpanded(
            Map<Object, XMLTextSection> sections)
    {
        return Collections.singleton(_validatorTypeSection);
    }

    @Override
    protected void doUpdateSelection(Object newSelection)
    {
        if (newSelection instanceof IValidatorTagElement)
        {
            IValidatorTagElement validatorTagElement = (IValidatorTagElement) newSelection;
            final ValidatorTypeInfo typeInfo = validatorTagElement.getValidator();

            if (typeInfo != null)
            {
                final String className = typeInfo.getClassName();
                final String validatorId = typeInfo.getValidatorId();

                String text = NLS.bind(Messages.ValidatorDetailsForm_SectionText, className == null ? "" : className, validatorId == null ? "" : validatorId); //$NON-NLS-1$ //$NON-NLS-2$
                _validatorTypeSection.setText(text, true, false); 

                _validatorTypeSection.refresh();
            }
        }
    }
}
