package org.eclipse.jst.jsf.context.symbol.tests;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.jdt.core.IType;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.osgi.framework.Bundle;

/**
 * @author cbateman
 *
 */
public class ModelBaseTestCase extends TestCase 
{
    /**
     * The test environment for a JavaProject
     */
    protected JDTTestEnvironment      _jdtTestEnvironment;
    
    /**
     * The base source folder name for Java classes
     */
    protected final static String srcFolderName = "src";

    protected void setUp() throws Exception 
    {
        super.setUp();
        
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com", "80");
        
        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestJDTBeanIntrospectorProject");
        projectTestEnvironment.createProject();
        
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);
    }

    /**
     * @param bundle
     * @param fileName
     * @param packageName
     * @param beanClassName
     * @throws Exception
     */
    protected void loadSourceClass(final Bundle bundle, final String fileName, final String packageName, final String beanClassName) throws Exception
    {
        TestFileResource codeRes = new TestFileResource();
        codeRes.load(bundle, fileName);
        String code = codeRes.toString();
        _jdtTestEnvironment.addSourceFile(srcFolderName, packageName, beanClassName, code);
        assertNotNull(_jdtTestEnvironment.getJavaProject().findType(packageName+"."+beanClassName));
    }
    
    /**
     * @param bundle 
     * @param fileName
     * @param packageName
     * @param beanClassName
     * @param properties
     * @return a bean instance set up for the indicated test class
     * @throws Exception
     */
    protected IBeanInstanceSymbol setupBeanProperty(Bundle bundle, String fileName, String packageName, String beanClassName, Map properties) throws Exception
    {
        loadSourceClass(bundle, fileName, packageName, beanClassName);
        
        final IType testBean1Type = 
            _jdtTestEnvironment.getJavaProject().findType(packageName+"."+beanClassName);
        assertNotNull(testBean1Type);
        
        final IJavaTypeDescriptor2 testBeanDescriptor = 
            SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        testBeanDescriptor.setType(testBean1Type);
        
        IBeanInstanceSymbol  bean = 
            SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        bean.setTypeDescriptor(testBeanDescriptor);
        bean.setName(beanClassName);
        List propertyList = bean.getProperties();
        for(final Iterator it = propertyList.iterator(); it.hasNext();)
        {
            final IBeanPropertySymbol  property = 
                (IBeanPropertySymbol) it.next();
            properties.put(property.getName(), property);
        }
        
        return bean;
    }
}
