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
package org.eclipse.jst.pagedesigner.properties.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldApplyListener;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.IDialogFieldChangeListener;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ISupportTextValue;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.jst.pagedesigner.properties.attrgroup.IElementContextable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * @author mengbo
 * @version 1.5
 * 
 * TODO: cleanup up constructors
 */
public class AttributeGroup extends DialogFieldGroup {
	private static final Object KEY_ATTR = "KEY_ATTR"; //$NON-NLS-1$

//	private String _helpContextId;

	private List<DialogField> _dialogFields = null;  //consider using Map
	private IDOMElement _ownerElement;
	private Entity _tagEntity;
	private List<String> _attrNames;
	private List<IPropertyPageDescriptor> _attrPDs;
	private IStructuredDocumentContext _sdContext;

	private String _uri;
	private String _tagName;
	
	/**
	 * Constructor
	 * @param tagEntity - may not be null
	 * @param attrNames - may not be null.  Attribute names must be valid for the tag, and have attribute-value-runtime-type trait info
	 */
	public AttributeGroup(Entity tagEntity, List<String> attrNames) {
		_tagEntity = tagEntity;
		_attrNames = attrNames;		
	}
	
	/**
	 * Constructor where tagEntity is determined later or set later
	 * @param uri - may be null
	 * @param tagName - may be null
	 * @param attributeNames - may not be null.  Attribute names must be valid for the tag, and have attribute-value-runtime-type trait info
	 */
	public AttributeGroup(String uri,
			String tagName, String[] attributeNames) {
		_uri = uri;
		_tagName = tagName;
		if (attributeNames != null)
			_attrNames = Arrays.asList(attributeNames);
		else 
			_attrNames = new ArrayList();
	}
	
	private List<IPropertyPageDescriptor> prepareDescriptors(Entity tagEntity,
			List<String> names) {
		
		List pds = new ArrayList();
		for (String attrName : names) {
			IPropertyPageDescriptor pd = getPD(tagEntity, attrName);
			if (pd != null)
				pds.add(pd);
		}
		return pds;
	}

	private IPropertyPageDescriptor getPD(Entity tagEntity, String attrName) {		
		IPropertyPageDescriptor pd = null;
		Entity attrEntity = TaglibDomainMetaDataQueryHelper.getEntity(tagEntity, attrName);
		if (attrEntity != null){
			List pds = MetaDataEnabledProcessingFactory.getInstance().getAttributeValueRuntimeTypeFeatureProcessors(
							IPropertyPageDescriptor.class, getStructuredDocumentContext(), attrEntity);					
			if (pds != null && !pds.isEmpty())
				pd = (IPropertyPageDescriptor)pds.get(0);
			else 
				pd = new DefaultPropertyPageDescriptor(tagEntity, attrEntity);
		}
		return pd;
	}


	private void resetStructuredDocumentContext() {
		_sdContext = null;
		getStructuredDocumentContext();		
	}

	private IStructuredDocumentContext getStructuredDocumentContext() {
		if (_sdContext == null) {
			if (_ownerElement != null) {
				_sdContext = IStructuredDocumentContextFactory.INSTANCE.getContext(_ownerElement.getStructuredDocument(), _ownerElement);				
				
			} else {
				IEditorPart edPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
				if (edPart != null && edPart instanceof HTMLEditor) {//edPart will always be the WPE, but checking just to be safe
					_sdContext = IStructuredDocumentContextFactory.INSTANCE.getContext(((HTMLEditor)edPart).getDocument(), 0);
				}
			} 
		}
		return _sdContext;
	}

	/**
	 * @return tag entity for this attribute group
	 */
	protected Entity getTagEntity() {
		if (_tagEntity == null) {
			if (_ownerElement != null){
				IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE.getContext(_ownerElement.getStructuredDocument(), 0);
				if (context != null){
					String uri = _uri != null ? _uri : IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(context).getNode().getBaseURI();
					String tagName = _tagName != null ? _tagName :  IStructuredDocumentContextResolverFactory.INSTANCE.getDOMContextResolver(context).getNode().getNodeName();
					if (uri != null){
						IProject project = IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(context).getProject();
						_tagEntity = TaglibDomainMetaDataQueryHelper.getEntity(TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(project, uri), tagName);
					}
				}
			}
		}
		return _tagEntity;
	}
	/**
	 * @return tag name
	 */
	public String getTagName() {
		if (getTagEntity() == null)
			return _tagName;
		return getTagEntity().getId();
	}

