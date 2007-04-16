/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.internal.jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.source.AbstractContextSymbolFactory;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.model.ModelLifecycleEvent;
import org.eclipse.wst.sse.core.internal.provisional.IModelLifecycleListener;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Processes a JSP model to determine information of interest about it such
 * as what tags are currently in use.  Listens to the model and updates it's 
 * information when the model changes.
 * 
 * @author cbateman
 *
 */
public class JSPModelProcessor
{
    private final static String SESSION_PROPERTY_QUALIFIER = "net.eclipse.jst.jsf.jsp";
    private final static String SESSION_PROPERTY_NAME_JSPMODELPROCESSOR = "JSPModelProcessor";
    private final static QualifiedName SESSION_PROPERTY_JSPMODELPROCESSOR_KEY = 
        new QualifiedName(SESSION_PROPERTY_QUALIFIER,SESSION_PROPERTY_NAME_JSPMODELPROCESSOR);
    /**
     * @param file The file to get the model processor for  
     * @return the processor for a particular model, creating it if it does not
     *         already exist
     * @throws CoreException if an attempt to get the model associated with file
     *         fails due to reasons other than I/O problems
     * @throws IOException if an attempt to get the model associated with file
     *         fails due to I/O problems
     */
    public static JSPModelProcessor get(IFile file) throws CoreException, IOException
    {
        synchronized(file)
        {
            JSPModelProcessor processor = 
                (JSPModelProcessor) file.getSessionProperty(SESSION_PROPERTY_JSPMODELPROCESSOR_KEY);
            
            if (processor == null)
            {
                processor = new JSPModelProcessor(file);
                file.setSessionProperty(SESSION_PROPERTY_JSPMODELPROCESSOR_KEY, processor);        
            }
            return processor;        
         }
    }
    
    /**
     * Disposes of the JSPModelProcessor associated with model
     * @param file the file associated with the model processor to be disposed
     */
    public static void dispose(IFile  file)
    {
        try
        {
            synchronized(file)
            {
                // TODO: do we need worry about the processor not being 
                // disposed if the underlying file has been deleted?
                // TODO: need isLocal check?
                if (file.isAccessible())
                {
                    JSPModelProcessor processor = 
                        get(file);
                    
                    if (processor != null
                            && !processor.isDisposed)
                    {
                        file.setSessionProperty(SESSION_PROPERTY_JSPMODELPROCESSOR_KEY, null);
                        processor.dispose();
                    }
                }
            }
        }
        catch (CoreException ce)
        {
            Platform.getLog(JSFCorePlugin.getDefault().getBundle()).log(
                    new Status(IStatus.ERROR, JSFCorePlugin.getDefault().getBundle().getSymbolicName(), 0, "Problem disposing JSPModelProcessor", new Throwable(ce)));
        }
        catch (IOException ioe)
        {
            Platform.getLog(JSFCorePlugin.getDefault().getBundle()).log(
                    new Status(IStatus.ERROR, JSFCorePlugin.getDefault().getBundle().getSymbolicName(), 0, "Problem disposing JSPModelProcessor", new Throwable(ioe)));
        }
    }
    
    private final IFile             _file;
    private final DOMModelForJSP    _model;
    private final ModelListener     _modelListener;
    private boolean                 isDisposed;
    private Map                     _requestMap;
    private Map                     _sessionMap;
    private Map                     _applicationMap;
    private Map                     _noneMap;
    private long                    _lastModificationStamp;
    
    // used to avoid infinite recursion in refresh.  Must never be null
    private final CountingMutex     _lastModificationStampMonitor = new CountingMutex();
    
    /**
     * Construct a new JSPModelProcessor for model
     * 
     * @param model
     */
    private JSPModelProcessor(IFile  file) throws CoreException, IOException
    {
        _model = getModelForFile(file);
        _modelListener = new ModelListener();
        _model.addModelLifecycleListener(_modelListener);
        _file = file;
        // a negative value guarantees that refresh(false) will 
        // force a refresh on the first run
        _lastModificationStamp = -1;
    }
    
    
    private DOMModelForJSP getModelForFile(IFile file) 
            throws CoreException, IOException
    {
        final IModelManager modelManager = 
            StructuredModelManager.getModelManager();

        IStructuredModel model = modelManager.getModelForRead(file);
        
        if (model instanceof DOMModelForJSP)
        {
            return (DOMModelForJSP) model;
        }

        model.releaseFromRead();
        
        throw new CoreException(new Status(IStatus.ERROR, "org.eclipse.blah", 0, 
                        "model not of expected type", new Throwable()));
    }
    
