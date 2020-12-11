/*******************************************************************************
 * Copyright (c) 2020, 2021 Axon Ivy AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Andreas Rusch/Axon Ivy - Lazy compute additional proposal info (javadoc) 
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.context.symbol.provider;

import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jst.jsf.context.symbol.IDescribedInDetail;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.sse.ui.internal.contentassist.CustomCompletionProposal;

/**
 * Proposal class that creates the additional text (JavaDoc) lazily, if not provided in constructor.
 * @since 8.0.11
 */
public class LazyAdditionalTextCompletionProposal extends CustomCompletionProposal
{
  final private Object symbol;

 /**
  * Constructor
  * @param replacementText
  * @param replacementOffset
  * @param replacementLength
  * @param cursorPosition
  * @param displayImage
  * @param displayText
  * @param contextInfo
  * @param relevance
  * @param target
  */
public LazyAdditionalTextCompletionProposal(String replacementText, int replacementOffset, int replacementLength,
          int cursorPosition, Image displayImage, String displayText, IContextInformation contextInfo, int relevance, Object target)
  {
    super(replacementText, replacementOffset, replacementLength,
            cursorPosition, displayImage, displayText, contextInfo,
            null, relevance);
      this.symbol = target;
  }
  
  @Override
  public String getAdditionalProposalInfo()
  {
    if (symbol instanceof IDescribedInDetail)
    {
      return ((IDescribedInDetail)symbol).getDetailedDescription();
    }
    return null;
  }

}
