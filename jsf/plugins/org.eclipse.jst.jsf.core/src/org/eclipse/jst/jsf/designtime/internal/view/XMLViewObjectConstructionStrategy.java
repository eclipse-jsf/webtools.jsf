package org.eclipse.jst.jsf.designtime.internal.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.ActionSourceInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.ActionSourceInfo2;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.EditableValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.INamingContainerInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.ValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;
import org.eclipse.jst.jsf.common.util.JDTBeanProperty;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * A strategy for constructing view objects.
 * 
 * @author cbateman
 * 
 */
public class XMLViewObjectConstructionStrategy extends
        ViewObjectConstructionStrategy<Element>
{
    private static final String GENERATED_ID = "_generatedId";
    private final ComponentConstructionData _constructionData;
    private final XMLViewDefnAdapter _adapter;

    /**
     * @param adapter
     * @param constructionData
     */
    public XMLViewObjectConstructionStrategy(final XMLViewDefnAdapter adapter,
            final ComponentConstructionData constructionData)
    {
        super();
        _constructionData = constructionData;
        _adapter = adapter;
    }

    @Override
    public ViewObject createViewObject(final Element element,
            final ITagElement tagElement)
    {
        try
        {
            if (tagElement instanceof IComponentTagElement)
            {
                final ComponentTypeInfo typeInfo = ((IComponentTagElement) tagElement)
                        .getComponent();
                String id = null;

                // only generate ids for non-viewroot components. This will
                // make the generated id's more faithful to runtime since the
                // running count won't be incremented for view roots (as they
                // won't
                // at runtime).
                if (!"javax.faces.ViewRoot".equals(typeInfo.getComponentType()))
                {
                    id = calculateId(element, _constructionData);
                }
                return findBestComponent(element, id, typeInfo);
            }
            else if (tagElement instanceof IConverterTagElement)
            {
                final ConverterTypeInfo typeInfo = ((IConverterTagElement) tagElement)
                        .getConverter();
                // TODO: validate when no parent
                return new ConverterDecorator(_constructionData.getParent(),
                        typeInfo);
            }
            else if (tagElement instanceof IValidatorTagElement)
            {
                final ValidatorTypeInfo typeInfo = ((IValidatorTagElement) tagElement)
                        .getValidator();
                return new ValidatorDecorator(_constructionData.getParent(),
                        typeInfo);
            }
        }
        catch (Exception e)
        {
            // log and ignore if an individual construction fails
            JSFCorePlugin.log(e, "Error constructing view object");
        }
        return null;
    }

    private ComponentInfo findBestComponent(final Element srcElement,
            final String id, final ComponentTypeInfo typeInfo)
    {
        ComponentInfo bestComponent = null;

        final ComponentInfo parent = _constructionData.getParent();

        final Map<String, Object> initMap = new HashMap();
        populateInitMap(initMap, srcElement, typeInfo);

        if (initMap.get("id") == null)
        {
            // id must be set
            initMap.put("id", id);
        }

        // if we have a well-established base type, try that first
        // sub-classes must occur before superclasses to ensure most accurate
        // detection.
        if (typeInfo.isInstanceOf(ComponentFactory.BASE_CLASS_UIINPUT))
        {
            bestComponent = ComponentFactory.createUIInputInfo(parent,
                    typeInfo, initMap);
        }
        else if (typeInfo.isInstanceOf(ComponentFactory.BASE_CLASS_UIOUTPUT))
        {
            bestComponent = ComponentFactory.createUIOutputInfo(parent,
                    typeInfo, initMap);
        }
        else if (typeInfo.isInstanceOf(ComponentFactory.BASE_CLASS_UICOMMAND))
        {
            bestComponent = ComponentFactory.createUICommandInfo(parent,
                    typeInfo, initMap);
        }
        else if (typeInfo.isInstanceOf(ComponentFactory.BASE_CLASS_UIDATA))
        {
            bestComponent = ComponentFactory.createUIDataInfo(parent, typeInfo,
                    initMap);
        }
        else if (typeInfo.isInstanceOf(ComponentFactory.BASE_CLASS_UIFORM))
        {
            // TODO: how handle prepend ids?
            bestComponent = ComponentFactory.createUIFormInfo(parent, typeInfo,
                    initMap);
        }
        else
        {
            // default
            bestComponent = ComponentFactory.createComponentInfo(
                    _constructionData.getParent(), typeInfo, initMap);
        }

        addTypeAdapters(bestComponent);
        // populateAttributes(srcElement, bestComponent);
        return bestComponent;
    }

    private void populateInitMap(final Map initMap, final Element srcElement,
            final ComponentTypeInfo typeInfo)
    {
        final Map<String, JDTBeanProperty> properties = DTComponentIntrospector
                .getBeanProperties(typeInfo, _constructionData.getProject());

        for (final Map.Entry<String, JDTBeanProperty> propertyEntry : properties
                .entrySet())
        {
            final String name = propertyEntry.getKey();

            // see if there is an attribute on srcElement
            final Attr valueAttr = _adapter.mapAttributeToComponent(srcElement,
                    name);

            if (valueAttr != null)
            {
                final String value = valueAttr.getNodeValue();

                // TODO: need to handle EL cases
                if (value != null)
                {
                    Object convertedValue = convertFromString(value,
                            propertyEntry.getValue());
                    initMap.put(name, convertedValue);
                }
            }
        }
    }

    private Object convertFromString(final String convertValue,
            final JDTBeanProperty ofThisType)
    {
        final String signature = ofThisType.getTypeSignature();

        Object result = null;
        switch (Signature.getTypeSignatureKind(signature))
        {
            case Signature.BASE_TYPE_SIGNATURE:
                result = convertFromBaseType(convertValue, signature);
                break;

            case Signature.CLASS_TYPE_SIGNATURE:
                if (TypeConstants.TYPE_STRING.equals(signature))
                {
                    result = convertValue;
                }
                break;
        }

        return result;
    }

    // TODO: does this belong somewhere else?
    private Object convertFromBaseType(final String convertValue,
            final String signature)
    {
        if (Signature.SIG_BOOLEAN.equals(signature))
        {
            return Boolean.valueOf(convertValue);
        }
        else if (Signature.SIG_INT.equals(signature)
                || Signature.SIG_BYTE.equals(signature)
                || Signature.SIG_SHORT.equals(signature))
        {
            return Integer.valueOf(convertValue);
        }
        else if (Signature.SIG_LONG.equals(convertValue))
        {
            return Long.valueOf(convertValue);
        }

        return null;
    }

    private void addTypeAdapters(final ComponentInfo component)
    {
        final String[] interfaceNames = component.getComponentTypeInfo()
                .getInterfaces();
        final Set interfaceNameSets = new HashSet();

        for (final String interfaceName : interfaceNames)
        {
            interfaceNameSets.add(interfaceName);
        }

        // don't replace intrinsic adapters
        if (interfaceNameSets.contains(ComponentFactory.INTERFACE_ACTIONSOURCE))
        {
            // an ActionSource2 is-a ActionSource
            if (interfaceNameSets
                    .contains(ComponentFactory.INTERFACE_ACTIONSOURCE2)
                    && component.getAdapter(ComponentFactory.ACTION_SOURCE2) == null)
            {
                component.addAdapter(ComponentFactory.ACTION_SOURCE2,
                        new ActionSourceInfo2(null, null, false, null));
            }

            if (component.getAdapter(ComponentFactory.ACTION_SOURCE) == null)
            {
                component.addAdapter(ComponentFactory.ACTION_SOURCE,
                        new ActionSourceInfo(null, null, false));
            }
        }

        if (interfaceNameSets.contains(ComponentFactory.INTERFACE_VALUEHOLDER))
        {
            // a EditableValueHolder is-a ValueHolder
            if (interfaceNameSets
                    .contains(ComponentFactory.INTERFACE_EDITABLEVALUEHOLDER)
                    && component
                            .getAdapter(ComponentFactory.EDITABLE_VALUE_HOLDER) == null)
            {
                component.addAdapter(ComponentFactory.EDITABLE_VALUE_HOLDER,
                        new EditableValueHolderInfo(null, null, null, false,
                                false, true, false, null, null, null));
            }

            if (component.getAdapter(ComponentFactory.VALUE_HOLDER) == null)
            {
                component.addAdapter(ComponentFactory.VALUE_HOLDER,
                        new ValueHolderInfo(null, null, null));
            }
        }

        if (interfaceNameSets
                .contains(ComponentFactory.INTERFACE_NAMINGCONTAINER)
                && component.getAdapter(ComponentFactory.NAMING_CONTAINER) == null)
        {
            component.addAdapter(ComponentFactory.NAMING_CONTAINER,
                    INamingContainerInfo.ADAPTER);
        }
    }

    /**
     * @param element
     * @param constructionData
     * @return the id for element either derived from the element using getId or
     *         if not present, using a generation algorithm
     */
    protected String calculateId(final Element element,
            final ComponentConstructionData constructionData)
    {
        final String id = _adapter.getId(element);
        if (id != null)
        {
            return id;
        }
        // TODO: improve this
        final String prefix = _adapter.getGeneratedIdPrefix();
        return (prefix != null ? prefix : GENERATED_ID)
                + constructionData.increment();
    }

    /**
     * @return the construction data for this strategy
     */
    public final ComponentConstructionData getConstructionData()
    {
        return _constructionData;
    }
}
