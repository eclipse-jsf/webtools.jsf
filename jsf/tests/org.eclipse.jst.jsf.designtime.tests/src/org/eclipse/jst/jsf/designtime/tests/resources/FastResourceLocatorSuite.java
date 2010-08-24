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
