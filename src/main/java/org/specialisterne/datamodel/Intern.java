package org.specialisterne.datamodel;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class Intern extends Person{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<File> lebenslauf;
	private Ausbildung ausbildung;
	private LocalDate gebDatum;
	
	
	public List<File> getLebenslauf() {
		return lebenslauf;
	}
	public void setLebenslauf(List<File> lebenslauf) {
		this.lebenslauf = lebenslauf;
	}
	public Ausbildung getAusbildung() {
		return ausbildung;
	}
	public void setAusbildung(Ausbildung ausbildung) {
		this.ausbildung = ausbildung;
	}
	public LocalDate getGebDatum() {
		return gebDatum;
	}
	public void setGebDatum(LocalDate gebDatum) {
		this.gebDatum = gebDatum;
	}
	
}
