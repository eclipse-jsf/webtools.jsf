/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation., and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;

/**
 * ISO-4217 Currency codes 
 */
public class CurrencyCodeType extends EnumerationType implements IPossibleValues {
	private static final CurrencyCodeMap _map = new CurrencyCodeMap();

	@Override
	protected String getReturnType() {
		return "java.lang.String"; //$NON-NLS-1$
	}

	public List getPossibleValues() {
		List ret = new ArrayList();
		for(Iterator it = _map.keySet().iterator();it.hasNext();){
			String key = (String)it.next();
			String value = _map.get(key);
			PossibleValue pv = new PossibleValue(key, value);
			ret.add(pv);
		}
		return ret;
	}

	public boolean isValidValue(String value) {
		if(!_map.containsKey(value.toUpperCase())) {
			addNewValidationMessage(Messages.CurrencyCodeType_invalid);
		}

		return getValidationMessages().isEmpty();
	}

	private static class CurrencyCodeMap extends HashMap<String, String> {
		
		private static final long serialVersionUID = 1L;

		CurrencyCodeMap(){
			put("AED",Messages.CurrencyCodeType_2); //$NON-NLS-1$ 
			put("AFA",Messages.CurrencyCodeType_3); //$NON-NLS-1$ 
			put("ALL",Messages.CurrencyCodeType_4); //$NON-NLS-1$ 
			put("AMD",Messages.CurrencyCodeType_5); //$NON-NLS-1$ 
			put("ANG",Messages.CurrencyCodeType_6); //$NON-NLS-1$ 
			put("AOA",Messages.CurrencyCodeType_7); //$NON-NLS-1$ 
			put("ARS",Messages.CurrencyCodeType_8); //$NON-NLS-1$ 
			put("AUD",Messages.CurrencyCodeType_9); //$NON-NLS-1$ 
			put("AWG",Messages.CurrencyCodeType_10); //$NON-NLS-1$ 
			put("AZM",Messages.CurrencyCodeType_11); //$NON-NLS-1$ 
			put("BAM",Messages.CurrencyCodeType_12); //$NON-NLS-1$ 
			put("BBD",Messages.CurrencyCodeType_13); //$NON-NLS-1$ 
			put("BDT",Messages.CurrencyCodeType_14); //$NON-NLS-1$ 
			put("BGN",Messages.CurrencyCodeType_15); //$NON-NLS-1$ 
			put("BHD",Messages.CurrencyCodeType_16); //$NON-NLS-1$ 
			put("BIF",Messages.CurrencyCodeType_17); //$NON-NLS-1$ 
			put("BMD",Messages.CurrencyCodeType_18); //$NON-NLS-1$ 
			put("BND",Messages.CurrencyCodeType_19); //$NON-NLS-1$ 
			put("BOB",Messages.CurrencyCodeType_20); //$NON-NLS-1$ 
			put("BRL",Messages.CurrencyCodeType_21); //$NON-NLS-1$ 
			put("BSD",Messages.CurrencyCodeType_22); //$NON-NLS-1$ 
			put("BTN",Messages.CurrencyCodeType_23); //$NON-NLS-1$ 
			put("BWP",Messages.CurrencyCodeType_24); //$NON-NLS-1$ 
			put("BYR",Messages.CurrencyCodeType_25); //$NON-NLS-1$ 
			put("BZD",Messages.CurrencyCodeType_26); //$NON-NLS-1$ 
			put("CAD",Messages.CurrencyCodeType_27); //$NON-NLS-1$ 
			put("CDF",Messages.CurrencyCodeType_28); //$NON-NLS-1$ 
			put("CHF",Messages.CurrencyCodeType_29); //$NON-NLS-1$ 
			put("CLP",Messages.CurrencyCodeType_30); //$NON-NLS-1$ 
			put("CNY",Messages.CurrencyCodeType_31); //$NON-NLS-1$ 
			put("COP",Messages.CurrencyCodeType_32); //$NON-NLS-1$ 
			put("CRC",Messages.CurrencyCodeType_33); //$NON-NLS-1$ 
			put("CSD",Messages.CurrencyCodeType_34); //$NON-NLS-1$ 
			put("CUP",Messages.CurrencyCodeType_35); //$NON-NLS-1$ 
			put("CVE",Messages.CurrencyCodeType_36); //$NON-NLS-1$ 
			put("CYP",Messages.CurrencyCodeType_37); //$NON-NLS-1$ 
			put("CZK",Messages.CurrencyCodeType_38); //$NON-NLS-1$ 
			put("DJF",Messages.CurrencyCodeType_39); //$NON-NLS-1$ 
			put("DKK",Messages.CurrencyCodeType_40); //$NON-NLS-1$ 
			put("DOP",Messages.CurrencyCodeType_41); //$NON-NLS-1$ 
			put("DZD",Messages.CurrencyCodeType_42); //$NON-NLS-1$ 
			put("EEK",Messages.CurrencyCodeType_43); //$NON-NLS-1$ 
			put("EGP",Messages.CurrencyCodeType_44); //$NON-NLS-1$ 
			put("ERN",Messages.CurrencyCodeType_45); //$NON-NLS-1$ 
			put("ETB",Messages.CurrencyCodeType_46); //$NON-NLS-1$ 
			put("EUR",Messages.CurrencyCodeType_47); //$NON-NLS-1$ 
			put("FJD",Messages.CurrencyCodeType_48); //$NON-NLS-1$ 
			put("FKP",Messages.CurrencyCodeType_49); //$NON-NLS-1$ 
			put("GBP",Messages.CurrencyCodeType_50); //$NON-NLS-1$ 
			put("GEL",Messages.CurrencyCodeType_51); //$NON-NLS-1$ 
			put("GGP",Messages.CurrencyCodeType_52); //$NON-NLS-1$ 
			put("GHC",Messages.CurrencyCodeType_53); //$NON-NLS-1$ 
			put("GIP",Messages.CurrencyCodeType_54); //$NON-NLS-1$ 
			put("GMD",Messages.CurrencyCodeType_55); //$NON-NLS-1$ 
			put("GNF",Messages.CurrencyCodeType_56); //$NON-NLS-1$ 
			put("GTQ",Messages.CurrencyCodeType_57); //$NON-NLS-1$ 
			put("GYD",Messages.CurrencyCodeType_58); //$NON-NLS-1$ 
			put("HKD",Messages.CurrencyCodeType_59); //$NON-NLS-1$ 
			put("HNL",Messages.CurrencyCodeType_60); //$NON-NLS-1$ 
			put("HRK",Messages.CurrencyCodeType_61); //$NON-NLS-1$ 
			put("HTG",Messages.CurrencyCodeType_62); //$NON-NLS-1$ 
			put("HUF",Messages.CurrencyCodeType_63); //$NON-NLS-1$ 
			put("IDR",Messages.CurrencyCodeType_64); //$NON-NLS-1$ 
			put("ILS",Messages.CurrencyCodeType_65); //$NON-NLS-1$ 
			put("IMP",Messages.CurrencyCodeType_66); //$NON-NLS-1$ 
			put("INR",Messages.CurrencyCodeType_67); //$NON-NLS-1$ 
			put("IQD",Messages.CurrencyCodeType_68); //$NON-NLS-1$ 
			put("IRR",Messages.CurrencyCodeType_69); //$NON-NLS-1$ 
			put("ISK",Messages.CurrencyCodeType_70); //$NON-NLS-1$ 
			put("JEP",Messages.CurrencyCodeType_71); //$NON-NLS-1$ 
			put("JMD",Messages.CurrencyCodeType_72); //$NON-NLS-1$ 
			put("JOD",Messages.CurrencyCodeType_73); //$NON-NLS-1$ 
			put("JPY",Messages.CurrencyCodeType_74); //$NON-NLS-1$ 
			put("KES",Messages.CurrencyCodeType_75); //$NON-NLS-1$ 
			put("KGS",Messages.CurrencyCodeType_76); //$NON-NLS-1$ 
			put("KHR",Messages.CurrencyCodeType_77); //$NON-NLS-1$ 
			put("KMF",Messages.CurrencyCodeType_78); //$NON-NLS-1$ 
			put("KPW",Messages.CurrencyCodeType_79); //$NON-NLS-1$ 
			put("KRW",Messages.CurrencyCodeType_80); //$NON-NLS-1$ 
			put("KWD",Messages.CurrencyCodeType_81); //$NON-NLS-1$ 
			put("KYD",Messages.CurrencyCodeType_82); //$NON-NLS-1$ 
			put("KZT",Messages.CurrencyCodeType_83); //$NON-NLS-1$ 
			put("LAK",Messages.CurrencyCodeType_84); //$NON-NLS-1$ 
			put("LBP",Messages.CurrencyCodeType_85); //$NON-NLS-1$ 
			put("LKR",Messages.CurrencyCodeType_86); //$NON-NLS-1$ 
			put("LRD",Messages.CurrencyCodeType_87); //$NON-NLS-1$ 
			put("LSL",Messages.CurrencyCodeType_88); //$NON-NLS-1$ 
			put("LTL",Messages.CurrencyCodeType_89); //$NON-NLS-1$ 
			put("LVL",Messages.CurrencyCodeType_90); //$NON-NLS-1$ 
			put("LYD",Messages.CurrencyCodeType_91); //$NON-NLS-1$ 
			put("MAD",Messages.CurrencyCodeType_92); //$NON-NLS-1$ 
			put("MDL",Messages.CurrencyCodeType_93); //$NON-NLS-1$ 
			put("MGA",Messages.CurrencyCodeType_94); //$NON-NLS-1$ 
			put("MKD",Messages.CurrencyCodeType_95); //$NON-NLS-1$ 
			put("MMK",Messages.CurrencyCodeType_96); //$NON-NLS-1$ 
			put("MNT",Messages.CurrencyCodeType_97); //$NON-NLS-1$ 
			put("MOP",Messages.CurrencyCodeType_98); //$NON-NLS-1$ 
			put("MRO",Messages.CurrencyCodeType_99); //$NON-NLS-1$ 
			put("MTL",Messages.CurrencyCodeType_100); //$NON-NLS-1$ 
			put("MUR",Messages.CurrencyCodeType_101); //$NON-NLS-1$ 
			put("MVR",Messages.CurrencyCodeType_102); //$NON-NLS-1$ 
			put("MWK",Messages.CurrencyCodeType_103); //$NON-NLS-1$ 
			put("MXN",Messages.CurrencyCodeType_104); //$NON-NLS-1$ 
			put("MYR",Messages.CurrencyCodeType_105); //$NON-NLS-1$ 
			put("MZM",Messages.CurrencyCodeType_106); //$NON-NLS-1$ 
			put("NAD",Messages.CurrencyCodeType_107); //$NON-NLS-1$ 
			put("NGN",Messages.CurrencyCodeType_108); //$NON-NLS-1$ 
			put("NIO",Messages.CurrencyCodeType_109); //$NON-NLS-1$ 
			put("NOK",Messages.CurrencyCodeType_110); //$NON-NLS-1$ 
			put("NPR",Messages.CurrencyCodeType_111); //$NON-NLS-1$ 
			put("NZD",Messages.CurrencyCodeType_112); //$NON-NLS-1$ 
			put("OMR",Messages.CurrencyCodeType_113); //$NON-NLS-1$ 
			put("PAB",Messages.CurrencyCodeType_114); //$NON-NLS-1$ 
			put("PEN",Messages.CurrencyCodeType_115); //$NON-NLS-1$ 
			put("PGK",Messages.CurrencyCodeType_116); //$NON-NLS-1$ 
			put("PHP",Messages.CurrencyCodeType_117); //$NON-NLS-1$ 
			put("PKR",Messages.CurrencyCodeType_118); //$NON-NLS-1$ 
			put("PLN",Messages.CurrencyCodeType_119); //$NON-NLS-1$ 
			put("PYG",Messages.CurrencyCodeType_120); //$NON-NLS-1$ 
			put("QAR",Messages.CurrencyCodeType_121); //$NON-NLS-1$ 
			put("ROL",Messages.CurrencyCodeType_122); //$NON-NLS-1$ 
			put("RUB",Messages.CurrencyCodeType_123); //$NON-NLS-1$ 
			put("RWF",Messages.CurrencyCodeType_124); //$NON-NLS-1$ 
			put("SAR",Messages.CurrencyCodeType_125); //$NON-NLS-1$ 
			put("SBD",Messages.CurrencyCodeType_126); //$NON-NLS-1$ 
			put("SCR",Messages.CurrencyCodeType_127); //$NON-NLS-1$ 
			put("SDD",Messages.CurrencyCodeType_128); //$NON-NLS-1$ 
			put("SEK",Messages.CurrencyCodeType_129); //$NON-NLS-1$ 
			put("SGD",Messages.CurrencyCodeType_130); //$NON-NLS-1$ 
			put("SHP",Messages.CurrencyCodeType_131); //$NON-NLS-1$ 
			put("SIT",Messages.CurrencyCodeType_132); //$NON-NLS-1$ 
			put("SKK",Messages.CurrencyCodeType_133); //$NON-NLS-1$ 
			put("SLL",Messages.CurrencyCodeType_134); //$NON-NLS-1$ 
			put("SOS",Messages.CurrencyCodeType_135); //$NON-NLS-1$ 
			put("SPL",Messages.CurrencyCodeType_136); //$NON-NLS-1$ 
			put("SRD",Messages.CurrencyCodeType_137); //$NON-NLS-1$ 
			put("STD",Messages.CurrencyCodeType_138); //$NON-NLS-1$ 
			put("SVC",Messages.CurrencyCodeType_139); //$NON-NLS-1$ 
			put("SYP",Messages.CurrencyCodeType_140); //$NON-NLS-1$ 
			put("SZL",Messages.CurrencyCodeType_141); //$NON-NLS-1$ 
			put("THB",Messages.CurrencyCodeType_142); //$NON-NLS-1$ 
			put("TJS",Messages.CurrencyCodeType_143); //$NON-NLS-1$ 
			put("TMM",Messages.CurrencyCodeType_144); //$NON-NLS-1$ 
			put("TND",Messages.CurrencyCodeType_145); //$NON-NLS-1$ 
			put("TOP",Messages.CurrencyCodeType_146); //$NON-NLS-1$ 
			put("TRL",Messages.CurrencyCodeType_147); //$NON-NLS-1$ 
			put("TRY",Messages.CurrencyCodeType_148); //$NON-NLS-1$ 
			put("TTD",Messages.CurrencyCodeType_149); //$NON-NLS-1$ 
			put("TVD",Messages.CurrencyCodeType_150); //$NON-NLS-1$ 
			put("TWD",Messages.CurrencyCodeType_151); //$NON-NLS-1$ 
			put("TZS",Messages.CurrencyCodeType_152); //$NON-NLS-1$ 
			put("UAH",Messages.CurrencyCodeType_153); //$NON-NLS-1$ 
			put("UGX",Messages.CurrencyCodeType_154); //$NON-NLS-1$ 
			put("USD",Messages.CurrencyCodeType_155); //$NON-NLS-1$ 
			put("UYU",Messages.CurrencyCodeType_156); //$NON-NLS-1$ 
			put("UZS",Messages.CurrencyCodeType_157); //$NON-NLS-1$ 
			put("VEB",Messages.CurrencyCodeType_158); //$NON-NLS-1$ 
			put("VND",Messages.CurrencyCodeType_159); //$NON-NLS-1$ 
			put("VUV",Messages.CurrencyCodeType_160); //$NON-NLS-1$ 
			put("WST",Messages.CurrencyCodeType_161); //$NON-NLS-1$ 
			put("XAF",Messages.CurrencyCodeType_162); //$NON-NLS-1$ 
			put("XAG",Messages.CurrencyCodeType_163); //$NON-NLS-1$ 
			put("XAU",Messages.CurrencyCodeType_164); //$NON-NLS-1$ 
			put("XCD",Messages.CurrencyCodeType_165); //$NON-NLS-1$ 
			put("XDR",Messages.CurrencyCodeType_166); //$NON-NLS-1$ 
			put("XOF",Messages.CurrencyCodeType_167); //$NON-NLS-1$ 
			put("XPD",Messages.CurrencyCodeType_168); //$NON-NLS-1$ 
			put("XPF",Messages.CurrencyCodeType_169); //$NON-NLS-1$ 
			put("XPT",Messages.CurrencyCodeType_170); //$NON-NLS-1$ 
			put("YER",Messages.CurrencyCodeType_171); //$NON-NLS-1$ 
			put("ZAR",Messages.CurrencyCodeType_172); //$NON-NLS-1$ 
			put("ZMK",Messages.CurrencyCodeType_173); //$NON-NLS-1$ 
			put("ZWD",Messages.CurrencyCodeType_174); //$NON-NLS-1$ 
		}
	}
}
