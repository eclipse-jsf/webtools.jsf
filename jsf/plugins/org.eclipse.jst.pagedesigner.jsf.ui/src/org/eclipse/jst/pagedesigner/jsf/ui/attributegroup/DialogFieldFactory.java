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
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ClassButtonDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.ComboDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.RadiosDialogField;
import org.eclipse.jst.jsf.common.ui.internal.dialogfield.StringDialogField;
import org.eclipse.jst.pagedesigner.meta.IAttributeDescriptor;
import org.eclipse.jst.pagedesigner.meta.ICMRegistry;
import org.eclipse.jst.pagedesigner.meta.IElementDescriptor;
import org.eclipse.jst.pagedesigner.meta.IValueType;
import org.eclipse.jst.pagedesigner.meta.internal.CMRegistry;

/**
 * @author mengbo
 * @version 1.5
 */
public class DialogFieldFactory
{
    /**
     * @param data
     * @return the dialog field for data
     */
    public static DialogField getDialogField(AttributeData data)
    {
        IAttributeDescriptor descriptor = getAttributeDescriptor(data.getUri(), data.getElementName(), data.getAttributeName());
        if(descriptor != null)
        {
            String type = descriptor.getValueType();
            if (IValueType.CLASSNAME.equalsIgnoreCase(type))
            {
                ClassButtonDialogField field = new ClassButtonDialogField(null);
                Object project = data.getParamMap().get(AttributeData.Project);
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

    private static IAttributeDescriptor getAttributeDescriptor(String uri, String elementName, String attributeName)
    {
        ICMRegistry registry = CMRegistry.getInstance();
        IElementDescriptor elementDescriptor = registry.getElementDescriptor(uri, elementName);
        return elementDescriptor.getAttributeDescriptor(attributeName);
    }

    /**
     * @param field
     * @param value
     */
    public static void setDialogFiledValue(DialogField field, Object value)
    {
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
     * @param pair
     */
    public static void prepareDialogFiledValue(DialogField field, AttributeData pair)
    {
        if (field instanceof StringDialogField)
        {
            pair.setValue(((StringDialogField) field).getText());
        }
    }

    /**
     * @param data
     * @return the dialog field label for data
     */
    public static String getDialogFieldLabel(AttributeData data)
    {
        String name = data.getAttributeName();
        int gap = 'a' - 'A';//$NON-NLS-1$ //$NON-NLS-2$
        if(name != null)
        {
            char[] chars = name.toCharArray();
            char[] newChars = new char[chars.length*2];
            if(chars.length > 0)
            {
                newChars[0] = chars[0] >= 'a' ? (char)(chars[0] - gap) : chars[0];//$NON-NLS-1$
            }
            int newPos = 1;
            for(int i=1; i<chars.length; i++,newPos++)
            {
                if(chars[i] >= 'A' && chars[i] <= 'Z')//$NON-NLS-1$ //$NON-NLS-2$
                {
                    newChars[newPos++] = ' ';//$NON-NLS-1$
                }
                newChars[newPos] = chars[i];
            }
            char[] labelChars = new char[newPos + 1];
            System.arraycopy(newChars,0,labelChars,0,newPos);
            labelChars[newPos] = ':';//$NON-NLS-1$
            return new String(labelChars);
        }
        return "";//$NON-NLS-1$
    }
}
