package org.eclipse.jst.jsf.common.runtime.tests.model.component;

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.INamingContainerInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UIFormInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.RuntimeTestUtil;

public class TestUIFormInfo extends TestComponentInfo {

    private UIFormInfo _uiForm1;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        _uiForm1 = ComponentFactory.createUIFormInfo
            ("id", null, _componentTypeInfo, true, true, true);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsPrependId() {
        assertTrue(_uiForm1.isPrependId());
    }

    public void testIsSubmitted() {
        assertTrue(_uiForm1.isSubmitted());
    }

    @Override
    protected ComponentInfo getComponentInfo() {
        return _uiForm1;
    }

    public void testImplicitAdapter() {
        RuntimeTestUtil.verifyImplicitAdapter(getComponentInfo(),
                ComponentFactory.NAMING_CONTAINER, new INamingContainerInfo() {

                    /**
                     * 
                     */
                    private static final long serialVersionUID = -3927882952151032590L;});
    }

    @Override
    public void testSerializable() throws Exception {
        final UIFormInfo uiForm = RuntimeTestUtil.serializeDeserialize(_uiForm1);
        RuntimeTestUtil.verifySame(_uiForm1, uiForm);
    }
}
