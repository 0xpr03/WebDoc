package webdoc.webdoc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/***
 * Main class
 * @author Aron
 *
 */
public class webdoc {
	
	private static String sdf = "yyyy-MM-dd HH:mm:ss";
	private static String log_file_name = "webdoc-client.log";
	
	private static final Logger logger = LogManager.getLogger();
	private static String version = "0.1 alpha";
	
	
	public static void main(String[] args){
		// bsp: nur bei log level info werden die strings zusammengefügt
		logger.info("Starting up {}", version);
		
		
	}
	
	
}
