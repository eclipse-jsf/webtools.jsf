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
package org.eclipse.jst.jsf.common.runtime.tests.model.component;

import java.io.Serializable;

import org.eclipse.jst.jsf.common.runtime.internal.model.bean.DataModelInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.INamingContainerInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIDataInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.RuntimeTestUtil;

public class TestUIDataInfo extends TestComponentInfo {

    private UIDataInfo _uiData;
    private UIDataInfo _uiDataWithFacetsAtConstruction;
    private DataModelInfo _dataModel;
    private Object _rowData;
    private Object _value;
    private ComponentInfo _header;
    private ComponentInfo _footer;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        _dataModel = new DataModelInfo(true, -1, null, 1, null);
        _rowData = new Object();
        _value = new Object();

        _uiData = ComponentFactory.createUIDataInfo("id", null,
                _componentTypeInfo, true, _dataModel, 0, null, null, 1, true,
                _rowData, 2, 3, _value, "row");

        _header = ComponentFactory.createComponentInfo("header", null,
                _componentTypeInfo, true);
        _footer = ComponentFactory.createComponentInfo("footer", null,
                _componentTypeInfo, true);
        _uiDataWithFacetsAtConstruction = ComponentFactory.createUIDataInfo(
                "id3", null, _componentTypeInfo, true, _dataModel, 3, _footer,
                _header, 2, true, _rowData, 1, 0, _value, "row");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testFacets()
    {
        // nothing set on construction
        assertNull(_uiData.getFooter());
        assertNull(_uiData.getFacet(UIDataInfo.FACET_NAME_FOOTER));
        assertNull(_uiData.getHeader());
        assertNull(_uiData.getFacet(UIDataInfo.FACET_NAME_HEADER));
        _uiData.addFacet(UIDataInfo.FACET_NAME_FOOTER,  _footer);
        _uiData.addFacet(UIDataInfo.FACET_NAME_HEADER,  _header);
        
        assertEquals(_footer, _uiData.getFooter());
        assertEquals(_header, _uiData.getHeader());
        
        // set on construction
        assertEquals(_footer, _uiDataWithFacetsAtConstruction.getFooter());
        assertEquals(_header, _uiDataWithFacetsAtConstruction.getHeader());
    }
    
    public void testGetDataModel() {
        RuntimeTestUtil.verifySame(_dataModel, _uiData.getDataModel());
    }

    public void testGetFirst() {
        assertEquals(0, _uiData.getFirst());
    }

    public void testGetRowCount() {
        assertEquals(1, _uiData.getRowCount());
    }

    public void testIsRowAvailable() {
        assertTrue(_uiData.isRowAvailable());
    }

    public void testGetRowData() {
        assertEquals(_rowData, _uiData.getRowData());
    }

    public void testGetRowIndex() {
        assertEquals(2, _uiData.getRowIndex());
    }

    public void testGetRows() {
        assertEquals(3, _uiData.getRows());
    }

    public void testGetValue() {
        assertEquals(_value, _uiData.getValue());
    }

    public void testGetVar() {
        assertEquals("row", _uiData.getVar());
    }

    public void testImplicitAdapter() {
        RuntimeTestUtil.verifyImplicitAdapter(getComponentInfo(),
                ComponentFactory.NAMING_CONTAINER, new INamingContainerInfo() {

                    /**
                     * 
                     */
                    private static final long serialVersionUID = -4727106447103788829L;
                });
    }

    @Override
    public ComponentInfo getComponentInfo() {
        return _uiData;
    }

    @Override
    public void testSerializable() throws Exception {
        final UIDataInfo deserialized = RuntimeTestUtil
                .serializeDeserialize(_uiData);

        RuntimeTestUtil.verifySame(_uiData, deserialized);

        // the Objects may not be serializable

        // if the object is not serializable, then the deserialize will be null
        if (_uiData.getRowData() instanceof Serializable) {
            assertEquals(_uiData.getRowData(), deserialized.getRowData());
        } else {
            assertNull(deserialized.getRowData());
        }

        // if the object is not serializable, then the deserialize will be null
        if (_uiData.getValue() instanceof Serializable) {
            assertEquals(_uiData.getValue(), deserialized.getValue());
        } else {
            assertNull(deserialized.getValue());
        }
    }

}
