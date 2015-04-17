package webdoc.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import webdoc.gui.CustomTreeObj.EntryType;

public class GUIMethoden {
	/*Erstellen den Menuebaum*/ 
	public static TreeModel Navi() {
		TreeNode root = createTree();
		JTree tree = new JTree(root);
		return tree.getModel();
	}

	private static TreeNode createTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new CustomTreeObj( "WebDoc",EntryType.ZWEIG) );
		
        DefaultMutableTreeNode patientenundpartner = new DefaultMutableTreeNode(new CustomTreeObj( "Patienten und Partner",EntryType.ZWEIG) );
        DefaultMutableTreeNode behandlung = new DefaultMutableTreeNode(new CustomTreeObj( "Behandlung",EntryType.ZWEIG) );
        DefaultMutableTreeNode main = new DefaultMutableTreeNode(new CustomTreeObj("Hauptmenü",EntryType.HAUPTMENÜ));
 
        DefaultMutableTreeNode neuerPatient = new DefaultMutableTreeNode(new CustomTreeObj( "Neuer Patient",EntryType.N_PATIENT) );
        DefaultMutableTreeNode neuerPartner = new DefaultMutableTreeNode(new CustomTreeObj( "Neuer Partner",EntryType.N_PARTNER) );
        DefaultMutableTreeNode patient = new DefaultMutableTreeNode(new CustomTreeObj( "Patient",EntryType.PATIENT) );
        DefaultMutableTreeNode partner = new DefaultMutableTreeNode(new CustomTreeObj( "Partner",EntryType.PARTNER) );
 
        DefaultMutableTreeNode neueBehandlung = new DefaultMutableTreeNode( new CustomTreeObj("Neue Behandlung",EntryType.N_BEHANDLUNG) );
        
        patientenundpartner.add( neuerPatient );
        patientenundpartner.add( neuerPartner );
        patientenundpartner.add(patient);
        patientenundpartner.add(partner);
 
        behandlung.add( neueBehandlung );
 
        root.add(main);
        root.add( patientenundpartner );
        root.add( behandlung );
		
        return root;
	}
	/*Erstellt das Dropdown Menu */
	public static JMenu menus(JMenuBar menuBar) {
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenuItem hilfe = new JMenuItem("Hilfe");
		JMenuItem close = new JMenuItem("Close");
		
		menu.add(hilfe);
		menu.add(close);
		
		return menu;
	}

}