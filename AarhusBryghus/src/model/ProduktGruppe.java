package model;

import java.util.ArrayList;

public class ProduktGruppe {
	private String type;
	private String beskrivelse;
	private ArrayList<Produkt> produkter = new ArrayList<>();

	public ProduktGruppe(String type, String beskrivelse) {
		this.type = type;
		this.beskrivelse = beskrivelse;
	}

	public String getType() {
		return type;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void addProdukt(Produkt produkt) {
		if (!produkter.contains(produkt)) {
			produkter.add(produkt);
		}
	}

	public void removeProdukt(Produkt produkt) {
		if (produkter.contains(produkt)) {
			produkter.remove(produkt);
		}
	}

	public ArrayList<Produkt> getProdukter() {
		return new ArrayList<>(produkter);
	}

	@Override
	public String toString() {
		return type + ", " + beskrivelse;
	}
}
