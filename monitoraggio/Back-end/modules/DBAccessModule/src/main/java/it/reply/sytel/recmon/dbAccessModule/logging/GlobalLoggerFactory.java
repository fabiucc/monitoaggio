package it.reply.sytel.recmon.dbAccessModule.logging;

import java.io.Serializable;
import org.apache.log4j.Logger;

public class GlobalLoggerFactory implements Serializable{
	
	private static final long serialVersionUID = -3947818902271189194L;

	public static Logger getLogger(Class<?> c,String componentId){
		return Logger.getLogger(c);
		//return new ExpoLogger(componentId);
	}
	
	public static Logger getLogger(Class<?> c){
		return Logger.getLogger(c);
		//return new ExpoLogger(c.getName());
	}
}
