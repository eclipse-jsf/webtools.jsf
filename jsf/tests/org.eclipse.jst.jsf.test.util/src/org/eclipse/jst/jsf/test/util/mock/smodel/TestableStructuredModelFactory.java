package org.eclipse.jst.jsf.test.util.mock.smodel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.IllegalCharsetNameException;

import org.eclipse.core.internal.runtime.PlatformActivator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jst.jsf.test.util.mock.osgi.BundleHacker;
import org.eclipse.wst.sse.core.internal.document.IDocumentLoader;
import org.eclipse.wst.sse.core.internal.encoding.CodedIO;
import org.eclipse.wst.sse.core.internal.encoding.CodedReaderCreator;
import org.eclipse.wst.sse.core.internal.encoding.EncodingMemento;
import org.eclipse.wst.sse.core.internal.encoding.util.Logger;
import org.eclipse.wst.sse.core.internal.encoding.util.NullInputStream;
import org.eclipse.wst.sse.core.internal.provisional.IModelLoader;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.document.IEncodedDocument;
import org.eclipse.wst.sse.core.internal.provisional.exceptions.ResourceInUse;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredPartitioning;
import org.eclipse.wst.sse.core.internal.util.ProjectResolver;
import org.eclipse.wst.sse.core.internal.util.URIResolver;
import org.eclipse.wst.xml.core.internal.encoding.XMLDocumentLoader;
import org.eclipse.wst.xml.core.internal.modelhandler.ModelHandlerForXML;
import org.eclipse.wst.xml.core.internal.modelhandler.XMLModelLoader;
import org.osgi.framework.BundleException;

@SuppressWarnings("deprecation")
public class TestableStructuredModelFactory
{
    public static void hackStatics() throws SecurityException,
            NoSuchFieldException, BundleException, IllegalArgumentException,
            IllegalAccessException
    {
        new BundleHacker().mockBundleContext(PlatformActivator.class);
    }

    public IStructuredModel createUnManagedModelFor(final IFile file)
            throws CoreException, ResourceInUse, IOException
    {
        IStructuredModel model = createUnManagedEmptyModelFor(file);
        final IDocumentLoader loader = model.getModelHandler()
                .getDocumentLoader();
        final IEncodedDocument document = loader
                .createNewStructuredDocument(file);

        model.getStructuredDocument().setText(this, document.get());
        return model;
    }

    /**
     * @param iFile
     * @param result
     * @return
     * @throws CoreException
     * @throws ResourceInUse
     */
    protected IStructuredModel createUnManagedEmptyModelFor(final IFile iFile)
            throws CoreException, ResourceInUse
    {
        final ModelHandlerForXML handler = new ModelHandlerForXML()
        {

            public IDocumentLoader getDocumentLoader()
            {
                return new TestableXMLModelLoader();
            }
        };
        final String id = iFile.getFullPath().toString();
        final URIResolver resolver = calculateURIResolver(iFile);

        return _commonCreateModel(id, handler, resolver);
    }

    /**
     *
     */
    private URIResolver calculateURIResolver(final IFile file)
    {
        // Note: see comment in plugin.xml for potentially
        // breaking change in behavior.

        final IProject project = file.getProject();
        final URIResolver resolver = new ProjectResolver(project);
        Object location = file.getLocation();
        if (location == null)
        {
            location = file.getLocationURI();
        }
        if (location != null)
        {
            resolver.setFileBaseLocation(location.toString());
        }
        return resolver;
    }

    private IStructuredModel _commonCreateModel(final String id,
            final ModelHandlerForXML handler, final URIResolver resolver)
            throws ResourceInUse
    {
        final IModelLoader loader = new XMLModelLoader()
        {

            public IDocumentLoader getDocumentLoader()
            {
                return new TestableXMLModelLoader();
            }
        };

        // handler.getModelLoader();
        final IStructuredModel result = loader.createModel();
        // in the past, id was null for "unmanaged" case, so we won't
        // try and set it
        if (id != null)
        {
            // result.setId(id);
        }
        result.setModelHandler(handler);
        result.setResolver(resolver);
        // some obvious redunancy here that maybe could be improved
        // in future, but is necessary for now
        result.setBaseLocation(id);
        if (resolver != null)
        {
            resolver.setFileBaseLocation(id);
        }
        // addFactories(result, handler);
        return result;
    }

    public static final String NO_SPEC_DEFAULT = "NoSpecDefault"; //$NON-NLS-1$

    /**
     * Note: once this instance is created, trace info still needs to be
     * appended by caller, depending on the context its created.
     */
    public static EncodingMemento createEncodingMemento(
            final String detectedCharsetName, final String reason,
            final String specDefaultEncoding)
    {
        EncodingMemento result = new EncodingMemento();
        result = new EncodingMemento();
        final String javaCharset = "UTF-8";
        result.setJavaCharsetName(javaCharset);
        result.setDetectedCharsetName(detectedCharsetName);
        // TODO: if detectedCharset and spec default is
        // null, need to use "work
        // bench based" defaults.
        if (specDefaultEncoding == null)
        {
            result.setAppropriateDefault(NO_SPEC_DEFAULT);
        }
        else
        {
            result.setAppropriateDefault(specDefaultEncoding);
        }
        // check if valid
        try
        {
            Charset.isSupported(javaCharset);
        }
        catch (final IllegalCharsetNameException e)
        {
            result.setInvalidEncoding(javaCharset);
        }

        return result;
    }

