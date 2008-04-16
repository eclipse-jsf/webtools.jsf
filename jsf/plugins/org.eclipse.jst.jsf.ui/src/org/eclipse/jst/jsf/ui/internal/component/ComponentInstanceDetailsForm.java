package org.eclipse.jst.jsf.ui.internal.component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.swt.widgets.Composite;

/**
 * Detail section for a component instance.
 * 
 * @author cbateman
 *
 */
/*package*/ class ComponentInstanceDetailsForm extends
AbstractXMLSectionsDetailsForm
{

    private final static String  COMPONENT_SECTION_KEY = "componentSection";
    private XMLTextSection       _componentSection;

    @Override
    protected void doUpdateSelection(final Object newSelection)
    {
        if (newSelection instanceof ComponentInfo)
        {
            final ComponentInfo compInfo = (ComponentInfo) newSelection;
            final ComponentTypeInfo typeInfo = compInfo.getComponentTypeInfo();
            if (typeInfo != null)
            {
                final String formatText = "<form><p><b>Component Type:</b> %s</p> <p><b>Component Family:</b> %s</p> <p><b>Render Type:</b> %s</p><p><b>Component Class:</b> %s</p></form>";
                final String componentType = typeInfo.getComponentType();
                final String componentFamily = typeInfo.getComponentFamily();
                final String renderType = typeInfo.getRenderFamily();
                final String componentClass = typeInfo.getClassName();
                _componentSection.setText(String.format(formatText,
                        componentType == null ? "" : componentType,
                                componentFamily == null ? "" : componentFamily,
                                        renderType == null ? "" : renderType,
                                                componentClass == null ? "" : componentClass), true, false);
                _componentSection.refresh();
            }
        }
    }

    @Override
    protected Map<? extends Object, XMLTextSection> createXMLTextSections(final Composite parent)
    {
        final Map<String, XMLTextSection> sections = new HashMap<String, XMLTextSection>();
        _componentSection = new XMLTextSection(getToolkit(), parent, "Component Instance Information");
        sections.put(COMPONENT_SECTION_KEY, _componentSection);
        return sections;
    }


    @Override
    protected Set<XMLTextSection> getInitiallyExpanded(
            final Map<Object, XMLTextSection> sections)
            {
        return Collections.singleton(_componentSection);
            }
}
