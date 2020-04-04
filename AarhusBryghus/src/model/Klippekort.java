package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Klippekort implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int startId = 1000;
	private int klipId;
	private int antalKlip;
	
	public Klippekort() {
		this.antalKlip = 4;
		this.klipId = ++startId;
	}
	
	/**
	 * 
	 * @param pgList - Liste med produktGrupper af de produkter de skal købes
	 * @return -Retunerer true hvis der er nok klip på klippekortet og false, hvis der ikke er.
	 */
	public boolean brugKlip(ArrayList<ProduktGruppe> pgList) {
		int klipHolder = 4;
		if (pgList != null) {
			for (ProduktGruppe produktGruppe : pgList) {
				switch (produktGruppe.getNavn()) {
				case "fadøl":
					klipHolder -=1;
					break;
				case "flaskeøl":
					klipHolder -=2;
					break;
				}
			}
		}
		if (antalKlip - klipHolder < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public int getAntalKlip() {
		return antalKlip;
	}
	
	public int getKlipId() {
		return klipId;
	}

}
