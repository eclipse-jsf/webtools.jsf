/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *
 ********************************************************************************/

package org.eclipse.jst.jsf.contentassist.tests;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistParser;
import org.eclipse.jst.jsf.core.internal.contentassist.el.ContentAssistStrategy;

/**
 * @author cbateman
 *
 */
public class CompletionPrefixTest extends TestCase
{
    /* empty expresion */
    private final static String empty_ = " ";

    /* simple value expressions */
    private final static String xxx = "xxx";
    private final static String xxxDot = "xxx.";
    private final static String xxxYYY = "xxx.yyy";
    private final static String xxxYYYZZZ = "xxx.yyy.zzz";

    /* value expressions with comparisons */
    private final static String xxxLtYYY = "xxx < yyy";
    private final static String xxxDotYYYLtZZZ = "xxx.yyy < zzz";

    /* expressions using the ['yyy'] map syntax */
    private final static String xxxMapYYY = "xxx['yyy']";
    private final static String xxxMapXXXExpr = "xxx[XXX]";
    private final static String xxxMapXXXDotExpr = "xxx[XXX.]";
    private final static String xxxMapXXXDotYYYExpr = "xxx[XXX.YYY]";
    private final static String xxxMapXXXDotYYYDotExpr = "xxx[XXX.YYY.]";
    private final static String xxxMapXXXDotYYYDotZZZExpr = "xxx[XXX.YYY.ZZZ]";



