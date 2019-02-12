/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
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
package org.eclipse.jst.jsf.common.internal.pde;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;

/**
 * @author cbateman
 * @param <T>
 * 
 */
public abstract class AbstractSimpleClassExtensionRegistryReader<T> extends
        AbstractRegistryReader<T>
{
    /**
     * Indicates no sorting of extension.
     */
    protected final static Comparator NO_SORT = null;
    
    private final String _attributeName;
    private final String _configElementName;
    private final Comparator<SortableExecutableExtension<T>> _comparator;

	private boolean _logWarnings = false;

    /**
     * @param extPtNamespace
     * @param extPtId
     * @param configElementName
     * @param attributeName
     * @param listComparator
     *            May be null if no sorting of the extensions list is required.
     */
    protected AbstractSimpleClassExtensionRegistryReader(
            final String extPtNamespace, final String extPtId,
            final String configElementName, final String attributeName,
            final Comparator<SortableExecutableExtension<T>> listComparator)
    {
        super(extPtNamespace, extPtId);
        _configElementName = configElementName;
        _attributeName = attributeName;
        _comparator = listComparator;
    }

    @Override
    protected final void initialize()
    {
        final List<SortableExecutableExtension<T>> result = new ArrayList<SortableExecutableExtension<T>>();
        final IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
                .getExtensionPoint(getExtPtNamespace(), getExtPtId());
        if (extensionPoint == null)
        {
            return;
        }
        IExtension[] extensions = extensionPoint.getExtensions();
        for (int i = 0; i < extensions.length; i++)
        {
            IExtension ext = extensions[i];
            IConfigurationElement[] tagConverter = ext
                    .getConfigurationElements();

            for (int j = 0; j < tagConverter.length; j++)
            {
                final IConfigurationElement element = tagConverter[j];

                if (element.getName().equals(_configElementName))
                {
                    element.getAttribute(_attributeName);
                    try
                    {
                        final T obj = (T) element
                                .createExecutableExtension(_attributeName);
                        result.add(new SortableExecutableExtension<T>(
                                _comparator, element.getContributor().getName(),
                                obj));
                    } catch (ClassCastException ce)
                    {
                        handleLoadFailure(new CoreException(new Status(
                                IStatus.ERROR, JSFCommonPlugin.PLUGIN_ID,
                                "Extension class is not the expected type", ce))); //$NON-NLS-1$
                    } catch (CoreException e)
                    {
                        handleLoadFailure(e);
                    }
                }
            }
        }

        if (result.size() > 0)
        {
            if (_comparator != NO_SORT)
            {
                Collections.sort(result, _comparator);
            }
        } else if (_logWarnings)
        {
            JSFCommonPlugin.log(IStatus.WARNING, String.format(
                    "No extensions found for: %s.%s", //$NON-NLS-1$
                    getExtPtNamespace(), getExtPtId()));
        }
        final List<T> finalExtensions = new ArrayList<T>();
        for (final SortableExecutableExtension<T> sortable : result)
        {
            finalExtensions.add(sortable.getExtensionObject());
        }
        internalSetExtensions(finalExtensions);
    }

    /**
     * Called by initialize when an error occurs trying to load a class from an
     * extension point. Sub-class should implement to handle the failure,
     * typically to log it using their bundle id.
     * 
     * @param ce
     */
    protected abstract void handleLoadFailure(final CoreException ce);

    /**
     * @param doLogWarnings
     */
    protected void logWarnings(final boolean doLogWarnings) {
    	_logWarnings = doLogWarnings;
    }
    
    /**
     * A comparator that sorts canonically by extension namespace and id, but
     * can make exceptions for certain prefices.
     * 
     * @param <T>
     * 
     */
    protected abstract static class CanonicalComparatorWithPrefixExceptions<T>
            implements Comparator<SortableExecutableExtension<T>>
    {

        public int compare(SortableExecutableExtension<T> o1,
                SortableExecutableExtension<T> o2)
        {
            int result = prefixSort(o1, o2);

            // if the prefix sort doesn't distinguish a sort order, then
            // compare it canonically
            if (result == 0)
            {
                result = o1.getContributorId().compareTo(o2.getContributorId());
            }

            return result;
        }

        /**
         * @param o1
         * @param o2
         * @return -1 if o1 should sort before o2 based on prefix. 1 if o2
         *         should sort before o1 or 0 if there is sort preference based
         *         on prefix.
         */
        protected abstract int prefixSort(SortableExecutableExtension<T> o1,
                SortableExecutableExtension<T> o2);
    }

    /**
     * Ensures that contributions from "org.eclipse.jst" plugins are sorted last
     *
     * @param <T>
     */
    public static class CompareOrgEclipseJstContributorsLastComparator<T> extends CanonicalComparatorWithPrefixExceptions<T> {
        @Override
        protected int prefixSort(
                SortableExecutableExtension<T> o1,
                SortableExecutableExtension<T> o2)
        {
	            // if o1 is contributed by open source, sort it
	            // after 
	            if (o1.getContributorId().startsWith("org.eclipse.jst")) //$NON-NLS-1$
	            {
	                return 1;
	            }
	            // if o2 is contributed by open source, sort o1 first
	            else if (o2.getContributorId().startsWith("org.eclipse.jst")) //$NON-NLS-1$
	            {
	                return -1;
	            }
	            // otherwise, we don't care
	            return 0;
        }

    }
    /**
     * Used to sort extensions before locking down the list.
     * 
     * @param <T>
     */
    protected final static class SortableExecutableExtension<T> implements
            Comparable<SortableExecutableExtension>
    {
        private final Comparator _comparator;
        private final String _contributorId;
        private final T _extensionObject;

        private SortableExecutableExtension(final Comparator comparator,
                final String contributorId, final T extensionObject)
        {
            if (comparator == null)
            {
                _comparator = new Comparator<T>()
                {
                    public int compare(T o1, T o2)
                    {
                        // always return equal.
                        return 0;
                    }
                };
            } else
            {
                _comparator = comparator;
            }
            _contributorId = contributorId;
            _extensionObject = extensionObject;
        }

        public int compareTo(SortableExecutableExtension o)
        {
            return _comparator.compare(this, o);
        }

        @Override
        public boolean equals(Object obj)
        {
            return _comparator.compare(this, obj) == 0;
        }

        @Override
        public int hashCode()
        {
            return _contributorId.hashCode() ^ _extensionObject.hashCode();
        }

        /**
         * @return the id of the bundle that contributed this extension
         */
        public String getContributorId()
        {
            return _contributorId;
        }

        /**
         * @return the extension object
         */
        public T getExtensionObject()
        {
            return _extensionObject;
        }
    }
}
