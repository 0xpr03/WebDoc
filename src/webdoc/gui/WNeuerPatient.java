package webdoc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;

import webdoc.gui.GUIMethoden;

public class WNeuerPatient {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WNeuerPatient window = new WNeuerPatient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WNeuerPatient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 882, 592);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.add(GUIMethoden.menus(menuBar));
		
		JPanel navigation = new JPanel();
		
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
				.addGap(0, 35, Short.MAX_VALUE)
				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		suche.setLayout(gl_suche);
		
		JPanel daten = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(suche, GroupLayout.PREFERRED_SIZE, 846, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(navigation, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(daten, GroupLayout.PREFERRED_SIZE, 684, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(suche, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(navigation, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
						.addComponent(daten, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		JTree navigationsbaum = new JTree();
		navigationsbaum.setModel(GUIMethoden.Navi());
		GroupLayout gl_navigation = new GroupLayout(navigation);
		gl_navigation.setHorizontalGroup(
			gl_navigation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_navigation.createSequentialGroup()
					.addGap(4)
					.addComponent(navigationsbaum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_navigation.setVerticalGroup(
			gl_navigation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_navigation.createSequentialGroup()
					.addGap(5)
					.addComponent(navigationsbaum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		navigation.setLayout(gl_navigation);
		frame.getContentPane().setLayout(groupLayout);
	}

}
