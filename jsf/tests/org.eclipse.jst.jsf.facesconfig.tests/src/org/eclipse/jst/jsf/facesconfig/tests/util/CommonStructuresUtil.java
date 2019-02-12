/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.util;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeNameType;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.DefaultValueType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacetNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacetType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.KeyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.KeyType;
import org.eclipse.jst.jsf.facesconfig.emf.LargeIconType;
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntryType;
import org.eclipse.jst.jsf.facesconfig.emf.NullValueType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyClassType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.SmallIconType;
import org.eclipse.jst.jsf.facesconfig.emf.SuggestedValueType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;

/**
 * Provides utility methods to quickly create and match common structures using
 * a specific prefix
 * 
 */
public final class CommonStructuresUtil extends TestCase {
    private static final String TEST_SANITY = "testSanity";
    private final static FacesConfigPackage _facesConfigPackage = FacesConfigPackage.eINSTANCE;
    private final static FacesConfigFactory _facesConfigFactory = _facesConfigPackage
            .getFacesConfigFactory();

    public static final String ID = "id";
    public static final String LANG = "lang";
    public static final String NAME = "name";
    public static final String CLASS = "class";
    public static final String VALUE = "value";
    private static final String KEY = "key";

    private static final String DESCRIPTION_VALUE = "description";
    private static final String DISPLAY_VALUE = "displayValue";
    private static final String ICON = "icon";
    private static final String SMALL_ICON = createPreficedString("small", ICON);
    private static final String LARGE_ICON = createPreficedString("large", ICON);;

    private static final String SUGGESTED_VALUE = createPreficedString(
            "suggested", VALUE);
    private static final String DEFAULT_VALUE = createPreficedString("default",
            VALUE);;

    private static final String ATTRIBUTE = "attribute";
    private static final String ATTRIBUTE_NAME = createPreficedString(
            ATTRIBUTE, NAME);
    private static final String ATTRIBUTE_CLASS = createPreficedString(
            ATTRIBUTE, CLASS);

    private static final String PROPERTY = "property";
    private static final String PROPERTY_NAME = createPreficedString(PROPERTY,
            NAME);
    private static final String PROPERTY_CLASS = createPreficedString(PROPERTY,
            CLASS);

    private static final String FACET = "facet";
    private static final String FACET_NAME = createPreficedString(FACET, NAME);

    private static final String MANAGED_PROPERTY = "managed-property";
    private static final String MAP_ENTRIES = "map-entries";
    private static final String MAP_ENTRIES_KEY_CLASS =
        createPreficedString(MAP_ENTRIES, "key-class");
    private static final String MAP_ENTRIES_VALUE_CLASS =
        createPreficedString(MAP_ENTRIES, "value-class");
    private static final String MAP_ENTRY = "map-entry";
    
    private static final String LIST_ENTRIES = "list-entries";
    private static final String LIST_ENTRIES_VALUE_CLASS = 
        createPreficedString(LIST_ENTRIES, "value-class");
    
    public static DescriptionType createDescription(final String prefix) {
        DescriptionType description = _facesConfigFactory
                .createDescriptionType();
        description.setTextContent(createPreficedString(prefix,
                DESCRIPTION_VALUE));
        description.setId(createPreficedString(prefix, ID));
        description.setLang(createPreficedString(prefix, LANG));
        return description;
    }

    public static void assertMatchesDescription(String prefix,
            DescriptionType description) {
        assertEquals(createPreficedString(prefix, DESCRIPTION_VALUE),
                description.getTextContent());
        assertEquals(createPreficedString(prefix, ID), description.getId());
        assertEquals(createPreficedString(prefix, LANG), description.getLang());
    }

    public static DisplayNameType createDisplayName(final String prefix) {
        DisplayNameType displayName = _facesConfigFactory
                .createDisplayNameType();
        displayName.setTextContent(createPreficedString(prefix, DISPLAY_VALUE));
        displayName.setId(createPreficedString(prefix, ID));
        displayName.setLang(createPreficedString(prefix, LANG));
        return displayName;
    }

