/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.test.util.mock;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeSettings;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;

public class MockContentType implements IContentType
{
    final static int SPEC_PRE_DEFINED = IGNORE_PRE_DEFINED;
    final static int SPEC_USER_DEFINED = IGNORE_USER_DEFINED;
    final static byte ASSOCIATED_BY_EXTENSION = 2;
    final static byte ASSOCIATED_BY_NAME = 1;
    final static byte NOT_ASSOCIATED = 0;

    private CopyOnWriteArrayList<FileSpec> fileSpecs = new CopyOnWriteArrayList<FileSpec>();

    private final IScopeContext _context = new InstanceScope();

    private final String _id;
    private boolean _builtInAssociations;

    public MockContentType(String id)
    {
        _id = id;
    }

    public void addFileSpec(String fileSpec, int type) throws CoreException
    {
        Assert.isLegal(type == FILE_EXTENSION_SPEC || type == FILE_NAME_SPEC,
                "Unknown type: " + type); //$NON-NLS-1$
//        String[] userSet;
        synchronized (this)
        {
            if (!internalAddFileSpec(fileSpec, type | SPEC_USER_DEFINED))
                return;
//            userSet = getFileSpecs(type | IGNORE_PRE_DEFINED);
        }
        // persist using preferences
//        Preferences contentTypeNode = manager.getPreferences().node(id);
//        String newValue = Util.toListString(userSet);
        // we are adding stuff, newValue must be non-null
//        Assert.isNotNull(newValue);
//        setPreference(contentTypeNode, getPreferenceKey(type), newValue);
//        TRY
//        {
//            CONTENTTYPENODE.FLUSH();
//        }
//        CATCH (BACKINGSTOREEXCEPTION BSE)
//        {
//            STRING MESSAGE = NLS.BIND(
//                    CONTENTMESSAGES.CONTENT_ERRORSAVINGSETTINGS, ID);
//            ISTATUS STATUS = NEW STATUS(ISTATUS.ERROR,
//                    CONTENTMESSAGES.OWNER_NAME, 0, MESSAGE, BSE);
//            THROW NEW COREEXCEPTION(STATUS);
//        }
        // notify listeners
//        manager.fireContentTypeChangeEvent(this);
    }

    /**
     * Adds a user-defined or pre-defined file spec.
     */
    boolean internalAddFileSpec(String fileSpec, int typeMask)
    {
        if (hasFileSpec(fileSpec, typeMask, false))
            return false;
        FileSpec newFileSpec = createFileSpec(fileSpec, typeMask);
        if ((typeMask & SPEC_USER_DEFINED) == 0)
        {
            // plug-in defined - all that is left to be done is to add it to the
            // list
            if (fileSpecs.isEmpty())
                fileSpecs = new CopyOnWriteArrayList<FileSpec>();
            fileSpecs.add(newFileSpec);
            return true;
        }
        return true;
    }

    public void removeFileSpec(final String fileSpec, final int type)
            throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public void setDefaultCharset(final String userCharset)
            throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public IContentType getBaseType()
    {
        throw new UnsupportedOperationException();
    }

    public IContentDescription getDefaultDescription()
    {
        throw new UnsupportedOperationException();
    }

    public IContentDescription getDescriptionFor(final InputStream contents,
            final QualifiedName[] options) throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public IContentDescription getDescriptionFor(final Reader contents,
            final QualifiedName[] options) throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public String getDefaultCharset()
    {
        return "UTF-8";
    }

    public String[] getFileSpecs(final int type)
    {
        throw new UnsupportedOperationException();
    }

    public String getId()
    {
        return _id;
    }

    public String getName()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isAssociatedWith(final String fileName)
    {
        return internalIsAssociatedWith(fileName, _context) != NOT_ASSOCIATED;
    }

