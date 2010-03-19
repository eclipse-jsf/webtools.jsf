package org.eclipse.jst.jsf.core.tests.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.TagElement;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;

public class MockJSPTagRegistry implements ITagRegistry
{

    public boolean isDisposed()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public void refresh(Runnable runAfter, boolean flushCaches)
    {
        throw new UnsupportedOperationException();
    }

    public void addListener(ITagRegistryListener listener)
    {
        //no-op
    }

    public void removeListener(ITagRegistryListener listener)
    {
    	//no-op
    }

    public Collection<? extends Namespace> getAllTagLibraries()
    {
        List<Namespace>  all = new ArrayList<Namespace>();
        MyNamespace ns_core = new MyNamespace(ITLDConstants.URI_JSF_CORE);
        ns_core.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_CORE, "view"));
        all.add(ns_core);
        
        //jsf html
        MyNamespace ns = new MyNamespace(ITLDConstants.URI_JSF_HTML);
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "form"));  
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "commandButton"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "commandLink"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "panelGrid"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "dataTable"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "graphicImage"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "inputHidden"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "message"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "messages"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "outputFormat"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "outputLabel"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "outputText"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "panelGroup"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "inputText"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "inputSecret"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "selectBooleanCheckbox"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "selectManyCheckbox"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "selectManyListbox"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "selectManyMenu"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "selectOneListbox"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "selectOneMenu"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "selectOneRadio"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "inputTextarea"));
        ns.addViewElement(new MyTagElement(ITLDConstants.URI_JSF_HTML, "selectBooleanCheckbox"));        
        all.add(ns);
        return all;        
    }

    public Namespace getTagLibrary(String uri)
    {
        for (final Namespace ns : getAllTagLibraries())
        {
            if (ns.getNSUri().equals(uri))
            {
                return ns;
            }
        }
        return null;
    }

    private static class MyNamespace extends Namespace
    {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private final String _uri;
        private final List<ITagElement> _viewElements = new ArrayList<ITagElement>();

        /**
         * @param uri
         */
        public MyNamespace(final String uri)
        {
            _uri = uri;
        }

        @Override
        public String getNSUri()
        {
            return _uri;
        }

        void addViewElement(final ITagElement tagElement)
        {
            _viewElements.add(tagElement);
        }
        
        @Override
        public Collection<ITagElement> getViewElements()
        {
            return _viewElements;
        }

        @Override
        public boolean hasViewElements()
        {
            return getViewElements().size() > 0;
        }

        @Override
        public boolean isInitialized()
        {
            return true;
        }

        @Override
        public ITagElement getViewElement(String name)
        {
            for (final ITagElement element : getViewElements()){
                if (element.getName().equals(name))
                {
                    return element;
                }
            }
            return null;
        }

        @Override
        public String getDisplayName()
        {
            throw new UnsupportedOperationException();
        }
        
    }
    
    private static class MyTagElement extends TagElement
    {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private final String    _name;
        private final String    _uri;
        
        public MyTagElement(String uri, String name)
        {
            super();
            _name = name;
            _uri = uri;
        }

        public Map<String, ? extends ITagAttribute> getAttributes()
        {
            return Collections.emptyMap();
        }

        @Override
        public String getName()
        {
            return _name;
        }

        @Override
        public String getUri()
        {
            return _uri;
        }

        @Override
        public String getTagHandlerClassName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        @SuppressWarnings("rawtypes")
        @Override
        public Map getAttributeHandlers()
        {
            return Collections.emptyMap();
        }
        
    }
}
