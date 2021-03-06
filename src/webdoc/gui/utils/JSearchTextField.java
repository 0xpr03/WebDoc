/*******************************************************************************
 * Copyright (c) 2015 by Aron Heinecke & Jonathan Peper
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Search textfield with API for input-based list entrys.
 * Search-Field in short.
 * @author "Aron Heinecke"
 */
public class JSearchTextField extends JTextField {

	private static final long serialVersionUID = -7804457208421039268L;

	/**
	 * Interface for communicating with the search field.
	 * 
	 * @author "Aron Heinecke"
	 */
	public interface searchFieldAPI {
		/**
		 * Function fired when the search text changes, needs to provide a list
		 * of Element to display.
		 * 
		 * @param text
		 * @return List<ACElement>
		 */
		public List<ACElement> getData(String text);

		/**
		 * Fired event when an element from the list is chosen. You have to
		 * declare by your own what should be displayed inside the Field, if you
		 * want so.
		 * 
		 * @param element
		 * @return boolean accept false for event abort
		 */
		public boolean changedSelectionEvent(ACElement element);

		/**
		 * Custom renderer, what should be displayed. Returns a String used as
		 * display text for an element
		 * 
		 * @param element
		 */
		public String listRenderer(ACElement element);
	}

	private static final int MAX_VISIBLE_ROWS = 8;
	private static final int MIN_CHARS = 3;
	private searchFieldAPI api = null;
	private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	private Logger logger = LogManager.getLogger();
	private final JList<ACElement> list = new JList<ACElement>(new DefaultListModel<ACElement>());

	private String userText = ""; // text the user typed in, NEVER the selected element
	private ACElement chosenElement = null; // the last selected element
	private boolean showCurrElement;
	private boolean notificationDenied;
	private boolean clearOnFocus = false;
	private boolean replaceUserText = false;
	
