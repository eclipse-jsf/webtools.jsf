package org.eclipse.jst.jsf.designtime.tests;

import org.eclipse.jst.jsf.designtime.tests.resources.FastResourceLocatorSuite;
import org.eclipse.jst.jsf.test.util.junit4.DualModeEnvironment;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@IncludeCategory(DualModeEnvironment.class)
@SuiteClasses(
{ FastResourceLocatorSuite.class})
public class DesignTimeFastTests
{

}
