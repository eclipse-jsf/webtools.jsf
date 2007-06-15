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

package org.eclipse.jst.jsf.context.symbol.provider;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider.IProposalCreationFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.ui.internal.contentassist.CustomCompletionProposal;

/**
 * Default implementation of the proposal creation factory
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public class ProposalCreationFactoryAdapter implements IProposalCreationFactory 
{
    /**
     * The absolute document offset where the replacement is to occur
     */
    protected final int       _replacementOffset;
    /**
     * The number of characters to replace starting from _replaceOffset with
     * the proposal.  0 indicates insertion with no replacement
     */
    protected final int       _replacementLength;
    
    /**
     * @param replacementOffset -- the absolute document offset to do the replacement
     * @param replacementLength  -- the number of characters to replace or 0
     * for insert without any replacement
     */
    public ProposalCreationFactoryAdapter(final int replacementOffset,
                                          final int replacementLength)
    {
        _replacementOffset = replacementOffset;
        _replacementLength = replacementLength;
    }

    public ICompletionProposal createProposal(String replacementText, 
                String displayText, String additionalText, Image displayImage,
                Object targetObject) 
                
    {
        return createDefaultProposal(replacementText, 
                _replacementOffset, 
                _replacementLength, 
                replacementText.length(), 
                displayImage, 
                displayText, 
                null, 
                additionalText,
                1);
    }
    
    /**
     * Simple factory method for creating a default proposal
     * 
     * @param replacementText
     * @param replacementOffset
     * @param replacementLength
     * @param cursorPosition
     * @param displayImage
     * @param displayText
     * @param contextInfo
     * @param additionalText
     * @param relevance
     * @return a default configuration of the completion proposal based on 
     * the CustomCompletionProposal
     */
    protected static ICompletionProposal 
                    createDefaultProposal(final String replacementText,
                                          final int replacementOffset,
                                          final int replacementLength,
                                          final int cursorPosition,
                                          final Image displayImage,
                                          final String displayText,
                                          final IContextInformation contextInfo,
                                          final String additionalText,
                                          final int relevance)
    {
        return new CustomCompletionProposal(replacementText, 
                replacementOffset, 
                replacementLength, 
                cursorPosition, 
                displayImage, 
                displayText, 
                contextInfo, 
                additionalText,
                relevance);
    }
}