	/**
	 * @return tag URI
	 */
	public String getURI() {
		if (getTagEntity() == null)
			return _uri;
		return getTagEntity().getModel().getCurrentModelContext().getUri();
	}


	/**
	 * Empty implementation. Subclasses should override to override default dialogField creation
	 * @param ppd
	 * @return DialogField
	 */
	protected DialogField createDialogField(IPropertyPageDescriptor ppd) {
		return null;
	}
	
//	protected DialogField createDialogField(String uri, String tag,
//			String attr) {
//		
//		return null;
//	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup#initialize()
	 */
	public void initialize() {
		if (_dialogFields == null) {
			_dialogFields = new ArrayList();

			for (IPropertyPageDescriptor pd : getAttributePDs()) {
				DialogField field;			
				field = createDialogField(pd);
				
				if (field == null) {
					field = pd.getDialogFieldEditor();
				}

				field.putAttachedData(KEY_ATTR, pd);//descriptors[i]);

				IDialogFieldApplyListener applyListener = getDialogFieldApplyListener(pd);
//						getURI(), getTagName(), pd.getAttributeName());//descriptors[i]);
				if (applyListener == null) {
					applyListener = getDefaultApplyListener();
				}
				field.setDialogFieldApplyListener(applyListener);

				IDialogFieldChangeListener changeListener = getDialogFieldChangeListener(pd);
//						getURI(), getTagName(), pd.getAttributeName());//descriptors[i]);
				if (changeListener == null) {
					changeListener = getDefaultChangeListener();
				}
				field.setDialogFieldChangeListener(changeListener);
				_dialogFields.add(field);
			}
		}
	}

	private List<IPropertyPageDescriptor> getAttributePDs() {
		if (_attrPDs == null){
			_attrPDs = prepareDescriptors(getTagEntity(), _attrNames);
		}
		return _attrPDs;
	}

	/**
	 * Child class can override the method to provide listener implementation
	 * 
	 * @param ppd
	 * @return IDialogFieldApplyListener
	 */
	protected IDialogFieldApplyListener getDialogFieldApplyListener(IPropertyPageDescriptor ppd) {
		return null;
	}

