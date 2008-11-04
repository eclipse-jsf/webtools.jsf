package org.eclipse.jst.pagedesigner.itemcreation.customizer;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SimpleForm extends Dialog {

	private static final int RESET_ID = IDialogConstants.NO_TO_ALL_ID + 1;

	public String value = null;
	public String id = null;
	
	private Text valueField;

	private Text idField;

	public SimpleForm(Shell parentShell) {
		super(parentShell);
	}

	protected Control createDialogArea(Composite parent) {
		Composite comp = (Composite) super.createDialogArea(parent);

		GridLayout layout = (GridLayout) comp.getLayout();
		layout.numColumns = 2;

		Label valueLabel = new Label(comp, SWT.RIGHT);
		valueLabel.setText("Value: ");

		valueField = new Text(comp, SWT.SINGLE);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		valueField.setLayoutData(data);

		Label idLabel = new Label(comp, SWT.RIGHT);
		idLabel.setText("Id: ");

		idField = new Text(comp, SWT.SINGLE);
		data = new GridData(GridData.FILL_HORIZONTAL);
		idField.setLayoutData(data);

		return comp;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		createButton(parent, RESET_ID, "Reset All", false);
	}
	
	protected void okPressed() {
		setReturnCode(OK);
		value = valueField.getText();
		id = idField.getText();
		close();
	}

	protected void buttonPressed(int buttonId) {
		if (buttonId == RESET_ID) {
			valueField.setText("");
			idField.setText("");
		} else {
			super.buttonPressed(buttonId);
		}
	}
	
	public static void main(String[] args)
	{
		//
		SimpleForm test = new SimpleForm(null);
		test.open();
		
	}
}