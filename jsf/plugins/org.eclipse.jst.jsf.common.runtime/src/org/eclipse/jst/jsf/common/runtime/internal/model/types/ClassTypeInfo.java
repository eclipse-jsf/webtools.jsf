package org.eclipse.jst.jsf.common.runtime.internal.model.types;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A type info that has class information.
 * 
 * @author cbateman
 *
 */
public abstract class ClassTypeInfo extends TypeInfo implements IClassTypeInfo
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = -5046230589012357680L;

    /**
     * the fully qualified class name of the implementation class for this
     * type.
     */
    protected final String _className;

    /**
     * known super classes of the component class
     */
    protected final String[] _superClasses;
    
    /**
     * known interfaces implemented by the component class
     */
    protected final String[] _interfaces;
    
    /**
     * used internally by isInstanceOf
     */
    private Set            _superTypes; // may be lazily constructed
    

    /**
     * @param className 
     * @param superClasses
     * @param interfaces
     */
    protected ClassTypeInfo(final String className, final String[] superClasses, final String[] interfaces)
    {
        _className = className;
        _superClasses = superClasses != null ? superClasses : new String[0];
        _interfaces = interfaces != null ? interfaces : new String[0];
        
        if (_superClasses.length + _interfaces.length == 0)
        {
            // there are no super-types known
            _superTypes = Collections.EMPTY_SET;
        }
    }
    
    public final String[] getInterfaces()
    {
        if (_interfaces.length > 0)
        {
            final String[]  copy =  new String[_interfaces.length];
            System.arraycopy(_interfaces, 0, copy, 0, _interfaces.length);
            return copy;
        }
        return new String[0];
    }

    public final String[] getSuperClasses()
    {
        if (_superClasses.length > 0)
        {
            final String[]  copy =  new String[_superClasses.length];
            System.arraycopy(_superClasses, 0, copy, 0, _superClasses.length);
            return copy;
        }
        return new String[0];
    }

    public final boolean isInstanceOf(String checkType)
    {
        if (_superTypes == null)
        {
            _superTypes = new HashSet();
            _superTypes.addAll(Arrays.asList(_superClasses));
            _superTypes.addAll(Arrays.asList(_interfaces));
        }
        
        return _superTypes.contains(checkType);
    }

    public final String getClassName()
    {
        return _className;
    }

    public String toString()
    {
        String supers = "[";
        
        for (int i = 0; i < _superClasses.length; i++)
        {
            supers += _superClasses[i];
            
            if (i < _superClasses.length-1)
            {
                supers += ",";
            }
        }
        
        for (int i = 0; i < _interfaces.length; i++)
        {
            supers += _interfaces[i];
            
            if (i < _interfaces.length-1)
            {
                supers += ",";
            }
        }
        supers +="]";
        
        return "class= "+getClassName()+ ", supers="+ supers;
    }
}
