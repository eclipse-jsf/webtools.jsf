package org.eclipse.jst.jsf.test.util.junit4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field in a JUnit test into which the base location for the test's data
 * is to be injected.  The field must be of type java.io.File.  Resources
 * loaded by the test should be referenced relative to this base location.
 * 
 * Generally, the base location will be dictated by the runtime.
 * 
 * @author cbateman
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TestDataBaseLocation
{
}
