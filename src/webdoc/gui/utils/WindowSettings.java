/*******************************************************************************
 * Copyright (c) 2015 by Aron Heinecke & Jonathan Peper
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui.utils;

import java.awt.Rectangle;

public class WindowSettings {
	private Rectangle bounds;
	//private HashMap<String,Integer> additionalSettings;
	public WindowSettings(Rectangle bounds){
		this.bounds = bounds;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
}
