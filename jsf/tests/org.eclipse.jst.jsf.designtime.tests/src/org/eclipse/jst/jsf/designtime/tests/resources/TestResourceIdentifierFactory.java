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
package org.eclipse.jst.jsf.designtime.tests.resources;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.util.regex.Pattern;

import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifier;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifierFactory;
import org.eclipse.jst.jsf.designtime.internal.resources.ResourceIdentifierFactory.InvalidIdentifierException;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TestResourceIdentifierFactory
{
    private ResourceIdentifierFactory _factory;

    @Before
    public void setUp() throws Exception
    {
        _factory = new ResourceIdentifierFactory();
    }
    
    @Test
    public void testCreateLibraryResource() throws InvalidIdentifierException
    {
        ResourceIdentifier id = _factory.createLibraryResource("resourceOnly");
        assertEquals("resourceOnly", id.getResourceName());
        
        id = _factory.createLibraryResource("libName/resourceName");
        assertEquals("resourceName", id.getResourceName());
        assertEquals("libName", id.getLibraryName());
        
        id = _factory.createLibraryResource("libName/1_0/resourceName");
        assertEquals("resourceName", id.getResourceName());
        assertEquals("libName", id.getLibraryName());
        
        id = _factory.createLibraryResource("locale/libraryName/1_0/resourceName");
        assertEquals("resourceName", id.getResourceName());
        assertEquals("libraryName", id.getLibraryName());
        
        id = _factory.createLibraryResource("libraryName/1_0/resourceName/resourceVersion");
        assertEquals("resourceName", id.getResourceName());
        assertEquals("libraryName", id.getLibraryName());
        
        id = _factory.createLibraryResource("locale/libraryName/resourceName/resourceVersion");
        assertEquals("resourceName", id.getResourceName());
        assertEquals("libraryName", id.getLibraryName());
        
        id = _factory.createLibraryResource("locale/libraryName/1_0/resourceName/resourceVersion");
        assertEquals("resourceName", id.getResourceName());
        assertEquals("libraryName", id.getLibraryName());
    }
    
    @Test(expected = InvalidIdentifierException.class)
    public void testCreateLibraryResource_InvalidId_BadLibVersion() throws InvalidIdentifierException
    {
        _factory.createLibraryResource("libraryName/bad_version/resourceName");
    }

    @Test
    public void testVersionPatternMatch()
    {
        Pattern versionPattern = ResourceIdentifierFactory.VersionPattern;
        assertTrue(versionPattern.matcher("1").matches());
        assertTrue(versionPattern.matcher("12").matches());
        assertTrue(versionPattern.matcher("123").matches());
        assertTrue(versionPattern.matcher("1_0").matches());
        assertTrue(versionPattern.matcher("12_10").matches());
        assertTrue(versionPattern.matcher("129_10").matches());
        assertTrue(versionPattern.matcher("12_10_1").matches());
        
        assertFalse(versionPattern.matcher("_").matches());
        assertFalse(versionPattern.matcher("1_").matches());
        assertFalse(versionPattern.matcher("_0").matches());
        assertFalse(versionPattern.matcher("1_0_").matches());
        assertFalse(versionPattern.matcher("a").matches());
        assertFalse(versionPattern.matcher("a_0").matches());
        assertFalse(versionPattern.matcher("1_0a").matches());
    }
}
