/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;

/**
 * entity map support.
 * 
 * @author mengbo
 */
public class HTMLSpecialCharHelper {
	private static Logger _log = PDPlugin
			.getLogger(HTMLSpecialCharHelper.class);

	private static Hashtable _table;

	private static Hashtable _reverse;
	static {
		_table = new Hashtable(256);

		_table.put(new Integer(34), "&quot;"); //$NON-NLS-1$
		_table.put(new Integer(38), "&amp;"); //$NON-NLS-1$
		_table.put(new Integer(60), "&lt;"); //$NON-NLS-1$
		_table.put(new Integer(62), "&gt;"); //$NON-NLS-1$

		_table.put(new Integer(160), "&nbsp;"); //$NON-NLS-1$
		_table.put(new Integer(161), "&iexcl;"); //$NON-NLS-1$
		_table.put(new Integer(162), "&cent;"); //$NON-NLS-1$
		_table.put(new Integer(163), "&pound;"); //$NON-NLS-1$
		_table.put(new Integer(164), "&curren;"); //$NON-NLS-1$
		_table.put(new Integer(165), "&yen;"); //$NON-NLS-1$
		_table.put(new Integer(166), "&brvbar;"); //$NON-NLS-1$
		_table.put(new Integer(167), "&sect;"); //$NON-NLS-1$
		_table.put(new Integer(168), "&uml;"); //$NON-NLS-1$
		_table.put(new Integer(169), "&copy;"); //$NON-NLS-1$
		_table.put(new Integer(170), "&ordf;"); //$NON-NLS-1$
		_table.put(new Integer(171), "&laquo;"); //$NON-NLS-1$
		_table.put(new Integer(172), "&not;"); //$NON-NLS-1$
		_table.put(new Integer(173), "&shy;"); //$NON-NLS-1$
		_table.put(new Integer(174), "&reg;"); //$NON-NLS-1$
		_table.put(new Integer(175), "&macr;"); //$NON-NLS-1$
		_table.put(new Integer(176), "&deg;"); //$NON-NLS-1$
		_table.put(new Integer(177), "&plusmn;"); //$NON-NLS-1$
		_table.put(new Integer(178), "&sup2;"); //$NON-NLS-1$
		_table.put(new Integer(179), "&sup3;"); //$NON-NLS-1$
		_table.put(new Integer(180), "&acute;"); //$NON-NLS-1$
		_table.put(new Integer(181), "&micro;"); //$NON-NLS-1$
		_table.put(new Integer(182), "&para;"); //$NON-NLS-1$
		_table.put(new Integer(183), "&middot;"); //$NON-NLS-1$
		_table.put(new Integer(184), "&cedil;"); //$NON-NLS-1$
		_table.put(new Integer(185), "&sup1;"); //$NON-NLS-1$
		_table.put(new Integer(186), "&ordm;"); //$NON-NLS-1$
		_table.put(new Integer(187), "&raquo;"); //$NON-NLS-1$
		_table.put(new Integer(188), "&frac14;"); //$NON-NLS-1$
		_table.put(new Integer(189), "&frac12;"); //$NON-NLS-1$
		_table.put(new Integer(190), "&frac34;"); //$NON-NLS-1$
		_table.put(new Integer(191), "&iquest;"); //$NON-NLS-1$
		_table.put(new Integer(192), "&Agrave;"); //$NON-NLS-1$
		_table.put(new Integer(193), "&Aacute;"); //$NON-NLS-1$
		_table.put(new Integer(194), "&Acirc;"); //$NON-NLS-1$
		_table.put(new Integer(195), "&Atilde;"); //$NON-NLS-1$
		_table.put(new Integer(196), "&Auml;"); //$NON-NLS-1$
		_table.put(new Integer(197), "&Aring;"); //$NON-NLS-1$
		_table.put(new Integer(198), "&AElig;"); //$NON-NLS-1$
		_table.put(new Integer(199), "&Ccedil;"); //$NON-NLS-1$
		_table.put(new Integer(200), "&Egrave;"); //$NON-NLS-1$
		_table.put(new Integer(201), "&Eacute;"); //$NON-NLS-1$
		_table.put(new Integer(202), "&Ecirc;"); //$NON-NLS-1$
		_table.put(new Integer(203), "&Euml;"); //$NON-NLS-1$
		_table.put(new Integer(204), "&Igrave;"); //$NON-NLS-1$
		_table.put(new Integer(205), "&Iacute;"); //$NON-NLS-1$
		_table.put(new Integer(206), "&Icirc;"); //$NON-NLS-1$
		_table.put(new Integer(207), "&Iuml;"); //$NON-NLS-1$
		_table.put(new Integer(208), "&ETH;"); //$NON-NLS-1$
		_table.put(new Integer(209), "&Ntilde;"); //$NON-NLS-1$
		_table.put(new Integer(210), "&Ograve;"); //$NON-NLS-1$
		_table.put(new Integer(211), "&Oacute;"); //$NON-NLS-1$
		_table.put(new Integer(212), "&Ocirc;"); //$NON-NLS-1$
		_table.put(new Integer(213), "&Otilde;"); //$NON-NLS-1$
		_table.put(new Integer(214), "&Ouml;"); //$NON-NLS-1$
		_table.put(new Integer(215), "&times;"); //$NON-NLS-1$
		_table.put(new Integer(216), "&Oslash;"); //$NON-NLS-1$
		_table.put(new Integer(217), "&Ugrave;"); //$NON-NLS-1$
		_table.put(new Integer(218), "&Uacute;"); //$NON-NLS-1$
		_table.put(new Integer(219), "&Ucirc;"); //$NON-NLS-1$
		_table.put(new Integer(220), "&Uuml;"); //$NON-NLS-1$
		_table.put(new Integer(221), "&Yacute;"); //$NON-NLS-1$
		_table.put(new Integer(222), "&THORN;"); //$NON-NLS-1$
		_table.put(new Integer(223), "&szlig;"); //$NON-NLS-1$
		_table.put(new Integer(224), "&agrave;"); //$NON-NLS-1$
		_table.put(new Integer(225), "&aacute;"); //$NON-NLS-1$
		_table.put(new Integer(226), "&acirc;"); //$NON-NLS-1$
		_table.put(new Integer(227), "&atilde;"); //$NON-NLS-1$
		_table.put(new Integer(228), "&auml;"); //$NON-NLS-1$
		_table.put(new Integer(229), "&aring;"); //$NON-NLS-1$
		_table.put(new Integer(230), "&aelig;"); //$NON-NLS-1$
		_table.put(new Integer(231), "&ccedil;"); //$NON-NLS-1$
		_table.put(new Integer(232), "&egrave;"); //$NON-NLS-1$
		_table.put(new Integer(233), "&eacute;"); //$NON-NLS-1$
		_table.put(new Integer(234), "&ecirc;"); //$NON-NLS-1$
		_table.put(new Integer(235), "&euml;"); //$NON-NLS-1$
		_table.put(new Integer(236), "&igrave;"); //$NON-NLS-1$
		_table.put(new Integer(237), "&iacute;"); //$NON-NLS-1$
		_table.put(new Integer(238), "&icirc;"); //$NON-NLS-1$
		_table.put(new Integer(239), "&iuml;"); //$NON-NLS-1$
		_table.put(new Integer(240), "&eth;"); //$NON-NLS-1$
		_table.put(new Integer(241), "&ntilde;"); //$NON-NLS-1$
		_table.put(new Integer(242), "&ograve;"); //$NON-NLS-1$
		_table.put(new Integer(243), "&oacute;"); //$NON-NLS-1$
		_table.put(new Integer(244), "&ocirc;"); //$NON-NLS-1$
		_table.put(new Integer(245), "&otilde;"); //$NON-NLS-1$
		_table.put(new Integer(246), "&ouml;"); //$NON-NLS-1$
		_table.put(new Integer(247), "&divide;"); //$NON-NLS-1$
		_table.put(new Integer(248), "&oslash;"); //$NON-NLS-1$
		_table.put(new Integer(249), "&ugrave;"); //$NON-NLS-1$
		_table.put(new Integer(250), "&uacute;"); //$NON-NLS-1$
		_table.put(new Integer(251), "&ucirc;"); //$NON-NLS-1$
		_table.put(new Integer(252), "&uuml;"); //$NON-NLS-1$
		_table.put(new Integer(253), "&yacute;"); //$NON-NLS-1$
		_table.put(new Integer(254), "&thorn;"); //$NON-NLS-1$
		_table.put(new Integer(255), "&yuml;"); //$NON-NLS-1$
		_table.put(new Integer(402), "&fnof;"); //$NON-NLS-1$
		_table.put(new Integer(913), "&Alpha;"); //$NON-NLS-1$
		_table.put(new Integer(914), "&Beta;"); //$NON-NLS-1$
		_table.put(new Integer(915), "&Gamma;"); //$NON-NLS-1$
		_table.put(new Integer(916), "&Delta;"); //$NON-NLS-1$
		_table.put(new Integer(917), "&Epsilon;"); //$NON-NLS-1$
		_table.put(new Integer(918), "&Zeta;"); //$NON-NLS-1$
		_table.put(new Integer(919), "&Eta;"); //$NON-NLS-1$
		_table.put(new Integer(920), "&Theta;"); //$NON-NLS-1$
		_table.put(new Integer(921), "&Iota;"); //$NON-NLS-1$
		_table.put(new Integer(922), "&Kappa;"); //$NON-NLS-1$
		_table.put(new Integer(923), "&Lambda;"); //$NON-NLS-1$
		_table.put(new Integer(924), "&Mu;"); //$NON-NLS-1$
		_table.put(new Integer(925), "&Nu;"); //$NON-NLS-1$
		_table.put(new Integer(926), "&Xi;"); //$NON-NLS-1$
		_table.put(new Integer(927), "&Omicron;"); //$NON-NLS-1$
		_table.put(new Integer(928), "&Pi;"); //$NON-NLS-1$
		_table.put(new Integer(929), "&Rho;"); //$NON-NLS-1$
		_table.put(new Integer(931), "&Sigma;"); //$NON-NLS-1$
		_table.put(new Integer(932), "&Tau;"); //$NON-NLS-1$
		_table.put(new Integer(933), "&Upsilon;"); //$NON-NLS-1$
		_table.put(new Integer(934), "&Phi;"); //$NON-NLS-1$
		_table.put(new Integer(935), "&Chi;"); //$NON-NLS-1$
		_table.put(new Integer(936), "&Psi;"); //$NON-NLS-1$
		_table.put(new Integer(937), "&Omega;"); //$NON-NLS-1$
		_table.put(new Integer(945), "&alpha;"); //$NON-NLS-1$
		_table.put(new Integer(946), "&beta;"); //$NON-NLS-1$
		_table.put(new Integer(947), "&gamma;"); //$NON-NLS-1$
		_table.put(new Integer(948), "&delta;"); //$NON-NLS-1$
		_table.put(new Integer(949), "&epsilon;"); //$NON-NLS-1$
		_table.put(new Integer(950), "&zeta;"); //$NON-NLS-1$
		_table.put(new Integer(951), "&eta;"); //$NON-NLS-1$
		_table.put(new Integer(952), "&theta;"); //$NON-NLS-1$
		_table.put(new Integer(953), "&iota;"); //$NON-NLS-1$
		_table.put(new Integer(954), "&kappa;"); //$NON-NLS-1$
		_table.put(new Integer(955), "&lambda;"); //$NON-NLS-1$
		_table.put(new Integer(956), "&mu;"); //$NON-NLS-1$
		_table.put(new Integer(957), "&nu;"); //$NON-NLS-1$
		_table.put(new Integer(958), "&xi;"); //$NON-NLS-1$
		_table.put(new Integer(959), "&omicron;"); //$NON-NLS-1$
		_table.put(new Integer(960), "&pi;"); //$NON-NLS-1$
		_table.put(new Integer(961), "&rho;"); //$NON-NLS-1$
		_table.put(new Integer(962), "&sigmaf;"); //$NON-NLS-1$
		_table.put(new Integer(963), "&sigma;"); //$NON-NLS-1$
		_table.put(new Integer(964), "&tau;"); //$NON-NLS-1$
		_table.put(new Integer(965), "&upsilon;"); //$NON-NLS-1$
		_table.put(new Integer(966), "&phi;"); //$NON-NLS-1$
		_table.put(new Integer(967), "&chi;"); //$NON-NLS-1$
		_table.put(new Integer(968), "&psi;"); //$NON-NLS-1$
		_table.put(new Integer(969), "&omega;"); //$NON-NLS-1$
		_table.put(new Integer(977), "&thetasym;"); //$NON-NLS-1$
		_table.put(new Integer(978), "&upsih;"); //$NON-NLS-1$
		_table.put(new Integer(982), "&piv;"); //$NON-NLS-1$
		_table.put(new Integer(8226), "&bull;"); //$NON-NLS-1$
		_table.put(new Integer(8230), "&hellip;"); //$NON-NLS-1$
		_table.put(new Integer(8242), "&prime;"); //$NON-NLS-1$
		_table.put(new Integer(8243), "&Prime;"); //$NON-NLS-1$
		_table.put(new Integer(8254), "&oline;"); //$NON-NLS-1$
		_table.put(new Integer(8260), "&frasl;"); //$NON-NLS-1$
		_table.put(new Integer(8472), "&weierp;"); //$NON-NLS-1$
		_table.put(new Integer(8465), "&image;"); //$NON-NLS-1$
		_table.put(new Integer(8476), "&real;"); //$NON-NLS-1$
		_table.put(new Integer(8482), "&trade;"); //$NON-NLS-1$
		_table.put(new Integer(8501), "&alefsym;"); //$NON-NLS-1$
		_table.put(new Integer(8592), "&larr;"); //$NON-NLS-1$
		_table.put(new Integer(8593), "&uarr;"); //$NON-NLS-1$
		_table.put(new Integer(8594), "&rarr;"); //$NON-NLS-1$
		_table.put(new Integer(8595), "&darr;"); //$NON-NLS-1$
		_table.put(new Integer(8596), "&harr;"); //$NON-NLS-1$
		_table.put(new Integer(8629), "&crarr;"); //$NON-NLS-1$
		_table.put(new Integer(8656), "&lArr;"); //$NON-NLS-1$
		_table.put(new Integer(8657), "&uArr;"); //$NON-NLS-1$
		_table.put(new Integer(8658), "&rArr;"); //$NON-NLS-1$
		_table.put(new Integer(8659), "&dArr;"); //$NON-NLS-1$
		_table.put(new Integer(8660), "&hArr;"); //$NON-NLS-1$
		_table.put(new Integer(8704), "&forall;"); //$NON-NLS-1$
		_table.put(new Integer(8706), "&part;"); //$NON-NLS-1$
		_table.put(new Integer(8707), "&exist;"); //$NON-NLS-1$
		_table.put(new Integer(8709), "&empty;"); //$NON-NLS-1$
		_table.put(new Integer(8711), "&nabla;"); //$NON-NLS-1$
		_table.put(new Integer(8712), "&isin;"); //$NON-NLS-1$
		_table.put(new Integer(8713), "&notin;"); //$NON-NLS-1$
		_table.put(new Integer(8715), "&ni;"); //$NON-NLS-1$
		_table.put(new Integer(8719), "&prod;"); //$NON-NLS-1$
		_table.put(new Integer(8722), "&sum;"); //$NON-NLS-1$
		_table.put(new Integer(8722), "&minus;"); //$NON-NLS-1$
		_table.put(new Integer(8727), "&lowast;"); //$NON-NLS-1$
		_table.put(new Integer(8730), "&radic;"); //$NON-NLS-1$
		_table.put(new Integer(8733), "&prop;"); //$NON-NLS-1$
		_table.put(new Integer(8734), "&infin;"); //$NON-NLS-1$
		_table.put(new Integer(8736), "&ang;"); //$NON-NLS-1$
		_table.put(new Integer(8869), "&and;"); //$NON-NLS-1$
		_table.put(new Integer(8870), "&or;"); //$NON-NLS-1$
		_table.put(new Integer(8745), "&cap;"); //$NON-NLS-1$
		_table.put(new Integer(8746), "&cup;"); //$NON-NLS-1$
		_table.put(new Integer(8747), "&int;"); //$NON-NLS-1$
		_table.put(new Integer(8756), "&there4;"); //$NON-NLS-1$
		_table.put(new Integer(8764), "&sim;"); //$NON-NLS-1$
		_table.put(new Integer(8773), "&cong;"); //$NON-NLS-1$
		_table.put(new Integer(8773), "&asymp;"); //$NON-NLS-1$
		_table.put(new Integer(8800), "&ne;"); //$NON-NLS-1$
		_table.put(new Integer(8801), "&equiv;"); //$NON-NLS-1$
		_table.put(new Integer(8804), "&le;"); //$NON-NLS-1$
		_table.put(new Integer(8805), "&ge;"); //$NON-NLS-1$
		_table.put(new Integer(8834), "&sub;"); //$NON-NLS-1$
		_table.put(new Integer(8835), "&sup;"); //$NON-NLS-1$
		_table.put(new Integer(8836), "&nsub;"); //$NON-NLS-1$
		_table.put(new Integer(8838), "&sube;"); //$NON-NLS-1$
		_table.put(new Integer(8839), "&supe;"); //$NON-NLS-1$
		_table.put(new Integer(8853), "&oplus;"); //$NON-NLS-1$
		_table.put(new Integer(8855), "&otimes;"); //$NON-NLS-1$
		_table.put(new Integer(8869), "&perp;"); //$NON-NLS-1$
		_table.put(new Integer(8901), "&sdot;"); //$NON-NLS-1$
		_table.put(new Integer(8968), "&lceil;"); //$NON-NLS-1$
		_table.put(new Integer(8969), "&rceil;"); //$NON-NLS-1$
		_table.put(new Integer(8970), "&lfloor;"); //$NON-NLS-1$
		_table.put(new Integer(8971), "&rfloor;"); //$NON-NLS-1$
		_table.put(new Integer(9001), "&lang;"); //$NON-NLS-1$
		_table.put(new Integer(9002), "&rang;"); //$NON-NLS-1$
		_table.put(new Integer(9674), "&loz;"); //$NON-NLS-1$
		_table.put(new Integer(9824), "&spades;"); //$NON-NLS-1$
		_table.put(new Integer(9827), "&clubs;"); //$NON-NLS-1$
		_table.put(new Integer(9829), "&hearts;"); //$NON-NLS-1$
		_table.put(new Integer(9830), "&diams;"); //$NON-NLS-1$

		_table.put(new Integer(338), "&OElig;"); //$NON-NLS-1$
		_table.put(new Integer(339), "&oelig;"); //$NON-NLS-1$
		_table.put(new Integer(352), "&Scaron;"); //$NON-NLS-1$
		_table.put(new Integer(353), "&scaron;"); //$NON-NLS-1$
		_table.put(new Integer(376), "&Yuml;"); //$NON-NLS-1$
		_table.put(new Integer(710), "&circ;"); //$NON-NLS-1$
		_table.put(new Integer(732), "&tilde;"); //$NON-NLS-1$
		_table.put(new Integer(8194), "&ensp;"); //$NON-NLS-1$
		_table.put(new Integer(8195), "&emsp;"); //$NON-NLS-1$
		_table.put(new Integer(8201), "&thinsp;"); //$NON-NLS-1$
		_table.put(new Integer(8204), "&zwnj;"); //$NON-NLS-1$
		_table.put(new Integer(8205), "&zwj;"); //$NON-NLS-1$
		_table.put(new Integer(8206), "&lrm;"); //$NON-NLS-1$
		_table.put(new Integer(8207), "&rlm;"); //$NON-NLS-1$
		_table.put(new Integer(8211), "&ndash;"); //$NON-NLS-1$
		_table.put(new Integer(151), "&mdash;"); //$NON-NLS-1$
		_table.put(new Integer(8216), "&lsquo;"); //$NON-NLS-1$
		_table.put(new Integer(8217), "&rsquo;"); //$NON-NLS-1$
		_table.put(new Integer(8218), "&sbquo;"); //$NON-NLS-1$
		_table.put(new Integer(8220), "&ldquo;"); //$NON-NLS-1$
		_table.put(new Integer(8221), "&rdquo;"); //$NON-NLS-1$
		_table.put(new Integer(8222), "&bdquo;"); //$NON-NLS-1$
		_table.put(new Integer(8224), "&dagger;"); //$NON-NLS-1$
		_table.put(new Integer(8225), "&Dagger;"); //$NON-NLS-1$
		_table.put(new Integer(8240), "&permil;"); //$NON-NLS-1$
		_table.put(new Integer(8249), "&lsaquo;"); //$NON-NLS-1$
		_table.put(new Integer(8250), "&rsaquo;"); //$NON-NLS-1$
		_table.put(new Integer(8364), "&euro;"); //$NON-NLS-1$

		_reverse = new Hashtable(256);
		for (Enumeration e = _table.keys(); e.hasMoreElements();) {
			Object key = e.nextElement();
			String value = (String) _table.get(key);
			_reverse.put(value, key);
			// also support without the training ';'
			_reverse.put(value.substring(0, value.length() - 1), key);
		}
	}

