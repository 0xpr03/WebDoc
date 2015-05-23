/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui.utils;

import java.util.Date;

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
	
	public TDListElement(long id, String name, LEType type, Date date){
		this.id = id;
		this.name = name;
		this.elemType = type;
		this.date = date;
	}
	/**
	 * Unsafe constructor, name will be null!
	 * @param id
	 * @param type
	 * @param date
	 */
	public TDListElement(long id, LEType type, Date date){
		this.id = id;
		this.elemType = type;
		this.date = date;
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
	/**
	 * Warning! Only 1 constructor creates date!
	 * @return
	 */
	public Date getDate(){
		return date;
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
