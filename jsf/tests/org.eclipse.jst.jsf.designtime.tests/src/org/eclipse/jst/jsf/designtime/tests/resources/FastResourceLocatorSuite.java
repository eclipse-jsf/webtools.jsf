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

import org.eclipse.jst.jsf.test.util.junit4.DualModeEnvironment;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@IncludeCategory(DualModeEnvironment.class)
@SuiteClasses({TestResourceIdentifierFactory.class,
    TestJarBasedJSFResource.class, TestJarBasedJSFResourceLocator.class,
    TestWorkspaceBasedJSFResource.class, TestWorkspaceBasedResourceLocator.class,
    TestJSFResource.class})
public class FastResourceLocatorSuite
{
    // do nothing, the annotations define the suite in JUnit 4
}
