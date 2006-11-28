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
package org.eclipse.jst.jsf.common.ui.internal.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;

/**
 * The main purpose of this class is to give better access methods for property
 * and resource bundle access.
 * 
 * @author mengbo
 */
public class PropertyUtils {
	// WARNING: There can be NO static logging line here since the logger uses
	// this class to figure out the preferences
	// for the logging system. "Logging" an error here would be useless since
	// you might be setting up the logging system
	// via a call to PropertyUtils.getServerProperty() instead it uses
	// "System.err.println".

	// This is the name for the properties file.
	// The prop-name will be prepended to this string....
	private static final String NAME_PROPERTIES = ".props";

	public static final String STR_BOUNDS_END = ".bounds"; // assumes the

	// window name or
	// name list is
	// prepended

	// //////////////////////////////////////////////////////////////////////////
	// Property get methods.
	// //////////////////////////////////////////////////////////////////////////
	public static String getProperty(Properties props, String key,
			String theDefault) {
		try {
			String value = props.getProperty(key, theDefault);
			if ((value != null) && (value.length() == 0)) {
				value = null;
			}
			// check again for null, since some versions of the jdk ignore the
			// default
			// if an empty property exists.
			if (value == null) {
				value = theDefault;
			}
			return value;
		} catch (Exception ee) {
			return theDefault;
		}
	}

	public static String getProperty(Properties props, String key) {
		try {
			String value = props.getProperty(key);
			if ((value != null) && (value.length() == 0)) {
				value = null;
			}
			return value;
		} catch (Exception ee) {
			return null;
		}
	}

	public static int getPropertyValue(Properties props, String key,
			int defaultValue, int minumumValue) {
		int theValue = getPropertyValue(props, key, defaultValue);

		if (theValue < minumumValue) {
			theValue = minumumValue;
		}
		return theValue;
	}

	public static int getPropertyValue(Properties props, String key,
			int defaultValue) {
		String stringValue = getProperty(props, key);
		if (stringValue != null) {
			try {
				return Integer.parseInt(stringValue);
			} catch (NumberFormatException ee)// NOPMD
			{
				// the property value maybe an invalid value, the editor should
				// show these to user.
			}
		}
		return defaultValue;
	}

	public static long getPropertyLongValue(Properties props, String key,
			long defaultValue) {
		String stringValue = getProperty(props, key);
		if (stringValue != null) {
			try {
				return Long.parseLong(stringValue);
			} catch (NumberFormatException ee)// NOPMD
			{
				// the property value maybe an invalid value, the editor should
				// show these to user.
			}
		}
		return defaultValue;
	}

	public static boolean isProperty(Properties props, String key,
			boolean bDefault) {
		return getProperty(props, key, "" + bDefault).equals("" + true);
	}

	public static String[] getPropertyStrings(Properties props, String key) {
		String tokenString = getProperty(props, key);

		if (tokenString == null) {
			return new String[0];
		}
        StringTokenizer tokenizer = new StringTokenizer(tokenString, ",");
        String[] pNames = new String[tokenizer.countTokens()];

        for (int ii = 0; ii < pNames.length; ii++) {
        	pNames[ii] = ((String) tokenizer.nextElement()).trim();
        }
        return pNames;
	}

	// //////////////////////////////////////////////////////////////////////////
	// Resource bundle get methods.
	// //////////////////////////////////////////////////////////////////////////
	public static String getResourceProperty(ResourceBundle bundle, String key,
			String theDefault) {
		try {
			String value = bundle.getString(key);
			if ((value == null) || (value.length() == 0)) {
				value = theDefault;
			}
			return value;
		} catch (Exception ee) {
			return theDefault;
		}
	}

	public static String getResourceProperty(ResourceBundle bundle, String key) {
		try {
			String value = bundle.getString(key);
			if ((value != null) && (value.length() == 0)) {
				value = null;
			}
			return value;
		} catch (Exception ee) {
			return null;
		}
	}

