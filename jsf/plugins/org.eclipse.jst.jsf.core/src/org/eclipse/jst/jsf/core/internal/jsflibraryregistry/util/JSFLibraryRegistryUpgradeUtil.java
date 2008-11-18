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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Utility for checking for, and upgrading the JSF Library Registry when the EMF model changes.
 * @deprecated
 */
public class JSFLibraryRegistryUpgradeUtil {	
	static String v1Tov2UpgradeURL = "http://www.eclipse.org/JSFxxxxxxxxxxxxxxxxxxxxxx"; //$NON-NLS-1$
	
	/**
	 * default state is OK, no upgrade
	 */
	private UpgradeStatus	upgradeStatus;

	/**
	 * The workspace-relative part of the URL of the JSF Library Registry 
	 * persistence store. (version 1)
	 */
	public static final String JSF_LIBRARY_REGISTRY_V1_URL = ".metadata/.plugins/org.eclipse.jst.jsf.core/JSFLibraryRegistry.xml"; //$NON-NLS-1$

	/**
	 * The workspace-relative part of the URL of the JSF Library Registry 
	 * persistence store. (version 2)
	 */
	public static final String JSF_LIBRARY_REGISTRY_V2_URL = ".metadata/.plugins/org.eclipse.jst.jsf.core/JSFLibraryRegistryV2.xml"; //$NON-NLS-1$

	/**
	 * The LATEST VERSION of the workspace-relative part of the URL of the JSF Library Registry 
	 * persistence store. 
	 */
	public static final String JSF_LIBRARY_REGISTRY_LATESTVERSION_URL = JSF_LIBRARY_REGISTRY_V2_URL;
	/**
	 * The latest version value.
	 */
	public static final int	   LATESTVERSION = 2;

	private static final int NO_VERSION = 0;
	
	private static JSFLibraryRegistryUpgradeUtil INSTANCE;
		
	/**
	 * @return the stateful runtime singleton
	 */
	public static synchronized JSFLibraryRegistryUpgradeUtil getInstance(){
		if (INSTANCE == null){
			INSTANCE = new JSFLibraryRegistryUpgradeUtil();
		}
		return INSTANCE;
	}
	
	/**
	 * Return the URI for the specified JSF Library Registry
	 * @param registryVersion
	 * @return URI 
	 * @throws MalformedURLException
	 */
	public static URI getRegistryURI(String registryVersion) throws MalformedURLException {
		URL jsfLibRegURL = new URL(Platform.getInstanceLocation().getURL(), registryVersion);
		return URI.createURI(jsfLibRegURL.toString());
	}
	
	/**
	 * @param originalFile
	 * @return the backup file name for a file
	 */
	public static String getBackupFileName(final String originalFile)
	{
	    return originalFile.concat(".bkp"); //$NON-NLS-1$
	}
	
	/**
	 * Upgrades the JSF Library registry from oldest to newest
	 * @param expectedVersion 
	 */
	public void upgradeRegistryIfNecessary(int expectedVersion) {
		//when adding upgrades, the upgrades should progress from oldest to newest
		try
		{
			int curVersion = getCurVersion();
			
			if (curVersion < expectedVersion && curVersion != NO_VERSION)
			{
				UpgradeOperation op = getUpgradeOperation(curVersion);
				
				if (op.canExecute())
				{
					try
					{
						// TODO: when move to Java 5, use co-variant return
						upgradeStatus = (UpgradeStatus)
							op.execute(new NullProgressMonitor(), null);
						upgradeStatus.setUpgradeOperation(op);
					}
					catch (ExecutionException e)
					{
						// should never happen since we control the URL's
						JSFCorePlugin.log(IStatus.ERROR, "Error during loading JSF Library registry", e); //$NON-NLS-1$
						//TODO: flag failure in status
						upgradeStatus = 
							new UpgradeStatus(IStatus.ERROR, true, Messages.JSFLibraryRegistryUpgradeUtil_Error); 
					}
				}
				else
				{
    				// TODO: what if can't execute?
                    upgradeStatus = 
                        new UpgradeStatus(IStatus.ERROR, false, Messages.JSFLibraryRegistryUpgradeUtil_Error); 				}
			}
			else
			{
			    // everything ok, not upgrade
			    upgradeStatus = new UpgradeStatus();
			}
		}
	    catch (MalformedURLException e) {	
			// should never happen since we control the URL's
			JSFCorePlugin.log(IStatus.ERROR, "Error during loading JSF Library registry", e); //$NON-NLS-1$
		}
	}

	private int getCurVersion() throws MalformedURLException
	{
		// TODO: need generalized algorithm here
		URI v2File = getRegistryURI(JSF_LIBRARY_REGISTRY_V2_URL);
		File file = new File(v2File.toFileString());
		if (file.exists())
		{
			return 2;
		}
		
		URI v1File = getRegistryURI(JSF_LIBRARY_REGISTRY_V1_URL);
		file = new File(v1File.toFileString());
		if (file.exists())
		{
			return 1;
		}
		return NO_VERSION;
	}
	
	/**
	 * @param curVersion
	 * @return the upgrade operation to move from curVersion to the latest
	 * @throws MalformedURLException
	 */
	protected UpgradeOperation getUpgradeOperation(int curVersion) throws MalformedURLException
	{
		UpgradeOperation  op = new UpgradeOperation(Messages.JSFLibraryRegistryUpgradeUtil_Operation); 
		switch(curVersion)
		{
			case 1:
				op.addVersionUpgrade(
						new MigrateV1toV2Operation(Messages.JSFLibraryRegistryUpgradeUtil_v1Tov2Operation 
								,getRegistryURI(JSF_LIBRARY_REGISTRY_V1_URL)
								, getRegistryURI(JSF_LIBRARY_REGISTRY_V2_URL)));
		}
		
		return op;
	}
	
	
	/**
	 * @return array of {@link UpgradeStatus}s.  There can be more than one if the registry has been upgraded by more than one version.
	 * Will not be null.   
	 */
	public UpgradeStatus getUpgradeStatus(){
		return this.upgradeStatus;
	}

	static void deleteFile(String fileName) {
		File f = new File(fileName);
		if (f.exists()){
			f.delete();
			if (f.exists())
				f.deleteOnExit();
		}
	}

	static void copyFile(String srcFileName, String destFileName) {
	
		File srcFile = new File(srcFileName);
		File destFile = new File(destFileName);
		FileInputStream from = null;
		FileOutputStream to = null;
		try {
			from = new FileInputStream(srcFile);
			to = new FileOutputStream(destFile);
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = from.read(buffer)) != -1)
				to.write(buffer, 0, bytesRead); // write
		} catch (IOException ioe){
			JSFCorePlugin.log(ioe, "Error during file copy"); //$NON-NLS-1$
		} finally {
			if (from != null)
				try {
					from.close();
				} catch (IOException e) {
		            JSFCorePlugin.log(e, "Error during file close"); //$NON-NLS-1$
				}
			if (to != null)
				try {
					to.close();
				} catch (IOException e) {
                    JSFCorePlugin.log(e, "Error during file close"); //$NON-NLS-1$
				}
		}

	}

}
