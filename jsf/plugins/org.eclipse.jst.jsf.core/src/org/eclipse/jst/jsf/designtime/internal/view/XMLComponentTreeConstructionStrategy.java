package org.eclipse.jst.jsf.designtime.internal.view;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.Decorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A component tree construction strategy based on XML view definition
 * 
 * @author cbateman
 * 
 */
public class XMLComponentTreeConstructionStrategy extends
        ComponentTreeConstructionStrategy<Node, IDocument>
{
    private final XMLViewDefnAdapter                _adapter;
    private final XMLViewObjectConstructionStrategy _objectConstructionStrategy;

    /**
     * @param adapter
     * @param project
     */
    public XMLComponentTreeConstructionStrategy(
            final XMLViewDefnAdapter adapter, final IProject project)
    {
        _adapter = adapter;
        _objectConstructionStrategy =
                new XMLViewObjectConstructionStrategy(adapter,
                        new ComponentConstructionData(0, null, project));
    }

    @Override
    public ComponentInfo createComponentTree(final DTFacesContext context,
            final DTUIViewRoot viewRoot)
    {
        final IDocument container =
                _adapter.getContainer(context, viewRoot.getViewId());
        final List<Node> roots = _adapter.getViewDefnRoots(container);

        if (roots.size() > 0)
        {
            _objectConstructionStrategy.getConstructionData().setIdCounter(0);
            // can only handle a single root for XML; should be the DOM root
            return buildComponentTree(roots.get(0), viewRoot, container);
        }

        return viewRoot;
    }

    private ComponentInfo buildComponentTree(final Node root,
            final DTUIViewRoot viewRoot, final IDocument document)
    {
        final ComponentInfo dummyRoot =
                ComponentFactory.createComponentInfo(null, null, null, true);
        recurseDOMModel(root, dummyRoot, document);

        ComponentInfo foundRoot = null;

        // TODO: additional cases:
        // 1) Valid case: view is a fragment and has one or more non-view root
        // children
        // 2) Invalid case: not a fragment and has no view root
        // 3) Invalid case: any definition and has more than one view root
        // 4) Invalid case: any definition and has component siblings to the
        // view root
        FIND_VIEWROOT: for (final Iterator it =
                dummyRoot.getChildren().iterator(); it.hasNext();)
        {
            final ComponentInfo topLevelChild = (ComponentInfo) it.next();

            if ("javax.faces.ViewRoot".equals(topLevelChild
                    .getComponentTypeInfo().getComponentType()))
            {
                foundRoot = topLevelChild;
                break FIND_VIEWROOT;
            }
        }

        if (foundRoot != null)
        {
            for (final Iterator it = foundRoot.getChildren().iterator(); it
                    .hasNext();)
            {
                final ComponentInfo child = (ComponentInfo) it.next();
                final String facetName = foundRoot.getFacetName(child);

                // if not a facet, add as a normal child
                if (facetName == null)
                {
                    viewRoot.addChild(child);
                }
                // if it is a facet, add as a facet
                else
                {
                    viewRoot.addFacet(facetName, child);
                }
            }
        }

        return viewRoot;
    }

    private void recurseDOMModel(final Node node, final ComponentInfo parent,
            final IDocument document)
    {
        ViewObject mappedObject = null;

        _objectConstructionStrategy.getConstructionData().setParent(parent);

        mappedObject =
                _adapter.mapToViewObject(node, _objectConstructionStrategy,
                        document);

        ComponentInfo newParent = parent;

        if (mappedObject instanceof ComponentInfo)
        {
            parent.addChild((ComponentInfo) mappedObject);
            newParent = (ComponentInfo) mappedObject;
        }
        else if (mappedObject instanceof ConverterDecorator)
        {
            // TODO: validate for parent is not a ValueHolder
            parent.addDecorator((Decorator) mappedObject,
                    ComponentFactory.CONVERTER);
        }
        else if (mappedObject instanceof ValidatorDecorator)
        {
            // TODO: validate for parent is a not an EditableValueHolder
            parent.addDecorator((Decorator) mappedObject,
                    ComponentFactory.VALIDATOR);
        }

        final NodeList children = node.getChildNodes();
        final int numChildren = children.getLength();

        for (int i = 0; i < numChildren; i++)
        {
            recurseDOMModel(children.item(i), newParent, document);
        }
    }
}