    private static final class TestableXMLModelLoader extends XMLDocumentLoader
    {

        public IEncodedDocument createNewStructuredDocument()
        {
            final IEncodedDocument structuredDocument = newEncodedDocument();
            // Make sure every structuredDocument has an Encoding Memento,
            // which is the default one for "empty" structuredDocuments
            final String charset = "UTF-8";
            final String specDefaultCharset = getDocumentEncodingDetector()
                    .getSpecDefaultEncoding();
            structuredDocument.setEncodingMemento(createEncodingMemento(
                    charset, EncodingMemento.DEFAULTS_ASSUMED_FOR_EMPTY_INPUT,
                    specDefaultCharset));

            // String lineDelimiter = null;
            // if (lineDelimiter != null)
            // structuredDocument.setPreferredLineDelimiter(lineDelimiter);

            final IDocumentPartitioner defaultPartitioner = getDefaultDocumentPartitioner();
            if (structuredDocument instanceof IDocumentExtension3)
            {
                ((IDocumentExtension3) structuredDocument)
                        .setDocumentPartitioner(
                                IStructuredPartitioning.DEFAULT_STRUCTURED_PARTITIONING,
                                defaultPartitioner);
            }
            else
            {
                structuredDocument.setDocumentPartitioner(defaultPartitioner);
            }
            defaultPartitioner.connect(structuredDocument);

            return structuredDocument;
        }

        /**
         * This abstract version should handle most cases, but won't if
         * contentType is sensitive to encoding, and/or embedded types
         */

        public IEncodedDocument createNewStructuredDocument(final IFile iFile)
                throws IOException, CoreException
        {
            final IEncodedDocument structuredDocument = createNewStructuredDocument();

            // String lineDelimiter = getPreferredNewLineDelimiter(iFile);
            // if (lineDelimiter != null)
            // structuredDocument.setPreferredLineDelimiter(lineDelimiter);

            try
            {
                // final CodedReaderCreator creator = getCodedReaderCreator();
                // creator.set(iFile);
                // fEncodingMemento = new
                // EncodingMemento();//creator.getEncodingMemento();
                // structuredDocument.setEncodingMemento(fEncodingMemento);
                // fFullPreparedReader =
                // getCodedReaderCreator().getCodedReader();
                InputStream streamToReturn = getResettableStream(iFile);
                streamToReturn.reset();
                Charset charset = Charset.forName("UTF-8");
                CharsetDecoder charsetDecoder = charset.newDecoder();

                fFullPreparedReader = new BufferedReader(new InputStreamReader(
                        streamToReturn, charsetDecoder), CodedIO.MAX_BUF_SIZE);
                fFullPreparedReader.mark(CodedIO.MAX_BUF_SIZE);

                setDocumentContentsFromReader(structuredDocument,
                        fFullPreparedReader);
            }
            finally
            {
                if (fFullPreparedReader != null)
                {
                    fFullPreparedReader.close();
                }
            }
            return structuredDocument;
        }

        private InputStream getResettableStream(IFile fIFile)
                throws CoreException, IOException
        {

            InputStream resettableStream = null;

            if (fIFile != null)
            {
                InputStream inputStream = null;
                try
                {
                    // note we always get contents, even if out of synch
                    inputStream = fIFile.getContents(true);
                }
                catch (CoreException e)
                {
                    // SHOULD actually check for existence of
                    // fIStorage, but
                    // for now will just assume core exception
                    // means it
                    // doesn't exist on file system, yet.
                    // and we'll log, just in case its a noteable error
                    Logger.logException(e);
                    inputStream = new NullInputStream();
                }
                resettableStream = new BufferedInputStream(inputStream,
                        CodedIO.MAX_BUF_SIZE);
            }
            // else
            // {
            // if (fInputStream != null)
            // {
            // if (fInputStream.markSupported())
            // {
            // resettableStream = fInputStream;
            // // try {
            // resettableStream.reset();
            // // }
            // // catch (IOException e) {
            // // // assumed just hasn't been marked yet, so ignore
            // // }
            // }
            // else
            // {
            // resettableStream = new BufferedInputStream(
            // fInputStream, CodedIO.MAX_BUF_SIZE);
            // }
            // }
            // }

            if (resettableStream == null)
            {
                resettableStream = new NullInputStream();
            }

            // mark this once, stream at "zero" position
            resettableStream.mark(CodedReaderCreator.MAX_MARK_SIZE);
            return resettableStream;
        }

    }

}
