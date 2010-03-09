package org.eclipse.jst.pagedesigner.editors.palette;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.context.IModelContext;

/**
 * Context in which the WPE palette is operating
 *
 */
public interface IPaletteContext extends IModelContext {
	/**
	 * @return IFile
	 */
	public IFile getFile();
}
