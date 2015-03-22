package webdoc.crap;

import webdoc.lib.database;

public class crap {
	
	int a;
	
	public crap(int i){
		this.a = i;
	}
	
	public void runInheritanceTest(){
		
		database.test();
	}
	
	public void addTest(){
		database.add();
	}
	
}