    public static void assertMatchesDisplayName(String prefix,
            DisplayNameType displayName) {
        assertEquals(createPreficedString(prefix, DISPLAY_VALUE), displayName
                .getTextContent());
        assertEquals(createPreficedString(prefix, ID), displayName.getId());
        assertEquals(createPreficedString(prefix, LANG), displayName.getLang());
    }

    public static IconType createIcon(final String prefix) {
        IconType iconType = _facesConfigFactory.createIconType();
        iconType.setId(createPreficedString(prefix, createPreficedString(ICON,
                ID)));
        iconType.setLang(createPreficedString(prefix, LANG));
        SmallIconType smallIconType = _facesConfigFactory.createSmallIconType();
        smallIconType.setTextContent(createPreficedString(prefix, SMALL_ICON));

        LargeIconType largeIconType = _facesConfigFactory.createLargeIconType();
        largeIconType.setTextContent(createPreficedString(prefix, LARGE_ICON));

        iconType.setSmallIcon(smallIconType);
        iconType.setLargeIcon(largeIconType);
        return iconType;
    }

    public static void assertMatchesIcon(final String prefix,
            final IconType iconType) {
        assertEquals(createPreficedString(prefix,
                createPreficedString(ICON, ID)), iconType.getId());
        assertEquals(createPreficedString(prefix, LANG), iconType.getLang());

        SmallIconType smallIconType = iconType.getSmallIcon();
        assertEquals(createPreficedString(prefix, SMALL_ICON), smallIconType
                .getTextContent());

        LargeIconType largeIconType = iconType.getLargeIcon();
        assertEquals(createPreficedString(prefix, LARGE_ICON), largeIconType
                .getTextContent());
    }

    public static AttributeType createAttribute(String prefix) {
        final String ATTRIBUTE_PREFIX = createPreficedString(prefix, ATTRIBUTE);

        AttributeType attrType = _facesConfigFactory.createAttributeType();

        attrType.getDescription().add(createDescription(ATTRIBUTE_PREFIX));
        attrType.getDisplayName().add(createDisplayName(ATTRIBUTE_PREFIX));
        attrType.getIcon().add(createIcon(ATTRIBUTE_PREFIX));

        AttributeNameType attributeNameType = _facesConfigFactory
                .createAttributeNameType();
        attributeNameType.setTextContent(createPreficedString(prefix,
                ATTRIBUTE_NAME));
        attributeNameType.setId(createPreficedString(prefix,
                createPreficedString(ATTRIBUTE_NAME, ID)));
        attrType.setAttributeName(attributeNameType);

        AttributeClassType attributeClassType = _facesConfigFactory
                .createAttributeClassType();
        attributeClassType.setTextContent(createPreficedString(prefix,
                ATTRIBUTE_CLASS));
        attributeClassType.setId(createPreficedString(prefix,
                createPreficedString(ATTRIBUTE_CLASS, ID)));
        attrType.setAttributeClass(attributeClassType);

        DefaultValueType defaultValueType = _facesConfigFactory
                .createDefaultValueType();
        defaultValueType.setTextContent(createPreficedString(prefix,
                createPreficedString(ATTRIBUTE, DEFAULT_VALUE)));
        defaultValueType.setId(createPreficedString(prefix,
                createPreficedString(ATTRIBUTE, createPreficedString(
                        DEFAULT_VALUE, ID))));
        attrType.setDefaultValue(defaultValueType);

        SuggestedValueType suggestedValueType = _facesConfigFactory
                .createSuggestedValueType();
        suggestedValueType.setTextContent(createPreficedString(prefix,
                createPreficedString(ATTRIBUTE, SUGGESTED_VALUE)));
        suggestedValueType.setId(createPreficedString(prefix,
                createPreficedString(ATTRIBUTE, createPreficedString(
                        SUGGESTED_VALUE, ID))));

        attrType.setSuggestedValue(suggestedValueType);

        attrType.setId(createPreficedString(prefix, createPreficedString(
                ATTRIBUTE, ID)));

        return attrType;
    }

