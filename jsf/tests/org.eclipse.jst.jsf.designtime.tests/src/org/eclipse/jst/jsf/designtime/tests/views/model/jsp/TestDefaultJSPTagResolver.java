package org.eclipse.jst.jsf.designtime.tests.views.model.jsp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.DefaultJSPTagResolver;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.JSPTagResolvingStrategy;

public class TestDefaultJSPTagResolver extends BaseStrategyTestClass
{

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetId()
    {
        assertEquals("org.eclipse.jst.jsf.THISISTEMPORARY", _strategy.getId());
    }

    @Override
    protected JSPTagResolvingStrategy createStrategy()
    {
        return new DefaultJSPTagResolver(_webProjectTestEnv.getTestProject());
    }

    @Override
    protected List<String> getTestUris()
    {
        return Collections.unmodifiableList(Arrays.asList(new String[]
        { ITLDConstants.URI_JSF_CORE, ITLDConstants.URI_JSF_HTML }));
    }
}
