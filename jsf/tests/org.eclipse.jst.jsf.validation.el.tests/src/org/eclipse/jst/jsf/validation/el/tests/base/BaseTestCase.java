/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.validation.el.tests.base;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigManager;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.designtime.resolver.AbstractStructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.designtime.resolver.CachingSymbolContextResolver;
import org.eclipse.jst.jsf.designtime.resolver.IStructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.designtime.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsf.validation.el.tests.ELValidationTestPlugin;
import org.eclipse.jst.jsf.validation.internal.ValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.el.ELExpressionValidator;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Base class for all JSP test cases in this plugin
 * 
 * @author cbateman
 *
 */
public abstract class BaseTestCase extends ConfigurableTestCase 
{
    public static final String      PROXY_SETTING_HOST = "proxySettings_Host";
    public static final String      PROXY_SETTING_PORT = "proxySettings_Port";
    public static final String      JSF_FACET_VERSION  = "jsfFacetVersion";
    
    private final JSFVersion            _defaultJSFVersion;
    private IStructuredDocumentSymbolResolverFactory _symbolResolverFactory
        = new MySymbolResolverFactory();;

    /**
     * Default constructor
     */
    public BaseTestCase(JSFVersion defaultJSFVersion)
    {
        super();
        _defaultJSFVersion = defaultJSFVersion;
    }
    
	/**
	 * @param name
	 */
	public BaseTestCase(String name, JSFVersion defaultJSFVersion) {
        super(name);
        _defaultJSFVersion = defaultJSFVersion;
    }

    /**
	 * The dynamic web project test environment
	 */
	protected WebProjectTestEnvironment  _testEnv;
	/**
	 * A handle to the Java project test environment
	 */
	protected JDTTestEnvironment         _jdtTestEnv;
    
	private MyConfiguration              _configuration;

	protected void doStandaloneSetup() 
	{
	    super.doStandaloneSetup();
	    _configuration = new MyConfiguration("www-proxy.uk.oracle.com","80",_defaultJSFVersion);
    }

    protected void doTestSuiteSetup() 
    {
        super.doTestSuiteSetup();
        _configuration = new MyConfiguration(_testConfiguration);
    }

    protected void setUp() throws Exception    
	{
		super.setUp();

        JSFTestUtil.setValidationEnabled(false);
        
        if (_configuration.isProxyEnabled())
        {
            JSFTestUtil.setInternetProxyPreferences
                (true, _configuration.getProxyHostName()
                        , _configuration.getProxyPort());
        }

        // if JSF 1.1, use web facet 2.4, if higher then use 2.5
        final String webProjVersion = 
            (_configuration.getJsfVersion() == JSFVersion.V1_0
                    || _configuration.getJsfVersion() == JSFVersion.V1_1)
                ? "2.4" : "2.5";
        
        _testEnv = new WebProjectTestEnvironment
            ("ELValidationTest_"+this.getClass().getName()+"_"+getName()+"_"+_configuration.getJsfVersion()
                    , JavaFacetUtils.JAVA_50
                    , ProjectFacetsManager.getProjectFacet( "jst.web" ).getVersion(webProjVersion));
        _testEnv.createProject(false);
        assertNotNull(_testEnv);       
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        // sub-classes may custom their JSF env; primarily to decide what version
        // of JSF
        configureJSFEnvironment();
        
        _jdtTestEnv = new JDTTestEnvironment(_testEnv);
        configureJDTTestEnvironment(_jdtTestEnv);
	}
    
	/**
	 * Used to configure the JSF environment. After successful 
	 * return, sub-classes must ensure that their JSF facet is
	 * installed and that there is at least one faces-config (in WEB-INF)
	 * installed
	 * 
	 * @throws Exception
	 */
	protected abstract JSFFacetedTestEnvironment configureJSFEnvironment() throws Exception;
	
