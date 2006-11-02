/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.ui.internal.classpath;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.ArchiveFile;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistryFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFVersion;
import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.jst.jsf.ui.internal.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Dialog for creating or editing a JSF Library or Implementation.
 * <br>
 * If the selection passed in init is not null then the item will be edit mode.
 * 
 * @author Gerry Kessler - Oracle
 */
public class JSFLibraryWizard extends Wizard implements INewWizard {
	private Text txtName;
	private Label lblName;
	private CCombo cboVersions;
	private Label lblVersions;
	private Button chkDeploy;
	private Button chkImpl;
	private Composite btnBar;
	private Button btnAdd;
	private Button btnRemove;
	private TableViewer jars;

	private boolean isNew = false;
	private boolean modified = false;
	private boolean implsOnly = false;

	private JSFLibrary curLibrary;
	private JSFLibrary workingCopyLibrary;

	private JSFLibraryWizardPage page;

	private static final String DESCRIPTION = Messages.JSFLibraryWizard_DESCRIPTION;
	private static final String IMPLS_ONLY_DESC = Messages.JSFLibraryWizard_IMPLS_ONLY_DESC;

	private boolean nonimplOnly = false;	
	
	public JSFLibraryWizard(boolean b) {
		super();
		implsOnly = b;
	}

	public JSFLibraryWizard() {
		super();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		if (selection != null
				&& selection.getFirstElement() instanceof JSFLibrary) {
			curLibrary = (JSFLibrary) selection.getFirstElement();
			workingCopyLibrary = curLibrary.getWorkingCopy();
		} else {
			isNew = true;
			workingCopyLibrary = JSFLibraryRegistryFactory.eINSTANCE.createJSFLibrary();
		}
		if (implsOnly) {
			setWindowTitle(Messages.JSFLibraryWizard_CreateImplementation);
		} else {
			setWindowTitle(isNew ? Messages.JSFLibraryWizard_CreateJSFLibrary : Messages.JSFLibraryWizard_EditJSFLibrary);
		}
	}
	public void init(IWorkbench workbench, IStructuredSelection selection, boolean nonimplonly) {
		init(workbench, selection);
		this.nonimplOnly = nonimplonly;
	}
	public boolean performFinish() {
		final String name = txtName.getText().trim();
		final boolean isDeployed = chkDeploy.getSelection();
		final boolean isImplementation = chkImpl.getSelection();

		workingCopyLibrary.setName(name);
		if (cboVersions.getSelectionIndex() >= 0) {
			JSFVersion ver = (JSFVersion) JSFVersion.VALUES.get(cboVersions
					.getSelectionIndex());
			workingCopyLibrary.setJSFVersion(ver);
		}
		workingCopyLibrary.setDeployed(isDeployed);
		workingCopyLibrary.setImplementation(isImplementation);
		if (isNew){
			JSFCorePlugin.getDefault().getJSFLibraryRegistry().addJSFLibrary(workingCopyLibrary);
		}
		else {
			curLibrary.updateValues(workingCopyLibrary);
		}
		JSFCorePlugin.getDefault().saveJSFLibraryRegistry();
		return true;
	}

	public void addPages() {
		page = new JSFLibraryWizardPage(Messages.JSFLibraryWizard_JSFLibrary);
		super.addPage(page);
		page.setWizard(this);
	}

	public JSFLibrary getJSFLibrary() {
		return workingCopyLibrary;
	}

