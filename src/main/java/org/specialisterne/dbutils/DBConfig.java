package org.specialisterne.dbutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class DBConfig {
	private static SAXBuilder saxBuilder;
	private File source;
	private static final String PATH = "src/dbconfig.xml";        /*System.getProperty("user.dir")+System.getProperty("file.separator")+"data"+System.getProperty("file.separator")+"dbconfig.xml";*/
	private String driver;
	private String regex, user, password, name;
	private List<String> names;
	
	public DBConfig(){
		saxBuilder = new SAXBuilder();
		source = new File(PATH); 
	}
	
	public DBConfig(String name){
		this.name = name;
		saxBuilder = new SAXBuilder();
		source = new File(PATH);
	}
	
	//Daten zur Datenbankverbindung werden aus dbconfig.xml geladen
	public void loadConfig(){
		try {
			Document doc = saxBuilder.build(source);
			Element root = doc.getRootElement();
			@SuppressWarnings("rawtypes")
			List list = root.getChildren("database");
			
			for(int i = 0; i < list.size(); i++){
				Element node = (Element) list.get(i);
				if(this.name.equalsIgnoreCase(node.getChildText("name")) ){
					this.setUser(node.getChildText("user"));
					this.setPassword(node.getChildText("password"));
					this.setRegex(node.getChildText("regex"));
				}
			}
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//Treibername werden aus dbconfig.xml geladen
	public void loadDriver() {
		Document doc;
		try {
			doc = saxBuilder.build(source);
			Element root = doc.getRootElement();
			@SuppressWarnings("rawtypes")
			List list = root.getChildren("database");
			for (int i = 0; i < list.size(); i++) {
			   Element node = (Element) list.get(i);	 
			   this.setDriver(node.getChildText("driver"));
			}
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> getDBnames(){
		Document doc;
		names = new ArrayList<String>();
		try {
			doc = saxBuilder.build(source);
			Element root = doc.getRootElement();
			@SuppressWarnings("rawtypes")
			List list = root.getChildren("database");
			for (int i = 0; i < list.size(); i++) {
			   Element node = (Element) list.get(i);
			   this.name = node.getChildText("name");
			   names.add(i, this.getName());
			}
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		return names;
	}
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
