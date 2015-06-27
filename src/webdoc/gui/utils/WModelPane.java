/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui.utils;

import javax.swing.JInternalFrame;
import javax.swing.JProgressBar;

public class WModelPane extends JInternalFrame {
	private DisabledGlassPane glassPane = new DisabledGlassPane();
	private JProgressBar progressBar = new JProgressBar();
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5487882455493200455L;
	
	public WModelPane(){
		setGlassPane(glassPane);
		progressBar.setIndeterminate(true);
		glassPane.add(progressBar);
	}

	protected void setGlassPaneVisible(boolean enabled){
		if(enabled){
			glassPane.activate(null);
		}else {
			glassPane.deactivate();
		}
		progressBar.setVisible(enabled);
		if(!enabled)
			this.requestFocusInWindow();
	}
	
	/**
	 * simple instance provider for events
	 * 
	 * @return
	 */
	protected WModelPane getFrame() {
		return this;
	}

	/**
	 * 
	 */
	public boolean invalidDouble(String s){
		try{
			Double.parseDouble(s);
			return false;
		}catch(NumberFormatException e){
			return true;
		}
	}
	
	public boolean invalidInt(String s){
		try{
			Integer.parseInt(s);
			return false;
		}catch(NumberFormatException e){
			return true;
		}
	}

}