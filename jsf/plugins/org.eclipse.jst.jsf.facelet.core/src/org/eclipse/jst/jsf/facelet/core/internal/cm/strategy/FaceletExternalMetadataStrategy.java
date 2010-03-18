package org.eclipse.jst.jsf.facelet.core.internal.cm.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.facelet.core.internal.cm.AttributeCMAdapter;
import org.eclipse.jst.jsf.facelet.core.internal.cm.TagInfo;
import org.eclipse.jst.jsf.facelet.core.internal.util.ViewUtil;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;

/**
 * The 
 * @author cbateman
 *
 */
public class FaceletExternalMetadataStrategy extends
        AbstractExternalMetadataStrategy
{
    /**
     * The unique identifier for the strategy.
     */
    public final static String STRATEGY_ID = "org.eclipse.jst.jsf.facelet.core.internal.cm.strategy.FaceletExternalMetadataStrategy"; //$NON-NLS-1$
    private final IProject _project;

    /**
     * @param project 
     */
    public FaceletExternalMetadataStrategy(final IProject project)
    {
        super(STRATEGY_ID, Messages.FaceletExternalMetadataStrategy_0);
        _project = project;
    }

    @Override
    public TagInfo perform(TagIdentifier input) throws Exception
    {
        final ITagRegistry tagRegistry = ViewUtil.getTagRegistry(_project);
        
        if (tagRegistry != null)
        {
            Namespace tagLibrary = tagRegistry.getTagLibrary(input.getUri());
            if (tagLibrary != null)
            {
                return new FaceletInternalInfo(tagLibrary);
            }
        }
        return getNoResult();
    }
    
    private static class FaceletInternalInfo extends TagInfo
    {
        private final Namespace _namespace;

        public FaceletInternalInfo(final Namespace namespace)
        {
            _namespace = namespace;
        }
        
        @Override
        public Object getTagProperty(String tagName, String key)
        {
            if ("description".equals(key)) //$NON-NLS-1$
            {
                // TODO:
            }
            return null;
        }

        @Override
        public CMNamedNodeMap getAttributes(String tagName)
        {
            ITagElement viewElement = _namespace.getViewElement(tagName);
            if (viewElement != null)
            {
                return new MyNodeNameMap(viewElement);
            }
            return null;
        }

        private static class MyNodeNameMap implements CMNamedNodeMap
        {
            private final ITagElement  _tagElement;
            private final ArrayList<CMNode> _attrs;

            public MyNodeNameMap(final ITagElement tagElement)
            {
                super();
                _tagElement = tagElement;
                _attrs = new ArrayList<CMNode>();
                for (final Map.Entry<String, ? extends ITagAttribute> entry : _tagElement.getAttributes().entrySet())
                {
                    final ITagAttribute attr = entry.getValue();
                    _attrs.add(new AttributeCMAdapter(attr));
                }
            }

            public int getLength()
            {
                return _attrs.size();
            }

            public CMNode getNamedItem(String name)
            {
                if (name == null)
                {
                    return null;
                }
                for (final CMNode cmNode : _attrs)
                {
                    if (name.equals(cmNode.getNodeName()))
                    {
                        return cmNode;
                    }
                }
                return null;
            }

            public CMNode item(int index)
            {
                return _attrs.get(index);
            }

            public Iterator iterator()
            {
                return Collections.unmodifiableList(_attrs).iterator();
            }
        }
    }
}
