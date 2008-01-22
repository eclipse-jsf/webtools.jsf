package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.types.TypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IHandlerTagElement.TagHandlerType;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * ** TEMPORARY CLASS UNTIL META-DATA STRATEGY IS AVAILABLE **
 * 
 * @author cbateman
 * 
 */
public class DefaultJSPTagResolver extends JSPTagResolvingStrategy
{
    /**
     * strategy id
     */
    public final static String ID = "org.eclipse.jst.jsf.THISISTEMPORARY";
    private final static Set<String> URI_IS_A_JSF_LIB;

    static
    {
        final Set<String> libs = new HashSet<String>();
        libs.add(ITLDConstants.URI_JSF_CORE);
        libs.add(ITLDConstants.URI_JSF_HTML);
        URI_IS_A_JSF_LIB = Collections.unmodifiableSet(libs);
    }

    private final IProject _project;

    /**
     * @param project
     */
    public DefaultJSPTagResolver(final IProject project)
    {
        _project = project;
    }

    @Override
    public ITagElement resolve(final TLDElementDeclaration elementDecl)
    {
        final TagIdentifier tagId = TagIdentifierFactory
                .createTLDTagWrapper(elementDecl);
        final TypeInfo elementType = getTypeInfo(tagId);

        if (elementType instanceof ComponentTypeInfo)
        {
            return new TLDComponentTagElement(elementDecl,
                    (ComponentTypeInfo) elementType);
        }
        else if (elementType instanceof ConverterTypeInfo)
        {
            return new TLDConverterTagElement(elementDecl,
                    (ConverterTypeInfo) elementType);
        }
        else if (elementType instanceof ValidatorTypeInfo)
        {
            return new TLDValidatorTagElement(elementDecl,
                    (ValidatorTypeInfo) elementType);
        }
        else if (elementType instanceof TagHandlerType)
        {
            return new TLDTagHandlerElement(elementDecl,
                    (TagHandlerType) elementType);
        }
        else if (URI_IS_A_JSF_LIB.contains(tagId.getUri()))
        {
            return new TLDTagElement(elementDecl);
        }

        // not found
        return null;
    }

    private TypeInfo getTypeInfo(final TagIdentifier tagId)
    {
        final IProjectFacetVersion version = JSFAppConfigUtils
                .getProjectFacet(_project);
        final String versionAsString = version.getVersionString();
        final JSFVersion jsfVersion = JSFVersion.valueOfString(versionAsString);

        switch (jsfVersion)
        {
            case V1_0:
            case V1_1:
                return JSF11_ELEMENTS.get(tagId);

            case V1_2:
                return JSF12_ELEMENTS.get(tagId);

            default:
                return null;
        }
    }

    @Override
    public String getId()
    {
        return ID;
    }

    private static final ComponentTypeInfo COMPINFO_PARAM = new ComponentTypeInfo(
            "javax.faces.Parameter", "javax.faces.component.UIParameter",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Parameter",
            null);

    private static final ComponentTypeInfo COMPINFO_SELECTITEM = new ComponentTypeInfo(
            "javax.faces.SelectItem", "javax.faces.component.UISelectItem",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.SelectItem",
            null);

    private static final ComponentTypeInfo COMPINFO_SELECTITEMS = new ComponentTypeInfo(
            "javax.faces.SelectItems", "javax.faces.component.UISelectItems",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", },
            "javax.faces.SelectItems", null);

