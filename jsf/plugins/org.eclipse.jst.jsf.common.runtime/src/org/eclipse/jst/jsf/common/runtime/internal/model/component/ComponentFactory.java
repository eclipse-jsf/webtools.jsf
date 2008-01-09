package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import org.eclipse.jst.jsf.common.runtime.internal.model.bean.DataModelInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSource2Info;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSourceInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IEditableValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.INamingContainerInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.FacetDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.event.IActionListenerInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.event.IValueChangeListenerInfo;

public class ComponentFactory {

    public final static Class VALUE_HOLDER = IValueHolderInfo.class;
    public final static Class EDITABLE_VALUE_HOLDER = IEditableValueHolderInfo.class;
    public final static Class ACTION_SOURCE = IActionSourceInfo.class;
    public final static Class ACTION_SOURCE2 = IActionSource2Info.class;
    public final static Class CONVERTER = ConverterDecorator.class;
    public final static Class FACET = FacetDecorator.class;
    public final static Class VALIDATOR = ValidatorDecorator.class;
    public final static Class VALUE_CHANGE_LISTENER = IValueChangeListenerInfo.class;
    public final static Class ACTION_LISTENER = IActionListenerInfo.class;
    public final static Class NAMING_CONTAINER = INamingContainerInfo.class;

    public static ComponentInfo createComponentInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final boolean isRendered) {
        return new ComponentInfo(id, parent, typeInfo, isRendered);
    }

    public static UIInputInfo createUIInputInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final IEditableValueHolderInfo editableValueHolder,
            final boolean isRendered) {
        return new UIInputInfo(id, parent, typeInfo, editableValueHolder,
                isRendered);
    }

    public static UIOutputInfo createUIOutputInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final IValueHolderInfo valueHolderInfo, final boolean isRendered) {
        return new UIOutputInfo(id, parent, typeInfo, valueHolderInfo,
                isRendered);
    }

    public static UICommandInfo createUICommandInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final IActionSourceInfo actionSourceInfo, final boolean isRendered) {
        return new UICommandInfo(id, parent, typeInfo, isRendered,
                actionSourceInfo);
    }

    public static UIFormInfo createUIFormInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final boolean isRendered, final boolean prependId,
            final boolean submitted) {
        return new UIFormInfo(id, parent, typeInfo, isRendered, prependId,
                submitted);
    }

    public static UIDataInfo createUIDataInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final boolean isRendered, final DataModelInfo dataModel,
            final int first, final ComponentInfo footer,
            final ComponentInfo header, final int rowCount,
            final boolean rowAvailable, final Object rowData,
            final int rowIndex, final int rows, final Object value,
            final String var) {
        return new UIDataInfo(id, parent, typeInfo, isRendered, dataModel,
                first, footer, header, rowCount, rowAvailable, rowData,
                rowIndex, rows, value, var);
    }
}
