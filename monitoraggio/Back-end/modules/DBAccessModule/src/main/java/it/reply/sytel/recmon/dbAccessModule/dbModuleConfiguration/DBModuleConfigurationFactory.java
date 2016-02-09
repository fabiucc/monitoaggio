package it.reply.sytel.recmon.dbAccessModule.dbModuleConfiguration;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class DBModuleConfigurationFactory {
	
	private static String filename = "Configuration.properties";
	private DBModuleConfiguration configuratorIstance;
	private static Logger logger = Logger.getLogger(DBModuleConfigurationFactory.class.getName());

	
	public DBModuleConfigurationFactory() {
		//In questo modo garantisco la possibilita' di cambiare i valori 
		//del file di configurazione, pur avendo un unico punto da cui leggo le configurazioni.
		if(configuratorIstance==null)
			   configuratorIstance=new DBModuleConfiguration();
			
		configurationLoading();	
	}


	private void configurationLoading()  {
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();	
			logger.info("START "+nomeMetodo);
		Properties properties = new Properties();
		try {	 
	        InputStream path = this.getClass().getClassLoader().getResourceAsStream(filename);
	        	logger.debug("File di properties "+path.toString());
		    properties.load(path);			
			configuratorIstance.setJndiName(properties.getProperty("jndiName"));			
				logger.debug("Letta la proprieta' jndiName ["+properties.getProperty("jndiName")+"]");
			
		} catch (Exception e) {
			logger.error("ECCEZIONE "+e);
		}
		logger.info("END "+nomeMetodo);
	}
	
	
	
	public DBModuleConfiguration getConfiguratorIstance() {
		return configuratorIstance;
	}

}
