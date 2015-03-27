package webdoc.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.WProgress;

/**
 * DB related functions without the direct connection
 * @author "Aron Heinecke"
 *
 */
public class dbTools {
	
	private static Logger logger = LogManager.getLogger();
	
	public boolean sqlTblCreator(String file, WProgress window){
		try{
			InputStream is = getClass().getResourceAsStream(file);
		    InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			
			Pattern pattern = Pattern.compile(".*;.*");
			Matcher matcher = pattern.matcher("");
			window.setSubMax(getMaxLines(file));
			window.setSubText("Creating tables..");
			
			for(String line = br.readLine(); line != null;line=br.readLine()){
				matcher.reset(line);
				sb.append(line);
				if(matcher.find()){
					//TODO: do sql command
					logger.debug("Found ; {}",line);
					Database.execUpdateQuery(sb.toString());
					sb.setLength(0);
				}
				window.addSubProgress(1);
			}
			window.addProgress(1);
			br.close();
			isr.close();
			is.close();
			return true;
		}catch(IOException | NullPointerException e){
			logger.fatal("Unable to create tables from file!", e);
			return false;
		}
	}
	
	/**
	 * Returns the max lines of a file
	 * @param f file to read
	 * @return amount of numbers or -1 on error
	 */
	private int getMaxLines(String file){
		try {
			InputStream is = getClass().getResourceAsStream(file);
		    InputStreamReader isr = new InputStreamReader(is);
			LineNumberReader lnr = new LineNumberReader(isr);
			lnr.skip(Long.MAX_VALUE);
			int i = lnr.getLineNumber();
			lnr.close();
			isr.close();
			is.close();
			return i;
		}catch(IOException e){
			logger.error("Unable to open file!",e);
			return -1;
		}
	}
}