    /**
     * Test passing null to the parser
     */
    public void testPrefix_null()
    {
        /* Test passing a null expression */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(1, null);
            assertNull(prefix);
        }
    }

    /**
     *
     */
    public void testPrefix_empty_()
    {
        /* Test   */
        /*      ^        */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(1, empty_);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "".equals(prefix.getValue()));
        }
    }

    /**
     *
     */
    public void testPrefix_xxx()
    {
        /* Test  x x x */
        /*      ^        */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(1, xxx);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x */
        /*        ^                    */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(2, xxx);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x */
        /*            ^                */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(4, xxx);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }
    }

    /**
     *
     */
    public void testPrefix_xxxDot()
    {
        /* Test  x x x . */
        /*      ^        */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(1, xxxDot);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . */
        /*        ^                    */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(2, xxxDot);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x .  */
        /*            ^                */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(4, xxxDot);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }
    }

    /**
     *
     */
    public void testSuffix_xxxDot()
    {
        /* Test  x x x . */
        /*              ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(5, xxxDot);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }
    }

    /**
     *
     */
    public void testPrefix_xxxYYY()
    {
        /* Test  x x x . y y y . z z z */
        /*      ^                      */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(1, xxxYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y . z z z */
        /*        ^                    */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(2, xxxYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y . z z z */
        /*            ^                */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(4, xxxYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }
    }

    /**
     *
     */
    public void testFirstSuffix_xxxYYY()
    {
        /* Test  x x x . y y y . z z z */
        /*              ^              */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(5, xxxYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y . z z z */
        /*                ^            */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(6, xxxYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y . z z z */
        /*                    ^        */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(8, xxxYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }
    }

    /**
     * Test code completion on an EL prefix expression such as prefix.suffix
     */
    public void testPrefix_xxxYYYZZZ()
    {
        /* Test  x x x . y y y . z z z */
        /*      ^                      */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(1, xxxYYYZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y . z z z */
        /*        ^                    */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(2, xxxYYYZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y . z z z */
        /*            ^                */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(4, xxxYYYZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }
    }


    /**
     *
     */
    public void testFirstSuffix_xxxYYYZZZ()
    {
        /* Test  x x x . y y y . z z z */
        /*              ^              */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(5, xxxYYYZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y . z z z */
        /*                ^            */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(6, xxxYYYZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y . z z z */
        /*                    ^        */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(8, xxxYYYZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }
    }


    /**
     *
     */
    public void testSecondSuffix_xxxYYYZZZ()
    {
        /* Test  x x x . y y y . z z z */
        /*                      ^      */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(9, xxxYYYZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx.yyy".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y . z z z */
        /*                        ^    */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(10, xxxYYYZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx.yyy".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y . z z z */
        /*                            ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(12, xxxYYYZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx.yyy".equals(prefix.getValue()));
        }
    }

    /**
     *
     */
    public void testPrefix_xxxLtYYY()
    {
        /* Test  x x x  < y y y */
        /*      ^               */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(1, xxxLtYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x  < y y y */
        /*        ^             */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(2, xxxLtYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x  < y y y */
        /*            ^         */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(4, xxxLtYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x  < y y y */
        /*               ^      */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(7, xxxLtYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                            && "yyy".equals(prefix.getValue()));
        }

        /* Test  x x x  < y y y */
        /*                 ^    */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(8, xxxLtYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                            && "yyy".equals(prefix.getValue()));
        }

        /* Test  x x x  < y y y */
        /*                 ^    */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(10, xxxLtYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                            && "yyy".equals(prefix.getValue()));
        }
    }

    /**
     *
     */
    public void testPrefix_xxxDotYYYLtZZZ()
    {
        /* Test  x x x . y y y < z z z */
        /*      ^                      */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(1, xxxDotYYYLtZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y < z z z */
        /*        ^                    */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(2, xxxDotYYYLtZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y < z z z */
        /*            ^                */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(4, xxxDotYYYLtZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y < z z z */
        /*              ^              */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(5, xxxDotYYYLtZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y < z z z */
        /*                ^            */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(6, xxxDotYYYLtZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y < z z z */
        /*                    ^        */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(8, xxxDotYYYLtZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y < z z z */
        /*                      ^      */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(11, xxxDotYYYLtZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                            && "zzz".equals(prefix.getValue()));
        }

        /* Test  x x x . y y y < z z z */
        /*                            ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(14, xxxDotYYYLtZZZ);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                            && "zzz".equals(prefix.getValue()));
        }
    }

    /**
     *
     */
    public void testSuffix_xxxDotYYYLtZZZ()
    {
        /* Test  x x x . y y y < z z z . */
        /*                              ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(15, xxxDotYYYLtZZZ+".");

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                            && "zzz".equals(prefix.getValue()));
        }
    }

    /**
     *
     */
    public void testPrefix_xxxMapYYY()
    {
        /* Test  x x x [ ' y y y ' ] */
        /*      ^                    */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(1, xxxMapYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x [ ' y y y ' ] */
        /*        ^                  */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(2, xxxMapYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }

        /* Test  x x x [ ' y y y ' ] */
        /*            ^              */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(4, xxxMapYYY);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "xxx".equals(prefix.getValue()));
        }

        /* Test x x x [ ' y y y ' ]  */
        /*                         ^ */
//        {
//            ContentAssistStrategy prefix = ContentAssistParser.getPrefix(11, xxxMapYYY);
//
//            System.out.println(prefix.getValue());
//            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
//                    && "xxx['yyy']".equals(prefix.getValue()));
//        }

        /* Test x x x [ ' y y y ' ] .  */
        /*                           ^ */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(12, xxxMapYYY+".");

            System.out.println(prefix.getValue());
            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION
                    && "xxx['yyy']".equals(prefix.getValue()));
        }
    }

    /**
     * Test completion inside an array
     */
    public void testPrefix_xxxMapXXXDotExpr()
    {
        /* Test x x x [ X X X ] */
        /*             ^ */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(5, xxxMapXXXExpr);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "XXX".equals(prefix.getValue()));
        }

        /* Test x x x [ X X X ] */
        /*               ^ */
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(6, xxxMapXXXExpr);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "XXX".equals(prefix.getValue()));
        }

        /* Test x x x [ X X X ] */
        /*                   ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(8, xxxMapXXXExpr);

            assertTrue(prefix.getType() == ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION
                    && "XXX".equals(prefix.getValue()));
        }
    }

    /**
     * Tests suffix completions on dots inside brackets
     */
    public void testSuffix_xxxMapXXXDotExpr()
    {
        /* Test x x x [ X X X . ] */
        /*                   ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(8, xxxMapXXXDotExpr);

            assertEquals(ContentAssistStrategy.PREFIX_TYPE_ID_COMPLETION, prefix.getType());
            assertEquals("XXX", prefix.getValue());
        }

        /* Test x x x [ X X X . ] */
        /*                     ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(9, xxxMapXXXDotExpr);

            assertEquals(ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION, prefix.getType());
            assertEquals("XXX", prefix.getValue());
        }
    }

    /**
     * Test dot completion inside an array expression
     */
    public void testSuffix_xxxMapXXXDotYYYExpr()
    {
        /* Test x x x [ X X X . Y Y Y ] */
        /*                     ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(9, xxxMapXXXDotYYYExpr);

            assertEquals(ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION, prefix.getType());
            assertEquals("XXX", prefix.getValue());
        }

        /* Test x x x [ X X X . Y Y Y ] */
        /*                       ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(10, xxxMapXXXDotYYYExpr);

            assertEquals(ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION, prefix.getType());
            assertEquals("XXX", prefix.getValue());
        }

        /* Test x x x [ X X X . Y Y Y ] */
        /*                           ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(12, xxxMapXXXDotYYYExpr);

            assertEquals(ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION, prefix.getType());
            assertEquals("XXX", prefix.getValue());
        }

        /* Test x x x [ X X X . Y Y Y . ] */
        /*                           ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(12, xxxMapXXXDotYYYDotExpr);

            assertEquals(ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION, prefix.getType());
            assertEquals("XXX", prefix.getValue());
        }

        /* Test x x x [ X X X . Y Y Y . ] */
        /*                             ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(13, xxxMapXXXDotYYYDotExpr);

            assertEquals(ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION, prefix.getType());
            assertEquals("XXX.YYY", prefix.getValue());
        }
    }

    /**
     * Test dot completion on XXX.YYY.ZZZ type expressions
     */
    public void testSuffix_xxxMapXXXDotYYYDotZZZExpr()
    {
        /* Test x x x [ X X X . Y Y Y . Z Z Z] */
        /*                             ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(13, xxxMapXXXDotYYYDotZZZExpr);

            assertEquals(ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION, prefix.getType());
            assertEquals("XXX.YYY", prefix.getValue());
        }

        /* Test x x x [ X X X . Y Y Y . Z Z Z] */
        /*                               ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(14, xxxMapXXXDotYYYDotZZZExpr);

            assertEquals(ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION, prefix.getType());
            assertEquals("XXX.YYY", prefix.getValue());
        }

        /* Test x x x [ X X X . Y Y Y . Z Z Z ] */
        /*                                   ^*/
        {
            final ContentAssistStrategy prefix = ContentAssistParser.getPrefix(16, xxxMapXXXDotYYYDotZZZExpr);

            assertEquals(ContentAssistStrategy.PREFIX_TYPE_DOT_COMPLETION, prefix.getType());
            assertEquals("XXX.YYY", prefix.getValue());
        }

    }
}
