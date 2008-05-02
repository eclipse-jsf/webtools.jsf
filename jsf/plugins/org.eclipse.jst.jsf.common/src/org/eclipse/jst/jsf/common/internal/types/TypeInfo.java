/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.internal.types;

import org.eclipse.jdt.core.IType;
import org.eclipse.jst.jsf.context.symbol.IBeanMethodSymbol;
import org.eclipse.jst.jsf.context.symbol.IBeanPropertySymbol;

/**This class contains all info that is cached for a given IType.
 * @see org.eclipse.jst.jsf.common.internal.types.TypeInfoCache
 * 
 * @author Matthias
 */
class TypeInfo {
    
    /**Empty String array. Used for the (usual) case when a type has no missing supertypes.
     */
    public static String[] NO_NAMES = new String[0];

    private IBeanMethodSymbol[] methods = null;
    private IBeanPropertySymbol[] properties = null;
    private IType[] supertypes = null;
    private IType[] interfaceTypes = null;
    private String[] missingSupertypeNames = null;
    
    /**Creates an empty TypeInfo object
     */
    public TypeInfo() {
        super();
    }
    
    /**Returns the method symbols. Returns <code>null</code> if none have been cached.
     * @return the method symbols. May be null.
     */
    public IBeanMethodSymbol[] getMethodSymbols() {
        return methods;
    }
    
    /**Returns the property symbols. Returns <code>null</code> if none have been cached.
     * @return the property symbols. May be null.
     */
    public IBeanPropertySymbol[] getPropertySymbols() {
        return properties;
    }

    /**Sets the methods symbols to be cached.
     * @param methods - the method symbols
     */
    public void setMethodSymbols(IBeanMethodSymbol[] methods) {
        this.methods = methods;
    }

    /**Sets the property symbols to be cached.
     * @param properties - the property symbols
     */
    public void setPropertySymbols(IBeanPropertySymbol[] properties) {
        this.properties = properties;
    }

    /**Returns the supertypes. Returns <code>null</code> if none have been cached.
     * @return the supertypes. May be null.
     */
    public IType[] getSupertypes() {
        return supertypes;
    }

    /**Sets the supertypes to be cached.
     * @param superTypes - the property symbols
     */
    public void setSupertypes(IType[] superTypes) {
        this.supertypes = superTypes;
    }

    /**Returns the interface types. Returns <code>null</code> if none have been cached.
     * @return the interface types. May be null.
     */
    public IType[] getInterfaceTypes() {
        return interfaceTypes;
    }

    /**Sets the interface types to be cached.
     * @param interfaceTypes - the property symbols
     */
    public void setInterfaceTypes(IType[] interfaceTypes) {
        this.interfaceTypes = interfaceTypes;
    }

    /**Returns the names of the missing supertypes. Returns <code>null</code> if none have been cached.
     * @return the names of the missing supertypes. May be null.
     */
    public String[] getMissingSupertypeNames() {
        return missingSupertypeNames;
    }

    /**Sets the supertypes to be cached.
     * @param missingSupertypeNames - the names of the missing supertypes
     */
    public void setMissingSupertypeNames(String[] missingSupertypeNames) {
        this.missingSupertypeNames = missingSupertypeNames;
    }
    
}
