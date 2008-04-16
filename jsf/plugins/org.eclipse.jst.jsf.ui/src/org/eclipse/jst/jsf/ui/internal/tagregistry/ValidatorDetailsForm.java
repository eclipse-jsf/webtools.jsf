package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.swt.widgets.Composite;

/**
 * Details form for validator tags.
 * @author cbateman
 *
 */
public class ValidatorDetailsForm extends AbstractXMLSectionsDetailsForm
{
    private final static String  VALIDATOR_TYPE_SECTION_KEY = "validatorSection";
    private XMLTextSection       _validatorTypeSection;

    @Override
    protected Map<? extends Object, XMLTextSection> createXMLTextSections(Composite parent)
    {
        final Map<String, XMLTextSection> sections = new HashMap<String, XMLTextSection>();
        _validatorTypeSection = new XMLTextSection(getToolkit(), parent, "Validator Type Information");
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
                final String formatText = "<form><p><b>Class:</b> %s</p>  <p><b>Validator Id:</b> %s</p></form>";
                final String className = typeInfo.getClassName();
                final String validatorId = typeInfo.getValidatorId();

                _validatorTypeSection.setText(String.format(formatText,
                        className == null ? "" : className,
                        validatorId == null ? "" : validatorId), true, false);
                _validatorTypeSection.refresh();
            }
        }
    }
}
