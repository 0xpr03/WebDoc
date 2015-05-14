/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui.utils;

/**
 * AutoComplete Element storing the ID & the Name
 * @author "Aron Heinecke"
 */
public class ACElement {
	public static enum ElementType{
		ANIMAL(0),PARTNER(1),RACE(2),ANAMNESEIS(3),TREATMENT(4);
		private ElementType(int ElementType){
			this.ElementType = ElementType;
		}
		private int ElementType;
		public int getType(){
			return this.ElementType;
		}
	}
	
	private String name;
	private String optname;
	private long id;
	private ElementType elemType;
	
	/**
	 * Creates a new ACElement, containing it's name, optname id & element type
	 * @param name name see partner name / patient name
	 * @param optname optional name see partner secondname / patient callname
	 * @param id PartnerID or PatientID
	 * @param elemType Animal or Partner
	 * @author "Aron Heinecke"
	 */
	public ACElement(String name, String optname, long id, ElementType elemType){
		this.name = name;
		this.optname = optname;
		this.id = id;
		this.elemType = elemType;
	}
	
	public long getID(){
		return id;
	}
	
	public ElementType getType(){
		return this.elemType;
	}
	
	public String getName(){
		return name;
	}
	public String getOptname(){
		return optname;
	}
	
	@Override
	public String toString(){
		return name+" "+optname+" "+id+" "+elemType;
	}
}
