/**
 * <copyright>
 * </copyright>
 *
 * $Id: IBoundedMapTypeDescriptorItemProvider.java,v 1.3 2007/04/09 02:29:53 cbateman Exp $
 */
package org.eclipse.jst.jsf.context.symbol.internal.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedMapTypeDescriptor;

/**
 * This is the item provider adapter for a {@link org.eclipse.jst.jsf.context.symbol.internal.provisional.IBoundedMapTypeDescriptor} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IBoundedMapTypeDescriptorItemProvider
    extends IMapTypeDescriptorItemProvider
    implements	
        IEditingDomainItemProvider,	
        IStructuredItemContentProvider,	
        ITreeItemContentProvider,	
        IItemLabelProvider,	
        IItemPropertySource {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final String copyright = "Copyright 2006 Oracle";

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
     * @param adapterFactory 
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IBoundedMapTypeDescriptorItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This returns IBoundedMapTypeDescriptor.gif.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/IBoundedMapTypeDescriptor"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getText(Object object) {
		String label = ((IBoundedMapTypeDescriptor)object).getTypeSignature();
		return label == null || label.length() == 0 ?
			getString("_UI_IBoundedMapTypeDescriptor_type") :
			getString("_UI_IBoundedMapTypeDescriptor_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren(Notification)} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged(Notification)}.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void notifyChanged(Notification notification) {
		updateChildren(notification);
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ResourceLocator getResourceLocator() {
		return JSFCommonPlugin.INSTANCE;
	}

}
