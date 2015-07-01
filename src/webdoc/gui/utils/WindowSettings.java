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
