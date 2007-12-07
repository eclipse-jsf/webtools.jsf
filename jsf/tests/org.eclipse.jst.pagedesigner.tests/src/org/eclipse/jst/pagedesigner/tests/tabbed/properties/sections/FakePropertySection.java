package org.eclipse.jst.pagedesigner.tests.tabbed.properties.sections;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.pagedesigner.properties.DesignerPropertyTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * Fake section for testing.... remove
 * Binds to "type" attribute if present on an element
 *
 */
public class FakePropertySection extends AbstractPropertySection {

	private Text txt;
	private Attr typeAttr;
	/**
	 * Constructor
	 */
	public FakePropertySection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControls(final Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
	
		super.createControls(parent, tabbedPropertySheetPage);
		TabbedPropertySheetWidgetFactory factory = tabbedPropertySheetPage.getWidgetFactory();
		
		Composite outer = factory.createComposite(parent);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		gl.marginTop = 0;
		gl.marginBottom = 0;
		outer.setLayout(gl);
		//
		CLabel lbl = factory.createCLabel(outer, "TYPE:", SWT.NONE);
		lbl.setLayoutData(new GridData());
		
		txt = factory.createText(outer, "");
		GridData gd = new GridData();
		gd.verticalAlignment = SWT.TOP;
		gd.grabExcessHorizontalSpace = true;
		txt.setLayoutData(gd);
		
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Element node = DesignerPropertyTool.getElement(part, selection);
		if (node != null){
			typeAttr = node.getAttributeNode("type");
			if (typeAttr != null){
				txt.setText(typeAttr.getValue());
				txt.addKeyListener(new KeyListener(){

					public void keyPressed(KeyEvent e) {
						//
					}

					public void keyReleased(KeyEvent e) {
							typeAttr.setValue(typeAttr.getValue()+ new String(new char[]{e.character}));						
					}
					
				});
			}
		}
	}

}