	private final JPopupMenu popup = new JPopupMenu() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -274947063859497655L;
		public Dimension getPreferredSize() {
			Dimension dimension = super.getPreferredSize();
			dimension.width = JSearchTextField.this.getWidth();
			return dimension;
		}
	};

	public void setAPI(searchFieldAPI api) {
		this.api = api;
	}
	
	/**
	 * Initialize JSearchTextField
	 * 
	 * @param showCurrentElement
	 *            Define if the textfield should get the current list-element as
	 *            value on change. If you are using HTML for the display
	 *            renderer, set this to false.
	 */
	public JSearchTextField(boolean showCurrentElement) {
		this.showCurrElement = showCurrentElement;
		setColumns(1);
		JScrollPane scrollPane = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBar(null);
		scrollPane.setBorder(null);
		
		list.setFocusable(false);
		list.setCellRenderer(new SearchCellRenderer());

		popup.add(scrollPane);
		popup.setFocusable(false);
		popup.setBorder(new LineBorder(Color.BLACK, 1));

		getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				onTextChanged();
			}

			public void removeUpdate(DocumentEvent e) {
				onTextChanged();
			}

			public void changedUpdate(DocumentEvent e) {
				onTextChanged();
			}
		});

		list.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				int index = list.locationToIndex(e.getPoint());
				if (index >= 0 && list.getSelectedIndex() != index) {
					list.setSelectedIndex(index);
					chosenElement = list.getSelectedValue();
				}
			}
		});

		list.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e) && chosenElement != null) {
					if(setElement()){
						if (showCurrElement)
							setTextWithoutNotification(api.listRenderer(list.getSelectedValue()));
					}
				}
			}
		});
		
		addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				popup.setVisible(false);
			}
			public void focusGained(FocusEvent e){
				if (userText.length() >= MIN_CHARS && isEditable()) {
					setTextWithoutNotification(userText);
					showElements();
				}
			}
		});
		
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if(e.getClickCount() == 1 && isEditable()){
					if (SwingUtilities.isLeftMouseButton(e) && userText.length() != 0 && getSelectedText() == null ){
						setTextWithoutNotification(userText);
						showElements();
					}
				}
			}
		});
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (popup.isShowing()) {
					logger.debug("chosenElement: {}", chosenElement);
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP: {
						changeListSelectedIndex(-1);
						break;
					}

					case KeyEvent.VK_PAGE_UP: {
						changeListSelectedIndex(-list.getVisibleRowCount());
						break;
					}

					case KeyEvent.VK_DOWN: {
						changeListSelectedIndex(1);
						break;
					}

					case KeyEvent.VK_PAGE_DOWN: {
						changeListSelectedIndex(list.getVisibleRowCount());
						break;
					}

					case KeyEvent.VK_ESCAPE: {
						popup.setVisible(false);
						setTextWithoutNotification(userText);
						break;
					}

					case KeyEvent.VK_ENTER:
						if(chosenElement != null){
							setElement();
						}else{
							break;
						}
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_RIGHT: {
						popup.setVisible(false);
						break;
					}
					default:
					}
				} else {
					if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP
							|| e.getKeyCode() == KeyEvent.VK_PAGE_UP || e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
						userText = getText();
						showElements();
					}
				}
			}
		});
	}
	
	private boolean setElement(){
		if(api.changedSelectionEvent(chosenElement)){
			if(clearOnFocus)
				userText = "";
			if(replaceUserText)
				userText = api.listRenderer(chosenElement);
			popup.setVisible(false);
			return true;
		}
		return false;
	}
	
	@Override
	public void setEditable(boolean editable){
		if(popup != null)
			popup.setVisible(false);
		super.setEditable(editable);
	}

	private void changeListSelectedIndex(int delta) {
		int size = list.getModel().getSize();
		int index = list.getSelectedIndex();

		int newIndex;

		if (index < 0) {
			newIndex = delta > 0 ? 0 : size - 1;
		} else {
			newIndex = index + delta;
		}

		if (newIndex >= size || newIndex < 0) {
			newIndex = newIndex < 0 ? 0 : size - 1;
			if (index == newIndex) {
				newIndex = -1;
			}
		}
		
		logger.debug("list selected Change.. {}",showCurrElement);
		if (newIndex < 0) {
			list.getSelectionModel().clearSelection();
			list.ensureIndexIsVisible(0);
			if(showCurrElement)
				setTextWithoutNotification(userText);
		} else {
			list.setSelectedIndex(newIndex);
			list.ensureIndexIsVisible(newIndex);
			if(showCurrElement)
				setTextWithoutNotification(api.listRenderer(list.getSelectedValue()));
		}
		chosenElement = list.getSelectedValue();
	}

	/**
	 * Set the current text without input triggering
	 * @param text
	 */
	public void setTextWithoutNotification(String text) {
		notificationDenied = true;
		try {
			setText(text);
		} finally {
			notificationDenied = false;
		}
	}
	
	/**
	 * Override the last user input text (storage)
	 * @param text
	 */
	public void overrideText(String text) {
		notificationDenied = true;
		try {
			setText(text);
			userText = text;
		} finally {
			notificationDenied = false;
		}
	}
	
	/**
	 * Clear last text on new focus
	 * @param clearOnFocus
	 */
	public void clearOnFocus(boolean clearOnFocus){
		this.clearOnFocus = clearOnFocus;
	}

	/**
	 * Replace user text with new element on select
	 * @param replaceUserText
	 */
	public void replaceUserText(boolean replaceUserText){
		this.replaceUserText = replaceUserText;
	}
	
	
	/**
	 * Sets a new font type
	 * @param font new font to use
	 */
	public void setFont(Font font){
		this.font = font;
	}

	private void onTextChanged() {
		if (!notificationDenied) {
			userText = getText();
			if (userText.length() >= MIN_CHARS) {
				showElements();
			} else {
				popup.setVisible(false);
			}
		}
	}
	
	/**
	 * Show elements using the userText
	 */
	private void showElements() {
		list.getSelectionModel().clearSelection();

		DefaultListModel<ACElement> model = (DefaultListModel<ACElement>) list.getModel();

		model.clear();

		for (ACElement s : api.getData(userText)) {
			model.addElement(s);
		}

		int size = model.size();

		if (size == 0) {
			popup.setVisible(false);
		} else {
			list.setVisibleRowCount(size < MAX_VISIBLE_ROWS ? size : MAX_VISIBLE_ROWS);

			popup.pack();

			if (!popup.isShowing()) {
				popup.show(JSearchTextField.this, 0, getHeight());
			}
		}
	}

	/**
	 * Custom cell renderer for ACElement Lists
	 * 
	 * @author "Aron Heinecke"
	 */
	class SearchCellRenderer extends JLabel implements ListCellRenderer<ACElement> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -149396533447910959L;
		private Color HIGHLIGHT_COLOR = new Color(51, 153, 255);

		public SearchCellRenderer() {
			setOpaque(true);
			setIconTextGap(12);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends ACElement> list, ACElement element, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(api.listRenderer(element));
			setFont(font);
			if (isSelected) {
				setBackground(HIGHLIGHT_COLOR);
				setForeground(Color.white);
			} else {
				setBackground(Color.white);
				setForeground(Color.black);
			}
			return this;
		}
	}
}