    private void dispose()
    {
        if (!isDisposed)
        {
            _model.releaseFromRead();
            _model.removeModelLifecycleListener(_modelListener);
            
            if (_requestMap != null)
            {
                _requestMap.clear();
                _requestMap = null;
            }
            
            if (_sessionMap != null)
            {
                _sessionMap.clear();
                _sessionMap = null;
            }
            
            if (_applicationMap != null)
            {
                _applicationMap.clear();
                _applicationMap = null;
            }
            
            if (_noneMap != null)
            {
                _noneMap.clear();
                _noneMap = null;
            }

            // mark as disposed
            isDisposed = true;
        }
    }
    
    /**
     * @return true if this model processor has been disposed.  Disposed
     * processors should not be used.
     */
    boolean isDisposed()
    {
        return isDisposed;
    }
    
    /**
     * Updates the internal model
     * @param forceRefresh -- if true, always refreshes, if false,
     * then it only refreshes if the file's modification has changed
     * since the last refresh
     */
    public void refresh(final boolean forceRefresh)
    {
        synchronized(_lastModificationStampMonitor)
        {
            if (_lastModificationStampMonitor.isSignalled())
            {
                // if this calls succeeds, then this thread has obtained the
                // lock already and has called through here before.  
                // return immediately to ensure that we don't recurse infinitely
                return;
            }

            try
            {
                _lastModificationStampMonitor.setSignalled(true);
                
                long currentModificationStamp;
                
                currentModificationStamp = _file.getModificationStamp();
    
                // only refresh if forced or if the underlying file has changed
                // since the last run
                if (forceRefresh
                        || _lastModificationStamp != currentModificationStamp)
                {
                    refreshInternal();
                    _lastModificationStamp = _file.getModificationStamp();
                }
            }
            // make sure that we unsignal the monitor before releasing the
            // mutex
            finally
            {
                _lastModificationStampMonitor.setSignalled(false);
            }
        }
    }
    
    private void refreshInternal()
    {
        final IStructuredDocumentContext context = 
            IStructuredDocumentContextFactory.INSTANCE.getContext(_model.getStructuredDocument(), -1);
        final ITaglibContextResolver taglibResolver =
            IStructuredDocumentContextResolverFactory.INSTANCE.getTaglibContextResolver(context);
        IDOMDocument document = _model.getDocument();
        getApplicationMap().clear();
        getRequestMap().clear();
        getSessionMap().clear();
        //long curTime = System.currentTimeMillis();
        recurseChildNodes(document.getChildNodes(), taglibResolver);
        //long netTime = System.currentTimeMillis() - curTime;
        //System.out.println("Net time to recurse document: "+netTime);
    }
   
    private void recurseChildNodes(final NodeList nodes, 
                                    final ITaglibContextResolver taglibResolver)
    {
        for (int i = 0; i < nodes.getLength(); i++)
        {
            final Node child = nodes.item(i);
            
            // process attributes at this node before recursing
            processAttributes(child, taglibResolver);
            recurseChildNodes(child.getChildNodes(), taglibResolver);
        }
    }
    
    private void processAttributes(final Node node, 
                                    final ITaglibContextResolver taglibResolver)
    {
        if (taglibResolver.hasTag(node))
        {
            final String uri =
                taglibResolver.getTagURIForNodeName(node);
            final String elementName = node.getLocalName();
            
            for (int i = 0; i < node.getAttributes().getLength(); i++)
            {
                final Node attribute = node.getAttributes().item(i);

                processSymbolContrib(uri, elementName, attribute);
                processSetsLocale(uri, elementName, attribute);
            }
        }
    }

