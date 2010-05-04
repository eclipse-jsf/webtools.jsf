package org.eclipse.jst.jsf.core.tests.resource;

import org.eclipse.jst.jsf.test.util.junit4.FastTest;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(FastTest.class)
@SuiteClasses(
{ FastLifecycleListenerTests.class, FastLifecycleListenerTests_Scenario.class })
public class AllLifecycleListenerTests
{

}
