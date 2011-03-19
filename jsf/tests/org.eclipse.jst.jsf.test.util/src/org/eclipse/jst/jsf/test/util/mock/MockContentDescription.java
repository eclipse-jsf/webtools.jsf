/*******************************************************************************
 * Copyright (c) 2011 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.test.util.mock;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;

public class MockContentDescription implements IContentDescription
{
    private IContentType _contentType;

    public MockContentDescription()
    {
        this (new MockContentType("RandomId_"+System.currentTimeMillis()));
    }
    public MockContentDescription(IContentType mockContentType)
    {
        _contentType = mockContentType;
    }
    public void setProperty(QualifiedName key, Object value)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isRequested(QualifiedName key)
    {
        throw new UnsupportedOperationException();
    }

    public Object getProperty(QualifiedName key)
    {
        return null;
    }

    public void setContentType(final IContentType contentType)
    {
        _contentType = contentType;
    }

    public IContentType getContentType()
    {
        return _contentType;
    }

    public String getCharset()
    {
        return "UTF-8";
    }
}