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
                                                                                       "javax.faces.Parameter", //$NON-NLS-1$
                                                                                       "javax.faces.component.UIParameter", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Parameter", //$NON-NLS-1$
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_SELECTITEM            = new ComponentTypeInfo(
                                                                                       "javax.faces.SelectItem", //$NON-NLS-1$
                                                                                       "javax.faces.component.UISelectItem", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectItem", //$NON-NLS-1$
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_SELECTITEMS           = new ComponentTypeInfo(
                                                                                       "javax.faces.SelectItems", //$NON-NLS-1$
                                                                                       "javax.faces.component.UISelectItems", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectItems", //$NON-NLS-1$
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_SUBVIEW               = new ComponentTypeInfo(
                                                                                       "javax.faces.NamingContainer", //$NON-NLS-1$
                                                                                       "javax.faces.component.UINamingContainer", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.NamingContainer", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.NamingContainer", //$NON-NLS-1$
                                                                                       null);

    private static final ComponentTypeInfo      COMPINFO_VERBATIM              = new ComponentTypeInfo(
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.component.UIOutput", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.Text"); //$NON-NLS-1$

    private static final ComponentTypeInfo      COMPINFO_VIEW                  = new ComponentTypeInfo(
                                                                                       "javax.faces.ViewRoot", //$NON-NLS-1$
                                                                                       "javax.faces.component.UIViewRoot", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.ViewRoot", //$NON-NLS-1$
                                                                                       null);

    // expected type info for jsf/html components
    private static final ComponentTypeInfo      COMPINFO_COLUMN                = new ComponentTypeInfo(
                                                                                       "javax.faces.Column", //$NON-NLS-1$
                                                                                       "javax.faces.component.UIColumn", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder" }, //$NON-NLS-1$
                                                                                       "javax.faces.Column", //$NON-NLS-1$
                                                                                       null);
    private static final ComponentTypeInfo      COMPINFO_COMMAND               = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlCommandButton", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlCommandButton", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UICommand", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ActionSource", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Command", //$NON-NLS-1$
                                                                                       "javax.faces.Button"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_COMMANDLINK           = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlCommandLink", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlCommandLink", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UICommand", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ActionSource", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Command", //$NON-NLS-1$
                                                                                       "javax.faces.Link"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_DATATABLE             = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlDataTable", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlDataTable", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIData", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.NamingContainer", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Data", //$NON-NLS-1$
                                                                                       "javax.faces.Table"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_FORM                  = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlForm", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlForm", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIForm", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.NamingContainer", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Form", //$NON-NLS-1$
                                                                                       "javax.faces.Form"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_GRAPHIC               = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlGraphicImage", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlGraphicImage", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIGraphic", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Graphic", //$NON-NLS-1$
                                                                                       "javax.faces.Image"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_HIDDEN                = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputHidden", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlInputHidden", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Input", //$NON-NLS-1$
                                                                                       "javax.faces.Hidden"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SECRET                = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputSecret", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlInputSecret", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Input", //$NON-NLS-1$
                                                                                       "javax.faces.Secret"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_INPUTTEXT             = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputText", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlInputText", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object"                   }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder"                                       }, //$NON-NLS-1$
                                                                                       "javax.faces.Input", //$NON-NLS-1$
                                                                                       "javax.faces.Text"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_INPUTTEXTAREA         = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlInputTextarea", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlInputTextarea", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Input", //$NON-NLS-1$
                                                                                       "javax.faces.Textarea"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_MESSAGE               = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlMessage", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlMessage", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIMessage", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Message", //$NON-NLS-1$
                                                                                       "javax.faces.Message"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_MESSAGES              = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlMessages", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlMessages", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIMessages", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Messages", //$NON-NLS-1$
                                                                                       "javax.faces.Messages"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTFORMAT          = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputFormat", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlOutputFormat", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.Format"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTLABEL           = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputLabel", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlOutputLabel", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.Label"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTLINK            = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputLink", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlOutputLink", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.Link"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_OUTPUTTEXT            = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlOutputText", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlOutputText", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIOutput", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.Output", //$NON-NLS-1$
                                                                                       "javax.faces.Text"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_PANELGRID             = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlPanelGrid", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlPanelGrid", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIPanel", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Panel", //$NON-NLS-1$
                                                                                       "javax.faces.Grid"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_PANELGROUP            = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlPanelGroup", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlPanelGroup", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UIPanel", //$NON-NLS-1$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       { "javax.faces.component.StateHolder", }, //$NON-NLS-1$
                                                                                       "javax.faces.Panel", //$NON-NLS-1$
                                                                                       "javax.faces.Group"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTBOOLEANCHECKBOX = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectBooleanCheckbox", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectBooleanCheckbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectBoolean", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectBoolean", //$NON-NLS-1$
                                                                                       "javax.faces.Checkbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYCHECKBOX    = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectManyCheckbox", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectManyCheckbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectMany", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectMany", //$NON-NLS-1$
                                                                                       "javax.faces.Checkbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYLISTBOX     = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectManyListbox", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectManyListbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectMany", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectMany", //$NON-NLS-1$
                                                                                       "javax.faces.Listbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTMANYMENU        = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectManyMenu", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectManyMenu", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectMany", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectMany", //$NON-NLS-1$
                                                                                       "javax.faces.Menu"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTONELISTBOX      = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectOneListbox", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectOneListbox", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectOne", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectOne", //$NON-NLS-1$
                                                                                       "javax.faces.Listbox"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTONEMENU         = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectOneMenu", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectOneMenu", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectOne", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectOne", //$NON-NLS-1$
                                                                                       "javax.faces.Menu"); //$NON-NLS-1$
    private static final ComponentTypeInfo      COMPINFO_SELECTONERADIO        = new ComponentTypeInfo(
                                                                                       "javax.faces.HtmlSelectOneRadio", //$NON-NLS-1$
                                                                                       "javax.faces.component.html.HtmlSelectOneRadio", //$NON-NLS-1$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.UISelectOne", //$NON-NLS-1$
            "javax.faces.component.UIInput", "javax.faces.component.UIOutput", //$NON-NLS-1$ //$NON-NLS-2$
            "javax.faces.component.UIComponentBase", //$NON-NLS-1$
            "javax.faces.component.UIComponent", "java.lang.Object",                  }, //$NON-NLS-1$ //$NON-NLS-2$
                                                                                       new String[]
                                                                                       {
            "javax.faces.component.EditableValueHolder", //$NON-NLS-1$
            "javax.faces.component.ValueHolder", //$NON-NLS-1$
            "javax.faces.component.StateHolder",                                      }, //$NON-NLS-1$
                                                                                       "javax.faces.SelectOne", //$NON-NLS-1$
                                                                                       "javax.faces.Radio"); //$NON-NLS-1$

    // default converters
    private static final ConverterTypeInfo      CONVERTERINFO_DATETIME         = new ConverterTypeInfo(
                                                                                       "javax.faces.convert.DateTimeConverter", //$NON-NLS-1$
                                                                                       "javax.faces.DateTime"); //$NON-NLS-1$

    private static final ConverterTypeInfo      CONVERTERINFO_NUMBER           = new ConverterTypeInfo(
                                                                                       "javax.faces.convert.NumberConverter", //$NON-NLS-1$
                                                                                       "javax.faces.Number"); //$NON-NLS-1$

    // default validators
    private static final ValidatorTypeInfo      VALIDATORINFO_DOUBLERANGE      = new ValidatorTypeInfo(
                                                                                       "javax.faces.validator.DoubleRangeValidator", //$NON-NLS-1$
                                                                                       "javax.faces.DoubleRange"); //$NON-NLS-1$

    private static final ValidatorTypeInfo      VALIDATORINFO_LENGTH           = new ValidatorTypeInfo(
                                                                                       "javax.faces.validator.LengthValidator", //$NON-NLS-1$
                                                                                       "javax.faces.Length"); //$NON-NLS-1$

    private static final ValidatorTypeInfo      VALIDATORINFO_LONGRANGE        = new ValidatorTypeInfo(
                                                                                       "javax.faces.validator.LongRangeValidator", //$NON-NLS-1$
                                                                                       "javax.faces.LongRange"); //$NON-NLS-1$

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
