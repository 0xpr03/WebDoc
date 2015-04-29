package webdoc.gui;



public class WPatient extends WNeuerPatient {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2269019202427909267L;	

	public WPatient(boolean editable, WHomescreen whs, long id) {
		super(editable, whs, id);
		this.setVisible(true);
	}
	
}
