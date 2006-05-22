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
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowMessages;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowElement;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPackage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
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

	static final String PAGEFLOW_NAME = "name"; //$NON-NLS-1$

	static final String PAGEFLOW_COMMENT = "comment"; //$NON-NLS-1$

	private static final String PAGEFLOW_CONFIG_FILE = "configfile"; //$NON-NLS-1$

	/** pageflow elememt */
	PageflowElement element;

	/** boolean value's label */
	private static final String P_VALUE_TRUE_LABEL = PageflowMessages.Pageflow_Label_True;

	private static final String P_VALUE_FALSE_LABEL = PageflowMessages.Pageflow_Label_False;

	/** Integer value for boolean type */
	private static final int P_VALUE_TRUE = 0;

	private static final int P_VALUE_FALSE = 1;

	static class BooleanLabelProvider extends LabelProvider {
		public String getText(Object element) {
			String[] values = new String[] { P_VALUE_TRUE_LABEL,
					P_VALUE_FALSE_LABEL };
			return values[((Integer) element).intValue()];
		}

		public Image getImage(Object element) {
			return EditorPlugin.getDefault().getImage(
					"facesconfig/Pageflow_BooleanValue.gif"); //$NON-NLS-1$
		}
	}

	static class PageflowLabelProvider extends LabelProvider {

		public Image getImage(Object element) {
			if (element instanceof String) {
				return EditorPlugin.getDefault().getImage(
						"facesconfig/Pageflow_TextValue.gif");//$NON-NLS-1$
			} else if (element instanceof Integer) {
				return EditorPlugin.getDefault().getImage(
						"facesconfig/Pageflow_IntegralValue.gif");//$NON-NLS-1$
			} else {
				return EditorPlugin.getDefault().getImage(
						"facesconfig/Pageflow_GenericValue.gif");//$NON-NLS-1$
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

		// if (element instanceof PageflowLink) {
		// return getLinkPropertyDescriptors();
		// }

		Iterator it;
		EClass cls = element.eClass();

		it = cls.getEAllAttributes().iterator();
		while (it.hasNext()) {
			EAttribute attr = (EAttribute) it.next();
			if (attr.isID()
					|| attr.getFeatureID() == PageflowPackage.PAGEFLOW__REFERENCE_LINK) {
				continue;
			}
			EDataType type = attr.getEAttributeType();
			if (type.getInstanceClass() == String.class) {
				PropertyDescriptor propertyDescriptor;
				if (attr.getName().equalsIgnoreCase(PAGEFLOW_PATH)) {
					propertyDescriptor = getPagePathPropertyDescriptor(attr);
				} else if (attr.getName()
						.equalsIgnoreCase(PAGEFLOW_CONFIG_FILE)) {
					propertyDescriptor = null;
				} else {
					propertyDescriptor = new TextPropertyDescriptor(Integer
							.toString(attr.getFeatureID()), getString(attr
							.getName()));
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
						.toString(attr.getFeatureID()), getString(attr
						.getName()));

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
				.toString(attr.getFeatureID()), getString(attr.getName())) {
			public CellEditor createPropertyEditor(Composite parent) {
				CellEditor editor = new PagePathDialogCellEditor(parent,
						(PageflowPage) element);
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
				.toString(attr.getFeatureID()), getString(attr.getName())) {
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
				.toString(attr.getFeatureID()), getString(attr.getName())) {
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
	PropertyDescriptor getBooleanTypePropertyDescriptor(EAttribute attr) {
		PropertyDescriptor propertyDescriptor;
		propertyDescriptor = new ComboBoxPropertyDescriptor(Integer
				.toString(attr.getFeatureID()), getString(attr.getName()),
				new String[] { P_VALUE_TRUE_LABEL, P_VALUE_FALSE_LABEL });
		return propertyDescriptor;
	}

	/**
	 * Get a translated string from the resource locator.
	 */
	protected String getString(String key) {
		if ("comment".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_comment;
		}

		if ("name".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_name;
		}

		if ("largeicon".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_largeicon;
		}

		if ("smallicon".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_smallicon;
		}
		
		if ("source".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_source;
		}
		
		if ("target".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_target;
		}

		if ("fromaction".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_fromaction;
		}

		if ("path".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_path;
		}
		if ("begin".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_begin;
		}
		if ("end".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_end;
		}
		if ("configfile".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_configfile;
		}
		if ("outcome".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_outcome;
		}
		if ("redirect".equals(key)) {
			return PageflowMessages.Pageflow_Model_Attributes_redirect;
		}

		return "";
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
