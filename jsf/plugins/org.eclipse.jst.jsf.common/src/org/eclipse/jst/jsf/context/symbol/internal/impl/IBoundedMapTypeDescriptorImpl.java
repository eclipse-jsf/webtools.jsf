/**
 * <copyright>
 * </copyright>
 *
 * $Id: IBoundedMapTypeDescriptorImpl.java,v 1.1 2006/11/28 20:20:51 cbateman Exp $
 */
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedJavaTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IBounded Map Type Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class IBoundedMapTypeDescriptorImpl extends IMapTypeDescriptorImpl implements IBoundedMapTypeDescriptor {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = "Copyright 2006 Oracle";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IBoundedMapTypeDescriptorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SymbolPackage.Literals.IBOUNDED_MAP_TYPE_DESCRIPTOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean isUnboundedForType(String typeSignature) {
        // TODO: for now, return true if the type is a resolved object
        // need to add support for template checking (Java5) and
        // decide what to do with unresolved (Q) type signatures
        return typeSignature != null
                && typeSignature.startsWith(Character.toString(Signature.C_RESOLVED));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public ISymbol getUnboundedProperty(Object name, String typeSignature) {
        ISymbol  retValue = null;
        
        if (isUnboundedForType(typeSignature))
        {
            // first see if we have it in our map source
            retValue = getFromMap(name.toString());
            
            if (retValue == null)
            {
                IPropertySymbol  propSymbol = SymbolFactory.eINSTANCE.createIPropertySymbol();
                // TODO: there is a possible problem here for non-string keyed maps
                propSymbol.setName(name.toString());
                propSymbol.setReadable(true);
                IBoundedJavaTypeDescriptor typeDesc = 
                    SymbolFactory.eINSTANCE.createIBoundedJavaTypeDescriptor();
                
                typeDesc.setTypeSignatureDelegate(TypeConstants.TYPE_JAVAOBJECT);
                propSymbol.setTypeDescriptor(typeDesc);
                retValue = propSymbol;
            }
        }

        return retValue;

    }

   
    /**
     * @generated NOT
     */
    private ISymbol getFromMap(final String name)
    {
        for (final Iterator it = getProperties().iterator(); it.hasNext();)
        {
            ISymbol  symbol = (ISymbol) it.next();
            
            if (symbol.getName().equals(name))
            {
                return symbol;
            }
        }
        
        return null;
    }

} //IBoundedMapTypeDescriptorImpl