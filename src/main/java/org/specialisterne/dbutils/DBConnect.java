package org.specialisterne.dbutils;

import java.sql.*;
import java.util.Properties;

public class DBConnect {
	
	private Connection dbconnection;
	private static String name = "mysql.issp.eu";
	
	public boolean isConnected(){
		try {
			return !dbconnection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public synchronized Connection getDBConnection() throws SQLException{
		if(dbconnection == null) {
	        //Treiber aus XML-Datei lesen 
			DBConfig configuration = new DBConfig(name);
	         configuration.loadDriver();
	         try {
				Class.forName(configuration.getDriver());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	        
	         //DB-Daten aus XML-Datei lesen
		    configuration.loadConfig();
		    Properties connectionProps = new Properties();
	 	    connectionProps.setProperty("user", configuration.getUser());
	 	    connectionProps.setProperty("password", configuration.getPassword());
		    
	 	    dbconnection = DriverManager.getConnection(configuration.getRegex(), connectionProps);
		}
		return dbconnection;
	}
	
	public boolean closeDBConnection(){
		if(isConnected()){
			try {
				dbconnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(isConnected()){
			return false;
		}
		else{return true;}
	}
	
	public ResultSet writeQuery(String statement, Object... args){
		PreparedStatement stm = null;
		try {
			stm = dbconnection.prepareStatement(statement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			for(int i=0; i<args.length; i++){
				stm.setObject(i+1, args[i]);
			}
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet readQuery(String statement, Object... args){
		PreparedStatement stm = null;
		try {
			stm = dbconnection.prepareStatement(statement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			for(int i=0; i<args.length; i++){
				stm.setObject(i+1, args[i]);
			}
			return stm.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		DBConnect.name = name;
	}
}