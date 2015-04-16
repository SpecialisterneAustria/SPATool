package org.specialisterne.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CreateStatement {
	DBConnect conn = new DBConnect();
	String statetype, table, statement, identifier, condition = null;
	String[] colnames; //Übergabe-Array für select und update
	String[] columns; //Array zum auslesen der Spaltennamen mittels getColumns()
	
	//Konstruktor für insert
	public CreateStatement(String statetype, String table){
		this.statetype = statetype;
		this.table = table;
	}
	
	//Konstruktor für delete
	public CreateStatement(String statetype, String table, String identifier){
		this.statetype = statetype;
		this.table = table;
		this.identifier = identifier;
	}
	
	//Konstruktor für select mit condition und update
	public CreateStatement(String statetype, String table, String colnames[], String condition){
		this.statetype = statetype;
		this.table = table;
		this.colnames = colnames;
		this.condition = condition;
	}
	
	//Konstruktor für select ohne condition
	public CreateStatement(String statetype, String table, String colnames[]){
		this.statetype = statetype;
		this.table = table;
		this.colnames = colnames;
	}
	
	//Methode, die der Client aufrufen muss
	public String getStatement(){
		
		switch (statetype) {
		case "insert": case "Insert":
			columns = this.getColumns();			
			return this.createInsertStmnt();
		
		case "select": case "Select":
			return this.createSelectStmnt();
			
		case "update": case "Update":
			return this.createUpdateStmnt();
		
		case "delete": case "Delete":
			return this.createDeleteStmnt();
		
		default: System.out.println("Statement nicht erkannt");
				return null;
		}
	}
	
	private String[] getColumns(){
		ResultSet rs = null;
		ResultSetMetaData rsMetaData;
		String[] columns = null;
		Connection dbconnection = null;
		PreparedStatement stm = null;
		
		try {
			dbconnection = conn.getDBConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String selectColStmnt = "select * from ";
		String selectstatement = selectColStmnt + table;
				
		try {
			stm = dbconnection.prepareStatement(selectstatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery();
			rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
			columns = new String[numberOfColumns];
				for(int i=1; numberOfColumns >= i; i++){
					columns[i-1] = rsMetaData.getColumnName(i);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return columns;
	}
	
	private String createInsertStmnt() {
		StringBuffer colnames = new StringBuffer();
		StringBuffer colentries = new StringBuffer();
		StringBuffer statement = new StringBuffer();
		
		//zusammenstellen der Spaltennamen aus String[] columns
		//if-Abfrage notwendig wegen Klammern
		if(columns.length == 1){
			colnames.append(" (").append(columns[0]).append(") ");
		}
		else{
			for(int i=0; i < columns.length; i++){
				if(i == 0){
					colnames.append(" (").append(columns[0]).append(", ");
				} else if(i == columns.length -1){
					colnames.append(columns[i]).append(") ");
				} else {
					colnames.append(columns[i]).append(", ");
				}
			}
		}
		
		//zusammenstellen der ?
		//if-Abfrage notwendig wegen Klammern
		if(columns.length == 1){
			colentries.append(" (?)");
		}
		else {
			for(int i=0; i < columns.length; i++){	
				if(i == 0){
					colentries.append(" (?, ");
					} else if(i == columns.length -1){
						colentries.append("?)");
					} else {
						colentries.append("?, ");
					}
			}
		}
		statement.append("insert into ").append(table).append(colnames).append("values").append(colentries);
		conn.closeDBConnection();
		return statement.toString();
	}
	
	private String createDeleteStmnt(){
		StringBuffer statement = new StringBuffer();
		statement.append("delete from ").append(table).append(" where ").append(identifier).append(" = ?");
		//conn.closeDBConnection();
		return statement.toString();
	}

	private String createSelectStmnt(){
		StringBuffer statement = new StringBuffer();
		StringBuffer colstring = new StringBuffer();
		
		//zusammenstellen der Spaltennamen aus String[] colnames
		for(int i=0; i < colnames.length; i++){
			if(i == colnames.length -1){
				colstring.append(colnames[i] + " ");
			} 
			else {
				colstring.append(colnames[i] + ", ");
			}
		}

		statement.append("select ").append(colstring).append("from ").append(table);
		//falls Bedingung angegeben wurde, wird diese hinzugefügt
		if(condition != null){
			statement.append(" where ").append(condition);
		}
		//conn.closeDBConnection();
		return statement.toString();
	}
	
	private String createUpdateStmnt() {
		StringBuffer statement = new StringBuffer();
		StringBuffer colstring = new StringBuffer();
		
		//zusammenstellen der Spaltennamen aus String[] colnames
		for(int i=0; i < colnames.length; i++){
			if(i == colnames.length -1){
				colstring.append(colnames[i]).append(" = ? ");
			}
			else{
				colstring.append(colnames[i]).append(" = ?, ");
			}
		}
		
		statement.append("update ").append(table).append(" set ").append(colstring).append("where ").append(condition);
		//conn.closeDBConnection();
		return statement.toString();
	}
}
