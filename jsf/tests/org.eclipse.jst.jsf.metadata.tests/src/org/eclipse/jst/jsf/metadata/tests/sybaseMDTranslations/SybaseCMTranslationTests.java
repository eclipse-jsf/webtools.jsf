package org.eclipse.jst.jsf.metadata.tests.sybaseMDTranslations;

import java.util.List;
import java.util.Locale;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.common.metadata.tests.AbstractBaseMetaDataTestCase;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.ListOfValues;
import org.eclipse.jst.jsf.metadata.tests.util.IJSFRuntimeRequiredV11;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.jst.pagedesigner.meta.IAttributeRuntimeValueType;


public class SybaseCMTranslationTests extends AbstractBaseMetaDataTestCase implements IJSFRuntimeRequiredV11 {
	private static QualifiedName qn = new QualifiedName("test","model");
	private String uri = "http://SybaseMD.test.jsfhtml";
	private Model _model;
	
	public void setUp() throws Exception {
		if (_model == null) {
			super.setUp();
			getModel();			
		}
	}
	
	private Model getModel() {
		try {
			_model = (Model)project.getSessionProperty(qn);
			if (_model == null) {
				ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, uri);
				_model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
				assertNotNull(_model);
				try {
					project.setSessionProperty(qn, _model);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return _model;
	}

	public void testBasics(){
		assertNotNull(getModel());
		assertEquals(25, getModel().getChildEntities().size());
		
		testSanity(getModel());
		testAttributeTraits(getModel());
		testPossibleValues(getModel());
		
		
	}

	private void testSanity(Model model){
		Entity form = TaglibDomainMetaDataQueryHelper.getEntity(model, "form");
		assertNotNull(form);
		assertTrue(form.getChildEntities().size() >= 10);
		
		Entity dir = TaglibDomainMetaDataQueryHelper.getEntity(form, "dir");
		assertNotNull(dir);
		assertTrue(dir.getTraits().size() > 2);
		
		Trait validValues = TaglibDomainMetaDataQueryHelper.getTrait(dir, "valid-values");
		assertNotNull(validValues);
		
		Entity nullEntity = TaglibDomainMetaDataQueryHelper.getEntity(model, "commandButton/converter");//converter should be excluded
		assertNull("commandButton/converter should not be present", nullEntity);
	}
	
	private void testAttributeTraits(Model model) {		
		if (Locale.getDefault().equals(Locale.US)) {
			testProps(model, "message/for", "true", "For", null, null, "JSF", null);
			testProps(model, "commandButton/action", "false", "Action", "action", null, "JSF", null);
			testProps(model, "dataTable/frame", "false", "Frame", null, "No sides", "HTML", null);
		}		
	}
	
	public void testEnumeratedValueTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.STRING, getAttrValRuntimeType(getModel(), "selectManyCheckbox/layout"));
		assertEquals(IAttributeRuntimeValueType.LANGUAGECODE, getAttrValRuntimeType(getModel(), "form/lang"));
	}

