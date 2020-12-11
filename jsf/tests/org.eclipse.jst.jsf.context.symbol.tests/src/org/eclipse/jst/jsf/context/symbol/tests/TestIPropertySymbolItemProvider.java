/*******************************************************************************
 * Copyright (c) 2006, 2021 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    Andreas Rusch/Axon Ivy - Lazy compute additional proposal info (javadoc) 
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.symbol.tests;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import junit.framework.TestCase;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.provider.IContentProposalProvider;
import org.eclipse.jst.jsf.context.symbol.provider.ProposalCreationFactoryAdapter;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.swt.graphics.Image;

/**
 * Tests for TestIPropertySymbolItemProvider
 * 
 * @author cbateman
 *
 */
public class TestIPropertySymbolItemProvider extends TestCase 
{   
    private Properties  props;
    
    protected void setUp() throws Exception 
    {
        super.setUp();
        TestFileResource propertiesFile = new TestFileResource();
        propertiesFile.load(ContextSymbolTestPlugin.getDefault().getBundle(), 
                                "/testdata/bundle.properties.data");
        props = new Properties();
        props.load(new ByteArrayInputStream(propertiesFile.toBytes()));
    }

    /**
     * Ensure test is in sync with test data and other sanity checks
     */
    public void testSanity()
    {
        assertEquals(props.size(), 3);
    }
    
    /**
     * Test completion proposal construction for a properties map
     */
    public void testProposalConstructionForMap()
    {
        IMapTypeDescriptor  mapDesc = 
            SymbolFactory.eINSTANCE.createIMapTypeDescriptor();
        mapDesc.setImmutable(true);
        mapDesc.setMapSource(props);

        final IComponentSymbol symbol = 
            SymbolFactory.eINSTANCE.createIComponentSymbol();
        symbol.setName("myTestSymbol");
        symbol.setTypeDescriptor(mapDesc);

        final ComposedAdapterFactory factory =
            new ComposedAdapterFactory(
                   ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

        final  MyCompletionFactory creationInfo = new MyCompletionFactory();

        for (final Iterator it = symbol.getTypeDescriptor().getProperties().iterator(); it.hasNext();)
        {
            final ISymbol propSymbol = (ISymbol) it.next();
            final Object  provider =  
              factory.adapt(propSymbol, IContentProposalProvider.class);

            assertTrue(provider instanceof IContentProposalProvider);

            final ICompletionProposal[] proposal  = 
                ((IContentProposalProvider) provider).
                    getProposals(propSymbol, creationInfo);

            assertNotNull(proposal);
        }
        
        List  list = creationInfo._replacementText;

        assertEquals(list.size(), 3);
        
        for (final Iterator it = list.iterator();it.hasNext();)
        {
            String replacementText = (String) it.next();
            
            if (replacementText.startsWith("['"))
            {
                replacementText = replacementText.substring(2);
                replacementText = replacementText.substring(0, replacementText.length()-2);
            }
            assertTrue(props.containsKey(replacementText));
        }
    }
    
    private class MyCompletionFactory extends ProposalCreationFactoryAdapter
    {
        final List<String>        _replacementText;
        
        /**
         * 
         */
        public MyCompletionFactory()
        {
            super(0, 0);
            _replacementText = new ArrayList<String>();
        }

        public ICompletionProposal createProposal(String replacementText, String displayText, Image displayImage, Object targetObject) 
        {
            _replacementText.add(replacementText);
            return super.createProposal(replacementText, displayText, displayImage, targetObject);
        }
    }
}