	public static int getResourcePropertyValue(ResourceBundle bundle,
			String key, int defaultValue, int minumumValue) {
		int theValue = getResourcePropertyValue(bundle, key, defaultValue);

		if (theValue < minumumValue) {
			theValue = minumumValue;
		}
		return theValue;
	}

	public static int getResourcePropertyValue(ResourceBundle bundle,
			String key, int defaultValue) {
		String stringValue = getResourceProperty(bundle, key);
		if (stringValue != null) {
			try {
				return Integer.parseInt(stringValue);
			} catch (NumberFormatException ee)// NOPMD
			{
				// the property value maybe an invalid value, the editor should
				// show these to user.
			}
		}
		return defaultValue;
	}

	public static long getResourcePropertyLongValue(ResourceBundle bundle,
			String key, long defaultValue) {
		String stringValue = getResourceProperty(bundle, key);
		if (stringValue != null) {
			try {
				return Long.parseLong(stringValue);
			} catch (NumberFormatException ee)// NOPMD
			{
				// the property value maybe an invalid value, the editor should
				// show these to user.
			}
		}
		return defaultValue;
	}

	public static boolean isResourceProperty(ResourceBundle bundle, String key,
			boolean bDefault) {
		return getResourceProperty(bundle, key, "" + bDefault)
				.equals("" + true);
	}

	// ///////////////////////////////////////////////////////////////////////
	// Property misc routines
	// ///////////////////////////////////////////////////////////////////////
	public static String encodeName(String theName) {
		int theSize = theName.length();
		StringBuffer encoded = new StringBuffer(theSize);
		char ch;

		for (int ii = 0; ii < theSize; ii++) {
			ch = theName.charAt(ii);
			switch (ch) {
			// these are the set of illegal characters in a Property name
			case '=': // %3d
				encoded.append("%3d");
				break;
			case ':': // %3a
				encoded.append("%3a");
				break;
			case ' ': // %20
				encoded.append("%20");
				break;
			case '\n': // %0a
				encoded.append("%0a");
				break;
			case '\t': // %09
				encoded.append("%09");
				break;
			case '\r': // %0d
				encoded.append("%0d");
				break;
			case '%': // %25
				// added because its our encoding flag
				encoded.append("%25");
				break;
			default:
				encoded.append(ch);
				break;
			}
		}

		return encoded.toString();
	}

	public static String decodeName(String theName) {
		int theSize = theName.length();
		int kk;
		StringBuffer decoded = new StringBuffer(theSize);
		char ch;

		for (int ii = 0; ii < theSize; ii++) {
			ch = theName.charAt(ii);
			if (ch == '%') {
				ch = theName.charAt(++ii);
				kk = Character.digit(ch, 16);
				kk *= 16;
				ch = theName.charAt(++ii);
				kk += Character.digit(ch, 16);
				decoded.append((char) kk);
			} else {
				decoded.append(ch);
			}
		}

		return decoded.toString();
	}

	public static Properties openProperties(String propName)
			throws IOException, FileNotFoundException {
		return openProperties(propName, null, true);
	}

	public static Properties openProperties(String propName,
			Properties propDefaults) throws IOException, FileNotFoundException {
		return openProperties(propName, propDefaults, true);
	}

	public static Properties openProperties(String propName,
			Properties propDefaults, boolean bCreatePropertiesPathname)
			throws IOException, FileNotFoundException {
		Properties theProperties = new Properties(propDefaults);

		try {
			String propertiesFilename = bCreatePropertiesPathname ? getPropertiesPathname(propName)
					: propName;
			InputStream theStream = new FileInputStream(propertiesFilename);
			theProperties.load(theStream);
			theStream.close();
		} catch (FileNotFoundException ee) {
			if (propDefaults == null) {
				throw ee;
			}
		} catch (IOException ee) {
			if (propDefaults == null) {
				throw ee;
			}
		}

		return theProperties;
	}

