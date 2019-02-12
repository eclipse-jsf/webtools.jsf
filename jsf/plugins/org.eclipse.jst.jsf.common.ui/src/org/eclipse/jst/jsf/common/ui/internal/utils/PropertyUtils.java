/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
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
public final class PropertyUtils {

	private static final String ENCODED_CHAR_PERCENT = "%25"; //$NON-NLS-1$
	private static final String ENCODED_CHAR_CARRIAGE_RETURN = "%0d"; //$NON-NLS-1$
	private static final String ENCODED_CHAR_TAB = "%09"; //$NON-NLS-1$
	private static final String ENCODED_CHAR_NEWLINE = "%0a"; //$NON-NLS-1$
	private static final String ENCODED_CHAR_SPACE = "%20"; //$NON-NLS-1$
	private static final String ENCODED_CHAR_COLON = "%3a"; //$NON-NLS-1$
	private static final String ENCODED_CHAR_EQUALS = "%3d"; //$NON-NLS-1$

	
	// WARNING: There can be NO static logging line here since the logger uses
	// this class to figure out the preferences
	// for the logging system. "Logging" an error here would be useless since
	// you might be setting up the logging system
	// via a call to PropertyUtils.getServerProperty() instead it uses
	// "System.err.println".

	// This is the name for the properties file.
	// The prop-name will be prepended to this string....
	private static final String NAME_PROPERTIES = ".props"; //$NON-NLS-1$

	private static final String STR_BOUNDS_END = ".bounds"; // assumes the //$NON-NLS-1$

	// window name or
	// name list is
	// prepended

	// //////////////////////////////////////////////////////////////////////////
	// Property get methods.
	// //////////////////////////////////////////////////////////////////////////
	/**
	 * @param props
	 * @param key
	 * @param theDefault
	 * @return the property
	 */
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

	/**
	 * @param props
	 * @param key
	 * @return the value for key in props, may return null
	 */
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

	/**
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @param minimumValue
	 * @return the integer property value for key, or defaultValue
	 * if none.  Enforces minimumValue in all cases
	 */
	public static int getPropertyValue(Properties props, String key,
			int defaultValue, int minimumValue) {
		int theValue = getPropertyValue(props, key, defaultValue);

		if (theValue < minimumValue) {
			theValue = minimumValue;
		}
		return theValue;
	}

	/**
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return the integer value for key in props or defaultValue if none
	 */
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

	/**
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return the long value for key props or defaultValue if none
	 */
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

