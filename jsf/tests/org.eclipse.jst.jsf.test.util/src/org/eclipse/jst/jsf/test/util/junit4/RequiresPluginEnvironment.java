package org.eclipse.jst.jsf.test.util.junit4;

/**
 * Marks a JUnit4 test category that must be run using the PDE Plugin Test JUnit
 * Runner and the plugin env that it bootstraps.  These tests cannot accept
 * Workspace mocks.
 * 
 * @author cbateman
 *
 */
public interface RequiresPluginEnvironment extends SlowTest
{

}
