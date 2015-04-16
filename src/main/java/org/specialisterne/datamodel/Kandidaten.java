package org.specialisterne.datamodel;

import java.time.LocalDate;
import java.util.List;

public class Kandidaten extends Intern{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate anmeldeDatum;
	private List<Kontakt> kontakte;
	private List<Vorstellungsgespräch> vorstellungsGespräch;
	private boolean vermittelt;
	
	public LocalDate getAnmeldeDatum() 
	
	{
		return anmeldeDatum;
	}
	public void setAnmeldeDatum(LocalDate anmeldeDatum) {
		this.anmeldeDatum = anmeldeDatum;
	}
	public List<Kontakt> getKontakte() {
		return kontakte;
	}
	public void setKontakte(List<Kontakt> kontakte) {
		this.kontakte = kontakte;
	}
	public List<Vorstellungsgespräch> getVorstellungsGespräch() {
		return vorstellungsGespräch;
	}
	public void setVorstellungsGespräch(
			List<Vorstellungsgespräch> vorstellungsGespräch) {
		this.vorstellungsGespräch = vorstellungsGespräch;
	}
	public boolean isVermittelt() {
		return vermittelt;
	}
	public void setVermittelt(boolean vermittelt) {
		this.vermittelt = vermittelt;
	}
	
	
	
	
}
