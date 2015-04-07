package webdoc.gui;

/**
 * Custom Tree Obj for localisation
 * @author "Aron Heinecke"
 *
 */
public class CustomTreeObj {
	public static enum EntryType{
		HAUPTMENÜ(0);
		private EntryType(int EntryType){
			this.EntryType = EntryType;
		}
		private int EntryType;
		public int getType(){
			return this.EntryType;
		}
	}
	
	private String name;
	private EntryType type;
	
	public CustomTreeObj(String name, EntryType type){
		this.name = name;
		this.type = type;
	}
	
	public EntryType getType(){
		return type;
	}
	public String getName(){
		return name;
	}
	@Override
	public String toString(){
		return getName();
	}
}
