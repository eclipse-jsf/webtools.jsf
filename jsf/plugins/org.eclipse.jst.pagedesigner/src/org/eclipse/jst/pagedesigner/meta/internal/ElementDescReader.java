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
package org.eclipse.jst.pagedesigner.meta.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.meta.AttributeDescriptor;
import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Used by Sybase CMConfig model loader
 * @author mengbo
 * @deprecated
 */
public class ElementDescReader {
	private static Logger _log = PDPlugin.getLogger(ElementDescReader.class);

	private Map _definedCategoryMap = new HashMap();

	private Map _definedAttributeMap = new HashMap();

	private URL _url;

	private String[] _optimizedLocales = null;

	/**
	 * @param url
	 */
	public ElementDescReader(URL url) {
		this._url = url;
	}

	/**
	 * Read xml information and fill the map
	 * 
	 * @param map
	 * @throws ParserConfigurationException
	 * @throws FactoryConfigurationError
	 * @throws SAXException
	 * @throws IOException
	 */
	public void readElements(Map map) throws ParserConfigurationException,
			FactoryConfigurationError, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		
		InputStream stream = null; 
		Document doc = null;
		try
		{
			stream = this._url.openStream();
			doc = builder.parse(stream);
		}
		finally
		{
			ResourceUtils.ensureClosed(stream);
		}

		Element taglib = doc.getDocumentElement();
		String nameSpace = taglib.getAttribute("uri"); //$NON-NLS-1$

		NodeList list = doc.getElementsByTagName("tag"); //$NON-NLS-1$
		if (list != null && list.getLength() != 0) {
			int size = list.getLength();
			for (int i = 0; i < size; i++) {
				Element tag = (Element) list.item(i);
				ElementDescriptor desc = new ElementDescriptor();
				desc.setTagName(tag.getAttribute("name")); //$NON-NLS-1$
				desc.setNamespaceURI(nameSpace);

				// support for help context id.
				desc.setHelpContextID(tag.getAttribute("helpContextId")); //$NON-NLS-1$

				NodeList children = tag.getChildNodes();
				// first calculate category and refered category quantity
				NodeList cateNodes = tag.getElementsByTagName("category"); //$NON-NLS-1$
				NodeList referedCateNodes = tag
						.getElementsByTagName("referedcategory"); //$NON-NLS-1$
				int cateNum = 0;
				if (cateNodes != null) {
					cateNum += cateNodes.getLength();
				}
				if (referedCateNodes != null) {
					cateNum += referedCateNodes.getLength();
				}

				if (cateNum > 0) {
					int length = children.getLength();
					String[] cates = new String[cateNum];
					List attrList = new ArrayList();
					int realCate = 0;
					for (int j = 0; j < length; j++) {
						Node node = children.item(j);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) node;
							String tagName = element.getTagName();
							if ("category".equals(tagName) //$NON-NLS-1$
									|| "referedcategory".equals(tagName)) { //$NON-NLS-1$
								String categoryName = element
										.getAttribute("name"); //$NON-NLS-1$
								if (categoryName != null
										&& !"".equals(categoryName)) { //$NON-NLS-1$
									cates[realCate++] = categoryName;
									parseCategory(element, element, attrList);
								} else {
									Element definedCategory = handleReference(
											doc, element, true);
									String labelKey = definedCategory
											.getAttribute("displaylabel"); //$NON-NLS-1$
									if (labelKey != null
											&& !"".equals(labelKey)) { //$NON-NLS-1$
										cates[realCate++] = getValue(labelKey);
									} else {
										cates[realCate++] = calculateDisplayLabel(definedCategory);
									}

									parseCategory(element, definedCategory,
											attrList);
								}
							}
						}

					}

					// for sort categories
					for (int m = 0, len = cates.length; m < len; m++) {
						for (int j = m + 1; j < len; j++) {
							CategoryNameComparator.addPair(cates[m], cates[j]);
						}
					}
					desc.setAttributeDescriptors(attrList);
				}

