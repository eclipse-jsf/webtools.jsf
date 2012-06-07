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


package org.eclipse.jst.jsf.core.tests.facet;


/**
 * @author Debajit Adhikary
 *
 */
public class JsfLibraryValidatorTest extends LibraryValidatorTest
{
    private static final String CLASS_NAME_IDENTIFYING_IMPLEMENTATION_JAR = "javax/faces/render/RenderKit.class"; //$NON-NLS-1$
    /*
    private static final String JARFILE = "testfiles/facet/jsflibrary-api-1.1.3.jar";  //$NON-NLS-1$
    private static final String JARFILE_WITHOUT_IMPLEMENTATION_VERSION_ENTRY = "testfiles/facet/no-version-entry/jsflibrary-api-1.1.3.jar"; //$NON-NLS-1$
    private static final String JARFILE_WITH_NONSTANDARD_IMPLEMENTATION_VERSION_ENTRY = "testfiles/facet/nonstandard_implementation_version/jsflibrary-api-1.1.3.jar"; //$NON-NLS-1$
    private static final String EXPECTED_LIBRARY_VERSION = "1.1.3"; //$NON-NLS-1$
	*/

    /**
     * @param name
     */
    public JsfLibraryValidatorTest (final String name)
    {
        super(name,
              CLASS_NAME_IDENTIFYING_IMPLEMENTATION_JAR
              /*
              JARFILE,
              JARFILE_WITHOUT_IMPLEMENTATION_VERSION_ENTRY,
              JARFILE_WITH_NONSTANDARD_IMPLEMENTATION_VERSION_ENTRY,
              EXPECTED_LIBRARY_VERSION*/
              );
    }
}