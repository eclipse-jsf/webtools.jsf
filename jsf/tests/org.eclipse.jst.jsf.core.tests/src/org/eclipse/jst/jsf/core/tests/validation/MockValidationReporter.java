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

        if (messages == null)
        {
            messages = new ArrayList<ReportedProblem>();
            _messagesByOffset.put(offset, messages);
        }

        return messages;
    }
    
    public void assertExpectedMessage(
            final int offset, final int length, final int severity)
    {
        final List<ReportedProblem> reportedProblems = getMessageListForOffset(offset);
        final List<ReportedProblem> reportedProblemsNotMatching = new ArrayList<ReportedProblem>();

        Assert.assertTrue(reportedProblems.size() > 0);

        for (final ReportedProblem problem : reportedProblems)
        {
            if (problem.getLength() == length && problem.getSeverity() == severity)
            {
                // we found the expected message
                return;
            }
            else
            {
                reportedProblemsNotMatching.add(problem);
            }
        }

        String failMessage = "";

        for (final ReportedProblem problem : reportedProblems)
        {
            failMessage += "\n" + problem.getText();
        }
        Assert.fail(String.format("Failed to find expected message at offset %d, found instead %s", offset, failMessage));
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
