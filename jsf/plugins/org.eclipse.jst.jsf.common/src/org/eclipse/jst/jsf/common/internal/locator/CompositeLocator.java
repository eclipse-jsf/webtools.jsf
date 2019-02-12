/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.internal.locator;

import java.util.Iterator;

import org.eclipse.jst.jsf.common.internal.policy.IIteratorPolicy;
import org.eclipse.jst.jsf.common.internal.strategy.IteratorPolicyBasedStrategyComposite;

/**
 * @author cbateman
 *
 * @param <LOCATORTYPE>
 * @param <COMPOSITETYPE>
 * @param <CONTEXTTYPE>
 * @param <IDTYPE>
 */
public abstract class CompositeLocator<LOCATORTYPE, COMPOSITETYPE, CONTEXTTYPE, IDTYPE> extends
IteratorPolicyBasedStrategyComposite<CONTEXTTYPE, LOCATORTYPE, COMPOSITETYPE, IDTYPE, ILocator<LOCATORTYPE, CONTEXTTYPE, IDTYPE>>
implements ILocator<COMPOSITETYPE, CONTEXTTYPE, IDTYPE>, ILocatorChangeListener
{
    private DefaultComposingLocatorDelegate<LOCATORTYPE, COMPOSITETYPE, CONTEXTTYPE, IDTYPE> _delegate;
    private final COMPOSITETYPE _noResultValue;

    /**
     * @param id
     * @param displayName
     * @param policy
     * @param noResultValue
     * @param compositionStrategy
     */
    public CompositeLocator(
            final IDTYPE id,
            final String displayName,
            final IIteratorPolicy<IDTYPE> policy,
            final COMPOSITETYPE noResultValue,
            final DefaultCompositionStrategy<CONTEXTTYPE, LOCATORTYPE, COMPOSITETYPE, ILocator<LOCATORTYPE, CONTEXTTYPE, IDTYPE>> compositionStrategy)
    {
        super(policy, compositionStrategy);
        _delegate = new DefaultComposingLocatorDelegate<LOCATORTYPE, COMPOSITETYPE, CONTEXTTYPE, IDTYPE>(id, displayName)
        {
            @Override
            protected COMPOSITETYPE doLocate(final CONTEXTTYPE context)
            {
                return CompositeLocator.this.doLocate(context);
            }
        };
        _noResultValue = noResultValue;
    }

    /**
     * @param context
     * @return the located value.
     */
    protected abstract COMPOSITETYPE doLocate(CONTEXTTYPE context);

    public final IDTYPE getId()
    {
        return _delegate.getId();
    }

    public String getDisplayName()
    {
        return _delegate.getDisplayName();
    }

    public void start(final CONTEXTTYPE initialContext)
    {
        if (canStart())
        {
            final Iterator<ILocator<LOCATORTYPE, CONTEXTTYPE, IDTYPE>> iterator = getIterator();
            while (iterator.hasNext())
            {

                final ILocator<LOCATORTYPE, CONTEXTTYPE, IDTYPE> next = iterator.next();
                next.addListener(this);
                next.start(initialContext);
            }
            _delegate.start(initialContext);
        }
        throw new IllegalStateException("canStart must be called before calling start"); //$NON-NLS-1$
    }

    public boolean canStart()
    {
        boolean canStart = true;
        final Iterator<ILocator<LOCATORTYPE, CONTEXTTYPE, IDTYPE>> iterator = getIterator();
        while (canStart && iterator.hasNext())
        {
            canStart &= iterator.next().canStart();
        }

        return canStart && _delegate.canStart();
    }

    public boolean isStarted()
    {
        return _delegate.isStarted();
    }

    public void stop()
    {
        if (!isStarted())
        {
            final Iterator<ILocator<LOCATORTYPE, CONTEXTTYPE, IDTYPE>> iterator = getIterator();
            while (iterator.hasNext())
            {
                final ILocator<LOCATORTYPE, CONTEXTTYPE, IDTYPE> next = iterator.next();
                next.removeListener(this);
                next.stop();
            }

            _delegate.stop();
        }
    }

    public COMPOSITETYPE locate(final CONTEXTTYPE context)
    {
        return _delegate.locate(context);
    }

    @Override
    public COMPOSITETYPE getNoResult()
    {
        return _noResultValue;
    }


    public void changed(final LocatorChangeEvent event)
    {
        _delegate.fireChangeEvent(event);
    }


    public void addListener(final ILocatorChangeListener listener)
    {
        _delegate.addListener(listener);
    }


    public void removeListener(final ILocatorChangeListener listener)
    {
        _delegate.removeListener(listener);
    }

    /**
     * Re-fire events from composed strategies.
     * 
     * @param event
     */
    protected void fireChangeEvent(final LocatorChangeEvent event)
    {
        _delegate.fireChangeEvent(event);
    }

}