	/*
	 * * Combines two properties objects, with the second one as the default
	 * properties
	 */
	public static Properties combineProperties(Properties localProperties,
			Properties defaultProperties) throws IOException {
		Properties theNewProperties = new Properties();

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		defaultProperties.store(os, "");
		localProperties.store(os, "");
		byte[] theData = os.toByteArray();
		ByteArrayInputStream is = new ByteArrayInputStream(theData);
		theNewProperties.load(is);

		return theNewProperties;
	}

	// This should only be called by the main interface on shutdown/close!!!
	public static void saveProperties(String propName, Properties theProperties)
			throws IOException, SecurityException {
		saveProperties(propName, theProperties, true);
	}

	public static void saveProperties(String propName,
			Properties theProperties, boolean bCreatePropertiesPathname)
			throws IOException, SecurityException {
		// write out the changed set of preferences...
		String propertiesFilename = bCreatePropertiesPathname ? getPropertiesPathname(propName)
				: propName;
		FileOutputStream fout = new FileOutputStream(propertiesFilename);
		theProperties.store(fout, "preferences");
		fout.close();
	}

	public static String encodeFilename(String theFilename) {
		// make theFilename legal on the local system....
		String theSeparator = System.getProperty("file.separator");
		// replace all occurrances of the file separator with a ' '
		for (int ii = 0; ii < theSeparator.length(); ii++) {
			char theChar = theSeparator.charAt(ii);
			theFilename = theFilename.replace(theChar, ' ');
		}

		return theFilename;
	}

	public static String getPropertiesPathname(String baseName) {
		if (baseName.endsWith(NAME_PROPERTIES)) {
			return System.getProperty("user.dir")
					+ System.getProperty("file.separator")
					+ encodeFilename(baseName);
		}
        return System.getProperty("user.dir")
        		+ System.getProperty("file.separator")
        		+ encodeFilename(baseName) + NAME_PROPERTIES;
	}

	// /////////////////////////////////////////////////////////////////////////
	// These are generic routines that are used to get/set/save window bounds

	private static final int INSET = 40;

	/**
	 * Set the initial bounds (size & location) of a component. This will get
	 * the location from the preferences file based on the values of the "names"
	 * parameter. These values will be encoded to make a legal properties name,
	 * joined togther with ".", and the value STR_BOUNDS_END will be appended.
	 * The resulting name will be used to obtain the intial bounds value from
	 * the properties file, which will be decoded and the specified component
	 * will then be set to that value.
	 */
	public static void setComponentBounds(Properties props,
			Component theComponent, String names[], String defaultValue) {
		setComponentBounds(props, theComponent, names, defaultValue, false);
	}

	public static void setComponentBounds(Properties props,
			Component theComponent, String names[], String defaultValue,
			boolean bEnsureDesktopVisibility) {
		String tmpString = getComponentPropertyName(names, STR_BOUNDS_END);
		setComponentBounds(props, theComponent, tmpString, defaultValue,
				bEnsureDesktopVisibility);
	}

	public static void setComponentBounds(Properties props,
			Component theComponent, String thePropertyName, String defaultValue) {
		setComponentBounds(props, theComponent, thePropertyName, defaultValue,
				false);
	}

	public static void setComponentBounds(Properties props,
			Component theComponent, String thePropertyName,
			String defaultValue, boolean bEnsureDesktopVisibility) {
		String tmpString = props.getProperty(thePropertyName, defaultValue);
		Rectangle theValue = decodeBounds(tmpString);
		theComponent.setBounds(theValue);
		if (bEnsureDesktopVisibility) {
			// make sure that this component is visible on the desktop...
			// verify that this window is visible...
			Point theLoc = theComponent.getLocation();
			// get width/height of desktop....
			Dimension portSize = new Dimension(Toolkit.getDefaultToolkit()
					.getScreenSize());
			if (theLoc.x > portSize.width) // move it to top
				theLoc.x = INSET;
			if (theLoc.y > portSize.height) // move it to left
				theLoc.y = INSET;
			theComponent.setLocation(theLoc);
		}
	}

	public static void saveComponentBounds(Properties props,
			Component theComponent, String names[]) {
		String tmpString = getComponentPropertyName(names, STR_BOUNDS_END);
		saveComponentBounds(props, theComponent, tmpString);
	}

