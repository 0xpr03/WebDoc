package webdoc.lib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.webdoc.webdoc;

/***
 * Backend to DBMS class
 * @author Aron
 *
 */
public class database extends webdoc {
	
	private static int i = 0;
	
	private static Logger logger = LogManager.getLogger("database");
	
	public database(){
		i = -1;
		logger.info("Lol this is NEVER called!");
		test();
	}
	
	public static void test(){
		logger.info("I = {}", i);
	}
	
	public static void add(){
		logger.info("i++");
		i++;
	}
}
