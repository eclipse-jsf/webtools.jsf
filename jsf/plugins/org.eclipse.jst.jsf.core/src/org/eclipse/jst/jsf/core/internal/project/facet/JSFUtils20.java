package org.eclipse.jst.jsf.core.internal.project.facet;

import java.io.PrintWriter;

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.core.JSFVersion;

/**
 * JSF Utils instance for JSF 2.0.
 * 
 * @author cbateman
 * 
 */
/* package: use JSFUtilFactory */class JSFUtils20 extends JSFUtils12
{
    private static final String DEFAULT_DEFAULT_MAPPING_SUFFIX = "xhtml"; //$NON-NLS-1$

    /**
     * @param modelProvider
     */
    protected JSFUtils20(final IModelProvider modelProvider)
    {
        super(JSFVersion.V2_0, modelProvider);
    }

    @Override
    protected String getDefaultDefaultSuffix()
    {
        return DEFAULT_DEFAULT_MAPPING_SUFFIX;
    }

    @Override
    public void doVersionSpecificConfigFile(PrintWriter pw)
    {
        final String QUOTE = new String(new char[]
        { '"' });
        pw.write("<?xml version=" + QUOTE + "1.0" + QUOTE + " encoding=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + QUOTE + "UTF-8" + QUOTE + "?>\n\n"); //$NON-NLS-1$ //$NON-NLS-2$
        pw.write("<faces-config\n"); //$NON-NLS-1$
        pw.write("    " + "xmlns=" + QUOTE //$NON-NLS-1$ //$NON-NLS-2$
                + "http://java.sun.com/xml/ns/javaee" + QUOTE + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        pw.write("    " + "xmlns:xsi=" + QUOTE //$NON-NLS-1$ //$NON-NLS-2$
                + "http://www.w3.org/2001/XMLSchema-instance" + QUOTE //$NON-NLS-1$
                + "\n"); //$NON-NLS-1$
        pw.write("    " //$NON-NLS-1$
                + "xsi:schemaLocation=" //$NON-NLS-1$
                + QUOTE
                + "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-facesconfig_2_0.xsd" //$NON-NLS-1$
                + QUOTE + "\n"); //$NON-NLS-1$
        pw.write("    " + "version=" + QUOTE + "2.0" + QUOTE + ">\n\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        pw.write("</faces-config>\n"); //$NON-NLS-1$
    }

}