    public static void assertMatchAttribute(String prefix,
            AttributeType attrType) {
        final String ATTRIBUTE_PREFIX = createPreficedString(prefix, ATTRIBUTE);

        assertEquals(1, attrType.getDescription().size());
        assertMatchesDescription(ATTRIBUTE_PREFIX, (DescriptionType) attrType
                .getDescription().get(0));

        assertEquals(1, attrType.getDisplayName().size());
        assertMatchesDisplayName(ATTRIBUTE_PREFIX, (DisplayNameType) attrType
                .getDisplayName().get(0));

        assertEquals(1, attrType.getIcon().size());
        assertMatchesIcon(ATTRIBUTE_PREFIX, (IconType) attrType.getIcon()
                .get(0));

        AttributeNameType attributeNameType = attrType.getAttributeName();
        assertEquals(createPreficedString(prefix, ATTRIBUTE_NAME),
                attributeNameType.getTextContent());
        assertEquals(createPreficedString(prefix, createPreficedString(
                ATTRIBUTE_NAME, ID)), attributeNameType.getId());

        AttributeClassType attributeClassType = attrType.getAttributeClass();
        assertEquals(createPreficedString(prefix, ATTRIBUTE_CLASS),
                attributeClassType.getTextContent());
        assertEquals(createPreficedString(prefix, createPreficedString(
                ATTRIBUTE_CLASS, ID)), attributeClassType.getId());

        DefaultValueType defaultValueType = attrType.getDefaultValue();
        assertEquals(createPreficedString(prefix, createPreficedString(
                ATTRIBUTE, DEFAULT_VALUE)), defaultValueType.getTextContent());
        assertEquals(createPreficedString(prefix, createPreficedString(
                ATTRIBUTE, createPreficedString(DEFAULT_VALUE, ID))),
                defaultValueType.getId());

        SuggestedValueType suggestedValueType = attrType.getSuggestedValue();
        assertEquals(createPreficedString(prefix, createPreficedString(
                ATTRIBUTE, SUGGESTED_VALUE)), suggestedValueType
                .getTextContent());
        assertEquals(createPreficedString(prefix, createPreficedString(
                ATTRIBUTE, createPreficedString(SUGGESTED_VALUE, ID))),
                suggestedValueType.getId());

        assertEquals(createPreficedString(prefix, createPreficedString(
                ATTRIBUTE, ID)), attrType.getId());
    }

    public static PropertyType createProperty(final String prefix) {
        final String PROPERTY_PREFIX = createPreficedString(prefix, PROPERTY);

        PropertyType propertyType = _facesConfigFactory.createPropertyType();

        propertyType.getDescription().add(createDescription(PROPERTY_PREFIX));
        propertyType.getDisplayName().add(createDisplayName(PROPERTY_PREFIX));
        propertyType.getIcon().add(createIcon(PROPERTY_PREFIX));

        PropertyNameType propertyNameType = _facesConfigFactory
                .createPropertyNameType();
        propertyNameType.setTextContent(createPreficedString(prefix,
                PROPERTY_NAME));
        propertyNameType.setId(createPreficedString(prefix,
                createPreficedString(PROPERTY_NAME, ID)));
        propertyType.setPropertyName(propertyNameType);

        PropertyClassType propertyClassType = _facesConfigFactory
                .createPropertyClassType();
        propertyClassType.setTextContent(createPreficedString(prefix,
                PROPERTY_CLASS));
        propertyClassType.setId(createPreficedString(prefix,
                createPreficedString(PROPERTY_CLASS, ID)));
        propertyType.setPropertyClass(propertyClassType);

        DefaultValueType defaultValueType = _facesConfigFactory
                .createDefaultValueType();
        defaultValueType.setTextContent(createPreficedString(prefix,
                createPreficedString(PROPERTY, DEFAULT_VALUE)));
        defaultValueType.setId(createPreficedString(prefix,
                createPreficedString(PROPERTY, createPreficedString(
                        DEFAULT_VALUE, ID))));
        propertyType.setDefaultValue(defaultValueType);

        SuggestedValueType suggestedValueType = _facesConfigFactory
                .createSuggestedValueType();
        suggestedValueType.setTextContent(createPreficedString(prefix,
                createPreficedString(PROPERTY, SUGGESTED_VALUE)));
        suggestedValueType.setId(createPreficedString(prefix,
                createPreficedString(PROPERTY, createPreficedString(
                        SUGGESTED_VALUE, ID))));
        propertyType.setSuggestedValue(suggestedValueType);

        propertyType.setId(createPreficedString(prefix, createPreficedString(
                PROPERTY, ID)));

        return propertyType;
    }

