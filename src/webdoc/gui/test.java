package webdoc.gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;

import webdoc.gui.utils.ACElement;
import webdoc.gui.utils.JAutoCompleteTextField;
import webdoc.gui.utils.JAutoCompleteTextField.DataProvider;

public class test {

	private JFrame Testframe;
	private JAutoCompleteTextField autoCompleteTextField;
	private List<ACElement> l2 = new ArrayList<ACElement>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.Testframe.setVisible(true);
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Testframe = new JFrame();
		Testframe.setTitle("Testframe");
		Testframe.setBounds(100, 100, 242, 217);
		Testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		autoCompleteTextField = new JAutoCompleteTextField();
		
		
		GroupLayout groupLayout = new GroupLayout(Testframe.getContentPane());
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
		Testframe.getContentPane().setLayout(groupLayout);
//		List<String> l = new ArrayList<String>();
//		l.add("123");
//		l.add("234");
//		l.add("345");
//		l.add("456");
//		l.add("567");
//		l.add("678");
//		l.add("789");
		
		l2.add(new ACElement("123", 1L));
		l2.add(new ACElement("234", 2L));
		l2.add(new ACElement("345", 3L));
		l2.add(new ACElement("456", 4L));
		l2.add(new ACElement("567", 5L));
		l2.add(new ACElement("678", 6L));
		l2.add(new ACElement("789", 7L));
		autoCompleteTextField.setHistory(l2);
		class Provider implements DataProvider{
			@Override
			public List<ACElement> getData(String text){
				return l2;
			}
		}
		autoCompleteTextField.setDataProvider(new Provider());
		
		
	}
	List<ACElement> getd(){
		return l2;
	}
}
