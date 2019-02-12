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
package org.eclipse.jst.jsf.test.util.mock.java;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;

public class MockClasspathEntry implements IClasspathEntry
{
    private final int _entryKind;
    private final IPath _path;

    public MockClasspathEntry(final int entryKind, final IPath path)
    {
        _entryKind = entryKind;
        _path = path;
        switch (entryKind)
        {
            case IClasspathEntry.CPE_CONTAINER:
            case IClasspathEntry.CPE_LIBRARY:
            case IClasspathEntry.CPE_PROJECT:
            case IClasspathEntry.CPE_SOURCE:
            case IClasspathEntry.CPE_VARIABLE:
                // do nothing
            break;
            default:
                throw new IllegalArgumentException("entryKind must be valid: "
                        + _entryKind);
        }
    }

    public boolean combineAccessRules()
    {
        throw new UnsupportedOperationException();
    }

    public IAccessRule[] getAccessRules()
    {
        throw new UnsupportedOperationException();
    }

    public int getContentKind()
    {
        throw new UnsupportedOperationException();
    }

    public int getEntryKind()
    {
        return _entryKind;
    }

    public IPath[] getExclusionPatterns()
    {
        throw new UnsupportedOperationException();
    }

    public IClasspathAttribute[] getExtraAttributes()
    {
        throw new UnsupportedOperationException();
    }

    public IPath[] getInclusionPatterns()
    {
        throw new UnsupportedOperationException();
    }

    public IPath getOutputLocation()
    {
        throw new UnsupportedOperationException();
    }

    public IPath getPath()
    {
        return _path;
    }

    public IPath getSourceAttachmentPath()
    {
        throw new UnsupportedOperationException();
    }

    public IPath getSourceAttachmentRootPath()
    {
        throw new UnsupportedOperationException();
    }

    public IClasspathEntry getReferencingEntry()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isExported()
    {
        throw new UnsupportedOperationException();
    }

    public IClasspathEntry getResolvedEntry()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString()
    {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(getPath());
        buffer.append('[');
        switch (getEntryKind())
        {
            case IClasspathEntry.CPE_LIBRARY:
                buffer.append("CPE_LIBRARY"); //$NON-NLS-1$
            break;
            case IClasspathEntry.CPE_PROJECT:
                buffer.append("CPE_PROJECT"); //$NON-NLS-1$
            break;
            case IClasspathEntry.CPE_SOURCE:
                buffer.append("CPE_SOURCE"); //$NON-NLS-1$
            break;
            case IClasspathEntry.CPE_VARIABLE:
                buffer.append("CPE_VARIABLE"); //$NON-NLS-1$
            break;
            case IClasspathEntry.CPE_CONTAINER:
                buffer.append("CPE_CONTAINER"); //$NON-NLS-1$
            break;
        }
        buffer.append("]"); //$NON-NLS-1$
        // switch (getContentKind())
        // {
        // case IPackageFragmentRoot.K_BINARY:
        //                buffer.append("K_BINARY"); //$NON-NLS-1$
        // break;
        // case IPackageFragmentRoot.K_SOURCE:
        //                buffer.append("K_SOURCE"); //$NON-NLS-1$
        // break;
        // case ClasspathEntry.K_OUTPUT:
        //                buffer.append("K_OUTPUT"); //$NON-NLS-1$
        // break;
        // }
        // buffer.append(']');
        // if (getSourceAttachmentPath() != null)
        // {
        //            buffer.append("[sourcePath:"); //$NON-NLS-1$
        // buffer.append(getSourceAttachmentPath());
        // buffer.append(']');
        // }
        // if (getSourceAttachmentRootPath() != null)
        // {
        //            buffer.append("[rootPath:"); //$NON-NLS-1$
        // buffer.append(getSourceAttachmentRootPath());
        // buffer.append(']');
        // }
        //        buffer.append("[isExported:"); //$NON-NLS-1$
        // buffer.append(this.isExported);
        // buffer.append(']');
        // IPath[] patterns = this.inclusionPatterns;
        // int length;
        // if ((length = patterns == null ? 0 : patterns.length) > 0)
        // {
        //            buffer.append("[including:"); //$NON-NLS-1$
        // for (int i = 0; i < length; i++)
        // {
        // buffer.append(patterns[i]);
        // if (i != length - 1)
        // {
        // buffer.append('|');
        // }
        // }
        // buffer.append(']');
        // }
        // patterns = this.exclusionPatterns;
        // if ((length = patterns == null ? 0 : patterns.length) > 0)
        // {
        //            buffer.append("[excluding:"); //$NON-NLS-1$
        // for (int i = 0; i < length; i++)
        // {
        // buffer.append(patterns[i]);
        // if (i != length - 1)
        // {
        // buffer.append('|');
        // }
        // }
        // buffer.append(']');
        // }
        // if (this.accessRuleSet != null)
        // {
        // buffer.append('[');
        // buffer.append(this.accessRuleSet.toString(false/* on one line */));
        // buffer.append(']');
        // }
        // if (this.entryKind == CPE_PROJECT)
        // {
        //            buffer.append("[combine access rules:"); //$NON-NLS-1$
        // buffer.append(this.combineAccessRules);
        // buffer.append(']');
        // }
        // if (getOutputLocation() != null)
        // {
        //            buffer.append("[output:"); //$NON-NLS-1$
        // buffer.append(getOutputLocation());
        // buffer.append(']');
        // }
        // if ((length = this.extraAttributes == null ? 0
        // : this.extraAttributes.length) > 0)
        // {
        //            buffer.append("[attributes:"); //$NON-NLS-1$
        // for (int i = 0; i < length; i++)
        // {
        // buffer.append(this.extraAttributes[i]);
        // if (i != length - 1)
        // {
        // buffer.append(',');
        // }
        // }
        // buffer.append(']');
        // }
        return buffer.toString();
    }

