package model;

import java.util.ArrayList;

public class PrisListe {

	private ArrayList<Pris> prislister = new ArrayList<>();
	private String navn;

	public PrisListe(String navn) {
		this.navn = navn;
	}

	public String getNavn() {
		return navn;
	}

	public void addPris(Pris pris) {
		if (!prislister.contains(pris)) {
			prislister.add(pris);
		}
	}

	public void removePris(Pris pris) {
		if (prislister.contains(pris)) {
			prislister.remove(pris);
		}
	}

	public ArrayList<Pris> getAllPriser() {
		return new ArrayList<>(prislister);
	}

	@Override
	public String toString() {
		return navn;
	}
}