	/**
	 * @return if not in the special list
	 */
	public static String getSpecial(int ch) {
		return (String) _table.get(new Integer(ch));
	}

	public static int getSpecial(String str) {
		Integer result = (Integer) _reverse.get(str);
		if (result == null) {
			return -1;
		}
        return result.intValue();
	}

	public static void encode(String str, int start, int end, Writer writer)
			throws IOException {
		for (int i = start; i < end; i++) {
			char ch = str.charAt(i);
			String special = getSpecial(ch);
			if (special != null) {
				writer.write(special);
			} else {
				if ((ch & 0xff) != 0) {
					writer.write("&#"); //$NON-NLS-1$
					writer.write(Integer.toString(ch));
					writer.write(";"); //$NON-NLS-1$
				} else {
					writer.write(ch);
				}
			}
		}
	}

	public static StringBuffer encode(String str, StringBuffer result) {
		return encode(str, 0, str.length(), result);
	}

	public static StringBuffer encode(String str, int start, int end,
			StringBuffer result) {
		for (int i = start; i < end; i++) {
			char ch = str.charAt(i);
			String special = getSpecial(ch);
			if (special != null) {
				result.append(special);
			} else {
				if ((ch & 0xff00) != 0) {
					result.append("&#"); //$NON-NLS-1$
					result.append(Integer.toString(ch));
					result.append(";"); //$NON-NLS-1$
				} else {
					result.append(ch);
				}
			}
		}
		return result;
	}

