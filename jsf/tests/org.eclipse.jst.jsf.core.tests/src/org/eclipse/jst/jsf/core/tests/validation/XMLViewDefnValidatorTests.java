package org.eclipse.jst.jsf.core.tests.validation;

import java.util.zip.ZipFile;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

/**
 * General testing for the XMLViewDefnValidator.
 * 
 * @author cbateman
 * 
 */
public class XMLViewDefnValidatorTests extends TestCase
{
    private WebProjectTestEnvironment _webProject;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        final ZipFile zipFile = JSFTestUtil.createZipFile(TestsPlugin.getDefault().getBundle()
                , "/testfiles/testzips/ValidationTestProject1.zip");

        _webProject = new WebProjectTestEnvironment("ValidationProject223");
        _webProject.createFromZip(zipFile, true);
    }

    public void testUnzip()
    {

    }
}
