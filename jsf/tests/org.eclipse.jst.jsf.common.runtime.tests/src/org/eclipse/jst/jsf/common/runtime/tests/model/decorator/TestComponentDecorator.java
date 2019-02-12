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
package org.eclipse.jst.jsf.common.runtime.tests.model.decorator;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ComponentDecorator;
import org.eclipse.jst.jsf.common.runtime.tests.model.RuntimeTestUtil;

public class TestComponentDecorator extends TestCase {

    private ComponentTypeInfo _componentTypeInfo;
    private ComponentInfo _decorates;
    private ComponentDecorator _componentDecorator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        _componentTypeInfo = new ComponentTypeInfo("org.eclipse.jst.jsf.test",
                "org.eclipse.jst.jsf.test.ComponentClass",
                "org.eclipse.jst.jsf.test.ComponentFamily",
                "org.eclipse.jst.jsf.test.RenderFamily");

        _decorates = ComponentFactory.createComponentInfo("id", null,
                _componentTypeInfo, true);

        _componentDecorator = new MockComponentDecorator(_decorates);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetDecorates() {
        assertEquals(_decorates, _componentDecorator.getDecorates());
    }

    public void testSerializable() throws Exception {
        final ComponentDecorator deserialized = RuntimeTestUtil
                .serializeDeserialize(_componentDecorator);

        RuntimeTestUtil.verifySame(_componentDecorator, deserialized);
    }

    public static class MockComponentDecorator extends ComponentDecorator {
        /**
         * serialization id
         */
        private static final long serialVersionUID = 7317971755600134713L;

        public MockComponentDecorator(final ComponentInfo decorates) {
            super(decorates);
        }
    }
}