	private class JSFLibraryWizardPage extends WizardPage implements
			ModifyListener, SelectionListener {

		private boolean initing;

		protected JSFLibraryWizardPage(String pageName) {
			super(pageName);
			setDescription(implsOnly ? IMPLS_ONLY_DESC : DESCRIPTION);
			setTitle(Messages.JSFLibraryWizard_JSFLibrary);
		}

		public boolean isPageComplete() {
			if (modified == false) {
				return false;
			}
			return super.isPageComplete();
		}

		public void createControl(Composite parent) {
			initing = true;
			initializeDialogUnits(parent);

			Composite c = new Composite(parent, SWT.NONE);
			c.setLayout(new GridLayout(2, false));
			c.setLayoutData(new GridData(GridData.FILL_BOTH));

			lblName = new Label(c, SWT.NONE);
			lblName.setText(Messages.JSFLibraryWizard_LibraryName);
			lblName.setLayoutData(new GridData(GridData.BEGINNING));

			txtName = new Text(c, SWT.BORDER);
			txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			txtName.addModifyListener(this);

			lblVersions = new Label(c, SWT.NONE);
			lblVersions.setText(Messages.JSFLibraryWizard_VersionSupported);

			cboVersions = new CCombo(c, SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER);
			cboVersions.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			cboVersions.addModifyListener(this);

			Group jarsComp = new Group(c, SWT.NONE);
			jarsComp.setText(Messages.JSFLibraryWizard_LibraryJars);
			GridLayout gl1 = new GridLayout(2, false);
			jarsComp.setLayout(gl1);
			GridData gd2 = new GridData(GridData.FILL_HORIZONTAL
					| GridData.FILL_VERTICAL);
			gd2.horizontalSpan = 2;
			jarsComp.setLayoutData(gd2);

			createJarsViewer(jarsComp);

			createButtons(jarsComp);

			chkImpl = new Button(c, SWT.CHECK);
			chkImpl.setText(Messages.JSFLibraryWizard_IsJSFImplementation);
			GridData gd3 = new GridData();
			gd3.horizontalSpan = 2;
			chkImpl.setLayoutData(gd3);
			chkImpl.addSelectionListener(this);

			chkDeploy = new Button(c, SWT.CHECK);
			chkDeploy.setText(Messages.JSFLibraryWizard_DeployJars);
			GridData gd4 = new GridData();
			gd4.horizontalSpan = 2;
			chkDeploy.setLayoutData(gd4);
			chkDeploy.addSelectionListener(this);
			chkDeploy.setVisible(false);

			loadVersions();

			if (!isNew) {
				txtName.setText(workingCopyLibrary.getName());
				cboVersions.setText(workingCopyLibrary.getJSFVersion().getName());
				chkDeploy.setSelection(workingCopyLibrary.isDeployed());
				chkImpl.setSelection(workingCopyLibrary.isImplementation());
			}

			if (implsOnly) {
				chkImpl.setSelection(true);
				chkImpl.setEnabled(false);
			}
			
			if (nonimplOnly) {
				chkImpl.setSelection(false);
				chkImpl.setEnabled(false);				
			}
			jars.setInput(workingCopyLibrary);

			initing = false;
			setControl(c);

			txtName.setFocus();
			setPageComplete(false);
		}

		private void createJarsViewer(Group jarsComp) {
			jars = new TableViewer(jarsComp, SWT.BORDER | SWT.MULTI);
			jars.setContentProvider(new IStructuredContentProvider() {
				public Object[] getElements(Object inputElement) {
					if (inputElement instanceof JSFLibrary)
						return ((JSFLibrary) inputElement).getArchiveFiles()
								.toArray();
					return new Object[0];
				}

				public void dispose() {
                    // do nothing
				}

				public void inputChanged(Viewer viewer, Object oldInput,
						Object newInput) {
					// won't happen
				}
			});
			jars.setLabelProvider(new ILabelProvider() {
				private Image jarImg = null;
				public Image getImage(Object element) {
					if (jarImg == null){
						ImageDescriptor desc = JSFUiPlugin.getImageDescriptor("obj16/jar_obj.gif"); //$NON-NLS-1$
						jarImg = desc.createImage();
					}
					return jarImg;
				}

				public String getText(Object element) {
					StringBuffer labelBuf = new StringBuffer();
					if (element instanceof ArchiveFile) {
						ArchiveFile archive = (ArchiveFile) element;
						labelBuf.append(archive.getName());	
						if (!archive.exists())
							labelBuf.append("[missing]"); //$NON-NLS-1$
						labelBuf.append(" - ").append(archive.getPath()); //$NON-NLS-1$
					}
					return labelBuf.toString();
				}

				public void addListener(ILabelProviderListener listener) {
                    // no listeners supported
				}

				public void dispose() {
					if (jarImg != null)
						jarImg.dispose();
				}

				public boolean isLabelProperty(Object element, String property) {
					return false;
				}

				public void removeListener(ILabelProviderListener listener) {
                    // no listeners supported
				}
			});

			GridData gd = new GridData(GridData.FILL_BOTH);
			gd.widthHint = convertWidthInCharsToPixels(30);
			gd.heightHint = convertHeightInCharsToPixels(10);
			jars.getControl().setLayoutData(gd);
		}

		private void updateButtons() {
			btnRemove.setEnabled(jars.getSelection() != null);
			// getButton(IDialogConstants.OK_ID).setEnabled(modified);
		}

		private void createButtons(Composite c) {
			btnBar = new Composite(c, SWT.NONE);
			GridLayout gl = new GridLayout(1, false);
			gl.marginHeight = 0;
			gl.marginTop = 0;
			gl.marginWidth = 0;
			btnBar.setLayout(gl);
			btnBar.setLayoutData(new GridData(GridData.END));

			btnAdd = new Button(btnBar, SWT.NONE);
			btnAdd.setText(Messages.JSFLibraryWizard_Add);
			btnAdd.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
					| GridData.VERTICAL_ALIGN_BEGINNING));
			btnAdd.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					String cur = null;
					String[] chosenJars = openExtJarFileDialog(cur);
					if (chosenJars != null) {
						for (int i = 0; i < chosenJars.length; i++) {
							String jar = chosenJars[i];
							if (!workingCopyLibrary.containsArchiveFile(jar)) {
								ArchiveFile archive = JSFLibraryRegistryFactory.eINSTANCE
									.createArchiveFile();
								archive.setSourceLocation(jar);
								archive.setRelativeDestLocation("WEB-INF/lib"); //$NON-NLS-1$
								workingCopyLibrary.getArchiveFiles().add(archive);
								modified = true;
							}
						}
						jars.refresh();
						validate();
					}
				}
			});

			btnRemove = new Button(btnBar, SWT.NONE);
			btnRemove.setText(Messages.JSFLibraryWizard_Remove);
			btnRemove.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
					| GridData.VERTICAL_ALIGN_BEGINNING));
			btnRemove.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (jars.getSelection() instanceof StructuredSelection){
						StructuredSelection objs = (StructuredSelection)jars.getSelection();
						if (objs != null){
							Iterator it = objs.iterator();
							 while (it.hasNext()){
								 Object obj = it.next();
								 ArchiveFile jar = (ArchiveFile)obj;
								 workingCopyLibrary.getArchiveFiles().remove(jar);
								 modified = true;
							 }
						}
						jars.refresh();
						validate();
					}
				}
			});
		}

		private void loadVersions() {
			cboVersions.removeAll();
			Iterator it = JSFVersion.VALUES.iterator();
			while (it.hasNext()) {
				JSFVersion ver = (JSFVersion) it.next();
				cboVersions.add(ver.getName());
			}
		}

		private String[] openExtJarFileDialog(String existing) {
			String title = Messages.JSFLibraryWizard_ExtJarFileDialogTitle;

			FileDialog dialog = new FileDialog(getShell(),
					existing == null ? SWT.MULTI : SWT.SINGLE);
			dialog.setText(title);
			dialog.setFilterExtensions(new String[] { "*.jar;*.zip" }); //$NON-NLS-1$
			// FIXME: remember and use last path chosen??
			String filterPath = ResourcesPlugin.getWorkspace().getRoot()
					.getFullPath().toString();
			dialog.setFilterPath(filterPath);
			// if (existing != null) {
			// dialog.setFileName(existing.getPath().lastSegment());
			// }

			String res = dialog.open();
			if (res == null) {
				return null;
			}
			String[] fileNames = dialog.getFileNames();
			String[] elems = new String[fileNames.length];
			IPath file = new Path(res);
			IPath apath = file.removeLastSegments(1);
			for (int i = 0; i < fileNames.length; i++) {
				elems[i] = apath.append(fileNames[i]).toString();
			}
			return elems;
		}	

		public void modifyText(ModifyEvent e) {
			modified = true;
			validate();
			updateButtons();
		}

		public void widgetSelected(SelectionEvent e) {
			modified = true;
			validate();
			updateButtons();
		}

		public void widgetDefaultSelected(SelectionEvent e) {
            // no handling for default selection
		}

		private void validate() {
			if (initing)
				return;
			setErrorMessage(null);
			setPageComplete(true);
			if (!validateName() || !validateJars() || !validateVersion()) {
				setPageComplete(false);
			}
		}

		private boolean validateJars() {
			if (getJSFLibrary().getArchiveFiles().isEmpty()) {
				setErrorMessage(Messages.JSFLibraryWizard_ValidateNoJars);
				return false;
			}
			return true;
		}

		private boolean validateVersion() {
			//FIXME: why isn't selection indesx correct???
//			if (cboVersions.getSelectionIndex() < 0) {
//				setErrorMessage("Choose the maximum JSF version supported if known.");
//				return false;
//			}
			return true;
		}

		private boolean validateName() {
			if (txtName.getText() == null
					|| txtName.getText().trim().equals("")) { //$NON-NLS-1$
				setErrorMessage(Messages.JSFLibraryWizard_ValidateNoLibraryName);
				return false;
			}
			String aName = txtName.getText().trim();
			if (isNew || (!isNew && !curLibrary.getName().equals(aName))) {
				if (isNameInRegistry(JSFCorePlugin.getDefault()
						.getJSFLibraryRegistry().getAllJSFLibraries(), aName)) {
					setErrorMessage(Messages.JSFLibraryWizard_ValidateExistingLibraryName);
					return false;
				}
			}
			return true;
		}

		private boolean isNameInRegistry(Collection c, String name) {
			Iterator it = c.iterator();
			while (it.hasNext()) {
				JSFLibrary lib = (JSFLibrary) it.next();
				if (lib.getName().equals(name)) {
					return true;
				}
			}
			return false;
		}
	}

}
