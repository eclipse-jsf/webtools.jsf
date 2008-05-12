package org.eclipse.jst.jsf.designtime.internal.view.mapping;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.types.ClassTypeInfo;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ClassTypeInfo_;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingFactory;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentTypeInfo_;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ConverterTypeInfo_;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ValidatorTypeInfo_;

/**
 * Maps meta-data to and from common.runtime data. In future, we may eliminate
 * this class by making the EMF meta-data structures into the common.runtime
 * data.
 * 
 * @author cbateman
 * 
 */
public final class ViewMetadataMapper
{

    /**
     * The default trait id where meta-data will annotated
     */
    public final static String DEFAULT_MAPPING_TRAIT_ID = "viewElementMapping"; //$NON-NLS-1$
    /**
     * The default trait id where meta-data will be annotated.
     */
    public final static String DEFAULT_ATTRIBUTE_TRAIT_ID = "attributeMapping"; //$NON-NLS-1$

    /**
     * @param classTypeInfo
     * @return a framework ClassTypeInfo equivalent to classTypeInfo or null if
     *         none.
     */
    public ClassTypeInfo mapToFrameworkData(final ClassTypeInfo_ classTypeInfo)
    {
        if (classTypeInfo instanceof ComponentTypeInfo_)
        {
            return createComponentTypeInfo_((ComponentTypeInfo_) classTypeInfo);
        }
        else if (classTypeInfo instanceof ConverterTypeInfo_)
        {
            return createConverterTypeInfo_((ConverterTypeInfo_) classTypeInfo);
        }
        else if (classTypeInfo instanceof ValidatorTypeInfo_)
        {
            return createValidatorTypeInfo_((ValidatorTypeInfo_) classTypeInfo);
        }
        return null;
    }

    private ClassTypeInfo createValidatorTypeInfo_(
            ValidatorTypeInfo_ classTypeInfo)
    {
        final String[] superClasses = classTypeInfo.getSuperClasses().toArray(
                new String[0]);
        final String[] interfaces = classTypeInfo.getInterfaces().toArray(
                new String[0]);
        if (classTypeInfo.getClassName()==null && classTypeInfo.getValidatorId() == null)
        {
            return ValidatorTypeInfo.UNKNOWN;
        }

        return new ValidatorTypeInfo(classTypeInfo.getClassName(),
                superClasses, interfaces, classTypeInfo.getValidatorId());
    }

    private ClassTypeInfo createConverterTypeInfo_(
            ConverterTypeInfo_ classTypeInfo)
    {
        final String[] superClasses = classTypeInfo.getSuperClasses().toArray(
                new String[0]);
        final String[] interfaces = classTypeInfo.getInterfaces().toArray(
                new String[0]);
        if (classTypeInfo.getClassName() == null
                && classTypeInfo.getConverterId() == null)
        {
            return ConverterTypeInfo.UNKNOWN;
        }
        final String[] forClasses = classTypeInfo.getForClasses().toArray(
                new String[0]);
        return new ConverterTypeInfo(classTypeInfo.getClassName(),
                superClasses, interfaces, classTypeInfo.getConverterId(),
                forClasses);
    }

    private ClassTypeInfo createComponentTypeInfo_(
            ComponentTypeInfo_ classTypeInfo)
    {
        final String[] superClasses = classTypeInfo.getSuperClasses().toArray(
                new String[0]);
        final String[] interfaces = classTypeInfo.getInterfaces().toArray(
                new String[0]);
        return new ComponentTypeInfo(classTypeInfo.getComponentType(),
                classTypeInfo.getClassName(), superClasses, interfaces,
                classTypeInfo.getComponentFamily(), classTypeInfo
                        .getRenderType());
    }

    /**
     * @param classTypeInfo
     * @return a metadata type info matching classTypeInfo or null if
     *         classTypeInfo isn't of a supported concrete type.
     */
    public ClassTypeInfo_ mapToMetadata(final ClassTypeInfo classTypeInfo)
    {
        if (classTypeInfo instanceof ComponentTypeInfo)
        {
            return createComponentTypeInfo((ComponentTypeInfo) classTypeInfo);
        }
        else if (classTypeInfo instanceof ConverterTypeInfo)
        {
            return createConverterTypeInfo((ConverterTypeInfo) classTypeInfo);
        }
        else if (classTypeInfo instanceof ValidatorTypeInfo)
        {
            return createValidatorTypeInfo((ValidatorTypeInfo) classTypeInfo);
        }
        return null;
    }

    private ClassTypeInfo_ createComponentTypeInfo(
            final ComponentTypeInfo typeInfo)
    {
        final ComponentTypeInfo_ metadata = ComponentMappingFactory.eINSTANCE
                .createComponentTypeInfo_();
        metadata.setComponentFamily(typeInfo.getComponentFamily());
        metadata.setComponentType(typeInfo.getComponentType());
        metadata.setRenderType(typeInfo.getRenderFamily());
        copy(typeInfo, metadata);
        return metadata;
    }

    private ClassTypeInfo_ createConverterTypeInfo(
            final ConverterTypeInfo typeInfo)
    {
        final ConverterTypeInfo_ metadata = ComponentMappingFactory.eINSTANCE
                .createConverterTypeInfo_();
        copy(typeInfo, metadata);
        metadata.setConverterId(typeInfo.getConverterId());
        return metadata;
    }

    private ClassTypeInfo_ createValidatorTypeInfo(
            final ValidatorTypeInfo typeInfo)
    {
        final ValidatorTypeInfo_ metadata = ComponentMappingFactory.eINSTANCE
                .createValidatorTypeInfo_();
        copy(typeInfo, metadata);
        metadata.setValidatorId(typeInfo.getValidatorId());
        return metadata;
    }

    private void copy(ClassTypeInfo source, ClassTypeInfo_ dest)
    {
        dest.setClassName(source.getClassName());
        for (final String interfaze : source.getInterfaces())
        {
            dest.getInterfaces().add(interfaze);
        }

        for (final String superClass : source.getSuperClasses())
        {
            dest.getSuperClasses().add(superClass);
        }
    }
}
