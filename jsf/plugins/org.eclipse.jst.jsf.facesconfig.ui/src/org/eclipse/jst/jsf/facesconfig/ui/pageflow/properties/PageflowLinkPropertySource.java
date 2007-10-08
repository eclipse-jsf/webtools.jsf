/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowValidation;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * The property source for Pageflow Link.
 * 
 * @author hmeng
 */

public class PageflowLinkPropertySource extends PageflowElementPropertySource {

	/**
	 * @param element
	 */
	public PageflowLinkPropertySource(PageflowElement element) {
		super(element);
	}

	public Object getPropertyValue(Object id) {
		Object result = super.getPropertyValue(id);
		if (Integer.parseInt((String) id) == PageflowPackage.PF_LINK__SOURCE
				|| Integer.parseInt((String) id) == PageflowPackage.PF_LINK__TARGET) {
			result = ((PageflowPage) result).getPath();
		}
		return result;
	}

	public void setPropertyValue(Object id, Object value) {
		if (Integer.parseInt((String) id) == PageflowPackage.PF_LINK__SOURCE) {
			((PageflowLink) element).setSourcePath((String) value);

		} else if (Integer.parseInt((String) id) == PageflowPackage.PF_LINK__TARGET) {
			((PageflowLink) element).setTargetPath((String) value);
		} else {
			super.setPropertyValue(id, value);
		}
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		// property descriptors
		List propertyDescriptors = new Vector();

		Iterator it;
		EClass cls = element.eClass();

		it = cls.getEAllAttributes().iterator();
		while (it.hasNext()) {
			EAttribute attr = (EAttribute) it.next();
			if (attr.isID()
					|| attr.getName().equalsIgnoreCase(PAGEFLOW_NAME)
					|| attr.getName().equalsIgnoreCase(PAGEFLOW_COMMENT)
					|| attr.getFeatureID() == PageflowPackage.PAGEFLOW__REFERENCE_LINK) {
				continue;
			}

			EDataType type = attr.getEAttributeType();
			if (type.getInstanceClass() == String.class) {
				PropertyDescriptor propertyDescriptor;

				propertyDescriptor = new TextPropertyDescriptor(Integer
						.toString(attr.getFeatureID()),
						getString(attr.getName()));

				if (propertyDescriptor != null) {
					propertyDescriptor
							.setLabelProvider(new PageflowLabelProvider());
					propertyDescriptors.add(propertyDescriptor);
				}

			} else if (type.getInstanceClass() == boolean.class) {
				PropertyDescriptor propertyDescriptor;
				propertyDescriptor = getBooleanTypePropertyDescriptor(attr);
				propertyDescriptor.setLabelProvider(new BooleanLabelProvider());
				propertyDescriptors.add(propertyDescriptor);
			}
		}
		it = cls.getEReferences().iterator();
		while (it.hasNext()) {
			EReference attr = (EReference) it.next();
			if (attr.getFeatureID() == PageflowPackage.PF_LINK__SOURCE
					|| attr.getFeatureID() == PageflowPackage.PF_LINK__TARGET) {

				PropertyDescriptor propertyDescriptor;

				propertyDescriptor = getExistingPagePathPropertyDescriptor(attr);

				if (propertyDescriptor != null) {
					propertyDescriptor
							.setLabelProvider(new PageflowLabelProvider());
					propertyDescriptors.add(propertyDescriptor);
				}
			}
		}

		return (IPropertyDescriptor[]) propertyDescriptors
				.toArray(new IPropertyDescriptor[] {});
	}

	/**
	 * The link's ends can be modified to another page existing in current pageflow.
	 * @param attr
	 * @return
	 */
	private PropertyDescriptor getExistingPagePathPropertyDescriptor(
			EReference attr) {
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(Integer
				.toString(attr.getFeatureID()), getString(attr.getName())) {
			public CellEditor createPropertyEditor(Composite parent) {
				CellEditor editor = new ExistingPagePathDialogCellEditor(
						parent, (Pageflow) element.eContainer());
				if (getValidator() != null) {
					editor.setValidator(getValidator());
				}
				return editor;
			}

			/**
			 * Returns the input validator for editing the property.
			 * 
			 * @return the validator used to verify correct values for this
			 *         property, or <code>null</code>
			 */
			protected ICellEditorValidator getValidator() {
				return new ICellEditorValidator() {
					public String isValid(Object value) {
						if (value != null) {
							if (!PageflowValidation.getInstance()
									.isExistedPage(
											((Pageflow) element.eContainer()),
											(String) value)) {
								// Pageflow.PageflowEditor.Alert.DNDResourceTitle
								// = Pageflow Creation Error
								// Pageflow.PageflowEditor.Alert.ExistingPage =
								// The web page {0} is already existed in
								// current PageFlow.
								EditorPlugin.getAlerts().info("Note",
										"Please select an existing page");
								// "Pageflow.PageflowEditor.Alert.DNDResourceTitle",
								// "Pageflow.PageflowEditor.Alert.ExistingPage",
								// (String) value);

								return EditorPlugin
										.getResourceString("Pageflow.PageflowEditor.Alert.ExistingPage");
							}
						}
						return null;
					}

				};
			}

		};
		return propertyDescriptor;
	}

}
