/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class FacesConfigFactoryImplForWriteRenderKitTwoFiles extends TestCase {
	IProject project = null;

	FacesConfigArtifactEdit edit = null;

	FacesConfigArtifactEdit edit1 = null;
	String desc = "description";
	String dispName = "display-name";
	String icon = "large-icon";
	String renderKitId = "render-kit-id";
	String renderKitClass = "render-kit-class";
	String rendererFamily = "component-family";
	String rendererClass = "renderer-class";
	String rendererType = "renderer-type";
	String rendererExtension = "renderer-extension";
	String rendererIcon = "renderer-icon";
	String rendDispName = "renderer-display-name";
	String rendIcon = "renderer-icon";
	// Attribute related variables
	String attrDispName = "attribute-display-name";
	String attrIcon = "attribute-icon";

	public FacesConfigFactoryImplForWriteRenderKitTwoFiles(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}

	public void testWriteRenderKit() {

		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");

			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();

				RenderKitType newApplication = facesConfigFactory
						.createRenderKitType();

				DisplayNameType actionList = facesConfigFactory
						.createDisplayNameType();
				actionList.setTextContent(dispName);
				newApplication.getDisplayName().add(actionList);

				IconType iconType = facesConfigFactory.createIconType();
				LargeIconType largeIconType = facesConfigFactory.createLargeIconType();
				largeIconType.setTextContent(icon);
				iconType.setLargeIcon(largeIconType);
				newApplication.getIcon().add(iconType);

				RenderKitIdType renderKitIdType = facesConfigFactory.createRenderKitIdType();
				renderKitIdType.setTextContent(renderKitId);
				newApplication.setRenderKitId(renderKitIdType);
				
				RenderKitClassType renderKitClassType = facesConfigFactory.createRenderKitClassType();
				renderKitClassType.setTextContent(renderKitClass);
				newApplication.setRenderKitClass(renderKitClassType);

				RendererType rendererT = facesConfigFactory
						.createRendererType();

				DisplayNameType renDispName = facesConfigFactory
						.createDisplayNameType();
				renDispName.setTextContent(rendDispName);
				rendererT.getDisplayName().add(renDispName);

				IconType rendIconType = facesConfigFactory.createIconType();
				LargeIconType rendLargeIconType = facesConfigFactory.createLargeIconType();
				rendLargeIconType.setTextContent(rendIcon);
				rendIconType.setLargeIcon(rendLargeIconType);
				rendererT.getIcon().add(rendIconType);
				
				ComponentFamilyType componentFamilyType = facesConfigFactory.createComponentFamilyType();
				componentFamilyType.setTextContent(rendererFamily);
				rendererT.setComponentFamily(componentFamilyType);
				
				RendererTypeType rendererTypeType = facesConfigFactory.createRendererTypeType();
				rendererTypeType.setTextContent(rendererType);
				rendererT.setRendererType(rendererTypeType);
				
				RendererClassType rendererClassType = facesConfigFactory.createRendererClassType();
				rendererClassType.setTextContent(rendererClass);
				rendererT.setRendererClass(rendererClassType);

				AttributeType attrType = facesConfigFactory
						.createAttributeType();

				DisplayNameType attributeDispName = facesConfigFactory
						.createDisplayNameType();
				attributeDispName.setTextContent(attrDispName);
				attrType.getDisplayName().add(attributeDispName);

				IconType attrIconType = facesConfigFactory.createIconType();
				LargeIconType attrLargeIconType = facesConfigFactory.createLargeIconType();
				attrLargeIconType.setTextContent(attrIcon);
				attrIconType.setLargeIcon(attrLargeIconType);
				attrType.getIcon().add(attrIconType);

				AttributeNameType attributeNameType = facesConfigFactory.createAttributeNameType();
				attributeNameType.setTextContent("attribute-name");
				attrType.setAttributeName(attributeNameType);

				AttributeClassType attributeClassType = facesConfigFactory.createAttributeClassType();
				attributeClassType.setTextContent("attribute-class");
				attrType.setAttributeClass(attributeClassType);

				DefaultValueType defaultValueType = facesConfigFactory.createDefaultValueType();
				defaultValueType.setTextContent("attribute-defaulat-value");
				attrType.setDefaultValue(defaultValueType);

				SuggestedValueType suggestedValueType = facesConfigFactory.createSuggestedValueType();
				suggestedValueType.setTextContent("attribute-suggested-value");
				attrType.setSuggestedValue(suggestedValueType);

				rendererT.getAttribute().add(attrType);
				newApplication.getRenderer().add(rendererT);

				edit.getFacesConfig().getRenderKit().add(newApplication);
				edit.save(null);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}

		String result = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList names = renderer.getDisplayName();
					for (int j = 0; j < names.size(); j++) {
						DisplayNameType displayName = (DisplayNameType) names
								.get(j);
						result = displayName.getTextContent();
						assertEquals(dispName, result);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}
		// icon
		String resultIcon = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList icons = renderer.getIcon();
					for (int j = 0; j < icons.size(); j++) {
						IconType displayName = (IconType) icons.get(j);
						resultIcon = displayName.getLargeIcon().getTextContent();
						assertEquals(icon, resultIcon);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// render-kit-id
		String resultRenderKitId = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					resultRenderKitId = renderer.getRenderKitId().getTextContent();
					assertEquals(renderKitId, resultRenderKitId);
					break;
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// renderKitClass

		// render-kit-id
		String resultrenderKitClass = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					resultrenderKitClass = renderer.getRenderKitClass().getTextContent();
					assertEquals(renderKitClass, resultrenderKitClass);
					break;
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// RENDERER PART
		String rendererResult = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList names = rendererType.getDisplayName();
						for (int k = 0; k < names.size(); k++) {
							DisplayNameType displayName = (DisplayNameType) names
									.get(k);
							rendererResult = displayName.getTextContent();
							assertEquals(rendDispName,
									rendererResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}
		// Renderer - icon
		String rendererIconResult = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList icons = rendererType.getIcon();
						for (int k = 0; k < icons.size(); k++) {
							IconType icon = (IconType) icons.get(k);
							rendererIconResult = icon.getLargeIcon().getTextContent();
							assertEquals(rendIcon, rendererIconResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// renderer--componentFmaily
		String rendererComponentFmaily = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						rendererComponentFmaily = rendererType.getComponentFamily().getTextContent();
						assertEquals(rendererFamily, rendererComponentFmaily);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// renderer-rendererType
		String rendererTyp = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						rendererTyp = rendererType.getRendererType().getTextContent();
						assertEquals("renderer-type", rendererTyp);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// renderer-rendererClass
		String rendererClassResult = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						rendererClassResult = rendererType.getRendererClass().getTextContent();
						assertEquals(rendererClass, rendererClassResult);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// THE FOLLOWING DEALS WITH RenderKit-->Renderer-->Attribute
		// display name
		String displayNameResult = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							EList names = attTyp.getDisplayName();
							for (int z = 0; z < names.size(); z++) {
								DisplayNameType displayName = (DisplayNameType) names
										.get(k);
								displayNameResult = displayName.getTextContent();
								assertEquals(attrDispName, displayNameResult);
								break;
							}
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// icon
		String iconResult = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							EList icons = attTyp.getIcon();
							for (int z = 0; z < icons.size(); z++) {
								IconType icon = (IconType) icons.get(k);
								iconResult = icon.getLargeIcon().getTextContent();
								assertEquals(attrIcon, iconResult);
								break;
							}
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// attributeNaame
		String attributeNameResult = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							attributeNameResult = attTyp.getAttributeName().getTextContent();
							assertEquals("attribute-name", attributeNameResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// attributeClass
		String attributeClassResult = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							attributeClassResult = attTyp.getAttributeClass().getTextContent();
							assertEquals("attribute-class",
									attributeClassResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// defaultValue
		String defaultValueResult = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							defaultValueResult = attTyp.getDefaultValue().getTextContent();
							assertEquals("attribute-defaulat-value",
									defaultValueResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

		// suggestedValue
		String suggestedValueResult = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList RendereKitList = edit.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							suggestedValueResult = attTyp.getSuggestedValue().getTextContent();
							assertEquals("attribute-suggested-value",
									suggestedValueResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}

	}

	public void testWriteRenderKitToFileOne() {
		// IProject project = WizardUtil.getTestProject();

		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();

				RenderKitType newApplication = facesConfigFactory
						.createRenderKitType();

				DisplayNameType actionList = facesConfigFactory
						.createDisplayNameType();
				actionList.setTextContent(dispName);
				newApplication.getDisplayName().add(actionList);

				IconType iconType = facesConfigFactory.createIconType();
				LargeIconType largeIconType = facesConfigFactory.createLargeIconType();
				largeIconType.setTextContent(icon);
				iconType.setLargeIcon(largeIconType);
				newApplication.getIcon().add(iconType);

				RenderKitIdType renderKitIdType = facesConfigFactory.createRenderKitIdType();
				renderKitIdType.setTextContent(renderKitId);
				newApplication.setRenderKitId(renderKitIdType);
				
				RenderKitClassType renderKitClassType = facesConfigFactory.createRenderKitClassType();
				renderKitClassType.setTextContent(renderKitClass);
				newApplication.setRenderKitClass(renderKitClassType);

				RendererType rendererT = facesConfigFactory
						.createRendererType();

				DisplayNameType renDispName = facesConfigFactory
						.createDisplayNameType();
				renDispName.setTextContent(rendDispName);
				rendererT.getDisplayName().add(renDispName);

				IconType rendIconType = facesConfigFactory.createIconType();
				LargeIconType rendLargeIconType = facesConfigFactory.createLargeIconType();
				rendLargeIconType.setTextContent(rendIcon);
				rendIconType.setLargeIcon(rendLargeIconType);
				rendererT.getIcon().add(rendIconType);
				
				ComponentFamilyType componentFamilyType = facesConfigFactory.createComponentFamilyType();
				componentFamilyType.setTextContent(rendererFamily);
				rendererT.setComponentFamily(componentFamilyType);
				
				RendererTypeType rendererTypeType = facesConfigFactory.createRendererTypeType();
				rendererTypeType.setTextContent(rendererType);
				rendererT.setRendererType(rendererTypeType);
				
				RendererClassType rendererClassType = facesConfigFactory.createRendererClassType();
				rendererClassType.setTextContent(rendererClass);
				rendererT.setRendererClass(rendererClassType);

				// The following item is not done since it takes
				// any type and getting a specific type is not possible.
				// RendererExtensionType

				AttributeType attrType = facesConfigFactory
						.createAttributeType();

				DisplayNameType attributeDispName = facesConfigFactory
						.createDisplayNameType();
				attributeDispName.setTextContent(dispName);
				attrType.getDisplayName().add(attributeDispName);

				IconType attrIconType = facesConfigFactory.createIconType();
				LargeIconType attrLargeIconType = facesConfigFactory.createLargeIconType();
				attrLargeIconType.setTextContent(attrIcon);
				attrIconType.setLargeIcon(attrLargeIconType);
				attrType.getIcon().add(attrIconType);

				AttributeNameType attributeNameType = facesConfigFactory.createAttributeNameType();
				attributeNameType.setTextContent("attribute-name");
				attrType.setAttributeName(attributeNameType);

				AttributeClassType attributeClassType = facesConfigFactory.createAttributeClassType();
				attributeClassType.setTextContent("attribute-class");
				attrType.setAttributeClass(attributeClassType);

				DefaultValueType defaultValueType = facesConfigFactory.createDefaultValueType();
				defaultValueType.setTextContent("attribute-defaulat-value");
				attrType.setDefaultValue(defaultValueType);

				SuggestedValueType suggestedValueType = facesConfigFactory.createSuggestedValueType();
				suggestedValueType.setTextContent("attribute-suggested-value");
				attrType.setSuggestedValue(suggestedValueType);

				rendererT.getAttribute().add(attrType);
				// rendererT.getRendererExtension().add(rendererExtension);
				newApplication.getRenderer().add(rendererT);

				edit1.getFacesConfig().getRenderKit().add(newApplication);
				edit1.save(null);

			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		String result = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList names = renderer.getDisplayName();
					for (int j = 0; j < names.size(); j++) {
						DisplayNameType displayName = (DisplayNameType) names
								.get(j);
						result = displayName.getTextContent();
						assertEquals(dispName, result);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		// icon
		String resultIcon = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList icons = renderer.getIcon();
					for (int j = 0; j < icons.size(); j++) {
						IconType displayName = (IconType) icons.get(j);
						resultIcon = displayName.getLargeIcon().getTextContent();
						assertEquals(icon, resultIcon);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// render-kit-id
		String resultRenderKitId = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					resultRenderKitId = renderer.getRenderKitId().getTextContent();
					assertEquals(renderKitId, resultRenderKitId);
					break;
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// renderKitClass

		// render-kit-id
		String resultrenderKitClass = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					resultrenderKitClass = renderer.getRenderKitClass().getTextContent();
					assertEquals(renderKitClass, resultrenderKitClass);
					break;
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// RENDERER PART
		String rendererResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList names = rendererType.getDisplayName();
						for (int k = 0; k < names.size(); k++) {
							DisplayNameType displayName = (DisplayNameType) names
									.get(k);
							rendererResult = displayName.getTextContent();
							assertEquals("renderer-display-name",
									rendererResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		// Renderer - icon
		String rendererIconResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList icons = rendererType.getIcon();
						for (int k = 0; k < icons.size(); k++) {
							IconType icon = (IconType) icons.get(k);
							rendererIconResult = icon.getLargeIcon().getTextContent();
							assertEquals(rendIcon, rendererIconResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// renderer--componentFmaily
		String rendererComponentFmaily = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						rendererComponentFmaily = rendererType.getComponentFamily().getTextContent();
						assertEquals(rendererFamily, rendererComponentFmaily);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// renderer-rendererType
		String rendererTyp = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						rendererTyp = rendererType.getRendererType().getTextContent();
						assertEquals("renderer-type", rendererTyp);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// renderer-rendererClass
		String rendererClassResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						rendererClassResult = rendererType.getRendererClass().getTextContent();
						assertEquals(rendererClass, rendererClassResult);
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// THE FOLLOWING DEALS WITH RenderKit-->Renderer-->Attribute
		// display name
		String displayNameResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							EList names = attTyp.getDisplayName();
							for (int z = 0; z < names.size(); z++) {
								DisplayNameType displayName = (DisplayNameType) names
										.get(k);
								displayNameResult = displayName.getTextContent();
								assertEquals(dispName, displayNameResult);
								break;
							}
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// icon
		String iconResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							EList icons = attTyp.getIcon();
							for (int z = 0; z < icons.size(); z++) {
								IconType icon = (IconType) icons.get(k);
								iconResult = icon.getLargeIcon().getTextContent();
								assertEquals(attrIcon, iconResult);
								break;
							}
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// attributeNaame
		String attributeNameResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							attributeNameResult = attTyp.getAttributeName().getTextContent();
							assertEquals("attribute-name", attributeNameResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// attributeClass
		String attributeClassResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							attributeClassResult = attTyp.getAttributeClass().getTextContent();
							assertEquals("attribute-class",
									attributeClassResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// defaultValue
		String defaultValueResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							defaultValueResult = attTyp.getDefaultValue().getTextContent();
							assertEquals("attribute-defaulat-value",
									defaultValueResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

		// suggestedValue
		String suggestedValueResult = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList RendereKitList = edit1.getFacesConfig().getRenderKit();
				for (int i = 0; i < RendereKitList.size(); i++) {
					RenderKitType renderer = (RenderKitType) RendereKitList
							.get(i);
					EList rendererList = renderer.getRenderer();
					for (int j = 0; j < rendererList.size(); j++) {
						RendererType rendererType = (RendererType) rendererList
								.get(j);
						EList attribute = rendererType.getAttribute();
						for (int k = 0; k < attribute.size(); k++) {
							AttributeType attTyp = (AttributeType) attribute
									.get(k);
							suggestedValueResult = attTyp.getSuggestedValue().getTextContent();
							assertEquals("attribute-suggested-value",
									suggestedValueResult);
							break;
						}
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}

	}
}