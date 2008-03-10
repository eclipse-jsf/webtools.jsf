package org.eclipse.jst.jsf.designtime.tests.views.model.jsp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory.TagRegistryFactoryException;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.TLDRegistryManager.MyRegistryFactory;
import org.eclipse.jst.jsf.designtime.tests.views.model.jsp.VerifyRegistryUtil.CompositeVerifier;

public class TestTLDTagRegistry extends BaseTestClass
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

    public void testGetRegistry() throws TagRegistryFactoryException
    {
        final ITagRegistry registry = new MyRegistryFactory()
                .createTagRegistry(_webProjectTestEnv.getTestProject());

        assertNotNull(registry);
    }

    public void testGetAllTagLibraries() throws TagRegistryFactoryException
    {
        final ITagRegistry registry = new MyRegistryFactory()
                .createTagRegistry(_webProjectTestEnv.getTestProject());

        assertNotNull(registry);

        final Collection<? extends Namespace> libraries = registry
                .getAllTagLibraries();

        final Map<String, Map<String, ITagElement>> elements = new HashMap<String, Map<String, ITagElement>>();

        for (final Namespace ns : libraries)
        {
            for (final Iterator<?> it = ns.getViewElements().iterator(); it
                    .hasNext();)
            {
                final ITagElement tagElement = (ITagElement) it.next();
                assertNotNull(
                        "Tag element shouldn't be null: " + ns.getNSUri(),
                        tagElement);

                Map<String, ITagElement> elementMap = elements.get(ns
                        .getNSUri());

                if (elementMap == null)
                {
                    elementMap = new HashMap<String, ITagElement>();
                    elements.put(ns.getNSUri(), elementMap);
                }

                elementMap.put(tagElement.getName(), tagElement);
            }
        }

        assertTrue(elements.containsKey(ITLDConstants.URI_JSF_CORE));
        assertTrue(elements.containsKey(ITLDConstants.URI_JSF_HTML));

        verifyCore(elements.get(ITLDConstants.URI_JSF_CORE));
        verifyHtml(elements.get(ITLDConstants.URI_JSF_HTML));
    }

    private void verifyCore(final Map<String, ITagElement> tagElements)
    {
        CompositeVerifier compVerifier = null;

        switch (_jsfVersion)
        {
        case V1_0:
        case V1_1:
            compVerifier = new CompositeVerifier(
                    VerifyRegistryUtil.CORE_VERIFIERS_11, 18, 18);
            break;
        case V1_2:
            compVerifier = new CompositeVerifier(
                    VerifyRegistryUtil.CORE_VERIFIERS_12, 20, 20);
            break;
        }
        
        assertNotNull(compVerifier);
        compVerifier.verify(tagElements);
    }

    private void verifyHtml(final Map<String, ITagElement> tagElements)
    {
        CompositeVerifier compVerifier =  new CompositeVerifier(
                    VerifyRegistryUtil.HTML_VERIFIERS, 25, 25);
       
        compVerifier.verify(tagElements);
    }

}
