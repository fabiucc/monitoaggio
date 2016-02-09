package it.reply.sytel.monitoraggio.dao;

import it.reply.sytel.monitoraggio.beanVO.impl.Phone;
import it.reply.sytel.recmon.dbAccessModule.dbAccess.DBAccessManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
	
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://127.7.209.130:3306/application";

	   //  Database credentials
	   static final String USER = "adminC7xB1Wa";
	   static final String PASS = "gGKjDJPbYSpZ";
	
//	public List<Phone> allphone(){
//		List<Phone> phones=new ArrayList<Phone>();
//		DBAccessManager dbAccessManager=DBAccessManager.getDBAccessManager(true);
//		String queryString="SELECT * FROM MAG_PHONE";
//		System.out.println("Esecuzione query:" + queryString);
//		PreparedStatement ps = dbAccessManager.createStatement(queryString);
//		ResultSet rs;
//		try{
//			rs=ps.executeQuery();
//			while(rs.next()){
//				Phone phonesTemp = new Phone();
//				phonesTemp.setId(rs.getInt("id_modello"));
//				phonesTemp.setNome(rs.getString("nome"));
//				phonesTemp.setDealer(rs.getString("dealer"));
//				phonesTemp.setPrezzo(rs.getDouble("prezzo"));
//				phonesTemp.setDescrizione(rs.getString("descrizione"));
//				phones.add(phonesTemp);	
//			}
//			if (rs != null)
//				rs.close();
//		}catch (SQLException e){
//			System.out.println("Errore esecuzione query:" + e.getMessage());
//		}
//		Connection c = dbAccessManager.takeConnectionFromStatement(ps);
//		try {
//			if (ps != null)
//				ps.close();
//			if (c != null)
//				c.close();
//		} catch (SQLException e) {
//			System.out.println("Errore chiusura connessione:" + e.getMessage());
//		}
//		
//		return phones;
//		
//	}
	
	public List<Phone> allphone(){
		
		List<Phone> phones=new ArrayList<Phone>();
		Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM MAG_PHONE";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	  Phone phonesTemp = new Phone();
//		         int id  = rs.getInt("id_modello");
//		         String nome = rs.getString("nome");
//		         String dealer = rs.getString("dealer");
//		         double prezzo = rs.getDouble("prezzo");
//		         String descrizione=rs.getString("descrizione");
					phonesTemp.setId(rs.getInt("id_modello"));
					phonesTemp.setNome(rs.getString("nome"));
					phonesTemp.setDealer(rs.getString("dealer"));
					phonesTemp.setPrezzo(rs.getDouble("prezzo"));
					phonesTemp.setDescrizione(rs.getString("descrizione"));
					phones.add(phonesTemp);	

		         //Display values
		        
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   return phones;
		
	}

}
