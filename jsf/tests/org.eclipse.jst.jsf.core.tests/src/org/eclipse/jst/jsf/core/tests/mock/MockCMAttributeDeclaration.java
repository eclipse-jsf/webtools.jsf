/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.mock;

import java.util.Enumeration;

import org.eclipse.wst.xml.core.internal.contentmodel.CMAttributeDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDataType;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;

public class MockCMAttributeDeclaration extends MockCMNode implements
        CMAttributeDeclaration
{

    private CMDataType _cmType;

    public MockCMAttributeDeclaration(String nodeName, CMDataType cmType)
    {
        super(nodeName, CMNode.ATTRIBUTE_DECLARATION);
        _cmType = cmType;
    }

    public String getAttrName()
    {
        return super.getNodeName();
    }

    public CMDataType getAttrType()
    {
        return _cmType;
    }

    public String getDefaultValue()
    {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getEnumAttr()
    {
        throw new UnsupportedOperationException();
    }

    public int getUsage()
    {
        throw new UnsupportedOperationException();
    }
}
