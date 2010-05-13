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
package org.eclipse.jst.jsf.designtime.internal.view;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;

/**
 * The interface for all design view root objects.
 * 
 */
public abstract class DTUIViewRoot extends ComponentInfo
{
    private StalenessAdvisor  _stalenessAdvisor;

    private String            _viewId;
    private VersionStamp      _versionStamp;

    /**
     * 
     */
    private static final long serialVersionUID = -6375907116774572269L;

    /**
     * @param id
     * @param parent
     * @param componentTypeInfo
     */
    protected DTUIViewRoot(final String id, final ComponentInfo parent,
            final ComponentTypeInfo componentTypeInfo)
    {
        super(id, parent, componentTypeInfo, true);
    }

    /**
     * @return the view id
     */
    public final String getViewId()
    {
        return _viewId;
    }

    /**
     * @param viewId
     * 
     */
    final void setViewId(final String viewId)
    {
        _viewId = viewId;
    }

    /**
     * <p>
     * Clients may use the version stamp to determine if their instance of a
     * view root is out of date. For example, if a client has cached an instance
     * of a view root and wants to quickly checked if there is a newer version,
     * it may request a new copy and compare version stamps
     * </p>
     * 
     * <p>
     * The version stamp must be calculated so that given two instance of a view
     * root for the same view id, v1 and v2:
     * </p>
     * 
     * <p>
     * if v1.getVersionStamp().equals(v2.getVersionStamp()) then v1 and v2 are
     * the same even if v1 != v2. Note that this uniqueness must hold true
     * across multiple sessions of the IDE, since v1 or v2 may have been
     * restored from permanent storage. For this reason, running counts should
     * are discouraged unless they too can be reliably stored. Better is
     * something that relies on large random numbers combined with a time stamp
     * or workspace modification stamp
     * </p>
     * 
     * @return the unique version stamp of this view root. This may be used as
     *         quick check as to whether to view roots are not equal.
     */
    public final VersionStamp getVersionStamp()
    {
        return delegateVersionStamp();
    }

    VersionStamp delegateVersionStamp()
    {
        return _versionStamp;
    }

    final void setVersionStamp(VersionStamp versionStamp)
    {
        _versionStamp = versionStamp;
    }
    
    
    /**
     * @return a map of ISymbols representing the currently available
     * view scope variables.  Never null, empty if no symbols
     * Map is unmodifiable (throws exception on mutation operations)
     */
    public final Map<String, ISymbol> getViewMap() {
    	return Collections.unmodifiableMap(doGetMapForScope(ISymbolConstants.SYMBOL_SCOPE_VIEW));
    }
    
    /**
     * @param scopeMask
     * @return Map of symbols
     */
    protected abstract Map<String, ISymbol> doGetMapForScope(final int scopeMask);

    /**
     * @return true if the view root is out of sync with its view source.
     */
    public final boolean isStale()
    {
        return _stalenessAdvisor.isStale();
    }

    final void setStalenessAdvisor(final StalenessAdvisor stalenessAdvisor)
    {
        _stalenessAdvisor = stalenessAdvisor;
    }

    /**
     * Implementations must ensure that listeners are only added if they are 
     * not already present.
     * 
     * @param listener
     */
    public final void addListener(final StalenessListener listener)
    {
        _stalenessAdvisor.addListener(listener);
    }

    /**
     * @param listener
     */
    public final void removeListener(final StalenessListener listener)
    {
        _stalenessAdvisor.removeListener(listener);
    }

    /**
     * @return an adapter that can be used to request additional services from
     *         the view root instance. This can be used to get services that are
     *         not specific to all view roots such as mappings between view
     *         definition artifacts and their view objects. Value may be null if
     *         a view root chooses not to expose any services.
     */
    public abstract IAdaptable getServices();

    /**
     * An abstract versioning stamp that can be used to uniquely identify a
     * version of a DTUIViewRoot.
     * 
     * @author cbateman
     * 
     */
    public abstract static class VersionStamp implements Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = -7869336223178326584L;

        /**
         * Called by equals to determine absolute equality.
         * 
         * @param other
         * @return true if and only if other represents the exact same version
         *         as this. Hashcode must be reimplemented in a way to guarantee
         *         that the equals() contract is respected.
         */
        protected abstract boolean isEqual(final VersionStamp other);

        /**
         * Must reimplement hashCode to match isEqual
         */
        public abstract int hashCode();

        @Override
        public final boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }

            // NOTE: instanceof covers obj == null check.
            if (obj instanceof VersionStamp)
            {
                return isEqual((VersionStamp) obj);
            }
            return false;
        }
    }

    /**
     * An advisor which allows a view root to bridge to its source to see if its
     * out-of-sync, while keeping it decoupled from what specifically its source
     * is.
     * 
     * @author cbateman
     * 
     */
    public static abstract class StalenessAdvisor implements Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = 3449240686744169430L;

        /**
         * Implementations must ensure that listeners are only added if they are 
         * not already present.
         * @param listener
         */
        public abstract void addListener(StalenessListener listener);

        /**
         * @param listener
         */
        public abstract void removeListener(StalenessListener listener);

        /**
         * NOTE: it is very dangerous to have an advisor that always returns
         * stale == true since many clients will rely on the staleness flag to
         * determine if they should try to update the model.
         * 
         * @return true if we are out of sync with the source that this tree was
         *         constructed from.
         */
        public abstract boolean isStale();

        /**
         * @return a flag similar to IResource.isAccessible that indicates if
         *         the underlying view definition is no longer accessible either
         *         due it being deleted or it's source file being unaccessible
         *         due to project closure or other issues.
         */
        public abstract boolean isAccessible();
    }

    /**
     * A listener that is informed of model staleness events
     * 
     */
    public static abstract class StalenessListener
    {
        /**
         * @param event
         */
        protected abstract void stalenessChanged(StalenessEvent event);
    }

    /**
     * An event that is fied when a model's staleness changes.
     * 
     */
    public final static class StalenessEvent
    {
        private final ChangeType _type;

        /**
         * @param type
         */
        protected StalenessEvent(final ChangeType type)
        {
            _type = type;
        }

        /**
         * @return true if the event source is now stale.
         */
        public final ChangeType getChangeType()
        {
            return _type;
        }

        /**
         * The type of staleness change
         * 
         */
        /**
         * @author cbateman
         *
         */
        public enum ChangeType
        {
            /**
             * Indicates that the view definition has been changed in a way that
             * will make existing DTUIViewRoot's stale.
             */
            VIEW_DEFN_CHANGED,

            /**
             * Inidicates that the view definition has been deleted.
             */
            VIEW_DEFN_DELETED,

            /**
             * Indicates that the view definition's enclosing project will be
             * closed, causing the definition to be made unavailable for an
             * indeterminate amount of time.
             */
            VIEW_DEFN_PROJECT_CLOSED,

            /**
             * Indicates that a view root has been invalid due to a project 
             * clean.  View root should generally be forcibly updated on
             * a project clean even if other staleness preconditions haven't
             * been met.
             */
            PROJECT_CLEANED
        }

        /**
         * 
         */
        private static final long serialVersionUID = 6765346004772568514L;
    }
}
