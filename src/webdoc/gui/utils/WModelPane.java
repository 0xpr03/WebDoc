/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui.utils;

import java.awt.Rectangle;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.JProgressBar;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.lib.GUIManager;

/**
 * Model Pane, basic InternalFrame for all other frames.<br>
 * Providing GlassPane,and other lib methods
 * @author "Aron Heinecke"
 */
public class WModelPane extends JInternalFrame {
	private DisabledGlassPane glassPane = new DisabledGlassPane();
	private JProgressBar progressBar = new JProgressBar();
	private final int PRECIZISION = 100000000;
	Logger logger = LogManager.getLogger();
	private long id;
	
	private static final long serialVersionUID = -5487882455493200455L;
	
	public WModelPane(long id){
		this.id = id;
		logger.debug("ID: {}",id);
		setGlassPane(glassPane);
		progressBar.setIndeterminate(true);
		glassPane.add(progressBar);
		addInternalFrameListener(new InternalFrameAdapter() { // dispose fix #66
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
	
	private boolean checkDesktopPane(){
		if(getDesktopPane() == null){
			logger.fatal("No desktop pane found! Error on resizing.");
			return false;
		}
		return true;
	}
	
	/**
	 * Loads and sets the current configuration from the GUIManager
	 */
	private void loadConfiguration(){
		if(checkDesktopPane()){
			LogManager.getLogger().debug("id: {}",id);
			if(GUIManager.settingsDB.containsKey(id)){
				WindowSettings ws = GUIManager.settingsDB.get(id);
				this.setBounds(getTotalBounds(ws.getBounds()));
			}else{
				GUIManager.settingsDB.put(id, new WindowSettings(getPercBounds(this.getBounds())));
			}
		}
	}
	
	/**
	 * Calculates the percentage location bounds based on the max & the current value
	 * @param max
	 * @param values
	 * @return Rectangle
	 */
	private Rectangle getPercBounds(Rectangle values){
		logger.debug("maxX {} valX {} = {}",getDesktopPane().getWidth(),values.getX(),values.getX() / getDesktopPane().getWidth()*PRECIZISION );
		Rectangle rect = new Rectangle();
		rect.setRect(values.getX() / getDesktopPane().getWidth() *PRECIZISION, values.getY() / getDesktopPane().getHeight() * PRECIZISION, values.getWidth(), values.getHeight());
		logger.debug("valX {}",rect.getX());
		return rect;
	}
	
	/**
	 * Retrive the real y,x values based on the max & percentage values
	 * @param max
	 * @param values
	 * @return
	 */
	private Rectangle getTotalBounds(Rectangle values){
//		logger.debug("maxX: {}",this.getDesktopPane().getHeight());
		logger.debug("maxX {} valX {} = {}",getDesktopPane().getWidth(),values.getX(),getDesktopPane().getWidth() * values.getX() / PRECIZISION );
		values.setRect(getDesktopPane().getWidth() * values.getX() / PRECIZISION, getDesktopPane().getHeight() * values.getY() / PRECIZISION, values.getWidth(), values.getHeight());
		return values;
	}
	
	/**
	 * Saves the configuration back to the GUIManager
	 */
	private void saveConfiguration(){
		if(checkDesktopPane()){
			LogManager.getLogger().debug("id: {}",id);
			if(!this.isMaximum()){
				try {
					this.setMaximum(false);
				} catch (PropertyVetoException e) {
					LogManager.getLogger().error(e);
				}
			}
			GUIManager.settingsDB.get(id).setBounds(getPercBounds(this.getBounds()));
		}
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