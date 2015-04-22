package webdoc.lib;

import java.awt.Component;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GUI {
	
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
}
