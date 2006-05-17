package org.eclipse.jst.jsf.core.internal.types;

/**
 * Defines a signature-based type.  Signatures must conform to the JVM
 * type signature format as defined in the JVM specs and in the JDT Signature 
 * class
 * 
 * @author cbateman
 *
 */
public interface SignatureBasedType 
{
    /**
     * @return the signature string
     */
    public String getSignature();
}
