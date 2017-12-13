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
package org.eclipse.jst.jsf.common.runtime.tests.model.component;

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.ActionSourceInfo2;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.UICommandInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ActionListenerDecorator;
import org.eclipse.jst.jsf.common.runtime.tests.model.RuntimeTestUtil;

public class TestUICommandInfo extends TestComponentInfo {
    private ActionListenerDecorator _actionListener;
    private ActionSourceInfo2 _actionSourceInfo;
    private ActionSourceInfo2 _actionSourceInfo2;
    private UICommandInfo _uiCommand1;
    private UICommandInfo _uiCommand_NoActionSourceAtConstruction;
    private UICommandInfo _uiCommandActionListenerAtConstruction;

    protected void setUp() throws Exception {
        super.setUp();

        _actionSourceInfo = new ActionSourceInfo2("bean.Action",
                "com.ActionListener", true, "bean.Action");
        _uiCommand1 = ComponentFactory.createUICommandInfo("id", null,
                _componentTypeInfo, _actionSourceInfo, true);

        _uiCommand_NoActionSourceAtConstruction = ComponentFactory
                .createUICommandInfo("id2", null, _componentTypeInfo, null,
                        false);

        _actionSourceInfo2 = new ActionSourceInfo2("bean.Action",
                "com.ActionListener", false, "bean.Action");
        _actionListener = new ActionListenerDecorator(null);
        _actionSourceInfo2.addActionListener(_actionListener);
        _uiCommandActionListenerAtConstruction = ComponentFactory
                .createUICommandInfo("id3", null, _componentTypeInfo,
                        _actionSourceInfo2, true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetActionExpression() {
        assertEquals("bean.Action", _uiCommand1.getActionExpression());
        assertNull(_uiCommand_NoActionSourceAtConstruction
                .getActionExpression());
    }

    public void testGetAction() {
        assertEquals("bean.Action", _uiCommand1.getAction());
        assertNull(_uiCommand_NoActionSourceAtConstruction.getAction());
    }

    public void testGetActionListener() {
        assertEquals("com.ActionListener", _uiCommand1.getActionListener());
        assertNull(_uiCommand_NoActionSourceAtConstruction.getActionListener());
    }

    public void testImplicitAdapter() {
        RuntimeTestUtil.verifyImplicitAdapter(getComponentInfo(),
                ComponentFactory.ACTION_SOURCE, _actionSourceInfo);
        RuntimeTestUtil.verifyImplicitAdapter(getComponentInfo(),
                ComponentFactory.ACTION_SOURCE2, _actionSourceInfo);
    }

    public void testGetActionListeners() {
        assertTrue(_uiCommandActionListenerAtConstruction.getActionListeners()
                .contains(_actionListener));
        assertTrue(_uiCommandActionListenerAtConstruction.getDecorators(
                ComponentFactory.ACTION_LISTENER).contains(
                        _actionListener));

        final ActionListenerDecorator actionListener = 
            new ActionListenerDecorator(_uiCommand1);
        _uiCommand1.addActionListener(actionListener);

        assertEquals(1, _uiCommand1.getActionListeners().size());
        assertTrue(_uiCommand1.getActionListeners().contains(
                actionListener));
        assertTrue(_uiCommand1.getDecorators(
                ComponentFactory.ACTION_LISTENER).contains(
                actionListener));
    }

    public void testIsImmediate() {
        assertTrue(_uiCommand1.isImmediate());
    }

    @Override
    public void testSerializable() throws Exception {
        UICommandInfo commandInfo = RuntimeTestUtil
                .serializeDeserialize(_uiCommand1);
        RuntimeTestUtil.verifySame(_uiCommand1, commandInfo);
    }

    @Override
    protected ComponentInfo getComponentInfo() {
        return _uiCommand1;
    }

}
