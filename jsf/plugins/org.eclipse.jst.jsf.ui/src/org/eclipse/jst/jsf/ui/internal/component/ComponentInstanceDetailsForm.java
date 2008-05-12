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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo.ComponentBeanProperty;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ComponentDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.FacetDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractXMLSectionsDetailsForm;
import org.eclipse.jst.jsf.ui.internal.common.ViewObjectPresenter;
import org.eclipse.jst.jsf.ui.internal.common.ViewObjectPresenter.TitleValuePair;
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
    private static final String COMPONENT_DECORATORS_KEY   = "componentDecorators";
    private static final String COMPONENT_PROPERTIES_KEY   = "componentProperties";
    private final LabelProvider _labelProvider             = new MyLabelProvider();

    private XMLTextSection      _componentTypeSection;
    private XMLTextSection      _componentSection;
    private XMLTextSection      _componentInterfacesSection;
    private XMLTextSection      _componentDecoratorsSection;
    private XMLTextSection      _componentPropertiesSection;

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
                updateComponentInterfacesSection(compInfo, typeInfo);
            }
        }
    }

    private void updateComponentSection(final ComponentInfo compInfo)
    {
        final String formatText = "<form>%s</form>";
        final String className = compInfo.getComponentTypeInfo().getClassName();
        final String id = compInfo.getId();
        final ComponentInfo parent = compInfo.getParent();
        final List<TitleValuePair> values = new ArrayList<TitleValuePair>();
        values.add(new TitleValuePair("Name", className != null ? Signature
                .getSimpleName(className) : ""));
        values.add(new TitleValuePair("Id", id != null ? id : "<i>none</i>"));
        values.add(new TitleValuePair("Parent Id", (parent != null && parent
                .getId() != null) ? parent.getId() : "<i>none</i>"));

        _componentSection.setText(String.format(formatText, ViewObjectPresenter
                .createLines(values)), true, false);
        _componentSection.refresh();
    }

    private void updateComponentTypeSection(final ComponentTypeInfo typeInfo)
    {
        _componentTypeSection.setText(ViewObjectPresenter.present(typeInfo),
                true, false);
        _componentTypeSection.refresh();
    }

    private void updateComponentInterfacesSection(final ComponentInfo compInfo,
            final ComponentTypeInfo typeInfo)
    {
        _componentInterfacesSection.setText(ViewObjectPresenter
                .presentCompInterfaces(typeInfo, compInfo), true, false);
        _componentInterfacesSection.refresh();
    }

    private void updateComponentDecoratorsSection(final ComponentInfo compInfo)
    {
        List<TitleValuePair> decoratorLines = new ArrayList<TitleValuePair>();
        String text = "";
        for (final ComponentDecorator decorator : (List<ComponentDecorator>) compInfo
                .getAllDecorators())
        {
            String labelText = _labelProvider.getText(decorator);

            if (labelText != null)
            {
                //text += ViewObjectPresenter.createLine(null, labelText);
                decoratorLines.add(new TitleValuePair(null, labelText));
            }
        }
        Collections.sort(decoratorLines);
        text = ViewObjectPresenter.createLines(decoratorLines);
        _componentDecoratorsSection.setText(String.format("<form>%s</form>",
                text), true, false);
        _componentDecoratorsSection.refresh();
    }

    private void updateComponentPropertiesSection(final ComponentInfo compInfo)
    {
        List<TitleValuePair> propertyLines = new ArrayList<TitleValuePair>();
        String text = "";
        Set<String> propNames = compInfo.getAttributeNames();

        for (final String propName : propNames)
        {
            final ComponentBeanProperty propValue = compInfo
                    .getAttribute(propName);
            if (propValue != null)
            {
                Object value = propValue.getValue();
                if (value != null)
                {
                    propertyLines.add(new TitleValuePair(propName, value.toString()));
                }
            }
        }
        Collections.sort(propertyLines);
        text = ViewObjectPresenter.createLines(propertyLines);
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

    private static class MyLabelProvider extends LabelProvider
    {
        @Override
        public String getText(Object element)
        {
            if (element instanceof ComponentDecorator)
            {
                if (element instanceof ConverterDecorator)
                {
                    final ConverterDecorator converter = (ConverterDecorator) element;
                    return "Converter: id=\""
                            + converter.getTypeInfo().getConverterId()
                            + "\", converter-class="
                            + converter.getTypeInfo().getClassName();
                }
                else if (element instanceof ValidatorDecorator)
                {
                    final ValidatorDecorator validator = (ValidatorDecorator) element;
                    return "Validator: id=\""
                            + validator.getTypeInfo().getValidatorId()
                            + "\", validator-class="
                            + validator.getTypeInfo().getClassName();
                }
                else if (element instanceof FacetDecorator)
                {
                    final FacetDecorator decorator = (FacetDecorator) element;
                    return "Facet: " + decorator.getName() + ", component="
                            + decorator.getDecorates().getId();
                }
            }

            return null;
        }

    }
}
