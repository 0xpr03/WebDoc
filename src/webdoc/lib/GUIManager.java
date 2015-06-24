/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.lib;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.WHomescreen;
import webdoc.gui.WNeuerPatient;
import webdoc.gui.WProgress;
import webdoc.lib.Database.DBError;

/**
 * GUI Manager, providing basic dialogs and InternalFrame handling for the mainscreen
 * @author "Aron Heinecke"
 *
 */
public final class GUIManager {
	
	private static WHomescreen whomescreen = null;
	private static Logger logger = LogManager.getLogger();
	private static Font font = new Font(Font.SANS_SERIF, Font.PLAIN,14);
	private static Font search_font = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
	private static Font search_font_default = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	
	/**
	 * initiates and shows the homescreen window
	 * @author "Aron Heinecke"
	 */
	public static void crateHomescreen(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					whomescreen = new WHomescreen();
				} catch (Exception e) {
					logger.error("Homescreen thread error \n{}", e);
				}
			}
		});
	}
	
	/**
	 * adds an iframe
	 */
	public static synchronized void addWNeuerPatient(boolean editable, long id){
		whomescreen.addWNeuerPatient(editable, id);
	}
	
	/**
	 * Provides a memory leak & performance test
	 * @author "Aron Heinecke"
	 */
	public static void closeMemoryTest(){
		if(GUIManager.showErrorYesNoDialog(whomescreen, "Do you really want to run this test ?\nYou won't be able to use the PC for the time of this test!", "MP Test") == 0){
			final WProgress wpg = new WProgress();
			wpg.setAlwaysOnTop(true);
			wpg.setTitle("MP Test");
			wpg.setText("Running memory-performance test with InternalFrames.\nThis DOES cause high cpu & memory load.");
			wpg.setMax(2);
			final int max = 50;
			wpg.setSubMax(max);
			wpg.setVisible(true);
			
//			final int processors = Runtime.getRuntime().availableProcessors();
//			wpg.setSubText(String.format("Allocating using %s threads..", processors));
//			ExecutorService taskExecutor = Executors.newFixedThreadPool(processors);
//			taskExecutor.execute(new Runnable() {
//				public void run() {
			wpg.setSubText(String.format("Allocating %s times..", max));
			for(int i=0; i <= max; i++){
				addWNeuerPatient(false, -1);
				wpg.addSubProgress();
			}
//				}
//			});
//			taskExecutor.shutdown();
//			try {
//				taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//			} catch (InterruptedException e) {
//				logger.debug(e);
//			}
			
			wpg.setSubMax(max);
			int jifs_amount = whomescreen.getJIFs().length;
			wpg.setSubText(String.format("Freeing %s found entrys..", jifs_amount));
			int removed = 0;
			for(JInternalFrame jif : whomescreen.getJIFs()){
				if(jif instanceof WNeuerPatient){
					removed++;
					((WNeuerPatient)jif).dispose();
				}else{
					logger.debug(jif);
				}
				wpg.addSubProgress();
			}
			wpg.setSubText(String.format("Freed %s/%s",removed, jifs_amount));
			System.gc();
			
			try {
				Thread.sleep(5000);
			} catch (Exception e) {/* ignore */
			}
			
			wpg.dispose();
		}
	}
	
	public static Font getCommentFont(){
		return font;
	}
	
	public static Font getSearchDefaultFont(){
		return search_font_default;
	}
	
	public static Font getSearchMainFont(){
		return search_font;
	}
	
	/**
	 * calls the destructor for an internalframe, preventing #22
	 * @param itpos
	 */
	public static void dropJID(JInternalFrame jif){
		whomescreen.removeJIF(jif);
		System.gc();
	}
	
	/**
	 * Calls WNeueBehandlung
	 */
	public static void callWNeueBehandlung(long animal_id, long pet_threatment_id){
		whomescreen.addWNeueBehandlung(animal_id, pet_threatment_id);
	}
	
	/**
	 * Calls WNeueAnamnesis
	 */
	public static void callWNewAnamnesis(boolean editable, long animal_id, long anamnesis_id, String name,String callname){
		whomescreen.addWNewAnamnesis(editable, animal_id, anamnesis_id, name,callname);
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
		return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Shows an YesNo dialog of definable type
	 * @param parent parent of the dialog
	 * @param message message to display
	 * @param title title for the dialog
	 * @param type JOptionPane constant for type
	 * @return returns 0 for okay, 1 for no / window close
	 */
	public static int showYesNoDialog(Component parent, String message,int type, String title){
		return JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_OPTION, type);
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
		JOptionPane.showConfirmDialog(parent, "The following error occured: "+dberr, "DB Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Predefined allSet() error dialog
	 * @param parent
	 */
	public static void showFieldErrorDialog(Component parent){
		JOptionPane.showConfirmDialog(parent, "Es sind nicht alle Felder richtig ausgefüllt!", "Fehlende Angaben",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
	}
}
