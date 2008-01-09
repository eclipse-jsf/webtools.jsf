package org.eclipse.jst.jsf.common.runtime.tests.model.component;

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.EditableValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIInputInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValueChangeListenerDecorator;
import org.eclipse.jst.jsf.common.runtime.tests.model.RuntimeTestUtil;

public class TestUIInputInfo extends TestUIOutputInfo {

    private UIInputInfo _uiComponentInfo1;
    private UIInputInfo _uiComponentInfo_NoValueHolderAtConstruction;
    private UIInputInfo _uiInput_ValidatorAndListenerAtConstruction;
    private EditableValueHolderInfo _editableValueHolder;
    private EditableValueHolderInfo _editableValueHolder2;
    private ValidatorDecorator _validator;
    private ValueChangeListenerDecorator _valueChangeListener;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        _uiComponentInfo_NoValueHolderAtConstruction = ComponentFactory
                .createUIInputInfo("idNoConstruction", null,
                        _componentTypeInfo, null, true);

        _editableValueHolder = new EditableValueHolderInfo(null, "value",
                "value", true, true, false, true, null, null, null);

        _uiComponentInfo1 = ComponentFactory.createUIInputInfo("id", null,
                _componentTypeInfo, _editableValueHolder, true);

        _editableValueHolder2 = new EditableValueHolderInfo(null, "value2",
                "value2", true, true, false, true, null, null, null);
        _validator = new ValidatorDecorator(null);
        _editableValueHolder2.addValidator(_validator);
        _valueChangeListener = new ValueChangeListenerDecorator(null);
        _editableValueHolder2.addValueChangeListener(_valueChangeListener);
        _uiInput_ValidatorAndListenerAtConstruction = ComponentFactory
                .createUIInputInfo("id", null, _componentTypeInfo,
                        _editableValueHolder2, true);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsValid() {
        assertFalse(_uiComponentInfo1.isValid());
        assertTrue(_uiComponentInfo_NoValueHolderAtConstruction.isValid());
    }

    public void testIsImmediate() {
        assertTrue(_uiComponentInfo1.isImmediate());
        assertFalse(_uiComponentInfo_NoValueHolderAtConstruction.isImmediate());
    }

    public void testIsRequired() {
        assertTrue(_uiComponentInfo1.isRequired());
        assertFalse(_uiComponentInfo_NoValueHolderAtConstruction.isRequired());
    }

    public void testIsLocalValueSet() {
        assertTrue(_uiComponentInfo1.isLocalSetValue());
        assertFalse(_uiComponentInfo_NoValueHolderAtConstruction
                .isLocalSetValue());
    }

    public void testValidators() {
        assertTrue(_uiInput_ValidatorAndListenerAtConstruction.getValidators()
                .contains(_validator));
        assertTrue(_uiInput_ValidatorAndListenerAtConstruction.getDecorators(
                ComponentFactory.VALIDATOR).contains(_validator));

        final ValidatorDecorator validator = new ValidatorDecorator(
                _uiComponentInfo1);
        _uiComponentInfo1.addValidator(validator);

        assertEquals(1, _uiComponentInfo1.getValidators().size());
        assertTrue(_uiComponentInfo1.getValidators().contains(validator));
        assertTrue(_uiComponentInfo1.getDecorators(ComponentFactory.VALIDATOR)
                .contains(validator));
    }

    public void testValueChangeListener() {
        assertTrue(_uiInput_ValidatorAndListenerAtConstruction
                .getValueChangeListeners().contains(_valueChangeListener));
        assertTrue(_uiInput_ValidatorAndListenerAtConstruction.getDecorators(
                ComponentFactory.VALUE_CHANGE_LISTENER).contains(
                _valueChangeListener));

        final ValueChangeListenerDecorator valueChangeListener = new ValueChangeListenerDecorator(
                _uiComponentInfo1);
        _uiComponentInfo1.addValueChangeListener(valueChangeListener);

        assertEquals(1, _uiComponentInfo1.getValueChangeListeners().size());
        assertTrue(_uiComponentInfo1.getValueChangeListeners().contains(
                valueChangeListener));
        assertTrue(_uiComponentInfo1.getDecorators(
                ComponentFactory.VALUE_CHANGE_LISTENER).contains(
                valueChangeListener));

    }

    @Override
    protected ComponentInfo getComponentInfo() {
        return _uiComponentInfo1;
    }

    @Override
    public void testImplicitAdapter() {
        super.testImplicitAdapter();
        RuntimeTestUtil.verifyImplicitAdapter(_uiComponentInfo1,
                ComponentFactory.EDITABLE_VALUE_HOLDER, _editableValueHolder);
    }

    @Override
    public void testSerializable() throws Exception {
        final UIInputInfo deserialized = RuntimeTestUtil
                .serializeDeserialize(_uiComponentInfo1);

        RuntimeTestUtil.verifySame(_uiComponentInfo1, deserialized);
    }
}
