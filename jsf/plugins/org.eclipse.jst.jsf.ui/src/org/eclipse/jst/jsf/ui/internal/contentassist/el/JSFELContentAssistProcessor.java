/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.ui.internal.contentassist.el;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistParser;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistStrategy;
import org.eclipse.jst.jsp.core.internal.regions.DOMJSPRegionContexts;
import org.eclipse.wst.xml.core.internal.regions.DOMRegionContext;
import org.eclipse.wst.xml.ui.internal.contentassist.ProposalComparator;

/**
 * The content assist processor for JSF EL partitions on attribute values.
 * 
 * @author cbateman
 *
 */
public class JSFELContentAssistProcessor implements IContentAssistProcessor 
{
	/**
	 * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer, int)
	 */
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
			int documentPosition) 
	{
		final List  proposals = new ArrayList();
		final IStructuredDocumentContext context = 
			IStructuredDocumentContextFactory.INSTANCE.getContext(viewer, documentPosition);
		
		if (context != null)
		{
			ITextRegionContextResolver  resolver = 
				IStructuredDocumentContextResolverFactory.INSTANCE.getTextRegionResolver(context);
			
			if (resolver != null)
			{
				final String regionType = resolver.getRegionType();
				
				if (regionType != null
						&& resolver.matchesRelative(new String[] {DOMRegionContext.XML_TAG_ATTRIBUTE_VALUE}))
				{
					
					String elText = null;
					
					// if we are in the EL content, then get the current region text
					if (DOMJSPRegionContexts.JSP_VBL_CONTENT.equals(regionType))
					{
						elText = resolver.getRegionText().trim();
					}
					// otherwise, we may be at the end of a content region but at
					// the beginning of a closing brace so check to see if the previous
					// region was a VBL_CONTENT
					// TODO: this search algorithm may need improvement
					else if (regionType.equals(DOMJSPRegionContexts.JSP_VBL_CLOSE))
					{
						IStructuredDocumentContext previousContext = 
							resolver.getPreviousContext();
						
						ITextRegionContextResolver prevResolver =
							IStructuredDocumentContextResolverFactory.INSTANCE.getTextRegionResolver(previousContext);
						
						if (prevResolver != null)
						{
                            if (DOMJSPRegionContexts.JSP_VBL_CONTENT.equals(prevResolver.getRegionType()))
                            {
    							resolver = prevResolver;
    							elText = prevResolver.getRegionText().trim();
                            }
                            else if (DOMJSPRegionContexts.JSP_VBL_OPEN.equals(prevResolver.getRegionType()))
                            {
                                elText = "";
                            }
						}
					}
					
                    
					final ContentAssistStrategy strategy = 
                        ContentAssistParser.getPrefix(documentPosition - resolver.getStartOffset() + 1, elText);
                    
					if (strategy != null)
						proposals.addAll(strategy.getProposals(context));
				}
			}
		}
		
        Collections.sort(proposals, new ProposalComparator());
		return (ICompletionProposal[]) proposals.toArray(new ICompletionProposal[0]);
	}


	public IContextInformation[] computeContextInformation(ITextViewer viewer,
			int offset) {
		// no context info
		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		// auto activate when user hits a '.'
		return new char[] {'.'};
	}

	public char[] getContextInformationAutoActivationCharacters() {
		// no auto-activation for context info
		return null;
	}

	public String getErrorMessage() {
		// don't flag errors
		return null;
	}

	public IContextInformationValidator getContextInformationValidator() {
		// don't validate context information
		return null;
	}
}
