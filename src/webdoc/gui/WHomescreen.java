package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
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
import webdoc.lib.GUIManager;

/**
 * Mainframe of the GUI
 * @author "Aron Heinecke"
 */
public final class WHomescreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4091113544481728677L;
	private JSearchTextField txtSuche;
	private WNeuerPartner FNeuerPartner = new WNeuerPartner(true, -1);
	private WNeuerPatient FNeuerPatient = new WNeuerPatient(true, -1);
	private WNeueAnamnese FNeueAnamnese = new WNeueAnamnese(true,null);
	private JTree navigationsbaum;
	private Logger logger = LogManager.getLogger();
	private JDesktopPane desktopPane;
	private PreparedStatement searchStm = null;
	private test test = new test();

	/**
	 * Launch the application.
	 */
//	public void main() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					WHomescreen window = new WHomescreen();
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public WHomescreen() {
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("WebDoc Home");
		setBounds(100, 100, 1936, 980);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(Color.WHITE);
		
		JPanel navigation = new JPanel();
		splitPane.setLeftComponent(navigation);
		
		JPanel secPanel = new JPanel();
		splitPane.setRightComponent(secPanel);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 1919, Short.MAX_VALUE)
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0)
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 909, Short.MAX_VALUE)
					.addGap(0)
					.addGap(0))
		);
		
		JPanel suchPanel = new JPanel();
		
		txtSuche = new JSearchTextField();
		//TODO: add placeholder function
		//txtSuche.setText("Suche"); WONT WORK ATM
		txtSuche.setColumns(10);
		GroupLayout gl_suchPanel = new GroupLayout(suchPanel);
		gl_suchPanel.setHorizontalGroup(
			gl_suchPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_suchPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtSuche, GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_suchPanel.setVerticalGroup(
			gl_suchPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_suchPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtSuche, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		suchPanel.setLayout(gl_suchPanel);
		
		JPanel desktopPanel = new JPanel();
		desktopPanel.setLayout(new BorderLayout(0, 0));
		
		desktopPane = new JDesktopPane();
		desktopPane.add(FNeuerPartner);
		desktopPane.add(FNeuerPatient);
		desktopPane.add(test);
		desktopPane.setBackground(Color.WHITE);
		desktopPanel.add(desktopPane, BorderLayout.CENTER);
		GroupLayout gl_secPanel = new GroupLayout(secPanel);
		gl_secPanel.setHorizontalGroup(
			gl_secPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_secPanel.createSequentialGroup()
					.addGap(1)
					.addComponent(suchPanel, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addGap(1))
				.addGroup(Alignment.LEADING, gl_secPanel.createSequentialGroup()
					.addGap(0)
					.addComponent(desktopPanel, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_secPanel.setVerticalGroup(
			gl_secPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secPanel.createSequentialGroup()
					.addComponent(suchPanel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(0)
					.addComponent(desktopPanel, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
					.addGap(0))
		);
		secPanel.setLayout(gl_secPanel);
		navigation.setLayout(new BorderLayout(0, 0));
		
		navigationsbaum = new JTree();
		navigationsbaum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickAction(arg0);
			}
		});
		navigationsbaum.setModel(GUIFunctions.Navi());
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
						list.add(new ACElement(result.getString(1),result.getString(2),result.getLong(3), result.getInt(4) == 0 ? ElementType.ANIMAL : ElementType.PARTNER));
					}
					result.close();
					
				} catch (SQLException e) {
					GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e,true));
				}
				return list;
			}

			@Override
			public boolean changedSelectionEvent(ACElement element) {
				logger.debug("Element chosen: {}",element);
				if(element.getType() == ElementType.ANIMAL){
					//TODO GUIManager.addIFrame(GUIManager.getFramepos()+1, new WPatient(false, element.getID(), GUIManager.getFramepos()+1));
				}else{
					logger.debug("atm unsupported");
				}
				txtSuche.setText("");
				return true;
			}

			@Override
			public String listRenderer(ACElement element) {
				return "<html>" + element.getName() + " " + element.getOptname() + " <font size=-5><i>"
						+ element.getType() + "</i></font></html>";
			}
		}
		
		txtSuche.setAPI(new Provider());
		
		try {
			searchStm = Database.prepareMultiSearchStm();
		} catch (SQLException e) {
			GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e,true));
		}
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnMenu.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new WAbout();
			}
		});
		
		JMenuItem mntmPerformanceTest = new JMenuItem("Performance test");
		mntmPerformanceTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						Thread t = new Thread(new Runnable() {
							public void run() {
								GUIManager.closeMemoryTest();
							}
						});
						t.start();
					}
				});
			}
		});
		
		JMenuItem mntmFreeMemory = new JMenuItem("Free memory");
		mntmFreeMemory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remAllDebug();
			}
		});
		mnHelp.add(mntmFreeMemory);
		mnHelp.add(mntmPerformanceTest);
		
		JSeparator separator = new JSeparator();
		mnHelp.add(separator);
		mnHelp.add(mntmAbout);
	}
	
	private void mouseClickAction(MouseEvent mevent){
		int row = navigationsbaum.getRowForLocation(mevent.getX(), mevent.getY());
		TreePath selPath = navigationsbaum.getPathForLocation(mevent.getX(), mevent.getY());
		if(row != -1){
			// detect doubleklick
			if(mevent.getClickCount() >= 2){
				CustomTreeObj selMenu = (CustomTreeObj) ((DefaultMutableTreeNode) selPath.getLastPathComponent()).getUserObject();
				logger.debug("menu: {}", selMenu.getType());
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
					//TODO GUIManager.addIFrame(GUIManager.getNewFrameId(),  new WNeuerPartner(false, -1));
					break;
				case PATIENT:
					addWindow(new WNeuerPatient(false, -1));
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
		if(jif == null){
			return;
		}
		if(jif.isClosed()){
			logger.debug("JIF = closed");
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
	
	private void remAllDebug(){
		for(JInternalFrame cmp: desktopPane.getAllFrames()){
			cmp.dispose();
		}
		
	}
	
	/**
	 * Creates a WNeuerPatient window
	 * @param editable
	 * @param id
	 */
	public void addWNeuerPatient(boolean editable, long id){
		WNeuerPatient wnp = new WNeuerPatient(editable, id);
		wnp.setVisible(true); // !creating,visible & add order IS important!
		desktopPane.add(wnp);
	}
	
	/**
	 * Creates a WNeuerPartner window
	 * @param editable
	 * @param id
	 */
	public void addWNeuerPartner(boolean editable, long id){
		WNeuerPartner wnp = new WNeuerPartner(editable, id);
		desktopPane.add(wnp);
		wnp.setVisible(true);
	}
	
	public void addWindow(JInternalFrame jif){
		desktopPane.add(jif);
		jif.setVisible(true);
	}
	
	/**
	 * returns the stored internalframes of the desktop pane
	 * @return
	 */
	public JInternalFrame[] getJIFs(){
		return desktopPane.getAllFrames();
	}
	
	/**
	 * removes the JInternalFrame from the desktopPane
	 * @param jif
	 */
	public void removeJIF(JInternalFrame jif){
		desktopPane.remove(jif);
	}
	
	/**
	 * adds a JInternalFrame to the desktopPane
	 * @param jif
	 */
	public void addJIF(JInternalFrame jif){
		desktopPane.add(jif);
	}
	
	public void callWNewAnamnesis(){
		reOpen(FNeueAnamnese);
	}
}
