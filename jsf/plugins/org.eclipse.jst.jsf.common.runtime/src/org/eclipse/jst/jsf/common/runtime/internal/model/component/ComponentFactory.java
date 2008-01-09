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

/**
 * Factory for creating component related objects.
 * 
 * @author cbateman
 *
 */
public class ComponentFactory {

    /**
     * The key for the standard ValueHolder adapter interface
     */
    public final static Class VALUE_HOLDER = IValueHolderInfo.class;
    /**
     * The key for the standard EditableValueHolder adapter interface
     */
    public final static Class EDITABLE_VALUE_HOLDER = IEditableValueHolderInfo.class;
    /**
     * The key for the standard ActionSource adapter interface
     */
    public final static Class ACTION_SOURCE = IActionSourceInfo.class;
    /**
     * The key for the standard ActionSource2 adapter interface
     */
    public final static Class ACTION_SOURCE2 = IActionSource2Info.class;
    /**
     * The key for the standard NamingContainer adapter interface
     */
    public final static Class NAMING_CONTAINER = INamingContainerInfo.class;

    
    /**
     * The key for the standard Converter decorator
     */
    public final static Class CONVERTER = ConverterDecorator.class;
    /**
     * The key for the standard Facet decorator
     */
    public final static Class FACET = FacetDecorator.class;
    /**
     * The key for the standard Validator decorator
     */
    public final static Class VALIDATOR = ValidatorDecorator.class;
    /**
     * The key for the standard ValueChangeListener decorator
     */
    public final static Class VALUE_CHANGE_LISTENER = IValueChangeListenerInfo.class;
    /**
     * The key for the standard ActionListener decorator
     */
    public final static Class ACTION_LISTENER = IActionListenerInfo.class;

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param isRendered
     * @return a new component info
     */
    public static ComponentInfo createComponentInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final boolean isRendered) {
        return new ComponentInfo(id, parent, typeInfo, isRendered);
    }

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param editableValueHolder
     * @param isRendered
     * @return a new UIInputInfo
     */
    public static UIInputInfo createUIInputInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final IEditableValueHolderInfo editableValueHolder,
            final boolean isRendered) {
        return new UIInputInfo(id, parent, typeInfo, editableValueHolder,
                isRendered);
    }

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param valueHolderInfo
     * @param isRendered
     * @return a new UIOutputInfo
     */
    public static UIOutputInfo createUIOutputInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final IValueHolderInfo valueHolderInfo, final boolean isRendered) {
        return new UIOutputInfo(id, parent, typeInfo, valueHolderInfo,
                isRendered);
    }

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param actionSourceInfo
     * @param isRendered
     * @return a new UICommandInfo
     */
    public static UICommandInfo createUICommandInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final IActionSourceInfo actionSourceInfo, final boolean isRendered) {
        return new UICommandInfo(id, parent, typeInfo, isRendered,
                actionSourceInfo);
    }

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param isRendered
     * @param prependId
     * @param submitted
     * @return a new UIFormInfo
     */
    public static UIFormInfo createUIFormInfo(final String id,
            final ComponentInfo parent, final ComponentTypeInfo typeInfo,
            final boolean isRendered, final boolean prependId,
            final boolean submitted) {
        return new UIFormInfo(id, parent, typeInfo, isRendered, prependId,
                submitted);
    }

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param isRendered
     * @param dataModel
     * @param first
     * @param footer
     * @param header
     * @param rowCount
     * @param rowAvailable
     * @param rowData
     * @param rowIndex
     * @param rows
     * @param value
     * @param var
     * @return a new UIDataInfo
     */
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
