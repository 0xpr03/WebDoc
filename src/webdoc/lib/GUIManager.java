package webdoc.lib;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.WHomescreen;
import webdoc.lib.Database.DBError;

/**
 * GUI Manager, providing basic dialogs and InternalFrame handling for the mainscreen
 * @author "Aron Heinecke"
 *
 */
public class GUIManager {
	
	private static int framepos = -1;
	private static HashMap<Integer, JInternalFrame> framemap = new HashMap<Integer, JInternalFrame>();
	private static WHomescreen whomescreen = null;
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * initiates and shows the homescreen window
	 */
	public static void crateHomescreen(){
		whomescreen = new WHomescreen();
		whomescreen.run();
	}
	
	/**
	 * returns the current framepos which is used as an iterator
	 * @return
	 */
	public static int getFramepos(){
		return framepos;
	}
	
	/**
	 * adds an iframe
	 * @param jif
	 */
	public static int addIFrame(int itpos, JInternalFrame jif){
		logger.debug("Adding: "+itpos);
		jif.setVisible(true);
		whomescreen.addJIF(jif);
		jif.setVisible(true);
		framemap.put(itpos, jif);
		framepos++;
		return itpos;
	}
	
	/**
	 * calls the destructor for an internalframe, preventing #22
	 * @param itpos
	 */
	public static void removeIFrame(int itpos){
		whomescreen.removeJIF(framemap.get(itpos));
		framemap.remove(itpos);
	}
	
	public static void callWNewAnamnesis(){
		whomescreen.callWNewAnamnesis();
	}
	
	public static int getNewFrameId(){
		return getFramepos()+1;
	}
	
	/**
	 * Shows a generic error dialog with a yes & no button
	 * @param parent parent window
	 * @param message message to display
	 * @param title title for the dialog
	 * @return returns 0 for okay, 1 for no / window close
	 */
	public static int showErrorYesNoDialog(Component parent, String message, String title){
		int i = JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		return i;
	}
	/**
	 * Shows a generic error dialog with a yes & no button
	 * @param message message to display
	 * @param title title for the dialog
	 * @return returns 0 for okay, 1 for no / window close
	 */
	public static int showErrorYesNoDialog(String message, String title){
		int i = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		return i;
	}
	
	/**
	 * Shows an generic error dialog
	 * @param message message to display
	 * @param title title for the dialog
	 */
	public static void showErrorDialog(String message, String title){
		JOptionPane.showConfirmDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Shows an generic error dialog
	 * @param parent parent window
	 * @param message message to display
	 * @param title title for the dialog
	 */
	public static void showErrorDialog(Component parent, String message, String title){
		JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Predefined error dialog for DB errors
	 * @param parent
	 * @param dberr
	 */
	public static void showDBErrorDialog(Component parent, DBError dberr){
		JOptionPane.showConfirmDialog(parent, "The following eroror occured: "+dberr, "DB Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
	}
}
