package org.eclipse.jst.jsf.core.tests.strategy;

import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.jst.jsf.common.internal.strategy.SimpleStrategyComposite;
import org.junit.Before;
import org.junit.Test;


public class StrategyCompositeTests
{
    private ConcatStrategy _concatStrategyAAAbbb;
    private ConcatStrategy _concatStrategyAAAccc;
    private ConcatStrategy _concatStrategyBBBccc;
    private ConcatStrategy _concatStrategyCCCaaa;
    private List<ConcatStrategy>  _strategies;
    
    @Before
    public void setup() throws Exception
    {
        _concatStrategyAAAbbb = new ConcatStrategy(Pattern.compile("^AAA.*"), "bbb", "AAA");
        _concatStrategyAAAccc = new ConcatStrategy(Pattern.compile("^AAA.*"), "ccc", "AAA");
        _concatStrategyBBBccc = new ConcatStrategy(Pattern.compile("^BBB.*"), "ccc", "BBB");
        _concatStrategyCCCaaa = new ConcatStrategy(Pattern.compile("^CCC.*"), "aaa", "CCC");
        _strategies = new ArrayList<ConcatStrategy>();
        _strategies.add(_concatStrategyAAAbbb);
        _strategies.add(_concatStrategyAAAccc);
        _strategies.add(_concatStrategyBBBccc);
        _strategies.add(_concatStrategyCCCaaa);
    }

    @Test
    public void testDefaultPerform()
    {
        SimpleStrategyComposite<String, String, String, String, ConcatStrategy> testComposite
            = new SimpleStrategyComposite<String, String, String, String, ConcatStrategy>(_strategies);
        assertEquals("AAAbbb", testComposite.perform("AAA"));
        assertEquals("AAAqqqbbb", testComposite.perform("AAAqqq"));
        assertEquals("BBBtccc", testComposite.perform("BBBt"));
        assertEquals("CCC_____aaa", testComposite.perform("CCC_____"));
    }
    
//    @Test
//    public void testListMergePerform()
//    {
//        final List<String>  composedResult = new ArrayList<String>();
//        new MergingCompositionStrategy.ListMergingCompositionStrategy<String, List<String>, ConcatStrategy>(composedResult, Collections.EMPTY_LIST);
//        SimpleStrategyComposite<String, String, String, ConcatStrategy> testComposite
//        = new SimpleStrategyComposite<String, String, String, ConcatStrategy>(_strategies, 
//                );
//    }
}