	public static void saveComponentBounds(Properties props,
			Component theComponent, String thePropertyName) {
		Rectangle theBounds = theComponent.getBounds();
		String theValue = encodeBounds(theBounds);
		props.put(thePropertyName, theValue);
	}

	public static String getComponentPropertyName(String names[],
			String subsystemName) {
		String tmpString = "";

		for (int ii = 0; ii < names.length; ii++) {
			tmpString = tmpString + (ii > 0 ? "." : "")
					+ PropertyUtils.encodeName(names[ii]);
		}
		if (subsystemName.startsWith(".") == false)
			tmpString += ".";
		tmpString = tmpString + subsystemName;
		return tmpString;
	}

	/**
	 * Decode the comma separated values stored in sBounds. This method is
	 * normally called to decode the location/size of a component which has been
	 * saved into a Properties object. See encodeBounds(); Order of items in the
	 * string is (x, y, w, h)
	 */
	public static Rectangle decodeBounds(String sBounds) {
		int index;
		int ii;
		int theValue[] = new int[4];
		String tmpString;
		String restString = sBounds;

		for (ii = 0; ii < 4; ii++) {
			theValue[ii] = 0;
		}
		try {
			for (ii = 0; ii < 4; ii++) {
				index = restString.indexOf(",");
				if (index > 0) {
					tmpString = restString.substring(0, index);
					restString = restString.substring(index + 1);
				} else {
					tmpString = restString; // should only happen on the last
					// one....
					restString = null; // will cause an exception if not last
					// one...
				}
				theValue[ii] = Integer.valueOf(tmpString).intValue();
			}
		} catch (Exception ee)// NOPMD
		{
			// the property value maybe an invalid value, the editor should show
			// these to user.
		}

		return new Rectangle(theValue[0], theValue[1], theValue[2], theValue[3]);
	}

	/**
	 * * Encode the bounds of a component into a comma separated list * that is
	 * appropriate for storing in a Properties object. * See decodeBounds();
	 */
	public static String encodeBounds(Rectangle rBounds) {
		return "" + rBounds.x + "," + rBounds.y + "," + rBounds.width + ","
				+ rBounds.height;
	}

	/**
	 * Methods for creating Properties objects from strings.
	 * 
	 * Then "Encoded" versions are used on values that are stored into a
	 * properties file (think of them as sub-properties). They do the encoding
	 * necessary to turn a properties object into a string that has legal
	 * "value" syntax (they actually do more than they need to, but its all
	 * non-destructive).
	 */
	public static Properties getPropertiesFromString(String thePropertyString)
			throws IOException {
		if (thePropertyString == null)
			return null;
		ByteArrayInputStream in = new ByteArrayInputStream(thePropertyString
				.getBytes());

		Properties props = new Properties();
		props.load(in); // throws IOException
		in = null;
		return props;
	}

	public static Properties getPropertiesFromEncodedString(
			String theEncodedPropertyString) throws IOException {
		if (theEncodedPropertyString == null)
			return null;
		return (getPropertiesFromString(decodeName(theEncodedPropertyString)));
	}

	public static Properties encodedStringToProperties(
			String theEncodedPropertyString) {
		try {
			return getPropertiesFromEncodedString(theEncodedPropertyString);
		} catch (IOException ee) {
			return null;
		}
	}

	public static String savePropertiesToString(Properties props, String comment)
			throws IOException {
		if (props == null)
			return null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		props.store(out, comment);
		String tmpString = out.toString();
		out = null;
		return tmpString;
	}

	public static String savePropertiesToEncodedString(Properties props,
			String comment) throws IOException {
		if (props == null)
			return null;
		return encodeName(savePropertiesToString(props, comment));
	}

	public static String propertiesToEncodedString(Properties props) {
		try {
			return savePropertiesToEncodedString(props, "");
		} catch (IOException ee)// NOPMD
		{
            JSFUICommonPlugin.getLogger(PropertyUtils.class).error("saving properties", ee);
		}
		return null;
	}
}
