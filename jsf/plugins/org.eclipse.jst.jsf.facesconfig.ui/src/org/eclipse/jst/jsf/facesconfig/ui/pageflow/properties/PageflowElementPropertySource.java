/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.EditorResources;
import org.eclipse.jst.jsf.facesconfig.ui.IconResources;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.PageflowValidation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * This class is intended to be a generic property source for all the objects in
 * the application's model. It currently only supports String and boolean types,
 * and doesn't support reset or nested properties. It uses information in the
 * EMF EAttribute and EDataType classes to get the id, display name, and type
 * information.
 * 
 * @author Xiao-guang Zhang
 */
public class PageflowElementPropertySource implements IPropertySource {
	/** attribute name of "path" and "action", which have special property editor */
	private static final String PAGEFLOW_PATH = "path"; //$NON-NLS-1$    

	private static final String PAGEFLOW_NAME = "name"; //$NON-NLS-1$

	private static final String PAGEFLOW_COMMENT = "comment"; //$NON-NLS-1$

	private static final String PAGEFLOW_CONFIG_FILE = "configfile"; //$NON-NLS-1$

	/** label's prefix for attribute definition name */
	private static final String PAGEFLOW_MODEL_PREFIX = "Pageflow.Model.Attributes."; //$NON-NLS-1$

	/** pageflow elememt */
	private PageflowElement element;

	/** boolean value's label */
	private static final String P_VALUE_TRUE_LABEL = EditorResources
			.getInstance().getString("Pageflow.Label.True"); //$NON-NLS-1$

	private static final String P_VALUE_FALSE_LABEL = EditorResources
			.getInstance().getString("Pageflow.Label.False"); //$NON-NLS-1$

	/** Integer value for boolean type */
	private static final int P_VALUE_TRUE = 0;

	private static final int P_VALUE_FALSE = 1;

	static private class BooleanLabelProvider extends LabelProvider {
		public String getText(Object element) {
			String[] values = new String[] { P_VALUE_TRUE_LABEL,
					P_VALUE_FALSE_LABEL };
			return values[((Integer) element).intValue()];
		}

		public Image getImage(Object element) {
			return EditorPlugin
					.getDefault()
					.getImage(
							IconResources
									.getString("Pageflow.elementPropertySource.booleanValue")); //$NON-NLS-1$
		}
	}

	static private class PageflowLabelProvider extends LabelProvider {

		public Image getImage(Object element) {
			if (element instanceof String) {
				return EditorPlugin
						.getDefault()
						.getImage(
								IconResources
										.getString("Pageflow.elementPropertySource.textValue"));//$NON-NLS-1$
			} else if (element instanceof Integer) {
				return EditorPlugin
						.getDefault()
						.getImage(
								IconResources
										.getString("Pageflow.elementPropertySource.integralValue"));//$NON-NLS-1$
			} else {
				return EditorPlugin
						.getDefault()
						.getImage(
								IconResources
										.getString("Pageflow.elementPropertySource.genericValue"));//$NON-NLS-1$
			}
		}
	}

