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
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.types.TypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IHandlerTagElement.TagHandlerType;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;

/**
 * Hard-coded TypeInfo for default core and html JSF tags.
 * 
 * @author cbateman
 * 
 */
public final class DefaultTagTypeInfo
{
    private final static Set<String> URI_IS_A_JSF_LIB;

    static
    {
        final Set<String> libs = new HashSet<String>();
        libs.add(ITLDConstants.URI_JSF_CORE);
        libs.add(ITLDConstants.URI_JSF_HTML);
        URI_IS_A_JSF_LIB = Collections.unmodifiableSet(libs);
    }

    /**
     * @param tagId
     * @param jsfVersion
     * @return a type info for the tag id in jsf version or null if none.
     */
    public TypeInfo getTypeInfo(final TagIdentifier tagId,
            final JSFVersion jsfVersion)
    {

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

    /**
     * @param uri
     * @return true if uri is covered by this class
     */
    public static boolean isDefaultLib(final String uri)
    {
        return URI_IS_A_JSF_LIB.contains(uri);
    }

    private static final ComponentTypeInfo      COMPINFO_PARAM                 = new ComponentTypeInfo(
                                                                                       "javax.faces.Parameter",
                                                                                       "javax.faces.component.UIParameter",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", },
                                                                                       "javax.faces.Parameter",
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_SELECTITEM            = new ComponentTypeInfo(
                                                                                       "javax.faces.SelectItem",
                                                                                       "javax.faces.component.UISelectItem",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", },
                                                                                       "javax.faces.SelectItem",
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_SELECTITEMS           = new ComponentTypeInfo(
                                                                                       "javax.faces.SelectItems",
                                                                                       "javax.faces.component.UISelectItems",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", },
                                                                                       "javax.faces.SelectItems",
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_SUBVIEW               = new ComponentTypeInfo(
                                                                                       "javax.faces.NamingContainer",
                                                                                       "javax.faces.component.UINamingContainer",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.NamingContainer",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.NamingContainer",
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_VERBATIM              = new ComponentTypeInfo(
                                                                                       "javax.faces.Output",
                                                                                       "javax.faces.component.UIOutput",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.Output",
                                                                                       "javax.faces.Text");

    private static final ComponentTypeInfo      COMPINFO_VIEW                  = new ComponentTypeInfo(
                                                                                       "javax.faces.ViewRoot",
                                                                                       "javax.faces.component.UIViewRoot",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", },
                                                                                       "javax.faces.ViewRoot",
                                                                                       null);

    // expected type info for jsf/html components
    private static final ComponentTypeInfo      COMPINFO_COLUMN                = new ComponentTypeInfo(
                                                                                       "javax.faces.Column",
                                                                                       "javax.faces.component.UIColumn",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object"                   },
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder" },
                                                                                       "javax.faces.Column",
                                                                                       null);
    private static final ComponentTypeInfo      COMPINFO_COMMAND               = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlCommandButton",
                                                                                       "javax.faces.component.html.HtmlCommandButton",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UICommand",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object"                   },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ActionSource",
            "javax.faces.component.StateHolder"                                       },
                                                                                       "javax.faces.Command",
                                                                                       "javax.faces.Button");
    private static final ComponentTypeInfo      COMPINFO_COMMANDLINK           = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlCommandLink",
                                                                                       "javax.faces.component.html.HtmlCommandLink",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UICommand",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object"                   },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ActionSource",
            "javax.faces.component.StateHolder"                                       },
                                                                                       "javax.faces.Command",
                                                                                       "javax.faces.Link");
    private static final ComponentTypeInfo      COMPINFO_DATATABLE             = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlDataTable",
                                                                                       "javax.faces.component.html.HtmlDataTable",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIData",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object"                   },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.NamingContainer",
            "javax.faces.component.StateHolder"                                       },
                                                                                       "javax.faces.Data",
                                                                                       "javax.faces.Table");
    private static final ComponentTypeInfo      COMPINFO_FORM                  = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlForm",
                                                                                       "javax.faces.component.html.HtmlForm",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIForm",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object"                   },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.NamingContainer",
            "javax.faces.component.StateHolder"                                       },
                                                                                       "javax.faces.Form",
                                                                                       "javax.faces.Form");
    private static final ComponentTypeInfo      COMPINFO_GRAPHIC               = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlGraphicImage",
                                                                                       "javax.faces.component.html.HtmlGraphicImage",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIGraphic",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", },
                                                                                       "javax.faces.Graphic",
                                                                                       "javax.faces.Image");
    private static final ComponentTypeInfo      COMPINFO_HIDDEN                = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputHidden",
                                                                                       "javax.faces.component.html.HtmlInputHidden",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder",
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.Input",
                                                                                       "javax.faces.Hidden");
    private static final ComponentTypeInfo      COMPINFO_SECRET                = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputSecret",
                                                                                       "javax.faces.component.html.HtmlInputSecret",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder",
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder"                                       },
                                                                                       "javax.faces.Input",
                                                                                       "javax.faces.Secret");
    private static final ComponentTypeInfo      COMPINFO_INPUTTEXT             = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputText",
                                                                                       "javax.faces.component.html.HtmlInputText",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object"                   },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder",
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder"                                       },
                                                                                       "javax.faces.Input",
                                                                                       "javax.faces.Text");
    private static final ComponentTypeInfo      COMPINFO_INPUTTEXTAREA         = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputTextarea",
                                                                                       "javax.faces.component.html.HtmlInputTextarea",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder",
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.Input",
                                                                                       "javax.faces.Textarea");
    private static final ComponentTypeInfo      COMPINFO_MESSAGE               = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlMessage",
                                                                                       "javax.faces.component.html.HtmlMessage",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIMessage",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", },
                                                                                       "javax.faces.Message",
                                                                                       "javax.faces.Message");
    private static final ComponentTypeInfo      COMPINFO_MESSAGES              = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlMessages",
                                                                                       "javax.faces.component.html.HtmlMessages",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIMessages",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", },
                                                                                       "javax.faces.Messages",
                                                                                       "javax.faces.Messages");
    private static final ComponentTypeInfo      COMPINFO_OUTPUTFORMAT          = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputFormat",
                                                                                       "javax.faces.component.html.HtmlOutputFormat",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.Output",
                                                                                       "javax.faces.Format");
    private static final ComponentTypeInfo      COMPINFO_OUTPUTLABEL           = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputLabel",
                                                                                       "javax.faces.component.html.HtmlOutputLabel",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.Output",
                                                                                       "javax.faces.Label");
    private static final ComponentTypeInfo      COMPINFO_OUTPUTLINK            = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputLink",
                                                                                       "javax.faces.component.html.HtmlOutputLink",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.Output",
                                                                                       "javax.faces.Link");
    private static final ComponentTypeInfo      COMPINFO_OUTPUTTEXT            = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputText",
                                                                                       "javax.faces.component.html.HtmlOutputText",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.Output",
                                                                                       "javax.faces.Text");
    private static final ComponentTypeInfo      COMPINFO_PANELGRID             = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlPanelGrid",
                                                                                       "javax.faces.component.html.HtmlPanelGrid",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIPanel",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", },
                                                                                       "javax.faces.Panel",
                                                                                       "javax.faces.Grid");
    private static final ComponentTypeInfo      COMPINFO_PANELGROUP            = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlPanelGroup",
                                                                                       "javax.faces.component.html.HtmlPanelGroup",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIPanel",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", },
                                                                                       "javax.faces.Panel",
                                                                                       "javax.faces.Group");
    private static final ComponentTypeInfo      COMPINFO_SELECTBOOLEANCHECKBOX = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectBooleanCheckbox",
                                                                                       "javax.faces.component.html.HtmlSelectBooleanCheckbox",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectBoolean",
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder",
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.SelectBoolean",
                                                                                       "javax.faces.Checkbox");
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYCHECKBOX    = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectManyCheckbox",
                                                                                       "javax.faces.component.html.HtmlSelectManyCheckbox",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectMany",
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder",
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.SelectMany",
                                                                                       "javax.faces.Checkbox");
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYLISTBOX     = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectManyListbox",
                                                                                       "javax.faces.component.html.HtmlSelectManyListbox",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectMany",
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder",
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.SelectMany",
                                                                                       "javax.faces.Listbox");
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYMENU        = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectManyMenu",
                                                                                       "javax.faces.component.html.HtmlSelectManyMenu",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectMany",
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder",
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.SelectMany",
                                                                                       "javax.faces.Menu");
    private static final ComponentTypeInfo      COMPINFO_SELECTONELISTBOX      = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectOneListbox",
                                                                                       "javax.faces.component.html.HtmlSelectOneListbox",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectOne",
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder",
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.SelectOne",
                                                                                       "javax.faces.Listbox");
    private static final ComponentTypeInfo      COMPINFO_SELECTONEMENU         = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectOneMenu",
                                                                                       "javax.faces.component.html.HtmlSelectOneMenu",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectOne",
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder",
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.SelectOne",
                                                                                       "javax.faces.Menu");
    private static final ComponentTypeInfo      COMPINFO_SELECTONERADIO        = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectOneRadio",
                                                                                       "javax.faces.component.html.HtmlSelectOneRadio",
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectOne",
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput",
            "javax.faces.component.UIComponentBase",
            "javax.faces.component.UIComponent", "java.lang.Object",                  },
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder",
            "javax.faces.component.ValueHolder",
            "javax.faces.component.StateHolder",                                      },
                                                                                       "javax.faces.SelectOne",
                                                                                       "javax.faces.Radio");

    // default converters
    private static final ConverterTypeInfo      CONVERTERINFO_DATETIME         = new ConverterTypeInfo(
                                                                                       "javax.faces.convert.DateTimeConverter",
                                                                                       "javax.faces.DateTime");

    private static final ConverterTypeInfo      CONVERTERINFO_NUMBER           = new ConverterTypeInfo(
                                                                                       "javax.faces.convert.NumberConverter",
                                                                                       "javax.faces.Number");

    // default validators
    private static final ValidatorTypeInfo      VALIDATORINFO_DOUBLERANGE      = new ValidatorTypeInfo(
                                                                                       "javax.faces.validator.DoubleRangeValidator",
                                                                                       "javax.faces.DoubleRange");

    private static final ValidatorTypeInfo      VALIDATORINFO_LENGTH           = new ValidatorTypeInfo(
                                                                                       "javax.faces.validator.LengthValidator",
                                                                                       "javax.faces.Length");

    private static final ValidatorTypeInfo      VALIDATORINFO_LONGRANGE        = new ValidatorTypeInfo(
                                                                                       "javax.faces.validator.LongRangeValidator",
                                                                                       "javax.faces.LongRange");

    private static Map<TagIdentifier, TypeInfo> JSF11_ELEMENTS;
    private static Map<TagIdentifier, TypeInfo> JSF12_ELEMENTS;

    static
    {
        final Map<TagIdentifier, TypeInfo> commonElements = new HashMap<TagIdentifier, TypeInfo>();
        // IJSFConstants.TAG_IDENTIFIER_PHASELISTENER);
        // IJSFConstants.TAG_IDENTIFIER_SETPROPERTYACTIONLISTENER);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_ACTIONLISTENER,
                TagHandlerType.ACTIONLISTENER);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_ATTRIBUTE,
                TagHandlerType.ATTRIBUTE);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_CONVERTDATETIME,
                CONVERTERINFO_DATETIME);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_CONVERTER,
                ConverterTypeInfo.UNKNOWN);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_CONVERTNUMBER,
                CONVERTERINFO_NUMBER);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_FACET, TagHandlerType.FACET);
        // elements.put(IJSFConstants.TAG_IDENTIFIER_LOADBUNDLE,
        // TagHandlerType.);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_PARAM, COMPINFO_PARAM);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTITEM,
                COMPINFO_SELECTITEM);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTITEMS,
                COMPINFO_SELECTITEMS);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SUBVIEW, COMPINFO_SUBVIEW);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATEDOUBLERANGE,
                VALIDATORINFO_DOUBLERANGE);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATELENGTH,
                VALIDATORINFO_LENGTH);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATELONGRANGE,
                VALIDATORINFO_LONGRANGE);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VALIDATOR,
                ValidatorTypeInfo.UNKNOWN);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VALUECHANGELISTENER,
                TagHandlerType.VALUECHANGELISTENER);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VERBATIM, COMPINFO_VERBATIM);
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_VIEW, COMPINFO_VIEW);

        // JSF 1.2 core elements
        // none currently...

        // JSF 1.1/1.2 HTML tags
        // html verifier
        commonElements.put(IJSFConstants.TAG_IDENTIFIER_COLUMN, COMPINFO_COLUMN);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON,
                COMPINFO_COMMAND);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_COMMANDLINK,
                COMPINFO_COMMANDLINK);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_DATA_TABLE,
                COMPINFO_DATATABLE);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_FORM, COMPINFO_FORM);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_GRAPHICIMAGE,
                COMPINFO_GRAPHIC);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_INPUTHIDDEN, COMPINFO_HIDDEN);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_INPUTSECRET, COMPINFO_SECRET);

        commonElements
                .put(IJSFConstants.TAG_IDENTIFIER_INPUTTEXT, COMPINFO_INPUTTEXT);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_INPUTTEXTAREA,
                COMPINFO_INPUTTEXTAREA);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_MESSAGE, COMPINFO_MESSAGE);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_MESSAGES, COMPINFO_MESSAGES);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTFORMAT,
                COMPINFO_OUTPUTFORMAT);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL,
                COMPINFO_OUTPUTLABEL);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTLINK,
                COMPINFO_OUTPUTLINK);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT,
                COMPINFO_OUTPUTTEXT);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_PANEL_GRID,
                COMPINFO_PANELGRID);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_PANEL_GROUP,
                COMPINFO_PANELGROUP);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX,
                COMPINFO_SELECTBOOLEANCHECKBOX);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYCHECKBOX,
                COMPINFO_SELECTMANYCHECKBOX);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYLISTBOX,
                COMPINFO_SELECTMANYLISTBOX);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTMANYMENU,
                COMPINFO_SELECTMANYMENU);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTONELISTBOX,
                COMPINFO_SELECTONELISTBOX);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTONEMENU,
                COMPINFO_SELECTONEMENU);

        commonElements.put(IJSFConstants.TAG_IDENTIFIER_SELECTONERADIO,
                COMPINFO_SELECTONERADIO);

        JSF11_ELEMENTS = Collections.unmodifiableMap(commonElements);

        JSF12_ELEMENTS = Collections
                .unmodifiableMap(new HashMap<TagIdentifier, TypeInfo>(commonElements));
    }

}
