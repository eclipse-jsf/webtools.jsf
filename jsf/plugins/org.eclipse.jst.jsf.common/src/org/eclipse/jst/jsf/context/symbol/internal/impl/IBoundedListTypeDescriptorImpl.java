/**
 * <copyright>
 * </copyright>
 *
 * $Id: IBoundedListTypeDescriptorImpl.java,v 1.3 2007/04/26 00:08:52 cbateman Exp $
 */
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.context.symbol.IBoundedListTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IBounded List Type Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class IBoundedListTypeDescriptorImpl extends IListTypeDescriptorImpl implements IBoundedListTypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("hiding")
    public static final String copyright = "Copyright 2006 Oracle";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IBoundedListTypeDescriptorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SymbolPackage.Literals.IBOUNDED_LIST_TYPE_DESCRIPTOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean isUnboundedForType(String typeSignature) 
    {
        // type signature must be a boxed integer
        // TODO: at this level, do we need to deal with coercion to
        // other integer types?  list.get() takes an integer...
        return typeSignature != null && TypeConstants.TYPE_BOXED_INTEGER.equals(typeSignature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public ISymbol getUnboundedProperty(Object name, String typeSignature) {
        ISymbol  retValue = null;

        if (isUnboundedForType(typeSignature)
                && name instanceof Number)
        {
            // get integer value
            int offset = ((Number)name).intValue();

            // first see if we have it in our map source
            // TODO: retValue = getFromMap(name.toString());

            if (retValue == null)
            {
                IPropertySymbol  propSymbol = SymbolFactory.eINSTANCE.createIPropertySymbol();
                // TODO: there is a possible problem here for non-string keyed maps
                propSymbol.setName(name.toString());
                propSymbol.setReadable(true);
                propSymbol.setTypeDescriptor(getBoundsTypeDescriptor(offset));
                retValue = propSymbol;
            }
        }

        return retValue;
    }

    /**
     * @return the ITypeDescriptor for this List's element type (bound type).
     * Defaults to java.lang.Object if no bounds or can't resolve bounds
     * 
     * @generated NOT 
     */
    private ITypeDescriptor getBoundsTypeDescriptor(int offset)
    {
        ITypeDescriptor  typeDesc = null;
        
        String[] bounds = Signature.getTypeArguments(getTypeSignature());
        
        // if no bounds at all, then default to bounded java object
        if (bounds.length == 0)
        {
            typeDesc = 
                SymbolFactory.eINSTANCE.createIBoundedJavaTypeDescriptor();
            
            typeDesc.setTypeSignatureDelegate(TypeConstants.TYPE_JAVAOBJECT);
        }
        else
        {
            // do nothing for now
        }
        
        return typeDesc;
    }
} //IBoundedListTypeDescriptorImpl
