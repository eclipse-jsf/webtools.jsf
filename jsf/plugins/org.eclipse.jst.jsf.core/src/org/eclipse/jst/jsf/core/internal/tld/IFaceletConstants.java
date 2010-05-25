package org.eclipse.jst.jsf.core.internal.tld;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Adds facelet-specific constants.*
 * 
 * @author cbateman
 * 
 */
public final class IFaceletConstants implements ITLDConstants
{
    /**
     * The set of all standard Facelet tag libraries.
     */
    public static final Set<String> ALL_FACELET_TAGLIBS;
    /**
     * The uri for the composite taglib (Facelets only)
     */
    public static final String URI_JSF_COMPOSITE = "http://java.sun.com/jsf/composite"; //$NON-NLS-1$
    /**
     * The uri for the ui taglib (Facelets only)
     */
    public static final String URI_JSF_UI = "http://java.sun.com/jsf/facelets"; //$NON-NLS-1$
    /**
     * The uri for the jsf implementation of JSTL core tags (Facelets only)
     */
    public static final String URI_JSF_JSTL_CORE = "http://java.sun.com/jsp/jstl/core"; //$NON-NLS-1$
    /**
     * The uri for the jsf implementation of JSTL function tags (Facelets only)
     */
    public static final String URI_JSF_JSTL_FUNCTIONS = "http://java.sun.com/jsp/jstl/functions"; //$NON-NLS-1$
    
    static
    {
        Set<String>  taglibs = new HashSet<String>();
        taglibs.add(URI_JSF_HTML);
        taglibs.add(URI_JSF_CORE);
        taglibs.add(URI_JSF_COMPOSITE);
        taglibs.add(URI_JSF_UI);
        taglibs.add(URI_JSF_JSTL_CORE);
        taglibs.add(URI_JSF_JSTL_FUNCTIONS);
        
        ALL_FACELET_TAGLIBS = Collections.unmodifiableSet(taglibs);
    }

}
