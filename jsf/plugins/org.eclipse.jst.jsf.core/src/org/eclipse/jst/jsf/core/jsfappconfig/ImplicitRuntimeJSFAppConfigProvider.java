/*******************************************************************************
 * Copyright (c) 2005, 2012 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsfappconfig;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;

/**
 * ImplicitRuntimeJSFAppConfigProvider provides an application configuration
 * model that contains implicit configuration objects provided by a JSF
 * implementation at runtime.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public class ImplicitRuntimeJSFAppConfigProvider extends AbstractJSFAppConfigProvider {

	/**
	 * Cached {@link FacesConfigType} instance.
	 */
	protected FacesConfigType facesConfig = null;

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvider#getFacesConfigModel()
	 */
	public FacesConfigType getFacesConfigModel() {
		if (facesConfig == null) {
			createModel();
			if (facesConfig != null) {
				jsfAppConfigLocater.getJSFAppConfigManager().addFacesConfigChangeAdapter(facesConfig);
			}
		}
		return facesConfig;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigProvider#releaseFacesConfigModel()
	 */
	public void releaseFacesConfigModel() {
		jsfAppConfigLocater.getJSFAppConfigManager().removeFacesConfigChangeAdapter(facesConfig);
		facesConfig = null;
	}

	/**
	 * Creates the application configuration model and assigns it to the
	 * facesConfig property.
	 */
	protected void createModel() {
		//Bug 340093 - [JSF2.0] Need version mechanism for determining what implicit components are valid
		IProject project = jsfAppConfigLocater.getJSFAppConfigManager().getProject();
		final boolean isAtLeastJSF20Project = JSFAppConfigUtils.isValidJSFProject(
				project, IJSFCoreConstants.FACET_VERSION_2_0);
		boolean isJakartaEE = JSFVersion.guessAtLeast(JSFVersion.V3_0, project);
		facesConfig = FacesConfigFactory.eINSTANCE.createFacesConfigType();
		//create and add converters by id
		EList converters = facesConfig.getConverter();
		converters.add(createConverter("BigDecimal")); //$NON-NLS-1$
		converters.add(createConverter("BigInteger")); //$NON-NLS-1$
		converters.add(createConverter("Boolean")); //$NON-NLS-1$
		converters.add(createConverter("Byte")); //$NON-NLS-1$
		converters.add(createConverter("Character")); //$NON-NLS-1$
		converters.add(createConverter("DateTime")); //$NON-NLS-1$
		converters.add(createConverter("Double")); //$NON-NLS-1$
		converters.add(createConverter("Float")); //$NON-NLS-1$
		converters.add(createConverter("Integer")); //$NON-NLS-1$
		converters.add(createConverter("Long")); //$NON-NLS-1$
		converters.add(createConverter("Number")); //$NON-NLS-1$
		converters.add(createConverter("Short")); //$NON-NLS-1$
		if (isAtLeastJSF20Project) {
			converters.add(createConverter("Enum")); //$NON-NLS-1$
		}
		// converters by for-class (see spec 3.3.3 -- Standard Converter Implementions
		converters.add(createForClassConverter("java.lang.Boolean", //$NON-NLS-1$
				isJakartaEE ? "jakarta.faces.convert.BooleanConverter" : "javax.faces.convert.BooleanConverter")); //$NON-NLS-1$ //$NON-NLS-2$
		converters.add(createForClassConverter("java.lang.Byte", //$NON-NLS-1$
				isJakartaEE ? "jakarta.faces.convert.ByteConverter" : "javax.faces.convert.ByteConverter")); //$NON-NLS-1$ //$NON-NLS-2$
		converters.add(createForClassConverter("java.lang.Character", //$NON-NLS-1$
				isJakartaEE ? "jakarta.faces.convert.CharacterConverter" : "javax.faces.convert.CharacterConverter")); //$NON-NLS-1$ //$NON-NLS-2$
		converters.add(createForClassConverter("java.lang.Double", //$NON-NLS-1$
				isJakartaEE ? "jakarta.faces.convert.DoubleConverter" : "javax.faces.convert.DoubleConverter")); //$NON-NLS-1$ //$NON-NLS-2$
		converters.add(createForClassConverter("java.lang.Float", //$NON-NLS-1$
				isJakartaEE ? "jakarta.faces.convert.FloatConverter" : "javax.faces.convert.FloatConverter")); //$NON-NLS-1$ //$NON-NLS-2$
		converters.add(createForClassConverter("java.lang.Integer", //$NON-NLS-1$
				isJakartaEE ? "jakarta.faces.convert.IntegerConverter" : "javax.faces.convert.IntegerConverter")); //$NON-NLS-1$ //$NON-NLS-2$
		converters.add(createForClassConverter("java.lang.Long", //$NON-NLS-1$
				isJakartaEE ? "jakarta.faces.convert.LongConverter" : "javax.faces.convert.LongConverter")); //$NON-NLS-1$ //$NON-NLS-2$
		converters.add(createForClassConverter("java.lang.Short", //$NON-NLS-1$
				isJakartaEE ? "jakarta.faces.convert.ShortConverter" : "javax.faces.converter.ShortConverter")); //$NON-NLS-1$ //$NON-NLS-2$
		if (isAtLeastJSF20Project) {
			converters.add(createForClassConverter("java.lang.Enum", //$NON-NLS-1$
					isJakartaEE ? "jakarta.faces.convert.EnumConverter" : "javax.faces.converter.EnumConverter")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		//create and add validators
		EList validators = facesConfig.getValidator();
		validators.add(createValidator("DoubleRange")); //$NON-NLS-1$
		validators.add(createValidator("Length")); //$NON-NLS-1$
		validators.add(createValidator("LongRange")); //$NON-NLS-1$
		if (isAtLeastJSF20Project) {
			validators.add(createValidator("Bean")); //$NON-NLS-1$
			validators.add(createValidator("RegularExpression", "Regex")); //$NON-NLS-1$ //$NON-NLS-2$
			validators.add(createValidator("Required")); //$NON-NLS-1$
		}
		//create and add UI components
		EList components = facesConfig.getComponent();
		components.add(createUIComponent("Column")); //$NON-NLS-1$
		components.add(createUIComponent("Command")); //$NON-NLS-1$
		components.add(createUIComponent("Data")); //$NON-NLS-1$
		components.add(createUIComponent("Form")); //$NON-NLS-1$
		components.add(createUIComponent("Graphic")); //$NON-NLS-1$
		components.add(createUIComponent("Input")); //$NON-NLS-1$
		components.add(createUIComponent("Message")); //$NON-NLS-1$
		components.add(createUIComponent("Messages")); //$NON-NLS-1$
		components.add(createUIComponent("NamingContainer")); //$NON-NLS-1$
		components.add(createUIComponent("Output")); //$NON-NLS-1$
		components.add(createUIComponent("Panel")); //$NON-NLS-1$
		components.add(createUIComponent("Parameter")); //$NON-NLS-1$
		components.add(createUIComponent("SelectBoolean")); //$NON-NLS-1$
		components.add(createUIComponent("SelectItem")); //$NON-NLS-1$
		components.add(createUIComponent("SelectItems")); //$NON-NLS-1$
		components.add(createUIComponent("SelectMany")); //$NON-NLS-1$
		components.add(createUIComponent("SelectOne")); //$NON-NLS-1$
		components.add(createUIComponent("ViewRoot")); //$NON-NLS-1$
		//create and add HTML components
		components.add(createHTMLComponent("HtmlCommandButton")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlCommandLink")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlDataTable")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlForm")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlGraphicImage")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlInputHidden")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlInputSecret")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlInputText")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlInputTextarea")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlMessage")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlMessages")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlOutputFormat")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlOutputLabel")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlOutputLink")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlOutputText")); //$NON-NLS-1$
		if (isAtLeastJSF20Project) {
			components.add(createHTMLComponent("HtmlOutcomeTargetLink")); //$NON-NLS-1$
			components.add(createHTMLComponent("HtmlOutcomeTargetButton")); //$NON-NLS-1$
		}
		components.add(createHTMLComponent("HtmlPanelGrid")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlPanelGroup")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlSelectBooleanCheckbox")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlSelectManyCheckbox")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlSelectManyListbox")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlSelectManyMenu")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlSelectOneListbox")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlSelectOneMenu")); //$NON-NLS-1$
		components.add(createHTMLComponent("HtmlSelectOneRadio")); //$NON-NLS-1$
	}

	/**
	 * Creates a {@link ConverterType} instance.
	 *
	 * @param name Base name of converter from which converter-id and
	 * converter-class are formed.
	 * @return {@link ConverterType} instance.
	 */
	protected ConverterType createConverter(String name) {
		IProject project = jsfAppConfigLocater.getJSFAppConfigManager().getProject();
		boolean isJakartaEE = JSFVersion.guessAtLeast(JSFVersion.V3_0, project);

		ConverterType converterType = FacesConfigFactory.eINSTANCE.createConverterType();
		//set converter-id
		ConverterIdType converterIdType = FacesConfigFactory.eINSTANCE.createConverterIdType();
		StringBuffer sb = new StringBuffer();
		sb.append(isJakartaEE ? "jakarta.faces.": "javax.faces."); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(name);
		converterIdType.setTextContent(sb.toString());
		converterType.setConverterId(converterIdType);
		//set converter-class
		ConverterClassType converterClassType = FacesConfigFactory.eINSTANCE.createConverterClassType();
		sb = new StringBuffer();
		sb.append(isJakartaEE ? "jakarta.faces.convert." : "javax.faces.convert."); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(name);
		sb.append("Converter"); //$NON-NLS-1$
		converterClassType.setTextContent(sb.toString());
		converterType.setConverterClass(converterClassType);
		return converterType;
	}

	private ConverterType createForClassConverter(String forClass, String converterClass)
	{
        ConverterType converterType = FacesConfigFactory.eINSTANCE.createConverterType();
        //set converter-id
        ConverterForClassType converterForClass = FacesConfigFactory.eINSTANCE.createConverterForClassType();
        converterForClass.setTextContent(forClass);
        converterType.setConverterForClass(converterForClass);
        //set converter-class
        ConverterClassType converterClassType = FacesConfigFactory.eINSTANCE.createConverterClassType();
        converterClassType.setTextContent(converterClass);
        converterType.setConverterClass(converterClassType);
        return converterType;
	}
	
	/**
	 * Creates a {@link ValidatorType} instance.
	 * 
	 * @param id Base ID of validator from which validator-id is formed.
	 * @param classname Base classname of validator from which validator-class is formed.
	 * @return {@link ValidatorType} instance.
	 */
	protected ValidatorType createValidator(String id, String classname) {
		IProject project = jsfAppConfigLocater.getJSFAppConfigManager().getProject();
		boolean isJakartaEE = JSFVersion.guessAtLeast(JSFVersion.V3_0, project);

		ValidatorType validatorType = FacesConfigFactory.eINSTANCE.createValidatorType();
		//set validator-id
		ValidatorIdType validatorIdType = FacesConfigFactory.eINSTANCE.createValidatorIdType();
		StringBuffer sb = new StringBuffer();
		sb.append(isJakartaEE ? "jakarta.faces." : "javax.faces."); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(id);
		validatorIdType.setTextContent(sb.toString());
		validatorType.setValidatorId(validatorIdType);
		//set validator-class
		ValidatorClassType validatorClassType = FacesConfigFactory.eINSTANCE.createValidatorClassType();
		sb = new StringBuffer();
		sb.append(isJakartaEE ? "jakarta.faces.validator." : "javax.faces.validator."); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(classname);
		sb.append("Validator"); //$NON-NLS-1$
		validatorClassType.setTextContent(sb.toString());
		validatorType.setValidatorClass(validatorClassType);
		return validatorType;
	}

	/**
	 * Creates a {@link ValidatorType} instance.
	 * 
	 * @param name Base name of validator from which validator-id and
	 * validator-class are formed.
	 * @return {@link ValidatorType} instance.
	 */
	protected ValidatorType createValidator(String name) {
		return createValidator(name, name);
	}

	/**
	 * Creates a {@link ComponentType} instance to represent a standard UI
	 * component.
	 * 
	 * @param name Base name of component from which component-type and
	 * component-class are formed.
	 * @return {@link ComponentType} instance.
	 */
	protected ComponentType createUIComponent(String name) {
		IProject project = jsfAppConfigLocater.getJSFAppConfigManager().getProject();
		boolean isJakartaEE = JSFVersion.guessAtLeast(JSFVersion.V3_0, project);

		ComponentType componentType = FacesConfigFactory.eINSTANCE.createComponentType();
		//set component-type
		ComponentTypeType componentTypeType = FacesConfigFactory.eINSTANCE.createComponentTypeType();
		StringBuffer sb = new StringBuffer();
		sb.append(isJakartaEE ? "jakarta.faces." : "javax.faces."); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(name);
		componentTypeType.setTextContent(sb.toString());
		componentType.setComponentType(componentTypeType);
		//set component-class
		ComponentClassType componentClassType = FacesConfigFactory.eINSTANCE.createComponentClassType();
		sb = new StringBuffer();
		sb.append(isJakartaEE ? "javax.faces.component.UI" : "javax.faces.component.UI"); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(name);
		componentClassType.setTextContent(sb.toString());
		componentType.setComponentClass(componentClassType);
		return componentType;
	}

	/**
	 * Creates a {@link ComponentType} instance to represent a concrete HTML
	 * component.
	 * 
	 * @param name Base name of component from which component-type and
	 * component-class are formed.
	 * @return {@link ComponentType} instance.
	 */
	protected ComponentType createHTMLComponent(String name) {
		IProject project = jsfAppConfigLocater.getJSFAppConfigManager().getProject();
		boolean isJakartaEE = JSFVersion.guessAtLeast(JSFVersion.V3_0, project);

		ComponentType componentType = FacesConfigFactory.eINSTANCE.createComponentType();
		//set component-type
		ComponentTypeType componentTypeType = FacesConfigFactory.eINSTANCE.createComponentTypeType();
		StringBuffer sb = new StringBuffer();
		sb.append(isJakartaEE ? "jakarta.faces." : "javax.faces."); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(name);
		componentTypeType.setTextContent(sb.toString());
		componentType.setComponentType(componentTypeType);
		//set component-class
		ComponentClassType componentClassType = FacesConfigFactory.eINSTANCE.createComponentClassType();
		sb = new StringBuffer();
		sb.append(isJakartaEE ? "jakarta.faces.component.html." : "javax.faces.component.html."); //$NON-NLS-1$ //$NON-NLS-2$
		sb.append(name);
		componentClassType.setTextContent(sb.toString());
		componentType.setComponentClass(componentClassType);
		return componentType;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object otherObject) {
		boolean equals = false;
		if (otherObject instanceof ImplicitRuntimeJSFAppConfigProvider) {
			equals = true;
		}
		return equals;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return ImplicitRuntimeJSFAppConfigProvider.class.getName().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ImplicitRuntimeJSFAppConfigProvider[]"; //$NON-NLS-1$
	}

}
