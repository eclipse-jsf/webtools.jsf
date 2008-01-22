package org.eclipse.jst.jsf.common.runtime.tests.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.bean.DataModelInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSource2Info;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSourceInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IEditableValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UICommandInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIDataInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIFormInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIInputInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIOutputInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ActionListenerDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ComponentDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.Decorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.FacetDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;

public final class RuntimeTestUtil extends Assert
{
    // expected type info for jsf/core components
    public static final ComponentTypeInfo COMPINFO_PARAM = new ComponentTypeInfo(
            "javax.faces.Parameter", "javax.faces.component.UIParameter",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Parameter",
            null);

    public static final ComponentTypeInfo COMPINFO_SELECTITEM = new ComponentTypeInfo(
            "javax.faces.SelectItem", "javax.faces.component.UISelectItem",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.SelectItem",
            null);

    public static final ComponentTypeInfo COMPINFO_SELECTITEMS = new ComponentTypeInfo(
            "javax.faces.SelectItems", "javax.faces.component.UISelectItems",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", },
            "javax.faces.SelectItems", null);

    public static final ComponentTypeInfo COMPINFO_SUBVIEW = new ComponentTypeInfo(
            "javax.faces.NamingContainer",
            "javax.faces.component.UINamingContainer", new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.NamingContainer",
                    "javax.faces.component.StateHolder", },
            "javax.faces.NamingContainer", null);

    public static final ComponentTypeInfo COMPINFO_VERBATIM = new ComponentTypeInfo(
            "javax.faces.Output", "javax.faces.component.UIOutput",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Output", "javax.faces.Text");

    public static final ComponentTypeInfo COMPINFO_VIEW = new ComponentTypeInfo(
            "javax.faces.ViewRoot", "javax.faces.component.UIViewRoot",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.ViewRoot",
            null);

    // expected type info for jsf/html components
    public static final ComponentTypeInfo COMPINFO_COLUMN = new ComponentTypeInfo(
            "javax.faces.Column", "javax.faces.component.UIColumn",
            new String[]
            { "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "javax.faces.component.StateHolder" }, "javax.faces.Column", null);
    public static final ComponentTypeInfo COMPINFO_COMMAND = new ComponentTypeInfo(
            "javax.faces.HtmlCommandButton",
            "javax.faces.component.html.HtmlCommandButton", new String[]
            { "javax.faces.component.UICommand",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "javax.faces.component.ActionSource",
                    "javax.faces.component.StateHolder" },
            "javax.faces.Command", "javax.faces.Button");
    public static final ComponentTypeInfo COMPINFO_COMMANDLINK = new ComponentTypeInfo(
            "javax.faces.HtmlCommandLink",
            "javax.faces.component.html.HtmlCommandLink",
            "javax.faces.Command", "javax.faces.Link");
    public static final ComponentTypeInfo COMPINFO_DATATABLE = new ComponentTypeInfo(
            "javax.faces.HtmlDataTable",
            "javax.faces.component.html.HtmlDataTable", new String[]
            { "javax.faces.component.UIData",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "javax.faces.component.NamingContainer",
                    "javax.faces.component.StateHolder", }, "javax.faces.Data",
            "javax.faces.Table");
    public static final ComponentTypeInfo COMPINFO_FORM = new ComponentTypeInfo(
            "javax.faces.HtmlForm", "javax.faces.component.html.HtmlForm",
            new String[]
            { "javax.faces.component.UIForm",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object" },
            new String[]
            { "javax.faces.component.NamingContainer",
                    "javax.faces.component.StateHolder" }, "javax.faces.Form",
            "javax.faces.Form");
    public static final ComponentTypeInfo COMPINFO_GRAPHIC = new ComponentTypeInfo(
            "javax.faces.HtmlGraphicImage",
            "javax.faces.component.html.HtmlGraphicImage", new String[]
            { "javax.faces.component.UIGraphic",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Graphic",
            "javax.faces.Image");
    public static final ComponentTypeInfo COMPINFO_HIDDEN = new ComponentTypeInfo(
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
    public static final ComponentTypeInfo COMPINFO_SECRET = new ComponentTypeInfo(
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
    public static final ComponentTypeInfo COMPINFO_INPUTTEXT = new ComponentTypeInfo(
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
    public static final ComponentTypeInfo COMPINFO_INPUTTEXTAREA = new ComponentTypeInfo(
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
    public static final ComponentTypeInfo COMPINFO_MESSAGE = new ComponentTypeInfo(
            "javax.faces.HtmlMessage",
            "javax.faces.component.html.HtmlMessage", new String[]
            { "javax.faces.component.UIMessage",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Message",
            "javax.faces.Message");
    public static final ComponentTypeInfo COMPINFO_MESSAGES = new ComponentTypeInfo(
            "javax.faces.HtmlMessages",
            "javax.faces.component.html.HtmlMessages", new String[]
            { "javax.faces.component.UIMessages",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Messages",
            "javax.faces.Messages");
    public static final ComponentTypeInfo COMPINFO_OUTPUTFORMAT = new ComponentTypeInfo(
            "javax.faces.HtmlOutputFormat",
            "javax.faces.component.html.HtmlOutputFormat", new String[]
            { "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Output", "javax.faces.Format");
    public static final ComponentTypeInfo COMPINFO_OUTPUTLABEL = new ComponentTypeInfo(
            "javax.faces.HtmlOutputLabel",
            "javax.faces.component.html.HtmlOutputLabel", new String[]
            { "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Output", "javax.faces.Label");
    public static final ComponentTypeInfo COMPINFO_OUTPUTLINK = new ComponentTypeInfo(
            "javax.faces.HtmlOutputLink",
            "javax.faces.component.html.HtmlOutputLink", new String[]
            { "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Output", "javax.faces.Link");
    public static final ComponentTypeInfo COMPINFO_OUTPUTTEXT = new ComponentTypeInfo(
            "javax.faces.HtmlOutputText",
            "javax.faces.component.html.HtmlOutputText", new String[]
            { "javax.faces.component.UIOutput",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.ValueHolder",
                    "javax.faces.component.StateHolder", },
            "javax.faces.Output", "javax.faces.Text");
    public static final ComponentTypeInfo COMPINFO_PANELGRID = new ComponentTypeInfo(
            "javax.faces.HtmlPanelGrid",
            "javax.faces.component.html.HtmlPanelGrid", new String[]
            { "javax.faces.component.UIPanel",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Panel",
            "javax.faces.Grid");
    public static final ComponentTypeInfo COMPINFO_PANELGROUP = new ComponentTypeInfo(
            "javax.faces.HtmlPanelGroup",
            "javax.faces.component.html.HtmlPanelGroup", new String[]
            { "javax.faces.component.UIPanel",
                    "javax.faces.component.UIComponentBase",
                    "javax.faces.component.UIComponent", "java.lang.Object", },
            new String[]
            { "javax.faces.component.StateHolder", }, "javax.faces.Panel",
            "javax.faces.Group");
    public static final ComponentTypeInfo COMPINFO_SELECTBOOLEANCHECKBOX = new ComponentTypeInfo(
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
    public static final ComponentTypeInfo COMPINFO_SELECTMANYCHECKBOX = new ComponentTypeInfo(
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
    public static final ComponentTypeInfo COMPINFO_SELECTMANYLISTBOX = new ComponentTypeInfo(
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
    public static final ComponentTypeInfo COMPINFO_SELECTMANYMENU = new ComponentTypeInfo(
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
    public static final ComponentTypeInfo COMPINFO_SELECTONELISTBOX = new ComponentTypeInfo(
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
    public static final ComponentTypeInfo COMPINFO_SELECTONEMENU = new ComponentTypeInfo(
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
    public static final ComponentTypeInfo COMPINFO_SELECTONERADIO = new ComponentTypeInfo(
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
    public static final ConverterTypeInfo CONVERTERINFO_DATETIME = new ConverterTypeInfo(
            "javax.faces.convert.DateTimeConverter", "javax.faces.DateTime");

    public static final ConverterTypeInfo CONVERTERINFO_NUMBER = new ConverterTypeInfo(
            "javax.faces.convert.NumberConverter", "javax.faces.Number");

    // default validators
    public static final ValidatorTypeInfo VALIDATORINFO_DOUBLERANGE = new ValidatorTypeInfo(
            "javax.faces.validator.DoubleRangeValidator",
            "javax.faces.DoubleRange");

    public static final ValidatorTypeInfo VALIDATORINFO_LENGTH = new ValidatorTypeInfo(
            "javax.faces.validator.LengthValidator", "javax.faces.Length");

    public static final ValidatorTypeInfo VALIDATORINFO_LONGRANGE = new ValidatorTypeInfo(
            "javax.faces.validator.LongRangeValidator", "javax.faces.LongRange");

    // public static final ValidatorTypeInfo VALIDATORINFO_METHODEXPRESSION =
    // new ValidatorTypeInfo(
    // "javax.faces.validator.MethodExpressionValidator ",
    // "javax.faces.LongRange");

    @SuppressWarnings("unchecked")
    public static <COMPONENT_T> COMPONENT_T serializeDeserialize(
            final COMPONENT_T object) throws IOException,
            ClassNotFoundException
    {
        final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        final ObjectOutputStream outStream = new ObjectOutputStream(byteStream);

        outStream.writeObject(object);

        final ByteArrayInputStream byteArray = new ByteArrayInputStream(
                byteStream.toByteArray());
        final ObjectInputStream inStream = new ObjectInputStream(byteArray);

        return (COMPONENT_T) inStream.readObject();
    }

    @SuppressWarnings("unchecked")
    public static void verifyImplicitAdapter(final ViewObject check,
            final Class adapterType, final Object explicitAdapter)
    {
        assertEquals(check, check.getAdapter(adapterType));
        // cannot add explicit adapter if check is already that type
        boolean caughtException = false;
        
        try
        {
            check.addAdapter(adapterType, explicitAdapter);
        }
        catch (IllegalArgumentException iae)
        {
            caughtException = true;
        }
        
        assertTrue(caughtException);

        // should be unaffected by the attempt
        assertEquals(check, check.getAdapter(adapterType));
    }

    public static void verifySame(final ComponentTypeInfo truth,
            final ComponentTypeInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getClassName(), check.getClassName());
        assertEquals(truth.getComponentFamily(), check.getComponentFamily());
        assertEquals(truth.getComponentType(), check.getComponentType());
        assertEquals(truth.getRenderFamily(), check.getRenderFamily());
        
        verifyArraysSame(truth.getInterfaces(), check.getInterfaces());
        verifyArraysSame(truth.getSuperClasses(), check.getSuperClasses());
    }

    public static <T> void verifyArraysSame(T[]  expected, T[] check)
    {
        assertEquals("Arrays must be same size", expected.length, check.length);
        
        Set<T>  expectedValues = new HashSet<T>();
        
        for (final T e : expected)
        {
            expectedValues.add(e);
        }
        
        for (final T e : check)
        {
            assertTrue("Must contain "+e,expectedValues.contains(e));
        }
    }
    
    public static void verifySame(final DataModelInfo truth,
            final DataModelInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getRowCount(), check.getRowCount());
        assertEquals(truth.getRowIndex(), check.getRowIndex());

        // assertEquals(truth.getRowData(), check.getRowData());
        // assertEquals(truth.getWrappedData(), check.getWrappedData());
    }

    public static void verifySame(final ViewObject truth, final ViewObject check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getAllDecorators().size(), check.getAllDecorators()
                .size());

        for (int i = 0; i < check.getAllDecorators().size(); i++)
        {
            verifySame((Decorator) truth.getAllDecorators().get(i),
                    (Decorator) check.getAllDecorators().get(i));
        }

        // TODO: hard to check adapters since they are arbitrary
        assertEquals(truth.getAllAdapters().size(), truth.getAllAdapters()
                .size());
        // can at least check that the keys match
        assertEquals(truth.getAllAdapters().keySet(), check.getAllAdapters()
                .keySet());
    }

    @SuppressWarnings("unchecked")
    public static void verifySame(final ComponentInfo truth,
            final ComponentInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ViewObject) truth, (ViewObject) check);
        verifySame(truth.getComponentTypeInfo(), check.getComponentTypeInfo());

        assertEquals(truth.getId(), check.getId());
        assertEquals(truth.isRendered(), check.isRendered());
//        assertEquals(truth.getMostSpecificComponentName(), check
//                .getMostSpecificComponentName());
        // assertEquals(truth.getParent(), t);
        // TestRenderNode.verifySame(truth.getRenderNode(),
        // check.getRenderNode());

        assertEquals(truth.getChildren().size(), check.getChildren().size());

        for (int i = 0; i < check.getChildren().size(); i++)
        {
            final ComponentInfo checkChild = (ComponentInfo) check
                    .getChildren().get(i);
            verifySame((ComponentInfo) truth.getChildren().get(i), checkChild);
        }

        final List truthGetAllDecorators = truth.getAllDecorators();
        final List checkGetAllDecorators = check.getAllDecorators();

        for (int i = 0; i < checkGetAllDecorators.size(); i++)
        {
            final Decorator checkDecorator = (Decorator) checkGetAllDecorators
                    .get(i);

            verifySame((Decorator) truthGetAllDecorators.get(i), checkDecorator);
        }
    }

    public static void verifySame(final UIOutputInfo truth,
            final UIOutputInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentInfo) truth, (ComponentInfo) check);
        verifySame((IValueHolderInfo) truth, (IValueHolderInfo) check);
    }

    public static void verifySame(final UIInputInfo truth,
            final UIInputInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((UIOutputInfo) truth, (UIOutputInfo) check);
        verifySame((IEditableValueHolderInfo) truth,
                (IEditableValueHolderInfo) check);
    }

    public static void verifySame(final UICommandInfo truth,
            final UICommandInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentInfo) truth, (ComponentInfo) check);
        verifySame((IActionSourceInfo) truth, (IActionSourceInfo) check);
    }

    public static void verifySame(final UIFormInfo truth, final UIFormInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentInfo) truth, (ComponentInfo) check);
        assertEquals(truth.isPrependId(), check.isPrependId());
        assertEquals(truth.isSubmitted(), check.isSubmitted());
    }

    public static void verifySame(final UIDataInfo truth, final UIDataInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentInfo) truth, (ComponentInfo) check);

        assertEquals(truth.getFirst(), check.getFirst());
        assertEquals(truth.getRowCount(), check.getRowCount());
        assertEquals(truth.isRowAvailable(), check.isRowAvailable());
        assertEquals(truth.getRowIndex(), check.getRowIndex());
        assertEquals(truth.getRows(), check.getRows());
        assertEquals(truth.getVar(), check.getVar());
    }

    public static void verifySame(final Decorator truth, final Decorator check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getClass(), check.getClass());

        verifySame((ViewObject) truth, (ViewObject) check);

        if (truth instanceof FacetDecorator)
        {
            verifySame((FacetDecorator) truth, (FacetDecorator) check);
        }
        else if (truth instanceof ActionListenerDecorator)
        {
            verifySame((ActionListenerDecorator) truth,
                    (ActionListenerDecorator) check);
        }
        else if (truth instanceof ValidatorDecorator)
        {
            verifySame((ValidatorDecorator) truth, (ValidatorDecorator) check);
        }
        else if (truth instanceof ConverterDecorator)
        {
            verifySame((ConverterDecorator) truth, (ConverterDecorator) check);
        }
        else if (truth instanceof ComponentDecorator)
        {
            verifySame((ComponentDecorator) truth, (ComponentDecorator) check);
        }
    }

    private static void verifySame(final ComponentDecorator truth,
            final ComponentDecorator check)
    {
        verifySame(truth.getDecorates(), check.getDecorates());
    }

    private static void verifySame(final ActionListenerDecorator truth,
            final ActionListenerDecorator check)
    {
        verifySame((ComponentDecorator) truth, (ComponentDecorator) check);
    }

    private static void verifySame(final FacetDecorator truth,
            final FacetDecorator check)
    {
        verifySame((ComponentDecorator) truth, (ComponentDecorator) check);
        assertEquals(truth.getName(), check.getName());
    }

    private static void verifySame(final ConverterDecorator truth,
            final ConverterDecorator check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentDecorator) truth, (ComponentDecorator) check);
    }

    public static void verifySame(final ConverterTypeInfo truth,
            final ConverterTypeInfo check)
    {
        if (truth == check)
        {
            return;
        }
        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getClassName(), check.getClassName());
        assertEquals(truth.getConverterId(), check.getConverterId());
    }

    private static void verifySame(final ValidatorDecorator truth,
            final ValidatorDecorator check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((ComponentDecorator) truth, (ComponentDecorator) check);
    }

    public static void verifySame(final ValidatorTypeInfo truth,
            final ValidatorTypeInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getClassName(), check.getClassName());
        assertEquals(truth.getValidatorId(), check.getValidatorId());
    }

    public static ComponentTypeInfo createComponentTypeInfo()
    {
        return new ComponentTypeInfo("org.eclipse.jst.jsf.test",
                "org.eclipse.jst.jsf.test.ComponentClass",
                "org.eclipse.jst.jsf.test.ComponentFamily",
                "org.eclipse.jst.jsf.test.RenderFamily");
    }

    public static void verifySame(final IValueHolderInfo truth,
            final IValueHolderInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getLocalValue(), check.getLocalValue());
        assertEquals(truth.getValue(), check.getValue());
        verifySame(truth.getConverter(), check.getConverter());
    }

    public static void verifySame(final IEditableValueHolderInfo truth,
            final IEditableValueHolderInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        verifySame((IValueHolderInfo) truth, (IValueHolderInfo) check);

        assertEquals(truth.isImmediate(), truth.isImmediate());
        assertEquals(truth.isLocalSetValue(), truth.isLocalSetValue());
        assertEquals(truth.isRequired(), truth.isRequired());
        assertEquals(truth.isValid(), truth.isValid());

        assertEquals(truth.getSubmittedValue(), check.getSubmittedValue());
        assertEquals(truth.getValidator(), check.getValidator());
        assertEquals(truth.getValueChangeListener(), truth
                .getValueChangeListener());

        // check validators
        assertEquals(truth.getValidators().size(), check.getValidators().size());

        for (int i = 0; i < check.getValidators().size(); i++)
        {
            verifySame((Decorator) truth.getValidators().get(i),
                    (Decorator) check.getValidators().get(i));
        }

        for (int i = 0; i < check.getValueChangeListeners().size(); i++)
        {
            verifySame((Decorator) truth.getValidators().get(i),
                    (Decorator) check.getValidators().get(i));
        }
    }

    public static void verifySame(final IActionSourceInfo truth,
            final IActionSourceInfo check)
    {
        if (truth == check)
        {
            return;
        }

        // the only way having truth or check null is valid is if
        // the are both null, which we check above
        assertNotNull(truth);
        assertNotNull(check);

        assertEquals(truth.getAction(), check.getAction());
        assertEquals(truth.getActionListener(), check.getActionListener());
        assertEquals(truth.isImmediate(), check.isImmediate());

        if (truth instanceof IActionSource2Info)
        {
            assertTrue(check instanceof IActionSource2Info);
            assertEquals(((IActionSource2Info) truth).getActionExpression(),
                    ((IActionSource2Info) check).getActionExpression());
        }

        // check action listener
        assertEquals(truth.getActionListeners().size(), check
                .getActionListeners().size());

        for (int i = 0; i < check.getActionListeners().size(); i++)
        {
            verifySame((Decorator) truth.getActionListeners().get(i),
                    (Decorator) check.getActionListeners().get(i));
        }
    }
}
