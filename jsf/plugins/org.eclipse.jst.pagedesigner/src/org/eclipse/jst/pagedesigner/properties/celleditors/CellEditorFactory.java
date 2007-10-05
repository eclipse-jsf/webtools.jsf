/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.properties.celleditors;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ComboDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StyleComboDialogField;
import org.eclipse.jst.pagedesigner.css2.CSSUtil;
import org.eclipse.jst.pagedesigner.jsp.core.IJSPCoreConstants;
import org.eclipse.jst.pagedesigner.meta.IAttributeCellEditorFactory;
import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.jst.pagedesigner.meta.IValueType;
import org.eclipse.jst.pagedesigner.ui.dialogfields.ClasspathResourceButtonDialogField;
import org.eclipse.jst.pagedesigner.ui.dialogfields.ContextableResourceButtonDialogField;
import org.eclipse.jst.pagedesigner.ui.dialogfields.StyleButtonDialogField;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * 
 * @author mengbo
 */
public class CellEditorFactory implements IAttributeCellEditorFactory {
	private static String[] CURRENCYCODES = { "AED", // United Arab Emirates,
			// Dirhams //$NON-NLS-1$
			"AFA", // Afghanistan, Afghanis //$NON-NLS-1$
			"ALL", // Albania, Leke //$NON-NLS-1$
			"AMD", // Armenia, Drams //$NON-NLS-1$
			"ANG", // Netherlands Antilles, Guilders (also called Florins)
			// //$NON-NLS-1$
			"AOA", // Angola, Kwanza //$NON-NLS-1$
			"ARS", // Argentina, Pesos //$NON-NLS-1$
			"AUD", // Australia, Dollars //$NON-NLS-1$
			"AWG", // Aruba, Guilders (also called Florins) //$NON-NLS-1$
			"AZM", // Azerbaijan, Manats //$NON-NLS-1$
			"BAM", // Bosnia and Herzegovina, Convertible Marka //$NON-NLS-1$
			"BBD", // Barbados, Dollars //$NON-NLS-1$
			"BDT", // Bangladesh, Taka //$NON-NLS-1$
			"BGN", // Bulgaria, Leva //$NON-NLS-1$
			"BHD", // Bahrain, Dinars //$NON-NLS-1$
			"BIF", // Burundi, Francs //$NON-NLS-1$
			"BMD", // Bermuda, Dollars //$NON-NLS-1$
			"BND", // Brunei Darussalam, Dollars //$NON-NLS-1$
			"BOB", // Bolivia, Bolivianos //$NON-NLS-1$
			"BRL", // Brazil, Brazil Real //$NON-NLS-1$
			"BSD", // Bahamas, Dollars //$NON-NLS-1$
			"BTN", // Bhutan, Ngultrum //$NON-NLS-1$
			"BWP", // Botswana, Pulas //$NON-NLS-1$
			"BYR", // Belarus, Rubles //$NON-NLS-1$
			"BZD", // Belize, Dollars //$NON-NLS-1$
			"CAD", // Canada, Dollars //$NON-NLS-1$
			"CDF", // Congo/Kinshasa, Congolese Francs //$NON-NLS-1$
			"CHF", // Switzerland, Francs //$NON-NLS-1$
			"CLP", // Chile, Pesos //$NON-NLS-1$
			"CNY", // China, Yuan Renminbi //$NON-NLS-1$
			"COP", // Colombia, Pesos //$NON-NLS-1$
			"CRC", // Costa Rica, Colones //$NON-NLS-1$
			"CSD", // Serbia, Dinars //$NON-NLS-1$
			"CUP", // Cuba, Pesos //$NON-NLS-1$
			"CVE", // Cape Verde, Escudos //$NON-NLS-1$
			"CYP", // Cyprus, Pounds //$NON-NLS-1$
			"CZK", // Czech Republic, Koruny //$NON-NLS-1$
			"DJF", // Djibouti, Francs //$NON-NLS-1$
			"DKK", // Denmark, Kroner //$NON-NLS-1$
			"DOP", // Dominican Republic, Pesos //$NON-NLS-1$
			"DZD", // Algeria, Algeria Dinars //$NON-NLS-1$
			"EEK", // Estonia, Krooni //$NON-NLS-1$
			"EGP", // Egypt, Pounds //$NON-NLS-1$
			"ERN", // Eritrea, Nakfa //$NON-NLS-1$
			"ETB", // Ethiopia, Birr //$NON-NLS-1$
			"EUR", // Euro Member Countries, Euro //$NON-NLS-1$
			"FJD", // Fiji, Dollars //$NON-NLS-1$
			"FKP", // Falkland Islands (Malvinas), Pounds //$NON-NLS-1$
			"GBP", // United Kingdom, Pounds //$NON-NLS-1$
			"GEL", // Georgia, Lari //$NON-NLS-1$
			"GGP", // Guernsey, Pounds //$NON-NLS-1$
			"GHC", // Ghana, Cedis //$NON-NLS-1$
			"GIP", // Gibraltar, Pounds //$NON-NLS-1$
			"GMD", // Gambia, Dalasi //$NON-NLS-1$
			"GNF", // Guinea, Francs //$NON-NLS-1$
			"GTQ", // Guatemala, Quetzales //$NON-NLS-1$
			"GYD", // Guyana, Dollars //$NON-NLS-1$
			"HKD", // Hong Kong, Dollars //$NON-NLS-1$
			"HNL", // Honduras, Lempiras //$NON-NLS-1$
			"HRK", // Croatia, Kuna //$NON-NLS-1$
			"HTG", // Haiti, Gourdes //$NON-NLS-1$
			"HUF", // Hungary, Forint //$NON-NLS-1$
			"IDR", // Indonesia, Rupiahs //$NON-NLS-1$
			"ILS", // Israel, New Shekels //$NON-NLS-1$
			"IMP", // Isle of Man, Pounds //$NON-NLS-1$
			"INR", // India, Rupees //$NON-NLS-1$
			"IQD", // Iraq, Dinars //$NON-NLS-1$
			"IRR", // Iran, Rials //$NON-NLS-1$
			"ISK", // Iceland, Kronur //$NON-NLS-1$
			"JEP", // Jersey, Pounds //$NON-NLS-1$
			"JMD", // Jamaica, Dollars //$NON-NLS-1$
			"JOD", // Jordan, Dinars //$NON-NLS-1$
			"JPY", // Japan, Yen //$NON-NLS-1$
			"KES", // Kenya, Shillings //$NON-NLS-1$
			"KGS", // Kyrgyzstan, Soms //$NON-NLS-1$
			"KHR", // Cambodia, Riels //$NON-NLS-1$
			"KMF", // Comoros, Francs //$NON-NLS-1$
			"KPW", // Korea (North), Won //$NON-NLS-1$
			"KRW", // Korea (South), Won //$NON-NLS-1$
			"KWD", // Kuwait, Dinars //$NON-NLS-1$
			"KYD", // Cayman Islands, Dollars //$NON-NLS-1$
			"KZT", // Kazakhstan, Tenge //$NON-NLS-1$
			"LAK", // Laos, Kips //$NON-NLS-1$
			"LBP", // Lebanon, Pounds //$NON-NLS-1$
			"LKR", // Sri Lanka, Rupees //$NON-NLS-1$
			"LRD", // Liberia, Dollars //$NON-NLS-1$
			"LSL", // Lesotho, Maloti //$NON-NLS-1$
			"LTL", // Lithuania, Litai //$NON-NLS-1$
			"LVL", // Latvia, Lati //$NON-NLS-1$
			"LYD", // Libya, Dinars //$NON-NLS-1$
			"MAD", // Morocco, Dirhams //$NON-NLS-1$
			"MDL", // Moldova, Lei //$NON-NLS-1$
			"MGA", // Madagascar, Ariary //$NON-NLS-1$
			"MKD", // Macedonia, Denars //$NON-NLS-1$
			"MMK", // Myanmar (Burma), Kyats //$NON-NLS-1$
			"MNT", // Mongolia, Tugriks //$NON-NLS-1$
			"MOP", // Macau, Patacas //$NON-NLS-1$
			"MRO", // Mauritania, Ouguiyas //$NON-NLS-1$
			"MTL", // Malta, Liri //$NON-NLS-1$
			"MUR", // Mauritius, Rupees //$NON-NLS-1$
			"MVR", // Maldives (Maldive Islands), Rufiyaa //$NON-NLS-1$
			"MWK", // Malawi, Kwachas //$NON-NLS-1$
			"MXN", // Mexico, Pesos //$NON-NLS-1$
			"MYR", // Malaysia, Ringgits //$NON-NLS-1$
			"MZM", // Mozambique, Meticais //$NON-NLS-1$
			"NAD", // Namibia, Dollars //$NON-NLS-1$
			"NGN", // Nigeria, Nairas //$NON-NLS-1$
			"NIO", // Nicaragua, Cordobas //$NON-NLS-1$
			"NOK", // Norway, Krone //$NON-NLS-1$
			"NPR", // Nepal, Nepal Rupees //$NON-NLS-1$
			"NZD", // New Zealand, Dollars //$NON-NLS-1$
			"OMR", // Oman, Rials //$NON-NLS-1$
			"PAB", // Panama, Balboa //$NON-NLS-1$
			"PEN", // Peru, Nuevos Soles //$NON-NLS-1$
			"PGK", // Papua New Guinea, Kina //$NON-NLS-1$
			"PHP", // Philippines, Pesos //$NON-NLS-1$
			"PKR", // Pakistan, Rupees //$NON-NLS-1$
			"PLN", // Poland, Zlotych //$NON-NLS-1$
			"PYG", // Paraguay, Guarani //$NON-NLS-1$
			"QAR", // Qatar, Rials //$NON-NLS-1$
			"ROL", // Romania, Lei //$NON-NLS-1$
			"RUB", // Russia, Rubles //$NON-NLS-1$
			"RWF", // Rwanda, Rwanda Francs //$NON-NLS-1$
			"SAR", // Saudi Arabia, Riyals //$NON-NLS-1$
			"SBD", // Solomon Islands, Dollars //$NON-NLS-1$
			"SCR", // Seychelles, Rupees //$NON-NLS-1$
			"SDD", // Sudan, Dinars //$NON-NLS-1$
			"SEK", // Sweden, Kronor //$NON-NLS-1$
			"SGD", // Singapore, Dollars //$NON-NLS-1$
			"SHP", // Saint Helena, Pounds //$NON-NLS-1$
			"SIT", // Slovenia, Tolars //$NON-NLS-1$
			"SKK", // Slovakia, Koruny //$NON-NLS-1$
			"SLL", // Sierra Leone, Leones //$NON-NLS-1$
			"SOS", // Somalia, Shillings //$NON-NLS-1$
			"SPL", // Seborga, Luigini //$NON-NLS-1$
			"SRD", // Suriname, Dollars //$NON-NLS-1$
			"STD", // S?o Tome and Principe, Dobras //$NON-NLS-1$
			"SVC", // El Salvador, Colones //$NON-NLS-1$
			"SYP", // Syria, Pounds //$NON-NLS-1$
			"SZL", // Swaziland, Emalangeni //$NON-NLS-1$
			"THB", // Thailand, Baht //$NON-NLS-1$
			"TJS", // Tajikistan, Somoni //$NON-NLS-1$
			"TMM", // Turkmenistan, Manats //$NON-NLS-1$
			"TND", // Tunisia, Dinars //$NON-NLS-1$
			"TOP", // Tonga, Pa'anga //$NON-NLS-1$
			"TRL", // Turkey, Liras [being phased out] //$NON-NLS-1$
			"TRY", // Turkey, New Lira //$NON-NLS-1$
			"TTD", // Trinidad and Tobago, Dollars //$NON-NLS-1$
			"TVD", // Tuvalu, Tuvalu Dollars //$NON-NLS-1$
			"TWD", // Taiwan, New Dollars //$NON-NLS-1$
			"TZS", // Tanzania, Shillings //$NON-NLS-1$
			"UAH", // Ukraine, Hryvnia //$NON-NLS-1$
			"UGX", // Uganda, Shillings //$NON-NLS-1$
			"USD", // United States of America, Dollars //$NON-NLS-1$
			"UYU", // Uruguay, Pesos //$NON-NLS-1$
			"UZS", // Uzbekistan, Sums //$NON-NLS-1$
			"VEB", // Venezuela, Bolivares //$NON-NLS-1$
			"VND", // Viet Nam, Dong //$NON-NLS-1$
			"VUV", // Vanuatu, Vatu //$NON-NLS-1$
			"WST", // Samoa, Tala //$NON-NLS-1$
			"XAF", // Communaut�� Financi��re Africaine BEAC, Francs
			// //$NON-NLS-1$
			"XAG", // Silver, Ounces //$NON-NLS-1$
			"XAU", // Gold, Ounces //$NON-NLS-1$
			"XCD", // East Caribbean Dollars //$NON-NLS-1$
			"XDR", // International Monetary Fund (IMF) Special Drawing Rights
			// //$NON-NLS-1$
			"XOF", // Communaut�� Financi��re Africaine BCEAO, Francs
			// //$NON-NLS-1$
			"XPD", // Palladium Ounces //$NON-NLS-1$
			"XPF", // Comptoirs Fran?ais du Pacifique Francs //$NON-NLS-1$
			"XPT", // Platinum, Ounces //$NON-NLS-1$
			"YER", // Yemen, Rials //$NON-NLS-1$
			"ZAR", // South Africa, Rand //$NON-NLS-1$
			"ZMK", // Zambia, Kwacha //$NON-NLS-1$
			"ZWD" // Zimbabwe, Zimbabwe Dollars //$NON-NLS-1$
	};

