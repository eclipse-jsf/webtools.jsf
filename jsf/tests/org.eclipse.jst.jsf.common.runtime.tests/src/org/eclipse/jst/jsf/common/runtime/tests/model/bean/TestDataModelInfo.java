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
package org.eclipse.jst.jsf.common.runtime.tests.model.bean;

import java.io.Serializable;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.common.runtime.internal.model.bean.DataModelInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.RuntimeTestUtil;

public class TestDataModelInfo extends TestCase {

    private DataModelInfo _dataModelInfo;
    private Object _rowDataObject;
    private Object _wrappedDataObject;

    protected void setUp() throws Exception {
        super.setUp();

        _rowDataObject = new Object();
        _wrappedDataObject = new Object();
        _dataModelInfo = new DataModelInfo(true, -1, _rowDataObject, -1,
                _wrappedDataObject);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsRowAvailable() {
        assertEquals(true, _dataModelInfo.isRowAvailable());
    }

    public void testGetRowCount() {
        assertEquals(-1, _dataModelInfo.getRowCount());
    }

    public void testGetRowData() {
        assertEquals(_rowDataObject, _dataModelInfo.getRowData());
    }

    public void testGetRowIndex() {
        assertEquals(-1, _dataModelInfo.getRowIndex());
    }

    public void testGetWrappedData() {
        assertEquals(_wrappedDataObject, _dataModelInfo.getWrappedData());
    }

    public void testSerializable() throws Exception {
        DataModelInfo deserialized = RuntimeTestUtil
                .serializeDeserialize(_dataModelInfo);
        RuntimeTestUtil.verifySame(_dataModelInfo, deserialized);
        
        // if the object is not serializable, then the deserialize will be null
        if (_dataModelInfo.getRowData() instanceof Serializable)
        {
            assertEquals(_dataModelInfo.getRowData(), deserialized.getRowData());
        }
        else
        {
            assertNull(deserialized.getRowData());
        }
        
        // if the object is not serializable, then the deserialize will be null
        if (_dataModelInfo.getWrappedData() instanceof Serializable)
        {
            assertEquals(_dataModelInfo.getWrappedData()
                    , deserialized.getWrappedData());
        }
        else
        {
            assertNull(deserialized.getWrappedData());
        }
    }
}
