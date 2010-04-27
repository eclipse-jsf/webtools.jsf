/*******************************************************************************
 * Copyright (c) 2005, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery;
import org.eclipse.jst.jsf.common.internal.managedobject.ObjectManager.ManagedObjectException;
import org.eclipse.jst.jsf.common.internal.resource.WorkspaceMediator;
import org.eclipse.jst.jsf.facelet.core.internal.FaceletCorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.Listener.TaglibChangedEvent;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.Listener.TaglibChangedEvent.CHANGE_TYPE;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglib;

/**
 * Attempts to locate Facelet taglib's specified as xml files in project
 * relative paths specified in the Facelet.LIBRARIES servlet parameters.
 * 
 * @author Based on class in org.eclipse.jst.jsf.coreby Ian Trimble - Oracle
 * 
 *         TODO:merge back with common code in JSFAppConfig framework
 */
public class ContextParamSpecifiedFaceletTaglibLocator extends
        AbstractFaceletTaglibLocator
{
    private static final String ID = ContextParamSpecifiedFaceletTaglibLocator.class
            .getCanonicalName();
    private static final String DISPLAYNAME = Messages.ContextParamSpecifiedFaceletTaglibLocator_0;
    private final IProject _project;
    private final Map<String, IFaceletTagRecord> _records;
    private final TagRecordFactory _factory;
    private final TaglibResourceManager _fileManager;

    /**
     * @param project
     * @param factory
     * @param webAppProvider
     * @param vcQuery
     * @param wsMediator
     */
    public ContextParamSpecifiedFaceletTaglibLocator(final IProject project,
            final TagRecordFactory factory,
            final IModelProvider webAppProvider,
            final AbstractVirtualComponentQuery vcQuery,
            final WorkspaceMediator wsMediator)
    {
        super(ID, DISPLAYNAME);
        _project = project;
        _records = new HashMap<String, IFaceletTagRecord>();
        _factory = factory;
        WebappConfiguration webConfig = new WebappConfiguration(project, webAppProvider,
                vcQuery, wsMediator);
        _fileManager = new TaglibResourceManager(project,
                new LibraryChangeHandler(), wsMediator, webConfig);
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.eclipse.jst.jsf.core.jsfappconfig.AbstractJSFAppConfigLocater#
     * startLocating()
     */
    @Override
    public void start(final IProject project)
    {
        _fileManager.initResources();
        super.start(project);
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.eclipse.jst.jsf.core.jsfappconfig.AbstractJSFAppConfigLocater#
     * stopLocating()
     */
    @Override
    public void stop()
    {
        _fileManager.dispose();
        super.stop();
    }

    @Override
    protected Map<String, ? extends IFaceletTagRecord> doLocate(
            final IProject context)
    {
        final List<IFile> files = _fileManager.getResources();

        _records.clear();

        for (final IFile file : files)
        {
            if (file.exists())
            {
                TaglibFileTracker tracker = null;
                try
                {
                    tracker = (TaglibFileTracker) _fileManager.getInstance(file);
                } catch (final ManagedObjectException e)
                {
                    FaceletCorePlugin.log("Creating record", e); //$NON-NLS-1$
                }

                final IFaceletTagRecord record = createTagRecord(file);
                if (record != null)
                {
                    _records.put(record.getURI(), record);
                    if (tracker != null)
                    {
                        tracker.setUri(record.getURI());
                    }
                }
            }
        }

        return _records;
    }

    private IFaceletTagRecord createTagRecord(final IFile file)
    {
        InputStream is = null;
        if (!file.isAccessible())
        {
            return null;
        }
        try
        {
            is = file.getContents();
            final TagModelLoader loader = new TagModelLoader(file.getFullPath()
                    .toFile().getCanonicalPath());
            loader.loadFromInputStream(is);
            final FaceletTaglib taglib = loader.getTaglib();
            // if no valid namespace, don't create a record.
            if (taglib != null && taglib.getNamespaceUri() != null && taglib.getNamespaceUri().trim().length()>0)
            {
                return _factory.createRecords(taglib);
            }
        } catch (final Exception e)
        {
            FaceletCorePlugin
                    .log(
                            "Loading web root taglibs for project: " + _project.getName(), e); //$NON-NLS-1$
        } finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                } catch (final IOException e)
                {
                    FaceletCorePlugin.log("Closing taglib.xml", e); //$NON-NLS-1$
                }
            }
        }
        return null;
    }

    class LibraryChangeHandler implements ILibraryChangeHandler
    {
        public void added(final IFile file)
        {
            final IFaceletTagRecord tagRecord = createTagRecord(file);
            TaglibFileTracker tracker = null;
            try
            {
                tracker = (TaglibFileTracker) _fileManager.getInstance(file);
            } catch (final ManagedObjectException e)
            {
                FaceletCorePlugin.log("Adding new library", e); //$NON-NLS-1$
            }

            if (tagRecord != null)
            {

                _records.put(tagRecord.getURI(), tagRecord);
                if (tracker != null)
                {
                    tracker.setUri(tagRecord.getURI());
                }

                fireChangeEvent(new TaglibChangedEvent(
                        ContextParamSpecifiedFaceletTaglibLocator.this, null,
                        tagRecord, CHANGE_TYPE.ADDED));
            }
        }

        public void removed(final String uri, final IFile file)
        {
            final IFaceletTagRecord tagRecord = _records.remove(uri);
            if (tagRecord != null)
            {
                fireChangeEvent(new TaglibChangedEvent(
                        ContextParamSpecifiedFaceletTaglibLocator.this, tagRecord,
                        null, CHANGE_TYPE.REMOVED));
            }
        }

        public void changed(final String uri, final IFile file)
        {
            final IFaceletTagRecord oldValue = _records.remove(uri);
            final IFaceletTagRecord newValue = createTagRecord(file);

            if (oldValue == null)
            {
                // no oldValue, is newValue so ADD
                if (newValue != null)
                {
                    _records.put(uri, newValue);
                    fireChangeEvent(new TaglibChangedEvent(
                            ContextParamSpecifiedFaceletTaglibLocator.this, null,
                            newValue, CHANGE_TYPE.ADDED));
                }
            }
            // if there is an old value
            else
            {
                // oldValue but no new value, so REMOVE
                if (newValue == null)
                {
                    fireChangeEvent(new TaglibChangedEvent(
                            ContextParamSpecifiedFaceletTaglibLocator.this, oldValue,
                            null, CHANGE_TYPE.REMOVED));
                    
                }
                // both old and new value, so a change of some kind
                else
                {
                    _records.put(uri, newValue);
                    // if the namespaces match, then it's a simple change
                    if (oldValue.getURI() != null && oldValue.getURI().equals(newValue.getURI()))
                    {
                        fireChangeEvent(new TaglibChangedEvent(
                                ContextParamSpecifiedFaceletTaglibLocator.this,
                                oldValue, newValue, CHANGE_TYPE.CHANGED));
                    }
                    // otherwise, it's a remove of old value and an add of new value
                    else
                    {
                        fireChangeEvent(new TaglibChangedEvent(
                                ContextParamSpecifiedFaceletTaglibLocator.this, oldValue,
                                null, CHANGE_TYPE.REMOVED));
                        fireChangeEvent(new TaglibChangedEvent(
                                ContextParamSpecifiedFaceletTaglibLocator.this, null,
                                newValue, CHANGE_TYPE.ADDED));
                    }
                }
            }
        }
    }
}
