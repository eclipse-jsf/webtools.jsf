package org.eclipse.jst.jsf.validation.internal.strategy;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.dom.DOMAdapter;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.internal.JSPUtil;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.eclipse.jst.jsf.common.sets.ConcreteAxiomaticSet;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.region.Region2ElementAdapter;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.jsf.core.set.constraint.MemberConstraint;
import org.eclipse.jst.jsf.core.set.mapping.ElementToTagIdentifierMapping;
import org.eclipse.jst.jsf.core.tagmatcher.EvaluationException;
import org.eclipse.jst.jsf.core.tagmatcher.InvalidExpressionException;
import org.eclipse.jst.jsf.core.tagmatcher.XPathMatchingAlgorithm;
import org.eclipse.jst.jsf.validation.internal.AbstractXMLViewValidationStrategy;
import org.eclipse.jst.jsf.validation.internal.JSFValidationContext;
import org.eclipse.jst.jsf.validation.internal.constraints.ContainsTagConstraint;
import org.eclipse.jst.jsf.validation.internal.constraints.TagId;
import org.eclipse.jst.jsf.validation.internal.constraints.TagSet;
import org.w3c.dom.Node;

/**
 * @author cbateman
 * 
 */
public class ContainmentValidatingStrategy extends
        AbstractXMLViewValidationStrategy
{
    /**
     * identifier
     */
    public final static String                         ID = 
        "org.eclipse.jst.jsf.validation.strategy.ElementValidatingStrategy";

    private final static ElementToTagIdentifierMapping elem2TagIdMapper = 
        new ElementToTagIdentifierMapping();
    private int                                        _containmentValidationCount;  // = 0;
    private final JSFValidationContext                 _jsfValidationContext;

    /**
     * @param jsfValidationContext
     */
    public ContainmentValidatingStrategy(
            final JSFValidationContext jsfValidationContext)
    {
        super(ID);
        _jsfValidationContext = jsfValidationContext;
    }

    @Override
    public boolean isInteresting(DOMAdapter domAdapter)
    {
        return domAdapter instanceof Region2ElementAdapter;
    }

    @Override
    public void validate(DOMAdapter domAdapter)
    {
        if (domAdapter instanceof Region2ElementAdapter)
        {
            final Region2ElementAdapter elementAdapter = 
                (Region2ElementAdapter) domAdapter;
            validateContainment(elementAdapter, _jsfValidationContext);
        }
    }

    private void validateContainment(
            final Region2ElementAdapter elementAdapter,
            final JSFValidationContext jsfValidationContext)
    {
        // don't validate JSP fragments since the necessary containment may
        // existing
        // in the JSP files that include them
        // also only validate the first instance of containment violation in a
        // file
        if (JSPUtil.isJSPFragment(jsfValidationContext.getFile())
                || _containmentValidationCount > 0)
        {
            return;
        }

        final IStructuredDocumentContext context = elementAdapter
                .getDocumentContext();
        final IDOMContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                .getDOMContextResolver(context);
        final Node node = resolver.getNode();

        final String uri = elementAdapter.getNamespace();
        final String tagName = elementAdapter.getLocalName();
        // final Element node = elementAdapter.

        final ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper
                .createMetaDataModelContext(jsfValidationContext.getFile()
                        .getProject(), uri);
        final Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(
                modelContext, tagName);
        if (entity != null)
        {
            final Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(
                    entity, "containment-constraint");

            if (trait != null)
            {
                final ContainsTagConstraint tagConstraint = (ContainsTagConstraint) trait
                        .getValue();

                final String algorithm = tagConstraint.getSetGenerator()
                        .getAlgorithm();

                // TODO: need generalized factory mechanism for registering and
                // constructing algorithms.
                if (!"xpath".equals(algorithm))
                {
                    return;
                }

                final String expr = tagConstraint.getSetGenerator()
                        .getExpression();

                // TODO: optimize on the expression and cache for reuse
                final XPathMatchingAlgorithm xpathAlg = new XPathMatchingAlgorithm(
                        expr);

                AxiomaticSet set = null;

                try
                {
                    set = xpathAlg.evaluate(node);
                    // map dom nodes to tag identifiers
                    set = elem2TagIdMapper.map(set);
                }
                catch (final InvalidExpressionException e)
                {
                    JSFCorePlugin.log(e, "Problem with expression: " + expr
                            + " on node " + node);
                    return;
                }
                catch (final EvaluationException e)
                {
                    JSFCorePlugin.log(e, "Problem evaluating expression: "
                            + expr + " on node " + node);
                    return;
                }

                final TagSet constraintData = tagConstraint.getSatisfiesSet();
                final AxiomaticSet constraintSet = new ConcreteAxiomaticSet();
                for (final Iterator it = constraintData.getTags().iterator(); it
                        .hasNext();)
                {
                    final TagId tagId = (TagId) it.next();
                    constraintSet.add(TagIdentifierFactory.createJSPTagWrapper(
                            tagId.getUri(), tagId.getName()));
                }
                final MemberConstraint memberConstraint = new MemberConstraint(
                        constraintSet);
                final Diagnostic diag = memberConstraint.isSatisfied(set);

                if (diag.getSeverity() != Diagnostic.OK)
                {
                    _containmentValidationCount++; // found a violation

                    final List data = diag.getData();

                    for (final Iterator it = data.iterator(); it.hasNext();)
                    {
                        final TagIdentifier missingParent = (TagIdentifier) it
                                .next();

                        reportContainmentProblem(context, node.getNodeName(),
                                Diagnostic.WARNING, node.getNodeName(),
                                missingParent.getTagName(), missingParent
                                        .getUri());
                    }
                }
            }
        }
    }

    private static final String MESSAGE_PATTERN = 
        "Tag {0} is missing required parent tag \"{1}\" ({2})";

    // TODO: need a diagnostic factory
    private void reportContainmentProblem(
            final IStructuredDocumentContext context,
            final String attributeValue, final int severity, 
            final String nodeName, final String tagName, final String uri)
    {

        final String msg = MessageFormat.format(
                MESSAGE_PATTERN, new Object[]
                                           {nodeName, tagName, uri});
            
        final Diagnostic problem = 
            new BasicDiagnostic(severity, "", -1, msg, null);
        final int start = context.getDocumentPosition();
        final int length = attributeValue.length();

       _jsfValidationContext.getReporter().report(problem, start, length);
    }

}
