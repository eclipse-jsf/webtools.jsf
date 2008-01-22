package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.model.bean.DataModelInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.bean.SerializableObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.INamingContainerInfo;

/**
 * Design time analog to UIData.
 * 
 * @author cbateman
 * 
 */
public class UIDataInfo extends ComponentInfo implements INamingContainerInfo
{
    /**
     * the standard name for the footer facet
     */
    public final static String FACET_NAME_FOOTER = "footer";
    /**
     * the standard name for the header facet.
     */
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

    /**
     * @param id
     * @param parent
     * @param componentTypeInfo
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
     */
    public UIDataInfo(final String id, final ComponentInfo parent,
            final ComponentTypeInfo componentTypeInfo,
            final boolean isRendered, final DataModelInfo dataModel,
            final int first, final ComponentInfo footer,
            final ComponentInfo header, final int rowCount,
            final boolean rowAvailable, final Object rowData,
            final int rowIndex, final int rows, final Object value,
            final String var)
    {
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

        if (footer != null)
        {
            addFacet(FACET_NAME_FOOTER, footer);
        }

        if (header != null)
        {
            addFacet(FACET_NAME_HEADER, header);
        }
    }

    /**
     * @param parent
     * @param componentTypeInfo
     * @param attributes
     */
    public UIDataInfo(final ComponentInfo parent,
            final ComponentTypeInfo componentTypeInfo, Map attributes)
    {
        this(getStringProperty("id", attributes, true), parent,
                componentTypeInfo, 
                getBooleanProperty("rendered", attributes),
                getDataModelInfo("$dataModel", attributes), 
                getIntegerProperty("first", attributes), 
                getComponentProperty("footer", attributes),
                getComponentProperty("header", attributes), 
                getIntegerProperty("rowCount", attributes), 
                getBooleanProperty("rowAvailable", attributes), 
                attributes.get("rowData"),
                getIntegerProperty("rowIndex", attributes), 
                getIntegerProperty("rows", attributes), 
                attributes.get("value"),
                getStringProperty("var", attributes, true));
    }

    private static DataModelInfo getDataModelInfo(String key, Map attributes)
    {
        return (DataModelInfo) attributes.get(key);
    }

    /**
     * @return the data model
     */
    public final DataModelInfo getDataModel()
    {
        return _dataModel;
    }

    /**
     * @return the first row
     */
    public final int getFirst()
    {
        return _first;
    }

    /**
     * @return the row count
     */
    public final int getRowCount()
    {
        return _rowCount;
    }

    /**
     * @return true if the row is available
     */
    public final boolean isRowAvailable()
    {
        return _rowAvailable;
    }

    /**
     * @return the row data (may be null if not serialiable)
     */
    public final Object getRowData()
    {
        return _rowData.getMaybeSerializable();
    }

    /**
     * @return the row index
     */
    public final int getRowIndex()
    {
        return _rowIndex;
    }

    /**
     * @return the rows
     */
    public final int getRows()
    {
        return _rows;
    }

    /**
     * @return the value of the model (may be null if not serialiable)
     */
    public final Object getValue()
    {
        return _value.getMaybeSerializable();
    }

    /**
     * @return the name used to define the EL row variable
     */
    public final String getVar()
    {
        return _var;
    }

    /**
     * @return the header facet or null.
     */
    public final ComponentInfo getHeader()
    {
        return getFacet(FACET_NAME_HEADER);
    }

    /**
     * @return the footer facet or null.
     */
    public final ComponentInfo getFooter()
    {
        return getFacet(FACET_NAME_FOOTER);
    }

    protected String getMostSpecificComponentName()
    {
        return "UIData";
    }
}