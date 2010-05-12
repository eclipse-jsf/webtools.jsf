package org.eclipse.jst.jsf.core.tests.resource;

import java.util.EventObject;

import org.eclipse.jst.jsf.common.internal.resource.EventResult;
import org.eclipse.jst.jsf.common.internal.resource.ILifecycleListener;

abstract class AbstractTestListener<EVENTTYPE extends EventObject> implements ILifecycleListener<EVENTTYPE>
{

    public abstract EventResult acceptEvent(final EVENTTYPE event);
}
