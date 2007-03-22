package org.eclipse.jst.jsf.designtime.internal.jsp;

import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.provisional.util.TypeUtil;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.provisional.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.internal.provisional.ISymbol;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.validation.internal.appconfig.AppConfigValidationUtil;
import org.eclipse.jst.jsf.validation.internal.el.ELExpressionValidator;
import org.eclipse.jst.jsf.validation.internal.el.IExpressionSemanticValidator;
import org.w3c.dom.Element;

/**
 * Constructs an EL IObjectSymbol for a particular data table row access variable
 * based on the type of the bound data (the value attribute)
 *  
 * 
 * @author cbateman
 *
 */
class DataModelVariableFactory 
{
    private static final String TYPE_DATA_MODEL = "Ljavax.faces.model.DataModel;";
    private static final String TYPE_RESULT_SET = "Ljava.sql.ResultSet;";
    private static final String TYPE_JAVAX_SERVLET_JSP_JSTL_SQL_RESULT = "Ljavax.servlet.jsp.jstl.sql.Result;";
    private static DataModelVariableFactory INSTANCE;
    
    /**
     * @return an instance of the factory
     */
    public synchronized static DataModelVariableFactory getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new DataModelVariableFactory();
        }
        
        return INSTANCE;
    }
    
    /**
     * @param symbolName
     * @param dataTableElement
     * @param context 
     * @return a symbol named symbolName based on the variable declared
     * by dataTableElement
     */
    public ISymbol createSymbol(final String symbolName, 
                                final Element  dataTableElement, 
                                final IStructuredDocumentContext context)
    {
        if (!IJSFConstants.TAG_DATATABLE.equals(dataTableElement.getLocalName()))
        {
            // TODO FUTURE: this may need to be relaxed if we make this reusable
            // for things like tomahawk dataTable
            throw new AssertionError("dataTableElement must be a dataTable");
        }
        
        final String elText = getELText(dataTableElement);
        final IFile file = getFile(context);
        
        if (elText != null && file != null)
        {
            final IStructuredDocumentContext elContext =
                IStructuredDocumentContextFactory.INSTANCE.getContext(context.getStructuredDocument(), dataTableElement.getAttributeNode("value"));
            final ELExpressionValidator validator = 
                new ELExpressionValidator(elContext, elText, file);
            validator.validateXMLNode();
            final IExpressionSemanticValidator semValidator = 
                validator.getSemanticValidator();
            
            final IJavaProject javaProject = JavaCore.create(file.getProject());
            
            if (semValidator != null
                    && semValidator.getExpressionType() instanceof ValueType)
                
            {
                final ValueType valueType = (ValueType) semValidator.getExpressionType();
                final String signature = valueType.getSignature();
                
                // based on JSF 1.1 spec section 4.2.1.4 the data model
                // value binding can be one of a number of object that will
                // get an implicit DataModel wrapper at runtime
                
                // could be an array
                // TODO: somewhere should validate arrayCount > 1
                if (Signature.getArrayCount(signature)==1)
                {
                    return createArraySymbol(symbolName, signature, javaProject);
                }

                // otherwise, we have to try to resolve the base type and see
                // if it's an instanceof any of the supported implicit or explict types
                return createFromBaseType(symbolName, valueType, javaProject);
            }
        }
        
        // by default create a default
        return createDefaultSymbol(symbolName);
    }
    
    private ISymbol createFromBaseType(String symbolName, ValueType valueType,
            IJavaProject javaProject) {
        // if is a list, JSTL ResultSet, java ResultSet or DataModel
        // return the default symbol -- in the absence of definite
        // template info, these row containers are opaque to us
        if (valueType.isInstanceOf(TypeConstants.TYPE_LIST)
                || valueType.isInstanceOf(TYPE_JAVAX_SERVLET_JSP_JSTL_SQL_RESULT)
                || valueType.isInstanceOf(TYPE_RESULT_SET)
                || valueType.isInstanceOf(TYPE_DATA_MODEL))
        {
            return createDefaultSymbol(symbolName);
        }
            
        // in other cases, we assume that the value is an explicit single row
        // scalar object
        return createScalarSymbol(symbolName, valueType.getSignature(), javaProject);
    }

    private ISymbol createArraySymbol(final String symbolName, final String signature, final IJavaProject javaProject)
    {
        final String arrayElementType = Signature.getElementType(signature);
        
        return createScalarSymbol(symbolName, arrayElementType, javaProject);
    }

    private ISymbol createScalarSymbol(final String symbolName, final String signature, final IJavaProject javaProject)
    {
        IJavaTypeDescriptor2 desc = 
            SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        IType type = findType(javaProject, signature);
        desc.setTypeSignatureDelegate(signature);
        if (type != null)
        {
            desc.setType(type);
        }

        IBeanInstanceSymbol symbol = SymbolFactory.eINSTANCE.createIBeanInstanceSymbol();
        symbol.setName(symbolName);
        symbol.setTypeDescriptor(desc);
        symbol.setRuntimeSource(ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL);
        return symbol;
    }
   
    private IType findType(IJavaProject javaProject, String signature)
    {   
        try {
            return javaProject.findType(TypeUtil.getFullyQualifiedName(signature));
        } catch (JavaModelException e) {
            return null;
        }
    }
    
    /**
     * @param symbolName
     * @return a default symbol that eliminates bogus warnings for this dataTable's
     * row variable in cases where something better is resolvable.  Note that this is
     * not ideal, since will result in any property being accepted on the variable with
     * this name.
     */
    private ISymbol createDefaultSymbol(final String symbolName)
    {
        final IMapTypeDescriptor typeDesc = 
            SymbolFactory.eINSTANCE.createIBoundedMapTypeDescriptor();
        // empty map source
        typeDesc.setMapSource(new HashMap());
        final IComponentSymbol symbol = 
            SymbolFactory.eINSTANCE.createIComponentSymbol();
        symbol.setName(symbolName);
        symbol.setTypeDescriptor(typeDesc);
        symbol.setDetailedDescription("Row variable for dataTable");
        return symbol;
    }
    
    private IFile getFile(IStructuredDocumentContext context)
    {
        final IWorkspaceContextResolver wsResolver = 
            IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(context);

        IResource res = wsResolver.getResource();
        
        if (res instanceof IFile)
        {
            final IFile file = (IFile) res;
            return file;
        }
        return null;
    }
    
    private String getELText(final Element dataTableElement)
    {
        String attrVal = dataTableElement.getAttribute(IJSFConstants.ATTR_VALUE);
        
        if (attrVal != null)
        {
            return AppConfigValidationUtil.extractELExpression(attrVal).getElText();
        }
        return null;
    }
        
    
    //private String getELText(IStructuredDocumentConte)
    private DataModelVariableFactory()
    {
        // no external instantiation
    }
}
