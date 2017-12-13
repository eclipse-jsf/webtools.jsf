package org.eclipse.jst.jsf.test.util.junit4;

public @interface BugRegressionTest
{
    // can be used to specify what bug system the bug was reported in.
    // default == Eclipse
    String bugSystem() default "Eclipse";

    /**
     * @return the bug number that that annotated test method covers
     * regression testing for.
     */
    long bugNumber();
}