    public static void assertMatchProperty(final String prefix,
            final PropertyType property) {
        final String PROPERTY_PREFIX = createPreficedString(prefix, PROPERTY);

        assertEquals(1, property.getDescription().size());
        assertMatchesDescription(PROPERTY_PREFIX, (DescriptionType) property
                .getDescription().get(0));

        assertEquals(1, property.getDisplayName().size());
        assertMatchesDisplayName(PROPERTY_PREFIX, (DisplayNameType) property
                .getDisplayName().get(0));

        assertEquals(1, property.getIcon().size());
        assertMatchesIcon(PROPERTY_PREFIX, (IconType) property.getIcon().get(0));

        PropertyNameType propertyNameType = property.getPropertyName();
        assertEquals(createPreficedString(prefix, PROPERTY_NAME),
                propertyNameType.getTextContent());
        assertEquals(createPreficedString(prefix, createPreficedString(
                PROPERTY_NAME, ID)), propertyNameType.getId());

        PropertyClassType propertyClassType = property.getPropertyClass();
        assertEquals(createPreficedString(prefix, PROPERTY_CLASS),
                propertyClassType.getTextContent());
        assertEquals(createPreficedString(prefix, createPreficedString(
                PROPERTY_CLASS, ID)), propertyClassType.getId());

        DefaultValueType defaultValueType = property.getDefaultValue();
        assertEquals(createPreficedString(prefix, createPreficedString(
                PROPERTY, DEFAULT_VALUE)), defaultValueType.getTextContent());
        assertEquals(createPreficedString(prefix, createPreficedString(
                PROPERTY, createPreficedString(DEFAULT_VALUE, ID))),
                defaultValueType.getId());

        SuggestedValueType suggestedValueType = property.getSuggestedValue();
        assertEquals(createPreficedString(prefix, createPreficedString(
                PROPERTY, SUGGESTED_VALUE)), suggestedValueType
                .getTextContent());
        assertEquals(createPreficedString(prefix, createPreficedString(
                PROPERTY, createPreficedString(SUGGESTED_VALUE, ID))),
                suggestedValueType.getId());

        assertEquals(createPreficedString(prefix, createPreficedString(
                PROPERTY, ID)), property.getId());

    }

    public static FacetType createFacet(final String prefix) {
        final String FACET_PREFIX = createPreficedString(prefix, FACET);

        final FacetType facet = _facesConfigFactory.createFacetType();

        facet.getDescription().add(createDescription(FACET_PREFIX));
        facet.getDisplayName().add(createDisplayName(FACET_PREFIX));
        facet.getIcon().add(createIcon(FACET_PREFIX));

        FacetNameType facetNameType = _facesConfigFactory.createFacetNameType();
        facetNameType.setTextContent(createPreficedString(prefix, FACET_NAME));
        facetNameType.setId(createPreficedString(prefix, createPreficedString(
                FACET_NAME, ID)));
        facet.setFacetName(facetNameType);

        facet.setId(createPreficedString(prefix,
                createPreficedString(FACET, ID)));

        return facet;
    }

