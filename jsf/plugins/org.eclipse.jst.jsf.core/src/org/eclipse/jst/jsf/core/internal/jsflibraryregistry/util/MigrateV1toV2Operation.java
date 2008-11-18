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
package org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;

/**
 * @deprecated
 */
class MigrateV1toV2Operation extends VersionUpgradeOperation {

	private final URI		_v1Registry;
	private final URI		_v2Registry;
	
	/**
	 * @param label
	 * @param v1Registry
	 * @param v2Registry 
	 */
	public MigrateV1toV2Operation(String label, URI v1Registry, URI v2Registry) {
		super(label, 1, 2);
		_v1Registry = v1Registry;
		_v2Registry = v2Registry;
	}

	public IStatus doCommit() {
		JSFLibraryRegistryUpgradeUtil.deleteFile(_v1Registry.toFileString());
		return Status.OK_STATUS;
	}

	public IStatus doExecute(IProgressMonitor monitor, IAdaptable info)
	{
		JSFLibraryRegistryUpgradeUtil.copyFile
			(_v1Registry.toFileString(), JSFLibraryRegistryUpgradeUtil.getBackupFileName(_v1Registry.toFileString()));
		JSFLibraryRegistryResourceFactoryImpl resourceFactory = new JSFLibraryRegistryResourceFactoryImpl();
		JSFLibraryRegistryResourceImpl res = (JSFLibraryRegistryResourceImpl)resourceFactory.createResource(_v1Registry);
		try {
			URI newRegURI = 
				JSFLibraryRegistryUpgradeUtil.getRegistryURI
					(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL);
			Map options = new HashMap();
			//disable notifications during load to avoid changing stored default implementation
			options.put(XMLResource.OPTION_DISABLE_NOTIFY, Boolean.TRUE);
			res.load(options);
			//if we got this far then the registry was empty
			//"upgrade" to v2 and then delete old.   no point in upgrade status being sent
			JSFLibraryRegistryUtil.getInstance().saveJSFLibraryRegistry();
			JSFLibraryRegistryUpgradeUtil.copyFile(_v1Registry.toFileString(), newRegURI.toFileString());//save as v2 file	
			JSFLibraryRegistryUpgradeUtil.deleteFile(_v1Registry.toFileString());

			return new UpgradeStatus();//all is ok and no need to alert user
			
		} catch(IOException ioe) {
			//this was expected... if there was actual v1 contents in the regsistry... upgrade by saving
			//perform save which will lose the ID
			try {
				res.save(Collections.EMPTY_MAP);
				//create v2 xml file
				URI newRegURI = 
					JSFLibraryRegistryUpgradeUtil.getRegistryURI
						(JSFLibraryRegistryUpgradeUtil.JSF_LIBRARY_REGISTRY_V2_URL);
				JSFLibraryRegistryUpgradeUtil.copyFile(_v1Registry.toFileString(), newRegURI.toFileString());
				//delete upgraded v1
				JSFLibraryRegistryUpgradeUtil.deleteFile(_v1Registry.toFileString());
				//restore backup to v1 name
				JSFLibraryRegistryUpgradeUtil.copyFile(_v1Registry.toFileString().concat(".bkp"), _v1Registry.toFileString()); //$NON-NLS-1$
				//Alert end user
				return new UpgradeStatus(IStatus.OK, true, Messages.JSFRegistryMigration05_to_10_customMessage);
			} catch(IOException e) {
				JSFCorePlugin.log(IStatus.ERROR, "Error during repository upgrade from v1 to v2", e); //$NON-NLS-1$
				return new UpgradeStatus(IStatus.ERROR, false, 	
						Messages.JSFRegistryMigrationCannot05_to_10_customMessage);
			}
		}
		//return ;
	}

	public IStatus doRedo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		return doExecute(monitor, info);
	}

	
	public boolean canUndo() {
		// commit is undoable for this operation
		return super.canUndo() && !hasCommitted();
	}

	public IStatus doUndo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException 
	{
		//restore backup to v1 name
		JSFLibraryRegistryUpgradeUtil.copyFile(_v1Registry.toFileString().concat(".bkp"), _v1Registry.toFileString()); //$NON-NLS-1$

		// delete the new registry 
		JSFLibraryRegistryUpgradeUtil.deleteFile(_v2Registry.toFileString());
		
		//and the backup
		JSFLibraryRegistryUpgradeUtil.deleteFile(_v1Registry.toFileString().concat(".bkp")); //$NON-NLS-1$
		
		return Status.OK_STATUS;
	}
}
