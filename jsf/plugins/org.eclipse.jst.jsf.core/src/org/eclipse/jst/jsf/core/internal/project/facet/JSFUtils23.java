package org.eclipse.jst.jsf.core.internal.project.facet;

import java.io.PrintWriter;

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.jsf.core.JSFVersion;

/**
 * JSF Utils instance for JSF 2.3.
 * 
 */
class JSFUtils23 extends JSFUtils22 {

	/**
	 * @param modelProvider
	 */
	protected JSFUtils23(final IModelProvider modelProvider) {
		super(JSFVersion.V2_3, modelProvider);
	}

	/**
	 * @param jsfVersion
	 * @param modelProvider
	 */
	protected JSFUtils23(final JSFVersion jsfVersion, final IModelProvider modelProvider) {
		super(jsfVersion, modelProvider);
		if (jsfVersion.compareTo(JSFVersion.V2_3) < 0) {
			throw new IllegalArgumentException("JSF Version must be at least 2.3"); //$NON-NLS-1$
		}
	}

	@Override
	public void doVersionSpecificConfigFile(PrintWriter pw) {
		final String QUOTE = new String(new char[] { '"' });
		final String schemaVersionString = getVersion().toString().replaceAll("\\.", "_"); //$NON-NLS-1$//$NON-NLS-2$
		pw.write("<?xml version=" + //$NON-NLS-1$
				QUOTE + "1.0" + QUOTE + //$NON-NLS-1$
				" encoding=" + //$NON-NLS-1$
				QUOTE + "UTF-8" + QUOTE + //$NON-NLS-1$
				"?>\n"); //$NON-NLS-1$
		pw.write("<faces-config\n"); //$NON-NLS-1$
		pw.write("    xmlns=" + //$NON-NLS-1$
				QUOTE + "http://xmlns.jcp.org/xml/ns/javaee" + QUOTE + //$NON-NLS-1$
				"\n"); //$NON-NLS-1$
		pw.write("    xmlns:xsi=" + //$NON-NLS-1$
				QUOTE + "http://www.w3.org/2001/XMLSchema-instance" + QUOTE + //$NON-NLS-1$
				"\n"); //$NON-NLS-1$
		pw.write("    xsi:schemaLocation=" + //$NON-NLS-1$
				QUOTE
				+ String.format(
						"http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_%s.xsd", //$NON-NLS-1$
						schemaVersionString)
				+ QUOTE + "\n"); //$NON-NLS-1$
		pw.write("    version=" + //$NON-NLS-1$
				QUOTE + getVersion().toString() + QUOTE + ">\n\n"); //$NON-NLS-1$
		pw.write("</faces-config>\n"); //$NON-NLS-1$
	}
}
