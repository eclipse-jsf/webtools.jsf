/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.designtime.symbols;

import java.util.Arrays;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.common.util.TypeUtil;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.validation.internal.appconfig.AppConfigValidationUtil;
import org.eclipse.jst.jsf.validation.internal.el.ELExpressionValidator;
import org.eclipse.jst.jsf.validation.internal.el.IExpressionSemanticValidator;
import org.w3c.dom.Element;

/**
 * A framework provided variable factory for EL model objects that are
 * constructed based on DataModel's dervied from an EL expression. 
 * 
 * @author cbateman
 *
 */
public abstract class AbstractDataModelVariableFactory 
{
    /**
     * @param elText The EL expression text. Must not be null
     * @param elContext The document context pointing to elText in the source document. Must not be null
     * @param file The workspace resource that contains elText. Must not be null.
     * @return the value expression resolved from elText or null if it cannot
     * be resolved or elText doesn't resolve to value expression (i.e. is a method expression)
     */
    public final ValueType createValueExpression(final String elText, final IStructuredDocumentContext elContext, final IFile file)
    {
        assert elText != null;
        assert elContext != null;
        assert file != null;
        
        final ELExpressionValidator validator = 
            new ELExpressionValidator(elContext, elText, file);
        validator.validateXMLNode();
        final IExpressionSemanticValidator semValidator = 
            validator.getSemanticValidator();
        if (semValidator != null
                && semValidator.getExpressionType() instanceof ValueType)
        {
            return (ValueType) semValidator.getExpressionType();
        }
        return null;
    }
    
    /**
     * @param symbolName The name of the symbol to be created. Must not be null
     * @param signature The type signature of the array type. Must not be null
     * @param javaProject must not be null
     * @return a symbol based approximating an implicit DataModel wrapper for an array
     */
    protected final ISymbol createArraySymbol(final String symbolName, final String signature, final IJavaProject javaProject)
    {
        assert symbolName != null;
        assert signature != null;
        assert javaProject != null;
        
        final String arrayElementType = Signature.getElementType(signature);
        final int arrayCount = Signature.getArrayCount(signature);
        String  adjustedSignature = null;
        
        // if it is a single array nesting, then it will just be the element type,
        // but if it is a multi-dim array, then the scalar element will be an array
        // with one less nesting level.  It's a strange corner case to have an implicit
        // array of something as a row type, but it is a valid case.  I suppose
        // it may be happen if you want to have tables of tables in which the nested tables
        // in turn use nested variables as their type...
        if (arrayCount > 0)
        {
            adjustedSignature = Signature.createArraySignature(arrayElementType, arrayCount-1);
        }
        else
        {
            adjustedSignature = arrayElementType;
        }
        
        return createScalarSymbol(symbolName, adjustedSignature, javaProject);
    }

    /**
     * Implements default rules for taking an non-array value expression
     * and resolving it to a ISymbol modelling a JSF DataModel
     * 
     * @param symbolName
     * @param valueType
     * @param javaProject
     * @return a symbol where valueType is considered to be the base type upon
     * which a data model would be created.  This combines the possibility that the
     * value expression is either an explicit DataModel implementation or a
     * non-DataModel type for which JSF be default provides an implicit wrapper model.
     * 
     * List is treated as a special case here, since their are two subcases:
     *    1) the list is a raw type, in which case it must be treated as implicit and opaque (as to the type of the variable created)
     *    2) the list has Java 5 type argument information that can be used to infer the type of the row variable
     */
    public ISymbol createFromType(String symbolName, ValueType valueType,
            IJavaProject javaProject)
    {
        return internalCreateFromBaseType(symbolName, valueType, javaProject);
    }
    
    private ISymbol internalCreateFromBaseType(String symbolName, ValueType valueType,
            IJavaProject javaProject) 
    {
        // based on JSF 1.1 spec section 4.2.1.4 the data model
        // value binding can be one of a number of object that will
        // get an implicit DataModel wrapper at runtime

        // could be an array
        if (Signature.getArrayCount(valueType.getSignature())>0)
        {
            return createArraySymbol(symbolName, valueType.getSignature(), javaProject);
        }

        // if is a list, then we have extra work to do if it
        // is generic and has info about its contents
        if (valueType.isInstanceOf(TypeConstants.TYPE_LIST))
        {
            return createFromList(symbolName, valueType, javaProject);
        }
        // if is JSTL ResultSet, java ResultSet or DataModel
        // return the default symbol -- in the absence of definite
        // template info, these row containers are opaque to us
        else if (valueType.isInstanceOf(TypeConstants.TYPE_JAVAX_SERVLET_JSP_JSTL_SQL_RESULT)
                || valueType.isInstanceOf(TypeConstants.TYPE_RESULT_SET)
                || valueType.isInstanceOf(TypeConstants.TYPE_DATA_MODEL))
        {
            return createDefaultSymbol(symbolName);
        }
            
        // in other cases, we assume that the value is an explicit single row
        // scalar object
        return createScalarSymbol(symbolName, valueType.getSignature(), javaProject);
    }

