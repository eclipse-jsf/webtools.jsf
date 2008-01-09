package org.eclipse.jst.jsf.common.runtime.tests.model.component;

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.ValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIOutputInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.RuntimeTestUtil;

public class TestUIOutputInfo extends TestComponentInfo {
    private UIOutputInfo _uiOutputInfo;
    private UIOutputInfo _uiOutputInfo2;
    private ValueHolderInfo _valueHolder;
    private ValueHolderInfo _valueHolder2;
    private ConverterDecorator _converter;
    private ConverterTypeInfo  _converterTypeInfo;
    private UIOutputInfo _uiOutputInfo_NoValueHolderAtConstruction;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        _valueHolder = new ValueHolderInfo(null, "value", "value");
        _uiOutputInfo = ComponentFactory.createUIOutputInfo("id", null,
                _componentTypeInfo, _valueHolder, true);

        _converterTypeInfo = ConverterTypeInfo.UNKNOWN; 
        _converter = new ConverterDecorator(null, _converterTypeInfo);
        _valueHolder2 = new ValueHolderInfo(_converter, "value2", "value2");
        _uiOutputInfo2 = ComponentFactory.createUIOutputInfo("id2", null,
                _componentTypeInfo, _valueHolder2, true);

        _uiOutputInfo_NoValueHolderAtConstruction = ComponentFactory
                .createUIOutputInfo("id", null, _componentTypeInfo, null, true);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetConverter() {
        // not converter by setup
        assertNull(_uiOutputInfo.getConverter());
        assertTrue(_uiOutputInfo.getDecorators(ComponentFactory.CONVERTER)
                .isEmpty());

        // this one has a converter by construction
        assertEquals(_converter, _uiOutputInfo2.getConverter());
        assertEquals(_converter, _uiOutputInfo2.getDecorators(
                ComponentFactory.CONVERTER).get(0));
        assertEquals(_converterTypeInfo, ((ConverterDecorator)_uiOutputInfo2.getDecorators(
                ComponentFactory.CONVERTER).get(0)).getTypeInfo());
    }

    public void testGetLocalValue() {
        assertEquals("value", _uiOutputInfo.getLocalValue());
        assertEquals("value2", _uiOutputInfo2.getLocalValue());
        assertNull(_uiOutputInfo_NoValueHolderAtConstruction.getLocalValue());
    }

    public void testGetValue() {
        assertEquals("value", _uiOutputInfo.getValue());
        assertEquals("value2", _uiOutputInfo2.getValue());
        assertNull(_uiOutputInfo_NoValueHolderAtConstruction.getValue());
    }

    public void testImplicitAdapter() {
        RuntimeTestUtil.verifyImplicitAdapter(getComponentInfo(),
                ComponentFactory.VALUE_HOLDER, _valueHolder);
    }

    @Override
    public void testSerializable() throws Exception {
        final UIOutputInfo deserialized = RuntimeTestUtil
                .serializeDeserialize(_uiOutputInfo);

        RuntimeTestUtil.verifySame(_uiOutputInfo, deserialized);

        final UIOutputInfo deserialized2 = RuntimeTestUtil
                .serializeDeserialize(_uiOutputInfo2);
        RuntimeTestUtil.verifySame(_uiOutputInfo2, deserialized2);
    }

    @Override
    protected ComponentInfo getComponentInfo() {
        return _uiOutputInfo;
    }
}
