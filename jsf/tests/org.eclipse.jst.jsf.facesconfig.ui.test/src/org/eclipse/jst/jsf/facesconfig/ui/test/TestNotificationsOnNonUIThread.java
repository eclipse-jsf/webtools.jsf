/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.test;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.facesconfig.emf.ActionListenerType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentFamilyType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultLocaleType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultRenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.emf.LocaleConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.MessageBundleType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationHandlerType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitIdType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererClassType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererTypeType;
import org.eclipse.jst.jsf.facesconfig.emf.StateManagerType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;
import org.eclipse.jst.jsf.facesconfig.emf.VariableResolverType;
import org.eclipse.jst.jsf.facesconfig.emf.ViewHandlerType;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.page.ComponentsPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.ManagedBeanPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.OthersPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.OverviewPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowEditor;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.progress.UIJob;

/**
 * This test covers :
 * 	[Bug 233506] [hotbug_request] SWTException in Faces config. file editor
 * 	[Bug 244486] [hotbug_request] JSF Tools: widget disposed exception, when model shared with other editors
 *	
 */
public class TestNotificationsOnNonUIThread extends FacesConfigEditorTest {
	
	public void setUp()throws Exception {
		super.setUp();	
		initializeEditor();
	}
	
	private void initializeEditor() {
		UIJob job = new UIJob("Initialize FCE Editor Pages") {

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				//the following should ensure that all of the adapters and listeners on the pages are initialized
				editor.setActiveEditorPage(OverviewPage.PAGE_ID);
				editor.setActiveEditorPage(PageflowEditor.PAGE_ID);
				editor.setActiveEditorPage(ManagedBeanPage.PAGE_ID);
				editor.setActiveEditorPage(ComponentsPage.PAGE_ID);
				editor.setActiveEditorPage(OthersPage.PAGE_ID);
				editor.setActiveEditorPage(FacesConfigEditor.SOURCE_PAGE_ID);
				return Status.OK_STATUS;
			}
			
		};
		job.runInUIThread(new NullProgressMonitor());
		
	}

	private void executeTest(FCRunnable runnable) throws Exception {				
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(project, "WEB-INF/faces-config.xml");
			runnable.setFCEdit(edit);
			Thread execThread = new Thread(runnable);
        	assert (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getDisplay().getThread() != Thread.currentThread());
			execThread.run();
			execThread.join();
		} finally {
			if (edit != null){
				edit.save(null);
				edit.dispose();
				edit = null;
			}
		}
		
	}
	
	public void testCreateFCElementsWithEditorOpen () throws Exception {
		internalCreateNavigationElements();
		closeAndReopenEditor();
		internalCreateComponentElements();
		closeAndReopenEditor();
		internalCreateManagedBeanElement();
		closeAndReopenEditor();
		internalCreateOtherPageElements();
	}
	
	public void testCreateFCElementsWithEditorOpenedThenClosed () throws Exception {
		internalCreateNavigationElements();
		closeEditor();
		internalCreateComponentElements();
//		closeAndReopenEditor();
		internalCreateManagedBeanElement();
//		closeAndReopenEditor();
		internalCreateOtherPageElements();
	}
	
	public void testCreateFCElementsWithEditorSometimesOpen () throws Exception {
		closeEditor();
		internalCreateNavigationElements();
		closeAndReopenEditor();
		internalCreateComponentElements();
		closeEditor();
		internalCreateManagedBeanElement();
		closeAndReopenEditor();
		internalCreateOtherPageElements();
	}
	
	private void closeAndReopenEditor() throws Exception {
		closeEditor();
		openEditor();
		initializeEditor();
	}
	
	private void internalCreateNavigationElements() throws Exception{
		WorkbenchPlugin.log("internalCreateNavigationElements");
		if (editor!= null) 
			this.editor.setActiveEditorPage(PageflowEditor.PAGE_ID);
		executeTest(new FCRunnable() {
			public void run(FacesConfigArtifactEdit edit){	        				           
				createNavRule(edit);
			}
		});
	}
	
	private void internalCreateComponentElements() throws Exception{
		WorkbenchPlugin.log("internalCreateComponentElements");
		if (editor!= null) 
			editor.setActiveEditorPage(ComponentsPage.PAGE_ID);
		executeTest(new FCRunnable() {
			public void run(FacesConfigArtifactEdit edit){	        	        		        
				createComp(edit);
				createConv(edit);
				createRenderkit(edit);
				createVal(edit);
			}

		});
	}
	
	private void internalCreateManagedBeanElement() throws Exception{
		WorkbenchPlugin.log("internalCreateManagedBeanElement");
		if (editor!= null) 
			editor.setActiveEditorPage(ManagedBeanPage.PAGE_ID);
		executeTest(new FCRunnable() {
			public void run(FacesConfigArtifactEdit edit){	        	        		        
            	createMBean(edit);
			}

		});
	}
	
	private void internalCreateOtherPageElements() throws Exception{
		WorkbenchPlugin.log("internalCreateOtherPageElements");
		if (editor!= null) 
			editor.setActiveEditorPage(OthersPage.PAGE_ID);
		executeTest(new FCRunnable() {
			public void run(FacesConfigArtifactEdit edit){
				createActionListener(edit);
				createDefRenderKit(edit);
				createLocaleConfig(edit);
				createMessageBundle(edit);
				createNavHandler(edit);
				createPropertyResolver(edit);
				createStateManager(edit);
				createVarResolver(edit);
				createViewHandler(edit);
				createAppFactory(edit);
				createFCFactory(edit);
				createLCFactory(edit);
				createRKFactory(edit);
				createPhaseListener(edit);
			}

		});
	}

	@SuppressWarnings("unchecked")
    private void createPhaseListener(FacesConfigArtifactEdit edit){
		LifecycleType app = getOrCreateLCType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		PhaseListenerType t = fac.createPhaseListenerType();
		t.setTextContent("PhaseListener"+System.currentTimeMillis());
		app.getPhaseListener().add(t);		
	}

	@SuppressWarnings("unchecked")
    private LifecycleType getOrCreateLCType(
			FacesConfigArtifactEdit edit) {
		
		List<LifecycleType> lcs = edit.getFacesConfig().getLifecycle();
		if (lcs == null || lcs.size() == 0){					
			LifecycleType lc = FacesConfigFactory.eINSTANCE.createLifecycleType();
			edit.getFacesConfig().getLifecycle().add(lc);
			return lc;
		}
		return lcs.get(0);
	}
	
	@SuppressWarnings("unchecked")
    private void createRKFactory(FacesConfigArtifactEdit edit) {
		FactoryType app = getOrCreateFactoryType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		RenderKitFactoryType t = fac.createRenderKitFactoryType();
		t.setTextContent("RKFac");					
		app.getRenderKitFactory().add(t);
	}

	@SuppressWarnings("unchecked")
    private void createLCFactory(FacesConfigArtifactEdit edit) {
		FactoryType app = getOrCreateFactoryType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		LifecycleFactoryType t = fac.createLifecycleFactoryType();
		t.setTextContent("lifeCycleFac");					
		app.getLifecycleFactory().add(t);		
	}

	@SuppressWarnings("unchecked")
    private void createFCFactory(FacesConfigArtifactEdit edit) {
		FactoryType app = getOrCreateFactoryType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		FacesContextFactoryType t = fac.createFacesContextFactoryType();
		t.setTextContent("FCFac");					
		app.getFacesContextFactory().add(t);				
	}

	@SuppressWarnings("unchecked")
    private void createAppFactory(FacesConfigArtifactEdit edit) {
		FactoryType app = getOrCreateFactoryType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		ApplicationFactoryType t = fac.createApplicationFactoryType();
		t.setTextContent("appFac");					
		app.getApplicationFactory().add(t);
	}

	@SuppressWarnings("unchecked")
    private FactoryType getOrCreateFactoryType(
			FacesConfigArtifactEdit edit) {
		List<FactoryType> facs = edit.getFacesConfig().getFactory();
		if (facs == null || facs.size() == 0){					
			FactoryType fac = FacesConfigFactory.eINSTANCE.createFactoryType();
			edit.getFacesConfig().getFactory().add(fac);
			return fac;
		}
		return facs.get(0);

	}

	@SuppressWarnings("unchecked")
    private void createViewHandler(FacesConfigArtifactEdit edit) {
		ApplicationType app = getOrCreateAppType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		ViewHandlerType t = fac.createViewHandlerType();
		t.setTextContent("ViewHandler");					
		app.getViewHandler().add(t);			
	}

	@SuppressWarnings("unchecked")
    private void createVarResolver(FacesConfigArtifactEdit edit) {
		ApplicationType app = getOrCreateAppType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		VariableResolverType t = fac.createVariableResolverType();
		t.setTextContent("VarResolver");					
		app.getVariableResolver().add(t);
	}

	@SuppressWarnings("unchecked")
    private void createStateManager(FacesConfigArtifactEdit edit) {
		ApplicationType app = getOrCreateAppType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		StateManagerType t = fac.createStateManagerType();
		t.setTextContent("StateMgr");					
		app.getStateManager().add(t);
	}

	@SuppressWarnings("unchecked")
    private void createPropertyResolver(FacesConfigArtifactEdit edit) {
		ApplicationType app = getOrCreateAppType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		PropertyResolverType t = fac.createPropertyResolverType();
		t.setTextContent("PropResolver");					
		app.getPropertyResolver().add(t);	
	}

	@SuppressWarnings("unchecked")
    private void createNavHandler(FacesConfigArtifactEdit edit) {
		ApplicationType app = getOrCreateAppType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		NavigationHandlerType t = fac.createNavigationHandlerType();
		t.setTextContent("NavHdlr");					
		app.getNavigationHandler().add(t);
	}

	@SuppressWarnings("unchecked")
    private void createMessageBundle(FacesConfigArtifactEdit edit) {
		ApplicationType app = getOrCreateAppType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		MessageBundleType t = fac.createMessageBundleType();
		t.setTextContent("messageBundle");					
		app.getMessageBundle().add(t);
	}

	@SuppressWarnings("unchecked")
    private void createLocaleConfig(FacesConfigArtifactEdit edit) {
		ApplicationType app = getOrCreateAppType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		LocaleConfigType t = fac.createLocaleConfigType();
		DefaultLocaleType lc = fac.createDefaultLocaleType();
		lc.setTextContent("en");
		t.setDefaultLocale(lc);
		app.getLocaleConfig().add(t);
	}

	@SuppressWarnings("unchecked")
    private void createActionListener(FacesConfigArtifactEdit edit) {
		ApplicationType app = getOrCreateAppType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		ActionListenerType t = fac.createActionListenerType();
		t.setTextContent("actionListener");
		app.getActionListener().add(t);		
	}

	@SuppressWarnings("unchecked")
    private void createDefRenderKit(FacesConfigArtifactEdit edit) {
		ApplicationType app = getOrCreateAppType(edit);
		FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		DefaultRenderKitIdType t = fac.createDefaultRenderKitIdType();
		t.setTextContent("defRenderKit");
		app.getDefaultRenderKitId().add(t);
	}

	@SuppressWarnings("unchecked")
    private ApplicationType getOrCreateAppType(
			FacesConfigArtifactEdit edit) {
		List<ApplicationType> apps = edit.getFacesConfig().getApplication();
		if (apps == null || apps.size() == 0){
			ApplicationType app = FacesConfigFactory.eINSTANCE.createApplicationType();
			edit.getFacesConfig().getApplication().add(app);
			return app;
		}
		return apps.get(0);
	}

	@SuppressWarnings("unchecked")
    private void createComp(FacesConfigArtifactEdit edit) {
		 FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		 ComponentType comp = fac.createComponentType();
		 ComponentTypeType ttype = fac.createComponentTypeType();
		 ttype.setTextContent("comp"+String.valueOf(System.currentTimeMillis()));
		 comp.setComponentType(ttype);
		 ComponentClassType klass = fac.createComponentClassType();
		 klass.setTextContent("com.foo.burger");
		 comp.setComponentClass(klass);
		 edit.getFacesConfig().getComponent().add(comp);
	}

	@SuppressWarnings("unchecked")
    private void createConv(FacesConfigArtifactEdit edit) {
		 FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		 ConverterType conv = fac.createConverterType();
		 ConverterClassType klass = fac.createConverterClassType();
		 klass.setTextContent("com.foo.burger");
		 conv.setConverterClass(klass);
//			 ConverterIdType id= fac.createConverterIdType();
//			 id.setTextContent("Conv"+System.currentTimeMillis());
//			 conv.setConverterId(id);
		 ConverterForClassType forKlass = fac.createConverterForClassType();
		 forKlass.setTextContent("com.foo.burger.converter");
		 conv.setConverterForClass(forKlass);
		 
		 AttributeType attr = fac.createAttributeType();
		 AttributeClassType aKlass = fac.createAttributeClassType();
		 AttributeNameType aName = fac.createAttributeNameType();
		 aName.setTextContent("foo");
		 aKlass.setTextContent("com.foo.burger");
		 attr.setAttributeClass(aKlass);
		 attr.setAttributeName(aName);
		 conv.getAttribute().add(attr);
		 
		 PropertyType prop = fac.createPropertyType();
		 PropertyClassType pKlass = fac.createPropertyClassType();
		 PropertyNameType pName = fac.createPropertyNameType();
		 pKlass.setTextContent("aProp");
		 pName.setTextContent("com.foo.Bar");
		 prop.setPropertyClass(pKlass);
		 prop.setPropertyName(pName);
		 conv.getProperty().add(prop);
		 
		 edit.getFacesConfig().getConverter().add(conv);
	}
	
	
	@SuppressWarnings("unchecked")
    private void createVal(FacesConfigArtifactEdit edit) {
		 FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		 ValidatorType val = fac.createValidatorType();
		 ValidatorClassType klass = fac.createValidatorClassType();
		 klass.setTextContent("com.foo.burger");
		 val.setValidatorClass(klass);
		 ValidatorIdType id= fac.createValidatorIdType();
		 id.setTextContent("Conv"+System.currentTimeMillis());
		 val.setValidatorId(id);
		 
		 AttributeType attr = fac.createAttributeType();
		 AttributeClassType aKlass = fac.createAttributeClassType();
		 AttributeNameType aName = fac.createAttributeNameType();
		 aName.setTextContent("foo");
		 aKlass.setTextContent("com.foo.burger");
		 attr.setAttributeClass(aKlass);
		 attr.setAttributeName(aName);
		 val.getAttribute().add(attr);
		 
		 PropertyType prop = fac.createPropertyType();
		 PropertyClassType pKlass = fac.createPropertyClassType();
		 PropertyNameType pName = fac.createPropertyNameType();
		 pKlass.setTextContent("aProp");
		 pName.setTextContent("com.foo.Bar");
		 prop.setPropertyClass(pKlass);
		 prop.setPropertyName(pName);
		 val.getProperty().add(prop);
		 
		 edit.getFacesConfig().getValidator().add(val);
	}

	@SuppressWarnings("unchecked")
    private void createRenderkit(FacesConfigArtifactEdit edit) {
		 FacesConfigFactory fac = FacesConfigFactory.eINSTANCE;
		 RenderKitType rt = fac.createRenderKitType();
		 RenderKitIdType id = fac.createRenderKitIdType();
		 id.setTextContent("foo"+System.currentTimeMillis());
		 rt.setRenderKitId(id);
		 RenderKitClassType klass = fac.createRenderKitClassType();
		 klass.setTextContent("fooClass");
		 rt.setRenderKitClass(klass);
		 
		 RendererType r = fac.createRendererType();
		 RendererClassType rk = fac.createRendererClassType();
		 rk.setTextContent("com.fooey");
		 r.setRendererClass(rk);
		 RendererTypeType rtt = fac.createRendererTypeType();
		 rtt.setTextContent("rendererTypeType");
		 r.setRendererType(rtt);
		 ComponentFamilyType c = fac.createComponentFamilyType();
		 c.setTextContent("componentFamilyType");
		 r.setComponentFamily(c);
		 rt.getRenderer().add(r);
		 
		 edit.getFacesConfig().getRenderKit().add(rt);
	}

	@SuppressWarnings("unchecked")
    private void createNavRule(FacesConfigArtifactEdit edit) {
		NavigationRuleType newNavRule = FacesConfigFactory.eINSTANCE.createNavigationRuleType();
		FromViewIdType fromView = FacesConfigFactory.eINSTANCE.createFromViewIdType();
		fromView.setTextContent("JSP"+String.valueOf(System.currentTimeMillis()));
		newNavRule.setFromViewId(fromView);
		NavigationCaseType newCase = FacesConfigFactory.eINSTANCE.createNavigationCaseType();
		ToViewIdType view = FacesConfigFactory.eINSTANCE.createToViewIdType();
		view.setTextContent("jsp2");
		newCase.setToViewId(view);
		FromOutcomeType fromOutcome = FacesConfigFactory.eINSTANCE.createFromOutcomeType();
		fromOutcome.setTextContent("la-la-la");
		newCase.setFromOutcome(fromOutcome);
		newNavRule.getNavigationCase().add(newCase);
		edit.getFacesConfig().getNavigationRule().add(newNavRule);
	}

	@SuppressWarnings("unchecked")
    private void createMBean(FacesConfigArtifactEdit edit) {
    	ManagedBeanType newBean = FacesConfigFactory.eINSTANCE.createManagedBeanType();	
    	ManagedBeanNameType name = FacesConfigFactory.eINSTANCE.createManagedBeanNameType();
    	ManagedBeanScopeType scope = FacesConfigFactory.eINSTANCE.createManagedBeanScopeType();
    	scope.setTextContent("session");
    	name.setTextContent("Foobar"+String.valueOf(System.currentTimeMillis()));
    	ManagedBeanClassType klass = FacesConfigFactory.eINSTANCE.createManagedBeanClassType();
    	klass.setTextContent("com.foo.Bar");
		newBean.setManagedBeanName(name);
		newBean.setManagedBeanClass(klass);
		newBean.setManagedBeanScope(scope);
		edit.getFacesConfig().getManagedBean().add(newBean);	
	}

	abstract class FCRunnable implements Runnable {
		FacesConfigArtifactEdit _edit;
		public void setFCEdit(FacesConfigArtifactEdit edit) {
			_edit = edit;
		}
		protected abstract void run(FacesConfigArtifactEdit edit);		
		public final void run() {
			run(_edit);
		}
	}
}
