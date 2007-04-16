/**
 * <copyright>
 * </copyright>
 *
 * $Id: IBoundedJavaTypeDescriptorImpl.java,v 1.5 2007/04/16 19:53:58 itrimble Exp $
 */
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.context.symbol.IBoundedJavaTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IBounded Java Type Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class IBoundedJavaTypeDescriptorImpl extends IJavaTypeDescriptor2Impl implements IBoundedJavaTypeDescriptor {
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
    protected IBoundedJavaTypeDescriptorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SymbolPackage.Literals.IBOUNDED_JAVA_TYPE_DESCRIPTOR;
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
        ISymbol retValue = null;
        
        if (isUnboundedForType(typeSignature))
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
        
        return retValue;

	}

} //IBoundedJavaTypeDescriptorImpl