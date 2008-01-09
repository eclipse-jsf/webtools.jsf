package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import org.eclipse.jst.jsf.common.runtime.internal.model.bean.DataModelInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.bean.SerializableObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.INamingContainerInfo;

/**
 * Design time analog to UIData.
 * 
 * @author cbateman
 * 
 */
public class UIDataInfo extends ComponentInfo implements INamingContainerInfo {
    public final static String FACET_NAME_FOOTER = "footer";
    public final static String FACET_NAME_HEADER = "header";
    /**
     * serialization id
     */
    private static final long serialVersionUID = 3473288390914978784L;

    private final DataModelInfo _dataModel;
    private final int _first;
    private final int _rowCount;
    private final boolean _rowAvailable;
    private final SerializableObject _rowData;
    private final int _rowIndex;
    private final int _rows;
    private final SerializableObject _value;
    private final String _var;

    public UIDataInfo(final String id, final ComponentInfo parent,
            final ComponentTypeInfo componentTypeInfo,
            final boolean isRendered, final DataModelInfo dataModel,
            final int first, final ComponentInfo footer,
            final ComponentInfo header, final int rowCount,
            final boolean rowAvailable, final Object rowData,
            final int rowIndex, final int rows, final Object value,
            final String var) {
        super(id, parent, componentTypeInfo, isRendered);
        _dataModel = dataModel;
        _first = first;
        _rowCount = rowCount;
        _rowAvailable = rowAvailable;
        _rowData = new SerializableObject(rowData);
        _rowIndex = rowIndex;
        _rows = rows;
        _value = new SerializableObject(value);
        _var = var;

        if (footer != null) {
            addFacet(FACET_NAME_FOOTER, footer);
        }

        if (header != null) {
            addFacet(FACET_NAME_HEADER, header);
        }
    }

    public final DataModelInfo getDataModel() {
        return _dataModel;
    }

    public final int getFirst() {
        return _first;
    }

    public final int getRowCount() {
        return _rowCount;
    }

    public final boolean isRowAvailable() {
        return _rowAvailable;
    }

    public final Object getRowData() {
        return _rowData.getMaybeSerializable();
    }

    public final int getRowIndex() {
        return _rowIndex;
    }

    public final int getRows() {
        return _rows;
    }

    public final Object getValue() {
        return _value.getMaybeSerializable();
    }

    public final String getVar() {
        return _var;
    }
    
    public final ComponentInfo getHeader()
    {
        return getFacet(FACET_NAME_HEADER);
    }
    
    public final ComponentInfo getFooter()
    {
        return getFacet(FACET_NAME_FOOTER);
    }
}