package org.eclipse.jst.jsf.common.runtime.internal.model.bean;

import java.io.Serializable;

/**
 * Design time analog of runtime DataModel bean
 * 
 * @author cbateman
 *
 */
public class DataModelInfo implements Serializable 
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = 6461056497382677871L;

    private final boolean               _rowAvailable;
    private final int                   _rowCount;
    private final SerializableObject    _rowData;
    private final int                   _rowIndex;
    private final SerializableObject    _wrappedData;

    public DataModelInfo(boolean rowAvailable, int rowCount, Object rowData,
            int rowIndex, Object wrappedData) {
        super();
        _rowAvailable = rowAvailable;
        _rowCount = rowCount;
        _rowData = new SerializableObject(rowData);
        _rowIndex = rowIndex;
        _wrappedData = new SerializableObject(wrappedData);
    }

    public final boolean isRowAvailable() {
        return _rowAvailable;
    }
    public final int getRowCount() {
        return _rowCount;
    }
    public final Object getRowData() {
        return _rowData.getMaybeSerializable();
    }
    public final int getRowIndex() {
        return _rowIndex;
    }
    public final Object getWrappedData() {
        return _wrappedData.getMaybeSerializable();
    }
}
