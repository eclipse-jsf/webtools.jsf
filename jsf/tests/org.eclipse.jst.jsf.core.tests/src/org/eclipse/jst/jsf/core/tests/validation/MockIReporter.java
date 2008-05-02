/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.jst.jsf.core.tests.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

class MockIReporter implements IReporter
{
    private List<IMessage>                  _messages = new ArrayList<IMessage>();
    private Map<Integer, List<IMessage>>    _messagesByOffset = 
        new TreeMap<Integer, List<IMessage>>();

    public void addMessage(final IValidator origin, final IMessage message)
    {
        _messages.add(message);

        // index the message by offset
        getMessageListForOffset(message.getOffset()).add(message);
    }

    public void displaySubtask(final IValidator validator, final IMessage message) {
        // do nothing, might eventually want to log this
    }

    public List<IMessage>  getMessageListForOffset(final int offset)
    {
        List<IMessage>  messages = _messagesByOffset.get(offset);

        if (messages == null)
        {
            messages = new ArrayList<IMessage>();
            _messagesByOffset.put(offset, messages);
        }

        return messages;
    }

    @SuppressWarnings("unchecked")
    public List getMessages() {
        return _messages;
    }

    public boolean isCancelled() {
        // do nothing; unused.
        return false;
    }

    public void removeAllMessages(final IValidator origin)
    {
        throw new UnsupportedOperationException("This reporter is for specific test purposes only");
    }

    public void removeAllMessages(final IValidator origin, final Object object)
    {
        throw new UnsupportedOperationException("This reporter is for specific test purposes only");
    }

    public void removeMessageSubset(final IValidator validator, final Object obj,
            final String groupName)
    {
        throw new UnsupportedOperationException("This reporter is for specific test purposes only");
    }

}