package it.reply.sytel.recmon.dbAccessModule.dbAccess;


import it.reply.sytel.recmon.dbAccessModule.dbModuleConfiguration.DBModuleConfiguration;
import it.reply.sytel.recmon.dbAccessModule.dbModuleConfiguration.DBModuleConfigurationFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBAccessManager {

	private static DBAccessManager dBManagerIstance=null;
	private DBModuleConfiguration conf=null;
	private static Logger logger = Logger.getLogger(DBAccessManager.class.getName());
	
	
	private DBAccessManager()
	{	   
		DBModuleConfigurationFactory factory=new DBModuleConfigurationFactory();
		conf=factory.getConfiguratorIstance();
	}
	
	
	
	public static DBAccessManager getDBAccessManager(boolean changeConfiguration)
	{
		if(dBManagerIstance==null || changeConfiguration==true)
		{			
			logger.debug("Creazione di un'istanza di DBAccessManager");
			dBManagerIstance=new DBAccessManager();
		}
		logger.debug("Restituzione dell'istanza di DBAccessManager");
		return dBManagerIstance;	
			
	}
	
	
	
	public PreparedStatement createStatement(String query)
	{
		String nomeMetodo = new Object() {
			}.getClass().getEnclosingMethod().getName();
		logger.info("START "+nomeMetodo);
		logger.info("INPUT   query ["+query+"]");
		
		PreparedStatement ps=null;
	
		logger.debug("Richiesta di una connessione al database");
		Connection conn = getConnection();
		try {
			logger.debug("Ricavo il PreparedStatement");
			ps=conn.prepareStatement(query);			
		}
		catch (SQLException e) {
			closeConnection(conn);
			logger.error("ECCEZIONE: "+ e);
		}
		logger.info("END "+nomeMetodo);
		return ps;
	}
	
	
	
	public CallableStatement creaStatement(String procedure)
	{
		String nomeMetodo = new Object() {
			}.getClass().getEnclosingMethod().getName();
		logger.info("START "+nomeMetodo);
		logger.info("INFO   procedure ["+procedure+"]");
		CallableStatement cs=null;
		
		logger.debug("Richiesta di una connessione al database");
		Connection conn = getConnection();
		try {
			logger.debug("Ricavo il CallableStatement");
			cs=conn.prepareCall(procedure);			
		}
		catch (SQLException e) {
			closeConnection(conn);
			logger.error("ECCEZIONE: "+ e);
		}
		logger.info("END "+nomeMetodo);
		return cs;
	}
	
	
	
	public List<? extends Object> executeQuery(PreparedStatement query, Class classe) {

		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();	
		
		logger.info("START "+nomeMetodo);
		logger.info("INPUT   PreparedStatement ["+query.toString()+"]");
		
		List listaBeans = new ArrayList();
		
		try {			
			//PreparedStatement ps = c.prepareStatement(query);
			logger.debug("Eseguo la query "+query.toString());
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				Object bean = createBean(classe);
				for (Field f : getFields(classe)) {
					String nomeMetodoSet = getMethodName("set", f.getName());
						logger.debug("Nome del metodo set per la proprieta' "+f.getName()+":   ["+nomeMetodoSet+"]");
					Method metodoSetBean = getMethod(nomeMetodoSet, classe,
							new Class[] { f.getType() });
					String tipoCampo = getFieldType(f);
						logger.debug("Tipo del campo per la proprieta' "+f.getName()+":   ["+tipoCampo+"]");
					Object value = getColumnValue(f.getName(), tipoCampo, rs);
						logger.debug("La proprieta' "+f.getName()+" vale:   ["+value+"]");
                    if(value!=null)
					   invokeMethod(bean, metodoSetBean, new Object[] { value });
				}
				listaBeans.add(bean);
			}
		}
		catch (Exception e) {
			logger.error("ECCEZIONE: "+ e);
		}
		finally{
			closeConnection(takeConnectionFromStatement(query));
		}
		logger.info("Numero record recuperati "+listaBeans.size());
		logger.info("END "+nomeMetodo);
		return listaBeans;
	}

	
	
	//restituisce la connessione correlata ad un certo PreparedStatement
	public Connection takeConnectionFromStatement(PreparedStatement ps)
	{
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();
			logger.info("START "+nomeMetodo);
			logger.info("INPUT   PreparedStatement ["+ps+"]");
		Connection c=null;
		try {
			logger.debug("Richiesta di una connessione al database");
			c=ps.getConnection();
		} catch (SQLException e) {
			logger.error("ECCEZIONE: "+e);
		}
		logger.info("END "+nomeMetodo);
		return c;
	}
	
	
	
	public Connection takeConnectionFromCStatement(CallableStatement cs)
	{
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();		
		
		logger.info("START "+nomeMetodo);
		logger.info("INPUT   CallableStatement ["+cs+"]");
		Connection c=null;
		try {
			logger.debug("Richiesta di una connessione al database");
			c=cs.getConnection();
		}
		catch (SQLException e) {
			logger.error("ECCEZIONE: "+e);
		}
		logger.info("END "+nomeMetodo);
		return c;
	}
	
	
	
	private Connection getConnection() 
	{		
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();		
		
		logger.info("START "+nomeMetodo);
		Connection con=null;		
		try {
			InitialContext initialContext = new InitialContext();
				logger.debug("Ricavo il Context");
		    Context ctx = (Context)initialContext.lookup("java:/comp/env");
		    	logger.debug("Look up al connection pool con jndi name ["+conf.getJndiName()+"]");
			DataSource ds = (DataSource)ctx.lookup(conf.getJndiName());
				logger.debug("Ricavato il DataSource");
			return ds.getConnection();
		}
		catch (NamingException e) {
			logger.error("ECCEZIONE: "+e);
			
		} catch (SQLException e) {
			logger.error("ECCEZIONE: "+e);
		}		
		logger.info("END "+nomeMetodo);
		return con;
	}

	

	public void closeConnection(Connection con)
	{
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();		
		
		logger.info("START "+nomeMetodo);
		
		if (con != null) {
			try {
				con.close();
			}
			catch (SQLException e) {
				logger.error("ECCEZIONE: "+e);
			}
		}
		logger.info("END "+nomeMetodo);
	}

	

	private Field[] getFields(Class classe) {
		
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();		
		
		logger.info("START "+nomeMetodo);
		
		Field[] attributi = classe.getDeclaredFields();
		logger.debug("Attributi estartti per la classe "+classe.toString()+"   "+attributi.length);
		logger.info("END "+nomeMetodo);
		return attributi;
	}



	private String getFieldType(Field attributo) {
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();
			logger.info("START "+nomeMetodo);
			logger.info("INPUT   attributo ["+attributo.getName()+"]");
		String type = attributo.getGenericType().toString();
		String[] trimmedType = type.split("\\.");
		type = trimmedType[trimmedType.length - 1];
			logger.info("Il tipo dell'attributo "+attributo.getName()+ " e' "+type);
			logger.info("END "+nomeMetodo);
		return type;
	}



	private Object getColumnValue(String columnName, String columnType,
			ResultSet rsIstance) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {		
		
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();		
		
		logger.info("START "+nomeMetodo);
		logger.info("INPUT   columnName ["+columnName+"] " +
				"columnType ["+columnType+"]");
		
		Object columnValue = null;
		String methodName = getMethodName("get", columnType);		
			logger.debug("Nome del metodo "+methodName);	
		Method methodGetOfResult = getMethod(methodName, ResultSet.class,
				new Class[] { String.class });

		try{
			logger.debug("Invocazione del metodo "+methodName+" dell'oggetto ResultSet per il campo "+columnName);		
		columnValue = invokeMethod(rsIstance, methodGetOfResult,
				new Object[] { columnName });
		}
		catch(Exception e)
		{
			logger.error("ECCEZIONE: "+e);
			return null;
		}

		logger.info("END "+nomeMetodo);				
		return columnValue;
	}



	private String getMethodName(String prefisso, String suffisso) {
		
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();		
		
		logger.info("START "+nomeMetodo);
		logger.info("INPUT   prefisso ["+prefisso+"] " +
				"suffisso ["+suffisso+"]");
		
		// Rendo maiuscola la prima lettera del suffisso se non lo e' (per rispettare la notazione CamelCase)
		char[] stringArray = suffisso.trim().toCharArray();
		stringArray[0] = Character.toUpperCase(stringArray[0]);
		suffisso = String.copyValueOf(stringArray);

		// genero il nome del metodo
		String method = prefisso + suffisso;	
		logger.info("Metodo da invocare   ["+method+"]");
		logger.info("END "+nomeMetodo);	
		return method;
	}

	
	
	// Creazione dell'istanza del bean vuoto (gli attributi devono essere impostati con il metodo set)
	private Object createBean(Class classe) throws InstantiationException,
			IllegalAccessException {
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();		
		
		logger.info("START "+nomeMetodo);
		logger.info("INPUT   classe ["+classe.getName()+"]");
		
		Object bean = null;
		bean = classe.newInstance();
		
		logger.debug("Creato un bean della classe "+classe.getName());
		logger.info("END "+nomeMetodo);
		return bean;
	}

	
	
	// Invocazione del metodo set del bean istanziato
	private Object invokeMethod(Object bean, Method method, Object[] parameters)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();		
		
		logger.info("START "+nomeMetodo);
		logger.info("INPUT   bean ["+bean.getClass().getName()+"] " +
				"method ["+method.getName()+"] " +
				"numero di parametri "+parameters.length);

		logger.debug("Invocazione in corso del metodo "+method.getName());
		logger.info("END "+nomeMetodo);
		return method.invoke(bean, parameters);
	}

	
	
	// Restituisce l'oggetto Method il cui nome combacia con la stringa passata in input (il primo della lista) 
	private Method getMethod(String methodName, Class classe,
			Class[] parametersClass) throws SecurityException,
			NoSuchMethodException {
		String nomeMetodo = new Object() {
		}.getClass().getEnclosingMethod().getName();		
		
		logger.info("START "+nomeMetodo);
		logger.info("INPUT   methodName ["+methodName+"] " +
				"classe ["+classe.getName()+"] " +
				"numero di parametri "+parametersClass.length);
		Method m = classe.getMethod(methodName, parametersClass);
		
		logger.info("END "+nomeMetodo);	
		return m;
	}

}
