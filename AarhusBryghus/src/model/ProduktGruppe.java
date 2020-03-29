package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProduktGruppe implements Serializable {
	private static final long serialVersionUID = 1L;
	private String navn;
	private ArrayList<Produkt> produkter = new ArrayList<>();

	public ProduktGruppe(String navn) {
		this.navn = navn;

	}

	public String getNavn() {
		return navn;
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
		return navn;
	}
}