    private void processSymbolContrib(final String uri, final String elementName, Node attribute)
    {
        final SymbolContribAggregator  aggregator =
            SymbolContribAggregator.
               create(_file.getProject(), uri, elementName, attribute.getLocalName());
  
        if (aggregator != null)
        {    
            final AbstractContextSymbolFactory factory = aggregator.getFactory();
            final String symbolName = attribute.getNodeValue();

            if (factory != null)
            {
//                long curTime = System.currentTimeMillis();
                 
                final List problems = new ArrayList();
                ISymbol symbol =
                    factory.create(symbolName, 
                                  ISymbolConstants.SYMBOL_SCOPE_REQUEST, //TODO:
                                  IStructuredDocumentContextFactory.INSTANCE.
                                      getContext(_model.getStructuredDocument(), 
                                                 attribute),
                                  problems);

//                long netTime = System.currentTimeMillis() - curTime;
//                System.out.println("Time to process loadBundle: "+netTime);

                if (symbol != null)
                {
                    updateMap(symbol, aggregator.getScope());
                }
            }
            else
            {
                IComponentSymbol componentSymbol = 
                    SymbolFactory.eINSTANCE.createIComponentSymbol();
                componentSymbol.setName(symbolName);

                updateMap(componentSymbol, aggregator.getScope());
            }
        }
    }
    
    private void processSetsLocale(final String uri, final String elementName, Node attribute)
    {
        LocaleSetAggregator  aggregator = LocaleSetAggregator.create(_file.getProject(), uri, elementName, attribute.getLocalName());

        if (aggregator != null)
        {
            DesignTimeApplicationManager  dtAppMgr =
                DesignTimeApplicationManager.getInstance(_file.getProject());
            
            DTFacesContext facesContext = dtAppMgr.getFacesContext(_file);
            
            if (facesContext != null)
            {
                facesContext.setLocaleString(attribute.getNodeValue());
            }
        }
    }

   /**
     * @param scopeName - one of "request", "session" or "application"
     * @return an unmodifable map containing all known symbols for
     * that scope.  If scopeName is not found, returns the empty map.
     */
    public Map getMapForScope(String scopeName)
    {
        final Map map = getMapForScopeInternal(scopeName);
        
        if (map != null)
        {
            return Collections.unmodifiableMap(map);
        }
        
        return Collections.EMPTY_MAP;
    }
    