	public CellEditor createCellEditor(Composite parent,
			IAttributeDescriptor attr, Element element) {
		String type = attr.getValueType();

		if (IValueType.ENUMERATED.equalsIgnoreCase(type)) {
			Map map = new HashMap(attr.getOptions());
			String defaultValue = attr.getDefaultValue();
			if (defaultValue == null) {
				return LabeledComboBoxCellEditor.newInstance(parent, map,
						SWT.NONE);
			}
            return LabeledStyleComboCellEditor.newInstance(parent, map,
            		defaultValue, SWT.NONE);
		} else if (IValueType.LOCALE.equalsIgnoreCase(type)) {
			Map map = new HashMap();
			Locale[] locales = Locale.getAvailableLocales();
			for (int i = 0, size = locales.length; i < size; i++) {
				map.put(locales[i].toString(), locales[i].toString());
			}
			return LabeledComboBoxCellEditor.newInstance(parent, map, SWT.NONE);
		} else if (IValueType.TIMEZONE.equalsIgnoreCase(type)) {
			Map map = new HashMap();
			String[] ids = TimeZone.getAvailableIDs();
			for (int i = 0, size = ids.length; i < size; i++) {
				map.put(ids[i], ids[i]);
			}
			return LabeledComboBoxCellEditor.newInstance(parent, map, SWT.NONE);
		} else if (IValueType.RELATIVEPATH.equalsIgnoreCase(type)
				|| IValueType.WEBPATH.equalsIgnoreCase(type)) {
			IProject project = getProject(element);
			if (project != null) {
				ResourceDialogCellEditor cellEditor = new ResourceDialogCellEditor(
						parent);
				cellEditor.setSuffixs(attr.getParameterByName(
						IAttributeDescriptor.PARAMETER_SUFFIX).split(";"));
				cellEditor
						.setSeparator(attr
								.getParameterByName(IAttributeDescriptor.PARAMETER_SEPARATOR));
				cellEditor.setProject(project);
				cellEditor.setReferredFile(getFile(element));
				if ("".equalsIgnoreCase(cellEditor.getSeparator())) {
					cellEditor.setResourceDescription(ResourceBoundle
							.getString("FileCellEditor.Msg"));
				} else {
					cellEditor.setResourceDescription(ResourceBoundle
							.getString("FileCellEditor.Msg1"));
				}
				if (IValueType.WEBPATH.equalsIgnoreCase(type)) {
					cellEditor.setWebPath(true);
				}

				if (IJSPCoreConstants.TAG_DIRECTIVE_INCLUDE.equals(element
						.getLocalName())
						|| IJSPCoreConstants.TAG_INCLUDE.equals(element
								.getLocalName())) {
					cellEditor.setTransformJSPURL(false);
				}
				return cellEditor;
			}
		} else if (IValueType.CLASSPATH_RESOURCE.equalsIgnoreCase(type)) {
			return new LoadbundleSelectionCellEditor(parent,
					getProject(element));
		} else if (IValueType.CSSID.equalsIgnoreCase(type)) {
		    // TODO: missing case?
		} else if (IValueType.CSSCLASS.equalsIgnoreCase(type)) {
			String cssclasses[] = CSSUtil.getCSSClasses(element
					.getOwnerDocument());
			Map map = new HashMap();
			if (cssclasses != null) {
				for (int i = 0; i < cssclasses.length; i++) {
					map.put(cssclasses[i], cssclasses[i]);
				}
			}
			return LabeledComboBoxCellEditor.newInstance(parent, map, SWT.NONE);
		} else if (IValueType.BOOLEAN.equalsIgnoreCase(type)) {
			String defaultValue = attr
					.getParameterByName(IAttributeDescriptor.PARAMETER_DEFAULT);
			Map booleanMap = new HashMap();
			booleanMap.put(Boolean.TRUE.toString(), Boolean.TRUE.toString());
			booleanMap.put(Boolean.FALSE.toString(), Boolean.FALSE.toString());
			if (defaultValue == null) {
				return LabeledComboBoxCellEditor.newInstance(parent,
						booleanMap, SWT.NONE);
			}
            return LabeledStyleComboCellEditor.newInstance(parent,
            		booleanMap, defaultValue, SWT.NONE);
		} else if (IValueType.CSSSTYLE.equalsIgnoreCase(type)) {
			String param = attr
					.getParameterByName(IAttributeDescriptor.PARAMETER_STYLE);
			if (!param.equalsIgnoreCase("STYLE")) {
				return null;
			}
			CSSDialogCellEditor cellEditor = new CSSDialogCellEditor(parent,
					(IDOMElement) element);
			return cellEditor;
		} else if (IValueType.NAMED_BOOLEAN.equalsIgnoreCase(type)) {
			return NamedBooleanCellEditor.newInstance(parent, SWT.NONE,
					(IDOMElement) element, attr);
		} else if (IValueType.CURRENCYCODE.equalsIgnoreCase(type)) {
			Map map = new HashMap();
			for (int i = 0, n = CURRENCYCODES.length; i < n; i++) {
				map.put(CURRENCYCODES[i], CURRENCYCODES[i]);
			}

			return LabeledComboBoxCellEditor.newInstance(parent, map, SWT.NONE);
		}

		// if there is no type or type unknonw, then we just return null. and
		// system will
		// create default (text cell editor).
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.meta.IAttributeCellEditorFactory#createDialogField(org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor,
	 *      org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public DialogField createDialogField(IAttributeDescriptor attr) {
		String type = attr.getValueType();

		if (IValueType.ENUMERATED.equalsIgnoreCase(type)
				|| IValueType.LOCALE.equalsIgnoreCase(type)
				|| IValueType.TIMEZONE.equalsIgnoreCase(type)) {
			Map map = new HashMap();
			if (IValueType.ENUMERATED.equalsIgnoreCase(type)) {
				map = attr.getOptions();
			} else if (IValueType.LOCALE.equalsIgnoreCase(type)) {
				Locale[] locales = Locale.getAvailableLocales();
				for (int i = 0, size = locales.length; i < size; i++) {
					map.put(locales[i].toString(), locales[i].toString());
				}
			} else {
				String[] ids = TimeZone.getAvailableIDs();
				for (int i = 0, size = ids.length; i < size; i++) {
					map.put(ids[i], ids[i]);
				}
			}
			if (map != null && !map.isEmpty()) {
				String defaultValue = attr.getDefaultValue();
				if (defaultValue == null || "".equals(defaultValue)) {
					attr
							.getParameterByName(IAttributeDescriptor.PARAMETER_DEFAULT);
				}
				StyleComboDialogField field = new StyleComboDialogField(
						SWT.NONE);
				field.setDefaultValue(defaultValue);
				field.setLabelText(attr.getLabelString());
				field.setEntryMap(new TreeMap(map));
				field.setRequired(attr.isRequired());
				field.setToolTip(attr.getDescription());
				return field;
			}
			// eles the config is incorrect. fall through, will return null.
		} else if (IValueType.RELATIVEPATH.equalsIgnoreCase(type)) {
			String param = attr.getTypeParameter();
			ContextableResourceButtonDialogField field = new ContextableResourceButtonDialogField();
			field.setLabelText(attr.getLabelString());
			if (param != null) {
				field.setSuffixs(attr.getParameterByName(
						IAttributeDescriptor.PARAMETER_SUFFIX).split(";"));
			}
			field.setResourceDescription(ResourceBoundle
					.getString("FileCellEditor.Msg"));
			field.setRequired(attr.isRequired());
			field.setToolTip(attr.getDescription());
			return field;
		} else if (IValueType.WEBPATH.equalsIgnoreCase(type)) {
			String param = attr.getTypeParameter();
			ContextableResourceButtonDialogField field = new ContextableResourceButtonDialogField();
			field.setLabelText(attr.getLabelString());
			if (param != null) {
				field.setSuffixs(attr.getParameterByName(
						IAttributeDescriptor.PARAMETER_SUFFIX).split(";"));
				field
						.setSeparator(attr
								.getParameterByName(IAttributeDescriptor.PARAMETER_SEPARATOR));
			}
			if ("".equalsIgnoreCase(field.getSeparator())) {
				field.setResourceDescription(ResourceBoundle
						.getString("FileCellEditor.Msg"));
			} else {
				field.setResourceDescription(ResourceBoundle
						.getString("FileCellEditor.Msg1"));
			}
			field.setWebPath(true);
			field.setRequired(attr.isRequired());
			field.setToolTip(attr.getDescription());
			return field;
		} else if (IValueType.CLASSPATH_RESOURCE.equals(type)) {
			ClasspathResourceButtonDialogField field = new ClasspathResourceButtonDialogField();
			field.setRequired(attr.isRequired());
			field.setToolTip(attr.getDescription());
			return field;
		} else if (IValueType.BOOLEAN.equalsIgnoreCase(type)) {
			String defaultValue = attr
					.getParameterByName(IAttributeDescriptor.PARAMETER_DEFAULT);
			StyleComboDialogField field = new StyleComboDialogField(SWT.NONE);
			TreeMap map = new TreeMap();
			map.put("", "");
			map.put(Boolean.FALSE.toString(), Boolean.FALSE.toString());
			map.put(Boolean.TRUE.toString(), Boolean.TRUE.toString());
			field.setEntryMap(map);
			field.setDefaultValue(defaultValue);
			field.setLabelText(attr.getLabelString());
			field.setRequired(attr.isRequired());
			field.setToolTip(attr.getDescription());
			return field;
		} else if (IValueType.CSSSTYLE.equalsIgnoreCase(type)) {
			String param = attr
					.getParameterByName(IAttributeDescriptor.PARAMETER_STYLE);
			if (!"STYLE".equalsIgnoreCase(param)) {
				return null;
			}
			StyleButtonDialogField field = new StyleButtonDialogField();
			field.setRequired(attr.isRequired());
			field.setToolTip(attr.getDescription());
			return field;
		} else if (IValueType.CURRENCYCODE.equalsIgnoreCase(type)) {
			ComboDialogField field = new ComboDialogField(SWT.NONE);
			field.setLabelText(attr.getLabelString());
			field.setItems(CURRENCYCODES);
			field.setRequired(attr.isRequired());
			field.setToolTip(attr.getDescription());
			return field;
		}

		// if there is no type or type unknonw, then we just return null. and
		// system will
		// create default (text cell editor).
		return null;
	}

	private IProject getProject(Element element) {
		if (element instanceof IDOMElement) {
			IDOMModel model = ((IDOMElement) element).getModel();
			IFile file = StructuredModelUtil.getFileFor(model);
			if (file != null) {
				return file.getProject();
			}
		}
		return null;
	}

	public String[] getSupportedValueTypes() {
		return null;
	}

	private IFile getFile(Element element) {
		if (element instanceof IDOMElement) {
			IDOMModel model = ((IDOMElement) element).getModel();
			IFile file = StructuredModelUtil.getFileFor(model);
			return file;
		}
		return null;
	}
}