    private static final ComponentTypeInfo COMPINFO_SUBVIEW = new ComponentTypeInfo(
            "javax.faces.NamingContainer",
            "javax.faces.component.UINamingContainer", new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.NamingContainer",
                    "javax.faces.component.StateHolder", },
            "javax.faces.NamingContainer", null);

    private static final ComponentTypeInfo COMPINFO_VERBATIM = new ComponentTypeInfo(
            "javax.faces.Output", "javax.faces.component.UIOutput",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Output", "javax.faces.Text");

    private static final ComponentTypeInfo COMPINFO_VIEW = new ComponentTypeInfo(
            "javax.faces.ViewRoot", "javax.faces.component.UIViewRoot",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.ViewRoot",
            null);

    // expected type info for jsf/html components
    private static final ComponentTypeInfo COMPINFO_COLUMN = new ComponentTypeInfo(
            "javax.faces.Column", "javax.faces.component.UIColumn",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "javax.faces.component.StateHolder" }, "javax.faces.Column", null);
    private static final ComponentTypeInfo COMPINFO_COMMAND = new ComponentTypeInfo(
            "javax.faces.HtmlCommandButton",
            "javax.faces.component.html.HtmlCommandButton", new String[]
            { "javax.faces.component.UICommand",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "javax.faces.component.ActionSource",
                    "javax.faces.component.StateHolder" },
            "javax.faces.Command", "javax.faces.Button");
    private static final ComponentTypeInfo COMPINFO_COMMANDLINK = new ComponentTypeInfo(
            "javax.faces.HtmlCommandLink",
            "javax.faces.component.html.HtmlCommandLink",
            "javax.faces.Command", "javax.faces.Link");
    private static final ComponentTypeInfo COMPINFO_DATATABLE = new ComponentTypeInfo(
            "javax.faces.HtmlDataTable",
            "javax.faces.component.html.HtmlDataTable", new String[]
            { "javax.faces.component.UIData",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object"},
            new String[]
            { "javax.faces.component.NamingContainer",
                    "javax.faces.component.StateHolder"},

            "javax.faces.Data", "javax.faces.Table");
    private static final ComponentTypeInfo COMPINFO_FORM = new ComponentTypeInfo(
            "javax.faces.HtmlForm", "javax.faces.component.html.HtmlForm",
            new String[]
            { "javax.faces.component.UIForm",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "javax.faces.component.NamingContainer",
                    "javax.faces.component.StateHolder" }, "javax.faces.Form",
            "javax.faces.Form");
    private static final ComponentTypeInfo COMPINFO_GRAPHIC = new ComponentTypeInfo(
            "javax.faces.HtmlGraphicImage",
            "javax.faces.component.html.HtmlGraphicImage", new String[]
            { "javax.faces.component.UIGraphic",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Graphic",
            "javax.faces.Image");
    private static final ComponentTypeInfo COMPINFO_HIDDEN = new ComponentTypeInfo(
            "javax.faces.HtmlInputHidden",
            "javax.faces.component.html.HtmlInputHidden", new String[]
            { "javax.faces.component.UIInput",
                    "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.EditableValueHolder",
                    "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Input", "javax.faces.Hidden");
    private static final ComponentTypeInfo COMPINFO_SECRET = new ComponentTypeInfo(
            "javax.faces.HtmlInputSecret",
            "javax.faces.component.html.HtmlInputSecret", new String[]
            { "javax.faces.component.UIInput",
                    "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.EditableValueHolder",
                    "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder" }, "javax.faces.Input",
            "javax.faces.Secret");
    private static final ComponentTypeInfo COMPINFO_INPUTTEXT = new ComponentTypeInfo(
            "javax.faces.HtmlInputText",
            "javax.faces.component.html.HtmlInputText", new String[]
            { "javax.faces.component.UIInput",
                    "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "javax.faces.component.EditableValueHolder",
                    "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder" }, "javax.faces.Input",
            "javax.faces.Text");
    private static final ComponentTypeInfo COMPINFO_INPUTTEXTAREA = new ComponentTypeInfo(
            "javax.faces.HtmlInputTextarea",
            "javax.faces.component.html.HtmlInputTextarea", new String[]
            { "javax.faces.component.UIInput",
                    "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.EditableValueHolder",
                    "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Input", "javax.faces.Textarea");
    private static final ComponentTypeInfo COMPINFO_MESSAGE = new ComponentTypeInfo(
            "javax.faces.HtmlMessage",
            "javax.faces.component.html.HtmlMessage", new String[]
            { "javax.faces.component.UIMessage",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Message",
            "javax.faces.Message");
    private static final ComponentTypeInfo COMPINFO_MESSAGES = new ComponentTypeInfo(
            "javax.faces.HtmlMessages",
            "javax.faces.component.html.HtmlMessages", new String[]
            { "javax.faces.component.UIMessages",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Messages",
            "javax.faces.Messages");
    private static final ComponentTypeInfo COMPINFO_OUTPUTFORMAT = new ComponentTypeInfo(
            "javax.faces.HtmlOutputFormat",
            "javax.faces.component.html.HtmlOutputFormat", new String[]
            { "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Output", "javax.faces.Format");
    private static final ComponentTypeInfo COMPINFO_OUTPUTLABEL = new ComponentTypeInfo(
            "javax.faces.HtmlOutputLabel",
            "javax.faces.component.html.HtmlOutputLabel", new String[]
            { "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Output", "javax.faces.Label");
    private static final ComponentTypeInfo COMPINFO_OUTPUTLINK = new ComponentTypeInfo(
            "javax.faces.HtmlOutputLink",
            "javax.faces.component.html.HtmlOutputLink", new String[]
            { "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Output", "javax.faces.Link");
    private static final ComponentTypeInfo COMPINFO_OUTPUTTEXT = new ComponentTypeInfo(
            "javax.faces.HtmlOutputText",
            "javax.faces.component.html.HtmlOutputText", new String[]
            { "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Output", "javax.faces.Text");
    private static final ComponentTypeInfo COMPINFO_PANELGRID = new ComponentTypeInfo(
            "javax.faces.HtmlPanelGrid",
            "javax.faces.component.html.HtmlPanelGrid", new String[]
            { "javax.faces.component.UIPanel",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Panel",
            "javax.faces.Grid");
    private static final ComponentTypeInfo COMPINFO_PANELGROUP = new ComponentTypeInfo(
            "javax.faces.HtmlPanelGroup",
            "javax.faces.component.html.HtmlPanelGroup", new String[]
            { "javax.faces.component.UIPanel",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Panel",
            "javax.faces.Group");
    private static final ComponentTypeInfo COMPINFO_SELECTBOOLEANCHECKBOX = new ComponentTypeInfo(
            "javax.faces.HtmlSelectBooleanCheckbox",
            "javax.faces.component.html.HtmlSelectBooleanCheckbox",
            new String[]
            { "javax.faces.component.UISelectBoolean",
                    "javax.faces.component.UIInput",
                    "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.EditableValueHolder",
                    "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.SelectBoolean", "javax.faces.Checkbox");
    private static final ComponentTypeInfo COMPINFO_SELECTMANYCHECKBOX = new ComponentTypeInfo(
            "javax.faces.HtmlSelectManyCheckbox",
            "javax.faces.component.html.HtmlSelectManyCheckbox", new String[]
            { "javax.faces.component.UISelectMany",
                    "javax.faces.component.UIInput",
                    "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.EditableValueHolder",
                    "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.SelectMany", "javax.faces.Checkbox");
    private static final ComponentTypeInfo COMPINFO_SELECTMANYLISTBOX = new ComponentTypeInfo(
            "javax.faces.HtmlSelectManyListbox",
            "javax.faces.component.html.HtmlSelectManyListbox", new String[]
            { "javax.faces.component.UISelectMany",
                    "javax.faces.component.UIInput",
                    "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.EditableValueHolder",
                    "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.SelectMany", "javax.faces.Listbox");
    private static final ComponentTypeInfo COMPINFO_SELECTMANYMENU = new ComponentTypeInfo(
            "javax.faces.HtmlSelectManyMenu",
            "javax.faces.component.html.HtmlSelectManyMenu", new String[]
            { "javax.faces.component.UISelectMany",
                    "javax.faces.component.UIInput",
                    "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.EditableValueHolder",
                    "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.SelectMany", "javax.faces.Menu");
    private static final ComponentTypeInfo COMPINFO_SELECTONELISTBOX = new ComponentTypeInfo(
            "javax.faces.HtmlSelectOneListbox",
            "javax.faces.component.html.HtmlSelectOneListbox", new String[]
            { "javax.faces.component.UISelectOne",
                    "javax.faces.component.UIInput",
                    "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.EditableValueHolder",
                    "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.SelectOne", "javax.faces.Listbox");
    private static final ComponentTypeInfo COMPINFO_SELECTONEMENU = new ComponentTypeInfo(
            "javax.faces.HtmlSelectOneMenu",
            "javax.faces.component.html.HtmlSelectOneMenu", new String[]
            { "javax.faces.component.UISelectOne",
                    "javax.faces.component.UIInput",
                    "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.EditableValueHolder",
                    "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.SelectOne", "javax.faces.Menu");
    private static final ComponentTypeInfo COMPINFO_SELECTONERADIO = new ComponentTypeInfo(
            "javax.faces.HtmlSelectOneRadio",
            "javax.faces.component.html.HtmlSelectOneRadio", new String[]
            { "javax.faces.component.UISelectOne",
                    "javax.faces.component.UIInput",
                    "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.EditableValueHolder",
                    "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.SelectOne", "javax.faces.Radio");

    // default converters
    private static final ConverterTypeInfo CONVERTERINFO_DATETIME = new ConverterTypeInfo(
            "javax.faces.convert.DateTimeConverter", "javax.faces.DateTime");

    private static final ConverterTypeInfo CONVERTERINFO_NUMBER = new ConverterTypeInfo(
            "javax.faces.convert.NumberConverter", "javax.faces.Number");

    // default validators
    private static final ValidatorTypeInfo VALIDATORINFO_DOUBLERANGE = new ValidatorTypeInfo(
            "javax.faces.validator.DoubleRangeValidator",
            "javax.faces.DoubleRange");

    private static final ValidatorTypeInfo VALIDATORINFO_LENGTH = new ValidatorTypeInfo(
            "javax.faces.validator.LengthValidator", "javax.faces.Length");

    private static final ValidatorTypeInfo VALIDATORINFO_LONGRANGE = new ValidatorTypeInfo(
            "javax.faces.validator.LongRangeValidator", "javax.faces.LongRange");

    private static Map<TagIdentifier, TypeInfo> JSF11_ELEMENTS;
    private static Map<TagIdentifier, TypeInfo> JSF12_ELEMENTS;

    static
    {
        final Map<TagIdentifier, TypeInfo> elements = new HashMap<TagIdentifier, TypeInfo>();
        // IJSFConstants.TAG_IDENTIFIER_PHASELISTENER);
        // IJSFConstants.TAG_IDENTIFIER_SETPROPERTYACTIONLISTENER);

        elements.put(IJSFConstants.TAG_IDENTIFIER_ACTIONLISTENER,
                TagHandlerType.ACTIONLISTENER);
        elements.put(IJSFConstants.TAG_IDENTIFIER_ATTRIBUTE,
                TagHandlerType.ATTRIBUTE);
        elements.put(IJSFConstants.TAG_IDENTIFIER_CONVERTDATETIME,
                CONVERTERINFO_DATETIME);
        elements.put(IJSFConstants.TAG_IDENTIFIER_CONVERTER,
                ConverterTypeInfo.UNKNOWN);
        elements.put(IJSFConstants.TAG_IDENTIFIER_CONVERTNUMBER,
                CONVERTERINFO_NUMBER);
        elements.put(IJSFConstants.TAG_IDENTIFIER_FACET, TagHandlerType.FACET);
        // elements.put(IJSFConstants.TAG_IDENTIFIER_LOADBUNDLE,
        // TagHandlerType.);
        elements.put(IJSFConstants.TAG_IDENTIFIER_PARAM, COMPINFO_PARAM);
        elements.put(IJSFConstants.TAG_IDENTIFIER_SELECTITEM,
                COMPINFO_SELECTITEM);
        elements.put(IJSFConstants.TAG_IDENTIFIER_SELECTITEMS,
                COMPINFO_SELECTITEMS);
        elements.put(IJSFConstants.TAG_IDENTIFIER_SUBVIEW, COMPINFO_SUBVIEW);
        elements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATEDOUBLERANGE,
                VALIDATORINFO_DOUBLERANGE);
        elements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATELENGTH,
                VALIDATORINFO_LENGTH);
        elements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATELONGRANGE,
                VALIDATORINFO_LONGRANGE);
        elements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATOR,
                ValidatorTypeInfo.UNKNOWN);
        elements.put(IJSFConstants.TAG_IDENTIFIER_VALUECHANGELISTENER,
                TagHandlerType.VALUECHANGELISTENER);
        elements.put(IJSFConstants.TAG_IDENTIFIER_VERBATIM, COMPINFO_VERBATIM);
        elements.put(IJSFConstants.TAG_IDENTIFIER_VIEW, COMPINFO_VIEW);

        // JSF 1.2 core elements
        // none currently...

        // JSF 1.1/1.2 HTML tags
        // html verifier
        elements.put(IJSFConstants.TAG_IDENTIFIER_COLUMN, COMPINFO_COLUMN);

        elements.put(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON,
                COMPINFO_COMMAND);

        elements.put(IJSFConstants.TAG_IDENTIFIER_COMMANDLINK,
                COMPINFO_COMMANDLINK);

        elements.put(IJSFConstants.TAG_IDENTIFIER_DATA_TABLE,
                COMPINFO_DATATABLE);

        elements.put(IJSFConstants.TAG_IDENTIFIER_FORM, COMPINFO_FORM);

        elements.put(IJSFConstants.TAG_IDENTIFIER_GRAPHICIMAGE,
                COMPINFO_GRAPHIC);

        elements.put(IJSFConstants.TAG_IDENTIFIER_INPUTHIDDEN, COMPINFO_HIDDEN);

        elements.put(IJSFConstants.TAG_IDENTIFIER_INPUTSECRET, COMPINFO_SECRET);

        elements
                .put(IJSFConstants.TAG_IDENTIFIER_INPUTTEXT, COMPINFO_INPUTTEXT);

        elements.put(IJSFConstants.TAG_IDENTIFIER_INPUTTEXTAREA,
                COMPINFO_INPUTTEXTAREA);

        elements.put(IJSFConstants.TAG_IDENTIFIER_MESSAGE, COMPINFO_MESSAGE);

        elements.put(IJSFConstants.TAG_IDENTIFIER_MESSAGES, COMPINFO_MESSAGES);

        elements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTFORMAT,
                COMPINFO_OUTPUTFORMAT);

        elements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL,
                COMPINFO_OUTPUTLABEL);

        elements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTLINK,
                COMPINFO_OUTPUTLINK);

        elements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT,
                COMPINFO_OUTPUTTEXT);

        elements.put(IJSFConstants.TAG_IDENTIFIER_PANEL_GRID,
                COMPINFO_PANELGRID);

        elements.put(IJSFConstants.TAG_IDENTIFIER_PANEL_GROUP,
                COMPINFO_PANELGROUP);

        elements.put(IJSFConstants.TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX,
                COMPINFO_SELECTBOOLEANCHECKBOX);

        elements.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYCHECKBOX,
                COMPINFO_SELECTMANYCHECKBOX);

        elements.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYLISTBOX,
                COMPINFO_SELECTMANYLISTBOX);

        elements.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYMENU,
                COMPINFO_SELECTMANYMENU);

        elements.put(IJSFConstants.TAG_IDENTIFIER_SELECTONELISTBOX,
                COMPINFO_SELECTONELISTBOX);

        elements.put(IJSFConstants.TAG_IDENTIFIER_SELECTONEMENU,
                COMPINFO_SELECTONEMENU);

        elements.put(IJSFConstants.TAG_IDENTIFIER_SELECTONERADIO,
                COMPINFO_SELECTONERADIO);

        JSF11_ELEMENTS = Collections.unmodifiableMap(elements);

        JSF12_ELEMENTS = Collections
                .unmodifiableMap(new HashMap<TagIdentifier, TypeInfo>(elements));
    }

}
