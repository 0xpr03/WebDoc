/*******************************************************************************
 * Copyright (c) 2015 by Aron Heinecke & Jonathan Peper
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.Component;
import java.awt.Dialog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.apache.logging.log4j.LogManager;

import webdoc.gui.utils.CustomTreeObj;
import webdoc.gui.utils.CustomTreeObj.EntryType;
import webdoc.lib.GUIManager;
import webdoc.webdoc.Config;

public class GUIFunctions {
	private static Calendar calendar = Calendar.getInstance();
	
	public static void initFunctions(){
		calendar.setTimeInMillis(0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	}
	
	/*Erstellen den Menuebaum*/ 
	public static TreeModel Navi() {
		TreeNode root = createTree();
		JTree tree = new JTree(root);
		return tree.getModel();
	}
	
	/**
	 * Return default date for spinners
	 * @return
	 */
	public static Date getDefaultDate(){
		return calendar.getTime();
	}
	
	/**
	 * Compare JSpinner value date to default date
	 * @param object
	 * @return boolean true when value matches default date
	 * @author "Aron Heinecke"
	 */
	public static boolean compareSpinnerDates(Object object){
		return getDefaultDate().compareTo((Date) object) == 0;
	}

	private static TreeNode createTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new CustomTreeObj( "WebDoc",EntryType.ZWEIG) );
		
        DefaultMutableTreeNode patientenundpartner = new DefaultMutableTreeNode(new CustomTreeObj( "Patienten und Partner",EntryType.ZWEIG) );
        DefaultMutableTreeNode behandlung = new DefaultMutableTreeNode(new CustomTreeObj( "Behandlung",EntryType.ZWEIG) );
        DefaultMutableTreeNode main = new DefaultMutableTreeNode(new CustomTreeObj("Hauptmenü",EntryType.HAUPTMENÜ));
 
        DefaultMutableTreeNode neuerPatient = new DefaultMutableTreeNode(new CustomTreeObj( "Neuer Patient",EntryType.N_PATIENT) );
        DefaultMutableTreeNode neueBehandlungsart = new DefaultMutableTreeNode(new CustomTreeObj( "Neue Behandlungsart",EntryType.N_BEHANDLUNGSART) );
        DefaultMutableTreeNode neuerPartner = new DefaultMutableTreeNode(new CustomTreeObj( "Neuer Partner",EntryType.N_PARTNER) );
        DefaultMutableTreeNode patient = new DefaultMutableTreeNode(new CustomTreeObj( "Patient",EntryType.PATIENT) );
        DefaultMutableTreeNode verwaltung = new DefaultMutableTreeNode(new CustomTreeObj( "Verwaltung",EntryType.VERWALTUNG) );
        
        patientenundpartner.add( neuerPatient );
        patientenundpartner.add( neuerPartner );
        patientenundpartner.add(patient);
 
        behandlung.add(neueBehandlungsart);
 
        root.add(main);
        root.add( patientenundpartner );
        root.add( behandlung );
        root.add(verwaltung);
		
        return root;
	}
	/*Erstellt das Dropdown Menu */
	public static JMenu menus(JMenuBar menuBar) {
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenuItem hilfe = new JMenuItem("Hilfe");
		JMenuItem close = new JMenuItem("Exit");
		
		menu.add(hilfe);
		menu.addSeparator();
		menu.add(close);
		
		return menu;
	}
	
	/**
	 * Shows a default "ignore changes" dialog
	 * @param parent
	 * @return 1 for okay, 0 for abort
	 */
	public static int showIgnoreChangesDialog(Component parent){
		return GUIManager.showYesNoDialog(parent, "Änderungen verwerfen ?", JOptionPane.WARNING_MESSAGE, "Ungespeicherte Änderungen");
	}
	
	public static <U extends Dialog > String getLicense(U parent) {
		try {
			InputStream is = GUIFunctions.class.getResourceAsStream(Config.getStrValue("licenseFilePath"));
		    InputStreamReader isr = new InputStreamReader(is, "UTF8");
			BufferedReader br = new BufferedReader(isr);
			
			StringBuilder sb = new StringBuilder();
			
			for(String line = br.readLine(); line != null;line=br.readLine()){
				sb.append(line+"\n");
			}
			return sb.toString();
		}catch(IOException | NullPointerException e){
			LogManager.getLogger().fatal("Error loading license!\n{}",e);
			GUIManager.showErrorDialog(parent, "Unable to load license!\nAborting..", "Error: License Loading");
			System.exit(404);
			return null;
		}
	}
}