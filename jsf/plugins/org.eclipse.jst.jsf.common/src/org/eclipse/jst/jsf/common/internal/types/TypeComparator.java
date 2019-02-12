/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.internal.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jdt.core.Signature;

/**
 * Static utility class used to compare two CompositeTypes for compatability
 * 
 * @author cbateman
 * 
 */
public final class TypeComparator {
    private static class SignatureTestResult {
        /**
         * the diagnostic
         */
        private final Diagnostic diagnostic;
        /**
         * Measure of the probability that the tested signatures were meant to
         * match. Larger value means higher probability.
         */
        private final int matchQuality;

        /**
         * @param diagnostic
         * @param matchQuality -
         *            Measure of the probability that the tested signatures were
         *            meant to match. Larger value means higher probability.
         */
        public SignatureTestResult(final Diagnostic diagnostic,
                final int matchQuality) {
            super();
            this.diagnostic = diagnostic;
            this.matchQuality = matchQuality;
        }
    }

    private final TypeComparatorDiagnosticFactory   _factory;

    /**
     * Default Constructor
     * @param factory 
     */
    public TypeComparator(final TypeComparatorDiagnosticFactory factory) 
    {
        _factory = factory;
    }

    /**
     * @param firstType
     * @param secondType
     * @return true if firstType is assignable to secondType or vice-versa,
     *         depending on their assignment and runtime types
     */
    public Diagnostic calculateTypeCompatibility(
            final CompositeType firstType, final CompositeType secondType) {
        // first, box all primitives
        final CompositeType boxedFirstType = TypeTransformer
                .transformBoxPrimitives(firstType);
        final CompositeType boxedSecondType = TypeTransformer
                .transformBoxPrimitives(secondType);

        final String[] mustBeSatisfied = boxedFirstType.getSignatures();
        final String[] testSignatures = boxedSecondType.getSignatures();
        List<String> mustbeMethods = Collections.emptyList();
        List<String> mustbeTypes = Collections.emptyList();
        for (final String mustbeSignature : mustBeSatisfied) {
            if (TypeUtil.isMethodSignature(mustbeSignature)) {
                if (mustbeMethods.isEmpty()) {
                    mustbeMethods = new ArrayList<String>(mustbeSignature
                            .length());
                }
                mustbeMethods.add(mustbeSignature);
            } else {
                if (mustbeTypes.isEmpty()) {
                    mustbeTypes = new ArrayList<String>(mustbeSignature
                            .length());
                }
                mustbeTypes.add(mustbeSignature);
            }
        }
        final boolean mustbeWriteable = firstType.isLHS();
        SignatureTestResult bestResult = null;
        for (final String isSignature : testSignatures) {
            SignatureTestResult testResult;
            if (TypeUtil.isMethodSignature(isSignature)) {
                testResult = checkMethodSignature(isSignature, mustbeTypes,
                        mustbeMethods);
                if (testResult.diagnostic.getSeverity() == Diagnostic.OK) {
                    return testResult.diagnostic;
                }
            } else {
                testResult = checkTypeSignature(isSignature, mustbeTypes,
                        mustbeMethods, mustbeWriteable);
                if (testResult.diagnostic.getSeverity() == Diagnostic.OK) {
                    return checkAssignability(firstType, secondType);
                }
            }
            if (bestResult == null
                    || bestResult.matchQuality < testResult.matchQuality) {
                bestResult = testResult;
            }
        }
        // TODO: bestResult empty? (should not happen, but who knows...
        return bestResult.diagnostic;
    }

    private SignatureTestResult checkTypeSignature(
            final String isSignature, final List<String> mustbeTypes,
            final List<String> mustbeMethods, final boolean mustbeWriteable) {
        if (mustbeTypes.isEmpty()) {
            final Diagnostic diag = _factory.create_METHOD_EXPRESSION_EXPECTED();
            return new SignatureTestResult(diag, 0);
        }
        for (final String mustbeSignature : mustbeTypes) {
            if (mustbeSignature.equals(isSignature)
                    || canCoerce(isSignature, mustbeSignature, mustbeWriteable)) {
                final Diagnostic diag = Diagnostic.OK_INSTANCE;
                return new SignatureTestResult(diag, 5);
            }
        }
        final String[] params = new String[2];
        params[0] = readableSignatures(mustbeTypes);
        params[1] = Signature.toString(isSignature);
        final Diagnostic diag = _factory.create_INCOMPATIBLE_TYPES(params);
        return new SignatureTestResult(diag, 1);
    }

