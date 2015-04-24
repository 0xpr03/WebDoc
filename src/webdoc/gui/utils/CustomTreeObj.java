package webdoc.gui.utils;

/**
 * Custom Tree Obj for localization
 * @author "Aron Heinecke"
 */
public class CustomTreeObj {
	public static enum EntryType{
		ZWEIG(-1),HAUPTMENÜ(0),N_PATIENT(1),N_PARTNER(2),PARTNER(5),N_BEHANDLUNG(3),PATIENT(4);
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
