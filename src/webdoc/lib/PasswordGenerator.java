/*******************************************************************************
 * Copyright (c) 2015 by Aron Heinecke & Jonathan Peper
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.lib;

import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Password Generator with extended possibilities
 * @author "Aron Heinecke"
 *
 */
public final class PasswordGenerator {
	
	/**
	 * generates the password
	 * @param validchars char array of valid chars
	 * @param len length of pw
	 * @return string password
	 */
	public String generate(char[] validchars, int len) {
	    char[] password = new char[len];
	    Random rand = new Random(System.nanoTime());
	    for (int i = 0; i < len; i++) {
	        password[i] = validchars[rand.nextInt(validchars.length)];
	    }
	    return new String(password);
	}
	
	/**
	 * Generates a char array of valid chars based on a regex input
	 * @param regex
	 * @param lastchar
	 * @return
	 */
	public final char[] getValid(final String regex, final int lastchar) {
	    char[] potential = new char[lastchar]; // 32768 is not huge....
	    int size = 0;
	    final Pattern pattern = Pattern.compile(regex);
	    for (int c = 0; c <= lastchar; c++) {
	        if (pattern.matcher(String.valueOf((char)c)).matches()) {
	            potential[size++] = (char)c;
	        }
	    }
	    return Arrays.copyOf(potential, size);
	}
	
	/**
	 * Generates a generic random password
	 * @param length length of the PW
	 * @return pw
	 */
	public String generateGenericPassword(int length){
		return generate(getValid("[a-zA-Z0-9]", Character.MAX_VALUE), length);
	}
}