    private SignatureTestResult checkMethodSignature(
            final String isSignature, final List<String> mustbeTypes,
            final List<String> mustbeMethods) {
        if (mustbeMethods.isEmpty()) {
            final Diagnostic diag = _factory.create_VALUE_EXPRESSION_EXPECTED();
            return new SignatureTestResult(diag, 0);
        }
        for (final String mustbeSignature : mustbeMethods) {
            if (methodSignaturesMatch(mustbeSignature, isSignature)) {
                final Diagnostic diag = Diagnostic.OK_INSTANCE;
                return new SignatureTestResult(diag, 5);
            }
        }
        final String[] params = new String[2];
        params[0] = readableSignatures(mustbeMethods);
        params[1] = Signature
                .toString(isSignature, "method", null, false, true); //$NON-NLS-1$
        final Diagnostic diag = _factory.create_INCOMPATIBLE_METHOD_TYPES(params);
        return new SignatureTestResult(diag, 1);
    }

    private static String readableSignatures(final List<String> signatures) {
        StringBuilder res = null;
        for (final String sig : signatures) {
            String sigText;
            if (TypeUtil.isMethodSignature(sig)) {
                sigText = Signature.toString(sig, "method", null, false, true); //$NON-NLS-1$
            } else {
                sigText = Signature.toString(sig);
            }
            if (res == null) {
                res = new StringBuilder(sigText);
            } else {
                res.append(", ").append(sigText); //$NON-NLS-1$
            }
        }
        return res != null ? res.toString() : "[no signature]"; //$NON-NLS-1$
    }

    private static boolean canCoerce(final String testType,
            final String checkType, final boolean checkTypeIsWritable) {
        boolean canCoerce = canCoerce(testType, checkType);

        // if the check type is writable, we need to be sure that the
        // coercion can work in both directions
        if (canCoerce && checkTypeIsWritable) {
            // reverse roles: can checkType assign back to test type?
            canCoerce &= canCoerce(checkType, testType);
        }

        return canCoerce;
    }

    private static boolean canCoerce(final String testType,
            final String checkType) {
        // can always to coerce to string or object
        if (TypeCoercer.typeIsString(checkType)/*
                || TypeConstants.TYPE_JAVAOBJECT.equals(checkType)*/) 
        {
            return true;
        } else if (TypeCoercer.typeIsNumeric(checkType)) {
            return canCoerceNumeric(testType);
        } else if (TypeCoercer.typeIsBoolean(checkType)) {
            return TypeCoercer.canCoerceToBoolean(testType);
        }

        // otherwise, no type coercion available
        return false;
    }

    private static boolean canCoerceNumeric(final String testType) {
        try {
            TypeCoercer.coerceToNumber(testType);
            // TODO: there is a case when coerceToNumber returns
            // null meaning "not sure", that we may want to handle
            // differently, with a warning
            return true;
        } catch (final TypeCoercionException tce) {
            // outright failure -- can't coerce
            return false;
        }
    }

    private static boolean methodSignaturesMatch(final String firstMethodSig,
            final String secondMethodSig) {
        // TODO: need to account for primitive type coercions
        if (firstMethodSig.equals(secondMethodSig)) {
            return true;
        }
        final String[] firstMethodParams = Signature
                .getParameterTypes(firstMethodSig);
        final String[] secondMethodParams = Signature
                .getParameterTypes(secondMethodSig);

        // fail fast if param count doesn't match
        if (firstMethodParams.length != secondMethodParams.length) {
            return false;
        }

        // now check each parameter
        for (int i = 0; i < firstMethodParams.length; i++) {
            // need to box primitives before comparing
            final String firstMethodParam = TypeTransformer
                    .transformBoxPrimitives(firstMethodParams[i]);
            final String secondMethodParam = TypeTransformer
                    .transformBoxPrimitives(secondMethodParams[i]);

            if (!firstMethodParam.equals(secondMethodParam)) {
                return false;
            }
        }

        // if we get to here then we need only check the return type
        final String firstReturn = TypeTransformer
                .transformBoxPrimitives(Signature.getReturnType(firstMethodSig));
        final String secondReturn = TypeTransformer
                .transformBoxPrimitives(Signature
                        .getReturnType(secondMethodSig));

        if (!firstReturn.equals(secondReturn)) {
            return false;
        }

        // if we get to here, then everything checks out
        return true;
    }

    /**
     * Precond: both firstType and secondType must represent value bindings.
     * 
     * @param firstType
     * @param secondType
     * @return a diagnostic validating that the two composite have compatible
     *         assignability
     */
    private Diagnostic checkAssignability(final CompositeType firstType,
            final CompositeType secondType) {
        if (firstType.isRHS() && !secondType.isRHS()) {
            return _factory.create_PROPERTY_NOT_READABLE();
        }

        if (firstType.isLHS() && !secondType.isLHS()) {
            return _factory.create_PROPERTY_NOT_WRITABLE();
        }

        return Diagnostic.OK_INSTANCE;
    }
}
