package org.eclipse.jst.jsf.ui.internal.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.jsf.common.runtime.internal.model.IDesigntimeAdapter;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo.ComponentBeanProperty;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ComponentDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.FacetDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.swt.widgets.Composite;

/**
 * Detail section for a component instance.
 * 
 * @author cbateman
 * 
 */
/* package */class ComponentInstanceDetailsForm extends
        AbstractXMLSectionsDetailsForm
{

    private static final String COMPONENT_SECTION_KEY      = "componentSection";
    private final static String COMPONENT_TYPE_SECTION_KEY = "componentTypeSection";
    private static final String COMPONENT_INTERFACES_KEY   = "componentInterfacesSection";
    private static final String COMPONENT_DECORATORS_KEY = "componentDecorators";
    private static final String COMPONENT_PROPERTIES_KEY = "componentProperties";
    private final LabelProvider _labelProvider = new MyLabelProvider();

    private XMLTextSection      _componentTypeSection;
    private XMLTextSection      _componentSection;
    private XMLTextSection      _componentInterfacesSection;
    private XMLTextSection      _componentDecoratorsSection;
    private XMLTextSection _componentPropertiesSection;
    

    @Override
    protected void doUpdateSelection(final Object newSelection)
    {
        if (newSelection instanceof ComponentInfo)
        {
            final ComponentInfo compInfo = (ComponentInfo) newSelection;
            updateComponentSection(compInfo);
            updateComponentDecoratorsSection(compInfo);
            updateComponentPropertiesSection(compInfo);
            final ComponentTypeInfo typeInfo = compInfo.getComponentTypeInfo();
            if (typeInfo != null)
            {
                updateComponentTypeSection(typeInfo);
                updateComponentInterfacesSection(compInfo,typeInfo);
            }
        }
    }

    private void updateComponentSection(final ComponentInfo compInfo)
    {
        final String formatText = "<form>%s</form>";
        final String className = compInfo.getComponentTypeInfo().getClassName();
        final String id = compInfo.getId();
        final ComponentInfo parent = compInfo.getParent();
        final Map<String, String> values = new HashMap<String, String>();
        values.put("Name", className != null ? Signature
                .getSimpleName(className) : "");
        values.put("Id", id);
        values
                .put("Parent Id", parent != null ? parent.getId()
                        : "<i>none</i>");

        _componentSection.setText(String
                .format(formatText, createLines(values)), true, false);
        _componentSection.refresh();
    }

    private void updateComponentTypeSection(final ComponentTypeInfo typeInfo)
    {
        final String formatText = "<form>%s</form>";
        final String componentType = typeInfo.getComponentType();
        final String componentFamily = typeInfo.getComponentFamily();
        final String renderType = typeInfo.getRenderFamily();
        final String componentClass = typeInfo.getClassName();
        final Map<String, String> values = new HashMap<String, String>();

        values
                .put("Component Type", componentType == null ? ""
                        : componentType);
        values.put("Component Family", componentFamily == null ? ""
                : componentFamily);
        values.put("Render Type", renderType == null ? "" : renderType);
        values.put("Component Class", componentClass == null ? ""
                : componentClass);

        _componentTypeSection.setText(String.format(formatText,
                createLines(values)), true, false);
        _componentTypeSection.refresh();
    }

    private void updateComponentInterfacesSection(final ComponentInfo compInfo,
            final ComponentTypeInfo typeInfo)
    {
        final Set<String> interfaces = new HashSet<String>();

        interfaces.addAll(Arrays.asList(typeInfo.getInterfaces()));

        for (final Map.Entry entry : (Set<Map.Entry>) compInfo.getAllAdapters()
                .entrySet())
        {
            final Object infObject = entry.getValue();
            if (infObject instanceof IDesigntimeAdapter)
            {
                interfaces.addAll(Arrays.asList(((IDesigntimeAdapter)infObject).getInterfaces()));
            }
        }
        final List<String> sortedInterfaceNames = new ArrayList<String>(
                interfaces);
        Collections.sort(sortedInterfaceNames);

        String text = "";
        for (final String name : sortedInterfaceNames)
        {
            text += createLine(null, name);
        }

        _componentInterfacesSection.setText(String.format("<form>%s</form>",
                text), true, false);
        _componentInterfacesSection.refresh();
    }
    
    private void updateComponentDecoratorsSection(final ComponentInfo compInfo)
    {
        List<String>  decoratorLines = new ArrayList<String>();
        String text = "";
        for (final ComponentDecorator decorator : (List<ComponentDecorator>)compInfo.getAllDecorators())
        {
            String labelText = _labelProvider.getText(decorator);
            
            if (labelText != null)
            {
                text += createLine(null, labelText);
            }
        }
        Collections.sort(decoratorLines);

        
        _componentDecoratorsSection.setText(String.format("<form>%s</form>",
                text), true, false);
        _componentDecoratorsSection.refresh();
    }

    private void updateComponentPropertiesSection(final ComponentInfo compInfo)
    {
        List<String>  decoratorLines = new ArrayList<String>();
        String text = "";
        Set<String>  propNames = compInfo.getAttributeNames();
        
        for (final String propName : propNames)
        {
            final ComponentBeanProperty propValue = compInfo.getAttribute(propName);
            if (propValue != null)
            {
                Object value = propValue.getValue();
                if (value != null)
                {
                    decoratorLines.add(createLine(propName, 
                            value.toString()));
                }
            }
        }
        _componentPropertiesSection.setText(String.format("<form>%s</form>",
                text), true, false);
        _componentPropertiesSection.refresh();
        
    }
    
    @Override
    protected Map<? extends Object, XMLTextSection> createXMLTextSections(
            final Composite parent)
    {
        final Map<String, XMLTextSection> sections = new HashMap<String, XMLTextSection>();
        _componentSection = new XMLTextSection(getToolkit(), parent,
                "Instance Info");
        sections.put(COMPONENT_SECTION_KEY, _componentSection);

        _componentTypeSection = new XMLTextSection(getToolkit(), parent,
                "Type Info Information");
        sections.put(COMPONENT_TYPE_SECTION_KEY, _componentTypeSection);

        _componentInterfacesSection = new XMLTextSection(getToolkit(), parent,
                "Interfaces");
        sections.put(COMPONENT_INTERFACES_KEY, _componentInterfacesSection);
        
        _componentDecoratorsSection = new XMLTextSection(getToolkit(), parent,
                "Decorators");
        sections.put(COMPONENT_DECORATORS_KEY, _componentDecoratorsSection);
        
        _componentPropertiesSection = new XMLTextSection(getToolkit(), parent,
                "Properties");
        sections.put(COMPONENT_PROPERTIES_KEY, _componentPropertiesSection);

        return sections;
    }

    @Override
    protected Set<XMLTextSection> getInitiallyExpanded(
            final Map<Object, XMLTextSection> sections)
    {
        return Collections.singleton(_componentSection);
    }

    private String createLines(final Map<String, String> values)
    {
        String lines = "";
        for (final Map.Entry<String, String> valueEntry : values.entrySet())
        {
            final String title = valueEntry.getKey();
            final String value = valueEntry.getValue();

            lines += createLine(title, value);
        }
        return lines;
    }

    private String createLine(final String title, final String value)
    {
        if (title == null)
        {
            return String.format("<p>%s</p>", value);
        }
        return String.format("<p><b>%s</b>: %s</p>", title, value);
    }
    
    private static class MyLabelProvider extends LabelProvider
    {
        @Override
        public String getText(Object element)
        {
            if (element instanceof ComponentDecorator)
            {
                if  (element instanceof ConverterDecorator)
                {
                    final ConverterDecorator converter = (ConverterDecorator) element;
                    return "Converter: id=\""+
                        converter.getTypeInfo().getConverterId()
                        +"\", converter-class="+converter.getTypeInfo().getClassName();
                }
                else if (element instanceof ValidatorDecorator)
                {
                    final ValidatorDecorator validator = (ValidatorDecorator) element;
                    return "Validator: id=\""+
                        validator.getTypeInfo().getValidatorId()
                        +"\", validator-class="+validator.getTypeInfo().getClassName();
                }
                else if (element instanceof FacetDecorator)
                {
                    final FacetDecorator decorator = (FacetDecorator) element;
                    return "Facet: "+decorator.getName()+", component="+decorator.getDecorates().getId();
                }
            }
            
            return null;
        }
        
    }
}
