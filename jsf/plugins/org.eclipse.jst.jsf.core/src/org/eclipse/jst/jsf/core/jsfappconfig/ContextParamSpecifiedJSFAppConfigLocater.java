/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsfappconfig;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.WebApp;

/**
 * ContextParamSpecifiedJSFAppConfigLocater attempts to locate application
 * configuration files specified by the JSF CONFIG_FILES context parameter.
 * 
 * @author Ian Trimble - Oracle
 */
public class ContextParamSpecifiedJSFAppConfigLocater extends WebContentRelativeJSFAppConfigLocater {

	/**
	 * Cached instance of ContextParamAdapter.
	 */
	protected ContextParamAdapter contextParamAdapter = null;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.AbstractJSFAppConfigLocater#startLocating()
	 */
	public void startLocating() {
		locateProviders();
		WebArtifactEdit webArtifactEdit = WebArtifactEdit.getWebArtifactEditForRead(manager.getProject());
		if (webArtifactEdit != null) {
			WebApp webApp = webArtifactEdit.getWebApp();
			if (webApp != null) {
				contextParamAdapter = new ContextParamAdapter();
				webApp.eAdapters().add(contextParamAdapter);
				EList contexts = webApp.getContexts();
				if (contexts != null) {
					Iterator itContexts = contexts.iterator();
					while (itContexts.hasNext()) {
						ContextParam contextParam = (ContextParam)itContexts.next();
						contextParam.eAdapters().add(contextParamAdapter);
					}
				}
				EList contextParams = webApp.getContextParams();
				if (contextParams != null) {
					Iterator itContextParams = contextParams.iterator();
					while (itContextParams.hasNext()) {
						ParamValue paramValue = (ParamValue)itContextParams.next();
						paramValue.eAdapters().add(contextParamAdapter);
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.AbstractJSFAppConfigLocater#stopLocating()
	 */
	public void stopLocating() {
		if (contextParamAdapter != null) {
			WebArtifactEdit webArtifactEdit = WebArtifactEdit.getWebArtifactEditForRead(manager.getProject());
			if (webArtifactEdit != null) {
				WebApp webApp = webArtifactEdit.getWebApp();
				if (webApp != null) {
					webApp.eAdapters().remove(contextParamAdapter);
					EList contexts = webApp.getContexts();
					if (contexts != null) {
						Iterator itContexts = contexts.iterator();
						while (itContexts.hasNext()) {
							ContextParam contextParam = (ContextParam)itContexts.next();
							contextParam.eAdapters().remove(contextParamAdapter);
						}
					}
					EList contextParams = webApp.getContextParams();
					if (contextParams != null) {
						Iterator itContextParams = contextParams.iterator();
						while (itContextParams.hasNext()) {
							ParamValue paramValue = (ParamValue)itContextParams.next();
							paramValue.eAdapters().remove(contextParamAdapter);
						}
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.WebContentRelativeJSFAppConfigLocater#getFilenames()
	 */
	protected List getFilenames() {
		return JSFAppConfigUtils.getConfigFilesFromContextParam(manager.getProject());
	}

	/**
	 * Adapter implementation used to monitor addition/removal of context-param
	 * nodes and change in name of existing nodes in order to respond to
	 * changes to the JSF CONFIG_FILES context-param.
	 * 
	 * @author Ian Trimble - Oracle
	 */
	class ContextParamAdapter extends AdapterImpl {

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification notification) {
			Object objNotifier = notification.getNotifier();
			if (objNotifier instanceof WebApp) {
				int eventType = notification.getEventType();
				switch (eventType) {
				case Notification.ADD:
					Object objNewValue = notification.getNewValue();
					if (objNewValue instanceof ContextParam) {
						contextParamAdded((ContextParam)objNewValue);
					} else if (objNewValue instanceof ParamValue) {
						paramValueAdded((ParamValue)objNewValue);
					}
					break;
				case Notification.REMOVE:
					Object objOldValue = notification.getOldValue();
					if (objOldValue instanceof ContextParam) {
						contextParamRemoved((ContextParam)objOldValue);
					} else if (objOldValue instanceof ParamValue) {
						paramValueRemoved((ParamValue)objOldValue);
					}
					break;
				}
			} else if (objNotifier instanceof ContextParam) {
				if (notification.getEventType() != Notification.REMOVING_ADAPTER) {
					locateProviders();
				}
			} else if (objNotifier instanceof ParamValue) {
				if (notification.getEventType() != Notification.REMOVING_ADAPTER) {
					locateProviders();
				}
			}
		}

		/**
		 * Called when a new ContextParam instance is added.
		 * 
		 * @param contextParam ContextParam instance.
		 */
		protected void contextParamAdded(ContextParam contextParam) {
			if (isConfigFilesContextParam(contextParam)) {
				locateProviders();
			}
			contextParam.eAdapters().add(this);
		}

		/**
		 * Called when a new ParamValue instance is added.
		 * 
		 * @param paramValue ParamValue instance.
		 */
		protected void paramValueAdded(ParamValue paramValue) {
			if (isConfigFilesParamValue(paramValue)) {
				locateProviders();
			}
			paramValue.eAdapters().add(this);
		}

		/**
		 * Called when a ContextParam instance is removed.
		 * 
		 * @param contextParam ContextParam instance.
		 */
		protected void contextParamRemoved(ContextParam contextParam) {
			if (isConfigFilesContextParam(contextParam)) {
				locateProviders();
			}
			contextParam.eAdapters().remove(this);
		}

		/**
		 * Called when a ParamValue instance is removed.
		 * 
		 * @param paramValue ParamValue instance.
		 */
		protected void paramValueRemoved(ParamValue paramValue) {
			if (isConfigFilesParamValue(paramValue)) {
				locateProviders();
			}
			paramValue.eAdapters().remove(this);
		}

		/**
		 * Tests if the passed ContextParam instance is the JSF CONFIG_FILES
		 * context parameter.
		 * 
		 * @param contextParam ContextParam instance.
		 * @return true if the passed ContextParam instance is the JSF
		 * CONFIG_FILES context parameter, else false
		 */
		protected boolean isConfigFilesContextParam(ContextParam contextParam) {
			boolean isConfigFiles = false;
			if (contextParam != null) {
				String name = contextParam.getParamName();
				if (name != null && name.equals(JSFAppConfigUtils.CONFIG_FILES_CONTEXT_PARAM_NAME)) {
					isConfigFiles = true;
				}
			}
			return isConfigFiles;
		}

		/**
		 * Tests if the passed ParamValue instance is the JSF CONFIG_FILES
		 * context parameter.
		 * 
		 * @param paramValue ParamValue instance.
		 * @return true if the passed ParamValue instance is the JSF
		 * CONFIG_FILES context parameter, else false
		 */
		protected boolean isConfigFilesParamValue(ParamValue paramValue) {
			boolean isConfigFiles = false;
			if (paramValue != null) {
				String name = paramValue.getName();
				if (name != null && name.equals(JSFAppConfigUtils.CONFIG_FILES_CONTEXT_PARAM_NAME)) {
					isConfigFiles = true;
				}
			}
			return isConfigFiles;
		}

	}

}
