package webdoc.gui.utils;


/**
 * Custom enum types for localization
 * @author "Aron Heinecke"
 */
public class GenderEnumObj {
	public static enum GenderType{
		UNKNOWN(-1),MALE(1),FEMALE(1);
		private GenderType(int EntryType){
			this.EntryType = EntryType;
		}
		private int EntryType;
		public int getType(){
			return this.EntryType;
		}
	}
	
	private String name;
	private GenderType type;
	
	public GenderEnumObj(String name, GenderType type){
		this.name = name;
		this.type = type;
	}
	
	public GenderType getType(){
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
