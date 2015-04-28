package webdoc.gui.utils;


/**
 * Custom enum types for localization
 * @author "Aron Heinecke"
 */
public class RoleEnumObj {
	public static enum RoleType{
		UNKNOWN(-1),PETOWNER(1),MEDIC(2);
		private RoleType(int EntryType){
			this.EntryType = EntryType;
		}
		private int EntryType;
		public int getType(){
			return this.EntryType;
		}
	}
	
	private String name;
	private RoleType type;
	
	public RoleEnumObj(String name, RoleType type){
		this.name = name;
		this.type = type;
	}
	
	public RoleType getType(){
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
