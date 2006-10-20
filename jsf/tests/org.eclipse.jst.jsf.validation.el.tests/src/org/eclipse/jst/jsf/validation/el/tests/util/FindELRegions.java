package org.eclipse.jst.jsf.validation.el.tests.util;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsf.validation.el.tests.ELValidationTestPlugin;
import org.eclipse.jst.jsf.validation.el.tests.util.CreateTestCaseForJSP.ELRegionHandler;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionCollection;

public class FindELRegions extends TestCase
{
    private final static String[]  jspFiles =
    {
        "arithmeticAdd"
        , "arithmeticDivide"
        , "arithmeticMinus"
        , "arithmeticModulo"
        , "arithmeticMultiply"
        , "assignability"
        , "badSyntax"
        , "beanScoping"
        , "beansProperties"
        , "beanSubClass"
        , "builtinSymbols"
        , "complexArithmetic1"
        , "complexComparison"
        , "emptyOperator"
        , "expressionTypeChecking"
        , "greaterThan"
        , "greaterThanEq"
        , "htmlSyntax"
        , "jspFunctions"
        , "lessThan"
        , "lessThanEq"
        , "listBeans"
        , "loadBundleExpressions"
        , "logicalAND"
        , "logicalEquals"
        , "logicalNOT"
        , "logicalNotEquals"
        , "logicalOR"
        , "methodBinding"
        , "test"
        , "unaryMinus"
        , "variableNaming"
    };
    
    private final static IFile[]  files = new IFile[jspFiles.length];
    private final static IStructuredModel[]  models = new IStructuredModel[jspFiles.length];

    
    private WebProjectTestEnvironment  _testEnv;
    
  
    protected void setUp() throws Exception {
        super.setUp();

        _testEnv = new WebProjectTestEnvironment("ELValidationTest_"+getName());
        _testEnv.createProject();
        assertNotNull(_testEnv);       
        assertNotNull(_testEnv.getTestProject());
        assertTrue(_testEnv.getTestProject().isAccessible());

        for (int i = 0; i < jspFiles.length; i++)
        {
            files[i] = 
                (IFile) 
                _testEnv.loadResourceInWebRoot(ELValidationTestPlugin.getDefault().getBundle(),
                    "/testdata/jsps/"+jspFiles[i]+".jsp.data", 
                    "/WEB-INF/"+jspFiles[i]+".jsp");
            
            models[i] = StructuredModelManager.getModelManager().getModelForRead(files[i]);
        }
    }

    protected void tearDown() throws Exception 
    {
        super.tearDown();
        
        for (int i = 0; i < jspFiles.length; i++)
        {
            models[i].releaseFromRead();
        }
    }
    
    public void testGenReport()
    {
        for (int i = 0; i < models.length; i++)
        {
            printRegionsForModel(i);
        }
    }
    
    
    
    private void printRegionsForModel(int i)
    {
        IStructuredDocument document = models[i].getStructuredDocument();
        
        ELRegionHandler handler = new ELRegionHandler()
        {
            public void handleRegion(ITextRegionCollection parentRegion, ITextRegion elRegion) {
                final int contentStart = 
                    parentRegion.getStartOffset(elRegion);
                
                final String elTextStr = "\""+parentRegion.getText(elRegion)+ "\"";
                System.out.println("assertEquals("+elTextStr+", getELText(_structuredDocument,"+contentStart+"));");

            }
        };


        CreateTestCaseForJSP.processJSP(document, handler);
    }
}