    /**
     * Returns true if the given object is a classpath entry with equivalent
     * attributes.
     */
    @Override
    public boolean equals(final Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (object instanceof MockClasspathEntry)
        {
            final MockClasspathEntry otherEntry = (MockClasspathEntry) object;
            // if (this.contentKind != otherEntry.getContentKind())
            // {
            // return false;
            // }
            if (this._entryKind != otherEntry.getEntryKind())
            {
                return false;
            }
            // if (this.isExported != otherEntry.isExported())
            // {
            // return false;
            // }
            if (!this._path.equals(otherEntry.getPath()))
            {
                return false;
            }
            // IPath otherPath = otherEntry.getSourceAttachmentPath();
            // if (this.sourceAttachmentPath == null) {
            // if (otherPath != null)
            // {
            // return false;
            // }
            // } else {
            // if (!this.sourceAttachmentPath.equals(otherPath))
            // {
            // return false;
            // }
            // }
            //
            // otherPath = otherEntry.getSourceAttachmentRootPath();
            // if (this.sourceAttachmentRootPath == null) {
            // if (otherPath != null)
            // {
            // return false;
            // }
            // } else {
            // if (!this.sourceAttachmentRootPath.equals(otherPath))
            // {
            // return false;
            // }
            // }
            //
            // if (!equalPatterns(this.inclusionPatterns,
            // otherEntry.getInclusionPatterns()))
            // {
            // return false;
            // }
            // if (!equalPatterns(this.exclusionPatterns,
            // otherEntry.getExclusionPatterns()))
            // {
            // return false;
            // }
            // final AccessRuleSet otherRuleSet = otherEntry.getAccessRuleSet();
            // if (getAccessRuleSet() != null) {
            // if (!getAccessRuleSet().equals(otherRuleSet))
            // {
            // return false;
            // }
            // } else if (otherRuleSet != null)
            // {
            // return false;
            // }
            // if (this.combineAccessRules != otherEntry.combineAccessRules())
            // {
            // return false;
            // }
            // otherPath = otherEntry.getOutputLocation();
            // if (this.specificOutputLocation == null) {
            // if (otherPath != null)
            // {
            // return false;
            // }
            // } else {
            // if (!this.specificOutputLocation.equals(otherPath))
            // {
            // return false;
            // }
            // }
            // if (!equalAttributes(this.extraAttributes,
            // otherEntry.getExtraAttributes()))
            // {
            // return false;
            // }
            return true;
        } else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return _path.hashCode();
    }
}
