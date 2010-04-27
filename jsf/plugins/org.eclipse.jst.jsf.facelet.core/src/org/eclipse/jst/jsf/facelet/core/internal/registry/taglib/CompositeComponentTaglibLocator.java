package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorProvider;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResourceLocator;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResource;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceChangeListener;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.JSFResourceBasedTagRecord.Builder;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.Listener.TaglibChangedEvent;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.Listener.TaglibChangedEvent.CHANGE_TYPE;

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
    private final class ResourceLocatorChangeListener extends
            JSFResourceChangeListener
    {
        @Override
        public void changed(final JSFResourceChangedEvent event)
        {
            final Builder builder = new Builder();
            switch (event.getChangeType())
            {
            case ADDED:
            case CHANGED:
            {
                List<TaglibChangedEvent> events = Collections.EMPTY_LIST;

                if (event.getNewValue().isFragment())
                {
                    // if it's a fragment handle as a possible library add
                    events = handleFolderAddChange(event, builder);
                } else
                {
                    // otherwise, handle as a file add.
                    events = handleFileAddChange(event, builder);
                }
                _records = builder.merge(events, _records);
                for (final TaglibChangedEvent fireEvent : events)
                {
                    fireChangeEvent(fireEvent);
                }
            }
                break;
            case REMOVED:
            {
                final JSFResource oldValue = event.getOldValue();
                builder.addTag(oldValue, CHANGE_TYPE.REMOVED);
                final List<TaglibChangedEvent> events = builder.createRemove(
                        CompositeComponentTaglibLocator.this, _records);
                _records = builder.merge(events, _records);
                for (final TaglibChangedEvent fireEvent : events)
                {
                    fireChangeEvent(fireEvent);
                }
            }
                break;
            }
        }

        private List<TaglibChangedEvent> handleFolderAddChange(
                JSFResourceChangedEvent event, Builder builder)
        {
            final JSFResource newValue = event.getNewValue();
            builder.addLibrary(newValue, CHANGE_TYPE.ADDED);
            return builder.createMerge(CompositeComponentTaglibLocator.this, _records);
        }

        private List<TaglibChangedEvent> handleFileAddChange(
                final JSFResourceChangedEvent event, final Builder builder)
        {
            final JSFResource newValue = event.getNewValue();
            builder.addTag(newValue, CHANGE_TYPE.ADDED);
            return builder.createMerge(CompositeComponentTaglibLocator.this,
                    _records);
        }
    }

    /**
     * the id of the locator strategy.
     */
    public static final String ID = CompositeComponentTaglibLocator.class
            .getCanonicalName();
    private static final String DISPLAY_NAME = "Composite Composite Tag Lib Locator"; //$NON-NLS-1$
    private final ILocatorProvider<IJSFResourceLocator> _locatorProvider;
    private Map<String, JSFResourceBasedTagRecord> _records;
    private final ResourceLocatorChangeListener _listener;

    /**
     * @param locatorProvider
     */
    public CompositeComponentTaglibLocator(
            final ILocatorProvider<IJSFResourceLocator> locatorProvider)
    {
        super(ID, DISPLAY_NAME);
        _locatorProvider = locatorProvider;
        _locatorProvider.initialize();
        _listener = new ResourceLocatorChangeListener();
    }

    @Override
    public void start(final IProject initialContext)
    {
        for (final IJSFResourceLocator locator : _locatorProvider.getLocators())
        {
            locator.start(initialContext);
            locator.addListener(_listener);
        }

        final Builder builder = new Builder();
        for (final IJSFResourceLocator locator : _locatorProvider.getLocators())
        {
            final List<JSFResource> resources = locator.locate(initialContext);
            for (final JSFResource resource : resources)
            {
                builder.addTag(resource, CHANGE_TYPE.ADDED);
            }
        }
        _records = builder.build();

        super.start(initialContext);
    }

    @Override
    public void stop()
    {
        for (final IJSFResourceLocator locator : _locatorProvider.getLocators())
        {
            locator.stop();
        }
        super.stop();
    }

    @Override
    protected Map<String, ? extends IFaceletTagRecord> doLocate(
            final IProject context)
    {
        return Collections.unmodifiableMap(_records);
    }
}
