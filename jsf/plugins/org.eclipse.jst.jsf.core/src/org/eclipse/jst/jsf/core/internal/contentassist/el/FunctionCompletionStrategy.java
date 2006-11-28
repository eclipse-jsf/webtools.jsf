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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.provider.IContentProposalProvider;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.provider.ProposalCreationFactoryAdapter;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.provider.IContentProposalProvider.IProposalCreationFactory;
import org.eclipse.jst.jsf.designtime.internal.provisional.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.designtime.internal.provisional.resolver.StructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IValidELValues;
import org.eclipse.jst.jsp.core.internal.regions.DOMJSPRegionContexts;
import org.eclipse.swt.graphics.Image;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * A completion strategy for function completions like:
 * 
 * v a r .
 *        ^ 
 *
 * @author cbateman
 *
 */
public class FunctionCompletionStrategy extends ContentAssistStrategy 
{
    /**
     * @param value
     * @param proposalStart 
     */
    public FunctionCompletionStrategy(String value, String proposalStart) 
    {
        super(ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION, value, proposalStart);
    }

    public List getProposals(IStructuredDocumentContext context) 
    {
        final List completionList = new ArrayList();
        
        String[]  ids = getValue().split("\\.");
        
        // if no suffixes, only one id
        if (ids.length < 1) 
        {
            ids = new String[] {getValue()};
        }

        final ISymbolContextResolver  symbolResolver = 
            StructuredDocumentSymbolResolverFactory.getInstance().
                getSymbolContextResolver(context);
        
        ISymbol symbol = null;
        
        if (symbolResolver != null)
        {
            symbol = symbolResolver.getVariable(ids[0]);
        }
        
        if (symbol instanceof IInstanceSymbol
                && ((IInstanceSymbol)symbol).isTypeResolved())
        {
            int  curSuffixIdx = 1;
            
            while 
                (curSuffixIdx < ids.length
                        && symbol != null) 
            {
                
                final ISymbol[]  properties = 
                    symbolResolver.getProperties(symbol);
                    
                // set symbol to null because hasn't been found yet
                symbol = null;
                
                SEARCH_SYMBOL_NAME:for
                    (int i = 0; i < properties.length; i++)
                {
                    final ISymbol element = properties[i];
                    
                    if (ids[curSuffixIdx].equals(element.getName()))
                    {
                        symbol = element;
                        break SEARCH_SYMBOL_NAME;
                    }
                }
                curSuffixIdx++;
            }
            
            // if we get a completion symbol, get it's proposals
            if (symbol instanceof IObjectSymbol)
            {
                final List expectedMethodBindings = new ArrayList();
                ISymbol[] suffixes = getSymbols((IObjectSymbol) symbol, 
                                                 context, 
                                                 symbolResolver,
                                                 expectedMethodBindings);

                final ComposedAdapterFactory factory =
                    new ComposedAdapterFactory(
                           ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
                final IProposalCreationFactory  creationInfo =
                    new MyProposalFactory(context, getProposalStart().length(), 
                                            expectedMethodBindings);
                
                for (int i = 0; i < suffixes.length; i++)
                {
                    final ISymbol propSymbol = suffixes[i];
                    final Object  provider =  
                      factory.adapt(propSymbol, IContentProposalProvider.class);
                        
                    if (provider instanceof IContentProposalProvider)
                    {
                        final ICompletionProposal[] proposal  = 
                            ((IContentProposalProvider) provider).
                                getProposals(propSymbol, creationInfo);
                        if (proposal != null)
                        {
                        	addProposalsMatchingProposalStart(completionList,
									proposal);
                        }
                    }
                }
            }
        }

        return completionList;
    }

	private ISymbol[] getSymbols(IObjectSymbol symbol, 
                                 IStructuredDocumentContext context, 
                                 ISymbolContextResolver  symbolResolver,
                                 List   expectedMethodBindings)
    {
        List symbols = new ArrayList();

        if (isMethodBindingExpected(context, expectedMethodBindings))
        {
            symbols.addAll(Arrays.asList(
                          symbolResolver.getMethods(symbol)));
        }

        symbols.addAll(Arrays.asList(symbolResolver.getProperties(symbol)));

        return (ISymbol[]) symbols.toArray(ISymbol.EMPTY_SYMBOL_ARRAY);
    }
    
    private boolean isMethodBindingExpected(IStructuredDocumentContext context,
                                            List            expectedBindings)
    {
        boolean  isMBExpected = false;  // assume false until we find it true
        
        final IDOMContextResolver  domResolver = 
            IStructuredDocumentContextResolverFactory.INSTANCE.
                getDOMContextResolver(context);
        
        final Node curNode = domResolver.getNode();
        
        if (curNode instanceof Attr)
        {
            final Attr attr = (Attr) curNode;
            final Element element = attr.getOwnerElement();

            final ITaglibContextResolver taglibResolver =
                IStructuredDocumentContextResolverFactory.INSTANCE.
                    getTaglibContextResolver(context);

            final String uri = taglibResolver.getTagURIForNodeName(element);
            
            final List elVals = 
                MetaDataEnabledProcessingFactory.getInstance()
                    .getAttributeValueRuntimeTypeFeatureProcessors
                        (IValidELValues.class, context, uri, 
                                element.getLocalName(), attr.getLocalName());
            
            for (final Iterator it = elVals.iterator(); it.hasNext();)
            {
                final IValidELValues validValues = (IValidELValues) it.next();
                
                try
                {
                    CompositeType type = validValues.getExpectedRuntimeType();
                    if (type != null
                            && type.getAssignmentTypeMask()
                                    == IAssignable.ASSIGNMENT_TYPE_NONE)
                    {
                        isMBExpected = true;
                        expectedBindings.addAll(
                                Arrays.asList(
                                        validValues.
                                            getExpectedRuntimeType().
                                            getSignatures()));
                    }
                }
                catch (ELIsNotValidException e)
                {
                    // do nothing
                }
            }
        }

        // default condition is no method binding
        return isMBExpected;
    }
    
    private static class MyProposalFactory extends ProposalCreationFactoryAdapter
    {
        private final static int   DEFAULT_RELEVANCE = 1;
        
        private final static int   HIGH_RELEVANCE = 2;
        private final static int   NORMAL_RELEVANCE = 1;
        private final static int   LOW_RELEVANCE = 0;
        
        private final List                         _expectedMethodBindings;
        private final IStructuredDocumentContext   _context;
        
        /**
         * @param context 
         * @param replacementLength
         * @param expectedMethodBindings 
         */
        public MyProposalFactory(IStructuredDocumentContext context, int replacementLength,
                                    List expectedMethodBindings) {
        	/*TODO I changed the meaning of "replacementLength" from "number of chars AFTER cursor to be
        	 * replaced" to "number of chars BEFORE cursor to be replaced. Since "replacementLength"
        	 * has always been 0 (constructor is only called by FunctionCompletionStrategy.getProposals()),
        	 * this should not change anything, but I don't know if there have been different plans
        	 * for "replacementLength".
        	 * TODO Maybe this change should be done in the super class instead?
        	 */
            super(context.getDocumentPosition() - replacementLength, replacementLength);
            _context = context;
            _expectedMethodBindings = expectedMethodBindings;
        }

        public ICompletionProposal createProposal(String replacementText, 
                                                  String displayText, 
                                                  String additionalText, 
                                                  Image displayImage,
                                                  Object target) 
        {
            int replacementOffset = _replacementOffset;
            int replacementLength = _replacementLength;
            
            // TODO: I regard this as a bit of hack, but until we write our 
            // proposal implementation, it's basically the only way I can
            // see to do this
            // if it's an array, we must check if we need to replace a
            // preceding '.'
            if (replacementText.startsWith("["))
            {
                ITextRegionContextResolver textResolver = 
                    IStructuredDocumentContextResolverFactory.INSTANCE.getTextRegionResolver(_context);
                
                if (textResolver.getRegionType().equals(DOMJSPRegionContexts.JSP_VBL_CLOSE))
                {
                    textResolver = 
                        IStructuredDocumentContextResolverFactory.
                            INSTANCE.getTextRegionResolver(textResolver.getPreviousContext());
                }
                
                String regionText = textResolver.getRegionText();
                int regionStart = textResolver.getStartOffset();
                
                if (DOMJSPRegionContexts.JSP_VBL_CONTENT.equals(textResolver.getRegionType())
                        && regionText != null
                        && regionStart != -1
                        && regionStart < _context.getDocumentPosition())
                {
                    int  relativeOffset = _context.getDocumentPosition() - regionStart - 1;
                    
                    if (regionText.charAt(relativeOffset) == '.')
                    {
                        // we must replace a length of 1 (the dot)
                        // at an offset on prior
                        replacementOffset--;
                        replacementLength = 1;
                    }
                }
            }
            
            return createDefaultProposal(replacementText, 
                                         replacementOffset, 
                                         replacementLength, 
                                         replacementText.length(), 
                                         displayImage, 
                                         displayText, 
                                         null, 
                                         additionalText, 
                                         getRelevance(target, DEFAULT_RELEVANCE));
        }

        private int getRelevance(final Object target, final int defaultRelevance)
        {
            // if method bindings are expected, then list exact signature 
            // matches top most.  Still list non-matching methods, but put 
            // them at the bottom
            if (_expectedMethodBindings.size() > 0)
            {
                if (target instanceof IMethodSymbol)
                {
                    final IMethodSymbol methodSymbol = (IMethodSymbol) target;
                    
                    for (final Iterator it = _expectedMethodBindings.iterator();
                            it.hasNext();)
                    {
                        final String methodType = (String) it.next();
                        
                        // we have a match, so push to the top
                        if (methodType.equals(methodSymbol.getSignature()))
                        {
                            return HIGH_RELEVANCE;
                        }
                    }
                    
                    // if we get out of the loop, then this method doesn't
                    // match the expected signature
                    return LOW_RELEVANCE;
                }

                // non-method targets have normal relevance when mb expected
                return NORMAL_RELEVANCE;
            }
 
            // otherwise, simply return the default for all
            return defaultRelevance;
        }
    }
}
