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
	List<IMessage>   		_messages = new ArrayList<IMessage>();
	Map<Integer, List<IMessage>>	_messagesByOffset = new TreeMap<Integer, List<IMessage>>(); 
	
	public void addMessage(IValidator origin, IMessage message) 
	{
		_messages.add(message);
		
		// index the message by offset
		getMessageListForOffset(message.getOffset()).add(message);
	}

	public void displaySubtask(IValidator validator, IMessage message) {
		// do nothing, might eventually want to log this
	}

	public List<IMessage>  getMessageListForOffset(int offset)
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

	public void removeAllMessages(IValidator origin) 
	{
		throw new UnsupportedOperationException("This reporter is for specific test purposes only");
	}

	public void removeAllMessages(IValidator origin, Object object) 
	{
		throw new UnsupportedOperationException("This reporter is for specific test purposes only");
	}

	public void removeMessageSubset(IValidator validator, Object obj,
			String groupName) 
	{
		throw new UnsupportedOperationException("This reporter is for specific test purposes only");
	}
	
}