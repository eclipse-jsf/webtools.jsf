package org.eclipse.jst.jsf.test.util.sanity;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.test.util.Activator;
import org.eclipse.jst.jsf.test.util.ProjectTestEnvironment;

/**
 * Test the base Project Test Environment
 * 
 * @author cbateman
 *
 */
public class TestProjectTestEnvironment extends TestCase 
{
	/**
	 * Test creating a basic test project environment
	 */
	public void testCreateProject()
	{
//		InternetPlugin iPlugin = InternetPlugin.getInstance();
//		IPreferenceStore store = iPlugin.getPreferenceStore();
		
		ProjectTestEnvironment testEnv = new ProjectTestEnvironment("TestProject1");
		testEnv.createProject();
		assertTrue(testEnv.isProjectCreated());
		
		IProject project = testEnv.getTestProject();
		
		assertNotNull(project);
		assertTrue(project.isAccessible());
	}
    
    /**
     * Test creating a web project and adding a faces-config.xml file to it
     */
    public void testAddFileToWebRoot()
    {
        ProjectTestEnvironment testEnv = new ProjectTestEnvironment("TestProject2");
        testEnv.createProject();
        assertTrue(testEnv.isProjectCreated());
        
        try
        {
            testEnv.loadResourceInWebRoot(Activator.getDefault().getBundle(), 
                                       "/testdata/faces-config.xml.data", 
                                       "/WEB-INF/faces-config.xml");
        }
        catch (Exception e)
        {
            fail(e.getLocalizedMessage());
        }
    }
}
