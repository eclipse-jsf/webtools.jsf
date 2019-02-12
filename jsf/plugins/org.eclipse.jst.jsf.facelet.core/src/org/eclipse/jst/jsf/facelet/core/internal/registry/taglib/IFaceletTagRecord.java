/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.io.Serializable;
import java.util.Collection;
import java.util.EventObject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag;

/**
 * @author cbateman
 * 
 */
public interface IFaceletTagRecord extends Serializable
{
    /**
     * @return the uri of the tag library
     */
    String getURI();

    /**
     * This may be implemented differently than getTags().size(), since getting
     * all tags may be very expensive, while the overall number may be cheap.
     * 
     * However, it should always be true that getTags().size() == getNumTags()
     * 
     * @return the number of tags in this record.
     */
    int getNumTags();

    /**
     * @return the tag definitions
     */
    Collection<? extends FaceletTaglibTag> getTags();

    /**
     * @param name
     * @return the tag definition for name or null.
     */
    FaceletTaglibTag getTag(final String name);

    /**
     * @return the tag record descriptor for the record.
     */
    TagRecordDescriptor getDescriptor();

    /**
     * @param listener
     */
    void addListener(final ITagRecordChangeListener listener);

    /**
     * @param listener
     */
    void removeListener(final ITagRecordChangeListener listener);

    /**
     * Indicates that a tag record has changed
     */
    public static class TagRecordChangeEvent extends EventObject
    {
        /**
         * 
         */
        private static final long serialVersionUID = 5655356157624922019L;

        /**
         * @param source
         */
        public TagRecordChangeEvent(final IFaceletTagRecord source)
        {
            super(source);
        }
    }

    /**
     * A listener for tag record change events.
     * 
     */
    public interface ITagRecordChangeListener
    {
        /**
         * @param event
         */
        public void changed(final TagRecordChangeEvent event);
    }

    /**
     * Describes the source of a tag record in the filesystem and workspace.
     * 
     */
    public abstract static class TagRecordDescriptor
    {
        private final Source _source;

        /**
         * @param source
         */
        public TagRecordDescriptor(final Source source)
        {
            super();
            _source = source;
        }

        /**
         * @return the source type of the descriptor
         */
        public Source getSource()
        {
            return _source;
        }

        /**
         * The source of the tag record
         * 
         */
        public enum Source
        {
            /**
             * Tag record is defined in a file in the workspace. If this is the
             * source, then getResource() will never return null and will be of
             * type IFile.
             */
            WORKSPACE_FILE,
            /**
             * Tag record is defined in a folder in the workspace. If this is
             * the source, then getResource() will never return null and will be
             * of type IFolder.
             */
            WORKSPACE_FOLDER,
            /**
             * Tag record is defined in a jar file. If this is the source then
             * getResource() will return an IFile if the jar is in the workspace
             * and null otherwise.
             */
            JAR
        }

        /**
         * see Source for information on what this returns.
         * 
         * @return the workspace resource where the tag record is defined. or
         *         null if it is not in the workspace
         */
        public abstract IResource getResource();

        /**
         * @return the absolute path in the file system to the where the library
         *         is defined. If Source is WORKSPACE_FOLDER then this will
         *         point to a directory.
         * 
         *         Otherwise, it will point to a file.
         */
        public abstract IPath getPath();
    }

    /**
     * Describes a tag record defined in workspace.
     * 
     */
    public static class WorkspaceTagRecordDescriptor extends
            TagRecordDescriptor
    {
        private final IResource _resource;

        /**
         * @param file
         */
        public WorkspaceTagRecordDescriptor(final IFile file)
        {
            super(Source.WORKSPACE_FILE);
            _resource = file;
        }

        /**
         * @param folder
         */
        public WorkspaceTagRecordDescriptor(final IFolder folder)
        {
            super(Source.WORKSPACE_FOLDER);
            _resource = folder;
        }

        @Override
        public IResource getResource()
        {
            return _resource;
        }

        @Override
        public IPath getPath()
        {
            return _resource.getLocation();
        }
    }

    /**
     * Describes a tag record defined in a jar. The additional entryName
     * provides the jar entry where the actual tag record source file is found.
     * 
     */
    public static class JarTagRecordDescriptor extends TagRecordDescriptor
    {
        private final IResource _resourceJar;
        private final String _entryName;
        private final IPath _absPath;

        /**
         * @param resourceJar
         * @param entryName
         */
        public JarTagRecordDescriptor(final IResource resourceJar,
                final String entryName)
        {
            super(Source.JAR);
            _resourceJar = resourceJar;
            _entryName = entryName;
            _absPath = resourceJar.getLocation();
        }

        /**
         * @param absPath
         * @param entryName
         */
        public JarTagRecordDescriptor(final IPath absPath,
                final String entryName)
        {
            super(Source.JAR);
            _resourceJar = null;
            _absPath = absPath;
            _entryName = entryName;
        }

        @Override
        public IResource getResource()
        {
            return _resourceJar;
        }

        @Override
        public IPath getPath()
        {
            return _absPath;
        }

        /**
         * @return the entry name o
         */
        public final String getEntryName()
        {
            return _entryName;
        }
    }
}