	public void testBooleanValueTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.BOOLEAN, getAttrValRuntimeType(getModel(), "inputText/immediate"));
		Entity attr = TaglibDomainMetaDataQueryHelper.getEntity(getModel(), "commandButton/rendered");
		List<IMetaDataEnabledFeature> list = MetaDataEnabledProcessingFactory.getInstance().getAttributeValueRuntimeTypeFeatureProcessors(IPropertyPageDescriptor.class, null , attr);
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}
	
	public void testJavaClassTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.JAVACLASS, getAttrValRuntimeType(getModel(), "column/faketypeattr"));
		Trait t = getTrait(getModel(), "column/faketypeattr", "valid-interfaces");
		assertNotNull(t);
		assertEquals("javax.faces.event.ActionListener", TraitValueHelper.getValueAsString(t));
	}
	
	public void testClasspathResourceTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.RESOURCEBUNDLE, getAttrValRuntimeType(getModel(), "column/fakeBasename"));
	}
	
	public void testColorTypeTranslation() {		
		assertEquals(IAttributeRuntimeValueType.COLOR, getAttrValRuntimeType(getModel(), "column/fakeColor"));
	}

	public void testCSSClassTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.CSSCLASS, getAttrValRuntimeType(getModel(), "selectBooleanCheckbox/styleClass"));
	}
	public void testCSSIdTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.CSSID, getAttrValRuntimeType(getModel(), "column/fakecssid"));
	}
	public void testCSSStyleTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.CSSSTYLE, getAttrValRuntimeType(getModel(), "selectBooleanCheckbox/style"));
	}
	public void testCurrencyCodeTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.CURRENCYCODE, getAttrValRuntimeType(getModel(), "column/fakeCurrencyCode"));
	}
	public void testJavaScriptTypeTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.SCRIPT, getAttrValRuntimeType(getModel(), "commandButton/onfocus"));
	}
	public void testLinkTypeTranslation() {
		// none known usages
	}
	public void testLocaleTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.LOCALE, getAttrValRuntimeType(getModel(), "column/fakeLocale"));
	}
	
	public void testMethodBindingTypeTranslation() {
		//validator
		assertEquals(IAttributeRuntimeValueType.METHODBINDING, getAttrValRuntimeType(getModel(), "inputText/validator"));
		Trait t = getTrait(getModel(), "inputText/validator", "runtime-param-types");
		assertNotNull(t);
		assertTrue(t.getValue() instanceof ListOfValues);
		List<String> vals = TraitValueHelper.getValueAsListOfStrings(t);
		assertEquals(3, vals.size());
		assertEquals("java.lang.Object", vals.get(2));
		
		t = getTrait(getModel(), "inputText/validator", "runtime-return-type");
		assertNotNull(t);
		assertEquals("void", TraitValueHelper.getValueAsString(t));
		
		//actionListener
		assertEquals(IAttributeRuntimeValueType.METHODBINDING, getAttrValRuntimeType(getModel(), "commandButton/actionListener"));
		t = getTrait(getModel(), "commandButton/actionListener", "runtime-param-types");
		assertNotNull(t);
		assertEquals("javax.faces.event.ActionEvent", TraitValueHelper.getValueAsString(t));
		
		assertEquals(IAttributeRuntimeValueType.METHODBINDING, getAttrValRuntimeType(getModel(), "inputText/valueChangeListener"));
		t = getTrait(getModel(), "inputText/valueChangeListener", "runtime-param-types");
		assertNotNull(t);
		assertEquals("javax.faces.event.ValueChangeEvent", TraitValueHelper.getValueAsString(t));
	}
	
	public void testMultiChoiceTypeTypeTranslation() {
		//i believe that this is a comma-delim enumerated type... noop for now
		assertEquals(IAttributeRuntimeValueType.STRING, getAttrValRuntimeType(getModel(), "column/fakeMultiChoice"));
	}
	public void testNamedBooleanTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.BOOLEAN, getAttrValRuntimeType(getModel(), "graphicImage/ismap"));
	}
	
	public void testPropertyBindingTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.VALUEBINDING, getAttrValRuntimeType(getModel(), "message/binding"));
	}
	
	public void testRelativePathValueTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.RELATIVEPATH, getAttrValRuntimeType(getModel(), "outputLink/value"));
	}
	
	public void testTimeZoneTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.TIMEZONE, getAttrValRuntimeType(getModel(), "column/fakeTimeZone"));
	}
	
	public void testJavaScriptTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.SCRIPT, getAttrValRuntimeType(getModel(), "commandButton/onclick"));
	}
	public void testWebPathTypeTranslation() {
		assertEquals(IAttributeRuntimeValueType.WEBPATH, getAttrValRuntimeType(getModel(), "graphicImage/value"));
		Trait trait = getTrait(getModel(), "graphicImage/value", "file-extensions");
		assertNotNull(trait);
		assertEquals(3, TraitValueHelper.getValueAsListOfStrings(trait).size());
	}

	private Trait getTrait(Model model, String entityKey, String traitKey) {
		Entity attrEntity = TaglibDomainMetaDataQueryHelper.getEntity(model, entityKey);
		assertNotNull(attrEntity);

		return TaglibDomainMetaDataQueryHelper.getTrait(attrEntity, traitKey);
	}
	
	private String getAttrValRuntimeType(Model model, String entityKey) {
		Trait t = getTrait(model, entityKey, MetaDataEnabledProcessingFactory.ATTRIBUTE_VALUE_RUNTIME_TYPE_PROP_NAME);
		assertNotNull(t);
		return TraitValueHelper.getValueAsString(t);
	}
	
	private void testProps(Model model, String entityKey, String requiredVal, String displayLabel, String description, String defaultValue, String cateagoryValue, String typeParam) {
		Entity attrEntity = TaglibDomainMetaDataQueryHelper.getEntity(model, entityKey);
		assertNotNull(attrEntity);
		assertTrue(attrEntity.getChildEntities().size() == 0);
		
		//required
		Trait t = TaglibDomainMetaDataQueryHelper.getTrait(attrEntity, "required");
		if (requiredVal != null){
			assertNotNull(t);
			assertEquals(requiredVal, TraitValueHelper.getValueAsString(t));
		}
		else
			assertNull(t);
		
		//display-label
		t = TaglibDomainMetaDataQueryHelper.getTrait(attrEntity, "display-label");
		if (displayLabel != null){
			assertNotNull(t);
			assertEquals(displayLabel, TraitValueHelper.getValueAsString(t));
		}
		else
			assertNull(t);
		
		//description
		t = TaglibDomainMetaDataQueryHelper.getTrait(attrEntity, "description");
		if (description != null){
			assertNotNull(t);
			assertEquals(description, TraitValueHelper.getValueAsString(t));
		}
		else
			assertNull(t);
		
		//description
		t = TaglibDomainMetaDataQueryHelper.getTrait(attrEntity, "default-value");
		if (defaultValue != null){
			assertNotNull(t);
			assertEquals(defaultValue, TraitValueHelper.getValueAsString(t));
		}
		else
			assertNull(t);
		
		//category
		t = TaglibDomainMetaDataQueryHelper.getTrait(attrEntity, IPropertyPageDescriptor.PROP_DESC_CATEGORY);
		if (cateagoryValue != null){
			assertNotNull(t);
			assertEquals(cateagoryValue, TraitValueHelper.getValueAsString(t));
		}
		else
			assertNull(t);
		
		//type-param
		t = TaglibDomainMetaDataQueryHelper.getTrait(attrEntity, "type-param");
		if (typeParam != null){
			assertNotNull(t);
			assertEquals(typeParam, TraitValueHelper.getValueAsString(t));
		}
		else
			assertNull(t);
		
	}
	
	private void testPossibleValues(Model model){
		//form/dir
		List<IMetaDataEnabledFeature> pvsList = MetaDataEnabledProcessingFactory.getInstance().getAttributeValueRuntimeTypeFeatureProcessors(IPossibleValues.class, null, uri, "form", "dir");
		assertNotNull(pvsList);
		assertEquals(1, pvsList.size());
		assertTrue(pvsList.get(0) instanceof IPossibleValues);
		IPossibleValues pvs = (IPossibleValues)pvsList.get(0);
		assertEquals(2, pvs.getPossibleValues().size());
		
		//form/lang
		pvsList = MetaDataEnabledProcessingFactory.getInstance().getAttributeValueRuntimeTypeFeatureProcessors(IPossibleValues.class, null, uri, "form", "lang");
		assertNotNull(pvsList);
		assertEquals(1, pvsList.size());
		assertTrue(pvsList.get(0) instanceof IPossibleValues);
		pvs = (IPossibleValues)pvsList.get(0);
		assertEquals(58, pvs.getPossibleValues().size());
	}
	
	// unused
	//	private List getProcessorAdapters(Class featureClass, String tag, String attrName) {
//		return MetaDataEnabledProcessingFactory.getInstance().
//			getAttributeValueRuntimeTypeFeatureProcessors(featureClass, docContext, 
//					uri, tag , attrName);
//	}
}