	/**
	 * Child class can override the method to provide listener implementation
	 * 
	 * @param ppd
	 * @return IDialogFieldChangeListener
	 */
	protected IDialogFieldChangeListener getDialogFieldChangeListener(IPropertyPageDescriptor ppd){
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroup#refreshData()
	 */
	public void refreshData() {
		if (_ownerElement == null) {
			return;
		}
		initialize();
		for (int i = 0, size = _dialogFields.size(); i < size; i++) {
			DialogField field = _dialogFields.get(i);

			ISupportTextValue textValue = (ISupportTextValue) field;
			IPropertyPageDescriptor attr = (IPropertyPageDescriptor) field
					.getAttachedData(KEY_ATTR);
			String attrName = attr.getAttributeName();
			String attrValue = _ownerElement.getAttribute(attrName);
			textValue.setTextWithoutUpdate(attrValue);
		}
	}

	/**
	 * @return IDOMElement
	 */
	public IDOMElement getElement() {
		return _ownerElement;
	}

	/**
	 * Sets the context in each of the dialogFields in the Group
	 * @param context
	 * @param owner
	 */
	public void setElementContext(IDOMNode context, IDOMElement owner) {
		this._ownerElement = owner;
		resetStructuredDocumentContext();
		initialize();
		if (context != null) {
			for (int i = 0, size = _dialogFields.size(); i < size; i++) {
				DialogField field = _dialogFields.get(i);
				if (field instanceof IElementContextable) {
					((IElementContextable) field).setElementContext(context,
							owner);
				}
			}
		}
		refreshData();
	}

	public void layoutDialogFields(FormToolkit toolkit, Composite parent) {
		Composite top;
		if (toolkit == null) {//when being displayed by DialogFieldGroupPage  (wizard)
			top = new Composite(parent, SWT.NONE);
			FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
			parent.setLayout(fillLayout);
		} else {
			top = toolkit.createComposite(parent, SWT.NONE);
		}
//		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
//		parent.setLayout(fillLayout);

//		if (this._helpContextId != null && this._helpContextId.length() > 0) {
//			PlatformUI.getWorkbench().getHelpSystem().setHelp(top,
//					_helpContextId);
//		}

		GridLayout layout = new GridLayout();
		int numColumns = getNumColumns();
		layout.numColumns = numColumns;
		top.setLayout(layout);
//		top.setLayoutData(new RowData());
		
		initialize();
		for (int i = 0, size = _dialogFields.size(); i < size; i++) {
			DialogField field = _dialogFields.get(i);
			field.doFillIntoGrid(toolkit, top, numColumns);
		}

		DialogField maxColumnField = null;
		int maxColumn = 0;
		for (int i = 0, size = _dialogFields.size(); i < size; i++) {
			DialogField field = _dialogFields.get(i);
			int c = field.getNumberOfControls();
			if (c > maxColumn) {
				maxColumn = c;
				maxColumnField = field;
			}
		}
		if (maxColumnField != null) {
			maxColumnField.handleGrabHorizontal();
		}
	}

	/**
	 * @return number of columns in for composite grid layout
	 */
	public int getNumColumns() {
		int columns = 1;
		initialize();
		for (int i = 0, size = _dialogFields.size(); i < size; i++) {
			DialogField field = _dialogFields.get(i);
			columns = Math.max(columns, field.getNumberOfControls());
		}
		return columns;
	}
	
	@Override
	public IStatus[] validateDialogFields() {
		return null;
	}

	/**
	 * @return DialogField[]
	 */
	public DialogField[] getDialogFields() {
		initialize();
		DialogField[] ret = new DialogField[_dialogFields.size()];
		_dialogFields.toArray(ret);
		return ret;
	}

	/**
	 * get the dialogfield for the corresponding attribute.
	 * 
	 * @param attrName
	 *            case sensitive attribute name.
	 * @return null if fail to find.
	 */
	public DialogField getDialogField(String attrName) {
		initialize();
		for (int i = 0, size = _dialogFields.size(); i < size; i++) {
			DialogField field = _dialogFields.get(i);
			IPropertyPageDescriptor attr = this.getPropertyDescriptor(field);
			if (attr != null && attr.getAttributeName().equals(attrName)) {
				return field;
			}
		}
		return null;
	}
	
	/**
	 * @param field
	 * @return IPropertyPageDescriptor
	 */
	public IPropertyPageDescriptor getPropertyDescriptor(DialogField field) {
		Object obj = field.getAttachedData(KEY_ATTR);
		if (obj instanceof IPropertyPageDescriptor) {
			return (IPropertyPageDescriptor) obj;
		}
	    return null;
	}
	
	@Override
	public void reset(){
		if (_dialogFields != null){
			for (DialogField field : _dialogFields){
				field.setDialogFieldApplyListener(null);
				field.setDialogFieldChangeListener(null);
			}
			_dialogFields = null;
		}
	}

	/**
	 * Set the tag entity
	 * @param tagEntity
	 */
	/*package*/ void setTagEntity(Entity tagEntity) {
		_tagEntity = tagEntity;		
	}

	public String toString(){
		StringBuffer buf = new StringBuffer("AttributeGroup: uri="); //$NON-NLS-1$
		buf.append("\r\r").append(getURI()).append("\r\rtag=").append(getTagName()); //$NON-NLS-1$ //$NON-NLS-2$
		buf.append("\r\rAttrs: "); //$NON-NLS-1$
		for (int i=0;i<_attrNames.size();i++) {
			String attr = _attrNames.get(i);
			buf.append(attr);
			if (i<_attrNames.size())
				buf.append(", "); //$NON-NLS-1$
		}
		return buf.toString();
	}
	

}