	/**
	 * constructor
	 * 
	 * @param element -
	 *            pageflow element model.
	 */
	public PageflowElementPropertySource(PageflowElement element) {
		this.element = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IPropertySource#getEditableValue()
	 */
	public Object getEditableValue() {
		return element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IPropertySource#getPropertyDescriptors()
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// property descriptors
		List propertyDescriptors = new Vector();

		if (element instanceof PageflowLink) {
			return getLinkPropertyDescriptors();
		}

		Iterator it;
		EClass cls = element.eClass();

		it = cls.getEAllAttributes().iterator();
		while (it.hasNext()) {
			EAttribute attr = (EAttribute) it.next();

			EDataType type = attr.getEAttributeType();
			if (attr.isID()) {
				// shouldn't be editable
			} else if (type.getInstanceClass() == String.class) {
				PropertyDescriptor propertyDescriptor;
				if (attr.getName().equalsIgnoreCase(PAGEFLOW_PATH)) {
					propertyDescriptor = getPagePathPropertyDescriptor(attr);
				} else if (attr.getName()
						.equalsIgnoreCase(PAGEFLOW_CONFIG_FILE)) {
					propertyDescriptor = null;
				} else {
					propertyDescriptor = new TextPropertyDescriptor(Integer
							.toString(attr.getFeatureID()),
							getString(PAGEFLOW_MODEL_PREFIX + attr.getName()));
				}
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

		return (IPropertyDescriptor[]) propertyDescriptors
				.toArray(new IPropertyDescriptor[] {});
	}

	/**
	 * @return
	 */
	private IPropertyDescriptor[] getLinkPropertyDescriptors() {
		// property descriptors
		List propertyDescriptors = new Vector();

		Iterator it;
		EClass cls = element.eClass();

		it = cls.getEAllAttributes().iterator();
		while (it.hasNext()) {
			EAttribute attr = (EAttribute) it.next();

			EDataType type = attr.getEAttributeType();
			if (attr.isID() || attr.getName().equalsIgnoreCase(PAGEFLOW_NAME)
					|| attr.getName().equalsIgnoreCase(PAGEFLOW_COMMENT)) {
				// shouldn't be editable
			} else if (type.getInstanceClass() == String.class) {
				PropertyDescriptor propertyDescriptor;

				propertyDescriptor = new TextPropertyDescriptor(Integer
						.toString(attr.getFeatureID()),
						getString(PAGEFLOW_MODEL_PREFIX + attr.getName()));

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

		return (IPropertyDescriptor[]) propertyDescriptors
				.toArray(new IPropertyDescriptor[] {});
	}

	/**
	 * get the property descriptor for the attribute "path" of page component
	 * 
	 * @param attr -
	 *            EAttribute
	 * @return
	 */
	private PropertyDescriptor getPagePathPropertyDescriptor(EAttribute attr) {
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(Integer
				.toString(attr.getFeatureID()), getString(PAGEFLOW_MODEL_PREFIX
				+ attr.getName())) {
			public CellEditor createPropertyEditor(Composite parent) {
				CellEditor editor = new PagePathDialogCellEditor(parent);
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
						if (((PageflowPage) element).getPath() != null
								&& value != null
								&& !((PageflowPage) element).getPath()
										.equalsIgnoreCase((String) value)) {
							if (PageflowValidation.getInstance().isExistedPage(
									((PageflowPage) element).getPageflow(),
									(String) value)) {
								// Pageflow.PageflowEditor.Alert.DNDResourceTitle
								// = Pageflow Creation Error
								// Pageflow.PageflowEditor.Alert.ExistingPage =
								// The web page {0} is already existed in
								// current PageFlow.
								EditorPlugin
										.getAlerts()
										.error(
												"Pageflow.PageflowEditor.Alert.DNDResourceTitle",
												"Pageflow.PageflowEditor.Alert.ExistingPage",
												(String) value);

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

	/**
	 * get the property descriptor for the attribute "action" of Action
	 * component
	 * 
	 * @param attr -
	 *            EAttribute
	 * @return
	 */
	private PropertyDescriptor getActionPropertyDescriptor(EAttribute attr) {
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(Integer
				.toString(attr.getFeatureID()), getString(PAGEFLOW_MODEL_PREFIX
				+ attr.getName())) {
			public CellEditor createPropertyEditor(Composite parent) {
				CellEditor editor = new ActionDialogCellEditor(parent);
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

						return null;
					}

				};
			}
		};
		return propertyDescriptor;
	}

	/**
	 * get the property descriptor for the attribute "outcome" of Link component
	 * 
	 * @param attr -
	 *            EAttribute
	 * @return
	 */
	private PropertyDescriptor getActionOutcomePropertyDescriptor(
			EAttribute attr) {
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(Integer
				.toString(attr.getFeatureID()), getString(PAGEFLOW_MODEL_PREFIX
				+ attr.getName())) {
			public CellEditor createPropertyEditor(Composite parent) {
				CellEditor editor = new ActionOutcomeDialogCellEditor(parent,
						element);
				if (getValidator() != null) {
					editor.setValidator(getValidator());
				}
				return editor;
			}
		};
		return propertyDescriptor;
	}

	/**
	 * get property descriptor for the attribute with the boolean type
	 * 
	 * @param attr -
	 *            EAttribute
	 * @return
	 */
	private PropertyDescriptor getBooleanTypePropertyDescriptor(EAttribute attr) {
		PropertyDescriptor propertyDescriptor;
		propertyDescriptor = new ComboBoxPropertyDescriptor(Integer
				.toString(attr.getFeatureID()), getString(PAGEFLOW_MODEL_PREFIX
				+ attr.getName()), new String[] { P_VALUE_TRUE_LABEL,
				P_VALUE_FALSE_LABEL });
		return propertyDescriptor;
	}

	/**
	 * Get a translated string from the resource locator.
	 */
	protected String getString(String key) {
		return EditorResources.getInstance().getString(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IPropertySource#getPropertyValue(Object)
	 */
	public Object getPropertyValue(Object id) {
		EStructuralFeature feature = element.eClass().getEStructuralFeature(
				Integer.parseInt((String) id));

		Object result = element.eGet(feature);

		if (result instanceof Boolean) {
			if (result == Boolean.TRUE) {
				result = new Integer(P_VALUE_TRUE);
			} else {
				result = new Integer(P_VALUE_FALSE);
			}
		}

		return result != null ? result : "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IPropertySource#isPropertySet(Object)
	 */
	public boolean isPropertySet(Object id) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IPropertySouce#resetPropertyValue(Object)
	 */
	public void resetPropertyValue(Object id) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IPropertySource#setPropertyValue(Object, Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		EStructuralFeature feature = element.eClass().getEStructuralFeature(
				Integer.parseInt((String) id));

		Object result = element.eGet(feature);
		// if the attribute is boolean type, the value should be changed from
		// string "true" or "false"
		// to Boolean.TRUE or Boolean.FALSE
		if (result instanceof Boolean) {
			if (value.equals(new Integer(P_VALUE_TRUE))) {
				value = Boolean.TRUE;
			} else {
				value = Boolean.FALSE;
			}
		}
		element.eSet(feature, value);
	}
}
