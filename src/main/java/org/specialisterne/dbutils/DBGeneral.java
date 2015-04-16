package org.specialisterne.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBGeneral {
	private DBConnect dbConn;
	private CreateStatement crStmt;
	private String statement;
	
	public int getNextID(String table){
		int nextID = 0;
		dbConn = new DBConnect();
		String cn[] = {"id"};
		crStmt = new CreateStatement("select", table, cn);
		try {
			dbConn.getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		statement = crStmt.getStatement();
		ResultSet rs = dbConn.readQuery(statement);
		try {
			rs.last();
			if(rs.last()){
				nextID = rs.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbConn.closeDBConnection();
		return nextID+1;
	}
}
