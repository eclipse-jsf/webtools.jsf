package org.eclipse.jst.jsf.core.tests;

import org.eclipse.jst.jsf.core.tests.resource.AllLifecycleListenerTests;
import org.eclipse.jst.jsf.core.tests.resource.TestResourceTracker;
import org.eclipse.jst.jsf.core.tests.serialization.TLDAttributeSerializationTests;
import org.eclipse.jst.jsf.test.util.junit4.FastTest;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(FastTest.class)
@SuiteClasses(
{ AllLifecycleListenerTests.class,
        TestResourceTracker.class, TLDAttributeSerializationTests.class })
public class JSFCoreFastTests
{

}
