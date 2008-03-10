package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.analyzer.TagAnalyzer;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * A resolving strategy that uses tag introspection.
 * 
 * @author cbateman
 * 
 */
public final class TagIntrospectingStrategy extends JSPTagResolvingStrategy
{
    /**
     * the identifier of this strategy
     */
    public final static String ID = "org.eclipse.jst.jsf.designtime.TagIntrospectingStrategy";
    /**
     * the display name
     */
    public final static String DISPLAY_NAME = "Introspecting Tag Resolver";

    private final IProject _project;

    /**
     * @param project
     */
    public TagIntrospectingStrategy(final IProject project)
    {
        // TODO: would it be better to have a model context on the resolve?
        _project = project;
    }

    @Override
    public String getId()
    {
        return ID;
    }

    public String getDisplayName()
    {
        return DISPLAY_NAME;
    }

    @Override
    public ITagElement resolve(TLDElementDeclaration element)
    {
        return TagAnalyzer.createTLDTagElement(element, _project);
    }

}
