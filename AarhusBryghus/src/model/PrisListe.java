package model;

import java.io.Serializable;
import java.util.ArrayList;

public class PrisListe implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Pris> priser = new ArrayList<>();
	private String navn;

	public PrisListe(String navn) {
		this.navn = navn;
	}

	public String getNavn() {
		return navn;
	}

	/**
	 * Tilføjer en pris på et produkt til prislisten.
	 * 
	 * @param pris - Prisen der skal tilføjes til priser i prislisten.
	 */
	public void addPris(Pris pris) {
		if (!priser.contains(pris)) {
			priser.add(pris);
			pris.setPrisListe(this);
		}
	}

	/**
	 * 
	 * @param pris - Prisen som ønskes slettet fra prislisten.
	 */
	public void removePris(Pris pris) {
		if (priser.contains(pris)) {
			priser.remove(pris);
			pris.removePrisListe();
		}
	}

	public ArrayList<Pris> getAllPriser() {
		return new ArrayList<>(priser);
	}

	@Override
	public String toString() {
		return navn;
	}
}
