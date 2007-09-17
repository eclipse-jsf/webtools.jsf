/**
 * 
 */
package org.eclipse.jst.jsf.test.util;

import java.io.PrintStream;
import java.text.MessageFormat;

public class PerfTracker {
    private long _max = Long.MIN_VALUE; // ensure any value compared to to this
                                        // will be bigger
    private long _maxIdx = 0;
    private long _min = Long.MAX_VALUE; // ensure any value compared to this
                                        // will be smaller
    private long _minIdx = 0;
    private long _runningTotal = 0;
    private final long[] _times;
    private int _numTimesRecorded = 0;

    private final String _name;

    public PerfTracker(final String name, final int numOfRuns) {
        _times = new long[numOfRuns];
        _name = name;
    }

    public void recordTime(long time) {
        _max = Math.max(_max, time);
        _maxIdx = _max == time ? _numTimesRecorded : _maxIdx;

        _min = Math.min(_min, time);
        _minIdx = _min == time ? _numTimesRecorded : _minIdx;

        _runningTotal += time;

        _times[_numTimesRecorded++] = time;
    }

    @SuppressWarnings("boxing")
    public void printReport(PrintStream outStream) {
        outStream
                .println("===================================================");
        outStream.println("Report for performance test: " + _name);
        outStream.println("Number of iterations: " + _numTimesRecorded);
        outStream
                .println("===================================================");
        outStream.println(MessageFormat.format("Max: {0}, Max Index: {1}",
                new Object[] { _max, _maxIdx }));
        outStream.println(MessageFormat.format("Min: {0}, Min Index: {1}",
                new Object[] { _min, _minIdx }));
        outStream.println(MessageFormat.format(
                "Avg: {0}, StdDev: {1}, StdDev Ignore Max/Min: {2}",
                new Object[] { average(), calculateStdDev(false),
                        calculateStdDev(true) }));
        outStream
                .println("===================================================");
        outStream.println("");
    }

    private double average() {
        return _runningTotal / _numTimesRecorded;
    }

    private double calculateStdDev(boolean ignoreMaxMin) {
        double total = 0;
        final double avg = average();
        for (int i = 0; i < _numTimesRecorded; i++) {
            if (!ignoreMaxMin || ((i != _maxIdx) && (i != _minIdx))) {
                total += Math.pow((_times[i] - avg), 2) / _numTimesRecorded;
            }
        }
        return Math.sqrt(total);
    }
}