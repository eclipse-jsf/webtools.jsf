/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.attributegroup;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ClassButtonDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ComboDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.RadiosDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.pagedesigner.meta.IAttributeRuntimeValueType;

/**
 * @author mengbo
 * @version 1.5
 * @TODO - unused now
 */
public class DialogFieldFactory
{
    /**
     * @param data
     * @return the dialog field for data
     */
    public static DialogField getDialogField(AttributeData data)
    {
    	Object project = data.getParamMap().get(AttributeData.Project);
    	Entity attrEntity = null;
    	if (project instanceof IProject){
    		attrEntity = TaglibDomainMetaDataQueryHelper.getEntity(TaglibDomainMetaDataQueryHelper.createMetaDataModelContext((IProject)project, data.getUri()), data.getElementName()+"/"+data.getAttributeName()); //$NON-NLS-1$
    	}

        if(attrEntity != null)
        {
        	Trait t = TaglibDomainMetaDataQueryHelper.getTrait(attrEntity, MetaDataEnabledProcessingFactory.ATTRIBUTE_VALUE_RUNTIME_TYPE_PROP_NAME);
            String type = TraitValueHelper.getValueAsString(t);
            if (IAttributeRuntimeValueType.JAVACLASS.equals(type)) 
            {
                ClassButtonDialogField field = new ClassButtonDialogField(null);
//                Object project = data.getParamMap().get(AttributeData.Project);
                if (project instanceof IProject)
                {
                    field.setProject((IProject) project);
                }
                Object superType = data.getParamMap().get(AttributeData.SuperType);
                field.setSuperClassName((String) superType);
                return field;
            }
        }
        return new StringDialogField();
    }   
    
    /**
     * Sets the initial value of dialog field
     * @param field
     * @param value
     */
    public static void setDialogFieldValue(DialogField field, Object value){
        if (field instanceof StringDialogField)
        {
            ((StringDialogField) field).setTextWithoutUpdate(value == null ? "" : value.toString());//$NON-NLS-1$
        }
        else if (field instanceof ComboDialogField)
        {
            ((ComboDialogField) field).setTextWithoutUpdate(value == null ? "" : value.toString());//$NON-NLS-1$
        }
        else if (field instanceof RadiosDialogField)
        {
            if (value instanceof Integer)
            {
                ((RadiosDialogField) field).setSelectedIndex(((Integer) value).intValue());
            }
        }
    }
    
    /**
     * @param field
     * @param value
     * @deprecated - use setDialogField method
     */
    public static void setDialogFiledValue(DialogField field, Object value)
    {
    	setDialogFieldValue(field, value);
    }

    /**
     * Sets value of field into AttributeData if it was a StringDialogField
     * @param field
     * @param pair
     */
    public static void prepareDialogFieldValue(DialogField field, AttributeData pair)
    {
        if (field instanceof StringDialogField)
        {
            pair.setValue(((StringDialogField) field).getText());
        }
    }

    /**
     * @param field
     * @param pair
     * @deprecated - use prepareDialogFieldValue
     */
    public static void prepareDialogFiledValue(DialogField field, AttributeData pair)
    {
    	prepareDialogFieldValue(field, pair);
    }
    /**
     * @param data
     * @return the dialog field label for data
     */
    public static String getDialogFieldLabel(AttributeData data)
    {
        String name = data.getAttributeName();
        int gap = 'a' - 'A';
        if(name != null)
        {
            char[] chars = name.toCharArray();
            char[] newChars = new char[chars.length*2];
            if(chars.length > 0)
            {
                newChars[0] = chars[0] >= 'a' ? (char)(chars[0] - gap) : chars[0];
            }
            int newPos = 1;
            for(int i=1; i<chars.length; i++,newPos++)
            {
                if(chars[i] >= 'A' && chars[i] <= 'Z')
                {
                    newChars[newPos++] = ' ';
                }
                newChars[newPos] = chars[i];
            }
            char[] labelChars = new char[newPos + 1];
            System.arraycopy(newChars,0,labelChars,0,newPos);
            labelChars[newPos] = ':';
            return new String(labelChars);
        }
        return "";//$NON-NLS-1$
    }
}