				map.put(desc.getTagName(), desc);
			}
		}
	}

	private String calculateDisplayLabel(Element definedElement) {
		String label = definedElement.getAttribute("displaylabel"); //$NON-NLS-1$
		return getValue(label);
	}

	private void parseCategory(Element category, Element definedCategory,
			List attrList) {
		String cateLabel = calculateDisplayLabel(definedCategory);
		// if the category is a referedcategory tag
		if (category != definedCategory) {
			String labelKey = category.getAttribute("displaylabel"); //$NON-NLS-1$
			if (labelKey != null && !"".equals(labelKey)) { //$NON-NLS-1$
				cateLabel = getValue(labelKey);
			}
		}
		handleAttributes(definedCategory, cateLabel, attrList);
		if (category == definedCategory) {
			return;
		}

		// the category should be a referedcategory tag
		// include/add more attributes to category
		NodeList includes = category.getElementsByTagName("includeattrs"); //$NON-NLS-1$
		if (includes != null && includes.getLength() != 0) {
			Element includeAttrsTag = (Element) includes.item(0);
			handleAttributes(includeAttrsTag, cateLabel, attrList);

			// handle attribute override
			HashMap tempMap = new HashMap();
			Iterator itor = attrList.iterator();
			while (itor.hasNext()) {
				AttributeDescriptor adp = (AttributeDescriptor) itor.next();
				tempMap.put(adp.getAttributeName(), adp);
			}
			int listSize = attrList.size();
			int mapSize = tempMap.size();
			if (listSize != mapSize) {
				attrList.clear();
				Set set = tempMap.keySet();
				Iterator setor = set.iterator();
				while (setor.hasNext()) {
					String atName = (String) setor.next();
					AttributeDescriptor ad = (AttributeDescriptor) tempMap
							.get(atName);
					attrList.add(ad);
				}
			}

		}

		// exclude attributes from category
		NodeList excludes = category.getElementsByTagName("excludeattrs"); //$NON-NLS-1$
		if (excludes != null && excludes.getLength() != 0) {
			String displayNames = ((Element) excludes.item(0))
					.getAttribute("refs"); //$NON-NLS-1$
			StringTokenizer tokenizer = new StringTokenizer(displayNames, ", "); //$NON-NLS-1$

			while (tokenizer.hasMoreTokens()) {
				String name = tokenizer.nextToken();
				Iterator itr = attrList.iterator();
				while (itr.hasNext()) {
					IAttributeDescriptor atr = (IAttributeDescriptor) itr
							.next();
					String atrName = atr.getAttributeName();
					if (name.equals(atrName)) {
						attrList.remove(atr);
						break;
					}
				}
			}
		}
	}

	/**
	 * handle all attribute element and referedattribute element under the root
	 * element
	 * 
	 * @param root
	 * @param cateLabel
	 * @param list
	 */
	private void handleAttributes(Element root, String cateLabel, List attrList) {
		NodeList allNodes = root.getChildNodes();
		NodeList attrNodes = root.getElementsByTagName("attribute"); //$NON-NLS-1$
		NodeList referedattrNodes = root
				.getElementsByTagName("referedattribute"); //$NON-NLS-1$
		int attrNum = 0;
		if (attrNodes != null) {
			attrNum += attrNodes.getLength();
		}
		if (referedattrNodes != null) {
			attrNum += referedattrNodes.getLength();
		}

		if (attrNum > 0) {
			int incAttrLength = allNodes.getLength();
			for (int i = 0; i < incAttrLength; i++) {
				Node node = allNodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element incAttr = (Element) node;
					String tagName = incAttr.getTagName();
					if ("attribute".equals(tagName) //$NON-NLS-1$
							|| "referedattribute".equals(tagName)) { //$NON-NLS-1$
						String incAtrName = incAttr.getAttribute("name"); //$NON-NLS-1$
						Element refAttr = incAttr;
						if (incAtrName == null || "".equals(incAtrName)) { //$NON-NLS-1$
							incAttr = handleReference(root.getOwnerDocument(),
									incAttr, false);
						}
						AttributeDescriptor attrDesc = parseAttribute(
								cateLabel, incAttr);
						String overrideName = refAttr
								.getAttribute("overridename"); //$NON-NLS-1$
						if (overrideName != null
								&& !"".equalsIgnoreCase(overrideName)) { //$NON-NLS-1$
							attrDesc.setAttributeName(overrideName);
						}
						String ovDisplayLabel = refAttr
								.getAttribute("displaylabel"); //$NON-NLS-1$
						if (ovDisplayLabel != null
								&& !"".equalsIgnoreCase(ovDisplayLabel)) { //$NON-NLS-1$
							attrDesc.setLabelString(getValue(ovDisplayLabel));
						}
						attrList.add(attrDesc);
					}

				}

			}
		}
	}

	private Element handleReference(Document doc, Element refElment,
			boolean isCategory) {
		String refName = refElment.getAttribute("ref"); //$NON-NLS-1$
		if (isCategory) {
			if (_definedCategoryMap.get(refName) != null) {
				Element definedCategory = (Element) _definedCategoryMap
						.get(refName);
				return definedCategory;
			}
		} else {
			if (_definedAttributeMap.get(refName) != null) {
				Element definedAttribute = (Element) _definedAttributeMap
						.get(refName);
				return definedAttribute;
			}
		}

		NodeList catgs = null;
		if (isCategory) {
			NodeList defineCates = doc.getElementsByTagName("categories"); //$NON-NLS-1$
			Element firstCate = (Element) defineCates.item(0);
			catgs = firstCate.getElementsByTagName("category"); //$NON-NLS-1$
		} else {
			NodeList defineCates = doc.getElementsByTagName("attributes"); //$NON-NLS-1$
			Element firstCate = (Element) defineCates.item(0);
			catgs = firstCate.getElementsByTagName("attribute"); //$NON-NLS-1$
		}
		int cateLen = catgs.getLength();
		Element definedElement = null;
		for (int n = 0; n < cateLen; n++) {
			String cateName = ((Element) catgs.item(n)).getAttribute("name"); //$NON-NLS-1$
			if (refName.equals(cateName) && !"".equals(refName)) { //$NON-NLS-1$
				definedElement = (Element) catgs.item(n);
				break;
			}
		}
		if (isCategory) {
			_definedCategoryMap.put(refName, definedElement);
		} else {
			_definedAttributeMap.put(refName, definedElement);
		}
		return definedElement;
	}

	private AttributeDescriptor parseAttribute(String categoryName,
			Element attribute) {
		AttributeDescriptor attrDesc = new AttributeDescriptor();
		attrDesc.setCategory(categoryName);

		attrDesc.setAttributeName(attribute.getAttribute("name")); //$NON-NLS-1$
		attrDesc.setDescription(attribute.getAttribute("description")); //$NON-NLS-1$
		attrDesc.setValueType(attribute.getAttribute("type")); //$NON-NLS-1$
		attrDesc.setRequired(attribute.hasAttribute("required")); //$NON-NLS-1$
		attrDesc.setTypeParameter(attribute.getAttribute("typeparam")); //$NON-NLS-1$
		String labelKey = attribute.getAttribute("displaylabel"); //$NON-NLS-1$
		attrDesc.setLabelString(getValue(labelKey));

		NodeList optionNodes = attribute.getElementsByTagName("option"); //$NON-NLS-1$
		if (optionNodes != null && optionNodes.getLength() != 0) {
			HashMap optionMap = new HashMap();
			int opLength = optionNodes.getLength();
			String defaultValue = null;
			for (int m = 0; m < opLength; m++) {
				Element optNode = (Element) optionNodes.item(m);
				String key = optNode.getAttribute("key"); //$NON-NLS-1$
				String value = optNode.getAttribute("value"); //$NON-NLS-1$
				if (value == null || value.length() == 0) {
					value = key;
				}
				if (optNode.hasAttribute("default")) { //$NON-NLS-1$
					defaultValue = value;
				}
				optionMap.put(key, value);
			}
			attrDesc.setOptions(optionMap, defaultValue);
		}

		return attrDesc;
	}

	private String getValue(String key) {
		if (key != null && key.startsWith("%")) { //$NON-NLS-1$
			String cmStr = this._url.toString();
			String propBaseStr = cmStr.substring(0, cmStr.lastIndexOf(".")); //$NON-NLS-1$

			String[] localeOptions = LocaleFallback.fallBack(Locale
					.getDefault());
			String[] options = localeOptions;
			if (_optimizedLocales != null) {
				options = _optimizedLocales;
			}
			for (int i = 0, size = options.length; i < size; i++) {
				StringBuffer sb = new StringBuffer();
				sb.append(propBaseStr);
				sb.append(options[i]);
				sb.append(".properties"); //$NON-NLS-1$
				String str = sb.toString();

				ResourceBundle rb = null;
				try {
					URL propUrl = new URL(str);
					rb = new PropertyResourceBundle(propUrl.openStream());
				} catch (Exception e1) {
					// we don't handle the exception here,since it is in a
					// fallback route,it is possible of not exist
					// _log.info("Info.ElementDescReader.ReadPropertyFile",
					// str);
					continue;
				}
				if (_optimizedLocales == null) {
					setOptimizedLocales(localeOptions, i);
				}

				String rbKey = key.substring(1);
				String value = null;
				try {
					value = rb.getString(rbKey);
				} catch (Exception e) {
					_log.info("Info.ElementDescReader.ReadPropertyFile.Key", //$NON-NLS-1$
							rbKey, str, null);
					continue;
				}

				if (value != null) {
					return value;
				}

			}
			return null;
		}
		return key;
	}

	private void setOptimizedLocales(String[] originalLocales, int startPoint) {
		int size = originalLocales.length - startPoint;
		_optimizedLocales = new String[size];
		for (int i = 0; i < size; i++) {
			_optimizedLocales[i] = originalLocales[i + startPoint];
		}
	}
}
