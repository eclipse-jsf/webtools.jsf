package org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.Messages;

/**
 * Utility for checking for, and upgrading the JSF Library Registry when the EMF model changes.
 */
public class JSFLibraryRegistryUpgradeUtil {	
	private String v1Tov2UpgradeURL = "http://www.eclipse.org/JSFxxxxxxxxxxxxxxxxxxxxxx";
	
	private List registryStatus;

	/**
	 * The workspace-relative part of the URL of the JSF Library Registry 
	 * persistence store. (version 1)
	 */
	public static final String JSF_LIBRARY_REGISTRY_URL = ".metadata/.plugins/org.eclipse.jst.jsf.core/JSFLibraryRegistry.xml"; //$NON-NLS-1$

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
	
	private static JSFLibraryRegistryUpgradeUtil INSTANCE;
		
	public static synchronized JSFLibraryRegistryUpgradeUtil getInstance(){
		if (INSTANCE == null){
			INSTANCE = new JSFLibraryRegistryUpgradeUtil();
			INSTANCE.init();
		}
		return INSTANCE;
	}
	
	private void init() {
		registryStatus = new ArrayList();
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
	 * Upgrades the JSF Library registry from oldest to newest
	 * @param aJsfLibRegURI - URI of current version registry file
	 */
	public void upgradeRegistryIfNecessary(URI aJsfLibRegURI) {
		//when adding upgrades, the upgrades should progress from oldest to newest
		
		//If current version registry file exists... exit.   No upgrade required.
		File file = new File(aJsfLibRegURI.toFileString());
		if (file.exists())
			return;
		
		//does v1 exist?  
		URI v1Reg;
		try {
			v1Reg = getRegistryURI(JSF_LIBRARY_REGISTRY_URL);
			file = new File(v1Reg.toFileString());
			if (file.exists()){
				upgradeJSFLibraryRegistryFromV1ToV2(v1Reg);
			}
		} catch (MalformedURLException e) {			
			JSFCorePlugin.log(IStatus.ERROR, "Error during loading JSF Library registry", e);
		}

		
	}

	/**
	 * @return array of {@link UpgradeStatus}s.  There can be more than one if the registry has been upgraded by more than one version.
	 * Will not be null.   
	 */
	public UpgradeStatus[] getUpgradeStatus(){
		if (registryStatus.size() == 0){
			registryStatus.add(new UpgradeStatus());
		}
		return (UpgradeStatus[])registryStatus.toArray(new UpgradeStatus[registryStatus.size()]);
	}

	private void upgradeJSFLibraryRegistryFromV1ToV2(URI reg) {
		copyFile(reg.toFileString(), reg.toFileString().concat(".bkp"));
		JSFLibraryRegistryResourceFactoryImpl resourceFactory = new JSFLibraryRegistryResourceFactoryImpl();
		JSFLibraryRegistryResourceImpl res = (JSFLibraryRegistryResourceImpl)resourceFactory.createResource(reg);
		try {
			URI newRegURI = getRegistryURI(JSF_LIBRARY_REGISTRY_V2_URL);
			Map options = new HashMap();
			//disable notifications during load to avoid changing stored default implementation
			options.put(XMLResource.OPTION_DISABLE_NOTIFY, Boolean.TRUE);
			res.load(options);
			//if we got this far then the registry was empty
			//"upgrade" to v2 and then delete old.   no point in upgrade status being sent
			JSFCorePlugin.getDefault().saveJSFLibraryRegistry();
			copyFile(reg.toFileString(), newRegURI.toFileString());//save as v2 file	
			deleteFile(reg.toFileString());
			
		} catch(IOException ioe) {
			//this was expected... if there was actual v1 contents in the regsistry... upgrade by saving
			//perform save which will lose the ID
			try {
				res.save(Collections.EMPTY_MAP);
				//create v2 xml file
				URI newRegURI = getRegistryURI(JSF_LIBRARY_REGISTRY_V2_URL);
				copyFile(reg.toFileString(), newRegURI.toFileString());
				//delete upgraded v1
				deleteFile(reg.toFileString());
				//restore backup to v1 name
				copyFile(reg.toFileString().concat(".bkp"), reg.toFileString());
				//Alert end user
				UpgradeStatus status = new UpgradeStatus(UpgradeStatus.UPGRADED, Messages.JSFRegistryMigration05_to_10_title,
				        Messages.JSFRegistryMigration05_to_10_customMessage,
							v1Tov2UpgradeURL,
							JSF_LIBRARY_REGISTRY_URL,
							JSF_LIBRARY_REGISTRY_V2_URL);
				
				registryStatus.add(status);
			} catch(IOException e) {
				JSFCorePlugin.log(IStatus.ERROR, "Error during repository upgrade from v1 to v2", e);
				UpgradeStatus status = new UpgradeStatus(UpgradeStatus.CANNOT_UPGRADE, Messages.JSFRegistryMigrationCannot05_to_10_title, 	
						Messages.JSFRegistryMigrationCannot05_to_10_customMessage,
						v1Tov2UpgradeURL,
						JSF_LIBRARY_REGISTRY_URL,
						JSF_LIBRARY_REGISTRY_V2_URL);
			
				registryStatus.add(status);
			}
		}
		
	}

	private void deleteFile(String fileName) {
		File f = new File(fileName);
		if (f.exists()){
			f.delete();
			if (f.exists())
				f.deleteOnExit();
		}
	}

	private void copyFile(String srcFileName, String destFileName) {
	
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
			
		} finally {
			if (from != null)
				try {
					from.close();
				} catch (IOException e) {
				}
			if (to != null)
				try {
					to.close();
				} catch (IOException e) {
				}
		}

	}

}
