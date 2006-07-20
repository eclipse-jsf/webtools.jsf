package org.eclipse.jst.jsf.core.internal.provisional.util;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Represents a single bean property backed by JDT data
 * 
 * @author cbateman
 *
 */
public class JDTBeanProperty 
{
	/**
	 * the IMethod for the accessor  (either is or get)
	 */
	private IMethod   _getter;
	
	/**
	 * the IMethod for a "set" accessor method
	 */
	private IMethod   _setter;

	/**
	 * The IType that this property belongs to
	 */
	protected final IType    _type;
    
    /**
     * @param type
     */
    protected JDTBeanProperty(IType type)
    {
        _type = type;
    }

    /**
	 * @return true if this property is readable
	 */
	public boolean isReadable()
	{
		return  _getter != null;
	}
	
	/**
	 * @return true if this property is writable
	 */
	public boolean isWritable()
	{
		return _setter != null;
	}
	
	
	/**
	 * @return the get accessor IMethod or null if none
	 */
	public IMethod getGetter() {
		return _getter;
	}

	
	
	/**
	 * Set the get accessor IMethod
	 * @param getter -- maybe null to indicate none
	 */
	void setGetter(IMethod getter) {
		_getter = getter;
	}


	/**
	 * @return the set mutator IMethod or null if none
	 */
	public IMethod getSetter() {
		return _setter;
	}

	/**
	 * @param setter
	 */
	void setSetter(IMethod setter) {
		_setter = setter;
	}
	
    /**
     * @return the IType for this property's type or null if it
     * cannot determined.  Note that null does not necessarily indicate an error
     * since some types like arrays of things do not have corresponding JDT IType's
     */
    public IType getType()
    {
        final String typeSignature = getTypeSignature();
        return TypeUtil.resolveType(_type, typeSignature);
    }
	
	/**
	 * @return the fully resolved (if possible) type signature for
     * the property or null if unable to determine
	 */
	public String getTypeSignature()
    {
        try
        {
            String unResolvedSig = getUnresolvedType();
            final String signature = TypeUtil.resolveTypeSignature(_type, unResolvedSig);
            return signature;
        }
        catch (JavaModelException jme)
        {
            JSFCorePlugin.log(jme, "Error resolving bean property type signature");
            return null;
        }
    }

    private String getUnresolvedType() throws JavaModelException
    {
        String   typeSig = null;
        
        // first decide which method to use; getter always gets precendence
        if (_getter != null)
        {
            typeSig = _getter.getReturnType();
        }
        // TODO: if no getter or setter could we have been created?
        // use setter
        else
        {
            typeSig = _setter.getParameterTypes()[0];
        }
        
        return typeSig;
    }
}
