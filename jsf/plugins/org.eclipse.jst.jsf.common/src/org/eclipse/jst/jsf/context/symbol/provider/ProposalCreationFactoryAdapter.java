/*******************************************************************************
 * Copyright (c) 2006, 2021 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    Andreas Rusch/Axon Ivy - Lazy compute additional proposal info (javadoc)
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.symbol.provider;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider.IProposalCreationFactory;
import org.eclipse.swt.graphics.Image;

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
                String displayText, Image displayImage,
                Object targetObject) 
                
    {
        return createDefaultProposal(replacementText, 
                _replacementOffset, 
                _replacementLength, 
                replacementText.length(), 
                displayImage, 
                displayText, 
                null, 
                1,
                targetObject);
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
     * @param relevance
     * @param target The original source object.
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
                                          final int relevance,
                                          final Object target)
    {
        return new LazyAdditionalTextCompletionProposal(replacementText, 
                replacementOffset, 
                replacementLength, 
                cursorPosition, 
                displayImage, 
                displayText, 
                contextInfo, 
                relevance,
                target);
    }
}
