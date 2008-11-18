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

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.region.Region2ElementAdapter;
import org.eclipse.jst.jsf.core.internal.region.Region2ElementAdapter.NoElementException;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.w3c.dom.Element;

/**
 * @author cbateman
 * 
 */
public final class XMLViewObjectMappingService implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -5371998199186683997L;
    private final Map<ElementData, ViewObject> _elementToViewObjMap;
    private final Map<ViewObject, ElementData> _viewObjToElementMap;

    /**
     * 
     */
    public XMLViewObjectMappingService()
    {
        _elementToViewObjMap = new HashMap<ElementData, ViewObject>();
        _viewObjToElementMap = new HashMap<ViewObject, ElementData>();
    }

    /**
     * @param elementData
     * @param viewObject
     */
    public void createMapping(final ElementData elementData,
            final ViewObject viewObject)
    {
        if (elementData == null)
        {
            throw new IllegalArgumentException("elementData cannot be null"); //$NON-NLS-1$
        }
        else if (viewObject == null)
        {
            throw new IllegalArgumentException("viewObject cannot be null"); //$NON-NLS-1$
        }

        synchronized(this)
        {
            _elementToViewObjMap.put(elementData, viewObject);
            _viewObjToElementMap.put(viewObject, elementData);
        }
    }

    /**
     * Remove all mappings
     */
    public void clearMappings()
    {
        synchronized (this)
        {
            _elementToViewObjMap.clear();
            _viewObjToElementMap.clear();
        }
    }

    /**
     * @param elementData
     * @return the view object for which the mapping was removed
     */
    public ViewObject removeMapping(final ElementData elementData)
    {
        if (elementData == null)
        {
            throw new IllegalArgumentException("elementData mustn't be null"); //$NON-NLS-1$
        }

        ViewObject viewObject = null;
        synchronized(this)
        {
            viewObject = _elementToViewObjMap.remove(elementData);
            if (viewObject != null)
            {
                _viewObjToElementMap.remove(viewObject);
            }
        }
        return viewObject;
    }

    /**
     * @param viewObject
     * @return the element data for which the mapping was removed
     */
    public ElementData removeMapping(final ViewObject viewObject)
    {
        if (viewObject == null)
        {
            throw new IllegalArgumentException("elementData mustn't be null"); //$NON-NLS-1$
        }
        
        ElementData elementData = null;
        synchronized(this)
        {
            elementData = _viewObjToElementMap.remove(viewObject);
            if (elementData != null)
            {
                _elementToViewObjMap.remove(elementData);
            }
        }
        return elementData;
    }

    /**
     * @param viewObject
     * @return the element data for the view object
     */
    public synchronized ElementData findElementData(final ViewObject viewObject)
    {
        return _viewObjToElementMap.get(viewObject);
    }

    /**
     * @param viewObject
     * @return the element for the viewObject
     */
    public Element findElement(final ViewObject viewObject)
    {
        final ElementData elementData = findElementData(viewObject);

        if (elementData != null)
        {
            final IFile file = ResourcesPlugin.getWorkspace().getRoot()
                    .getFile(new Path(elementData.getDocumentPath()));

            if (file != null && file.isAccessible())
            {
                IStructuredModel model = null;
                try
                {
                    model = StructuredModelManager.getModelManager()
                            .getModelForRead(file);
                    final IStructuredDocument sDoc = model
                            .getStructuredDocument();
                    final IStructuredDocumentRegion region = sDoc
                            .getRegionAtCharacterOffset(elementData
                                    .getStartOffset());
                    final Region2ElementAdapter adapter = new Region2ElementAdapter(
                            region);
                    final TagIdentifier tagId = adapter.getTagId();
                    if (tagId.equals(elementData.getTagId()))
                    {
                        return adapter.getElement();
                    }
                }
                catch (CoreException ce)
                {
                    JSFCorePlugin.log(ce,
                            "While finding element for viewObject: " //$NON-NLS-1$
                                    + viewObject.toString() + " in " //$NON-NLS-1$
                                    + file.getFullPath());
                }
                catch (IOException e)
                {
                    JSFCorePlugin.log(e,
                            "While finding element for viewObject: " //$NON-NLS-1$
                                    + viewObject.toString() + " in " //$NON-NLS-1$
                                    + file.getFullPath());
                }
                catch (NoElementException e)
                {
                    JSFCorePlugin.log(e,
                            "While finding element for viewObject: " //$NON-NLS-1$
                                    + viewObject.toString() + " in " //$NON-NLS-1$
                                    + file.getFullPath());
                }
                finally
                {
                    if (model != null)
                    {
                        model.releaseFromRead();
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param elementData
     * @return the mapped view object for elementData or null if not found.
     */
    public synchronized ViewObject findViewObject(final ElementData elementData)
    {
        return _elementToViewObjMap.get(elementData);
    }

    /**
     * @param namespace
     * @param tagName
     * @param context
     * @param attributeToPropertyMap 
     * @return a new Element data for the namespace/element name in 'context'
     */
    public static ElementData createElementData(final String namespace,
            final String tagName, final IStructuredDocumentContext context, 
            final Map<String, String> attributeToPropertyMap)
    {
        final IFile file = getFile(context);
        final int offset = context.getDocumentPosition();

        if (file != null && file.isAccessible() && offset > -1
                && tagName != null && namespace != null)
        {
            return new ElementData(offset, file.getFullPath().toString(),
                    TagIdentifierFactory
                            .createJSPTagWrapper(namespace, tagName),
                     attributeToPropertyMap);
        }
        return null;
    }

    private static IFile getFile(final IStructuredDocumentContext context)
    {
        final IWorkspaceContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                .getWorkspaceContextResolver(context);
        IResource res = resolver.getResource();
        if (res instanceof IFile)
        {
            return (IFile) res;
        }
        // fall through.
        return null;
    }

    /**
     * Describes enough information about an element instance to look it up
     * again, without storing possibly transient references to it.
     * 
     * @author cbateman
     * 
     */
    public final static class ElementData implements Serializable
    {
        /**
         * serializable id
         */
        private static final long       serialVersionUID = 7937312530318827977L;

        private transient TagIdentifier _tagId;
        private Map<String, String>     _attributeToPropertyMap;
        private String                  _documentPath;
        private int                     _startOffset;

        /**
         * @param startOffset
         * @param documentPath
         * @param tagId
         * @param attributeToPropertyMap constructor takes copy of map
         */
        private ElementData(final int startOffset, final String documentPath,
                final TagIdentifier tagId, Map<String, String> attributeToPropertyMap)
        {
            super();
            _startOffset = startOffset;
            
            _tagId = tagId;
            _documentPath = documentPath;
            _attributeToPropertyMap = Collections.unmodifiableMap(
                    new HashMap<String,String>(attributeToPropertyMap));
        }

        /**
         * Default constructor
         */
        protected ElementData()
        {
            // for serialization
        }

        /**
         * @return the tag identifier for this element
         */
        public final TagIdentifier getTagId()
        {
            return _tagId;
        }

        /**
         * @param forTagAttribute
         * @return the name of the view object property that forTagAttribute
         * maps to on this element or null if none.
         */
        public final String getPropertyName(final String forTagAttribute)
        {
            return _attributeToPropertyMap.get(forTagAttribute);
        }

        final int getStartOffset()
        {
            return _startOffset;
        }

        final String getDocumentPath()
        {
            return _documentPath;
        }

        private void writeObject(final java.io.ObjectOutputStream out)
                throws IOException
        {
            out.defaultWriteObject();
            out.writeObject(_tagId.getUri());
            out.writeObject(_tagId.getTagName());
        }

        private void readObject(final java.io.ObjectInputStream in)
                throws IOException, ClassNotFoundException
        {
            in.defaultReadObject();
            final String uri = (String) in.readObject();
            final String tagName = (String) in.readObject();
            _tagId = TagIdentifierFactory.createJSPTagWrapper(uri, tagName);
        }

        @Override
        public boolean equals(final Object obj)
        {
            if (this == obj)
            {
                return true;
            }
            if (obj instanceof ElementData)
            {
                final ElementData other = (ElementData) obj;
                return _startOffset == other._startOffset
                        && _documentPath.equals(other._documentPath)
                        && _tagId.equals(other._tagId);
            }
            return false;
        }

        @Override
        public int hashCode()
        {
            int hashCode = _tagId.hashCode();
            hashCode ^= _documentPath.hashCode();
            // startOffsets will generally fit in the first 10 bits, so mix
            // it up a bit.
            hashCode ^= ~(_startOffset * 104551);
            return hashCode;
        }
    }
}
