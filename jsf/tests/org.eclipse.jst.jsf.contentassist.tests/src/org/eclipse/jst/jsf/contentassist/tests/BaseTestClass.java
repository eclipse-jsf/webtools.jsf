package org.eclipse.jst.jsf.contentassist.tests;

import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistParser;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistStrategy;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper.ContextWrapper;
import org.eclipse.jst.jsp.core.internal.regions.DOMJSPRegionContexts;

public class BaseTestClass extends TestCase {




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
			wrapper = JSFCoreUtilHelper.getDocumentContext(jspFile, docOffset);

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
			wrapper = JSFCoreUtilHelper.getDocumentContext(jspFile, docOffset);


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
			wrapper = JSFCoreUtilHelper.getDocumentContext(jspFile, offset);

			applyMe.apply(JSFCoreUtilHelper.getDocumentContext(jspFile, offset).getContext()
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
