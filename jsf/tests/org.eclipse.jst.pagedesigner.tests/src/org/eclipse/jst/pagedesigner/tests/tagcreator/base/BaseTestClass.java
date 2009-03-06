/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tests.tagcreator.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gef.EditDomain;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.tests.PageDesignerTestsPlugin;
import org.eclipse.wst.html.core.internal.document.DOMStyleModelImpl;
import org.eclipse.wst.html.core.internal.format.HTMLFormatProcessorImpl;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.document.ElementImpl;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class BaseTestClass extends TestCase
{
    protected WebProjectTestEnvironment _webProjectTestEnv;
    protected PaletteItemManager _manager;
    protected final String _compareDataSubDir;

    public BaseTestClass(final String compareDataSubDir)
    {
        _compareDataSubDir = compareDataSubDir;
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);

        _webProjectTestEnv = new WebProjectTestEnvironment(getClass().getName()
                + "_" + getName());
        _webProjectTestEnv.createProject(false);
        assertNotNull(_webProjectTestEnv);
        assertNotNull(_webProjectTestEnv.getTestProject());
        assertTrue(_webProjectTestEnv.getTestProject().isAccessible());

        final JSFFacetedTestEnvironment jsfFacetedTestEnv = new JSFFacetedTestEnvironment(
                _webProjectTestEnv);
        jsfFacetedTestEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

        assertTrue(JSFCoreUtilHelper.addJSFRuntimeJarsToClasspath(
                JSFVersion.V1_1, jsfFacetedTestEnv));

        // ensure this gets called so that the getCurrentInstance
        _manager = PaletteItemManager.getInstance(_webProjectTestEnv
                .getTestProject());
    }

    protected CreationData getCreationData(final String uri,
            final String tagName, final String defaultPrefix, final IFile file,
            final int offset) throws Exception
    {
        final ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper
                .createMetaDataModelContext(
                        _webProjectTestEnv.getTestProject(), uri);

        final TagToolPaletteEntry entry = createNonNullPaletteEntry(uri,
                tagName);

        final ContextWrapper context = getDocumentContext(offset, file);
        final IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                .getDOMContextResolver(context.getContext());
        // we want to simulate a drop inside the the body after the text node
        final Node node = resolver.getNode();
        assertEquals(Node.TEXT_NODE, node.getNodeType());

        final DOMPosition domPosition = new DOMPosition(node, 0);

        return new CreationData(entry, (IDOMModel) context.getModel(),
                domPosition, modelContext, null);
    }

    protected TagToolPaletteEntry createPaletteEntry(final String uri,
            final String tagName)
    {
        final TaglibPaletteDrawer drawer = _manager
                .getTaglibPalletteDrawer(uri);
        assertNotNull(String.format("Uri: %s, tagName: %s", uri, tagName),
                drawer);
        TagToolPaletteEntry entry = drawer.getTagPaletteEntryByTagName(tagName);

        // covers case for HTML where the id is what's important because
        // the tag name is over loaded (i.e. input).
        if (entry == null)
        {
            entry = drawer.getTagPaletteEntryById(tagName);
        }

        return entry;
    }

    protected TagToolPaletteEntry createNonNullPaletteEntry(final String uri,
            final String tagName)
    {
        final TagToolPaletteEntry entry = createPaletteEntry(uri, tagName);
        assertNotNull(entry);
        return entry;
    }

    protected ContextWrapper getDocumentContext(final int offset,
            final IFile jspFile) throws Exception
    {

        assertTrue(jspFile.exists());
        final IModelManager modelManager = StructuredModelManager
                .getModelManager();
        IStructuredModel model = null;
        model = modelManager.getModelForRead(jspFile);
        // jsp, jspx or xhtml
        assertTrue(model instanceof DOMModelForJSP
                || model instanceof DOMStyleModelImpl);
        final IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE
                .getContext(model.getStructuredDocument(), offset);
        return new ContextWrapper(context, model);
    }

    public static class ContextWrapper
    {
        private final IStructuredDocumentContext context;
        private final IStructuredModel model;

        public ContextWrapper(final IStructuredDocumentContext context,
                final IStructuredModel model)
        {
            super();
            this.context = context;
            this.model = model;
        }

        public IStructuredDocumentContext getContext()
        {
            return context;
        }

        public IStructuredModel getModel()
        {
            return model;
        }

        void dispose()
        {
            model.releaseFromRead();
        }
    }

    protected String getExpectedResult(final IPath path) throws IOException
    {
        return JSFTestUtil.loadFromFile(path.toFile()).toString();
    }

    protected final String getExpectedResult(final String tagName,
            final String outExt) throws Exception
    {
        final String ext = outExt == null ? "" : "." + outExt;
        final String fileName = "expectedResult_"
                + tagName.replaceAll(":", "_") + ext + ".data";
        final String pathStr = "/testdata/tagcreator/" + _compareDataSubDir
                + "/" + fileName;
        return getExpectedResult(pathStr);
    }

    protected final String getExpectedResult(final String pathStr)
            throws Exception
    {
        final IPath expectedPath = JSFTestUtil.getAbsolutePath(
                PageDesignerTestsPlugin.getDefault().getBundle(), pathStr);
        return getExpectedResult(expectedPath);
    }

    protected final void assertExpectedResult(final IFile file,
            final String tagName, final String outExt) throws Exception
    {
        final String expected = getExpectedString(file, tagName, outExt);
        final String result = getResultString(file);

        assertEquals(expected, result);
    }

    private String getResultString(final IFile file) throws Exception
    {
        final ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        new HTMLFormatProcessorImpl()
                .formatDocument(getDocumentContext(0, file).getModel()
                        .getStructuredDocument());
        getDocumentContext(0, file).getModel().save(resultStream);
        return resultStream.toString("ISO-8859-1").trim();
    }

    private final String getExpectedString(final IFile file, String tagName,
            String outExt) throws Exception
    {
        final String expectedUnformatted = getExpectedResult(tagName, outExt)
                .trim();

        final IPath path = file.getProjectRelativePath().removeLastSegments(1)
                .append("/expected/");
        final IFolder folder = file.getProject().getFolder(path);

        if (!folder.exists())
        {
            folder.create(true, true, null);
        }

        assertTrue(folder.isAccessible());

        // need to format
        final IFile tempFile = file.getProject().getFile(
                path.append(file.getName()));
        assertFalse(tempFile.exists());
        tempFile.create(
                new ByteArrayInputStream(expectedUnformatted.getBytes()), true,
                null);

        assertTrue(tempFile.isAccessible());
        final IModelManager modelManager = StructuredModelManager
                .getModelManager();
        IStructuredModel model = null;
        model = modelManager.getModelForRead(tempFile);
        new HTMLFormatProcessorImpl().formatDocument(model
                .getStructuredDocument());
        final ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
        model.save(expectedStream);
        return expectedStream.toString("ISO-8859-1").trim();
    }

    protected MockItemCreationTool createMockItemCreationTool(final IFile file,
            final int offset, final TagIdentifier tagId) throws Exception
    {
        return createMockItemCreationTool(file, offset, tagId, IStatus.OK);
    }

    protected MockItemCreationTool createMockItemCreationTool(final IFile file,
            final int offset, final TagIdentifier tagId,
            final int expectedResult) throws Exception
    {
        final TagToolPaletteEntry toolEntry = createNonNullPaletteEntry(tagId
                .getUri(), tagId.getTagName());

        final MockItemCreationTool tool = new MockItemCreationTool(toolEntry);

        final ContextWrapper wrapper = getDocumentContext(offset, file);
        final IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                .getDOMContextResolver(wrapper.getContext());
        final IStructuredModel model = wrapper.getModel();

        final DOMPosition domPosition = new DOMPosition(resolver.getNode(), 0);

        final MockCreateItemCommand command = new MockCreateItemCommand(
                "Test Command", (IDOMModel) model, domPosition, toolEntry);

        tool.setEditDomain(new EditDomain());
        tool.setCurrentCommand(command);

        return tool;
    }

    protected final void forceTagEmpty(final ElementImpl element)
    {
        for (int i = 0; i < element.getChildNodes().getLength(); i++)
        {
            final Node node = element.getChildNodes().item(i);

            if (node instanceof ElementImpl)
            {
                forceTagEmpty((ElementImpl) node);
            }
        }

        // if element has no children, force it to an empty tag
        if (element.getChildNodes().getLength() == 0)
        {
            (element).setEmptyTag(true);
            (element).removeChildNodes();
            final Node copy = (element).cloneNode(false);

            /*
             * ElementImpl.cloneNode(...) seems to have started creating
             * attributes that display differently than the cloned Node's
             * (attr='' rather than attr=""), breaking textual comparisons.
             * By overwriting the existing attributes after cloning, we get the
             * expected form (double quotes instead of single quotes).
             * 
             *  - Ian Trimble, 20090305
             */
            if (element.hasAttributes() && copy instanceof Element) {
	            NamedNodeMap attrMap = element.getAttributes();
                for (int i = 0; i < attrMap.getLength(); i++) {
                    Node attrNode = attrMap.item(i);
                    ((Element)copy).setAttribute(
                            attrNode.getNodeName(), attrNode.getNodeValue());
                }
            }

            element.getParentNode().replaceChild(copy, element);
        }
    }

}
