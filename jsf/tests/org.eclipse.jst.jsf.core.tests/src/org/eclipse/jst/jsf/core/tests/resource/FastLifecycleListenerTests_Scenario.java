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
package org.eclipse.jst.jsf.core.tests.resource;

import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.jst.jsf.common.internal.resource.LifecycleListener;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.EventType;
import org.eclipse.jst.jsf.common.internal.resource.ResourceLifecycleEvent.ReasonType;
import org.eclipse.jst.jsf.core.tests.resource.MyTestListener.EventData;
import org.eclipse.jst.jsf.test.util.junit4.DualModeEnvironment;
import org.eclipse.jst.jsf.test.util.junit4.WorkspaceContext;
import org.eclipse.jst.jsf.test.util.junit4.WorkspaceRunner;
import org.eclipse.jst.jsf.test.util.mock.IWorkspaceContextWithEvents;
import org.eclipse.jst.jsf.test.util.mock.MockResourceChangeEventFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(WorkspaceRunner.class)
@Category(DualModeEnvironment.class)
public class FastLifecycleListenerTests_Scenario
{
    @WorkspaceContext
    private IWorkspaceContextWithEvents _wsContext;
    private MockResourceChangeEventFactory _factory;
    private IProject _project;
    private IFile _file;
    private IFolder _folderInProject;
    private IFolder _folderInFolder;

    @Before
    public void setUp() throws Exception
    {
//        _wsContext = new MockWorkspaceContext();
        _project = _wsContext.createProject("SomeTestProject");
        _folderInProject = _project.getFolder("/folder");
        _folderInFolder = _project.getFolder("/folder/folderInFolder");
        _file = _project.getFile("/folder/myfile.txt");
        _factory = new MockResourceChangeEventFactory(_wsContext);
    }

    /**
     * Add a new file to a folder.
     */
    @Test
    public void testAddFile()
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        final MyTestListener tester = new MyTestListener(_wsContext, listener);
        final IResourceChangeEvent event = _factory
                .createSimpleFileAdded(_file);
        // we shouldn't expect an event because there are no resources
        // registered
        tester.fireAndExpectNull(event);

        listener.addResource(_file);
        tester.fireAndExpect(event, _file,
        // IMPORTANT: ReasonType is RESOURCE_ADDED because _file is listened for
                EventType.RESOURCE_ADDED, ReasonType.RESOURCE_ADDED);
        listener.removeResource(_file);

        listener.addResource(_file.getParent());
        tester.fireAndExpect(
                event,
                _file,
                // IMPORTANT: ReasonType is RESOURCE_ADDED_TO_CONTAINTER because
                // _file's
                // parent is being listened for.
                EventType.RESOURCE_ADDED,
                ReasonType.RESOURCE_ADDED_TO_CONTAINER);

