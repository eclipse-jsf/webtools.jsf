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

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;

import junit.framework.TestCase;

public class TestComponentTypeInfo extends TestCase {

    private ComponentTypeInfo _componentTypeInfo;

    protected void setUp() throws Exception {
        super.setUp();
        
        _componentTypeInfo = new ComponentTypeInfo("blah", "Blah", "Blah", "renderBlah");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testToString()
    {
        // for now, just test toString to get coverage.  We get enough
        // coverage from the component test classes currently.
        // later we will need to look for more coverage in this classe
        System.out.println(_componentTypeInfo.toString());
    }
}
