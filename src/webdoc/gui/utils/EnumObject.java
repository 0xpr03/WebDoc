/*******************************************************************************
 * Copyright (c) 2015 by Aron Heinecke & Jonathan Peper
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui.utils;


/**
 * Custom enum types for localization
 * @author "Aron Heinecke"
 */
public class EnumObject {
	public static enum EnumType{
		UNKNOWN(0),A(1),B(2),C(3),D(4);
		private EnumType(int EnumType){
			this.EnumType = EnumType;
		}
		private int EnumType;
		public int getType(){
			return this.EnumType;
		}
	}
	
	private String name;
	private EnumType type;
	
	public EnumObject(String name, EnumType type){
		this.name = name;
		this.type = type;
	}
	
	public EnumType getType(){
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
