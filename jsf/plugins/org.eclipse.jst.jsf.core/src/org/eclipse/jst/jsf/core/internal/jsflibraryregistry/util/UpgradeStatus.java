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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Communicates the status of the JSF Library Registry
 * @deprecated
 */
public class UpgradeStatus extends Status
{
	private final boolean upgradeOccurred;
	private UpgradeOperation  upgradeOperation;
	
	/**
	 * All-is-well UpgradeStatus constructor 
	 */
	public UpgradeStatus(){		
		super(IStatus.OK, JSFCorePlugin.getDefault().getPluginID(), Messages.UpgradeStatus_OK); 
		this.upgradeOccurred = false;
	}
	
	/**
	 * Constructor when registry upgrade has occured or there is a problem during upgrade
	 * @param severity 
	 * @param upgradeOccurred flag
	 * @param message 
	 * 
	 */
	public UpgradeStatus(int severity, boolean upgradeOccurred, String message){	
		super(severity, JSFCorePlugin.getDefault().getPluginID(), message);
		this.upgradeOccurred = upgradeOccurred;
	}

	/**
	 * @return true if a registry upgrade occurred
	 */
	public boolean isUpgradeOccurred() {
		return upgradeOccurred;
	}

	/**
	 * @return the operation used to do the upgrade.
	 */
	protected UpgradeOperation getUpgradeOperation() {
		return upgradeOperation;
	}
	
	void setUpgradeOperation(UpgradeOperation upgradeOperation)
	{
		this.upgradeOperation = upgradeOperation;
	}
	
	/**
	 * Commits any upgrade that has occurred
	 * @return the result of the commit
	 */
	public IStatus commit()
	{
		if (upgradeOperation != null)
		{
			try
			{
				return upgradeOperation.commit();
			}
			catch (ExecutionException e)
			{
				return new Status(IStatus.ERROR, JSFCorePlugin.getDefault().getPluginID(), Messages.UpgradeStatus_Error, e);
			}
		}
		return Status.OK_STATUS;
	}
	
	/**
	 * @return the result of rolling back any changes
	 */
	public IStatus rollback()
	{
		if (upgradeOperation != null)
		{
			try
			{
				return upgradeOperation.undo(new NullProgressMonitor(), null);
			}
			catch (ExecutionException e)
			{
				return new Status(IStatus.ERROR, JSFCorePlugin.getDefault().getPluginID(), Messages.UpgradeStatus_Error, e); 
			}
		}
		return Status.OK_STATUS;
	}
}
