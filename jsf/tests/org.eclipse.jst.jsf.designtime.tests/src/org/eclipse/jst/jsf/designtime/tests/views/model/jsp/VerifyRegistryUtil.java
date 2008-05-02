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
package org.eclipse.jst.jsf.designtime.tests.views.model.jsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IJSFTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;
import org.eclipse.jst.jsf.common.runtime.tests.model.RuntimeTestUtil;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;

public class VerifyRegistryUtil extends Assert
{
    // core verifiers
    public static final Verifier ACTIONLISTENENER_VERIFIER = new JSFTagElementVerifier(
            IJSFConstants.TAG_IDENTIFIER_ACTIONLISTENER);

    public static final Verifier ATTRIBUTE_VERIFIER = new JSFTagElementVerifier(
            IJSFConstants.TAG_IDENTIFIER_ATTRIBUTE);

    public static final Verifier CONVERTDATETIME_VERIFIER = new ConverterTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_CONVERTDATETIME,
            RuntimeTestUtil.CONVERTERINFO_DATETIME);

    public static final Verifier CONVERTER_VERIFIER = new ConverterTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_CONVERTER, ConverterTypeInfo.UNKNOWN);

    public static final Verifier CONVERTNUMBER_VERIFIER = new ConverterTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_CONVERTNUMBER,
            RuntimeTestUtil.CONVERTERINFO_NUMBER);

    public static final Verifier FACET_VERIFIER = new JSFTagElementVerifier(
            IJSFConstants.TAG_IDENTIFIER_FACET);

    public static final Verifier LOADBUNDLE_VERIFIER = new TagHandlerVerifier(
            IJSFConstants.TAG_IDENTIFIER_LOADBUNDLE);

    public static final Verifier PARAM_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_PARAM, RuntimeTestUtil.COMPINFO_PARAM);

    public static final Verifier PHASELISTENER_VERIFIER = new TagHandlerVerifier(
            IJSFConstants.TAG_IDENTIFIER_PHASELISTENER);

    public static final Verifier SELECTITEM_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_SELECTITEM,
            RuntimeTestUtil.COMPINFO_SELECTITEM);

    public static final Verifier SELECTITEMS_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_SELECTITEMS,
            RuntimeTestUtil.COMPINFO_SELECTITEMS);

    public static final Verifier SETPROPERTYACTIONLISTENER_VERIFIER = new TagHandlerVerifier(
            IJSFConstants.TAG_IDENTIFIER_SETPROPERTYACTIONLISTENER);

    public static final Verifier SUBVIEW_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_SUBVIEW,
            RuntimeTestUtil.COMPINFO_SUBVIEW);

    public static final Verifier VALIDATEDOUBLERANGE_VERIFIER = new ValidatorTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_VALIDATEDOUBLERANGE,
            RuntimeTestUtil.VALIDATORINFO_DOUBLERANGE);

    public static final Verifier VALIDATELENGTH_VERIFIER = new ValidatorTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_VALIDATELENGTH,
            RuntimeTestUtil.VALIDATORINFO_LENGTH);

    public static final Verifier VALIDATELONGRANGE_VERIFIER = new ValidatorTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_VALIDATELONGRANGE,
            RuntimeTestUtil.VALIDATORINFO_LONGRANGE);

    public static final Verifier VALIDATOR_VERIFIER = new ValidatorTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_VALIDATOR, ValidatorTypeInfo.UNKNOWN);

    public static final Verifier VALUECHANGELISTENER_VERIFIER = new JSFTagElementVerifier(
            IJSFConstants.TAG_IDENTIFIER_VALUECHANGELISTENER);

    public static final Verifier VERBATIM_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_VERBATIM,
            RuntimeTestUtil.COMPINFO_VERBATIM);

    public static final Verifier VIEW_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_VIEW, RuntimeTestUtil.COMPINFO_VIEW);

    // html verifier
    public static final Verifier COLUMN_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_COLUMN,
            RuntimeTestUtil.COMPINFO_COLUMN);

    public static final Verifier COMMANDBUTTON_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON,
            RuntimeTestUtil.COMPINFO_COMMAND);

    public static final Verifier COMMANDLINK_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_COMMANDLINK,
            RuntimeTestUtil.COMPINFO_COMMANDLINK);

    public static final Verifier DATATABLE_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_DATA_TABLE,
            RuntimeTestUtil.COMPINFO_DATATABLE);

    public static final Verifier FORM_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_FORM, RuntimeTestUtil.COMPINFO_FORM);

    public static final Verifier GRAPHICIMAGE_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_GRAPHICIMAGE,
            RuntimeTestUtil.COMPINFO_GRAPHIC);

    public static final Verifier INPUTHIDDEN_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_INPUTHIDDEN,
            RuntimeTestUtil.COMPINFO_HIDDEN);

    public static final Verifier INPUTSECRET_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_INPUTSECRET,
            RuntimeTestUtil.COMPINFO_SECRET);

    public static final Verifier INPUTTEXT_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_INPUTTEXT,
            RuntimeTestUtil.COMPINFO_INPUTTEXT);

    public static final Verifier INPUTTEXTAREA_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_INPUTTEXTAREA,
            RuntimeTestUtil.COMPINFO_INPUTTEXTAREA);

    public static final Verifier MESSAGE_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_MESSAGE,
            RuntimeTestUtil.COMPINFO_MESSAGE);

    public static final Verifier MESSAGES_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_MESSAGES,
            RuntimeTestUtil.COMPINFO_MESSAGES);

    public static final Verifier OUTPUTFORMAT_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_OUTPUTFORMAT,
            RuntimeTestUtil.COMPINFO_OUTPUTFORMAT);

    public static final Verifier OUTPUTLABEL_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL,
            RuntimeTestUtil.COMPINFO_OUTPUTLABEL);

    public static final Verifier OUTPUTLINK_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_OUTPUTLINK,
            RuntimeTestUtil.COMPINFO_OUTPUTLINK);

    public static final Verifier OUTPUTTEXT_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT,
            RuntimeTestUtil.COMPINFO_OUTPUTTEXT);

    public static final Verifier PANELGRID_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_PANEL_GRID,
            RuntimeTestUtil.COMPINFO_PANELGRID);

    public static final Verifier PANELGROUP_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_PANEL_GROUP,
            RuntimeTestUtil.COMPINFO_PANELGROUP);

    public static final Verifier SELECTBOOLEANCHECKBOX_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX,
            RuntimeTestUtil.COMPINFO_SELECTBOOLEANCHECKBOX);

    public static final Verifier SELECTMANYCHECKBOX_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_SELECTMANYCHECKBOX,
            RuntimeTestUtil.COMPINFO_SELECTMANYCHECKBOX);

    public static final Verifier SELECTMANYLISTBOX_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_SELECTMANYLISTBOX,
            RuntimeTestUtil.COMPINFO_SELECTMANYLISTBOX);

    public static final Verifier SELECTMANYMENU_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_SELECTMANYMENU,
            RuntimeTestUtil.COMPINFO_SELECTMANYMENU);

    public static final Verifier SELECTONELISTBOX_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_SELECTONELISTBOX,
            RuntimeTestUtil.COMPINFO_SELECTONELISTBOX);

    public static final Verifier SELECTONEMENU_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_SELECTONEMENU,
            RuntimeTestUtil.COMPINFO_SELECTONEMENU);

    public static final Verifier SELECTONERADIO_VERIFIER = new ComponentTagVerifier(
            IJSFConstants.TAG_IDENTIFIER_SELECTONERADIO,
            RuntimeTestUtil.COMPINFO_SELECTONERADIO);

    public static final List<Verifier> CORE_VERIFIERS_11;
    public static final List<Verifier> CORE_VERIFIERS_12;

    public static final List<Verifier> HTML_VERIFIERS;

    static
    {
        List<Verifier> verifiers = new ArrayList<Verifier>();

        verifiers.add(ACTIONLISTENENER_VERIFIER);
        verifiers.add(ATTRIBUTE_VERIFIER);
        verifiers.add(CONVERTDATETIME_VERIFIER);
        verifiers.add(CONVERTER_VERIFIER);
        verifiers.add(CONVERTNUMBER_VERIFIER);
        verifiers.add(FACET_VERIFIER);
        verifiers.add(LOADBUNDLE_VERIFIER);
        verifiers.add(PARAM_VERIFIER);
        verifiers.add(SELECTITEM_VERIFIER);
        verifiers.add(SELECTITEMS_VERIFIER);
        verifiers.add(SUBVIEW_VERIFIER);
        verifiers.add(VALIDATEDOUBLERANGE_VERIFIER);
        verifiers.add(VALIDATELENGTH_VERIFIER);
        verifiers.add(VALIDATELONGRANGE_VERIFIER);
        verifiers.add(VALIDATOR_VERIFIER);
        verifiers.add(VALUECHANGELISTENER_VERIFIER);
        verifiers.add(VERBATIM_VERIFIER);
        verifiers.add(VIEW_VERIFIER);

        CORE_VERIFIERS_11 = Collections.unmodifiableList(verifiers);

        verifiers = new ArrayList<Verifier>(CORE_VERIFIERS_11);
        verifiers.add(PHASELISTENER_VERIFIER);
        verifiers.add(SETPROPERTYACTIONLISTENER_VERIFIER);
        CORE_VERIFIERS_12 = Collections.unmodifiableList(verifiers);

        verifiers = new ArrayList<Verifier>();

        verifiers.add(COLUMN_VERIFIER);
        verifiers.add(COMMANDBUTTON_VERIFIER);
        verifiers.add(COMMANDLINK_VERIFIER);
        verifiers.add(DATATABLE_VERIFIER);
        verifiers.add(FORM_VERIFIER);
        verifiers.add(GRAPHICIMAGE_VERIFIER);
        verifiers.add(INPUTHIDDEN_VERIFIER);
        verifiers.add(INPUTSECRET_VERIFIER);
        verifiers.add(INPUTTEXT_VERIFIER);
        verifiers.add(INPUTTEXTAREA_VERIFIER);
        verifiers.add(MESSAGE_VERIFIER);
        verifiers.add(MESSAGES_VERIFIER);
        verifiers.add(OUTPUTFORMAT_VERIFIER);
        verifiers.add(OUTPUTLABEL_VERIFIER);
        verifiers.add(OUTPUTLINK_VERIFIER);
        verifiers.add(OUTPUTTEXT_VERIFIER);
        verifiers.add(PANELGRID_VERIFIER);
        verifiers.add(PANELGROUP_VERIFIER);
        verifiers.add(SELECTBOOLEANCHECKBOX_VERIFIER);
        verifiers.add(SELECTMANYCHECKBOX_VERIFIER);
        verifiers.add(SELECTMANYLISTBOX_VERIFIER);
        verifiers.add(SELECTMANYMENU_VERIFIER);
        verifiers.add(SELECTONELISTBOX_VERIFIER);
        verifiers.add(SELECTONEMENU_VERIFIER);
        verifiers.add(SELECTONERADIO_VERIFIER);

        HTML_VERIFIERS = Collections.unmodifiableList(verifiers);
    }

    public static void runVerifiers(final List<Verifier> verifiers,
            final Map<String, ITagElement> tags)
    {
        for (final Verifier verifier : verifiers)
        {
            verifier.verify(tags);
        }
    }

    abstract static class Verifier
    {
        protected final TagIdentifier _tagId;

        public Verifier(final TagIdentifier tagId)
        {
            _tagId = tagId;
        }

        public abstract void verify(final Map<String, ITagElement> tagElements);
    }

    abstract static class TypeVerifier<TAGTYPEINFO> extends Verifier
    {
        protected final TAGTYPEINFO _typeInfo;

        public TypeVerifier(final TagIdentifier tagId,
                final TAGTYPEINFO typeInfo)
        {
            super(tagId);
            _typeInfo = typeInfo;
        }
    }

    static class ConverterTagVerifier extends TypeVerifier<ConverterTypeInfo>
    {
        public ConverterTagVerifier(final TagIdentifier tagId,
                final ConverterTypeInfo typeInfo)
        {
            super(tagId, typeInfo);
        }

        @Override
        public void verify(final Map<String, ITagElement> tagElements)
        {
            final ITagElement tagElement = tagElements.remove(_tagId
                    .getTagName());
            assertTrue("Expected element to be converter: "
                    + _tagId.getTagName(),
                    tagElement instanceof IConverterTagElement);

            final IConverterTagElement converterTag = (IConverterTagElement) tagElement;
            final ConverterTypeInfo converterTypeInfo = converterTag
                    .getConverter();
            // do verifySame from common.runtime.tests
            try
            {
                RuntimeTestUtil.verifySame(_typeInfo, converterTypeInfo);
            }
            catch (final AssertionFailedError afe)
            {
                System.err.printf("Failure occurred in tag %s", _tagId
                        .asQName().toString());
                throw afe;
            }
        }
    }

    static class ValidatorTagVerifier extends TypeVerifier<ValidatorTypeInfo>
    {
        public ValidatorTagVerifier(final TagIdentifier tagId,
                final ValidatorTypeInfo typeInfo)
        {
            super(tagId, typeInfo);
        }

        @Override
        public void verify(final Map<String, ITagElement> tagElements)
        {
            final ITagElement tagElement = tagElements.remove(_tagId
                    .getTagName());
            assertTrue("Expected element to be validator: "
                    + _tagId.getTagName(),
                    tagElement instanceof IValidatorTagElement);
            final IValidatorTagElement validatorTag = (IValidatorTagElement) tagElement;
            final ValidatorTypeInfo validatorTypeInfo = validatorTag
                    .getValidator();
            // do verifySame from common.runtime.tests
            try
            {
                RuntimeTestUtil.verifySame(_typeInfo, validatorTypeInfo);
            }
            catch (final AssertionFailedError afe)
            {
                System.err.printf("Failure occurred in tag %s", _tagId
                        .asQName().toString());
                throw afe;
            }
        }
    }

    static class ComponentTagVerifier extends TypeVerifier<ComponentTypeInfo>
    {
        public ComponentTagVerifier(final TagIdentifier tagId,
                final ComponentTypeInfo typeInfo)
        {
            super(tagId, typeInfo);
        }

        @Override
        public void verify(final Map<String, ITagElement> tagElements)
        {
            final ITagElement tagElement = tagElements.remove(_tagId
                    .getTagName());
            assertTrue("Expected element to be component: "
                    + _tagId.getTagName(),
                    tagElement instanceof IComponentTagElement);
            final IComponentTagElement componentTag = (IComponentTagElement) tagElement;
            final ComponentTypeInfo componentTypeInfo = componentTag
                    .getComponent();
            // do verifySame from common.runtime.tests
            try
            {
                RuntimeTestUtil.verifySame(_typeInfo, componentTypeInfo);
            }
            catch (final AssertionFailedError afe)
            {
                System.err.printf("Failure occurred in tag %s", _tagId
                        .asQName().toString());
                throw afe;
            }
        }
    }

    public static class JSFTagElementVerifier extends Verifier
    {
        public JSFTagElementVerifier(final TagIdentifier tagId)
        {
            super(tagId);
        }

        @Override
        public void verify(final Map<String, ITagElement> tagElements)
        {
            final ITagElement tagElement = tagElements.remove(_tagId
                    .getTagName());
            assertTrue("Expected jsf element to be jsf tag: "
                    + _tagId.getTagName(), tagElement instanceof IJSFTagElement);
        }
    }

    public static class TagHandlerVerifier extends Verifier
    {
        public TagHandlerVerifier(final TagIdentifier tagId)
        {
            super(tagId);
        }

        @Override
        public void verify(final Map<String, ITagElement> tagElements)
        {
            final ITagElement tagElement = tagElements.remove(_tagId
                    .getTagName());
            assertTrue("Expected handler element to be jsf tag: "
                    + _tagId.getTagName(), tagElement instanceof ITagElement);
        }
    }

    public static class CompositeVerifier
    {
        private final List<Verifier> _verifiers;
        private final int _minExpectedTags;
        private final int _maxExpectedTags;

        public CompositeVerifier(final List<Verifier> verifiers,
                final int minExpectedTags, final int maxExpectedTags)
        {
            _verifiers = verifiers;
            _minExpectedTags = minExpectedTags;
            _maxExpectedTags = maxExpectedTags;
        }

        public void verify(final Map<String, ITagElement> tags)
        {
            final int size = tags.size();

            assertTrue(size >= _minExpectedTags);
            assertTrue(size <= _maxExpectedTags);

            runVerifiers(_verifiers, tags);

            assertTrue(tags.size() <= (size - _minExpectedTags));
        }
    }
}
