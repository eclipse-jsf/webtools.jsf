package org.eclipse.jst.jsf.core.internal;

import java.net.MalformedURLException;
import java.net.URL;

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

        UpgradeStatus[]  status = upgradeUtil.getUpgradeStatus();

        if (status.length > 0)
        {
            // TODO: don't understand why there are multiple
            // statuses here
            if (status[0].getState() == UpgradeStatus.UPGRADED)
            {
                handle05_to_10(status[0]);
            }
        }
    }

    private void handle05_to_10(UpgradeStatus status)
    {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Handle05_to_10_migration(status));
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
            	new ConfirmDialog(shell, _status.getShortMessage(), _status.getMessage());
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
    		
    		// if user confirmed, launch migration doc
    		if (userWantsMigrationDocLaunch)
				try {
					IWorkbenchBrowserSupport browserSupport = 
						PlatformUI.getWorkbench().getBrowserSupport();
					URL url = new URL("http://wiki.eclipse.org/index.php/JSF_Library_Migration");
					browserSupport.createBrowser("JSFMigrationDoc").openURL(url);
				} catch (PartInitException e) {
					JSFCorePlugin.log(e,"Error handling migration");
				} catch (MalformedURLException e) {
					JSFCorePlugin.log(e,"Error handling migration");
				}
    	}
    	
    	private void doAbortAndExit()
    	{
    		// delete V2 file
    		
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
		 * @param dialogTitleImage
		 * @param dialogMessage
		 * @param dialogImageType
		 * @param dialogButtonLabels
		 * @param defaultIndex
		 */
		public ConfirmDialog(Shell parentShell, String dialogTitle,
				String dialogMessage) {
			super(parentShell
					, dialogTitle
					, null
					, dialogMessage
					, WARNING
					, new String[] { "Confirm", "Abort and Exit" }
					, ABORT_AND_EXIT
					, "Launch Migration Doc on Confirm", true);
//			super(parentShell, dialogTitle, null, dialogMessage,
//					WARNING, , ABORT_AND_EXIT);
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
