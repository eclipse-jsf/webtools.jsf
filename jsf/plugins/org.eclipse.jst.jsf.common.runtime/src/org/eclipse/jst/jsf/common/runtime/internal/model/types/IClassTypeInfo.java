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
    
    /**
     * Should never return null.  Return empty array if none.
     * 
     * @return the list of fully-qualified super class names.  List is as 
     * calculated at IClassTypeInfo construction and no guarantee is made
     * (although an implementer may do so) that it will be updated if the
     * definition of className changes.
     */
    String[]  getSuperClasses();
    
    /**
     * Should never return null.  Return empty array if none.
     * 
     * @return the list of fully-qualified interface names.  List is as 
     * calculated at IClassTypeInfo construction and no guarantee is made
     * (although an implementer may do so) that it will be updated if the
     * definition of className changes.
     */
    String[]  getInterfaces();
    
    /**
     * @param checkType
     * @return true if checkType is in the set comprised of getSuperClasses()+getInterfaces()
     */
    boolean isInstanceOf(final String checkType);
}
