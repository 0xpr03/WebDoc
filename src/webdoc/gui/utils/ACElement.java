package webdoc.gui.utils;

/**
 * AutoComplete Element storing the ID & the Name
 * @author "Aron Heinecke"
 */
public class ACElement {
	public static enum ElementType{
		ANIMAL(0),PARTNER(1);
		private ElementType(int ElementType){
			this.ElementType = ElementType;
		}
		private int ElementType;
		public int getType(){
			return this.ElementType;
		}
	}
	
	private String name;
	private long id;
	private ElementType elemType;
	
	public ACElement(String name, long id, ElementType elemType){
		this.name = name;
		this.id = id;
		this.elemType = elemType;
	}
	
	public long getID(){
		return id;
	}
	
	public ElementType getType(){
		return this.elemType;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
