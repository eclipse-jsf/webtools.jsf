/*******************************************************************************
 * Copyright (c) 2001, 2017 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.core.tests.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator.ReporterAdapter;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

public class MockValidationReporter extends ReporterAdapter
{
    private final List<ReportedProblem>             _reportedProblems;
    private Map<Integer, List<ReportedProblem>>     _messagesByOffset = 
        new TreeMap<Integer, List<ReportedProblem>>();

    public MockValidationReporter()
    {
        _reportedProblems = new ArrayList<ReportedProblem>();
    }

    @Override
    public void report(Diagnostic diagnostic, int start, int length)
    {
        ReportedProblem problem = new ReportedProblem(diagnostic,start,length);
        _reportedProblems.add(problem);

     // index the message by offset
        getMessageListForOffset(start).add(problem);
    }

    @Override
    public void report(IMessage message)
    {
        final ReportedProblem problem = new ReportedProblem(message);
        _reportedProblems.add(problem);
        
        // index the message by offset
        getMessageListForOffset(message.getOffset()).add(problem);
    }
    
    public void reset()
    {
        _reportedProblems.clear();
        _messagesByOffset.clear();
    }
    
    public List<ReportedProblem>  getReportedProblems()
    {
        return Collections.unmodifiableList(_reportedProblems);
    }

    public List<ReportedProblem>  getMessageListForOffset(final int offset)
    {
        List<ReportedProblem>  messages = _messagesByOffset.get(offset);

        if (messages == null || messages.size() == 0)
        {
            for (final Map.Entry<Integer, List<ReportedProblem>> entry : _messagesByOffset.entrySet())
            {
                int entryOffset = entry.getKey().intValue();
                // if the offset looked for is within +/-5 of an entry,
                // dump it to stderr for debugging slightly off offsets
                if (offset >= entryOffset - 5 && offset <= entryOffset+5)
                {
                    System.err.printf("Offset %d requested not found but close is: %d", offset, entryOffset);
                }
            }
            messages = new ArrayList<ReportedProblem>();
            _messagesByOffset.put(offset, messages);
        }

        return messages;
    }
    
    public void assertExpectedMessage(
            final int offset, final int length, final int severity)
    {
        assertExpectedMessage(offset, length, severity, null);
    }

    public void assertExpectedMessage(
            final int offset, final int length, final int severity, final Integer code)
    {
        final List<ReportedProblem> reportedProblems = getMessageListForOffset(offset);

        Assert.assertTrue(reportedProblems.size() > 0);

        for (final ReportedProblem problem : reportedProblems)
        {
            if (problem.getLength() == length && problem.getSeverity() == severity)
            {
                if (code == null || code == Integer.valueOf(problem.getErrorCode()))
                {
                    // we found the expected message
                    return;
                }
            }
        }

        String failMessage = "";

        for (final ReportedProblem problem : reportedProblems)
        {
            failMessage += 
                String.format("\n at offset offset %d, code=%d, length=%d, message=%s", problem.getOffset(), 
                        problem.getErrorCode(), problem.getLength(), problem.getText());
        }
        Assert.fail(String.format(
                "Failed to find expected message at offset %d%s, length %d, found instead %s"
                , offset, (code == null ? "" : ", with errorCode "+code), length, failMessage));

    }

    public static class ReportedProblem
    {
        private final int _offset;
        private final int _length;
        private final int _severity;
        private final String _text;
        private final int    _errorCode;

        public ReportedProblem(final Diagnostic problem, final int start, final int length)
        {
            _severity = convertSeverity(problem);
            _offset = start;
            _length = length;
            _text = problem.getMessage();
            _errorCode = problem.getCode();
        }

        private ReportedProblem(final IMessage message)
        {
            _severity = message.getSeverity();
            _offset = message.getOffset();
            _length = message.getLength();
            _text = message.getText();
            _errorCode = -1;
        }

        /**
         * @param severity
         * @return a Message severity equivilent to diagnostic.getSeverity()
         */
        private static int convertSeverity(final Diagnostic problem)
        {
            switch (problem.getSeverity())
            {
                case Diagnostic.ERROR:
                    return IMessage.HIGH_SEVERITY;
                case Diagnostic.WARNING:
                    return IMessage.NORMAL_SEVERITY;
                case Diagnostic.INFO:
                    return IMessage.LOW_SEVERITY;
                case Diagnostic.OK:
                default:
                    // no bits set
                    return 0;
            }
        }

        public int getOffset()
        {
            return _offset;
        }

        public int getLength()
        {
            return _length;
        }

        public int getSeverity()
        {
            return _severity;
        }

        public String getText()
        {
            return _text;
        }

        public int getErrorCode()
        {
            return _errorCode;
        }
    }
}
