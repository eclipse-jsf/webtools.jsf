package org.eclipse.jst.jsf.contentassist.tests;

import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistParser;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistStrategy;
import org.eclipse.jst.jsf.designtime.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.designtime.resolver.StructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.jst.jsp.core.internal.regions.DOMJSPRegionContexts;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

public class BaseTestClass extends TestCase {

	protected static class ContextWrapper {
		private final IStructuredDocumentContext _context;
		private final IStructuredModel _model;

		ContextWrapper(final IStructuredDocumentContext context,
				final IStructuredModel model) {
			super();
			_context = context;
			_model = model;
		}

		IStructuredDocumentContext getContext() {
			return _context;
		}

		IStructuredModel getModel() {
			return _model;
		}

		void dispose() {
			_model.releaseFromRead();
		}
	}

	/**
	 * @param jspFile
	 * @param offset
	 * @return the context wrapper. Caller must ensure dispose is called so
	 *         model is released when finished.
	 * @throws Exception
	 */
	protected final ContextWrapper getDocumentContext(final IFile jspFile,
			final int offset) throws Exception {
		final IModelManager modelManager = StructuredModelManager
				.getModelManager();

		IStructuredModel model = null;

		model = modelManager.getModelForRead(jspFile);
		assertTrue(model instanceof DOMModelForJSP);
		final IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE
				.getContext(model.getStructuredDocument(), offset);
		return new ContextWrapper(context, model);
	}

	protected final void assertELSanity(final IFile jspFile, final int offset,
			final String attrName, final String elExpr) throws Exception {
		ContextWrapper wrapper = null;
		try {
			wrapper = getDocumentContext(jspFile, offset);
			final IStructuredDocumentContext context = wrapper.getContext();
			final IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
					.getDOMContextResolver(context);
			final Node node = resolver.getNode();
			// bJSFTestUtil.getIndexedRegion((IStructuredDocument)
			// context.getStructuredDocument(), 589);
			assertTrue(node instanceof Attr);
			assertEquals(attrName, ((Attr) node).getNodeName());
			assertEquals(elExpr, ((Attr) node).getNodeValue());
		} finally {
			if (wrapper != null) {
				wrapper.dispose();
			}
		}
	}

	protected final void assertELVariableSanity(final IFile jspFile,
			final String varName) throws Exception {
		ContextWrapper contextWrapper = null;

		try {
			contextWrapper = getDocumentContext(jspFile, -1);
			final ISymbolContextResolver symbolResolver = StructuredDocumentSymbolResolverFactory
					.getInstance().getSymbolContextResolver(
							contextWrapper.getContext());
			final ISymbol bundleVar = symbolResolver.getVariable(varName);
			assertNotNull(bundleVar);
		} finally {
			if (contextWrapper != null) {
				contextWrapper.dispose();
			}
		}
	}

	/**
	 * Uses the docOffset to find the EL text used to generate the proposals on.
	 * @param jspFile
	 * @param docOffset
	 * @param exprOffset
	 * @return the proposals
	 * @throws Exception
	 */
	protected final List<ICompletionProposal> getProposals(final IFile jspFile,
			final int docOffset, final int exprOffset) throws Exception {
		ContextWrapper wrapper = null;

		try {
			wrapper = getDocumentContext(jspFile, docOffset);

			final ITextRegionContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
					.getTextRegionResolver(wrapper.getContext());

			assertEquals(DOMJSPRegionContexts.JSP_VBL_CONTENT, resolver.getRegionType());
			final String elText = resolver.getRegionText().trim();
			assertNotNull(elText);

			final ContentAssistStrategy strategy = ContentAssistParser
					.getPrefix(exprOffset, elText);
			assertNotNull(strategy);
			return strategy.getProposals(wrapper.getContext());
		} finally {
			if (wrapper != null) {
				wrapper.dispose();
			}
		}
	}

	/**
	 * This method doesn't use the docOffset to find the EL text.
	 *
	 * @param jspFile
	 * @param exprOffset
	 * @param elText
	 * @return the proposals at 1-based exprOffset into elText
	 * @throws Exception
	 */
	protected final List<ICompletionProposal> getProposals(final IFile jspFile,
			final int docOffset, final String elText, final int exprOffset) throws Exception
	{
		ContextWrapper wrapper = null;

		try {
			wrapper = getDocumentContext(jspFile, docOffset);


			final ContentAssistStrategy strategy = ContentAssistParser
					.getPrefix(exprOffset, elText);
			assertNotNull(strategy);
			return strategy.getProposals(wrapper.getContext());
		} finally {
			if (wrapper != null) {
				wrapper.dispose();
			}
		}

	}

    protected final void applyAndCheck(final IFile jspFile, final int offset, final ICompletionProposal applyMe, final String expectedResult)
			throws Exception {
		ContextWrapper wrapper = null;

		try {
			wrapper = getDocumentContext(jspFile, offset);

			applyMe.apply(getDocumentContext(jspFile, offset).getContext()
					.getStructuredDocument());

			final ITextRegionContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
					.getTextRegionResolver(wrapper.getContext());

			final String newELText = resolver.getRegionText();
			assertEquals(expectedResult, newELText);
		} finally {
			if (wrapper != null) {
				wrapper.dispose();
			}
		}
	}

	/**
	 * @param expected
	 * @param proposals
	 */
	protected void assertDisplayNamesMatch(final Set<String> expected, final List<ICompletionProposal>  proposals) {
		assertEquals(expected.size(), proposals.size());

		for (final ICompletionProposal proposal : proposals)
		{
			expected.contains(proposal.getDisplayString());
		}
	}
}
