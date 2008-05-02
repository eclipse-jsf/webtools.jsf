/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.itemcreation.internal;


/**
 * A registry reader that lazy initializes a list of uri-sensitive
 * extensions
 * 
 * @author cbateman
 *
 */
public final class TagCreatorFactoryRegistryReader 
{
    // TODO: move this logic into the ElementEditFacRegistryReader
//    private static Map<String, List<ITagCreatorFactory>> _handlers = null;
//
//    /**
//     * @param uri the uri to get a list of factories for
//     * @return all available handers for the ext-pt
//     */
//    public static synchronized List<ITagCreatorFactory> getAllHandlers(final String uri) 
//    {
//        if (_handlers == null) {
//            _handlers = readAllHandlers();
//        }
//        return _handlers.get(uri);
//    }
//
//    private static Map<String, List<ITagCreatorFactory>> readAllHandlers() 
//    {
//        final Map<String, List<ExtElement>>  map = 
//            new HashMap<String, List<ExtElement>>();
//
//        IExtensionPoint extensionPoint = Platform.getExtensionRegistry()
//                .getExtensionPoint(PDPlugin.getPluginId(),
//                        IJMTConstants.TAG_CREATOR_FACTORY_EXT_NAME);
//        IExtension[] extensions = extensionPoint.getExtensions();
//
//        for (int i = 0; i < extensions.length; i++) 
//        {
//            IExtension ext = extensions[i];
//            IConfigurationElement[] tagCreationHandlers = ext
//                    .getConfigurationElements();
//
//            for (int j = 0; j < tagCreationHandlers.length; j++) {
//                if (tagCreationHandlers[j].getName().equals(
//                        IJMTConstants.TAG_CREATOR_FACTORY_ELEMENT_NAME)) 
//                {
//                    try
//                    {
//                        final ExtElement extElement = 
//                            new ExtElement(tagCreationHandlers[j]);
//                        List<ExtElement> elements = 
//                            map.get(extElement.getUri());
//                        
//                        if (elements == null)
//                        {
//                            elements = new ArrayList<ExtElement>();
//                            map.put(extElement.getUri(), elements);
//                        }
//                        
//                        elements.add(extElement);
//                    }
//                    catch (CoreException ce)
//                    {
//                        PDPlugin.getLogger(ExtElement.class)
//                            .error("Error.ProblemLoadingExtensionPoint", ce);
//                    }
//                }
//            }
//        }
//        
//        final Map<String, List<ITagCreatorFactory>> handlers = 
//            new HashMap<String, List<ITagCreatorFactory>>();
//        
//        // now loop through the extensions, looking for collisions
//        // on the same uri
//        for (String uri : map.keySet())
//        {
//            List<ExtElement>  elements = map.get(uri);
//            
//            if (elements.size() > 1)
//            {
//                Collections.sort(elements);
//                
//                MultiStatus chainedStatus = new MultiStatus(PDPlugin.getPluginId(), 0, "Collision detected in ITagCreatorFactory extensions for uri: "+uri+". Factories will be queried in the following order with the first contender to provide a ITagCreator winning",
//                        new Throwable());
//                
//                for (ExtElement element : elements)
//                {
//                    IStatus status = 
//                        new Status(IStatus.WARNING, PDPlugin.getPluginId(), element.getClassName());
//                    chainedStatus.add(status);
//                }
//                
//                PDPlugin.log(chainedStatus);
//            }
//            
//            List<ITagCreatorFactory>  factories = 
//                new ArrayList<ITagCreatorFactory>();
//
//            for (ExtElement element : elements)
//            {
//                factories.add(element.getFactoryInstance());
//            }
//            
//            handlers.put(uri, factories);
//        }
//
//        return handlers;
//    }
//
//    private static class ExtElement implements Comparable<ExtElement>
//    {
//        private final IConfigurationElement  _element;
//        
//        private final ITagCreatorFactory  _factoryInstance;
//        
//        ExtElement(IConfigurationElement element) throws CoreException
//        {
//            _element = element;
//            
//            final String uri = getUri();
//            final String factory = getClassName();
//            
//            if (uri == null || factory == null)
//            {
//                throw new CoreException(new Status(IStatus.ERROR,PDPlugin.getPluginId(), "uri and factory must not be null: uri="+uri+", factory="+factory));
//            }
//            
//            Object obj = _element.createExecutableExtension("factory");
//
//            if (obj instanceof ITagCreatorFactory) 
//            {
//                _factoryInstance = (ITagCreatorFactory) obj;
//            }
//            else
//            {
//                throw new CoreException(new Status(IStatus.ERROR,PDPlugin.getPluginId(), "Factory must be instanceof ITagCreatorFactory"));
//            }
//        }
//
//        /**
//         * @return the uri
//         */
//        public String getUri() {
//            return _element.getAttribute("uri");
//        }
//
//        /**
//         * @return the class name
//         */
//        public String getClassName() {
//            return _element.getAttribute("factory");
//        }
//
//        /**
//         * @return the factory instance or null
//         */
//        public ITagCreatorFactory getFactoryInstance() 
//        {
//            return _factoryInstance;
//        }
//
//        /* 
//         * Two elements compare by the canonical comparision of their
//         * contributor's bundle name string
//         */
//        public int compareTo(ExtElement o)
//        {
//            return _element.getContributor().getName().compareTo
//                (o._element.getContributor().getName());
//        }
//
//        @Override
//        public boolean equals(Object obj) 
//        {
//            // ensure that x.equals(y) iff x.compareTo(y) == 0
//            if (obj instanceof ExtElement)
//            {
//                return compareTo((ExtElement) obj) == 0;
//            }
//            
//            return false;
//        }
//
//        @Override
//        public int hashCode() 
//        {
//            // ensure that x.equals(y) => x.hashCode == y.hashCode
//            return _element.getContributor().getName().hashCode();
//        }
//    }
}
