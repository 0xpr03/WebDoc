package webdoc.gui;

import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JInternalFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.utils.ACElement;
import webdoc.gui.utils.ACElement.ElementType;
import webdoc.gui.utils.JSearchTextField;
import webdoc.gui.utils.JSearchTextField.DataProvider;
import webdoc.lib.Database;
import webdoc.lib.GUI;

/**
 * Test class for JUnit tests like JSearchTextField
 * @author "Aron Heinecke"
 *
 */
public class test extends JInternalFrame {

	private static final long serialVersionUID = -8772029628747927216L;
	private JSearchTextField autoCompleteTextField;
	private PreparedStatement searchStm = null;
	private Logger logger = LogManager.getLogger();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
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
	public test() {
		setClosable(true);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Testframe");
		setBounds(100, 100, 242, 217);
		
		autoCompleteTextField = new JSearchTextField();
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(autoCompleteTextField, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(219, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(autoCompleteTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(231, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

		class Provider implements DataProvider{
			@Override
			public List<ACElement> getData(String text){
				List<ACElement> list = new ArrayList<ACElement>();
				try {
					searchStm.setString(1, "%"+text+"%");
					ResultSet result = searchStm.executeQuery();
					
					while(result.next()){
						logger.debug("Found: {}", result.getString(1));
						list.add(new ACElement(result.getString(1)+" "+result.getString(2),result.getLong(3), ElementType.ANIMAL));
					}
					result.close();
					
				} catch (SQLException e) {
					GUI.showDBErrorDialog(null, Database.DBExceptionConverter(e,true));
				}
				return list;
			}
		}
		autoCompleteTextField.setDataProvider(new Provider());
		
		try {
			searchStm = Database.prepareMultiSearchStm();
		} catch (SQLException e) {
			GUI.showDBErrorDialog(this, Database.DBExceptionConverter(e,true));
		}
	}
}
