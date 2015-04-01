package webdoc.gui;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;

public class Haupfenster extends GUIMethoden{

	private JFrame frmWebdoc;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Haupfenster window = new Haupfenster();
					window.frmWebdoc.setExtendedState(JFrame.MAXIMIZED_BOTH);
					window.frmWebdoc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Haupfenster() {
		initialize();
	}

	/**
	 *  JFrame frame = new JFrame();
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setVisible(true);
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWebdoc = new JFrame();
		frmWebdoc.setTitle("WebDoc");
		frmWebdoc.setBounds(100, 100, 882, 592);
		frmWebdoc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmWebdoc.setJMenuBar(menuBar);
		menuBar.add(GUIMethoden.menus(menuBar));
		
		JPanel navigation = new JPanel();
		
		JTree tree = new JTree();
		tree.setModel(GUIMethoden.Navi());
		GroupLayout gl_panel = new GroupLayout(navigation);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(4)
					.addComponent(tree, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(tree, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
		);
		navigation.setLayout(gl_panel);
		
		JPanel suche = new JPanel();
		
		textField = new JTextField();
		textField.setText("Suche");
		textField.setColumns(10);
		GroupLayout gl_suche = new GroupLayout(suche);
		gl_suche.setHorizontalGroup(
			gl_suche.createParallelGroup(Alignment.LEADING)
				.addGap(0, 846, Short.MAX_VALUE)
				.addGroup(gl_suche.createSequentialGroup()
					.addGap(119)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
					.addGap(129))
		);
		gl_suche.setVerticalGroup(
			gl_suche.createParallelGroup(Alignment.LEADING)
				.addGap(0, 20, Short.MAX_VALUE)
				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		suche.setLayout(gl_suche);
		
	}
}