    private byte internalIsAssociatedWith(final String fileName, IScopeContext context)
    {
        if (hasFileSpec(context, fileName, FILE_NAME_SPEC))
            return ASSOCIATED_BY_NAME;
        String fileExtension = getFileExtension(fileName);
        if (hasFileSpec(context, fileExtension, FILE_EXTENSION_SPEC))
            return ASSOCIATED_BY_EXTENSION;
        // if does not have built-in file specs, delegate to parent (if any)
        if (!hasBuiltInAssociations() /*&& baseType != null*/)
        {
//            return baseType.internalIsAssociatedWith(fileName, context);
        }
        return NOT_ASSOCIATED;
    }
    private boolean hasBuiltInAssociations()
    {
        return _builtInAssociations;
    }
    /*
     * Returns the extension for a file name (omitting the leading '.').
     */
    static String getFileExtension(String fileName) {
        int dotPosition = fileName.lastIndexOf('.');
        return (dotPosition == -1 || dotPosition == fileName.length() - 1) ? "" : fileName.substring(dotPosition + 1); //$NON-NLS-1$
    }

    private boolean hasFileSpec(IScopeContext context, String text, int typeMask)
    {
        if (context.equals(_context)
                || (typeMask & IGNORE_USER_DEFINED) != 0)
            return hasFileSpec(text, typeMask, false);
//        String[] fileSpecs = ContentTypeSettings.getFileSpecs(context, _id,
//                typeMask);
//        for (int i = 0; i < fileSpecs.length; i++)
//            if (text.equalsIgnoreCase(fileSpecs[i]))
//                return true;
        // no user defined association... try built-in
        return hasFileSpec(text, typeMask | IGNORE_PRE_DEFINED, false);
    }

    /**
     * Returns whether this content type has the given file spec.
     * 
     * @param text
     *            the file spec string
     * @param typeMask
     *            FILE_NAME_SPEC or FILE_EXTENSION_SPEC
     * @param strict
     * @return true if this file spec has already been added, false otherwise
     */
    private boolean hasFileSpec(String text, int typeMask, boolean strict)
    {
        if (fileSpecs.isEmpty())
            return false;
        for (Iterator<FileSpec> i = fileSpecs.iterator(); i.hasNext();)
        {
            FileSpec spec = (FileSpec) i.next();
            if (spec.equals(text, typeMask, strict))
                return true;
        }
        return false;
    }

    public boolean isAssociatedWith(final String fileName,
            final IScopeContext context)
    {
        // ignore scope context
        return isAssociatedWith(fileName);
    }

    public boolean isKindOf(final IContentType another)
    {
        throw new UnsupportedOperationException();

    }

    public IContentTypeSettings getSettings(final IScopeContext context)
            throws CoreException
    {
        throw new UnsupportedOperationException();
    }

    public static IContentType createContentType(/*ContentTypeCatalog catalog,*/
            String uniqueId, /*String name, byte priority,*/
            String[] fileExtensions, String[] fileNames/*, String baseTypeId,
            String aliasTargetId, Map defaultProperties,
            IConfigurationElement contentTypeElement*/)
    {
        MockContentType contentType = new MockContentType(uniqueId);
        // ContentType contentType = new ContentType(catalog.getManager());
        // contentType.catalog = catalog;
        // contentType.defaultDescription = new DefaultDescription(contentType);
        // contentType.name = name;
        // contentType.priority = priority;
        if ((fileExtensions != null && fileExtensions.length > 0)
                || (fileNames != null && fileNames.length > 0))
        {
            contentType._builtInAssociations = true;
            contentType.fileSpecs = new CopyOnWriteArrayList<FileSpec>();
            for (int i = 0; i < fileNames.length; i++)
            {
                contentType.internalAddFileSpec(fileNames[i], FILE_NAME_SPEC
                        | SPEC_PRE_DEFINED);
            }
            for (int i = 0; i < fileExtensions.length; i++)
            {
                contentType.internalAddFileSpec(fileExtensions[i],
                        FILE_EXTENSION_SPEC | SPEC_PRE_DEFINED);
            }
        }
        // contentType.defaultProperties = defaultProperties;
        // contentType.contentTypeElement = contentTypeElement;
        // contentType.baseTypeId = baseTypeId;
        // contentType.aliasTargetId = aliasTargetId;
        return contentType;
    }
    static FileSpec createFileSpec(String fileSpec, int type) {
        return new FileSpec(fileSpec, type);
    }

	public boolean isUserDefined()
	{
		throw new UnsupportedOperationException();
	}

}
