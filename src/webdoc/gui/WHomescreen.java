package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.utils.ACElement;
import webdoc.gui.utils.ACElement.ElementType;
import webdoc.gui.utils.CustomTreeObj;
import webdoc.gui.utils.JSearchTextField;
import webdoc.gui.utils.JSearchTextField.searchFieldAPI;
import webdoc.lib.Database;
import webdoc.lib.GUI;

/**
 * Mainframe of the GUI
 * @author "Aron Heinecke"
 */
public class WHomescreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4091113544481728677L;
	private JSearchTextField txtSuche;
	private WNeuerPartner FNeuerPartner = new WNeuerPartner(true);
	private WNeuerPartner FPartner = new WNeuerPartner(false);
	private WNeuerPatient FNeuerPatient = new WNeuerPatient(true);
	private WPatient FPatient = new WPatient(false);
	private JTree navigationsbaum;
	private Logger logger = LogManager.getLogger();
	private JDesktopPane desktopPane;
	private PreparedStatement searchStm = null;
	private test test = new test();

	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WHomescreen window = new WHomescreen();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WHomescreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("WebDoc Home");
		setBounds(100, 100, 1006, 692);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(GUIMethoden.menus(menuBar));
		JPanel navigation = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(navigation, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
							.addGap(4)))
					.addGap(9))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(navigation, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 621, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
							.addGap(11)))
					.addGap(0))
		);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		desktopPane = new JDesktopPane();
		desktopPane.add(FNeuerPartner);
		desktopPane.add(FPartner);
		desktopPane.add(FPatient);
		desktopPane.add(FNeuerPatient);
		desktopPane.add(test);
		desktopPane.setBackground(Color.WHITE);
		panel_1.add(desktopPane, BorderLayout.CENTER);
		
		txtSuche = new JSearchTextField();
		//TODO: add placeholder function
		//txtSuche.setText("Suche"); WONT WORK ATM
		txtSuche.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtSuche, GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtSuche, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		navigation.setLayout(new BorderLayout(0, 0));
		
		navigationsbaum = new JTree();
		navigationsbaum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickAction(arg0);
			}
		});
		navigationsbaum.setModel(GUIMethoden.Navi());
		navigation.add(navigationsbaum, BorderLayout.CENTER);
		getContentPane().setLayout(groupLayout);
		
		/**
		 * Default DataProvider for these kinds
		 * @author "Aron Heinecke"
		 */
		class Provider implements searchFieldAPI{
			@Override
			public List<ACElement> getData(String text){
				List<ACElement> list = new ArrayList<ACElement>();
				try {
					text = "%"+text+"%";
					searchStm.setString(1, text);
					searchStm.setString(2, text);
					searchStm.setString(3, text);
					searchStm.setString(4, text);
					ResultSet result = searchStm.executeQuery();
					
					while(result.next()){
						list.add(new ACElement(result.getString(1),result.getString(2),result.getLong(3), ElementType.ANIMAL));
					}
					result.close();
					
				} catch (SQLException e) {
					GUI.showDBErrorDialog(null, Database.DBExceptionConverter(e,true));
				}
				return list;
			}

			@Override
			public void changedSelectionEvent(ACElement element) {
				logger.debug("Element chosen: {}",element);
			}
		}
		
		try {
			searchStm = Database.prepareMultiSearchStm();
		} catch (SQLException e) {
			GUI.showDBErrorDialog(this, Database.DBExceptionConverter(e,true));
		}
		
		txtSuche.setAPI(new Provider());
	}
	
	private void mouseClickAction(MouseEvent mevent){
		int row = navigationsbaum.getRowForLocation(mevent.getX(), mevent.getY());
		TreePath selPath = navigationsbaum.getPathForLocation(mevent.getX(), mevent.getY());
		if(row != -1){
			// detect doubleklick
			if(mevent.getClickCount() >= 2){
				CustomTreeObj selMenu = (CustomTreeObj) ((DefaultMutableTreeNode) selPath.getLastPathComponent()).getUserObject();
				logger.debug("selected: {}", selMenu.getType());
				switch(selMenu.getType()){
				case HAUPTMENÜ:
					//TODO: show main menu					
					break;
				case N_PATIENT:
					reOpen(FNeuerPatient);
					break;
				case N_PARTNER:
					reOpen(FNeuerPartner);
					break;
				case PARTNER:
					reOpen(FPartner);
					break;
				case PATIENT:
					reOpen(FPatient);
					break;
				case TEST:
					reOpen(test);
					break;
				case N_BEHANDLUNG:
					//TODO: show new behandlung
					break;
				default:
					
				}
			}
		}
	}
	
	private void reOpen(JInternalFrame jif){
		if(jif.isClosed()){
			logger.debug("JIF ist closed.");
			desktopPane.remove(jif);
			desktopPane.add(jif);
			jif.setVisible(true);
		}else if(!jif.isVisible()){
			jif.setVisible(true);
		}else if(jif.isIcon()){
			try {
				jif.setIcon(false);
			} catch (PropertyVetoException e) {
				logger.debug(e);
			};
		}else{
			jif.toFront();
		}
	}
}

