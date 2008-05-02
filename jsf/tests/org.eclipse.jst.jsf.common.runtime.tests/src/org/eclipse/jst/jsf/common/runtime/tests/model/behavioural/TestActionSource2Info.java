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
package org.eclipse.jst.jsf.common.runtime.tests.model.behavioural;

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.ActionSourceInfo2;

import junit.framework.TestCase;

public class TestActionSource2Info extends TestCase {

    private ActionSourceInfo2 _actionSource2;

    protected void setUp() throws Exception {
        super.setUp();

        _actionSource2 = new ActionSourceInfo2("com.Action",
                "com.ActionListener", true, "com.ActionExpression");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetActionExpression() {
        assertEquals("com.ActionExpression", _actionSource2.getActionExpression());
    }

    public void testAddActionListener() {
        
    }

    public void testGetAction() {
        assertEquals("com.Action", _actionSource2.getAction());
    }

    public void testGetActionListener() {
        assertEquals("com.ActionListener", _actionSource2.getActionListener());
    }

    public void testGetActionListeners() {
        //fail("Not yet implemented");
    }

    public void testIsImmediate() {
        assertTrue(_actionSource2.isImmediate());
    }

}
