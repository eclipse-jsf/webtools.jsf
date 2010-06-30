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
package org.eclipse.jst.jsf.core.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jst.jsf.common.internal.policy.CanonicallyOrderedIteratorPolicy;
import org.eclipse.jst.jsf.common.internal.policy.IIteratorPolicy;
import org.eclipse.jst.jsf.common.internal.policy.IdentifierOrderedIteratorPolicy;
import org.eclipse.jst.jsf.common.internal.strategy.AbstractIdentifiableStrategy;
import org.eclipse.jst.jsf.common.internal.strategy.IIdentifiableStrategy;
import org.eclipse.jst.jsf.common.internal.strategy.IteratorPolicyBasedStrategyComposite;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory.TagRegistryFactoryException;

/**
 * Employs a policy-based strategy to construct a TagRegistry for a particular
 * content-type in a particular project.
 * 
 * @author cbateman
 * 
 */
public final class CompositeTagRegistryFactory
{
    private static CompositeTagRegistryFactory INSTANCE;

    private static ITagRegistryFactoryProvider   TEST_PROVIDER;
    /**
     * For JUNIT TEST ONLY!!!!!
     * @param factoryProvider
     */
    public synchronized void setTestInjectedProvider(final ITagRegistryFactoryProvider factoryProvider)
    {
        TEST_PROVIDER = factoryProvider;
        // TODO: this is risky
//        _cachedExtensionsByType.clear();
    }

    /**
     * @return the single instance of the registry factory
     */
    public static synchronized CompositeTagRegistryFactory getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new CompositeTagRegistryFactory();
        }
        return INSTANCE;
    }

