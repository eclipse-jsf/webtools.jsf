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
package org.eclipse.jst.jsf.core.jsflibraryconfiguration.internal;




import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryReference;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFVersion;
/**
 * @author gekessle
 * @deprecated
 */
public abstract class AbstractJSFLibraryReferenceImpl implements JSFLibraryReference {
	
	/**
	 * The {@link org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference} being wrapped
	 */
	protected JSFLibraryInternalReference libRef;
	private String _id;
	private String _label;
	private boolean _isImplementation;
	private boolean _isDeloyed;

	/**
	 * Constructor for "virtual" JSF Library References like "ServerSupplied"
	 * @param id
	 * @param label 
	 * @param isImplementation 
	 */
	public AbstractJSFLibraryReferenceImpl(String id, String label, boolean isImplementation){
		_id = id;
		_label = label;
		_isImplementation = isImplementation;
	}
	
	/**
	 * Constructor non-virtual library references
	 * @param libRef
	 * @param isDeployed 
	 */
	public AbstractJSFLibraryReferenceImpl(JSFLibraryInternalReference libRef, boolean isDeployed){
		this.libRef = libRef;
		_isDeloyed = isDeployed;
	}
	
	public String getId() {
		if (libRef != null)
			return libRef.getID();
		
		return _id;
	}

	public String getLabel() {
		if (libRef != null)
			return libRef.getLabel();

		return _label;
	}

	public boolean isDeployed() {
		return _isDeloyed;
	}

	public boolean isJSFImplementation() {
		if (libRef != null)
			return libRef.isImplementation();
		
		return _isImplementation;
	}
	
	/**
	 * @return the JSFLibrary underpinning the reference.  
	 * May be null if the library is missing or cannot be resolved from the registry.
	 */
	protected JSFLibrary getLibrary(){
		return libRef.getLibrary();
	}
	
	public Collection<IClasspathEntry> getJars() {
		Set<IClasspathEntry> results = new HashSet<IClasspathEntry>();
		if (getLibrary() != null){
			List jars = getLibrary().getArchiveFiles();
			for (Iterator it= jars.iterator();it.hasNext();){
				ArchiveFile jar = (ArchiveFile)it.next();
				String path = jar.getResolvedSourceLocation();
				results.add(JavaCore.newLibraryEntry(new Path(path), null, null));
			}
		}			
		return results;
	}

	public JSFVersion getMaxSupportedVersion() {
		if (getLibrary() != null)
			return adaptVersion(getLibrary().getJSFVersion());
		return null;
	}

	private JSFVersion adaptVersion(
			org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion version) {
		
		switch (version.getValue()){
			case org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion.V1_1:
				return JSFVersion.V1_1;				
			case org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion.V1_2:
				return JSFVersion.V1_2;		
			default:
				return JSFVersion.UNKNOWN;			
				
		}
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryReference#getName()
	 */
	public String getName() {
		if (getLibrary()!= null) {
			return getLibrary().getName();
		}
		return getId();
	}

	public String toString(){
		StringBuffer buf = new StringBuffer("id: "); //$NON-NLS-1$
		buf.append(getId());
		buf.append(", label: "); //$NON-NLS-1$
		buf.append(getLabel());
		buf.append(", isDeployed: "); //$NON-NLS-1$
		buf.append(isDeployed());
		buf.append(", isImpl: "); //$NON-NLS-1$
		buf.append(isJSFImplementation());
		buf.append(", version: "); //$NON-NLS-1$
		buf.append(getMaxSupportedVersion().name());
		
		return buf.toString();
	}
}
