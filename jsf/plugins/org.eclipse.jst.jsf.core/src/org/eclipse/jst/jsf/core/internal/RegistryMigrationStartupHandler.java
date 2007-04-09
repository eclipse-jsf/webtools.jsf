package org.eclipse.jst.jsf.core.internal;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util.JSFLibraryRegistryUpgradeUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util.UpgradeStatus;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

/**
 * Startup controller that handles a situation where the existing JSF
 * Library registry is old and needs to be migrated.
 *
 * @author cbateman
 *
 */
public class RegistryMigrationStartupHandler implements IStartup
{

    public void earlyStartup()
    {
        JSFLibraryRegistryUpgradeUtil upgradeUtil =
            JSFLibraryRegistryUpgradeUtil.getInstance();

        UpgradeStatus status = upgradeUtil.getUpgradeStatus();

        if (status.getSeverity() == IStatus.OK)
        {
        	if (status.isUpgradeOccurred())
        	{
        		handle05_to_10(status);
        	}
        }
        else
        {
        	handleErrorInMigration(status);
        }
    }

    private void handle05_to_10(UpgradeStatus status)
    {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Handle05_to_10_migration(status));
    }

    private void handleErrorInMigration(UpgradeStatus status)
    {
    	PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable()
    	{
    		public void run()
    		{
    			final Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
    			MessageDialog.openError(shell, "Serious Error Encountered!", "An unrecoverable error occurred while trying to migrate your JSF Library Registry to a newer version.  This may cause your library references to be broken in your JSF projects.");
    		}
    	});
    }
    
    private static class Handle05_to_10_migration implements Runnable
    {
        private final UpgradeStatus     _status;

        Handle05_to_10_migration(UpgradeStatus status)
        {
            _status = status;
        }

        public void run()
        {
            final Shell  shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            MessageDialogWithToggle  dialog = 
            	new ConfirmDialog(shell, Messages.JSFRegistryMigration05_to_10_title, Messages.JSFRegistryMigration05_to_10_customMessage);
            int result = dialog.open();
            
            switch(result)
            {
            	case ConfirmDialog.CONFIRMED:
            		doConfirmed(dialog.getToggleState());
            	break;
            	
            	default:
            		// all other cases than explicit proceed, abort and exit
            		doAbortAndExit();
            }
        }
        
    	private void doConfirmed(boolean userWantsMigrationDocLaunch) {
    		// delete V1 registry, leave backup file
    		IStatus result = _status.commit();
    		
    		if (result.getSeverity() != IStatus.OK)
    		{
    			final Shell  shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    			MessageDialog.openError(shell, Messages.RegistryMigrationStartupHandler_Error_committing_migration, result.getMessage());
    		}
    		
    		// if user confirmed, launch migration doc
    		if (userWantsMigrationDocLaunch)
				try {
					IWorkbenchBrowserSupport browserSupport = 
						PlatformUI.getWorkbench().getBrowserSupport();
					URL url = new URL("http://wiki.eclipse.org/index.php/JSF_Library_Migration"); //$NON-NLS-1$
					browserSupport.createBrowser("JSFMigrationDoc").openURL(url); //$NON-NLS-1$
				} catch (PartInitException e) {
					JSFCorePlugin.log(e,"Error handling migration"); //$NON-NLS-1$
				} catch (MalformedURLException e) {
					JSFCorePlugin.log(e,"Error handling migration"); //$NON-NLS-1$
				}
    	}
    	
    	private void doAbortAndExit()
    	{
    		// rollback
    		IStatus result = _status.rollback();
    		
    		if (result.getSeverity() != IStatus.OK)
    		{
    			final Shell  shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    			MessageDialog.openError(shell, Messages.RegistryMigrationStartupHandler_Error_Rolling_Back_Migration, result.getMessage());
    		}
    		// close workbench
    		PlatformUI.getWorkbench().close();
    	}
    }
    
    private static class ConfirmDialog extends MessageDialogWithToggle
    {
    	final static int	CONFIRMED = 0;
    	final static int	ABORT_AND_EXIT = 1;
    	
		/**
		 * @param parentShell
		 * @param dialogTitle
		 * @param dialogMessage
		 */
		public ConfirmDialog(Shell parentShell, String dialogTitle,
				String dialogMessage) {
			super(parentShell
					, dialogTitle
					, null
					, dialogMessage
					, WARNING
					, new String[] { Messages.RegistryMigrationStartupHandler_Dialog_Confirm_Migration, Messages.RegistryMigrationStartupHandler_Dialog_Abort_And_Exit_Migration }
					, ABORT_AND_EXIT
					, Messages.RegistryMigrationStartupHandler_Launch_Migration_Doc_On_Confirm, true);
		}

		/**
		 * Override so that the button ids line up with the constants
		 * expected
		 * @param parent 
		 */
		protected void createButtonsForButtonBar(Composite parent) {
	        final String[] buttonLabels = getButtonLabels();
	        final Button[] buttons = new Button[buttonLabels.length];
	        final int defaultButtonIndex = getDefaultButtonIndex();

	        for (int i = 0; i < buttonLabels.length; i++) {
	            String label = buttonLabels[i];
	            Button button = createButton(parent, i, label,
	                    defaultButtonIndex == i);
	            buttons[i] = button;
	 
	        }
	        setButtons(buttons);
		}
		
    }
}