    public static void assertMatchFacet(final String prefix, FacetType facet) {
        final String FACET_PREFIX = createPreficedString(prefix, FACET);

        assertEquals(1, facet.getDescription().size());
        assertMatchesDescription(FACET_PREFIX, (DescriptionType) facet
                .getDescription().get(0));

        assertEquals(1, facet.getDisplayName().size());
        assertMatchesDisplayName(FACET_PREFIX, (DisplayNameType) facet
                .getDisplayName().get(0));

        assertEquals(1, facet.getIcon().size());
        assertMatchesIcon(FACET_PREFIX, (IconType) facet.getIcon().get(0));

        FacetNameType propertyNameType = facet.getFacetName();
        assertEquals(createPreficedString(prefix, FACET_NAME), propertyNameType
                .getTextContent());
        assertEquals(createPreficedString(prefix, createPreficedString(
                FACET_NAME, ID)), propertyNameType.getId());

        assertEquals(createPreficedString(prefix, createPreficedString(FACET,
                ID)), facet.getId());
    }

    public static ManagedPropertyType createManagedPropertyBase(
            final String prefix) {
        final String MANAGED_PROPERTY_PREFIX = createPreficedString(prefix,
                MANAGED_PROPERTY);

        final ManagedPropertyType managedProperty = _facesConfigFactory
                .createManagedPropertyType();

        managedProperty.getDescription().add(
                createDescription(MANAGED_PROPERTY_PREFIX));
        managedProperty.getDisplayName().add(
                createDisplayName(MANAGED_PROPERTY_PREFIX));
        managedProperty.getIcon().add(createIcon(MANAGED_PROPERTY_PREFIX));

        PropertyNameType propertyNameType = _facesConfigFactory
                .createPropertyNameType();
        propertyNameType.setTextContent(createPreficedString(prefix,
                PROPERTY_NAME));
        propertyNameType.setId(createPreficedString(prefix,
                createPreficedString(PROPERTY_NAME, ID)));
        managedProperty.setPropertyName(propertyNameType);

        PropertyClassType propertyClassType = _facesConfigFactory
                .createPropertyClassType();
        propertyClassType.setTextContent(createPreficedString(prefix,
                PROPERTY_CLASS));
        propertyClassType.setId(createPreficedString(prefix,
                createPreficedString(PROPERTY_CLASS, ID)));
        managedProperty.setPropertyClass(propertyClassType);

        return managedProperty;
    }

    public static void assertMatchManagedPropertyBase(final String prefix,
            final ManagedPropertyType managedProperty) {
        final String MANAGED_PROPERTY_PREFIX = createPreficedString(prefix,
                MANAGED_PROPERTY);

        assertEquals(1, managedProperty.getDescription().size());
        assertMatchesDescription(MANAGED_PROPERTY_PREFIX,
                (DescriptionType) managedProperty.getDescription().get(0));

        assertEquals(1, managedProperty.getDisplayName().size());
        assertMatchesDisplayName(MANAGED_PROPERTY_PREFIX,
                (DisplayNameType) managedProperty.getDisplayName().get(0));

        assertEquals(1, managedProperty.getIcon().size());
        assertMatchesIcon(MANAGED_PROPERTY_PREFIX, (IconType) managedProperty
                .getIcon().get(0));

        {
            PropertyNameType propertyNameType = managedProperty
                    .getPropertyName();
            assertEquals(createPreficedString(prefix, PROPERTY_NAME),
                    propertyNameType.getTextContent());
            assertEquals(createPreficedString(prefix, createPreficedString(
                    PROPERTY_NAME, ID)), propertyNameType.getId());
        }

        {
            PropertyClassType propertyClassType = managedProperty
                    .getPropertyClass();

            assertEquals(createPreficedString(prefix, PROPERTY_CLASS),
                    propertyClassType.getTextContent());
            assertEquals(createPreficedString(prefix, createPreficedString(
                    PROPERTY_CLASS, ID)), propertyClassType.getId());
        }

    }

    public static ValueType createValue(final String prefix) {
        final String VALUE_PREFIX = createPreficedString(prefix, VALUE);
        final ValueType value = _facesConfigFactory.createValueType();

        value.setTextContent(VALUE_PREFIX);
        value.setId(createPreficedString(VALUE_PREFIX, ID));
        
        return value;
    }

