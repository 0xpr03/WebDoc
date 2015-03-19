package webdoc.webdoc;

import webdoc.lib.cLogger;

public class webdoc {
	
	private static String sdf = "yyyy-MM-dd HH:mm:ss";
	private static String log_file_name = "webdoc-client.log";
	
	public static void main(String[] args){
		cLogger logger = new cLogger(log_file_name, sdf);
		logger.info("main", "I'm alive!");
		
		logger.close();
	}
	
}
