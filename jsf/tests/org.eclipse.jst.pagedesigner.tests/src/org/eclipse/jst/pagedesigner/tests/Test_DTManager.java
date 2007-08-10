/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tests;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.dtmanager.DTManager;
import org.eclipse.jst.pagedesigner.dtmanager.IDTInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagConvertInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * DTManager tests.
 * 
 * @author Ian Trimble - Oracle
 */
public class Test_DTManager extends TestCase {

	private WebProjectTestEnvironment webProjectTestEnv;

	protected void setUp() throws Exception {
		super.setUp();

		JSFTestUtil.setValidationEnabled(false);

		webProjectTestEnv = new WebProjectTestEnvironment("Test_DTManager_" + getName());
		webProjectTestEnv.createProject(false);
		assertNotNull(webProjectTestEnv);
		assertNotNull(webProjectTestEnv.getTestProject());
		assertTrue(webProjectTestEnv.getTestProject().isAccessible());

        JSFFacetedTestEnvironment jsfFacetedTestEnv = new JSFFacetedTestEnvironment(webProjectTestEnv);
        jsfFacetedTestEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

        webProjectTestEnv.loadResourceInWebRoot(
        		PageDesignerTestsPlugin.getDefault().getBundle(),
        		"/testdata/Test_DTManager.jsp.data",
        		"/Test_DTManager.jsp");

        assertTrue(Utils.addJSFRuntimeJarsToClasspath("1.1", jsfFacetedTestEnv));
	}

	/**
	 * Sanity check.
	 */
	public void testSanity() {
		ContextWrapper wrapper = null;
		try {
			wrapper = getDocumentContext("/WebContent/Test_DTManager.jsp", 477);
			IStructuredDocumentContext context = wrapper.getContext();
            IDOMContextResolver resolver =
                IStructuredDocumentContextResolverFactory.INSTANCE.
                    getDOMContextResolver(context);
            Node node = resolver.getNode();
            assertTrue(node instanceof Element);
            assertTrue(node.getPrefix().equals("h"));
            assertTrue(node.getLocalName().equals("outputText"));
		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} finally {
			if (wrapper != null) {
				wrapper.dispose();
			}
		}
	}

	/**
	 * Test "getTagConverter(...)" method.
	 */
	public void testGetTagConverter() {
		ContextWrapper wrapper = null;
		try {
			wrapper = getDocumentContext("/WebContent/Test_DTManager.jsp", 477);
			IStructuredDocumentContext context = wrapper.getContext();
            IDOMContextResolver resolver =
                IStructuredDocumentContextResolverFactory.INSTANCE.
                    getDOMContextResolver(context);

            //get Element
            Node node = resolver.getNode();
            assertTrue(node instanceof Element);
            assertTrue(node.getPrefix().equals("h"));
            assertTrue(node.getLocalName().equals("outputText"));
            Element element = (Element)node;

            //get IDOMDocument
            IStructuredModel model = wrapper.getModel();
            assertTrue(model instanceof IDOMModel);
            IDOMDocument document = ((IDOMModel)model).getDocument();
            assertNotNull(document);

            //get ITagConverter instance
            ITagConverter tagConverter = DTManager.getInstance().getTagConverter(
            		element,
            		IConverterFactory.MODE_DESIGNER,
            		document);
            assertNotNull(tagConverter);

            //invoke ITagConverter instance
            tagConverter.convertRefresh(null);

            //test result element to ensure operations were discovered and invoked
            Element resultElement = tagConverter.getResultElement();
            assertNotNull(resultElement);
            assertTrue(resultElement.getLocalName().equals("span"));
            assertNull(resultElement.getAttributeNode("value"));
            NodeList childNodes = resultElement.getChildNodes();
            assertTrue(childNodes.getLength() == 1);
           	Node childNode = childNodes.item(0);
            assertTrue(childNode instanceof Text);
            assertTrue(((Text)childNode).getData().equals("valueValue"));
            assertNull(resultElement.getAttributeNode("styleClass"));
            assertNotNull(resultElement.getAttributeNode("class"));
            assertTrue(resultElement.getAttribute("class").equals("classValue"));
            assertNotNull(resultElement.getAttributeNode("attr1"));
            assertTrue(resultElement.getAttribute("attr1").equals("attr1Value"));
		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} finally {
			if (wrapper != null) {
				wrapper.dispose();
			}
		}
	}

	/**
	 * Test "getDTInfo(...)" method.
	 */
	public void testGetDTInfo() {
		ContextWrapper wrapper = null;
		try {
			wrapper = getDocumentContext("/WebContent/Test_DTManager.jsp", 477);
			IStructuredDocumentContext context = wrapper.getContext();
            IDOMContextResolver resolver =
                IStructuredDocumentContextResolverFactory.INSTANCE.
                    getDOMContextResolver(context);

            //get Element
            Node node = resolver.getNode();
            assertTrue(node instanceof Element);
            assertTrue(node.getPrefix().equals("h"));
            assertTrue(node.getLocalName().equals("outputText"));
            Element element = (Element)node;

            //get IDTInfo instance
            IDTInfo dtInfo = DTManager.getInstance().getDTInfo(element);
            assertNotNull(dtInfo);

            //test IDTInfo instance
            TagConvertInfo tcInfo = dtInfo.getTagConvertInfo();
            List operations = tcInfo.getOperations();
            assertNotNull(operations);
            assertEquals(4, operations.size());
            List tdInfos = dtInfo.getTagDecorateInfos();
            assertEquals(2, tdInfos.size());
            TagDecorateInfo tdInfoDesign = dtInfo.getTagDecorateInfo("vpd-decorate-design");
            assertNotNull(tdInfoDesign);
            assertTrue(tdInfoDesign.isNeedBorderDecorator());
            assertTrue(tdInfoDesign.isMultiLevel());
            assertTrue(tdInfoDesign.isWidget());
            assertTrue(tdInfoDesign.isSetNonVisualChildElements());
            TagDecorateInfo tdInfoPreview = dtInfo.getTagDecorateInfo("vpd-decorate-preview");
            assertNotNull(tdInfoPreview);
            assertTrue(tdInfoPreview.isResolveChildText());
		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} finally {
			if (wrapper != null) {
				wrapper.dispose();
			}
		}
		
	}

    private ContextWrapper getDocumentContext(String path, int offset) throws Exception {
        IProject project = webProjectTestEnv.getTestProject();
        IFile jspFile = project.getFile(new Path(path));
        assertTrue(jspFile.exists());
        final IModelManager modelManager = 
            StructuredModelManager.getModelManager();
        IStructuredModel model = null;
        model = modelManager.getModelForRead(jspFile);
        assertTrue(model instanceof DOMModelForJSP);
        final IStructuredDocumentContext context =
        	IStructuredDocumentContextFactory.INSTANCE.getContext(
        			model.getStructuredDocument(),
        			offset);
        return new ContextWrapper(context, model);
    }

    private static class ContextWrapper {
        private final IStructuredDocumentContext context;
        private final IStructuredModel model;
        ContextWrapper(final IStructuredDocumentContext context, final IStructuredModel model) {
            super();
            this.context = context;
            this.model = model;
        }
        IStructuredDocumentContext getContext() {
            return context;
        }
        IStructuredModel getModel() {
            return model;
        }
        void dispose() {
            model.releaseFromRead();
        }
    }

}
