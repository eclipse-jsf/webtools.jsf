package org.eclipse.jst.jsf.designtime.internal.resources;

import java.util.regex.Pattern;

/**
 * Creates a resource identifier from a string.  Because id strings are ambiguous,
 * the caller must used specific methods to resolve the ambiguity.
 * 
 * @author cbateman
 *
 */
public class ResourceIdentifierFactory
{
    /**
     * The regular expression for version matching
     */
    public static final String VersionRegex = "[0-9]+(_[0-9]+)*"; //$NON-NLS-1$
    /**
     * A precompiled regex pattern for matching resource version.
     */
    public static final Pattern VersionPattern = Pattern.compile(VersionRegex);

    /**
     * @param idString 
     * @return a LibraryResourceIdentifier based on the string.  The call
     * assumes that the identifier contains a library name.  If the idString
     * can't be resolved for correctness, InvalidIdentifierException will be thrown.
     * @throws InvalidIdentifierException 
     */
    public ResourceIdentifier createLibraryResource(final String idString) throws InvalidIdentifierException
    {
        final String[] parts = idString.split("/"); //$NON-NLS-1$
        String resName = null;
        String libraryName = null;
        switch(parts.length)
        {
            case 1:
                // The whole string is the resource name.  There is no library name.
                resName = parts[0];
                break;
            case 2:
                // the first part is the libraryName and the second is the res name
                // TODO: this is ambiguous with locale/resourceName
                resName = parts[1];
                libraryName = parts[0];
                break;
            case 3:
                // is it of form libraryName/libraryVersion/resourceName?
                if (VersionPattern.matcher(parts[1]).matches())
                {
                    resName = parts[2];
                    libraryName = parts[0];
                    break;
                }
                // if no, then this is invalid
                throw new InvalidIdentifierException(idString);
            case 4:
                // is it of the form locale/libraryName/libraryVersion/resourceName
                if (VersionPattern.matcher(parts[2]).matches())
                {
                    resName = parts[3];
                    libraryName = parts[1];
                    break;
                }
                // no? then is it libraryName/libraryVersion/resourceName/resourceVersion
                else if (VersionPattern.matcher(parts[1]).matches())
                {
                    resName = parts[2];
                    libraryName = parts[0];
                    break;
                }
                // no? then assume it is locale/libraryName/resourceName/resourceVersion
                else
                {
                    resName = parts[2];
                    libraryName = parts[1];
                    break;
                }
            case 5:
                // this is the full one: locale/libraryName/libraryVersion/resourceName/resourceVersion
                resName = parts[3];
                libraryName = parts[1];
            break;
        }
        
        if (resName == null)
        {
            throw new InvalidIdentifierException(idString);
        }
        
        return new LibraryResourceIdentifier(resName, libraryName);
    }

    /**
     * @param libraryName
     * @return a new resource fragment identifier for a library folder.
     */
    public ResourceFragmentIdentifier createLibraryFragment(final String libraryName)
    {
        return new LibraryResourceFragmentIdentifier(libraryName);
    }

    /**
     * Indicates an invalid id.
     * 
     * @author cbateman
     *
     */
    public static class InvalidIdentifierException extends Exception
    {
        
        private final String _id;

        /**
         * @param id
         */
        public InvalidIdentifierException(final String id)
        {
            super();
            _id = id;
       }

        /**
         * @param message
         * @param cause
         * @param id
         */
        public InvalidIdentifierException(final String message, final Throwable cause, final String id)
        {
            super(message, cause);
            _id = id;
        }

        /**
         * @param message
         * @param id
         */
        public InvalidIdentifierException(final String message, final String id)
        {
            super(message);
            _id = id;
        }

        /**
         * @param cause
         * @param id
         */
        public InvalidIdentifierException(final Throwable cause, final String id)
        {
            super(cause);
            _id = id;
        }

        /**
         * @return the id that was found to be invalid.
         */
        public final String getId()
        {
            return _id;
        }

        /**
         * 
         */
        private static final long serialVersionUID = -8926293207447823901L;
    }
}
