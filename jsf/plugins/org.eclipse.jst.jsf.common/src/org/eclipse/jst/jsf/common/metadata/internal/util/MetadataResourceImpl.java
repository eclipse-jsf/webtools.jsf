/**
 * <copyright>
 * </copyright>
 *
 * $Id: MetadataResourceImpl.java,v 1.10 2008/09/03 23:29:03 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.common.metadata.internal.util.MetadataResourceFactoryImpl
 * @generated NOT
 */
public class MetadataResourceImpl extends XMLResourceImpl implements XMLResource.ResourceHandler {
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Oracle inc.";
	
	/**
	 * IMetaDataSourceModelProvider
	 */
	protected IMetaDataSourceModelProvider _provider;
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public MetadataResourceImpl(URI uri) {
		super(uri);
	}

	/**
	 * Constructor
	 */
	public MetadataResourceImpl() {
		super();
	}


	/**
	 * Constructorb
	 * @param provider
	 */
	public MetadataResourceImpl(IMetaDataSourceModelProvider provider){
		super();
		_provider = provider;
	}
	
	/**
	 * @generated NOT
	 */
	protected XMLLoad createXMLLoad() {
		return new MetadataXMLLoad(createXMLHelper());
	}
	/**
	* Override createXMLHelper so that MetadataPackage.eINSTANCE is used for the NoNamespace package
	* @generated NOT
	*/
	protected XMLHelper createXMLHelper() {
		return new XMLHelperImpl(){
			@Override
			public EPackage getNoNamespacePackage() {
				return MetadataPackage.eINSTANCE;
			}
		};
	}
	
	public void postLoad(XMLResource resource, InputStream inputStream,
            Map options)
    {
        final List<EObject>  resContents = resource.getContents();
        
        if (resContents.size() > 0)
        {
            final Object aRoot = resContents.get(0);
            if (aRoot instanceof Model)
            {
                setModelKeyInTraits((Model) aRoot, (Model) aRoot);
            }
        }
        else
        {
            JSFCommonPlugin.log(IStatus.WARNING, 
               "No model loaded for "+getURI());
        }
    }

	private void setModelKeyInTraits(Model root, Entity currentEntity) {
		// TODO: does this have side effect that we care about? nothing is done with the return value
	    MetadataPackage.eINSTANCE.getTrait_SourceModelProvider();
		for (int i=0;i < currentEntity.getTraits().size();i++){
			((Trait)currentEntity.getTraits().get(i)).setSourceModelProvider(_provider);
		}
		for (int j=0;j < currentEntity.getChildEntities().size();j++){
			setModelKeyInTraits(root,(Entity)currentEntity.getChildEntities().get(j));
		}
		
		if (currentEntity == root){
			for (int k=0;k < root.getEntityGroups().size();k++){
				setModelKeyInTraits(root,(Entity)root.getEntityGroups().get(k));
			}
		}
	}


	public void postSave(XMLResource resource, OutputStream outputStream,
			Map options) {
		// do nothing		
	}

	public void preLoad(XMLResource resource, InputStream inputStream,
			Map options) {
		// do nothing		
	}

	public void preSave(XMLResource resource, OutputStream outputStream,
			Map options) {
		// do nothing		
	}

	/**
	 * Override the handleErrors() method so that resource will load gracefully, and errors reported later
	 * when appropriate
	 *
	 */
	private static class MetadataXMLLoad extends XMLLoadImpl {

		public MetadataXMLLoad(XMLHelper helper) {
			super(helper);
		}

		@Override
		protected void handleErrors() throws IOException {
			//by doing nothing here, this allows the list of non-fatal errors (res.getErrors()) to be returned
		}
		
	}
} //MetadataResourceImpl
