package org.eclipse.jst.jsf.test.util.junit4;

public @interface BugRegressionTest
{
    String bugSystem() default "Eclipse";

    long bugNumber();
}
