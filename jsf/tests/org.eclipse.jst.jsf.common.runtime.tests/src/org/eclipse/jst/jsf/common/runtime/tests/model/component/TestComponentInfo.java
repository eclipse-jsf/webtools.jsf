/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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

import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.FacetDecorator;
import org.eclipse.jst.jsf.common.runtime.tests.model.RuntimeTestUtil;

public class TestComponentInfo extends ComponentTestCase {

    protected ComponentTypeInfo _componentTypeInfo;
    private ComponentInfo _componentInfo;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        _componentTypeInfo = RuntimeTestUtil.createComponentTypeInfo();

        _componentInfo = ComponentFactory.createComponentInfo("id", null,
                _componentTypeInfo, true);
    }

    public void testToString()
    {
        // just call it for now to get coverage.  toString should only 
        // be used for diagnostics and dumps on these objects anyway...
        System.out.println(getComponentInfo().toString());
    }
    
    /**
     * @return the main test component info. Override in child tests.
     */
    protected ComponentInfo getComponentInfo() {
        return _componentInfo;
    }

    public void testGetId() {
        assertEquals("id", getComponentInfo().getId());
    }

    public void testGetComponentTypeInfo() {
        assertEquals(_componentTypeInfo, getComponentInfo()
                .getComponentTypeInfo());
    }

    public void testIsRendered() {
        assertTrue(getComponentInfo().isRendered());
    }

    public void testChildren() {
        assertTrue(getComponentInfo().getChildren().isEmpty());
        final ComponentInfo componentInfo = ComponentFactory
                .createComponentInfo("id2", getComponentInfo(),
                        _componentTypeInfo, true);
        getComponentInfo().addChild(componentInfo);
        assertEquals(1, getComponentInfo().getChildren().size());
        RuntimeTestUtil.verifySame(componentInfo,
                (ComponentInfo) getComponentInfo().getChildren().get(0));
    }

    public void testFacet() {
        assertTrue(getComponentInfo().getChildren().isEmpty());
        final ComponentInfo componentInfo = ComponentFactory
                .createComponentInfo("id2", getComponentInfo(),
                        _componentTypeInfo, true);
        getComponentInfo().addFacet("header", componentInfo);
        assertEquals(1, getComponentInfo().getChildren().size());
        RuntimeTestUtil.verifySame(componentInfo,
                (ComponentInfo) getComponentInfo().getChildren().get(0));
        assertEquals("header", getComponentInfo().getFacetName(componentInfo));
        assertEquals(componentInfo, getComponentInfo().getFacet("header"));
        
        final List<?> facetDecorators = getComponentInfo().getDecorators(
                ComponentFactory.FACET);
        boolean hasFacet = false;
        for (final Iterator<?> it = facetDecorators.iterator(); it.hasNext();) {
            final FacetDecorator decorator = (FacetDecorator) it.next();

            if ("header".equals(decorator.getName())) {
                RuntimeTestUtil.verifySame(componentInfo, decorator
                        .getDecorates());
                hasFacet = true;
                break;
            }
        }
        assertTrue(hasFacet);

        final List<?> useGetFacets = getComponentInfo().getFacets();
        assertEquals(facetDecorators, useGetFacets);
    }

    public void testGetParent() {
        final ComponentInfo componentInfo = ComponentFactory
                .createComponentInfo("id2", getComponentInfo(),
                        _componentTypeInfo, true);
        assertEquals(getComponentInfo(), componentInfo.getParent());
    }

    @Override
    public void testSerializable() throws Exception {
        final ComponentInfo child = ComponentFactory.createComponentInfo("id2",
                getComponentInfo(), _componentTypeInfo, true);
        getComponentInfo().addChild(child);

        final ComponentInfo facet = ComponentFactory.createComponentInfo("id3",
                getComponentInfo(), _componentTypeInfo, true);
        getComponentInfo().addFacet("header", facet);

        final ComponentInfo deserialized = RuntimeTestUtil
                .serializeDeserialize(getComponentInfo());

        RuntimeTestUtil.verifySame(getComponentInfo(), deserialized);
    }
}
