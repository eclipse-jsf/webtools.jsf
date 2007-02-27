package org.eclipse.jst.jsf.common.metadata.internal;

public interface IClassLoaderProvider {
	/**
	 * @param className
	 * @return Class - implementers should eat exceptions and return null whenever class cannot be returned
	 */
	public Class loadClass(String className);
}
