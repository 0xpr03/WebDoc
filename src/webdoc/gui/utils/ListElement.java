package webdoc.gui.utils;

/**
 * Custom object to store list entrys
 * Lightweighter then ACElement
 * @author "Aron Heinecke"
 */
public class ListElement {
	public static enum LEType{
		UNDEFINED(-1),TYPE_A(0),TYPE_B(1);
		private LEType(int LEType){
			this.LEType = LEType;
		}
		private int LEType;
		public int getType(){
			return this.LEType;
		}
	}
	private long id;
	private LEType elemType;
	private String name;
	
	public ListElement(long id, String name, LEType type){
		this.id = id;
		this.name = name;
		this.elemType = type;
	}
	public ListElement(long id, String name){
		this.id = id;
		this.name = name;
		this.elemType = LEType.UNDEFINED;
	}
	
	public String getName(){
		return name;
	}
	public long getID(){
		return id;
	}
	public LEType getType(){
		return elemType;
	}
}
