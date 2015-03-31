package webdoc.gui;

import java.awt.Component;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

public class GUIMethoden {
	/*Erstellen den Menuebaum*/ 
	public static TreeModel Navi() {
		TreeNode root = createTree();
		JTree tree = new JTree(root);
		return tree.getModel();
	}

	private static TreeNode createTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode( "WebDoc" );
		 
        DefaultMutableTreeNode patientenundpartner = new DefaultMutableTreeNode( "Patienten und Partner" );
        DefaultMutableTreeNode behandlung = new DefaultMutableTreeNode( "Behandlung" );
        DefaultMutableTreeNode main = new DefaultMutableTreeNode("Hauptmenü");
 
        DefaultMutableTreeNode neuerPatient = new DefaultMutableTreeNode( "Neuer Patient" );
        DefaultMutableTreeNode neuerPartner = new DefaultMutableTreeNode( "Neuer Partner" );
 
        DefaultMutableTreeNode neueBehandlung = new DefaultMutableTreeNode( "Neue Behandlung" );
        
        patientenundpartner.add( neuerPatient );
        patientenundpartner.add( neuerPartner );
 
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
