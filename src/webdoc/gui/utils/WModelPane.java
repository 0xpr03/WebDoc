/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui.utils;

import javax.swing.JInternalFrame;
import javax.swing.JProgressBar;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import webdoc.lib.GUIManager;

/**
 * Model Pane, basic InternalFrame for all other frames.<br>
 * Providing GlassPane,and other lib methods
 * @author "Aron Heinecke"
 */
public class WModelPane extends JInternalFrame {
	private DisabledGlassPane glassPane = new DisabledGlassPane();
	private JProgressBar progressBar = new JProgressBar();
	private long id;
	
	private static final long serialVersionUID = -5487882455493200455L;
	
	public WModelPane(long id){
		this.id = id;
		setGlassPane(glassPane);
		progressBar.setIndeterminate(true);
		glassPane.add(progressBar);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent arg0) {
				dispose();
			}
		});
	}

	/**
	 * Change GlassPane visibility
	 * @param enabled
	 * @param catchFocus check if the focus should be moved onto the window afterwards
	 */
	protected void setGlassPaneVisible(boolean enabled, boolean catchFocus){
		if(enabled){
			glassPane.activate(null);
			if(catchFocus)
				this.requestFocusInWindow();
		}else {
			glassPane.deactivate();
		}
		progressBar.setVisible(enabled);
	}
	
	/**
	 * Loads and sets the current configuration from the GUIManager
	 */
	private void loadConfiguration(){
		if(GUIManager.settingsDB.containsKey(id)){
			WindowSettings ws = GUIManager.settingsDB.get(id);
			this.setBounds(ws.getBounds());
		}else{
			GUIManager.settingsDB.put(id, new WindowSettings(this.getBounds()));
		}
	}
	
	/**
	 * Saves the configuration back to the GUIManager
	 */
	private void saveConfiguration(){
		GUIManager.settingsDB.get(id).setBounds(this.getBounds());
	}
	
	@Override
	public void setVisible(boolean b){
		if(b)
			loadConfiguration();
		super.setVisible(b);
	}
	
	@Override
	public void dispose(){
		saveConfiguration();
		super.dispose();
	}
	
	/**
	 * Change GlassPane visibility
	 * @param enabled
	 */
	protected void setGlassPaneVisible(boolean enabled){
		setGlassPaneVisible(enabled,false);
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
	 * Check if valid double
	 * @param s
	 * @return
	 */
	public boolean invalidDouble(String s){
		try{
			Double.parseDouble(s);
			return false;
		}catch(NumberFormatException e){
			return true;
		}
	}
	
	/**
	 * Check if valid int
	 * @param s
	 * @return
	 */
	public boolean invalidInt(String s){
		try{
			Integer.parseInt(s);
			return false;
		}catch(NumberFormatException e){
			return true;
		}
	}

}