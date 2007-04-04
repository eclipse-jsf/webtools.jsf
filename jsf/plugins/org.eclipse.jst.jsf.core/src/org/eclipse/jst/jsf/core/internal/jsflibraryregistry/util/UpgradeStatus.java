package org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

/**
 * Communicates the status of the JSF Library Registry
 *
 */
public class UpgradeStatus extends Status
{
	private final boolean upgradeOccurred;
	private UpgradeOperation  upgradeOperation;
	
	/**
	 * All-is-well UpgradeStatus constructor 
	 */
	public UpgradeStatus(){		
		super(IStatus.OK, JSFCorePlugin.getDefault().getPluginID(), "OK");
		this.upgradeOccurred = false;
	}
	
	/**
	 * Constructor when registry upgrade has occured or there is a problem during upgrade
	 * @param severity 
	 * @param upgradeOccurred 
	 * @param upgradeOperation 
	 * @param shortMessage - cannot be null
	 * @param message - cannot be null
	 * @param helpRef - href to page with additional information.  may be null.
	 * @param initialRegistryURL - may be null
	 * @param upgradedRegistryURL - may be null
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
				return new Status(IStatus.ERROR, JSFCorePlugin.getDefault().getPluginID(), "Error committing status", e);
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
				return new Status(IStatus.ERROR, JSFCorePlugin.getDefault().getPluginID(), "Error committing status", e);
			}
		}
		return Status.OK_STATUS;
	}
}
