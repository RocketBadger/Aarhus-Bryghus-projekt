package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Klippekort implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int startId = 1000;
	
	private int klipId;
	private int antalKlip;

	public Klippekort() {
		this.klipId = ++startId;
		this.antalKlip = 4;
	}

	/**
	 * Beregner hvor mange klip der er til rådighed på et givent klippekort og
	 * bruger det nødvendige antal, hvis der er nok til indholdet af listen
	 * 
	 * @param pgList - Liste med produktGrupper af de produkter de skal købes
	 * @return -Returnerer true hvis der er nok klip på klippekortet og false, hvis
	 *         der ikke er.
	 */
	public boolean brugKlip(ArrayList<ProduktGruppe> pgList) {
		int needed = 0;
		for (ProduktGruppe pg : pgList) {
			if (pg.getNavn().equals("fadøl")) {
				needed += 1;
			}
			if (pg.getNavn().equals("flaskeøl")) {
				needed += 2;
			}
		}
		if (this.antalKlip < needed)
			return false;
		else {
			if (pgList != null) {
				for (ProduktGruppe produktGruppe : pgList) {
					if (produktGruppe.getNavn().equals("fadøl") && this.antalKlip >= 1) {
						this.antalKlip -= 1;
					}
					if (produktGruppe.getNavn().equals("flaskeøl") && this.antalKlip >= 2) {
						this.antalKlip -= 2;
					}
				}
			}
			return true;
		}
	}

	public int getAntalKlip() {
		return antalKlip;
	}

	public int getKlipId() {
		return klipId;
	}

	@Override
	public String toString() {
		return "Klippekort";
	}

}
