/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.itemcreation.internal;

import org.eclipse.jst.pagedesigner.itemcreation.AbstractTagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreationAdvisor;

/**
 * {@link org.eclipse.jst.pagedesigner.itemcreation.ITagCreator} used by the Web Page Editor palette. 
 * 
 * Uses org.eclipse.jst.jsf.common.metadata 
 */
public class DefaultTagCreator extends AbstractTagCreator 
{

    @Override
    protected ITagCreationAdvisor doSelectCreationAdvisor(
            CreationData creationData) {
        // return null by default. This causes the default advisor to be used.
        return null;
    }
}
