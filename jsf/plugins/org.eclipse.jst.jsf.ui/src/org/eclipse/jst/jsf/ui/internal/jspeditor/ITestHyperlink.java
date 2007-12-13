package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.jdt.core.IJavaElement;

/**
 * Test-only interface.  Not for normal use.
 *
 * @author cbateman
 *
 */
public interface ITestHyperlink {
	/**
	 * @return the java element for symbol2 or null if none.
	 */
	public IJavaElement determineJavaElement();
}
