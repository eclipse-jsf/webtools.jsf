/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorProvider;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResource;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResourceContainer;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResourceFragment;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResourceFragment.Type;
import org.eclipse.jst.jsf.designtime.internal.resources.IJSFResourceLocator;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceChangeListener;
import org.eclipse.jst.jsf.designtime.internal.resources.JSFResourceContainer;
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
                    handleAddAndChange(event, builder);
                }
                break;
                case REMOVED:
                {
                    handleRemove(event, builder);
                }
                break;
            }
        }

        private void handleRemove(final JSFResourceChangedEvent event,
                final Builder builder)
        {
        	//Bug 324234 - NPE from CompositeComponentTaglibLocator
        	if (event != null)
        	{
	            IJSFResourceFragment oldValue = event.getOldValue();
	            if (oldValue != null)
	            {
	            	Type type = oldValue.getType();
	            	if (type != null)
	            	{
			            List<TaglibChangedEvent> events = Collections.EMPTY_LIST;
			            switch (type)
			            {
			                case RESOURCE:
			                {
			                    events = handleRemoveResource(event, builder);
			                }
			                break;
			                case CONTAINER:
			                {
			                    events = handleRemoveContainer(event, builder);
			                }
			                break;
			            }
			            _records = builder.merge(events, _records);
			            for (final TaglibChangedEvent fireEvent : events)
			            {
			                fireChangeEvent(fireEvent);
			            }
	            	}
	            }
        	}
        }

        private List<TaglibChangedEvent> handleRemoveContainer(
                final JSFResourceChangedEvent event, final Builder builder)
        {
            final IJSFResourceFragment oldValue = event.getOldValue();
            builder.addLibrary((IJSFResourceContainer) oldValue,
                    CHANGE_TYPE.REMOVED);
            return builder.createRemove(CompositeComponentTaglibLocator.this,
                    _records);
        }

        private List<TaglibChangedEvent> handleRemoveResource(
                final JSFResourceChangedEvent event, final Builder builder)
        {
            final IJSFResourceFragment oldValue = event.getOldValue();
            builder.addTag((IJSFResource) oldValue, CHANGE_TYPE.REMOVED);
            return builder.createRemove(CompositeComponentTaglibLocator.this,
                    _records);
        }

        private void handleAddAndChange(final JSFResourceChangedEvent event,
                final Builder builder)
        {
            List<TaglibChangedEvent> events = Collections.EMPTY_LIST;

            switch (event.getNewValue().getType())
            {
                case CONTAINER:
                    // if it's a fragment handle as a possible library add
                    events = handleFolderAddChange(event, builder);
                break;
                case RESOURCE:
                    // otherwise, handle as a file add.
                    events = handleFileAddChange(event, builder);
                break;
            }

            _records = builder.merge(events, _records);

            for (final TaglibChangedEvent fireEvent : events)
            {
                fireChangeEvent(fireEvent);
            }
        }

        private List<TaglibChangedEvent> handleFolderAddChange(
                final JSFResourceChangedEvent event, final Builder builder)
        {
            final JSFResourceContainer newValue = (JSFResourceContainer) event
                    .getNewValue();
            builder.addLibrary(newValue, CHANGE_TYPE.ADDED);
            return builder.createMerge(CompositeComponentTaglibLocator.this,
                    _records);
        }

        private List<TaglibChangedEvent> handleFileAddChange(
                final JSFResourceChangedEvent event, final Builder builder)
        {
            final IJSFResource newValue = (IJSFResource) event.getNewValue();
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
            final List<IJSFResourceFragment> resources = locator
                    .locate(initialContext);
            for (final IJSFResourceFragment resource : resources)
            {
                if (resource.getType() == Type.RESOURCE)
                {
                    builder.addTag((IJSFResource) resource, CHANGE_TYPE.ADDED);
                }
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
