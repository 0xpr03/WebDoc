package webdoc.gui.utils;

/**
 * AutoComplete Element storing the ID & the Name
 * @author "Aron Heinecke"
 */
public class ACElement {
	
	private String name;
	private long id;
	
	public ACElement(String name, long id){
		this.name = name;
		this.id = id;
	}
	
	public long getType(){
		return id;
	}
	@Override
	public String toString(){
		return name;
	}
}
