package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.swt.widgets.Composite;

/**
 * Details page for component details.
 * 
 * @author cbateman
 * 
 */
public class ComponentDetailSubForm extends AbstractXMLSectionsDetailsForm
{
    private final static String  COMPONENT_TYPE_SECTION_KEY = "componentSection";
    private XMLTextSection       _componentTypeSection;

    @Override
    protected Map<? extends Object, XMLTextSection> createXMLTextSections(Composite parent)
    {
        final Map<String, XMLTextSection> sections = new HashMap<String, XMLTextSection>();
        _componentTypeSection = new XMLTextSection(getToolkit(), parent, "Component Type Information");
        sections.put(COMPONENT_TYPE_SECTION_KEY, _componentTypeSection);
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
                final String formatText = "<form><p><b>Component Type:</b> %s</p> <p><b>Component Family:</b> %s</p> <p><b>Render Type:</b> %s</p><p><b>Component Class:</b> %s</p></form>";
                final String componentType = typeInfo.getComponentType();
                final String componentFamily = typeInfo.getComponentFamily();
                final String renderType = typeInfo.getRenderFamily();
                final String componentClass = typeInfo.getClassName();
                _componentTypeSection.setText(String.format(formatText,
                        componentType == null ? "" : componentType,
                        componentFamily == null ? "" : componentFamily,
                        renderType == null ? "" : renderType, 
                        componentClass == null ? "" : componentClass), true, false);
                _componentTypeSection.refresh();
            }
        }
    }
}
