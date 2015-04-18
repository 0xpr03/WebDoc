package webdoc.webdoc;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.crap.crap;
import webdoc.lib.*;

/***
 * Main class
 * @author Aron
 *
 */
public class webdoc {
	
	private static String sdf = "yyyy-MM-dd HH:mm:ss";
	private static String log_file_name = "webdoc-client.log";
	
	protected  static final Logger logger = LogManager.getLogger();
	private static String version = "0.1 alpha";
	protected  static HashMap<String,Object> settings = new HashMap<String,Object>();
	
	
	public static void main(String[] args){
		// bsp: nur bei log level info werden die strings zusammengefügt
		logger.info("Starting up {}", version);
		logger.debug("Test");
		logger.warn("test");
		logger.error("test");
		
		logger.info("First test:");
		database.test();
		database.add();
		database.test();
		
		runCrap();
	}
	
	private static void runCrap(){
		crap crap = new crap(0);
		
		crap.runInheritanceTest();
		
		crap.addTest();
		
		crap.runInheritanceTest();
		database.test();
	}
	
	
}
