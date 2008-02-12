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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistParser;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistStrategy;
import org.eclipse.jst.jsf.designtime.DTAppManagerUtil;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewDefnAdapter;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler.ViewHandlerException;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewDefnAdapter.DTELExpression;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
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
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer,
     *      int)
     */
    public ICompletionProposal[] computeCompletionProposals(
            final ITextViewer viewer, final int documentPosition)
    {
        final List<ICompletionProposal> proposals =
                new ArrayList<ICompletionProposal>();
        final IStructuredDocumentContext context =
                IStructuredDocumentContextFactory.INSTANCE.getContext(viewer,
                        documentPosition);
        if (context != null)
        {
            final XMLViewDefnAdapter viewAdapter =
                    DTAppManagerUtil.getXMLViewDefnAdapter(context);

            if (viewAdapter != null)
            {
                try
                {
                    final DTELExpression elExpression =
                            viewAdapter.getELExpression(context);

                    if (elExpression != null)
                    {
                        final ContentAssistStrategy strategy =
                                ContentAssistParser.getPrefix(documentPosition
                                        - elExpression.getDocumentContext()
                                                .getDocumentPosition() + 1,
                                        elExpression.getText());

                        if (strategy != null)
                        {
                            proposals.addAll(strategy.getProposals(context));
                        }
                        Collections.sort(proposals, new ProposalComparator());
                    }
                }
                catch (ViewHandlerException e)
                {
                    JSFUiPlugin.log(IStatus.ERROR, "During el resolution", e);
                }
            }
        }

        return proposals.toArray(new ICompletionProposal[0]);
    }

    public IContextInformation[] computeContextInformation(
            final ITextViewer viewer, final int offset)
    {
        // no context info
        return null;
    }

    public char[] getCompletionProposalAutoActivationCharacters()
    {
        // auto activate when user hits a '.'
        return new char[]
        { '.' };
    }

    public char[] getContextInformationAutoActivationCharacters()
    {
        // no auto-activation for context info
        return null;
    }

    public String getErrorMessage()
    {
        // don't flag errors
        return null;
    }

    public IContextInformationValidator getContextInformationValidator()
    {
        // don't validate context information
        return null;
    }
}
