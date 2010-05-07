/*******************************************************************************
* Copyright (c) 2010 Oracle Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Oracle Corporation - initial API and implementation and/or initial documentation
*******************************************************************************/
package org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.dtmanager.converter.internal.DTTagConverterContext;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.w3c.dom.Element;


/**
 * Transform operation that executes child ITransformOperation
 * instances if the view mode matches the argument (parameter) provided.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext() to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform() method.
 */
public class CheckModeOperation extends AbstractTransformOperation {

    /**
     * Constant indicating the current render mode is design
     */
    public static final String MODE_DESIGN = "design"; //$NON-NLS-1$
    /**
     * Constant indicating the current render mode is preview
     */
    public static final String MODE_PREVIEW = "preview"; //$NON-NLS-1$

    /*
     * (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
     */
    @Override
    public Element transform(Element srcElement, Element curElement) {
        if (getParameters().length < 1) {
            getLog().error("Warning.TransformOperationFactory.TooFewParameters", //$NON-NLS-1$
                           getTransformOperationID());
            return null;
        }
        
        String modeParam = getParameters()[0];
        Assert.isNotNull(modeParam);

        int currentMode = getMode();
        if ((MODE_DESIGN.equals(modeParam) && currentMode == IConverterFactory.MODE_DESIGNER)
                || (MODE_PREVIEW.equals(modeParam) && currentMode == IConverterFactory.MODE_PREVIEW)) {
            return executeChildOperations(srcElement, curElement);
        }

        // if we haven't transformed the element at all, and this is 
        // just the initial transform, return null
        if (srcElement == curElement) {
            return null;
        }

        // otherwise, return the currently transformed element
        return curElement;
    }

    /**
     * @return the converter mode. If unable to get the mode, return -1.
     */
    protected int getMode() {
        if (tagConverterContext != null
                && tagConverterContext instanceof DTTagConverterContext) {
            return ((DTTagConverterContext) tagConverterContext).getMode();
        }

        return -1;
    }
}
