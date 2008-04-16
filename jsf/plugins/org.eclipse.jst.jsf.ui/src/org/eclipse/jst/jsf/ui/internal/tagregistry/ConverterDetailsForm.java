package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.swt.widgets.Composite;

/**
 * Details form for a converter tag.
 * 
 * @author cbateman
 *
 */
public class ConverterDetailsForm extends AbstractXMLSectionsDetailsForm
{
    private final static String  CONVERTER_TYPE_SECTION_KEY = "converterSection";
    private XMLTextSection       _converterTypeSection;

    @Override
    protected Map<? extends Object, XMLTextSection> createXMLTextSections(Composite parent)
    {
        final Map<String, XMLTextSection> sections = new HashMap<String, XMLTextSection>();
        _converterTypeSection = new XMLTextSection(getToolkit(), parent, "Converter Type Information");
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
                final String formatText = "<form><p><b>Class:</b> %s</p>  <p><b>Converter Id:</b> %s</p></form>";
                final String className = typeInfo.getClassName();
                final String converterId = typeInfo.getConverterId();

                _converterTypeSection.setText(String.format(formatText,
                        className == null ? "" : className,
                        converterId == null ? "" : converterId), true, false);
                _converterTypeSection.refresh();
            }
        }
    }
}
