package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorProvider;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResourceLocator;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResource;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.JSFResourceBasedTagRecord.Builder;

/**
 * A taglib locator that locates composite components, which a located as a type
 * of JSF locatable resource.
 * 
 * @author cbateman
 * 
 */
public class CompositeComponentTaglibLocator extends
        AbstractFaceletTaglibLocator
{
    /**
     * the id of the locator strategy.
     */
    public static final String ID = CompositeComponentTaglibLocator.class
            .getCanonicalName();
    private static final String DISPLAY_NAME = "Composite Composite Tag Lib Locator"; //$NON-NLS-1$
    private static final String FACELET_FILE_CONTENT_TYPE = "org.eclipse.wst.html.core.htmlsource"; //$NON-NLS-1$
    private final ILocatorProvider<IJSFResourceLocator> _locatorProvider;
    private Map<String, ? extends IFaceletTagRecord> _records;

    /**
     * @param locatorProvider
     */
    public CompositeComponentTaglibLocator(
            final ILocatorProvider<IJSFResourceLocator> locatorProvider)
    {
        super(ID, DISPLAY_NAME);
        _locatorProvider = locatorProvider;
        _locatorProvider.initialize();
    }

    @Override
    public void start(IProject initialContext)
    {
        for (IJSFResourceLocator locator : _locatorProvider.getLocators())
        {
            locator.start(initialContext);
        }
        super.start(initialContext);
    }

    @Override
    public void stop()
    {
        for (IJSFResourceLocator locator : _locatorProvider.getLocators())
        {
            locator.stop();
        }
        super.stop();
    }

    @Override
    protected Map<String, ? extends IFaceletTagRecord> doLocate(
            final IProject context)
    {
        final Builder builder = new Builder();
        for (final IJSFResourceLocator locator : _locatorProvider.getLocators())
        {
            List<JSFResource> resources = locator.locate(context);
            for (final JSFResource resource : resources)
            {
                if (resource.isAccessible()
                        && resource.isContentType(FACELET_FILE_CONTENT_TYPE))
                {
                    builder.addTag(resource);
                }
            }
        }
        _records = builder.build();
        return _records;
    }

}
