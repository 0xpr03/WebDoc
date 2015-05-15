package webdoc.gui.utils;

/**
 * Custom object to store list entrys
 * Lightweighter then ACElement
 * @author "Aron Heinecke"
 */
public class TDListElement {
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
	private Date date;
	
	public TDListElement(long id, String name, LEType type){
		this.id = id;
		this.name = name;
		this.elemType = type;
	}
	public TDListElement(long id, String name_1, String name_2){
		this.id = id;
		this.name = name_1 + name_2;
		this.elemType = LEType.UNDEFINED;
	}
	public TDListElement(long id, String name){
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