        // now remove and verify we no longer get the event.
        listener.removeListener(tester);
        tester.fireAndExpectNull(event);
    }

    /**
     * Remove a file from a folder.
     */
    @Test
    public void testRemoveFile()
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        final MyTestListener tester = new MyTestListener(_wsContext, listener);
        final IResourceChangeEvent event = _factory
                .createSimpleFileRemove(_file);
        // we shouldn't expect an event because there are no resources
        // registered
        tester.fireAndExpectNull(event);

        listener.addResource(_file);
        tester.fireAndExpect(event, _file,
        // IMPORTANT: ReasonType is RESOURCE_DELETED because _file is listened
        // for
                EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_DELETED);
        listener.removeResource(_file);

        listener.addResource(_file.getParent());
        tester.fireAndExpect(
                event,
                _file,
                // IMPORTANT: ReasonType is RESOURCE_DELETED_FROM_CONTAINER
                // because
                // _file's parent is being listened for.
                EventType.RESOURCE_INACCESSIBLE,
                ReasonType.RESOURCE_DELETED_FROM_CONTAINER);

        // now remove and verify we no longer get the event.
        listener.removeListener(tester);
        tester.fireAndExpectNull(event);
    }

    /**
     * Add a new folder.
     */
    @Test
    public void testAddFolderToProject()
    {
        assertEquals(_project, _folderInProject.getParent());
        verifyAddFolderTo(_folderInProject);
    }

    /**
     * Add a new folder to a folder.
     */
    @Test
    public void testAddFolderToFolder()
    {
        verifyAddFolderTo(_folderInFolder);
    }

    public void verifyAddFolderTo(final IFolder folder)
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        final MyTestListener tester = new MyTestListener(_wsContext, listener);
        final IResourceChangeEvent event = _factory
                .createSimpleFolderAdded(folder);
        // we shouldn't expect an event because there are no resources
        // registered
        tester.fireAndExpectNull(event);

        listener.addResource(folder);
        tester.fireAndExpect(event, folder,
        // IMPORTANT: ReasonType is RESOURCE_ADDED because folder is listened
        // for
                EventType.RESOURCE_ADDED, ReasonType.RESOURCE_ADDED);
        listener.removeResource(folder);

        listener.addResource(folder.getParent());
        tester.fireAndExpect(event,
                folder,
                // IMPORTANT: ReasonType is RESOURCE_ADDED_TO_CONTAINER because
                // folder's parent is being listened for.
                EventType.RESOURCE_ADDED,
                ReasonType.RESOURCE_ADDED_TO_CONTAINER);

        // now remove and verify we no longer get the event.
        listener.removeListener(tester);
        tester.fireAndExpectNull(event);
    }

    @Test
    public void testRemoveFolderFromProject()
    {
        verifyRemoveFolderFrom(_folderInProject);
    }

    @Test
    public void testRemoveFolderFromFolder()
    {
        verifyRemoveFolderFrom(_folderInFolder);
    }

    public void verifyRemoveFolderFrom(final IFolder folder)
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        final MyTestListener tester = new MyTestListener(_wsContext, listener);
        final IResourceChangeEvent event = _factory
                .createSimpleFolderDeleted(folder);
        // we shouldn't expect an event because there are no resources
        // registered
        tester.fireAndExpectNull(event);

        listener.addResource(folder);
        tester.fireAndExpect(event, folder,
        // IMPORTANT: ReasonType is RESOURCE_DELETED because folder is listened
        // for
                EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_DELETED);
        listener.removeResource(folder);

        listener.addResource(folder.getParent());
        tester.fireAndExpect(
                event,
                folder,
                // IMPORTANT: ReasonType is RESOURCE_DELETED_FROM_CONTAINER
                // because
                // folder's parent is being listened for.
                EventType.RESOURCE_INACCESSIBLE,
                ReasonType.RESOURCE_DELETED_FROM_CONTAINER);

        // now remove and verify we no longer get the event.
        listener.removeListener(tester);
        tester.fireAndExpectNull(event);
    }

    @Test
    public void testRenameFolderInProject()
    {
        verifyRenameFolder(_folderInProject);
    }

    @Test
    public void testRenameFolderInFolder()
    {
        verifyRenameFolder(_folderInFolder);
    }

    private void verifyRenameFolder(final IFolder folder)
    {
        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        final MyTestListener tester = new MyTestListener(_wsContext, listener);
        final IFolder newFolder = _project.getFolder(folder
                .getProjectRelativePath().removeLastSegments(1).append(
                        "NewFolderName"));
        final IResourceChangeEvent event = _factory.createSimpleFolderRename(
                folder, newFolder);
        // we shouldn't expect an event because there are no resources
        // registered
        tester.fireAndExpectNull(event);
        EventData oldFolderChange = new EventData(folder,
                EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_MOVED);
        listener.addResource(folder);
        tester.fireAndExpect(event, Collections.singletonList(oldFolderChange));
        listener.removeResource(folder);

        List<EventData> eventData = new ArrayList<MyTestListener.EventData>();
        eventData.add(new EventData(folder,
                EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_MOVED_CONTAINER));
        eventData.add(new EventData(newFolder, EventType.RESOURCE_ADDED,
                ReasonType.RESOURCE_MOVED_CONTAINER));
        listener.addResource(folder.getParent());
        tester.fireAndExpect(event,
                eventData);

        // now remove and verify we no longer get the event.
        listener.removeListener(tester);
        tester.fireAndExpectNull(event);
    }
    
    @Test
    public void testRenameFile()
    {
        verifyRenameFile(_file);
    }

    private void verifyRenameFile(final IFile file)
    {
        List<EventData> eventData = new ArrayList<MyTestListener.EventData>();

        final LifecycleListener listener = new LifecycleListener(_wsContext
                .getWorkspace());
        final MyTestListener tester = new MyTestListener(_wsContext, listener);
        final IFile newFile = _project.getFile(file
                .getProjectRelativePath().removeLastSegments(1).append(
                        "NewFileName"));
        final IResourceChangeEvent event = _factory.createSimpleFileRename(
                file, newFile);
        // we shouldn't expect an event because there are no resources
        // registered
        tester.fireAndExpectNull(event);
        EventData oldFileChange = new EventData(file,
                EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_MOVED);
        listener.addResource(file);
        eventData.add(oldFileChange);
        tester.fireAndExpect(event, eventData);
        listener.addResource(newFile);
        eventData.add(new EventData(newFile,
                EventType.RESOURCE_ADDED, ReasonType.RESOURCE_MOVED));
        tester.fireAndExpect(event, eventData);
        listener.removeResource(file);
        listener.removeResource(newFile);
        eventData.clear();

        eventData.add(new EventData(file,
                EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_MOVED_CONTAINER));
        eventData.add(new EventData(newFile, EventType.RESOURCE_ADDED,
                ReasonType.RESOURCE_MOVED_CONTAINER));
        listener.addResource(file.getParent());
        tester.fireAndExpect(event,
                eventData);

        // now remove and verify we no longer get the event.
        listener.removeListener(tester);
        tester.fireAndExpectNull(event);
    }

//    /**
//     * Move a folder from one place to another
//     */
//    @Test
//    public void testMoveFolderIn()
//    {
//        final LifecycleListener listener = new LifecycleListener(_wsContext
//                .getWorkspace());
//        final MyTestListener tester = new MyTestListener(_wsContext, listener);
//        final IFolder newFolder = _project.getFolder("/someOtherFolder");
//        final IResourceChangeEvent event = _factory.createSimpleFolderRename(
//                _folderInFolder, newFolder);
//        // we shouldn't expect an event because there are no resources
//        // registered
//        tester.fireAndExpectNull(event);
//        EventData oldFolderChange = new EventData(_folderInFolder,
//                EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_MOVED);
//        listener.addResource(_folderInFolder);
//        tester.fireAndExpect(event, Collections.singletonList(oldFolderChange));
//        listener.removeResource(_folderInFolder);
//
//        List<EventData> eventData = new ArrayList<MyTestListener.EventData>();
//        eventData.add(new EventData(_folderInFolder,
//                EventType.RESOURCE_INACCESSIBLE, ReasonType.RESOURCE_MOVED_CONTAINER));
//        eventData.add(new EventData(newFolder, EventType.RESOURCE_ADDED,
//                ReasonType.RESOURCE_MOVED_CONTAINER));
//        listener.addResource(_folderInFolder.getParent());
//        tester.fireAndExpect(event,
//                eventData);
//
//        // now remove and verify we no longer get the event.
//        listener.removeListener(tester);
//        tester.fireAndExpectNull(event);
//    }
}