    /**
     * @param symbolName The name of the symbol to create. Must not be null.
     * @param valueType The value expression representing the implicit list.  The signature
     * on the valueType must be a list. Must not be null.
     * @param javaProject The JavaProject whose classpath will be used to resolve types.  Must not be null.
     * 
     * @return a symbol that approximates as best as possible an implicit DataModel for java.util.List value expressions.  If the List has
     * resolvable Java 5 type arguments, then a scalar symbol will be created
     * using this type information.  If it is a raw type, then 
     * createDefaultSymbol() is called
     */
    protected final ISymbol createFromList(String symbolName, ValueType valueType, IJavaProject javaProject) 
    {
        assert symbolName != null;
        assert valueType != null;
        assert javaProject != null;
        assert TypeConstants.TYPE_LIST.equals(valueType.getSignature());
        
        final String[] typeArguments = valueType.getTypeArguments();
        
        if (typeArguments != null && typeArguments.length > 0)
        {
            // a list has a single type argument
            final String typeArg = typeArguments[0];

            if (Signature.getTypeSignatureKind(typeArg) == Signature.CLASS_TYPE_SIGNATURE)
            {
                return createScalarSymbol(symbolName, typeArg, javaProject);
            }
        }
        
        // if no resolvable type signatures, do the default thing
        return createDefaultSymbol(symbolName);
    }


    /**
     * @param symbolName The name of the symbol to create. Must not be null.
     * @param signature The fully resolved type signature of the scalar.  Must not be null.
     * @param javaProject The JavaProject whose classpath is to be used to resolve type information for signture. Must not be null.
     * @return a symbol approximating a scalar object DataModel wrapper.  The row variable for the
     * data model becomes of type signature
     */
    protected final ISymbol createScalarSymbol(final String symbolName, final String signature, final IJavaProject javaProject)
    {
        assert symbolName != null;
        assert signature != null;
        assert javaProject != null;

        final String elementType = Signature.getElementType(signature);
        
        IJavaTypeDescriptor2 desc = 
            SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        final int arrayCount = Signature.getArrayCount(signature);
        if (arrayCount > 0)
        {
            desc.setArrayCount(arrayCount);
        }
        
        IType type = TypeUtil.resolveType(javaProject, elementType);
        if (type != null)
        {
            desc.setType(type);
        }
        else
        {
            desc.setTypeSignatureDelegate(Signature.getTypeErasure(signature));
        }
        desc.getTypeParameterSignatures().addAll(Arrays.asList(Signature.getTypeArguments(signature)));

        IComponentSymbol symbol = SymbolFactory.eINSTANCE.createIComponentSymbol();
        symbol.setName(symbolName);
        symbol.setTypeDescriptor(desc);
        symbol.setRuntimeSource(ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL);
        return symbol;
    }
   
    /**
     * @param symbolName The name of the symbol to create.  Must not be null
     * @return a default symbol that eliminates bogus warnings for this dataTable's
     * row variable in cases where something better is resolvable.  Note that this is
     * not ideal, since will result in any property being accepted on the variable with
     * this name.
     */
    public final ISymbol createDefaultSymbol(final String symbolName)
    {
        assert symbolName != null;
        
        final IMapTypeDescriptor typeDesc = 
            SymbolFactory.eINSTANCE.createIBoundedMapTypeDescriptor();
        // empty map source
        typeDesc.setMapSource(new HashMap());
        final IComponentSymbol symbol = 
            SymbolFactory.eINSTANCE.createIComponentSymbol();
        symbol.setName(symbolName);
        symbol.setTypeDescriptor(typeDesc);
        symbol.setDetailedDescription(Messages.getString("AbstractDataModelVariableFactory.DataModel.Symbol.RowVariable.DetailedDescription")); //$NON-NLS-1$
        symbol.setRuntimeSource(ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL);
        return symbol;
    }
    
    /**
     * @param dataTableElement the DOM element that has a "value" attribute.
     * Must not be null.
     * @return the el text from the 'value attribute of a dataTable element
     * or null if not found
     */
    protected static String getELText(final Element dataTableElement)
    {
        assert dataTableElement != null;
        String attrVal = dataTableElement.getAttribute(IJSFConstants.ATTR_VALUE);
        
        if (attrVal != null)
        {
            return AppConfigValidationUtil.extractELExpression(attrVal).getElText();
        }
        return null;
    }
    
    /**
     * @return the variable source name.  Protects against null in the abstract method
     */
    protected final String internalGetVariableSourceName()
    {
        String variableSourceName = getVariableSourceName();
        
        if (variableSourceName == null)
        {
            JSFCorePlugin.log("Missing variableSourceName", new Throwable()); //$NON-NLS-1$
            return "**missing variable source name**"; //$NON-NLS-1$
        }
        
        return variableSourceName;
    }

    /**
     * @return a user displayable name for the source of variables created by this factory
     * Must not return null.
     */
    protected abstract String getVariableSourceName();
}