	/**
	 * @param props
	 * @param key
	 * @param bDefault
	 * @return true if props has a value for key
	 */
	public static boolean isProperty(Properties props, String key,
			boolean bDefault) {
		return getProperty(props, key, "" + bDefault).equals("" + true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * @param props
	 * @param key
	 * @return the string values in props for key tokenized from
	 * a comma-separated string
	 */
	public static String[] getPropertyStrings(Properties props, String key) {
		String tokenString = getProperty(props, key);

		if (tokenString == null) {
			return new String[0];
		}
        StringTokenizer tokenizer = new StringTokenizer(tokenString, ","); //$NON-NLS-1$
        String[] pNames = new String[tokenizer.countTokens()];

        for (int ii = 0; ii < pNames.length; ii++) {
        	pNames[ii] = ((String) tokenizer.nextElement()).trim();
        }
        return pNames;
	}

	// //////////////////////////////////////////////////////////////////////////
	// Resource bundle get methods.
	// //////////////////////////////////////////////////////////////////////////
	/**
	 * @param bundle
	 * @param key
	 * @param theDefault
	 * @return the string value from bundle for key or default if none
	 */
	public static String getResourceProperty(ResourceBundle bundle, String key,
			String theDefault) {
		try {
			String value = bundle.getString(key);
			if ((value == null) || (value.length() == 0)) {
				value = theDefault;
			}
			return value;
		} 
		catch(NullPointerException npe)
		{
			return theDefault;
		}
		catch (MissingResourceException mre)
		{
			return theDefault;
		}
		catch (ClassCastException cce)
		{
			return theDefault;
		}
	}

	/**
	 * @param bundle
	 * @param key
	 * @return the value for key in bundle or null if none
	 */
	public static String getResourceProperty(ResourceBundle bundle, String key) {
		try 
		{
			String value = bundle.getString(key);
			if ((value != null) && (value.length() == 0)) 
			{
				value = null;
			}
				return value;
		}
		catch(NullPointerException npe)
		{
			return null;
		}
		catch (MissingResourceException mre)
		{
			return null;
		}
		catch (ClassCastException cce)
		{
			return null;
		}
	}

	/**
	 * @param bundle
	 * @param key
	 * @param defaultValue
	 * @param minimumValue
	 * @return the integer value for key in bundle or defaultValue if none
	 * Enforces minimum value in all cases
	 */
	public static int getResourcePropertyValue(ResourceBundle bundle,
			String key, int defaultValue, int minimumValue) {
		int theValue = getResourcePropertyValue(bundle, key, defaultValue);

		if (theValue < minimumValue) {
			theValue = minimumValue;
		}
		return theValue;
	}

	/**
	 * @param bundle
	 * @param key
	 * @param defaultValue
	 * @return the integer value for key in bundle or defaultValue if  none
	 */
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

	/**
	 * @param bundle
	 * @param key
	 * @param defaultValue
	 * @return the long value for key in bundle or default value if none
	 */
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

	/**
	 * @param bundle
	 * @param key
	 * @param bDefault
	 * @return true if bundle has a value for key
	 */
	public static boolean isResourceProperty(ResourceBundle bundle, String key,
			boolean bDefault) {
		return getResourceProperty(bundle, key, "" + bDefault) //$NON-NLS-1$
				.equals("" + true); //$NON-NLS-1$
	}

	// ///////////////////////////////////////////////////////////////////////
	// Property misc routines
	// ///////////////////////////////////////////////////////////////////////
	/**
	 * @param theName
	 * @return the encoded name
	 */
	public static String encodeName(String theName) {
		int theSize = theName.length();
		StringBuffer encoded = new StringBuffer(theSize);
		char ch;

		for (int ii = 0; ii < theSize; ii++) {
			ch = theName.charAt(ii);
			switch (ch) {
			// these are the set of illegal characters in a Property name
			case '=': // %3d
				encoded.append(ENCODED_CHAR_EQUALS);
				break;
			case ':': // %3a
				encoded.append(ENCODED_CHAR_COLON);
				break;
			case ' ': // %20
				encoded.append(ENCODED_CHAR_SPACE);
				break;
			case '\n': // %0a
				encoded.append(ENCODED_CHAR_NEWLINE);
				break;
			case '\t': // %09
				encoded.append(ENCODED_CHAR_TAB);
				break;
			case '\r': // %0d
				encoded.append(ENCODED_CHAR_CARRIAGE_RETURN);
				break;
			case '%': // %25
				// added because its our encoding flag
				encoded.append(ENCODED_CHAR_PERCENT);
				break;
			default:
				encoded.append(ch);
				break;
			}
		}

		return encoded.toString();
	}

	/**
	 * @param theName
	 * @return the decoded name
	 */
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

	/**
	 * @param propName
	 * @return the properties
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static Properties openProperties(String propName)
			throws IOException, FileNotFoundException {
		return openProperties(propName, null, true);
	}

	/**
	 * @param propName
	 * @param propDefaults
	 * @return the properties
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static Properties openProperties(String propName,
			Properties propDefaults) throws IOException, FileNotFoundException {
		return openProperties(propName, propDefaults, true);
	}

	/**
	 * @param propName
	 * @param propDefaults
	 * @param bCreatePropertiesPathname
	 * @return the properties
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
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

	/**
	 * Combines two properties objects, with the second one as the default
	 * properties
	 * 
	 * @param localProperties
	 * @param defaultProperties
	 * @return the combined properties
	 * @throws IOException
	 */
	public static Properties combineProperties(Properties localProperties,
			Properties defaultProperties) throws IOException {
		Properties theNewProperties = new Properties();

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		defaultProperties.store(os, ""); //$NON-NLS-1$
		localProperties.store(os, ""); //$NON-NLS-1$
		byte[] theData = os.toByteArray();
		ByteArrayInputStream is = new ByteArrayInputStream(theData);
		theNewProperties.load(is);

		return theNewProperties;
	}

	/**
	 * @param theFilename
	 * @return the encoded file name
	 */ 
	public static String encodeFilename(String theFilename) {
		// make theFilename legal on the local system....
		String theSeparator = System.getProperty("file.separator"); //$NON-NLS-1$
		// replace all occurrances of the file separator with a ' '
		for (int ii = 0; ii < theSeparator.length(); ii++) {
			char theChar = theSeparator.charAt(ii);
			theFilename = theFilename.replace(theChar, ' ');
		}

		return theFilename;
	}

	/**
	 * @param baseName
	 * @return the properties path
	 */
	public static String getPropertiesPathname(String baseName) {
		if (baseName.endsWith(NAME_PROPERTIES)) {
			return System.getProperty("user.dir") //$NON-NLS-1$
					+ System.getProperty("file.separator") //$NON-NLS-1$
					+ encodeFilename(baseName);
		}
        return System.getProperty("user.dir") //$NON-NLS-1$
        		+ System.getProperty("file.separator") //$NON-NLS-1$
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
	 * @param props 
	 * @param theComponent 
	 * @param names 
	 * @param defaultValue 
	 */
	public static void setComponentBounds(Properties props,
			Component theComponent, String names[], String defaultValue) {
		setComponentBounds(props, theComponent, names, defaultValue, false);
	}

	/**
	 * @param props
	 * @param theComponent
	 * @param names
	 * @param defaultValue
	 * @param bEnsureDesktopVisibility
	 */
	public static void setComponentBounds(Properties props,
			Component theComponent, String names[], String defaultValue,
			boolean bEnsureDesktopVisibility) {
		String tmpString = getComponentPropertyName(names, STR_BOUNDS_END);
		setComponentBounds(props, theComponent, tmpString, defaultValue,
				bEnsureDesktopVisibility);
	}

	/**
	 * @param props
	 * @param theComponent
	 * @param thePropertyName
	 * @param defaultValue
	 */
	public static void setComponentBounds(Properties props,
			Component theComponent, String thePropertyName, String defaultValue) {
		setComponentBounds(props, theComponent, thePropertyName, defaultValue,
				false);
	}

	/**
	 * @param props
	 * @param theComponent
	 * @param thePropertyName
	 * @param defaultValue
	 * @param bEnsureDesktopVisibility
	 */
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

	/**
	 * @param props
	 * @param theComponent
	 * @param names
	 */
	public static void saveComponentBounds(Properties props,
			Component theComponent, String names[]) {
		String tmpString = getComponentPropertyName(names, STR_BOUNDS_END);
		saveComponentBounds(props, theComponent, tmpString);
	}

	/**
	 * @param props
	 * @param theComponent
	 * @param thePropertyName
	 */
	public static void saveComponentBounds(Properties props,
			Component theComponent, String thePropertyName) {
		Rectangle theBounds = theComponent.getBounds();
		String theValue = encodeBounds(theBounds);
		props.put(thePropertyName, theValue);
	}

	/**
	 * @param names
	 * @param subsystemName
	 * @return the component property name or ""
	 */
	public static String getComponentPropertyName(String names[],
			String subsystemName) {
		String tmpString = ""; //$NON-NLS-1$

		for (int ii = 0; ii < names.length; ii++) {
			tmpString = tmpString + (ii > 0 ? "." : "") //$NON-NLS-1$ //$NON-NLS-2$
					+ PropertyUtils.encodeName(names[ii]);
		}
		if (subsystemName.startsWith(".") == false) //$NON-NLS-1$
			tmpString += "."; //$NON-NLS-1$
		tmpString = tmpString + subsystemName;
		return tmpString;
	}

	/**
	 * Decode the comma separated values stored in sBounds. This method is
	 * normally called to decode the location/size of a component which has been
	 * saved into a Properties object. See encodeBounds(); Order of items in the
	 * string is (x, y, w, h)
	 * @param sBounds 
	 * @return the rectangle
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
				index = restString.indexOf(","); //$NON-NLS-1$
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
	 * @param rBounds 
	 * @return the encoded bounds
	 */
	public static String encodeBounds(Rectangle rBounds) {
		return "" + rBounds.x + "," + rBounds.y + "," + rBounds.width + "," //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
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
	 * @param thePropertyString 
	 * @return the properties from the string
	 * @throws IOException 
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

	/**
	 * @param theEncodedPropertyString
	 * @return the properties
	 * @throws IOException
	 */
	public static Properties getPropertiesFromEncodedString(
			String theEncodedPropertyString) throws IOException {
		if (theEncodedPropertyString == null)
			return null;
		return (getPropertiesFromString(decodeName(theEncodedPropertyString)));
	}

	/**
	 * @param theEncodedPropertyString
	 * @return the properties
	 */
	public static Properties encodedStringToProperties(
			String theEncodedPropertyString) {
		try {
			return getPropertiesFromEncodedString(theEncodedPropertyString);
		} catch (IOException ee) {
			return null;
		}
	}

	/**
	 * @param props
	 * @param comment
	 * @return the string
	 * @throws IOException
	 */
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

	/**
	 * @param props
	 * @param comment
	 * @return the encoded string
	 * @throws IOException
	 */
	public static String savePropertiesToEncodedString(Properties props,
			String comment) throws IOException {
		if (props == null)
			return null;
		return encodeName(savePropertiesToString(props, comment));
	}

	/**
	 * @param props
	 * @return the encoded string
	 */
	public static String propertiesToEncodedString(Properties props) {
		try {
			return savePropertiesToEncodedString(props, ""); //$NON-NLS-1$
		} catch (IOException ee)// NOPMD
		{
            JSFUICommonPlugin.getLogger(PropertyUtils.class).error("saving properties", ee); //$NON-NLS-1$
		}
		return null;
	}
	
	private PropertyUtils()
	{
		// no instantiation
	}
}