	public static StringBuffer decode(String str, StringBuffer buffer)
			throws RuntimeException {
		return decode(str, 0, str.length(), buffer);
	}

	public static StringBuffer decode(String str, int start, int end,
			StringBuffer buffer) throws RuntimeException {
		int pos = start;
		do {
			char ch = str.charAt(pos);
			if (ch == '&') {
				int stop = str.indexOf(';', pos + 1);
				if (stop < 0 || stop >= end) {
					_log.error("HTMLSpecialCharHelper.3"); //$NON-NLS-1$
					throw new RuntimeException("HTMLSpecialCharHelper.2"); //$NON-NLS-1$
				}
				String sp = str.substring(pos, stop + 1);
				int special = getSpecial(sp);
				if (special != -1) {
					buffer.append((char) special);
				} else {
					ch = sp.charAt(1);
					if (ch != '#')
						throw new RuntimeException("HTMLSpecialCharHelper.1"); //$NON-NLS-1$
					try {
						buffer.append((char) Integer.parseInt(sp.substring(2,
								sp.length() - 1)));
					} catch (NumberFormatException ex) {
						_log.info("HTMLSpecialCharHelper.0", ex); //$NON-NLS-1$
						throw new RuntimeException("illegal: " + sp); //$NON-NLS-1$
					}
				}
				pos = stop + 1;
			} else {
				buffer.append(ch);
				pos++;
			}
		} while (pos < end);
		return buffer;
	}

	public static int decodeEntity(String entityRef) {
		Integer result = (Integer) _reverse.get(entityRef);
		if (result != null) {
			return result.intValue();
		}
		if (entityRef.length() >= 2 && entityRef.charAt(1) == '#') {
			String s = entityRef.substring(2);
			if (s.endsWith(";")) {
				s = s.substring(0, s.length() - 1);
			}
			try {
				return Integer.parseInt(s);
			} catch (Exception ex) {
				// ignore
			}
		}
		return -1;
	}
}

// FIXME: will it better to use Character instead of Integer ? (yang)
