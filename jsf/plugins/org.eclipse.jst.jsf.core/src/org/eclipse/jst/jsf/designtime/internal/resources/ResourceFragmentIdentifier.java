package org.eclipse.jst.jsf.designtime.internal.resources;

import org.eclipse.core.runtime.IStatus;

/**
 * Represents a piece of a resource identifier.
 * 
 * [localePrefix/][libraryName/][libraryVersion/][resourceName][/resourceVersion]
 * 
 * @author cbateman
 *
 */
public abstract class ResourceFragmentIdentifier
{

    /**
     * @return true if validate().isOk
     */
    public boolean isValid()
    {
        return validate().isOK();
    }

    /**
     * @return a validation status for the current value of this identifier
     */
    public abstract IStatus validate();

    /**
     * @return the resource name or null if none.
     */
    public String getResourceName()
    {
        return null;
    }

    /**
     * @return the locale prefix or null if none.
     */
    public String getLocalePrefix()
    {
        return null;
    }

    /**
     * @return the library name or null if none.
     */
    public String getLibraryName()
    {
        return null;
    }

    /**
     * @return the library version or null if none.
     */
    public String getLibraryVersion()
    {
        return null;
    }

    /**
     * @return the resource version or null if none.
     */
    public String getResourceVersion()
    {
        return null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (super.equals(obj))
        {
            return true;
        }
    
        if (obj instanceof ResourceIdentifier)
        {
            String meToString = toString();
            String otherToString = obj.toString();
            return meToString != null && meToString.equals(otherToString);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        String toString = toString();
        return toString != null ? toString.hashCode() : 0;
    }

    
    @Override
    public String toString()
    {
        String toString = ""; //$NON-NLS-1$
        
        toString = append(toString, getLocalePrefix());
        toString = append(toString, getLibraryName());
        toString = append(toString, getLibraryVersion());
        toString = append(toString, getResourceName());
        toString = append(toString, getResourceVersion());
        return toString;
    }

    /**
     * @param appendStr
     * @param toAppend
     * @return a string with to toAppend appended to appendStr if it is not null.
     * If appendStr is not append, a '/' is added between the two concatenated strings.
     */
    protected String append(String appendStr, final String toAppend)
    {
        if (toAppend != null)
        {
            if (appendStr.length() > 0)
            {
                appendStr += "/"; //$NON-NLS-1$
            }
            appendStr += toAppend;
        }
        return appendStr;
    }
    /**
     * @return true if this identifier represents only a fragment of an identifier.
     */
    public boolean isFragment()
    {
        return true;
    }
}
