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
package org.eclipse.jst.jsf.facesconfig.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.internal.translator.IAnyTranslator;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMAdapter;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRenderer;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.xml.core.internal.emf2xml.EMF2DOMSSEAdapter;
import org.eclipse.wst.xml.core.internal.emf2xml.EMF2DOMSSERenderer;
import org.eclipse.wst.xml.core.internal.emf2xml.EMF2DOMSSERendererFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * The custom render factory for the Faces Config model EMF2DOM translations.
 * A singleton factory.
 */
public class FacesRendererFactory extends EMF2DOMSSERendererFactory 
{
    /**
     * The singleton factory instance.
     */
    @SuppressWarnings("hiding")
    public static  FacesRendererFactory INSTANCE = new FacesRendererFactory();
    
    private FacesRendererFactory() {
        super();
    }

    /**
     * @see org.eclipse.wst.common.internal.emf.resource.RendererFactory#createRenderer()
     */
    public Renderer createRenderer() {
        return new MyRenderer();
    }

    private static class MyRenderer extends EMF2DOMSSERenderer
    {

        protected EMF2DOMAdapter createRootDOMAdapter() 
        {
            return new MyEMF2DOMAdapterRoot(getResource(), document, this, getResource().getRootTranslator());
        }
    }
    
    /**
     * Customized adapter that lets us inject arbitrary DOM children
     * into for ANY elements
     */
    private static class MyEMF2DOMAdapterRoot extends EMF2DOMSSEAdapter
    {

        /**
         * @param object
         * @param node
         * @param renderer
         * @param translator
         */
        public MyEMF2DOMAdapterRoot(org.eclipse.emf.common.notify.Notifier object, Node node,
                EMF2DOMRenderer renderer, Translator translator) {
            super(object, node, renderer, translator);
        }

        /**
         * @param node
         * @param renderer
         * @param translator
         */
        public MyEMF2DOMAdapterRoot(Node node, EMF2DOMRenderer renderer,
                Translator translator) {
            super(node, renderer, translator);
        }

        /**
         * @param resource
         * @param document
         * @param renderer
         * @param translator
         */
        public MyEMF2DOMAdapterRoot(TranslatorResource resource, Document document,
                EMF2DOMRenderer renderer, Translator translator) {
            super(resource, document, renderer, translator);
        }

        protected void initChildTranslators() {
            // if this is an ANY translator, then we have prepend the list after
            // initialization with any dynamic attribute translators
            super.initChildTranslators();
            
            if (fTranslator instanceof IAnyTranslator)
            {
                final Translator[] dynamicAttrs =
                    ((IAnyTranslator)fTranslator)
                         .getDynamicAttributeTranslators(fNode);
                
                // avoid an array copy if not necessary
                if (dynamicAttrs.length > 0)
                {
                    final Translator[] allTranslators =
                        new Translator[childTranslators.length+dynamicAttrs.length];
                    // prepend the dynamic attributes so they are queried first
                    System.arraycopy(dynamicAttrs, 0, allTranslators, 0, dynamicAttrs.length);
                    System.arraycopy(childTranslators, 0, allTranslators, dynamicAttrs.length, childTranslators.length);
                    childTranslators = allTranslators;
                }
            }
        }

        protected Translator[] getChildTranslators() 
        {
            Translator[] translators = super.getChildTranslators();
            // TODO: I'm thinking there must be a more efficient way to do this
            if (fTranslator instanceof  IAnyTranslator)
            {
               EObject eObj = getEObject();
               
               if (eObj instanceof DynamicElement)
               {
                   List newArrayList = new ArrayList();
                   Translator[] dynamicTranslators = 
                       ((IAnyTranslator)fTranslator).getDynamicAttributeTranslators((DynamicElement)eObj);
                   
                   // loop through the dynamic translators and add any to the list
                   // that aren't already there
                   for (int i = 0; i < dynamicTranslators.length; i++)
                   {
                       Translator translator = dynamicTranslators[i];
                       
                       for (int j = 0; j < translators.length; j++)
                       {
                           final Translator testTranslator = translators[j];
                           
                           if (testTranslator.isDOMAttribute()
                               // TODO: not fond of passing null, but the arg is ignored anyway (for now)
                               && testTranslator.getDOMName(null).equals(translator.getDOMName(null)))
                           {
                               // null indicates found
                               translator = null;
                               break;
                           }
                        }
                       
                        // if not found, add to list
                        if (translator != null)
                        {
                            newArrayList.add(translator);
                        }
                    }
                   
                    if (newArrayList.size() > 0)
                    {
                        newArrayList.addAll(Arrays.asList(translators));
                        translators = (Translator[]) newArrayList.toArray(translators);
                    }
                }
            }
            
            return translators;
        }

        protected List getDOMChildren(Node node, Translator map) 
        {
            if (map instanceof IAnyTranslator)
            {
                List children = new ArrayList();
                
                for (int i = 0; i < node.getChildNodes().getLength(); i++)
                {
                    Node child = node.getChildNodes().item(i);
                    if (child.getNodeType() == Node.ELEMENT_NODE)
                    {
                        children.add(child);
                    }
                }
                return children;
            }
            return super.getDOMChildren(node, map);
        }
        /**
         * Create an adapter for a child DOM node
         * 
         * @param mofObject
         *            org.w3c.dom.Node The node to create the adapter for.
         */
        protected EMF2DOMAdapter primCreateAdapter(EObject mofObject, Translator childMap) {
            // TODO: this may only be necessary when childMap is an IAnyTranslator...
            Element newNode = createNewNode(mofObject, childMap);
            return new MyEMF2DOMAdapterRoot(mofObject, newNode, fRenderer, childMap);
        }

        /**
         * Create an adapter for a child DOM node
         * 
         * @param node
         *            org.w3c.dom.Node The node to create the adapter for.
         */
        protected EMF2DOMAdapter primCreateAdapter(Node node, Translator childMap) {
            return new MyEMF2DOMAdapterRoot(node, fRenderer, childMap);
        }
    }
}