    private void updateMap(ISymbol symbol, String  scopeName)
    {
        final Map map = getMapForScopeInternal(scopeName);
        
        if (map != null)
        {
            map.put(symbol.getName(), symbol);
        }
        else
        {
            Platform.getLog(JSFCorePlugin.getDefault().getBundle()).log(new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID, 0, "Scope not found: "+scopeName, new Throwable()));
        }
    }

    private Map getMapForScopeInternal(String scopeName)
    {
        if (ISymbolConstants.SYMBOL_SCOPE_REQUEST_STRING.equals(scopeName))
        {
            return getRequestMap();
        }
        else if (ISymbolConstants.SYMBOL_SCOPE_SESSION_STRING.equals(scopeName))
        {
            return getSessionMap();
        }
        else if (ISymbolConstants.SYMBOL_SCOPE_APPLICATION_STRING.equals(scopeName))
        {
            return getApplicationMap();
        }
        else if (ISymbolConstants.SYMBOL_SCOPE_NONE_STRING.equals(scopeName))
        {
            return getNoneMap();
        }
        
        Platform.getLog(JSFCorePlugin.getDefault().getBundle()).log(new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID, 0, "Scope not found: "+scopeName, new Throwable()));
        return null;
    
    }
    
    private Map getRequestMap()
    {
        if (_requestMap == null)
        {
            _requestMap = new SymbolMap();
        }
        
        return _requestMap;
    }
    
    private Map getSessionMap()
    {
        if (_sessionMap == null)
        {
            _sessionMap = new SymbolMap();
        }
        
        return _sessionMap;
    }
    
    private Map getApplicationMap()
    {
        if (_applicationMap == null)
        {
            _applicationMap = new SymbolMap();
        }
        
        return _applicationMap;
    }
    
    private Map getNoneMap()
    {
        if (_noneMap == null)
        {
            _noneMap = new SymbolMap();
        }
        
        return _noneMap;
    }

    /**
     * Listens to the JSP model and reacts to changes
     * @author cbateman
     *
     */
    private class ModelListener implements IModelLifecycleListener
    {
        public void processPostModelEvent(ModelLifecycleEvent event)
        {
            // TODO: figure this event structure out seems like it is possibly
            // broken...
            if (((event.getType() & ModelLifecycleEvent.MODEL_DIRTY_STATE) != 0
                    && !_model.isDirty()) // if the dirty state changed as now not dirty, then we have a save
                )//|| (event.getType() & ModelLifecycleEvent.MODEL_REINITIALIZED) != 0)
            {
                // refresh if modified on disk
                refresh(false);
            }
        }

        public void processPreModelEvent(ModelLifecycleEvent arg0) {
            // do nothing
        }
    }
    
    
    /**
     * Aggregates the sets-locale meta-data
     * 
     * @author cbateman
     */
    private static class LocaleSetAggregator
    {
        private final static String SETS_LOCALE = "sets-locale";
        
        static LocaleSetAggregator create(IProject project, 
                                              final String uri, 
                                              final String elementName, final String attributeName)
        {            
        	final IMetaDataModelContext mdContext = MetaDataQueryHelper.createMetaDataModelContext(project, MetaDataQueryHelper.TAGLIB_DOMAIN, uri);
            Trait trait = MetaDataQueryHelper.getTrait(mdContext, elementName+"/"+attributeName, SETS_LOCALE);

            if (TraitValueHelper.getValueAsBoolean(trait))
            {
                return new LocaleSetAggregator();
            }

            return null;
        }
    }
    
    /**
     * Aggregates all the symbol contributor meta-data into a single object
     * 
     * @author cbateman
     *
     */
    private static class SymbolContribAggregator
    {
        private final static String CONTRIBUTES_VALUE_BINDING = 
            "contributes-value-binding";
        private final static String VALUE_BINDING_SCOPE = "value-binding-scope";
        private final static String VALUE_BINDING_SYMBOL_FACTORY = 
            "value-binding-symbol-factory";

        /**
         * @param attributeName
         * @return a new instance only if attributeName is a symbol contributor
         */
        static SymbolContribAggregator create(final IProject project, 
        									  final String uri, 
                                              final String elementName, 
                                              final String attributeName)
        {
        	final String entityKey = elementName+"/"+attributeName;
        	final IMetaDataModelContext mdContext = MetaDataQueryHelper.createMetaDataModelContext(project, MetaDataQueryHelper.TAGLIB_DOMAIN, uri);
            Trait trait = MetaDataQueryHelper.getTrait(mdContext, entityKey, CONTRIBUTES_VALUE_BINDING);

            boolean contribsValueBindings = TraitValueHelper.getValueAsBoolean(trait);

            if (contribsValueBindings)
            {
                String scope = null;
                String symbolFactory = null;
                
                trait = MetaDataQueryHelper.getTrait(mdContext, entityKey, VALUE_BINDING_SCOPE);
                scope = TraitValueHelper.getValueAsString(trait);

                if (scope != null & !scope.equals(""))
                {
                	trait = MetaDataQueryHelper.getTrait(mdContext, entityKey, VALUE_BINDING_SYMBOL_FACTORY);
                	symbolFactory = TraitValueHelper.getValueAsString(trait);                      
                }

                return new SymbolContribAggregator(scope, symbolFactory);
            }

            return null;
        }

        private final Map   _metadata = new HashMap(4);

        SymbolContribAggregator(final String scope, final String factory)
        {
            _metadata.put("scope", scope);
            _metadata.put("factory", factory);
        }

        /**
         * @return the scope
         */
        public String getScope()
        {
            return (String) _metadata.get("scope");
        }
        
        /**
         * @return the factory
         */
        public AbstractContextSymbolFactory getFactory()
        {
            return (AbstractContextSymbolFactory) 
                JSFCommonPlugin.getSymbolFactories().get(_metadata.get("factory"));
        }
    }
    
    private static class CountingMutex extends Object
    {
        private boolean _signalled = false;

        /**
         * @return true if the state of mutex is signalled
         */
        public synchronized boolean isSignalled() {
            return _signalled;
        }

        /**
         * @param signalled
         */
        public synchronized void setSignalled(boolean signalled) {
            this._signalled = signalled;
        }
        

    }
}
