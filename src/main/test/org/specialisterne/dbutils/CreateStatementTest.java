package org.specialisterne.dbutils;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreateStatementTest {

	@Test
	public void createInsertStmntTest1() throws Exception {
		CreateStatement crstmt = new CreateStatement("insert", "Angebote");
		String statement;
		statement = crstmt.getStatement();
		System.out.println("InsertStmntTest1: "+statement);
		assertEquals("insert into Angebote (id, kundenID, projektID, angebotsDatum, dauer, chance, summe) values (?, ?, ?, ?, ?, ?, ?)", statement);
	}
	
	@Test
	public void createInsertStmntTest2() throws Exception {
		CreateStatement crstmt = new CreateStatement("insert", "Kontakte");
		String statement;
		statement = crstmt.getStatement();
		System.out.println("InsertStmntTest2: "+statement);
		assertEquals("insert into Kontakte (id, firmenname, ansprechperson, telefon, adresse, email, kunde) values (?, ?, ?, ?, ?, ?, ?)", statement);
	}
		
	@Test
	public void createSelectStmntTest1() throws Exception {
		String cn[] = {"firmenname", "ansprechperson"};
		CreateStatement crstmt = new CreateStatement("select", "Kontakte", cn);
		String statement;
		statement = crstmt.getStatement();
		System.out.println(statement);
		assertEquals("select firmenname, ansprechperson from Kontakte", statement);
	}
	
	@Test
	public void createSelectStmntTest2() throws Exception {
		String cn[] = {"*"};
		CreateStatement crstmt = new CreateStatement("select", "Kontakte", cn);
		String statement;
		statement = crstmt.getStatement();
		System.out.println(statement);
		assertEquals("select * from Kontakte", statement);
	}
	
	@Test
	public void createSelectCondStmntTest1() throws Exception {
		String cn[] = {"firmenname", "ansprechperson"};
		String cond = "firmenname = 'abc'";
		CreateStatement crstmt = new CreateStatement("select", "Kontakte", cn, cond);
		String statement;
		statement = crstmt.getStatement();
		System.out.println(statement);
		assertEquals("select firmenname, ansprechperson from Kontakte where firmenname = 'abc'", statement);
	}
	
	@Test
	public void createSelectCondStmntTest2() throws Exception {
		String cn[] = {"firmenname", "ansprechperson"};
		String selection = "abc";
		String cond = "firmenname = '"+ selection +"'";
		CreateStatement crstmt = new CreateStatement("select", "Kontakte", cn, cond);
		String statement;
		statement = crstmt.getStatement();
		System.out.println(statement);
		assertEquals("select firmenname, ansprechperson from Kontakte where firmenname = 'abc'", statement);
	}
	
	@Test
	public void createDeleteStmntTest1() throws Exception {
		String ident = "firmenname";
		CreateStatement crstmt = new CreateStatement("delete", "Kontakte", ident);
		String statement;
		statement = crstmt.getStatement();
		System.out.println(statement);
		assertEquals("delete from Kontakte where firmenname = ?", statement);
	}
	
	@Test
	public void createUpdateStmntTest1() throws Exception {
		String cn[] = {"firmenname", "ansprechperson"};
		String cond = "firmenname = 'abc'";
		CreateStatement crstmt = new CreateStatement("Update", "Kontakte", cn, cond);
		String statement;
		statement = crstmt.getStatement();
		System.out.println(statement);
		assertEquals("update Kontakte set firmenname = ?, ansprechperson = ? where firmenname = 'abc'", statement);
	}
	
	@Test
	public void createUpdateStmntTest2() throws Exception {
		String cn[] = {"firmenname"};
		String cond = "firmenname = 'abc'";
		CreateStatement crstmt = new CreateStatement("Update", "Kontakte", cn, cond);
		String statement;
		statement = crstmt.getStatement();
		System.out.println(statement);
		assertEquals("update Kontakte set firmenname = ? where firmenname = 'abc'", statement);
	}

}
