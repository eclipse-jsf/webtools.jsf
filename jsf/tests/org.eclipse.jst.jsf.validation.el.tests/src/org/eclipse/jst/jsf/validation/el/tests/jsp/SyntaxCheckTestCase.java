package org.eclipse.jst.jsf.validation.el.tests.jsp;

import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.JSPSemanticsValidator;

public class SyntaxCheckTestCase extends SingleJSPTestCase 
{
    private final JSPSemanticsValidator validator;
    
    protected SyntaxCheckTestCase(String srcFileName, String destFileName,
            String defaultJSFVersion, String defaultFacesConfigFile) 
    {
        super("/testdata/jsps/syntaxCheck.jsp.data", "/syntaxCheck.jsp", IJSFCoreConstants.FACET_VERSION_1_1, FACES_CONFIG_FILE_NAME_1_1);
        validator = new JSPSemanticsValidator();
        validator.connect(_structuredDocument);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    public void testWarningExprs() 
    {
        IStructuredDocumentContext context = 
            IStructuredDocumentContextFactory.INSTANCE.getContext(_structuredDocument, 877);
        ITextRegionContextResolver resolver =
            IStructuredDocumentContextResolverFactory.INSTANCE.getTextRegionResolver(context);

    
    }

    @Override
    public void testErrorExprs() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void testNoErrorExprs()
    {
        // do nothing
        
    }

    
}
