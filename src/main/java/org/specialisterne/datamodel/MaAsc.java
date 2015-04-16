package org.specialisterne.datamodel;

import java.io.File;
import java.time.Month;
import java.util.List;
import java.util.Map;

public class MaAsc extends Intern{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> position; 
	private Kontakt firma; 
	private Vertrag vertrag; 
	private Map<Month, File> lohnzettel;
	private boolean aktiv;
	
	
	
	public List<String> getPosition() {
		return position;
	}
	public void setPosition(List<String> position) {
		this.position = position;
	}
	public Kontakt getFirma() {
		return firma;
	}
	public void setFirma(Kontakt firma) {
		this.firma = firma;
	}
	public Vertrag getVertrag() {
		return vertrag;
	}
	public void setVertrag(Vertrag vertrag) {
		this.vertrag = vertrag;
	}
	public Map<Month, File> getLohnzettel() {
		return lohnzettel;
	}
	public void setLohnzettel(Map<Month, File> lohnzettel) {
		this.lohnzettel = lohnzettel;
	}
	public boolean isAktiv() {
		return aktiv;
	}
	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}
	
}
