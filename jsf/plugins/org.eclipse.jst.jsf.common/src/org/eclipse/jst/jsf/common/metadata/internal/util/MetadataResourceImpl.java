/**
 * <copyright>
 * </copyright>
 *
 * $Id: MetadataResourceImpl.java,v 1.3 2007/02/07 00:03:50 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.jsf.common.metadata.internal.util.MetadataResourceFactoryImpl
 * @generated NOT
 */
public class MetadataResourceImpl extends XMLResourceImpl implements XMLResource.ResourceHandler {
	/**
	* Override createXMLHelper so that MetadataPackage.eINSTANCE is used for the NoNamespace package
	* @generated NOT
	*/
	protected XMLHelper createXMLHelper() {
		return new XMLHelperImpl(){
			public EPackage getNoNamespacePackage() {
				return MetadataPackage.eINSTANCE;
			}
		};
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Oracle inc.";

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

	public MetadataResourceImpl() {
		super();
	}
	
	public void postLoad(XMLResource resource, InputStream inputStream,
			Map options) {
		Object aRoot = resource.getContents().get(0);
		if (aRoot != null && aRoot instanceof Model){			
			setModelKeyInTraits((Model)aRoot, (Model)aRoot);
		}
	}

	private void setModelKeyInTraits(Model root, Entity currentEntity) {
		EStructuralFeature feature = MetadataPackage.eINSTANCE.getTrait_SourceModel();
		for (int i=0;i < currentEntity.getTraits().size();i++){
			((Trait)currentEntity.getTraits().get(i)).setSourceModel(root);
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
		// TODO Auto-generated method stub
		
	}

	public void preLoad(XMLResource resource, InputStream inputStream,
			Map options) {
		// TODO Auto-generated method stub
		
	}

	public void preSave(XMLResource resource, OutputStream outputStream,
			Map options) {
		// TODO Auto-generated method stub
		
	}

} //MetadataResourceImpl