    public static void assertMatchValue(final String prefix, ValueType value)
    {
        final String VALUE_PREFIX = createPreficedString(prefix, VALUE);

        assertEquals(VALUE_PREFIX, value.getTextContent());
        assertEquals(createPreficedString(VALUE_PREFIX, ID), value.getId());
    }
    
    public static MapEntriesType createMapEntries(final String prefix)
    {
        final String MAP_ENTRIES_PREFIX = 
            createPreficedString(prefix, 
                    createPreficedString(MANAGED_PROPERTY, MAP_ENTRIES));
        final MapEntriesType mapEntries = 
            _facesConfigFactory.createMapEntriesType();
        
        {
            KeyClassType keyClass = _facesConfigFactory.createKeyClassType();
            keyClass.setTextContent(createPreficedString(prefix,MAP_ENTRIES_KEY_CLASS));
            keyClass.setId(createPreficedString(prefix,
                    createPreficedString(MAP_ENTRIES_KEY_CLASS, ID)));
            mapEntries.setKeyClass(keyClass);
        }
        
        {
            ValueClassType valueClass = _facesConfigFactory.createValueClassType();
            valueClass.setTextContent(MAP_ENTRIES_VALUE_CLASS);
            valueClass.setId(createPreficedString(MAP_ENTRIES_VALUE_CLASS, ID));
            mapEntries.setValueClass(valueClass);
        }

        {
            MapEntryType mapEntry = _facesConfigFactory.createMapEntryType();
            mapEntry.setId(createPreficedString(MAP_ENTRY, ID));
            KeyType keyType = _facesConfigFactory.createKeyType();
            keyType.setId(createPreficedString(createPreficedString(MAP_ENTRY, ID), KEY));
            keyType.setTextContent(createPreficedString(MAP_ENTRY, KEY));
            mapEntry.setKey(keyType);
            mapEntry.setValue(createValue(MAP_ENTRIES_PREFIX));
            
            mapEntries.getMapEntry().add(mapEntry);
        }
        
        {
            MapEntryType mapEntry = _facesConfigFactory.createMapEntryType();
            mapEntry.setId(createPreficedString(createPreficedString(MAP_ENTRY, ID), "2"));
            KeyType keyType = _facesConfigFactory.createKeyType();
            keyType.setId(createPreficedString(createPreficedString(createPreficedString(MAP_ENTRY, ID), KEY),"2"));
            keyType.setTextContent(createPreficedString(createPreficedString(MAP_ENTRY, KEY), "2"));
            mapEntry.setKey(keyType);
            NullValueType nullValue = _facesConfigFactory.createNullValueType();
            nullValue.setId(createPreficedString("null", MAP_ENTRY));
            mapEntry.setNullValue(nullValue);
            
            mapEntries.getMapEntry().add(mapEntry);
        }
        
        mapEntries.setId(createPreficedString(MAP_ENTRIES_PREFIX, ID));

        return mapEntries;
    }
    
    public static void assertMatchMapEntries(final String prefix, MapEntriesType mapEntries)
    {
        final String MAP_ENTRIES_PREFIX = 
            createPreficedString(prefix, 
                    createPreficedString(MANAGED_PROPERTY, MAP_ENTRIES));
       
        {
            KeyClassType keyClass = mapEntries.getKeyClass();
            assertEquals(createPreficedString(prefix,MAP_ENTRIES_KEY_CLASS)
                    , keyClass.getTextContent());
            assertEquals(createPreficedString(prefix,
                            createPreficedString(MAP_ENTRIES_KEY_CLASS, ID))
                    , keyClass.getId());
        }
        
        {
            ValueClassType valueClass = mapEntries.getValueClass();
            assertEquals(MAP_ENTRIES_VALUE_CLASS, valueClass.getTextContent());
            assertEquals(createPreficedString(MAP_ENTRIES_VALUE_CLASS, ID)
                    , valueClass.getId());
        }

        assertEquals(2, mapEntries.getMapEntry().size());
        {
            MapEntryType mapEntry = 
                (MapEntryType) mapEntries.getMapEntry().get(0); 
            assertEquals(createPreficedString(MAP_ENTRY, ID)
                    , mapEntry.getId());
            
            KeyType keyType = mapEntry.getKey();
            assertEquals(createPreficedString(createPreficedString(MAP_ENTRY, ID), KEY)
                    , keyType.getId());
            assertEquals(createPreficedString(MAP_ENTRY, KEY)
                    , keyType.getTextContent());
            assertMatchValue(MAP_ENTRIES_PREFIX, mapEntry.getValue());
        }
        
        {
            MapEntryType mapEntry = 
                (MapEntryType) mapEntries.getMapEntry().get(1); 
            assertEquals(createPreficedString(createPreficedString(MAP_ENTRY, ID), "2")
                    , mapEntry.getId());
            
            KeyType keyType = mapEntry.getKey();
            assertEquals(createPreficedString(
                            createPreficedString(createPreficedString(MAP_ENTRY, ID), KEY), "2")
                                , keyType.getId());

            NullValueType nullValue = mapEntry.getNullValue();
            assertEquals(createPreficedString("null", MAP_ENTRY), nullValue.getId());
        }

        assertEquals(createPreficedString(MAP_ENTRIES_PREFIX, ID)
                , mapEntries.getId());
    }
    
