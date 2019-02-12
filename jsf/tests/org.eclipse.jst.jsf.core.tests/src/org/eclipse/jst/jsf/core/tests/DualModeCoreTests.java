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
package org.eclipse.jst.jsf.core.tests;

import org.eclipse.jst.jsf.core.tests.resource.AllLifecycleListenerTests;
import org.eclipse.jst.jsf.core.tests.resource.FastClasspathEntryLifecycleTests;
import org.eclipse.jst.jsf.core.tests.resource.TestDefaultJarLocator;
import org.eclipse.jst.jsf.core.tests.resource.TestResourceTracker;
import org.eclipse.jst.jsf.core.tests.serialization.TLDAttributeSerializationTests;
import org.eclipse.jst.jsf.test.util.junit4.DualModeEnvironment;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Tests that can be run as either plugin or regular JUnit tests.
 * @author cbateman
 *
 */
@RunWith(Suite.class)
@IncludeCategory(DualModeEnvironment.class)
@SuiteClasses(
{ AllLifecycleListenerTests.class,
        TestResourceTracker.class,TLDAttributeSerializationTests.class ,
        FastClasspathEntryLifecycleTests.class, TestDefaultJarLocator.class})
public class DualModeCoreTests
{
}
