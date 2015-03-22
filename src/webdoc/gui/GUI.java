package webdoc.gui;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GUI {
	
	private static Logger logger = LogManager.getLogger();
	
	public static int showErrorYesNoDialog(String message, String title){
		logger.entry();
		int i = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
		logger.debug("Return value {}",i);
		return i;
	}
}