//    private final Map<TagRegistryIdentifier, Set<ITagRegistryFactoryInfo>> _cachedExtensionsByType =
//        new HashMap<TagRegistryIdentifier, Set<ITagRegistryFactoryInfo>>(4);

    private CompositeTagRegistryFactory()
    {
        // singleton: no external instantiation
    }

    
    /**
     * @param id
     * @return a tag registry for the id or null if there isn't one.
     */
    public final ITagRegistry getRegistry(final TagRegistryIdentifier id)
    {
        final Set<ITagRegistryFactoryInfo> handlers = getAllTagRegistryFactories();

        final Set<ITagRegistryFactoryInfo> matchingHandlers = findMatchingExtensions(
                id, handlers);

        if (matchingHandlers.size() > 0)
        {
            // optimize: if there is only one handler, no need for strategy
            if (matchingHandlers.size() == 1)
            {
                try
                {
                    return matchingHandlers.iterator().next().getTagRegistryFactory()
                            .createTagRegistry(id.getProject());
                }
                catch (TagRegistryFactoryException e)
                {
                    JSFCorePlugin.log(e, "While trying to acquire a registry"); //$NON-NLS-1$
                }
            }
            else
            {
                // for now, we will order by the canonical name of the extension
                // id in ascending order. Later, we may make this preference
                // based.
                final TagRegistrySelectionStrategy selectionStrategy = new TagRegistrySelectionStrategy(
                        new CanonicallyOrderedIteratorPolicy<String>());

                for (final Iterator<ITagRegistryFactoryInfo> it = matchingHandlers
                        .iterator(); it.hasNext();)
                {
                    selectionStrategy.addStrategy(it.next().getTagRegistryFactory());
                }
                return selectionStrategy.perform(id.getProject());
            }
        }

        return null;
    }

    /**
     * @return get all tag registry factories
     */
    public Set<ITagRegistryFactoryInfo> getAllTagRegistryFactories()
    {
        List<String>  selectionOrder = new ArrayList<String>();
        selectionOrder.add("testInjection"); //$NON-NLS-1$
        selectionOrder.add("extensionPointInjection"); //$NON-NLS-1$
        selectionOrder.add("platformDefault"); //$NON-NLS-1$

        IdentifierOrderedIteratorPolicy<String> policy = new IdentifierOrderedIteratorPolicy<String>(selectionOrder);
        // ignore iterator values that don't exist in the list of possible selections.
        policy.setExcludeNonExplicitValues(true);
        final TagRegistryFactoryProviderSelectionStrategy providerSelector
            = new TagRegistryFactoryProviderSelectionStrategy(policy);
        providerSelector.addStrategy(
          new AbstractIdentifiableStrategy<IProject, ITagRegistryFactoryProvider, String>("testInjection", "FIXME: not for display", null) //$NON-NLS-1$ //$NON-NLS-2$
        {
            @Override
            public ITagRegistryFactoryProvider perform(IProject input)
                    throws Exception
            {
                ITagRegistryFactoryProvider injectedProvider = null;
                synchronized(CompositeTagRegistryFactory.class)
                {
                     injectedProvider = TEST_PROVIDER;
                }
                if (injectedProvider != null)
                {
                    final ITagRegistryFactoryProvider useMe = injectedProvider;
                    return new AbstractTagRegistryFactoryProvider()
                    {
                        @Override
                        public Set<ITagRegistryFactoryInfo> getTagRegistryFactories()
                        {
                            return useMe.getTagRegistryFactories();
                        }
                    };
                }
                return null;
            }
            });
        providerSelector.addStrategy(
                new AbstractIdentifiableStrategy<IProject, ITagRegistryFactoryProvider, String>("platformDefault", "FIXME: not for display", null) //$NON-NLS-1$ //$NON-NLS-2$
              {
                  @Override
                  public ITagRegistryFactoryProvider perform(IProject input)
                          throws Exception
                  {
                      return new AbstractTagRegistryFactoryProvider()
                      {
                          @Override
                          public Set<ITagRegistryFactoryInfo> getTagRegistryFactories()
                          {
                              return TagLibraryRegistryLoader.getAllHandlers();
                          }
                      };
                  }
              });

        ITagRegistryFactoryProvider provider = providerSelector.perform(null);
        if (provider != null)
        {
            return provider.getTagRegistryFactories();
        }
        return Collections.emptySet();
    }

	private Set<ITagRegistryFactoryInfo> findMatchingExtensions(
			TagRegistryIdentifier id, Set<ITagRegistryFactoryInfo> handlers) 
	{
		Set<ITagRegistryFactoryInfo> matching = new HashSet<ITagRegistryFactoryInfo>(
				4);

		for (final ITagRegistryFactoryInfo handler : handlers) {
			if (handler.getContentTypes().contains(id.getContentType())
					&& handler.getTagRegistryFactory().projectIsValid(
							id.getProject())) {
				matching.add(handler);
			}
		}

		// // if there is nothing matching, just store the empty set and
		// // discard the extra memory
		// if (matching.size() > 0)
		// {
		// _cachedExtensionsByType.put(id, matching);
		// }
		// else
		// {
		// _cachedExtensionsByType.put(id, Collections.EMPTY_SET);
		// }
		return matching;
	}

    /**
     * Identifies a content type/project context in which to request a tag
     * registry.
     * 
     * @author cbateman
     * 
     */
    public final static class TagRegistryIdentifier
    {
        private final IProject     _project;
        private final IContentType _contentType;

        /**
         * @param project
         * @param contentType
         */
        public TagRegistryIdentifier(final IProject project,
                final IContentType contentType)
        {
            _project = project;
            _contentType = contentType;
        }

        /**
         * @return project
         */
        public IProject getProject()
        {
            return _project;
        }

        /**
         * @return content type
         */
        public IContentType getContentType()
        {
            return _contentType;
        }
        
        public boolean equals(final Object o) {
        	if (o instanceof TagRegistryIdentifier) {        	
	        	final TagRegistryIdentifier other = (TagRegistryIdentifier)o;
	        	final int otherProjectHash = other.getProject() != null ? other.getProject().hashCode() : 0;
	        	final int thisProjectHash = getProject() != null ? getProject().hashCode() : 0;
	        	if (otherProjectHash == thisProjectHash &&
	        			other.getContentType().equals(this.getContentType()))
	        		return true;
        	}
        	return false;
        }
        
        public int hashCode() {
        	return (getProject() != null ? getProject().hashCode() : 0) + 7*_contentType.hashCode();
        }

    }

    private static class TagRegistrySelectionStrategy
            extends
            IteratorPolicyBasedStrategyComposite<IProject, ITagRegistry, ITagRegistry, String, IIdentifiableStrategy<IProject, ITagRegistry, String>>
    {
        private static final ITagRegistry NO_RESULT = null;

        protected TagRegistrySelectionStrategy(IIteratorPolicy<String> policy)
        {
            super(policy);
        }

        @Override
        public ITagRegistry getNoResult()
        {
            return NO_RESULT;
        }
    }
    
    private static class TagRegistryFactoryProviderSelectionStrategy
            extends
            IteratorPolicyBasedStrategyComposite<IProject, ITagRegistryFactoryProvider, ITagRegistryFactoryProvider, String, IIdentifiableStrategy<IProject, ITagRegistryFactoryProvider, String>>
    {
        protected TagRegistryFactoryProviderSelectionStrategy(
                IIteratorPolicy<String> policy)
        {
            super(policy);
        }

        private static final ITagRegistryFactoryProvider NO_RESULT = null;

        @Override
        public ITagRegistryFactoryProvider getNoResult()
        {
            return NO_RESULT;
        }
    }
}
