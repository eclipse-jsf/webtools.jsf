/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
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

    /**
     * @param rowAvailable
     * @param rowCount
     * @param rowData
     * @param rowIndex
     * @param wrappedData
     */
    public DataModelInfo(boolean rowAvailable, int rowCount, Object rowData,
            int rowIndex, Object wrappedData) {
        super();
        _rowAvailable = rowAvailable;
        _rowCount = rowCount;
        _rowData = new SerializableObject(rowData);
        _rowIndex = rowIndex;
        _wrappedData = new SerializableObject(wrappedData);
    }

    /**
     * @return true if the current row is available
     */
    public final boolean isRowAvailable() {
        return _rowAvailable;
    }
    /**
     * @return the row count of this model
     */
    public final int getRowCount() {
        return _rowCount;
    }
    /**
     * @return the row data
     */
    public final Object getRowData() {
        return _rowData.getMaybeSerializable();
    }
    /**
     * @return the row index
     */
    public final int getRowIndex() {
        return _rowIndex;
    }
    /**
     * @return the wrapped data.  may be null if wrapped object was not
     * serializable.
     */
    public final Object getWrappedData() {
        return _wrappedData.getMaybeSerializable();
    }
}
