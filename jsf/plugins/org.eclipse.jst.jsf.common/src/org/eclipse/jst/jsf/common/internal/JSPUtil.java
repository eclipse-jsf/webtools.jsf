package org.eclipse.jst.jsf.common.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;

/**
 * Utility JSP methods
 * @author cbateman
 *
 */
public final class JSPUtil 
{
    private final static String  CTYPE_JSPSOURCE = 
        "org.eclipse.jst.jsp.core.jspsource"; //$NON-NLS-1$
    private final static String  CTYPE_JSPFRAGMENTSOURCE = 
        "org.eclipse.jst.jsp.core.jspfragmentsource"; //$NON-NLS-1$
    /**
     * @param contentType
     * @return true if contentType is one of the content types registered
     * for JSP files
     */
    public static boolean isJSPContentType(final String contentType)
    {
        return CTYPE_JSPSOURCE.equals(contentType)
                    || CTYPE_JSPFRAGMENTSOURCE.equals(contentType);
    }
    
    /**
     * @param file
     * @return true if file is associated with a JSP or JSP fragment content type
     */
    public static boolean isJSPContentType(final IFile file)
    {
        final IContentTypeManager typeManager = Platform.getContentTypeManager();
        IContentType jspContentType = 
            typeManager.getContentType(CTYPE_JSPSOURCE);
        if (jspContentType != null
                && jspContentType.isAssociatedWith(file.getName()))
        {
            return true;
        }

        jspContentType = 
            typeManager.getContentType(CTYPE_JSPFRAGMENTSOURCE); 
        
        // otherwise check if fragment
        if (jspContentType != null
                && jspContentType.isAssociatedWith(file.getName()))
        {
            return true;
        }
        
        return false;
    }
    
    private JSPUtil()
    {
        // no instantiation
    }
}
