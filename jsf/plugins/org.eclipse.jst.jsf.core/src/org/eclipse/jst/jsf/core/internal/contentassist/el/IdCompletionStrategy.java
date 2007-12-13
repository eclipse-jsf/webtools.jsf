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

package org.eclipse.jst.jsf.core.internal.contentassist.el;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider;
import org.eclipse.jst.jsf.context.symbol.provider.ProposalCreationFactoryAdapter;
import org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider.IProposalCreationFactory;
import org.eclipse.jst.jsf.designtime.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.designtime.resolver.StructuredDocumentSymbolResolverFactory;
import org.eclipse.swt.graphics.Image;

/**
 * A strategy for getting completions for EL id completions like:
 * 
 *     var
 *     ^
 *     
 * @author cbateman
 *
 */
class IdCompletionStrategy extends ContentAssistStrategy 
{

    /**
     * Construct a strategy for completing an ID completion
     * 
     * @param prefixValue
     * @param proposalStart - the part of the proposal which was already typed when user invoked autocomplete
     */
    public IdCompletionStrategy(final String prefixValue, String proposalStart) 
    {
        super(ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION, prefixValue, proposalStart);
    }

    /**
     * @see org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistStrategy#getProposals(org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext)
     */
    public List<ICompletionProposal> getProposals(IStructuredDocumentContext context) 
    {
        final List<ICompletionProposal> completionList = new ArrayList<ICompletionProposal>();
        final IWorkspaceContextResolver workspaceResolver = 
            IStructuredDocumentContextResolverFactory.INSTANCE.
                getWorkspaceContextResolver(context);

        final IFile  iFile = (IFile) workspaceResolver.getResource();

        ISymbol[]       symbols = null;

        if (iFile != null)
        {
            final ISymbolContextResolver  symbolResolver =
                StructuredDocumentSymbolResolverFactory.getInstance().
                    getSymbolContextResolver(context);
            
            symbols = symbolResolver.getAllVariables();
        }

        final ComposedAdapterFactory factory =
            new ComposedAdapterFactory(ComposedAdapterFactory.
                                                  Descriptor.Registry.INSTANCE);

        final IProposalCreationFactory  creationInfo =
           new MyProposalFactory(context.getDocumentPosition(), getProposalStart().length());

        for (int i = 0; symbols != null && i < symbols.length; i++)
        {
            final ISymbol  symbol = symbols[i];

            final Object  adapter =  
                factory.adapt(symbol, IContentProposalProvider.class);
                
            if (adapter instanceof IContentProposalProvider)
            {
                final IContentProposalProvider  provider = 
                    (IContentProposalProvider) adapter;
                final ICompletionProposal[] proposal  = 
                    provider.getProposals(symbol, creationInfo);
                if (proposal != null)
                {
                	addProposalsMatchingProposalStart(completionList, proposal);
                }
            }
        }

        return Collections.unmodifiableList(completionList);
    }
    
    private static class MyProposalFactory extends ProposalCreationFactoryAdapter
    {
        private final static int   HIGH_RELEVANCE = 2;
        private final static int   NORMAL_RELEVANCE = 1;
        private final static int   LOW_RELEVANCE = 0;

        /**
         * @param replacementOffset
         * @param replacementLength
         */
        public MyProposalFactory(int replacementOffset, int replacementLength) 
        {
        	/*TODO I changed the meaning of "replacementLength" from "number of chars AFTER cursor to be
        	 * replaced" to "number of chars BEFORE cursor to be replaced. Since "replacementLength"
        	 * has always been 0 (constructor is only called by IdCompletionStrategy.getProposals()),
        	 * this should not change anything, but I don't know if there have been different plans
        	 * for "replacementLength".
        	 * TODO Maybe this change should be done in the super class instead?
        	 */
            super(replacementOffset - replacementLength, replacementLength);
        }

        public ICompletionProposal createProposal(final String replacementText, 
                                                  final String displayText, 
                                                  final String additionalText, 
                                                  final Image displayImage, 
                                                  final Object targetObject) 
        {
            return createDefaultProposal(replacementText, _replacementOffset, 
                                         _replacementLength, 
                                         replacementText.length(), displayImage, 
                                         displayText, null, additionalText, 
                                         getRelevance(targetObject));
        }
        
        private int getRelevance(Object target)
        {
            // put tag vars at top, followed by beans, then implicits and other
            if (target instanceof IInstanceSymbol)
            {
                final IInstanceSymbol symbol = (IInstanceSymbol) target;
                
                switch (symbol.getRuntimeSource().getValue())
                {
                    case ERuntimeSource.TAG_INSTANTIATED_SYMBOL:
                        return HIGH_RELEVANCE;
                    case ERuntimeSource.MANAGED_BEAN_SYMBOL:
                        return NORMAL_RELEVANCE;
                    case ERuntimeSource.BUILT_IN_SYMBOL:
                    case ERuntimeSource.OTHER:
                        return LOW_RELEVANCE;
                }
            }
            
            // if none of the above, return low relevance
            return LOW_RELEVANCE;
        }
    }
}
