package webdoc.dbfuzzer;

import java.math.BigInteger;
import java.security.SecureRandom;

import webdoc.lib.ConfigLib;
import webdoc.lib.Database;
import webdoc.lib.PasswordGenerator;

public class DBFuzzer {
	private static final String CONFIG_FILE_NAME = "config.yml";
	private static final String DEFAULT_CONFIG_PATH = "/webdoc/files/config.yml";
	
	public static void main(String[] args){
		try{
			init();
			PasswordGenerator pwg = new PasswordGenerator();
//			for()
//			Database.insertTreatment(pwg.generateGenericPassword(10), preis, erklaerung)
			
		}finally{
			Database.disconnect();
		}
	}
	
	private static void init(){
		ConfigLib cfl = new ConfigLib(CONFIG_FILE_NAME, DEFAULT_CONFIG_PATH);
		cfl.loadConfig();
		cfl.parseConfig();
		Database.connect(true, false);
	}
	
	public final class SessionIdentifierGenerator {
		private SecureRandom random = new SecureRandom();

		public String nextSessionId() {
			return new BigInteger(130, random).toString(32);
		}
	}
}