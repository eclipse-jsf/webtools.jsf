package org.eclipse.jst.jsf.core.internal.provisional.contentmodel.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * Used to supply an InputStream to the parser of an annotation 
 * meta-data file and the URL to resource bundle for a properties file, if any.
 * 
 * Implementors must provide a zero-argument constructor.
 *
 */
public abstract class CMSourceFileLocator{
	protected ICMAnnotationSourceFileInfo fileInfo;
	
	public final void setFileInfo(ICMAnnotationSourceFileInfo fileInfo){
		this.fileInfo = fileInfo;
	}
	
	protected final ICMAnnotationSourceFileInfo getFileInfo(){
		return fileInfo;
	}
	/**
	 * Return InputStream to the meta-data annotation.  Callers will be responsble for closing the stream.
	 * @return InputStream
	 * @throws IOException
	 */
	public abstract InputStream getAnnotationSourceInputStream() throws IOException;
	/**
	 * Return ResourceBundle for the property files if there are any.  Return null if not.
	 * 
	 * @return java.util.ResourceBundle
	 * @throws IOException
	 * @throws MissingResourceException
	 */
	public abstract ResourceBundle getResourceBundle() throws IOException, MissingResourceException;
}
