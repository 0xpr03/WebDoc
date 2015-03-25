package webdoc.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class db_lib {
	
	private static Logger logger = LogManager.getLogger();
	
	public static boolean sqlTblCreator(File f){
		try{
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			Pattern pattern = Pattern.compile(".*;.*");
			Matcher matcher = pattern.matcher("");
			
			for(String line = br.readLine(); line != null;line=br.readLine()){
				matcher.reset(line);
				sb.append(line);
				if(matcher.find()){
					//TODO: do sql command
					
					sb.setLength(0);
				}
			}
			br.close();
			fr.close();
			return true;
		}catch(IOException e){
			logger.error("Unable to open file!", e);
			return false;
		}
	}
}
