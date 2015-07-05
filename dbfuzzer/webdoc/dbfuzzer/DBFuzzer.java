package webdoc.dbfuzzer;

import java.sql.SQLException;
import java.util.Random;

import webdoc.lib.ConfigLib;
import webdoc.lib.Database;
import webdoc.lib.PasswordGenerator;

/**
 * WebDoc DB Fuzzer & pen tester class, do NOT let this outside the dev evironment
 * @author Aron Heinecke
 */
public class DBFuzzer {
	private static final String CONFIG_FILE_NAME = "config.yml";
	private static final String DEFAULT_CONFIG_PATH = "/webdoc/files/config.yml";
	
	public static void main(String[] args){
		try{
			init();
//			 SecureRandom random = new SecureRandom();
//			System.out.println(new BigInteger(130, random).toString(32));
			PasswordGenerator pwg = new PasswordGenerator();
			Random rand = new Random(System.nanoTime());
			for(int i = 0; i <= 10000; i++)
				Database.insertTreatment(pwg.generateGenericPassword(10), (double)rand.nextInt(50), pwg.generateGenericPassword(20));
			
		} catch (SQLException e) {
			e.printStackTrace();
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
	
}