    public static ListEntriesType createListEntries(final String prefix)
    {
        final String LIST_ENTRIES_PREFIX = 
            createPreficedString(prefix, 
                    createPreficedString(MANAGED_PROPERTY, LIST_ENTRIES));
        final ListEntriesType listEntries = 
            _facesConfigFactory.createListEntriesType();

        {
            ValueClassType valueClass = _facesConfigFactory.createValueClassType();
            valueClass.setTextContent(LIST_ENTRIES_VALUE_CLASS);
            valueClass.setId(createPreficedString(LIST_ENTRIES_VALUE_CLASS, ID));
            listEntries.setValueClass(valueClass);
        }

        listEntries.getValue().add(createValue(LIST_ENTRIES_PREFIX));
        listEntries.setId(createPreficedString(LIST_ENTRIES_PREFIX, ID));

        return listEntries;
    }
    
    public static void assertMatchListEntries(final String prefix, ListEntriesType listEntries)
    {
        final String LIST_ENTRIES_PREFIX = 
            createPreficedString(prefix, 
                    createPreficedString(MANAGED_PROPERTY, LIST_ENTRIES));

        {
            ValueClassType valueClass = listEntries.getValueClass();
            assertEquals(LIST_ENTRIES_VALUE_CLASS, valueClass.getTextContent());
            assertEquals(createPreficedString(LIST_ENTRIES_VALUE_CLASS, ID)
                    , valueClass.getId());
        }

        assertEquals(1,listEntries.getValue().size());
        ValueType value = (ValueType) listEntries.getValue().get(0);
        assertMatchValue(LIST_ENTRIES_PREFIX, value);
        
        assertEquals(createPreficedString(LIST_ENTRIES_PREFIX, ID), listEntries.getId());
    }
    
    public static String createPreficedString(final String prefix,
            final String value) {
        return prefix + "-" + value;
    }

    /**
     * Tests that what is created by each method is successfully matched by the
     * corresponding match method
     */
    public void testSanity() {
        assertMatchesDescription(TEST_SANITY, createDescription(TEST_SANITY));
        assertMatchesDisplayName(TEST_SANITY, createDisplayName(TEST_SANITY));
        assertMatchesIcon(TEST_SANITY, createIcon(TEST_SANITY));
        assertMatchAttribute(TEST_SANITY, createAttribute(TEST_SANITY));
        assertMatchProperty(TEST_SANITY, createProperty(TEST_SANITY));
        assertMatchFacet(TEST_SANITY, createFacet(TEST_SANITY));
        assertMatchManagedPropertyBase(TEST_SANITY,
                createManagedPropertyBase(TEST_SANITY));
        assertMatchValue(TEST_SANITY, createValue(TEST_SANITY));
        assertMatchMapEntries(TEST_SANITY, createMapEntries(TEST_SANITY));
        assertMatchListEntries(TEST_SANITY, createListEntries(TEST_SANITY));
    }
}
