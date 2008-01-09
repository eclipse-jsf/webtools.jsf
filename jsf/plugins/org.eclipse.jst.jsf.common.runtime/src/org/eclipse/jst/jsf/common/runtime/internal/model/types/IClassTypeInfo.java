package org.eclipse.jst.jsf.common.runtime.internal.model.types;

/**
 * A type info that has an associate Java class.
 * @author cbateman
 *
 */
public interface IClassTypeInfo
{
    /**
     * The fully qualified class name, i.e. java.lang.String
     * 
     * @return the fully qualified class name in dot notation
     * 
     */
    String getClassName();
}
