/**
 * 
 */
package org.eclipse.jst.jsf.core.tests.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

class MockIReporter implements IReporter
{
	List<IMessage>   messages = new ArrayList<IMessage>();
	
	public void addMessage(IValidator origin, IMessage message) 
	{
		messages.add(message);
	}

	public void displaySubtask(IValidator validator, IMessage message) {
		throw new UnsupportedOperationException("This reporter is for specific test purposes only");
	}

	@SuppressWarnings("unchecked")
    public List getMessages() {
		return messages;
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