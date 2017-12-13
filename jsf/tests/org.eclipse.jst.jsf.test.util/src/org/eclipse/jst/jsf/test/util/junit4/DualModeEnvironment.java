package org.eclipse.jst.jsf.test.util.junit4;

/**
 * This class should never be used directly. It is used to mark JUnit test
 * classes that can run either with or without a plugin environment.
 * 
 * Such tests may require a special test runner such as {@link WorkspaceRunner}
 * 
 */
public interface DualModeEnvironment extends NoPluginEnvironment
{
}