	/**
	 * Add all Java and property file resources to the Java source path
	 * needed for testing.  Sub-classes may override.
	 * 
	 * @param jdtTestEnv
	 * @throws Exception
	 */
	protected void configureJDTTestEnvironment(JDTTestEnvironment jdtTestEnv) throws Exception
	{
	    // load enums first, since other classes have dependencies
        TestFileResource resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MyEnum1.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyEnum1", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MyEnum2.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyEnum2", resource.toString());
        
        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MyBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBean", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MapBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MapBean", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MyBeanSettable.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBeanSettable", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/MyBeanSubClass.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "MyBeanSubClass", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/BeanWithMapProperties.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "BeanWithMapProperties", resource.toString());

        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/BeanWithListProperties.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "BeanWithListProperties", resource.toString());
        
        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/ListBean.java.data");
        jdtTestEnv.addSourceFile("src", "beans", "ListBean", resource.toString());
        
        resource = new TestFileResource();
        resource.load(ELValidationTestPlugin.getDefault().getBundle(), 
                      "/testdata/classes/Bundle.properties.data");
        jdtTestEnv.addResourceFile("src", new ByteArrayInputStream(resource.toBytes()), 
                      "beans", "Bundle.properties");
	}
	
    protected void tearDown() throws Exception
    {
        _testEnv.getTestProject().close(null);
        //_testEnv.getTestProject().delete(true, null);
    }
    
    /**
     * Performs pre-condition and other sanity checks on what was done in setUp()
     */
    public void testSanity()
    {
        final IJavaProject javaProject = _jdtTestEnv.getJavaProject(); 
        assertNotNull(javaProject);
        
        try
        {
            IType type = javaProject.findType("beans.MyBean");
            assertNotNull(type);
            
            type = javaProject.findType("beans.MapBean");
            assertNotNull(type);

            type = javaProject.findType("beans.MyBeanSettable");
            assertNotNull(type);

            type = javaProject.findType("beans.MyBeanSubClass");
            assertNotNull(type);
            
            type = javaProject.findType("beans.BeanWithMapProperties");
            assertNotNull(type);

            type = javaProject.findType("beans.BeanWithListProperties");
            assertNotNull(type);

            type = javaProject.findType("beans.ListBean");
            assertNotNull(type);

            IPackageFragmentRoot srcRoot = _jdtTestEnv.getPackageFragmentRoot("src");
            assertTrue(srcRoot.exists());
            IPackageFragment frag = srcRoot.getPackageFragment("beans");
            assertTrue(frag.exists());
            IFolder res = (IFolder) frag.getResource();
            IFile bundleFile = res.getFile("Bundle.properties");
            assertTrue(bundleFile.exists());
            
            JSFAppConfigManager  cfgManager = 
                JSFAppConfigManager.getInstance(_jdtTestEnv.getProjectEnvironment().getTestProject());
            assertNotNull(cfgManager);
            
            List<?> mbeans = cfgManager.getManagedBeans();
            Map<String, ManagedBeanType>  nameTest = new HashMap<String, ManagedBeanType>();
            
            for (final Iterator<?> it = mbeans.iterator(); it.hasNext();)
            {
                ManagedBeanType  mbean = (ManagedBeanType) it.next();
                nameTest.put(mbean.getManagedBeanName().getTextContent(), mbean);
            }

            assertTrue(nameTest.containsKey("myBean"));
            assertTrue(nameTest.containsKey("mapBean"));
            assertTrue(nameTest.containsKey("myBeanSettable"));
            assertTrue(nameTest.containsKey("myBeanSubClass"));
            assertTrue(nameTest.containsKey("beanWithMapProperties"));
            assertTrue(nameTest.containsKey("beanWithListProperties"));
            assertTrue(nameTest.containsKey("listBean"));
        }
        catch(JavaModelException jme)
        {
            assertTrue("JDT error: "+jme.getLocalizedMessage(), false);
        }
        catch (CoreException ce)
        {
            assertTrue("Problem loading bundle: "+ce.getLocalizedMessage(), false);
        }
    }
    
    /**
     * @param document
     * @param docPos
     * @return the ELText at docPos in document or null if no such text
     */
    protected String getELText(IStructuredDocument document, int docPos)
    {
        final IStructuredDocumentContext context = 
            IStructuredDocumentContextFactory.INSTANCE.getContext(document, docPos);
        final ITextRegionContextResolver resolver =
            IStructuredDocumentContextResolverFactory.INSTANCE.getTextRegionResolver(context);
        return resolver.getRegionText();
    }
    
    /**
     * @param document
     * @param docPos
     * @param file
     * @return a new expression validator for docPos in the document
     */
    protected ELExpressionValidator createELValidator(
            IStructuredDocument document, int docPos, IFile file, MockELValidationReporter reporter)
    {
        final String elText = getELText(document, docPos);
        final IStructuredDocumentContext context = 
            IStructuredDocumentContextFactory.INSTANCE.getContext(document, docPos);
        final ValidationPreferences prefs =
            new ValidationPreferences(JSFCorePlugin.getDefault().getPreferenceStore());
        prefs.load();
        
        return new ELExpressionValidator(context, elText, _symbolResolverFactory, reporter);
    }
    
    private static class MySymbolResolverFactory extends AbstractStructuredDocumentSymbolResolverFactory
    {
        private ISymbolContextResolver      _resolver;

        @Override
        public ISymbolContextResolver getSymbolContextResolver(
                IModelContext context)
        {
            if (_resolver == null)
            {
                _resolver = new CachingSymbolContextResolver((IStructuredDocumentContext) context);
            }
            else
            {
                if (!_resolver.hasSameResolution(context))
                {
                    _resolver = new CachingSymbolContextResolver((IStructuredDocumentContext) context);
                }
            }
            return _resolver;
        }
    }
    
    /**
     * Uses assertSyntaxProblems with max severity of error.
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedProblems
     * @return the list of found syntax problems
     */
    protected List<ReportedProblem> assertSyntaxError(IStructuredDocument document, 
            int docPos, 
            IFile file, 
            int expectedProblems)
    {
        return assertSyntaxProblems(document, docPos, file, expectedProblems, IMessage.HIGH_SEVERITY/* "high" is Warning for some reason*/);
    }

    
    /**
     * Uses assertSyntaxProblems with max severity of warning.
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedProblems
     * @return the list of syntax problems
     */
    protected List<ReportedProblem> assertSyntaxWarning(IStructuredDocument document, 
                                          int docPos, 
                                          IFile file, 
                                          int expectedProblems)
    {
        return assertSyntaxProblems(document, docPos, file, expectedProblems, IMessage.NORMAL_SEVERITY/* "normal" is Warning for some reason*/);
    }
    
    /**
     * Checks the el expression in document at docPos in file. Asserts
     * that there exactly the number of expectedProblems expected and that
     * the highest error severity is expectedMaxSeverity.  Returns the list
     * of syntax errors
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedProblems
     * @param expectedMaxSeverity
     * @return the (possibly empty) list of problems
     */
    protected List<ReportedProblem> assertSyntaxProblems(IStructuredDocument document, 
                                          int docPos, 
                                          IFile file, 
                                          int expectedProblems,
                                          int expectedMaxSeverity)
    {
        final MockELValidationReporter reporter = new MockELValidationReporter();

        final ELExpressionValidator validator = 
            createELValidator(document, docPos, file, reporter);
        validator.validateXMLNode();

        final List<ReportedProblem> problems = reporter.getSyntaxProblems();
        assertEquals(expectedProblems, problems.size());
        int worstSeverity = 0;

        for (final ReportedProblem problem : problems)
        {
            // for some reason, the number values are lower for higher severity
            // constants
            worstSeverity = maxSeverity(worstSeverity, problem.getSeverity());
        }
    
        
        assertEquals(expectedMaxSeverity, worstSeverity);
        
        return problems;
    }

    /**
     * Asserts that the provided expression generates no problem diagnostics 
     * whatsever and that the resolved type is as expected
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedSignature
     */
    protected void assertNoError(IStructuredDocument document, int docPos, IFile file, String expectedSignature)
    {
        assertNoError(document, docPos, file, expectedSignature, -1);
    }
    
    /**
     * Asserts that the provided expression generates no problem diagnostics 
     * whatsever and that the resolved type is as expected and checks assignability
     * if value positive, non-zero.
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedSignature
     * @param assignability 
     */
    protected void assertNoError(IStructuredDocument document, int docPos, IFile file, String expectedSignature, int assignability)
    {
        final MockELValidationReporter reporter = new MockELValidationReporter();

        ELExpressionValidator validator = createELValidator(document, docPos, file, reporter);
        validator.validateXMLNode();
        assertEquals(0, reporter.getSyntaxProblems().size());
        assertEquals(0, reporter.getSemanticProblems().size());

        if (expectedSignature != null)
        {
            assertNotNull(validator.getExpressionType());
            assertEquals(expectedSignature, validator.getExpressionType().getSignatures()[0]);
        }
        
        if (assignability >= 0)
        {
            assertEquals(assignability, validator.getExpressionType().getAssignmentTypeMask());
        }
    }
    
    /**
     * Asserts that the provided expression generates one or more problem 
     * diagnostics of which the most severe is of ERROR severity and that
     * the total number expected is returned 
     * Asserts also that no syntax errors are present.
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedSignature
     * @param expectedProblems 
     * @return the list of semantic warnings
     */
    protected List<ReportedProblem> assertSemanticError(IStructuredDocument document, int docPos, IFile file, String expectedSignature, int expectedProblems)
    {
        return assertSemanticProblems(document, docPos, file, expectedSignature, expectedProblems, IMessage.HIGH_SEVERITY/* "high" is Error for some reason*/);
    }
    
    /**
     * Asserts that the provided expression generates one or more problem 
     * diagnostics of which the most severe is of WARNING severity and that
     * the total number expected is returned 
     * Asserts also that no syntax errors are present.
     * 
     * @param document
     * @param docPos
     * @param file
     * @param expectedSignature
     * @param expectedProblems 
     * @return the list of semantic warnings
     */
    protected List<ReportedProblem> assertSemanticWarning(IStructuredDocument document, int docPos, IFile file, String expectedSignature, int expectedProblems)
    {
        return assertSemanticProblems(document, docPos, file, expectedSignature, expectedProblems, IMessage.NORMAL_SEVERITY/* "normal" is Warning for some reason*/);
    }
    
    /**
     * @param document
     * @param docPos
     * @param file
     * @param expectedSignature
     * @param expectedProblems
     * @param expectedMaxSeverity
     * @return the list of semantic problems found
     */
    protected List<ReportedProblem> assertSemanticProblems(final IStructuredDocument document, 
                                          final int docPos, 
                                          final IFile file, 
                                          final String expectedSignature, 
                                          final int expectedProblems,
                                          final int expectedMaxSeverity)
    {
        final MockELValidationReporter reporter = new MockELValidationReporter();

        final ELExpressionValidator validator = 
                createELValidator(document, docPos, file, reporter);
        validator.validateXMLNode();

        if (expectedSignature != null
                && validator.getExpressionType() != null)
        {
            assertEquals(expectedSignature, validator.getExpressionType().getSignatures()[0]);
        }

        assertEquals(0, reporter.getSyntaxProblems().size());
        final List<ReportedProblem> problems = reporter.getSemanticProblems();
        assertEquals(expectedProblems, problems.size());
        int worstSeverity = 0;

        for (final ReportedProblem problem : problems)
        {
            // for some reason, the number values are lower for higher severity
            // constants
            worstSeverity = maxSeverity(worstSeverity, problem.getSeverity());
        }

        assertEquals(expectedMaxSeverity, worstSeverity);

        return problems;
    }
    
    /**
     * Asserts that the list of problems contains one whose id == code
     * 
     * @param problems
     * @param code
     */
    protected void assertContainsProblem(List<ReportedProblem> problems, int code)
    {
        assertContainsProblem(problems, code, -1, -1);
    }
    
    /**
     * Asserts that the list of problems contains one whose id == code
     * If startPos > -1, also checks the offset and length on the matching
     * problem against startPos and length
     * 
     * @param problems
     * @param code
     * @param startPos
     * @param length
     */
    protected void assertContainsProblem(List<ReportedProblem> problems, int code, int startPos, int length)
    {
        final Set<Integer>  probsFound = new HashSet<Integer>();
        
        for (final ReportedProblem problem : problems)
        {
            probsFound.add(new Integer(problem.getErrorCode()));
            if (problem.getErrorCode() == code)
            {
                assertTrue("Offset of message must be >= 0", problem.getOffset()>=0);
                assertTrue("Length of message marker must be >=0", problem.getLength()>=0);
                
                if (startPos >= 0)
                {
                    assertEquals("Offset must be == startPos", startPos, problem.getOffset());
                    assertEquals("Length must be == length", problem.getLength(), length);
                }

                // found the required code, so exit without throwing
                // any error assertions
                return;
            }
        }
        // if we reach this point then we have not found the asserted
        // error code
        assertTrue("Expected find error code matching "+code+" found "+probsFound.toString(), false);
    }
    
    private static int maxSeverity(int sev1, int sev2)
    {
        if (sev1 == 0)
        {
            return sev2;
        }
        else if (sev2 == 0)
        {
            return sev1;
        }
        else
        {
            // if both are non-0, then the most sever is the lowest value
            return Math.min(sev1, sev2);
        }
    }
    
    private static class MyConfiguration
    {
        private final String  _proxyHostName;
        private final String  _proxyPort;
        private final JSFVersion  _jsfVersion;
        
        MyConfiguration(final String proxyHostName, final String proxyPort, final JSFVersion jsfVersion)
        {
            _proxyHostName = proxyHostName;
            _proxyPort = proxyPort;
            _jsfVersion = jsfVersion;
        }
        
        MyConfiguration(TestConfiguration  configuration)
        {
            _proxyHostName = configuration.get(BaseTestCase.PROXY_SETTING_HOST);
            _proxyPort = configuration.get(BaseTestCase.PROXY_SETTING_PORT);
            _jsfVersion = JSFVersion.valueOfString(configuration.get(BaseTestCase.JSF_FACET_VERSION));
        }
        
        public boolean isProxyEnabled()
        {
            return _proxyHostName != null && _proxyPort != null;
        }

        public String getProxyHostName() {
            return _proxyHostName;
        }

        public String getProxyPort() {
            return _proxyPort;
        }

        public JSFVersion getJsfVersion() {
            return _jsfVersion;
        }
        
    }
}