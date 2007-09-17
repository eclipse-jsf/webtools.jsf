package org.eclipse.jst.jsf.validation.el.tests.perf;


import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.el.AbstractDTPropertyResolver;
import org.eclipse.jst.jsf.designtime.el.AbstractDTVariableResolver;
import org.eclipse.jst.jsf.test.util.PerfTracker;
import org.eclipse.jst.jsf.validation.el.tests.base.JSPTestCase;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.ELExpressionValidator;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

/**
 * TODO: should the resolver specific stuff be split to different test plugin?
 * 
 * @author cbateman
 *
 */
public class StressTest extends JSPTestCase 
{
    public StressTest() 
    {
        super(JSFVersion.V1_1, SingleJSPTestCase.FACES_CONFIG_FILE_NAME_1_1);
    }

    protected IFile _testJSP;
    private IStructuredModel _structuredModel;
    private IStructuredDocument _structuredDocument;
    
    
    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        super.setUp();
        
        _testJSP = loadJSP("/testdata/jsps/perfTest1.jsp.data", "/perfTest1.jsp");
        
        _structuredModel = StructuredModelManager.getModelManager().getModelForRead(_testJSP);
        _structuredDocument = _structuredModel.getStructuredDocument();
    }

    @Override
    protected void tearDown() throws Exception 
    {
        super.tearDown();
        
        if (_structuredModel != null)
        {
            _structuredModel.releaseFromRead();
        }
    }

    public void testStressVariableResolver()
    {
        final int      numTimes = 100000;
        
        final AbstractDTVariableResolver  variableRes = 
            DesignTimeApplicationManager.getInstance(_testEnv.getTestProject()).getVariableResolver();
        final DTFacesContext  facesContext = 
            DesignTimeApplicationManager.getInstance(_testEnv.getTestProject()).getFacesContext(_testJSP);
        final PerfTracker perfTracker = new PerfTracker("Stress Variable Resolver", numTimes);
        
        // resolve the same variable 100K times
        for (int x = 0; x < numTimes; x++)
        {
            final long startTime = System.nanoTime();

            ISymbol var = variableRes.resolveVariable(facesContext, "myBean", _testJSP);
            
            perfTracker.recordTime(System.nanoTime() - startTime);  
            
            assertNotNull(var);
            assertEquals("myBean",var.getName());
        }
        
        perfTracker.printReport(System.out);
    }

    public void testStressPropertyResolver()
    {
        final int      numTimes = 1000;
        
        final AbstractDTVariableResolver  variableRes = 
            DesignTimeApplicationManager.getInstance(_testEnv.getTestProject()).getVariableResolver();
        final DTFacesContext  facesContext = 
            DesignTimeApplicationManager.getInstance(_testEnv.getTestProject()).getFacesContext(_testJSP);
        final ISymbol var = variableRes.resolveVariable(facesContext, "myBean", _testJSP);
        final AbstractDTPropertyResolver  propRes =
            DesignTimeApplicationManager.getInstance(_testEnv.getTestProject()).getPropertyResolver();
        final PerfTracker perfTracker = new PerfTracker("Stress Property Resolver", numTimes);
        
        // resolve the same variable 100K times
        for (int x = 0; x < numTimes; x++)
        {
            final long startTime = System.nanoTime();

            final ISymbol property = propRes.getProperty(var, "stringProperty");
            
            perfTracker.recordTime(System.nanoTime() - startTime);  
            
            assertNotNull(property);
            assertEquals("stringProperty",property.getName());
        }
        
        perfTracker.printReport(System.out);
    }
    
    public void testStressSimpleValueBindingValidation()
    {
        final int      numTimes = 1000;
        final int      elOffset = 819;
        assertEquals("myBean.stringProperty", getELText(_structuredDocument,elOffset));

        final PerfTracker perfTracker = 
            new PerfTracker("Stress Simple Bean Property Validation", numTimes);

        // resolve the same variable 100K times
        for (int x = 0; x < numTimes; x++)
        {
            final ELExpressionValidator validator = 
                createELValidator(_structuredDocument, elOffset, _testJSP);

            final long startTime = System.nanoTime();
            
            validator.validateXMLNode();
            
            perfTracker.recordTime(System.nanoTime() - startTime);
        }

        perfTracker.printReport(System.out);
    }
}
